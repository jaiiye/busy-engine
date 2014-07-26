



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ServiceType;

public interface ServiceTypeDao extends IGenericDao<ServiceType, Integer>
{
                    
      ServiceType getRelatedServiceList(ServiceType service_type);     
        
}
    

