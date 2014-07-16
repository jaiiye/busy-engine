


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Discount implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_DISCOUNT_ID = "DiscountId";
        public static final String PROP_DISCOUNT_NAME = "DiscountName";
        public static final String PROP_DISCOUNT_AMOUNT = "DiscountAmount";
        public static final String PROP_DISCOUNT_PERCENT = "DiscountPercent";
        public static final String PROP_START_DATE = "StartDate";
        public static final String PROP_END_DATE = "EndDate";
        public static final String PROP_COUPON_CODE = "CouponCode";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_ASK_COUPON_CODE = "AskCouponCode";
        public static final String PROP_USE_PERCENTAGE = "UsePercentage";
        

        private Integer discountId;
        private String discountName;
        private Double discountAmount;
        private Double discountPercent;
        private Date startDate;
        private Date endDate;
        private String couponCode;
        private Integer status;
        private Boolean askCouponCode;
        private Boolean usePercentage;
        

        public Discount()
        {
            this.discountId = 0; 
       this.discountName = ""; 
       this.discountAmount = 0.0; 
       this.discountPercent = 0.0; 
       this.startDate = null; 
       this.endDate = null; 
       this.couponCode = ""; 
       this.status = 0; 
       this.askCouponCode = null; 
       this.usePercentage = null; 
        }
        
        public Discount(Integer DiscountId, String DiscountName, Double DiscountAmount, Double DiscountPercent, Date StartDate, Date EndDate, String CouponCode, Integer Status, Boolean AskCouponCode, Boolean UsePercentage)
        {
            this.discountId = DiscountId;
       this.discountName = DiscountName;
       this.discountAmount = DiscountAmount;
       this.discountPercent = DiscountPercent;
       this.startDate = StartDate;
       this.endDate = EndDate;
       this.couponCode = CouponCode;
       this.status = Status;
       this.askCouponCode = AskCouponCode;
       this.usePercentage = UsePercentage;
        } 
        
             
        
            public Integer getDiscountId()
            {
                return this.discountId;
            }
            
            public void setDiscountId(Integer DiscountId)
            {
                this.discountId = DiscountId;
            }
        
            public String getDiscountName()
            {
                return this.discountName;
            }
            
            public void setDiscountName(String DiscountName)
            {
                this.discountName = DiscountName;
            }
        
            public Double getDiscountAmount()
            {
                return this.discountAmount;
            }
            
            public void setDiscountAmount(Double DiscountAmount)
            {
                this.discountAmount = DiscountAmount;
            }
        
            public Double getDiscountPercent()
            {
                return this.discountPercent;
            }
            
            public void setDiscountPercent(Double DiscountPercent)
            {
                this.discountPercent = DiscountPercent;
            }
        
            public Date getStartDate()
            {
                return this.startDate;
            }
            
            public void setStartDate(Date StartDate)
            {
                this.startDate = StartDate;
            }
        
            public Date getEndDate()
            {
                return this.endDate;
            }
            
            public void setEndDate(Date EndDate)
            {
                this.endDate = EndDate;
            }
        
            public String getCouponCode()
            {
                return this.couponCode;
            }
            
            public void setCouponCode(String CouponCode)
            {
                this.couponCode = CouponCode;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Boolean getAskCouponCode()
            {
                return this.askCouponCode;
            }
            
            public void setAskCouponCode(Boolean AskCouponCode)
            {
                this.askCouponCode = AskCouponCode;
            }
        
            public Boolean getUsePercentage()
            {
                return this.usePercentage;
            }
            
            public void setUsePercentage(Boolean UsePercentage)
            {
                this.usePercentage = UsePercentage;
            }
           
            
    }

