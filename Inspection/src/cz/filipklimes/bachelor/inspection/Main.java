package cz.filipklimes.bachelor.inspection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author Filip Klimes <filip@filipklimes.cz>
 */
public class Main
{

    public static void main(String[] args)
    {
        ModelInspector modelInspector = new ModelInspectorImpl(); // DI not needed here

        try {
            Map<Field, List<Annotation>> fieldAnnotations = modelInspector.inspectClass("cz.filipklimes.bachelor.model.User");
            for (Map.Entry<Field, List<Annotation>> entry : fieldAnnotations.entrySet()) {
                System.out.printf("Field - %s:\n", entry.getKey().getName());
                for (Annotation annotation : entry.getValue()) {
                    System.out.printf("\t%s\n", annotation.annotationType().getSimpleName());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
