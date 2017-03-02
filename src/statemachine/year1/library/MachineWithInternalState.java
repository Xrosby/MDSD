package statemachine.year1.library;

public abstract class MachineWithInternalState extends Machine<RuntimeState> {
	@Override
	protected RuntimeState createCurrentState() { return new RuntimeState(); }
}
