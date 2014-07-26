



















package com.busy.engine.dao.base;

import com.busy.engine.domain.User;

public interface UserDao extends IGenericDao<User, Integer>
{
                    
      User getRelatedAffiliateList(User user);     
                  
      User getRelatedBlogPostList(User user);     
                  
      User getRelatedCommentList(User user);     
                  
      User getRelatedCustomerList(User user);     
                  
      User getRelatedMailinglistList(User user);     
                  
      User getRelatedUserActionList(User user);     
                  
      User getRelatedUserServiceList(User user);     
        
}
    

