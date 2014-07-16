











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Site;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Site.PROP_SITE_ID) || column.equals(Site.PROP_SITE_NAME) || column.equals(Site.PROP_DOMAIN) || column.equals(Site.PROP_MODE) || column.equals(Site.PROP_URL) || column.equals(Site.PROP_EMAIL_HOST) || column.equals(Site.PROP_EMAIL_PORT) || column.equals(Site.PROP_EMAIL_USERNAME) || column.equals(Site.PROP_EMAIL_PASSWORD) || column.equals(Site.PROP_STATUS) || column.equals(Site.PROP_LOCALE) || column.equals(Site.PROP_TEMPLATE_ID) )
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
            if (column.equals(Site.PROP_SITE_ID) || column.equals(Site.PROP_MODE) || column.equals(Site.PROP_EMAIL_PORT) || column.equals(Site.PROP_STATUS) || column.equals(Site.PROP_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Site processSite(ResultSet rs) throws SQLException
        {        
            return new Site(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12));
        }
        
        public static int addSite(String SiteName, String Domain, Integer Mode, String Url, String EmailHost, Integer EmailPort, String EmailUsername, String EmailPassword, Integer Status, String Locale, Integer TemplateId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(SiteName, 100);
                checkColumnSize(Domain, 255);
                
                checkColumnSize(Url, 255);
                checkColumnSize(EmailHost, 255);
                
                checkColumnSize(EmailUsername, 255);
                checkColumnSize(EmailPassword, 45);
                
                checkColumnSize(Locale, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site(SiteName,Domain,Mode,Url,EmailHost,EmailPort,EmailUsername,EmailPassword,Status,Locale,TemplateId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, SiteName);
                preparedStatement.setString(2, Domain);
                preparedStatement.setInt(3, Mode);
                preparedStatement.setString(4, Url);
                preparedStatement.setString(5, EmailHost);
                preparedStatement.setInt(6, EmailPort);
                preparedStatement.setString(7, EmailUsername);
                preparedStatement.setString(8, EmailPassword);
                preparedStatement.setInt(9, Status);
                preparedStatement.setString(10, Locale);
                preparedStatement.setInt(11, TemplateId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteCount()
        {
            return getAllRecordsCountByTableName("site");        
        }
        
        public static ArrayList<Site> getAllSite()
        {
            ArrayList<Site> site = new ArrayList<Site>();
            try
            {
                getAllRecordsByTableName("site");
                while(rs.next())
                {
                    site.add(processSite(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        }
                
        public static ArrayList<Site> getSitePaged(int limit, int offset)
        {
            ArrayList<Site> site = new ArrayList<Site>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site", limit, offset);
                while (rs.next())
                {
                    site.add(processSite(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSitePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        } 
        
        public static ArrayList<Site> getAllSiteByColumn(String columnName, String columnValue)
        {
            ArrayList<Site> site = new ArrayList<Site>();
            try
            {
                getAllRecordsByColumn("site", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site.add(processSite(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        }
        
        public static Site getSiteByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Site site = new Site();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site = processSite(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        }                
                
        public static void updateSite(Integer SiteId,String SiteName,String Domain,Integer Mode,String Url,String EmailHost,Integer EmailPort,String EmailUsername,String EmailPassword,Integer Status,String Locale,Integer TemplateId)
        {  
            try
            {   
                
                checkColumnSize(SiteName, 100);
                checkColumnSize(Domain, 255);
                
                checkColumnSize(Url, 255);
                checkColumnSize(EmailHost, 255);
                
                checkColumnSize(EmailUsername, 255);
                checkColumnSize(EmailPassword, 45);
                
                checkColumnSize(Locale, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site SET SiteName=?,Domain=?,Mode=?,Url=?,EmailHost=?,EmailPort=?,EmailUsername=?,EmailPassword=?,Status=?,Locale=?,TemplateId=? WHERE SiteId=?;");                    
                preparedStatement.setString(1, SiteName);
                preparedStatement.setString(2, Domain);
                preparedStatement.setInt(3, Mode);
                preparedStatement.setString(4, Url);
                preparedStatement.setString(5, EmailHost);
                preparedStatement.setInt(6, EmailPort);
                preparedStatement.setString(7, EmailUsername);
                preparedStatement.setString(8, EmailPassword);
                preparedStatement.setInt(9, Status);
                preparedStatement.setString(10, Locale);
                preparedStatement.setInt(11, TemplateId);
                preparedStatement.setInt(12, SiteId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSite()
        {
            try
            {
                updateQuery("DELETE FROM site;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

