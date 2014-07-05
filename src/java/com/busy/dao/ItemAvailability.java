


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAvailability extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMAVAILABILITYID = "ItemAvailabilityId";
        public static final String PROP_ITEMAVAILABILITYTYPE = "ItemAvailabilityType";
        
        
        private Integer itemAvailabilityId;
        private String itemAvailabilityType;
        
        
        public ItemAvailability()
        {
            this.itemAvailabilityId = 0; 
       this.itemAvailabilityType = ""; 
        }
        
        public ItemAvailability(Integer ItemAvailabilityId, String ItemAvailabilityType)
        {
            this.itemAvailabilityId = ItemAvailabilityId;
       this.itemAvailabilityType = ItemAvailabilityType;
        } 
        
        
            public Integer getItemAvailabilityId()
            {
                return this.itemAvailabilityId;
            }
            
            public void setItemAvailabilityId(Integer ItemAvailabilityId)
            {
                this.itemAvailabilityId = ItemAvailabilityId;
            }
        
            public String getItemAvailabilityType()
            {
                return this.itemAvailabilityType;
            }
            
            public void setItemAvailabilityType(String ItemAvailabilityType)
            {
                this.itemAvailabilityType = ItemAvailabilityType;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAvailability.PROP_ITEMAVAILABILITYID) || column.equals(ItemAvailability.PROP_ITEMAVAILABILITYTYPE) )
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
            if (column.equals(ItemAvailability.PROP_ITEMAVAILABILITYID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemAvailability processItemAvailability(ResultSet rs) throws SQLException
        {        
            return new ItemAvailability(rs.getInt(1), rs.getString(2));
        }
        
        public static int addItemAvailability(String ItemAvailabilityType)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ItemAvailabilityType, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_availability(ItemAvailabilityType) VALUES (?);");                    
                preparedStatement.setString(1, ItemAvailabilityType);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_availability;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemAvailabilityCount()
        {
            return getAllRecordsCountByTableName("item_availability");        
        }
        
        public static ArrayList<ItemAvailability> getAllItemAvailability()
        {
            ArrayList<ItemAvailability> item_availability = new ArrayList<ItemAvailability>();
            try
            {
                getAllRecordsByTableName("item_availability");
                while(rs.next())
                {
                    item_availability.add(processItemAvailability(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
        }
                
        public static ArrayList<ItemAvailability> getItemAvailabilityPaged(int limit, int offset)
        {
            ArrayList<ItemAvailability> item_availability = new ArrayList<ItemAvailability>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_availability", limit, offset);
                while (rs.next())
                {
                    item_availability.add(processItemAvailability(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemAvailabilityPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
        } 
        
        public static ArrayList<ItemAvailability> getAllItemAvailabilityByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemAvailability> item_availability = new ArrayList<ItemAvailability>();
            try
            {
                getAllRecordsByColumn("item_availability", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_availability.add(processItemAvailability(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAvailabilityByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
        }
        
        public static ItemAvailability getItemAvailabilityByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemAvailability item_availability = new ItemAvailability();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_availability", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_availability = processItemAvailability(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemAvailabilityByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
        }                
                
        public static void updateItemAvailability(Integer ItemAvailabilityId,String ItemAvailabilityType)
        {  
            try
            {   
                
                checkColumnSize(ItemAvailabilityType, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_availability SET ItemAvailabilityType=? WHERE ItemAvailabilityId=?;");                    
                preparedStatement.setString(1, ItemAvailabilityType);
                preparedStatement.setInt(2, ItemAvailabilityId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemAvailability()
        {
            try
            {
                updateQuery("DELETE FROM item_availability;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemAvailabilityById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAvailabilityById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemAvailabilityByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_availability WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAvailabilityByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

