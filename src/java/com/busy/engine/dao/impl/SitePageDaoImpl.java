





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SitePageDaoImpl extends BasicConnection implements Serializable, SitePageDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SitePage find(Integer id)
        {
            return findByColumn("SitePageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SitePage> findAll(Integer limit, Integer offset)
        {
            ArrayList<SitePage> site_page = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_page");
                while(rs.next())
                {
                    site_page.add(SitePage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SitePage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_page;
         
        }
        
        @Override
        public ArrayList<SitePage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SitePage> site_pageList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_page", limit, offset);
                while (rs.next())
                {
                    site_pageList.add(SitePage.process(rs));
                }

                
                    for(SitePage site_page : site_pageList)
                    {
                        
                            getRecordById("Site", site_page.getSiteId().toString());
                            site_page.setSite(Site.process(rs));               
                        
                            getRecordById("Page", site_page.getPageId().toString());
                            site_page.setPage(Page.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SitePage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_pageList;
        }
        
        @Override
        public ArrayList<SitePage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SitePage> site_page = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_page", SitePage.checkColumnName(columnName), columnValue, SitePage.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_page.add(SitePage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object SitePage's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_page;
        } 
    
        @Override
        public void add(SitePage obj)
        {
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_page(SiteId,PageId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getPageId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer SiteId, Integer PageId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_page(SiteId,PageId) VALUES (?,?);");                    
                preparedStatement.setInt(1, SiteId);
                preparedStatement.setInt(2, PageId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_page;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSitePage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public SitePage update(SitePage obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_page SET SiteId=?,PageId=? WHERE SitePageId=?;");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getPageId());
                preparedStatement.setInt(3, obj.getSitePageId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSitePage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SitePageId,Integer SiteId,Integer PageId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_page SET SiteId=?,PageId=? WHERE SitePageId=?;");                    
                preparedStatement.setInt(1, SiteId);
                preparedStatement.setInt(2, PageId);
                preparedStatement.setInt(3, SitePageId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSitePage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site_page");
        }
        
        
        @Override
        public SitePage getRelatedInfo(SitePage site_page)
        {
            
                try
                { 
                    
                        getRecordById("Site", site_page.getSiteId().toString());
                        site_page.setSite(Site.process(rs));               
                    
                        getRecordById("Page", site_page.getPageId().toString());
                        site_page.setPage(Page.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return site_page;
        }
        
        
        @Override
        public SitePage getRelatedObjects(SitePage site_page)
        {
                         
            return site_page;
        }
        
        
        
        @Override
        public void remove(SitePage obj)
        {            
            try
            {
                updateQuery("DELETE FROM site_page WHERE SitePageId=" + obj.getSitePageId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSitePageById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_page WHERE SitePageId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSitePageById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_page;");
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_page WHERE " + SitePage.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

