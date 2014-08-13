package com.busy.engine.entity;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Sourena
 * @param <T>
 */
public abstract class AbstractEntity<T> implements JsonItem, Serializable
{    
    @Override
    public JsonObject toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addJson(builder);
        return builder.build();
    }
}
