





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemDaoImpl extends BasicConnection implements Serializable, ItemDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Item find(Integer id)
        {
            return findByColumn("ItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Item> findAll(Integer limit, Integer offset)
        {
            ArrayList<Item> item = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item");
                while(rs.next())
                {
                    item.add(Item.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Item object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item;
         
        }
        
        @Override
        public ArrayList<Item> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Item> itemList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item", limit, offset);
                while (rs.next())
                {
                    itemList.add(Item.process(rs));
                }

                
                    for(Item item : itemList)
                    {
                        
                            getRecordById("ItemType", item.getItemTypeId().toString());
                            item.setItemType(ItemType.process(rs));               
                        
                            getRecordById("ItemBrand", item.getItemBrandId().toString());
                            item.setItemBrand(ItemBrand.process(rs));               
                        
                            getRecordById("MetaTag", item.getMetaTagId().toString());
                            item.setMetaTag(MetaTag.process(rs));               
                        
                            getRecordById("Template", item.getTemplateId().toString());
                            item.setTemplate(Template.process(rs));               
                        
                            getRecordById("Vendor", item.getVendorId().toString());
                            item.setVendor(Vendor.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Item method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemList;
        }
        
        @Override
        public ArrayList<Item> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Item> item = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item", Item.checkColumnName(columnName), columnValue, Item.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item.add(Item.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Item's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item;
        } 
    
        @Override
        public void add(Item obj)
        {
            try
            {
                
                Item.checkColumnSize(obj.getItemName(), 255);
                Item.checkColumnSize(obj.getDescription(), 65535);
                
                
                Item.checkColumnSize(obj.getShortDescription(), 255);
                
                Item.checkColumnSize(obj.getSku(), 30);
                
                
                
                
                Item.checkColumnSize(obj.getLocale(), 10);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item(ItemName,Description,ListPrice,Price,ShortDescription,Adjustment,Sku,RatingSum,VoteCount,Rank,ItemStatus,Locale,ItemTypeId,ItemBrandId,MetaTagId,TemplateId,VendorId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getItemName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setDouble(3, obj.getListPrice());
                preparedStatement.setDouble(4, obj.getPrice());
                preparedStatement.setString(5, obj.getShortDescription());
                preparedStatement.setInt(6, obj.getAdjustment());
                preparedStatement.setString(7, obj.getSku());
                preparedStatement.setInt(8, obj.getRatingSum());
                preparedStatement.setInt(9, obj.getVoteCount());
                preparedStatement.setInt(10, obj.getRank());
                preparedStatement.setInt(11, obj.getItemStatus());
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getItemTypeId());
                preparedStatement.setInt(14, obj.getItemBrandId());
                preparedStatement.setInt(15, obj.getMetaTagId());
                preparedStatement.setInt(16, obj.getTemplateId());
                preparedStatement.setInt(17, obj.getVendorId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Item's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String ItemName, String Description, Double ListPrice, Double Price, String ShortDescription, Integer Adjustment, String Sku, Integer RatingSum, Integer VoteCount, Integer Rank, Integer ItemStatus, String Locale, Integer ItemTypeId, Integer ItemBrandId, Integer MetaTagId, Integer TemplateId, Integer VendorId)
        {   
            int id = 0;
            try
            {
                
                Item.checkColumnSize(ItemName, 255);
                Item.checkColumnSize(Description, 65535);
                
                
                Item.checkColumnSize(ShortDescription, 255);
                
                Item.checkColumnSize(Sku, 30);
                
                
                
                
                Item.checkColumnSize(Locale, 10);
                
                
                
                
                
                                            
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
        
        
        @Override
        public Item update(Item obj)
        {
           try
            {   
                
                Item.checkColumnSize(obj.getItemName(), 255);
                Item.checkColumnSize(obj.getDescription(), 65535);
                
                
                Item.checkColumnSize(obj.getShortDescription(), 255);
                
                Item.checkColumnSize(obj.getSku(), 30);
                
                
                
                
                Item.checkColumnSize(obj.getLocale(), 10);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item SET ItemName=?,Description=?,ListPrice=?,Price=?,ShortDescription=?,Adjustment=?,Sku=?,RatingSum=?,VoteCount=?,Rank=?,ItemStatus=?,Locale=?,ItemTypeId=?,ItemBrandId=?,MetaTagId=?,TemplateId=?,VendorId=? WHERE ItemId=?;");                    
                preparedStatement.setString(1, obj.getItemName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setDouble(3, obj.getListPrice());
                preparedStatement.setDouble(4, obj.getPrice());
                preparedStatement.setString(5, obj.getShortDescription());
                preparedStatement.setInt(6, obj.getAdjustment());
                preparedStatement.setString(7, obj.getSku());
                preparedStatement.setInt(8, obj.getRatingSum());
                preparedStatement.setInt(9, obj.getVoteCount());
                preparedStatement.setInt(10, obj.getRank());
                preparedStatement.setInt(11, obj.getItemStatus());
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getItemTypeId());
                preparedStatement.setInt(14, obj.getItemBrandId());
                preparedStatement.setInt(15, obj.getMetaTagId());
                preparedStatement.setInt(16, obj.getTemplateId());
                preparedStatement.setInt(17, obj.getVendorId());
                preparedStatement.setInt(18, obj.getItemId());
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
            return obj;
        }
        
        public static void update(Integer ItemId,String ItemName,String Description,Double ListPrice,Double Price,String ShortDescription,Integer Adjustment,String Sku,Integer RatingSum,Integer VoteCount,Integer Rank,Integer ItemStatus,String Locale,Integer ItemTypeId,Integer ItemBrandId,Integer MetaTagId,Integer TemplateId,Integer VendorId)
        {  
            try
            {   
                
                Item.checkColumnSize(ItemName, 255);
                Item.checkColumnSize(Description, 65535);
                
                
                Item.checkColumnSize(ShortDescription, 255);
                
                Item.checkColumnSize(Sku, 30);
                
                
                
                
                Item.checkColumnSize(Locale, 10);
                
                
                
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item");
        }
        
        
        @Override
        public Item getRelatedInfo(Item item)
        {
            
                try
                { 
                    
                        getRecordById("ItemType", item.getItemTypeId().toString());
                        item.setItemType(ItemType.process(rs));               
                    
                        getRecordById("ItemBrand", item.getItemBrandId().toString());
                        item.setItemBrand(ItemBrand.process(rs));               
                    
                        getRecordById("MetaTag", item.getMetaTagId().toString());
                        item.setMetaTag(MetaTag.process(rs));               
                    
                        getRecordById("Template", item.getTemplateId().toString());
                        item.setTemplate(Template.process(rs));               
                    
                        getRecordById("Vendor", item.getVendorId().toString());
                        item.setVendor(Vendor.process(rs));               
                    

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
        
        
        @Override
        public Item getRelatedObjects(Item item)
        {
            item.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setItemFileList(new ItemFileDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setItemImageList(new ItemImageDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setItemReviewList(new ItemReviewDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setOrderItemList(new OrderItemDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
item.setSiteItemList(new SiteItemDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
             
            return item;
        }
        
        
        
        @Override
        public void remove(Item obj)
        {            
            try
            {
                updateQuery("DELETE FROM item WHERE ItemId=" + obj.getItemId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM item WHERE ItemId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM item;");
            }
            catch (Exception ex)
            {
                System.out.println("Item's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item WHERE " + Item.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Item's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Item getRelatedItemAttributeList(Item item)
        {           
            item.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedItemCategoryList(Item item)
        {           
            item.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedItemDiscountList(Item item)
        {           
            item.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedItemFileList(Item item)
        {           
            item.setItemFileList(new ItemFileDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedItemImageList(Item item)
        {           
            item.setItemImageList(new ItemImageDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedItemLocationList(Item item)
        {           
            item.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedItemReviewList(Item item)
        {           
            item.setItemReviewList(new ItemReviewDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedOptionAvailabilityList(Item item)
        {           
            item.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedOrderItemList(Item item)
        {           
            item.setOrderItemList(new OrderItemDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
                    
        public Item getRelatedSiteItemList(Item item)
        {           
            item.setSiteItemList(new SiteItemDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
            return item;
        }        
        
                             
    }

