



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Vendor extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_VENDOR_ID = "VendorId";
        public static final String PROP_VENDOR_NAME = "VendorName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_VENDOR_STATUS = "VendorStatus";
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_VENDOR_TYPE_ID = "VendorTypeId";
        

        private Integer vendorId;
                
        private String vendorName;
                
        private String description;
                
        private Integer rank;
                
        private Integer vendorStatus;
                
        private Integer metaTagId;
        private MetaTag metaTag;        
        private Integer templateId;
        private Template template;        
        private Integer vendorTypeId;
        private VendorType vendorType;        
                 
        ArrayList<Item> itemList; 
        
        

        public Vendor()
        {
            this.vendorId = 0; 
       this.vendorName = ""; 
       this.description = ""; 
       this.rank = 0; 
       this.vendorStatus = 0; 
       this.metaTagId = 0; 
       this.templateId = 0; 
       this.vendorTypeId = 0; 
        
       itemList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return vendorId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("vendorId", vendorId == null ? 0 : vendorId);
                
            builder.add("vendorName", vendorName == null ? "" : vendorName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("rank", rank == null ? 0 : rank);
                
            builder.add("vendorStatus", vendorStatus == null ? 0 : vendorStatus);
                
            builder.add("metaTagId", metaTagId == null ? 0 : metaTagId);
                
            builder.add("templateId", templateId == null ? 0 : templateId);
                
            builder.add("vendorTypeId", vendorTypeId == null ? 0 : vendorTypeId);
        
        
    
     
     
     
     
     if(metaTag != null) metaTag.addJson(builder);
        
     if(template != null) template.addJson(builder);
        
     if(vendorType != null) vendorType.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Vendor.PROP_VENDOR_ID) || column.equals(Vendor.PROP_VENDOR_NAME) || column.equals(Vendor.PROP_DESCRIPTION) || column.equals(Vendor.PROP_RANK) || column.equals(Vendor.PROP_VENDOR_STATUS) || column.equals(Vendor.PROP_META_TAG_ID) || column.equals(Vendor.PROP_TEMPLATE_ID) || column.equals(Vendor.PROP_VENDOR_TYPE_ID) )
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
            if (column.equals(Vendor.PROP_VENDOR_ID) || column.equals(Vendor.PROP_RANK) || column.equals(Vendor.PROP_VENDOR_STATUS) || column.equals(Vendor.PROP_META_TAG_ID) || column.equals(Vendor.PROP_TEMPLATE_ID) || column.equals(Vendor.PROP_VENDOR_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Vendor process(ResultSet rs) throws SQLException
        {        
            return new Vendor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }
              
       public Vendor(Integer VendorId, String VendorName, String Description, Integer Rank, Integer VendorStatus, Integer MetaTagId, Integer TemplateId, Integer VendorTypeId)
       {
            this.vendorId = VendorId;
       this.vendorName = VendorName;
       this.description = Description;
       this.rank = Rank;
       this.vendorStatus = VendorStatus;
       this.metaTagId = MetaTagId;
       this.templateId = TemplateId;
       this.vendorTypeId = VendorTypeId;
              
       itemList = null; 
        
       } 
        
             
        
            public Integer getVendorId()
            {
                return this.vendorId;
            }
            
            public void setVendorId(Integer VendorId)
            {
                this.vendorId = VendorId;
            }
            
            
        
            public String getVendorName()
            {
                return this.vendorName;
            }
            
            public void setVendorName(String VendorName)
            {
                this.vendorName = VendorName;
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
            
            
        
            public Integer getVendorStatus()
            {
                return this.vendorStatus;
            }
            
            public void setVendorStatus(Integer VendorStatus)
            {
                this.vendorStatus = VendorStatus;
            }
            
            
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
            
            
                   
            public MetaTag getMetaTag()
                {
                    return this.metaTag;
                }

                public void setMetaTag(MetaTag metaTag)
                {
                    this.metaTag = metaTag;
                }
                   
            
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
            
            
                   
            public Template getTemplate()
                {
                    return this.template;
                }

                public void setTemplate(Template template)
                {
                    this.template = template;
                }
                   
            
        
            public Integer getVendorTypeId()
            {
                return this.vendorTypeId;
            }
            
            public void setVendorTypeId(Integer VendorTypeId)
            {
                this.vendorTypeId = VendorTypeId;
            }
            
            
                   
            public VendorType getVendorType()
                {
                    return this.vendorType;
                }

                public void setVendorType(VendorType vendorType)
                {
                    this.vendorType = vendorType;
                }
                   
            
         
        
        
        public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
            
    }

