


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class OptionAvailabilityDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(OptionAvailability.PROP_OPTION_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_ITEM_ID) || column.equals(OptionAvailability.PROP_ITEM_OPTION_ID) || column.equals(OptionAvailability.PROP_ITEM_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_AVAILABLE_QUANTITY) || column.equals(OptionAvailability.PROP_PRICE) || column.equals(OptionAvailability.PROP_AVAILABLE_FROM) || column.equals(OptionAvailability.PROP_AVAILABLE_TO) || column.equals(OptionAvailability.PROP_MAXIMUM_QUANTITY) )
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
            if (column.equals(OptionAvailability.PROP_OPTION_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_ITEM_ID) || column.equals(OptionAvailability.PROP_ITEM_OPTION_ID) || column.equals(OptionAvailability.PROP_ITEM_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_AVAILABLE_QUANTITY) || column.equals(OptionAvailability.PROP_PRICE) || column.equals(OptionAvailability.PROP_MAXIMUM_QUANTITY) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static OptionAvailability processOptionAvailability(ResultSet rs) throws SQLException
        {        
            return new OptionAvailability(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
        }
        
        public static int addOptionAvailability(Integer ItemId, Integer ItemOptionId, Integer ItemAvailabilityId, Integer AvailableQuantity, Double Price, Date AvailableFrom, Date AvailableTo, Integer MaximumQuantity)
        {   
            int id = 0;
            try
            {
                
                
                
                
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO option_availability(ItemId,ItemOptionId,ItemAvailabilityId,AvailableQuantity,Price,AvailableFrom,AvailableTo,MaximumQuantity) VALUES (?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, ItemOptionId);
                preparedStatement.setInt(3, ItemAvailabilityId);
                preparedStatement.setInt(4, AvailableQuantity);
                preparedStatement.setDouble(5, Price);
                preparedStatement.setDate(6, new java.sql.Date(AvailableFrom.getTime()));
                preparedStatement.setDate(7, new java.sql.Date(AvailableTo.getTime()));
                preparedStatement.setInt(8, MaximumQuantity);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from option_availability;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addOptionAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllOptionAvailabilityCount()
        {
            return getAllRecordsCountByTableName("option_availability");        
        }
        
        public static ArrayList<OptionAvailability> getAllOptionAvailability()
        {
            ArrayList<OptionAvailability> option_availability = new ArrayList<OptionAvailability>();
            try
            {
                getAllRecordsByTableName("option_availability");
                while(rs.next())
                {
                    option_availability.add(processOptionAvailability(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOptionAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availability;
        }
                
        public static ArrayList<OptionAvailability> getOptionAvailabilityPaged(int limit, int offset)
        {
            ArrayList<OptionAvailability> option_availability = new ArrayList<OptionAvailability>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("option_availability", limit, offset);
                while (rs.next())
                {
                    option_availability.add(processOptionAvailability(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getOptionAvailabilityPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availability;
        } 
        
        public static ArrayList<OptionAvailability> getAllOptionAvailabilityByColumn(String columnName, String columnValue)
        {
            ArrayList<OptionAvailability> option_availability = new ArrayList<OptionAvailability>();
            try
            {
                getAllRecordsByColumn("option_availability", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    option_availability.add(processOptionAvailability(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllOptionAvailabilityByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availability;
        }
        
        public static OptionAvailability getOptionAvailabilityByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            OptionAvailability option_availability = new OptionAvailability();
            try
            {
                getRecordsByColumnWithLimitAndOffset("option_availability", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   option_availability = processOptionAvailability(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getOptionAvailabilityByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availability;
        }                
                
        public static void updateOptionAvailability(Integer OptionAvailabilityId,Integer ItemId,Integer ItemOptionId,Integer ItemAvailabilityId,Integer AvailableQuantity,Double Price,Date AvailableFrom,Date AvailableTo,Integer MaximumQuantity)
        {  
            try
            {   
                
                
                
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE option_availability SET ItemId=?,ItemOptionId=?,ItemAvailabilityId=?,AvailableQuantity=?,Price=?,AvailableFrom=?,AvailableTo=?,MaximumQuantity=? WHERE OptionAvailabilityId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, ItemOptionId);
                preparedStatement.setInt(3, ItemAvailabilityId);
                preparedStatement.setInt(4, AvailableQuantity);
                preparedStatement.setDouble(5, Price);
                preparedStatement.setDate(6, new java.sql.Date(AvailableFrom.getTime()));
                preparedStatement.setDate(7, new java.sql.Date(AvailableTo.getTime()));
                preparedStatement.setInt(8, MaximumQuantity);
                preparedStatement.setInt(9, OptionAvailabilityId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateOptionAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllOptionAvailability()
        {
            try
            {
                updateQuery("DELETE FROM option_availability;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllOptionAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteOptionAvailabilityById(String id)
        {
            try
            {
                updateQuery("DELETE FROM option_availability WHERE OptionAvailabilityId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteOptionAvailabilityById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteOptionAvailabilityByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM option_availability WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteOptionAvailabilityByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

