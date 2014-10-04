


































package com.busy.engine.dao;

import com.busy.engine.entity.Discount;

public interface DiscountDao extends IGenericDao<Discount, Integer>
{
                    
      void getRelatedCategoryList(Discount discount);     
                  
      void getRelatedCustomerOrderList(Discount discount);     
                  
      void getRelatedItemDiscountList(Discount discount);     
                  
      void getRelatedUserGroupList(Discount discount);     
        
}
    

