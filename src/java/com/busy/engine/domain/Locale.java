











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class Locale extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_LOCALE_ID = "LocaleId";
        public static final String PROP_LOCALE_STRING = "LocaleString";
        public static final String PROP_LOCALE_CHARACTER_SET = "LocaleCharacterSet";
        

        private Integer localeId;
                
        private String localeString;
                
        private String localeCharacterSet;
                
                 
        
        

        public Locale()
        {
            this.localeId = 0; 
       this.localeString = ""; 
       this.localeCharacterSet = ""; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return localeId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Locale.PROP_LOCALE_ID) || column.equals(Locale.PROP_LOCALE_STRING) || column.equals(Locale.PROP_LOCALE_CHARACTER_SET) )
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
            if (column.equals(Locale.PROP_LOCALE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Locale process(ResultSet rs) throws SQLException
        {        
            return new Locale(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public Locale(Integer LocaleId, String LocaleString, String LocaleCharacterSet)
       {
            this.localeId = LocaleId;
       this.localeString = LocaleString;
       this.localeCharacterSet = LocaleCharacterSet;
              
       
       } 
        
             
        
            public Integer getLocaleId()
            {
                return this.localeId;
            }
            
            public void setLocaleId(Integer LocaleId)
            {
                this.localeId = LocaleId;
            }
            
            
        
            public String getLocaleString()
            {
                return this.localeString;
            }
            
            public void setLocaleString(String LocaleString)
            {
                this.localeString = LocaleString;
            }
            
            
        
            public String getLocaleCharacterSet()
            {
                return this.localeCharacterSet;
            }
            
            public void setLocaleCharacterSet(String LocaleCharacterSet)
            {
                this.localeCharacterSet = LocaleCharacterSet;
            }
            
            
         
        
        
            
    }

