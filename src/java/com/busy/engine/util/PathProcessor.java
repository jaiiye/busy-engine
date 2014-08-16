package com.busy.engine.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sourena
 */
public class PathProcessor
{
    //private Integer id = null;
    private String resource = "";
    private String operation = "";
    

    public PathProcessor(String pathInfo)
    {
        //tries to extract a resource name followed by an operation and an optional number as id of resource
        
        //Pattern p = Pattern.compile("/([^/]+)/([^/]+)(/([0-9]*))*"); // ex: /item/find/12
        Pattern p = Pattern.compile("/([^/]+)/([^/]+)");
        Matcher m = p.matcher(pathInfo);
        if (m.find()) 
        {
            resource = m.group(1);
            operation = m.group(2).replace(".json", "");
            //String Id = m.group(3) == null ? null : m.group(3).replace("/", "");
            //if(Id != null && (!Id.equals("")))
            //{
            //    id = Integer.parseInt(Id);
            //}            
        }
    }

//    public Integer getId()
//    {
//        return id;
//    }
    
    public String getResource()
    {
        return resource;
    }
    
    public String getOperation()
    {
        return operation;
    }
    
    public static void main(String[] args)
    {
        //PathProcessor pp = new PathProcessor("/item/find.json");
        PathProcessor pp = new PathProcessor("/item/findall.json");
        //PathProcessor pp = new PathProcessor("/item/remove.json");
        System.out.println("Resource: " + pp.getResource());
        System.out.println("Operation: " + pp.getOperation());
        //System.out.println("id: " + pp.getId());
    }

}
