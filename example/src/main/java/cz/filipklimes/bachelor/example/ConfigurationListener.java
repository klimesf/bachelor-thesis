
package cz.filipklimes.bachelor.example;

import cz.cvut.fel.adaptiverestfulapi.data.GetHandler;
import cz.filipklimes.bachelor.example.security.Users;
import cz.cvut.fel.adaptiverestfulapi.meta.ConfigurationInspectionListener;
import cz.cvut.fel.adaptiverestfulapi.meta.configuration.Variable;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Attribute;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Entity;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Model;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Relationship;
import cz.cvut.fel.adaptiverestfulapi.serialization.Authorization;
import cz.cvut.fel.adaptiverestfulapi.serialization.application.json.JsonSerializer;
import cz.cvut.fel.adaptiverestfulapi.serialization.text.plain.PlainTextSerializer;

import java.util.LinkedList;
import java.util.List;


public class ConfigurationListener implements ConfigurationInspectionListener {

    @Override
    public List<Variable> configuration() {

        List<Variable> vars = new LinkedList<>();

        // Handlers
        vars.add(new Variable(GetHandler.Key, new InspectorGetHandler()));

        vars.add(new Variable(PlainTextSerializer.MIME, new PlainTextSerializer()));
        vars.add(new Variable(JsonSerializer.MIME, new JsonSerializer()));
        return vars;
    }

    @Override
    public List<Variable> configuration(Model model) {
        return new LinkedList<>();
    }

    @Override
    public List<Variable> configuration(Entity entity) {
        List<Variable> vars = new LinkedList<>();

        Authorization auth = Users.getInstance().serializationAuthorization(entity);
        if (auth != null) {
            vars.add(new Variable(Authorization.Key, auth));
        }
        return vars;
    }

    @Override
    public List<Variable> configuration(Attribute attribute) {
        List<Variable> vars = new LinkedList<>();

        Authorization auth = Users.getInstance().serializationAuthorization(attribute);
        if (auth != null) {
            vars.add(new Variable(Authorization.Key, auth));
        }
        return vars;
    }

    @Override
    public List<Variable> configuration(Relationship relationship) {
        List<Variable> vars = new LinkedList<>();

        Authorization auth = Users.getInstance().serializationAuthorization(relationship);
        if (auth != null) {
            vars.add(new Variable(Authorization.Key, auth));
        }
        return vars;
    }

}
