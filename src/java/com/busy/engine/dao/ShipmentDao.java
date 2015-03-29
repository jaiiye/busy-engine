


































package com.busy.engine.dao;

import com.busy.engine.entity.Shipment;

public interface ShipmentDao extends IGenericDao<Shipment, Integer>
{

        void getRelatedOrder(Shipment shipment);
        void getRelatedOrderWithInfo(Shipment shipment);        
         

      
}
    
