














package com.busy.engine.service;

import com.busy.engine.entity.KnowledgeBase;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface KnowledgeBaseService
{
      public Result<KnowledgeBase> find(String userName, Integer id);
      public Result<List<KnowledgeBase>> findAll(String userName); 
      public Result<KnowledgeBase> store(String userName, Integer knowledgeBaseId, String knowledgeBaseName, String description, Integer rank, Date lastModified, Integer latestTopic, Integer latestPost);
      public Result<KnowledgeBase> remove(String userName, Integer id);
}    








