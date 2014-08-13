



















package com.busy.engine.dao;

import com.busy.engine.entity.UserGroup;

public interface UserGroupDao extends IGenericDao<UserGroup, Integer>
{
                    
      void getRelatedUserList(UserGroup user_group);     
        
}
    

