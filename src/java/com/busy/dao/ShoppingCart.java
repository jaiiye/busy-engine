


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ShoppingCart extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SHOPPINGCARTID = "ShoppingCartId";
        public static final String PROP_CUSTOMERID = "CustomerId";
        public static final String PROP_ORDERID = "OrderId";
        
        
        private Integer shoppingCartId;
        private Integer customerId;
        private Integer orderId;
        
        
        public ShoppingCart()
        {
            this.shoppingCartId = 0; 
       this.customerId = 0; 
       this.orderId = 0; 
        }
        
        public ShoppingCart(Integer ShoppingCartId, Integer CustomerId, Integer OrderId)
        {
            this.shoppingCartId = ShoppingCartId;
       this.customerId = CustomerId;
       this.orderId = OrderId;
        } 
        
        
            public Integer getShoppingCartId()
            {
                return this.shoppingCartId;
            }
            
            public void setShoppingCartId(Integer ShoppingCartId)
            {
                this.shoppingCartId = ShoppingCartId;
            }
        
            public Integer getCustomerId()
            {
                return this.customerId;
            }
            
            public void setCustomerId(Integer CustomerId)
            {
                this.customerId = CustomerId;
            }
        
            public Integer getOrderId()
            {
                return this.orderId;
            }
            
            public void setOrderId(Integer OrderId)
            {
                this.orderId = OrderId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ShoppingCart.PROP_SHOPPINGCARTID) || column.equals(ShoppingCart.PROP_CUSTOMERID) || column.equals(ShoppingCart.PROP_ORDERID) )
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
            if (column.equals(ShoppingCart.PROP_SHOPPINGCARTID) || column.equals(ShoppingCart.PROP_CUSTOMERID) || column.equals(ShoppingCart.PROP_ORDERID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ShoppingCart processShoppingCart(ResultSet rs) throws SQLException
        {        
            return new ShoppingCart(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addShoppingCart(Integer CustomerId, Integer OrderId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shopping_cart(CustomerId,OrderId) VALUES (?,?);");                    
                preparedStatement.setInt(1, CustomerId);
                preparedStatement.setInt(2, OrderId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shopping_cart;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addShoppingCart error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllShoppingCartCount()
        {
            return getAllRecordsCountByTableName("shopping_cart");        
        }
        
        public static ArrayList<ShoppingCart> getAllShoppingCart()
        {
            ArrayList<ShoppingCart> shopping_cart = new ArrayList<ShoppingCart>();
            try
            {
                getAllRecordsByTableName("shopping_cart");
                while(rs.next())
                {
                    shopping_cart.add(processShoppingCart(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShoppingCart error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart;
        }
                
        public static ArrayList<ShoppingCart> getShoppingCartPaged(int limit, int offset)
        {
            ArrayList<ShoppingCart> shopping_cart = new ArrayList<ShoppingCart>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("shopping_cart", limit, offset);
                while (rs.next())
                {
                    shopping_cart.add(processShoppingCart(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShoppingCartPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart;
        } 
        
        public static ArrayList<ShoppingCart> getAllShoppingCartByColumn(String columnName, String columnValue)
        {
            ArrayList<ShoppingCart> shopping_cart = new ArrayList<ShoppingCart>();
            try
            {
                getAllRecordsByColumn("shopping_cart", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    shopping_cart.add(processShoppingCart(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShoppingCartByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart;
        }
        
        public static ShoppingCart getShoppingCartByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ShoppingCart shopping_cart = new ShoppingCart();
            try
            {
                getRecordsByColumnWithLimitAndOffset("shopping_cart", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   shopping_cart = processShoppingCart(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShoppingCartByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart;
        }                
                
        public static void updateShoppingCart(Integer ShoppingCartId,Integer CustomerId,Integer OrderId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shopping_cart SET CustomerId=?,OrderId=? WHERE ShoppingCartId=?;");                    
                preparedStatement.setInt(1, CustomerId);
                preparedStatement.setInt(2, OrderId);
                preparedStatement.setInt(3, ShoppingCartId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateShoppingCart error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllShoppingCart()
        {
            try
            {
                updateQuery("DELETE FROM shopping_cart;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllShoppingCart error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteShoppingCartById(String id)
        {
            try
            {
                updateQuery("DELETE FROM shopping_cart WHERE ShoppingCartId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShoppingCartById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteShoppingCartByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM shopping_cart WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteShoppingCartByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

