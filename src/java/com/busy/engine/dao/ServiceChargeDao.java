


































package com.busy.engine.dao;

import com.busy.engine.entity.ServiceCharge;

public interface ServiceChargeDao extends IGenericDao<ServiceCharge, Integer>
{

        void getRelatedUserService(ServiceCharge service_charge);
        void getRelatedUserServiceWithInfo(ServiceCharge service_charge);        
         

    void getRelatedServiceList(ServiceCharge service_charge);
      
}
    
