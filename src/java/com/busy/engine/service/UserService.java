package com.busy.engine.service;

import com.busy.engine.entity.User;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserService
{
    public Result<User> find(String userName, Integer id);
    public Result<List<User>> findAll(String userName);
    public Result<User> store(String userName, Integer userId, String username, String password, String email, String securityQuestion, String securityAnswer, Date registerDate, String imageUrl, Integer userStatus, Integer rank, String webUrl, Integer itemBrandId, Integer userTypeId, Integer addressId, Integer contactId, Integer userGroupId);
    public Result<User> remove(String userName, Integer id);
    public Result<User> findByUsernamePassword(String username, String password);
}
