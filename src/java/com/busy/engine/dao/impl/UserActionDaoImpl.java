





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object UserAction's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
        } 
    
        @Override
        public void add(UserAction obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Date Date, String Detail, Integer UserActionTypeId, Integer UserId)
        {   
            int id = 0;
            try
            {
                
                
                UserAction.checkColumnSize(Detail, 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action(Date,Detail,UserActionTypeId,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(Date.getTime()));
                preparedStatement.setString(2, Detail);
                preparedStatement.setInt(3, UserActionTypeId);
                preparedStatement.setInt(4, UserId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserAction error: " + ex.getMessage());
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
                System.out.println("updateUserAction error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer UserActionId,Date Date,String Detail,Integer UserActionTypeId,Integer UserId)
        {  
            try
            {   
                
                
                UserAction.checkColumnSize(Detail, 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action SET Date=?,Detail=?,UserActionTypeId=?,UserId=? WHERE UserActionId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(Date.getTime()));
                preparedStatement.setString(2, Detail);
                preparedStatement.setInt(3, UserActionTypeId);
                preparedStatement.setInt(4, UserId);
                preparedStatement.setInt(5, UserActionId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserAction error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("user_action");
        }
        
        
        @Override
        public UserAction getRelatedInfo(UserAction user_action)
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
              
            
            return user_action;
        }
        
        
        @Override
        public UserAction getRelatedObjects(UserAction user_action)
        {
                         
            return user_action;
        }
        
        
        
        @Override
        public void remove(UserAction obj)
        {            
            try
            {
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + obj.getUserActionId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action;");
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action WHERE " + UserAction.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

