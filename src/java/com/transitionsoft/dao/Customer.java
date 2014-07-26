package com.transitionsoft.dao;

import com.busy.engine.domain.Address;
import java.io.Serializable;

public class Customer implements Serializable
{
    private static final long serialVersionUId = 1L;

    private int custId;
    private Contact contact;
    private Address address;

    public Customer()
    {
    }

    public Customer(int custId)
    {
        this.custId = custId;
    }
    
    public Customer(int custId, Contact con, Address addr )
    {
        this.custId = custId;
        contact = con;
        address = addr;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Contact getContact()
    {
        return contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        return "com.transitionsoft.Customer[custId=" + custId + "]";
    }


}
