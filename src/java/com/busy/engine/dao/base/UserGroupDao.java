



















package com.busy.engine.dao.base;

import com.busy.engine.domain.UserGroup;

public interface UserGroupDao extends IGenericDao<UserGroup, Integer>
{
                    
      UserGroup getRelatedUserList(UserGroup user_group);     
        
}
    

