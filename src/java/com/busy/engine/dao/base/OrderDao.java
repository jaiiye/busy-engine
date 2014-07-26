



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Order;

public interface OrderDao extends IGenericDao<Order, Integer>
{
                    
      Order getRelatedCustomerOrderList(Order order);     
                  
      Order getRelatedRecurringPaymentList(Order order);     
                  
      Order getRelatedShipmentList(Order order);     
        
}
    

