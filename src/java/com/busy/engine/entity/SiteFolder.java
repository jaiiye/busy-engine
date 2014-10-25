






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SiteFolder extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_FOLDER_ID = "SiteFolderId";
        public static final String PROP_FOLDER_NAME = "FolderName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteFolderId;
                
        private String folderName;
                
        private String description;
                
        private Integer rank;
                
        private Integer siteId;
        private Site site;        
                 
        ArrayList<FileFolder> fileFolderList; 
        
        

        public SiteFolder()
        {
            this.siteFolderId = 0; 
       this.folderName = ""; 
       this.description = ""; 
       this.rank = 0; 
       this.siteId = 0; 
        
       fileFolderList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return siteFolderId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("siteFolderId", siteFolderId == null ? 0 : siteFolderId);
                
            builder.add("folderName", folderName == null ? "" : folderName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("rank", rank == null ? 0 : rank);
                
            builder.add("siteId", siteId == null ? 0 : siteId);
        
        
    
     
     
     
     if(site != null) site.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteFolder.PROP_SITE_FOLDER_ID) || column.equals(SiteFolder.PROP_FOLDER_NAME) || column.equals(SiteFolder.PROP_DESCRIPTION) || column.equals(SiteFolder.PROP_RANK) || column.equals(SiteFolder.PROP_SITE_ID) )
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
            if (column.equals(SiteFolder.PROP_SITE_FOLDER_ID) || column.equals(SiteFolder.PROP_RANK) || column.equals(SiteFolder.PROP_SITE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteFolder process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new SiteFolder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
              
       public SiteFolder(Integer SiteFolderId, String FolderName, String Description, Integer Rank, Integer SiteId)
       {
            this.siteFolderId = SiteFolderId;
       this.folderName = FolderName;
       this.description = Description;
       this.rank = Rank;
       this.siteId = SiteId;
              
       fileFolderList = null; 
        
       } 
        
             
        
            public Integer getSiteFolderId()
            {
                return this.siteFolderId;
            }
            
            public void setSiteFolderId(Integer SiteFolderId)
            {
                this.siteFolderId = SiteFolderId;
            }
            
            
        
            public String getFolderName()
            {
                return this.folderName;
            }
            
            public void setFolderName(String FolderName)
            {
                this.folderName = FolderName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
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
                   
            
         
        
        
        public ArrayList<FileFolder> getFileFolderList()
            {
                return this.fileFolderList;
            }
            
            public void setFileFolderList(ArrayList<FileFolder> fileFolderList)
            {
                this.fileFolderList = fileFolderList;
            }
        
            
    }

