






















































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
    
    public class OptionAvailabilityDaoImpl extends BasicConnection implements Serializable, OptionAvailabilityDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public OptionAvailabilityDaoImpl()
        {
            cachingEnabled = false;
        }

        public OptionAvailabilityDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class OptionAvailabilityCache
        {
            public static final ConcurrentLruCache<Integer, OptionAvailability> optionAvailabilityCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (OptionAvailability i : findAll())
                {
                    getCache().put(i.getOptionAvailabilityId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, OptionAvailability> getCache()
        {
            return OptionAvailabilityCache.optionAvailabilityCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, OptionAvailability> buildCache(ArrayList<OptionAvailability> optionAvailabilityList)
        {        
            ConcurrentLruCache<Integer, OptionAvailability> cache = new ConcurrentLruCache<Integer, OptionAvailability>(optionAvailabilityList.size() + 1000);
            for (OptionAvailability i : optionAvailabilityList)
            {
                cache.put(i.getOptionAvailabilityId(), i);
            }
            return cache;
        }

        private static ArrayList<OptionAvailability> findAll()
        {
            ArrayList<OptionAvailability> optionAvailability = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("optionAvailability");
                while (rs.next())
                {
                    optionAvailability.add(OptionAvailability.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OptionAvailability object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return optionAvailability;
        }
        
        @Override
        public OptionAvailability find(Integer id)
        {
            return findByColumn("OptionAvailabilityId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<OptionAvailability> findAll(Integer limit, Integer offset)
        {
            ArrayList<OptionAvailability> optionAvailabilityList = new ArrayList<OptionAvailability>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for OptionAvailability, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    optionAvailabilityList = new ArrayList<OptionAvailability>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("option_availability", limit, offset);
                    while (rs.next())
                    {
                        optionAvailabilityList.add(OptionAvailability.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("OptionAvailability object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return optionAvailabilityList;
         
        }
        
        @Override
        public ArrayList<OptionAvailability> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<OptionAvailability> optionAvailabilityList = new ArrayList<OptionAvailability>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for OptionAvailability, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    optionAvailabilityList = new ArrayList<OptionAvailability>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            OptionAvailability optionAvailability = (OptionAvailability) e.getValue();

                            
                                getRecordById("Item", optionAvailability.getItemId().toString());
                                optionAvailability.setItem(Item.process(rs));               
                            
                                getRecordById("ItemOption", optionAvailability.getItemOptionId().toString());
                                optionAvailability.setItemOption(ItemOption.process(rs));               
                            
                                getRecordById("ItemAvailability", optionAvailability.getItemAvailabilityId().toString());
                                optionAvailability.setItemAvailability(ItemAvailability.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object OptionAvailability method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                optionAvailabilityList = new ArrayList<OptionAvailability>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("option_availability", limit, offset);
                    while (rs.next())
                    {
                        optionAvailabilityList.add(OptionAvailability.process(rs));
                    }

                    
                    
                        for (OptionAvailability optionAvailability : optionAvailabilityList)
                        {                        
                            
                                getRecordById("Item", optionAvailability.getItemId().toString());
                                optionAvailability.setItem(Item.process(rs));               
                            
                                getRecordById("ItemOption", optionAvailability.getItemOptionId().toString());
                                optionAvailability.setItemOption(ItemOption.process(rs));               
                            
                                getRecordById("ItemAvailability", optionAvailability.getItemAvailabilityId().toString());
                                optionAvailability.setItemAvailability(ItemAvailability.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object OptionAvailability method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return optionAvailabilityList;            
        }
        
        @Override
        public ArrayList<OptionAvailability> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<OptionAvailability> optionAvailabilityList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for OptionAvailability(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            OptionAvailability i = (OptionAvailability) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                optionAvailabilityList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            optionAvailabilityList = null;
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
                    getRecordsByColumnWithLimitOrOffset("option_availability", OptionAvailability.checkColumnName(columnName), columnValue, OptionAvailability.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        optionAvailabilityList.add(OptionAvailability.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("OptionAvailability's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return optionAvailabilityList;
        } 
    
        @Override
        public int add(OptionAvailability obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO option_availability(OptionAvailabilityId,ItemId,ItemOptionId,ItemAvailabilityId,AvailableQuantity,Price,AvailableFrom,AvailableTo,MaximumQuantity,) VALUES (?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getOptionAvailabilityId());
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getItemOptionId());
                preparedStatement.setInt(3, obj.getItemAvailabilityId());
                preparedStatement.setInt(4, obj.getAvailableQuantity());
                preparedStatement.setDouble(5, obj.getPrice());
                preparedStatement.setDate(6, new java.sql.Date(obj.getAvailableFrom().getTime()));
                preparedStatement.setDate(7, new java.sql.Date(obj.getAvailableTo().getTime()));
                preparedStatement.setInt(8, obj.getMaximumQuantity());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from option_availability;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setOptionAvailabilityId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public OptionAvailability update(OptionAvailability obj)
        {
           try
            {   
                
                
                
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE option_availability SET com.busy.util.DatabaseColumn@25c7a560=?,com.busy.util.DatabaseColumn@2c42601b=?,com.busy.util.DatabaseColumn@aa6baac=?,com.busy.util.DatabaseColumn@3e610101=?,com.busy.util.DatabaseColumn@26954f08=?,com.busy.util.DatabaseColumn@19e8b179=?,com.busy.util.DatabaseColumn@26a41b32=?,com.busy.util.DatabaseColumn@10fb7fee=? WHERE OptionAvailabilityId=?;");                    
                preparedStatement.setInt(0, obj.getOptionAvailabilityId());
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getItemOptionId());
                preparedStatement.setInt(3, obj.getItemAvailabilityId());
                preparedStatement.setInt(4, obj.getAvailableQuantity());
                preparedStatement.setDouble(5, obj.getPrice());
                preparedStatement.setDate(6, new java.sql.Date(obj.getAvailableFrom().getTime()));
                preparedStatement.setDate(7, new java.sql.Date(obj.getAvailableTo().getTime()));
                preparedStatement.setInt(8, obj.getMaximumQuantity());
                preparedStatement.setInt(9, obj.getOptionAvailabilityId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getOptionAvailabilityId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("option_availability");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(OptionAvailability option_availability)
        {
            
                try
                { 
                    
                            getRecordById("Item", option_availability.getItemId().toString());
                            option_availability.setItem(Item.process(rs));                                       
                    
                            getRecordById("ItemOption", option_availability.getItemOptionId().toString());
                            option_availability.setItemOption(ItemOption.process(rs));                                       
                    
                            getRecordById("ItemAvailability", option_availability.getItemAvailabilityId().toString());
                            option_availability.setItemAvailability(ItemAvailability.process(rs));                                       
                    
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
        public void getRelatedObjects(OptionAvailability option_availability)
        {
             
        }
        
        @Override
        public boolean remove(OptionAvailability obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM option_availability WHERE OptionAvailabilityId=" + obj.getOptionAvailabilityId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getOptionAvailabilityId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM option_availability WHERE OptionAvailabilityId=" + id + ";");           
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
                updateQuery("DELETE FROM option_availability;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM option_availability WHERE " + OptionAvailability.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's removeByColumn method error: " + ex.getMessage());
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
                        OptionAvailability i = (OptionAvailability) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getOptionAvailabilityId());
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
        
        
                             
    }

