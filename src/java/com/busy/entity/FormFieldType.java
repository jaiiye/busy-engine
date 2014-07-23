package com.busy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class FormFieldType implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_FORM_FIELD_TYPE_ID = "FormFieldTypeId";
    public static final String PROP_TYPE_NAME = "TypeName";
    public static final String PROP_INPUT_TYPE = "InputType";

    private Integer formFieldTypeId;

    private String typeName;

    private String inputType;

    ArrayList<FormField> formFieldList;

    public FormFieldType()
    {
        this.formFieldTypeId = 0;
        this.typeName = "";
        this.inputType = "";

        formFieldList = null;

    }

    public FormFieldType(Integer FormFieldTypeId, String TypeName, String InputType)
    {
        this.formFieldTypeId = FormFieldTypeId;
        this.typeName = TypeName;
        this.inputType = InputType;

        formFieldList = null;

    }

    public Integer getFormFieldTypeId()
    {
        return this.formFieldTypeId;
    }

    public void setFormFieldTypeId(Integer FormFieldTypeId)
    {
        this.formFieldTypeId = FormFieldTypeId;
    }

    public String getTypeName()
    {
        return this.typeName;
    }

    public void setTypeName(String TypeName)
    {
        this.typeName = TypeName;
    }

    public String getInputType()
    {
        return this.inputType;
    }

    public void setInputType(String InputType)
    {
        this.inputType = InputType;
    }

    public ArrayList<FormField> getFormFieldList()
    {
        return this.formFieldList;
    }

    public void setFormFieldList(ArrayList<FormField> formFieldList)
    {
        this.formFieldList = formFieldList;
    }

}
