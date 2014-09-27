






















































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
    
    public class VendorDaoImpl extends BasicConnection implements Serializable, VendorDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public VendorDaoImpl()
        {
            cachingEnabled = false;
        }

        public VendorDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class VendorCache
        {
            public static final ConcurrentLruCache<Integer, Vendor> vendorCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Vendor i : findAll())
                {
                    getCache().put(i.getVendorId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Vendor> getCache()
        {
            return VendorCache.vendorCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Vendor> buildCache(ArrayList<Vendor> vendorList)
        {        
            ConcurrentLruCache<Integer, Vendor> cache = new ConcurrentLruCache<Integer, Vendor>(vendorList.size() + 1000);
            for (Vendor i : vendorList)
            {
                cache.put(i.getVendorId(), i);
            }
            return cache;
        }

        private static ArrayList<Vendor> findAll()
        {
            ArrayList<Vendor> vendor = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("vendor");
                while (rs.next())
                {
                    vendor.add(Vendor.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Vendor object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        }
        
        @Override
        public Vendor find(Integer id)
        {
            return findByColumn("VendorId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Vendor> findAll(Integer limit, Integer offset)
        {
            ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Vendor, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    vendorList = new ArrayList<Vendor>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("vendor", limit, offset);
                    while (rs.next())
                    {
                        vendorList.add(Vendor.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Vendor object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return vendorList;
         
        }
        
        @Override
        public ArrayList<Vendor> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Vendor, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    vendorList = new ArrayList<Vendor>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Vendor vendor = (Vendor) e.getValue();

                            
                                getRecordById("MetaTag", vendor.getMetaTagId().toString());
                                vendor.setMetaTag(MetaTag.process(rs));               
                            
                                getRecordById("Template", vendor.getTemplateId().toString());
                                vendor.setTemplate(Template.process(rs));               
                            
                                getRecordById("VendorType", vendor.getVendorTypeId().toString());
                                vendor.setVendorType(VendorType.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Vendor method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                vendorList = new ArrayList<Vendor>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("vendor", limit, offset);
                    while (rs.next())
                    {
                        vendorList.add(Vendor.process(rs));
                    }

                    
                    
                        for (Vendor vendor : vendorList)
                        {                        
                            
                                getRecordById("MetaTag", vendor.getMetaTagId().toString());
                                vendor.setMetaTag(MetaTag.process(rs));               
                            
                                getRecordById("Template", vendor.getTemplateId().toString());
                                vendor.setTemplate(Template.process(rs));               
                            
                                getRecordById("VendorType", vendor.getVendorTypeId().toString());
                                vendor.setVendorType(VendorType.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Vendor method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return vendorList;            
        }
        
        @Override
        public ArrayList<Vendor> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Vendor> vendorList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Vendor(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Vendor i = (Vendor) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                vendorList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            vendorList = null;
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
                    getRecordsByColumnWithLimitOrOffset("vendor", Vendor.checkColumnName(columnName), columnValue, Vendor.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        vendorList.add(Vendor.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Vendor's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return vendorList;
        } 
    
        @Override
        public int add(Vendor obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Vendor.checkColumnSize(obj.getVendorName(), 100);
                Vendor.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO vendor(VendorId,VendorName,Description,Rank,VendorStatus,MetaTagId,TemplateId,VendorTypeId,) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getVendorId());
                preparedStatement.setString(1, obj.getVendorName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getVendorStatus());
                preparedStatement.setInt(5, obj.getMetaTagId());
                preparedStatement.setInt(6, obj.getTemplateId());
                preparedStatement.setInt(7, obj.getVendorTypeId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from vendor;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setVendorId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Vendor update(Vendor obj)
        {
           try
            {   
                
                Vendor.checkColumnSize(obj.getVendorName(), 100);
                Vendor.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE vendor SET com.busy.util.DatabaseColumn@2d0b38e6=?,com.busy.util.DatabaseColumn@8adf061=?,com.busy.util.DatabaseColumn@5abd6e94=?,com.busy.util.DatabaseColumn@52bbe3d5=?,com.busy.util.DatabaseColumn@26832d6c=?,com.busy.util.DatabaseColumn@414aef25=?,com.busy.util.DatabaseColumn@70b2e056=? WHERE VendorId=?;");                    
                preparedStatement.setInt(0, obj.getVendorId());
                preparedStatement.setString(1, obj.getVendorName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getVendorStatus());
                preparedStatement.setInt(5, obj.getMetaTagId());
                preparedStatement.setInt(6, obj.getTemplateId());
                preparedStatement.setInt(7, obj.getVendorTypeId());
                preparedStatement.setInt(8, obj.getVendorId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getVendorId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("vendor");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Vendor vendor)
        {
            
                try
                { 
                    
                            getRecordById("MetaTag", vendor.getMetaTagId().toString());
                            vendor.setMetaTag(MetaTag.process(rs));                                       
                    
                            getRecordById("Template", vendor.getTemplateId().toString());
                            vendor.setTemplate(Template.process(rs));                                       
                    
                            getRecordById("VendorType", vendor.getVendorTypeId().toString());
                            vendor.setVendorType(VendorType.process(rs));                                       
                    
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
        public void getRelatedObjects(Vendor vendor)
        {
            vendor.setItemList(new ItemDaoImpl().findByColumn("VendorId", vendor.getVendorId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Vendor obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + obj.getVendorId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getVendorId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + id + ";");           
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
                updateQuery("DELETE FROM vendor;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM vendor WHERE " + Vendor.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's removeByColumn method error: " + ex.getMessage());
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
                        Vendor i = (Vendor) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getVendorId());
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
        
                    
        public void getRelatedItemList(Vendor vendor)
        {           
            vendor.setItemList(new ItemDaoImpl().findByColumn("VendorId", vendor.getVendorId().toString(),null,null));
        }        
        
                             
    }

