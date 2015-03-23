package cz.filipklimes.bachelor.inspection;

import cz.filipklimes.bachelor.inspection.metadata.SerializableMetadata;

import java.io.IOException;
import java.util.List;

/**
 * @author klimesf
 */
public class PrefixedModelInspectorImpl implements ModelInspector {

    private final String prefix;

    private final ModelInspector instance;

    public PrefixedModelInspectorImpl(ModelInspector modelInspector, String prefix) {
        this.instance = modelInspector;
        this.prefix = prefix;
    }

    @Override
    public SerializableMetadata inspectClass(String className) throws ClassNotFoundException {
        return this.instance.inspectClass(this.prefix + className);
    }

    @Override
    public List<SerializableMetadata> inspectPackage(String packageName) throws IOException, ClassNotFoundException {
        return this.instance.inspectPackage(this.prefix + packageName);
    }

}
