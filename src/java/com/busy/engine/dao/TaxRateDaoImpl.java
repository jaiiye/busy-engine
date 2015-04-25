
























































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
    
    public class TaxRateDaoImpl extends BasicConnection implements Serializable, TaxRateDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public TaxRateDaoImpl()
        {
            cachingEnabled = false;
        }

        public TaxRateDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class TaxRateCache
        {
            public static final ConcurrentLruCache<Integer, TaxRate> taxRateCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (TaxRate i : findAll())
                {
                    getCache().put(i.getTaxRateId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, TaxRate> getCache()
        {
            return TaxRateCache.taxRateCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, TaxRate> buildCache(ArrayList<TaxRate> taxRateList)
        {        
            ConcurrentLruCache<Integer, TaxRate> cache = new ConcurrentLruCache<Integer, TaxRate>(taxRateList.size() + 1000);
            for (TaxRate i : taxRateList)
            {
                cache.put(i.getTaxRateId(), i);
            }
            return cache;
        }

        private static ArrayList<TaxRate> findAll()
        {
            ArrayList<TaxRate> taxRate = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("taxRate");
                while (rs.next())
                {
                    taxRate.add(TaxRate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TaxRate object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return taxRate;
        }
        
        @Override
        public TaxRate find(Integer id)
        {
            return findByColumn("TaxRateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public TaxRate findWithInfo(Integer id)
        {
            TaxRate taxRate = findByColumn("TaxRateId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("state_province", taxRate.getStateProvinceId().toString());
                    taxRate.setStateProvince(StateProvince.process(rs));               
                
                    getRecordById("country", taxRate.getCountryId().toString());
                    taxRate.setCountry(Country.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object TaxRate method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return taxRate;
        }
        
        @Override
        public ArrayList<TaxRate> findAll(Integer limit, Integer offset)
        {
            ArrayList<TaxRate> taxRateList = new ArrayList<TaxRate>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for TaxRate, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    taxRateList = new ArrayList<TaxRate>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("tax_rate", limit, offset);
                    while (rs.next())
                    {
                        taxRateList.add(TaxRate.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TaxRate object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return taxRateList;
         
        }
        
        @Override
        public ArrayList<TaxRate> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TaxRate> taxRateList = new ArrayList<TaxRate>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for TaxRate, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    taxRateList = new ArrayList<TaxRate>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            TaxRate taxRate = (TaxRate) e.getValue();

                            
                                getRecordById("state_province", taxRate.getStateProvinceId().toString());
                                taxRate.setStateProvince(StateProvince.process(rs));               
                            
                                getRecordById("country", taxRate.getCountryId().toString());
                                taxRate.setCountry(Country.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object TaxRate method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                taxRateList = new ArrayList<TaxRate>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("tax_rate", limit, offset);
                    while (rs.next())
                    {
                        taxRateList.add(TaxRate.process(rs));
                    }

                    
                    
                        for (TaxRate taxRate : taxRateList)
                        {                        
                            
                                getRecordById("state_province", taxRate.getStateProvinceId().toString());
                                taxRate.setStateProvince(StateProvince.process(rs));               
                            
                                getRecordById("country", taxRate.getCountryId().toString());
                                taxRate.setCountry(Country.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object TaxRate method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return taxRateList;            
        }
        
        @Override
        public ArrayList<TaxRate> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TaxRate> taxRateList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for TaxRate(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            TaxRate i = (TaxRate) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                taxRateList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            taxRateList = null;
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
                    getRecordsByColumnWithLimitOrOffset("tax_rate", TaxRate.checkColumnName(columnName), columnValue, TaxRate.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        taxRateList.add(TaxRate.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TaxRate's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return taxRateList;
        } 
        
        @Override
        public ArrayList<TaxRate> findByColumns(Column... columns)
        {
            ArrayList<TaxRate> taxRateList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(TaxRate.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("tax_rate", columns);
                while (rs.next())
                {
                    taxRateList.add(TaxRate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TaxRate's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return taxRateList;
        }
    
        @Override
        public int add(TaxRate obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                TaxRate.checkColumnSize(obj.getTaxCategory(), 100);
                
                TaxRate.checkColumnSize(obj.getZipPostalCode(), 15);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO tax_rate(TaxCategory,Percentage,ZipPostalCode,StateProvinceId,CountryId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTaxCategory());
                preparedStatement.setDouble(2, obj.getPercentage());
                preparedStatement.setString(3, obj.getZipPostalCode());
                preparedStatement.setInt(4, obj.getStateProvinceId());
                preparedStatement.setInt(5, obj.getCountryId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from tax_rate;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setTaxRateId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public TaxRate update(TaxRate obj)
        {
           try
            {   
                
                TaxRate.checkColumnSize(obj.getTaxCategory(), 100);
                
                TaxRate.checkColumnSize(obj.getZipPostalCode(), 15);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tax_rate SET TaxCategory=?,Percentage=?,ZipPostalCode=?,StateProvinceId=?,CountryId=? WHERE TaxRateId=?;");                    
                preparedStatement.setString(1, obj.getTaxCategory());
                preparedStatement.setDouble(2, obj.getPercentage());
                preparedStatement.setString(3, obj.getZipPostalCode());
                preparedStatement.setInt(4, obj.getStateProvinceId());
                preparedStatement.setInt(5, obj.getCountryId());
                preparedStatement.setInt(6, obj.getTaxRateId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getTaxRateId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("tax_rate");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(TaxRate tax_rate)
        {
            
                try
                { 
                    
                            getRecordById("state_province", tax_rate.getStateProvinceId().toString());
                            tax_rate.setStateProvince(StateProvince.process(rs));                                       
                    
                            getRecordById("country", tax_rate.getCountryId().toString());
                            tax_rate.setCountry(Country.process(rs));                                       
                    
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
        public void getRelatedObjects(TaxRate tax_rate)
        {
             
        }
        
        @Override
        public boolean remove(TaxRate obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + obj.getTaxRateId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getTaxRateId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + id + ";");           
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
                updateQuery("DELETE FROM tax_rate;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM tax_rate WHERE " + TaxRate.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's removeByColumn method error: " + ex.getMessage());
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
                        TaxRate i = (TaxRate) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getTaxRateId());
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
        
        
            
        
        
        public void getRelatedStateProvince(TaxRate tax_rate)
        {            
            try
            {                 
                getRecordById("StateProvince", tax_rate.getStateProvinceId().toString());
                tax_rate.setStateProvince(StateProvince.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getStateProvince error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedCountry(TaxRate tax_rate)
        {            
            try
            {                 
                getRecordById("Country", tax_rate.getCountryId().toString());
                tax_rate.setCountry(Country.process(rs));                                                       
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
          
        
                
        
        public void getRelatedStateProvinceWithInfo(TaxRate tax_rate)
        {            
            tax_rate.setStateProvince(new StateProvinceDaoImpl().findWithInfo(tax_rate.getStateProvinceId()));
        }
        
        public void getRelatedCountryWithInfo(TaxRate tax_rate)
        {            
            tax_rate.setCountry(new CountryDaoImpl().findWithInfo(tax_rate.getCountryId()));
        }
          
        
    }

