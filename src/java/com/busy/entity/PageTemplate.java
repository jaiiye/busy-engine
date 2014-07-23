











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class PageTemplate implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_PAGE_TEMPLATE_ID = "PageTemplateId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_MARKUP = "Markup";
        

        private Integer pageTemplateId;
                
        private String name;
                
        private String markup;
                
                 
        
        

        public PageTemplate()
        {
            this.pageTemplateId = 0; 
       this.name = ""; 
       this.markup = ""; 
        
       
       }
        
        public PageTemplate(Integer PageTemplateId, String Name, String Markup)
        {
            this.pageTemplateId = PageTemplateId;
       this.name = Name;
       this.markup = Markup;
              
       
       } 
        
             
        
            public Integer getPageTemplateId()
            {
                return this.pageTemplateId;
            }
            
            public void setPageTemplateId(Integer PageTemplateId)
            {
                this.pageTemplateId = PageTemplateId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
        
            public String getMarkup()
            {
                return this.markup;
            }
            
            public void setMarkup(String Markup)
            {
                this.markup = Markup;
            }
            
            
         
        
        
            
    }

