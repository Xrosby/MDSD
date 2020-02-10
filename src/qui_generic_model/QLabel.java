package qui_generic_model;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * GUI metamodel: a simple label
 */
public class QLabel extends QComponent {
    /**
     * The properties of this element
     */
    private List<Parameter> properties = new ArrayList<Parameter>();
    /**
     * Create a new Label element with the given properties
     * @param kind the element kind
     * @param properties the element properties
     */
    public QLabel(Parameter[] properties) {
        this.properties.addAll(Arrays.asList(properties));
    }
    /**
     * Create the corresponding Swing element, depending on the kind, store in map etc
     */
    @Override protected JComponent create(ActionListener handler, Map<String,JComponent> componentMap) {
    	 String name = Parameter.get(properties,Parameter.Kind.NAME);
         JLabel label = new JLabel(Parameter.get(properties,Parameter.Kind.TEXT));
         if(name!=null) componentMap.put(name,label);
         return label;
    }
    /**
     * For debugging
     */
    @Override public String toString() {
        return "Label[...]";
    }
}