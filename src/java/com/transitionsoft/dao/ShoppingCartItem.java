package com.transitionsoft.dao;

public class ShoppingCartItem
{
    private String cartItemId;
    private String cartId;
    private String itemId;
    private String itemQuantity;
    private String itemOption;
    private String itemUnitPrice;

    public ShoppingCartItem(String cartItemId, String cartId, String itemId, String itemQuantity, String itemOption, String itemUnitPrice)
    {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemOption = itemOption;
        this.itemUnitPrice = itemUnitPrice;
    }

    public String getCartItemId()
    {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId)
    {
        this.cartItemId = cartItemId;
    }

    public String getCartId()
    {
        return cartId;
    }

    public void setCartId(String cartId)
    {
        this.cartId = cartId;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public String getItemQuantity()
    {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity)
    {
        this.itemQuantity = itemQuantity;
    }

    public String getItemOption()
    {
        return itemOption;
    }

    public void setItemOption(String itemOption)
    {
        this.itemOption = itemOption;
    }

    public String getItemUnitPrice()
    {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(String itemUnitPrice)
    {
        this.itemUnitPrice = itemUnitPrice;
    }
    
    
}
