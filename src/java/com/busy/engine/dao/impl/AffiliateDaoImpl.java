





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class AffiliateDaoImpl extends BasicConnection implements Serializable, AffiliateDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Affiliate find(Integer id)
        {
            return findByColumn("AffiliateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Affiliate> findAll(Integer limit, Integer offset)
        {
            ArrayList<Affiliate> affiliate = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("affiliate");
                while(rs.next())
                {
                    affiliate.add(Affiliate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Affiliate object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliate;
         
        }
        
        @Override
        public ArrayList<Affiliate> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Affiliate> affiliateList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("affiliate", limit, offset);
                while (rs.next())
                {
                    affiliateList.add(Affiliate.process(rs));
                }

                
                    for(Affiliate affiliate : affiliateList)
                    {
                        
                            getRecordById("User", affiliate.getUserId().toString());
                            affiliate.setUser(User.process(rs));               
                        
                            getRecordById("Contact", affiliate.getContactId().toString());
                            affiliate.setContact(Contact.process(rs));               
                        
                            getRecordById("Address", affiliate.getAddressId().toString());
                            affiliate.setAddress(Address.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Affiliate method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliateList;
        }
        
        @Override
        public ArrayList<Affiliate> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Affiliate> affiliate = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("affiliate", Affiliate.checkColumnName(columnName), columnValue, Affiliate.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   affiliate.add(Affiliate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Affiliate's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return affiliate;
        } 
    
        @Override
        public void add(Affiliate obj)
        {
            try
            {
                
                Affiliate.checkColumnSize(obj.getCompanyName(), 100);
                Affiliate.checkColumnSize(obj.getEmail(), 50);
                Affiliate.checkColumnSize(obj.getPhone(), 15);
                Affiliate.checkColumnSize(obj.getFax(), 15);
                Affiliate.checkColumnSize(obj.getWebUrl(), 255);
                Affiliate.checkColumnSize(obj.getDetails(), 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO affiliate(CompanyName,Email,Phone,Fax,WebUrl,Details,ServiceHours,AffiliateStatus,UserId,ContactId,AddressId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getCompanyName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setString(3, obj.getPhone());
                preparedStatement.setString(4, obj.getFax());
                preparedStatement.setString(5, obj.getWebUrl());
                preparedStatement.setString(6, obj.getDetails());
                preparedStatement.setInt(7, obj.getServiceHours());
                preparedStatement.setInt(8, obj.getAffiliateStatus());
                preparedStatement.setInt(9, obj.getUserId());
                preparedStatement.setInt(10, obj.getContactId());
                preparedStatement.setInt(11, obj.getAddressId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String CompanyName, String Email, String Phone, String Fax, String WebUrl, String Details, Integer ServiceHours, Integer AffiliateStatus, Integer UserId, Integer ContactId, Integer AddressId)
        {   
            int id = 0;
            try
            {
                
                Affiliate.checkColumnSize(CompanyName, 100);
                Affiliate.checkColumnSize(Email, 50);
                Affiliate.checkColumnSize(Phone, 15);
                Affiliate.checkColumnSize(Fax, 15);
                Affiliate.checkColumnSize(WebUrl, 255);
                Affiliate.checkColumnSize(Details, 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO affiliate(CompanyName,Email,Phone,Fax,WebUrl,Details,ServiceHours,AffiliateStatus,UserId,ContactId,AddressId) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, CompanyName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3, Phone);
                preparedStatement.setString(4, Fax);
                preparedStatement.setString(5, WebUrl);
                preparedStatement.setString(6, Details);
                preparedStatement.setInt(7, ServiceHours);
                preparedStatement.setInt(8, AffiliateStatus);
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
        
        
        @Override
        public Affiliate update(Affiliate obj)
        {
           try
            {   
                
                Affiliate.checkColumnSize(obj.getCompanyName(), 100);
                Affiliate.checkColumnSize(obj.getEmail(), 50);
                Affiliate.checkColumnSize(obj.getPhone(), 15);
                Affiliate.checkColumnSize(obj.getFax(), 15);
                Affiliate.checkColumnSize(obj.getWebUrl(), 255);
                Affiliate.checkColumnSize(obj.getDetails(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE affiliate SET CompanyName=?,Email=?,Phone=?,Fax=?,WebUrl=?,Details=?,ServiceHours=?,AffiliateStatus=?,UserId=?,ContactId=?,AddressId=? WHERE AffiliateId=?;");                    
                preparedStatement.setString(1, obj.getCompanyName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setString(3, obj.getPhone());
                preparedStatement.setString(4, obj.getFax());
                preparedStatement.setString(5, obj.getWebUrl());
                preparedStatement.setString(6, obj.getDetails());
                preparedStatement.setInt(7, obj.getServiceHours());
                preparedStatement.setInt(8, obj.getAffiliateStatus());
                preparedStatement.setInt(9, obj.getUserId());
                preparedStatement.setInt(10, obj.getContactId());
                preparedStatement.setInt(11, obj.getAddressId());
                preparedStatement.setInt(12, obj.getAffiliateId());
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
            return obj;
        }
        
        public static void update(Integer AffiliateId,String CompanyName,String Email,String Phone,String Fax,String WebUrl,String Details,Integer ServiceHours,Integer AffiliateStatus,Integer UserId,Integer ContactId,Integer AddressId)
        {  
            try
            {   
                
                Affiliate.checkColumnSize(CompanyName, 100);
                Affiliate.checkColumnSize(Email, 50);
                Affiliate.checkColumnSize(Phone, 15);
                Affiliate.checkColumnSize(Fax, 15);
                Affiliate.checkColumnSize(WebUrl, 255);
                Affiliate.checkColumnSize(Details, 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE affiliate SET CompanyName=?,Email=?,Phone=?,Fax=?,WebUrl=?,Details=?,ServiceHours=?,AffiliateStatus=?,UserId=?,ContactId=?,AddressId=? WHERE AffiliateId=?;");                    
                preparedStatement.setString(1, CompanyName);
                preparedStatement.setString(2, Email);
                preparedStatement.setString(3, Phone);
                preparedStatement.setString(4, Fax);
                preparedStatement.setString(5, WebUrl);
                preparedStatement.setString(6, Details);
                preparedStatement.setInt(7, ServiceHours);
                preparedStatement.setInt(8, AffiliateStatus);
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("affiliate");
        }
        
        
        @Override
        public Affiliate getRelatedInfo(Affiliate affiliate)
        {
            
                try
                { 
                    
                        getRecordById("User", affiliate.getUserId().toString());
                        affiliate.setUser(User.process(rs));               
                    
                        getRecordById("Contact", affiliate.getContactId().toString());
                        affiliate.setContact(Contact.process(rs));               
                    
                        getRecordById("Address", affiliate.getAddressId().toString());
                        affiliate.setAddress(Address.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return affiliate;
        }
        
        
        @Override
        public Affiliate getRelatedObjects(Affiliate affiliate)
        {
            affiliate.setOrderList(new OrderDaoImpl().findByColumn("AffiliateId", affiliate.getAffiliateId().toString(),null,null));
             
            return affiliate;
        }
        
        
        
        @Override
        public void remove(Affiliate obj)
        {            
            try
            {
                updateQuery("DELETE FROM affiliate WHERE AffiliateId=" + obj.getAffiliateId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM affiliate WHERE AffiliateId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM affiliate;");
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM affiliate WHERE " + Affiliate.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Affiliate getRelatedOrderList(Affiliate affiliate)
        {           
            affiliate.setOrderList(new OrderDaoImpl().findByColumn("AffiliateId", affiliate.getAffiliateId().toString(),null,null));
            return affiliate;
        }        
        
                             
    }

