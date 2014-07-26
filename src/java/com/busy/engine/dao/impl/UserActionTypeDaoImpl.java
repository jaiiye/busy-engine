





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object UserActionType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
        } 
    
        @Override
        public void add(UserActionType obj)
        {
            try
            {
                
                UserActionType.checkColumnSize(obj.getTypeName(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName)
        {   
            int id = 0;
            try
            {
                
                UserActionType.checkColumnSize(TypeName, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, TypeName);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserActionType error: " + ex.getMessage());
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
                System.out.println("updateUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer UserActionTypeId,String TypeName)
        {  
            try
            {   
                
                UserActionType.checkColumnSize(TypeName, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action_type SET TypeName=? WHERE UserActionTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, UserActionTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("user_action_type");
        }
        
        
        @Override
        public UserActionType getRelatedInfo(UserActionType user_action_type)
        {
              
            
            return user_action_type;
        }
        
        
        @Override
        public UserActionType getRelatedObjects(UserActionType user_action_type)
        {
            user_action_type.setUserActionList(new UserActionDaoImpl().findByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString(),null,null));
             
            return user_action_type;
        }
        
        
        
        @Override
        public void remove(UserActionType obj)
        {            
            try
            {
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + obj.getUserActionTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action_type;");
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action_type WHERE " + UserActionType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public UserActionType getRelatedUserActionList(UserActionType user_action_type)
        {           
            user_action_type.setUserActionList(new UserActionDaoImpl().findByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString(),null,null));
            return user_action_type;
        }        
        
                             
    }

