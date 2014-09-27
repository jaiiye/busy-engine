






















































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
    
    public class CategoryDaoImpl extends BasicConnection implements Serializable, CategoryDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public CategoryDaoImpl()
        {
            cachingEnabled = false;
        }

        public CategoryDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class CategoryCache
        {
            public static final ConcurrentLruCache<Integer, Category> categoryCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Category i : findAll())
                {
                    getCache().put(i.getCategoryId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Category> getCache()
        {
            return CategoryCache.categoryCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Category> buildCache(ArrayList<Category> categoryList)
        {        
            ConcurrentLruCache<Integer, Category> cache = new ConcurrentLruCache<Integer, Category>(categoryList.size() + 1000);
            for (Category i : categoryList)
            {
                cache.put(i.getCategoryId(), i);
            }
            return cache;
        }

        private static ArrayList<Category> findAll()
        {
            ArrayList<Category> category = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("category");
                while (rs.next())
                {
                    category.add(Category.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Category object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return category;
        }
        
        @Override
        public Category find(Integer id)
        {
            return findByColumn("CategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Category> findAll(Integer limit, Integer offset)
        {
            ArrayList<Category> categoryList = new ArrayList<Category>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Category, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    categoryList = new ArrayList<Category>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("category", limit, offset);
                    while (rs.next())
                    {
                        categoryList.add(Category.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Category object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return categoryList;
         
        }
        
        @Override
        public ArrayList<Category> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Category> categoryList = new ArrayList<Category>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Category, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    categoryList = new ArrayList<Category>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Category category = (Category) e.getValue();

                            
                                getRecordById("Discount", category.getDiscountId().toString());
                                category.setDiscount(Discount.process(rs));               
                            
                                getRecordById("Category", category.getParentCategoryId().toString());
                                category.setParentCategory(Category.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Category method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                categoryList = new ArrayList<Category>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("category", limit, offset);
                    while (rs.next())
                    {
                        categoryList.add(Category.process(rs));
                    }

                    
                    
                        for (Category category : categoryList)
                        {                        
                            
                                getRecordById("Discount", category.getDiscountId().toString());
                                category.setDiscount(Discount.process(rs));               
                            
                                getRecordById("Category", category.getParentCategoryId().toString());
                                category.setParentCategory(Category.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Category method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return categoryList;            
        }
        
        @Override
        public ArrayList<Category> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Category> categoryList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Category(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Category i = (Category) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                categoryList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            categoryList = null;
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
                    getRecordsByColumnWithLimitOrOffset("category", Category.checkColumnName(columnName), columnValue, Category.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        categoryList.add(Category.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Category's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return categoryList;
        } 
    
        @Override
        public int add(Category obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Category.checkColumnSize(obj.getCategoryName(), 100);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO category(CategoryId,CategoryName,DiscountId,ParentCategoryId,) VALUES (?,?,?);");                    
                preparedStatement.setInt(0, obj.getCategoryId());
                preparedStatement.setString(1, obj.getCategoryName());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getParentCategoryId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from category;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Category's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setCategoryId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Category update(Category obj)
        {
           try
            {   
                
                Category.checkColumnSize(obj.getCategoryName(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE category SET com.busy.util.DatabaseColumn@22384566=?,com.busy.util.DatabaseColumn@5253c34f=?,com.busy.util.DatabaseColumn@29f5a26d=? WHERE CategoryId=?;");                    
                preparedStatement.setInt(0, obj.getCategoryId());
                preparedStatement.setString(1, obj.getCategoryName());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getParentCategoryId());
                preparedStatement.setInt(4, obj.getCategoryId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getCategoryId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Category's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("category");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Category category)
        {
            
                try
                { 
                    
                            getRecordById("Discount", category.getDiscountId().toString());
                            category.setDiscount(Discount.process(rs));                                       
                    
                            getRecordById("Category", category.getParentCategoryId().toString());
                            category.setParentCategory(Category.process(rs));                                       
                    
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
        public void getRelatedObjects(Category category)
        {
            category.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("CategoryId", category.getCategoryId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Category obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM category WHERE CategoryId=" + obj.getCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Category's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getCategoryId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM category WHERE CategoryId=" + id + ";");           
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
                updateQuery("DELETE FROM category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Category's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM category WHERE " + Category.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Category's removeByColumn method error: " + ex.getMessage());
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
                        Category i = (Category) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getCategoryId());
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
        
                    
        public void getRelatedItemCategoryList(Category category)
        {           
            category.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("CategoryId", category.getCategoryId().toString(),null,null));
        }        
        
                             
    }

