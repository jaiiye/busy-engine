











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class LocalizedString extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_LOCALIZED_STRING_ID = "LocalizedStringId";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_STRING_VALUE = "StringValue";
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        

        private Integer localizedStringId;
                
        private Integer locale;
                
        private String stringValue;
                
        private Integer textStringId;
        private TextString textString;        
                 
        
        

        public LocalizedString()
        {
            this.localizedStringId = 0; 
       this.locale = 0; 
       this.stringValue = ""; 
       this.textStringId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return localizedStringId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("localizedStringId", localizedStringId).add("locale", locale).add("stringValue", stringValue).add("textStringId", textStringId);
        
    if(textString != null) textString.addJson(builder);
                 
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(LocalizedString.PROP_LOCALIZED_STRING_ID) || column.equals(LocalizedString.PROP_LOCALE) || column.equals(LocalizedString.PROP_STRING_VALUE) || column.equals(LocalizedString.PROP_TEXT_STRING_ID) )
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
            if (column.equals(LocalizedString.PROP_LOCALIZED_STRING_ID) || column.equals(LocalizedString.PROP_LOCALE) || column.equals(LocalizedString.PROP_TEXT_STRING_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static LocalizedString process(ResultSet rs) throws SQLException
        {        
            return new LocalizedString(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
        }
              
       public LocalizedString(Integer LocalizedStringId, Integer Locale, String StringValue, Integer TextStringId)
       {
            this.localizedStringId = LocalizedStringId;
       this.locale = Locale;
       this.stringValue = StringValue;
       this.textStringId = TextStringId;
              
       
       } 
        
             
        
            public Integer getLocalizedStringId()
            {
                return this.localizedStringId;
            }
            
            public void setLocalizedStringId(Integer LocalizedStringId)
            {
                this.localizedStringId = LocalizedStringId;
            }
            
            
        
            public Integer getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(Integer Locale)
            {
                this.locale = Locale;
            }
            
            
        
            public String getStringValue()
            {
                return this.stringValue;
            }
            
            public void setStringValue(String StringValue)
            {
                this.stringValue = StringValue;
            }
            
            
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
            
            
                   
            public TextString getTextString()
                {
                    return this.textString;
                }

                public void setTextString(TextString textString)
                {
                    this.textString = textString;
                }
                   
            
         
        
        
            
    }

