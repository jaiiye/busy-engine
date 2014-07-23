











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class TemplateType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEMPLATE_TYPE_ID = "TemplateTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_TYPE_VALUE = "TypeValue";
        

        private Integer templateTypeId;
                
        private String typeName;
                
        private String typeValue;
                
                 
        ArrayList<Template> templateList; 
        
        

        public TemplateType()
        {
            this.templateTypeId = 0; 
       this.typeName = ""; 
       this.typeValue = ""; 
        
       templateList = null; 
        
       }
        
        public TemplateType(Integer TemplateTypeId, String TypeName, String TypeValue)
        {
            this.templateTypeId = TemplateTypeId;
       this.typeName = TypeName;
       this.typeValue = TypeValue;
              
       templateList = null; 
        
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
            
            
        
            public String getTypeValue()
            {
                return this.typeValue;
            }
            
            public void setTypeValue(String TypeValue)
            {
                this.typeValue = TypeValue;
            }
            
            
         
        
        
            public ArrayList<Template> getTemplateList()
            {
                return this.templateList;
            }
            
            public void setTemplateList(ArrayList<Template> templateList)
            {
                this.templateList = templateList;
            }
        
            
    }

