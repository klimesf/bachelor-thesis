package cz.filipklimes.bachelor.inspection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * @author klimesf
 */
public class ModelInspectorImpl implements ModelInspector {

    /**
     * {@inheritDoc}
     *
     * @param className Canonical name of the class.
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Map<Field, List<Annotation>> inspectClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        Map<Field, List<Annotation>> fieldAnnotations = new HashMap<>();
        fieldAnnotations.putAll(this.findFieldAnnotations(clazz));

        // Merge in annotations from getters/setters
        for (Map.Entry<Field, List<Annotation>> entry : this.findMethodAnnotations(clazz).entrySet()) {
            if (fieldAnnotations.containsKey(entry.getKey())) {
                // New list needs to be created here, the existing cannot be added to
                List<Annotation> mergedList = new ArrayList<>();
                mergedList.addAll(fieldAnnotations.get(entry.getKey()));
                mergedList.addAll(entry.getValue());
                fieldAnnotations.put(entry.getKey(), mergedList);
            } else {
                fieldAnnotations.put(entry.getKey(), entry.getValue());
            }
        }

        return fieldAnnotations;
    }

    /**
     * {@inheritDoc}
     *
     * @param packageName Canonical name of the package.
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Map<Class, Map<Field, Annotation>> inspectPackage(String packageName) throws ClassNotFoundException {
        throw new NotImplementedException();
        //  foreach class in package
        //      list add this.inspectClass()
    }

    /**
     * Finds field annotations in the given class from fields themselves.
     *
     * @param clazz
     * @return
     */
    private Map<Field, List<Annotation>> findFieldAnnotations(Class<?> clazz) {
        Map<Field, List<Annotation>> fieldAnnotations = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            fieldAnnotations.put(field, Arrays.asList(field.getAnnotations()));
        }
        return fieldAnnotations;
    }

    /**
     * Finds field annotations of the given class from getters and setters.
     *
     * @param clazz
     * @return
     */
    private Map<Field, List<Annotation>> findMethodAnnotations(Class<?> clazz) {
        Map<Field, List<Annotation>> fieldAnnotations = new HashMap<>();
        List<Method> methods = this.findGettersSetters(clazz);
        for (Method method : methods) {
            String fieldName = this.getFieldName(method);
            try {
                Field field = clazz.getDeclaredField(fieldName);
                fieldAnnotations.put(field, Arrays.asList(method.getAnnotations()));

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return fieldAnnotations;
    }

    /**
     * Finds getters and setters of the given class.
     *
     * @param clazz
     * @return
     */
    private List<Method> findGettersSetters(Class<?> clazz) {
        ArrayList<Method> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (this.isGetter(method) || this.isSetter(method)) {
                list.add(method);
            }
        }
        return list;
    }

    /**
     * Finds out whether the given method is a getter.
     *
     * @param method
     * @return
     */
    private boolean isGetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) &&
                !method.getReturnType().equals(void.class) &&
                method.getParameterTypes().length == 0 &&
                method.getName().matches("^(get|is)[A-Z].*");
    }

    /**
     * Finds out whether the given method is a setter.
     *
     * @param method
     * @return
     */
    private boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) &&
                method.getParameterTypes().length == 1 &&
                method.getName().matches("^set[A-Z].*");
    }

    /**
     * Returns name of the field to which the given method is a getter/setter.
     *
     * @param method
     * @return
     */
    private String getFieldName(Method method) {
        String methodName = method.getName();
        String fieldName;
        if (methodName.startsWith("is")) {
            fieldName = Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
        } else {
            fieldName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
        }
        return fieldName;
    }

}
