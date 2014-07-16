


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FormFieldDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(FormField.PROP_FORM_FIELD_ID) || column.equals(FormField.PROP_FIELD_NAME) || column.equals(FormField.PROP_LABEL) || column.equals(FormField.PROP_ERROR_TEXT) || column.equals(FormField.PROP_VALIDATION_REGEX) || column.equals(FormField.PROP_RANK) || column.equals(FormField.PROP_DEFAULT_VALUE) || column.equals(FormField.PROP_OPTIONS) || column.equals(FormField.PROP_GROUP_NAME) || column.equals(FormField.PROP_OPTIONAL) || column.equals(FormField.PROP_FORM_FIELD_TYPE_ID) || column.equals(FormField.PROP_FORM_ID) )
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
            if (column.equals(FormField.PROP_FORM_FIELD_ID) || column.equals(FormField.PROP_RANK) || column.equals(FormField.PROP_OPTIONAL) || column.equals(FormField.PROP_FORM_FIELD_TYPE_ID) || column.equals(FormField.PROP_FORM_ID) )
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
            return new FormField(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
        }
        
        public static int addFormField(String FieldName, String Label, String ErrorText, String ValidationRegex, Integer Rank, String DefaultValue, String Options, String GroupName, Integer Optional, Integer FormFieldTypeId, Integer FormId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FieldName, 255);
                checkColumnSize(Label, 255);
                checkColumnSize(ErrorText, 255);
                checkColumnSize(ValidationRegex, 255);
                
                checkColumnSize(DefaultValue, 255);
                checkColumnSize(Options, 65535);
                checkColumnSize(GroupName, 255);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO form_field(FieldName,Label,ErrorText,ValidationRegex,Rank,DefaultValue,Options,GroupName,Optional,FormFieldTypeId,FormId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, FieldName);
                preparedStatement.setString(2, Label);
                preparedStatement.setString(3, ErrorText);
                preparedStatement.setString(4, ValidationRegex);
                preparedStatement.setInt(5, Rank);
                preparedStatement.setString(6, DefaultValue);
                preparedStatement.setString(7, Options);
                preparedStatement.setString(8, GroupName);
                preparedStatement.setInt(9, Optional);
                preparedStatement.setInt(10, FormFieldTypeId);
                preparedStatement.setInt(11, FormId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
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
                while(rs.next())
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
                while(rs.next())
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
                while(rs.next())
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
                
        public static void updateFormField(Integer FormFieldId,String FieldName,String Label,String ErrorText,String ValidationRegex,Integer Rank,String DefaultValue,String Options,String GroupName,Integer Optional,Integer FormFieldTypeId,Integer FormId)
        {  
            try
            {   
                
                checkColumnSize(FieldName, 255);
                checkColumnSize(Label, 255);
                checkColumnSize(ErrorText, 255);
                checkColumnSize(ValidationRegex, 255);
                
                checkColumnSize(DefaultValue, 255);
                checkColumnSize(Options, 65535);
                checkColumnSize(GroupName, 255);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field SET FieldName=?,Label=?,ErrorText=?,ValidationRegex=?,Rank=?,DefaultValue=?,Options=?,GroupName=?,Optional=?,FormFieldTypeId=?,FormId=? WHERE FormFieldId=?;");                    
                preparedStatement.setString(1, FieldName);
                preparedStatement.setString(2, Label);
                preparedStatement.setString(3, ErrorText);
                preparedStatement.setString(4, ValidationRegex);
                preparedStatement.setInt(5, Rank);
                preparedStatement.setString(6, DefaultValue);
                preparedStatement.setString(7, Options);
                preparedStatement.setString(8, GroupName);
                preparedStatement.setInt(9, Optional);
                preparedStatement.setInt(10, FormFieldTypeId);
                preparedStatement.setInt(11, FormId);
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

