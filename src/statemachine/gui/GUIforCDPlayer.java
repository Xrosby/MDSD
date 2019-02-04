package statemachine.generic;

import javax.swing.JLabel;

import quickqui.QuickGUI;
import quickqui.QuickGUI.GUIModel;
import quickqui.QuickGUI.GUIModel.Layout;

/**
 * GUI for CD player test
 */
public abstract class GUIforCDPlayer<T extends IRuntime> extends GraphicalMachine<T> {

	public GUIforCDPlayer(IMachine<T> machine) {
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

	private static class GUI extends QuickGUI.GUIModel {

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