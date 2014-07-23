





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ShipmentDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Shipment.PROP_SHIPMENT_ID) || column.equals(Shipment.PROP_CREATED_ON) || column.equals(Shipment.PROP_TRACKING_NUMBER) || column.equals(Shipment.PROP_TOTAL_WEIGHT) || column.equals(Shipment.PROP_SHIP_DATE) || column.equals(Shipment.PROP_DELIVERY_DATE) || column.equals(Shipment.PROP_ITEM_QUANTITY) || column.equals(Shipment.PROP_ORDER_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(Shipment.PROP_SHIPMENT_ID) || column.equals(Shipment.PROP_TOTAL_WEIGHT) || column.equals(Shipment.PROP_ITEM_QUANTITY) || column.equals(Shipment.PROP_ORDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Shipment processShipment(ResultSet rs) throws SQLException
        {        
            return new Shipment(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addShipment(Date CreatedOn, String TrackingNumber, Double TotalWeight, Date ShipDate, Date DeliveryDate, Integer ItemQuantity, Integer OrderId)
        {   
            int id = 0;
            try
            {
                
                
                checkColumnSize(TrackingNumber, 30);
                
                
                
                
                
                                            
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
        
        public static int getAllShipmentCount()
        {
            return getAllRecordsCountByTableName("shipment");        
        }
        
        public static ArrayList<Shipment> getAllShipment()
        {
            ArrayList<Shipment> shipment = new ArrayList<Shipment>();
            try
            {
                getAllRecordsByTableName("shipment");
                while(rs.next())
                {
                    shipment.add(processShipment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShipment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipment;
        }
        
        public static ArrayList<Shipment> getAllShipmentWithRelatedInfo()
        {
            ArrayList<Shipment> shipmentList = new ArrayList<Shipment>();
            try
            {
                getAllRecordsByTableName("shipment");
                while (rs.next())
                {
                    shipmentList.add(processShipment(rs));
                }

                
                    for(Shipment shipment : shipmentList)
                    {
                        
                            getRecordById("Order", shipment.getOrderId().toString());
                            shipment.setOrder(OrderDAO.processOrder(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShipmentWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipmentList;
        }
        
        
        public static Shipment getRelatedInfo(Shipment shipment)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Order", shipment.getOrderId().toString());
                            shipment.setOrder(OrderDAO.processOrder(rs));               
                        

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
        
        public static Shipment getAllRelatedObjects(Shipment shipment)
        {           
                         
            return shipment;
        }
        
        
        
                
        public static ArrayList<Shipment> getShipmentPaged(int limit, int offset)
        {
            ArrayList<Shipment> shipment = new ArrayList<Shipment>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("shipment", limit, offset);
                while (rs.next())
                {
                    shipment.add(processShipment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShipmentPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipment;
        } 
        
        public static ArrayList<Shipment> getAllShipmentByColumn(String columnName, String columnValue)
        {
            ArrayList<Shipment> shipment = new ArrayList<Shipment>();
            try
            {
                getAllRecordsByColumn("shipment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    shipment.add(processShipment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShipmentByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipment;
        }
        
        public static Shipment getShipmentByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Shipment shipment = new Shipment();
            try
            {
                getRecordsByColumnWithLimitAndOffset("shipment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   shipment = processShipment(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShipmentByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipment;
        }                
                
        public static void updateShipment(Integer ShipmentId,Date CreatedOn,String TrackingNumber,Double TotalWeight,Date ShipDate,Date DeliveryDate,Integer ItemQuantity,Integer OrderId)
        {  
            try
            {   
                
                
                checkColumnSize(TrackingNumber, 30);
                
                
                
                
                
                                  
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
        
        public static void deleteAllShipment()
        {
            try
            {
                updateQuery("DELETE FROM shipment;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllShipment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteShipmentById(String id)
        {
            try
            {
                updateQuery("DELETE FROM shipment WHERE ShipmentId=" + id + ";");            
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

        public static void deleteShipmentByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM shipment WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteShipmentByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

