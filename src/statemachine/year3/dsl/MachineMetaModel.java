/*
Copyright (c) 2018, Ulrik Pagh Schultz, University of Southern Denmark
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import statemachine.year2.framework.MachineDescription;
import statemachine.year2.framework.State;

/**
 * Metamodel for the state machine, constructed by FluentMachine and used at runtime by MachineExecutor
 * @author ups
 */
public class MachineMetaModel implements MachineDescription<GenericRuntimeState> {

	/**
	 *  The complete list of all states (first is assumed to be initial)
	 */
	private List<State<GenericRuntimeState>> allStates;
	
	/**
	 * Set containing names of all extended state variables
	 */
	private Set<String> extendedStateVariables;
	
	/**
	 * Initialize metamodel
	 */
	public MachineMetaModel(List<State<GenericRuntimeState>> states, Set<String> variables) {
		this.allStates = new ArrayList<State<GenericRuntimeState>>(states);
		this.extendedStateVariables = new HashSet<>(variables);
	}

	/**
	 * Get all states in the metamodel
	 * @return the list of states, read-only
	 */
	public List<State<GenericRuntimeState>> getAllStates() {
		return Collections.unmodifiableList(allStates);
	}

	/**
	 * Get the names of all extended state variables in the metamodel
	 * @return the list of names, read-only
	 */
	public Set<String> getExtendedStateVariables() {
		return Collections.unmodifiableSet(extendedStateVariables);
	}
	
	/**
	 * Create a runtime state representation based on this metamodel
	 * @return a runtime state object representing the current state of the metamodel
	 */
	@Override
	public GenericRuntimeState createRuntimeState() {
		return new GenericRuntimeState(extendedStateVariables);
	}
}