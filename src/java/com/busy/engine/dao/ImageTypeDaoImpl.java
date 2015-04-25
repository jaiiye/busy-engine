
























































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
    
    public class ImageTypeDaoImpl extends BasicConnection implements Serializable, ImageTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ImageTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ImageTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ImageTypeCache
        {
            public static final ConcurrentLruCache<Integer, ImageType> imageTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ImageType i : findAll())
                {
                    getCache().put(i.getImageTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ImageType> getCache()
        {
            return ImageTypeCache.imageTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ImageType> buildCache(ArrayList<ImageType> imageTypeList)
        {        
            ConcurrentLruCache<Integer, ImageType> cache = new ConcurrentLruCache<Integer, ImageType>(imageTypeList.size() + 1000);
            for (ImageType i : imageTypeList)
            {
                cache.put(i.getImageTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<ImageType> findAll()
        {
            ArrayList<ImageType> imageType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("imageType");
                while (rs.next())
                {
                    imageType.add(ImageType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ImageType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return imageType;
        }
        
        @Override
        public ImageType find(Integer id)
        {
            return findByColumn("ImageTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ImageType findWithInfo(Integer id)
        {
            ImageType imageType = findByColumn("ImageTypeId", id.toString(), null, null).get(0);
            
            
            
            return imageType;
        }
        
        @Override
        public ArrayList<ImageType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ImageType> imageTypeList = new ArrayList<ImageType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ImageType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    imageTypeList = new ArrayList<ImageType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("image_type", limit, offset);
                    while (rs.next())
                    {
                        imageTypeList.add(ImageType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ImageType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return imageTypeList;
         
        }
        
        @Override
        public ArrayList<ImageType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ImageType> imageTypeList = new ArrayList<ImageType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ImageType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    imageTypeList = new ArrayList<ImageType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                imageTypeList = new ArrayList<ImageType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("image_type", limit, offset);
                    while (rs.next())
                    {
                        imageTypeList.add(ImageType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ImageType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return imageTypeList;            
        }
        
        @Override
        public ArrayList<ImageType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ImageType> imageTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ImageType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ImageType i = (ImageType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                imageTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            imageTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("image_type", ImageType.checkColumnName(columnName), columnValue, ImageType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        imageTypeList.add(ImageType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ImageType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return imageTypeList;
        } 
        
        @Override
        public ArrayList<ImageType> findByColumns(Column... columns)
        {
            ArrayList<ImageType> imageTypeList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(ImageType.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("image_type", columns);
                while (rs.next())
                {
                    imageTypeList.add(ImageType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ImageType's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return imageTypeList;
        }
    
        @Override
        public int add(ImageType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ImageType.checkColumnSize(obj.getTypeName(), 45);
                ImageType.checkColumnSize(obj.getDescription(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO image_type(TypeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from image_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setImageTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ImageType update(ImageType obj)
        {
           try
            {   
                
                ImageType.checkColumnSize(obj.getTypeName(), 45);
                ImageType.checkColumnSize(obj.getDescription(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE image_type SET TypeName=?,Description=? WHERE ImageTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getImageTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getImageTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("image_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ImageType image_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ImageType image_type)
        {
            image_type.setSiteImageList(new SiteImageDaoImpl().findByColumn("ImageTypeId", image_type.getImageTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ImageType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + obj.getImageTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getImageTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM image_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM image_type WHERE " + ImageType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's removeByColumn method error: " + ex.getMessage());
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
                        ImageType i = (ImageType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getImageTypeId());
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
        
                    
        public void getRelatedSiteImageList(ImageType image_type)
        {           
            image_type.setSiteImageList(new SiteImageDaoImpl().findByColumn("ImageTypeId", image_type.getImageTypeId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

