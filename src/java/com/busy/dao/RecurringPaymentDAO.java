











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.RecurringPayment;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class RecurringPaymentDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(RecurringPayment.PROP_RECURRING_PAYMENT_ID) || column.equals(RecurringPayment.PROP_CYCLE_LENGTH) || column.equals(RecurringPayment.PROP_CYCLE_PERIOD) || column.equals(RecurringPayment.PROP_TOTAL_CYCLES) || column.equals(RecurringPayment.PROP_START_DATE) || column.equals(RecurringPayment.PROP_ORDER_ID) )
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
            if (column.equals(RecurringPayment.PROP_RECURRING_PAYMENT_ID) || column.equals(RecurringPayment.PROP_CYCLE_LENGTH) || column.equals(RecurringPayment.PROP_CYCLE_PERIOD) || column.equals(RecurringPayment.PROP_TOTAL_CYCLES) || column.equals(RecurringPayment.PROP_ORDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static RecurringPayment processRecurringPayment(ResultSet rs) throws SQLException
        {        
            return new RecurringPayment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getInt(6));
        }
        
        public static int addRecurringPayment(Integer CycleLength, Integer CyclePeriod, Integer TotalCycles, Date StartDate, Integer OrderId)
        {   
            int id = 0;
            try
            {
                
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO recurring_payment(CycleLength,CyclePeriod,TotalCycles,StartDate,OrderId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, CycleLength);
                preparedStatement.setInt(2, CyclePeriod);
                preparedStatement.setInt(3, TotalCycles);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setInt(5, OrderId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from recurring_payment;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addRecurringPayment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllRecurringPaymentCount()
        {
            return getAllRecordsCountByTableName("recurring_payment");        
        }
        
        public static ArrayList<RecurringPayment> getAllRecurringPayment()
        {
            ArrayList<RecurringPayment> recurring_payment = new ArrayList<RecurringPayment>();
            try
            {
                getAllRecordsByTableName("recurring_payment");
                while(rs.next())
                {
                    recurring_payment.add(processRecurringPayment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllRecurringPayment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
        }
                
        public static ArrayList<RecurringPayment> getRecurringPaymentPaged(int limit, int offset)
        {
            ArrayList<RecurringPayment> recurring_payment = new ArrayList<RecurringPayment>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("recurring_payment", limit, offset);
                while (rs.next())
                {
                    recurring_payment.add(processRecurringPayment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getRecurringPaymentPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
        } 
        
        public static ArrayList<RecurringPayment> getAllRecurringPaymentByColumn(String columnName, String columnValue)
        {
            ArrayList<RecurringPayment> recurring_payment = new ArrayList<RecurringPayment>();
            try
            {
                getAllRecordsByColumn("recurring_payment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    recurring_payment.add(processRecurringPayment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllRecurringPaymentByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
        }
        
        public static RecurringPayment getRecurringPaymentByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            RecurringPayment recurring_payment = new RecurringPayment();
            try
            {
                getRecordsByColumnWithLimitAndOffset("recurring_payment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   recurring_payment = processRecurringPayment(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getRecurringPaymentByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
        }                
                
        public static void updateRecurringPayment(Integer RecurringPaymentId,Integer CycleLength,Integer CyclePeriod,Integer TotalCycles,Date StartDate,Integer OrderId)
        {  
            try
            {   
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE recurring_payment SET CycleLength=?,CyclePeriod=?,TotalCycles=?,StartDate=?,OrderId=? WHERE RecurringPaymentId=?;");                    
                preparedStatement.setInt(1, CycleLength);
                preparedStatement.setInt(2, CyclePeriod);
                preparedStatement.setInt(3, TotalCycles);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setInt(5, OrderId);
                preparedStatement.setInt(6, RecurringPaymentId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateRecurringPayment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllRecurringPayment()
        {
            try
            {
                updateQuery("DELETE FROM recurring_payment;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllRecurringPayment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteRecurringPaymentById(String id)
        {
            try
            {
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteRecurringPaymentById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteRecurringPaymentByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM recurring_payment WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteRecurringPaymentByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

