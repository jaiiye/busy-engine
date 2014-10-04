






















































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
    
    public class FormFieldTypeDaoImpl extends BasicConnection implements Serializable, FormFieldTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public FormFieldTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public FormFieldTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class FormFieldTypeCache
        {
            public static final ConcurrentLruCache<Integer, FormFieldType> formFieldTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (FormFieldType i : findAll())
                {
                    getCache().put(i.getFormFieldTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, FormFieldType> getCache()
        {
            return FormFieldTypeCache.formFieldTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, FormFieldType> buildCache(ArrayList<FormFieldType> formFieldTypeList)
        {        
            ConcurrentLruCache<Integer, FormFieldType> cache = new ConcurrentLruCache<Integer, FormFieldType>(formFieldTypeList.size() + 1000);
            for (FormFieldType i : formFieldTypeList)
            {
                cache.put(i.getFormFieldTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<FormFieldType> findAll()
        {
            ArrayList<FormFieldType> formFieldType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("formFieldType");
                while (rs.next())
                {
                    formFieldType.add(FormFieldType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("FormFieldType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return formFieldType;
        }
        
        @Override
        public FormFieldType find(Integer id)
        {
            return findByColumn("FormFieldTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<FormFieldType> findAll(Integer limit, Integer offset)
        {
            ArrayList<FormFieldType> formFieldTypeList = new ArrayList<FormFieldType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for FormFieldType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    formFieldTypeList = new ArrayList<FormFieldType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("form_field_type", limit, offset);
                    while (rs.next())
                    {
                        formFieldTypeList.add(FormFieldType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("FormFieldType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formFieldTypeList;
         
        }
        
        @Override
        public ArrayList<FormFieldType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<FormFieldType> formFieldTypeList = new ArrayList<FormFieldType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for FormFieldType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    formFieldTypeList = new ArrayList<FormFieldType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                formFieldTypeList = new ArrayList<FormFieldType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("form_field_type", limit, offset);
                    while (rs.next())
                    {
                        formFieldTypeList.add(FormFieldType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object FormFieldType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formFieldTypeList;            
        }
        
        @Override
        public ArrayList<FormFieldType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<FormFieldType> formFieldTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for FormFieldType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            FormFieldType i = (FormFieldType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                formFieldTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            formFieldTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("form_field_type", FormFieldType.checkColumnName(columnName), columnValue, FormFieldType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        formFieldTypeList.add(FormFieldType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("FormFieldType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formFieldTypeList;
        } 
    
        @Override
        public int add(FormFieldType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                FormFieldType.checkColumnSize(obj.getTypeName(), 45);
                FormFieldType.checkColumnSize(obj.getInputType(), 45);
                  

                openConnection();
                prepareStatement("INSERT INTO form_field_type(FormFieldTypeId,TypeName,InputType,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getFormFieldTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getInputType());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setFormFieldTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public FormFieldType update(FormFieldType obj)
        {
           try
            {   
                
                FormFieldType.checkColumnSize(obj.getTypeName(), 45);
                FormFieldType.checkColumnSize(obj.getInputType(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field_type SET com.busy.util.DatabaseColumn@5b338dbc=?,com.busy.util.DatabaseColumn@7b408f30=? WHERE FormFieldTypeId=?;");                    
                preparedStatement.setInt(0, obj.getFormFieldTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getInputType());
                preparedStatement.setInt(3, obj.getFormFieldTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getFormFieldTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("form_field_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(FormFieldType form_field_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(FormFieldType form_field_type)
        {
            form_field_type.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormFieldTypeId", form_field_type.getFormFieldTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(FormFieldType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + obj.getFormFieldTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getFormFieldTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM form_field_type WHERE FormFieldTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM form_field_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM form_field_type WHERE " + FormFieldType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("FormFieldType's removeByColumn method error: " + ex.getMessage());
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
                        FormFieldType i = (FormFieldType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getFormFieldTypeId());
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
        
                    
        public void getRelatedFormFieldList(FormFieldType form_field_type)
        {           
            form_field_type.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormFieldTypeId", form_field_type.getFormFieldTypeId().toString(),null,null));
        }        
        
                             
    }

