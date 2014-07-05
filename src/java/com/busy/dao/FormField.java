package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FormField extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_FORMFIELDID = "FormFieldId";
    public static final String PROP_FORMID = "FormId";
    public static final String PROP_FIELDNAME = "FieldName";
    public static final String PROP_FIELDDATATYPE = "FieldDataType";
    public static final String PROP_FIELDLABEL = "FieldLabel";
    public static final String PROP_FIELDERRORTEXT = "FieldErrorText";
    public static final String PROP_FIELDVALIDATIONREGEX = "FieldValidationRegex";
    public static final String PROP_FIELDRANK = "FieldRank";
    public static final String PROP_FIELDDEFAULTVALUE = "FieldDefaultValue";
    public static final String PROP_FIELDOPTIONS = "FieldOptions";
    public static final String PROP_FIELDGROUPNAME = "FieldGroupName";
    public static final String PROP_FIELDOPTIONAL = "FieldOptional";

    private Integer formFieldId;
    private Integer formId;
    private String fieldName;
    private String fieldDataType;
    private String fieldLabel;
    private String fieldErrorText;
    private String fieldValidationRegex;
    private Integer fieldRank;
    private String fieldDefaultValue;
    private String fieldOptions;
    private String fieldGroupName;
    private Integer fieldOptional;

    public FormField()
    {
        this.formFieldId = 0;
        this.formId = 0;
        this.fieldName = "";
        this.fieldDataType = "";
        this.fieldLabel = "";
        this.fieldErrorText = "";
        this.fieldValidationRegex = "";
        this.fieldRank = 0;
        this.fieldDefaultValue = "";
        this.fieldOptions = "";
        this.fieldGroupName = "";
        this.fieldOptional = 0;
    }

    public FormField(Integer FormFieldId, Integer FormId, String FieldName, String FieldDataType, String FieldLabel, String FieldErrorText, String FieldValidationRegex, Integer FieldRank, String FieldDefaultValue, String FieldOptions, String FieldGroupName, Integer FieldOptional)
    {
        this.formFieldId = FormFieldId;
        this.formId = FormId;
        this.fieldName = FieldName;
        this.fieldDataType = FieldDataType;
        this.fieldLabel = FieldLabel;
        this.fieldErrorText = FieldErrorText;
        this.fieldValidationRegex = FieldValidationRegex;
        this.fieldRank = FieldRank;
        this.fieldDefaultValue = FieldDefaultValue;
        this.fieldOptions = FieldOptions;
        this.fieldGroupName = FieldGroupName;
        this.fieldOptional = FieldOptional;
    }

    public Integer getFormFieldId()
    {
        return this.formFieldId;
    }

    public void setFormFieldId(Integer FormFieldId)
    {
        this.formFieldId = FormFieldId;
    }

    public Integer getFormId()
    {
        return this.formId;
    }

    public void setFormId(Integer FormId)
    {
        this.formId = FormId;
    }

    public String getFieldName()
    {
        return this.fieldName;
    }

    public void setFieldName(String FieldName)
    {
        this.fieldName = FieldName;
    }

    public String getFieldDataType()
    {
        return this.fieldDataType;
    }

    public void setFieldDataType(String FieldDataType)
    {
        this.fieldDataType = FieldDataType;
    }

    public String getFieldLabel()
    {
        return this.fieldLabel;
    }

    public void setFieldLabel(String FieldLabel)
    {
        this.fieldLabel = FieldLabel;
    }

    public String getFieldErrorText()
    {
        return this.fieldErrorText;
    }

    public void setFieldErrorText(String FieldErrorText)
    {
        this.fieldErrorText = FieldErrorText;
    }

    public String getFieldValidationRegex()
    {
        return this.fieldValidationRegex;
    }

    public void setFieldValidationRegex(String FieldValidationRegex)
    {
        this.fieldValidationRegex = FieldValidationRegex;
    }

    public Integer getFieldRank()
    {
        return this.fieldRank;
    }

    public void setFieldRank(Integer FieldRank)
    {
        this.fieldRank = FieldRank;
    }

    public String getFieldDefaultValue()
    {
        return this.fieldDefaultValue;
    }

    public void setFieldDefaultValue(String FieldDefaultValue)
    {
        this.fieldDefaultValue = FieldDefaultValue;
    }

    public String getFieldOptions()
    {
        return this.fieldOptions;
    }

    public void setFieldOptions(String FieldOptions)
    {
        this.fieldOptions = FieldOptions;
    }

    public String getFieldGroupName()
    {
        return this.fieldGroupName;
    }

    public void setFieldGroupName(String FieldGroupName)
    {
        this.fieldGroupName = FieldGroupName;
    }

    public Integer getFieldOptional()
    {
        return this.fieldOptional;
    }

    public void setFieldOptional(Integer FieldOptional)
    {
        this.fieldOptional = FieldOptional;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(FormField.PROP_FORMFIELDID) || column.equals(FormField.PROP_FORMID) || column.equals(FormField.PROP_FIELDNAME) || column.equals(FormField.PROP_FIELDDATATYPE) || column.equals(FormField.PROP_FIELDLABEL) || column.equals(FormField.PROP_FIELDERRORTEXT) || column.equals(FormField.PROP_FIELDVALIDATIONREGEX) || column.equals(FormField.PROP_FIELDRANK) || column.equals(FormField.PROP_FIELDDEFAULTVALUE) || column.equals(FormField.PROP_FIELDOPTIONS) || column.equals(FormField.PROP_FIELDGROUPNAME) || column.equals(FormField.PROP_FIELDOPTIONAL))
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
        if (column.equals(FormField.PROP_FORMFIELDID) || column.equals(FormField.PROP_FORMID) || column.equals(FormField.PROP_FIELDRANK) || column.equals(FormField.PROP_FIELDOPTIONAL))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static FormField processFormField(ResultSet rs) throws SQLException
    {
        return new FormField(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12));
    }

    public static int addFormField(Integer FormId, String FieldName, String FieldDataType, String FieldLabel, String FieldErrorText, String FieldValidationRegex, Integer FieldRank, String FieldDefaultValue, String FieldOptions, String FieldGroupName, Integer FieldOptional)
    {
        int id = 0;
        try
        {

            checkColumnSize(FieldName, 255);
            checkColumnSize(FieldDataType, 45);
            checkColumnSize(FieldLabel, 255);
            checkColumnSize(FieldErrorText, 255);
            checkColumnSize(FieldValidationRegex, 255);

            checkColumnSize(FieldDefaultValue, 255);
            checkColumnSize(FieldOptions, 65535);
            checkColumnSize(FieldGroupName, 255);

            openConnection();
            prepareStatement("INSERT INTO form_field(FormId,FieldName,FieldDataType,FieldLabel,FieldErrorText,FieldValidationRegex,FieldRank,FieldDefaultValue,FieldOptions,FieldGroupName,FieldOptional) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, FormId);
            preparedStatement.setString(2, FieldName);
            preparedStatement.setString(3, FieldDataType);
            preparedStatement.setString(4, FieldLabel);
            preparedStatement.setString(5, FieldErrorText);
            preparedStatement.setString(6, FieldValidationRegex);
            preparedStatement.setInt(7, FieldRank);
            preparedStatement.setString(8, FieldDefaultValue);
            preparedStatement.setString(9, FieldOptions);
            preparedStatement.setString(10, FieldGroupName);
            preparedStatement.setInt(11, FieldOptional);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addFormField error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllFormFieldCount()
    {
        return getAllRecordsCountByTableName("form_field");
    }

    public static ArrayList<FormField> getAllFormField()
    {
        ArrayList<FormField> form_field = new ArrayList<FormField>();
        try
        {
            getAllRecordsByTableName("form_field");
            while (rs.next())
            {
                form_field.add(processFormField(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllFormField error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return form_field;
    }

    public static ArrayList<FormField> getFormFieldPaged(int limit, int offset)
    {
        ArrayList<FormField> form_field = new ArrayList<FormField>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("form_field", limit, offset);
            while (rs.next())
            {
                form_field.add(processFormField(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getFormFieldPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return form_field;
    }

    public static ArrayList<FormField> getAllFormFieldByColumn(String columnName, String columnValue)
    {
        ArrayList<FormField> form_field = new ArrayList<FormField>();
        try
        {
            getAllRecordsByColumn("form_field", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                form_field.add(processFormField(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllFormFieldByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return form_field;
    }

    public static FormField getFormFieldByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        FormField form_field = new FormField();
        try
        {
            getRecordsByColumnWithLimitAndOffset("form_field", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                form_field = processFormField(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getFormFieldByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return form_field;
    }

    public static void updateFormField(Integer FormFieldId, Integer FormId, String FieldName, String FieldDataType, String FieldLabel, String FieldErrorText, String FieldValidationRegex, Integer FieldRank, String FieldDefaultValue, String FieldOptions, String FieldGroupName, Integer FieldOptional)
    {
        try
        {

            checkColumnSize(FieldName, 255);
            checkColumnSize(FieldDataType, 45);
            checkColumnSize(FieldLabel, 255);
            checkColumnSize(FieldErrorText, 255);
            checkColumnSize(FieldValidationRegex, 255);

            checkColumnSize(FieldDefaultValue, 255);
            checkColumnSize(FieldOptions, 65535);
            checkColumnSize(FieldGroupName, 255);

            openConnection();
            prepareStatement("UPDATE form_field SET FormId=?,FieldName=?,FieldDataType=?,FieldLabel=?,FieldErrorText=?,FieldValidationRegex=?,FieldRank=?,FieldDefaultValue=?,FieldOptions=?,FieldGroupName=?,FieldOptional=? WHERE FormFieldId=?;");
            preparedStatement.setInt(1, FormId);
            preparedStatement.setString(2, FieldName);
            preparedStatement.setString(3, FieldDataType);
            preparedStatement.setString(4, FieldLabel);
            preparedStatement.setString(5, FieldErrorText);
            preparedStatement.setString(6, FieldValidationRegex);
            preparedStatement.setInt(7, FieldRank);
            preparedStatement.setString(8, FieldDefaultValue);
            preparedStatement.setString(9, FieldOptions);
            preparedStatement.setString(10, FieldGroupName);
            preparedStatement.setInt(11, FieldOptional);
            preparedStatement.setInt(12, FormFieldId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateFormField error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllFormField()
    {
        try
        {
            updateQuery("DELETE FROM form_field;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllFormField error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteFormFieldById(String id)
    {
        try
        {
            updateQuery("DELETE FROM form_field WHERE FormFieldId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteFormFieldById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteFormFieldByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM form_field WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteFormFieldByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
