











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class SiteFile implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_FILE_ID = "SiteFileId";
        public static final String PROP_FILE_NAME = "FileName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_LABEL = "Label";
        

        private Integer siteFileId;
                
        private String fileName;
                
        private String description;
                
        private String label;
                
                 
        ArrayList<FileFolder> fileFolderList; 
        
        

        public SiteFile()
        {
            this.siteFileId = 0; 
       this.fileName = ""; 
       this.description = ""; 
       this.label = ""; 
        
       fileFolderList = null; 
        
       }
        
        public SiteFile(Integer SiteFileId, String FileName, String Description, String Label)
        {
            this.siteFileId = SiteFileId;
       this.fileName = FileName;
       this.description = Description;
       this.label = Label;
              
       fileFolderList = null; 
        
       } 
        
             
        
            public Integer getSiteFileId()
            {
                return this.siteFileId;
            }
            
            public void setSiteFileId(Integer SiteFileId)
            {
                this.siteFileId = SiteFileId;
            }
            
            
        
            public String getFileName()
            {
                return this.fileName;
            }
            
            public void setFileName(String FileName)
            {
                this.fileName = FileName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getLabel()
            {
                return this.label;
            }
            
            public void setLabel(String Label)
            {
                this.label = Label;
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

