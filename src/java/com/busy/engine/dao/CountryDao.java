


































package com.busy.engine.dao;

import com.busy.engine.entity.Country;

public interface CountryDao extends IGenericDao<Country, Integer>
{
                    
      void getRelatedShippingList(Country country);     
                  
      void getRelatedStateProvinceList(Country country);     
                  
      void getRelatedTaxRateList(Country country);     
        
}
    

