





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FormFieldTypeDaoImpl extends BasicConnection implements Serializable, FormFieldTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public FormFieldType find(Integer id)
        {
            return findByColumn("FormFieldTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<FormFieldType> findAll(Integer limit, Integer offset)
        {
            ArrayList<FormFieldType> form_field_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("form_field_type");
                while(rs.next())
                {
                    form_field_type.add(FormFieldType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("FormFieldType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
         
        }
        
        @Override
        public ArrayList<FormFieldType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<FormFieldType> form_field_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("form_field_type", limit, offset);
                while (rs.next())
                {
                    form_field_typeList.add(FormFieldType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object FormFieldType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_typeList;
        }
        
        @Override
        public ArrayList<FormFieldType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<FormFieldType> form_field_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("form_field_type", FormFieldType.checkColumnName(columnName), columnValue, FormFieldType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   form_field_type.add(FormFieldType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object FormFieldType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
        } 
    
        @Override
        public void add(FormFieldType obj)
        {
            try
            {
                
                FormFieldType.checkColumnSize(obj.getTypeName(), 45);
                FormFieldType.checkColumnSize(obj.getInputType(), 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO form_field_type(TypeName,InputType) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getInputType());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String InputType)
        {   
            int id = 0;
            try
            {
                
                FormFieldType.checkColumnSize(TypeName, 45);
                FormFieldType.checkColumnSize(InputType, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO form_field_type(TypeName,InputType) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, InputType);
                
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
        
        
        @Override
        public FormFieldType update(FormFieldType obj)
        {
           try
            {   
                
                FormFieldType.checkColumnSize(obj.getTypeName(), 45);
                FormFieldType.checkColumnSize(obj.getInputType(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field_type SET TypeName=?,InputType=? WHERE FormFieldTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getInputType());
                preparedStatement.setInt(3, obj.getFormFieldTypeId());
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
            return obj;
        }
        
        public static void update(Integer FormFieldTypeId,String TypeName,String InputType)
        {  
            try
            {   
                
                FormFieldType.checkColumnSize(TypeName, 45);
                FormFieldType.checkColumnSize(InputType, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field_type SET TypeName=?,InputType=? WHERE FormFieldTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, InputType);
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("form_field_type");
        }
        
        
        @Override
        public FormFieldType getRelatedInfo(FormFieldType form_field_type)
        {
              
            
            return form_field_type;
        }
        
        
        @Override
        public FormFieldType getRelatedObjects(FormFieldType form_field_type)
        {
            form_field_type.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormFieldTypeId", form_field_type.getFormFieldTypeId().toString(),null,null));
             
            return form_field_type;
        }
        
        
        
        @Override
        public void remove(FormFieldType obj)
        {            
            try
            {
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + obj.getFormFieldTypeId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM form_field_type;");
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM form_field_type WHERE " + FormFieldType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public FormFieldType getRelatedFormFieldList(FormFieldType form_field_type)
        {           
            form_field_type.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormFieldTypeId", form_field_type.getFormFieldTypeId().toString(),null,null));
            return form_field_type;
        }        
        
                             
    }

