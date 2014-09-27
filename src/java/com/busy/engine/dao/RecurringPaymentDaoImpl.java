






















































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
    
    public class RecurringPaymentDaoImpl extends BasicConnection implements Serializable, RecurringPaymentDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public RecurringPaymentDaoImpl()
        {
            cachingEnabled = false;
        }

        public RecurringPaymentDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class RecurringPaymentCache
        {
            public static final ConcurrentLruCache<Integer, RecurringPayment> recurringPaymentCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (RecurringPayment i : findAll())
                {
                    getCache().put(i.getRecurringPaymentId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, RecurringPayment> getCache()
        {
            return RecurringPaymentCache.recurringPaymentCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, RecurringPayment> buildCache(ArrayList<RecurringPayment> recurringPaymentList)
        {        
            ConcurrentLruCache<Integer, RecurringPayment> cache = new ConcurrentLruCache<Integer, RecurringPayment>(recurringPaymentList.size() + 1000);
            for (RecurringPayment i : recurringPaymentList)
            {
                cache.put(i.getRecurringPaymentId(), i);
            }
            return cache;
        }

        private static ArrayList<RecurringPayment> findAll()
        {
            ArrayList<RecurringPayment> recurringPayment = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("recurringPayment");
                while (rs.next())
                {
                    recurringPayment.add(RecurringPayment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("RecurringPayment object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurringPayment;
        }
        
        @Override
        public RecurringPayment find(Integer id)
        {
            return findByColumn("RecurringPaymentId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<RecurringPayment> findAll(Integer limit, Integer offset)
        {
            ArrayList<RecurringPayment> recurringPaymentList = new ArrayList<RecurringPayment>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for RecurringPayment, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    recurringPaymentList = new ArrayList<RecurringPayment>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("recurring_payment", limit, offset);
                    while (rs.next())
                    {
                        recurringPaymentList.add(RecurringPayment.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("RecurringPayment object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return recurringPaymentList;
         
        }
        
        @Override
        public ArrayList<RecurringPayment> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<RecurringPayment> recurringPaymentList = new ArrayList<RecurringPayment>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for RecurringPayment, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    recurringPaymentList = new ArrayList<RecurringPayment>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            RecurringPayment recurringPayment = (RecurringPayment) e.getValue();

                            
                                getRecordById("Order", recurringPayment.getOrderId().toString());
                                recurringPayment.setOrder(Order.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object RecurringPayment method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                recurringPaymentList = new ArrayList<RecurringPayment>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("recurring_payment", limit, offset);
                    while (rs.next())
                    {
                        recurringPaymentList.add(RecurringPayment.process(rs));
                    }

                    
                    
                        for (RecurringPayment recurringPayment : recurringPaymentList)
                        {                        
                            
                                getRecordById("Order", recurringPayment.getOrderId().toString());
                                recurringPayment.setOrder(Order.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object RecurringPayment method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return recurringPaymentList;            
        }
        
        @Override
        public ArrayList<RecurringPayment> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<RecurringPayment> recurringPaymentList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for RecurringPayment(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            RecurringPayment i = (RecurringPayment) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                recurringPaymentList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            recurringPaymentList = null;
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
                    getRecordsByColumnWithLimitOrOffset("recurring_payment", RecurringPayment.checkColumnName(columnName), columnValue, RecurringPayment.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        recurringPaymentList.add(RecurringPayment.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("RecurringPayment's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return recurringPaymentList;
        } 
    
        @Override
        public int add(RecurringPayment obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO recurring_payment(RecurringPaymentId,CycleLength,CyclePeriod,TotalCycles,StartDate,OrderId,) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getRecurringPaymentId());
                preparedStatement.setInt(1, obj.getCycleLength());
                preparedStatement.setInt(2, obj.getCyclePeriod());
                preparedStatement.setInt(3, obj.getTotalCycles());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setInt(5, obj.getOrderId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from recurring_payment;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setRecurringPaymentId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public RecurringPayment update(RecurringPayment obj)
        {
           try
            {   
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE recurring_payment SET com.busy.util.DatabaseColumn@70f8ec23=?,com.busy.util.DatabaseColumn@77382ce9=?,com.busy.util.DatabaseColumn@3189af1f=?,com.busy.util.DatabaseColumn@1b66a69b=?,com.busy.util.DatabaseColumn@2c48d820=? WHERE RecurringPaymentId=?;");                    
                preparedStatement.setInt(0, obj.getRecurringPaymentId());
                preparedStatement.setInt(1, obj.getCycleLength());
                preparedStatement.setInt(2, obj.getCyclePeriod());
                preparedStatement.setInt(3, obj.getTotalCycles());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setInt(5, obj.getOrderId());
                preparedStatement.setInt(6, obj.getRecurringPaymentId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getRecurringPaymentId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("recurring_payment");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(RecurringPayment recurring_payment)
        {
            
                try
                { 
                    
                            getRecordById("Order", recurring_payment.getOrderId().toString());
                            recurring_payment.setOrder(Order.process(rs));                                       
                    
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
        public void getRelatedObjects(RecurringPayment recurring_payment)
        {
             
        }
        
        @Override
        public boolean remove(RecurringPayment obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + obj.getRecurringPaymentId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getRecurringPaymentId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + id + ";");           
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
                updateQuery("DELETE FROM recurring_payment;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM recurring_payment WHERE " + RecurringPayment.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's removeByColumn method error: " + ex.getMessage());
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
                        RecurringPayment i = (RecurringPayment) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getRecurringPaymentId());
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

