





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ReturnRequest's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
        } 
    
        @Override
        public void add(ReturnRequest obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer Quantity, Date RequestDate, String ReturnReason, String RequestedAction, String Notes, Integer RequestStatus, Integer OrderItemId)
        {   
            int id = 0;
            try
            {
                
                
                
                ReturnRequest.checkColumnSize(ReturnReason, 255);
                ReturnRequest.checkColumnSize(RequestedAction, 255);
                ReturnRequest.checkColumnSize(Notes, 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO return_request(Quantity,RequestDate,ReturnReason,RequestedAction,Notes,RequestStatus,OrderItemId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, Quantity);
                preparedStatement.setDate(2, new java.sql.Date(RequestDate.getTime()));
                preparedStatement.setString(3, ReturnReason);
                preparedStatement.setString(4, RequestedAction);
                preparedStatement.setString(5, Notes);
                preparedStatement.setInt(6, RequestStatus);
                preparedStatement.setInt(7, OrderItemId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from return_request;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addReturnRequest error: " + ex.getMessage());
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
                System.out.println("updateReturnRequest error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ReturnRequestId,Integer Quantity,Date RequestDate,String ReturnReason,String RequestedAction,String Notes,Integer RequestStatus,Integer OrderItemId)
        {  
            try
            {   
                
                
                
                ReturnRequest.checkColumnSize(ReturnReason, 255);
                ReturnRequest.checkColumnSize(RequestedAction, 255);
                ReturnRequest.checkColumnSize(Notes, 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE return_request SET Quantity=?,RequestDate=?,ReturnReason=?,RequestedAction=?,Notes=?,RequestStatus=?,OrderItemId=? WHERE ReturnRequestId=?;");                    
                preparedStatement.setInt(1, Quantity);
                preparedStatement.setDate(2, new java.sql.Date(RequestDate.getTime()));
                preparedStatement.setString(3, ReturnReason);
                preparedStatement.setString(4, RequestedAction);
                preparedStatement.setString(5, Notes);
                preparedStatement.setInt(6, RequestStatus);
                preparedStatement.setInt(7, OrderItemId);
                preparedStatement.setInt(8, ReturnRequestId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateReturnRequest error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("return_request");
        }
        
        
        @Override
        public ReturnRequest getRelatedInfo(ReturnRequest return_request)
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
              
            
            return return_request;
        }
        
        
        @Override
        public ReturnRequest getRelatedObjects(ReturnRequest return_request)
        {
                         
            return return_request;
        }
        
        
        
        @Override
        public void remove(ReturnRequest obj)
        {            
            try
            {
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + obj.getReturnRequestId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteReturnRequestById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteReturnRequestById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM return_request;");
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM return_request WHERE " + ReturnRequest.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ReturnRequest's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

