





































    package com.busy.engine.dao;

import com.busy.engine.entity.Customer;
import com.busy.engine.entity.CustomerOrder;
import com.busy.engine.entity.Discount;
import com.busy.engine.entity.Order;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("CustomerOrder's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order;
        } 
    
        @Override
        public int add(CustomerOrder obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer_order;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("CustomerOrder's add method error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("customer_order");
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
            return success;
        }
        
                    
        public void getRelatedOrderItemList(CustomerOrder customer_order)
        {           
            customer_order.setOrderItemList(new OrderItemDaoImpl().findByColumn("CustomerOrderId", customer_order.getCustomerOrderId().toString(),null,null));
        }        
        
                             
    }

