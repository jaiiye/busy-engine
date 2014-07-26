



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ServiceCharge;

public interface ServiceChargeDao extends IGenericDao<ServiceCharge, Integer>
{
                    
      ServiceCharge getRelatedServiceList(ServiceCharge service_charge);     
        
}
    

