


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Page implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_PAGE_ID = "PageId";
        public static final String PROP_PAGE_NAME = "PageName";
        public static final String PROP_CONTENT = "Content";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_FORM_ID = "FormId";
        public static final String PROP_SLIDER_ID = "SliderId";
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        

        private Integer pageId;
        private String pageName;
        private String content;
        private Integer status;
        private Integer formId;
        private Integer sliderId;
        private Integer metaTagId;
        private Integer templateId;
        

        public Page()
        {
            this.pageId = 0; 
       this.pageName = ""; 
       this.content = ""; 
       this.status = 0; 
       this.formId = 0; 
       this.sliderId = 0; 
       this.metaTagId = 0; 
       this.templateId = 0; 
        }
        
        public Page(Integer PageId, String PageName, String Content, Integer Status, Integer FormId, Integer SliderId, Integer MetaTagId, Integer TemplateId)
        {
            this.pageId = PageId;
       this.pageName = PageName;
       this.content = Content;
       this.status = Status;
       this.formId = FormId;
       this.sliderId = SliderId;
       this.metaTagId = MetaTagId;
       this.templateId = TemplateId;
        } 
        
             
        
            public Integer getPageId()
            {
                return this.pageId;
            }
            
            public void setPageId(Integer PageId)
            {
                this.pageId = PageId;
            }
        
            public String getPageName()
            {
                return this.pageName;
            }
            
            public void setPageName(String PageName)
            {
                this.pageName = PageName;
            }
        
            public String getContent()
            {
                return this.content;
            }
            
            public void setContent(String Content)
            {
                this.content = Content;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
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
           
            
    }

