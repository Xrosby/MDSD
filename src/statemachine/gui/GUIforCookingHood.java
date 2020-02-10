package statemachine.gui;

import javax.swing.JLabel;

import examples.gui_nested.QuickNestedGUI;
import qui_generic_model.QPanel.Layout;
import statemachine.generic.IMachine;

/**
 * GUI for cooking hood test
 */
public class GUIforCookingHood extends GraphicalMachine { 

	public GUIforCookingHood(IMachine machine) {
		super(new GUI(), machine, POWER_ON_COMMAND);
	}

	private static String POWER_ON_COMMAND = "__ON__";

	/**
	 * Handle updates to the state machine, display the current state in the GUI
	 */
	@Override
	public void update() {
		((JLabel)gui.getComponent("state")).setText(machine.getRuntimeStateFor(null));
		((JLabel)gui.getComponent("power")).setText(machine.getRuntimeStateFor("power"));
	}

	private static class GUI extends QuickNestedGUI.GUIBuilder {

		@Override 
		public void build() {
			frame("Cooking hood",Layout.VERTICAL,
					panel(Layout.HORIZONTAL,
							label(text("Current state: ")),
							label(name("state"),text("?"))),
					panel(Layout.HORIZONTAL,
							label(text("Current motor power: ")),
							label(name("power"),text("?"))),
					panel(Layout.HORIZONTAL,
							label(text("Controls: ")),
							button(name("PLUS"),text("(+)")),
							button(name("MINUS"),text("(-)"))
							),
					button(name(POWER_ON_COMMAND),text("Power on machine"))
					)
			;
		}
	}
}
