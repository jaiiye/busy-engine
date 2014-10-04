






















































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
    
    public class ShippingDaoImpl extends BasicConnection implements Serializable, ShippingDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ShippingDaoImpl()
        {
            cachingEnabled = false;
        }

        public ShippingDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ShippingCache
        {
            public static final ConcurrentLruCache<Integer, Shipping> shippingCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Shipping i : findAll())
                {
                    getCache().put(i.getShippingId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Shipping> getCache()
        {
            return ShippingCache.shippingCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Shipping> buildCache(ArrayList<Shipping> shippingList)
        {        
            ConcurrentLruCache<Integer, Shipping> cache = new ConcurrentLruCache<Integer, Shipping>(shippingList.size() + 1000);
            for (Shipping i : shippingList)
            {
                cache.put(i.getShippingId(), i);
            }
            return cache;
        }

        private static ArrayList<Shipping> findAll()
        {
            ArrayList<Shipping> shipping = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("shipping");
                while (rs.next())
                {
                    shipping.add(Shipping.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Shipping object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        }
        
        @Override
        public Shipping find(Integer id)
        {
            return findByColumn("ShippingId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Shipping> findAll(Integer limit, Integer offset)
        {
            ArrayList<Shipping> shippingList = new ArrayList<Shipping>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Shipping, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    shippingList = new ArrayList<Shipping>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("shipping", limit, offset);
                    while (rs.next())
                    {
                        shippingList.add(Shipping.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Shipping object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return shippingList;
         
        }
        
        @Override
        public ArrayList<Shipping> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Shipping> shippingList = new ArrayList<Shipping>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Shipping, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    shippingList = new ArrayList<Shipping>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Shipping shipping = (Shipping) e.getValue();

                            
                                getRecordById("StateProvince", shipping.getStateProvinceId().toString());
                                shipping.setStateProvince(StateProvince.process(rs));               
                            
                                getRecordById("Country", shipping.getCountryId().toString());
                                shipping.setCountry(Country.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Shipping method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                shippingList = new ArrayList<Shipping>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("shipping", limit, offset);
                    while (rs.next())
                    {
                        shippingList.add(Shipping.process(rs));
                    }

                    
                    
                        for (Shipping shipping : shippingList)
                        {                        
                            
                                getRecordById("StateProvince", shipping.getStateProvinceId().toString());
                                shipping.setStateProvince(StateProvince.process(rs));               
                            
                                getRecordById("Country", shipping.getCountryId().toString());
                                shipping.setCountry(Country.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Shipping method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return shippingList;            
        }
        
        @Override
        public ArrayList<Shipping> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Shipping> shippingList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Shipping(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Shipping i = (Shipping) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                shippingList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            shippingList = null;
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
                    getRecordsByColumnWithLimitOrOffset("shipping", Shipping.checkColumnName(columnName), columnValue, Shipping.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        shippingList.add(Shipping.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Shipping's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return shippingList;
        } 
    
        @Override
        public int add(Shipping obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Shipping.checkColumnSize(obj.getMethodName(), 100);
                
                Shipping.checkColumnSize(obj.getUnitOfMeasure(), 100);
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO shipping(ShippingId,MethodName,Quantity,UnitOfMeasure,RatePerUnitCost,AdditionalCost,StateProvinceId,CountryId,) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getShippingId());
                preparedStatement.setString(1, obj.getMethodName());
                preparedStatement.setDouble(2, obj.getQuantity());
                preparedStatement.setString(3, obj.getUnitOfMeasure());
                preparedStatement.setDouble(4, obj.getRatePerUnitCost());
                preparedStatement.setDouble(5, obj.getAdditionalCost());
                preparedStatement.setInt(6, obj.getStateProvinceId());
                preparedStatement.setInt(7, obj.getCountryId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shipping;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setShippingId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Shipping update(Shipping obj)
        {
           try
            {   
                
                Shipping.checkColumnSize(obj.getMethodName(), 100);
                
                Shipping.checkColumnSize(obj.getUnitOfMeasure(), 100);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipping SET com.busy.util.DatabaseColumn@36e41315=?,com.busy.util.DatabaseColumn@7404f198=?,com.busy.util.DatabaseColumn@13e3c8ad=?,com.busy.util.DatabaseColumn@30a1e1cc=?,com.busy.util.DatabaseColumn@264b2076=?,com.busy.util.DatabaseColumn@75108d16=?,com.busy.util.DatabaseColumn@78bb85b9=? WHERE ShippingId=?;");                    
                preparedStatement.setInt(0, obj.getShippingId());
                preparedStatement.setString(1, obj.getMethodName());
                preparedStatement.setDouble(2, obj.getQuantity());
                preparedStatement.setString(3, obj.getUnitOfMeasure());
                preparedStatement.setDouble(4, obj.getRatePerUnitCost());
                preparedStatement.setDouble(5, obj.getAdditionalCost());
                preparedStatement.setInt(6, obj.getStateProvinceId());
                preparedStatement.setInt(7, obj.getCountryId());
                preparedStatement.setInt(8, obj.getShippingId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getShippingId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("shipping");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Shipping shipping)
        {
            
                try
                { 
                    
                            getRecordById("StateProvince", shipping.getStateProvinceId().toString());
                            shipping.setStateProvince(StateProvince.process(rs));                                       
                    
                            getRecordById("Country", shipping.getCountryId().toString());
                            shipping.setCountry(Country.process(rs));                                       
                    
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
        public void getRelatedObjects(Shipping shipping)
        {
            shipping.setOrderList(new OrderDaoImpl().findByColumn("ShippingId", shipping.getShippingId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Shipping obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + obj.getShippingId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getShippingId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + id + ";");           
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
                updateQuery("DELETE FROM shipping;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipping WHERE " + Shipping.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's removeByColumn method error: " + ex.getMessage());
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
                        Shipping i = (Shipping) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getShippingId());
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
        
                    
        public void getRelatedOrderList(Shipping shipping)
        {           
            shipping.setOrderList(new OrderDaoImpl().findByColumn("ShippingId", shipping.getShippingId().toString(),null,null));
        }        
        
                             
    }

