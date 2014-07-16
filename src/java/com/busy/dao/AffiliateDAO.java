











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Affiliate;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class AffiliateDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Affiliate.PROP_AFFILIATE_ID) || column.equals(Affiliate.PROP_COMPANY_NAME) || column.equals(Affiliate.PROP_EMAIL) || column.equals(Affiliate.PROP_PHONE) || column.equals(Affiliate.PROP_FAX) || column.equals(Affiliate.PROP_WEB_URL) || column.equals(Affiliate.PROP_DETAILS) || column.equals(Affiliate.PROP_SERVICE_HOURS) || column.equals(Affiliate.PROP_STATUS) || column.equals(Affiliate.PROP_USER_ID) || column.equals(Affiliate.PROP_CONTACT_ID) || column.equals(Affiliate.PROP_ADDRESS_ID) )
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
            if (column.equals(Affiliate.PROP_AFFILIATE_ID) || column.equals(Affiliate.PROP_SERVICE_HOURS) || column.equals(Affiliate.PROP_STATUS) || column.equals(Affiliate.PROP_USER_ID) || column.equals(Affiliate.PROP_CONTACT_ID) || column.equals(Affiliate.PROP_ADDRESS_ID) )
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
            return new Affiliate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
        }
        
        public static int addAffiliate(String CompanyName, String Email, String Phone, String Fax, String WebUrl, String Details, Integer ServiceHours, Integer Status, Integer UserId, Integer ContactId, Integer AddressId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(CompanyName, 100);
                checkColumnSize(Email, 50);
                checkColumnSize(Phone, 15);
                checkColumnSize(Fax, 15);
                checkColumnSize(WebUrl, 255);
                checkColumnSize(Details, 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO affiliate(CompanyName,Email,Phone,Fax,WebUrl,Details,ServiceHours,Status,UserId,ContactId,AddressId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, CompanyName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3, Phone);
                preparedStatement.setString(4, Fax);
                preparedStatement.setString(5, WebUrl);
                preparedStatement.setString(6, Details);
                preparedStatement.setInt(7, ServiceHours);
                preparedStatement.setInt(8, Status);
                preparedStatement.setInt(9, UserId);
                preparedStatement.setInt(10, ContactId);
                preparedStatement.setInt(11, AddressId);
                
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
                
        public static void updateAffiliate(Integer AffiliateId,String CompanyName,String Email,String Phone,String Fax,String WebUrl,String Details,Integer ServiceHours,Integer Status,Integer UserId,Integer ContactId,Integer AddressId)
        {  
            try
            {   
                
                checkColumnSize(CompanyName, 100);
                checkColumnSize(Email, 50);
                checkColumnSize(Phone, 15);
                checkColumnSize(Fax, 15);
                checkColumnSize(WebUrl, 255);
                checkColumnSize(Details, 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE affiliate SET CompanyName=?,Email=?,Phone=?,Fax=?,WebUrl=?,Details=?,ServiceHours=?,Status=?,UserId=?,ContactId=?,AddressId=? WHERE AffiliateId=?;");                    
                preparedStatement.setString(1, CompanyName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3, Phone);
                preparedStatement.setString(4, Fax);
                preparedStatement.setString(5, WebUrl);
                preparedStatement.setString(6, Details);
                preparedStatement.setInt(7, ServiceHours);
                preparedStatement.setInt(8, Status);
                preparedStatement.setInt(9, UserId);
                preparedStatement.setInt(10, ContactId);
                preparedStatement.setInt(11, AddressId);
                preparedStatement.setInt(12, AffiliateId);
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

