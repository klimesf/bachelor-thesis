
package cz.cvut.fel.adaptiverestfulapi.serialization.json;

import com.google.gson.*;
import com.google.gson.JsonSerializer;
import cz.cvut.fel.adaptiverestfulapi.meta.configuration.Configuration;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Attribute;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Entity;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Model;
import cz.cvut.fel.adaptiverestfulapi.meta.model.Relationship;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;


public class EntitySerializer implements JsonSerializer {

    private Entity entity;
    private Model model;
    private Configuration configuration;

    public EntitySerializer(Entity entity, Model model, Configuration configuration) {
        this.entity = entity;
        this.model = model;
        this.configuration = configuration;
    }

    @Override
    public JsonElement serialize(Object object, Type typeOfSrc, JsonSerializationContext context) throws JsonParseException {
        JsonObject jsonObject = new JsonObject();

        this.serializeAttributes(object, typeOfSrc, jsonObject, context);
        this.serializeRelationships(object, typeOfSrc, jsonObject, context);

        return jsonObject;
    }

    protected void serializeAttributes(Object object, Type typeOfSrc, JsonObject jsonObject, JsonSerializationContext context) throws JsonParseException {
        for (Attribute attribute : this.entity.getAttributes().values()) {
            if (attribute.getGetter() != null) {
                this.serializeAttribute(attribute, object, typeOfSrc, jsonObject, context);
            }
        }
    }

    protected void serializeAttribute(Attribute attribute, Object object, Type typeOfSrc, JsonObject jsonObject, JsonSerializationContext context) throws JsonParseException {
        Method getter = attribute.getGetter();
        String name = attribute.getShortName();

        try {
            Object value = getter.invoke(object);
            jsonObject.add(name, context.serialize(value));

        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);

        } catch (InvocationTargetException e) {
            throw new JsonParseException(e);
        }
    }

    protected void serializeRelationships(Object object, Type typeOfSrc, JsonObject jsonObject, JsonSerializationContext context) throws JsonParseException {
        for (Relationship relationship : this.entity.getRelationships().values()) {
            if (relationship.getGetter() != null) {
                if (relationship.isToOne()) {
                    this.serializeToOne(relationship, object, typeOfSrc, jsonObject, context);

                } else if (relationship.isToMany()) {
                    this.serializeToMany(relationship, object, typeOfSrc, jsonObject, context);
                }
            }
        }
    }

    /**
     * Serializes `toOne` relationship with identifier, eq: "project": 3.
     * @param relationship
     * @param object
     * @param typeOfSrc
     * @param jsonObject
     * @param context
     * @throws JsonParseException
     */
    protected void serializeToOne(Relationship relationship, Object object, Type typeOfSrc, JsonObject jsonObject, JsonSerializationContext context) throws JsonParseException {
        Method getter = relationship.getGetter();
        String name = relationship.getShortName();

        try {
            Object target = getter.invoke(object);

            if (target != null) {
                getter = this.model.entityForName(relationship.getTargetEntity()).getPrimary().getGetter();
                Object id = getter.invoke(target);
                jsonObject.add(name, context.serialize(id));

            } else {
                jsonObject.add(name, context.serialize(null));
            }

        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);

        } catch (InvocationTargetException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * Serializes `toMany` relationship as an array of IDs, eq: "issues": [1, 2, 3].
     * @param relationship
     * @param object
     * @param typeOfSrc
     * @param jsonObject
     * @param context
     * @throws JsonParseException
     */
    protected void serializeToMany(Relationship relationship, Object object, Type typeOfSrc, JsonObject jsonObject, JsonSerializationContext context) throws JsonParseException {
        Method getter = relationship.getGetter();
        String name = relationship.getShortName();

        try {
            Collection collection = (Collection)getter.invoke(object);
            getter = this.model.entityForName(relationship.getTargetEntity()).getPrimary().getGetter();

            JsonArray ids = new JsonArray();
            for (Object obj : collection) {
                Object id = getter.invoke(obj);
                ids.add(context.serialize(id));
            }
            jsonObject.add(name, ids);

        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);

        } catch (InvocationTargetException e) {
            throw new JsonParseException(e);
        }
    }

}