






















































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
    
    public class PaypalDaoImpl extends BasicConnection implements Serializable, PaypalDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public PaypalDaoImpl()
        {
            cachingEnabled = false;
        }

        public PaypalDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class PaypalCache
        {
            public static final ConcurrentLruCache<Integer, Paypal> paypalCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Paypal i : findAll())
                {
                    getCache().put(i.getPaypalId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Paypal> getCache()
        {
            return PaypalCache.paypalCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Paypal> buildCache(ArrayList<Paypal> paypalList)
        {        
            ConcurrentLruCache<Integer, Paypal> cache = new ConcurrentLruCache<Integer, Paypal>(paypalList.size() + 1000);
            for (Paypal i : paypalList)
            {
                cache.put(i.getPaypalId(), i);
            }
            return cache;
        }

        private static ArrayList<Paypal> findAll()
        {
            ArrayList<Paypal> paypal = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("paypal");
                while (rs.next())
                {
                    paypal.add(Paypal.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Paypal object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        }
        
        @Override
        public Paypal find(Integer id)
        {
            return findByColumn("PaypalId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Paypal findWithInfo(Integer id)
        {
            Paypal paypal = findByColumn("PaypalId", id.toString(), null, null).get(0);
            
            
            
            return paypal;
        }
        
        @Override
        public ArrayList<Paypal> findAll(Integer limit, Integer offset)
        {
            ArrayList<Paypal> paypalList = new ArrayList<Paypal>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Paypal, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    paypalList = new ArrayList<Paypal>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("paypal", limit, offset);
                    while (rs.next())
                    {
                        paypalList.add(Paypal.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Paypal object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return paypalList;
         
        }
        
        @Override
        public ArrayList<Paypal> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Paypal> paypalList = new ArrayList<Paypal>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Paypal, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    paypalList = new ArrayList<Paypal>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                paypalList = new ArrayList<Paypal>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("paypal", limit, offset);
                    while (rs.next())
                    {
                        paypalList.add(Paypal.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Paypal method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return paypalList;            
        }
        
        @Override
        public ArrayList<Paypal> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Paypal> paypalList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Paypal(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Paypal i = (Paypal) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                paypalList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            paypalList = null;
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
                    getRecordsByColumnWithLimitOrOffset("paypal", Paypal.checkColumnName(columnName), columnValue, Paypal.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        paypalList.add(Paypal.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Paypal's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return paypalList;
        } 
    
        @Override
        public int add(Paypal obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Paypal.checkColumnSize(obj.getPayPalUrl(), 95);
                Paypal.checkColumnSize(obj.getCurrencyCode(), 5);
                Paypal.checkColumnSize(obj.getApiUsername(), 80);
                Paypal.checkColumnSize(obj.getApiPassword(), 25);
                Paypal.checkColumnSize(obj.getApiSignature(), 65);
                Paypal.checkColumnSize(obj.getApiEndpoint(), 125);
                
                Paypal.checkColumnSize(obj.getReturnUrl(), 255);
                Paypal.checkColumnSize(obj.getCancelUrl(), 255);
                Paypal.checkColumnSize(obj.getPaymentType(), 15);
                Paypal.checkColumnSize(obj.getEnvironment(), 15);
                  

                openConnection();
                prepareStatement("INSERT INTO paypal(PayPalUrl,CurrencyCode,ApiUsername,ApiPassword,ApiSignature,ApiEndpoint,ActiveProfile,ReturnUrl,CancelUrl,PaymentType,Environment) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getPayPalUrl());
                preparedStatement.setString(2, obj.getCurrencyCode());
                preparedStatement.setString(3, obj.getApiUsername());
                preparedStatement.setString(4, obj.getApiPassword());
                preparedStatement.setString(5, obj.getApiSignature());
                preparedStatement.setString(6, obj.getApiEndpoint());
                preparedStatement.setBoolean(7, obj.getActiveProfile());
                preparedStatement.setString(8, obj.getReturnUrl());
                preparedStatement.setString(9, obj.getCancelUrl());
                preparedStatement.setString(10, obj.getPaymentType());
                preparedStatement.setString(11, obj.getEnvironment());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from paypal;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setPaypalId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Paypal update(Paypal obj)
        {
           try
            {   
                
                Paypal.checkColumnSize(obj.getPayPalUrl(), 95);
                Paypal.checkColumnSize(obj.getCurrencyCode(), 5);
                Paypal.checkColumnSize(obj.getApiUsername(), 80);
                Paypal.checkColumnSize(obj.getApiPassword(), 25);
                Paypal.checkColumnSize(obj.getApiSignature(), 65);
                Paypal.checkColumnSize(obj.getApiEndpoint(), 125);
                
                Paypal.checkColumnSize(obj.getReturnUrl(), 255);
                Paypal.checkColumnSize(obj.getCancelUrl(), 255);
                Paypal.checkColumnSize(obj.getPaymentType(), 15);
                Paypal.checkColumnSize(obj.getEnvironment(), 15);
                                  
                openConnection();                           
                prepareStatement("UPDATE paypal SET PayPalUrl=?,CurrencyCode=?,ApiUsername=?,ApiPassword=?,ApiSignature=?,ApiEndpoint=?,ActiveProfile=?,ReturnUrl=?,CancelUrl=?,PaymentType=?,Environment=? WHERE PaypalId=?;");                    
                preparedStatement.setString(1, obj.getPayPalUrl());
                preparedStatement.setString(2, obj.getCurrencyCode());
                preparedStatement.setString(3, obj.getApiUsername());
                preparedStatement.setString(4, obj.getApiPassword());
                preparedStatement.setString(5, obj.getApiSignature());
                preparedStatement.setString(6, obj.getApiEndpoint());
                preparedStatement.setBoolean(7, obj.getActiveProfile());
                preparedStatement.setString(8, obj.getReturnUrl());
                preparedStatement.setString(9, obj.getCancelUrl());
                preparedStatement.setString(10, obj.getPaymentType());
                preparedStatement.setString(11, obj.getEnvironment());
                preparedStatement.setInt(12, obj.getPaypalId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getPaypalId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("paypal");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Paypal paypal)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Paypal paypal)
        {
             
        }
        
        @Override
        public boolean remove(Paypal obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + obj.getPaypalId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getPaypalId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + id + ";");           
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
                updateQuery("DELETE FROM paypal;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM paypal WHERE " + Paypal.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's removeByColumn method error: " + ex.getMessage());
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
                        Paypal i = (Paypal) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getPaypalId());
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

