





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemDiscountDaoImpl extends BasicConnection implements Serializable, ItemDiscountDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemDiscount find(Integer id)
        {
            return findByColumn("ItemDiscountId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemDiscount> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemDiscount> item_discount = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_discount");
                while(rs.next())
                {
                    item_discount.add(ItemDiscount.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemDiscount object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discount;
         
        }
        
        @Override
        public ArrayList<ItemDiscount> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemDiscount> item_discountList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_discount", limit, offset);
                while (rs.next())
                {
                    item_discountList.add(ItemDiscount.process(rs));
                }

                
                    for(ItemDiscount item_discount : item_discountList)
                    {
                        
                            getRecordById("Item", item_discount.getItemId().toString());
                            item_discount.setItem(Item.process(rs));               
                        
                            getRecordById("Discount", item_discount.getDiscountId().toString());
                            item_discount.setDiscount(Discount.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemDiscount method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discountList;
        }
        
        @Override
        public ArrayList<ItemDiscount> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemDiscount> item_discount = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_discount", ItemDiscount.checkColumnName(columnName), columnValue, ItemDiscount.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_discount.add(ItemDiscount.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemDiscount's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discount;
        } 
    
        @Override
        public void add(ItemDiscount obj)
        {
            try
            {
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_discount(ItemId,DiscountId,ApplyToOptions) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getApplyToOptions());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer ItemId, Integer DiscountId, Integer ApplyToOptions)
        {   
            int id = 0;
            try
            {
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_discount(ItemId,DiscountId,ApplyToOptions) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, DiscountId);
                preparedStatement.setInt(3, ApplyToOptions);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_discount;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public ItemDiscount update(ItemDiscount obj)
        {
           try
            {   
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_discount SET ItemId=?,DiscountId=?,ApplyToOptions=? WHERE ItemDiscountId=?;");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getApplyToOptions());
                preparedStatement.setInt(4, obj.getItemDiscountId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemDiscountId,Integer ItemId,Integer DiscountId,Integer ApplyToOptions)
        {  
            try
            {   
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_discount SET ItemId=?,DiscountId=?,ApplyToOptions=? WHERE ItemDiscountId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, DiscountId);
                preparedStatement.setInt(3, ApplyToOptions);
                preparedStatement.setInt(4, ItemDiscountId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_discount");
        }
        
        
        @Override
        public ItemDiscount getRelatedInfo(ItemDiscount item_discount)
        {
            
                try
                { 
                    
                        getRecordById("Item", item_discount.getItemId().toString());
                        item_discount.setItem(Item.process(rs));               
                    
                        getRecordById("Discount", item_discount.getDiscountId().toString());
                        item_discount.setDiscount(Discount.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return item_discount;
        }
        
        
        @Override
        public ItemDiscount getRelatedObjects(ItemDiscount item_discount)
        {
                         
            return item_discount;
        }
        
        
        
        @Override
        public void remove(ItemDiscount obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_discount WHERE ItemDiscountId=" + obj.getItemDiscountId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemDiscountById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_discount WHERE ItemDiscountId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemDiscountById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_discount;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_discount WHERE " + ItemDiscount.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

