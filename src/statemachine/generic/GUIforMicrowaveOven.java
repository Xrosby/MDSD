package statemachine.generic;

import quickqui.QuickGUI;
import quickqui.QuickGUI.GUIModel.Layout;

/**
 * GUI for microwave oven test
 */
public class GUIforMicrowaveOven extends QuickGUI.GUIModel {
    
    public static String POWER_ON_COMMAND = "__ON__";
    
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