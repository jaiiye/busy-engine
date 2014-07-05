


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ShoppingCartItem extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SHOPPINGCARTITEMID = "ShoppingCartItemId";
        public static final String PROP_SHOPPINGCARTID = "ShoppingCartId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_ITEMQUANTITY = "ItemQuantity";
        public static final String PROP_ITEMOPTION = "ItemOption";
        public static final String PROP_ITEMUNITPRICE = "ItemUnitPrice";
        
        
        private Integer shoppingCartItemId;
        private Integer shoppingCartId;
        private Integer itemId;
        private Integer itemQuantity;
        private String itemOption;
        private Double itemUnitPrice;
        
        
        public ShoppingCartItem()
        {
            this.shoppingCartItemId = 0; 
       this.shoppingCartId = 0; 
       this.itemId = 0; 
       this.itemQuantity = 0; 
       this.itemOption = ""; 
       this.itemUnitPrice = 0.0; 
        }
        
        public ShoppingCartItem(Integer ShoppingCartItemId, Integer ShoppingCartId, Integer ItemId, Integer ItemQuantity, String ItemOption, Double ItemUnitPrice)
        {
            this.shoppingCartItemId = ShoppingCartItemId;
       this.shoppingCartId = ShoppingCartId;
       this.itemId = ItemId;
       this.itemQuantity = ItemQuantity;
       this.itemOption = ItemOption;
       this.itemUnitPrice = ItemUnitPrice;
        } 
        
        
            public Integer getShoppingCartItemId()
            {
                return this.shoppingCartItemId;
            }
            
            public void setShoppingCartItemId(Integer ShoppingCartItemId)
            {
                this.shoppingCartItemId = ShoppingCartItemId;
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
        
            public Integer getItemQuantity()
            {
                return this.itemQuantity;
            }
            
            public void setItemQuantity(Integer ItemQuantity)
            {
                this.itemQuantity = ItemQuantity;
            }
        
            public String getItemOption()
            {
                return this.itemOption;
            }
            
            public void setItemOption(String ItemOption)
            {
                this.itemOption = ItemOption;
            }
        
            public Double getItemUnitPrice()
            {
                return this.itemUnitPrice;
            }
            
            public void setItemUnitPrice(Double ItemUnitPrice)
            {
                this.itemUnitPrice = ItemUnitPrice;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ShoppingCartItem.PROP_SHOPPINGCARTITEMID) || column.equals(ShoppingCartItem.PROP_SHOPPINGCARTID) || column.equals(ShoppingCartItem.PROP_ITEMID) || column.equals(ShoppingCartItem.PROP_ITEMQUANTITY) || column.equals(ShoppingCartItem.PROP_ITEMOPTION) || column.equals(ShoppingCartItem.PROP_ITEMUNITPRICE) )
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
            if (column.equals(ShoppingCartItem.PROP_SHOPPINGCARTITEMID) || column.equals(ShoppingCartItem.PROP_SHOPPINGCARTID) || column.equals(ShoppingCartItem.PROP_ITEMID) || column.equals(ShoppingCartItem.PROP_ITEMQUANTITY) || column.equals(ShoppingCartItem.PROP_ITEMUNITPRICE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ShoppingCartItem processShoppingCartItem(ResultSet rs) throws SQLException
        {        
            return new ShoppingCartItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
        }
        
        public static int addShoppingCartItem(Integer ShoppingCartId, Integer ItemId, Integer ItemQuantity, String ItemOption, Double ItemUnitPrice)
        {   
            int id = 0;
            try
            {
                
                
                
                
                checkColumnSize(ItemOption, 5);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shopping_cart_item(ShoppingCartId,ItemId,ItemQuantity,ItemOption,ItemUnitPrice) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, ShoppingCartId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, ItemQuantity);
                preparedStatement.setString(4, ItemOption);
                preparedStatement.setDouble(5, ItemUnitPrice);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shopping_cart_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addShoppingCartItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllShoppingCartItemCount()
        {
            return getAllRecordsCountByTableName("shopping_cart_item");        
        }
        
        public static ArrayList<ShoppingCartItem> getAllShoppingCartItem()
        {
            ArrayList<ShoppingCartItem> shopping_cart_item = new ArrayList<ShoppingCartItem>();
            try
            {
                getAllRecordsByTableName("shopping_cart_item");
                while(rs.next())
                {
                    shopping_cart_item.add(processShoppingCartItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShoppingCartItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart_item;
        }
                
        public static ArrayList<ShoppingCartItem> getShoppingCartItemPaged(int limit, int offset)
        {
            ArrayList<ShoppingCartItem> shopping_cart_item = new ArrayList<ShoppingCartItem>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("shopping_cart_item", limit, offset);
                while (rs.next())
                {
                    shopping_cart_item.add(processShoppingCartItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShoppingCartItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart_item;
        } 
        
        public static ArrayList<ShoppingCartItem> getAllShoppingCartItemByColumn(String columnName, String columnValue)
        {
            ArrayList<ShoppingCartItem> shopping_cart_item = new ArrayList<ShoppingCartItem>();
            try
            {
                getAllRecordsByColumn("shopping_cart_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    shopping_cart_item.add(processShoppingCartItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShoppingCartItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart_item;
        }
        
        public static ShoppingCartItem getShoppingCartItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ShoppingCartItem shopping_cart_item = new ShoppingCartItem();
            try
            {
                getRecordsByColumnWithLimitAndOffset("shopping_cart_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   shopping_cart_item = processShoppingCartItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShoppingCartItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shopping_cart_item;
        }                
                
        public static void updateShoppingCartItem(Integer ShoppingCartItemId,Integer ShoppingCartId,Integer ItemId,Integer ItemQuantity,String ItemOption,Double ItemUnitPrice)
        {  
            try
            {   
                
                
                
                
                checkColumnSize(ItemOption, 5);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shopping_cart_item SET ShoppingCartId=?,ItemId=?,ItemQuantity=?,ItemOption=?,ItemUnitPrice=? WHERE ShoppingCartItemId=?;");                    
                preparedStatement.setInt(1, ShoppingCartId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, ItemQuantity);
                preparedStatement.setString(4, ItemOption);
                preparedStatement.setDouble(5, ItemUnitPrice);
                preparedStatement.setInt(6, ShoppingCartItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateShoppingCartItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllShoppingCartItem()
        {
            try
            {
                updateQuery("DELETE FROM shopping_cart_item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllShoppingCartItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteShoppingCartItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM shopping_cart_item WHERE ShoppingCartItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShoppingCartItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteShoppingCartItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM shopping_cart_item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteShoppingCartItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

