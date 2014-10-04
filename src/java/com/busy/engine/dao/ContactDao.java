


































package com.busy.engine.dao;

import com.busy.engine.entity.Contact;

public interface ContactDao extends IGenericDao<Contact, Integer>
{
                    
      void getRelatedAffiliateList(Contact contact);     
                  
      void getRelatedCustomerList(Contact contact);     
                  
      void getRelatedItemLocationList(Contact contact);     
                  
      void getRelatedUserList(Contact contact);     
        
}
    

