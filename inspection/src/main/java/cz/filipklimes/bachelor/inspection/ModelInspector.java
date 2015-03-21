package cz.filipklimes.bachelor.inspection;

import cz.filipklimes.bachelor.inspection.metadata.SerializableMetadata;

import java.io.IOException;
import java.util.List;

/**
 * @author klimesf
 */
public interface ModelInspector {

    SerializableMetadata inspectClass(String className) throws ClassNotFoundException;

    List<SerializableMetadata> inspectPackage(String packageName) throws IOException, ClassNotFoundException;

}
