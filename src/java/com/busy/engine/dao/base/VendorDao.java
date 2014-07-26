



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Vendor;

public interface VendorDao extends IGenericDao<Vendor, Integer>
{
                    
      Vendor getRelatedItemList(Vendor vendor);     
        
}
    

