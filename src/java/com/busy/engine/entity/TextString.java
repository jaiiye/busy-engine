











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class TextString extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        public static final String PROP_KEY = "Key";
        

        private Integer textStringId;
                
        private String key;
                
                 
        ArrayList<LocalizedString> localizedStringList; 
        
        

        public TextString()
        {
            this.textStringId = 0; 
       this.key = ""; 
        
       localizedStringList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return textStringId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("textStringId", textStringId).add("key", key);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TextString.PROP_TEXT_STRING_ID) || column.equals(TextString.PROP_KEY) )
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
            if (column.equals(TextString.PROP_TEXT_STRING_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TextString process(ResultSet rs) throws SQLException
        {        
            return new TextString(rs.getInt(1), rs.getString(2));
        }
              
       public TextString(Integer TextStringId, String Key)
       {
            this.textStringId = TextStringId;
       this.key = Key;
              
       localizedStringList = null; 
        
       } 
        
             
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
            
            
        
            public String getKey()
            {
                return this.key;
            }
            
            public void setKey(String Key)
            {
                this.key = Key;
            }
            
            
         
        
        
        public ArrayList<LocalizedString> getLocalizedStringList()
            {
                return this.localizedStringList;
            }
            
            public void setLocalizedStringList(ArrayList<LocalizedString> localizedStringList)
            {
                this.localizedStringList = localizedStringList;
            }
        
            
    }

