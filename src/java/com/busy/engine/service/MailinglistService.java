














package com.busy.engine.service;

import com.busy.engine.entity.Mailinglist;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface MailinglistService
{
      public Result<Mailinglist> find(String userName, Integer id);
      public Result<List<Mailinglist>> findAll(String userName); 
      public Result<Mailinglist> store(String userName, Integer mailinglistId, String fullName, String email, Integer listStatus, Integer userId);
      public Result<Mailinglist> remove(String userName, Integer id);
}    








