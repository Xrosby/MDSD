package statemachine.year3.dsl;

import statemachine.year2.framework.Transition;
import statemachine.year3.dsl.FluentMachine.Condition;
import statemachine.year3.dsl.FluentMachine.Effect;

/**
 * Generic transition that performs its function depending on its
 * description of effects and conditions (passed as parameters, at
 * most one of each)
 * @author ups
 */
class GenericTransition extends Transition<GenericRuntimeState> {
	/**
	 * The effect of the transition, if any, null otherwise
	 */
    private Effect effectMaybe;
    /**
     * Variable on which the transition has an effect, if any, null otherwise
     */
    private String effectVariableName;
    /**
     * The argument of the effect (e.g., how much is added) if any, null otherwise
     */
    private int effectArgument;
    /**
     * The condition type on the transition, if any
     */
    private Condition conditionMaybe;
    /**
     * Condition variable on the transition, if any, null otherwise
     */
    private String condVariableNameMaybe;
    /**
     * The value which the condition compares to (e.g., is equal to?) if any
     */
    private int condValue;

    /**
     * Create a generic transition specified according to the arguments.
     * @param target The target state
     * @param effectMaybe The effect of the transition, if any, null otherwise
     * @param effectVariable Variable on which the transition has an effect, if any, null otherwise
     * @param effectArgument The argument of the effect (e.g., how much is added) if any, null otherwise
     * @param cond The condition type on the transition, if any
     * @param condVariableMaybe Condition variable on the transition, if any, null otherwise
     * @param condValue The value which the condition compares to (e.g., is equal to?) if any
     */
    public GenericTransition(String target, 
            Effect effectMaybe, String effectVariableName, int effectArgument, 
            Condition cond, String condVariableNameMaybe, int condValue) {
        super(target);
        this.effectMaybe = effectMaybe; this.effectVariableName = effectVariableName; this.effectArgument = effectArgument; 
        this.conditionMaybe = cond; this.condVariableNameMaybe = condVariableNameMaybe; this.condValue = condValue;
        if(effectMaybe!=null && effectVariableName==null) throw new Error("Inconistent effect description");
    }
    
    /**
     * True is the transition is applicable in the current state
     */
    @Override public boolean isApplicable(GenericRuntimeState extendedState) {
        if(conditionMaybe==null) return true; // no condition
        if(conditionMaybe==Condition.EQUAL) {
            return extendedState.get(condVariableNameMaybe).intValue()==condValue;
        } else if(conditionMaybe==Condition.GREATER) {
            return extendedState.get(condVariableNameMaybe).intValue()>condValue;
        } else 
            throw new Error("Illegal condition kind");
    }
    
    /**
     * Perform the effect of the transition
     */
    @Override public void effect(GenericRuntimeState extendedState) {
        if(effectMaybe==null) return; // no effect
        if(effectMaybe==Effect.SET)
            extendedState.set(effectVariableName,effectArgument);
        else if(effectMaybe==Effect.CHANGE)
            extendedState.set(effectVariableName,extendedState.get(effectVariableName).intValue()+effectArgument);
        else
            throw new Error("Uknown effect");
    }

    /**
     * String representation (for debugging)
     */
    public String toString() {
        return "T("+super.getTarget()+"): "+effectMaybe + "@" + effectVariableName + "," + effectArgument;
    }
}