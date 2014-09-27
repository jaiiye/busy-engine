






















































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
    
    public class PageTemplateDaoImpl extends BasicConnection implements Serializable, PageTemplateDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public PageTemplateDaoImpl()
        {
            cachingEnabled = false;
        }

        public PageTemplateDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class PageTemplateCache
        {
            public static final ConcurrentLruCache<Integer, PageTemplate> pageTemplateCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (PageTemplate i : findAll())
                {
                    getCache().put(i.getPageTemplateId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, PageTemplate> getCache()
        {
            return PageTemplateCache.pageTemplateCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, PageTemplate> buildCache(ArrayList<PageTemplate> pageTemplateList)
        {        
            ConcurrentLruCache<Integer, PageTemplate> cache = new ConcurrentLruCache<Integer, PageTemplate>(pageTemplateList.size() + 1000);
            for (PageTemplate i : pageTemplateList)
            {
                cache.put(i.getPageTemplateId(), i);
            }
            return cache;
        }

        private static ArrayList<PageTemplate> findAll()
        {
            ArrayList<PageTemplate> pageTemplate = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("pageTemplate");
                while (rs.next())
                {
                    pageTemplate.add(PageTemplate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("PageTemplate object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return pageTemplate;
        }
        
        @Override
        public PageTemplate find(Integer id)
        {
            return findByColumn("PageTemplateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<PageTemplate> findAll(Integer limit, Integer offset)
        {
            ArrayList<PageTemplate> pageTemplateList = new ArrayList<PageTemplate>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for PageTemplate, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    pageTemplateList = new ArrayList<PageTemplate>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("page_template", limit, offset);
                    while (rs.next())
                    {
                        pageTemplateList.add(PageTemplate.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("PageTemplate object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return pageTemplateList;
         
        }
        
        @Override
        public ArrayList<PageTemplate> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<PageTemplate> pageTemplateList = new ArrayList<PageTemplate>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for PageTemplate, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    pageTemplateList = new ArrayList<PageTemplate>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                pageTemplateList = new ArrayList<PageTemplate>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("page_template", limit, offset);
                    while (rs.next())
                    {
                        pageTemplateList.add(PageTemplate.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object PageTemplate method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return pageTemplateList;            
        }
        
        @Override
        public ArrayList<PageTemplate> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<PageTemplate> pageTemplateList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for PageTemplate(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            PageTemplate i = (PageTemplate) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                pageTemplateList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            pageTemplateList = null;
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
                    getRecordsByColumnWithLimitOrOffset("page_template", PageTemplate.checkColumnName(columnName), columnValue, PageTemplate.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        pageTemplateList.add(PageTemplate.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("PageTemplate's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return pageTemplateList;
        } 
    
        @Override
        public int add(PageTemplate obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                PageTemplate.checkColumnSize(obj.getName(), 45);
                PageTemplate.checkColumnSize(obj.getMarkup(), 65535);
                  

                openConnection();
                prepareStatement("INSERT INTO page_template(PageTemplateId,Name,Markup,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getPageTemplateId());
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getMarkup());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from page_template;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setPageTemplateId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public PageTemplate update(PageTemplate obj)
        {
           try
            {   
                
                PageTemplate.checkColumnSize(obj.getName(), 45);
                PageTemplate.checkColumnSize(obj.getMarkup(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE page_template SET com.busy.util.DatabaseColumn@6cae7d61=?,com.busy.util.DatabaseColumn@d0c4c3d=? WHERE PageTemplateId=?;");                    
                preparedStatement.setInt(0, obj.getPageTemplateId());
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getPageTemplateId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getPageTemplateId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("page_template");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(PageTemplate page_template)
        {
              
        }
        
        @Override
        public void getRelatedObjects(PageTemplate page_template)
        {
             
        }
        
        @Override
        public boolean remove(PageTemplate obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM page_template WHERE PageTemplateId=" + obj.getPageTemplateId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getPageTemplateId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM page_template WHERE PageTemplateId=" + id + ";");           
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
                updateQuery("DELETE FROM page_template;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page_template WHERE " + PageTemplate.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("PageTemplate's removeByColumn method error: " + ex.getMessage());
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
                        PageTemplate i = (PageTemplate) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getPageTemplateId());
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
        
        
                             
    }

