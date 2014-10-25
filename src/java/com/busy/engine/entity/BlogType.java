






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class BlogType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_TYPE_ID = "BlogTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer blogTypeId;
                
        private String typeName;
                
                 
        ArrayList<Blog> blogList; 
        
        

        public BlogType()
        {
            this.blogTypeId = 0; 
       this.typeName = ""; 
        
       blogList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return blogTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("blogTypeId", blogTypeId == null ? 0 : blogTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
        
        
    
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(BlogType.PROP_BLOG_TYPE_ID) || column.equals(BlogType.PROP_TYPE_NAME) )
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
            if (column.equals(BlogType.PROP_BLOG_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static BlogType process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new BlogType(rs.getInt(1), rs.getString(2));
        }
              
       public BlogType(Integer BlogTypeId, String TypeName)
       {
            this.blogTypeId = BlogTypeId;
       this.typeName = TypeName;
              
       blogList = null; 
        
       } 
        
             
        
            public Integer getBlogTypeId()
            {
                return this.blogTypeId;
            }
            
            public void setBlogTypeId(Integer BlogTypeId)
            {
                this.blogTypeId = BlogTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
         
        
        
        public ArrayList<Blog> getBlogList()
            {
                return this.blogList;
            }
            
            public void setBlogList(ArrayList<Blog> blogList)
            {
                this.blogList = blogList;
            }
        
            
    }

