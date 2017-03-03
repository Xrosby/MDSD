package statemachine.year3.dsl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import statemachine.year2.framework.AbstractRuntimeState;

public class GenericState extends AbstractRuntimeState<GenericState> {

	private Map<String,Integer> values = new HashMap<>();

	public GenericState(Set<String> variables) {
		for(String v: variables)
			values.put(v, 0);
	}

	public Integer get(String name) {
		if(!values.containsKey(name)) throw new Error("Undeclared variable: "+name);
		return values.get(name);
	}

	public void set(String name, int value) {
		if(!values.containsKey(name)) throw new Error("Undeclared variable: "+name);
		values.put(name, value);
	}

}
