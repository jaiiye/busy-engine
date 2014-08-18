



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class PageTemplate extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_PAGE_TEMPLATE_ID = "PageTemplateId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_MARKUP = "Markup";
        

        private Integer pageTemplateId;
                
        private String name;
                
        private String markup;
                
                 
        
        

        public PageTemplate()
        {
            this.pageTemplateId = 0; 
       this.name = ""; 
       this.markup = ""; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return pageTemplateId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("pageTemplateId", pageTemplateId == null ? 0 : pageTemplateId);
                
            builder.add("name", name == null ? "" : name);
                
            builder.add("markup", markup == null ? "" : markup);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(PageTemplate.PROP_PAGE_TEMPLATE_ID) || column.equals(PageTemplate.PROP_NAME) || column.equals(PageTemplate.PROP_MARKUP) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(PageTemplate.PROP_PAGE_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static PageTemplate process(ResultSet rs) throws SQLException
        {        
            return new PageTemplate(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public PageTemplate(Integer PageTemplateId, String Name, String Markup)
       {
            this.pageTemplateId = PageTemplateId;
       this.name = Name;
       this.markup = Markup;
              
       
       } 
        
             
        
            public Integer getPageTemplateId()
            {
                return this.pageTemplateId;
            }
            
            public void setPageTemplateId(Integer PageTemplateId)
            {
                this.pageTemplateId = PageTemplateId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
        
            public String getMarkup()
            {
                return this.markup;
            }
            
            public void setMarkup(String Markup)
            {
                this.markup = Markup;
            }
            
            
         
        
        
            
    }

