


































package com.busy.engine.dao;

import com.busy.engine.entity.VendorType;

public interface VendorTypeDao extends IGenericDao<VendorType, Integer>
{
                    
      void getRelatedVendorList(VendorType vendor_type);     
        
}
    

