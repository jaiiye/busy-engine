











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Paypal;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PaypalDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Paypal.PROP_PAYPAL_ID) || column.equals(Paypal.PROP_PAY_PAL_URL) || column.equals(Paypal.PROP_CURRENCY_CODE) || column.equals(Paypal.PROP_API_USERNAME) || column.equals(Paypal.PROP_API_PASSWORD) || column.equals(Paypal.PROP_API_SIGNATURE) || column.equals(Paypal.PROP_API_ENDPOINT) || column.equals(Paypal.PROP_ACTIVE_PROFILE) || column.equals(Paypal.PROP_RETURN_URL) || column.equals(Paypal.PROP_CANCEL_URL) || column.equals(Paypal.PROP_PAYMENT_TYPE) || column.equals(Paypal.PROP_ENVIRONMENT) )
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
            if (column.equals(Paypal.PROP_PAYPAL_ID) || column.equals(Paypal.PROP_ACTIVE_PROFILE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Paypal processPaypal(ResultSet rs) throws SQLException
        {        
            return new Paypal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
        }
        
        public static int addPaypal(String PayPalUrl, String CurrencyCode, String ApiUsername, String ApiPassword, String ApiSignature, String ApiEndpoint, Boolean ActiveProfile, String ReturnUrl, String CancelUrl, String PaymentType, String Environment)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(PayPalUrl, 95);
                checkColumnSize(CurrencyCode, 5);
                checkColumnSize(ApiUsername, 80);
                checkColumnSize(ApiPassword, 25);
                checkColumnSize(ApiSignature, 65);
                checkColumnSize(ApiEndpoint, 125);
                
                checkColumnSize(ReturnUrl, 255);
                checkColumnSize(CancelUrl, 255);
                checkColumnSize(PaymentType, 15);
                checkColumnSize(Environment, 15);
                                            
                openConnection();
                prepareStatement("INSERT INTO paypal(PayPalUrl,CurrencyCode,ApiUsername,ApiPassword,ApiSignature,ApiEndpoint,ActiveProfile,ReturnUrl,CancelUrl,PaymentType,Environment) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, PayPalUrl);
                preparedStatement.setString(2, CurrencyCode);
                preparedStatement.setString(3, ApiUsername);
                preparedStatement.setString(4, ApiPassword);
                preparedStatement.setString(5, ApiSignature);
                preparedStatement.setString(6, ApiEndpoint);
                preparedStatement.setBoolean(7, ActiveProfile);
                preparedStatement.setString(8, ReturnUrl);
                preparedStatement.setString(9, CancelUrl);
                preparedStatement.setString(10, PaymentType);
                preparedStatement.setString(11, Environment);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from paypal;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addPaypal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllPaypalCount()
        {
            return getAllRecordsCountByTableName("paypal");        
        }
        
        public static ArrayList<Paypal> getAllPaypal()
        {
            ArrayList<Paypal> paypal = new ArrayList<Paypal>();
            try
            {
                getAllRecordsByTableName("paypal");
                while(rs.next())
                {
                    paypal.add(processPaypal(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPaypal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        }
                
        public static ArrayList<Paypal> getPaypalPaged(int limit, int offset)
        {
            ArrayList<Paypal> paypal = new ArrayList<Paypal>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("paypal", limit, offset);
                while (rs.next())
                {
                    paypal.add(processPaypal(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPaypalPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        } 
        
        public static ArrayList<Paypal> getAllPaypalByColumn(String columnName, String columnValue)
        {
            ArrayList<Paypal> paypal = new ArrayList<Paypal>();
            try
            {
                getAllRecordsByColumn("paypal", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    paypal.add(processPaypal(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPaypalByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        }
        
        public static Paypal getPaypalByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Paypal paypal = new Paypal();
            try
            {
                getRecordsByColumnWithLimitAndOffset("paypal", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   paypal = processPaypal(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPaypalByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        }                
                
        public static void updatePaypal(Integer PaypalId,String PayPalUrl,String CurrencyCode,String ApiUsername,String ApiPassword,String ApiSignature,String ApiEndpoint,Boolean ActiveProfile,String ReturnUrl,String CancelUrl,String PaymentType,String Environment)
        {  
            try
            {   
                
                checkColumnSize(PayPalUrl, 95);
                checkColumnSize(CurrencyCode, 5);
                checkColumnSize(ApiUsername, 80);
                checkColumnSize(ApiPassword, 25);
                checkColumnSize(ApiSignature, 65);
                checkColumnSize(ApiEndpoint, 125);
                
                checkColumnSize(ReturnUrl, 255);
                checkColumnSize(CancelUrl, 255);
                checkColumnSize(PaymentType, 15);
                checkColumnSize(Environment, 15);
                                  
                openConnection();                           
                prepareStatement("UPDATE paypal SET PayPalUrl=?,CurrencyCode=?,ApiUsername=?,ApiPassword=?,ApiSignature=?,ApiEndpoint=?,ActiveProfile=?,ReturnUrl=?,CancelUrl=?,PaymentType=?,Environment=? WHERE PaypalId=?;");                    
                preparedStatement.setString(1, PayPalUrl);
                preparedStatement.setString(2, CurrencyCode);
                preparedStatement.setString(3, ApiUsername);
                preparedStatement.setString(4, ApiPassword);
                preparedStatement.setString(5, ApiSignature);
                preparedStatement.setString(6, ApiEndpoint);
                preparedStatement.setBoolean(7, ActiveProfile);
                preparedStatement.setString(8, ReturnUrl);
                preparedStatement.setString(9, CancelUrl);
                preparedStatement.setString(10, PaymentType);
                preparedStatement.setString(11, Environment);
                preparedStatement.setInt(12, PaypalId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updatePaypal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllPaypal()
        {
            try
            {
                updateQuery("DELETE FROM paypal;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllPaypal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deletePaypalById(String id)
        {
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deletePaypalById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deletePaypalByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM paypal WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deletePaypalByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

