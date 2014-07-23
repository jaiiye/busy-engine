





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Item.PROP_ITEM_ID) || column.equals(Item.PROP_ITEM_NAME) || column.equals(Item.PROP_DESCRIPTION) || column.equals(Item.PROP_LIST_PRICE) || column.equals(Item.PROP_PRICE) || column.equals(Item.PROP_SHORT_DESCRIPTION) || column.equals(Item.PROP_ADJUSTMENT) || column.equals(Item.PROP_SKU) || column.equals(Item.PROP_RATING_SUM) || column.equals(Item.PROP_VOTE_COUNT) || column.equals(Item.PROP_RANK) || column.equals(Item.PROP_ITEM_STATUS) || column.equals(Item.PROP_LOCALE) || column.equals(Item.PROP_ITEM_TYPE_ID) || column.equals(Item.PROP_ITEM_BRAND_ID) || column.equals(Item.PROP_META_TAG_ID) || column.equals(Item.PROP_TEMPLATE_ID) || column.equals(Item.PROP_VENDOR_ID) )
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
            if (column.equals(Item.PROP_ITEM_ID) || column.equals(Item.PROP_LIST_PRICE) || column.equals(Item.PROP_PRICE) || column.equals(Item.PROP_ADJUSTMENT) || column.equals(Item.PROP_RATING_SUM) || column.equals(Item.PROP_VOTE_COUNT) || column.equals(Item.PROP_RANK) || column.equals(Item.PROP_ITEM_STATUS) || column.equals(Item.PROP_ITEM_TYPE_ID) || column.equals(Item.PROP_ITEM_BRAND_ID) || column.equals(Item.PROP_META_TAG_ID) || column.equals(Item.PROP_TEMPLATE_ID) || column.equals(Item.PROP_VENDOR_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Item processItem(ResultSet rs) throws SQLException
        {        
            return new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18));
        }
        
        public static int addItem(String ItemName, String Description, Double ListPrice, Double Price, String ShortDescription, Integer Adjustment, String Sku, Integer RatingSum, Integer VoteCount, Integer Rank, Integer ItemStatus, String Locale, Integer ItemTypeId, Integer ItemBrandId, Integer MetaTagId, Integer TemplateId, Integer VendorId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ItemName, 255);
                checkColumnSize(Description, 65535);
                
                
                checkColumnSize(ShortDescription, 255);
                
                checkColumnSize(Sku, 30);
                
                
                
                
                checkColumnSize(Locale, 10);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item(ItemName,Description,ListPrice,Price,ShortDescription,Adjustment,Sku,RatingSum,VoteCount,Rank,ItemStatus,Locale,ItemTypeId,ItemBrandId,MetaTagId,TemplateId,VendorId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, ItemName);
                preparedStatement.setString(2, Description);
                preparedStatement.setDouble(3, ListPrice);
                preparedStatement.setDouble(4, Price);
                preparedStatement.setString(5, ShortDescription);
                preparedStatement.setInt(6, Adjustment);
                preparedStatement.setString(7, Sku);
                preparedStatement.setInt(8, RatingSum);
                preparedStatement.setInt(9, VoteCount);
                preparedStatement.setInt(10, Rank);
                preparedStatement.setInt(11, ItemStatus);
                preparedStatement.setString(12, Locale);
                preparedStatement.setInt(13, ItemTypeId);
                preparedStatement.setInt(14, ItemBrandId);
                preparedStatement.setInt(15, MetaTagId);
                preparedStatement.setInt(16, TemplateId);
                preparedStatement.setInt(17, VendorId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemCount()
        {
            return getAllRecordsCountByTableName("item");        
        }
        
        public static ArrayList<Item> getAllItem()
        {
            ArrayList<Item> item = new ArrayList<Item>();
            try
            {
                getAllRecordsByTableName("item");
                while(rs.next())
                {
                    item.add(processItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item;
        }
        
        public static ArrayList<Item> getAllItemWithRelatedInfo()
        {
            ArrayList<Item> itemList = new ArrayList<Item>();
            try
            {
                getAllRecordsByTableName("item");
                while (rs.next())
                {
                    itemList.add(processItem(rs));
                }

                
                    for(Item item : itemList)
                    {
                        
                            getRecordById("ItemType", item.getItemTypeId().toString());
                            item.setItemType(ItemTypeDAO.processItemType(rs));               
                        
                            getRecordById("ItemBrand", item.getItemBrandId().toString());
                            item.setItemBrand(ItemBrandDAO.processItemBrand(rs));               
                        
                            getRecordById("MetaTag", item.getMetaTagId().toString());
                            item.setMetaTag(MetaTagDAO.processMetaTag(rs));               
                        
                            getRecordById("Template", item.getTemplateId().toString());
                            item.setTemplate(TemplateDAO.processTemplate(rs));               
                        
                            getRecordById("Vendor", item.getVendorId().toString());
                            item.setVendor(VendorDAO.processVendor(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemList;
        }
        
        
        public static Item getRelatedInfo(Item item)
        {
           
                
                    try
                    { 
                        
                            getRecordById("ItemType", item.getItemTypeId().toString());
                            item.setItemType(ItemTypeDAO.processItemType(rs));               
                        
                            getRecordById("ItemBrand", item.getItemBrandId().toString());
                            item.setItemBrand(ItemBrandDAO.processItemBrand(rs));               
                        
                            getRecordById("MetaTag", item.getMetaTagId().toString());
                            item.setMetaTag(MetaTagDAO.processMetaTag(rs));               
                        
                            getRecordById("Template", item.getTemplateId().toString());
                            item.setTemplate(TemplateDAO.processTemplate(rs));               
                        
                            getRecordById("Vendor", item.getVendorId().toString());
                            item.setVendor(VendorDAO.processVendor(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return item;
        }
        
        public static Item getAllRelatedObjects(Item item)
        {           
            item.setItemAttributeList(ItemAttributeDAO.getAllItemAttributeByColumn("ItemId", item.getItemId().toString()));
item.setItemCategoryList(ItemCategoryDAO.getAllItemCategoryByColumn("ItemId", item.getItemId().toString()));
item.setItemDiscountList(ItemDiscountDAO.getAllItemDiscountByColumn("ItemId", item.getItemId().toString()));
item.setItemFileList(ItemFileDAO.getAllItemFileByColumn("ItemId", item.getItemId().toString()));
item.setItemImageList(ItemImageDAO.getAllItemImageByColumn("ItemId", item.getItemId().toString()));
item.setItemLocationList(ItemLocationDAO.getAllItemLocationByColumn("ItemId", item.getItemId().toString()));
item.setItemReviewList(ItemReviewDAO.getAllItemReviewByColumn("ItemId", item.getItemId().toString()));
item.setOptionAvailabilityList(OptionAvailabilityDAO.getAllOptionAvailabilityByColumn("ItemId", item.getItemId().toString()));
item.setOrderItemList(OrderItemDAO.getAllOrderItemByColumn("ItemId", item.getItemId().toString()));
item.setSiteItemList(SiteItemDAO.getAllSiteItemByColumn("ItemId", item.getItemId().toString()));
             
            return item;
        }
        
        
                    
        public static Item getRelatedItemAttributeList(Item item)
        {           
            item.setItemAttributeList(ItemAttributeDAO.getAllItemAttributeByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedItemCategoryList(Item item)
        {           
            item.setItemCategoryList(ItemCategoryDAO.getAllItemCategoryByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedItemDiscountList(Item item)
        {           
            item.setItemDiscountList(ItemDiscountDAO.getAllItemDiscountByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedItemFileList(Item item)
        {           
            item.setItemFileList(ItemFileDAO.getAllItemFileByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedItemImageList(Item item)
        {           
            item.setItemImageList(ItemImageDAO.getAllItemImageByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedItemLocationList(Item item)
        {           
            item.setItemLocationList(ItemLocationDAO.getAllItemLocationByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedItemReviewList(Item item)
        {           
            item.setItemReviewList(ItemReviewDAO.getAllItemReviewByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedOptionAvailabilityList(Item item)
        {           
            item.setOptionAvailabilityList(OptionAvailabilityDAO.getAllOptionAvailabilityByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedOrderItemList(Item item)
        {           
            item.setOrderItemList(OrderItemDAO.getAllOrderItemByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
                    
        public static Item getRelatedSiteItemList(Item item)
        {           
            item.setSiteItemList(SiteItemDAO.getAllSiteItemByColumn("ItemId", item.getItemId().toString()));
            return item;
        }        
        
                
        public static ArrayList<Item> getItemPaged(int limit, int offset)
        {
            ArrayList<Item> item = new ArrayList<Item>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item", limit, offset);
                while (rs.next())
                {
                    item.add(processItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item;
        } 
        
        public static ArrayList<Item> getAllItemByColumn(String columnName, String columnValue)
        {
            ArrayList<Item> item = new ArrayList<Item>();
            try
            {
                getAllRecordsByColumn("item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item.add(processItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item;
        }
        
        public static Item getItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Item item = new Item();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item = processItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item;
        }                
                
        public static void updateItem(Integer ItemId,String ItemName,String Description,Double ListPrice,Double Price,String ShortDescription,Integer Adjustment,String Sku,Integer RatingSum,Integer VoteCount,Integer Rank,Integer ItemStatus,String Locale,Integer ItemTypeId,Integer ItemBrandId,Integer MetaTagId,Integer TemplateId,Integer VendorId)
        {  
            try
            {   
                
                checkColumnSize(ItemName, 255);
                checkColumnSize(Description, 65535);
                
                
                checkColumnSize(ShortDescription, 255);
                
                checkColumnSize(Sku, 30);
                
                
                
                
                checkColumnSize(Locale, 10);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item SET ItemName=?,Description=?,ListPrice=?,Price=?,ShortDescription=?,Adjustment=?,Sku=?,RatingSum=?,VoteCount=?,Rank=?,ItemStatus=?,Locale=?,ItemTypeId=?,ItemBrandId=?,MetaTagId=?,TemplateId=?,VendorId=? WHERE ItemId=?;");                    
                preparedStatement.setString(1, ItemName);
                preparedStatement.setString(2, Description);
                preparedStatement.setDouble(3, ListPrice);
                preparedStatement.setDouble(4, Price);
                preparedStatement.setString(5, ShortDescription);
                preparedStatement.setInt(6, Adjustment);
                preparedStatement.setString(7, Sku);
                preparedStatement.setInt(8, RatingSum);
                preparedStatement.setInt(9, VoteCount);
                preparedStatement.setInt(10, Rank);
                preparedStatement.setInt(11, ItemStatus);
                preparedStatement.setString(12, Locale);
                preparedStatement.setInt(13, ItemTypeId);
                preparedStatement.setInt(14, ItemBrandId);
                preparedStatement.setInt(15, MetaTagId);
                preparedStatement.setInt(16, TemplateId);
                preparedStatement.setInt(17, VendorId);
                preparedStatement.setInt(18, ItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItem()
        {
            try
            {
                updateQuery("DELETE FROM item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item WHERE ItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

