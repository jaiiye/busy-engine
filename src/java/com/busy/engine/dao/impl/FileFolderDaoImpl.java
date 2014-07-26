





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FileFolderDaoImpl extends BasicConnection implements Serializable, FileFolderDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public FileFolder find(Integer id)
        {
            return findByColumn("FileFolderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<FileFolder> findAll(Integer limit, Integer offset)
        {
            ArrayList<FileFolder> file_folder = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("file_folder");
                while(rs.next())
                {
                    file_folder.add(FileFolder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("FileFolder object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
         
        }
        
        @Override
        public ArrayList<FileFolder> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<FileFolder> file_folderList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("file_folder", limit, offset);
                while (rs.next())
                {
                    file_folderList.add(FileFolder.process(rs));
                }

                
                    for(FileFolder file_folder : file_folderList)
                    {
                        
                            getRecordById("SiteFile", file_folder.getSiteFileId().toString());
                            file_folder.setSiteFile(SiteFile.process(rs));               
                        
                            getRecordById("SiteFolder", file_folder.getSiteFolderId().toString());
                            file_folder.setSiteFolder(SiteFolder.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object FileFolder method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folderList;
        }
        
        @Override
        public ArrayList<FileFolder> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<FileFolder> file_folder = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("file_folder", FileFolder.checkColumnName(columnName), columnValue, FileFolder.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   file_folder.add(FileFolder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object FileFolder's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
        } 
    
        @Override
        public void add(FileFolder obj)
        {
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO file_folder(SiteFileId,SiteFolderId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteFileId());
                preparedStatement.setInt(2, obj.getSiteFolderId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer SiteFileId, Integer SiteFolderId)
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
        
        
        @Override
        public FileFolder update(FileFolder obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE file_folder SET SiteFileId=?,SiteFolderId=? WHERE FileFolderId=?;");                    
                preparedStatement.setInt(1, obj.getSiteFileId());
                preparedStatement.setInt(2, obj.getSiteFolderId());
                preparedStatement.setInt(3, obj.getFileFolderId());
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
            return obj;
        }
        
        public static void update(Integer FileFolderId,Integer SiteFileId,Integer SiteFolderId)
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("file_folder");
        }
        
        
        @Override
        public FileFolder getRelatedInfo(FileFolder file_folder)
        {
            
                try
                { 
                    
                        getRecordById("SiteFile", file_folder.getSiteFileId().toString());
                        file_folder.setSiteFile(SiteFile.process(rs));               
                    
                        getRecordById("SiteFolder", file_folder.getSiteFolderId().toString());
                        file_folder.setSiteFolder(SiteFolder.process(rs));               
                    

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
        
        
        @Override
        public FileFolder getRelatedObjects(FileFolder file_folder)
        {
                         
            return file_folder;
        }
        
        
        
        @Override
        public void remove(FileFolder obj)
        {            
            try
            {
                updateQuery("DELETE FROM file_folder WHERE FileFolderId=" + obj.getFileFolderId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM file_folder WHERE FileFolderId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM file_folder;");
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM file_folder WHERE " + FileFolder.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

