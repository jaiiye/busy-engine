


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemFile implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_FILE_ID = "ItemFileId";
        public static final String PROP_FILE_NAME = "FileName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_LABEL = "Label";
        public static final String PROP_HIDDEN = "Hidden";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemFileId;
        private String fileName;
        private String description;
        private String label;
        private Boolean hidden;
        private Integer itemId;
        

        public ItemFile()
        {
            this.itemFileId = 0; 
       this.fileName = ""; 
       this.description = ""; 
       this.label = ""; 
       this.hidden = null; 
       this.itemId = 0; 
        }
        
        public ItemFile(Integer ItemFileId, String FileName, String Description, String Label, Boolean Hidden, Integer ItemId)
        {
            this.itemFileId = ItemFileId;
       this.fileName = FileName;
       this.description = Description;
       this.label = Label;
       this.hidden = Hidden;
       this.itemId = ItemId;
        } 
        
             
        
            public Integer getItemFileId()
            {
                return this.itemFileId;
            }
            
            public void setItemFileId(Integer ItemFileId)
            {
                this.itemFileId = ItemFileId;
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
        
            public Boolean getHidden()
            {
                return this.hidden;
            }
            
            public void setHidden(Boolean Hidden)
            {
                this.hidden = Hidden;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
           
            
    }

