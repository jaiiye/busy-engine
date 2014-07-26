





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object TemplateType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
        } 
    
        @Override
        public void add(TemplateType obj)
        {
            try
            {
                
                TemplateType.checkColumnSize(obj.getTypeName(), 45);
                TemplateType.checkColumnSize(obj.getTypeValue(), 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO template_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String TypeValue)
        {   
            int id = 0;
            try
            {
                
                TemplateType.checkColumnSize(TypeName, 45);
                TemplateType.checkColumnSize(TypeValue, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO template_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTemplateType error: " + ex.getMessage());
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
                System.out.println("updateTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer TemplateTypeId,String TypeName,String TypeValue)
        {  
            try
            {   
                
                TemplateType.checkColumnSize(TypeName, 45);
                TemplateType.checkColumnSize(TypeValue, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE template_type SET TypeName=?,TypeValue=? WHERE TemplateTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                preparedStatement.setInt(3, TemplateTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("template_type");
        }
        
        
        @Override
        public TemplateType getRelatedInfo(TemplateType template_type)
        {
              
            
            return template_type;
        }
        
        
        @Override
        public TemplateType getRelatedObjects(TemplateType template_type)
        {
            template_type.setTemplateList(new TemplateDaoImpl().findByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString(),null,null));
             
            return template_type;
        }
        
        
        
        @Override
        public void remove(TemplateType obj)
        {            
            try
            {
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + obj.getTemplateTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template_type;");
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template_type WHERE " + TemplateType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public TemplateType getRelatedTemplateList(TemplateType template_type)
        {           
            template_type.setTemplateList(new TemplateDaoImpl().findByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString(),null,null));
            return template_type;
        }        
        
                             
    }

