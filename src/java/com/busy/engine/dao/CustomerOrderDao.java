


































package com.busy.engine.dao;

import com.busy.engine.entity.CustomerOrder;

public interface CustomerOrderDao extends IGenericDao<CustomerOrder, Integer>
{

        void getRelatedCustomer(CustomerOrder customer_order);
        void getRelatedCustomerWithInfo(CustomerOrder customer_order);        
        
        void getRelatedOrder(CustomerOrder customer_order);
        void getRelatedOrderWithInfo(CustomerOrder customer_order);        
        
        void getRelatedDiscount(CustomerOrder customer_order);
        void getRelatedDiscountWithInfo(CustomerOrder customer_order);        
         

    void getRelatedOrderItemList(CustomerOrder customer_order);
      
}
    
