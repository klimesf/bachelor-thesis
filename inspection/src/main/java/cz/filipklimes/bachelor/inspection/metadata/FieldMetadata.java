package cz.filipklimes.bachelor.inspection.metadata;

/**
 * @author klimesf
 */
public class FieldMetadata extends Metadata {

    private String name;

    private String type;

    /**
     * @param name Name of the field.
     * @param type FQN of the field's type.
     */
    public FieldMetadata(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Returns name of the field.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns FQN of the field's type.
     *
     * @return
     */
    public String getType() {
        return type;
    }

}
