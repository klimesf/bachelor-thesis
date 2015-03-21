package cz.filipklimes.bachelor.inspection.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author klimesf
 */
public class ClassMetadata extends Metadata {

    private String name;

    private List<SerializableMetadata> fields = new ArrayList<>();

    /**
     * @param name   FQN of the class.
     * @param fields List of the class's serializable metadata.
     */
    public ClassMetadata(String name, List<SerializableMetadata> fields) {
        this.name = name;
        this.fields = fields;
    }

    /**
     * Returns FQN of the class, which is also its identifier.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns list of class's fields serializable metadata.
     *
     * @return
     */
    public List<SerializableMetadata> getFields() {
        return fields;
    }

}
