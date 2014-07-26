



















package com.busy.engine.dao.base;

import com.busy.engine.domain.OrderItem;

public interface OrderItemDao extends IGenericDao<OrderItem, Integer>
{
                    
      OrderItem getRelatedReturnRequestList(OrderItem order_item);     
        
}
    

