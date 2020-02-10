/*
Copyright (c) 2012, Ulrik Pagh Schultz, University of Southern Denmark
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the University of Southern Denmark.
*/

package examples.gui_nested;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;

import examples.communication.WindowControllerDevice;
import qui_generic_model.QPanel.Layout;

/**
 * Extension of the window controller device server to display information in GUI and make
 * control of temperature sensor available as a user interface
 * @author ups
 */
public class WindowDeviceGUI extends WindowControllerDevice implements ActionListener {
    
    /**
     * The specific GUI to use for this application
     * @author ups
     */
    public static class ControlGUI extends QuickNestedGUI.GUIBuilder {
        
        @Override 
        public void build() {
            frame("Window controller with temperature sensor",Layout.VERTICAL,
                label(name("position"),text("Target position of window")),
                label(name("speed"),text("Speed of positioning")),
                panel(Layout.HORIZONTAL,
                  label(text("Temperature: ")),
                  button(name("tLow"),text("Low")),
                  button(name("tMedium"),text("Medium")),
                  button(name("tHigh"),text("High"))
                ),
                label(name("status"),text("Initializing"))
              )
            ;
        }
    }

    /**
     * Create GUI and then activate device server functionality
     */
    public static void main(String argv[]) throws IOException {
        WindowDeviceGUI device = new WindowDeviceGUI();
        device.gui = new QuickNestedGUI(new ControlGUI(),device);
        device.main();
    }

    /**
     * Temperature adjustment: low, medium, high, NONE if not set
     */
    private String temperatureLevel = "NONE";
    
    /**
     * The GUI 
     */
    private QuickNestedGUI gui;
    
    /**
     * Respond to GUI events
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("tLow")) temperatureLevel = "LOW";
        else if(event.getActionCommand().equals("tMedium")) temperatureLevel = "MEDIUM";
        else if(event.getActionCommand().equals("tHigh")) temperatureLevel = "HIGH";
        else System.err.println("Warning: unknown event "+event);
    }

    /**
     * Respond to a set position command received over the network
     */
    @Override
    protected void setPosition(int position, int speed) {
        JLabel posLabel = (JLabel)gui.getComponent("position");
        JLabel spdLabel = (JLabel)gui.getComponent("speed");
        posLabel.setText("Left="+position);
        spdLabel.setText("Right="+speed);
        System.out.println("Moving window to "+position+" at "+speed);
    }

    /**
     * Respond to a stop command received over the network
     */
    @Override
    protected void positionStop() {
        JLabel posLabel = (JLabel)gui.getComponent("position");
        JLabel spdLabel = (JLabel)gui.getComponent("speed");
        posLabel.setText("Position: stopped");
        spdLabel.setText("Speed: stopped");
        System.out.println("Window movement stopped");
    }

    /**
     * Respond to a read temperature event received over the network
     */
    @Override
    protected int readTemperature(String sensor) {
    	int base;
    	if(temperatureLevel.equals("LOW")) base = 10;
    	else if(temperatureLevel.equals("MEDIUM")) base = 20;
    	else if(temperatureLevel.equals("HIGH")) base = 30;
    	else return Integer.MIN_VALUE;
    	if(sensor.equals("FLOOR")) base-=5;
    	else if(sensor.equals("CEILING")) base+=5;
    	return base;
    }

}
