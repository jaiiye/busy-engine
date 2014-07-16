


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Vendor implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_VENDOR_ID = "VendorId";
        public static final String PROP_VENDOR_NAME = "VendorName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_VENDOR_TYPE_ID = "VendorTypeId";
        

        private Integer vendorId;
        private String vendorName;
        private String description;
        private Integer rank;
        private Integer status;
        private Integer metaTagId;
        private Integer templateId;
        private Integer vendorTypeId;
        

        public Vendor()
        {
            this.vendorId = 0; 
       this.vendorName = ""; 
       this.description = ""; 
       this.rank = 0; 
       this.status = 0; 
       this.metaTagId = 0; 
       this.templateId = 0; 
       this.vendorTypeId = 0; 
        }
        
        public Vendor(Integer VendorId, String VendorName, String Description, Integer Rank, Integer Status, Integer MetaTagId, Integer TemplateId, Integer VendorTypeId)
        {
            this.vendorId = VendorId;
       this.vendorName = VendorName;
       this.description = Description;
       this.rank = Rank;
       this.status = Status;
       this.metaTagId = MetaTagId;
       this.templateId = TemplateId;
       this.vendorTypeId = VendorTypeId;
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
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
        
            public Integer getVendorTypeId()
            {
                return this.vendorTypeId;
            }
            
            public void setVendorTypeId(Integer VendorTypeId)
            {
                this.vendorTypeId = VendorTypeId;
            }
           
            
    }

