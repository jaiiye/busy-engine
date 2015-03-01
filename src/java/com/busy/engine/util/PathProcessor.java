package com.busy.engine.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sourena
 */
public class PathProcessor
{
//    private Integer id = null;
    private String operation = "";
    

    public PathProcessor(String pathInfo)
    {
        //tries to extract an operation and an optional number as id of resource        
        Pattern p = Pattern.compile("/([^/]+)(/([0-9]*))*"); // ex: /find/12
        Matcher m = p.matcher(pathInfo);
        
        if (m.find()) 
        {
            operation = m.group(1).replace(".json", "");
//            String Id = m.group(2) == null ? null : m.group(2).replace("/", "");
//            if(Id != null && (!Id.equals("")))
//            {
//                id = Integer.parseInt(Id);
//            }            
        }
    }

//    public Integer getId()
//    {
//        return id;
//    }
    
//    public String getResource()
//    {
//        return resource;
//    }
    
    public String getOperation()
    {
        return operation;
    }
    
    public static void main(String[] args)
    {
        //PathProcessor pp = new PathProcessor("/find.json/12");
        //PathProcessor pp = new PathProcessor("/findall.json");
        PathProcessor pp = new PathProcessor("/remove.json/1");
        //System.out.println("Resource: " + pp.getResource());
        System.out.println("Operation: " + pp.getOperation());
//        System.out.println("id: " + pp.getId());
    }

}
