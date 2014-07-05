


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Item extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_ITEMNAME = "ItemName";
        public static final String PROP_ITEMDESCRIPTION = "ItemDescription";
        public static final String PROP_ITEMBRANDID = "ItemBrandId";
        public static final String PROP_ITEMLISTPRICE = "ItemListPrice";
        public static final String PROP_ITEMPRICE = "ItemPrice";
        public static final String PROP_ITEMPRICEADJUSTMENT = "ItemPriceAdjustment";
        public static final String PROP_ITEMSEOTITLE = "ItemSEOTitle";
        public static final String PROP_ITEMSEODESCRIPTION = "ItemSEODescription";
        public static final String PROP_ITEMSEOKEYWORDS = "ItemSEOKeywords";
        public static final String PROP_ITEMTYPE = "ItemType";
        public static final String PROP_ITEMUPC = "ItemUPC";
        public static final String PROP_ITEMRATING = "ItemRating";
        public static final String PROP_ITEMVOTECOUNT = "ItemVoteCount";
        public static final String PROP_ITEMSHORTDESCRIPTION = "ItemShortDescription";
        
        
        private Integer itemId;
        private String itemName;
        private String itemDescription;
        private Integer itemBrandId;
        private Double itemListPrice;
        private Double itemPrice;
        private Integer itemPriceAdjustment;
        private String itemSEOTitle;
        private String itemSEODescription;
        private String itemSEOKeywords;
        private Integer itemType;
        private String itemUPC;
        private Double itemRating;
        private Integer itemVoteCount;
        private String itemShortDescription;
        
        
        public Item()
        {
            this.itemId = 0; 
       this.itemName = ""; 
       this.itemDescription = ""; 
       this.itemBrandId = 0; 
       this.itemListPrice = 0.0; 
       this.itemPrice = 0.0; 
       this.itemPriceAdjustment = 0; 
       this.itemSEOTitle = ""; 
       this.itemSEODescription = ""; 
       this.itemSEOKeywords = ""; 
       this.itemType = 0; 
       this.itemUPC = ""; 
       this.itemRating = 0.0; 
       this.itemVoteCount = 0; 
       this.itemShortDescription = ""; 
        }
        
        public Item(Integer ItemId, String ItemName, String ItemDescription, Integer ItemBrandId, Double ItemListPrice, Double ItemPrice, Integer ItemPriceAdjustment, String ItemSEOTitle, String ItemSEODescription, String ItemSEOKeywords, Integer ItemType, String ItemUPC, Double ItemRating, Integer ItemVoteCount, String ItemShortDescription)
        {
            this.itemId = ItemId;
       this.itemName = ItemName;
       this.itemDescription = ItemDescription;
       this.itemBrandId = ItemBrandId;
       this.itemListPrice = ItemListPrice;
       this.itemPrice = ItemPrice;
       this.itemPriceAdjustment = ItemPriceAdjustment;
       this.itemSEOTitle = ItemSEOTitle;
       this.itemSEODescription = ItemSEODescription;
       this.itemSEOKeywords = ItemSEOKeywords;
       this.itemType = ItemType;
       this.itemUPC = ItemUPC;
       this.itemRating = ItemRating;
       this.itemVoteCount = ItemVoteCount;
       this.itemShortDescription = ItemShortDescription;
        } 
        
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public String getItemName()
            {
                return this.itemName;
            }
            
            public void setItemName(String ItemName)
            {
                this.itemName = ItemName;
            }
        
            public String getItemDescription()
            {
                return this.itemDescription;
            }
            
            public void setItemDescription(String ItemDescription)
            {
                this.itemDescription = ItemDescription;
            }
        
            public Integer getItemBrandId()
            {
                return this.itemBrandId;
            }
            
            public void setItemBrandId(Integer ItemBrandId)
            {
                this.itemBrandId = ItemBrandId;
            }
        
            public Double getItemListPrice()
            {
                return this.itemListPrice;
            }
            
            public void setItemListPrice(Double ItemListPrice)
            {
                this.itemListPrice = ItemListPrice;
            }
        
            public Double getItemPrice()
            {
                return this.itemPrice;
            }
            
            public void setItemPrice(Double ItemPrice)
            {
                this.itemPrice = ItemPrice;
            }
        
            public Integer getItemPriceAdjustment()
            {
                return this.itemPriceAdjustment;
            }
            
            public void setItemPriceAdjustment(Integer ItemPriceAdjustment)
            {
                this.itemPriceAdjustment = ItemPriceAdjustment;
            }
        
            public String getItemSEOTitle()
            {
                return this.itemSEOTitle;
            }
            
            public void setItemSEOTitle(String ItemSEOTitle)
            {
                this.itemSEOTitle = ItemSEOTitle;
            }
        
            public String getItemSEODescription()
            {
                return this.itemSEODescription;
            }
            
            public void setItemSEODescription(String ItemSEODescription)
            {
                this.itemSEODescription = ItemSEODescription;
            }
        
            public String getItemSEOKeywords()
            {
                return this.itemSEOKeywords;
            }
            
            public void setItemSEOKeywords(String ItemSEOKeywords)
            {
                this.itemSEOKeywords = ItemSEOKeywords;
            }
        
            public Integer getItemType()
            {
                return this.itemType;
            }
            
            public void setItemType(Integer ItemType)
            {
                this.itemType = ItemType;
            }
        
            public String getItemUPC()
            {
                return this.itemUPC;
            }
            
            public void setItemUPC(String ItemUPC)
            {
                this.itemUPC = ItemUPC;
            }
        
            public Double getItemRating()
            {
                return this.itemRating;
            }
            
            public void setItemRating(Double ItemRating)
            {
                this.itemRating = ItemRating;
            }
        
            public Integer getItemVoteCount()
            {
                return this.itemVoteCount;
            }
            
            public void setItemVoteCount(Integer ItemVoteCount)
            {
                this.itemVoteCount = ItemVoteCount;
            }
        
            public String getItemShortDescription()
            {
                return this.itemShortDescription;
            }
            
            public void setItemShortDescription(String ItemShortDescription)
            {
                this.itemShortDescription = ItemShortDescription;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Item.PROP_ITEMID) || column.equals(Item.PROP_ITEMNAME) || column.equals(Item.PROP_ITEMDESCRIPTION) || column.equals(Item.PROP_ITEMBRANDID) || column.equals(Item.PROP_ITEMLISTPRICE) || column.equals(Item.PROP_ITEMPRICE) || column.equals(Item.PROP_ITEMPRICEADJUSTMENT) || column.equals(Item.PROP_ITEMSEOTITLE) || column.equals(Item.PROP_ITEMSEODESCRIPTION) || column.equals(Item.PROP_ITEMSEOKEYWORDS) || column.equals(Item.PROP_ITEMTYPE) || column.equals(Item.PROP_ITEMUPC) || column.equals(Item.PROP_ITEMRATING) || column.equals(Item.PROP_ITEMVOTECOUNT) || column.equals(Item.PROP_ITEMSHORTDESCRIPTION) )
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
            if (column.equals(Item.PROP_ITEMID) || column.equals(Item.PROP_ITEMBRANDID) || column.equals(Item.PROP_ITEMLISTPRICE) || column.equals(Item.PROP_ITEMPRICE) || column.equals(Item.PROP_ITEMPRICEADJUSTMENT) || column.equals(Item.PROP_ITEMTYPE) || column.equals(Item.PROP_ITEMRATING) || column.equals(Item.PROP_ITEMVOTECOUNT) )
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
            return new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getDouble(13), rs.getInt(14), rs.getString(15));
        }
        
        public static int addItem(String ItemName, String ItemDescription, Integer ItemBrandId, Double ItemListPrice, Double ItemPrice, Integer ItemPriceAdjustment, String ItemSEOTitle, String ItemSEODescription, String ItemSEOKeywords, Integer ItemType, String ItemUPC, Double ItemRating, Integer ItemVoteCount, String ItemShortDescription)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ItemName, 255);
                checkColumnSize(ItemDescription, 65535);
                
                
                
                
                checkColumnSize(ItemSEOTitle, 245);
                checkColumnSize(ItemSEODescription, 245);
                checkColumnSize(ItemSEOKeywords, 245);
                
                checkColumnSize(ItemUPC, 45);
                
                
                checkColumnSize(ItemShortDescription, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO item(ItemName,ItemDescription,ItemBrandId,ItemListPrice,ItemPrice,ItemPriceAdjustment,ItemSEOTitle,ItemSEODescription,ItemSEOKeywords,ItemType,ItemUPC,ItemRating,ItemVoteCount,ItemShortDescription) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, ItemName);
                preparedStatement.setString(2, ItemDescription);
                preparedStatement.setInt(3, ItemBrandId);
                preparedStatement.setDouble(4, ItemListPrice);
                preparedStatement.setDouble(5, ItemPrice);
                preparedStatement.setInt(6, ItemPriceAdjustment);
                preparedStatement.setString(7, ItemSEOTitle);
                preparedStatement.setString(8, ItemSEODescription);
                preparedStatement.setString(9, ItemSEOKeywords);
                preparedStatement.setInt(10, ItemType);
                preparedStatement.setString(11, ItemUPC);
                preparedStatement.setDouble(12, ItemRating);
                preparedStatement.setInt(13, ItemVoteCount);
                preparedStatement.setString(14, ItemShortDescription);
                
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
                
        public static void updateItem(Integer ItemId,String ItemName,String ItemDescription,Integer ItemBrandId,Double ItemListPrice,Double ItemPrice,Integer ItemPriceAdjustment,String ItemSEOTitle,String ItemSEODescription,String ItemSEOKeywords,Integer ItemType,String ItemUPC,Double ItemRating,Integer ItemVoteCount,String ItemShortDescription)
        {  
            try
            {   
                
                checkColumnSize(ItemName, 255);
                checkColumnSize(ItemDescription, 65535);
                
                
                
                
                checkColumnSize(ItemSEOTitle, 245);
                checkColumnSize(ItemSEODescription, 245);
                checkColumnSize(ItemSEOKeywords, 245);
                
                checkColumnSize(ItemUPC, 45);
                
                
                checkColumnSize(ItemShortDescription, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE item SET ItemName=?,ItemDescription=?,ItemBrandId=?,ItemListPrice=?,ItemPrice=?,ItemPriceAdjustment=?,ItemSEOTitle=?,ItemSEODescription=?,ItemSEOKeywords=?,ItemType=?,ItemUPC=?,ItemRating=?,ItemVoteCount=?,ItemShortDescription=? WHERE ItemId=?;");                    
                preparedStatement.setString(1, ItemName);
                preparedStatement.setString(2, ItemDescription);
                preparedStatement.setInt(3, ItemBrandId);
                preparedStatement.setDouble(4, ItemListPrice);
                preparedStatement.setDouble(5, ItemPrice);
                preparedStatement.setInt(6, ItemPriceAdjustment);
                preparedStatement.setString(7, ItemSEOTitle);
                preparedStatement.setString(8, ItemSEODescription);
                preparedStatement.setString(9, ItemSEOKeywords);
                preparedStatement.setInt(10, ItemType);
                preparedStatement.setString(11, ItemUPC);
                preparedStatement.setDouble(12, ItemRating);
                preparedStatement.setInt(13, ItemVoteCount);
                preparedStatement.setString(14, ItemShortDescription);
                preparedStatement.setInt(15, ItemId);
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

