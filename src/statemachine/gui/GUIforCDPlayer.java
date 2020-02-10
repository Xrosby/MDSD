package statemachine.gui;

import javax.swing.JLabel;

import examples.gui_nested.QuickNestedGUI;
import qui_generic_model.QPanel.Layout;
import statemachine.generic.IMachine;

/**
 * GUI for CD player test
 */
public class GUIforCDPlayer extends GraphicalMachine {

	public GUIforCDPlayer(IMachine machine) {
		super(new GUI(), machine, POWER_ON_COMMAND);
	}

	private static String POWER_ON_COMMAND = "__ON__";

	/**
	 * Handle updates to the state machine, display the current state in the GUI
	 */
	@Override
	public void update() {
		((JLabel)gui.getComponent("state")).setText(machine.getRuntimeStateFor(null));
		((JLabel)gui.getComponent("track")).setText(machine.getRuntimeStateFor("track"));
	}

	private static class GUI extends QuickNestedGUI.GUIBuilder {

		@Override 
		public void build() {
			frame("CD Player",Layout.VERTICAL,
					panel(Layout.HORIZONTAL,
							label(text("Current state: ")),
							label(name("state"),text("?"))),
					panel(Layout.HORIZONTAL,
							label(text("Current track: ")),
							label(name("track"),text("?"))),
					panel(Layout.HORIZONTAL,
							label(text("Controls: ")),
							button(name("PLAY"),text("Play")),
							button(name("STOP"),text("Stop")),
							button(name("PAUSE"),text("Pause")),
							button(name("FORWARD"),text("Forward")),
							button(name("BACK"),text("Back"))),
					panel(Layout.HORIZONTAL,
							label(text("Events: ")),
							button(name("TRACK_END"),text("End of track"))),
					button(name(POWER_ON_COMMAND),text("Power on machine"))
					)
			;
		}
	}
}