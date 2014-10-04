

































package com.busy.engine.service;

import com.busy.engine.entity.Address;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface AddressService
{
      public Result<Address> find(String userName, Integer id);
      public Result<List<Address>> findAll(String userName); 
      public Result<Address> store(String userName, Integer addressId, String recipient, String address1, String address2, String city, String stateProvince, String zipPostalCode, String country, String region, Integer addressStatus, String locale);
      public Result<Address> remove(String userName, Integer id);
}    




