



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Discount;

public interface DiscountDao extends IGenericDao<Discount, Integer>
{
                    
      Discount getRelatedCategoryList(Discount discount);     
                  
      Discount getRelatedCustomerOrderList(Discount discount);     
                  
      Discount getRelatedItemDiscountList(Discount discount);     
                  
      Discount getRelatedUserGroupList(Discount discount);     
        
}
    

