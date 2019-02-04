/*
Copyright (c) 2017, Ulrik Pagh Schultz, University of Southern Denmark
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the University of Southern Denmark.
*/
package statemachine.year5.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import statemachine.year3.dsl.FluentMachine;

/**
 * Parse a source file and use it to build a semantic model using the existing fluent machine interface.
 * @author ups
 *
 */
public class MachineParser extends FluentMachine {

	/**
	 * Map of external variables
	 */
	private Set<String> externalVariables = new HashSet<String>();
	/**
	 * The input text to parse
	 */
	private BufferedReader stream;
	/**
	 * The full name of the machine
	 */
	private String name;
	
	/**
	 * Create a parser for the given input stream
	 * @param stream the input to parse
	 */
	public MachineParser(BufferedReader stream) {
		if(stream==null) throw new Error("Illegal stream: null");
		this.stream = stream;
	}
	
	/**
	 * Get the full name of the machine
	 * @return the machine name
	 */
	public String getMachineName() {
		super.buildMachine();
		return name;
	}
	
	/**
	 * Parse the input text, building the semantic model
	 */
	@Override
	protected void build() {
		String line = "EOF";
		try {
			// First line: name
			line = stream.readLine();
			if(line==null) throw new ParseError("Empty file");
			parseStateMachineName(line);
			// Read variable declarations
			do {
				line = stream.readLine();		
			} while(line!=null && parseVariableName(line));
			// Read each state declaration
			while(line!=null && parseStateLine(line)) {
				// Read all transitions of the current state
				do {
					line = stream.readLine();
				} while(line!=null && parseTransitionLine(line));
			}
		} catch(IOException exn) {
			throw new Error("I/O exception reading file: "+exn+" at line "+line);
		} catch(ParseError err) {
			System.err.println("Error parsing file: "+err+" at line "+line);
			throw new Error(err);
		}
	}
	
	/**
	 * Parse the statemachine name declaration
	 * @param line the line to parse
	 * @throws ParseError if the line does not define the name
	 */
	private void parseStateMachineName(String line) throws ParseError {
		Lexer lex = new Lexer(line);
		lex.popAndMatchToken("machine");
		name = lex.popToken("machine name");
	}
	
	/**
	 * Attempt to parse a single line as a variable name declaration
	 * @param line the line to parse
	 * @return true if the line declared a variable, false otherwise
	 * @throws ParseError if the line contained a partial (incorrect) variable declaration
	 */
	private boolean parseVariableName(String line) throws ParseError {
		Lexer lex = new Lexer(line);
		if(!lex.checkMatch("variable")) return false;
		lex.popAndMatchToken("variable");
		makeExternalVariable(lex.popToken("variable name"));
		return true;
	}
	
	/**
	 * Parse the declaration of a state name
	 * @param line the line to parse
	 * @return true if this line declares a state name, false otherwise
	 * @throws ParseError if the line contained a partial (incorrect) state name declaration
	 */
	private boolean parseStateLine(String line) throws ParseError {
		Lexer lex = new Lexer(line);
		if(!lex.checkMatch("state")) return false;
		lex.popAndMatchToken("state");
		super.state(lex.popToken("state"));
		return true;
	}
	
	/**
	 * Parse a single state transition line.  "Else" clauses currently do not support actions.
	 * @param line the line to parse
	 * @return true if the line declared a state transition that was matched, false otherwise
	 * @throws ParseError if the line was recognized as a state transition but had bad syntax
	 */
	private boolean parseTransitionLine(String line) throws ParseError {
		Lexer lex = new Lexer(line);
		if(lex.checkMatch("state")) return false;
		// Else line?
		if(lex.checkMatch("else")) {
			lex.popAndMatchToken("else");
			String state = lex.popToken("state");
			super.to(state);
			super.otherwise();
			// Skip any action
			return true;
		}
		// Event
		String event = lex.popToken("event");
		super.transition(event);
		// Condition?
		String condVariable = null;
		String condOperator = null;
		Integer condValue = null;
		if(lex.checkMatch("[")) {
			lex.popAndMatchToken("[");
			 condVariable = getExtendedVariable(lex.popToken("variable"));
			 condOperator = lex.popToken("operator");
			 condValue = lex.popTokenAsInt();
			 lex.popAndMatchToken("]");
		}
		// Target state
		lex.popAndMatchToken("to");
		super.to(lex.popToken("state"));
		// Action?
		if(lex.checkMatch("/")) {
			lex.popAndMatchToken("/");
			String actionVariable = getExtendedVariable(lex.popToken("variable"));
			String actionOperator = lex.popToken("oeprator");
			Integer actionValue = lex.popTokenAsInt();
			if("=".equals(actionOperator))
				super.setState(actionVariable, actionValue);
			else if("+".equals(actionOperator))
				super.changeState(actionVariable, actionValue);
			else
				throw new ParseError("Illegal action operator: "+actionOperator);
		}
		// Insert condition, if any
		if("=".equals(condOperator))
			super.whenStateEquals(condVariable, condValue);
		else if(">".equals(condOperator))
			super.whenStateGreaterThan(condVariable, condValue);
		else if(condOperator!=null)
			throw new ParseError("Illegal condition operator: "+condOperator);
		return true;
	}

	/**
	 * Get the extended variable of the given name
	 * @param name the name of the extended variable
	 * @return the extended variable of the corresponding name
	 * @throws ParseError if the name was not found
	 */
	private String getExtendedVariable(String name) throws ParseError {
		if(!externalVariables.contains(name)) throw new ParseError("Variable not found: "+name);
		return name;
	}

	/**
	 * Create an external variable, and store it for later retrieval
	 * @param name the name of the external variable
	 */
	private void makeExternalVariable(String name) {
		externalVariables.add(name);
		super.integerState(name);
	}

}
