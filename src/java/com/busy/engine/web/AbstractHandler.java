package com.busy.engine.web;

import com.busy.engine.entity.AbstractEntity;
import com.busy.engine.entity.JsonItem;
import com.busy.engine.entity.User;
import com.busy.engine.vo.Result;
import static com.busy.engine.web.SecurityHelper.getSessionUser;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Provides base functionality for all AJAX driven handler classes. 
 * Uses JSON syntax that can be consumed by Sencha ExtJS4 and Touch2 clients.
 *
 */
public abstract class AbstractHandler extends HttpServlet
{
    protected SimpleDateFormat operatingDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    protected SimpleDateFormat getOperatingDateFormat()
    {
        return operatingDateFormat;
    }    
    
    /**
     * Processes the List of results and constructs a JSON representation in the
     * format
     *
     * {success:true, data:[ theResults]}
     *
     * where theResults is an array of JSON objects. Each item in the results
     * List MUST implement JsonItem as the toJson() method is invoked on each
     * item to generate the JSON object
     *
     * @param results
     * @return
     */
    public static String getJsonSuccessData(List<? extends JsonItem> results)
    {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        builder.add("success", true);

        for (JsonItem ji : results)
        {
            arrayBuilder.add(ji.toJson());
        }

        builder.add("data", arrayBuilder);
        return toJsonString(builder.build());
    }

    /**
     * Returns a JSON success data array in the format { success: true, data:
     * jsonData} where jsonData could be in the format [ {} , {} ...] for lists
     * or simply { } for a single record
     *
     * @param jsonItem
     * @return
     */
    public static String getJsonSuccessData(JsonItem jsonItem)
    {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);
        builder.add("data", jsonItem.toJson());

        return toJsonString(builder.build());
    }

    /**
     * Returns a JSON success data array in the format { success:true,
     * total:count, data:jsonData} where jsonData would be in the format [ {} ,
     * {} ...]. The total property would be used by Ext.data.Stores for paging
     * purposes
     *
     * @param jsonItem
     * @param totalCount
     * @return
     */
    public static String getJsonSuccessData(JsonItem jsonItem, int totalCount)
    {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);
        builder.add("total", totalCount);
        builder.add("data", jsonItem.toJson());

        return toJsonString(builder.build());
    }

    public static String getJsonErrorMsg(String theErrorMessage)
    {
        return getJsonMsg(theErrorMessage, false);
    }

    public static String getJsonSuccessMsg(String msg)
    {
        return getJsonMsg(msg, true);
    }

    /**
     * Constructs a JSON representation of the msg with a text payload in the
     * following format:
     *
     * {success:true/false, msg:\"the message\" }
     *
     * @param msg
     * @param success
     * @return
     */
    public static String getJsonMsg(String msg, boolean success)
    {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", success);
        builder.add("msg", msg);
        return toJsonString(builder.build());
    }

    public static String toJsonString(JsonObject model)
    {
        final StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter))
        {
            jsonWriter.writeObject(model);
        }
        return stWriter.toString();
    }

    protected JsonObject parseJsonObject(String jsonString)
    {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        return reader.readObject();
    }

    protected Integer getIntegerValue(JsonValue jsonValue)
    {
        Integer value = null;

        switch (jsonValue.getValueType())
        {
            case NUMBER:
                JsonNumber num = (JsonNumber) jsonValue;
                value = num.intValue();
                break;
            case NULL:
                break;
        }
        
        return value;
    }
    
    protected String getRequiredParameter(HttpServletRequest request, String paramName) throws Exception
    {
        String value = request.getParameter(paramName);
        if (value == null || value.equals(""))
        {
            throw new Exception("Invalid required parameter was passed.");
        }
        else
        {
            return value;
        }
    }

    protected void generateFindServiceResult(Result<? extends AbstractEntity> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessData(result.getData()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }

    protected void generateFindAllServiceResult(Result<? extends List<? extends AbstractEntity>> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessData(result.getData()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }

    protected void generateStoreServiceResult(Result<? extends AbstractEntity> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessData(result.getData()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }

    protected void generateRemoveServiceResult(Result<?> result, PrintWriter out)
    {
        if (result.isSuccess())
        {
            out.print(getJsonSuccessMsg(result.getMsg()));
        }
        else
        {
            out.print(getJsonErrorMsg(result.getMsg()));
        }
    }   
}
