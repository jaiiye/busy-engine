


































package com.busy.engine.dao;

import com.busy.engine.entity.ServiceCharge;

public interface ServiceChargeDao extends IGenericDao<ServiceCharge, Integer>
{
                    
      void getRelatedServiceList(ServiceCharge service_charge);     
        
}
    

