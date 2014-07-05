


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteInfo extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITEINFOID = "SiteInfoId";
        public static final String PROP_SITEADMINISTRATORNAME = "SiteAdministratorName";
        public static final String PROP_SITEADMINISTRATOREMAIL = "SiteAdministratorEmail";
        public static final String PROP_SITENOTIFICATIONEMAIL = "SiteNotificationEmail";
        public static final String PROP_SITEURL = "SiteURL";
        public static final String PROP_SITETESTINGURL = "SiteTestingURL";
        public static final String PROP_SITEMODE = "SiteMode";
        public static final String PROP_SITETESTINGEMAIL = "SiteTestingEmail";
        public static final String PROP_SITETESTINGEMAILPASSWORD = "SiteTestingEmailPassword";
        
        
        private Integer siteInfoId;
        private String siteAdministratorName;
        private String siteAdministratorEmail;
        private String siteNotificationEmail;
        private String siteURL;
        private String siteTestingURL;
        private Integer siteMode;
        private String siteTestingEmail;
        private String siteTestingEmailPassword;
        
        
        public SiteInfo()
        {
            this.siteInfoId = 0; 
       this.siteAdministratorName = ""; 
       this.siteAdministratorEmail = ""; 
       this.siteNotificationEmail = ""; 
       this.siteURL = ""; 
       this.siteTestingURL = ""; 
       this.siteMode = 0; 
       this.siteTestingEmail = ""; 
       this.siteTestingEmailPassword = ""; 
        }
        
        public SiteInfo(Integer SiteInfoId, String SiteAdministratorName, String SiteAdministratorEmail, String SiteNotificationEmail, String SiteURL, String SiteTestingURL, Integer SiteMode, String SiteTestingEmail, String SiteTestingEmailPassword)
        {
            this.siteInfoId = SiteInfoId;
       this.siteAdministratorName = SiteAdministratorName;
       this.siteAdministratorEmail = SiteAdministratorEmail;
       this.siteNotificationEmail = SiteNotificationEmail;
       this.siteURL = SiteURL;
       this.siteTestingURL = SiteTestingURL;
       this.siteMode = SiteMode;
       this.siteTestingEmail = SiteTestingEmail;
       this.siteTestingEmailPassword = SiteTestingEmailPassword;
        } 
        
        
            public Integer getSiteInfoId()
            {
                return this.siteInfoId;
            }
            
            public void setSiteInfoId(Integer SiteInfoId)
            {
                this.siteInfoId = SiteInfoId;
            }
        
            public String getSiteAdministratorName()
            {
                return this.siteAdministratorName;
            }
            
            public void setSiteAdministratorName(String SiteAdministratorName)
            {
                this.siteAdministratorName = SiteAdministratorName;
            }
        
            public String getSiteAdministratorEmail()
            {
                return this.siteAdministratorEmail;
            }
            
            public void setSiteAdministratorEmail(String SiteAdministratorEmail)
            {
                this.siteAdministratorEmail = SiteAdministratorEmail;
            }
        
            public String getSiteNotificationEmail()
            {
                return this.siteNotificationEmail;
            }
            
            public void setSiteNotificationEmail(String SiteNotificationEmail)
            {
                this.siteNotificationEmail = SiteNotificationEmail;
            }
        
            public String getSiteURL()
            {
                return this.siteURL;
            }
            
            public void setSiteURL(String SiteURL)
            {
                this.siteURL = SiteURL;
            }
        
            public String getSiteTestingURL()
            {
                return this.siteTestingURL;
            }
            
            public void setSiteTestingURL(String SiteTestingURL)
            {
                this.siteTestingURL = SiteTestingURL;
            }
        
            public Integer getSiteMode()
            {
                return this.siteMode;
            }
            
            public void setSiteMode(Integer SiteMode)
            {
                this.siteMode = SiteMode;
            }
        
            public String getSiteTestingEmail()
            {
                return this.siteTestingEmail;
            }
            
            public void setSiteTestingEmail(String SiteTestingEmail)
            {
                this.siteTestingEmail = SiteTestingEmail;
            }
        
            public String getSiteTestingEmailPassword()
            {
                return this.siteTestingEmailPassword;
            }
            
            public void setSiteTestingEmailPassword(String SiteTestingEmailPassword)
            {
                this.siteTestingEmailPassword = SiteTestingEmailPassword;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteInfo.PROP_SITEINFOID) || column.equals(SiteInfo.PROP_SITEADMINISTRATORNAME) || column.equals(SiteInfo.PROP_SITEADMINISTRATOREMAIL) || column.equals(SiteInfo.PROP_SITENOTIFICATIONEMAIL) || column.equals(SiteInfo.PROP_SITEURL) || column.equals(SiteInfo.PROP_SITETESTINGURL) || column.equals(SiteInfo.PROP_SITEMODE) || column.equals(SiteInfo.PROP_SITETESTINGEMAIL) || column.equals(SiteInfo.PROP_SITETESTINGEMAILPASSWORD) )
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
            if (column.equals(SiteInfo.PROP_SITEINFOID) || column.equals(SiteInfo.PROP_SITEMODE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteInfo processSiteInfo(ResultSet rs) throws SQLException
        {        
            return new SiteInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9));
        }
        
        public static int addSiteInfo(String SiteAdministratorName, String SiteAdministratorEmail, String SiteNotificationEmail, String SiteURL, String SiteTestingURL, Integer SiteMode, String SiteTestingEmail, String SiteTestingEmailPassword)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(SiteAdministratorName, 45);
                checkColumnSize(SiteAdministratorEmail, 155);
                checkColumnSize(SiteNotificationEmail, 155);
                checkColumnSize(SiteURL, 255);
                checkColumnSize(SiteTestingURL, 255);
                
                checkColumnSize(SiteTestingEmail, 255);
                checkColumnSize(SiteTestingEmailPassword, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO site_info(SiteAdministratorName,SiteAdministratorEmail,SiteNotificationEmail,SiteURL,SiteTestingURL,SiteMode,SiteTestingEmail,SiteTestingEmailPassword) VALUES (?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, SiteAdministratorName);
                preparedStatement.setString(2, SiteAdministratorEmail);
                preparedStatement.setString(3, SiteNotificationEmail);
                preparedStatement.setString(4, SiteURL);
                preparedStatement.setString(5, SiteTestingURL);
                preparedStatement.setInt(6, SiteMode);
                preparedStatement.setString(7, SiteTestingEmail);
                preparedStatement.setString(8, SiteTestingEmailPassword);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_info;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteInfoCount()
        {
            return getAllRecordsCountByTableName("site_info");        
        }
        
        public static ArrayList<SiteInfo> getAllSiteInfo()
        {
            ArrayList<SiteInfo> site_info = new ArrayList<SiteInfo>();
            try
            {
                getAllRecordsByTableName("site_info");
                while(rs.next())
                {
                    site_info.add(processSiteInfo(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_info;
        }
                
        public static ArrayList<SiteInfo> getSiteInfoPaged(int limit, int offset)
        {
            ArrayList<SiteInfo> site_info = new ArrayList<SiteInfo>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_info", limit, offset);
                while (rs.next())
                {
                    site_info.add(processSiteInfo(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteInfoPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_info;
        } 
        
        public static ArrayList<SiteInfo> getAllSiteInfoByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteInfo> site_info = new ArrayList<SiteInfo>();
            try
            {
                getAllRecordsByColumn("site_info", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_info.add(processSiteInfo(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteInfoByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_info;
        }
        
        public static SiteInfo getSiteInfoByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteInfo site_info = new SiteInfo();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_info", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_info = processSiteInfo(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteInfoByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_info;
        }                
                
        public static void updateSiteInfo(Integer SiteInfoId,String SiteAdministratorName,String SiteAdministratorEmail,String SiteNotificationEmail,String SiteURL,String SiteTestingURL,Integer SiteMode,String SiteTestingEmail,String SiteTestingEmailPassword)
        {  
            try
            {   
                
                checkColumnSize(SiteAdministratorName, 45);
                checkColumnSize(SiteAdministratorEmail, 155);
                checkColumnSize(SiteNotificationEmail, 155);
                checkColumnSize(SiteURL, 255);
                checkColumnSize(SiteTestingURL, 255);
                
                checkColumnSize(SiteTestingEmail, 255);
                checkColumnSize(SiteTestingEmailPassword, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_info SET SiteAdministratorName=?,SiteAdministratorEmail=?,SiteNotificationEmail=?,SiteURL=?,SiteTestingURL=?,SiteMode=?,SiteTestingEmail=?,SiteTestingEmailPassword=? WHERE SiteInfoId=?;");                    
                preparedStatement.setString(1, SiteAdministratorName);
                preparedStatement.setString(2, SiteAdministratorEmail);
                preparedStatement.setString(3, SiteNotificationEmail);
                preparedStatement.setString(4, SiteURL);
                preparedStatement.setString(5, SiteTestingURL);
                preparedStatement.setInt(6, SiteMode);
                preparedStatement.setString(7, SiteTestingEmail);
                preparedStatement.setString(8, SiteTestingEmailPassword);
                preparedStatement.setInt(9, SiteInfoId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteInfo()
        {
            try
            {
                updateQuery("DELETE FROM site_info;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteInfoById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_info WHERE SiteInfoId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteInfoById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteInfoByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_info WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteInfoByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

