














package com.busy.engine.service;

import com.busy.engine.entity.Paypal;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface PaypalService
{
      public Result<Paypal> find(String userName, Integer id);
      public Result<List<Paypal>> findAll(String userName); 
      public Result<Paypal> store(String userName, Integer paypalId, String payPalUrl, String currencyCode, String apiUsername, String apiPassword, String apiSignature, String apiEndpoint, Boolean activeProfile, String returnUrl, String cancelUrl, String paymentType, String environment);
      public Result<Paypal> remove(String userName, Integer id);
}    








