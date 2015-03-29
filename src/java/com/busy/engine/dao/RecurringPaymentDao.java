


































package com.busy.engine.dao;

import com.busy.engine.entity.RecurringPayment;

public interface RecurringPaymentDao extends IGenericDao<RecurringPayment, Integer>
{

        void getRelatedOrder(RecurringPayment recurring_payment);
        void getRelatedOrderWithInfo(RecurringPayment recurring_payment);        
         

      
}
    
