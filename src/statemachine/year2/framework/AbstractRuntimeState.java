package statemachine.year2.framework;

import statemachine.year1.library.RuntimeState;

public class AbstractRuntimeState<T extends AbstractRuntimeState<T>> implements RuntimeState {
	private State<T> currentState;
	public void setState(State<T> state) { currentState = state; }
	public State<T> getState() { return currentState; }
	public void reset() { ; }
	public String getStateName() { return currentState.toString(); }
}
