


































package com.busy.engine.dao;

import com.busy.engine.entity.Service;

public interface ServiceDao extends IGenericDao<Service, Integer>
{

        void getRelatedServiceCharge(Service service);
        void getRelatedServiceChargeWithInfo(Service service);        
        
        void getRelatedServiceType(Service service);
        void getRelatedServiceTypeWithInfo(Service service);        
         

    void getRelatedUserServiceList(Service service);
      
}
    
