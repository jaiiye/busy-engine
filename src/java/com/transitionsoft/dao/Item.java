package com.transitionsoft.dao;


public class Item implements java.io.Serializable
{
    private String itemId;
    private String itemImageName;
    private String itemName;    
    private String itemBrandId;
    private String itemBrandName;
    private String itemOption;
    private double itemPrice;
    private int itemPriceAdjustment;
    private double itemMSRP;
    private double itemRate;
    private int itemVotes;
    private int itemQuantity;
    private String itemDescription;
    private ItemInfo itemInfo;
    private String itemSeoTitle;
    private String itemSeoDescription;
    private String itemSeoKeywords;
    private String itemShortDescription;

    public Item()
    {
        itemInfo = null;
    }


    public Item(String id, String name, String option, double price, int quantity)
    {
        itemId = id;
        itemName = name;
        itemOption = option;
        itemPrice = price;
        itemQuantity = quantity;
        itemInfo = null;
    }


    public Item(String id, String itemImageName, String name, String option, double itemMSRP, double price, int quantity, String itemBrandName, String desc, double rate, String shortDesc)
    {
        itemId = id;
        this.itemImageName = itemImageName;
        itemName = name;
        itemOption = option;
        itemPrice = price;
        this.itemMSRP = itemMSRP;
        itemQuantity = quantity;
        this.itemBrandName = itemBrandName;
        this.itemDescription = desc;
        this.itemRate = rate;
        itemInfo = null;
        itemShortDescription = shortDesc;
    }
    
    public Item(String id, String itemImageName, String name, String seoTitle, String seoDesc, String seoKey, String option, double itemMSRP, double price, int quantity, String itemBrandName, String desc, double rate, String brandId, String shortDesc)
    {
        itemId = id;
        this.itemImageName = itemImageName;
        itemName = name;
        itemOption = option;
        itemPrice = price;
        this.itemMSRP = itemMSRP;
        itemQuantity = quantity;
        this.itemBrandName = itemBrandName;
        this.itemDescription = desc;
        this.itemRate = rate;
        itemInfo = null;
        itemSeoTitle = seoTitle;
        itemSeoDescription = seoDesc;
        itemSeoKeywords = seoKey;
        itemBrandId = brandId;
        itemShortDescription = shortDesc;
    }

    // Make get/set methods so the attributes will appear as bean attributes.
    public String getItemId() { return itemId; }
    public void setItemId(String aProductCode) { itemId = aProductCode; }

    public String getItemName() { return itemName; }
    public void setItemName(String aDescription) { itemName = aDescription; }  
    
    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double aPrice) { itemPrice = aPrice; }

    public double getItemRate() { return itemRate; }
    public void setItemRate(double aRate) { itemRate = aRate; }

    public int getQuantity() { return itemQuantity; }
    public void setQuantity(int aQuantity) { itemQuantity = aQuantity; }

    public int getItemVotes() { return itemVotes; }
    public void setItemVotes(int Votes) { itemVotes = Votes; }

    public String getItemBrandId()
    {
        return itemBrandId;
    }

    public void setItemBrandId(String itemBrandId)
    {
        this.itemBrandId = itemBrandId;
    }

    public String getItemSeoTitle()
    {
        return itemSeoTitle;
    }

    public void setItemSeoTitle(String itemSeoTitle)
    {
        this.itemSeoTitle = itemSeoTitle;
    }

    public String getItemSeoDescription()
    {
        return itemSeoDescription;
    }

    public void setItemSeoDescription(String itemSeoDescription)
    {
        this.itemSeoDescription = itemSeoDescription;
    }

    public String getItemSeoKeywords()
    {
        return itemSeoKeywords;
    }

    public void setItemSeoKeywords(String itemSeoKeywords)
    {
        this.itemSeoKeywords = itemSeoKeywords;
    }

    public int getItemPriceAdjustment()
    {
        return itemPriceAdjustment;
    }

    public void setItemPriceAdjustment(int itemPriceAdjustment)
    {
        this.itemPriceAdjustment = itemPriceAdjustment;
    }

    public String getItemBrandName()
    {
        return itemBrandName;
    }

    public void setItemBrandName(String itemBrandName)
    {
        this.itemBrandName = itemBrandName;
    }


    public double getItemMSRP()
    {
        return itemMSRP;
    }

    public void setItemMSRP(double itemMSRP)
    {
        this.itemMSRP = itemMSRP;
    }


    public String getItemImageName()
    {
        return itemImageName;
    }

    public void setItemImageName(String itemImageName)
    {
        this.itemImageName = itemImageName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public String getItemOption() {
        return itemOption;
    }

    public void setItemOption(String itemOption) {
        this.itemOption = itemOption;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public ItemInfo getItemInfo()
    {
        return itemInfo;
    }

    public void setItemInfo(ItemInfo itemInfo)
    {
        this.itemInfo = itemInfo;
    }

    public String getItemShortDescription()
    {
        return itemShortDescription;
    }

    public void setItemShortDescription(String itemShortDescription)
    {
        this.itemShortDescription = itemShortDescription;
    }



    @Override
    public boolean equals(Object ob)
    {
        if (ob == this) return true;
        if (!(ob instanceof Item)) return false;
        if (((Item)ob).getItemId().equals(getItemId()))
        {
            if(((Item)ob).itemName.equals(itemName))
            {
                if(((Item)ob).itemOption.equals(itemOption))
                {
                    return true;
                }
            }
            else
            {
                return false;
            }            
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do 
    }
    
}
