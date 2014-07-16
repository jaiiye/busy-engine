


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CustomerOrderItem extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_CUSTOMERORDERITEMID = "CustomerOrderItemId";
        public static final String PROP_SHOPPINGCARTID = "ShoppingCartId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_QUANTITY = "Quantity";
        public static final String PROP_OPTIONNAME = "OptionName";
        public static final String PROP_UNITPRICE = "UnitPrice";
        
        
        private Integer customerOrderItemId;
        private Integer shoppingCartId;
        private Integer itemId;
        private Integer quantity;
        private String optionName;
        private Double unitPrice;
        
        
        public CustomerOrderItem()
        {
            this.customerOrderItemId = 0; 
       this.shoppingCartId = 0; 
       this.itemId = 0; 
       this.quantity = 0; 
       this.optionName = ""; 
       this.unitPrice = 0.0; 
        }
        
        public CustomerOrderItem(Integer CustomerOrderItemId, Integer ShoppingCartId, Integer ItemId, Integer Quantity, String OptionName, Double UnitPrice)
        {
            this.customerOrderItemId = CustomerOrderItemId;
       this.shoppingCartId = ShoppingCartId;
       this.itemId = ItemId;
       this.quantity = Quantity;
       this.optionName = OptionName;
       this.unitPrice = UnitPrice;
        } 
        
        
            public Integer getCustomerOrderItemId()
            {
                return this.customerOrderItemId;
            }
            
            public void setCustomerOrderItemId(Integer CustomerOrderItemId)
            {
                this.customerOrderItemId = CustomerOrderItemId;
            }
        
            public Integer getShoppingCartId()
            {
                return this.shoppingCartId;
            }
            
            public void setShoppingCartId(Integer ShoppingCartId)
            {
                this.shoppingCartId = ShoppingCartId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getQuantity()
            {
                return this.quantity;
            }
            
            public void setQuantity(Integer Quantity)
            {
                this.quantity = Quantity;
            }
        
            public String getOptionName()
            {
                return this.optionName;
            }
            
            public void setOptionName(String OptionName)
            {
                this.optionName = OptionName;
            }
        
            public Double getUnitPrice()
            {
                return this.unitPrice;
            }
            
            public void setUnitPrice(Double UnitPrice)
            {
                this.unitPrice = UnitPrice;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(CustomerOrderItem.PROP_CUSTOMERORDERITEMID) || column.equals(CustomerOrderItem.PROP_SHOPPINGCARTID) || column.equals(CustomerOrderItem.PROP_ITEMID) || column.equals(CustomerOrderItem.PROP_QUANTITY) || column.equals(CustomerOrderItem.PROP_OPTIONNAME) || column.equals(CustomerOrderItem.PROP_UNITPRICE) )
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
            if (column.equals(CustomerOrderItem.PROP_CUSTOMERORDERITEMID) || column.equals(CustomerOrderItem.PROP_SHOPPINGCARTID) || column.equals(CustomerOrderItem.PROP_ITEMID) || column.equals(CustomerOrderItem.PROP_QUANTITY) || column.equals(CustomerOrderItem.PROP_UNITPRICE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static CustomerOrderItem processCustomerOrderItem(ResultSet rs) throws SQLException
        {        
            return new CustomerOrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
        }
        
        public static int addCustomerOrderItem(Integer ShoppingCartId, Integer ItemId, Integer Quantity, String OptionName, Double UnitPrice)
        {   
            int id = 0;
            try
            {
                
                
                
                
                checkColumnSize(OptionName, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO customer_order_item(ShoppingCartId,ItemId,Quantity,OptionName,UnitPrice) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, ShoppingCartId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, Quantity);
                preparedStatement.setString(4, OptionName);
                preparedStatement.setDouble(5, UnitPrice);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer_order_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addCustomerOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllCustomerOrderItemCount()
        {
            return getAllRecordsCountByTableName("customer_order_item");        
        }
        
        public static ArrayList<CustomerOrderItem> getAllCustomerOrderItem()
        {
            ArrayList<CustomerOrderItem> customer_order_item = new ArrayList<CustomerOrderItem>();
            try
            {
                getAllRecordsByTableName("customer_order_item");
                while(rs.next())
                {
                    customer_order_item.add(processCustomerOrderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCustomerOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order_item;
        }
                
        public static ArrayList<CustomerOrderItem> getCustomerOrderItemPaged(int limit, int offset)
        {
            ArrayList<CustomerOrderItem> customer_order_item = new ArrayList<CustomerOrderItem>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("customer_order_item", limit, offset);
                while (rs.next())
                {
                    customer_order_item.add(processCustomerOrderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerOrderItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order_item;
        } 
        
        public static ArrayList<CustomerOrderItem> getAllCustomerOrderItemByColumn(String columnName, String columnValue)
        {
            ArrayList<CustomerOrderItem> customer_order_item = new ArrayList<CustomerOrderItem>();
            try
            {
                getAllRecordsByColumn("customer_order_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    customer_order_item.add(processCustomerOrderItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCustomerOrderItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order_item;
        }
        
        public static CustomerOrderItem getCustomerOrderItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            CustomerOrderItem customer_order_item = new CustomerOrderItem();
            try
            {
                getRecordsByColumnWithLimitAndOffset("customer_order_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   customer_order_item = processCustomerOrderItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerOrderItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer_order_item;
        }                
                
        public static void updateCustomerOrderItem(Integer CustomerOrderItemId,Integer ShoppingCartId,Integer ItemId,Integer Quantity,String OptionName,Double UnitPrice)
        {  
            try
            {   
                
                
                
                
                checkColumnSize(OptionName, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE customer_order_item SET ShoppingCartId=?,ItemId=?,Quantity=?,OptionName=?,UnitPrice=? WHERE CustomerOrderItemId=?;");                    
                preparedStatement.setInt(1, ShoppingCartId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, Quantity);
                preparedStatement.setString(4, OptionName);
                preparedStatement.setDouble(5, UnitPrice);
                preparedStatement.setInt(6, CustomerOrderItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateCustomerOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllCustomerOrderItem()
        {
            try
            {
                updateQuery("DELETE FROM customer_order_item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllCustomerOrderItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteCustomerOrderItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM customer_order_item WHERE CustomerOrderItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerOrderItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteCustomerOrderItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM customer_order_item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerOrderItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

