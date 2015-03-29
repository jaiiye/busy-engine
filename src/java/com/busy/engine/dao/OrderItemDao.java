


































package com.busy.engine.dao;

import com.busy.engine.entity.OrderItem;

public interface OrderItemDao extends IGenericDao<OrderItem, Integer>
{

        void getRelatedCustomerOrder(OrderItem order_item);
        void getRelatedCustomerOrderWithInfo(OrderItem order_item);        
        
        void getRelatedItem(OrderItem order_item);
        void getRelatedItemWithInfo(OrderItem order_item);        
         

    void getRelatedReturnRequestList(OrderItem order_item);
      
}
    
