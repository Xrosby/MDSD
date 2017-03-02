package statemachine.year1.library;

public class RuntimeState {
	private State currentState;
	public void setState(State state) { currentState = state; }
	public State getState() { return currentState; }
	public void reset() { currentState = null; }
}
