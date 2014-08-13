



















package com.busy.engine.dao;

import com.busy.engine.entity.Order;

public interface OrderDao extends IGenericDao<Order, Integer>
{
                    
      void getRelatedCustomerOrderList(Order order);     
                  
      void getRelatedRecurringPaymentList(Order order);     
                  
      void getRelatedShipmentList(Order order);     
        
}
    

