






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemFile extends AbstractEntity implements EntityItem<Integer>
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
                
        private Integer hidden;
                
        private Integer itemId;
        private Item item;        
                 
        
        

        public ItemFile()
        {
            this.itemFileId = 0; 
       this.fileName = ""; 
       this.description = ""; 
       this.label = ""; 
       this.hidden = 0; 
       this.itemId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return itemFileId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemFileId", itemFileId == null ? 0 : itemFileId);
                
            builder.add("fileName", fileName == null ? "" : fileName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("label", label == null ? "" : label);
                
            builder.add("hidden", hidden == null ? 0 : hidden);
                
            builder.add("itemId", itemId == null ? 0 : itemId);
        
        
    
     
     
     
     
     if(item != null) item.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemFile.PROP_ITEM_FILE_ID) || column.equals(ItemFile.PROP_FILE_NAME) || column.equals(ItemFile.PROP_DESCRIPTION) || column.equals(ItemFile.PROP_LABEL) || column.equals(ItemFile.PROP_HIDDEN) || column.equals(ItemFile.PROP_ITEM_ID) )
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
            if (column.equals(ItemFile.PROP_ITEM_FILE_ID) || column.equals(ItemFile.PROP_FILE_NAME) || column.equals(ItemFile.PROP_DESCRIPTION) || column.equals(ItemFile.PROP_LABEL) || column.equals(ItemFile.PROP_HIDDEN) || column.equals(ItemFile.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemFile process(ResultSet rs) throws SQLException
        {        
            return new ItemFile(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
              
       public ItemFile(Integer ItemFileId, String FileName, String Description, String Label, Integer Hidden, Integer ItemId)
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
            
            
        
            public Integer getHidden()
            {
                return this.hidden;
            }
            
            public void setHidden(Integer Hidden)
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
            
            
                   
            public Item getItem()
                {
                    return this.item;
                }

                public void setItem(Item item)
                {
                    this.item = item;
                }
                   
            
         
        
        
            
    }

