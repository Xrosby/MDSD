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
package statemachine.year3.dsl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import statemachine.year2.framework.AbstractRuntimeState;

/**
 * Generic extended state representation based on a map
 * @author ups
 */
public class GenericRuntimeState extends AbstractRuntimeState<GenericRuntimeState> {
	/**
	 * The extended state
	 */
	private Map<String,Integer> values = new HashMap<>();

	/**
	 * Construct a generic state of the given set of variables
	 * @param variables the set of legal names in the state machine
	 */
	public GenericRuntimeState(Set<String> variables) {
		for(String v: variables)
			values.put(v, 0);
	}

	/**
	 * Look up the value of a variable
	 * @param name the name of the variable
	 * @return the value of the variable
	 */
	public Integer get(String name) {
		if(!values.containsKey(name)) throw new Error("Undeclared variable: "+name);
		return values.get(name);
	}

	/**
	 * Set the value of a variable
	 * @param name the name of the variable to set
	 * @param value the value to set the variable to
	 */
	public void set(String name, int value) {
		if(!values.containsKey(name)) throw new Error("Undeclared variable: "+name);
		values.put(name, value);
	}

	/**
	 * Reset the runtime state
	 */
	public void resetExtendedState() { 
		for(Map.Entry<String, Integer> vars: values.entrySet())
			vars.setValue(0);
	}
	
	/**
	 * Get string presentation of the value of an extended state variable
	 * @param what the name of the variable
	 * @return string representation of the state
	 */
	@Override public String getExtendedState(String what) {
		if(what==null)
			return super.getExtendedState(what);
		else
			return this.get(what).toString();
	}

}
