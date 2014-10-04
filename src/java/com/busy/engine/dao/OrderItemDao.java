


































package com.busy.engine.dao;

import com.busy.engine.entity.OrderItem;

public interface OrderItemDao extends IGenericDao<OrderItem, Integer>
{
                    
      void getRelatedReturnRequestList(OrderItem order_item);     
        
}
    

