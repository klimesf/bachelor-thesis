package cz.filipklimes.bachelor.inspection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author klimesf
 */
public interface ModelInspector
{

    /**
     * Inspects given class for field attributes.
     *
     * @param className Canonical name of the class.
     * @return
     */
    Map<Field, List<Annotation>> inspectClass(String className) throws ClassNotFoundException;

    /**
     * Inspects given package's classes for field attributes.
     *
     * @param packageName Canonical name of the package.
     * @return
     * @throws ClassNotFoundException
     */
    Map<Class, Map<Field, Annotation>> inspectPackage(String packageName) throws ClassNotFoundException;

}
