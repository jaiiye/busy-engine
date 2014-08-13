














package com.busy.engine.service;

import com.busy.engine.entity.Order;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface OrderService
{
      public Result<Order> find(String userName, Integer id);
      public Result<List<Order>> findAll(String userName); 
      public Result<Order> store(String userName, Integer orderId, Date orderDate, Date shipDate, String paymentMethod, String purchaseOrder, String transactionId, Double amountBilled, String paymentStatus, String pendingReason, String paymentType, Double transactionFee, String currencyCode, String payerId, Double subtotalAmount, Double discountAmount, Double taxAmount, Double shippingAmount, Double totalAmount, Double refundAmount, String notes, Integer orderStatus, Integer shippingId, Integer affiliateId);
      public Result<Order> remove(String userName, Integer id);
}    








