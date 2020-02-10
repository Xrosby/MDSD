package examples.gui_nested;

/**
 * A parameter provided to describe a GUI element
 */
public class Parameter {
    /**
     * Description of the kind of a parameter
     */
    public enum Kind { TEXT, NAME; }
    /**
     * The kind of the parameter
     */
    private Parameter.Kind kind;
    /**
     * The value of the parameter 
     */
    private String value;
    /**
     * Create a new parameter
     * @param kind the kind of the parameter
     * @param value the value of the parameter
     */
    public Parameter(Parameter.Kind kind, String value) {
        this.kind = kind;
        this.value = value;
    }
    /**
     * Get the kind of the parameter
     * @return the parameter kind
     */
    public Parameter.Kind getKind() { return kind; }
    /**
     * Get the value of the parameter if it is text, otherwise null
     * @return the parameter value
     */
    public String getText() { return kind==Kind.TEXT?value:null; }
    /**
     * Get the value of the parameter if it is name, otherwise null
     * @return the parameter value
     */
    public String getName() { return kind==Kind.NAME?value:null; }
}