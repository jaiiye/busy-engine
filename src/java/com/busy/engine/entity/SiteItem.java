











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SiteItem extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ITEM_ID = "SiteItemId";
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer siteItemId;
                
        private Integer siteId;
        private Site site;        
        private Integer itemId;
        private Item item;        
                 
        
        

        public SiteItem()
        {
            this.siteItemId = 0; 
       this.siteId = 0; 
       this.itemId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return siteItemId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("siteItemId", siteItemId).add("siteId", siteId).add("itemId", itemId);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteItem.PROP_SITE_ITEM_ID) || column.equals(SiteItem.PROP_SITE_ID) || column.equals(SiteItem.PROP_ITEM_ID) )
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
            if (column.equals(SiteItem.PROP_SITE_ITEM_ID) || column.equals(SiteItem.PROP_SITE_ID) || column.equals(SiteItem.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteItem process(ResultSet rs) throws SQLException
        {        
            return new SiteItem(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
              
       public SiteItem(Integer SiteItemId, Integer SiteId, Integer ItemId)
       {
            this.siteItemId = SiteItemId;
       this.siteId = SiteId;
       this.itemId = ItemId;
              
       
       } 
        
             
        
            public Integer getSiteItemId()
            {
                return this.siteItemId;
            }
            
            public void setSiteItemId(Integer SiteItemId)
            {
                this.siteItemId = SiteItemId;
            }
            
            
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
            
            
                   
            public Site getSite()
                {
                    return this.site;
                }

                public void setSite(Site site)
                {
                    this.site = site;
                }
                   
            
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
            
            
                   
            public Item getItem()
                {
                    return this.item;
                }

                public void setItem(Item item)
                {
                    this.item = item;
                }
                   
            
         
        
        
            
    }

