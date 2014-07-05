package com.transitionsoft.dao;

import com.transitionsoft.Database;
import java.util.*;

public class ShoppingCart implements java.io.Serializable
{
    // The shopping cart items are stored in a Vector.
    protected Vector<Item> items;
    protected double shippingAmount = 0.0;
    protected double taxAmount = 0.0;
    protected ShippingAddress shipping;
    protected BillingAddress billing;

    public ShoppingCart()
    {
        items = new Vector<Item>();        
    }
    
    public ShippingAddress getShippingAddress()
    {
        return shipping;
    }
    
    public void setShippingAddress(ShippingAddress s)
    {
        shipping = s;
    }
    
    public BillingAddress getBillingAddress()
    {
        return billing;
    }

    public void setBillingAddress(BillingAddress billing)
    {
        this.billing = billing;
    }      

    public Vector<Item> getItems()
    {
        return (Vector<Item>) items.clone();
    }

    public synchronized void addItem(Item newItem)
    {
        Enumeration e = items.elements();

        // See if there is already an item like this in the cart.
        while (e.hasMoreElements())
        {
            Item currItem = (Item) e.nextElement();

            if (newItem.equals(currItem))
            {
                // Update the order quantity on the existing item.
                currItem.setItemQuantity(currItem.getItemQuantity() + newItem.getItemQuantity()); 
                return;
            }
        }

        // Didn't find one like this one, so add this one to the cart.
        items.addElement(newItem);
    }

    public synchronized void removeItem(int itemIndex)
    {
        Item item = (Item) items.elementAt(itemIndex);
        items.removeElementAt(itemIndex);
    }
    
    public synchronized int getTotalQuantity()
    {
        Enumeration e = items.elements();
        int qty = 0;

        // See if there is already an item like this in the cart.
        while (e.hasMoreElements())
        {
            Item currItem = (Item) e.nextElement();
            qty += currItem.getItemQuantity();
        }
        return qty;
    }
    
    
    public synchronized double getSubTotalPrice()
    {
        Enumeration e = items.elements();
        double amount = 0.0;

        // See if there is already an item like this in the cart.
        while (e.hasMoreElements())
        {
            Item currItem = (Item) e.nextElement();
            amount += currItem.getItemPrice()*currItem.getItemQuantity();
        }
        return amount;
    }
    
    public synchronized double getTaxPrice()
    {
        return taxAmount;
    }
            
    public synchronized double getShipPrice()
    {        
        return shippingAmount;
    }
            
    public synchronized void finalizeShipping(String id)
    {
        double[] shippingAmounts = Database.getShippingRates(id);
        shippingAmount = shippingAmounts[0] + (this.getTotalQuantity()-1)*shippingAmounts[1] ;              
    }
    
    public synchronized void finalizeTax()
    {
        taxAmount = getSubTotalPrice() * ( Database.getTaxRate(shipping.getState() ) / 100.0);
    }
    
    public synchronized double getTotalPrice()
    {        
        return getSubTotalPrice() + getTaxPrice() + getShipPrice();        
    }  
    
 
    
}