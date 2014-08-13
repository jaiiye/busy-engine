





































    package com.busy.engine.dao;

import com.busy.engine.entity.UserType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserTypeDaoImpl extends BasicConnection implements Serializable, UserTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public UserType find(Integer id)
        {
            return findByColumn("UserTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<UserType> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserType> user_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user_type");
                while(rs.next())
                {
                    user_type.add(UserType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
         
        }
        
        @Override
        public ArrayList<UserType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserType> user_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_type", limit, offset);
                while (rs.next())
                {
                    user_typeList.add(UserType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object UserType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_typeList;
        }
        
        @Override
        public ArrayList<UserType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserType> user_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("user_type", UserType.checkColumnName(columnName), columnValue, UserType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_type.add(UserType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
        } 
    
        @Override
        public int add(UserType obj)
        {
            int id = 0;
            try
            {
                
                UserType.checkColumnSize(obj.getTypeName(), 45);
                UserType.checkColumnSize(obj.getDescription(), 255);
                UserType.checkColumnSize(obj.getRedirectUrl(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_type(TypeName,Description,RedirectUrl) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRedirectUrl());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("UserType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public UserType update(UserType obj)
        {
           try
            {   
                
                UserType.checkColumnSize(obj.getTypeName(), 45);
                UserType.checkColumnSize(obj.getDescription(), 255);
                UserType.checkColumnSize(obj.getRedirectUrl(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_type SET TypeName=?,Description=?,RedirectUrl=? WHERE UserTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRedirectUrl());
                preparedStatement.setInt(4, obj.getUserTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("UserType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("user_type");
        }
        
        
        @Override
        public void getRelatedInfo(UserType user_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(UserType user_type)
        {
            user_type.setUserList(new UserDaoImpl().findByColumn("UserTypeId", user_type.getUserTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + obj.getUserTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM user_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_type WHERE " + UserType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedUserList(UserType user_type)
        {           
            user_type.setUserList(new UserDaoImpl().findByColumn("UserTypeId", user_type.getUserTypeId().toString(),null,null));
        }        
        
                             
    }

