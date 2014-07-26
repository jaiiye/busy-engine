



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Contact;

public interface ContactDao extends IGenericDao<Contact, Integer>
{
                    
      Contact getRelatedAffiliateList(Contact contact);     
                  
      Contact getRelatedCustomerList(Contact contact);     
                  
      Contact getRelatedItemLocationList(Contact contact);     
                  
      Contact getRelatedUserList(Contact contact);     
        
}
    

