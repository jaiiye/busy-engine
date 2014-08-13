



















package com.busy.engine.dao;

import com.busy.engine.entity.ServiceType;

public interface ServiceTypeDao extends IGenericDao<ServiceType, Integer>
{
                    
      void getRelatedServiceList(ServiceType service_type);     
        
}
    

