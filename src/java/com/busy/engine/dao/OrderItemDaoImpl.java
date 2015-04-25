
























































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
    
    public class OrderItemDaoImpl extends BasicConnection implements Serializable, OrderItemDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public OrderItemDaoImpl()
        {
            cachingEnabled = false;
        }

        public OrderItemDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class OrderItemCache
        {
            public static final ConcurrentLruCache<Integer, OrderItem> orderItemCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (OrderItem i : findAll())
                {
                    getCache().put(i.getOrderItemId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, OrderItem> getCache()
        {
            return OrderItemCache.orderItemCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, OrderItem> buildCache(ArrayList<OrderItem> orderItemList)
        {        
            ConcurrentLruCache<Integer, OrderItem> cache = new ConcurrentLruCache<Integer, OrderItem>(orderItemList.size() + 1000);
            for (OrderItem i : orderItemList)
            {
                cache.put(i.getOrderItemId(), i);
            }
            return cache;
        }

        private static ArrayList<OrderItem> findAll()
        {
            ArrayList<OrderItem> orderItem = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("orderItem");
                while (rs.next())
                {
                    orderItem.add(OrderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OrderItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return orderItem;
        }
        
        @Override
        public OrderItem find(Integer id)
        {
            return findByColumn("OrderItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public OrderItem findWithInfo(Integer id)
        {
            OrderItem orderItem = findByColumn("OrderItemId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("customer_order", orderItem.getCustomerOrderId().toString());
                    orderItem.setCustomerOrder(CustomerOrder.process(rs));               
                
                    getRecordById("item", orderItem.getItemId().toString());
                    orderItem.setItem(Item.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object OrderItem method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return orderItem;
        }
        
        @Override
        public ArrayList<OrderItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for OrderItem, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    orderItemList = new ArrayList<OrderItem>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("order_item", limit, offset);
                    while (rs.next())
                    {
                        orderItemList.add(OrderItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("OrderItem object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return orderItemList;
         
        }
        
        @Override
        public ArrayList<OrderItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for OrderItem, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    orderItemList = new ArrayList<OrderItem>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            OrderItem orderItem = (OrderItem) e.getValue();

                            
                                getRecordById("customer_order", orderItem.getCustomerOrderId().toString());
                                orderItem.setCustomerOrder(CustomerOrder.process(rs));               
                            
                                getRecordById("item", orderItem.getItemId().toString());
                                orderItem.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object OrderItem method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                orderItemList = new ArrayList<OrderItem>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("order_item", limit, offset);
                    while (rs.next())
                    {
                        orderItemList.add(OrderItem.process(rs));
                    }

                    
                    
                        for (OrderItem orderItem : orderItemList)
                        {                        
                            
                                getRecordById("customer_order", orderItem.getCustomerOrderId().toString());
                                orderItem.setCustomerOrder(CustomerOrder.process(rs));               
                            
                                getRecordById("item", orderItem.getItemId().toString());
                                orderItem.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object OrderItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return orderItemList;            
        }
        
        @Override
        public ArrayList<OrderItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<OrderItem> orderItemList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for OrderItem(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            OrderItem i = (OrderItem) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                orderItemList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            orderItemList = null;
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
                    getRecordsByColumnWithLimitOrOffset("order_item", OrderItem.checkColumnName(columnName), columnValue, OrderItem.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        orderItemList.add(OrderItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("OrderItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return orderItemList;
        } 
        
        @Override
        public ArrayList<OrderItem> findByColumns(Column... columns)
        {
            ArrayList<OrderItem> orderItemList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(OrderItem.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("order_item", columns);
                while (rs.next())
                {
                    orderItemList.add(OrderItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OrderItem's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return orderItemList;
        }
    
        @Override
        public int add(OrderItem obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                OrderItem.checkColumnSize(obj.getOptionName(), 100);
                
                  

                openConnection();
                prepareStatement("INSERT INTO order_item(CustomerOrderId,ItemId,Quantity,OptionName,UnitPrice) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getCustomerOrderId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getQuantity());
                preparedStatement.setString(4, obj.getOptionName());
                preparedStatement.setDouble(5, obj.getUnitPrice());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order_item;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setOrderItemId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public OrderItem update(OrderItem obj)
        {
           try
            {   
                
                
                
                
                OrderItem.checkColumnSize(obj.getOptionName(), 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE order_item SET CustomerOrderId=?,ItemId=?,Quantity=?,OptionName=?,UnitPrice=? WHERE OrderItemId=?;");                    
                preparedStatement.setInt(1, obj.getCustomerOrderId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getQuantity());
                preparedStatement.setString(4, obj.getOptionName());
                preparedStatement.setDouble(5, obj.getUnitPrice());
                preparedStatement.setInt(6, obj.getOrderItemId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getOrderItemId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("order_item");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(OrderItem order_item)
        {
            
                try
                { 
                    
                            getRecordById("customer_order", order_item.getCustomerOrderId().toString());
                            order_item.setCustomerOrder(CustomerOrder.process(rs));                                       
                    
                            getRecordById("item", order_item.getItemId().toString());
                            order_item.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(OrderItem order_item)
        {
            order_item.setReturnRequestList(new ReturnRequestDaoImpl().findByColumn("OrderItemId", order_item.getOrderItemId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(OrderItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + obj.getOrderItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getOrderItemId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + id + ";");           
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
                updateQuery("DELETE FROM order_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order_item WHERE " + OrderItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("OrderItem's removeByColumn method error: " + ex.getMessage());
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
                        OrderItem i = (OrderItem) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getOrderItemId());
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
        
                    
        public void getRelatedReturnRequestList(OrderItem order_item)
        {           
            order_item.setReturnRequestList(new ReturnRequestDaoImpl().findByColumn("OrderItemId", order_item.getOrderItemId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedCustomerOrder(OrderItem order_item)
        {            
            try
            {                 
                getRecordById("CustomerOrder", order_item.getCustomerOrderId().toString());
                order_item.setCustomerOrder(CustomerOrder.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedItem(OrderItem order_item)
        {            
            try
            {                 
                getRecordById("Item", order_item.getItemId().toString());
                order_item.setItem(Item.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedCustomerOrderWithInfo(OrderItem order_item)
        {            
            order_item.setCustomerOrder(new CustomerOrderDaoImpl().findWithInfo(order_item.getCustomerOrderId()));
        }
        
        public void getRelatedItemWithInfo(OrderItem order_item)
        {            
            order_item.setItem(new ItemDaoImpl().findWithInfo(order_item.getItemId()));
        }
          
        
    }

