
























































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.data.Column;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class ContactDaoImpl extends BasicConnection implements Serializable, ContactDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ContactDaoImpl()
        {
            cachingEnabled = false;
        }

        public ContactDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ContactCache
        {
            public static final ConcurrentLruCache<Integer, Contact> contactCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Contact i : findAll())
                {
                    getCache().put(i.getContactId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Contact> getCache()
        {
            return ContactCache.contactCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Contact> buildCache(ArrayList<Contact> contactList)
        {        
            ConcurrentLruCache<Integer, Contact> cache = new ConcurrentLruCache<Integer, Contact>(contactList.size() + 1000);
            for (Contact i : contactList)
            {
                cache.put(i.getContactId(), i);
            }
            return cache;
        }

        private static ArrayList<Contact> findAll()
        {
            ArrayList<Contact> contact = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("contact");
                while (rs.next())
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
        public Contact find(Integer id)
        {
            return findByColumn("ContactId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Contact findWithInfo(Integer id)
        {
            Contact contact = findByColumn("ContactId", id.toString(), null, null).get(0);
            
            
            
            return contact;
        }
        
        @Override
        public ArrayList<Contact> findAll(Integer limit, Integer offset)
        {
            ArrayList<Contact> contactList = new ArrayList<Contact>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Contact, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    contactList = new ArrayList<Contact>(getCache().getValues());
                }
                else
                {
                    cacheNotUsed = true;
                }
            }

            if( !cachingEnabled || cacheNotUsed)
            {
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
                    System.out.println("Contact object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return contactList;
         
        }
        
        @Override
        public ArrayList<Contact> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Contact> contactList = new ArrayList<Contact>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Contact, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    contactList = new ArrayList<Contact>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                contactList = new ArrayList<Contact>();
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
            }
            return contactList;            
        }
        
        @Override
        public ArrayList<Contact> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Contact> contactList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Contact(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Contact i = (Contact) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                contactList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            contactList = null;
                        }
                    }
                }
                else
                {
                    cacheNotUsed = true;
                }
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                try
                {
                    getRecordsByColumnWithLimitOrOffset("contact", Contact.checkColumnName(columnName), columnValue, Contact.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        contactList.add(Contact.process(rs));
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
            }
            return contactList;
        } 
        
        @Override
        public ArrayList<Contact> findByColumns(Column... columns)
        {
            ArrayList<Contact> contactList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(Contact.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("contact", columns);
                while (rs.next())
                {
                    contactList.add(Contact.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Contact's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return contactList;
        }
    
        @Override
        public int add(Contact obj)
        {        
            boolean success = false;
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
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Contact's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setContactId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
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
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getContactId(), obj);
                }            
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
            int count = 0;
            if (cachingEnabled)
            {
                count = getCache().size();
            }
            else
            {
                count = getAllRecordsCountByTableName("contact");
            }
            return count;
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
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getContactId());
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
            
            if(cachingEnabled && success)
            {
                getCache().remove(id);
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
        
            if(cachingEnabled && success)
            {
                getCache().clear();
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
            
            if(cachingEnabled && success)
            {
                ArrayList<Integer> keys = new ArrayList<Integer>();

                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        Contact i = (Contact) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getContactId());
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                    }
                }

                for(int id : keys)
                {
                    getCache().remove(id);
                }
            }
            
            return success;
        }
        
        public boolean isCachingEnabled()
        {
            return cachingEnabled;
        }
        
        public void setCachingEnabled(boolean cachingEnabled)
        {
            this.cachingEnabled = cachingEnabled;
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

