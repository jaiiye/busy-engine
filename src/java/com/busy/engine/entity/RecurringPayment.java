






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class RecurringPayment extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RECURRING_PAYMENT_ID = "RecurringPaymentId";
        public static final String PROP_CYCLE_LENGTH = "CycleLength";
        public static final String PROP_CYCLE_PERIOD = "CyclePeriod";
        public static final String PROP_TOTAL_CYCLES = "TotalCycles";
        public static final String PROP_START_DATE = "StartDate";
        public static final String PROP_ORDER_ID = "OrderId";
        

        private Integer recurringPaymentId;
                
        private Integer cycleLength;
                
        private Integer cyclePeriod;
                
        private Integer totalCycles;
                
        private Date startDate;
                
        private Integer orderId;
        private Order order;        
                 
        
        

        public RecurringPayment()
        {
            this.recurringPaymentId = 0; 
       this.cycleLength = 0; 
       this.cyclePeriod = 0; 
       this.totalCycles = 0; 
       this.startDate = null; 
       this.orderId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return recurringPaymentId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("recurringPaymentId", recurringPaymentId == null ? 0 : recurringPaymentId);
                
            builder.add("cycleLength", cycleLength == null ? 0 : cycleLength);
                
            builder.add("cyclePeriod", cyclePeriod == null ? 0 : cyclePeriod);
                
            builder.add("totalCycles", totalCycles == null ? 0 : totalCycles);
                
            builder.add("startDate", startDate == null ? "" : new SimpleDateFormat("yyyyMMdd").format(startDate));
                
            builder.add("orderId", orderId == null ? 0 : orderId);
        
        
    
     
     
     
     
     if(order != null) order.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(RecurringPayment.PROP_RECURRING_PAYMENT_ID) || column.equals(RecurringPayment.PROP_CYCLE_LENGTH) || column.equals(RecurringPayment.PROP_CYCLE_PERIOD) || column.equals(RecurringPayment.PROP_TOTAL_CYCLES) || column.equals(RecurringPayment.PROP_START_DATE) || column.equals(RecurringPayment.PROP_ORDER_ID) )
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
            if (column.equals(RecurringPayment.PROP_RECURRING_PAYMENT_ID) || column.equals(RecurringPayment.PROP_CYCLE_LENGTH) || column.equals(RecurringPayment.PROP_CYCLE_PERIOD) || column.equals(RecurringPayment.PROP_TOTAL_CYCLES) || column.equals(RecurringPayment.PROP_START_DATE) || column.equals(RecurringPayment.PROP_ORDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static RecurringPayment process(ResultSet rs) throws SQLException
        {        
            return new RecurringPayment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getInt(6));
        }
              
       public RecurringPayment(Integer RecurringPaymentId, Integer CycleLength, Integer CyclePeriod, Integer TotalCycles, Date StartDate, Integer OrderId)
       {
            this.recurringPaymentId = RecurringPaymentId;
       this.cycleLength = CycleLength;
       this.cyclePeriod = CyclePeriod;
       this.totalCycles = TotalCycles;
       this.startDate = StartDate;
       this.orderId = OrderId;
              
       
       } 
        
             
        
            public Integer getRecurringPaymentId()
            {
                return this.recurringPaymentId;
            }
            
            public void setRecurringPaymentId(Integer RecurringPaymentId)
            {
                this.recurringPaymentId = RecurringPaymentId;
            }
            
            
        
            public Integer getCycleLength()
            {
                return this.cycleLength;
            }
            
            public void setCycleLength(Integer CycleLength)
            {
                this.cycleLength = CycleLength;
            }
            
            
        
            public Integer getCyclePeriod()
            {
                return this.cyclePeriod;
            }
            
            public void setCyclePeriod(Integer CyclePeriod)
            {
                this.cyclePeriod = CyclePeriod;
            }
            
            
        
            public Integer getTotalCycles()
            {
                return this.totalCycles;
            }
            
            public void setTotalCycles(Integer TotalCycles)
            {
                this.totalCycles = TotalCycles;
            }
            
            
        
            public Date getStartDate()
            {
                return this.startDate;
            }
            
            public void setStartDate(Date StartDate)
            {
                this.startDate = StartDate;
            }
            
            
        
            public Integer getOrderId()
            {
                return this.orderId;
            }
            
            public void setOrderId(Integer OrderId)
            {
                this.orderId = OrderId;
            }
            
            
                   
            public Order getOrder()
                {
                    return this.order;
                }

                public void setOrder(Order order)
                {
                    this.order = order;
                }
                   
            
         
        
        
            
    }

