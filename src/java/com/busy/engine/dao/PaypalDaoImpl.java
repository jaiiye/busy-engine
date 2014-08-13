





































    package com.busy.engine.dao;

import com.busy.engine.entity.Paypal;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("Paypal's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return paypal;
        } 
    
        @Override
        public int add(Paypal obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from paypal;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's add method error: " + ex.getMessage());
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
                System.out.println("Paypal's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("paypal");
        }
        
        
        @Override
        public void getRelatedInfo(Paypal paypal)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Paypal paypal)
        {
             
        }
        
        @Override
        public boolean remove(Paypal obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + obj.getPaypalId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM paypal WHERE PaypalId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM paypal;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM paypal WHERE " + Paypal.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Paypal's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

