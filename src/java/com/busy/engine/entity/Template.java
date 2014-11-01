






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Template extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_TEMPLATE_NAME = "TemplateName";
        public static final String PROP_MARKUP = "Markup";
        public static final String PROP_TEMPLATE_STATUS = "TemplateStatus";
        public static final String PROP_TEMPLATE_TYPE_ID = "TemplateTypeId";
        public static final String PROP_PARENT_TEMPLATE_ID = "ParentTemplateId";
        

        private Integer templateId;
                
        private String templateName;
                
        private String markup;
                
        private Integer templateStatus;
                
        private Integer templateTypeId;
        private TemplateType templateType;        
        private Integer parentTemplateId;
        private Template parentTemplate;        
                 
        ArrayList<Item> itemList; 
        ArrayList<Page> pageList; 
        ArrayList<ResourceUrl> resourceUrlList; 
        ArrayList<Site> siteList; 
        ArrayList<Vendor> vendorList; 
        
        

        public Template()
        {
            this.templateId = 0; 
       this.templateName = ""; 
       this.markup = ""; 
       this.templateStatus = 0; 
       this.templateTypeId = 0; 
       this.parentTemplateId = 0; 
        
       itemList = null; 
        pageList = null; 
        resourceUrlList = null; 
        siteList = null; 
        vendorList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return templateId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("templateId", templateId == null ? 0 : templateId);
                
            builder.add("templateName", templateName == null ? "" : templateName);
                
            builder.add("markup", markup == null ? "" : markup);
                
            builder.add("templateStatus", templateStatus == null ? 0 : templateStatus);
                
            builder.add("templateTypeId", templateTypeId == null ? 0 : templateTypeId);
                
            builder.add("parentTemplateId", parentTemplateId == null ? 0 : parentTemplateId);
        
        
    
     
     
     
     if(templateType != null) templateType.addJson(builder);
        
     if(parentTemplate != null) parentTemplate.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Template.PROP_TEMPLATE_ID) || column.equals(Template.PROP_TEMPLATE_NAME) || column.equals(Template.PROP_MARKUP) || column.equals(Template.PROP_TEMPLATE_STATUS) || column.equals(Template.PROP_TEMPLATE_TYPE_ID) || column.equals(Template.PROP_PARENT_TEMPLATE_ID) )
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
            if (column.equals(Template.PROP_TEMPLATE_ID) || column.equals(Template.PROP_TEMPLATE_STATUS) || column.equals(Template.PROP_TEMPLATE_TYPE_ID) || column.equals(Template.PROP_PARENT_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Template process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Template(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
        }
              
       public Template(Integer TemplateId, String TemplateName, String Markup, Integer TemplateStatus, Integer TemplateTypeId, Integer ParentTemplateId)
       {
            this.templateId = TemplateId;
       this.templateName = TemplateName;
       this.markup = Markup;
       this.templateStatus = TemplateStatus;
       this.templateTypeId = TemplateTypeId;
       this.parentTemplateId = ParentTemplateId;
              
       itemList = null; 
        pageList = null; 
        resourceUrlList = null; 
        siteList = null; 
        vendorList = null; 
        
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
            
            
        
            public Integer getTemplateStatus()
            {
                return this.templateStatus;
            }
            
            public void setTemplateStatus(Integer TemplateStatus)
            {
                this.templateStatus = TemplateStatus;
            }
            
            
        
            public Integer getTemplateTypeId()
            {
                return this.templateTypeId;
            }
            
            public void setTemplateTypeId(Integer TemplateTypeId)
            {
                this.templateTypeId = TemplateTypeId;
            }
            
            
                   
            public TemplateType getTemplateType()
                {
                    return this.templateType;
                }

                public void setTemplateType(TemplateType templateType)
                {
                    this.templateType = templateType;
                }
                   
            
        
            public Integer getParentTemplateId()
            {
                return this.parentTemplateId;
            }
            
            public void setParentTemplateId(Integer ParentTemplateId)
            {
                this.parentTemplateId = ParentTemplateId;
            }
            
            
                   
            public Template getParentTemplate()
                {
                    return this.parentTemplate;
                }

                public void setParentTemplate(Template parentTemplate)
                {
                    this.parentTemplate = parentTemplate;
                }
                   
            
         
        
        
        public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
        public ArrayList<Page> getPageList()
            {
                return this.pageList;
            }
            
            public void setPageList(ArrayList<Page> pageList)
            {
                this.pageList = pageList;
            }
        
        public ArrayList<ResourceUrl> getResourceUrlList()
            {
                return this.resourceUrlList;
            }
            
            public void setResourceUrlList(ArrayList<ResourceUrl> resourceUrlList)
            {
                this.resourceUrlList = resourceUrlList;
            }
        
        public ArrayList<Site> getSiteList()
            {
                return this.siteList;
            }
            
            public void setSiteList(ArrayList<Site> siteList)
            {
                this.siteList = siteList;
            }
        
        public ArrayList<Vendor> getVendorList()
            {
                return this.vendorList;
            }
            
            public void setVendorList(ArrayList<Vendor> vendorList)
            {
                this.vendorList = vendorList;
            }
        
            
    }

