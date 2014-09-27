






















































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
    
    public class PageDaoImpl extends BasicConnection implements Serializable, PageDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public PageDaoImpl()
        {
            cachingEnabled = false;
        }

        public PageDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class PageCache
        {
            public static final ConcurrentLruCache<Integer, Page> pageCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Page i : findAll())
                {
                    getCache().put(i.getPageId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Page> getCache()
        {
            return PageCache.pageCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Page> buildCache(ArrayList<Page> pageList)
        {        
            ConcurrentLruCache<Integer, Page> cache = new ConcurrentLruCache<Integer, Page>(pageList.size() + 1000);
            for (Page i : pageList)
            {
                cache.put(i.getPageId(), i);
            }
            return cache;
        }

        private static ArrayList<Page> findAll()
        {
            ArrayList<Page> page = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("page");
                while (rs.next())
                {
                    page.add(Page.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Page object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
        }
        
        @Override
        public Page find(Integer id)
        {
            return findByColumn("PageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Page> findAll(Integer limit, Integer offset)
        {
            ArrayList<Page> pageList = new ArrayList<Page>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Page, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    pageList = new ArrayList<Page>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("page", limit, offset);
                    while (rs.next())
                    {
                        pageList.add(Page.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Page object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return pageList;
         
        }
        
        @Override
        public ArrayList<Page> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Page> pageList = new ArrayList<Page>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Page, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    pageList = new ArrayList<Page>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Page page = (Page) e.getValue();

                            
                                getRecordById("Form", page.getFormId().toString());
                                page.setForm(Form.process(rs));               
                            
                                getRecordById("Slider", page.getSliderId().toString());
                                page.setSlider(Slider.process(rs));               
                            
                                getRecordById("MetaTag", page.getMetaTagId().toString());
                                page.setMetaTag(MetaTag.process(rs));               
                            
                                getRecordById("Template", page.getTemplateId().toString());
                                page.setTemplate(Template.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Page method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                pageList = new ArrayList<Page>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("page", limit, offset);
                    while (rs.next())
                    {
                        pageList.add(Page.process(rs));
                    }

                    
                    
                        for (Page page : pageList)
                        {                        
                            
                                getRecordById("Form", page.getFormId().toString());
                                page.setForm(Form.process(rs));               
                            
                                getRecordById("Slider", page.getSliderId().toString());
                                page.setSlider(Slider.process(rs));               
                            
                                getRecordById("MetaTag", page.getMetaTagId().toString());
                                page.setMetaTag(MetaTag.process(rs));               
                            
                                getRecordById("Template", page.getTemplateId().toString());
                                page.setTemplate(Template.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Page method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return pageList;            
        }
        
        @Override
        public ArrayList<Page> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Page> pageList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Page(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Page i = (Page) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                pageList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            pageList = null;
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
                    getRecordsByColumnWithLimitOrOffset("page", Page.checkColumnName(columnName), columnValue, Page.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        pageList.add(Page.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Page's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return pageList;
        } 
    
        @Override
        public int add(Page obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Page.checkColumnSize(obj.getPageName(), 150);
                Page.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO page(PageId,PageName,Content,PageStatus,FormId,SliderId,MetaTagId,TemplateId,) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getPageId());
                preparedStatement.setString(1, obj.getPageName());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setInt(3, obj.getPageStatus());
                preparedStatement.setInt(4, obj.getFormId());
                preparedStatement.setInt(5, obj.getSliderId());
                preparedStatement.setInt(6, obj.getMetaTagId());
                preparedStatement.setInt(7, obj.getTemplateId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from page;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Page's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setPageId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Page update(Page obj)
        {
           try
            {   
                
                Page.checkColumnSize(obj.getPageName(), 150);
                Page.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE page SET com.busy.util.DatabaseColumn@69d0ccef=?,com.busy.util.DatabaseColumn@3e891d70=?,com.busy.util.DatabaseColumn@6ce640b6=?,com.busy.util.DatabaseColumn@18e9175f=?,com.busy.util.DatabaseColumn@77b10719=?,com.busy.util.DatabaseColumn@125f602=?,com.busy.util.DatabaseColumn@5f18f560=? WHERE PageId=?;");                    
                preparedStatement.setInt(0, obj.getPageId());
                preparedStatement.setString(1, obj.getPageName());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setInt(3, obj.getPageStatus());
                preparedStatement.setInt(4, obj.getFormId());
                preparedStatement.setInt(5, obj.getSliderId());
                preparedStatement.setInt(6, obj.getMetaTagId());
                preparedStatement.setInt(7, obj.getTemplateId());
                preparedStatement.setInt(8, obj.getPageId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getPageId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Page's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("page");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Page page)
        {
            
                try
                { 
                    
                            getRecordById("Form", page.getFormId().toString());
                            page.setForm(Form.process(rs));                                       
                    
                            getRecordById("Slider", page.getSliderId().toString());
                            page.setSlider(Slider.process(rs));                                       
                    
                            getRecordById("MetaTag", page.getMetaTagId().toString());
                            page.setMetaTag(MetaTag.process(rs));                                       
                    
                            getRecordById("Template", page.getTemplateId().toString());
                            page.setTemplate(Template.process(rs));                                       
                    
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
        public void getRelatedObjects(Page page)
        {
            page.setSitePageList(new SitePageDaoImpl().findByColumn("PageId", page.getPageId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Page obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM page WHERE PageId=" + obj.getPageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Page's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getPageId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM page WHERE PageId=" + id + ";");           
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
                updateQuery("DELETE FROM page;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Page's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM page WHERE " + Page.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Page's removeByColumn method error: " + ex.getMessage());
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
                        Page i = (Page) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getPageId());
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
        
                    
        public void getRelatedSitePageList(Page page)
        {           
            page.setSitePageList(new SitePageDaoImpl().findByColumn("PageId", page.getPageId().toString(),null,null));
        }        
        
                             
    }

