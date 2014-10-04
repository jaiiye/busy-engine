






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class MetaTag extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_KEYWORDS = "Keywords";
        

        private Integer metaTagId;
                
        private String title;
                
        private String description;
                
        private String keywords;
                
                 
        ArrayList<BlogPost> blogPostList; 
        ArrayList<Item> itemList; 
        ArrayList<Page> pageList; 
        ArrayList<Vendor> vendorList; 
        
        

        public MetaTag()
        {
            this.metaTagId = 0; 
       this.title = ""; 
       this.description = ""; 
       this.keywords = ""; 
        
       blogPostList = null; 
        itemList = null; 
        pageList = null; 
        vendorList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return metaTagId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("metaTagId", metaTagId == null ? 0 : metaTagId);
                
            builder.add("title", title == null ? "" : title);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("keywords", keywords == null ? "" : keywords);
        
        
    
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(MetaTag.PROP_META_TAG_ID) || column.equals(MetaTag.PROP_TITLE) || column.equals(MetaTag.PROP_DESCRIPTION) || column.equals(MetaTag.PROP_KEYWORDS) )
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
            if (column.equals(MetaTag.PROP_META_TAG_ID) || column.equals(MetaTag.PROP_TITLE) || column.equals(MetaTag.PROP_DESCRIPTION) || column.equals(MetaTag.PROP_KEYWORDS) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static MetaTag process(ResultSet rs) throws SQLException
        {        
            return new MetaTag(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
              
       public MetaTag(Integer MetaTagId, String Title, String Description, String Keywords)
       {
            this.metaTagId = MetaTagId;
       this.title = Title;
       this.description = Description;
       this.keywords = Keywords;
              
       blogPostList = null; 
        itemList = null; 
        pageList = null; 
        vendorList = null; 
        
       } 
        
             
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
            
            
        
            public String getTitle()
            {
                return this.title;
            }
            
            public void setTitle(String Title)
            {
                this.title = Title;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getKeywords()
            {
                return this.keywords;
            }
            
            public void setKeywords(String Keywords)
            {
                this.keywords = Keywords;
            }
            
            
         
        
        
        public ArrayList<BlogPost> getBlogPostList()
            {
                return this.blogPostList;
            }
            
            public void setBlogPostList(ArrayList<BlogPost> blogPostList)
            {
                this.blogPostList = blogPostList;
            }
        
        public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
        public ArrayList<Page> getPageList()
            {
                return this.pageList;
            }
            
            public void setPageList(ArrayList<Page> pageList)
            {
                this.pageList = pageList;
            }
        
        public ArrayList<Vendor> getVendorList()
            {
                return this.vendorList;
            }
            
            public void setVendorList(ArrayList<Vendor> vendorList)
            {
                this.vendorList = vendorList;
            }
        
            
    }

