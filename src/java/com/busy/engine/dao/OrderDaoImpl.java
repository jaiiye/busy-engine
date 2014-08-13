





































    package com.busy.engine.dao;

import com.busy.engine.entity.Affiliate;
import com.busy.engine.entity.Order;
import com.busy.engine.entity.Shipping;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class OrderDaoImpl extends BasicConnection implements Serializable, OrderDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Order find(Integer id)
        {
            return findByColumn("OrderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Order> findAll(Integer limit, Integer offset)
        {
            ArrayList<Order> order = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("order");
                while(rs.next())
                {
                    order.add(Order.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Order object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
         
        }
        
        @Override
        public ArrayList<Order> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Order> orderList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("order", limit, offset);
                while (rs.next())
                {
                    orderList.add(Order.process(rs));
                }

                
                    for(Order order : orderList)
                    {
                        
                            getRecordById("Shipping", order.getShippingId().toString());
                            order.setShipping(Shipping.process(rs));               
                        
                            getRecordById("Affiliate", order.getAffiliateId().toString());
                            order.setAffiliate(Affiliate.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Order method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return orderList;
        }
        
        @Override
        public ArrayList<Order> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Order> order = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("order", Order.checkColumnName(columnName), columnValue, Order.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   order.add(Order.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Order's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
        } 
    
        @Override
        public int add(Order obj)
        {
            int id = 0;
            try
            {
                
                
                
                Order.checkColumnSize(obj.getPaymentMethod(), 100);
                Order.checkColumnSize(obj.getPurchaseOrder(), 100);
                Order.checkColumnSize(obj.getTransactionId(), 45);
                
                Order.checkColumnSize(obj.getPaymentStatus(), 45);
                Order.checkColumnSize(obj.getPendingReason(), 45);
                Order.checkColumnSize(obj.getPaymentType(), 15);
                
                Order.checkColumnSize(obj.getCurrencyCode(), 45);
                Order.checkColumnSize(obj.getPayerId(), 45);
                
                
                
                
                
                
                Order.checkColumnSize(obj.getNotes(), 65535);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO order(OrderDate,ShipDate,PaymentMethod,PurchaseOrder,TransactionId,AmountBilled,PaymentStatus,PendingReason,PaymentType,TransactionFee,CurrencyCode,PayerId,SubtotalAmount,DiscountAmount,TaxAmount,ShippingAmount,TotalAmount,RefundAmount,Notes,OrderStatus,ShippingId,AffiliateId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getOrderDate().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(obj.getShipDate().getTime()));
                preparedStatement.setString(3, obj.getPaymentMethod());
                preparedStatement.setString(4, obj.getPurchaseOrder());
                preparedStatement.setString(5, obj.getTransactionId());
                preparedStatement.setDouble(6, obj.getAmountBilled());
                preparedStatement.setString(7, obj.getPaymentStatus());
                preparedStatement.setString(8, obj.getPendingReason());
                preparedStatement.setString(9, obj.getPaymentType());
                preparedStatement.setDouble(10, obj.getTransactionFee());
                preparedStatement.setString(11, obj.getCurrencyCode());
                preparedStatement.setString(12, obj.getPayerId());
                preparedStatement.setDouble(13, obj.getSubtotalAmount());
                preparedStatement.setDouble(14, obj.getDiscountAmount());
                preparedStatement.setDouble(15, obj.getTaxAmount());
                preparedStatement.setDouble(16, obj.getShippingAmount());
                preparedStatement.setDouble(17, obj.getTotalAmount());
                preparedStatement.setDouble(18, obj.getRefundAmount());
                preparedStatement.setString(19, obj.getNotes());
                preparedStatement.setInt(20, obj.getOrderStatus());
                preparedStatement.setInt(21, obj.getShippingId());
                preparedStatement.setInt(22, obj.getAffiliateId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Order's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Order update(Order obj)
        {
           try
            {   
                
                
                
                Order.checkColumnSize(obj.getPaymentMethod(), 100);
                Order.checkColumnSize(obj.getPurchaseOrder(), 100);
                Order.checkColumnSize(obj.getTransactionId(), 45);
                
                Order.checkColumnSize(obj.getPaymentStatus(), 45);
                Order.checkColumnSize(obj.getPendingReason(), 45);
                Order.checkColumnSize(obj.getPaymentType(), 15);
                
                Order.checkColumnSize(obj.getCurrencyCode(), 45);
                Order.checkColumnSize(obj.getPayerId(), 45);
                
                
                
                
                
                
                Order.checkColumnSize(obj.getNotes(), 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE order SET OrderDate=?,ShipDate=?,PaymentMethod=?,PurchaseOrder=?,TransactionId=?,AmountBilled=?,PaymentStatus=?,PendingReason=?,PaymentType=?,TransactionFee=?,CurrencyCode=?,PayerId=?,SubtotalAmount=?,DiscountAmount=?,TaxAmount=?,ShippingAmount=?,TotalAmount=?,RefundAmount=?,Notes=?,OrderStatus=?,ShippingId=?,AffiliateId=? WHERE OrderId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getOrderDate().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(obj.getShipDate().getTime()));
                preparedStatement.setString(3, obj.getPaymentMethod());
                preparedStatement.setString(4, obj.getPurchaseOrder());
                preparedStatement.setString(5, obj.getTransactionId());
                preparedStatement.setDouble(6, obj.getAmountBilled());
                preparedStatement.setString(7, obj.getPaymentStatus());
                preparedStatement.setString(8, obj.getPendingReason());
                preparedStatement.setString(9, obj.getPaymentType());
                preparedStatement.setDouble(10, obj.getTransactionFee());
                preparedStatement.setString(11, obj.getCurrencyCode());
                preparedStatement.setString(12, obj.getPayerId());
                preparedStatement.setDouble(13, obj.getSubtotalAmount());
                preparedStatement.setDouble(14, obj.getDiscountAmount());
                preparedStatement.setDouble(15, obj.getTaxAmount());
                preparedStatement.setDouble(16, obj.getShippingAmount());
                preparedStatement.setDouble(17, obj.getTotalAmount());
                preparedStatement.setDouble(18, obj.getRefundAmount());
                preparedStatement.setString(19, obj.getNotes());
                preparedStatement.setInt(20, obj.getOrderStatus());
                preparedStatement.setInt(21, obj.getShippingId());
                preparedStatement.setInt(22, obj.getAffiliateId());
                preparedStatement.setInt(23, obj.getOrderId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Order's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("order");
        }
        
        
        @Override
        public void getRelatedInfo(Order order)
        {
            
                try
                { 
                    
                            getRecordById("Shipping", order.getShippingId().toString());
                            order.setShipping(Shipping.process(rs));                                       
                    
                            getRecordById("Affiliate", order.getAffiliateId().toString());
                            order.setAffiliate(Affiliate.process(rs));                                       
                    
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
        public void getRelatedObjects(Order order)
        {
            order.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
order.setRecurringPaymentList(new RecurringPaymentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
order.setShipmentList(new ShipmentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Order obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM order WHERE OrderId=" + obj.getOrderId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Order's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM order WHERE OrderId=" + id + ";");           
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
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM order;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Order's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM order WHERE " + Order.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Order's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedCustomerOrderList(Order order)
        {           
            order.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
        }        
                    
        public void getRelatedRecurringPaymentList(Order order)
        {           
            order.setRecurringPaymentList(new RecurringPaymentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
        }        
                    
        public void getRelatedShipmentList(Order order)
        {           
            order.setShipmentList(new ShipmentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
        }        
        
                             
    }

