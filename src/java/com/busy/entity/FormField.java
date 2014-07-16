


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class FormField implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_FORM_FIELD_ID = "FormFieldId";
        public static final String PROP_FIELD_NAME = "FieldName";
        public static final String PROP_LABEL = "Label";
        public static final String PROP_ERROR_TEXT = "ErrorText";
        public static final String PROP_VALIDATION_REGEX = "ValidationRegex";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_DEFAULT_VALUE = "DefaultValue";
        public static final String PROP_OPTIONS = "Options";
        public static final String PROP_GROUP_NAME = "GroupName";
        public static final String PROP_OPTIONAL = "Optional";
        public static final String PROP_FORM_FIELD_TYPE_ID = "FormFieldTypeId";
        public static final String PROP_FORM_ID = "FormId";
        

        private Integer formFieldId;
        private String fieldName;
        private String label;
        private String errorText;
        private String validationRegex;
        private Integer rank;
        private String defaultValue;
        private String options;
        private String groupName;
        private Integer optional;
        private Integer formFieldTypeId;
        private Integer formId;
        

        public FormField()
        {
            this.formFieldId = 0; 
       this.fieldName = ""; 
       this.label = ""; 
       this.errorText = ""; 
       this.validationRegex = ""; 
       this.rank = 0; 
       this.defaultValue = ""; 
       this.options = ""; 
       this.groupName = ""; 
       this.optional = 0; 
       this.formFieldTypeId = 0; 
       this.formId = 0; 
        }
        
        public FormField(Integer FormFieldId, String FieldName, String Label, String ErrorText, String ValidationRegex, Integer Rank, String DefaultValue, String Options, String GroupName, Integer Optional, Integer FormFieldTypeId, Integer FormId)
        {
            this.formFieldId = FormFieldId;
       this.fieldName = FieldName;
       this.label = Label;
       this.errorText = ErrorText;
       this.validationRegex = ValidationRegex;
       this.rank = Rank;
       this.defaultValue = DefaultValue;
       this.options = Options;
       this.groupName = GroupName;
       this.optional = Optional;
       this.formFieldTypeId = FormFieldTypeId;
       this.formId = FormId;
        } 
        
             
        
            public Integer getFormFieldId()
            {
                return this.formFieldId;
            }
            
            public void setFormFieldId(Integer FormFieldId)
            {
                this.formFieldId = FormFieldId;
            }
        
            public String getFieldName()
            {
                return this.fieldName;
            }
            
            public void setFieldName(String FieldName)
            {
                this.fieldName = FieldName;
            }
        
            public String getLabel()
            {
                return this.label;
            }
            
            public void setLabel(String Label)
            {
                this.label = Label;
            }
        
            public String getErrorText()
            {
                return this.errorText;
            }
            
            public void setErrorText(String ErrorText)
            {
                this.errorText = ErrorText;
            }
        
            public String getValidationRegex()
            {
                return this.validationRegex;
            }
            
            public void setValidationRegex(String ValidationRegex)
            {
                this.validationRegex = ValidationRegex;
            }
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
        
            public String getDefaultValue()
            {
                return this.defaultValue;
            }
            
            public void setDefaultValue(String DefaultValue)
            {
                this.defaultValue = DefaultValue;
            }
        
            public String getOptions()
            {
                return this.options;
            }
            
            public void setOptions(String Options)
            {
                this.options = Options;
            }
        
            public String getGroupName()
            {
                return this.groupName;
            }
            
            public void setGroupName(String GroupName)
            {
                this.groupName = GroupName;
            }
        
            public Integer getOptional()
            {
                return this.optional;
            }
            
            public void setOptional(Integer Optional)
            {
                this.optional = Optional;
            }
        
            public Integer getFormFieldTypeId()
            {
                return this.formFieldTypeId;
            }
            
            public void setFormFieldTypeId(Integer FormFieldTypeId)
            {
                this.formFieldTypeId = FormFieldTypeId;
            }
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
           
            
    }

