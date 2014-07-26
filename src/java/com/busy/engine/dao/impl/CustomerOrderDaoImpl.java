





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CustomerOrderDaoImpl extends BasicConnection implements Serializable, CustomerOrderDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public CustomerOrder find(Integer id)
        {
            return findByColumn("CustomerOrderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<CustomerOrder> findAll(Integer limit, Integer offset)
        {
            ArrayList<CustomerOrder> customer_order = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("customer_order");
                while(rs.next())
                {
                    customer_order.add(CustomerOrder.process(rs));
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
            return customer_order;
         
        }
        
        @Override
        public ArrayList<CustomerOrder> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<CustomerOrder> customer_orderList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("customer_order", limit, offset);
                while (rs.next())
                {
                    customer_orderList.add(CustomerOrder.process(rs));
                }

                
                    for(CustomerOrder customer_order : customer_orderList)
                    {
                        
                            getRecordById("Customer", customer_order.getCustomerId().toString());
                            customer_order.setCustomer(Customer.process(rs));               
                        
                            getRecordById("Order", customer_order.getOrderId().toString());
                            customer_order.setOrder(Order.process(rs));               
                        
                            getRecordById("Discount", customer_order.getDiscountId().toString());
                            customer_order.setDiscount(Discount.process(rs));               
                        
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
            return customer_orderList;
        }
        
        @Override
        public ArrayList<CustomerOrder> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<CustomerOrder> customer_order = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("customer_order", CustomerOrder.checkColumnName(columnName), columnValue, CustomerOrder.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   customer_order.add(CustomerOrder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object CustomerOrder's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order;
        } 
    
        @Override
        public void add(CustomerOrder obj)
        {
            try
            {
                
                
                
                
                CustomerOrder.checkColumnSize(obj.getCustomerIp(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO customer_order(CustomerId,OrderId,DiscountId,CustomerIp) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getCustomerId());
                preparedStatement.setInt(2, obj.getOrderId());
                preparedStatement.setInt(3, obj.getDiscountId());
                preparedStatement.setString(4, obj.getCustomerIp());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer CustomerId, Integer OrderId, Integer DiscountId, String CustomerIp)
        {   
            int id = 0;
            try
            {
                
                
                
                
                CustomerOrder.checkColumnSize(CustomerIp, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO customer_order(CustomerId,OrderId,DiscountId,CustomerIp) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, CustomerId);
                preparedStatement.setInt(2, OrderId);
                preparedStatement.setInt(3, DiscountId);
                preparedStatement.setString(4, CustomerIp);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer_order;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addCustomerOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
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
                prepareStatement("UPDATE customer_order SET CustomerId=?,OrderId=?,DiscountId=?,CustomerIp=? WHERE CustomerOrderId=?;");                    
                preparedStatement.setInt(1, obj.getCustomerId());
                preparedStatement.setInt(2, obj.getOrderId());
                preparedStatement.setInt(3, obj.getDiscountId());
                preparedStatement.setString(4, obj.getCustomerIp());
                preparedStatement.setInt(5, obj.getCustomerOrderId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateCustomerOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer CustomerOrderId,Integer CustomerId,Integer OrderId,Integer DiscountId,String CustomerIp)
        {  
            try
            {   
                
                
                
                
                CustomerOrder.checkColumnSize(CustomerIp, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE customer_order SET CustomerId=?,OrderId=?,DiscountId=?,CustomerIp=? WHERE CustomerOrderId=?;");                    
                preparedStatement.setInt(1, CustomerId);
                preparedStatement.setInt(2, OrderId);
                preparedStatement.setInt(3, DiscountId);
                preparedStatement.setString(4, CustomerIp);
                preparedStatement.setInt(5, CustomerOrderId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateCustomerOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("customer_order");
        }
        
        
        @Override
        public CustomerOrder getRelatedInfo(CustomerOrder customer_order)
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
              
            
            return customer_order;
        }
        
        
        @Override
        public CustomerOrder getRelatedObjects(CustomerOrder customer_order)
        {
            customer_order.setOrderItemList(new OrderItemDaoImpl().findByColumn("CustomerOrderId", customer_order.getCustomerOrderId().toString(),null,null));
             
            return customer_order;
        }
        
        
        
        @Override
        public void remove(CustomerOrder obj)
        {            
            try
            {
                updateQuery("DELETE FROM customer_order WHERE CustomerOrderId=" + obj.getCustomerOrderId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerOrderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM customer_order WHERE CustomerOrderId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerOrderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM customer_order;");
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM customer_order WHERE " + CustomerOrder.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public CustomerOrder getRelatedOrderItemList(CustomerOrder customer_order)
        {           
            customer_order.setOrderItemList(new OrderItemDaoImpl().findByColumn("CustomerOrderId", customer_order.getCustomerOrderId().toString(),null,null));
            return customer_order;
        }        
        
                             
    }

