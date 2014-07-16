











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Status;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class StatusDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Status.PROP_STATUS_ID) || column.equals(Status.PROP_CODE) || column.equals(Status.PROP_STATUS_NAME) || column.equals(Status.PROP_APPLIES_TO) )
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
            if (column.equals(Status.PROP_STATUS_ID) || column.equals(Status.PROP_CODE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Status processStatus(ResultSet rs) throws SQLException
        {        
            return new Status(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addStatus(Integer Code, String StatusName, String AppliesTo)
        {   
            int id = 0;
            try
            {
                
                
                checkColumnSize(StatusName, 100);
                checkColumnSize(AppliesTo, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO status(Code,StatusName,AppliesTo) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, Code);
                preparedStatement.setString(2, StatusName);
                preparedStatement.setString(3, AppliesTo);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from status;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllStatusCount()
        {
            return getAllRecordsCountByTableName("status");        
        }
        
        public static ArrayList<Status> getAllStatus()
        {
            ArrayList<Status> status = new ArrayList<Status>();
            try
            {
                getAllRecordsByTableName("status");
                while(rs.next())
                {
                    status.add(processStatus(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return status;
        }
                
        public static ArrayList<Status> getStatusPaged(int limit, int offset)
        {
            ArrayList<Status> status = new ArrayList<Status>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("status", limit, offset);
                while (rs.next())
                {
                    status.add(processStatus(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getStatusPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return status;
        } 
        
        public static ArrayList<Status> getAllStatusByColumn(String columnName, String columnValue)
        {
            ArrayList<Status> status = new ArrayList<Status>();
            try
            {
                getAllRecordsByColumn("status", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    status.add(processStatus(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStatusByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return status;
        }
        
        public static Status getStatusByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Status status = new Status();
            try
            {
                getRecordsByColumnWithLimitAndOffset("status", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   status = processStatus(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getStatusByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return status;
        }                
                
        public static void updateStatus(Integer StatusId,Integer Code,String StatusName,String AppliesTo)
        {  
            try
            {   
                
                
                checkColumnSize(StatusName, 100);
                checkColumnSize(AppliesTo, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE status SET Code=?,StatusName=?,AppliesTo=? WHERE StatusId=?;");                    
                preparedStatement.setInt(1, Code);
                preparedStatement.setString(2, StatusName);
                preparedStatement.setString(3, AppliesTo);
                preparedStatement.setInt(4, StatusId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllStatus()
        {
            try
            {
                updateQuery("DELETE FROM status;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteStatusById(String id)
        {
            try
            {
                updateQuery("DELETE FROM status WHERE StatusId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteStatusById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteStatusByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM status WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteStatusByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

