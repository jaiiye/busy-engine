





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object RecurringPayment's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return recurring_payment;
        } 
    
        @Override
        public void add(RecurringPayment obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer CycleLength, Integer CyclePeriod, Integer TotalCycles, Date StartDate, Integer OrderId)
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
                System.out.println("updateRecurringPayment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer RecurringPaymentId,Integer CycleLength,Integer CyclePeriod,Integer TotalCycles,Date StartDate,Integer OrderId)
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("recurring_payment");
        }
        
        
        @Override
        public RecurringPayment getRelatedInfo(RecurringPayment recurring_payment)
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
              
            
            return recurring_payment;
        }
        
        
        @Override
        public RecurringPayment getRelatedObjects(RecurringPayment recurring_payment)
        {
                         
            return recurring_payment;
        }
        
        
        
        @Override
        public void remove(RecurringPayment obj)
        {            
            try
            {
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + obj.getRecurringPaymentId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM recurring_payment WHERE RecurringPaymentId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM recurring_payment;");
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM recurring_payment WHERE " + RecurringPayment.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("RecurringPayment's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

