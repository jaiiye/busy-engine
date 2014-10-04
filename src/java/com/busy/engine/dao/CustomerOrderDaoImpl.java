






















































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
    
    public class CustomerOrderDaoImpl extends BasicConnection implements Serializable, CustomerOrderDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public CustomerOrderDaoImpl()
        {
            cachingEnabled = false;
        }

        public CustomerOrderDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class CustomerOrderCache
        {
            public static final ConcurrentLruCache<Integer, CustomerOrder> customerOrderCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (CustomerOrder i : findAll())
                {
                    getCache().put(i.getCustomerOrderId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, CustomerOrder> getCache()
        {
            return CustomerOrderCache.customerOrderCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, CustomerOrder> buildCache(ArrayList<CustomerOrder> customerOrderList)
        {        
            ConcurrentLruCache<Integer, CustomerOrder> cache = new ConcurrentLruCache<Integer, CustomerOrder>(customerOrderList.size() + 1000);
            for (CustomerOrder i : customerOrderList)
            {
                cache.put(i.getCustomerOrderId(), i);
            }
            return cache;
        }

        private static ArrayList<CustomerOrder> findAll()
        {
            ArrayList<CustomerOrder> customerOrder = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("customerOrder");
                while (rs.next())
                {
                    customerOrder.add(CustomerOrder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("CustomerOrder object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customerOrder;
        }
        
        @Override
        public CustomerOrder find(Integer id)
        {
            return findByColumn("CustomerOrderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<CustomerOrder> findAll(Integer limit, Integer offset)
        {
            ArrayList<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for CustomerOrder, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    customerOrderList = new ArrayList<CustomerOrder>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("customer_order", limit, offset);
                    while (rs.next())
                    {
                        customerOrderList.add(CustomerOrder.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("CustomerOrder object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return customerOrderList;
         
        }
        
        @Override
        public ArrayList<CustomerOrder> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for CustomerOrder, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    customerOrderList = new ArrayList<CustomerOrder>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            CustomerOrder customerOrder = (CustomerOrder) e.getValue();

                            
                                getRecordById("Customer", customerOrder.getCustomerId().toString());
                                customerOrder.setCustomer(Customer.process(rs));               
                            
                                getRecordById("Order", customerOrder.getOrderId().toString());
                                customerOrder.setOrder(Order.process(rs));               
                            
                                getRecordById("Discount", customerOrder.getDiscountId().toString());
                                customerOrder.setDiscount(Discount.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object CustomerOrder method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                customerOrderList = new ArrayList<CustomerOrder>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("customer_order", limit, offset);
                    while (rs.next())
                    {
                        customerOrderList.add(CustomerOrder.process(rs));
                    }

                    
                    
                        for (CustomerOrder customerOrder : customerOrderList)
                        {                        
                            
                                getRecordById("Customer", customerOrder.getCustomerId().toString());
                                customerOrder.setCustomer(Customer.process(rs));               
                            
                                getRecordById("Order", customerOrder.getOrderId().toString());
                                customerOrder.setOrder(Order.process(rs));               
                            
                                getRecordById("Discount", customerOrder.getDiscountId().toString());
                                customerOrder.setDiscount(Discount.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object CustomerOrder method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return customerOrderList;            
        }
        
        @Override
        public ArrayList<CustomerOrder> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<CustomerOrder> customerOrderList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for CustomerOrder(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            CustomerOrder i = (CustomerOrder) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                customerOrderList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            customerOrderList = null;
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
                    getRecordsByColumnWithLimitOrOffset("customer_order", CustomerOrder.checkColumnName(columnName), columnValue, CustomerOrder.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        customerOrderList.add(CustomerOrder.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("CustomerOrder's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return customerOrderList;
        } 
    
        @Override
        public int add(CustomerOrder obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                CustomerOrder.checkColumnSize(obj.getCustomerIp(), 100);
                  

                openConnection();
                prepareStatement("INSERT INTO customer_order(CustomerOrderId,CustomerId,OrderId,DiscountId,CustomerIp,) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getCustomerOrderId());
                preparedStatement.setInt(1, obj.getCustomerId());
                preparedStatement.setInt(2, obj.getOrderId());
                preparedStatement.setInt(3, obj.getDiscountId());
                preparedStatement.setString(4, obj.getCustomerIp());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer_order;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setCustomerOrderId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public CustomerOrder update(CustomerOrder obj)
        {
           try
            {   
                
                
                
                
                CustomerOrder.checkColumnSize(obj.getCustomerIp(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE customer_order SET com.busy.util.DatabaseColumn@242b038a=?,com.busy.util.DatabaseColumn@6a817e49=?,com.busy.util.DatabaseColumn@70dd5f8=?,com.busy.util.DatabaseColumn@2f685314=? WHERE CustomerOrderId=?;");                    
                preparedStatement.setInt(0, obj.getCustomerOrderId());
                preparedStatement.setInt(1, obj.getCustomerId());
                preparedStatement.setInt(2, obj.getOrderId());
                preparedStatement.setInt(3, obj.getDiscountId());
                preparedStatement.setString(4, obj.getCustomerIp());
                preparedStatement.setInt(5, obj.getCustomerOrderId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getCustomerOrderId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("customer_order");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(CustomerOrder customer_order)
        {
            
                try
                { 
                    
                            getRecordById("Customer", customer_order.getCustomerId().toString());
                            customer_order.setCustomer(Customer.process(rs));                                       
                    
                            getRecordById("Order", customer_order.getOrderId().toString());
                            customer_order.setOrder(Order.process(rs));                                       
                    
                            getRecordById("Discount", customer_order.getDiscountId().toString());
                            customer_order.setDiscount(Discount.process(rs));                                       
                    
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
        public void getRelatedObjects(CustomerOrder customer_order)
        {
            customer_order.setOrderItemList(new OrderItemDaoImpl().findByColumn("CustomerOrderId", customer_order.getCustomerOrderId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(CustomerOrder obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM customer_order WHERE CustomerOrderId=" + obj.getCustomerOrderId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getCustomerOrderId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM customer_order WHERE CustomerOrderId=" + id + ";");           
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
                updateQuery("DELETE FROM customer_order;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM customer_order WHERE " + CustomerOrder.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's removeByColumn method error: " + ex.getMessage());
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
                        CustomerOrder i = (CustomerOrder) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getCustomerOrderId());
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
        
                    
        public void getRelatedOrderItemList(CustomerOrder customer_order)
        {           
            customer_order.setOrderItemList(new OrderItemDaoImpl().findByColumn("CustomerOrderId", customer_order.getCustomerOrderId().toString(),null,null));
        }        
        
                             
    }

