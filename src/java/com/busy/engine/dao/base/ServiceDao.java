



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Service;

public interface ServiceDao extends IGenericDao<Service, Integer>
{
                    
      Service getRelatedUserServiceList(Service service);     
        
}
    

