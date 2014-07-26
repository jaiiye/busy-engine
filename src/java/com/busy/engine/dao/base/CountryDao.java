



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Country;

public interface CountryDao extends IGenericDao<Country, Integer>
{
                    
      Country getRelatedShippingList(Country country);     
                  
      Country getRelatedStateProvinceList(Country country);     
                  
      Country getRelatedTaxRateList(Country country);     
        
}
    

