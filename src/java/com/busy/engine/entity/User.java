package com.busy.engine.entity;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;

public class User extends AbstractEntity implements EntityItem<Integer>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_USER_ID = "UserId";
    public static final String PROP_USERNAME = "Username";
    public static final String PROP_PASSWORD = "Password";
    public static final String PROP_EMAIL = "Email";
    public static final String PROP_SECURITY_QUESTION = "SecurityQuestion";
    public static final String PROP_SECURITY_ANSWER = "SecurityAnswer";
    public static final String PROP_REGISTER_DATE = "RegisterDate";
    public static final String PROP_IMAGE_URL = "ImageUrl";
    public static final String PROP_USER_STATUS = "UserStatus";
    public static final String PROP_RANK = "Rank";
    public static final String PROP_WEB_URL = "WebUrl";
    public static final String PROP_ITEM_BRAND_ID = "ItemBrandId";
    public static final String PROP_USER_TYPE_ID = "UserTypeId";
    public static final String PROP_ADDRESS_ID = "AddressId";
    public static final String PROP_CONTACT_ID = "ContactId";
    public static final String PROP_USER_GROUP_ID = "UserGroupId";

    private Integer userId;

    private String username;

    private String password;

    private String email;

    private String securityQuestion;

    private String securityAnswer;

    private Date registerDate;

    private String imageUrl;

    private Integer userStatus;

    private Integer rank;

    private String webUrl;

    private Integer itemBrandId;
    private ItemBrand itemBrand;
    private Integer userTypeId;
    private UserType userType;
    private Integer addressId;
    private Address address;
    private Integer contactId;
    private Contact contact;
    private Integer userGroupId;
    private UserGroup userGroup;

    ArrayList<Affiliate> affiliateList;
    ArrayList<BlogPost> blogPostList;
    ArrayList<Comment> commentList;
    ArrayList<Customer> customerList;
    ArrayList<Mailinglist> mailinglistList;
    ArrayList<UserAction> userActionList;
    ArrayList<UserService> userServiceList;

    public User()
    {
        this.userId = 0;
        this.username = "";
        this.password = "";
        this.email = "";
        this.securityQuestion = "";
        this.securityAnswer = "";
        this.registerDate = null;
        this.imageUrl = "";
        this.userStatus = 0;
        this.rank = 0;
        this.webUrl = "";
        this.itemBrandId = 0;
        this.userTypeId = 0;
        this.addressId = 0;
        this.contactId = 0;
        this.userGroupId = 0;

        affiliateList = null;
        blogPostList = null;
        commentList = null;
        customerList = null;
        mailinglistList = null;
        userActionList = null;
        userServiceList = null;

    }

    @Override
    public Integer getId()
    {

        return userId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {

        builder.add("userId", userId == null ? 0 : userId);

        builder.add("username", username == null ? "" : username);

        builder.add("password", password == null ? "" : password);

        builder.add("email", email == null ? "" : email);

        builder.add("securityQuestion", securityQuestion == null ? "" : securityQuestion);

        builder.add("securityAnswer", securityAnswer == null ? "" : securityAnswer);

        builder.add("registerDate", registerDate == null ? "" : new SimpleDateFormat("yyyyMMdd").format(registerDate));

        builder.add("imageUrl", imageUrl == null ? "" : imageUrl);

        builder.add("userStatus", userStatus == null ? 0 : userStatus);

        builder.add("rank", rank == null ? 0 : rank);

        builder.add("webUrl", webUrl == null ? "" : webUrl);

        builder.add("itemBrandId", itemBrandId == null ? 0 : itemBrandId);

        builder.add("userTypeId", userTypeId == null ? 0 : userTypeId);

        builder.add("addressId", addressId == null ? 0 : addressId);

        builder.add("contactId", contactId == null ? 0 : contactId);

        builder.add("userGroupId", userGroupId == null ? 0 : userGroupId);

        if (itemBrand != null)
        {
            itemBrand.addJson(builder);
        }

        if (userType != null)
        {
            userType.addJson(builder);
        }

        if (address != null)
        {
            address.addJson(builder);
        }

        if (contact != null)
        {
            contact.addJson(builder);
        }

        if (userGroup != null)
        {
            userGroup.addJson(builder);
        }

    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(User.PROP_USER_ID) || column.equals(User.PROP_USERNAME) || column.equals(User.PROP_PASSWORD) || column.equals(User.PROP_EMAIL) || column.equals(User.PROP_SECURITY_QUESTION) || column.equals(User.PROP_SECURITY_ANSWER) || column.equals(User.PROP_REGISTER_DATE) || column.equals(User.PROP_IMAGE_URL) || column.equals(User.PROP_USER_STATUS) || column.equals(User.PROP_RANK) || column.equals(User.PROP_WEB_URL) || column.equals(User.PROP_ITEM_BRAND_ID) || column.equals(User.PROP_USER_TYPE_ID) || column.equals(User.PROP_ADDRESS_ID) || column.equals(User.PROP_CONTACT_ID) || column.equals(User.PROP_USER_GROUP_ID))
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
        if (column.equals(User.PROP_USER_ID) || column.equals(User.PROP_USER_STATUS) || column.equals(User.PROP_RANK) || column.equals(User.PROP_ITEM_BRAND_ID) || column.equals(User.PROP_USER_TYPE_ID) || column.equals(User.PROP_ADDRESS_ID) || column.equals(User.PROP_CONTACT_ID) || column.equals(User.PROP_USER_GROUP_ID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static User process(ResultSet rs) throws SQLException
    {
        if (rs.getRow() == 0)
        {
            rs.first();
        }
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16));
    }

    public User(Integer UserId, String Username, String Password, String Email, String SecurityQuestion, String SecurityAnswer, Date RegisterDate, String ImageUrl, Integer UserStatus, Integer Rank, String WebUrl, Integer ItemBrandId, Integer UserTypeId, Integer AddressId, Integer ContactId, Integer UserGroupId)
    {
        this.userId = UserId;
        this.username = Username;
        this.password = Password;
        this.email = Email;
        this.securityQuestion = SecurityQuestion;
        this.securityAnswer = SecurityAnswer;
        this.registerDate = RegisterDate;
        this.imageUrl = ImageUrl;
        this.userStatus = UserStatus;
        this.rank = Rank;
        this.webUrl = WebUrl;
        this.itemBrandId = ItemBrandId;
        this.userTypeId = UserTypeId;
        this.addressId = AddressId;
        this.contactId = ContactId;
        this.userGroupId = UserGroupId;

        affiliateList = null;
        blogPostList = null;
        commentList = null;
        customerList = null;
        mailinglistList = null;
        userActionList = null;
        userServiceList = null;

    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String Username)
    {
        this.username = Username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String Password)
    {
        this.password = Password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String Email)
    {
        this.email = Email;
    }

    public String getSecurityQuestion()
    {
        return this.securityQuestion;
    }

    public void setSecurityQuestion(String SecurityQuestion)
    {
        this.securityQuestion = SecurityQuestion;
    }

    public String getSecurityAnswer()
    {
        return this.securityAnswer;
    }

    public void setSecurityAnswer(String SecurityAnswer)
    {
        this.securityAnswer = SecurityAnswer;
    }

    public Date getRegisterDate()
    {
        return this.registerDate;
    }

    public void setRegisterDate(Date RegisterDate)
    {
        this.registerDate = RegisterDate;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public void setImageUrl(String ImageUrl)
    {
        this.imageUrl = ImageUrl;
    }

    public Integer getUserStatus()
    {
        return this.userStatus;
    }

    public void setUserStatus(Integer UserStatus)
    {
        this.userStatus = UserStatus;
    }

    public Integer getRank()
    {
        return this.rank;
    }

    public void setRank(Integer Rank)
    {
        this.rank = Rank;
    }

    public String getWebUrl()
    {
        return this.webUrl;
    }

    public void setWebUrl(String WebUrl)
    {
        this.webUrl = WebUrl;
    }

    public Integer getItemBrandId()
    {
        return this.itemBrandId;
    }

    public void setItemBrandId(Integer ItemBrandId)
    {
        this.itemBrandId = ItemBrandId;
    }

    public ItemBrand getItemBrand()
    {
        return this.itemBrand;
    }

    public void setItemBrand(ItemBrand itemBrand)
    {
        this.itemBrand = itemBrand;
    }

    public Integer getUserTypeId()
    {
        return this.userTypeId;
    }

    public void setUserTypeId(Integer UserTypeId)
    {
        this.userTypeId = UserTypeId;
    }

    public UserType getUserType()
    {
        return this.userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public Integer getAddressId()
    {
        return this.addressId;
    }

    public void setAddressId(Integer AddressId)
    {
        this.addressId = AddressId;
    }

    public Address getAddress()
    {
        return this.address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Integer getContactId()
    {
        return this.contactId;
    }

    public void setContactId(Integer ContactId)
    {
        this.contactId = ContactId;
    }

    public Contact getContact()
    {
        return this.contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
    }

    public Integer getUserGroupId()
    {
        return this.userGroupId;
    }

    public void setUserGroupId(Integer UserGroupId)
    {
        this.userGroupId = UserGroupId;
    }

    public UserGroup getUserGroup()
    {
        return this.userGroup;
    }

    public void setUserGroup(UserGroup userGroup)
    {
        this.userGroup = userGroup;
    }

    public ArrayList<Affiliate> getAffiliateList()
    {
        return this.affiliateList;
    }

    public void setAffiliateList(ArrayList<Affiliate> affiliateList)
    {
        this.affiliateList = affiliateList;
    }

    public ArrayList<BlogPost> getBlogPostList()
    {
        return this.blogPostList;
    }

    public void setBlogPostList(ArrayList<BlogPost> blogPostList)
    {
        this.blogPostList = blogPostList;
    }

    public ArrayList<Comment> getCommentList()
    {
        return this.commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList)
    {
        this.commentList = commentList;
    }

    public ArrayList<Customer> getCustomerList()
    {
        return this.customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList)
    {
        this.customerList = customerList;
    }

    public ArrayList<Mailinglist> getMailinglistList()
    {
        return this.mailinglistList;
    }

    public void setMailinglistList(ArrayList<Mailinglist> mailinglistList)
    {
        this.mailinglistList = mailinglistList;
    }

    public ArrayList<UserAction> getUserActionList()
    {
        return this.userActionList;
    }

    public void setUserActionList(ArrayList<UserAction> userActionList)
    {
        this.userActionList = userActionList;
    }

    public ArrayList<UserService> getUserServiceList()
    {
        return this.userServiceList;
    }

    public void setUserServiceList(ArrayList<UserService> userServiceList)
    {
        this.userServiceList = userServiceList;
    }

}
