


































package com.busy.engine.dao;

import com.busy.engine.entity.Service;

public interface ServiceDao extends IGenericDao<Service, Integer>
{
                    
      void getRelatedUserServiceList(Service service);     
        
}
    

