





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ItemBrand's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
        } 
    
        @Override
        public void add(ItemBrand obj)
        {
            try
            {
                
                ItemBrand.checkColumnSize(obj.getName(), 100);
                ItemBrand.checkColumnSize(obj.getDescription(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_brand(Name,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Name, String Description)
        {   
            int id = 0;
            try
            {
                
                ItemBrand.checkColumnSize(Name, 100);
                ItemBrand.checkColumnSize(Description, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_brand(Name,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Description);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_brand;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemBrand error: " + ex.getMessage());
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
                
                ItemBrand.checkColumnSize(obj.getName(), 100);
                ItemBrand.checkColumnSize(obj.getDescription(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_brand SET Name=?,Description=? WHERE ItemBrandId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemBrandId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemBrandId,String Name,String Description)
        {  
            try
            {   
                
                ItemBrand.checkColumnSize(Name, 100);
                ItemBrand.checkColumnSize(Description, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_brand SET Name=?,Description=? WHERE ItemBrandId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, ItemBrandId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_brand");
        }
        
        
        @Override
        public ItemBrand getRelatedInfo(ItemBrand item_brand)
        {
              
            
            return item_brand;
        }
        
        
        @Override
        public ItemBrand getRelatedObjects(ItemBrand item_brand)
        {
            item_brand.setItemList(new ItemDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
item_brand.setUserList(new UserDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
             
            return item_brand;
        }
        
        
        
        @Override
        public void remove(ItemBrand obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + obj.getItemBrandId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemBrandById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemBrandById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM item_brand;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_brand WHERE " + ItemBrand.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ItemBrand getRelatedItemList(ItemBrand item_brand)
        {           
            item_brand.setItemList(new ItemDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
            return item_brand;
        }        
                    
        public ItemBrand getRelatedUserList(ItemBrand item_brand)
        {           
            item_brand.setUserList(new UserDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
            return item_brand;
        }        
        
                             
    }

