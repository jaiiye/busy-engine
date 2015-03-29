


































package com.busy.engine.dao;

import com.busy.engine.entity.TaxRate;

public interface TaxRateDao extends IGenericDao<TaxRate, Integer>
{

        void getRelatedStateProvince(TaxRate tax_rate);
        void getRelatedStateProvinceWithInfo(TaxRate tax_rate);        
        
        void getRelatedCountry(TaxRate tax_rate);
        void getRelatedCountryWithInfo(TaxRate tax_rate);        
         

      
}
    
