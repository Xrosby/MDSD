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

import java.util.StringTokenizer;

/**
 * Simple lexer based on standard tokenizer, allows matching without removing tokens
 * from the stream. Operates on a single line at a time.
 * @author ups
 *
 */
public class Lexer {

	/**
	 * Tokenizer for breaking the line into words
	 */
	private StringTokenizer tokenizer;
	
	/**
	 * The current token, if any, null otherwise
	 */
	private String current;

	/**
	 * Create lexer for the given line
	 * @param line the line to be lexed
	 */
	public Lexer(String line) {
		tokenizer = new StringTokenizer(line);
	}

	/**
	 * Get the current token, either the stored one, or the first available one from token stream
	 * @return the current token, or null if none
	 */
	private String getCurrentToken() {
		if(!tokenizer.hasMoreTokens()) return null;
		if(current==null) current = tokenizer.nextToken();
		return current;
	}
	
	/**
	 * Check if the current token matches the given string
	 * @param expected the string to compare to the current token
	 * @return true if is matches, false otherwise
	 */
	public boolean checkMatch(String expected) {
		String n = getCurrentToken();
		if(n==null) return false;
		return n.equals(expected);
	}

	/**
	 * Removes the current token, generating an error if there are no tokens
	 * @param expectedKind the expected kind of token, used to signal an error
	 * @return the current token
	 * @throws ParseError if there are no more token available
	 */
	public String popToken(String expectedKind) throws ParseError {
		String n = getCurrentToken();
		if(n==null) throw new ParseError("Expected token "+expectedKind+", but end of line");
		current = null;
		return n;
	}

	/**
	 * Removes the current token, checking that it matches the provided text, signalling an error otherwise
	 * @param expected the text that the current token must match
	 * @throws ParseError if the current token does not match the expected text
	 */
	public void popAndMatchToken(String expected) throws ParseError {
		String n = getCurrentToken();
		if(n==null) throw new ParseError("Expected token "+expected+", but end of line");
		if(!n.equals(expected)) throw new ParseError("Expected token "+expected+", but got "+n);
		current = null;
	}

	/**
	 * Removes the current token, and converts it to an integer
	 * @return the integer value of the token
	 * @throws ParseError if there was no token or if the token was not a number
	 */
	public Integer popTokenAsInt() throws ParseError {
		String n = getCurrentToken();
		if(n==null) throw new ParseError("Expected number token, but end of line");
		Integer i = Integer.parseInt(n);
		if(i==null) throw new ParseError("Expected number token, but got "+i);
		current = null;
		return i;
	}

}
