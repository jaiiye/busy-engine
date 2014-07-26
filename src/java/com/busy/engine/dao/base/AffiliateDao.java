



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Affiliate;

public interface AffiliateDao extends IGenericDao<Affiliate, Integer>
{
                    
      Affiliate getRelatedOrderList(Affiliate affiliate);     
        
}
    

