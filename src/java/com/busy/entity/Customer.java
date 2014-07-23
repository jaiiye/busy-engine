package com.busy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Customer implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_CUSTOMER_ID = "CustomerId";
    public static final String PROP_CONTACT_ID = "ContactId";
    public static final String PROP_USER_ID = "UserId";
    public static final String PROP_BILLING_ADDRESS_ID = "BillingAddressId";
    public static final String PROP_SHIPPING_ADDRESS_ID = "ShippingAddressId";
    public static final String PROP_CUSTOMER_STATUS = "CustomerStatus";

    private Integer customerId;

    private Integer contactId;
    private Contact contact;
    private Integer userId;
    private User user;
    private Integer billingAddressId;
    private Address billingAddress;
    private Integer shippingAddressId;
    private Address shippingAddress;
    private Integer customerStatus;

    ArrayList<CustomerOrder> customerOrderList;

    public Customer()
    {
        this.customerId = 0;
        this.contactId = 0;
        this.userId = 0;
        this.billingAddressId = 0;
        this.shippingAddressId = 0;
        this.customerStatus = 0;

        customerOrderList = null;

    }

    public Customer(Integer CustomerId, Integer ContactId, Integer UserId, Integer BillingAddressId, Integer ShippingAddressId, Integer CustomerStatus)
    {
        this.customerId = CustomerId;
        this.contactId = ContactId;
        this.userId = UserId;
        this.billingAddressId = BillingAddressId;
        this.shippingAddressId = ShippingAddressId;
        this.customerStatus = CustomerStatus;

        customerOrderList = null;

    }

    public Integer getCustomerId()
    {
        return this.customerId;
    }

    public void setCustomerId(Integer CustomerId)
    {
        this.customerId = CustomerId;
    }

    public Integer getContactId()
    {
        return this.contactId;
    }

    public void setContactId(Integer ContactId)
    {
        this.contactId = ContactId;
    }

    public Contact getContact()
    {
        return this.contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Integer getBillingAddressId()
    {
        return this.billingAddressId;
    }

    public void setBillingAddressId(Integer BillingAddressId)
    {
        this.billingAddressId = BillingAddressId;
    }

    public Address getBillingAddress()
    {
        return this.billingAddress;
    }

    public void setBillingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public Integer getShippingAddressId()
    {
        return this.shippingAddressId;
    }

    public void setShippingAddressId(Integer ShippingAddressId)
    {
        this.shippingAddressId = ShippingAddressId;
    }

    public Address getShippingAddress()
    {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public Integer getCustomerStatus()
    {
        return this.customerStatus;
    }

    public void setCustomerStatus(Integer CustomerStatus)
    {
        this.customerStatus = CustomerStatus;
    }

    public ArrayList<CustomerOrder> getCustomerOrderList()
    {
        return this.customerOrderList;
    }

    public void setCustomerOrderList(ArrayList<CustomerOrder> customerOrderList)
    {
        this.customerOrderList = customerOrderList;
    }

}
