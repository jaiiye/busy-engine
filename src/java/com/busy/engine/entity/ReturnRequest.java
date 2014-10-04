






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ReturnRequest extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return returnRequestId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("returnRequestId", returnRequestId == null ? 0 : returnRequestId);
                
            builder.add("quantity", quantity == null ? 0 : quantity);
                
            builder.add("requestDate", requestDate == null ? "" : new SimpleDateFormat("yyyyMMdd").format(requestDate));
                
            builder.add("returnReason", returnReason == null ? "" : returnReason);
                
            builder.add("requestedAction", requestedAction == null ? "" : requestedAction);
                
            builder.add("notes", notes == null ? "" : notes);
                
            builder.add("requestStatus", requestStatus == null ? 0 : requestStatus);
                
            builder.add("orderItemId", orderItemId == null ? 0 : orderItemId);
        
        
    
     
     
     
     
     
     
     if(orderItem != null) orderItem.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ReturnRequest.PROP_RETURN_REQUEST_ID) || column.equals(ReturnRequest.PROP_QUANTITY) || column.equals(ReturnRequest.PROP_REQUEST_DATE) || column.equals(ReturnRequest.PROP_RETURN_REASON) || column.equals(ReturnRequest.PROP_REQUESTED_ACTION) || column.equals(ReturnRequest.PROP_NOTES) || column.equals(ReturnRequest.PROP_REQUEST_STATUS) || column.equals(ReturnRequest.PROP_ORDER_ITEM_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(ReturnRequest.PROP_RETURN_REQUEST_ID) || column.equals(ReturnRequest.PROP_QUANTITY) || column.equals(ReturnRequest.PROP_REQUEST_DATE) || column.equals(ReturnRequest.PROP_RETURN_REASON) || column.equals(ReturnRequest.PROP_REQUESTED_ACTION) || column.equals(ReturnRequest.PROP_NOTES) || column.equals(ReturnRequest.PROP_REQUEST_STATUS) || column.equals(ReturnRequest.PROP_ORDER_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ReturnRequest process(ResultSet rs) throws SQLException
        {        
            return new ReturnRequest(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
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

