











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Page extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_PAGE_ID = "PageId";
        public static final String PROP_PAGE_NAME = "PageName";
        public static final String PROP_CONTENT = "Content";
        public static final String PROP_PAGE_STATUS = "PageStatus";
        public static final String PROP_FORM_ID = "FormId";
        public static final String PROP_SLIDER_ID = "SliderId";
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        

        private Integer pageId;
                
        private String pageName;
                
        private String content;
                
        private Integer pageStatus;
                
        private Integer formId;
        private Form form;        
        private Integer sliderId;
        private Slider slider;        
        private Integer metaTagId;
        private MetaTag metaTag;        
        private Integer templateId;
        private Template template;        
                 
        ArrayList<SitePage> sitePageList; 
        
        

        public Page()
        {
            this.pageId = 0; 
       this.pageName = ""; 
       this.content = ""; 
       this.pageStatus = 0; 
       this.formId = 0; 
       this.sliderId = 0; 
       this.metaTagId = 0; 
       this.templateId = 0; 
        
       sitePageList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return pageId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("pageId", pageId).add("pageName", pageName).add("content", content).add("pageStatus", pageStatus).add("formId", formId).add("sliderId", sliderId).add("metaTagId", metaTagId).add("templateId", templateId);
        
    if(form != null) form.addJson(builder);
        if(slider != null) slider.addJson(builder);
        if(metaTag != null) metaTag.addJson(builder);
        if(template != null) template.addJson(builder);
                 
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Page.PROP_PAGE_ID) || column.equals(Page.PROP_PAGE_NAME) || column.equals(Page.PROP_CONTENT) || column.equals(Page.PROP_PAGE_STATUS) || column.equals(Page.PROP_FORM_ID) || column.equals(Page.PROP_SLIDER_ID) || column.equals(Page.PROP_META_TAG_ID) || column.equals(Page.PROP_TEMPLATE_ID) )
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
            if (column.equals(Page.PROP_PAGE_ID) || column.equals(Page.PROP_PAGE_STATUS) || column.equals(Page.PROP_FORM_ID) || column.equals(Page.PROP_SLIDER_ID) || column.equals(Page.PROP_META_TAG_ID) || column.equals(Page.PROP_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Page process(ResultSet rs) throws SQLException
        {        
            return new Page(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }
              
       public Page(Integer PageId, String PageName, String Content, Integer PageStatus, Integer FormId, Integer SliderId, Integer MetaTagId, Integer TemplateId)
       {
            this.pageId = PageId;
       this.pageName = PageName;
       this.content = Content;
       this.pageStatus = PageStatus;
       this.formId = FormId;
       this.sliderId = SliderId;
       this.metaTagId = MetaTagId;
       this.templateId = TemplateId;
              
       sitePageList = null; 
        
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
            
            
        
            public Integer getPageStatus()
            {
                return this.pageStatus;
            }
            
            public void setPageStatus(Integer PageStatus)
            {
                this.pageStatus = PageStatus;
            }
            
            
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
            
            
                   
            public Form getForm()
                {
                    return this.form;
                }

                public void setForm(Form form)
                {
                    this.form = form;
                }
                   
            
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
            }
            
            
                   
            public Slider getSlider()
                {
                    return this.slider;
                }

                public void setSlider(Slider slider)
                {
                    this.slider = slider;
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
                   
            
         
        
        
        public ArrayList<SitePage> getSitePageList()
            {
                return this.sitePageList;
            }
            
            public void setSitePageList(ArrayList<SitePage> sitePageList)
            {
                this.sitePageList = sitePageList;
            }
        
            
    }

