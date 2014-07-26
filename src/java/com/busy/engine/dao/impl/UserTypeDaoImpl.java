





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object UserType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
        } 
    
        @Override
        public void add(UserType obj)
        {
            try
            {
                
                UserType.checkColumnSize(obj.getTypeName(), 45);
                UserType.checkColumnSize(obj.getDescription(), 255);
                UserType.checkColumnSize(obj.getRedirectURL(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_type(TypeName,Description,RedirectURL) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRedirectURL());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("UserType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String Description, String RedirectURL)
        {   
            int id = 0;
            try
            {
                
                UserType.checkColumnSize(TypeName, 45);
                UserType.checkColumnSize(Description, 255);
                UserType.checkColumnSize(RedirectURL, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_type(TypeName,Description,RedirectURL) VALUES (?,?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, RedirectURL);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserType error: " + ex.getMessage());
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
                UserType.checkColumnSize(obj.getRedirectURL(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_type SET TypeName=?,Description=?,RedirectURL=? WHERE UserTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRedirectURL());
                preparedStatement.setInt(4, obj.getUserTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer UserTypeId,String TypeName,String Description,String RedirectURL)
        {  
            try
            {   
                
                UserType.checkColumnSize(TypeName, 45);
                UserType.checkColumnSize(Description, 255);
                UserType.checkColumnSize(RedirectURL, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_type SET TypeName=?,Description=?,RedirectURL=? WHERE UserTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, RedirectURL);
                preparedStatement.setInt(4, UserTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("user_type");
        }
        
        
        @Override
        public UserType getRelatedInfo(UserType user_type)
        {
              
            
            return user_type;
        }
        
        
        @Override
        public UserType getRelatedObjects(UserType user_type)
        {
            user_type.setUserList(new UserDaoImpl().findByColumn("UserTypeId", user_type.getUserTypeId().toString(),null,null));
             
            return user_type;
        }
        
        
        
        @Override
        public void remove(UserType obj)
        {            
            try
            {
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + obj.getUserTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_type;");
            }
            catch (Exception ex)
            {
                System.out.println("UserType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_type WHERE " + UserType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("UserType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public UserType getRelatedUserList(UserType user_type)
        {           
            user_type.setUserList(new UserDaoImpl().findByColumn("UserTypeId", user_type.getUserTypeId().toString(),null,null));
            return user_type;
        }        
        
                             
    }

