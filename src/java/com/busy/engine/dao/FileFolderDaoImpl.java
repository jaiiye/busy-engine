






















































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class FileFolderDaoImpl extends BasicConnection implements Serializable, FileFolderDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public FileFolderDaoImpl()
        {
            cachingEnabled = false;
        }

        public FileFolderDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class FileFolderCache
        {
            public static final ConcurrentLruCache<Integer, FileFolder> fileFolderCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (FileFolder i : findAll())
                {
                    getCache().put(i.getFileFolderId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, FileFolder> getCache()
        {
            return FileFolderCache.fileFolderCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, FileFolder> buildCache(ArrayList<FileFolder> fileFolderList)
        {        
            ConcurrentLruCache<Integer, FileFolder> cache = new ConcurrentLruCache<Integer, FileFolder>(fileFolderList.size() + 1000);
            for (FileFolder i : fileFolderList)
            {
                cache.put(i.getFileFolderId(), i);
            }
            return cache;
        }

        private static ArrayList<FileFolder> findAll()
        {
            ArrayList<FileFolder> fileFolder = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("fileFolder");
                while (rs.next())
                {
                    fileFolder.add(FileFolder.process(rs));
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
            return fileFolder;
        }
        
        @Override
        public FileFolder find(Integer id)
        {
            return findByColumn("FileFolderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public FileFolder findWithInfo(Integer id)
        {
            FileFolder fileFolder = findByColumn("FileFolderId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("SiteFile", fileFolder.getSiteFileId().toString());
                    fileFolder.setSiteFile(SiteFile.process(rs));               
                
                    getRecordById("SiteFolder", fileFolder.getSiteFolderId().toString());
                    fileFolder.setSiteFolder(SiteFolder.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object FileFolder method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return fileFolder;
        }
        
        @Override
        public ArrayList<FileFolder> findAll(Integer limit, Integer offset)
        {
            ArrayList<FileFolder> fileFolderList = new ArrayList<FileFolder>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for FileFolder, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    fileFolderList = new ArrayList<FileFolder>(getCache().getValues());
                }
                else
                {
                    cacheNotUsed = true;
                }
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("file_folder", limit, offset);
                    while (rs.next())
                    {
                        fileFolderList.add(FileFolder.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("FileFolder object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return fileFolderList;
         
        }
        
        @Override
        public ArrayList<FileFolder> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<FileFolder> fileFolderList = new ArrayList<FileFolder>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for FileFolder, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    fileFolderList = new ArrayList<FileFolder>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            FileFolder fileFolder = (FileFolder) e.getValue();

                            
                                getRecordById("SiteFile", fileFolder.getSiteFileId().toString());
                                fileFolder.setSiteFile(SiteFile.process(rs));               
                            
                                getRecordById("SiteFolder", fileFolder.getSiteFolderId().toString());
                                fileFolder.setSiteFolder(SiteFolder.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object FileFolder method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                fileFolderList = new ArrayList<FileFolder>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("file_folder", limit, offset);
                    while (rs.next())
                    {
                        fileFolderList.add(FileFolder.process(rs));
                    }

                    
                    
                        for (FileFolder fileFolder : fileFolderList)
                        {                        
                            
                                getRecordById("SiteFile", fileFolder.getSiteFileId().toString());
                                fileFolder.setSiteFile(SiteFile.process(rs));               
                            
                                getRecordById("SiteFolder", fileFolder.getSiteFolderId().toString());
                                fileFolder.setSiteFolder(SiteFolder.process(rs));               
                              
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
            }
            return fileFolderList;            
        }
        
        @Override
        public ArrayList<FileFolder> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<FileFolder> fileFolderList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for FileFolder(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            FileFolder i = (FileFolder) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                fileFolderList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            fileFolderList = null;
                        }
                    }
                }
                else
                {
                    cacheNotUsed = true;
                }
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                try
                {
                    getRecordsByColumnWithLimitOrOffset("file_folder", FileFolder.checkColumnName(columnName), columnValue, FileFolder.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        fileFolderList.add(FileFolder.process(rs));
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
            }
            return fileFolderList;
        } 
    
        @Override
        public int add(FileFolder obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO file_folder(SiteFileId,SiteFolderId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteFileId());
                preparedStatement.setInt(2, obj.getSiteFolderId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from file_folder;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FileFolder's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setFileFolderId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
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
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getFileFolderId(), obj);
                }            
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
            int count = 0;
            if (cachingEnabled)
            {
                count = getCache().size();
            }
            else
            {
                count = getAllRecordsCountByTableName("file_folder");
            }
            return count;
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
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getFileFolderId());
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
            
            if(cachingEnabled && success)
            {
                getCache().remove(id);
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
        
            if(cachingEnabled && success)
            {
                getCache().clear();
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
            
            if(cachingEnabled && success)
            {
                ArrayList<Integer> keys = new ArrayList<Integer>();

                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        FileFolder i = (FileFolder) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getFileFolderId());
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                    }
                }

                for(int id : keys)
                {
                    getCache().remove(id);
                }
            }
            
            return success;
        }
        
        public boolean isCachingEnabled()
        {
            return cachingEnabled;
        }
        
        public void setCachingEnabled(boolean cachingEnabled)
        {
            this.cachingEnabled = cachingEnabled;
        }
        
        
            
        
        
        public void getRelatedSiteFile(FileFolder file_folder)
        {            
            try
            {                 
                getRecordById("SiteFile", file_folder.getSiteFileId().toString());
                file_folder.setSiteFile(SiteFile.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedSiteFolder(FileFolder file_folder)
        {            
            try
            {                 
                getRecordById("SiteFolder", file_folder.getSiteFolderId().toString());
                file_folder.setSiteFolder(SiteFolder.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedSiteFileWithInfo(FileFolder file_folder)
        {            
            file_folder.setSiteFile(new SiteFileDaoImpl().findWithInfo(file_folder.getSiteFileId()));
        }
        
        public void getRelatedSiteFolderWithInfo(FileFolder file_folder)
        {            
            file_folder.setSiteFolder(new SiteFolderDaoImpl().findWithInfo(file_folder.getSiteFolderId()));
        }
          
        
    }

