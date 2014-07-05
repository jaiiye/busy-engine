


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class User extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_USERID = "UserId";
        public static final String PROP_BRANDID = "BrandId";
        public static final String PROP_TYPEID = "TypeId";
        public static final String PROP_USERNAME = "UserName";
        public static final String PROP_USERPASSWORD = "UserPassword";
        public static final String PROP_USERSECURITYQUESTION = "UserSecurityQuestion";
        public static final String PROP_USERSECURITYANSWER = "UserSecurityAnswer";
        public static final String PROP_USERIMGURL = "UserImgURL";
        public static final String PROP_USEREMAIL = "UserEmail";
        public static final String PROP_USEREMAILCONFIRMED = "UserEmailConfirmed";
        public static final String PROP_USERWEBURL = "UserWebUrl";
        public static final String PROP_CONTACTID = "ContactId";
        public static final String PROP_ADDRESSID = "AddressId";
        
        
        private Integer userId;
        private Integer brandId;
        private Integer typeId;
        private String userName;
        private String userPassword;
        private String userSecurityQuestion;
        private String userSecurityAnswer;
        private String userImgURL;
        private String userEmail;
        private Integer userEmailConfirmed;
        private String userWebUrl;
        private Integer contactId;
        private Integer addressId;
        
        
        public User()
        {
            this.userId = 0; 
       this.brandId = 0; 
       this.typeId = 0; 
       this.userName = ""; 
       this.userPassword = ""; 
       this.userSecurityQuestion = ""; 
       this.userSecurityAnswer = ""; 
       this.userImgURL = ""; 
       this.userEmail = ""; 
       this.userEmailConfirmed = 0; 
       this.userWebUrl = ""; 
       this.contactId = 0; 
       this.addressId = 0; 
        }
        
        public User(Integer UserId, Integer BrandId, Integer TypeId, String UserName, String UserPassword, String UserSecurityQuestion, String UserSecurityAnswer, String UserImgURL, String UserEmail, Integer UserEmailConfirmed, String UserWebUrl, Integer ContactId, Integer AddressId)
        {
            this.userId = UserId;
       this.brandId = BrandId;
       this.typeId = TypeId;
       this.userName = UserName;
       this.userPassword = UserPassword;
       this.userSecurityQuestion = UserSecurityQuestion;
       this.userSecurityAnswer = UserSecurityAnswer;
       this.userImgURL = UserImgURL;
       this.userEmail = UserEmail;
       this.userEmailConfirmed = UserEmailConfirmed;
       this.userWebUrl = UserWebUrl;
       this.contactId = ContactId;
       this.addressId = AddressId;
        } 
        
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getBrandId()
            {
                return this.brandId;
            }
            
            public void setBrandId(Integer BrandId)
            {
                this.brandId = BrandId;
            }
        
            public Integer getTypeId()
            {
                return this.typeId;
            }
            
            public void setTypeId(Integer TypeId)
            {
                this.typeId = TypeId;
            }
        
            public String getUserName()
            {
                return this.userName;
            }
            
            public void setUserName(String UserName)
            {
                this.userName = UserName;
            }
        
            public String getUserPassword()
            {
                return this.userPassword;
            }
            
            public void setUserPassword(String UserPassword)
            {
                this.userPassword = UserPassword;
            }
        
            public String getUserSecurityQuestion()
            {
                return this.userSecurityQuestion;
            }
            
            public void setUserSecurityQuestion(String UserSecurityQuestion)
            {
                this.userSecurityQuestion = UserSecurityQuestion;
            }
        
            public String getUserSecurityAnswer()
            {
                return this.userSecurityAnswer;
            }
            
            public void setUserSecurityAnswer(String UserSecurityAnswer)
            {
                this.userSecurityAnswer = UserSecurityAnswer;
            }
        
            public String getUserImgURL()
            {
                return this.userImgURL;
            }
            
            public void setUserImgURL(String UserImgURL)
            {
                this.userImgURL = UserImgURL;
            }
        
            public String getUserEmail()
            {
                return this.userEmail;
            }
            
            public void setUserEmail(String UserEmail)
            {
                this.userEmail = UserEmail;
            }
        
            public Integer getUserEmailConfirmed()
            {
                return this.userEmailConfirmed;
            }
            
            public void setUserEmailConfirmed(Integer UserEmailConfirmed)
            {
                this.userEmailConfirmed = UserEmailConfirmed;
            }
        
            public String getUserWebUrl()
            {
                return this.userWebUrl;
            }
            
            public void setUserWebUrl(String UserWebUrl)
            {
                this.userWebUrl = UserWebUrl;
            }
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
            }
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(User.PROP_USERID) || column.equals(User.PROP_BRANDID) || column.equals(User.PROP_TYPEID) || column.equals(User.PROP_USERNAME) || column.equals(User.PROP_USERPASSWORD) || column.equals(User.PROP_USERSECURITYQUESTION) || column.equals(User.PROP_USERSECURITYANSWER) || column.equals(User.PROP_USERIMGURL) || column.equals(User.PROP_USEREMAIL) || column.equals(User.PROP_USEREMAILCONFIRMED) || column.equals(User.PROP_USERWEBURL) || column.equals(User.PROP_CONTACTID) || column.equals(User.PROP_ADDRESSID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(User.PROP_USERID) || column.equals(User.PROP_BRANDID) || column.equals(User.PROP_TYPEID) || column.equals(User.PROP_USEREMAILCONFIRMED) || column.equals(User.PROP_CONTACTID) || column.equals(User.PROP_ADDRESSID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static User processUser(ResultSet rs) throws SQLException
        {        
            return new User(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
        }
        
        public static int addUser(Integer BrandId, Integer TypeId, String UserName, String UserPassword, String UserSecurityQuestion, String UserSecurityAnswer, String UserImgURL, String UserEmail, Integer UserEmailConfirmed, String UserWebUrl, Integer ContactId, Integer AddressId)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(UserName, 30);
                checkColumnSize(UserPassword, 15);
                checkColumnSize(UserSecurityQuestion, 100);
                checkColumnSize(UserSecurityAnswer, 30);
                checkColumnSize(UserImgURL, 255);
                checkColumnSize(UserEmail, 255);
                
                checkColumnSize(UserWebUrl, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user(BrandId,TypeId,UserName,UserPassword,UserSecurityQuestion,UserSecurityAnswer,UserImgURL,UserEmail,UserEmailConfirmed,UserWebUrl,ContactId,AddressId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, BrandId);
                preparedStatement.setInt(2, TypeId);
                preparedStatement.setString(3, UserName);
                preparedStatement.setString(4, UserPassword);
                preparedStatement.setString(5, UserSecurityQuestion);
                preparedStatement.setString(6, UserSecurityAnswer);
                preparedStatement.setString(7, UserImgURL);
                preparedStatement.setString(8, UserEmail);
                preparedStatement.setInt(9, UserEmailConfirmed);
                preparedStatement.setString(10, UserWebUrl);
                preparedStatement.setInt(11, ContactId);
                preparedStatement.setInt(12, AddressId);
                
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
        
        public static int getAllUserCount()
        {
            return getAllRecordsCountByTableName("user");        
        }
        
        public static ArrayList<User> getAllUser()
        {
            ArrayList<User> user = new ArrayList<User>();
            try
            {
                getAllRecordsByTableName("user");
                while(rs.next())
                {
                    user.add(processUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }
                
        public static ArrayList<User> getUserPaged(int limit, int offset)
        {
            ArrayList<User> user = new ArrayList<User>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user", limit, offset);
                while (rs.next())
                {
                    user.add(processUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        } 
        
        public static ArrayList<User> getAllUserByColumn(String columnName, String columnValue)
        {
            ArrayList<User> user = new ArrayList<User>();
            try
            {
                getAllRecordsByColumn("user", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user.add(processUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }
        
        public static User getUserByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            User user = new User();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user = processUser(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }                
                
        public static void updateUser(Integer UserId,Integer BrandId,Integer TypeId,String UserName,String UserPassword,String UserSecurityQuestion,String UserSecurityAnswer,String UserImgURL,String UserEmail,Integer UserEmailConfirmed,String UserWebUrl,Integer ContactId,Integer AddressId)
        {  
            try
            {   
                
                
                
                checkColumnSize(UserName, 30);
                checkColumnSize(UserPassword, 15);
                checkColumnSize(UserSecurityQuestion, 100);
                checkColumnSize(UserSecurityAnswer, 30);
                checkColumnSize(UserImgURL, 255);
                checkColumnSize(UserEmail, 255);
                
                checkColumnSize(UserWebUrl, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user SET BrandId=?,TypeId=?,UserName=?,UserPassword=?,UserSecurityQuestion=?,UserSecurityAnswer=?,UserImgURL=?,UserEmail=?,UserEmailConfirmed=?,UserWebUrl=?,ContactId=?,AddressId=? WHERE UserId=?;");                    
                preparedStatement.setInt(1, BrandId);
                preparedStatement.setInt(2, TypeId);
                preparedStatement.setString(3, UserName);
                preparedStatement.setString(4, UserPassword);
                preparedStatement.setString(5, UserSecurityQuestion);
                preparedStatement.setString(6, UserSecurityAnswer);
                preparedStatement.setString(7, UserImgURL);
                preparedStatement.setString(8, UserEmail);
                preparedStatement.setInt(9, UserEmailConfirmed);
                preparedStatement.setString(10, UserWebUrl);
                preparedStatement.setInt(11, ContactId);
                preparedStatement.setInt(12, AddressId);
                preparedStatement.setInt(13, UserId);
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
        
        public static void deleteAllUser()
        {
            try
            {
                updateQuery("DELETE FROM user;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user WHERE UserId=" + id + ";");            
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

        public static void deleteUserByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

