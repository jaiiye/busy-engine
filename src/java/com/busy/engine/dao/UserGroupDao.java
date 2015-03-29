


































package com.busy.engine.dao;

import com.busy.engine.entity.UserGroup;

public interface UserGroupDao extends IGenericDao<UserGroup, Integer>
{

        void getRelatedSite(UserGroup user_group);
        void getRelatedSiteWithInfo(UserGroup user_group);        
        
        void getRelatedDiscount(UserGroup user_group);
        void getRelatedDiscountWithInfo(UserGroup user_group);        
         

    void getRelatedUserList(UserGroup user_group);
      
}
    
