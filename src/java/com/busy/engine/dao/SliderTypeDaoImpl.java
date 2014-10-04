






















































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.entity.*;
    import com.busy.engine.dao.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class SliderTypeDaoImpl extends BasicConnection implements Serializable, SliderTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SliderTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public SliderTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SliderTypeCache
        {
            public static final ConcurrentLruCache<Integer, SliderType> sliderTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SliderType i : findAll())
                {
                    getCache().put(i.getSliderTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SliderType> getCache()
        {
            return SliderTypeCache.sliderTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SliderType> buildCache(ArrayList<SliderType> sliderTypeList)
        {        
            ConcurrentLruCache<Integer, SliderType> cache = new ConcurrentLruCache<Integer, SliderType>(sliderTypeList.size() + 1000);
            for (SliderType i : sliderTypeList)
            {
                cache.put(i.getSliderTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<SliderType> findAll()
        {
            ArrayList<SliderType> sliderType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("sliderType");
                while (rs.next())
                {
                    sliderType.add(SliderType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SliderType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return sliderType;
        }
        
        @Override
        public SliderType find(Integer id)
        {
            return findByColumn("SliderTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SliderType> findAll(Integer limit, Integer offset)
        {
            ArrayList<SliderType> sliderTypeList = new ArrayList<SliderType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SliderType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    sliderTypeList = new ArrayList<SliderType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("slider_type", limit, offset);
                    while (rs.next())
                    {
                        sliderTypeList.add(SliderType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SliderType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderTypeList;
         
        }
        
        @Override
        public ArrayList<SliderType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SliderType> sliderTypeList = new ArrayList<SliderType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SliderType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    sliderTypeList = new ArrayList<SliderType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                sliderTypeList = new ArrayList<SliderType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("slider_type", limit, offset);
                    while (rs.next())
                    {
                        sliderTypeList.add(SliderType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SliderType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderTypeList;            
        }
        
        @Override
        public ArrayList<SliderType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SliderType> sliderTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SliderType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SliderType i = (SliderType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                sliderTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            sliderTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("slider_type", SliderType.checkColumnName(columnName), columnValue, SliderType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        sliderTypeList.add(SliderType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SliderType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderTypeList;
        } 
    
        @Override
        public int add(SliderType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SliderType.checkColumnSize(obj.getTypeName(), 100);
                SliderType.checkColumnSize(obj.getCode(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO slider_type(SliderTypeId,TypeName,Code,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getSliderTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getCode());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSliderTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SliderType update(SliderType obj)
        {
           try
            {   
                
                SliderType.checkColumnSize(obj.getTypeName(), 100);
                SliderType.checkColumnSize(obj.getCode(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_type SET com.busy.util.DatabaseColumn@44d82941=?,com.busy.util.DatabaseColumn@55394892=? WHERE SliderTypeId=?;");                    
                preparedStatement.setInt(0, obj.getSliderTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getCode());
                preparedStatement.setInt(3, obj.getSliderTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSliderTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("slider_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SliderType slider_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(SliderType slider_type)
        {
            slider_type.setSliderList(new SliderDaoImpl().findByColumn("SliderTypeId", slider_type.getSliderTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(SliderType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + obj.getSliderTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSliderTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM slider_type WHERE SliderTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM slider_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_type WHERE " + SliderType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SliderType's removeByColumn method error: " + ex.getMessage());
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
                        SliderType i = (SliderType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSliderTypeId());
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
        
                    
        public void getRelatedSliderList(SliderType slider_type)
        {           
            slider_type.setSliderList(new SliderDaoImpl().findByColumn("SliderTypeId", slider_type.getSliderTypeId().toString(),null,null));
        }        
        
                             
    }

