


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class TemplateType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEMPLATE_TYPE_ID = "TemplateTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_VALUE = "Value";
        

        private Integer templateTypeId;
        private String typeName;
        private String value;
        

        public TemplateType()
        {
            this.templateTypeId = 0; 
       this.typeName = ""; 
       this.value = ""; 
        }
        
        public TemplateType(Integer TemplateTypeId, String TypeName, String Value)
        {
            this.templateTypeId = TemplateTypeId;
       this.typeName = TypeName;
       this.value = Value;
        } 
        
             
        
            public Integer getTemplateTypeId()
            {
                return this.templateTypeId;
            }
            
            public void setTemplateTypeId(Integer TemplateTypeId)
            {
                this.templateTypeId = TemplateTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
           
            
    }

