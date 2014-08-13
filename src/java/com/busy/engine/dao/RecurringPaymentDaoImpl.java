





































    package com.busy.engine.dao;

import com.busy.engine.entity.Order;
import com.busy.engine.entity.RecurringPayment;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class RecurringPaymentDaoImpl extends BasicConnection implements Serializable, RecurringPaymentDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public RecurringPayment find(Integer id)
        {
            return findByColumn("RecurringPaymentId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<RecurringPayment> findAll(Integer limit, Integer offset)
        {
            ArrayList<RecurringPayment> recurring_payment = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("recurring_payment");
                while(rs.next())
                {
                    recurring_payment.add(RecurringPayment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("RecurringPayment object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
         
        }
        
        @Override
        public ArrayList<RecurringPayment> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<RecurringPayment> recurring_paymentList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("recurring_payment", limit, offset);
                while (rs.next())
                {
                    recurring_paymentList.add(RecurringPayment.process(rs));
                }

                
                    for(RecurringPayment recurring_payment : recurring_paymentList)
                    {
                        
                            getRecordById("Order", recurring_payment.getOrderId().toString());
                            recurring_payment.setOrder(Order.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object RecurringPayment method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_paymentList;
        }
        
        @Override
        public ArrayList<RecurringPayment> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<RecurringPayment> recurring_payment = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("recurring_payment", RecurringPayment.checkColumnName(columnName), columnValue, RecurringPayment.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   recurring_payment.add(RecurringPayment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("RecurringPayment's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
        } 
    
        @Override
        public int add(RecurringPayment obj)
        {
            int id = 0;
            try
            {
                
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO recurring_payment(CycleLength,CyclePeriod,TotalCycles,StartDate,OrderId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getCycleLength());
                preparedStatement.setInt(2, obj.getCyclePeriod());
                preparedStatement.setInt(3, obj.getTotalCycles());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setInt(5, obj.getOrderId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from recurring_payment;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public RecurringPayment update(RecurringPayment obj)
        {
           try
            {   
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE recurring_payment SET CycleLength=?,CyclePeriod=?,TotalCycles=?,StartDate=?,OrderId=? WHERE RecurringPaymentId=?;");                    
                preparedStatement.setInt(1, obj.getCycleLength());
                preparedStatement.setInt(2, obj.getCyclePeriod());
                preparedStatement.setInt(3, obj.getTotalCycles());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setInt(5, obj.getOrderId());
                preparedStatement.setInt(6, obj.getRecurringPaymentId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("recurring_payment");
        }
        
        
        @Override
        public void getRelatedInfo(RecurringPayment recurring_payment)
        {
            
                try
                { 
                    
                            getRecordById("Order", recurring_payment.getOrderId().toString());
                            recurring_payment.setOrder(Order.process(rs));                                       
                    
                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
        }
        
        @Override
        public void getRelatedObjects(RecurringPayment recurring_payment)
        {
             
        }
        
        @Override
        public boolean remove(RecurringPayment obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + obj.getRecurringPaymentId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + id + ";");           
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
                updateQuery("DELETE FROM recurring_payment;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM recurring_payment WHERE " + RecurringPayment.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

