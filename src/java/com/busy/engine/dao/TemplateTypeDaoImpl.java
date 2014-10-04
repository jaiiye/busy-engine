






















































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
    
    public class TemplateTypeDaoImpl extends BasicConnection implements Serializable, TemplateTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public TemplateTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public TemplateTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class TemplateTypeCache
        {
            public static final ConcurrentLruCache<Integer, TemplateType> templateTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (TemplateType i : findAll())
                {
                    getCache().put(i.getTemplateTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, TemplateType> getCache()
        {
            return TemplateTypeCache.templateTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, TemplateType> buildCache(ArrayList<TemplateType> templateTypeList)
        {        
            ConcurrentLruCache<Integer, TemplateType> cache = new ConcurrentLruCache<Integer, TemplateType>(templateTypeList.size() + 1000);
            for (TemplateType i : templateTypeList)
            {
                cache.put(i.getTemplateTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<TemplateType> findAll()
        {
            ArrayList<TemplateType> templateType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("templateType");
                while (rs.next())
                {
                    templateType.add(TemplateType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TemplateType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return templateType;
        }
        
        @Override
        public TemplateType find(Integer id)
        {
            return findByColumn("TemplateTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<TemplateType> findAll(Integer limit, Integer offset)
        {
            ArrayList<TemplateType> templateTypeList = new ArrayList<TemplateType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for TemplateType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    templateTypeList = new ArrayList<TemplateType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("template_type", limit, offset);
                    while (rs.next())
                    {
                        templateTypeList.add(TemplateType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TemplateType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return templateTypeList;
         
        }
        
        @Override
        public ArrayList<TemplateType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TemplateType> templateTypeList = new ArrayList<TemplateType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for TemplateType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    templateTypeList = new ArrayList<TemplateType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                templateTypeList = new ArrayList<TemplateType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("template_type", limit, offset);
                    while (rs.next())
                    {
                        templateTypeList.add(TemplateType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object TemplateType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return templateTypeList;            
        }
        
        @Override
        public ArrayList<TemplateType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TemplateType> templateTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for TemplateType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            TemplateType i = (TemplateType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                templateTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            templateTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("template_type", TemplateType.checkColumnName(columnName), columnValue, TemplateType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        templateTypeList.add(TemplateType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TemplateType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return templateTypeList;
        } 
    
        @Override
        public int add(TemplateType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                TemplateType.checkColumnSize(obj.getTypeName(), 45);
                TemplateType.checkColumnSize(obj.getTypeValue(), 45);
                  

                openConnection();
                prepareStatement("INSERT INTO template_type(TemplateTypeId,TypeName,TypeValue,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getTemplateTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setTemplateTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public TemplateType update(TemplateType obj)
        {
           try
            {   
                
                TemplateType.checkColumnSize(obj.getTypeName(), 45);
                TemplateType.checkColumnSize(obj.getTypeValue(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE template_type SET com.busy.util.DatabaseColumn@3243f937=?,com.busy.util.DatabaseColumn@555de8fd=? WHERE TemplateTypeId=?;");                    
                preparedStatement.setInt(0, obj.getTemplateTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                preparedStatement.setInt(3, obj.getTemplateTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getTemplateTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("template_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(TemplateType template_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(TemplateType template_type)
        {
            template_type.setTemplateList(new TemplateDaoImpl().findByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(TemplateType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + obj.getTemplateTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getTemplateTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM template_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template_type WHERE " + TemplateType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TemplateType's removeByColumn method error: " + ex.getMessage());
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
                        TemplateType i = (TemplateType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getTemplateTypeId());
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
        
                    
        public void getRelatedTemplateList(TemplateType template_type)
        {           
            template_type.setTemplateList(new TemplateDaoImpl().findByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString(),null,null));
        }        
        
                             
    }

