


































package com.busy.engine.dao;

import com.busy.engine.entity.ReturnRequest;

public interface ReturnRequestDao extends IGenericDao<ReturnRequest, Integer>
{

        void getRelatedOrderItem(ReturnRequest return_request);
        void getRelatedOrderItemWithInfo(ReturnRequest return_request);        
         

      
}
    
