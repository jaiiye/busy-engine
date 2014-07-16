


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CustomerOrderDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(CustomerOrder.PROP_CUSTOMER_ORDER_ID) || column.equals(CustomerOrder.PROP_CUSTOMER_ID) || column.equals(CustomerOrder.PROP_ORDER_ID) || column.equals(CustomerOrder.PROP_DISCOUNT_ID) || column.equals(CustomerOrder.PROP_CUSTOMER_IP) )
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
            if (column.equals(CustomerOrder.PROP_CUSTOMER_ORDER_ID) || column.equals(CustomerOrder.PROP_CUSTOMER_ID) || column.equals(CustomerOrder.PROP_ORDER_ID) || column.equals(CustomerOrder.PROP_DISCOUNT_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static CustomerOrder processCustomerOrder(ResultSet rs) throws SQLException
        {        
            return new CustomerOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
        }
        
        public static int addCustomerOrder(Integer CustomerId, Integer OrderId, Integer DiscountId, String CustomerIp)
        {   
            int id = 0;
            try
            {
                
                
                
                
                checkColumnSize(CustomerIp, 100);
                                            
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
        
        public static int getAllCustomerOrderCount()
        {
            return getAllRecordsCountByTableName("customer_order");        
        }
        
        public static ArrayList<CustomerOrder> getAllCustomerOrder()
        {
            ArrayList<CustomerOrder> customer_order = new ArrayList<CustomerOrder>();
            try
            {
                getAllRecordsByTableName("customer_order");
                while(rs.next())
                {
                    customer_order.add(processCustomerOrder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCustomerOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order;
        }
                
        public static ArrayList<CustomerOrder> getCustomerOrderPaged(int limit, int offset)
        {
            ArrayList<CustomerOrder> customer_order = new ArrayList<CustomerOrder>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("customer_order", limit, offset);
                while (rs.next())
                {
                    customer_order.add(processCustomerOrder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerOrderPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order;
        } 
        
        public static ArrayList<CustomerOrder> getAllCustomerOrderByColumn(String columnName, String columnValue)
        {
            ArrayList<CustomerOrder> customer_order = new ArrayList<CustomerOrder>();
            try
            {
                getAllRecordsByColumn("customer_order", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    customer_order.add(processCustomerOrder(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCustomerOrderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order;
        }
        
        public static CustomerOrder getCustomerOrderByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            CustomerOrder customer_order = new CustomerOrder();
            try
            {
                getRecordsByColumnWithLimitAndOffset("customer_order", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   customer_order = processCustomerOrder(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerOrderByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order;
        }                
                
        public static void updateCustomerOrder(Integer CustomerOrderId,Integer CustomerId,Integer OrderId,Integer DiscountId,String CustomerIp)
        {  
            try
            {   
                
                
                
                
                checkColumnSize(CustomerIp, 100);
                                  
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
        
        public static void deleteAllCustomerOrder()
        {
            try
            {
                updateQuery("DELETE FROM customer_order;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllCustomerOrder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteCustomerOrderById(String id)
        {
            try
            {
                updateQuery("DELETE FROM customer_order WHERE CustomerOrderId=" + id + ";");            
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

        public static void deleteCustomerOrderByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM customer_order WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerOrderByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

