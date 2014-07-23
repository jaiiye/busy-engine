





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class EntityStatusDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(EntityStatus.PROP_ENTITY_STATUS_ID) || column.equals(EntityStatus.PROP_STATUS_CODE) || column.equals(EntityStatus.PROP_STATUS_NAME) || column.equals(EntityStatus.PROP_APPLIES_TO) )
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
            if (column.equals(EntityStatus.PROP_ENTITY_STATUS_ID) || column.equals(EntityStatus.PROP_STATUS_CODE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static EntityStatus processEntityStatus(ResultSet rs) throws SQLException
        {        
            return new EntityStatus(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addEntityStatus(Integer StatusCode, String StatusName, String AppliesTo)
        {   
            int id = 0;
            try
            {
                
                
                checkColumnSize(StatusName, 100);
                checkColumnSize(AppliesTo, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO entity_status(StatusCode,StatusName,AppliesTo) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, StatusCode);
                preparedStatement.setString(2, StatusName);
                preparedStatement.setString(3, AppliesTo);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from entity_status;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addEntityStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllEntityStatusCount()
        {
            return getAllRecordsCountByTableName("entity_status");        
        }
        
        public static ArrayList<EntityStatus> getAllEntityStatus()
        {
            ArrayList<EntityStatus> entity_status = new ArrayList<EntityStatus>();
            try
            {
                getAllRecordsByTableName("entity_status");
                while(rs.next())
                {
                    entity_status.add(processEntityStatus(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllEntityStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
        }
        
        public static ArrayList<EntityStatus> getAllEntityStatusWithRelatedInfo()
        {
            ArrayList<EntityStatus> entity_statusList = new ArrayList<EntityStatus>();
            try
            {
                getAllRecordsByTableName("entity_status");
                while (rs.next())
                {
                    entity_statusList.add(processEntityStatus(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllEntityStatusWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_statusList;
        }
        
        
        public static EntityStatus getRelatedInfo(EntityStatus entity_status)
        {
           
                  
            
            return entity_status;
        }
        
        public static EntityStatus getAllRelatedObjects(EntityStatus entity_status)
        {           
                         
            return entity_status;
        }
        
        
        
                
        public static ArrayList<EntityStatus> getEntityStatusPaged(int limit, int offset)
        {
            ArrayList<EntityStatus> entity_status = new ArrayList<EntityStatus>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("entity_status", limit, offset);
                while (rs.next())
                {
                    entity_status.add(processEntityStatus(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getEntityStatusPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
        } 
        
        public static ArrayList<EntityStatus> getAllEntityStatusByColumn(String columnName, String columnValue)
        {
            ArrayList<EntityStatus> entity_status = new ArrayList<EntityStatus>();
            try
            {
                getAllRecordsByColumn("entity_status", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    entity_status.add(processEntityStatus(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllEntityStatusByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
        }
        
        public static EntityStatus getEntityStatusByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            EntityStatus entity_status = new EntityStatus();
            try
            {
                getRecordsByColumnWithLimitAndOffset("entity_status", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   entity_status = processEntityStatus(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getEntityStatusByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
        }                
                
        public static void updateEntityStatus(Integer EntityStatusId,Integer StatusCode,String StatusName,String AppliesTo)
        {  
            try
            {   
                
                
                checkColumnSize(StatusName, 100);
                checkColumnSize(AppliesTo, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE entity_status SET StatusCode=?,StatusName=?,AppliesTo=? WHERE EntityStatusId=?;");                    
                preparedStatement.setInt(1, StatusCode);
                preparedStatement.setString(2, StatusName);
                preparedStatement.setString(3, AppliesTo);
                preparedStatement.setInt(4, EntityStatusId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateEntityStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllEntityStatus()
        {
            try
            {
                updateQuery("DELETE FROM entity_status;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllEntityStatus error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteEntityStatusById(String id)
        {
            try
            {
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteEntityStatusById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteEntityStatusByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM entity_status WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteEntityStatusByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

