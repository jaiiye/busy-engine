





































    package com.busy.engine.dao;

import com.busy.engine.entity.EntityStatus;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("EntityStatus's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entity_status;
        } 
    
        @Override
        public int add(EntityStatus obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from entity_status;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's add method error: " + ex.getMessage());
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
                System.out.println("EntityStatus's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("entity_status");
        }
        
        
        @Override
        public void getRelatedInfo(EntityStatus entity_status)
        {
              
        }
        
        @Override
        public void getRelatedObjects(EntityStatus entity_status)
        {
             
        }
        
        @Override
        public boolean remove(EntityStatus obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + obj.getEntityStatusId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + id + ";");           
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
                updateQuery("DELETE FROM entity_status;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM entity_status WHERE " + EntityStatus.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

