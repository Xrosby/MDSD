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

import statemachine.year2.framework.Transition;
import statemachine.year3.dsl.FluentMachine.Condition;
import statemachine.year3.dsl.FluentMachine.Effect;
import statemachine.year3.dsl.GenericRuntimeState;

/**
 * Data value object for transitions
 * @author ups
 *
 */
public class TransitionHolder extends Transition<GenericRuntimeState> {
	private Effect effect;
	private String effectVar;
	private int effectArg;
	private Condition cond;
	private String condVariableMaybe;
	private int condValue;
	public TransitionHolder(String target, Effect effect,
			String effectVar, int effectArg, Condition cond,
			String condVariableMaybe, int condValue) {
		super(target);
		this.effect = effect;
		this.effectVar = effectVar;
		this.effectArg = effectArg;
		this.cond = cond;
		this.condVariableMaybe = condVariableMaybe;
		this.condValue = condValue;
	}
	public Effect getEffect() {
		return effect;
	}
	public String getEffectVar() {
		return effectVar;
	}
	public int getEffectArg() {
		return effectArg;
	}
	public Condition getCond() {
		return cond;
	}
	public String getCondVariableMaybe() {
		return condVariableMaybe;
	}
	public int getCondValue() {
		return condValue;
	}

}
