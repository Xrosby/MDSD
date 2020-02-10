package qui_generic_model;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * GUI metamodel: a simple, named button
 */
public class QButton extends QComponent {
    /**
     * The properties of this element
     */
    private List<Parameter> properties = new ArrayList<Parameter>();
    /**
     * Create a new Button element with the given properties
     * @param kind the element kind
     * @param properties the element properties
     */
    public QButton(Parameter[] properties) {
        this.properties.addAll(Arrays.asList(properties));
    }
    /**
     * Create the corresponding Swing element, depending on the kind, store in map etc
     */
    @Override protected JComponent create(ActionListener handler, Map<String,JComponent> componentMap) {
    	String name = Parameter.get(properties,Parameter.Kind.NAME);
        JButton button = new JButton(Parameter.get(properties,Parameter.Kind.TEXT));
        button.setActionCommand(name);
        button.addActionListener(handler);
        if(name!=null) componentMap.put(name, button);
        return button;
    }
    /**
     * For debugging
     */
    @Override public String toString() {
        return "Button[...]";
    }
}