package cz.filipklimes.bachelor.inspection.metadata;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author klimesf
 */
public class ClassMetadataExtractor implements MetadataExtractor {

    private Class<?> clazz;

    private List<SerializableMetadata> fieldMetadata = new ArrayList<>();

    public ClassMetadataExtractor(Class<?> clazz) {
        this.clazz = clazz;
        Arrays.asList(clazz.getDeclaredFields()).forEach(
                (field) -> this.fieldMetadata.add((new FieldMetadataExtractor(field).createSerializableMetadata())));
    }

    @Override
    public SerializableMetadata createSerializableMetadata() {
        return new ClassMetadata(clazz.getCanonicalName(), this.fieldMetadata);
    }

}
