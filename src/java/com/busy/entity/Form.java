











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Form implements Serializable
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

