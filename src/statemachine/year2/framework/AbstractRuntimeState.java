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
package statemachine.year2.framework;

/**
 * Abstract baseclass for statemachine runtime states that can be specialized
 * to hold the extended state required for a given statemachine
 * @param <T> The specific type required to hold the extended state
 * @author ups
 */
public abstract class AbstractRuntimeState<T extends AbstractRuntimeState<T>> {
	/**
	 * The current state of the statemachine
	 */
	private State<T> currentState;
	/**
	 * Set the current state of the statemachine
	 * @param state the new state
	 */
	public void setState(State<T> state) { currentState = state; }
	/**
	 * Get the current state of the statemachine
	 * @return the current state
	 */
	public State<T> getState() { return currentState; }
	/**
	 * Reset the runtime state
	 */
	public void resetExtendedState() { ; }
	/**
	 * Get the name of the currently executing state
	 * @return The name of the current state
	 */
	public String getStateName() { return currentState.toString(); }
	/**
	 * Get string presentation of the value of an extended state variable
	 * @param what the name of the varialbe
	 * @return string representation of the state
	 */
	public String getExtendedState(String what) {
		if(what==null)
			return currentState.getName();
		else
			throw new Error("Bad name for extended state: "+what);
	}
}
