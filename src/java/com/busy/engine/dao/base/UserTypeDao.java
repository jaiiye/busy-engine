



















package com.busy.engine.dao.base;

import com.busy.engine.domain.UserType;

public interface UserTypeDao extends IGenericDao<UserType, Integer>
{
                    
      UserType getRelatedUserList(UserType user_type);     
        
}
    

