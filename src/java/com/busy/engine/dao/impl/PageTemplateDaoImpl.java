





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PageTemplateDaoImpl extends BasicConnection implements Serializable, PageTemplateDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public PageTemplate find(Integer id)
        {
            return findByColumn("PageTemplateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<PageTemplate> findAll(Integer limit, Integer offset)
        {
            ArrayList<PageTemplate> page_template = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("page_template");
                while(rs.next())
                {
                    page_template.add(PageTemplate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("PageTemplate object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_template;
         
        }
        
        @Override
        public ArrayList<PageTemplate> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<PageTemplate> page_templateList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("page_template", limit, offset);
                while (rs.next())
                {
                    page_templateList.add(PageTemplate.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object PageTemplate method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_templateList;
        }
        
        @Override
        public ArrayList<PageTemplate> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<PageTemplate> page_template = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("page_template", PageTemplate.checkColumnName(columnName), columnValue, PageTemplate.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   page_template.add(PageTemplate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object PageTemplate's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_template;
        } 
    
        @Override
        public void add(PageTemplate obj)
        {
            try
            {
                
                PageTemplate.checkColumnSize(obj.getName(), 45);
                PageTemplate.checkColumnSize(obj.getMarkup(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO page_template(Name,Markup) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getMarkup());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Name, String Markup)
        {   
            int id = 0;
            try
            {
                
                PageTemplate.checkColumnSize(Name, 45);
                PageTemplate.checkColumnSize(Markup, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO page_template(Name,Markup) VALUES (?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Markup);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from page_template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addPageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public PageTemplate update(PageTemplate obj)
        {
           try
            {   
                
                PageTemplate.checkColumnSize(obj.getName(), 45);
                PageTemplate.checkColumnSize(obj.getMarkup(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE page_template SET Name=?,Markup=? WHERE PageTemplateId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getPageTemplateId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updatePageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer PageTemplateId,String Name,String Markup)
        {  
            try
            {   
                
                PageTemplate.checkColumnSize(Name, 45);
                PageTemplate.checkColumnSize(Markup, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE page_template SET Name=?,Markup=? WHERE PageTemplateId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Markup);
                preparedStatement.setInt(3, PageTemplateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updatePageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("page_template");
        }
        
        
        @Override
        public PageTemplate getRelatedInfo(PageTemplate page_template)
        {
              
            
            return page_template;
        }
        
        
        @Override
        public PageTemplate getRelatedObjects(PageTemplate page_template)
        {
                         
            return page_template;
        }
        
        
        
        @Override
        public void remove(PageTemplate obj)
        {            
            try
            {
                updateQuery("DELETE FROM page_template WHERE PageTemplateId=" + obj.getPageTemplateId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deletePageTemplateById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page_template WHERE PageTemplateId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deletePageTemplateById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page_template;");
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page_template WHERE " + PageTemplate.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

