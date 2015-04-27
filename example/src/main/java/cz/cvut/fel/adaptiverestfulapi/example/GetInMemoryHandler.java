package cz.cvut.fel.adaptiverestfulapi.example;

import cz.cvut.fel.adaptiverestfulapi.core.HttpContext;
import cz.cvut.fel.adaptiverestfulapi.core.HttpRouter;
import cz.cvut.fel.adaptiverestfulapi.data.DataException;
import cz.cvut.fel.adaptiverestfulapi.data.GetHandler;
import cz.cvut.fel.adaptiverestfulapi.data.NotFoundException;
import cz.cvut.fel.adaptiverestfulapi.meta.configuration.Configuration;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Entity;

/**
 * @author Karel Cemus
 */
public class GetInMemoryHandler extends GetHandler {

    @Override
    protected HttpContext get(Entity entity, HttpContext context, Configuration configuration) throws DataException {
        HttpRouter router = context.getRouter();
        Object identifier = router.getIdentifier(entity.getPrimary().getAttributeType());
        Object result = null;

        if (identifier != null) {
            result = ExampleData.find(entity, identifier);
            if (result == null) {
                throw new NotFoundException(entity.getName(), identifier.toString());
            }

        } else {
            result = ExampleData.findAll(entity);
        }

        context.setContent(result);
        return context;
    }
}
