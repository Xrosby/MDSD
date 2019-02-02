package statemachine.generic;

import quickqui.QuickGUI;
import quickqui.QuickGUI.GUIModel.Layout;

/**
 * GUI for CD player test
 */
public class CDPlayerGUI extends QuickGUI.GUIModel {
    
    public static String POWER_ON_COMMAND = "__ON__";
    
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