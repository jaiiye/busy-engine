


































package com.busy.engine.dao;

import com.busy.engine.entity.UserType;

public interface UserTypeDao extends IGenericDao<UserType, Integer>
{
                    
      void getRelatedUserList(UserType user_type);     
        
}
    

