


































package com.busy.engine.dao;

import com.busy.engine.entity.StateProvince;

public interface StateProvinceDao extends IGenericDao<StateProvince, Integer>
{

        void getRelatedCountry(StateProvince state_province);
        void getRelatedCountryWithInfo(StateProvince state_province);        
         

    void getRelatedShippingList(StateProvince state_province);
    void getRelatedTaxRateList(StateProvince state_province);
      
}
    
