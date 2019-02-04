package statemachine.generic;

import javax.swing.JLabel;

import quickqui.QuickGUI;
import quickqui.QuickGUI.GUIModel.Layout;

/**
 * GUI for microwave oven test
 */
public class GUIforMicrowaveOven<T extends IRuntime> extends GraphicalMachine<T> {

	public GUIforMicrowaveOven(IMachine<T> machine) {
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


	private static class GUI extends QuickGUI.GUIModel {


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