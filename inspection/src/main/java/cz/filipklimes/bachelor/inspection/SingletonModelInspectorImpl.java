package cz.filipklimes.bachelor.inspection;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.ClassPath;
import cz.filipklimes.bachelor.inspection.metadata.ClassMetadataExtractor;
import cz.filipklimes.bachelor.inspection.metadata.MetadataExtractor;
import cz.filipklimes.bachelor.inspection.metadata.SerializableMetadata;

import java.io.IOException;
import java.util.List;

/**
 * @author klimesf
 */
public class SingletonModelInspectorImpl implements ModelInspector {

    protected static ModelInspector instance = new SingletonModelInspectorImpl();

    protected SingletonModelInspectorImpl() {
        if (this.instance != null) {
            throw new RuntimeException("SingletonModelInstanceImpl has already been constructed.");
        }
    }

    public static ModelInspector getInstance() {
        return instance;
    }

    @Override
    public SerializableMetadata inspectClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        MetadataExtractor extractor = new ClassMetadataExtractor(clazz);
        return extractor.createSerializableMetadata();
    }

    @Override
    public List<SerializableMetadata> inspectPackage(String packageName) throws IOException, ClassNotFoundException {
        ImmutableList.Builder<SerializableMetadata> entityMetadataListBuilder = new ImmutableList.Builder<>();
        for (ClassPath.ClassInfo classInfo : ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(packageName)) {
            entityMetadataListBuilder.add(this.inspectClass(classInfo.getName()));
        }
        return entityMetadataListBuilder.build();
    }

}
