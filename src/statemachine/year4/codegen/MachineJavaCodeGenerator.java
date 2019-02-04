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
package statemachine.year4.codegen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import statemachine.year2.framework.State;
import statemachine.year2.framework.Transition;
import statemachine.year3.dsl.FluentMachine;
import statemachine.year3.dsl.FluentMachine.Condition;
import statemachine.year3.dsl.FluentMachine.Effect;
import statemachine.year3.dsl.GenericRuntimeState;
import statemachine.year3.dsl.MachineMetaModel;

/**
 * Code generator for state machines described using the FluentMachine model.
 * Generates a single class definition that subclasses GeneratedMachine and
 * implements the two methods for processing events and for initializing the
 * maps between names and integer IDs.  Moreover, also generates fields and
 * getter methods for extended state.
 * @author ups
 *
 */
public class MachineJavaCodeGenerator {

	/**
	 * The model according to which the code is generated
	 */
	private MachineMetaModel model;
	/**
	 * The buffer in which the output string (the classs definition) is accumulated
	 */
	private StringBuilder builder;
	/**
	 * The set of extended state variables used in the state machine.  Accumulates during
	 * generation and used to create definitions and accessor methods at the end
	 */
	private Set<String> variables;
	/**
	 * Map from event names to their corresponding integer ID
	 */
	private Map<String,Integer> eventMap;
	/**
	 * Map from state names to their corresponding integer ID
	 */
	private Map<String,Integer> stateMap;
	/**
	 * Counter for assigning fresh IDs to event names
	 */
	private int eventIDcounter;
	/**
	 * Counter for assigned fresh IDs to state names
	 */
	private int stateIDcounter;
	
	/**
	 * Create a code generator for the corresponding state machine model
	 * @param model the state machine for which code is to be generated
	 */
	public MachineJavaCodeGenerator(FluentMachine machine) {
		this.model = machine.getMetaModel();
	}

	/**
	 * Generate code for the corresponding state machine, named according to the supplied class
	 * name and package name. 
	 * @param packageName the package name to use for the generated class definition
	 * @param className the class name to use for the generated class definition
	 * @return the text of a complete class definition
	 */
	public String generate(String packageName, String className) {
		builder = new StringBuilder();
		variables = new HashSet<String>();
		eventMap = new HashMap<String,Integer>();
		stateMap = new HashMap<String,Integer>();
		eventIDcounter = 0;
		stateIDcounter = 0;
		generateHeader(packageName, className);
		beginGenerateStates();
		for(State<GenericRuntimeState> state: model.getAllStates())
			generateState(state);
		finishGenerateStates();
		generateVariableDeclarations();
		generateInitializer();
		generateFooter();
		return builder.toString();
	}

	/**
	 * Map a state name to an integer ID, creates new IDs on-demand
	 * @param name name of a state of the state machine
	 * @return a unique ID corresponding to that exact state name
	 */
	private int getStateID(String name) {
		if(name==null) throw new Error("Illegal null name");
		if(stateMap.get(name)==null)
			stateMap.put(name, stateIDcounter++);
		return stateMap.get(name);
	}
	
	/**
	 * Map an event name to an integer ID, creates new IDs on-demand
	 * @param name name of an event that the state machine can respond to
	 * @return a unique ID corresponding to that exact event name
	 */
	private int getEventID(String name) {
		if(name==null) throw new Error("Illegal null event");
		if(eventMap.get(name)==null)
			eventMap.put(name, eventIDcounter++);
		return eventMap.get(name);
	}
	
	/**
	 * Convenience method for appending text to the accumulating buffer and then adding a newline
	 * @param text the text to append to the buffer
	 */
	private void p(String text) {
		builder.append(text);
		builder.append('\n');
	}
	
	/**
	 * Generate the class header: package, import, and class declaration
	 * @param packageName the package name to use
	 * @param className the class name to use
	 */
	private void generateHeader(String packageName, String className) {
		p("// Automatically generated code, do not edit");
		p("package "+packageName+";");
		p("import java.util.Map;");
		p("import statemachine.year4.codegen.GeneratedMachine;");
		p("public class "+className+" extends GeneratedMachine {");
	}
	
	/**
	 * Generate the method header and initial code for the state processing method
	 */
	private void beginGenerateStates() {
		p("  @Override protected void internalProcessEvent(int event) {");
		p("    switch(state) {");
	}
	
	/**
	 * Generate the code for handling a single state
	 * @param state the state to handle
	 */
	private void generateState(State<GenericRuntimeState> state) {
		// CASE STATE:
		p("    case "+getStateID(state.getName())+": // "+state.getName());
		p("      switch(event) {");
		for(String event: state.getApplicableEvents()) {
			// CASE EVENT:
			p("      case "+getEventID(event)+": // "+event);
			boolean first = true;
			for(Transition<GenericRuntimeState> transition: state.getTransitionsForEvent(event)) {
				// IF(TRANSITION IS APPLICABLE)
				if(first)
					first = false;
				else
					p("      else");
				TransitionHolder holder = (TransitionHolder)transition;
				String indent = "        ";
				if(holder.getCond()!=null) {
					// What kind of condition?
					Condition c = holder.getCond();
					String operator;
					if(c==Condition.EQUAL) operator = "==";
					else if(c==Condition.GREATER) operator = ">";
					else throw new Error("Internal error");
					// Generate condition
					String var = holder.getCondVariableMaybe();
					variables.add(var);
					p(indent+"if("+var+operator+holder.getCondValue()+") {");
				} 
				else 
					p(indent+"{");
				// TRANSITION EFFECT
				if(holder.getEffect()!=null) {
					// What kind of effect?
					Effect e = holder.getEffect();
					String operator;
					if(e==Effect.CHANGE) operator = "+=";
					else if(e==Effect.SET) operator = "=";
					else throw new Error("Internal error");
					// Generate effect
					String var = holder.getEffectVar();
					variables.add(var);
					p(indent+"  "+var+operator+holder.getEffectArg()+";");
				}
				// TRANSITION CHANGE STATE
				if(holder.getTarget()!=null) p(indent+"  state = "+getStateID(holder.getTarget())+"; // "+holder.getTarget());
				p(indent+"}");
			}
			p("      break;");
		}
		p("      default: ; // ignore");
		p("      }");
		p("    break;");
	}

	/**
	 * Generate the end of the event processing method
	 */
	private void finishGenerateStates() {
		p("    default: throw new Error(\"Internal error: unsupported state code\");");
		p("    }");
		p("  }");
	}

	/**
	 * Generate declarations and getter methods for extended state
	 */
	private void generateVariableDeclarations() {
		for(String var: variables) {
			p("  private int "+var+";");
			p("  /** Get the value of the extended state "+var);
			p("    * @return value of "+var);
			p("  */");
			p("  public int get_"+var+"() { return "+var+"; }");
			p("  @Override public String getRuntimeStateFor(String what) {");
			p("    return \""+var+"\".equals(what)?Integer.toString("+var+"):super.getRuntimeStateFor(what);");
			p("  }");
		}
	}

	/**
	 * Generate initializer method for establishing maps between IDs and corresponding names
	 */
	private void generateInitializer() {
		p("  @Override protected void internalInitialize(Map<String, Integer> event_code2int, Map<Integer, String> state_int2code) {");
		for(Map.Entry<String, Integer> state: stateMap.entrySet())
			p("    state_int2code.put("+state.getValue()+",\""+state.getKey()+"\");");
		for(Map.Entry<String, Integer> event: eventMap.entrySet())
			p("    event_code2int.put(\""+event.getKey()+"\","+event.getValue()+");");
		p("  }");
	}

	/**
	 * Generate end of the class declaration
	 */
	private void generateFooter() {
		p("}");
	}

}
