
package com.transitionsoft.dao;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FormField {
    
    public int fieldId;
    public String fieldName;
    public String fieldDataType;
    public String fieldLabel;    
    public String fieldErrorText;
    public String fieldValidationRegex;
    public int fieldRank;
    public String fieldDefaultValue;
    public int fieldOptional;
    public ArrayList<SimpleEntry<String,String>> fieldOptions;

    public FormField(String fieldName, String fieldDataType, String fieldLabel, String fieldErrorText, String fieldValidationRegex, int fieldRank, String fieldDefaultValue, String fieldOptions) {
        this.fieldName = fieldName;
        this.fieldDataType = fieldDataType;
        this.fieldLabel = fieldLabel;
        this.fieldErrorText = fieldErrorText;
        this.fieldValidationRegex = fieldValidationRegex;
        this.fieldRank = fieldRank;
        this.fieldDefaultValue = fieldDefaultValue;
        
        //parse the options
        this.fieldOptions = new ArrayList<SimpleEntry<String,String>>();
        if(fieldOptions != null){
            if(fieldOptions.length() > 0)
            {
                StringTokenizer st = new StringTokenizer(fieldOptions, "=,"); 
                while(st.hasMoreTokens()) 
                { 
                    String key = st.nextToken(); 
                    String val = st.nextToken(); 
                    this.fieldOptions.add(new SimpleEntry<String,String>(key,val));
                }
            }
        }
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDataType() {
        return fieldDataType;
    }

    public void setFieldDataType(String fieldDataType) {
        this.fieldDataType = fieldDataType;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldErrorText() {
        return fieldErrorText;
    }

    public void setFieldErrorText(String fieldErrorText) {
        this.fieldErrorText = fieldErrorText;
    }

    public String getFieldValidationRegex() {
        return fieldValidationRegex;
    }

    public void setFieldValidationRegex(String fieldValidationRegex) {
        this.fieldValidationRegex = fieldValidationRegex;
    }

    public int getFieldRank() {
        return fieldRank;
    }

    public void setFieldRank(int fieldRank) {
        this.fieldRank = fieldRank;
    }

    public String getFieldDefaultValue() {
        return fieldDefaultValue;
    }

    public void setFieldDefaultValue(String fieldDefaultValue) {
        this.fieldDefaultValue = fieldDefaultValue;
    }

    public ArrayList<SimpleEntry<String,String>> getFieldOptions() {
        return fieldOptions;
    }

    public void setFieldOptions(ArrayList<SimpleEntry<String,String>> fieldOptions) {
        this.fieldOptions = fieldOptions;
    }

    public int getFieldOptional() {
        return fieldOptional;
    }

    public void setFieldOptional(int fieldOptional) {
        this.fieldOptional = fieldOptional;
    }
    
    
}
