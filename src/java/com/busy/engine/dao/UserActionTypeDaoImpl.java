





































    package com.busy.engine.dao;

import com.busy.engine.entity.UserActionType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserActionTypeDaoImpl extends BasicConnection implements Serializable, UserActionTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public UserActionType find(Integer id)
        {
            return findByColumn("UserActionTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<UserActionType> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserActionType> user_action_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user_action_type");
                while(rs.next())
                {
                    user_action_type.add(UserActionType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserActionType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
         
        }
        
        @Override
        public ArrayList<UserActionType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserActionType> user_action_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_action_type", limit, offset);
                while (rs.next())
                {
                    user_action_typeList.add(UserActionType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object UserActionType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_typeList;
        }
        
        @Override
        public ArrayList<UserActionType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserActionType> user_action_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("user_action_type", UserActionType.checkColumnName(columnName), columnValue, UserActionType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_action_type.add(UserActionType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserActionType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
        } 
    
        @Override
        public int add(UserActionType obj)
        {
            int id = 0;
            try
            {
                
                UserActionType.checkColumnSize(obj.getTypeName(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public UserActionType update(UserActionType obj)
        {
           try
            {   
                
                UserActionType.checkColumnSize(obj.getTypeName(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action_type SET TypeName=? WHERE UserActionTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getUserActionTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("user_action_type");
        }
        
        
        @Override
        public void getRelatedInfo(UserActionType user_action_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(UserActionType user_action_type)
        {
            user_action_type.setUserActionList(new UserActionDaoImpl().findByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserActionType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + obj.getUserActionTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM user_action_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action_type WHERE " + UserActionType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedUserActionList(UserActionType user_action_type)
        {           
            user_action_type.setUserActionList(new UserActionDaoImpl().findByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString(),null,null));
        }        
        
                             
    }

