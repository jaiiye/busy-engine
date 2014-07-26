





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object FormField's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field;
        } 
    
        @Override
        public void add(FormField obj)
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
            }
            catch (Exception ex)
            {
                System.out.println("FormField's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String FieldName, String Label, String ErrorText, String ValidationRegex, Integer Rank, String DefaultValue, String Options, String GroupName, Integer Optional, Integer FormFieldTypeId, Integer FormId)
        {   
            int id = 0;
            try
            {
                
                FormField.checkColumnSize(FieldName, 255);
                FormField.checkColumnSize(Label, 255);
                FormField.checkColumnSize(ErrorText, 255);
                FormField.checkColumnSize(ValidationRegex, 255);
                
                FormField.checkColumnSize(DefaultValue, 255);
                FormField.checkColumnSize(Options, 65535);
                FormField.checkColumnSize(GroupName, 255);
                
                
                
                                            
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
                System.out.println("updateFormField error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer FormFieldId,String FieldName,String Label,String ErrorText,String ValidationRegex,Integer Rank,String DefaultValue,String Options,String GroupName,Integer Optional,Integer FormFieldTypeId,Integer FormId)
        {  
            try
            {   
                
                FormField.checkColumnSize(FieldName, 255);
                FormField.checkColumnSize(Label, 255);
                FormField.checkColumnSize(ErrorText, 255);
                FormField.checkColumnSize(ValidationRegex, 255);
                
                FormField.checkColumnSize(DefaultValue, 255);
                FormField.checkColumnSize(Options, 65535);
                FormField.checkColumnSize(GroupName, 255);
                
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("form_field");
        }
        
        
        @Override
        public FormField getRelatedInfo(FormField form_field)
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
              
            
            return form_field;
        }
        
        
        @Override
        public FormField getRelatedObjects(FormField form_field)
        {
                         
            return form_field;
        }
        
        
        
        @Override
        public void remove(FormField obj)
        {            
            try
            {
                updateQuery("DELETE FROM form_field WHERE FormFieldId=" + obj.getFormFieldId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM form_field WHERE FormFieldId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM form_field;");
            }
            catch (Exception ex)
            {
                System.out.println("FormField's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM form_field WHERE " + FormField.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("FormField's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

