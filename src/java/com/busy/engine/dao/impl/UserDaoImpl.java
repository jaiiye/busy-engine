





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserDaoImpl extends BasicConnection implements Serializable, UserDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public User find(Integer id)
        {
            return findByColumn("UserId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<User> findAll(Integer limit, Integer offset)
        {
            ArrayList<User> user = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user");
                while(rs.next())
                {
                    user.add(User.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("User object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
         
        }
        
        @Override
        public ArrayList<User> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<User> userList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user", limit, offset);
                while (rs.next())
                {
                    userList.add(User.process(rs));
                }

                
                    for(User user : userList)
                    {
                        
                            getRecordById("ItemBrand", user.getItemBrandId().toString());
                            user.setItemBrand(ItemBrand.process(rs));               
                        
                            getRecordById("UserType", user.getUserTypeId().toString());
                            user.setUserType(UserType.process(rs));               
                        
                            getRecordById("Address", user.getAddressId().toString());
                            user.setAddress(Address.process(rs));               
                        
                            getRecordById("Contact", user.getContactId().toString());
                            user.setContact(Contact.process(rs));               
                        
                            getRecordById("UserGroup", user.getUserGroupId().toString());
                            user.setUserGroup(UserGroup.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object User method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return userList;
        }
        
        @Override
        public ArrayList<User> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<User> user = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("user", User.checkColumnName(columnName), columnValue, User.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user.add(User.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object User's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        } 
    
        @Override
        public void add(User obj)
        {
            try
            {
                
                User.checkColumnSize(obj.getUsername(), 30);
                User.checkColumnSize(obj.getPassword(), 15);
                User.checkColumnSize(obj.getEmail(), 255);
                User.checkColumnSize(obj.getSecurityQuestion(), 100);
                User.checkColumnSize(obj.getSecurityAnswer(), 30);
                
                User.checkColumnSize(obj.getImageURL(), 255);
                
                
                User.checkColumnSize(obj.getWebUrl(), 255);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user(Username,Password,Email,SecurityQuestion,SecurityAnswer,RegisterDate,ImageURL,Status,Rank,WebUrl,ItemBrandId,UserTypeId,AddressId,ContactId,UserGroupId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getUsername());
                preparedStatement.setString(2, obj.getPassword());
                preparedStatement.setString(3, obj.getEmail());
                preparedStatement.setString(4, obj.getSecurityQuestion());
                preparedStatement.setString(5, obj.getSecurityAnswer());
                preparedStatement.setDate(6, new java.sql.Date(obj.getRegisterDate().getTime()));
                preparedStatement.setString(7, obj.getImageURL());
                preparedStatement.setInt(8, obj.getStatus());
                preparedStatement.setInt(9, obj.getRank());
                preparedStatement.setString(10, obj.getWebUrl());
                preparedStatement.setInt(11, obj.getItemBrandId());
                preparedStatement.setInt(12, obj.getUserTypeId());
                preparedStatement.setInt(13, obj.getAddressId());
                preparedStatement.setInt(14, obj.getContactId());
                preparedStatement.setInt(15, obj.getUserGroupId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("User's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Username, String Password, String Email, String SecurityQuestion, String SecurityAnswer, Date RegisterDate, String ImageURL, Integer Status, Integer Rank, String WebUrl, Integer ItemBrandId, Integer UserTypeId, Integer AddressId, Integer ContactId, Integer UserGroupId)
        {   
            int id = 0;
            try
            {
                
                User.checkColumnSize(Username, 30);
                User.checkColumnSize(Password, 15);
                User.checkColumnSize(Email, 255);
                User.checkColumnSize(SecurityQuestion, 100);
                User.checkColumnSize(SecurityAnswer, 30);
                
                User.checkColumnSize(ImageURL, 255);
                
                
                User.checkColumnSize(WebUrl, 255);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user(Username,Password,Email,SecurityQuestion,SecurityAnswer,RegisterDate,ImageURL,Status,Rank,WebUrl,ItemBrandId,UserTypeId,AddressId,ContactId,UserGroupId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, SecurityQuestion);
                preparedStatement.setString(5, SecurityAnswer);
                preparedStatement.setDate(6, new java.sql.Date(RegisterDate.getTime()));
                preparedStatement.setString(7, ImageURL);
                preparedStatement.setInt(8, Status);
                preparedStatement.setInt(9, Rank);
                preparedStatement.setString(10, WebUrl);
                preparedStatement.setInt(11, ItemBrandId);
                preparedStatement.setInt(12, UserTypeId);
                preparedStatement.setInt(13, AddressId);
                preparedStatement.setInt(14, ContactId);
                preparedStatement.setInt(15, UserGroupId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public User update(User obj)
        {
           try
            {   
                
                User.checkColumnSize(obj.getUsername(), 30);
                User.checkColumnSize(obj.getPassword(), 15);
                User.checkColumnSize(obj.getEmail(), 255);
                User.checkColumnSize(obj.getSecurityQuestion(), 100);
                User.checkColumnSize(obj.getSecurityAnswer(), 30);
                
                User.checkColumnSize(obj.getImageURL(), 255);
                
                
                User.checkColumnSize(obj.getWebUrl(), 255);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user SET Username=?,Password=?,Email=?,SecurityQuestion=?,SecurityAnswer=?,RegisterDate=?,ImageURL=?,Status=?,Rank=?,WebUrl=?,ItemBrandId=?,UserTypeId=?,AddressId=?,ContactId=?,UserGroupId=? WHERE UserId=?;");                    
                preparedStatement.setString(1, obj.getUsername());
                preparedStatement.setString(2, obj.getPassword());
                preparedStatement.setString(3, obj.getEmail());
                preparedStatement.setString(4, obj.getSecurityQuestion());
                preparedStatement.setString(5, obj.getSecurityAnswer());
                preparedStatement.setDate(6, new java.sql.Date(obj.getRegisterDate().getTime()));
                preparedStatement.setString(7, obj.getImageURL());
                preparedStatement.setInt(8, obj.getStatus());
                preparedStatement.setInt(9, obj.getRank());
                preparedStatement.setString(10, obj.getWebUrl());
                preparedStatement.setInt(11, obj.getItemBrandId());
                preparedStatement.setInt(12, obj.getUserTypeId());
                preparedStatement.setInt(13, obj.getAddressId());
                preparedStatement.setInt(14, obj.getContactId());
                preparedStatement.setInt(15, obj.getUserGroupId());
                preparedStatement.setInt(16, obj.getUserId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer UserId,String Username,String Password,String Email,String SecurityQuestion,String SecurityAnswer,Date RegisterDate,String ImageURL,Integer Status,Integer Rank,String WebUrl,Integer ItemBrandId,Integer UserTypeId,Integer AddressId,Integer ContactId,Integer UserGroupId)
        {  
            try
            {   
                
                User.checkColumnSize(Username, 30);
                User.checkColumnSize(Password, 15);
                User.checkColumnSize(Email, 255);
                User.checkColumnSize(SecurityQuestion, 100);
                User.checkColumnSize(SecurityAnswer, 30);
                
                User.checkColumnSize(ImageURL, 255);
                
                
                User.checkColumnSize(WebUrl, 255);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user SET Username=?,Password=?,Email=?,SecurityQuestion=?,SecurityAnswer=?,RegisterDate=?,ImageURL=?,Status=?,Rank=?,WebUrl=?,ItemBrandId=?,UserTypeId=?,AddressId=?,ContactId=?,UserGroupId=? WHERE UserId=?;");                    
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, SecurityQuestion);
                preparedStatement.setString(5, SecurityAnswer);
                preparedStatement.setDate(6, new java.sql.Date(RegisterDate.getTime()));
                preparedStatement.setString(7, ImageURL);
                preparedStatement.setInt(8, Status);
                preparedStatement.setInt(9, Rank);
                preparedStatement.setString(10, WebUrl);
                preparedStatement.setInt(11, ItemBrandId);
                preparedStatement.setInt(12, UserTypeId);
                preparedStatement.setInt(13, AddressId);
                preparedStatement.setInt(14, ContactId);
                preparedStatement.setInt(15, UserGroupId);
                preparedStatement.setInt(16, UserId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("user");
        }
        
        
        @Override
        public User getRelatedInfo(User user)
        {
            
                try
                { 
                    
                        getRecordById("ItemBrand", user.getItemBrandId().toString());
                        user.setItemBrand(ItemBrand.process(rs));               
                    
                        getRecordById("UserType", user.getUserTypeId().toString());
                        user.setUserType(UserType.process(rs));               
                    
                        getRecordById("Address", user.getAddressId().toString());
                        user.setAddress(Address.process(rs));               
                    
                        getRecordById("Contact", user.getContactId().toString());
                        user.setContact(Contact.process(rs));               
                    
                        getRecordById("UserGroup", user.getUserGroupId().toString());
                        user.setUserGroup(UserGroup.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return user;
        }
        
        
        @Override
        public User getRelatedObjects(User user)
        {
            user.setAffiliateList(new AffiliateDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setBlogPostList(new BlogPostDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setCommentList(new CommentDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setCustomerList(new CustomerDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setMailinglistList(new MailinglistDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setUserActionList(new UserActionDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setUserServiceList(new UserServiceDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
             
            return user;
        }
        
        
        
        @Override
        public void remove(User obj)
        {            
            try
            {
                updateQuery("DELETE FROM user WHERE UserId=" + obj.getUserId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user WHERE UserId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user;");
            }
            catch (Exception ex)
            {
                System.out.println("User's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user WHERE " + User.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("User's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public User getRelatedAffiliateList(User user)
        {           
            user.setAffiliateList(new AffiliateDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
                    
        public User getRelatedBlogPostList(User user)
        {           
            user.setBlogPostList(new BlogPostDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
                    
        public User getRelatedCommentList(User user)
        {           
            user.setCommentList(new CommentDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
                    
        public User getRelatedCustomerList(User user)
        {           
            user.setCustomerList(new CustomerDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
                    
        public User getRelatedMailinglistList(User user)
        {           
            user.setMailinglistList(new MailinglistDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
                    
        public User getRelatedUserActionList(User user)
        {           
            user.setUserActionList(new UserActionDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
                    
        public User getRelatedUserServiceList(User user)
        {           
            user.setUserServiceList(new UserServiceDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
            return user;
        }        
        
                             
    }

