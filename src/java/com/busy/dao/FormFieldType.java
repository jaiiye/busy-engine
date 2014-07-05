


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FormFieldType extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_FORMFIELDTYPEID = "FormFieldTypeId";
        public static final String PROP_FIELDTYPENAME = "FieldTypeName";
        public static final String PROP_FIELDTYPEINPUTTYPE = "FieldTypeInputType";
        
        
        private Integer formFieldTypeId;
        private String fieldTypeName;
        private String fieldTypeInputType;
        
        
        public FormFieldType()
        {
            this.formFieldTypeId = 0; 
       this.fieldTypeName = ""; 
       this.fieldTypeInputType = ""; 
        }
        
        public FormFieldType(Integer FormFieldTypeId, String FieldTypeName, String FieldTypeInputType)
        {
            this.formFieldTypeId = FormFieldTypeId;
       this.fieldTypeName = FieldTypeName;
       this.fieldTypeInputType = FieldTypeInputType;
        } 
        
        
            public Integer getFormFieldTypeId()
            {
                return this.formFieldTypeId;
            }
            
            public void setFormFieldTypeId(Integer FormFieldTypeId)
            {
                this.formFieldTypeId = FormFieldTypeId;
            }
        
            public String getFieldTypeName()
            {
                return this.fieldTypeName;
            }
            
            public void setFieldTypeName(String FieldTypeName)
            {
                this.fieldTypeName = FieldTypeName;
            }
        
            public String getFieldTypeInputType()
            {
                return this.fieldTypeInputType;
            }
            
            public void setFieldTypeInputType(String FieldTypeInputType)
            {
                this.fieldTypeInputType = FieldTypeInputType;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(FormFieldType.PROP_FORMFIELDTYPEID) || column.equals(FormFieldType.PROP_FIELDTYPENAME) || column.equals(FormFieldType.PROP_FIELDTYPEINPUTTYPE) )
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
            if (column.equals(FormFieldType.PROP_FORMFIELDTYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static FormFieldType processFormFieldType(ResultSet rs) throws SQLException
        {        
            return new FormFieldType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addFormFieldType(String FieldTypeName, String FieldTypeInputType)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FieldTypeName, 45);
                checkColumnSize(FieldTypeInputType, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO form_field_type(FieldTypeName,FieldTypeInputType) VALUES (?,?);");                    
                preparedStatement.setString(1, FieldTypeName);
                preparedStatement.setString(2, FieldTypeInputType);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addFormFieldType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllFormFieldTypeCount()
        {
            return getAllRecordsCountByTableName("form_field_type");        
        }
        
        public static ArrayList<FormFieldType> getAllFormFieldType()
        {
            ArrayList<FormFieldType> form_field_type = new ArrayList<FormFieldType>();
            try
            {
                getAllRecordsByTableName("form_field_type");
                while(rs.next())
                {
                    form_field_type.add(processFormFieldType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllFormFieldType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
        }
                
        public static ArrayList<FormFieldType> getFormFieldTypePaged(int limit, int offset)
        {
            ArrayList<FormFieldType> form_field_type = new ArrayList<FormFieldType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("form_field_type", limit, offset);
                while (rs.next())
                {
                    form_field_type.add(processFormFieldType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getFormFieldTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
        } 
        
        public static ArrayList<FormFieldType> getAllFormFieldTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<FormFieldType> form_field_type = new ArrayList<FormFieldType>();
            try
            {
                getAllRecordsByColumn("form_field_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    form_field_type.add(processFormFieldType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllFormFieldTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
        }
        
        public static FormFieldType getFormFieldTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            FormFieldType form_field_type = new FormFieldType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("form_field_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   form_field_type = processFormFieldType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getFormFieldTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
        }                
                
        public static void updateFormFieldType(Integer FormFieldTypeId,String FieldTypeName,String FieldTypeInputType)
        {  
            try
            {   
                
                checkColumnSize(FieldTypeName, 45);
                checkColumnSize(FieldTypeInputType, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field_type SET FieldTypeName=?,FieldTypeInputType=? WHERE FormFieldTypeId=?;");                    
                preparedStatement.setString(1, FieldTypeName);
                preparedStatement.setString(2, FieldTypeInputType);
                preparedStatement.setInt(3, FormFieldTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateFormFieldType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllFormFieldType()
        {
            try
            {
                updateQuery("DELETE FROM form_field_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllFormFieldType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteFormFieldTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteFormFieldTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteFormFieldTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM form_field_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteFormFieldTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

