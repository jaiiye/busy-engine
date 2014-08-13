package com.busy.engine.dao;

import com.busy.engine.entity.User;

public interface UserDao extends IGenericDao<User, Integer>
{

    void getRelatedAffiliateList(User user);

    void getRelatedBlogPostList(User user);

    void getRelatedCommentList(User user);

    void getRelatedCustomerList(User user);

    void getRelatedMailinglistList(User user);

    void getRelatedUserActionList(User user);

    void getRelatedUserServiceList(User user);

}
