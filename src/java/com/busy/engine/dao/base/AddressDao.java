package com.busy.engine.dao.base;

import com.busy.engine.domain.Address;

public interface AddressDao extends IGenericDao<Address, Integer>
{

    Address getRelatedAffiliateList(Address address);

    Address getRelatedItemLocationList(Address address);

    Address getRelatedUserList(Address address);

}
