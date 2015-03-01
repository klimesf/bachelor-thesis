package cz.filipklimes.bachelor.inspection.metadata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author klimesf
 */
public class FieldMetadataExtractor implements MetadataExtractor {

    private Field field;

    /**
     * @param field
     */
    public FieldMetadataExtractor(Field field) {
        this.field = field;
    }

    /**
     * If you pass array of class' methods, metadata from getters and setters will be also taken into consideration.
     * @param field
     * @param methods
     */
    public FieldMetadataExtractor(Field field, Method[] methods) {
        throw new UnsupportedOperationException("This functionality has not been implemented yet.");
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public SerializableMetadata createSerializableMetadata() {
        return new FieldMetadata(field.getName(), field.getType().getCanonicalName());
    }

}
