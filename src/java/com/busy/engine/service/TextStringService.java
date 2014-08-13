














package com.busy.engine.service;

import com.busy.engine.entity.TextString;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface TextStringService
{
      public Result<TextString> find(String userName, Integer id);
      public Result<List<TextString>> findAll(String userName); 
      public Result<TextString> store(String userName, Integer textStringId, String key);
      public Result<TextString> remove(String userName, Integer id);
}    








