






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class PostCategory extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_POST_CATEGORY_ID = "PostCategoryId";
        public static final String PROP_CATEGORY_NAME = "CategoryName";
        

        private Integer postCategoryId;
                
        private String categoryName;
                
                 
        ArrayList<BlogPostCategory> blogPostCategoryList; 
        
        

        public PostCategory()
        {
            this.postCategoryId = 0; 
       this.categoryName = ""; 
        
       blogPostCategoryList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return postCategoryId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("postCategoryId", postCategoryId == null ? 0 : postCategoryId);
                
            builder.add("categoryName", categoryName == null ? "" : categoryName);
        
        
    
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(PostCategory.PROP_POST_CATEGORY_ID) || column.equals(PostCategory.PROP_CATEGORY_NAME) )
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
            if (column.equals(PostCategory.PROP_POST_CATEGORY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static PostCategory process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new PostCategory(rs.getInt(1), rs.getString(2));
        }
              
       public PostCategory(Integer PostCategoryId, String CategoryName)
       {
            this.postCategoryId = PostCategoryId;
       this.categoryName = CategoryName;
              
       blogPostCategoryList = null; 
        
       } 
        
             
        
            public Integer getPostCategoryId()
            {
                return this.postCategoryId;
            }
            
            public void setPostCategoryId(Integer PostCategoryId)
            {
                this.postCategoryId = PostCategoryId;
            }
            
            
        
            public String getCategoryName()
            {
                return this.categoryName;
            }
            
            public void setCategoryName(String CategoryName)
            {
                this.categoryName = CategoryName;
            }
            
            
         
        
        
        public ArrayList<BlogPostCategory> getBlogPostCategoryList()
            {
                return this.blogPostCategoryList;
            }
            
            public void setBlogPostCategoryList(ArrayList<BlogPostCategory> blogPostCategoryList)
            {
                this.blogPostCategoryList = blogPostCategoryList;
            }
        
            
    }

