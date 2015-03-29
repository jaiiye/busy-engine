






















































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
    
    public class SliderItemDaoImpl extends BasicConnection implements Serializable, SliderItemDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SliderItemDaoImpl()
        {
            cachingEnabled = false;
        }

        public SliderItemDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SliderItemCache
        {
            public static final ConcurrentLruCache<Integer, SliderItem> sliderItemCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SliderItem i : findAll())
                {
                    getCache().put(i.getSliderItemId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SliderItem> getCache()
        {
            return SliderItemCache.sliderItemCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SliderItem> buildCache(ArrayList<SliderItem> sliderItemList)
        {        
            ConcurrentLruCache<Integer, SliderItem> cache = new ConcurrentLruCache<Integer, SliderItem>(sliderItemList.size() + 1000);
            for (SliderItem i : sliderItemList)
            {
                cache.put(i.getSliderItemId(), i);
            }
            return cache;
        }

        private static ArrayList<SliderItem> findAll()
        {
            ArrayList<SliderItem> sliderItem = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("sliderItem");
                while (rs.next())
                {
                    sliderItem.add(SliderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SliderItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return sliderItem;
        }
        
        @Override
        public SliderItem find(Integer id)
        {
            return findByColumn("SliderItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SliderItem findWithInfo(Integer id)
        {
            SliderItem sliderItem = findByColumn("SliderItemId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Slider", sliderItem.getSliderId().toString());
                    sliderItem.setSlider(Slider.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object SliderItem method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return sliderItem;
        }
        
        @Override
        public ArrayList<SliderItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<SliderItem> sliderItemList = new ArrayList<SliderItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SliderItem, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    sliderItemList = new ArrayList<SliderItem>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("slider_item", limit, offset);
                    while (rs.next())
                    {
                        sliderItemList.add(SliderItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SliderItem object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderItemList;
         
        }
        
        @Override
        public ArrayList<SliderItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SliderItem> sliderItemList = new ArrayList<SliderItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SliderItem, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    sliderItemList = new ArrayList<SliderItem>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            SliderItem sliderItem = (SliderItem) e.getValue();

                            
                                getRecordById("Slider", sliderItem.getSliderId().toString());
                                sliderItem.setSlider(Slider.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object SliderItem method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                sliderItemList = new ArrayList<SliderItem>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("slider_item", limit, offset);
                    while (rs.next())
                    {
                        sliderItemList.add(SliderItem.process(rs));
                    }

                    
                    
                        for (SliderItem sliderItem : sliderItemList)
                        {                        
                            
                                getRecordById("Slider", sliderItem.getSliderId().toString());
                                sliderItem.setSlider(Slider.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SliderItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderItemList;            
        }
        
        @Override
        public ArrayList<SliderItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SliderItem> sliderItemList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SliderItem(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SliderItem i = (SliderItem) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                sliderItemList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            sliderItemList = null;
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
                    getRecordsByColumnWithLimitOrOffset("slider_item", SliderItem.checkColumnName(columnName), columnValue, SliderItem.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        sliderItemList.add(SliderItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SliderItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderItemList;
        } 
    
        @Override
        public int add(SliderItem obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SliderItem.checkColumnSize(obj.getTitle(), 255);
                SliderItem.checkColumnSize(obj.getDescription(), 255);
                SliderItem.checkColumnSize(obj.getUrl(), 255);
                SliderItem.checkColumnSize(obj.getImageName(), 100);
                SliderItem.checkColumnSize(obj.getAlternateText(), 255);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO slider_item(Title,Description,Url,ImageName,AlternateText,Rank,SliderId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getUrl());
                preparedStatement.setString(4, obj.getImageName());
                preparedStatement.setString(5, obj.getAlternateText());
                preparedStatement.setInt(6, obj.getRank());
                preparedStatement.setInt(7, obj.getSliderId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider_item;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSliderItemId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SliderItem update(SliderItem obj)
        {
           try
            {   
                
                SliderItem.checkColumnSize(obj.getTitle(), 255);
                SliderItem.checkColumnSize(obj.getDescription(), 255);
                SliderItem.checkColumnSize(obj.getUrl(), 255);
                SliderItem.checkColumnSize(obj.getImageName(), 100);
                SliderItem.checkColumnSize(obj.getAlternateText(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider_item SET Title=?,Description=?,Url=?,ImageName=?,AlternateText=?,Rank=?,SliderId=? WHERE SliderItemId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getUrl());
                preparedStatement.setString(4, obj.getImageName());
                preparedStatement.setString(5, obj.getAlternateText());
                preparedStatement.setInt(6, obj.getRank());
                preparedStatement.setInt(7, obj.getSliderId());
                preparedStatement.setInt(8, obj.getSliderItemId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSliderItemId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("slider_item");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SliderItem slider_item)
        {
            
                try
                { 
                    
                            getRecordById("Slider", slider_item.getSliderId().toString());
                            slider_item.setSlider(Slider.process(rs));                                       
                    
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
        public void getRelatedObjects(SliderItem slider_item)
        {
             
        }
        
        @Override
        public boolean remove(SliderItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + obj.getSliderItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSliderItemId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM slider_item WHERE SliderItemId=" + id + ";");           
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
                updateQuery("DELETE FROM slider_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider_item WHERE " + SliderItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SliderItem's removeByColumn method error: " + ex.getMessage());
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
                        SliderItem i = (SliderItem) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSliderItemId());
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
        
        
            
        
        
        public void getRelatedSlider(SliderItem slider_item)
        {            
            try
            {                 
                getRecordById("Slider", slider_item.getSliderId().toString());
                slider_item.setSlider(Slider.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getSlider error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedSliderWithInfo(SliderItem slider_item)
        {            
            slider_item.setSlider(new SliderDaoImpl().findWithInfo(slider_item.getSliderId()));
        }
          
        
    }

