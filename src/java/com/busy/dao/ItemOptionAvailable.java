


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemOptionAvailable extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMOPTIONAVAILABLEID = "ItemOptionAvailableId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_ITEMOPTIONID = "ItemOptionId";
        public static final String PROP_ITEMAVAILABILITYID = "ItemAvailabilityId";
        public static final String PROP_ITEMQUANTITY = "ItemQuantity";
        
        
        private Integer itemOptionAvailableId;
        private Integer itemId;
        private Integer itemOptionId;
        private Integer itemAvailabilityId;
        private Integer itemQuantity;
        
        
        public ItemOptionAvailable()
        {
            this.itemOptionAvailableId = 0; 
       this.itemId = 0; 
       this.itemOptionId = 0; 
       this.itemAvailabilityId = 0; 
       this.itemQuantity = 0; 
        }
        
        public ItemOptionAvailable(Integer ItemOptionAvailableId, Integer ItemId, Integer ItemOptionId, Integer ItemAvailabilityId, Integer ItemQuantity)
        {
            this.itemOptionAvailableId = ItemOptionAvailableId;
       this.itemId = ItemId;
       this.itemOptionId = ItemOptionId;
       this.itemAvailabilityId = ItemAvailabilityId;
       this.itemQuantity = ItemQuantity;
        } 
        
        
            public Integer getItemOptionAvailableId()
            {
                return this.itemOptionAvailableId;
            }
            
            public void setItemOptionAvailableId(Integer ItemOptionAvailableId)
            {
                this.itemOptionAvailableId = ItemOptionAvailableId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getItemOptionId()
            {
                return this.itemOptionId;
            }
            
            public void setItemOptionId(Integer ItemOptionId)
            {
                this.itemOptionId = ItemOptionId;
            }
        
            public Integer getItemAvailabilityId()
            {
                return this.itemAvailabilityId;
            }
            
            public void setItemAvailabilityId(Integer ItemAvailabilityId)
            {
                this.itemAvailabilityId = ItemAvailabilityId;
            }
        
            public Integer getItemQuantity()
            {
                return this.itemQuantity;
            }
            
            public void setItemQuantity(Integer ItemQuantity)
            {
                this.itemQuantity = ItemQuantity;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemOptionAvailable.PROP_ITEMOPTIONAVAILABLEID) || column.equals(ItemOptionAvailable.PROP_ITEMID) || column.equals(ItemOptionAvailable.PROP_ITEMOPTIONID) || column.equals(ItemOptionAvailable.PROP_ITEMAVAILABILITYID) || column.equals(ItemOptionAvailable.PROP_ITEMQUANTITY) )
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
            if (column.equals(ItemOptionAvailable.PROP_ITEMOPTIONAVAILABLEID) || column.equals(ItemOptionAvailable.PROP_ITEMID) || column.equals(ItemOptionAvailable.PROP_ITEMOPTIONID) || column.equals(ItemOptionAvailable.PROP_ITEMAVAILABILITYID) || column.equals(ItemOptionAvailable.PROP_ITEMQUANTITY) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemOptionAvailable processItemOptionAvailable(ResultSet rs) throws SQLException
        {        
            return new ItemOptionAvailable(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addItemOptionAvailable(Integer ItemId, Integer ItemOptionId, Integer ItemAvailabilityId, Integer ItemQuantity)
        {   
            int id = 0;
            try
            {
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_option_available(ItemId,ItemOptionId,ItemAvailabilityId,ItemQuantity) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, ItemOptionId);
                preparedStatement.setInt(3, ItemAvailabilityId);
                preparedStatement.setInt(4, ItemQuantity);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_option_available;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemOptionAvailable error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemOptionAvailableCount()
        {
            return getAllRecordsCountByTableName("item_option_available");        
        }
        
        public static ArrayList<ItemOptionAvailable> getAllItemOptionAvailable()
        {
            ArrayList<ItemOptionAvailable> item_option_available = new ArrayList<ItemOptionAvailable>();
            try
            {
                getAllRecordsByTableName("item_option_available");
                while(rs.next())
                {
                    item_option_available.add(processItemOptionAvailable(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemOptionAvailable error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option_available;
        }
                
        public static ArrayList<ItemOptionAvailable> getItemOptionAvailablePaged(int limit, int offset)
        {
            ArrayList<ItemOptionAvailable> item_option_available = new ArrayList<ItemOptionAvailable>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_option_available", limit, offset);
                while (rs.next())
                {
                    item_option_available.add(processItemOptionAvailable(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemOptionAvailablePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option_available;
        } 
        
        public static ArrayList<ItemOptionAvailable> getAllItemOptionAvailableByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemOptionAvailable> item_option_available = new ArrayList<ItemOptionAvailable>();
            try
            {
                getAllRecordsByColumn("item_option_available", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_option_available.add(processItemOptionAvailable(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemOptionAvailableByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option_available;
        }
        
        public static ItemOptionAvailable getItemOptionAvailableByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemOptionAvailable item_option_available = new ItemOptionAvailable();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_option_available", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_option_available = processItemOptionAvailable(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemOptionAvailableByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option_available;
        }                
                
        public static void updateItemOptionAvailable(Integer ItemOptionAvailableId,Integer ItemId,Integer ItemOptionId,Integer ItemAvailabilityId,Integer ItemQuantity)
        {  
            try
            {   
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_option_available SET ItemId=?,ItemOptionId=?,ItemAvailabilityId=?,ItemQuantity=? WHERE ItemOptionAvailableId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, ItemOptionId);
                preparedStatement.setInt(3, ItemAvailabilityId);
                preparedStatement.setInt(4, ItemQuantity);
                preparedStatement.setInt(5, ItemOptionAvailableId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemOptionAvailable error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemOptionAvailable()
        {
            try
            {
                updateQuery("DELETE FROM item_option_available;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemOptionAvailable error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemOptionAvailableById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_option_available WHERE ItemOptionAvailableId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemOptionAvailableById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemOptionAvailableByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_option_available WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemOptionAvailableByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

