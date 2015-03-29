


































package com.busy.engine.dao;

import com.busy.engine.entity.Mailinglist;

public interface MailinglistDao extends IGenericDao<Mailinglist, Integer>
{

        void getRelatedUser(Mailinglist mailinglist);
        void getRelatedUserWithInfo(Mailinglist mailinglist);        
         

      
}
    
