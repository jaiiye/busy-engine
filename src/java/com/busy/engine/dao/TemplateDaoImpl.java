





































    package com.busy.engine.dao;

import com.busy.engine.entity.TemplateType;
import com.busy.engine.entity.Template;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TemplateDaoImpl extends BasicConnection implements Serializable, TemplateDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Template find(Integer id)
        {
            return findByColumn("TemplateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Template> findAll(Integer limit, Integer offset)
        {
            ArrayList<Template> template = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("template");
                while(rs.next())
                {
                    template.add(Template.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Template object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
         
        }
        
        @Override
        public ArrayList<Template> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Template> templateList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("template", limit, offset);
                while (rs.next())
                {
                    templateList.add(Template.process(rs));
                }

                
                    for(Template template : templateList)
                    {
                        
                            getRecordById("TemplateType", template.getTemplateTypeId().toString());
                            template.setTemplateType(TemplateType.process(rs));               
                        
                            getRecordById("Template", template.getParentTemplateId().toString());
                            template.setParentTemplate(Template.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Template method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return templateList;
        }
        
        @Override
        public ArrayList<Template> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Template> template = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("template", Template.checkColumnName(columnName), columnValue, Template.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   template.add(Template.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Template's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        } 
    
        @Override
        public int add(Template obj)
        {
            int id = 0;
            try
            {
                
                Template.checkColumnSize(obj.getTemplateName(), 100);
                Template.checkColumnSize(obj.getMarkup(), 65535);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO template(TemplateName,Markup,TemplateStatus,TemplateTypeId,ParentTemplateId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTemplateName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getTemplateStatus());
                preparedStatement.setInt(4, obj.getTemplateTypeId());
                preparedStatement.setInt(5, obj.getParentTemplateId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Template's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Template update(Template obj)
        {
           try
            {   
                
                Template.checkColumnSize(obj.getTemplateName(), 100);
                Template.checkColumnSize(obj.getMarkup(), 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE template SET TemplateName=?,Markup=?,TemplateStatus=?,TemplateTypeId=?,ParentTemplateId=? WHERE TemplateId=?;");                    
                preparedStatement.setString(1, obj.getTemplateName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getTemplateStatus());
                preparedStatement.setInt(4, obj.getTemplateTypeId());
                preparedStatement.setInt(5, obj.getParentTemplateId());
                preparedStatement.setInt(6, obj.getTemplateId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Template's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("template");
        }
        
        
        @Override
        public void getRelatedInfo(Template template)
        {
            
                try
                { 
                    
                            getRecordById("TemplateType", template.getTemplateTypeId().toString());
                            template.setTemplateType(TemplateType.process(rs));                                       
                    
                            getRecordById("Template", template.getParentTemplateId().toString());
                            template.setParentTemplate(Template.process(rs));                                       
                    
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
        public void getRelatedObjects(Template template)
        {
            template.setItemList(new ItemDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setPageList(new PageDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setSiteList(new SiteDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setVendorList(new VendorDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Template obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM template WHERE TemplateId=" + obj.getTemplateId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Template's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template WHERE TemplateId=" + id + ";");           
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
                updateQuery("DELETE FROM template;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Template's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template WHERE " + Template.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Template's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedItemList(Template template)
        {           
            template.setItemList(new ItemDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedPageList(Template template)
        {           
            template.setPageList(new PageDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedResourceUrlList(Template template)
        {           
            template.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedSiteList(Template template)
        {           
            template.setSiteList(new SiteDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedVendorList(Template template)
        {           
            template.setVendorList(new VendorDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
        
                             
    }

