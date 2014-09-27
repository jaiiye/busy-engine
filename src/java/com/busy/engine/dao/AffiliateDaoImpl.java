






















































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.entity.*;
    import com.busy.engine.dao.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class AffiliateDaoImpl extends BasicConnection implements Serializable, AffiliateDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public AffiliateDaoImpl()
        {
            cachingEnabled = false;
        }

        public AffiliateDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class AffiliateCache
        {
            public static final ConcurrentLruCache<Integer, Affiliate> affiliateCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Affiliate i : findAll())
                {
                    getCache().put(i.getAffiliateId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Affiliate> getCache()
        {
            return AffiliateCache.affiliateCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Affiliate> buildCache(ArrayList<Affiliate> affiliateList)
        {        
            ConcurrentLruCache<Integer, Affiliate> cache = new ConcurrentLruCache<Integer, Affiliate>(affiliateList.size() + 1000);
            for (Affiliate i : affiliateList)
            {
                cache.put(i.getAffiliateId(), i);
            }
            return cache;
        }

        private static ArrayList<Affiliate> findAll()
        {
            ArrayList<Affiliate> affiliate = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("affiliate");
                while (rs.next())
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
        public Affiliate find(Integer id)
        {
            return findByColumn("AffiliateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Affiliate> findAll(Integer limit, Integer offset)
        {
            ArrayList<Affiliate> affiliateList = new ArrayList<Affiliate>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Affiliate, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    affiliateList = new ArrayList<Affiliate>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("affiliate", limit, offset);
                    while (rs.next())
                    {
                        affiliateList.add(Affiliate.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Affiliate object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return affiliateList;
         
        }
        
        @Override
        public ArrayList<Affiliate> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Affiliate> affiliateList = new ArrayList<Affiliate>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Affiliate, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    affiliateList = new ArrayList<Affiliate>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Affiliate affiliate = (Affiliate) e.getValue();

                            
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
                        System.out.println("Object Affiliate method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                affiliateList = new ArrayList<Affiliate>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("affiliate", limit, offset);
                    while (rs.next())
                    {
                        affiliateList.add(Affiliate.process(rs));
                    }

                    
                    
                        for (Affiliate affiliate : affiliateList)
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
            }
            return affiliateList;            
        }
        
        @Override
        public ArrayList<Affiliate> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Affiliate> affiliateList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Affiliate(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Affiliate i = (Affiliate) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                affiliateList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            affiliateList = null;
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
                    getRecordsByColumnWithLimitOrOffset("affiliate", Affiliate.checkColumnName(columnName), columnValue, Affiliate.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        affiliateList.add(Affiliate.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Affiliate's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return affiliateList;
        } 
    
        @Override
        public int add(Affiliate obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Affiliate.checkColumnSize(obj.getCompanyName(), 100);
                Affiliate.checkColumnSize(obj.getEmail(), 50);
                Affiliate.checkColumnSize(obj.getPhone(), 15);
                Affiliate.checkColumnSize(obj.getFax(), 15);
                Affiliate.checkColumnSize(obj.getWebUrl(), 255);
                Affiliate.checkColumnSize(obj.getDetails(), 65535);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO affiliate(AffiliateId,CompanyName,Email,Phone,Fax,WebUrl,Details,ServiceHours,AffiliateStatus,UserId,ContactId,AddressId,) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getAffiliateId());
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

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from affiliate;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setAffiliateId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
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
                prepareStatement("UPDATE affiliate SET com.busy.util.DatabaseColumn@6e12340b=?,com.busy.util.DatabaseColumn@c3ac171=?,com.busy.util.DatabaseColumn@5b954a75=?,com.busy.util.DatabaseColumn@4f4bdded=?,com.busy.util.DatabaseColumn@30860a63=?,com.busy.util.DatabaseColumn@6aed744b=?,com.busy.util.DatabaseColumn@1b5a5d81=?,com.busy.util.DatabaseColumn@aef4fb2=?,com.busy.util.DatabaseColumn@a3a899e=?,com.busy.util.DatabaseColumn@b74ebbe=?,com.busy.util.DatabaseColumn@420b0904=? WHERE AffiliateId=?;");                    
                preparedStatement.setInt(0, obj.getAffiliateId());
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
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getAffiliateId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("affiliate");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Affiliate affiliate)
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
              
        }
        
        @Override
        public void getRelatedObjects(Affiliate affiliate)
        {
            affiliate.setOrderList(new OrderDaoImpl().findByColumn("AffiliateId", affiliate.getAffiliateId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Affiliate obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM affiliate WHERE AffiliateId=" + obj.getAffiliateId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getAffiliateId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM affiliate WHERE AffiliateId=" + id + ";");           
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
                updateQuery("DELETE FROM affiliate;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM affiliate WHERE " + Affiliate.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Affiliate's removeByColumn method error: " + ex.getMessage());
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
                        Affiliate i = (Affiliate) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getAffiliateId());
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
        
                    
        public void getRelatedOrderList(Affiliate affiliate)
        {           
            affiliate.setOrderList(new OrderDaoImpl().findByColumn("AffiliateId", affiliate.getAffiliateId().toString(),null,null));
        }        
        
                             
    }

