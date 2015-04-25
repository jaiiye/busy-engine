
























































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
    
    public class ShipmentDaoImpl extends BasicConnection implements Serializable, ShipmentDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ShipmentDaoImpl()
        {
            cachingEnabled = false;
        }

        public ShipmentDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ShipmentCache
        {
            public static final ConcurrentLruCache<Integer, Shipment> shipmentCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Shipment i : findAll())
                {
                    getCache().put(i.getShipmentId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Shipment> getCache()
        {
            return ShipmentCache.shipmentCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Shipment> buildCache(ArrayList<Shipment> shipmentList)
        {        
            ConcurrentLruCache<Integer, Shipment> cache = new ConcurrentLruCache<Integer, Shipment>(shipmentList.size() + 1000);
            for (Shipment i : shipmentList)
            {
                cache.put(i.getShipmentId(), i);
            }
            return cache;
        }

        private static ArrayList<Shipment> findAll()
        {
            ArrayList<Shipment> shipment = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("shipment");
                while (rs.next())
                {
                    shipment.add(Shipment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Shipment object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipment;
        }
        
        @Override
        public Shipment find(Integer id)
        {
            return findByColumn("ShipmentId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Shipment findWithInfo(Integer id)
        {
            Shipment shipment = findByColumn("ShipmentId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("order", shipment.getOrderId().toString());
                    shipment.setOrder(Order.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Shipment method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return shipment;
        }
        
        @Override
        public ArrayList<Shipment> findAll(Integer limit, Integer offset)
        {
            ArrayList<Shipment> shipmentList = new ArrayList<Shipment>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Shipment, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    shipmentList = new ArrayList<Shipment>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("shipment", limit, offset);
                    while (rs.next())
                    {
                        shipmentList.add(Shipment.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Shipment object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return shipmentList;
         
        }
        
        @Override
        public ArrayList<Shipment> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Shipment> shipmentList = new ArrayList<Shipment>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Shipment, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    shipmentList = new ArrayList<Shipment>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Shipment shipment = (Shipment) e.getValue();

                            
                                getRecordById("order", shipment.getOrderId().toString());
                                shipment.setOrder(Order.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Shipment method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                shipmentList = new ArrayList<Shipment>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("shipment", limit, offset);
                    while (rs.next())
                    {
                        shipmentList.add(Shipment.process(rs));
                    }

                    
                    
                        for (Shipment shipment : shipmentList)
                        {                        
                            
                                getRecordById("order", shipment.getOrderId().toString());
                                shipment.setOrder(Order.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Shipment method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return shipmentList;            
        }
        
        @Override
        public ArrayList<Shipment> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Shipment> shipmentList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Shipment(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Shipment i = (Shipment) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                shipmentList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            shipmentList = null;
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
                    getRecordsByColumnWithLimitOrOffset("shipment", Shipment.checkColumnName(columnName), columnValue, Shipment.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        shipmentList.add(Shipment.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Shipment's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return shipmentList;
        } 
        
        @Override
        public ArrayList<Shipment> findByColumns(Column... columns)
        {
            ArrayList<Shipment> shipmentList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(Shipment.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("shipment", columns);
                while (rs.next())
                {
                    shipmentList.add(Shipment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Shipment's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return shipmentList;
        }
    
        @Override
        public int add(Shipment obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                Shipment.checkColumnSize(obj.getTrackingNumber(), 30);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO shipment(CreatedOn,TrackingNumber,TotalWeight,ShipDate,DeliveryDate,ItemQuantity,OrderId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getCreatedOn().getTime()));
                preparedStatement.setString(2, obj.getTrackingNumber());
                preparedStatement.setDouble(3, obj.getTotalWeight());
                preparedStatement.setDate(4, new java.sql.Date(obj.getShipDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(obj.getDeliveryDate().getTime()));
                preparedStatement.setInt(6, obj.getItemQuantity());
                preparedStatement.setInt(7, obj.getOrderId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shipment;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setShipmentId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Shipment update(Shipment obj)
        {
           try
            {   
                
                
                Shipment.checkColumnSize(obj.getTrackingNumber(), 30);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipment SET CreatedOn=?,TrackingNumber=?,TotalWeight=?,ShipDate=?,DeliveryDate=?,ItemQuantity=?,OrderId=? WHERE ShipmentId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getCreatedOn().getTime()));
                preparedStatement.setString(2, obj.getTrackingNumber());
                preparedStatement.setDouble(3, obj.getTotalWeight());
                preparedStatement.setDate(4, new java.sql.Date(obj.getShipDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(obj.getDeliveryDate().getTime()));
                preparedStatement.setInt(6, obj.getItemQuantity());
                preparedStatement.setInt(7, obj.getOrderId());
                preparedStatement.setInt(8, obj.getShipmentId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getShipmentId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("shipment");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Shipment shipment)
        {
            
                try
                { 
                    
                            getRecordById("order", shipment.getOrderId().toString());
                            shipment.setOrder(Order.process(rs));                                       
                    
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
        public void getRelatedObjects(Shipment shipment)
        {
             
        }
        
        @Override
        public boolean remove(Shipment obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM shipment WHERE ShipmentId=" + obj.getShipmentId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getShipmentId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM shipment WHERE ShipmentId=" + id + ";");           
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
                updateQuery("DELETE FROM shipment;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipment WHERE " + Shipment.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's removeByColumn method error: " + ex.getMessage());
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
                        Shipment i = (Shipment) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getShipmentId());
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
        
        
            
        
        
        public void getRelatedOrder(Shipment shipment)
        {            
            try
            {                 
                getRecordById("Order", shipment.getOrderId().toString());
                shipment.setOrder(Order.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedOrderWithInfo(Shipment shipment)
        {            
            shipment.setOrder(new OrderDaoImpl().findWithInfo(shipment.getOrderId()));
        }
          
        
    }

