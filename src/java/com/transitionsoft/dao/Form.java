
package com.transitionsoft.dao;

import java.util.ArrayList;

public class Form 
{
    public int formId;
    public String formName;
    public String formDescription;
    public String formSubmissionEmail;    
    public String formSubmissionMethod;
    public String formAction;
    public boolean formResettable;
    public boolean formFileUpload;
    public ArrayList<FormField> fields;

    public Form(String formName, String formDescription, String formSubmissionEmail, String formSubmissionMethod, String formAction, boolean formReset, boolean formFileUpload) {
        this.formName = formName;
        this.formDescription = formDescription;
        this.formSubmissionEmail = formSubmissionEmail;
        this.formSubmissionMethod = formSubmissionMethod;
        this.formAction = formAction;
        this.formResettable = formReset;
        this.formFileUpload = formFileUpload;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

    public String getFormSubmissionEmail() {
        return formSubmissionEmail;
    }

    public void setFormSubmissionEmail(String formSubmissionEmail) {
        this.formSubmissionEmail = formSubmissionEmail;
    }

    public String getFormSubmissionMethod() {
        return formSubmissionMethod;
    }

    public void setFormSubmissionMethod(String formSubmissionMethod) {
        this.formSubmissionMethod = formSubmissionMethod;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public boolean isFormResettable() {
        return formResettable;
    }

    public void setFormResettable(boolean formReset) {
        this.formResettable = formReset;
    }

    public boolean getFormFileUpload() {
        return formFileUpload;
    }

    public void setFormFileUpload(boolean formFileUpload) {
        this.formFileUpload = formFileUpload;
    }

    public ArrayList<FormField> getFields() {
        return fields;
    }

    public void setFields(ArrayList<FormField> fields) {
        this.fields = fields;
    }


}
