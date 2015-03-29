






















































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
    
    public class CountryDaoImpl extends BasicConnection implements Serializable, CountryDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public CountryDaoImpl()
        {
            cachingEnabled = false;
        }

        public CountryDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class CountryCache
        {
            public static final ConcurrentLruCache<Integer, Country> countryCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Country i : findAll())
                {
                    getCache().put(i.getCountryId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Country> getCache()
        {
            return CountryCache.countryCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Country> buildCache(ArrayList<Country> countryList)
        {        
            ConcurrentLruCache<Integer, Country> cache = new ConcurrentLruCache<Integer, Country>(countryList.size() + 1000);
            for (Country i : countryList)
            {
                cache.put(i.getCountryId(), i);
            }
            return cache;
        }

        private static ArrayList<Country> findAll()
        {
            ArrayList<Country> country = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("country");
                while (rs.next())
                {
                    country.add(Country.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Country object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        }
        
        @Override
        public Country find(Integer id)
        {
            return findByColumn("CountryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Country findWithInfo(Integer id)
        {
            Country country = findByColumn("CountryId", id.toString(), null, null).get(0);
            
            
            
            return country;
        }
        
        @Override
        public ArrayList<Country> findAll(Integer limit, Integer offset)
        {
            ArrayList<Country> countryList = new ArrayList<Country>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Country, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    countryList = new ArrayList<Country>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("country", limit, offset);
                    while (rs.next())
                    {
                        countryList.add(Country.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Country object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return countryList;
         
        }
        
        @Override
        public ArrayList<Country> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Country> countryList = new ArrayList<Country>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Country, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    countryList = new ArrayList<Country>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                countryList = new ArrayList<Country>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("country", limit, offset);
                    while (rs.next())
                    {
                        countryList.add(Country.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Country method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return countryList;            
        }
        
        @Override
        public ArrayList<Country> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Country> countryList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Country(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Country i = (Country) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                countryList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            countryList = null;
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
                    getRecordsByColumnWithLimitOrOffset("country", Country.checkColumnName(columnName), columnValue, Country.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        countryList.add(Country.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Country's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return countryList;
        } 
    
        @Override
        public int add(Country obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Country.checkColumnSize(obj.getName(), 100);
                Country.checkColumnSize(obj.getIsoCode(), 5);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO country(Name,IsoCode,IsoNumber,HasVat) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getIsoCode());
                preparedStatement.setInt(3, obj.getIsoNumber());
                preparedStatement.setInt(4, obj.getHasVat());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from country;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Country's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setCountryId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Country update(Country obj)
        {
           try
            {   
                
                Country.checkColumnSize(obj.getName(), 100);
                Country.checkColumnSize(obj.getIsoCode(), 5);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE country SET Name=?,IsoCode=?,IsoNumber=?,HasVat=? WHERE CountryId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getIsoCode());
                preparedStatement.setInt(3, obj.getIsoNumber());
                preparedStatement.setInt(4, obj.getHasVat());
                preparedStatement.setInt(5, obj.getCountryId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getCountryId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Country's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("country");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Country country)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Country country)
        {
            country.setShippingList(new ShippingDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
country.setStateProvinceList(new StateProvinceDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
country.setTaxRateList(new TaxRateDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Country obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM country WHERE CountryId=" + obj.getCountryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Country's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getCountryId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM country WHERE CountryId=" + id + ";");           
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
                updateQuery("DELETE FROM country;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Country's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM country WHERE " + Country.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Country's removeByColumn method error: " + ex.getMessage());
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
                        Country i = (Country) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getCountryId());
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
        
                    
        public void getRelatedShippingList(Country country)
        {           
            country.setShippingList(new ShippingDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
        }        
                    
        public void getRelatedStateProvinceList(Country country)
        {           
            country.setStateProvinceList(new StateProvinceDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
        }        
                    
        public void getRelatedTaxRateList(Country country)
        {           
            country.setTaxRateList(new TaxRateDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

