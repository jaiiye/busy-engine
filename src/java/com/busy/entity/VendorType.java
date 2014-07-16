


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class VendorType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_VENDOR_TYPE_ID = "VendorTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer vendorTypeId;
        private String typeName;
        

        public VendorType()
        {
            this.vendorTypeId = 0; 
       this.typeName = ""; 
        }
        
        public VendorType(Integer VendorTypeId, String TypeName)
        {
            this.vendorTypeId = VendorTypeId;
       this.typeName = TypeName;
        } 
        
             
        
            public Integer getVendorTypeId()
            {
                return this.vendorTypeId;
            }
            
            public void setVendorTypeId(Integer VendorTypeId)
            {
                this.vendorTypeId = VendorTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
           
            
    }

