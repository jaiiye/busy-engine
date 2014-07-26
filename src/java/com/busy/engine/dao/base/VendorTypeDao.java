



















package com.busy.engine.dao.base;

import com.busy.engine.domain.VendorType;

public interface VendorTypeDao extends IGenericDao<VendorType, Integer>
{
                    
      VendorType getRelatedVendorList(VendorType vendor_type);     
        
}
    

