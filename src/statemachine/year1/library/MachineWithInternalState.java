package statemachine.year1.library;

public abstract class MachineWithInternalState extends Machine<SimpleRuntimeState> {
	@Override
	protected SimpleRuntimeState createCurrentState() { return new SimpleRuntimeState(); }
}
