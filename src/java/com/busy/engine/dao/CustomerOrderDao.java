



















package com.busy.engine.dao;

import com.busy.engine.entity.CustomerOrder;

public interface CustomerOrderDao extends IGenericDao<CustomerOrder, Integer>
{
                    
      void getRelatedOrderItemList(CustomerOrder customer_order);     
        
}
    

