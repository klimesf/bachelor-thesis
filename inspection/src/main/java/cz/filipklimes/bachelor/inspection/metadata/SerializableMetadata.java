package cz.filipklimes.bachelor.inspection.metadata;

import java.io.Serializable;

/**
 * @author klimesf
 */
public interface SerializableMetadata extends Serializable {

    /**
     * Returns JSON representation of the metadata.
     *
     * @return The JSON string.
     */
    String toJson();

}
