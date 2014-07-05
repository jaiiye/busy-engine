package com.transitionsoft.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderInfo
{
    private String orderId;
    private String shippingId;
    private String orderStatus;
    private String orderDate;
    private String paypalTransactionId;
    private String paypalAmountBilled;
    private String paypalPaymentStatus;
    private String paypalPendingReason;
    private String paypalPaymentType;
    private String paypalFeeCharged;
    private String paypalCurrencyCode;
    private String paypalPayerId;
    private String orderTaxAmount;
    private String orderShippingAmount;
    private String orderAdditionalData;
    private String shoppingCartId;
    private String customerId;
    private ArrayList<ShoppingCartItem> cartItems;
    private Customer customer;
    private ShippingMethod shippingMethod; 

    public OrderInfo(String orderId, String shippingId, String orderStatus, String orderDate, String paypalTransactionId, String paypalAmountBilled, String paypalPaymentStatus, String paypalPendingReason, String paypalPaymentType, String paypalFeeCharged, String paypalCurrencyCode, String paypalPayerId, String orderTaxAmount, String orderShippingAmount, String orderAdditionalData, String shoppingCartId, String customerId)
    {
        this.orderId = orderId;
        this.shippingId = shippingId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.paypalTransactionId = paypalTransactionId;
        this.paypalAmountBilled = paypalAmountBilled;
        this.paypalPaymentStatus = paypalPaymentStatus;
        this.paypalPendingReason = paypalPendingReason;
        this.paypalPaymentType = paypalPaymentType;
        this.paypalFeeCharged = paypalFeeCharged;
        this.paypalCurrencyCode = paypalCurrencyCode;
        this.paypalPayerId = paypalPayerId;
        this.orderTaxAmount = orderTaxAmount;
        this.orderShippingAmount = orderShippingAmount;
        this.orderAdditionalData = orderAdditionalData;
        this.shoppingCartId = shoppingCartId;
        this.customerId = customerId;
        cartItems = new ArrayList<ShoppingCartItem>();
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getShippingId()
    {
        return shippingId;
    }

    public void setShippingId(String shippingId)
    {
        this.shippingId = shippingId;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getPaypalTransactionId()
    {
        return paypalTransactionId;
    }

    public void setPaypalTransactionId(String paypalTransactionId)
    {
        this.paypalTransactionId = paypalTransactionId;
    }

    public String getPaypalAmountBilled()
    {
        return paypalAmountBilled;
    }

    public void setPaypalAmountBilled(String paypalAmountBilled)
    {
        this.paypalAmountBilled = paypalAmountBilled;
    }

    public String getPaypalPaymentStatus()
    {
        return paypalPaymentStatus;
    }

    public void setPaypalPaymentStatus(String paypalPaymentStatus)
    {
        this.paypalPaymentStatus = paypalPaymentStatus;
    }

    public String getPaypalPendingReason()
    {
        return paypalPendingReason;
    }

    public void setPaypalPendingReason(String paypalPendingReason)
    {
        this.paypalPendingReason = paypalPendingReason;
    }

    public String getPaypalPaymentType()
    {
        return paypalPaymentType;
    }

    public void setPaypalPaymentType(String paypalPaymentType)
    {
        this.paypalPaymentType = paypalPaymentType;
    }

    public String getPaypalFeeCharged()
    {
        return paypalFeeCharged;
    }

    public void setPaypalFeeCharged(String paypalFeeCharged)
    {
        this.paypalFeeCharged = paypalFeeCharged;
    }

    public String getPaypalCurrencyCode()
    {
        return paypalCurrencyCode;
    }

    public void setPaypalCurrencyCode(String paypalCurrencyCode)
    {
        this.paypalCurrencyCode = paypalCurrencyCode;
    }

    public String getPaypalPayerId()
    {
        return paypalPayerId;
    }

    public void setPaypalPayerId(String paypalPayerId)
    {
        this.paypalPayerId = paypalPayerId;
    }

    public String getOrderTaxAmount()
    {
        return orderTaxAmount;
    }

    public void setOrderTaxAmount(String orderTaxAmount)
    {
        this.orderTaxAmount = orderTaxAmount;
    }

    public String getOrderShippingAmount()
    {
        return orderShippingAmount;
    }

    public void setOrderShippingAmount(String orderShippingAmount)
    {
        this.orderShippingAmount = orderShippingAmount;
    }

    public String getOrderAdditionalData()
    {
        return orderAdditionalData;
    }

    public void setOrderAdditionalData(String orderAdditionalData)
    {
        this.orderAdditionalData = orderAdditionalData;
    }

    public ArrayList<ShoppingCartItem> getCartItems()
    {
        return cartItems;
    }
    
    public String getCartItemsTotalCost()
    {
        double total = 0.0;
        for(ShoppingCartItem item : cartItems)
        {
            total += Double.valueOf(item.getItemQuantity()) * Double.valueOf(item.getItemUnitPrice());
        }        
        return (new DecimalFormat("####.00")).format(total);
    }

    public void setCartItems(ArrayList<ShoppingCartItem> cartItems)
    {
        this.cartItems = cartItems;
    }

    public String getShoppingCartId()
    {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId)
    {
        this.shoppingCartId = shoppingCartId;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public ShippingMethod getShippingMethod()
    {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod)
    {
        this.shippingMethod = shippingMethod;
    }
    
    
}
