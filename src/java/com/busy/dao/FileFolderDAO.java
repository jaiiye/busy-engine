





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FileFolderDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(FileFolder.PROP_FILE_FOLDER_ID) || column.equals(FileFolder.PROP_SITE_FILE_ID) || column.equals(FileFolder.PROP_SITE_FOLDER_ID) )
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
            if (column.equals(FileFolder.PROP_FILE_FOLDER_ID) || column.equals(FileFolder.PROP_SITE_FILE_ID) || column.equals(FileFolder.PROP_SITE_FOLDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static FileFolder processFileFolder(ResultSet rs) throws SQLException
        {        
            return new FileFolder(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addFileFolder(Integer SiteFileId, Integer SiteFolderId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO file_folder(SiteFileId,SiteFolderId) VALUES (?,?);");                    
                preparedStatement.setInt(1, SiteFileId);
                preparedStatement.setInt(2, SiteFolderId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from file_folder;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addFileFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllFileFolderCount()
        {
            return getAllRecordsCountByTableName("file_folder");        
        }
        
        public static ArrayList<FileFolder> getAllFileFolder()
        {
            ArrayList<FileFolder> file_folder = new ArrayList<FileFolder>();
            try
            {
                getAllRecordsByTableName("file_folder");
                while(rs.next())
                {
                    file_folder.add(processFileFolder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllFileFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
        }
        
        public static ArrayList<FileFolder> getAllFileFolderWithRelatedInfo()
        {
            ArrayList<FileFolder> file_folderList = new ArrayList<FileFolder>();
            try
            {
                getAllRecordsByTableName("file_folder");
                while (rs.next())
                {
                    file_folderList.add(processFileFolder(rs));
                }

                
                    for(FileFolder file_folder : file_folderList)
                    {
                        
                            getRecordById("SiteFile", file_folder.getSiteFileId().toString());
                            file_folder.setSiteFile(SiteFileDAO.processSiteFile(rs));               
                        
                            getRecordById("SiteFolder", file_folder.getSiteFolderId().toString());
                            file_folder.setSiteFolder(SiteFolderDAO.processSiteFolder(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllFileFolderWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folderList;
        }
        
        
        public static FileFolder getRelatedInfo(FileFolder file_folder)
        {
           
                
                    try
                    { 
                        
                            getRecordById("SiteFile", file_folder.getSiteFileId().toString());
                            file_folder.setSiteFile(SiteFileDAO.processSiteFile(rs));               
                        
                            getRecordById("SiteFolder", file_folder.getSiteFolderId().toString());
                            file_folder.setSiteFolder(SiteFolderDAO.processSiteFolder(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return file_folder;
        }
        
        public static FileFolder getAllRelatedObjects(FileFolder file_folder)
        {           
                         
            return file_folder;
        }
        
        
        
                
        public static ArrayList<FileFolder> getFileFolderPaged(int limit, int offset)
        {
            ArrayList<FileFolder> file_folder = new ArrayList<FileFolder>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("file_folder", limit, offset);
                while (rs.next())
                {
                    file_folder.add(processFileFolder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getFileFolderPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
        } 
        
        public static ArrayList<FileFolder> getAllFileFolderByColumn(String columnName, String columnValue)
        {
            ArrayList<FileFolder> file_folder = new ArrayList<FileFolder>();
            try
            {
                getAllRecordsByColumn("file_folder", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    file_folder.add(processFileFolder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllFileFolderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
        }
        
        public static FileFolder getFileFolderByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            FileFolder file_folder = new FileFolder();
            try
            {
                getRecordsByColumnWithLimitAndOffset("file_folder", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   file_folder = processFileFolder(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getFileFolderByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
        }                
                
        public static void updateFileFolder(Integer FileFolderId,Integer SiteFileId,Integer SiteFolderId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE file_folder SET SiteFileId=?,SiteFolderId=? WHERE FileFolderId=?;");                    
                preparedStatement.setInt(1, SiteFileId);
                preparedStatement.setInt(2, SiteFolderId);
                preparedStatement.setInt(3, FileFolderId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateFileFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllFileFolder()
        {
            try
            {
                updateQuery("DELETE FROM file_folder;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllFileFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteFileFolderById(String id)
        {
            try
            {
                updateQuery("DELETE FROM file_folder WHERE FileFolderId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteFileFolderById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteFileFolderByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM file_folder WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteFileFolderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

