














package com.busy.engine.service;

import com.busy.engine.entity.Customer;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface CustomerService
{
      public Result<Customer> find(String userName, Integer id);
      public Result<List<Customer>> findAll(String userName); 
      public Result<Customer> store(String userName, Integer customerId, Integer contactId, Integer userId, Integer billingAddressId, Integer shippingAddressId, Integer customerStatus);
      public Result<Customer> remove(String userName, Integer id);
}    








