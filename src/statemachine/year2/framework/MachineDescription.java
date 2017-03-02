package statemachine.year2.framework;

import java.util.List;

/**
 * State machine description: static description of the
 * structure of a given statemachine (will eventually be
 * evolved to play the metamodel role)
 * @author ups
 */
public abstract class MachineDescription<T extends AbstractRuntimeState<T>> {
    /**
     * Overridden by concrete state machine.  By convention the first element must be
     * the initial state.
     * @return List of all states, first element is initial state
     */
    protected abstract List<State<T>> getAllStates();

	protected abstract T createExtendedState();
}
