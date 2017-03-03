package statemachine.year2.framework;

import statemachine.year1.library.IRuntimeState;

public abstract class AbstractRuntimeState<T extends AbstractRuntimeState<T>> implements IRuntimeState {
	private State<T> currentState;
	public void setState(State<T> state) { currentState = state; }
	public State<T> getState() { return currentState; }
	public void reset() { ; }
	public String getStateName() { return currentState.toString(); }
}
