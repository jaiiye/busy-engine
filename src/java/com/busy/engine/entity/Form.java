






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Form extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_FORM_ID = "FormId";
        public static final String PROP_FORM_NAME = "FormName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_SUBMISSION_EMAIL = "SubmissionEmail";
        public static final String PROP_SUBMISSION_METHOD = "SubmissionMethod";
        public static final String PROP_ACTION = "Action";
        public static final String PROP_RESETTABLE = "Resettable";
        public static final String PROP_FILE_UPLOAD = "FileUpload";
        

        private Integer formId;
                
        private String formName;
                
        private String description;
                
        private String submissionEmail;
                
        private String submissionMethod;
                
        private String action;
                
        private Integer resettable;
                
        private Integer fileUpload;
                
                 
        ArrayList<FormField> formFieldList; 
        ArrayList<Page> pageList; 
        ArrayList<Slider> sliderList; 
        
        

        public Form()
        {
            this.formId = 0; 
       this.formName = ""; 
       this.description = ""; 
       this.submissionEmail = ""; 
       this.submissionMethod = ""; 
       this.action = ""; 
       this.resettable = 0; 
       this.fileUpload = 0; 
        
       formFieldList = null; 
        pageList = null; 
        sliderList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return formId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("formId", formId == null ? 0 : formId);
                
            builder.add("formName", formName == null ? "" : formName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("submissionEmail", submissionEmail == null ? "" : submissionEmail);
                
            builder.add("submissionMethod", submissionMethod == null ? "" : submissionMethod);
                
            builder.add("action", action == null ? "" : action);
                
            builder.add("resettable", resettable == null ? 0 : resettable);
                
            builder.add("fileUpload", fileUpload == null ? 0 : fileUpload);
        
        
    
     
     
     
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Form.PROP_FORM_ID) || column.equals(Form.PROP_FORM_NAME) || column.equals(Form.PROP_DESCRIPTION) || column.equals(Form.PROP_SUBMISSION_EMAIL) || column.equals(Form.PROP_SUBMISSION_METHOD) || column.equals(Form.PROP_ACTION) || column.equals(Form.PROP_RESETTABLE) || column.equals(Form.PROP_FILE_UPLOAD) )
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
            if (column.equals(Form.PROP_FORM_ID) || column.equals(Form.PROP_RESETTABLE) || column.equals(Form.PROP_FILE_UPLOAD) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Form process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Form(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        }
              
       public Form(Integer FormId, String FormName, String Description, String SubmissionEmail, String SubmissionMethod, String Action, Integer Resettable, Integer FileUpload)
       {
            this.formId = FormId;
       this.formName = FormName;
       this.description = Description;
       this.submissionEmail = SubmissionEmail;
       this.submissionMethod = SubmissionMethod;
       this.action = Action;
       this.resettable = Resettable;
       this.fileUpload = FileUpload;
              
       formFieldList = null; 
        pageList = null; 
        sliderList = null; 
        
       } 
        
             
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
            
            
        
            public String getFormName()
            {
                return this.formName;
            }
            
            public void setFormName(String FormName)
            {
                this.formName = FormName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getSubmissionEmail()
            {
                return this.submissionEmail;
            }
            
            public void setSubmissionEmail(String SubmissionEmail)
            {
                this.submissionEmail = SubmissionEmail;
            }
            
            
        
            public String getSubmissionMethod()
            {
                return this.submissionMethod;
            }
            
            public void setSubmissionMethod(String SubmissionMethod)
            {
                this.submissionMethod = SubmissionMethod;
            }
            
            
        
            public String getAction()
            {
                return this.action;
            }
            
            public void setAction(String Action)
            {
                this.action = Action;
            }
            
            
        
            public Integer getResettable()
            {
                return this.resettable;
            }
            
            public void setResettable(Integer Resettable)
            {
                this.resettable = Resettable;
            }
            
            
        
            public Integer getFileUpload()
            {
                return this.fileUpload;
            }
            
            public void setFileUpload(Integer FileUpload)
            {
                this.fileUpload = FileUpload;
            }
            
            
         
        
        
        public ArrayList<FormField> getFormFieldList()
            {
                return this.formFieldList;
            }
            
            public void setFormFieldList(ArrayList<FormField> formFieldList)
            {
                this.formFieldList = formFieldList;
            }
        
        public ArrayList<Page> getPageList()
            {
                return this.pageList;
            }
            
            public void setPageList(ArrayList<Page> pageList)
            {
                this.pageList = pageList;
            }
        
        public ArrayList<Slider> getSliderList()
            {
                return this.sliderList;
            }
            
            public void setSliderList(ArrayList<Slider> sliderList)
            {
                this.sliderList = sliderList;
            }
        
            
    }

