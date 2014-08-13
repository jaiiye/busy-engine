





































    package com.busy.engine.dao;

import com.busy.engine.entity.SiteFolder;
import com.busy.engine.entity.SiteFile;
import com.busy.engine.entity.FileFolder;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("FileFolder's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return file_folder;
        } 
    
        @Override
        public int add(FileFolder obj)
        {
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO file_folder(SiteFileId,SiteFolderId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteFileId());
                preparedStatement.setInt(2, obj.getSiteFolderId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from file_folder;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's add method error: " + ex.getMessage());
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
                System.out.println("FileFolder's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("file_folder");
        }
        
        
        @Override
        public void getRelatedInfo(FileFolder file_folder)
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
              
        }
        
        @Override
        public void getRelatedObjects(FileFolder file_folder)
        {
             
        }
        
        @Override
        public boolean remove(FileFolder obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM file_folder WHERE FileFolderId=" + obj.getFileFolderId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM file_folder WHERE FileFolderId=" + id + ";");           
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
                updateQuery("DELETE FROM file_folder;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM file_folder WHERE " + FileFolder.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

