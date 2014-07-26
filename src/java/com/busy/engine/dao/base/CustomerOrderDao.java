



















package com.busy.engine.dao.base;

import com.busy.engine.domain.CustomerOrder;

public interface CustomerOrderDao extends IGenericDao<CustomerOrder, Integer>
{
                    
      CustomerOrder getRelatedOrderItemList(CustomerOrder customer_order);     
        
}
    

