
























































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
    
    public class SliderDaoImpl extends BasicConnection implements Serializable, SliderDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SliderDaoImpl()
        {
            cachingEnabled = false;
        }

        public SliderDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SliderCache
        {
            public static final ConcurrentLruCache<Integer, Slider> sliderCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Slider i : findAll())
                {
                    getCache().put(i.getSliderId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Slider> getCache()
        {
            return SliderCache.sliderCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Slider> buildCache(ArrayList<Slider> sliderList)
        {        
            ConcurrentLruCache<Integer, Slider> cache = new ConcurrentLruCache<Integer, Slider>(sliderList.size() + 1000);
            for (Slider i : sliderList)
            {
                cache.put(i.getSliderId(), i);
            }
            return cache;
        }

        private static ArrayList<Slider> findAll()
        {
            ArrayList<Slider> slider = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("slider");
                while (rs.next())
                {
                    slider.add(Slider.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Slider object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return slider;
        }
        
        @Override
        public Slider find(Integer id)
        {
            return findByColumn("SliderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Slider findWithInfo(Integer id)
        {
            Slider slider = findByColumn("SliderId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("slider_type", slider.getSliderTypeId().toString());
                    slider.setSliderType(SliderType.process(rs));               
                
                    getRecordById("form", slider.getFormId().toString());
                    slider.setForm(Form.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Slider method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return slider;
        }
        
        @Override
        public ArrayList<Slider> findAll(Integer limit, Integer offset)
        {
            ArrayList<Slider> sliderList = new ArrayList<Slider>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Slider, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    sliderList = new ArrayList<Slider>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("slider", limit, offset);
                    while (rs.next())
                    {
                        sliderList.add(Slider.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Slider object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderList;
         
        }
        
        @Override
        public ArrayList<Slider> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Slider> sliderList = new ArrayList<Slider>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Slider, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    sliderList = new ArrayList<Slider>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Slider slider = (Slider) e.getValue();

                            
                                getRecordById("slider_type", slider.getSliderTypeId().toString());
                                slider.setSliderType(SliderType.process(rs));               
                            
                                getRecordById("form", slider.getFormId().toString());
                                slider.setForm(Form.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Slider method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                sliderList = new ArrayList<Slider>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("slider", limit, offset);
                    while (rs.next())
                    {
                        sliderList.add(Slider.process(rs));
                    }

                    
                    
                        for (Slider slider : sliderList)
                        {                        
                            
                                getRecordById("slider_type", slider.getSliderTypeId().toString());
                                slider.setSliderType(SliderType.process(rs));               
                            
                                getRecordById("form", slider.getFormId().toString());
                                slider.setForm(Form.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Slider method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderList;            
        }
        
        @Override
        public ArrayList<Slider> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Slider> sliderList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Slider(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Slider i = (Slider) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                sliderList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            sliderList = null;
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
                    getRecordsByColumnWithLimitOrOffset("slider", Slider.checkColumnName(columnName), columnValue, Slider.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        sliderList.add(Slider.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Slider's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sliderList;
        } 
        
        @Override
        public ArrayList<Slider> findByColumns(Column... columns)
        {
            ArrayList<Slider> sliderList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(Slider.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("slider", columns);
                while (rs.next())
                {
                    sliderList.add(Slider.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Slider's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return sliderList;
        }
    
        @Override
        public int add(Slider obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Slider.checkColumnSize(obj.getSliderName(), 100);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO slider(SliderName,SliderTypeId,FormId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getSliderName());
                preparedStatement.setInt(2, obj.getSliderTypeId());
                preparedStatement.setInt(3, obj.getFormId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from slider;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Slider's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSliderId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Slider update(Slider obj)
        {
           try
            {   
                
                Slider.checkColumnSize(obj.getSliderName(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE slider SET SliderName=?,SliderTypeId=?,FormId=? WHERE SliderId=?;");                    
                preparedStatement.setString(1, obj.getSliderName());
                preparedStatement.setInt(2, obj.getSliderTypeId());
                preparedStatement.setInt(3, obj.getFormId());
                preparedStatement.setInt(4, obj.getSliderId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSliderId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Slider's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("slider");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Slider slider)
        {
            
                try
                { 
                    
                            getRecordById("slider_type", slider.getSliderTypeId().toString());
                            slider.setSliderType(SliderType.process(rs));                                       
                    
                            getRecordById("form", slider.getFormId().toString());
                            slider.setForm(Form.process(rs));                                       
                    
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
        public void getRelatedObjects(Slider slider)
        {
            slider.setPageList(new PageDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
slider.setSliderItemList(new SliderItemDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Slider obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM slider WHERE SliderId=" + obj.getSliderId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Slider's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSliderId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM slider WHERE SliderId=" + id + ";");           
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
                updateQuery("DELETE FROM slider;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Slider's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM slider WHERE " + Slider.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Slider's removeByColumn method error: " + ex.getMessage());
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
                        Slider i = (Slider) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSliderId());
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
        
                    
        public void getRelatedPageList(Slider slider)
        {           
            slider.setPageList(new PageDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
        }        
                    
        public void getRelatedSliderItemList(Slider slider)
        {           
            slider.setSliderItemList(new SliderItemDaoImpl().findByColumn("SliderId", slider.getSliderId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedSliderType(Slider slider)
        {            
            try
            {                 
                getRecordById("SliderType", slider.getSliderTypeId().toString());
                slider.setSliderType(SliderType.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getSliderType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedForm(Slider slider)
        {            
            try
            {                 
                getRecordById("Form", slider.getFormId().toString());
                slider.setForm(Form.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getForm error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedSliderTypeWithInfo(Slider slider)
        {            
            slider.setSliderType(new SliderTypeDaoImpl().findWithInfo(slider.getSliderTypeId()));
        }
        
        public void getRelatedFormWithInfo(Slider slider)
        {            
            slider.setForm(new FormDaoImpl().findWithInfo(slider.getFormId()));
        }
          
        
    }

