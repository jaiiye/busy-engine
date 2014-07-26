



















package com.busy.engine.dao.base;

import com.busy.engine.domain.StateProvince;

public interface StateProvinceDao extends IGenericDao<StateProvince, Integer>
{
                    
      StateProvince getRelatedShippingList(StateProvince state_province);     
                  
      StateProvince getRelatedTaxRateList(StateProvince state_province);     
        
}
    

