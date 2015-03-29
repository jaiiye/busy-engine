






















































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
    
    public class VendorTypeDaoImpl extends BasicConnection implements Serializable, VendorTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public VendorTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public VendorTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class VendorTypeCache
        {
            public static final ConcurrentLruCache<Integer, VendorType> vendorTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (VendorType i : findAll())
                {
                    getCache().put(i.getVendorTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, VendorType> getCache()
        {
            return VendorTypeCache.vendorTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, VendorType> buildCache(ArrayList<VendorType> vendorTypeList)
        {        
            ConcurrentLruCache<Integer, VendorType> cache = new ConcurrentLruCache<Integer, VendorType>(vendorTypeList.size() + 1000);
            for (VendorType i : vendorTypeList)
            {
                cache.put(i.getVendorTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<VendorType> findAll()
        {
            ArrayList<VendorType> vendorType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("vendorType");
                while (rs.next())
                {
                    vendorType.add(VendorType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("VendorType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendorType;
        }
        
        @Override
        public VendorType find(Integer id)
        {
            return findByColumn("VendorTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public VendorType findWithInfo(Integer id)
        {
            VendorType vendorType = findByColumn("VendorTypeId", id.toString(), null, null).get(0);
            
            
            
            return vendorType;
        }
        
        @Override
        public ArrayList<VendorType> findAll(Integer limit, Integer offset)
        {
            ArrayList<VendorType> vendorTypeList = new ArrayList<VendorType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for VendorType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    vendorTypeList = new ArrayList<VendorType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("vendor_type", limit, offset);
                    while (rs.next())
                    {
                        vendorTypeList.add(VendorType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("VendorType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return vendorTypeList;
         
        }
        
        @Override
        public ArrayList<VendorType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<VendorType> vendorTypeList = new ArrayList<VendorType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for VendorType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    vendorTypeList = new ArrayList<VendorType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                vendorTypeList = new ArrayList<VendorType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("vendor_type", limit, offset);
                    while (rs.next())
                    {
                        vendorTypeList.add(VendorType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object VendorType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return vendorTypeList;            
        }
        
        @Override
        public ArrayList<VendorType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<VendorType> vendorTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for VendorType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            VendorType i = (VendorType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                vendorTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            vendorTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("vendor_type", VendorType.checkColumnName(columnName), columnValue, VendorType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        vendorTypeList.add(VendorType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("VendorType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return vendorTypeList;
        } 
    
        @Override
        public int add(VendorType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                VendorType.checkColumnSize(obj.getTypeName(), 100);
                  

                openConnection();
                prepareStatement("INSERT INTO vendor_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from vendor_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setVendorTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public VendorType update(VendorType obj)
        {
           try
            {   
                
                VendorType.checkColumnSize(obj.getTypeName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE vendor_type SET TypeName=? WHERE VendorTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getVendorTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getVendorTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("vendor_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(VendorType vendor_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(VendorType vendor_type)
        {
            vendor_type.setVendorList(new VendorDaoImpl().findByColumn("VendorTypeId", vendor_type.getVendorTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(VendorType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + obj.getVendorTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getVendorTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM vendor_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM vendor_type WHERE " + VendorType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's removeByColumn method error: " + ex.getMessage());
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
                        VendorType i = (VendorType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getVendorTypeId());
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
        
                    
        public void getRelatedVendorList(VendorType vendor_type)
        {           
            vendor_type.setVendorList(new VendorDaoImpl().findByColumn("VendorTypeId", vendor_type.getVendorTypeId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

