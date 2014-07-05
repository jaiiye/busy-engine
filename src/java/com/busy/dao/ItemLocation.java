


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemLocation extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMLOCATIONID = "ItemLocationId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_ADDRESSID = "AddressId";
        public static final String PROP_LATITUDE = "Latitude";
        public static final String PROP_LONGITUDE = "Longitude";
        
        
        private Integer itemLocationId;
        private Integer itemId;
        private Integer addressId;
        private String latitude;
        private String longitude;
        
        
        public ItemLocation()
        {
            this.itemLocationId = 0; 
       this.itemId = 0; 
       this.addressId = 0; 
       this.latitude = ""; 
       this.longitude = ""; 
        }
        
        public ItemLocation(Integer ItemLocationId, Integer ItemId, Integer AddressId, String Latitude, String Longitude)
        {
            this.itemLocationId = ItemLocationId;
       this.itemId = ItemId;
       this.addressId = AddressId;
       this.latitude = Latitude;
       this.longitude = Longitude;
        } 
        
        
            public Integer getItemLocationId()
            {
                return this.itemLocationId;
            }
            
            public void setItemLocationId(Integer ItemLocationId)
            {
                this.itemLocationId = ItemLocationId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
        
            public String getLatitude()
            {
                return this.latitude;
            }
            
            public void setLatitude(String Latitude)
            {
                this.latitude = Latitude;
            }
        
            public String getLongitude()
            {
                return this.longitude;
            }
            
            public void setLongitude(String Longitude)
            {
                this.longitude = Longitude;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemLocation.PROP_ITEMLOCATIONID) || column.equals(ItemLocation.PROP_ITEMID) || column.equals(ItemLocation.PROP_ADDRESSID) || column.equals(ItemLocation.PROP_LATITUDE) || column.equals(ItemLocation.PROP_LONGITUDE) )
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
            if (column.equals(ItemLocation.PROP_ITEMLOCATIONID) || column.equals(ItemLocation.PROP_ITEMID) || column.equals(ItemLocation.PROP_ADDRESSID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemLocation processItemLocation(ResultSet rs) throws SQLException
        {        
            return new ItemLocation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
        }
        
        public static int addItemLocation(Integer ItemId, Integer AddressId, String Latitude, String Longitude)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(Latitude, 20);
                checkColumnSize(Longitude, 20);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_location(ItemId,AddressId,Latitude,Longitude) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, AddressId);
                preparedStatement.setString(3, Latitude);
                preparedStatement.setString(4, Longitude);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_location;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemLocationCount()
        {
            return getAllRecordsCountByTableName("item_location");        
        }
        
        public static ArrayList<ItemLocation> getAllItemLocation()
        {
            ArrayList<ItemLocation> item_location = new ArrayList<ItemLocation>();
            try
            {
                getAllRecordsByTableName("item_location");
                while(rs.next())
                {
                    item_location.add(processItemLocation(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_location;
        }
                
        public static ArrayList<ItemLocation> getItemLocationPaged(int limit, int offset)
        {
            ArrayList<ItemLocation> item_location = new ArrayList<ItemLocation>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_location", limit, offset);
                while (rs.next())
                {
                    item_location.add(processItemLocation(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemLocationPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_location;
        } 
        
        public static ArrayList<ItemLocation> getAllItemLocationByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemLocation> item_location = new ArrayList<ItemLocation>();
            try
            {
                getAllRecordsByColumn("item_location", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_location.add(processItemLocation(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemLocationByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_location;
        }
        
        public static ItemLocation getItemLocationByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemLocation item_location = new ItemLocation();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_location", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_location = processItemLocation(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemLocationByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_location;
        }                
                
        public static void updateItemLocation(Integer ItemLocationId,Integer ItemId,Integer AddressId,String Latitude,String Longitude)
        {  
            try
            {   
                
                
                
                checkColumnSize(Latitude, 20);
                checkColumnSize(Longitude, 20);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_location SET ItemId=?,AddressId=?,Latitude=?,Longitude=? WHERE ItemLocationId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, AddressId);
                preparedStatement.setString(3, Latitude);
                preparedStatement.setString(4, Longitude);
                preparedStatement.setInt(5, ItemLocationId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemLocation()
        {
            try
            {
                updateQuery("DELETE FROM item_location;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemLocationById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_location WHERE ItemLocationId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemLocationById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemLocationByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_location WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemLocationByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

