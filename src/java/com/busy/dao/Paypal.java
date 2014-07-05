


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Paypal extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_PAYPALID = "PaypalId";
        public static final String PROP_PAYPALURL = "PayPalURL";
        public static final String PROP_CURRENCYCODE = "CurrencyCode";
        public static final String PROP_APIUSERNAME = "ApiUserName";
        public static final String PROP_APIPASSWORD = "ApiPassword";
        public static final String PROP_APISIGNATURE = "ApiSignature";
        public static final String PROP_APIENDPOINT = "ApiEndpoint";
        public static final String PROP_ACTIVEPROFILE = "ActiveProfile";
        public static final String PROP_RETURNURL = "ReturnURL";
        public static final String PROP_CANCELURL = "CancelURL";
        public static final String PROP_PAYMENTTYPE = "PaymentType";
        public static final String PROP_ENVIRONMENT = "Environment";
        
        
        private Integer paypalId;
        private String payPalURL;
        private String currencyCode;
        private String apiUserName;
        private String apiPassword;
        private String apiSignature;
        private String apiEndpoint;
        private Boolean activeProfile;
        private String returnURL;
        private String cancelURL;
        private String paymentType;
        private String environment;
        
        
        public Paypal()
        {
            this.paypalId = 0; 
       this.payPalURL = ""; 
       this.currencyCode = ""; 
       this.apiUserName = ""; 
       this.apiPassword = ""; 
       this.apiSignature = ""; 
       this.apiEndpoint = ""; 
       this.activeProfile = null; 
       this.returnURL = ""; 
       this.cancelURL = ""; 
       this.paymentType = ""; 
       this.environment = ""; 
        }
        
        public Paypal(Integer PaypalId, String PayPalURL, String CurrencyCode, String ApiUserName, String ApiPassword, String ApiSignature, String ApiEndpoint, Boolean ActiveProfile, String ReturnURL, String CancelURL, String PaymentType, String Environment)
        {
            this.paypalId = PaypalId;
       this.payPalURL = PayPalURL;
       this.currencyCode = CurrencyCode;
       this.apiUserName = ApiUserName;
       this.apiPassword = ApiPassword;
       this.apiSignature = ApiSignature;
       this.apiEndpoint = ApiEndpoint;
       this.activeProfile = ActiveProfile;
       this.returnURL = ReturnURL;
       this.cancelURL = CancelURL;
       this.paymentType = PaymentType;
       this.environment = Environment;
        } 
        
        
            public Integer getPaypalId()
            {
                return this.paypalId;
            }
            
            public void setPaypalId(Integer PaypalId)
            {
                this.paypalId = PaypalId;
            }
        
            public String getPayPalURL()
            {
                return this.payPalURL;
            }
            
            public void setPayPalURL(String PayPalURL)
            {
                this.payPalURL = PayPalURL;
            }
        
            public String getCurrencyCode()
            {
                return this.currencyCode;
            }
            
            public void setCurrencyCode(String CurrencyCode)
            {
                this.currencyCode = CurrencyCode;
            }
        
            public String getApiUserName()
            {
                return this.apiUserName;
            }
            
            public void setApiUserName(String ApiUserName)
            {
                this.apiUserName = ApiUserName;
            }
        
            public String getApiPassword()
            {
                return this.apiPassword;
            }
            
            public void setApiPassword(String ApiPassword)
            {
                this.apiPassword = ApiPassword;
            }
        
            public String getApiSignature()
            {
                return this.apiSignature;
            }
            
            public void setApiSignature(String ApiSignature)
            {
                this.apiSignature = ApiSignature;
            }
        
            public String getApiEndpoint()
            {
                return this.apiEndpoint;
            }
            
            public void setApiEndpoint(String ApiEndpoint)
            {
                this.apiEndpoint = ApiEndpoint;
            }
        
            public Boolean getActiveProfile()
            {
                return this.activeProfile;
            }
            
            public void setActiveProfile(Boolean ActiveProfile)
            {
                this.activeProfile = ActiveProfile;
            }
        
            public String getReturnURL()
            {
                return this.returnURL;
            }
            
            public void setReturnURL(String ReturnURL)
            {
                this.returnURL = ReturnURL;
            }
        
            public String getCancelURL()
            {
                return this.cancelURL;
            }
            
            public void setCancelURL(String CancelURL)
            {
                this.cancelURL = CancelURL;
            }
        
            public String getPaymentType()
            {
                return this.paymentType;
            }
            
            public void setPaymentType(String PaymentType)
            {
                this.paymentType = PaymentType;
            }
        
            public String getEnvironment()
            {
                return this.environment;
            }
            
            public void setEnvironment(String Environment)
            {
                this.environment = Environment;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Paypal.PROP_PAYPALID) || column.equals(Paypal.PROP_PAYPALURL) || column.equals(Paypal.PROP_CURRENCYCODE) || column.equals(Paypal.PROP_APIUSERNAME) || column.equals(Paypal.PROP_APIPASSWORD) || column.equals(Paypal.PROP_APISIGNATURE) || column.equals(Paypal.PROP_APIENDPOINT) || column.equals(Paypal.PROP_ACTIVEPROFILE) || column.equals(Paypal.PROP_RETURNURL) || column.equals(Paypal.PROP_CANCELURL) || column.equals(Paypal.PROP_PAYMENTTYPE) || column.equals(Paypal.PROP_ENVIRONMENT) )
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
            if (column.equals(Paypal.PROP_PAYPALID) || column.equals(Paypal.PROP_ACTIVEPROFILE) )
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
        
        public static int addPaypal(String PayPalURL, String CurrencyCode, String ApiUserName, String ApiPassword, String ApiSignature, String ApiEndpoint, Boolean ActiveProfile, String ReturnURL, String CancelURL, String PaymentType, String Environment)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(PayPalURL, 95);
                checkColumnSize(CurrencyCode, 5);
                checkColumnSize(ApiUserName, 80);
                checkColumnSize(ApiPassword, 25);
                checkColumnSize(ApiSignature, 65);
                checkColumnSize(ApiEndpoint, 125);
                
                checkColumnSize(ReturnURL, 255);
                checkColumnSize(CancelURL, 255);
                checkColumnSize(PaymentType, 15);
                checkColumnSize(Environment, 15);
                                            
                openConnection();
                prepareStatement("INSERT INTO paypal(PayPalURL,CurrencyCode,ApiUserName,ApiPassword,ApiSignature,ApiEndpoint,ActiveProfile,ReturnURL,CancelURL,PaymentType,Environment) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, PayPalURL);
                preparedStatement.setString(2, CurrencyCode);
                preparedStatement.setString(3, ApiUserName);
                preparedStatement.setString(4, ApiPassword);
                preparedStatement.setString(5, ApiSignature);
                preparedStatement.setString(6, ApiEndpoint);
                preparedStatement.setBoolean(7, ActiveProfile);
                preparedStatement.setString(8, ReturnURL);
                preparedStatement.setString(9, CancelURL);
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
                
        public static void updatePaypal(Integer PaypalId,String PayPalURL,String CurrencyCode,String ApiUserName,String ApiPassword,String ApiSignature,String ApiEndpoint,Boolean ActiveProfile,String ReturnURL,String CancelURL,String PaymentType,String Environment)
        {  
            try
            {   
                
                checkColumnSize(PayPalURL, 95);
                checkColumnSize(CurrencyCode, 5);
                checkColumnSize(ApiUserName, 80);
                checkColumnSize(ApiPassword, 25);
                checkColumnSize(ApiSignature, 65);
                checkColumnSize(ApiEndpoint, 125);
                
                checkColumnSize(ReturnURL, 255);
                checkColumnSize(CancelURL, 255);
                checkColumnSize(PaymentType, 15);
                checkColumnSize(Environment, 15);
                                  
                openConnection();                           
                prepareStatement("UPDATE paypal SET PayPalURL=?,CurrencyCode=?,ApiUserName=?,ApiPassword=?,ApiSignature=?,ApiEndpoint=?,ActiveProfile=?,ReturnURL=?,CancelURL=?,PaymentType=?,Environment=? WHERE PaypalId=?;");                    
                preparedStatement.setString(1, PayPalURL);
                preparedStatement.setString(2, CurrencyCode);
                preparedStatement.setString(3, ApiUserName);
                preparedStatement.setString(4, ApiPassword);
                preparedStatement.setString(5, ApiSignature);
                preparedStatement.setString(6, ApiEndpoint);
                preparedStatement.setBoolean(7, ActiveProfile);
                preparedStatement.setString(8, ReturnURL);
                preparedStatement.setString(9, CancelURL);
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

