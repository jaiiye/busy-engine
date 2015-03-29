


































package com.busy.engine.dao;

import com.busy.engine.entity.Affiliate;

public interface AffiliateDao extends IGenericDao<Affiliate, Integer>
{

        void getRelatedUser(Affiliate affiliate);
        void getRelatedUserWithInfo(Affiliate affiliate);        
        
        void getRelatedContact(Affiliate affiliate);
        void getRelatedContactWithInfo(Affiliate affiliate);        
        
        void getRelatedAddress(Affiliate affiliate);
        void getRelatedAddressWithInfo(Affiliate affiliate);        
         

    void getRelatedOrderList(Affiliate affiliate);
      
}
    
