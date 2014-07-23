





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SitePageDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SitePage.PROP_SITE_PAGE_ID) || column.equals(SitePage.PROP_SITE_ID) || column.equals(SitePage.PROP_PAGE_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(SitePage.PROP_SITE_PAGE_ID) || column.equals(SitePage.PROP_SITE_ID) || column.equals(SitePage.PROP_PAGE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SitePage processSitePage(ResultSet rs) throws SQLException
        {        
            return new SitePage(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addSitePage(Integer SiteId, Integer PageId)
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
        
        public static int getAllSitePageCount()
        {
            return getAllRecordsCountByTableName("site_page");        
        }
        
        public static ArrayList<SitePage> getAllSitePage()
        {
            ArrayList<SitePage> site_page = new ArrayList<SitePage>();
            try
            {
                getAllRecordsByTableName("site_page");
                while(rs.next())
                {
                    site_page.add(processSitePage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSitePage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_page;
        }
        
        public static ArrayList<SitePage> getAllSitePageWithRelatedInfo()
        {
            ArrayList<SitePage> site_pageList = new ArrayList<SitePage>();
            try
            {
                getAllRecordsByTableName("site_page");
                while (rs.next())
                {
                    site_pageList.add(processSitePage(rs));
                }

                
                    for(SitePage site_page : site_pageList)
                    {
                        
                            getRecordById("Site", site_page.getSiteId().toString());
                            site_page.setSite(SiteDAO.processSite(rs));               
                        
                            getRecordById("Page", site_page.getPageId().toString());
                            site_page.setPage(PageDAO.processPage(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSitePageWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_pageList;
        }
        
        
        public static SitePage getRelatedInfo(SitePage site_page)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Site", site_page.getSiteId().toString());
                            site_page.setSite(SiteDAO.processSite(rs));               
                        
                            getRecordById("Page", site_page.getPageId().toString());
                            site_page.setPage(PageDAO.processPage(rs));               
                        

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
        
        public static SitePage getAllRelatedObjects(SitePage site_page)
        {           
                         
            return site_page;
        }
        
        
        
                
        public static ArrayList<SitePage> getSitePagePaged(int limit, int offset)
        {
            ArrayList<SitePage> site_page = new ArrayList<SitePage>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_page", limit, offset);
                while (rs.next())
                {
                    site_page.add(processSitePage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSitePagePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_page;
        } 
        
        public static ArrayList<SitePage> getAllSitePageByColumn(String columnName, String columnValue)
        {
            ArrayList<SitePage> site_page = new ArrayList<SitePage>();
            try
            {
                getAllRecordsByColumn("site_page", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_page.add(processSitePage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSitePageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_page;
        }
        
        public static SitePage getSitePageByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SitePage site_page = new SitePage();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_page", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_page = processSitePage(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSitePageByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_page;
        }                
                
        public static void updateSitePage(Integer SitePageId,Integer SiteId,Integer PageId)
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
        
        public static void deleteAllSitePage()
        {
            try
            {
                updateQuery("DELETE FROM site_page;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSitePage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSitePageById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_page WHERE SitePageId=" + id + ";");            
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

        public static void deleteSitePageByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_page WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSitePageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

