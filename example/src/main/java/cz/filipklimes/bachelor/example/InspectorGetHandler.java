package cz.filipklimes.bachelor.example;

import cz.cvut.fel.adaptiverestfulapi.core.HttpContext;
import cz.cvut.fel.adaptiverestfulapi.data.DataException;
import cz.cvut.fel.adaptiverestfulapi.data.GetHandler;
import cz.cvut.fel.adaptiverestfulapi.meta.configuration.Configuration;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Entity;
import cz.filipklimes.bachelor.inspection.ModelInspector;
import cz.filipklimes.bachelor.inspection.SingletonModelInspectorImpl;
import cz.filipklimes.bachelor.inspection.metadata.SerializableMetadata;

/**
 * @author klimesf
 */
public class InspectorGetHandler extends GetHandler {
    @Override
    protected HttpContext get(Entity entity, HttpContext context, Configuration configuration) throws DataException {
        ModelInspector modelInspector = SingletonModelInspectorImpl.getInstance();
        SerializableMetadata entityMetadata = null;
        try {
            entityMetadata = modelInspector.inspectClass(entity.getName());
            context.setContent(entityMetadata);
        } catch (ClassNotFoundException e) {
        }
        return context;
    }
}
