





































    package com.busy.engine.dao;

import com.busy.engine.entity.FormFieldType;
import com.busy.engine.entity.FormField;
import com.busy.engine.entity.Form;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FormFieldDaoImpl extends BasicConnection implements Serializable, FormFieldDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public FormField find(Integer id)
        {
            return findByColumn("FormFieldId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<FormField> findAll(Integer limit, Integer offset)
        {
            ArrayList<FormField> form_field = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("form_field");
                while(rs.next())
                {
                    form_field.add(FormField.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("FormField object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field;
         
        }
        
        @Override
        public ArrayList<FormField> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<FormField> form_fieldList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("form_field", limit, offset);
                while (rs.next())
                {
                    form_fieldList.add(FormField.process(rs));
                }

                
                    for(FormField form_field : form_fieldList)
                    {
                        
                            getRecordById("FormFieldType", form_field.getFormFieldTypeId().toString());
                            form_field.setFormFieldType(FormFieldType.process(rs));               
                        
                            getRecordById("Form", form_field.getFormId().toString());
                            form_field.setForm(Form.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object FormField method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_fieldList;
        }
        
        @Override
        public ArrayList<FormField> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<FormField> form_field = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("form_field", FormField.checkColumnName(columnName), columnValue, FormField.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   form_field.add(FormField.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("FormField's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field;
        } 
    
        @Override
        public int add(FormField obj)
        {
            int id = 0;
            try
            {
                
                FormField.checkColumnSize(obj.getFieldName(), 255);
                FormField.checkColumnSize(obj.getLabel(), 255);
                FormField.checkColumnSize(obj.getErrorText(), 255);
                FormField.checkColumnSize(obj.getValidationRegex(), 255);
                
                FormField.checkColumnSize(obj.getDefaultValue(), 255);
                FormField.checkColumnSize(obj.getOptions(), 65535);
                FormField.checkColumnSize(obj.getGroupName(), 255);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO form_field(FieldName,Label,ErrorText,ValidationRegex,Rank,DefaultValue,Options,GroupName,Optional,FormFieldTypeId,FormId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFieldName());
                preparedStatement.setString(2, obj.getLabel());
                preparedStatement.setString(3, obj.getErrorText());
                preparedStatement.setString(4, obj.getValidationRegex());
                preparedStatement.setInt(5, obj.getRank());
                preparedStatement.setString(6, obj.getDefaultValue());
                preparedStatement.setString(7, obj.getOptions());
                preparedStatement.setString(8, obj.getGroupName());
                preparedStatement.setInt(9, obj.getOptional());
                preparedStatement.setInt(10, obj.getFormFieldTypeId());
                preparedStatement.setInt(11, obj.getFormId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("FormField's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public FormField update(FormField obj)
        {
           try
            {   
                
                FormField.checkColumnSize(obj.getFieldName(), 255);
                FormField.checkColumnSize(obj.getLabel(), 255);
                FormField.checkColumnSize(obj.getErrorText(), 255);
                FormField.checkColumnSize(obj.getValidationRegex(), 255);
                
                FormField.checkColumnSize(obj.getDefaultValue(), 255);
                FormField.checkColumnSize(obj.getOptions(), 65535);
                FormField.checkColumnSize(obj.getGroupName(), 255);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field SET FieldName=?,Label=?,ErrorText=?,ValidationRegex=?,Rank=?,DefaultValue=?,Options=?,GroupName=?,Optional=?,FormFieldTypeId=?,FormId=? WHERE FormFieldId=?;");                    
                preparedStatement.setString(1, obj.getFieldName());
                preparedStatement.setString(2, obj.getLabel());
                preparedStatement.setString(3, obj.getErrorText());
                preparedStatement.setString(4, obj.getValidationRegex());
                preparedStatement.setInt(5, obj.getRank());
                preparedStatement.setString(6, obj.getDefaultValue());
                preparedStatement.setString(7, obj.getOptions());
                preparedStatement.setString(8, obj.getGroupName());
                preparedStatement.setInt(9, obj.getOptional());
                preparedStatement.setInt(10, obj.getFormFieldTypeId());
                preparedStatement.setInt(11, obj.getFormId());
                preparedStatement.setInt(12, obj.getFormFieldId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("FormField's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("form_field");
        }
        
        
        @Override
        public void getRelatedInfo(FormField form_field)
        {
            
                try
                { 
                    
                            getRecordById("FormFieldType", form_field.getFormFieldTypeId().toString());
                            form_field.setFormFieldType(FormFieldType.process(rs));                                       
                    
                            getRecordById("Form", form_field.getFormId().toString());
                            form_field.setForm(Form.process(rs));                                       
                    
                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
        }
        
        @Override
        public void getRelatedObjects(FormField form_field)
        {
             
        }
        
        @Override
        public boolean remove(FormField obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form_field WHERE FormFieldId=" + obj.getFormFieldId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormField's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM form_field WHERE FormFieldId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form_field;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormField's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM form_field WHERE " + FormField.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("FormField's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

