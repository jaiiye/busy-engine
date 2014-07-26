



















package com.busy.engine.dao.base;

import com.busy.engine.domain.UserActionType;

public interface UserActionTypeDao extends IGenericDao<UserActionType, Integer>
{
                    
      UserActionType getRelatedUserActionList(UserActionType user_action_type);     
        
}
    

