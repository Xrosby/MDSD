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
import java.util.Map;
import java.util.Observable;

import statemachine.generic.Event;
import statemachine.generic.IMachine;
import statemachine.gui.GraphicalMachine;

/**
 * Abstract class that serves as the basis for code generated for statemachines.
 * Hooks up the regular String-based event and state approach to one based on integer IDs
 * @author ups
 *
 */
public abstract class GeneratedMachine implements IMachine {

	/**
	 * The current state of the state machine, represented as an integer
	 */
	protected int state;
	/**
	 * Map from event names to event IDs
	 */
	private Map<String,Integer> event_code2int = new HashMap<String,Integer>();
	/**
	 * Map from state IDs to state names
	 */
	private Map<Integer,String> state_int2code = new HashMap<Integer,String>();
	/**
     * The GUI attached to this statemachine
     */
    private GraphicalMachine gui;
    
	/**
	 * Initialize the state machine, including the event and state maps
	 */
	@Override
	public void initialize() {
		internalInitialize(event_code2int, state_int2code);
		this.notifyGUI();
	}

	/**
	 * Process an event with the corresponding String-based event code
	 * @param event the event to process
	 */
	@Override
	public void processEvent(Event event) {
		internalProcessEvent(event_code2int.get(event.code()));
		this.notifyGUI();
	}

	/**
	 * Get the name of the currently active state
	 * @return the current state
	 */
	@Override public String getRuntimeStateFor(String what) {
		if(what==null)
			return state_int2code.get(state);
		else
			throw new Error("Illegal extended state name: "+what);
	}
	
    
    /**
     * Notify the GUI that the state changed
     */
	private void notifyGUI() {
		gui.updateGUI();		
	}

	/**
     * Add an observer to the state machine, notified whenever the state changes
     * @param observer the statemachine observer
     */
	public void addGUI(GraphicalMachine graphicalMachine) {
		gui = graphicalMachine;
	}

	/**
	 * Template method for the compiled event processed based on integer state IDs
	 * @param code
	 */
	protected abstract void internalProcessEvent(int code);

	/**
	 * Template method for initializing the maps between state and event names and their corresponding IDs
	 * @param event_code2int Map from event name to event code ID
	 * @param state_int2code Map from state ID to state name
	 */
	protected abstract void internalInitialize(Map<String, Integer> event_code2int, Map<Integer, String> state_int2code);

}
