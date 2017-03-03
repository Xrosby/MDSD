package statemachine.year1.library;

public class SimpleRuntimeState implements IRuntimeState {
	private State currentState;
	public void setState(State state) { currentState = state; }
	public State getState() { return currentState; }
	public void reset() { ; }
	public String getStateName() { return currentState.toString(); }
}
