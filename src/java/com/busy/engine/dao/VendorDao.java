


































package com.busy.engine.dao;

import com.busy.engine.entity.Vendor;

public interface VendorDao extends IGenericDao<Vendor, Integer>
{

        void getRelatedMetaTag(Vendor vendor);
        void getRelatedMetaTagWithInfo(Vendor vendor);        
        
        void getRelatedTemplate(Vendor vendor);
        void getRelatedTemplateWithInfo(Vendor vendor);        
        
        void getRelatedVendorType(Vendor vendor);
        void getRelatedVendorTypeWithInfo(Vendor vendor);        
         

    void getRelatedItemList(Vendor vendor);
      
}
    
