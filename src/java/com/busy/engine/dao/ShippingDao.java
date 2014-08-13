



















package com.busy.engine.dao;

import com.busy.engine.entity.Shipping;

public interface ShippingDao extends IGenericDao<Shipping, Integer>
{
                    
      void getRelatedOrderList(Shipping shipping);     
        
}
    

