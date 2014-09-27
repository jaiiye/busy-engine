






















































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
    
    public class TemplateDaoImpl extends BasicConnection implements Serializable, TemplateDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public TemplateDaoImpl()
        {
            cachingEnabled = false;
        }

        public TemplateDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class TemplateCache
        {
            public static final ConcurrentLruCache<Integer, Template> templateCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Template i : findAll())
                {
                    getCache().put(i.getTemplateId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Template> getCache()
        {
            return TemplateCache.templateCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Template> buildCache(ArrayList<Template> templateList)
        {        
            ConcurrentLruCache<Integer, Template> cache = new ConcurrentLruCache<Integer, Template>(templateList.size() + 1000);
            for (Template i : templateList)
            {
                cache.put(i.getTemplateId(), i);
            }
            return cache;
        }

        private static ArrayList<Template> findAll()
        {
            ArrayList<Template> template = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("template");
                while (rs.next())
                {
                    template.add(Template.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Template object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        }
        
        @Override
        public Template find(Integer id)
        {
            return findByColumn("TemplateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Template> findAll(Integer limit, Integer offset)
        {
            ArrayList<Template> templateList = new ArrayList<Template>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Template, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    templateList = new ArrayList<Template>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("template", limit, offset);
                    while (rs.next())
                    {
                        templateList.add(Template.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Template object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return templateList;
         
        }
        
        @Override
        public ArrayList<Template> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Template> templateList = new ArrayList<Template>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Template, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    templateList = new ArrayList<Template>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Template template = (Template) e.getValue();

                            
                                getRecordById("TemplateType", template.getTemplateTypeId().toString());
                                template.setTemplateType(TemplateType.process(rs));               
                            
                                getRecordById("Template", template.getParentTemplateId().toString());
                                template.setParentTemplate(Template.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Template method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                templateList = new ArrayList<Template>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("template", limit, offset);
                    while (rs.next())
                    {
                        templateList.add(Template.process(rs));
                    }

                    
                    
                        for (Template template : templateList)
                        {                        
                            
                                getRecordById("TemplateType", template.getTemplateTypeId().toString());
                                template.setTemplateType(TemplateType.process(rs));               
                            
                                getRecordById("Template", template.getParentTemplateId().toString());
                                template.setParentTemplate(Template.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Template method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return templateList;            
        }
        
        @Override
        public ArrayList<Template> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Template> templateList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Template(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Template i = (Template) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                templateList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            templateList = null;
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
                    getRecordsByColumnWithLimitOrOffset("template", Template.checkColumnName(columnName), columnValue, Template.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        templateList.add(Template.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Template's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return templateList;
        } 
    
        @Override
        public int add(Template obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Template.checkColumnSize(obj.getTemplateName(), 100);
                Template.checkColumnSize(obj.getMarkup(), 65535);
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO template(TemplateId,TemplateName,Markup,TemplateStatus,TemplateTypeId,ParentTemplateId,) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getTemplateId());
                preparedStatement.setString(1, obj.getTemplateName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getTemplateStatus());
                preparedStatement.setInt(4, obj.getTemplateTypeId());
                preparedStatement.setInt(5, obj.getParentTemplateId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Template's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setTemplateId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Template update(Template obj)
        {
           try
            {   
                
                Template.checkColumnSize(obj.getTemplateName(), 100);
                Template.checkColumnSize(obj.getMarkup(), 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE template SET com.busy.util.DatabaseColumn@11bad3ea=?,com.busy.util.DatabaseColumn@7dc3fba9=?,com.busy.util.DatabaseColumn@14a85b74=?,com.busy.util.DatabaseColumn@6a5d2710=?,com.busy.util.DatabaseColumn@2d3f9c32=? WHERE TemplateId=?;");                    
                preparedStatement.setInt(0, obj.getTemplateId());
                preparedStatement.setString(1, obj.getTemplateName());
                preparedStatement.setString(2, obj.getMarkup());
                preparedStatement.setInt(3, obj.getTemplateStatus());
                preparedStatement.setInt(4, obj.getTemplateTypeId());
                preparedStatement.setInt(5, obj.getParentTemplateId());
                preparedStatement.setInt(6, obj.getTemplateId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getTemplateId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Template's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("template");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Template template)
        {
            
                try
                { 
                    
                            getRecordById("TemplateType", template.getTemplateTypeId().toString());
                            template.setTemplateType(TemplateType.process(rs));                                       
                    
                            getRecordById("Template", template.getParentTemplateId().toString());
                            template.setParentTemplate(Template.process(rs));                                       
                    
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
        public void getRelatedObjects(Template template)
        {
            template.setItemList(new ItemDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setPageList(new PageDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setSiteList(new SiteDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
template.setVendorList(new VendorDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Template obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM template WHERE TemplateId=" + obj.getTemplateId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Template's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getTemplateId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM template WHERE TemplateId=" + id + ";");           
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
                updateQuery("DELETE FROM template;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Template's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM template WHERE " + Template.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Template's removeByColumn method error: " + ex.getMessage());
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
                        Template i = (Template) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getTemplateId());
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
        
                    
        public void getRelatedItemList(Template template)
        {           
            template.setItemList(new ItemDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedPageList(Template template)
        {           
            template.setPageList(new PageDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedResourceUrlList(Template template)
        {           
            template.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedSiteList(Template template)
        {           
            template.setSiteList(new SiteDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
                    
        public void getRelatedVendorList(Template template)
        {           
            template.setVendorList(new VendorDaoImpl().findByColumn("TemplateId", template.getTemplateId().toString(),null,null));
        }        
        
                             
    }

