


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemFile;

public interface ItemFileDao extends IGenericDao<ItemFile, Integer>
{

        void getRelatedItem(ItemFile item_file);
        void getRelatedItemWithInfo(ItemFile item_file);        
         

      
}
    
