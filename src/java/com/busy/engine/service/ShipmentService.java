

































package com.busy.engine.service;

import com.busy.engine.entity.Shipment;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ShipmentService
{
      public Result<Shipment> find(String userName, Integer id);
      public Result<List<Shipment>> findAll(String userName); 
      public Result<Shipment> store(String userName, Integer shipmentId, Date createdOn, String trackingNumber, Double totalWeight, Date shipDate, Date deliveryDate, Integer itemQuantity, Integer orderId);
      public Result<Shipment> remove(String userName, Integer id);
}    




