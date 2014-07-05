


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Affiliate extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_AFFILIATEID = "AffiliateId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_INTERNETURL = "InternetURL";
        public static final String PROP_EMAIL = "EMail";
        public static final String PROP_PHONE = "Phone";
        public static final String PROP_FAX = "Fax";
        public static final String PROP_DETAILS = "Details";
        public static final String PROP_SERVICEHOURS = "ServiceHours";
        public static final String PROP_USERID = "UserId";
        public static final String PROP_CONTACTID = "ContactId";
        public static final String PROP_ADDRESSID = "AddressId";
        
        
        private Integer affiliateId;
        private String name;
        private String internetURL;
        private String eMail;
        private String phone;
        private String fax;
        private String details;
        private Integer serviceHours;
        private Integer userId;
        private Integer contactId;
        private Integer addressId;
        
        
        public Affiliate()
        {
            this.affiliateId = 0; 
       this.name = ""; 
       this.internetURL = ""; 
       this.eMail = ""; 
       this.phone = ""; 
       this.fax = ""; 
       this.details = ""; 
       this.serviceHours = 0; 
       this.userId = 0; 
       this.contactId = 0; 
       this.addressId = 0; 
        }
        
        public Affiliate(Integer AffiliateId, String Name, String InternetURL, String EMail, String Phone, String Fax, String Details, Integer ServiceHours, Integer UserId, Integer ContactId, Integer AddressId)
        {
            this.affiliateId = AffiliateId;
       this.name = Name;
       this.internetURL = InternetURL;
       this.eMail = EMail;
       this.phone = Phone;
       this.fax = Fax;
       this.details = Details;
       this.serviceHours = ServiceHours;
       this.userId = UserId;
       this.contactId = ContactId;
       this.addressId = AddressId;
        } 
        
        
            public Integer getAffiliateId()
            {
                return this.affiliateId;
            }
            
            public void setAffiliateId(Integer AffiliateId)
            {
                this.affiliateId = AffiliateId;
            }
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
        
            public String getInternetURL()
            {
                return this.internetURL;
            }
            
            public void setInternetURL(String InternetURL)
            {
                this.internetURL = InternetURL;
            }
        
            public String getEMail()
            {
                return this.eMail;
            }
            
            public void setEMail(String EMail)
            {
                this.eMail = EMail;
            }
        
            public String getPhone()
            {
                return this.phone;
            }
            
            public void setPhone(String Phone)
            {
                this.phone = Phone;
            }
        
            public String getFax()
            {
                return this.fax;
            }
            
            public void setFax(String Fax)
            {
                this.fax = Fax;
            }
        
            public String getDetails()
            {
                return this.details;
            }
            
            public void setDetails(String Details)
            {
                this.details = Details;
            }
        
            public Integer getServiceHours()
            {
                return this.serviceHours;
            }
            
            public void setServiceHours(Integer ServiceHours)
            {
                this.serviceHours = ServiceHours;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
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
            if(column.equals(Affiliate.PROP_AFFILIATEID) || column.equals(Affiliate.PROP_NAME) || column.equals(Affiliate.PROP_INTERNETURL) || column.equals(Affiliate.PROP_EMAIL) || column.equals(Affiliate.PROP_PHONE) || column.equals(Affiliate.PROP_FAX) || column.equals(Affiliate.PROP_DETAILS) || column.equals(Affiliate.PROP_SERVICEHOURS) || column.equals(Affiliate.PROP_USERID) || column.equals(Affiliate.PROP_CONTACTID) || column.equals(Affiliate.PROP_ADDRESSID) )
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
            if (column.equals(Affiliate.PROP_AFFILIATEID) || column.equals(Affiliate.PROP_SERVICEHOURS) || column.equals(Affiliate.PROP_USERID) || column.equals(Affiliate.PROP_CONTACTID) || column.equals(Affiliate.PROP_ADDRESSID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Affiliate processAffiliate(ResultSet rs) throws SQLException
        {        
            return new Affiliate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
        }
        
        public static int addAffiliate(String Name, String InternetURL, String EMail, String Phone, String Fax, String Details, Integer ServiceHours, Integer UserId, Integer ContactId, Integer AddressId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 30);
                checkColumnSize(InternetURL, 255);
                checkColumnSize(EMail, 50);
                checkColumnSize(Phone, 15);
                checkColumnSize(Fax, 15);
                checkColumnSize(Details, 24);
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO affiliate(Name,InternetURL,EMail,Phone,Fax,Details,ServiceHours,UserId,ContactId,AddressId) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, InternetURL);
                preparedStatement.setString(3, EMail);
                preparedStatement.setString(4, Phone);
                preparedStatement.setString(5, Fax);
                preparedStatement.setString(6, Details);
                preparedStatement.setInt(7, ServiceHours);
                preparedStatement.setInt(8, UserId);
                preparedStatement.setInt(9, ContactId);
                preparedStatement.setInt(10, AddressId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from affiliate;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addAffiliate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllAffiliateCount()
        {
            return getAllRecordsCountByTableName("affiliate");        
        }
        
        public static ArrayList<Affiliate> getAllAffiliate()
        {
            ArrayList<Affiliate> affiliate = new ArrayList<Affiliate>();
            try
            {
                getAllRecordsByTableName("affiliate");
                while(rs.next())
                {
                    affiliate.add(processAffiliate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllAffiliate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliate;
        }
                
        public static ArrayList<Affiliate> getAffiliatePaged(int limit, int offset)
        {
            ArrayList<Affiliate> affiliate = new ArrayList<Affiliate>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("affiliate", limit, offset);
                while (rs.next())
                {
                    affiliate.add(processAffiliate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAffiliatePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliate;
        } 
        
        public static ArrayList<Affiliate> getAllAffiliateByColumn(String columnName, String columnValue)
        {
            ArrayList<Affiliate> affiliate = new ArrayList<Affiliate>();
            try
            {
                getAllRecordsByColumn("affiliate", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    affiliate.add(processAffiliate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllAffiliateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliate;
        }
        
        public static Affiliate getAffiliateByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Affiliate affiliate = new Affiliate();
            try
            {
                getRecordsByColumnWithLimitAndOffset("affiliate", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   affiliate = processAffiliate(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAffiliateByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliate;
        }                
                
        public static void updateAffiliate(Integer AffiliateId,String Name,String InternetURL,String EMail,String Phone,String Fax,String Details,Integer ServiceHours,Integer UserId,Integer ContactId,Integer AddressId)
        {  
            try
            {   
                
                checkColumnSize(Name, 30);
                checkColumnSize(InternetURL, 255);
                checkColumnSize(EMail, 50);
                checkColumnSize(Phone, 15);
                checkColumnSize(Fax, 15);
                checkColumnSize(Details, 24);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE affiliate SET Name=?,InternetURL=?,EMail=?,Phone=?,Fax=?,Details=?,ServiceHours=?,UserId=?,ContactId=?,AddressId=? WHERE AffiliateId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, InternetURL);
                preparedStatement.setString(3, EMail);
                preparedStatement.setString(4, Phone);
                preparedStatement.setString(5, Fax);
                preparedStatement.setString(6, Details);
                preparedStatement.setInt(7, ServiceHours);
                preparedStatement.setInt(8, UserId);
                preparedStatement.setInt(9, ContactId);
                preparedStatement.setInt(10, AddressId);
                preparedStatement.setInt(11, AffiliateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateAffiliate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllAffiliate()
        {
            try
            {
                updateQuery("DELETE FROM affiliate;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllAffiliate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAffiliateById(String id)
        {
            try
            {
                updateQuery("DELETE FROM affiliate WHERE AffiliateId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteAffiliateById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteAffiliateByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM affiliate WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteAffiliateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

