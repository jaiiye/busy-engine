

































package com.busy.engine.service;

import com.busy.engine.entity.ReturnRequest;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ReturnRequestService
{
      public Result<ReturnRequest> find(String userName, Integer id);
      public Result<List<ReturnRequest>> findAll(String userName); 
      public Result<ReturnRequest> store(String userName, Integer returnRequestId, Integer quantity, Date requestDate, String returnReason, String requestedAction, String notes, Integer requestStatus, Integer orderItemId);
      public Result<ReturnRequest> remove(String userName, Integer id);
}    




