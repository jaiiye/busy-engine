package com.busy.engine.entity;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public interface JsonItem
{
    public JsonObject toJson();
    public void addJson(JsonObjectBuilder builder);
}
