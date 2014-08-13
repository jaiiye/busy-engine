



















package com.busy.engine.dao;

import com.busy.engine.entity.UserActionType;

public interface UserActionTypeDao extends IGenericDao<UserActionType, Integer>
{
                    
      void getRelatedUserActionList(UserActionType user_action_type);     
        
}
    

