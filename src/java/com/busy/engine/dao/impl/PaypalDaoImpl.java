





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PaypalDaoImpl extends BasicConnection implements Serializable, PaypalDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Paypal find(Integer id)
        {
            return findByColumn("PaypalId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Paypal> findAll(Integer limit, Integer offset)
        {
            ArrayList<Paypal> paypal = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("paypal");
                while(rs.next())
                {
                    paypal.add(Paypal.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Paypal object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
         
        }
        
        @Override
        public ArrayList<Paypal> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Paypal> paypalList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("paypal", limit, offset);
                while (rs.next())
                {
                    paypalList.add(Paypal.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Paypal method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypalList;
        }
        
        @Override
        public ArrayList<Paypal> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Paypal> paypal = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("paypal", Paypal.checkColumnName(columnName), columnValue, Paypal.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   paypal.add(Paypal.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Paypal's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        } 
    
        @Override
        public void add(Paypal obj)
        {
            try
            {
                
                Paypal.checkColumnSize(obj.getPayPalUrl(), 95);
                Paypal.checkColumnSize(obj.getCurrencyCode(), 5);
                Paypal.checkColumnSize(obj.getApiUsername(), 80);
                Paypal.checkColumnSize(obj.getApiPassword(), 25);
                Paypal.checkColumnSize(obj.getApiSignature(), 65);
                Paypal.checkColumnSize(obj.getApiEndpoint(), 125);
                
                Paypal.checkColumnSize(obj.getReturnUrl(), 255);
                Paypal.checkColumnSize(obj.getCancelUrl(), 255);
                Paypal.checkColumnSize(obj.getPaymentType(), 15);
                Paypal.checkColumnSize(obj.getEnvironment(), 15);
                                            
                openConnection();
                prepareStatement("INSERT INTO paypal(PayPalUrl,CurrencyCode,ApiUsername,ApiPassword,ApiSignature,ApiEndpoint,ActiveProfile,ReturnUrl,CancelUrl,PaymentType,Environment) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getPayPalUrl());
                preparedStatement.setString(2, obj.getCurrencyCode());
                preparedStatement.setString(3, obj.getApiUsername());
                preparedStatement.setString(4, obj.getApiPassword());
                preparedStatement.setString(5, obj.getApiSignature());
                preparedStatement.setString(6, obj.getApiEndpoint());
                preparedStatement.setBoolean(7, obj.getActiveProfile());
                preparedStatement.setString(8, obj.getReturnUrl());
                preparedStatement.setString(9, obj.getCancelUrl());
                preparedStatement.setString(10, obj.getPaymentType());
                preparedStatement.setString(11, obj.getEnvironment());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String PayPalUrl, String CurrencyCode, String ApiUsername, String ApiPassword, String ApiSignature, String ApiEndpoint, Boolean ActiveProfile, String ReturnUrl, String CancelUrl, String PaymentType, String Environment)
        {   
            int id = 0;
            try
            {
                
                Paypal.checkColumnSize(PayPalUrl, 95);
                Paypal.checkColumnSize(CurrencyCode, 5);
                Paypal.checkColumnSize(ApiUsername, 80);
                Paypal.checkColumnSize(ApiPassword, 25);
                Paypal.checkColumnSize(ApiSignature, 65);
                Paypal.checkColumnSize(ApiEndpoint, 125);
                
                Paypal.checkColumnSize(ReturnUrl, 255);
                Paypal.checkColumnSize(CancelUrl, 255);
                Paypal.checkColumnSize(PaymentType, 15);
                Paypal.checkColumnSize(Environment, 15);
                                            
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
        
        
        @Override
        public Paypal update(Paypal obj)
        {
           try
            {   
                
                Paypal.checkColumnSize(obj.getPayPalUrl(), 95);
                Paypal.checkColumnSize(obj.getCurrencyCode(), 5);
                Paypal.checkColumnSize(obj.getApiUsername(), 80);
                Paypal.checkColumnSize(obj.getApiPassword(), 25);
                Paypal.checkColumnSize(obj.getApiSignature(), 65);
                Paypal.checkColumnSize(obj.getApiEndpoint(), 125);
                
                Paypal.checkColumnSize(obj.getReturnUrl(), 255);
                Paypal.checkColumnSize(obj.getCancelUrl(), 255);
                Paypal.checkColumnSize(obj.getPaymentType(), 15);
                Paypal.checkColumnSize(obj.getEnvironment(), 15);
                                  
                openConnection();                           
                prepareStatement("UPDATE paypal SET PayPalUrl=?,CurrencyCode=?,ApiUsername=?,ApiPassword=?,ApiSignature=?,ApiEndpoint=?,ActiveProfile=?,ReturnUrl=?,CancelUrl=?,PaymentType=?,Environment=? WHERE PaypalId=?;");                    
                preparedStatement.setString(1, obj.getPayPalUrl());
                preparedStatement.setString(2, obj.getCurrencyCode());
                preparedStatement.setString(3, obj.getApiUsername());
                preparedStatement.setString(4, obj.getApiPassword());
                preparedStatement.setString(5, obj.getApiSignature());
                preparedStatement.setString(6, obj.getApiEndpoint());
                preparedStatement.setBoolean(7, obj.getActiveProfile());
                preparedStatement.setString(8, obj.getReturnUrl());
                preparedStatement.setString(9, obj.getCancelUrl());
                preparedStatement.setString(10, obj.getPaymentType());
                preparedStatement.setString(11, obj.getEnvironment());
                preparedStatement.setInt(12, obj.getPaypalId());
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
            return obj;
        }
        
        public static void update(Integer PaypalId,String PayPalUrl,String CurrencyCode,String ApiUsername,String ApiPassword,String ApiSignature,String ApiEndpoint,Boolean ActiveProfile,String ReturnUrl,String CancelUrl,String PaymentType,String Environment)
        {  
            try
            {   
                
                Paypal.checkColumnSize(PayPalUrl, 95);
                Paypal.checkColumnSize(CurrencyCode, 5);
                Paypal.checkColumnSize(ApiUsername, 80);
                Paypal.checkColumnSize(ApiPassword, 25);
                Paypal.checkColumnSize(ApiSignature, 65);
                Paypal.checkColumnSize(ApiEndpoint, 125);
                
                Paypal.checkColumnSize(ReturnUrl, 255);
                Paypal.checkColumnSize(CancelUrl, 255);
                Paypal.checkColumnSize(PaymentType, 15);
                Paypal.checkColumnSize(Environment, 15);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("paypal");
        }
        
        
        @Override
        public Paypal getRelatedInfo(Paypal paypal)
        {
              
            
            return paypal;
        }
        
        
        @Override
        public Paypal getRelatedObjects(Paypal paypal)
        {
                         
            return paypal;
        }
        
        
        
        @Override
        public void remove(Paypal obj)
        {            
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + obj.getPaypalId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM paypal;");
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM paypal WHERE " + Paypal.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

