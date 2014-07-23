





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemDiscountDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemDiscount.PROP_ITEM_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_ITEM_ID) || column.equals(ItemDiscount.PROP_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_APPLY_TO_OPTIONS) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(ItemDiscount.PROP_ITEM_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_ITEM_ID) || column.equals(ItemDiscount.PROP_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_APPLY_TO_OPTIONS) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemDiscount processItemDiscount(ResultSet rs) throws SQLException
        {        
            return new ItemDiscount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addItemDiscount(Integer ItemId, Integer DiscountId, Integer ApplyToOptions)
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
        
        public static int getAllItemDiscountCount()
        {
            return getAllRecordsCountByTableName("item_discount");        
        }
        
        public static ArrayList<ItemDiscount> getAllItemDiscount()
        {
            ArrayList<ItemDiscount> item_discount = new ArrayList<ItemDiscount>();
            try
            {
                getAllRecordsByTableName("item_discount");
                while(rs.next())
                {
                    item_discount.add(processItemDiscount(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discount;
        }
        
        public static ArrayList<ItemDiscount> getAllItemDiscountWithRelatedInfo()
        {
            ArrayList<ItemDiscount> item_discountList = new ArrayList<ItemDiscount>();
            try
            {
                getAllRecordsByTableName("item_discount");
                while (rs.next())
                {
                    item_discountList.add(processItemDiscount(rs));
                }

                
                    for(ItemDiscount item_discount : item_discountList)
                    {
                        
                            getRecordById("Item", item_discount.getItemId().toString());
                            item_discount.setItem(ItemDAO.processItem(rs));               
                        
                            getRecordById("Discount", item_discount.getDiscountId().toString());
                            item_discount.setDiscount(DiscountDAO.processDiscount(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemDiscountWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discountList;
        }
        
        
        public static ItemDiscount getRelatedInfo(ItemDiscount item_discount)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Item", item_discount.getItemId().toString());
                            item_discount.setItem(ItemDAO.processItem(rs));               
                        
                            getRecordById("Discount", item_discount.getDiscountId().toString());
                            item_discount.setDiscount(DiscountDAO.processDiscount(rs));               
                        

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
        
        public static ItemDiscount getAllRelatedObjects(ItemDiscount item_discount)
        {           
                         
            return item_discount;
        }
        
        
        
                
        public static ArrayList<ItemDiscount> getItemDiscountPaged(int limit, int offset)
        {
            ArrayList<ItemDiscount> item_discount = new ArrayList<ItemDiscount>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_discount", limit, offset);
                while (rs.next())
                {
                    item_discount.add(processItemDiscount(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemDiscountPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discount;
        } 
        
        public static ArrayList<ItemDiscount> getAllItemDiscountByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemDiscount> item_discount = new ArrayList<ItemDiscount>();
            try
            {
                getAllRecordsByColumn("item_discount", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_discount.add(processItemDiscount(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemDiscountByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discount;
        }
        
        public static ItemDiscount getItemDiscountByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemDiscount item_discount = new ItemDiscount();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_discount", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_discount = processItemDiscount(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemDiscountByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_discount;
        }                
                
        public static void updateItemDiscount(Integer ItemDiscountId,Integer ItemId,Integer DiscountId,Integer ApplyToOptions)
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
        
        public static void deleteAllItemDiscount()
        {
            try
            {
                updateQuery("DELETE FROM item_discount;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemDiscountById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_discount WHERE ItemDiscountId=" + id + ";");            
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

        public static void deleteItemDiscountByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_discount WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemDiscountByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

