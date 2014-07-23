











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ReturnRequest implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RETURN_REQUEST_ID = "ReturnRequestId";
        public static final String PROP_QUANTITY = "Quantity";
        public static final String PROP_REQUEST_DATE = "RequestDate";
        public static final String PROP_RETURN_REASON = "ReturnReason";
        public static final String PROP_REQUESTED_ACTION = "RequestedAction";
        public static final String PROP_NOTES = "Notes";
        public static final String PROP_REQUEST_STATUS = "RequestStatus";
        public static final String PROP_ORDER_ITEM_ID = "OrderItemId";
        

        private Integer returnRequestId;
                
        private Integer quantity;
                
        private Date requestDate;
                
        private String returnReason;
                
        private String requestedAction;
                
        private String notes;
                
        private Integer requestStatus;
                
        private Integer orderItemId;
        private OrderItem orderItem;        
                 
        
        

        public ReturnRequest()
        {
            this.returnRequestId = 0; 
       this.quantity = 0; 
       this.requestDate = null; 
       this.returnReason = ""; 
       this.requestedAction = ""; 
       this.notes = ""; 
       this.requestStatus = 0; 
       this.orderItemId = 0; 
        
       
       }
        
        public ReturnRequest(Integer ReturnRequestId, Integer Quantity, Date RequestDate, String ReturnReason, String RequestedAction, String Notes, Integer RequestStatus, Integer OrderItemId)
        {
            this.returnRequestId = ReturnRequestId;
       this.quantity = Quantity;
       this.requestDate = RequestDate;
       this.returnReason = ReturnReason;
       this.requestedAction = RequestedAction;
       this.notes = Notes;
       this.requestStatus = RequestStatus;
       this.orderItemId = OrderItemId;
              
       
       } 
        
             
        
            public Integer getReturnRequestId()
            {
                return this.returnRequestId;
            }
            
            public void setReturnRequestId(Integer ReturnRequestId)
            {
                this.returnRequestId = ReturnRequestId;
            }
            
            
        
            public Integer getQuantity()
            {
                return this.quantity;
            }
            
            public void setQuantity(Integer Quantity)
            {
                this.quantity = Quantity;
            }
            
            
        
            public Date getRequestDate()
            {
                return this.requestDate;
            }
            
            public void setRequestDate(Date RequestDate)
            {
                this.requestDate = RequestDate;
            }
            
            
        
            public String getReturnReason()
            {
                return this.returnReason;
            }
            
            public void setReturnReason(String ReturnReason)
            {
                this.returnReason = ReturnReason;
            }
            
            
        
            public String getRequestedAction()
            {
                return this.requestedAction;
            }
            
            public void setRequestedAction(String RequestedAction)
            {
                this.requestedAction = RequestedAction;
            }
            
            
        
            public String getNotes()
            {
                return this.notes;
            }
            
            public void setNotes(String Notes)
            {
                this.notes = Notes;
            }
            
            
        
            public Integer getRequestStatus()
            {
                return this.requestStatus;
            }
            
            public void setRequestStatus(Integer RequestStatus)
            {
                this.requestStatus = RequestStatus;
            }
            
            
        
            public Integer getOrderItemId()
            {
                return this.orderItemId;
            }
            
            public void setOrderItemId(Integer OrderItemId)
            {
                this.orderItemId = OrderItemId;
            }
            
            
                   
            public OrderItem getOrderItem()
                {
                    return this.orderItem;
                }

                public void setOrderItem(OrderItem orderItem)
                {
                    this.orderItem = orderItem;
                }
                   
            
         
        
        
            
    }

