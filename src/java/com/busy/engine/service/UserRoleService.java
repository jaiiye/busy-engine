package com.busy.engine.service;

import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserRoleService
{
    public Result<UserRole> find(String userName, String roleName);
    public Result<List<UserRole>> findAll(String userName);
    public Result<UserRole> store(String userName, String roleName);
    public Result<UserRole> remove(String userName, String roleName);
}
