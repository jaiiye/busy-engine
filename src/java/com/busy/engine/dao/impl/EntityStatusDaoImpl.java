





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class EntityStatusDaoImpl extends BasicConnection implements Serializable, EntityStatusDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public EntityStatus find(Integer id)
        {
            return findByColumn("EntityStatusId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<EntityStatus> findAll(Integer limit, Integer offset)
        {
            ArrayList<EntityStatus> entity_status = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("entity_status");
                while(rs.next())
                {
                    entity_status.add(EntityStatus.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("EntityStatus object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
         
        }
        
        @Override
        public ArrayList<EntityStatus> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<EntityStatus> entity_statusList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("entity_status", limit, offset);
                while (rs.next())
                {
                    entity_statusList.add(EntityStatus.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object EntityStatus method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_statusList;
        }
        
        @Override
        public ArrayList<EntityStatus> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<EntityStatus> entity_status = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("entity_status", EntityStatus.checkColumnName(columnName), columnValue, EntityStatus.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   entity_status.add(EntityStatus.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object EntityStatus's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
        } 
    
        @Override
        public void add(EntityStatus obj)
        {
            try
            {
                
                
                EntityStatus.checkColumnSize(obj.getStatusName(), 100);
                EntityStatus.checkColumnSize(obj.getAppliesTo(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO entity_status(StatusCode,StatusName,AppliesTo) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, obj.getStatusCode());
                preparedStatement.setString(2, obj.getStatusName());
                preparedStatement.setString(3, obj.getAppliesTo());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer StatusCode, String StatusName, String AppliesTo)
        {   
            int id = 0;
            try
            {
                
                
                EntityStatus.checkColumnSize(StatusName, 100);
                EntityStatus.checkColumnSize(AppliesTo, 100);
                                            
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
        
        
        @Override
        public EntityStatus update(EntityStatus obj)
        {
           try
            {   
                
                
                EntityStatus.checkColumnSize(obj.getStatusName(), 100);
                EntityStatus.checkColumnSize(obj.getAppliesTo(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE entity_status SET StatusCode=?,StatusName=?,AppliesTo=? WHERE EntityStatusId=?;");                    
                preparedStatement.setInt(1, obj.getStatusCode());
                preparedStatement.setString(2, obj.getStatusName());
                preparedStatement.setString(3, obj.getAppliesTo());
                preparedStatement.setInt(4, obj.getEntityStatusId());
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
            return obj;
        }
        
        public static void update(Integer EntityStatusId,Integer StatusCode,String StatusName,String AppliesTo)
        {  
            try
            {   
                
                
                EntityStatus.checkColumnSize(StatusName, 100);
                EntityStatus.checkColumnSize(AppliesTo, 100);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("entity_status");
        }
        
        
        @Override
        public EntityStatus getRelatedInfo(EntityStatus entity_status)
        {
              
            
            return entity_status;
        }
        
        
        @Override
        public EntityStatus getRelatedObjects(EntityStatus entity_status)
        {
                         
            return entity_status;
        }
        
        
        
        @Override
        public void remove(EntityStatus obj)
        {            
            try
            {
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + obj.getEntityStatusId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM entity_status;");
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM entity_status WHERE " + EntityStatus.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

