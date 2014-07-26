



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Customer;

public interface CustomerDao extends IGenericDao<Customer, Integer>
{
                    
      Customer getRelatedCustomerOrderList(Customer customer);     
        
}
    

