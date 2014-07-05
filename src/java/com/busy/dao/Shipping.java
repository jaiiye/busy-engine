


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Shipping extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SHIPPINGID = "ShippingId";
        public static final String PROP_SHIPPINGREGION = "ShippingRegion";
        public static final String PROP_SHIPPINGMETHOD = "ShippingMethod";
        public static final String PROP_SHIPPINGRATE = "ShippingRate";
        public static final String PROP_SHIPPINGADDITIONAL = "ShippingAdditional";
        
        
        private Integer shippingId;
        private String shippingRegion;
        private String shippingMethod;
        private Double shippingRate;
        private Double shippingAdditional;
        
        
        public Shipping()
        {
            this.shippingId = 0; 
       this.shippingRegion = ""; 
       this.shippingMethod = ""; 
       this.shippingRate = 0.0; 
       this.shippingAdditional = 0.0; 
        }
        
        public Shipping(Integer ShippingId, String ShippingRegion, String ShippingMethod, Double ShippingRate, Double ShippingAdditional)
        {
            this.shippingId = ShippingId;
       this.shippingRegion = ShippingRegion;
       this.shippingMethod = ShippingMethod;
       this.shippingRate = ShippingRate;
       this.shippingAdditional = ShippingAdditional;
        } 
        
        
            public Integer getShippingId()
            {
                return this.shippingId;
            }
            
            public void setShippingId(Integer ShippingId)
            {
                this.shippingId = ShippingId;
            }
        
            public String getShippingRegion()
            {
                return this.shippingRegion;
            }
            
            public void setShippingRegion(String ShippingRegion)
            {
                this.shippingRegion = ShippingRegion;
            }
        
            public String getShippingMethod()
            {
                return this.shippingMethod;
            }
            
            public void setShippingMethod(String ShippingMethod)
            {
                this.shippingMethod = ShippingMethod;
            }
        
            public Double getShippingRate()
            {
                return this.shippingRate;
            }
            
            public void setShippingRate(Double ShippingRate)
            {
                this.shippingRate = ShippingRate;
            }
        
            public Double getShippingAdditional()
            {
                return this.shippingAdditional;
            }
            
            public void setShippingAdditional(Double ShippingAdditional)
            {
                this.shippingAdditional = ShippingAdditional;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Shipping.PROP_SHIPPINGID) || column.equals(Shipping.PROP_SHIPPINGREGION) || column.equals(Shipping.PROP_SHIPPINGMETHOD) || column.equals(Shipping.PROP_SHIPPINGRATE) || column.equals(Shipping.PROP_SHIPPINGADDITIONAL) )
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
            if (column.equals(Shipping.PROP_SHIPPINGID) || column.equals(Shipping.PROP_SHIPPINGRATE) || column.equals(Shipping.PROP_SHIPPINGADDITIONAL) )
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
            return new Shipping(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
        }
        
        public static int addShipping(String ShippingRegion, String ShippingMethod, Double ShippingRate, Double ShippingAdditional)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ShippingRegion, 100);
                checkColumnSize(ShippingMethod, 45);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shipping(ShippingRegion,ShippingMethod,ShippingRate,ShippingAdditional) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, ShippingRegion);
                preparedStatement.setString(2, ShippingMethod);
                preparedStatement.setDouble(3, ShippingRate);
                preparedStatement.setDouble(4, ShippingAdditional);
                
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
                
        public static void updateShipping(Integer ShippingId,String ShippingRegion,String ShippingMethod,Double ShippingRate,Double ShippingAdditional)
        {  
            try
            {   
                
                checkColumnSize(ShippingRegion, 100);
                checkColumnSize(ShippingMethod, 45);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipping SET ShippingRegion=?,ShippingMethod=?,ShippingRate=?,ShippingAdditional=? WHERE ShippingId=?;");                    
                preparedStatement.setString(1, ShippingRegion);
                preparedStatement.setString(2, ShippingMethod);
                preparedStatement.setDouble(3, ShippingRate);
                preparedStatement.setDouble(4, ShippingAdditional);
                preparedStatement.setInt(5, ShippingId);
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

