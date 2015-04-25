
























































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.data.Column;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class SiteFileDaoImpl extends BasicConnection implements Serializable, SiteFileDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteFileDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteFileDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteFileCache
        {
            public static final ConcurrentLruCache<Integer, SiteFile> siteFileCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SiteFile i : findAll())
                {
                    getCache().put(i.getSiteFileId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SiteFile> getCache()
        {
            return SiteFileCache.siteFileCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SiteFile> buildCache(ArrayList<SiteFile> siteFileList)
        {        
            ConcurrentLruCache<Integer, SiteFile> cache = new ConcurrentLruCache<Integer, SiteFile>(siteFileList.size() + 1000);
            for (SiteFile i : siteFileList)
            {
                cache.put(i.getSiteFileId(), i);
            }
            return cache;
        }

        private static ArrayList<SiteFile> findAll()
        {
            ArrayList<SiteFile> siteFile = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("siteFile");
                while (rs.next())
                {
                    siteFile.add(SiteFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteFile object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteFile;
        }
        
        @Override
        public SiteFile find(Integer id)
        {
            return findByColumn("SiteFileId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SiteFile findWithInfo(Integer id)
        {
            SiteFile siteFile = findByColumn("SiteFileId", id.toString(), null, null).get(0);
            
            
            
            return siteFile;
        }
        
        @Override
        public ArrayList<SiteFile> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteFile> siteFileList = new ArrayList<SiteFile>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SiteFile, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteFileList = new ArrayList<SiteFile>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_file", limit, offset);
                    while (rs.next())
                    {
                        siteFileList.add(SiteFile.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteFile object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteFileList;
         
        }
        
        @Override
        public ArrayList<SiteFile> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteFile> siteFileList = new ArrayList<SiteFile>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SiteFile, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteFileList = new ArrayList<SiteFile>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteFileList = new ArrayList<SiteFile>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_file", limit, offset);
                    while (rs.next())
                    {
                        siteFileList.add(SiteFile.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SiteFile method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteFileList;            
        }
        
        @Override
        public ArrayList<SiteFile> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteFile> siteFileList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SiteFile(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SiteFile i = (SiteFile) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteFileList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteFileList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_file", SiteFile.checkColumnName(columnName), columnValue, SiteFile.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteFileList.add(SiteFile.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteFile's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteFileList;
        } 
        
        @Override
        public ArrayList<SiteFile> findByColumns(Column... columns)
        {
            ArrayList<SiteFile> siteFileList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(SiteFile.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("site_file", columns);
                while (rs.next())
                {
                    siteFileList.add(SiteFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteFile's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return siteFileList;
        }
    
        @Override
        public int add(SiteFile obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SiteFile.checkColumnSize(obj.getFileName(), 255);
                SiteFile.checkColumnSize(obj.getDescription(), 255);
                SiteFile.checkColumnSize(obj.getLabel(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO site_file(FileName,Description,Label) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_file;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteFileId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SiteFile update(SiteFile obj)
        {
           try
            {   
                
                SiteFile.checkColumnSize(obj.getFileName(), 255);
                SiteFile.checkColumnSize(obj.getDescription(), 255);
                SiteFile.checkColumnSize(obj.getLabel(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_file SET FileName=?,Description=?,Label=? WHERE SiteFileId=?;");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                preparedStatement.setInt(4, obj.getSiteFileId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteFileId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_file");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SiteFile site_file)
        {
              
        }
        
        @Override
        public void getRelatedObjects(SiteFile site_file)
        {
            site_file.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFileId", site_file.getSiteFileId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(SiteFile obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + obj.getSiteFileId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteFileId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + id + ";");           
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
                updateQuery("DELETE FROM site_file;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_file WHERE " + SiteFile.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's removeByColumn method error: " + ex.getMessage());
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
                        SiteFile i = (SiteFile) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteFileId());
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
        
                    
        public void getRelatedFileFolderList(SiteFile site_file)
        {           
            site_file.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFileId", site_file.getSiteFileId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

