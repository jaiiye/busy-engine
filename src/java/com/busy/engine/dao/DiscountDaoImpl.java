






















































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
    
    public class DiscountDaoImpl extends BasicConnection implements Serializable, DiscountDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public DiscountDaoImpl()
        {
            cachingEnabled = false;
        }

        public DiscountDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class DiscountCache
        {
            public static final ConcurrentLruCache<Integer, Discount> discountCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Discount i : findAll())
                {
                    getCache().put(i.getDiscountId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Discount> getCache()
        {
            return DiscountCache.discountCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Discount> buildCache(ArrayList<Discount> discountList)
        {        
            ConcurrentLruCache<Integer, Discount> cache = new ConcurrentLruCache<Integer, Discount>(discountList.size() + 1000);
            for (Discount i : discountList)
            {
                cache.put(i.getDiscountId(), i);
            }
            return cache;
        }

        private static ArrayList<Discount> findAll()
        {
            ArrayList<Discount> discount = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("discount");
                while (rs.next())
                {
                    discount.add(Discount.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Discount object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return discount;
        }
        
        @Override
        public Discount find(Integer id)
        {
            return findByColumn("DiscountId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Discount> findAll(Integer limit, Integer offset)
        {
            ArrayList<Discount> discountList = new ArrayList<Discount>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Discount, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    discountList = new ArrayList<Discount>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("discount", limit, offset);
                    while (rs.next())
                    {
                        discountList.add(Discount.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Discount object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return discountList;
         
        }
        
        @Override
        public ArrayList<Discount> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Discount> discountList = new ArrayList<Discount>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Discount, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    discountList = new ArrayList<Discount>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                discountList = new ArrayList<Discount>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("discount", limit, offset);
                    while (rs.next())
                    {
                        discountList.add(Discount.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Discount method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return discountList;            
        }
        
        @Override
        public ArrayList<Discount> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Discount> discountList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Discount(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Discount i = (Discount) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                discountList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            discountList = null;
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
                    getRecordsByColumnWithLimitOrOffset("discount", Discount.checkColumnName(columnName), columnValue, Discount.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        discountList.add(Discount.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Discount's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return discountList;
        } 
    
        @Override
        public int add(Discount obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Discount.checkColumnSize(obj.getDiscountName(), 100);
                
                
                
                
                Discount.checkColumnSize(obj.getCouponCode(), 100);
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO discount(DiscountId,DiscountName,DiscountAmount,DiscountPercent,StartDate,EndDate,CouponCode,DiscountStatus,AskCouponCode,UsePercentage,) VALUES (?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getDiscountId());
                preparedStatement.setString(1, obj.getDiscountName());
                preparedStatement.setDouble(2, obj.getDiscountAmount());
                preparedStatement.setDouble(3, obj.getDiscountPercent());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(obj.getEndDate().getTime()));
                preparedStatement.setString(6, obj.getCouponCode());
                preparedStatement.setInt(7, obj.getDiscountStatus());
                preparedStatement.setInt(8, obj.getAskCouponCode());
                preparedStatement.setInt(9, obj.getUsePercentage());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from discount;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Discount's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setDiscountId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Discount update(Discount obj)
        {
           try
            {   
                
                Discount.checkColumnSize(obj.getDiscountName(), 100);
                
                
                
                
                Discount.checkColumnSize(obj.getCouponCode(), 100);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE discount SET com.busy.util.DatabaseColumn@193d9918=?,com.busy.util.DatabaseColumn@fb7c9db=?,com.busy.util.DatabaseColumn@1d81de06=?,com.busy.util.DatabaseColumn@3bc8bf6d=?,com.busy.util.DatabaseColumn@4ec80455=?,com.busy.util.DatabaseColumn@7f80e546=?,com.busy.util.DatabaseColumn@6ce2655e=?,com.busy.util.DatabaseColumn@122f9c78=?,com.busy.util.DatabaseColumn@1ccbd8c4=? WHERE DiscountId=?;");                    
                preparedStatement.setInt(0, obj.getDiscountId());
                preparedStatement.setString(1, obj.getDiscountName());
                preparedStatement.setDouble(2, obj.getDiscountAmount());
                preparedStatement.setDouble(3, obj.getDiscountPercent());
                preparedStatement.setDate(4, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(obj.getEndDate().getTime()));
                preparedStatement.setString(6, obj.getCouponCode());
                preparedStatement.setInt(7, obj.getDiscountStatus());
                preparedStatement.setInt(8, obj.getAskCouponCode());
                preparedStatement.setInt(9, obj.getUsePercentage());
                preparedStatement.setInt(10, obj.getDiscountId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getDiscountId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Discount's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("discount");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Discount discount)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Discount discount)
        {
            discount.setCategoryList(new CategoryDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
discount.setUserGroupList(new UserGroupDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Discount obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM discount WHERE DiscountId=" + obj.getDiscountId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Discount's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getDiscountId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM discount WHERE DiscountId=" + id + ";");           
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
                updateQuery("DELETE FROM discount;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Discount's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM discount WHERE " + Discount.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Discount's removeByColumn method error: " + ex.getMessage());
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
                        Discount i = (Discount) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getDiscountId());
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
        
                    
        public void getRelatedCategoryList(Discount discount)
        {           
            discount.setCategoryList(new CategoryDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
                    
        public void getRelatedCustomerOrderList(Discount discount)
        {           
            discount.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
                    
        public void getRelatedItemDiscountList(Discount discount)
        {           
            discount.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
                    
        public void getRelatedUserGroupList(Discount discount)
        {           
            discount.setUserGroupList(new UserGroupDaoImpl().findByColumn("DiscountId", discount.getDiscountId().toString(),null,null));
        }        
        
                             
    }

