package statemachine.year4.codegen;

import statemachine.year2.framework.Transition;
import statemachine.year3.dsl.FluentMachine.Condition;
import statemachine.year3.dsl.FluentMachine.Effect;

/**
 * Data value object for transitions
 * @author ups
 *
 */
public class TransitionHolder extends Transition {
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
