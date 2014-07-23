





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class OrderDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Order.PROP_ORDER_ID) || column.equals(Order.PROP_ORDER_DATE) || column.equals(Order.PROP_SHIP_DATE) || column.equals(Order.PROP_PAYMENT_METHOD) || column.equals(Order.PROP_PURCHASE_ORDER) || column.equals(Order.PROP_TRANSACTION_ID) || column.equals(Order.PROP_AMOUNT_BILLED) || column.equals(Order.PROP_PAYMENT_STATUS) || column.equals(Order.PROP_PENDING_REASON) || column.equals(Order.PROP_PAYMENT_TYPE) || column.equals(Order.PROP_TRANSACTION_FEE) || column.equals(Order.PROP_CURRENCY_CODE) || column.equals(Order.PROP_PAYER_ID) || column.equals(Order.PROP_SUBTOTAL_AMOUNT) || column.equals(Order.PROP_DISCOUNT_AMOUNT) || column.equals(Order.PROP_TAX_AMOUNT) || column.equals(Order.PROP_SHIPPING_AMOUNT) || column.equals(Order.PROP_TOTAL_AMOUNT) || column.equals(Order.PROP_REFUND_AMOUNT) || column.equals(Order.PROP_NOTES) || column.equals(Order.PROP_ORDER_STATUS) || column.equals(Order.PROP_SHIPPING_ID) || column.equals(Order.PROP_AFFILIATE_ID) )
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
            if (column.equals(Order.PROP_ORDER_ID) || column.equals(Order.PROP_AMOUNT_BILLED) || column.equals(Order.PROP_TRANSACTION_FEE) || column.equals(Order.PROP_SUBTOTAL_AMOUNT) || column.equals(Order.PROP_DISCOUNT_AMOUNT) || column.equals(Order.PROP_TAX_AMOUNT) || column.equals(Order.PROP_SHIPPING_AMOUNT) || column.equals(Order.PROP_TOTAL_AMOUNT) || column.equals(Order.PROP_REFUND_AMOUNT) || column.equals(Order.PROP_ORDER_STATUS) || column.equals(Order.PROP_SHIPPING_ID) || column.equals(Order.PROP_AFFILIATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Order processOrder(ResultSet rs) throws SQLException
        {        
            return new Order(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getString(12), rs.getString(13), rs.getDouble(14), rs.getDouble(15), rs.getDouble(16), rs.getDouble(17), rs.getDouble(18), rs.getDouble(19), rs.getString(20), rs.getInt(21), rs.getInt(22), rs.getInt(23));
        }
        
        public static int addOrder(Date OrderDate, Date ShipDate, String PaymentMethod, String PurchaseOrder, String TransactionId, Double AmountBilled, String PaymentStatus, String PendingReason, String PaymentType, Double TransactionFee, String CurrencyCode, String PayerId, Double SubtotalAmount, Double DiscountAmount, Double TaxAmount, Double ShippingAmount, Double TotalAmount, Double RefundAmount, String Notes, Integer OrderStatus, Integer ShippingId, Integer AffiliateId)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(PaymentMethod, 100);
                checkColumnSize(PurchaseOrder, 100);
                checkColumnSize(TransactionId, 45);
                
                checkColumnSize(PaymentStatus, 45);
                checkColumnSize(PendingReason, 45);
                checkColumnSize(PaymentType, 15);
                
                checkColumnSize(CurrencyCode, 45);
                checkColumnSize(PayerId, 45);
                
                
                
                
                
                
                checkColumnSize(Notes, 65535);
                
                
                
                                            
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
        
        public static int getAllOrderCount()
        {
            return getAllRecordsCountByTableName("order");        
        }
        
        public static ArrayList<Order> getAllOrder()
        {
            ArrayList<Order> order = new ArrayList<Order>();
            try
            {
                getAllRecordsByTableName("order");
                while(rs.next())
                {
                    order.add(processOrder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
        }
        
        public static ArrayList<Order> getAllOrderWithRelatedInfo()
        {
            ArrayList<Order> orderList = new ArrayList<Order>();
            try
            {
                getAllRecordsByTableName("order");
                while (rs.next())
                {
                    orderList.add(processOrder(rs));
                }

                
                    for(Order order : orderList)
                    {
                        
                            getRecordById("Shipping", order.getShippingId().toString());
                            order.setShipping(ShippingDAO.processShipping(rs));               
                        
                            getRecordById("Affiliate", order.getAffiliateId().toString());
                            order.setAffiliate(AffiliateDAO.processAffiliate(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOrderWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return orderList;
        }
        
        
        public static Order getRelatedInfo(Order order)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Shipping", order.getShippingId().toString());
                            order.setShipping(ShippingDAO.processShipping(rs));               
                        
                            getRecordById("Affiliate", order.getAffiliateId().toString());
                            order.setAffiliate(AffiliateDAO.processAffiliate(rs));               
                        

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
        
        public static Order getAllRelatedObjects(Order order)
        {           
            order.setCustomerOrderList(CustomerOrderDAO.getAllCustomerOrderByColumn("OrderId", order.getOrderId().toString()));
order.setRecurringPaymentList(RecurringPaymentDAO.getAllRecurringPaymentByColumn("OrderId", order.getOrderId().toString()));
order.setShipmentList(ShipmentDAO.getAllShipmentByColumn("OrderId", order.getOrderId().toString()));
             
            return order;
        }
        
        
                    
        public static Order getRelatedCustomerOrderList(Order order)
        {           
            order.setCustomerOrderList(CustomerOrderDAO.getAllCustomerOrderByColumn("OrderId", order.getOrderId().toString()));
            return order;
        }        
                    
        public static Order getRelatedRecurringPaymentList(Order order)
        {           
            order.setRecurringPaymentList(RecurringPaymentDAO.getAllRecurringPaymentByColumn("OrderId", order.getOrderId().toString()));
            return order;
        }        
                    
        public static Order getRelatedShipmentList(Order order)
        {           
            order.setShipmentList(ShipmentDAO.getAllShipmentByColumn("OrderId", order.getOrderId().toString()));
            return order;
        }        
        
                
        public static ArrayList<Order> getOrderPaged(int limit, int offset)
        {
            ArrayList<Order> order = new ArrayList<Order>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("order", limit, offset);
                while (rs.next())
                {
                    order.add(processOrder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getOrderPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
        } 
        
        public static ArrayList<Order> getAllOrderByColumn(String columnName, String columnValue)
        {
            ArrayList<Order> order = new ArrayList<Order>();
            try
            {
                getAllRecordsByColumn("order", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    order.add(processOrder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOrderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
        }
        
        public static Order getOrderByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Order order = new Order();
            try
            {
                getRecordsByColumnWithLimitAndOffset("order", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   order = processOrder(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getOrderByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order;
        }                
                
        public static void updateOrder(Integer OrderId,Date OrderDate,Date ShipDate,String PaymentMethod,String PurchaseOrder,String TransactionId,Double AmountBilled,String PaymentStatus,String PendingReason,String PaymentType,Double TransactionFee,String CurrencyCode,String PayerId,Double SubtotalAmount,Double DiscountAmount,Double TaxAmount,Double ShippingAmount,Double TotalAmount,Double RefundAmount,String Notes,Integer OrderStatus,Integer ShippingId,Integer AffiliateId)
        {  
            try
            {   
                
                
                
                checkColumnSize(PaymentMethod, 100);
                checkColumnSize(PurchaseOrder, 100);
                checkColumnSize(TransactionId, 45);
                
                checkColumnSize(PaymentStatus, 45);
                checkColumnSize(PendingReason, 45);
                checkColumnSize(PaymentType, 15);
                
                checkColumnSize(CurrencyCode, 45);
                checkColumnSize(PayerId, 45);
                
                
                
                
                
                
                checkColumnSize(Notes, 65535);
                
                
                
                                  
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
        
        public static void deleteAllOrder()
        {
            try
            {
                updateQuery("DELETE FROM order;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteOrderById(String id)
        {
            try
            {
                updateQuery("DELETE FROM order WHERE OrderId=" + id + ";");            
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

        public static void deleteOrderByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM order WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

