


































package com.busy.engine.dao;

import com.busy.engine.entity.Vendor;

public interface VendorDao extends IGenericDao<Vendor, Integer>
{
                    
      void getRelatedItemList(Vendor vendor);     
        
}
    

