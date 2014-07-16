


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteUser extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITEUSERID = "SiteUserId";
        public static final String PROP_SITEID = "SiteId";
        public static final String PROP_USERID = "UserId";
        
        
        private Integer siteUserId;
        private Integer siteId;
        private Integer userId;
        
        
        public SiteUser()
        {
            this.siteUserId = 0; 
       this.siteId = 0; 
       this.userId = 0; 
        }
        
        public SiteUser(Integer SiteUserId, Integer SiteId, Integer UserId)
        {
            this.siteUserId = SiteUserId;
       this.siteId = SiteId;
       this.userId = UserId;
        } 
        
        
            public Integer getSiteUserId()
            {
                return this.siteUserId;
            }
            
            public void setSiteUserId(Integer SiteUserId)
            {
                this.siteUserId = SiteUserId;
            }
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteUser.PROP_SITEUSERID) || column.equals(SiteUser.PROP_SITEID) || column.equals(SiteUser.PROP_USERID) )
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
            if (column.equals(SiteUser.PROP_SITEUSERID) || column.equals(SiteUser.PROP_SITEID) || column.equals(SiteUser.PROP_USERID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteUser processSiteUser(ResultSet rs) throws SQLException
        {        
            return new SiteUser(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addSiteUser(Integer SiteId, Integer UserId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_user(SiteId,UserId) VALUES (?,?);");                    
                preparedStatement.setInt(1, SiteId);
                preparedStatement.setInt(2, UserId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_user;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteUserCount()
        {
            return getAllRecordsCountByTableName("site_user");        
        }
        
        public static ArrayList<SiteUser> getAllSiteUser()
        {
            ArrayList<SiteUser> site_user = new ArrayList<SiteUser>();
            try
            {
                getAllRecordsByTableName("site_user");
                while(rs.next())
                {
                    site_user.add(processSiteUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_user;
        }
                
        public static ArrayList<SiteUser> getSiteUserPaged(int limit, int offset)
        {
            ArrayList<SiteUser> site_user = new ArrayList<SiteUser>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_user", limit, offset);
                while (rs.next())
                {
                    site_user.add(processSiteUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteUserPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_user;
        } 
        
        public static ArrayList<SiteUser> getAllSiteUserByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteUser> site_user = new ArrayList<SiteUser>();
            try
            {
                getAllRecordsByColumn("site_user", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_user.add(processSiteUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteUserByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_user;
        }
        
        public static SiteUser getSiteUserByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteUser site_user = new SiteUser();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_user", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_user = processSiteUser(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteUserByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_user;
        }                
                
        public static void updateSiteUser(Integer SiteUserId,Integer SiteId,Integer UserId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_user SET SiteId=?,UserId=? WHERE SiteUserId=?;");                    
                preparedStatement.setInt(1, SiteId);
                preparedStatement.setInt(2, UserId);
                preparedStatement.setInt(3, SiteUserId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteUser()
        {
            try
            {
                updateQuery("DELETE FROM site_user;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteUserById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_user WHERE SiteUserId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteUserById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteUserByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_user WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteUserByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

