



















package com.busy.engine.dao.base;

import com.busy.engine.domain.KnowledgeBase;

public interface KnowledgeBaseDao extends IGenericDao<KnowledgeBase, Integer>
{
                    
      KnowledgeBase getRelatedBlogList(KnowledgeBase knowledge_base);     
        
}
    

