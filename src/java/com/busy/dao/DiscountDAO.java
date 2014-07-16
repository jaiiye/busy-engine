


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class DiscountDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Discount.PROP_DISCOUNT_ID) || column.equals(Discount.PROP_DISCOUNT_NAME) || column.equals(Discount.PROP_DISCOUNT_AMOUNT) || column.equals(Discount.PROP_DISCOUNT_PERCENT) || column.equals(Discount.PROP_START_DATE) || column.equals(Discount.PROP_END_DATE) || column.equals(Discount.PROP_COUPON_CODE) || column.equals(Discount.PROP_STATUS) || column.equals(Discount.PROP_ASK_COUPON_CODE) || column.equals(Discount.PROP_USE_PERCENTAGE) )
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
            if (column.equals(Discount.PROP_DISCOUNT_ID) || column.equals(Discount.PROP_DISCOUNT_AMOUNT) || column.equals(Discount.PROP_DISCOUNT_PERCENT) || column.equals(Discount.PROP_STATUS) || column.equals(Discount.PROP_ASK_COUPON_CODE) || column.equals(Discount.PROP_USE_PERCENTAGE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Discount processDiscount(ResultSet rs) throws SQLException
        {        
            return new Discount(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getBoolean(10));
        }
        
        public static int addDiscount(String DiscountName, Double DiscountAmount, Double DiscountPercent, Date StartDate, Date EndDate, String CouponCode, Integer Status, Boolean AskCouponCode, Boolean UsePercentage)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(DiscountName, 100);
                
                
                
                
                checkColumnSize(CouponCode, 100);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO discount(DiscountName,DiscountAmount,DiscountPercent,StartDate,EndDate,CouponCode,Status,AskCouponCode,UsePercentage) VALUES (?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, DiscountName);
                preparedStatement.setDouble(2, DiscountAmount);
                preparedStatement.setDouble(3, DiscountPercent);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(6, CouponCode);
                preparedStatement.setInt(7, Status);
                preparedStatement.setBoolean(8, AskCouponCode);
                preparedStatement.setBoolean(9, UsePercentage);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from discount;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllDiscountCount()
        {
            return getAllRecordsCountByTableName("discount");        
        }
        
        public static ArrayList<Discount> getAllDiscount()
        {
            ArrayList<Discount> discount = new ArrayList<Discount>();
            try
            {
                getAllRecordsByTableName("discount");
                while(rs.next())
                {
                    discount.add(processDiscount(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        }
                
        public static ArrayList<Discount> getDiscountPaged(int limit, int offset)
        {
            ArrayList<Discount> discount = new ArrayList<Discount>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("discount", limit, offset);
                while (rs.next())
                {
                    discount.add(processDiscount(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getDiscountPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        } 
        
        public static ArrayList<Discount> getAllDiscountByColumn(String columnName, String columnValue)
        {
            ArrayList<Discount> discount = new ArrayList<Discount>();
            try
            {
                getAllRecordsByColumn("discount", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    discount.add(processDiscount(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllDiscountByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        }
        
        public static Discount getDiscountByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Discount discount = new Discount();
            try
            {
                getRecordsByColumnWithLimitAndOffset("discount", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   discount = processDiscount(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getDiscountByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        }                
                
        public static void updateDiscount(Integer DiscountId,String DiscountName,Double DiscountAmount,Double DiscountPercent,Date StartDate,Date EndDate,String CouponCode,Integer Status,Boolean AskCouponCode,Boolean UsePercentage)
        {  
            try
            {   
                
                checkColumnSize(DiscountName, 100);
                
                
                
                
                checkColumnSize(CouponCode, 100);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE discount SET DiscountName=?,DiscountAmount=?,DiscountPercent=?,StartDate=?,EndDate=?,CouponCode=?,Status=?,AskCouponCode=?,UsePercentage=? WHERE DiscountId=?;");                    
                preparedStatement.setString(1, DiscountName);
                preparedStatement.setDouble(2, DiscountAmount);
                preparedStatement.setDouble(3, DiscountPercent);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(6, CouponCode);
                preparedStatement.setInt(7, Status);
                preparedStatement.setBoolean(8, AskCouponCode);
                preparedStatement.setBoolean(9, UsePercentage);
                preparedStatement.setInt(10, DiscountId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllDiscount()
        {
            try
            {
                updateQuery("DELETE FROM discount;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteDiscountById(String id)
        {
            try
            {
                updateQuery("DELETE FROM discount WHERE DiscountId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteDiscountById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteDiscountByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM discount WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteDiscountByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

