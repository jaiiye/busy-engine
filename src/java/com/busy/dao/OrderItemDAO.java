


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class OrderItemDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(OrderItem.PROP_ORDER_ITEM_ID) || column.equals(OrderItem.PROP_CUSTOMER_ORDER_ID) || column.equals(OrderItem.PROP_ITEM_ID) || column.equals(OrderItem.PROP_QUANTITY) || column.equals(OrderItem.PROP_OPTION_NAME) || column.equals(OrderItem.PROP_UNIT_PRICE) )
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
            if (column.equals(OrderItem.PROP_ORDER_ITEM_ID) || column.equals(OrderItem.PROP_CUSTOMER_ORDER_ID) || column.equals(OrderItem.PROP_ITEM_ID) || column.equals(OrderItem.PROP_QUANTITY) || column.equals(OrderItem.PROP_UNIT_PRICE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static OrderItem processOrderItem(ResultSet rs) throws SQLException
        {        
            return new OrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
        }
        
        public static int addOrderItem(Integer CustomerOrderId, Integer ItemId, Integer Quantity, String OptionName, Double UnitPrice)
        {   
            int id = 0;
            try
            {
                
                
                
                
                checkColumnSize(OptionName, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO order_item(CustomerOrderId,ItemId,Quantity,OptionName,UnitPrice) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, CustomerOrderId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, Quantity);
                preparedStatement.setString(4, OptionName);
                preparedStatement.setDouble(5, UnitPrice);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from order_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllOrderItemCount()
        {
            return getAllRecordsCountByTableName("order_item");        
        }
        
        public static ArrayList<OrderItem> getAllOrderItem()
        {
            ArrayList<OrderItem> order_item = new ArrayList<OrderItem>();
            try
            {
                getAllRecordsByTableName("order_item");
                while(rs.next())
                {
                    order_item.add(processOrderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
        }
                
        public static ArrayList<OrderItem> getOrderItemPaged(int limit, int offset)
        {
            ArrayList<OrderItem> order_item = new ArrayList<OrderItem>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("order_item", limit, offset);
                while (rs.next())
                {
                    order_item.add(processOrderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getOrderItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
        } 
        
        public static ArrayList<OrderItem> getAllOrderItemByColumn(String columnName, String columnValue)
        {
            ArrayList<OrderItem> order_item = new ArrayList<OrderItem>();
            try
            {
                getAllRecordsByColumn("order_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    order_item.add(processOrderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOrderItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
        }
        
        public static OrderItem getOrderItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            OrderItem order_item = new OrderItem();
            try
            {
                getRecordsByColumnWithLimitAndOffset("order_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   order_item = processOrderItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getOrderItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return order_item;
        }                
                
        public static void updateOrderItem(Integer OrderItemId,Integer CustomerOrderId,Integer ItemId,Integer Quantity,String OptionName,Double UnitPrice)
        {  
            try
            {   
                
                
                
                
                checkColumnSize(OptionName, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE order_item SET CustomerOrderId=?,ItemId=?,Quantity=?,OptionName=?,UnitPrice=? WHERE OrderItemId=?;");                    
                preparedStatement.setInt(1, CustomerOrderId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, Quantity);
                preparedStatement.setString(4, OptionName);
                preparedStatement.setDouble(5, UnitPrice);
                preparedStatement.setInt(6, OrderItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllOrderItem()
        {
            try
            {
                updateQuery("DELETE FROM order_item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteOrderItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM order_item WHERE OrderItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteOrderItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM order_item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteOrderItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

