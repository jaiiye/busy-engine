





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteFolderDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteFolder.PROP_SITE_FOLDER_ID) || column.equals(SiteFolder.PROP_FOLDER_NAME) || column.equals(SiteFolder.PROP_DESCRIPTION) || column.equals(SiteFolder.PROP_RANK) || column.equals(SiteFolder.PROP_SITE_ID) )
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
            if (column.equals(SiteFolder.PROP_SITE_FOLDER_ID) || column.equals(SiteFolder.PROP_RANK) || column.equals(SiteFolder.PROP_SITE_ID) )
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
            return new SiteFolder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addSiteFolder(String FolderName, String Description, Integer Rank, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FolderName, 100);
                checkColumnSize(Description, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_folder(FolderName,Description,Rank,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, FolderName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setInt(4, SiteId);
                
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
        
        public static ArrayList<SiteFolder> getAllSiteFolderWithRelatedInfo()
        {
            ArrayList<SiteFolder> site_folderList = new ArrayList<SiteFolder>();
            try
            {
                getAllRecordsByTableName("site_folder");
                while (rs.next())
                {
                    site_folderList.add(processSiteFolder(rs));
                }

                
                    for(SiteFolder site_folder : site_folderList)
                    {
                        
                            getRecordById("Site", site_folder.getSiteId().toString());
                            site_folder.setSite(SiteDAO.processSite(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteFolderWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folderList;
        }
        
        
        public static SiteFolder getRelatedInfo(SiteFolder site_folder)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Site", site_folder.getSiteId().toString());
                            site_folder.setSite(SiteDAO.processSite(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return site_folder;
        }
        
        public static SiteFolder getAllRelatedObjects(SiteFolder site_folder)
        {           
            site_folder.setFileFolderList(FileFolderDAO.getAllFileFolderByColumn("SiteFolderId", site_folder.getSiteFolderId().toString()));
             
            return site_folder;
        }
        
        
                    
        public static SiteFolder getRelatedFileFolderList(SiteFolder site_folder)
        {           
            site_folder.setFileFolderList(FileFolderDAO.getAllFileFolderByColumn("SiteFolderId", site_folder.getSiteFolderId().toString()));
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
                
        public static void updateSiteFolder(Integer SiteFolderId,String FolderName,String Description,Integer Rank,Integer SiteId)
        {  
            try
            {   
                
                checkColumnSize(FolderName, 100);
                checkColumnSize(Description, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_folder SET FolderName=?,Description=?,Rank=?,SiteId=? WHERE SiteFolderId=?;");                    
                preparedStatement.setString(1, FolderName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setInt(4, SiteId);
                preparedStatement.setInt(5, SiteFolderId);
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

