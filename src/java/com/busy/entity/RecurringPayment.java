


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class RecurringPayment implements Serializable
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
        

        public RecurringPayment()
        {
            this.recurringPaymentId = 0; 
       this.cycleLength = 0; 
       this.cyclePeriod = 0; 
       this.totalCycles = 0; 
       this.startDate = null; 
       this.orderId = 0; 
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
           
            
    }

