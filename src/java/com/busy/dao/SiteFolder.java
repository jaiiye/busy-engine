


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteFolder extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITEFOLDERID = "SiteFolderId";
        public static final String PROP_SITEFOLDERNAME = "SiteFolderName";
        public static final String PROP_SITEFOLDERDESCRIPTION = "SiteFolderDescription";
        public static final String PROP_SITEFOLDERRANK = "SiteFolderRank";
        
        
        private Integer siteFolderId;
        private String siteFolderName;
        private String siteFolderDescription;
        private Integer siteFolderRank;
        
        
        public SiteFolder()
        {
            this.siteFolderId = 0; 
       this.siteFolderName = ""; 
       this.siteFolderDescription = ""; 
       this.siteFolderRank = 0; 
        }
        
        public SiteFolder(Integer SiteFolderId, String SiteFolderName, String SiteFolderDescription, Integer SiteFolderRank)
        {
            this.siteFolderId = SiteFolderId;
       this.siteFolderName = SiteFolderName;
       this.siteFolderDescription = SiteFolderDescription;
       this.siteFolderRank = SiteFolderRank;
        } 
        
        
            public Integer getSiteFolderId()
            {
                return this.siteFolderId;
            }
            
            public void setSiteFolderId(Integer SiteFolderId)
            {
                this.siteFolderId = SiteFolderId;
            }
        
            public String getSiteFolderName()
            {
                return this.siteFolderName;
            }
            
            public void setSiteFolderName(String SiteFolderName)
            {
                this.siteFolderName = SiteFolderName;
            }
        
            public String getSiteFolderDescription()
            {
                return this.siteFolderDescription;
            }
            
            public void setSiteFolderDescription(String SiteFolderDescription)
            {
                this.siteFolderDescription = SiteFolderDescription;
            }
        
            public Integer getSiteFolderRank()
            {
                return this.siteFolderRank;
            }
            
            public void setSiteFolderRank(Integer SiteFolderRank)
            {
                this.siteFolderRank = SiteFolderRank;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteFolder.PROP_SITEFOLDERID) || column.equals(SiteFolder.PROP_SITEFOLDERNAME) || column.equals(SiteFolder.PROP_SITEFOLDERDESCRIPTION) || column.equals(SiteFolder.PROP_SITEFOLDERRANK) )
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
            if (column.equals(SiteFolder.PROP_SITEFOLDERID) || column.equals(SiteFolder.PROP_SITEFOLDERRANK) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteFolder processSiteFolder(ResultSet rs) throws SQLException
        {        
            return new SiteFolder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }
        
        public static int addSiteFolder(String SiteFolderName, String SiteFolderDescription, Integer SiteFolderRank)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(SiteFolderName, 100);
                checkColumnSize(SiteFolderDescription, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_folder(SiteFolderName,SiteFolderDescription,SiteFolderRank) VALUES (?,?,?);");                    
                preparedStatement.setString(1, SiteFolderName);
                preparedStatement.setString(2, SiteFolderDescription);
                preparedStatement.setInt(3, SiteFolderRank);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_folder;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteFolderCount()
        {
            return getAllRecordsCountByTableName("site_folder");        
        }
        
        public static ArrayList<SiteFolder> getAllSiteFolder()
        {
            ArrayList<SiteFolder> site_folder = new ArrayList<SiteFolder>();
            try
            {
                getAllRecordsByTableName("site_folder");
                while(rs.next())
                {
                    site_folder.add(processSiteFolder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folder;
        }
                
        public static ArrayList<SiteFolder> getSiteFolderPaged(int limit, int offset)
        {
            ArrayList<SiteFolder> site_folder = new ArrayList<SiteFolder>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_folder", limit, offset);
                while (rs.next())
                {
                    site_folder.add(processSiteFolder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteFolderPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folder;
        } 
        
        public static ArrayList<SiteFolder> getAllSiteFolderByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteFolder> site_folder = new ArrayList<SiteFolder>();
            try
            {
                getAllRecordsByColumn("site_folder", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_folder.add(processSiteFolder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteFolderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folder;
        }
        
        public static SiteFolder getSiteFolderByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteFolder site_folder = new SiteFolder();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_folder", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_folder = processSiteFolder(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteFolderByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folder;
        }                
                
        public static void updateSiteFolder(Integer SiteFolderId,String SiteFolderName,String SiteFolderDescription,Integer SiteFolderRank)
        {  
            try
            {   
                
                checkColumnSize(SiteFolderName, 100);
                checkColumnSize(SiteFolderDescription, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_folder SET SiteFolderName=?,SiteFolderDescription=?,SiteFolderRank=? WHERE SiteFolderId=?;");                    
                preparedStatement.setString(1, SiteFolderName);
                preparedStatement.setString(2, SiteFolderDescription);
                preparedStatement.setInt(3, SiteFolderRank);
                preparedStatement.setInt(4, SiteFolderId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteFolder()
        {
            try
            {
                updateQuery("DELETE FROM site_folder;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteFolderById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_folder WHERE SiteFolderId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteFolderById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteFolderByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_folder WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteFolderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

