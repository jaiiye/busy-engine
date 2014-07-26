





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserGroupDaoImpl extends BasicConnection implements Serializable, UserGroupDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public UserGroup find(Integer id)
        {
            return findByColumn("UserGroupId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<UserGroup> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserGroup> user_group = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user_group");
                while(rs.next())
                {
                    user_group.add(UserGroup.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserGroup object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
         
        }
        
        @Override
        public ArrayList<UserGroup> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserGroup> user_groupList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_group", limit, offset);
                while (rs.next())
                {
                    user_groupList.add(UserGroup.process(rs));
                }

                
                    for(UserGroup user_group : user_groupList)
                    {
                        
                            getRecordById("Site", user_group.getSiteId().toString());
                            user_group.setSite(Site.process(rs));               
                        
                            getRecordById("Discount", user_group.getDiscountId().toString());
                            user_group.setDiscount(Discount.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object UserGroup method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_groupList;
        }
        
        @Override
        public ArrayList<UserGroup> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserGroup> user_group = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("user_group", UserGroup.checkColumnName(columnName), columnValue, UserGroup.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_group.add(UserGroup.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object UserGroup's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
        } 
    
        @Override
        public void add(UserGroup obj)
        {
            try
            {
                
                UserGroup.checkColumnSize(obj.getGroupName(), 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_group(GroupName,SiteId,DiscountId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getGroupName());
                preparedStatement.setInt(2, obj.getSiteId());
                preparedStatement.setInt(3, obj.getDiscountId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String GroupName, Integer SiteId, Integer DiscountId)
        {   
            int id = 0;
            try
            {
                
                UserGroup.checkColumnSize(GroupName, 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_group(GroupName,SiteId,DiscountId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, GroupName);
                preparedStatement.setInt(2, SiteId);
                preparedStatement.setInt(3, DiscountId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_group;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public UserGroup update(UserGroup obj)
        {
           try
            {   
                
                UserGroup.checkColumnSize(obj.getGroupName(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_group SET GroupName=?,SiteId=?,DiscountId=? WHERE UserGroupId=?;");                    
                preparedStatement.setString(1, obj.getGroupName());
                preparedStatement.setInt(2, obj.getSiteId());
                preparedStatement.setInt(3, obj.getDiscountId());
                preparedStatement.setInt(4, obj.getUserGroupId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer UserGroupId,String GroupName,Integer SiteId,Integer DiscountId)
        {  
            try
            {   
                
                UserGroup.checkColumnSize(GroupName, 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_group SET GroupName=?,SiteId=?,DiscountId=? WHERE UserGroupId=?;");                    
                preparedStatement.setString(1, GroupName);
                preparedStatement.setInt(2, SiteId);
                preparedStatement.setInt(3, DiscountId);
                preparedStatement.setInt(4, UserGroupId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("user_group");
        }
        
        
        @Override
        public UserGroup getRelatedInfo(UserGroup user_group)
        {
            
                try
                { 
                    
                        getRecordById("Site", user_group.getSiteId().toString());
                        user_group.setSite(Site.process(rs));               
                    
                        getRecordById("Discount", user_group.getDiscountId().toString());
                        user_group.setDiscount(Discount.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return user_group;
        }
        
        
        @Override
        public UserGroup getRelatedObjects(UserGroup user_group)
        {
            user_group.setUserList(new UserDaoImpl().findByColumn("UserGroupId", user_group.getUserGroupId().toString(),null,null));
             
            return user_group;
        }
        
        
        
        @Override
        public void remove(UserGroup obj)
        {            
            try
            {
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + obj.getUserGroupId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserGroupById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserGroupById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_group;");
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_group WHERE " + UserGroup.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public UserGroup getRelatedUserList(UserGroup user_group)
        {           
            user_group.setUserList(new UserDaoImpl().findByColumn("UserGroupId", user_group.getUserGroupId().toString(),null,null));
            return user_group;
        }        
        
                             
    }

