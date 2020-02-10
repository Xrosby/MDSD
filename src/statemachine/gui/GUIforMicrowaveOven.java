package statemachine.gui;

import javax.swing.JLabel;

import examples.gui_nested.QuickNestedGUI;
import qui_generic_model.QPanel.Layout;
import statemachine.generic.IMachine;

/**
 * GUI for microwave oven test
 */
public class GUIforMicrowaveOven extends GraphicalMachine {

	public GUIforMicrowaveOven(IMachine machine) {
		super(new GUI(), machine, POWER_ON_COMMAND);
	}

	public static String POWER_ON_COMMAND = "__ON__";

	/**
	 * Handle updates to the state machine, display the current state in the GUI
	 */
	@Override
	public void update() {
		((JLabel)gui.getComponent("state")).setText(machine.getRuntimeStateFor(null));
	}

	private static class GUI extends QuickNestedGUI.GUIBuilder {

		@Override 
		public void build() {
			frame("Microwave oven",Layout.VERTICAL,
					panel(Layout.HORIZONTAL,
							label(text("Current state: ")),
							label(name("state"),text("?"))),
					panel(Layout.HORIZONTAL,
							label(text("Controls: ")),
							button(name("START"),text("Start")),
							button(name("STOP"),text("Stop"))
							),
					panel(Layout.HORIZONTAL,
							label(text("Door: ")),
							button(name("OPEN"),text("Open")),
							button(name("CLOSE"),text("Close"))
							),
					panel(Layout.HORIZONTAL,
							label(text("Timer: ")),
							button(name("TIMER"),text("Trigger"))
							),
					button(name(POWER_ON_COMMAND),text("Power on machine"))
					)
			;
		}
	}
}