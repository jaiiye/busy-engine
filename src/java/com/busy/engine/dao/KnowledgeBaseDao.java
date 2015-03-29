


































package com.busy.engine.dao;

import com.busy.engine.entity.KnowledgeBase;

public interface KnowledgeBaseDao extends IGenericDao<KnowledgeBase, Integer>
{
 

    void getRelatedBlogList(KnowledgeBase knowledge_base);
      
}
    
