


































package com.busy.engine.dao;

import com.busy.engine.entity.Customer;

public interface CustomerDao extends IGenericDao<Customer, Integer>
{
                    
      void getRelatedCustomerOrderList(Customer customer);     
        
}
    

