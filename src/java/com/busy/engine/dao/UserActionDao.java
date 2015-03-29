


































package com.busy.engine.dao;

import com.busy.engine.entity.UserAction;

public interface UserActionDao extends IGenericDao<UserAction, Integer>
{

        void getRelatedUserActionType(UserAction user_action);
        void getRelatedUserActionTypeWithInfo(UserAction user_action);        
        
        void getRelatedUser(UserAction user_action);
        void getRelatedUserWithInfo(UserAction user_action);        
         

      
}
    
