


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ShippingDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Shipping.PROP_SHIPPING_ID) || column.equals(Shipping.PROP_METHOD_NAME) || column.equals(Shipping.PROP_STATE_PROVINCE) || column.equals(Shipping.PROP_COUNTRY) || column.equals(Shipping.PROP_QUANTITY) || column.equals(Shipping.PROP_UNIT_OF_MEASURE) || column.equals(Shipping.PROP_RATE_PER_UNIT_COST) || column.equals(Shipping.PROP_ADDITIONAL_COST) )
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
            if (column.equals(Shipping.PROP_SHIPPING_ID) || column.equals(Shipping.PROP_QUANTITY) || column.equals(Shipping.PROP_RATE_PER_UNIT_COST) || column.equals(Shipping.PROP_ADDITIONAL_COST) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Shipping processShipping(ResultSet rs) throws SQLException
        {        
            return new Shipping(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8));
        }
        
        public static int addShipping(String MethodName, String StateProvince, String Country, Double Quantity, String UnitOfMeasure, Double RatePerUnitCost, Double AdditionalCost)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(MethodName, 100);
                checkColumnSize(StateProvince, 50);
                checkColumnSize(Country, 100);
                
                checkColumnSize(UnitOfMeasure, 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shipping(MethodName,StateProvince,Country,Quantity,UnitOfMeasure,RatePerUnitCost,AdditionalCost) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, MethodName);
                preparedStatement.setString(2, StateProvince);
                preparedStatement.setString(3, Country);
                preparedStatement.setDouble(4, Quantity);
                preparedStatement.setString(5, UnitOfMeasure);
                preparedStatement.setDouble(6, RatePerUnitCost);
                preparedStatement.setDouble(7, AdditionalCost);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shipping;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addShipping error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllShippingCount()
        {
            return getAllRecordsCountByTableName("shipping");        
        }
        
        public static ArrayList<Shipping> getAllShipping()
        {
            ArrayList<Shipping> shipping = new ArrayList<Shipping>();
            try
            {
                getAllRecordsByTableName("shipping");
                while(rs.next())
                {
                    shipping.add(processShipping(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShipping error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        }
                
        public static ArrayList<Shipping> getShippingPaged(int limit, int offset)
        {
            ArrayList<Shipping> shipping = new ArrayList<Shipping>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("shipping", limit, offset);
                while (rs.next())
                {
                    shipping.add(processShipping(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShippingPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        } 
        
        public static ArrayList<Shipping> getAllShippingByColumn(String columnName, String columnValue)
        {
            ArrayList<Shipping> shipping = new ArrayList<Shipping>();
            try
            {
                getAllRecordsByColumn("shipping", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    shipping.add(processShipping(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllShippingByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        }
        
        public static Shipping getShippingByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Shipping shipping = new Shipping();
            try
            {
                getRecordsByColumnWithLimitAndOffset("shipping", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   shipping = processShipping(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getShippingByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        }                
                
        public static void updateShipping(Integer ShippingId,String MethodName,String StateProvince,String Country,Double Quantity,String UnitOfMeasure,Double RatePerUnitCost,Double AdditionalCost)
        {  
            try
            {   
                
                checkColumnSize(MethodName, 100);
                checkColumnSize(StateProvince, 50);
                checkColumnSize(Country, 100);
                
                checkColumnSize(UnitOfMeasure, 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipping SET MethodName=?,StateProvince=?,Country=?,Quantity=?,UnitOfMeasure=?,RatePerUnitCost=?,AdditionalCost=? WHERE ShippingId=?;");                    
                preparedStatement.setString(1, MethodName);
                preparedStatement.setString(2, StateProvince);
                preparedStatement.setString(3, Country);
                preparedStatement.setDouble(4, Quantity);
                preparedStatement.setString(5, UnitOfMeasure);
                preparedStatement.setDouble(6, RatePerUnitCost);
                preparedStatement.setDouble(7, AdditionalCost);
                preparedStatement.setInt(8, ShippingId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateShipping error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllShipping()
        {
            try
            {
                updateQuery("DELETE FROM shipping;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllShipping error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteShippingById(String id)
        {
            try
            {
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShippingById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteShippingByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM shipping WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteShippingByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

