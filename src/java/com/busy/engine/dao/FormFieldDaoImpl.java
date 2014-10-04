






















































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
    
    public class FormFieldDaoImpl extends BasicConnection implements Serializable, FormFieldDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public FormFieldDaoImpl()
        {
            cachingEnabled = false;
        }

        public FormFieldDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class FormFieldCache
        {
            public static final ConcurrentLruCache<Integer, FormField> formFieldCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (FormField i : findAll())
                {
                    getCache().put(i.getFormFieldId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, FormField> getCache()
        {
            return FormFieldCache.formFieldCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, FormField> buildCache(ArrayList<FormField> formFieldList)
        {        
            ConcurrentLruCache<Integer, FormField> cache = new ConcurrentLruCache<Integer, FormField>(formFieldList.size() + 1000);
            for (FormField i : formFieldList)
            {
                cache.put(i.getFormFieldId(), i);
            }
            return cache;
        }

        private static ArrayList<FormField> findAll()
        {
            ArrayList<FormField> formField = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("formField");
                while (rs.next())
                {
                    formField.add(FormField.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("FormField object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return formField;
        }
        
        @Override
        public FormField find(Integer id)
        {
            return findByColumn("FormFieldId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<FormField> findAll(Integer limit, Integer offset)
        {
            ArrayList<FormField> formFieldList = new ArrayList<FormField>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for FormField, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    formFieldList = new ArrayList<FormField>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("form_field", limit, offset);
                    while (rs.next())
                    {
                        formFieldList.add(FormField.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("FormField object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formFieldList;
         
        }
        
        @Override
        public ArrayList<FormField> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<FormField> formFieldList = new ArrayList<FormField>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for FormField, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    formFieldList = new ArrayList<FormField>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            FormField formField = (FormField) e.getValue();

                            
                                getRecordById("FormFieldType", formField.getFormFieldTypeId().toString());
                                formField.setFormFieldType(FormFieldType.process(rs));               
                            
                                getRecordById("Form", formField.getFormId().toString());
                                formField.setForm(Form.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object FormField method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                formFieldList = new ArrayList<FormField>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("form_field", limit, offset);
                    while (rs.next())
                    {
                        formFieldList.add(FormField.process(rs));
                    }

                    
                    
                        for (FormField formField : formFieldList)
                        {                        
                            
                                getRecordById("FormFieldType", formField.getFormFieldTypeId().toString());
                                formField.setFormFieldType(FormFieldType.process(rs));               
                            
                                getRecordById("Form", formField.getFormId().toString());
                                formField.setForm(Form.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object FormField method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formFieldList;            
        }
        
        @Override
        public ArrayList<FormField> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<FormField> formFieldList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for FormField(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            FormField i = (FormField) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                formFieldList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            formFieldList = null;
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
                    getRecordsByColumnWithLimitOrOffset("form_field", FormField.checkColumnName(columnName), columnValue, FormField.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        formFieldList.add(FormField.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("FormField's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return formFieldList;
        } 
    
        @Override
        public int add(FormField obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                FormField.checkColumnSize(obj.getFieldName(), 255);
                FormField.checkColumnSize(obj.getLabel(), 255);
                FormField.checkColumnSize(obj.getErrorText(), 255);
                FormField.checkColumnSize(obj.getValidationRegex(), 255);
                
                FormField.checkColumnSize(obj.getDefaultValue(), 255);
                FormField.checkColumnSize(obj.getOptions(), 65535);
                FormField.checkColumnSize(obj.getGroupName(), 255);
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO form_field(FormFieldId,FieldName,Label,ErrorText,ValidationRegex,Rank,DefaultValue,Options,GroupName,Optional,FormFieldTypeId,FormId,) VALUES (?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getFormFieldId());
                preparedStatement.setString(1, obj.getFieldName());
                preparedStatement.setString(2, obj.getLabel());
                preparedStatement.setString(3, obj.getErrorText());
                preparedStatement.setString(4, obj.getValidationRegex());
                preparedStatement.setInt(5, obj.getRank());
                preparedStatement.setString(6, obj.getDefaultValue());
                preparedStatement.setString(7, obj.getOptions());
                preparedStatement.setString(8, obj.getGroupName());
                preparedStatement.setInt(9, obj.getOptional());
                preparedStatement.setInt(10, obj.getFormFieldTypeId());
                preparedStatement.setInt(11, obj.getFormId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form_field;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormField's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setFormFieldId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public FormField update(FormField obj)
        {
           try
            {   
                
                FormField.checkColumnSize(obj.getFieldName(), 255);
                FormField.checkColumnSize(obj.getLabel(), 255);
                FormField.checkColumnSize(obj.getErrorText(), 255);
                FormField.checkColumnSize(obj.getValidationRegex(), 255);
                
                FormField.checkColumnSize(obj.getDefaultValue(), 255);
                FormField.checkColumnSize(obj.getOptions(), 65535);
                FormField.checkColumnSize(obj.getGroupName(), 255);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form_field SET com.busy.util.DatabaseColumn@1406351e=?,com.busy.util.DatabaseColumn@119480b4=?,com.busy.util.DatabaseColumn@404f056=?,com.busy.util.DatabaseColumn@134ffcba=?,com.busy.util.DatabaseColumn@36449508=?,com.busy.util.DatabaseColumn@3e03254=?,com.busy.util.DatabaseColumn@20c5e43e=?,com.busy.util.DatabaseColumn@4c0366b0=?,com.busy.util.DatabaseColumn@5e7d56ed=?,com.busy.util.DatabaseColumn@5c8dade4=?,com.busy.util.DatabaseColumn@5366565f=? WHERE FormFieldId=?;");                    
                preparedStatement.setInt(0, obj.getFormFieldId());
                preparedStatement.setString(1, obj.getFieldName());
                preparedStatement.setString(2, obj.getLabel());
                preparedStatement.setString(3, obj.getErrorText());
                preparedStatement.setString(4, obj.getValidationRegex());
                preparedStatement.setInt(5, obj.getRank());
                preparedStatement.setString(6, obj.getDefaultValue());
                preparedStatement.setString(7, obj.getOptions());
                preparedStatement.setString(8, obj.getGroupName());
                preparedStatement.setInt(9, obj.getOptional());
                preparedStatement.setInt(10, obj.getFormFieldTypeId());
                preparedStatement.setInt(11, obj.getFormId());
                preparedStatement.setInt(12, obj.getFormFieldId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getFormFieldId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("FormField's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("form_field");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(FormField form_field)
        {
            
                try
                { 
                    
                            getRecordById("FormFieldType", form_field.getFormFieldTypeId().toString());
                            form_field.setFormFieldType(FormFieldType.process(rs));                                       
                    
                            getRecordById("Form", form_field.getFormId().toString());
                            form_field.setForm(Form.process(rs));                                       
                    
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
        public void getRelatedObjects(FormField form_field)
        {
             
        }
        
        @Override
        public boolean remove(FormField obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form_field WHERE FormFieldId=" + obj.getFormFieldId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormField's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getFormFieldId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM form_field WHERE FormFieldId=" + id + ";");           
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
                updateQuery("DELETE FROM form_field;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("FormField's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM form_field WHERE " + FormField.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("FormField's removeByColumn method error: " + ex.getMessage());
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
                        FormField i = (FormField) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getFormFieldId());
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

