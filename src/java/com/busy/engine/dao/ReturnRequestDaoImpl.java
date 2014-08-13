





































    package com.busy.engine.dao;

import com.busy.engine.entity.OrderItem;
import com.busy.engine.entity.ReturnRequest;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ReturnRequestDaoImpl extends BasicConnection implements Serializable, ReturnRequestDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ReturnRequest find(Integer id)
        {
            return findByColumn("ReturnRequestId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ReturnRequest> findAll(Integer limit, Integer offset)
        {
            ArrayList<ReturnRequest> return_request = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("return_request");
                while(rs.next())
                {
                    return_request.add(ReturnRequest.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ReturnRequest object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
         
        }
        
        @Override
        public ArrayList<ReturnRequest> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ReturnRequest> return_requestList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("return_request", limit, offset);
                while (rs.next())
                {
                    return_requestList.add(ReturnRequest.process(rs));
                }

                
                    for(ReturnRequest return_request : return_requestList)
                    {
                        
                            getRecordById("OrderItem", return_request.getOrderItemId().toString());
                            return_request.setOrderItem(OrderItem.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ReturnRequest method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_requestList;
        }
        
        @Override
        public ArrayList<ReturnRequest> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ReturnRequest> return_request = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("return_request", ReturnRequest.checkColumnName(columnName), columnValue, ReturnRequest.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   return_request.add(ReturnRequest.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ReturnRequest's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
        } 
    
        @Override
        public int add(ReturnRequest obj)
        {
            int id = 0;
            try
            {
                
                
                
                ReturnRequest.checkColumnSize(obj.getReturnReason(), 255);
                ReturnRequest.checkColumnSize(obj.getRequestedAction(), 255);
                ReturnRequest.checkColumnSize(obj.getNotes(), 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO return_request(Quantity,RequestDate,ReturnReason,RequestedAction,Notes,RequestStatus,OrderItemId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getQuantity());
                preparedStatement.setDate(2, new java.sql.Date(obj.getRequestDate().getTime()));
                preparedStatement.setString(3, obj.getReturnReason());
                preparedStatement.setString(4, obj.getRequestedAction());
                preparedStatement.setString(5, obj.getNotes());
                preparedStatement.setInt(6, obj.getRequestStatus());
                preparedStatement.setInt(7, obj.getOrderItemId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from return_request;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ReturnRequest update(ReturnRequest obj)
        {
           try
            {   
                
                
                
                ReturnRequest.checkColumnSize(obj.getReturnReason(), 255);
                ReturnRequest.checkColumnSize(obj.getRequestedAction(), 255);
                ReturnRequest.checkColumnSize(obj.getNotes(), 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE return_request SET Quantity=?,RequestDate=?,ReturnReason=?,RequestedAction=?,Notes=?,RequestStatus=?,OrderItemId=? WHERE ReturnRequestId=?;");                    
                preparedStatement.setInt(1, obj.getQuantity());
                preparedStatement.setDate(2, new java.sql.Date(obj.getRequestDate().getTime()));
                preparedStatement.setString(3, obj.getReturnReason());
                preparedStatement.setString(4, obj.getRequestedAction());
                preparedStatement.setString(5, obj.getNotes());
                preparedStatement.setInt(6, obj.getRequestStatus());
                preparedStatement.setInt(7, obj.getOrderItemId());
                preparedStatement.setInt(8, obj.getReturnRequestId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("return_request");
        }
        
        
        @Override
        public void getRelatedInfo(ReturnRequest return_request)
        {
            
                try
                { 
                    
                            getRecordById("OrderItem", return_request.getOrderItemId().toString());
                            return_request.setOrderItem(OrderItem.process(rs));                                       
                    
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
        public void getRelatedObjects(ReturnRequest return_request)
        {
             
        }
        
        @Override
        public boolean remove(ReturnRequest obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + obj.getReturnRequestId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + id + ";");           
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
                updateQuery("DELETE FROM return_request;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM return_request WHERE " + ReturnRequest.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

