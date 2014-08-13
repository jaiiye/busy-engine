





































    package com.busy.engine.dao;

import com.busy.engine.entity.FormFieldType;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("FormFieldType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form_field_type;
        } 
    
        @Override
        public int add(FormFieldType obj)
        {
            int id = 0;
            try
            {
                
                FormFieldType.checkColumnSize(obj.getTypeName(), 45);
                FormFieldType.checkColumnSize(obj.getInputType(), 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO form_field_type(TypeName,InputType) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getInputType());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's add method error: " + ex.getMessage());
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
                System.out.println("FormFieldType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("form_field_type");
        }
        
        
        @Override
        public void getRelatedInfo(FormFieldType form_field_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(FormFieldType form_field_type)
        {
            form_field_type.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormFieldTypeId", form_field_type.getFormFieldTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(FormFieldType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + obj.getFormFieldTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM form_field_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM form_field_type WHERE " + FormFieldType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedFormFieldList(FormFieldType form_field_type)
        {           
            form_field_type.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormFieldTypeId", form_field_type.getFormFieldTypeId().toString(),null,null));
        }        
        
                             
    }

