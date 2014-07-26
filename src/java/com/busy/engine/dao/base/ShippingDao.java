



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Shipping;

public interface ShippingDao extends IGenericDao<Shipping, Integer>
{
                    
      Shipping getRelatedOrderList(Shipping shipping);     
        
}
    

