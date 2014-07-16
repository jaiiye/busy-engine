











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.ReturnRequest;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ReturnRequestDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ReturnRequest.PROP_RETURN_REQUEST_ID) || column.equals(ReturnRequest.PROP_QUANTITY) || column.equals(ReturnRequest.PROP_REQUEST_DATE) || column.equals(ReturnRequest.PROP_RETURN_REASON) || column.equals(ReturnRequest.PROP_REQUESTED_ACTION) || column.equals(ReturnRequest.PROP_NOTES) || column.equals(ReturnRequest.PROP_STATUS) || column.equals(ReturnRequest.PROP_ORDER_ITEM_ID) )
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
            if (column.equals(ReturnRequest.PROP_RETURN_REQUEST_ID) || column.equals(ReturnRequest.PROP_QUANTITY) || column.equals(ReturnRequest.PROP_STATUS) || column.equals(ReturnRequest.PROP_ORDER_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ReturnRequest processReturnRequest(ResultSet rs) throws SQLException
        {        
            return new ReturnRequest(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addReturnRequest(Integer Quantity, Date RequestDate, String ReturnReason, String RequestedAction, String Notes, Integer Status, Integer OrderItemId)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(ReturnReason, 255);
                checkColumnSize(RequestedAction, 255);
                checkColumnSize(Notes, 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO return_request(Quantity,RequestDate,ReturnReason,RequestedAction,Notes,Status,OrderItemId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, Quantity);
                preparedStatement.setDate(2, new java.sql.Date(RequestDate.getTime()));
                preparedStatement.setString(3, ReturnReason);
                preparedStatement.setString(4, RequestedAction);
                preparedStatement.setString(5, Notes);
                preparedStatement.setInt(6, Status);
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
        
        public static int getAllReturnRequestCount()
        {
            return getAllRecordsCountByTableName("return_request");        
        }
        
        public static ArrayList<ReturnRequest> getAllReturnRequest()
        {
            ArrayList<ReturnRequest> return_request = new ArrayList<ReturnRequest>();
            try
            {
                getAllRecordsByTableName("return_request");
                while(rs.next())
                {
                    return_request.add(processReturnRequest(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllReturnRequest error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
        }
                
        public static ArrayList<ReturnRequest> getReturnRequestPaged(int limit, int offset)
        {
            ArrayList<ReturnRequest> return_request = new ArrayList<ReturnRequest>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("return_request", limit, offset);
                while (rs.next())
                {
                    return_request.add(processReturnRequest(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getReturnRequestPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
        } 
        
        public static ArrayList<ReturnRequest> getAllReturnRequestByColumn(String columnName, String columnValue)
        {
            ArrayList<ReturnRequest> return_request = new ArrayList<ReturnRequest>();
            try
            {
                getAllRecordsByColumn("return_request", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    return_request.add(processReturnRequest(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllReturnRequestByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
        }
        
        public static ReturnRequest getReturnRequestByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ReturnRequest return_request = new ReturnRequest();
            try
            {
                getRecordsByColumnWithLimitAndOffset("return_request", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   return_request = processReturnRequest(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getReturnRequestByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return return_request;
        }                
                
        public static void updateReturnRequest(Integer ReturnRequestId,Integer Quantity,Date RequestDate,String ReturnReason,String RequestedAction,String Notes,Integer Status,Integer OrderItemId)
        {  
            try
            {   
                
                
                
                checkColumnSize(ReturnReason, 255);
                checkColumnSize(RequestedAction, 255);
                checkColumnSize(Notes, 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE return_request SET Quantity=?,RequestDate=?,ReturnReason=?,RequestedAction=?,Notes=?,Status=?,OrderItemId=? WHERE ReturnRequestId=?;");                    
                preparedStatement.setInt(1, Quantity);
                preparedStatement.setDate(2, new java.sql.Date(RequestDate.getTime()));
                preparedStatement.setString(3, ReturnReason);
                preparedStatement.setString(4, RequestedAction);
                preparedStatement.setString(5, Notes);
                preparedStatement.setInt(6, Status);
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
        
        public static void deleteAllReturnRequest()
        {
            try
            {
                updateQuery("DELETE FROM return_request;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllReturnRequest error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteReturnRequestById(String id)
        {
            try
            {
                updateQuery("DELETE FROM return_request WHERE ReturnRequestId=" + id + ";");            
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

        public static void deleteReturnRequestByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM return_request WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteReturnRequestByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

