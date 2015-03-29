


































package com.busy.engine.dao;

import com.busy.engine.entity.Shipping;

public interface ShippingDao extends IGenericDao<Shipping, Integer>
{

        void getRelatedStateProvince(Shipping shipping);
        void getRelatedStateProvinceWithInfo(Shipping shipping);        
        
        void getRelatedCountry(Shipping shipping);
        void getRelatedCountryWithInfo(Shipping shipping);        
         

    void getRelatedOrderList(Shipping shipping);
      
}
    
