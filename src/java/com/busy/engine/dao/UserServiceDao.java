


































package com.busy.engine.dao;

import com.busy.engine.entity.UserService;

public interface UserServiceDao extends IGenericDao<UserService, Integer>
{

        void getRelatedBlog(UserService user_service);
        void getRelatedBlogWithInfo(UserService user_service);        
        
        void getRelatedUser(UserService user_service);
        void getRelatedUserWithInfo(UserService user_service);        
        
        void getRelatedService(UserService user_service);
        void getRelatedServiceWithInfo(UserService user_service);        
         

    void getRelatedServiceChargeList(UserService user_service);
      
}
    
