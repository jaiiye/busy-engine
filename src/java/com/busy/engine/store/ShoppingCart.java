package com.busy.engine.store;

import com.busy.engine.data.Database;
import com.busy.engine.entity.Address;
import com.busy.engine.entity.OrderItem;
import java.util.*;

/**
 *
 * @author Sourena
 */
public class ShoppingCart implements java.io.Serializable
{
    // The shopping cart items are stored in a Vector.
    protected ArrayList<OrderItem> items;
    protected double shippingAmount = 0.0;
    protected double taxAmount = 0.0;
    protected Address shipping;
    protected Address billing;

    public ShoppingCart()
    {
        items = new ArrayList<OrderItem>();        
    }
    
    public Address getShippingAddress()
    {
        return shipping;
    }
    
    public void setShippingAddress(Address s)
    {
        shipping = s;
    }
    
    public Address getBillingAddress()
    {
        return billing;
    }

    public void setBillingAddress(Address billing)
    {
        this.billing = billing;
    }      

    @SuppressWarnings("unchecked")    
    public ArrayList<OrderItem> getOrderItems()
    {
        return (ArrayList<OrderItem>) items.clone();
    }

    public synchronized void addOrderItem(OrderItem newItem)
    {
        // See if there is already an item like this in the cart.
        for(OrderItem currItem : items)
        {
            if (newItem.equals(currItem))
            {
                // Update the order quantity on the existing item.
                currItem.setQuantity(currItem.getQuantity() + newItem.getQuantity()); 
                return;
            }
        }

        // Didn't find one like this one, so add this one to the cart.
        items.add(newItem);
    }

    public synchronized void removeItem(int itemIndex)
    {
        OrderItem item = (OrderItem) items.get(itemIndex);
        items.remove(itemIndex);
    }
    
    public synchronized int getTotalQuantity()
    {
        int qty = 0;
        for(OrderItem currItem : items)
        {
        // See if there is already an item like this in the cart.
            qty += currItem.getQuantity();
        }
        return qty;
    }
    
    
    public synchronized double getSubTotalPrice()
    {
        double amount = 0.0;

        // See if there is already an item like this in the cart.
        for(OrderItem currItem : items)
        {
            amount += currItem.getUnitPrice()*currItem.getQuantity();
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
        taxAmount = getSubTotalPrice() * ( Database.getTaxRate(shipping.getStateProvince() ) / 100.0);
    }
    
    public synchronized double getTotalPrice()
    {        
        return getSubTotalPrice() + getTaxPrice() + getShipPrice();        
    }  
    
 
    
}