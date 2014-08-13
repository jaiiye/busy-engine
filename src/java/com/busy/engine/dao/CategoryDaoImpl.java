





































    package com.busy.engine.dao;

import com.busy.engine.entity.Discount;
import com.busy.engine.entity.Category;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CategoryDaoImpl extends BasicConnection implements Serializable, CategoryDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Category find(Integer id)
        {
            return findByColumn("CategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Category> findAll(Integer limit, Integer offset)
        {
            ArrayList<Category> category = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("category");
                while(rs.next())
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
        public ArrayList<Category> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Category> categoryList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("category", limit, offset);
                while (rs.next())
                {
                    categoryList.add(Category.process(rs));
                }

                
                    for(Category category : categoryList)
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
            return categoryList;
        }
        
        @Override
        public ArrayList<Category> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Category> category = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("category", Category.checkColumnName(columnName), columnValue, Category.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   category.add(Category.process(rs));
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
            return category;
        } 
    
        @Override
        public int add(Category obj)
        {
            int id = 0;
            try
            {
                
                Category.checkColumnSize(obj.getCategoryName(), 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO category(CategoryName,DiscountId,ParentCategoryId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getCategoryName());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getParentCategoryId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Category's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
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
                prepareStatement("UPDATE category SET CategoryName=?,DiscountId=?,ParentCategoryId=? WHERE CategoryId=?;");                    
                preparedStatement.setString(1, obj.getCategoryName());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getParentCategoryId());
                preparedStatement.setInt(4, obj.getCategoryId());
                preparedStatement.executeUpdate();
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
            return getAllRecordsCountByTableName("category");
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
            return success;
        }
        
                    
        public void getRelatedItemCategoryList(Category category)
        {           
            category.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("CategoryId", category.getCategoryId().toString(),null,null));
        }        
        
                             
    }

