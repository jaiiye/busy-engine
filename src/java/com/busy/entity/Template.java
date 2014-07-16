


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Template implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_TEMPLATE_NAME = "TemplateName";
        public static final String PROP_MARKUP = "Markup";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_TEMPLATE_TYPE_ID = "TemplateTypeId";
        

        private Integer templateId;
        private String templateName;
        private String markup;
        private Integer status;
        private Integer templateTypeId;
        

        public Template()
        {
            this.templateId = 0; 
       this.templateName = ""; 
       this.markup = ""; 
       this.status = 0; 
       this.templateTypeId = 0; 
        }
        
        public Template(Integer TemplateId, String TemplateName, String Markup, Integer Status, Integer TemplateTypeId)
        {
            this.templateId = TemplateId;
       this.templateName = TemplateName;
       this.markup = Markup;
       this.status = Status;
       this.templateTypeId = TemplateTypeId;
        } 
        
             
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
        
            public String getTemplateName()
            {
                return this.templateName;
            }
            
            public void setTemplateName(String TemplateName)
            {
                this.templateName = TemplateName;
            }
        
            public String getMarkup()
            {
                return this.markup;
            }
            
            public void setMarkup(String Markup)
            {
                this.markup = Markup;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getTemplateTypeId()
            {
                return this.templateTypeId;
            }
            
            public void setTemplateTypeId(Integer TemplateTypeId)
            {
                this.templateTypeId = TemplateTypeId;
            }
           
            
    }

