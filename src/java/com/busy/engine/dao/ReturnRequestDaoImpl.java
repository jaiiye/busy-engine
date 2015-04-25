
























































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
    
    public class ReturnRequestDaoImpl extends BasicConnection implements Serializable, ReturnRequestDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ReturnRequestDaoImpl()
        {
            cachingEnabled = false;
        }

        public ReturnRequestDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ReturnRequestCache
        {
            public static final ConcurrentLruCache<Integer, ReturnRequest> returnRequestCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ReturnRequest i : findAll())
                {
                    getCache().put(i.getReturnRequestId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ReturnRequest> getCache()
        {
            return ReturnRequestCache.returnRequestCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ReturnRequest> buildCache(ArrayList<ReturnRequest> returnRequestList)
        {        
            ConcurrentLruCache<Integer, ReturnRequest> cache = new ConcurrentLruCache<Integer, ReturnRequest>(returnRequestList.size() + 1000);
            for (ReturnRequest i : returnRequestList)
            {
                cache.put(i.getReturnRequestId(), i);
            }
            return cache;
        }

        private static ArrayList<ReturnRequest> findAll()
        {
            ArrayList<ReturnRequest> returnRequest = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("returnRequest");
                while (rs.next())
                {
                    returnRequest.add(ReturnRequest.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ReturnRequest object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return returnRequest;
        }
        
        @Override
        public ReturnRequest find(Integer id)
        {
            return findByColumn("ReturnRequestId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ReturnRequest findWithInfo(Integer id)
        {
            ReturnRequest returnRequest = findByColumn("ReturnRequestId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("order_item", returnRequest.getOrderItemId().toString());
                    returnRequest.setOrderItem(OrderItem.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object ReturnRequest method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return returnRequest;
        }
        
        @Override
        public ArrayList<ReturnRequest> findAll(Integer limit, Integer offset)
        {
            ArrayList<ReturnRequest> returnRequestList = new ArrayList<ReturnRequest>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ReturnRequest, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    returnRequestList = new ArrayList<ReturnRequest>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("return_request", limit, offset);
                    while (rs.next())
                    {
                        returnRequestList.add(ReturnRequest.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ReturnRequest object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return returnRequestList;
         
        }
        
        @Override
        public ArrayList<ReturnRequest> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ReturnRequest> returnRequestList = new ArrayList<ReturnRequest>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ReturnRequest, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    returnRequestList = new ArrayList<ReturnRequest>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ReturnRequest returnRequest = (ReturnRequest) e.getValue();

                            
                                getRecordById("order_item", returnRequest.getOrderItemId().toString());
                                returnRequest.setOrderItem(OrderItem.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ReturnRequest method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                returnRequestList = new ArrayList<ReturnRequest>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("return_request", limit, offset);
                    while (rs.next())
                    {
                        returnRequestList.add(ReturnRequest.process(rs));
                    }

                    
                    
                        for (ReturnRequest returnRequest : returnRequestList)
                        {                        
                            
                                getRecordById("order_item", returnRequest.getOrderItemId().toString());
                                returnRequest.setOrderItem(OrderItem.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ReturnRequest method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return returnRequestList;            
        }
        
        @Override
        public ArrayList<ReturnRequest> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ReturnRequest> returnRequestList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ReturnRequest(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ReturnRequest i = (ReturnRequest) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                returnRequestList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            returnRequestList = null;
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
                    getRecordsByColumnWithLimitOrOffset("return_request", ReturnRequest.checkColumnName(columnName), columnValue, ReturnRequest.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        returnRequestList.add(ReturnRequest.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ReturnRequest's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return returnRequestList;
        } 
        
        @Override
        public ArrayList<ReturnRequest> findByColumns(Column... columns)
        {
            ArrayList<ReturnRequest> returnRequestList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(ReturnRequest.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("return_request", columns);
                while (rs.next())
                {
                    returnRequestList.add(ReturnRequest.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ReturnRequest's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return returnRequestList;
        }
    
        @Override
        public int add(ReturnRequest obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                ReturnRequest.checkColumnSize(obj.getReturnReason(), 255);
                ReturnRequest.checkColumnSize(obj.getRequestedAction(), 255);
                ReturnRequest.checkColumnSize(obj.getNotes(), 65535);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO return_request(Quantity,RequestDate,ReturnReason,RequestedAction,Notes,RequestStatus,OrderItemId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getQuantity());
                preparedStatement.setDate(2, new java.sql.Date(obj.getRequestDate().getTime()));
                preparedStatement.setString(3, obj.getReturnReason());
                preparedStatement.setString(4, obj.getRequestedAction());
                preparedStatement.setString(5, obj.getNotes());
                preparedStatement.setInt(6, obj.getRequestStatus());
                preparedStatement.setInt(7, obj.getOrderItemId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from return_request;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setReturnRequestId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ReturnRequest update(ReturnRequest obj)
        {
           try
            {   
                
                
                
                ReturnRequest.checkColumnSize(obj.getReturnReason(), 255);
                ReturnRequest.checkColumnSize(obj.getRequestedAction(), 255);
                ReturnRequest.checkColumnSize(obj.getNotes(), 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE return_request SET Quantity=?,RequestDate=?,ReturnReason=?,RequestedAction=?,Notes=?,RequestStatus=?,OrderItemId=? WHERE ReturnRequestId=?;");                    
                preparedStatement.setInt(1, obj.getQuantity());
                preparedStatement.setDate(2, new java.sql.Date(obj.getRequestDate().getTime()));
                preparedStatement.setString(3, obj.getReturnReason());
                preparedStatement.setString(4, obj.getRequestedAction());
                preparedStatement.setString(5, obj.getNotes());
                preparedStatement.setInt(6, obj.getRequestStatus());
                preparedStatement.setInt(7, obj.getOrderItemId());
                preparedStatement.setInt(8, obj.getReturnRequestId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getReturnRequestId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("return_request");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ReturnRequest return_request)
        {
            
                try
                { 
                    
                            getRecordById("order_item", return_request.getOrderItemId().toString());
                            return_request.setOrderItem(OrderItem.process(rs));                                       
                    
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
        public void getRelatedObjects(ReturnRequest return_request)
        {
             
        }
        
        @Override
        public boolean remove(ReturnRequest obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + obj.getReturnRequestId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getReturnRequestId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + id + ";");           
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
                updateQuery("DELETE FROM return_request;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM return_request WHERE " + ReturnRequest.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's removeByColumn method error: " + ex.getMessage());
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
                        ReturnRequest i = (ReturnRequest) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getReturnRequestId());
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
        
        
            
        
        
        public void getRelatedOrderItem(ReturnRequest return_request)
        {            
            try
            {                 
                getRecordById("OrderItem", return_request.getOrderItemId().toString());
                return_request.setOrderItem(OrderItem.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedOrderItemWithInfo(ReturnRequest return_request)
        {            
            return_request.setOrderItem(new OrderItemDaoImpl().findWithInfo(return_request.getOrderItemId()));
        }
          
        
    }

