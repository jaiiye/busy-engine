





































    package com.busy.engine.dao;

import com.busy.engine.entity.User;
import com.busy.engine.entity.UserActionType;
import com.busy.engine.entity.UserAction;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserActionDaoImpl extends BasicConnection implements Serializable, UserActionDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public UserAction find(Integer id)
        {
            return findByColumn("UserActionId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<UserAction> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserAction> user_action = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user_action");
                while(rs.next())
                {
                    user_action.add(UserAction.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserAction object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
         
        }
        
        @Override
        public ArrayList<UserAction> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserAction> user_actionList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_action", limit, offset);
                while (rs.next())
                {
                    user_actionList.add(UserAction.process(rs));
                }

                
                    for(UserAction user_action : user_actionList)
                    {
                        
                            getRecordById("UserActionType", user_action.getUserActionTypeId().toString());
                            user_action.setUserActionType(UserActionType.process(rs));               
                        
                            getRecordById("User", user_action.getUserId().toString());
                            user_action.setUser(User.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object UserAction method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_actionList;
        }
        
        @Override
        public ArrayList<UserAction> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserAction> user_action = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("user_action", UserAction.checkColumnName(columnName), columnValue, UserAction.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_action.add(UserAction.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserAction's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
        } 
    
        @Override
        public int add(UserAction obj)
        {
            int id = 0;
            try
            {
                
                
                UserAction.checkColumnSize(obj.getDetail(), 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action(Date,Detail,UserActionTypeId,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setString(2, obj.getDetail());
                preparedStatement.setInt(3, obj.getUserActionTypeId());
                preparedStatement.setInt(4, obj.getUserId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public UserAction update(UserAction obj)
        {
           try
            {   
                
                
                UserAction.checkColumnSize(obj.getDetail(), 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action SET Date=?,Detail=?,UserActionTypeId=?,UserId=? WHERE UserActionId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setString(2, obj.getDetail());
                preparedStatement.setInt(3, obj.getUserActionTypeId());
                preparedStatement.setInt(4, obj.getUserId());
                preparedStatement.setInt(5, obj.getUserActionId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("user_action");
        }
        
        
        @Override
        public void getRelatedInfo(UserAction user_action)
        {
            
                try
                { 
                    
                            getRecordById("UserActionType", user_action.getUserActionTypeId().toString());
                            user_action.setUserActionType(UserActionType.process(rs));                                       
                    
                            getRecordById("User", user_action.getUserId().toString());
                            user_action.setUser(User.process(rs));                                       
                    
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
        public void getRelatedObjects(UserAction user_action)
        {
             
        }
        
        @Override
        public boolean remove(UserAction obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + obj.getUserActionId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + id + ";");           
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
                updateQuery("DELETE FROM user_action;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action WHERE " + UserAction.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

