





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Order's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
        } 
    
        @Override
        public void add(Order obj)
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
            }
            catch (Exception ex)
            {
                System.out.println("Order's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Date OrderDate, Date ShipDate, String PaymentMethod, String PurchaseOrder, String TransactionId, Double AmountBilled, String PaymentStatus, String PendingReason, String PaymentType, Double TransactionFee, String CurrencyCode, String PayerId, Double SubtotalAmount, Double DiscountAmount, Double TaxAmount, Double ShippingAmount, Double TotalAmount, Double RefundAmount, String Notes, Integer OrderStatus, Integer ShippingId, Integer AffiliateId)
        {   
            int id = 0;
            try
            {
                
                
                
                Order.checkColumnSize(PaymentMethod, 100);
                Order.checkColumnSize(PurchaseOrder, 100);
                Order.checkColumnSize(TransactionId, 45);
                
                Order.checkColumnSize(PaymentStatus, 45);
                Order.checkColumnSize(PendingReason, 45);
                Order.checkColumnSize(PaymentType, 15);
                
                Order.checkColumnSize(CurrencyCode, 45);
                Order.checkColumnSize(PayerId, 45);
                
                
                
                
                
                
                Order.checkColumnSize(Notes, 65535);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO order(OrderDate,ShipDate,PaymentMethod,PurchaseOrder,TransactionId,AmountBilled,PaymentStatus,PendingReason,PaymentType,TransactionFee,CurrencyCode,PayerId,SubtotalAmount,DiscountAmount,TaxAmount,ShippingAmount,TotalAmount,RefundAmount,Notes,OrderStatus,ShippingId,AffiliateId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(OrderDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(ShipDate.getTime()));
                preparedStatement.setString(3, PaymentMethod);
                preparedStatement.setString(4, PurchaseOrder);
                preparedStatement.setString(5, TransactionId);
                preparedStatement.setDouble(6, AmountBilled);
                preparedStatement.setString(7, PaymentStatus);
                preparedStatement.setString(8, PendingReason);
                preparedStatement.setString(9, PaymentType);
                preparedStatement.setDouble(10, TransactionFee);
                preparedStatement.setString(11, CurrencyCode);
                preparedStatement.setString(12, PayerId);
                preparedStatement.setDouble(13, SubtotalAmount);
                preparedStatement.setDouble(14, DiscountAmount);
                preparedStatement.setDouble(15, TaxAmount);
                preparedStatement.setDouble(16, ShippingAmount);
                preparedStatement.setDouble(17, TotalAmount);
                preparedStatement.setDouble(18, RefundAmount);
                preparedStatement.setString(19, Notes);
                preparedStatement.setInt(20, OrderStatus);
                preparedStatement.setInt(21, ShippingId);
                preparedStatement.setInt(22, AffiliateId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addOrder error: " + ex.getMessage());
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
                System.out.println("updateOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer OrderId,Date OrderDate,Date ShipDate,String PaymentMethod,String PurchaseOrder,String TransactionId,Double AmountBilled,String PaymentStatus,String PendingReason,String PaymentType,Double TransactionFee,String CurrencyCode,String PayerId,Double SubtotalAmount,Double DiscountAmount,Double TaxAmount,Double ShippingAmount,Double TotalAmount,Double RefundAmount,String Notes,Integer OrderStatus,Integer ShippingId,Integer AffiliateId)
        {  
            try
            {   
                
                
                
                Order.checkColumnSize(PaymentMethod, 100);
                Order.checkColumnSize(PurchaseOrder, 100);
                Order.checkColumnSize(TransactionId, 45);
                
                Order.checkColumnSize(PaymentStatus, 45);
                Order.checkColumnSize(PendingReason, 45);
                Order.checkColumnSize(PaymentType, 15);
                
                Order.checkColumnSize(CurrencyCode, 45);
                Order.checkColumnSize(PayerId, 45);
                
                
                
                
                
                
                Order.checkColumnSize(Notes, 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE order SET OrderDate=?,ShipDate=?,PaymentMethod=?,PurchaseOrder=?,TransactionId=?,AmountBilled=?,PaymentStatus=?,PendingReason=?,PaymentType=?,TransactionFee=?,CurrencyCode=?,PayerId=?,SubtotalAmount=?,DiscountAmount=?,TaxAmount=?,ShippingAmount=?,TotalAmount=?,RefundAmount=?,Notes=?,OrderStatus=?,ShippingId=?,AffiliateId=? WHERE OrderId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(OrderDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(ShipDate.getTime()));
                preparedStatement.setString(3, PaymentMethod);
                preparedStatement.setString(4, PurchaseOrder);
                preparedStatement.setString(5, TransactionId);
                preparedStatement.setDouble(6, AmountBilled);
                preparedStatement.setString(7, PaymentStatus);
                preparedStatement.setString(8, PendingReason);
                preparedStatement.setString(9, PaymentType);
                preparedStatement.setDouble(10, TransactionFee);
                preparedStatement.setString(11, CurrencyCode);
                preparedStatement.setString(12, PayerId);
                preparedStatement.setDouble(13, SubtotalAmount);
                preparedStatement.setDouble(14, DiscountAmount);
                preparedStatement.setDouble(15, TaxAmount);
                preparedStatement.setDouble(16, ShippingAmount);
                preparedStatement.setDouble(17, TotalAmount);
                preparedStatement.setDouble(18, RefundAmount);
                preparedStatement.setString(19, Notes);
                preparedStatement.setInt(20, OrderStatus);
                preparedStatement.setInt(21, ShippingId);
                preparedStatement.setInt(22, AffiliateId);
                preparedStatement.setInt(23, OrderId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("order");
        }
        
        
        @Override
        public Order getRelatedInfo(Order order)
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
              
            
            return order;
        }
        
        
        @Override
        public Order getRelatedObjects(Order order)
        {
            order.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
order.setRecurringPaymentList(new RecurringPaymentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
order.setShipmentList(new ShipmentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
             
            return order;
        }
        
        
        
        @Override
        public void remove(Order obj)
        {            
            try
            {
                updateQuery("DELETE FROM order WHERE OrderId=" + obj.getOrderId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order WHERE OrderId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order;");
            }
            catch (Exception ex)
            {
                System.out.println("Order's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM order WHERE " + Order.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Order's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Order getRelatedCustomerOrderList(Order order)
        {           
            order.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
            return order;
        }        
                    
        public Order getRelatedRecurringPaymentList(Order order)
        {           
            order.setRecurringPaymentList(new RecurringPaymentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
            return order;
        }        
                    
        public Order getRelatedShipmentList(Order order)
        {           
            order.setShipmentList(new ShipmentDaoImpl().findByColumn("OrderId", order.getOrderId().toString(),null,null));
            return order;
        }        
        
                             
    }

