





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Template's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        } 
    
        @Override
        public void add(Template obj)
        {
            try
            {
                
                Template.checkColumnSize(obj.getTemplateName(), 100);
                Template.checkColumnSize(obj.getMarkup(), 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO template(TemplateName,Markup,TemplateStatus,TemplateTypeId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTemplateName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getTemplateStatus());
                preparedStatement.setInt(4, obj.getTemplateTypeId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Template's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TemplateName, String Markup, Integer TemplateStatus, Integer TemplateTypeId)
        {   
            int id = 0;
            try
            {
                
                Template.checkColumnSize(TemplateName, 100);
                Template.checkColumnSize(Markup, 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO template(TemplateName,Markup,TemplateStatus,TemplateTypeId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, Markup);
                preparedStatement.setInt(3, TemplateStatus);
                preparedStatement.setInt(4, TemplateTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTemplate error: " + ex.getMessage());
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
                prepareStatement("UPDATE template SET TemplateName=?,Markup=?,TemplateStatus=?,TemplateTypeId=? WHERE TemplateId=?;");                    
                preparedStatement.setString(1, obj.getTemplateName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getTemplateStatus());
                preparedStatement.setInt(4, obj.getTemplateTypeId());
                preparedStatement.setInt(5, obj.getTemplateId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer TemplateId,String TemplateName,String Markup,Integer TemplateStatus,Integer TemplateTypeId)
        {  
            try
            {   
                
                Template.checkColumnSize(TemplateName, 100);
                Template.checkColumnSize(Markup, 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE template SET TemplateName=?,Markup=?,TemplateStatus=?,TemplateTypeId=? WHERE TemplateId=?;");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, Markup);
                preparedStatement.setInt(3, TemplateStatus);
                preparedStatement.setInt(4, TemplateTypeId);
                preparedStatement.setInt(5, TemplateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("template");
        }
        
        
        @Override
        public Template getRelatedInfo(Template template)
        {
            
                try
                { 
                    
                        getRecordById("TemplateType", template.getTemplateTypeId().toString());
                        template.setTemplateType(TemplateType.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return template;
        }
        
        
        @Override
        public Template getRelatedObjects(Template template)
        {
            template.setItemList(new ItemDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setPageList(new PageDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setSiteList(new SiteDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setVendorList(new VendorDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
             
            return template;
        }
        
        
        
        @Override
        public void remove(Template obj)
        {            
            try
            {
                updateQuery("DELETE FROM template WHERE TemplateId=" + obj.getTemplateId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template WHERE TemplateId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template;");
            }
            catch (Exception ex)
            {
                System.out.println("Template's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template WHERE " + Template.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Template's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Template getRelatedItemList(Template template)
        {           
            template.setItemList(new ItemDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
            return template;
        }        
                    
        public Template getRelatedPageList(Template template)
        {           
            template.setPageList(new PageDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
            return template;
        }        
                    
        public Template getRelatedResourceUrlList(Template template)
        {           
            template.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
            return template;
        }        
                    
        public Template getRelatedSiteList(Template template)
        {           
            template.setSiteList(new SiteDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
            return template;
        }        
                    
        public Template getRelatedVendorList(Template template)
        {           
            template.setVendorList(new VendorDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
            return template;
        }        
        
                             
    }

