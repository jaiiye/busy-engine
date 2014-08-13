











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SitePage extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_PAGE_ID = "SitePageId";
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_PAGE_ID = "PageId";
        

        private Integer sitePageId;
                
        private Integer siteId;
        private Site site;        
        private Integer pageId;
        private Page page;        
                 
        
        

        public SitePage()
        {
            this.sitePageId = 0; 
       this.siteId = 0; 
       this.pageId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return sitePageId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("sitePageId", sitePageId).add("siteId", siteId).add("pageId", pageId);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SitePage.PROP_SITE_PAGE_ID) || column.equals(SitePage.PROP_SITE_ID) || column.equals(SitePage.PROP_PAGE_ID) )
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
            if (column.equals(SitePage.PROP_SITE_PAGE_ID) || column.equals(SitePage.PROP_SITE_ID) || column.equals(SitePage.PROP_PAGE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SitePage process(ResultSet rs) throws SQLException
        {        
            return new SitePage(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
              
       public SitePage(Integer SitePageId, Integer SiteId, Integer PageId)
       {
            this.sitePageId = SitePageId;
       this.siteId = SiteId;
       this.pageId = PageId;
              
       
       } 
        
             
        
            public Integer getSitePageId()
            {
                return this.sitePageId;
            }
            
            public void setSitePageId(Integer SitePageId)
            {
                this.sitePageId = SitePageId;
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
                   
            
        
            public Integer getPageId()
            {
                return this.pageId;
            }
            
            public void setPageId(Integer PageId)
            {
                this.pageId = PageId;
            }
            
            
                   
            public Page getPage()
                {
                    return this.page;
                }

                public void setPage(Page page)
                {
                    this.page = page;
                }
                   
            
         
        
        
            
    }

