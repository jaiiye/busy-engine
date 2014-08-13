package com.busy.engine.dao;

import com.busy.engine.entity.Address;

public interface AddressDao extends IGenericDao<Address, Integer>
{

    void getRelatedAffiliateList(Address address);

    void getRelatedItemLocationList(Address address);

    void getRelatedUserList(Address address);

}
