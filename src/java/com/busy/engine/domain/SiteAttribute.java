











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class SiteAttribute extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ATTRIBUTE_ID = "SiteAttributeId";
        public static final String PROP_ATTRIBUTE_KEY = "AttributeKey";
        public static final String PROP_ATTRIBUTE_VALUE = "AttributeValue";
        public static final String PROP_ATTRIBUTE_TYPE = "AttributeType";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteAttributeId;
                
        private String attributeKey;
                
        private String attributeValue;
                
        private String attributeType;
                
        private Integer siteId;
        private Site site;        
                 
        
        

        public SiteAttribute()
        {
            this.siteAttributeId = 0; 
       this.attributeKey = ""; 
       this.attributeValue = ""; 
       this.attributeType = ""; 
       this.siteId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return siteAttributeId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteAttribute.PROP_SITE_ATTRIBUTE_ID) || column.equals(SiteAttribute.PROP_ATTRIBUTE_KEY) || column.equals(SiteAttribute.PROP_ATTRIBUTE_VALUE) || column.equals(SiteAttribute.PROP_ATTRIBUTE_TYPE) || column.equals(SiteAttribute.PROP_SITE_ID) )
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
            if (column.equals(SiteAttribute.PROP_SITE_ATTRIBUTE_ID) || column.equals(SiteAttribute.PROP_SITE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteAttribute process(ResultSet rs) throws SQLException
        {        
            return new SiteAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        }
              
       public SiteAttribute(Integer SiteAttributeId, String AttributeKey, String AttributeValue, String AttributeType, Integer SiteId)
       {
            this.siteAttributeId = SiteAttributeId;
       this.attributeKey = AttributeKey;
       this.attributeValue = AttributeValue;
       this.attributeType = AttributeType;
       this.siteId = SiteId;
              
       
       } 
        
             
        
            public Integer getSiteAttributeId()
            {
                return this.siteAttributeId;
            }
            
            public void setSiteAttributeId(Integer SiteAttributeId)
            {
                this.siteAttributeId = SiteAttributeId;
            }
            
            
        
            public String getAttributeKey()
            {
                return this.attributeKey;
            }
            
            public void setAttributeKey(String AttributeKey)
            {
                this.attributeKey = AttributeKey;
            }
            
            
        
            public String getAttributeValue()
            {
                return this.attributeValue;
            }
            
            public void setAttributeValue(String AttributeValue)
            {
                this.attributeValue = AttributeValue;
            }
            
            
        
            public String getAttributeType()
            {
                return this.attributeType;
            }
            
            public void setAttributeType(String AttributeType)
            {
                this.attributeType = AttributeType;
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
                   
            
         
        
        
            
    }

