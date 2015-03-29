


































package com.busy.engine.dao;

import com.busy.engine.entity.Customer;

public interface CustomerDao extends IGenericDao<Customer, Integer>
{

        void getRelatedContact(Customer customer);
        void getRelatedContactWithInfo(Customer customer);        
        
        void getRelatedUser(Customer customer);
        void getRelatedUserWithInfo(Customer customer);        
        
        void getRelatedBillingAddress(Customer customer);
        void getRelatedBillingAddressWithInfo(Customer customer);        
        
        void getRelatedShippingAddress(Customer customer);
        void getRelatedShippingAddressWithInfo(Customer customer);        
         

    void getRelatedCustomerOrderList(Customer customer);
      
}
    
