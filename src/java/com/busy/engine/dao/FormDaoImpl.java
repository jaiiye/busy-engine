






















































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
    
    public class FormDaoImpl extends BasicConnection implements Serializable, FormDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public FormDaoImpl()
        {
            cachingEnabled = false;
        }

        public FormDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class FormCache
        {
            public static final ConcurrentLruCache<Integer, Form> formCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Form i : findAll())
                {
                    getCache().put(i.getFormId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Form> getCache()
        {
            return FormCache.formCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Form> buildCache(ArrayList<Form> formList)
        {        
            ConcurrentLruCache<Integer, Form> cache = new ConcurrentLruCache<Integer, Form>(formList.size() + 1000);
            for (Form i : formList)
            {
                cache.put(i.getFormId(), i);
            }
            return cache;
        }

        private static ArrayList<Form> findAll()
        {
            ArrayList<Form> form = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("form");
                while (rs.next())
                {
                    form.add(Form.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Form object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        }
        
        @Override
        public Form find(Integer id)
        {
            return findByColumn("FormId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Form> findAll(Integer limit, Integer offset)
        {
            ArrayList<Form> formList = new ArrayList<Form>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Form, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    formList = new ArrayList<Form>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("form", limit, offset);
                    while (rs.next())
                    {
                        formList.add(Form.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Form object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formList;
         
        }
        
        @Override
        public ArrayList<Form> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Form> formList = new ArrayList<Form>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Form, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    formList = new ArrayList<Form>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                formList = new ArrayList<Form>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("form", limit, offset);
                    while (rs.next())
                    {
                        formList.add(Form.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Form method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formList;            
        }
        
        @Override
        public ArrayList<Form> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Form> formList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Form(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Form i = (Form) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                formList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            formList = null;
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
                    getRecordsByColumnWithLimitOrOffset("form", Form.checkColumnName(columnName), columnValue, Form.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        formList.add(Form.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Form's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formList;
        } 
    
        @Override
        public int add(Form obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Form.checkColumnSize(obj.getFormName(), 100);
                Form.checkColumnSize(obj.getDescription(), 255);
                Form.checkColumnSize(obj.getSubmissionEmail(), 255);
                Form.checkColumnSize(obj.getSubmissionMethod(), 5);
                Form.checkColumnSize(obj.getAction(), 255);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO form(FormId,FormName,Description,SubmissionEmail,SubmissionMethod,Action,Resettable,FileUpload,) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getFormId());
                preparedStatement.setString(1, obj.getFormName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getSubmissionEmail());
                preparedStatement.setString(4, obj.getSubmissionMethod());
                preparedStatement.setString(5, obj.getAction());
                preparedStatement.setInt(6, obj.getResettable());
                preparedStatement.setInt(7, obj.getFileUpload());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Form's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setFormId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Form update(Form obj)
        {
           try
            {   
                
                Form.checkColumnSize(obj.getFormName(), 100);
                Form.checkColumnSize(obj.getDescription(), 255);
                Form.checkColumnSize(obj.getSubmissionEmail(), 255);
                Form.checkColumnSize(obj.getSubmissionMethod(), 5);
                Form.checkColumnSize(obj.getAction(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form SET com.busy.util.DatabaseColumn@696fa525=?,com.busy.util.DatabaseColumn@52757ab4=?,com.busy.util.DatabaseColumn@43d0556d=?,com.busy.util.DatabaseColumn@10e70f93=?,com.busy.util.DatabaseColumn@435c627=?,com.busy.util.DatabaseColumn@7decaccf=?,com.busy.util.DatabaseColumn@3c56f522=? WHERE FormId=?;");                    
                preparedStatement.setInt(0, obj.getFormId());
                preparedStatement.setString(1, obj.getFormName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getSubmissionEmail());
                preparedStatement.setString(4, obj.getSubmissionMethod());
                preparedStatement.setString(5, obj.getAction());
                preparedStatement.setInt(6, obj.getResettable());
                preparedStatement.setInt(7, obj.getFileUpload());
                preparedStatement.setInt(8, obj.getFormId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getFormId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Form's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("form");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Form form)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Form form)
        {
            form.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
form.setPageList(new PageDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
form.setSliderList(new SliderDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Form obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + obj.getFormId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Form's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getFormId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + id + ";");           
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
                updateQuery("DELETE FROM form;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Form's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM form WHERE " + Form.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Form's removeByColumn method error: " + ex.getMessage());
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
                        Form i = (Form) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getFormId());
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
        
                    
        public void getRelatedFormFieldList(Form form)
        {           
            form.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
        }        
                    
        public void getRelatedPageList(Form form)
        {           
            form.setPageList(new PageDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
        }        
                    
        public void getRelatedSliderList(Form form)
        {           
            form.setSliderList(new SliderDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
        }        
        
                             
    }

