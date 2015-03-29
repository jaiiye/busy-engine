






















































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
    
    public class StateProvinceDaoImpl extends BasicConnection implements Serializable, StateProvinceDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public StateProvinceDaoImpl()
        {
            cachingEnabled = false;
        }

        public StateProvinceDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class StateProvinceCache
        {
            public static final ConcurrentLruCache<Integer, StateProvince> stateProvinceCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (StateProvince i : findAll())
                {
                    getCache().put(i.getStateProvinceId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, StateProvince> getCache()
        {
            return StateProvinceCache.stateProvinceCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, StateProvince> buildCache(ArrayList<StateProvince> stateProvinceList)
        {        
            ConcurrentLruCache<Integer, StateProvince> cache = new ConcurrentLruCache<Integer, StateProvince>(stateProvinceList.size() + 1000);
            for (StateProvince i : stateProvinceList)
            {
                cache.put(i.getStateProvinceId(), i);
            }
            return cache;
        }

        private static ArrayList<StateProvince> findAll()
        {
            ArrayList<StateProvince> stateProvince = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("stateProvince");
                while (rs.next())
                {
                    stateProvince.add(StateProvince.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("StateProvince object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return stateProvince;
        }
        
        @Override
        public StateProvince find(Integer id)
        {
            return findByColumn("StateProvinceId", id.toString(), null, null).get(0);
        }
        
        @Override
        public StateProvince findWithInfo(Integer id)
        {
            StateProvince stateProvince = findByColumn("StateProvinceId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Country", stateProvince.getCountryId().toString());
                    stateProvince.setCountry(Country.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object StateProvince method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return stateProvince;
        }
        
        @Override
        public ArrayList<StateProvince> findAll(Integer limit, Integer offset)
        {
            ArrayList<StateProvince> stateProvinceList = new ArrayList<StateProvince>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for StateProvince, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    stateProvinceList = new ArrayList<StateProvince>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("state_province", limit, offset);
                    while (rs.next())
                    {
                        stateProvinceList.add(StateProvince.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("StateProvince object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return stateProvinceList;
         
        }
        
        @Override
        public ArrayList<StateProvince> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<StateProvince> stateProvinceList = new ArrayList<StateProvince>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for StateProvince, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    stateProvinceList = new ArrayList<StateProvince>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            StateProvince stateProvince = (StateProvince) e.getValue();

                            
                                getRecordById("Country", stateProvince.getCountryId().toString());
                                stateProvince.setCountry(Country.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object StateProvince method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                stateProvinceList = new ArrayList<StateProvince>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("state_province", limit, offset);
                    while (rs.next())
                    {
                        stateProvinceList.add(StateProvince.process(rs));
                    }

                    
                    
                        for (StateProvince stateProvince : stateProvinceList)
                        {                        
                            
                                getRecordById("Country", stateProvince.getCountryId().toString());
                                stateProvince.setCountry(Country.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object StateProvince method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return stateProvinceList;            
        }
        
        @Override
        public ArrayList<StateProvince> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<StateProvince> stateProvinceList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for StateProvince(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            StateProvince i = (StateProvince) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                stateProvinceList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            stateProvinceList = null;
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
                    getRecordsByColumnWithLimitOrOffset("state_province", StateProvince.checkColumnName(columnName), columnValue, StateProvince.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        stateProvinceList.add(StateProvince.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("StateProvince's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return stateProvinceList;
        } 
    
        @Override
        public int add(StateProvince obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                StateProvince.checkColumnSize(obj.getName(), 100);
                StateProvince.checkColumnSize(obj.getAbbreviation(), 10);
                
                  

                openConnection();
                prepareStatement("INSERT INTO state_province(Name,Abbreviation,CountryId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getAbbreviation());
                preparedStatement.setInt(3, obj.getCountryId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from state_province;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setStateProvinceId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public StateProvince update(StateProvince obj)
        {
           try
            {   
                
                StateProvince.checkColumnSize(obj.getName(), 100);
                StateProvince.checkColumnSize(obj.getAbbreviation(), 10);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE state_province SET Name=?,Abbreviation=?,CountryId=? WHERE StateProvinceId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getAbbreviation());
                preparedStatement.setInt(3, obj.getCountryId());
                preparedStatement.setInt(4, obj.getStateProvinceId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getStateProvinceId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("state_province");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(StateProvince state_province)
        {
            
                try
                { 
                    
                            getRecordById("Country", state_province.getCountryId().toString());
                            state_province.setCountry(Country.process(rs));                                       
                    
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
        public void getRelatedObjects(StateProvince state_province)
        {
            state_province.setShippingList(new ShippingDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
state_province.setTaxRateList(new TaxRateDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(StateProvince obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + obj.getStateProvinceId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getStateProvinceId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + id + ";");           
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
                updateQuery("DELETE FROM state_province;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM state_province WHERE " + StateProvince.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's removeByColumn method error: " + ex.getMessage());
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
                        StateProvince i = (StateProvince) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getStateProvinceId());
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
        
                    
        public void getRelatedShippingList(StateProvince state_province)
        {           
            state_province.setShippingList(new ShippingDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
        }        
                    
        public void getRelatedTaxRateList(StateProvince state_province)
        {           
            state_province.setTaxRateList(new TaxRateDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedCountry(StateProvince state_province)
        {            
            try
            {                 
                getRecordById("Country", state_province.getCountryId().toString());
                state_province.setCountry(Country.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getCountry error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedCountryWithInfo(StateProvince state_province)
        {            
            state_province.setCountry(new CountryDaoImpl().findWithInfo(state_province.getCountryId()));
        }
          
        
    }

