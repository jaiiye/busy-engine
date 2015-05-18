    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.data.Column;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class ItemDaoImpl extends BasicConnection implements Serializable, ItemDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemCache
        {
            public static final ConcurrentLruCache<Integer, Item> itemCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Item i : findAll())
                {
                    getCache().put(i.getItemId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Item> getCache()
        {
            return ItemCache.itemCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Item> buildCache(ArrayList<Item> itemList)
        {        
            ConcurrentLruCache<Integer, Item> cache = new ConcurrentLruCache<Integer, Item>(itemList.size() + 1000);
            for (Item i : itemList)
            {
                cache.put(i.getItemId(), i);
            }
            return cache;
        }

        private static ArrayList<Item> findAll()
        {
            ArrayList<Item> item = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item");
                while (rs.next())
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
        public Item find(Integer id)
        {
            return findByColumn("ItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Item findWithInfo(Integer id)
        {
            Item item = findByColumn("ItemId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("item_type", item.getItemTypeId().toString());
                    item.setItemType(ItemType.process(rs));               
                
                    getRecordById("item_brand", item.getItemBrandId().toString());
                    item.setItemBrand(ItemBrand.process(rs));               
                
                    getRecordById("meta_tag", item.getMetaTagId().toString());
                    item.setMetaTag(MetaTag.process(rs));               
                
                    getRecordById("template", item.getTemplateId().toString());
                    item.setTemplate(Template.process(rs));               
                
                    getRecordById("vendor", item.getVendorId().toString());
                    item.setVendor(Vendor.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Item method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return item;
        }
        
        @Override
        public ArrayList<Item> findAll(Integer limit, Integer offset)
        {
            ArrayList<Item> itemList = new ArrayList<Item>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Item, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemList = new ArrayList<Item>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item", limit, offset);
                    while (rs.next())
                    {
                        itemList.add(Item.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Item object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemList;
         
        }
        
        @Override
        public ArrayList<Item> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Item> itemList = new ArrayList<Item>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Item, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemList = new ArrayList<Item>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Item item = (Item) e.getValue();

                            
                                getRecordById("item_type", item.getItemTypeId().toString());
                                item.setItemType(ItemType.process(rs));               
                            
                                getRecordById("item_brand", item.getItemBrandId().toString());
                                item.setItemBrand(ItemBrand.process(rs));               
                            
                                getRecordById("meta_tag", item.getMetaTagId().toString());
                                item.setMetaTag(MetaTag.process(rs));               
                            
                                getRecordById("template", item.getTemplateId().toString());
                                item.setTemplate(Template.process(rs));               
                            
                                getRecordById("vendor", item.getVendorId().toString());
                                item.setVendor(Vendor.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Item method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemList = new ArrayList<Item>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item", limit, offset);
                    while (rs.next())
                    {
                        itemList.add(Item.process(rs));
                    }

                    
                    
                        for (Item item : itemList)
                        {                        
                            
                                getRecordById("item_type", item.getItemTypeId().toString());
                                item.setItemType(ItemType.process(rs));               
                            
                                getRecordById("item_brand", item.getItemBrandId().toString());
                                item.setItemBrand(ItemBrand.process(rs));               
                            
                                getRecordById("meta_tag", item.getMetaTagId().toString());
                                item.setMetaTag(MetaTag.process(rs));               
                            
                                getRecordById("template", item.getTemplateId().toString());
                                item.setTemplate(Template.process(rs));               
                            
                                getRecordById("vendor", item.getVendorId().toString());
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
            }
            return itemList;            
        }
        
        @Override
        public ArrayList<Item> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Item> itemList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Item(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Item i = (Item) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item", Item.checkColumnName(columnName), columnValue, Item.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemList.add(Item.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Item's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemList;
        } 
        
        @Override
        public ArrayList<Item> findByColumns(Column... columns)
        {
            ArrayList<Item> itemList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(Item.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("item", columns);
                while (rs.next())
                {
                    itemList.add(Item.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Item's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return itemList;
        }
    
        @Override
        public int add(Item obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Item.checkColumnSize(obj.getItemName(), 255);
                Item.checkColumnSize(obj.getDescription(), 65535);                                
                Item.checkColumnSize(obj.getShortDescription(), 255);                
                Item.checkColumnSize(obj.getSku(), 30);                
                Item.checkColumnSize(obj.getLocale(), 10);
                
                openConnection();
                prepareStatement("INSERT INTO item(ItemName,Description,ListPrice,Price,ShortDescription,Adjustment,Sku,RatingSum,VoteCount,Rank,ItemStatus,Locale,ItemTypeId,ItemBrandId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getItemName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setDouble(3, obj.getListPrice());
                preparedStatement.setDouble(4, obj.getPrice());
                preparedStatement.setString(5, obj.getShortDescription());
                preparedStatement.setInt(6, obj.getAdjustment());
                preparedStatement.setString(7, obj.getSku());
                preparedStatement.setInt(8, 0);
                preparedStatement.setInt(9, 0);
                preparedStatement.setInt(10, 0);
                preparedStatement.setInt(11, obj.getItemStatus());
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getItemTypeId());
                preparedStatement.setInt(14, obj.getItemBrandId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Item's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
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
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Item's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Item item)
        {
            
                try
                { 
                    
                            getRecordById("item_type", item.getItemTypeId().toString());
                            item.setItemType(ItemType.process(rs));                                       
                    
                            getRecordById("item_brand", item.getItemBrandId().toString());
                            item.setItemBrand(ItemBrand.process(rs));                                       
                    
                            getRecordById("meta_tag", item.getMetaTagId().toString());
                            item.setMetaTag(MetaTag.process(rs));                                       
                    
                            getRecordById("template", item.getTemplateId().toString());
                            item.setTemplate(Template.process(rs));                                       
                    
                            getRecordById("vendor", item.getVendorId().toString());
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
              
        }
        
        @Override
        public void getRelatedObjects(Item item)
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
 
        }
        
        @Override
        public boolean remove(Item obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item WHERE ItemId=" + obj.getItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Item's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item WHERE ItemId=" + id + ";");           
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
                updateQuery("DELETE FROM item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Item's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item WHERE " + Item.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Item's removeByColumn method error: " + ex.getMessage());
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
                        Item i = (Item) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemId());
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
        
                    
        public void getRelatedItemAttributeList(Item item)
        {           
            item.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedItemCategoryList(Item item)
        {           
            item.setItemCategoryList(new ItemCategoryDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedItemDiscountList(Item item)
        {           
            item.setItemDiscountList(new ItemDiscountDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedItemFileList(Item item)
        {           
            item.setItemFileList(new ItemFileDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedItemImageList(Item item)
        {           
            item.setItemImageList(new ItemImageDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedItemLocationList(Item item)
        {           
            item.setItemLocationList(new ItemLocationDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedItemReviewList(Item item)
        {           
            item.setItemReviewList(new ItemReviewDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedOptionAvailabilityList(Item item)
        {           
            item.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedOrderItemList(Item item)
        {           
            item.setOrderItemList(new OrderItemDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
                    
        public void getRelatedSiteItemList(Item item)
        {           
            item.setSiteItemList(new SiteItemDaoImpl().findByColumn("ItemId", item.getItemId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedItemType(Item item)
        {            
            try
            {                 
                getRecordById("ItemType", item.getItemTypeId().toString());
                item.setItemType(ItemType.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedItemBrand(Item item)
        {            
            try
            {                 
                getRecordById("ItemBrand", item.getItemBrandId().toString());
                item.setItemBrand(ItemBrand.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedMetaTag(Item item)
        {            
            try
            {                 
                getRecordById("MetaTag", item.getMetaTagId().toString());
                item.setMetaTag(MetaTag.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getMetaTag error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedTemplate(Item item)
        {            
            try
            {                 
                getRecordById("Template", item.getTemplateId().toString());
                item.setTemplate(Template.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedVendor(Item item)
        {            
            try
            {                 
                getRecordById("Vendor", item.getVendorId().toString());
                item.setVendor(Vendor.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getVendor error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedItemTypeWithInfo(Item item)
        {            
            item.setItemType(new ItemTypeDaoImpl().findWithInfo(item.getItemTypeId()));
        }
        
        public void getRelatedItemBrandWithInfo(Item item)
        {            
            item.setItemBrand(new ItemBrandDaoImpl().findWithInfo(item.getItemBrandId()));
        }
        
        public void getRelatedMetaTagWithInfo(Item item)
        {            
            item.setMetaTag(new MetaTagDaoImpl().findWithInfo(item.getMetaTagId()));
        }
        
        public void getRelatedTemplateWithInfo(Item item)
        {            
            item.setTemplate(new TemplateDaoImpl().findWithInfo(item.getTemplateId()));
        }
        
        public void getRelatedVendorWithInfo(Item item)
        {            
            item.setVendor(new VendorDaoImpl().findWithInfo(item.getVendorId()));
        }
          
        
    }

