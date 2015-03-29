


































package com.busy.engine.dao;

import com.busy.engine.entity.User;

public interface UserDao extends IGenericDao<User, Integer>
{

        void getRelatedItemBrand(User user);
        void getRelatedItemBrandWithInfo(User user);        
        
        void getRelatedUserType(User user);
        void getRelatedUserTypeWithInfo(User user);        
        
        void getRelatedAddress(User user);
        void getRelatedAddressWithInfo(User user);        
        
        void getRelatedContact(User user);
        void getRelatedContactWithInfo(User user);        
        
        void getRelatedUserGroup(User user);
        void getRelatedUserGroupWithInfo(User user);        
         

    void getRelatedAffiliateList(User user);
    void getRelatedBlogPostList(User user);
    void getRelatedCommentList(User user);
    void getRelatedCustomerList(User user);
    void getRelatedMailinglistList(User user);
    void getRelatedUserActionList(User user);
    void getRelatedUserServiceList(User user);
      
}
    
