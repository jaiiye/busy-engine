


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
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
        private Boolean resettable;
        private Boolean fileUpload;
        

        public Form()
        {
            this.formId = 0; 
       this.formName = ""; 
       this.description = ""; 
       this.submissionEmail = ""; 
       this.submissionMethod = ""; 
       this.action = ""; 
       this.resettable = null; 
       this.fileUpload = null; 
        }
        
        public Form(Integer FormId, String FormName, String Description, String SubmissionEmail, String SubmissionMethod, String Action, Boolean Resettable, Boolean FileUpload)
        {
            this.formId = FormId;
       this.formName = FormName;
       this.description = Description;
       this.submissionEmail = SubmissionEmail;
       this.submissionMethod = SubmissionMethod;
       this.action = Action;
       this.resettable = Resettable;
       this.fileUpload = FileUpload;
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
        
            public Boolean getResettable()
            {
                return this.resettable;
            }
            
            public void setResettable(Boolean Resettable)
            {
                this.resettable = Resettable;
            }
        
            public Boolean getFileUpload()
            {
                return this.fileUpload;
            }
            
            public void setFileUpload(Boolean FileUpload)
            {
                this.fileUpload = FileUpload;
            }
           
            
    }

