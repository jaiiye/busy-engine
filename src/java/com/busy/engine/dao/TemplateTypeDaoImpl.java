





































    package com.busy.engine.dao;

import com.busy.engine.entity.TemplateType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TemplateTypeDaoImpl extends BasicConnection implements Serializable, TemplateTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public TemplateType find(Integer id)
        {
            return findByColumn("TemplateTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<TemplateType> findAll(Integer limit, Integer offset)
        {
            ArrayList<TemplateType> template_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("template_type");
                while(rs.next())
                {
                    template_type.add(TemplateType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TemplateType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
         
        }
        
        @Override
        public ArrayList<TemplateType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TemplateType> template_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("template_type", limit, offset);
                while (rs.next())
                {
                    template_typeList.add(TemplateType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object TemplateType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_typeList;
        }
        
        @Override
        public ArrayList<TemplateType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TemplateType> template_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("template_type", TemplateType.checkColumnName(columnName), columnValue, TemplateType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   template_type.add(TemplateType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TemplateType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
        } 
    
        @Override
        public int add(TemplateType obj)
        {
            int id = 0;
            try
            {
                
                TemplateType.checkColumnSize(obj.getTypeName(), 45);
                TemplateType.checkColumnSize(obj.getTypeValue(), 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO template_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public TemplateType update(TemplateType obj)
        {
           try
            {   
                
                TemplateType.checkColumnSize(obj.getTypeName(), 45);
                TemplateType.checkColumnSize(obj.getTypeValue(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE template_type SET TypeName=?,TypeValue=? WHERE TemplateTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                preparedStatement.setInt(3, obj.getTemplateTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("template_type");
        }
        
        
        @Override
        public void getRelatedInfo(TemplateType template_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(TemplateType template_type)
        {
            template_type.setTemplateList(new TemplateDaoImpl().findByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(TemplateType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + obj.getTemplateTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM template_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template_type WHERE " + TemplateType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedTemplateList(TemplateType template_type)
        {           
            template_type.setTemplateList(new TemplateDaoImpl().findByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString(),null,null));
        }        
        
                             
    }

