





































    package com.busy.engine.dao;

import com.busy.engine.entity.UserGroup;
import com.busy.engine.entity.Discount;
import com.busy.engine.entity.Site;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("UserGroup's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
        } 
    
        @Override
        public int add(UserGroup obj)
        {
            int id = 0;
            try
            {
                
                UserGroup.checkColumnSize(obj.getGroupName(), 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_group(GroupName,SiteId,DiscountId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getGroupName());
                preparedStatement.setInt(2, obj.getSiteId());
                preparedStatement.setInt(3, obj.getDiscountId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_group;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's add method error: " + ex.getMessage());
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
                System.out.println("UserGroup's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("user_group");
        }
        
        
        @Override
        public void getRelatedInfo(UserGroup user_group)
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
              
        }
        
        @Override
        public void getRelatedObjects(UserGroup user_group)
        {
            user_group.setUserList(new UserDaoImpl().findByColumn("UserGroupId", user_group.getUserGroupId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserGroup obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + obj.getUserGroupId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + id + ";");           
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
                updateQuery("DELETE FROM user_group;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_group WHERE " + UserGroup.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedUserList(UserGroup user_group)
        {           
            user_group.setUserList(new UserDaoImpl().findByColumn("UserGroupId", user_group.getUserGroupId().toString(),null,null));
        }        
        
                             
    }

