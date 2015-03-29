






















































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class MailinglistDaoImpl extends BasicConnection implements Serializable, MailinglistDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public MailinglistDaoImpl()
        {
            cachingEnabled = false;
        }

        public MailinglistDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class MailinglistCache
        {
            public static final ConcurrentLruCache<Integer, Mailinglist> mailinglistCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Mailinglist i : findAll())
                {
                    getCache().put(i.getMailinglistId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Mailinglist> getCache()
        {
            return MailinglistCache.mailinglistCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Mailinglist> buildCache(ArrayList<Mailinglist> mailinglistList)
        {        
            ConcurrentLruCache<Integer, Mailinglist> cache = new ConcurrentLruCache<Integer, Mailinglist>(mailinglistList.size() + 1000);
            for (Mailinglist i : mailinglistList)
            {
                cache.put(i.getMailinglistId(), i);
            }
            return cache;
        }

        private static ArrayList<Mailinglist> findAll()
        {
            ArrayList<Mailinglist> mailinglist = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("mailinglist");
                while (rs.next())
                {
                    mailinglist.add(Mailinglist.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Mailinglist object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
        }
        
        @Override
        public Mailinglist find(Integer id)
        {
            return findByColumn("MailinglistId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Mailinglist findWithInfo(Integer id)
        {
            Mailinglist mailinglist = findByColumn("MailinglistId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("User", mailinglist.getUserId().toString());
                    mailinglist.setUser(User.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Mailinglist method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return mailinglist;
        }
        
        @Override
        public ArrayList<Mailinglist> findAll(Integer limit, Integer offset)
        {
            ArrayList<Mailinglist> mailinglistList = new ArrayList<Mailinglist>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Mailinglist, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    mailinglistList = new ArrayList<Mailinglist>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("mailinglist", limit, offset);
                    while (rs.next())
                    {
                        mailinglistList.add(Mailinglist.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Mailinglist object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return mailinglistList;
         
        }
        
        @Override
        public ArrayList<Mailinglist> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Mailinglist> mailinglistList = new ArrayList<Mailinglist>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Mailinglist, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    mailinglistList = new ArrayList<Mailinglist>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Mailinglist mailinglist = (Mailinglist) e.getValue();

                            
                                getRecordById("User", mailinglist.getUserId().toString());
                                mailinglist.setUser(User.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Mailinglist method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                mailinglistList = new ArrayList<Mailinglist>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("mailinglist", limit, offset);
                    while (rs.next())
                    {
                        mailinglistList.add(Mailinglist.process(rs));
                    }

                    
                    
                        for (Mailinglist mailinglist : mailinglistList)
                        {                        
                            
                                getRecordById("User", mailinglist.getUserId().toString());
                                mailinglist.setUser(User.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Mailinglist method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return mailinglistList;            
        }
        
        @Override
        public ArrayList<Mailinglist> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Mailinglist> mailinglistList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Mailinglist(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Mailinglist i = (Mailinglist) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                mailinglistList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            mailinglistList = null;
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
                    getRecordsByColumnWithLimitOrOffset("mailinglist", Mailinglist.checkColumnName(columnName), columnValue, Mailinglist.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        mailinglistList.add(Mailinglist.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Mailinglist's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return mailinglistList;
        } 
    
        @Override
        public int add(Mailinglist obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Mailinglist.checkColumnSize(obj.getFullName(), 45);
                Mailinglist.checkColumnSize(obj.getEmail(), 245);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO mailinglist(FullName,Email,ListStatus,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFullName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setInt(3, obj.getListStatus());
                preparedStatement.setInt(4, obj.getUserId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from mailinglist;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setMailinglistId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Mailinglist update(Mailinglist obj)
        {
           try
            {   
                
                Mailinglist.checkColumnSize(obj.getFullName(), 45);
                Mailinglist.checkColumnSize(obj.getEmail(), 245);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE mailinglist SET FullName=?,Email=?,ListStatus=?,UserId=? WHERE MailinglistId=?;");                    
                preparedStatement.setString(1, obj.getFullName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setInt(3, obj.getListStatus());
                preparedStatement.setInt(4, obj.getUserId());
                preparedStatement.setInt(5, obj.getMailinglistId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getMailinglistId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("mailinglist");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Mailinglist mailinglist)
        {
            
                try
                { 
                    
                            getRecordById("User", mailinglist.getUserId().toString());
                            mailinglist.setUser(User.process(rs));                                       
                    
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
        public void getRelatedObjects(Mailinglist mailinglist)
        {
             
        }
        
        @Override
        public boolean remove(Mailinglist obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM mailinglist WHERE MailinglistId=" + obj.getMailinglistId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getMailinglistId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM mailinglist WHERE MailinglistId=" + id + ";");           
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
                updateQuery("DELETE FROM mailinglist;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM mailinglist WHERE " + Mailinglist.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Mailinglist's removeByColumn method error: " + ex.getMessage());
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
                        Mailinglist i = (Mailinglist) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getMailinglistId());
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
        
        
            
        
        
        public void getRelatedUser(Mailinglist mailinglist)
        {            
            try
            {                 
                getRecordById("User", mailinglist.getUserId().toString());
                mailinglist.setUser(User.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedUserWithInfo(Mailinglist mailinglist)
        {            
            mailinglist.setUser(new UserDaoImpl().findWithInfo(mailinglist.getUserId()));
        }
          
        
    }

