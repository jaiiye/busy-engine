





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemBrand;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemBrandDaoImpl extends BasicConnection implements Serializable, ItemBrandDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemBrand find(Integer id)
        {
            return findByColumn("ItemBrandId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemBrand> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemBrand> item_brand = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_brand");
                while(rs.next())
                {
                    item_brand.add(ItemBrand.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemBrand object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
         
        }
        
        @Override
        public ArrayList<ItemBrand> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemBrand> item_brandList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_brand", limit, offset);
                while (rs.next())
                {
                    item_brandList.add(ItemBrand.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemBrand method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brandList;
        }
        
        @Override
        public ArrayList<ItemBrand> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemBrand> item_brand = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_brand", ItemBrand.checkColumnName(columnName), columnValue, ItemBrand.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_brand.add(ItemBrand.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemBrand's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
        } 
    
        @Override
        public int add(ItemBrand obj)
        {
            int id = 0;
            try
            {
                
                ItemBrand.checkColumnSize(obj.getBrandName(), 100);
                ItemBrand.checkColumnSize(obj.getDescription(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_brand(BrandName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getBrandName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_brand;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemBrand update(ItemBrand obj)
        {
           try
            {   
                
                ItemBrand.checkColumnSize(obj.getBrandName(), 100);
                ItemBrand.checkColumnSize(obj.getDescription(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_brand SET BrandName=?,Description=? WHERE ItemBrandId=?;");                    
                preparedStatement.setString(1, obj.getBrandName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemBrandId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_brand");
        }
        
        
        @Override
        public void getRelatedInfo(ItemBrand item_brand)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemBrand item_brand)
        {
            item_brand.setItemList(new ItemDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
            item_brand.setUserList(new UserDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemBrand obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + obj.getItemBrandId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + id + ";");           
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
                updateQuery("DELETE FROM item_brand;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_brand WHERE " + ItemBrand.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedItemList(ItemBrand item_brand)
        {           
            item_brand.setItemList(new ItemDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
        }        
                    
        public void getRelatedUserList(ItemBrand item_brand)
        {           
            item_brand.setUserList(new UserDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
        }        
        
                             
    }

