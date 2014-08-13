



















package com.busy.engine.dao;

import com.busy.engine.entity.Affiliate;

public interface AffiliateDao extends IGenericDao<Affiliate, Integer>
{
                    
      void getRelatedOrderList(Affiliate affiliate);     
        
}
    

