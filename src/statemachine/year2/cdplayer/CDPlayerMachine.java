/*
Copyright (c) 2012, Ulrik Pagh Schultz, University of Southern Denmark
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

package statemachine.year2.cdplayer;

import java.util.Arrays;
import java.util.List;

import statemachine.year2.framework.AbstractRuntimeState;
import statemachine.year2.framework.MachineDescription;
import statemachine.year2.framework.State;
import statemachine.year2.framework.Transition;
import statemachine.year2.cdplayer.CDPlayerMachine.CDPlayer;

public class CDPlayerMachine implements MachineDescription<CDPlayer> {

	public static class CDPlayer extends AbstractRuntimeState<CDPlayer> {
		public int track;
		@Override public void resetExtendedState() { track = 0; }
		@Override public String getExtendedState(String what) { 
			return "track".equals(what) ? Integer.toString(track):super.getExtendedState(what); 
		}
	}
	
    // States
    private State<CDPlayer> STATE_STOP, STATE_PLAYING, STATE_PAUSED;
    
    // State machine definition
    public CDPlayerMachine() {
        // Forward and backward transitions are identical in STOPPED and PAUSED, share
        Transition<CDPlayer> forward = new Transition<CDPlayer>(null) {
            @Override public void effect(CDPlayer state) { state.track++; }
        };
        Transition<CDPlayer> backward = new Transition<CDPlayer>(null) {
            @Override public boolean isApplicable(CDPlayer state) { return state.track>1; }
            @Override public void effect(CDPlayer state) { state.track--; }
        };
        // Stop transition is identical in PLAYING and PAUSED, share
        Transition<CDPlayer> stop = new Transition<CDPlayer>("STOP") {
            @Override public void effect(CDPlayer state) { state.track=0; } 
        };
        // Define states and transitions
        STATE_STOP = new State<CDPlayer>("STOP");
        STATE_STOP.addTransition("PLAY", new Transition<CDPlayer>("PLAYING") {
           @Override public void effect(CDPlayer state) { if(state.track==0) state.track=1; } 
        });
        STATE_STOP.addTransition("FORWARD", forward);
        STATE_STOP.addTransition("BACK", backward);
        STATE_PLAYING = new State<CDPlayer>("PLAYING");
        STATE_PLAYING.addTransition("STOP", stop);
        STATE_PLAYING.addTransition("PAUSE", new Transition<CDPlayer>("PAUSED"));
        STATE_PLAYING.addTransition("TRACK_END", new Transition<CDPlayer>(null) {
            @Override public void effect(CDPlayer state) { state.track++; }
        });
        STATE_PAUSED = new State<CDPlayer>("PAUSED");
        STATE_PAUSED.addTransition("PLAY", new Transition<CDPlayer>("PLAYING"));
        STATE_PAUSED.addTransition("STOP", stop);
        STATE_PAUSED.addTransition("FORWARD", forward);
        STATE_PAUSED.addTransition("BACK", backward);
    }
    
    @Override
    public List<State<CDPlayer>> getAllStates() {
        return Arrays.asList(STATE_STOP, STATE_PLAYING, STATE_PAUSED);
    }

	@Override
	public CDPlayer createRuntimeState() {
		return new CDPlayer();
	}

}
