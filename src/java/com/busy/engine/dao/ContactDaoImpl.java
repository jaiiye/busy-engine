





































    package com.busy.engine.dao;

import com.busy.engine.entity.Contact;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("Contact's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
        } 
    
        @Override
        public int add(Contact obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from contact;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Contact's add method error: " + ex.getMessage());
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
                System.out.println("Contact's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("contact");
        }
        
        
        @Override
        public void getRelatedInfo(Contact contact)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Contact contact)
        {
            contact.setAffiliateList(new AffiliateDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
contact.setCustomerList(new CustomerDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
contact.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
contact.setUserList(new UserDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Contact obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM contact WHERE ContactId=" + obj.getContactId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Contact's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM contact WHERE ContactId=" + id + ";");           
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
                updateQuery("DELETE FROM contact;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Contact's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM contact WHERE " + Contact.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Contact's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedAffiliateList(Contact contact)
        {           
            contact.setAffiliateList(new AffiliateDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
        }        
                    
        public void getRelatedCustomerList(Contact contact)
        {           
            contact.setCustomerList(new CustomerDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
        }        
                    
        public void getRelatedItemLocationList(Contact contact)
        {           
            contact.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
        }        
                    
        public void getRelatedUserList(Contact contact)
        {           
            contact.setUserList(new UserDaoImpl().findByColumn("ContactId", contact.getContactId().toString(),null,null));
        }        
        
                             
    }

