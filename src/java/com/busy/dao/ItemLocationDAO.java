











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.ItemLocation;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemLocationDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemLocation.PROP_ITEM_LOCATION_ID) || column.equals(ItemLocation.PROP_LATITUDE) || column.equals(ItemLocation.PROP_LONGITUDE) || column.equals(ItemLocation.PROP_ITEM_ID) || column.equals(ItemLocation.PROP_ADDRESS_ID) )
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
            if (column.equals(ItemLocation.PROP_ITEM_LOCATION_ID) || column.equals(ItemLocation.PROP_ITEM_ID) || column.equals(ItemLocation.PROP_ADDRESS_ID) )
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
            return new ItemLocation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addItemLocation(String Latitude, String Longitude, Integer ItemId, Integer AddressId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Latitude, 20);
                checkColumnSize(Longitude, 20);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_location(Latitude,Longitude,ItemId,AddressId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, Latitude);
                preparedStatement.setString(2, Longitude);
                preparedStatement.setInt(3, ItemId);
                preparedStatement.setInt(4, AddressId);
                
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
                
        public static void updateItemLocation(Integer ItemLocationId,String Latitude,String Longitude,Integer ItemId,Integer AddressId)
        {  
            try
            {   
                
                checkColumnSize(Latitude, 20);
                checkColumnSize(Longitude, 20);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_location SET Latitude=?,Longitude=?,ItemId=?,AddressId=? WHERE ItemLocationId=?;");                    
                preparedStatement.setString(1, Latitude);
                preparedStatement.setString(2, Longitude);
                preparedStatement.setInt(3, ItemId);
                preparedStatement.setInt(4, AddressId);
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

