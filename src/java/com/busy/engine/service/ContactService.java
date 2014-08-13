














package com.busy.engine.service;

import com.busy.engine.entity.Contact;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ContactService
{
      public Result<Contact> find(String userName, Integer id);
      public Result<List<Contact>> findAll(String userName); 
      public Result<Contact> store(String userName, Integer contactId, String title, String firstName, String lastName, String position, String phone, String fax, String email, Integer contactStatus, String webUrl, String info);
      public Result<Contact> remove(String userName, Integer id);
}    








