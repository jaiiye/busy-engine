





































    package com.busy.engine.dao;

import com.busy.engine.entity.MetaTag;
import com.busy.engine.entity.Template;
import com.busy.engine.entity.Page;
import com.busy.engine.entity.Slider;
import com.busy.engine.entity.Form;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PageDaoImpl extends BasicConnection implements Serializable, PageDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Page find(Integer id)
        {
            return findByColumn("PageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Page> findAll(Integer limit, Integer offset)
        {
            ArrayList<Page> page = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("page");
                while(rs.next())
                {
                    page.add(Page.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Page object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
         
        }
        
        @Override
        public ArrayList<Page> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Page> pageList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("page", limit, offset);
                while (rs.next())
                {
                    pageList.add(Page.process(rs));
                }

                
                    for(Page page : pageList)
                    {
                        
                            getRecordById("Form", page.getFormId().toString());
                            page.setForm(Form.process(rs));               
                        
                            getRecordById("Slider", page.getSliderId().toString());
                            page.setSlider(Slider.process(rs));               
                        
                            getRecordById("MetaTag", page.getMetaTagId().toString());
                            page.setMetaTag(MetaTag.process(rs));               
                        
                            getRecordById("Template", page.getTemplateId().toString());
                            page.setTemplate(Template.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Page method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return pageList;
        }
        
        @Override
        public ArrayList<Page> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Page> page = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("page", Page.checkColumnName(columnName), columnValue, Page.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   page.add(Page.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Page's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
        } 
    
        @Override
        public int add(Page obj)
        {
            int id = 0;
            try
            {
                
                Page.checkColumnSize(obj.getPageName(), 150);
                Page.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO page(PageName,Content,PageStatus,FormId,SliderId,MetaTagId,TemplateId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getPageName());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setInt(3, obj.getPageStatus());
                preparedStatement.setInt(4, obj.getFormId());
                preparedStatement.setInt(5, obj.getSliderId());
                preparedStatement.setInt(6, obj.getMetaTagId());
                preparedStatement.setInt(7, obj.getTemplateId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from page;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Page's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Page update(Page obj)
        {
           try
            {   
                
                Page.checkColumnSize(obj.getPageName(), 150);
                Page.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE page SET PageName=?,Content=?,PageStatus=?,FormId=?,SliderId=?,MetaTagId=?,TemplateId=? WHERE PageId=?;");                    
                preparedStatement.setString(1, obj.getPageName());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setInt(3, obj.getPageStatus());
                preparedStatement.setInt(4, obj.getFormId());
                preparedStatement.setInt(5, obj.getSliderId());
                preparedStatement.setInt(6, obj.getMetaTagId());
                preparedStatement.setInt(7, obj.getTemplateId());
                preparedStatement.setInt(8, obj.getPageId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Page's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("page");
        }
        
        
        @Override
        public void getRelatedInfo(Page page)
        {
            
                try
                { 
                    
                            getRecordById("Form", page.getFormId().toString());
                            page.setForm(Form.process(rs));                                       
                    
                            getRecordById("Slider", page.getSliderId().toString());
                            page.setSlider(Slider.process(rs));                                       
                    
                            getRecordById("MetaTag", page.getMetaTagId().toString());
                            page.setMetaTag(MetaTag.process(rs));                                       
                    
                            getRecordById("Template", page.getTemplateId().toString());
                            page.setTemplate(Template.process(rs));                                       
                    
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
        public void getRelatedObjects(Page page)
        {
            page.setSitePageList(new SitePageDaoImpl().findByColumn("PageId", page.getPageId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Page obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM page WHERE PageId=" + obj.getPageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Page's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page WHERE PageId=" + id + ";");           
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
                updateQuery("DELETE FROM page;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Page's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page WHERE " + Page.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Page's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedSitePageList(Page page)
        {           
            page.setSitePageList(new SitePageDaoImpl().findByColumn("PageId", page.getPageId().toString(),null,null));
        }        
        
                             
    }

