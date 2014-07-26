





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ContactDaoImpl extends BasicConnection implements Serializable, ContactDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Contact find(Integer id)
        {
            return findByColumn("ContactId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Contact> findAll(Integer limit, Integer offset)
        {
            ArrayList<Contact> contact = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("contact");
                while(rs.next())
                {
                    contact.add(Contact.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Contact object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
         
        }
        
        @Override
        public ArrayList<Contact> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Contact> contactList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("contact", limit, offset);
                while (rs.next())
                {
                    contactList.add(Contact.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Contact method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contactList;
        }
        
        @Override
        public ArrayList<Contact> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Contact> contact = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("contact", Contact.checkColumnName(columnName), columnValue, Contact.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   contact.add(Contact.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Contact's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
        } 
    
        @Override
        public void add(Contact obj)
        {
            try
            {
                
                Contact.checkColumnSize(obj.getTitle(), 5);
                Contact.checkColumnSize(obj.getFirstName(), 50);
                Contact.checkColumnSize(obj.getLastName(), 50);
                Contact.checkColumnSize(obj.getPosition(), 255);
                Contact.checkColumnSize(obj.getPhone(), 15);
                Contact.checkColumnSize(obj.getFax(), 15);
                Contact.checkColumnSize(obj.getEmail(), 255);
                
                Contact.checkColumnSize(obj.getWebUrl(), 255);
                Contact.checkColumnSize(obj.getInfo(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO contact(Title,FirstName,LastName,Position,Phone,Fax,Email,ContactStatus,WebUrl,Info) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getFirstName());
                preparedStatement.setString(3, obj.getLastName());
                preparedStatement.setString(4, obj.getPosition());
                preparedStatement.setString(5, obj.getPhone());
                preparedStatement.setString(6, obj.getFax());
                preparedStatement.setString(7, obj.getEmail());
                preparedStatement.setInt(8, obj.getContactStatus());
                preparedStatement.setString(9, obj.getWebUrl());
                preparedStatement.setString(10, obj.getInfo());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Contact's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Title, String FirstName, String LastName, String Position, String Phone, String Fax, String Email, Integer ContactStatus, String WebUrl, String Info)
        {   
            int id = 0;
            try
            {
                
                Contact.checkColumnSize(Title, 5);
                Contact.checkColumnSize(FirstName, 50);
                Contact.checkColumnSize(LastName, 50);
                Contact.checkColumnSize(Position, 255);
                Contact.checkColumnSize(Phone, 15);
                Contact.checkColumnSize(Fax, 15);
                Contact.checkColumnSize(Email, 255);
                
                Contact.checkColumnSize(WebUrl, 255);
                Contact.checkColumnSize(Info, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO contact(Title,FirstName,LastName,Position,Phone,Fax,Email,ContactStatus,WebUrl,Info) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, FirstName);
                preparedStatement.setString(3, LastName);
                preparedStatement.setString(4, Position);
                preparedStatement.setString(5, Phone);
                preparedStatement.setString(6, Fax);
                preparedStatement.setString(7, Email);
                preparedStatement.setInt(8, ContactStatus);
                preparedStatement.setString(9, WebUrl);
                preparedStatement.setString(10, Info);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from contact;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public Contact update(Contact obj)
        {
           try
            {   
                
                Contact.checkColumnSize(obj.getTitle(), 5);
                Contact.checkColumnSize(obj.getFirstName(), 50);
                Contact.checkColumnSize(obj.getLastName(), 50);
                Contact.checkColumnSize(obj.getPosition(), 255);
                Contact.checkColumnSize(obj.getPhone(), 15);
                Contact.checkColumnSize(obj.getFax(), 15);
                Contact.checkColumnSize(obj.getEmail(), 255);
                
                Contact.checkColumnSize(obj.getWebUrl(), 255);
                Contact.checkColumnSize(obj.getInfo(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE contact SET Title=?,FirstName=?,LastName=?,Position=?,Phone=?,Fax=?,Email=?,ContactStatus=?,WebUrl=?,Info=? WHERE ContactId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getFirstName());
                preparedStatement.setString(3, obj.getLastName());
                preparedStatement.setString(4, obj.getPosition());
                preparedStatement.setString(5, obj.getPhone());
                preparedStatement.setString(6, obj.getFax());
                preparedStatement.setString(7, obj.getEmail());
                preparedStatement.setInt(8, obj.getContactStatus());
                preparedStatement.setString(9, obj.getWebUrl());
                preparedStatement.setString(10, obj.getInfo());
                preparedStatement.setInt(11, obj.getContactId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ContactId,String Title,String FirstName,String LastName,String Position,String Phone,String Fax,String Email,Integer ContactStatus,String WebUrl,String Info)
        {  
            try
            {   
                
                Contact.checkColumnSize(Title, 5);
                Contact.checkColumnSize(FirstName, 50);
                Contact.checkColumnSize(LastName, 50);
                Contact.checkColumnSize(Position, 255);
                Contact.checkColumnSize(Phone, 15);
                Contact.checkColumnSize(Fax, 15);
                Contact.checkColumnSize(Email, 255);
                
                Contact.checkColumnSize(WebUrl, 255);
                Contact.checkColumnSize(Info, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE contact SET Title=?,FirstName=?,LastName=?,Position=?,Phone=?,Fax=?,Email=?,ContactStatus=?,WebUrl=?,Info=? WHERE ContactId=?;");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, FirstName);
                preparedStatement.setString(3, LastName);
                preparedStatement.setString(4, Position);
                preparedStatement.setString(5, Phone);
                preparedStatement.setString(6, Fax);
                preparedStatement.setString(7, Email);
                preparedStatement.setInt(8, ContactStatus);
                preparedStatement.setString(9, WebUrl);
                preparedStatement.setString(10, Info);
                preparedStatement.setInt(11, ContactId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("contact");
        }
        
        
        @Override
        public Contact getRelatedInfo(Contact contact)
        {
              
            
            return contact;
        }
        
        
        @Override
        public Contact getRelatedObjects(Contact contact)
        {
            contact.setAffiliateList(new AffiliateDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
contact.setCustomerList(new CustomerDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
contact.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
contact.setUserList(new UserDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
             
            return contact;
        }
        
        
        
        @Override
        public void remove(Contact obj)
        {            
            try
            {
                updateQuery("DELETE FROM contact WHERE ContactId=" + obj.getContactId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteContactById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM contact WHERE ContactId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteContactById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM contact;");
            }
            catch (Exception ex)
            {
                System.out.println("Contact's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM contact WHERE " + Contact.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Contact's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Contact getRelatedAffiliateList(Contact contact)
        {           
            contact.setAffiliateList(new AffiliateDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
            return contact;
        }        
                    
        public Contact getRelatedCustomerList(Contact contact)
        {           
            contact.setCustomerList(new CustomerDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
            return contact;
        }        
                    
        public Contact getRelatedItemLocationList(Contact contact)
        {           
            contact.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
            return contact;
        }        
                    
        public Contact getRelatedUserList(Contact contact)
        {           
            contact.setUserList(new UserDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
            return contact;
        }        
        
                             
    }

