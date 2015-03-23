package cz.filipklimes.bachelor.inspection;

import cz.filipklimes.bachelor.inspection.metadata.SerializableMetadata;
import cz.filipklimes.bachelor.inspection.model.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class PrefixedModelInspectorImplTest {

    private ModelInspector inspector = new PrefixedModelInspectorImpl(SingletonModelInspectorImpl.getInstance(), "cz.filipklimes.bachelor.inspection.");

    @Test(dataProvider = "testInspectClass")
    public void testInspectClass(String clazz, String json) throws Exception {
        SerializableMetadata metadata = inspector.inspectClass(clazz);
        Assert.assertEquals(
                json,
                metadata.toJson()
        );
    }

    @DataProvider(name = "testInspectClass")
    private Object[][] provideInspectClassData() {
        return new Object[][]{
                new String[]{
                        "model.User",
                        "{\"name\":\"cz.filipklimes.bachelor.inspection.model.User\",\"fields\":[{\"name\":\"username\",\"type\":\"java.lang.String\"},{\"name\":\"password\",\"type\":\"java.lang.String\"},{\"name\":\"confirmed\",\"type\":\"boolean\"}]}"
                }
        };
    }

    @Test
    public void testInspectPackage() throws Exception {
        List<SerializableMetadata> metadataList = inspector.inspectPackage("model");
        Assert.assertEquals(1, metadataList.size());
        metadataList.forEach((metadata) -> Assert.assertNotNull(metadata.toJson()));
    }

}