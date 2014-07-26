





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ShipmentDaoImpl extends BasicConnection implements Serializable, ShipmentDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Shipment find(Integer id)
        {
            return findByColumn("ShipmentId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Shipment> findAll(Integer limit, Integer offset)
        {
            ArrayList<Shipment> shipment = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("shipment");
                while(rs.next())
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
        public ArrayList<Shipment> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Shipment> shipmentList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("shipment", limit, offset);
                while (rs.next())
                {
                    shipmentList.add(Shipment.process(rs));
                }

                
                    for(Shipment shipment : shipmentList)
                    {
                        
                            getRecordById("Order", shipment.getOrderId().toString());
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
            return shipmentList;
        }
        
        @Override
        public ArrayList<Shipment> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Shipment> shipment = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("shipment", Shipment.checkColumnName(columnName), columnValue, Shipment.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   shipment.add(Shipment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Shipment's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipment;
        } 
    
        @Override
        public void add(Shipment obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Date CreatedOn, String TrackingNumber, Double TotalWeight, Date ShipDate, Date DeliveryDate, Integer ItemQuantity, Integer OrderId)
        {   
            int id = 0;
            try
            {
                
                
                Shipment.checkColumnSize(TrackingNumber, 30);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shipment(CreatedOn,TrackingNumber,TotalWeight,ShipDate,DeliveryDate,ItemQuantity,OrderId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(CreatedOn.getTime()));
                preparedStatement.setString(2, TrackingNumber);
                preparedStatement.setDouble(3, TotalWeight);
                preparedStatement.setDate(4, new java.sql.Date(ShipDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(DeliveryDate.getTime()));
                preparedStatement.setInt(6, ItemQuantity);
                preparedStatement.setInt(7, OrderId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shipment;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addShipment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
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
            }
            catch (Exception ex)
            {
                System.out.println("updateShipment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ShipmentId,Date CreatedOn,String TrackingNumber,Double TotalWeight,Date ShipDate,Date DeliveryDate,Integer ItemQuantity,Integer OrderId)
        {  
            try
            {   
                
                
                Shipment.checkColumnSize(TrackingNumber, 30);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipment SET CreatedOn=?,TrackingNumber=?,TotalWeight=?,ShipDate=?,DeliveryDate=?,ItemQuantity=?,OrderId=? WHERE ShipmentId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(CreatedOn.getTime()));
                preparedStatement.setString(2, TrackingNumber);
                preparedStatement.setDouble(3, TotalWeight);
                preparedStatement.setDate(4, new java.sql.Date(ShipDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(DeliveryDate.getTime()));
                preparedStatement.setInt(6, ItemQuantity);
                preparedStatement.setInt(7, OrderId);
                preparedStatement.setInt(8, ShipmentId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateShipment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("shipment");
        }
        
        
        @Override
        public Shipment getRelatedInfo(Shipment shipment)
        {
            
                try
                { 
                    
                        getRecordById("Order", shipment.getOrderId().toString());
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
              
            
            return shipment;
        }
        
        
        @Override
        public Shipment getRelatedObjects(Shipment shipment)
        {
                         
            return shipment;
        }
        
        
        
        @Override
        public void remove(Shipment obj)
        {            
            try
            {
                updateQuery("DELETE FROM shipment WHERE ShipmentId=" + obj.getShipmentId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShipmentById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM shipment WHERE ShipmentId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShipmentById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM shipment;");
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM shipment WHERE " + Shipment.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Shipment's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

