package cz.filipklimes.bachelor.inspection;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ModelInspectorTest {

    private ModelInspector inspector;

    @BeforeMethod
    public void setUp() {
        this.inspector = new ModelInspectorImpl();
    }


    @DataProvider
    public Object[][] classProvider() {
        return new Object[][]{
                {"cz.filipklimes.bachelor.inspection.model.User", 3, 8}
        };
    }

    @Test(dataProvider = "classProvider")
    public void testInspectClass(String className, int numberOfFields, int numberOfAnnotations) throws Exception {
        Map<Field, List<Annotation>> fieldAnnotations = this.inspector.inspectClass(className);
        Assert.assertEquals(numberOfFields, fieldAnnotations.size());
    }

    @Test
    public void testInspectPackage() throws Exception {
        // @TODO
        Assert.assertTrue(true);
    }
}