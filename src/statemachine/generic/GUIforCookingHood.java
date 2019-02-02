package statemachine.generic;

import quickqui.QuickGUI;
import quickqui.QuickGUI.GUIModel.Layout;

/**
 * GUI for cooking hood test
 */
public class GUIforCookingHood extends QuickGUI.GUIModel {
    
    public static String POWER_ON_COMMAND = "__ON__";
    
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