


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Order implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ORDER_ID = "OrderId";
        public static final String PROP_ORDER_DATE = "OrderDate";
        public static final String PROP_SHIP_DATE = "ShipDate";
        public static final String PROP_PAYMENT_METHOD = "PaymentMethod";
        public static final String PROP_PURCHASE_ORDER = "PurchaseOrder";
        public static final String PROP_TRANSACTION_ID = "TransactionId";
        public static final String PROP_AMOUNT_BILLED = "AmountBilled";
        public static final String PROP_PAYMENT_STATUS = "PaymentStatus";
        public static final String PROP_PENDING_REASON = "PendingReason";
        public static final String PROP_PAYMENT_TYPE = "PaymentType";
        public static final String PROP_TRANSACTION_FEE = "TransactionFee";
        public static final String PROP_CURRENCY_CODE = "CurrencyCode";
        public static final String PROP_PAYER_ID = "PayerId";
        public static final String PROP_SUBTOTAL_AMOUNT = "SubtotalAmount";
        public static final String PROP_DISCOUNT_AMOUNT = "DiscountAmount";
        public static final String PROP_TAX_AMOUNT = "TaxAmount";
        public static final String PROP_SHIPPING_AMOUNT = "ShippingAmount";
        public static final String PROP_TOTAL_AMOUNT = "TotalAmount";
        public static final String PROP_REFUND_AMOUNT = "RefundAmount";
        public static final String PROP_NOTES = "Notes";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_SHIPPING_ID = "ShippingId";
        public static final String PROP_AFFILIATE_ID = "AffiliateId";
        

        private Integer orderId;
        private Date orderDate;
        private Date shipDate;
        private String paymentMethod;
        private String purchaseOrder;
        private String transactionId;
        private Double amountBilled;
        private String paymentStatus;
        private String pendingReason;
        private String paymentType;
        private Double transactionFee;
        private String currencyCode;
        private String payerId;
        private Double subtotalAmount;
        private Double discountAmount;
        private Double taxAmount;
        private Double shippingAmount;
        private Double totalAmount;
        private Double refundAmount;
        private String notes;
        private Integer status;
        private Integer shippingId;
        private Integer affiliateId;
        

        public Order()
        {
            this.orderId = 0; 
       this.orderDate = null; 
       this.shipDate = null; 
       this.paymentMethod = ""; 
       this.purchaseOrder = ""; 
       this.transactionId = ""; 
       this.amountBilled = 0.0; 
       this.paymentStatus = ""; 
       this.pendingReason = ""; 
       this.paymentType = ""; 
       this.transactionFee = 0.0; 
       this.currencyCode = ""; 
       this.payerId = ""; 
       this.subtotalAmount = 0.0; 
       this.discountAmount = 0.0; 
       this.taxAmount = 0.0; 
       this.shippingAmount = 0.0; 
       this.totalAmount = 0.0; 
       this.refundAmount = 0.0; 
       this.notes = ""; 
       this.status = 0; 
       this.shippingId = 0; 
       this.affiliateId = 0; 
        }
        
        public Order(Integer OrderId, Date OrderDate, Date ShipDate, String PaymentMethod, String PurchaseOrder, String TransactionId, Double AmountBilled, String PaymentStatus, String PendingReason, String PaymentType, Double TransactionFee, String CurrencyCode, String PayerId, Double SubtotalAmount, Double DiscountAmount, Double TaxAmount, Double ShippingAmount, Double TotalAmount, Double RefundAmount, String Notes, Integer Status, Integer ShippingId, Integer AffiliateId)
        {
            this.orderId = OrderId;
       this.orderDate = OrderDate;
       this.shipDate = ShipDate;
       this.paymentMethod = PaymentMethod;
       this.purchaseOrder = PurchaseOrder;
       this.transactionId = TransactionId;
       this.amountBilled = AmountBilled;
       this.paymentStatus = PaymentStatus;
       this.pendingReason = PendingReason;
       this.paymentType = PaymentType;
       this.transactionFee = TransactionFee;
       this.currencyCode = CurrencyCode;
       this.payerId = PayerId;
       this.subtotalAmount = SubtotalAmount;
       this.discountAmount = DiscountAmount;
       this.taxAmount = TaxAmount;
       this.shippingAmount = ShippingAmount;
       this.totalAmount = TotalAmount;
       this.refundAmount = RefundAmount;
       this.notes = Notes;
       this.status = Status;
       this.shippingId = ShippingId;
       this.affiliateId = AffiliateId;
        } 
        
             
        
            public Integer getOrderId()
            {
                return this.orderId;
            }
            
            public void setOrderId(Integer OrderId)
            {
                this.orderId = OrderId;
            }
        
            public Date getOrderDate()
            {
                return this.orderDate;
            }
            
            public void setOrderDate(Date OrderDate)
            {
                this.orderDate = OrderDate;
            }
        
            public Date getShipDate()
            {
                return this.shipDate;
            }
            
            public void setShipDate(Date ShipDate)
            {
                this.shipDate = ShipDate;
            }
        
            public String getPaymentMethod()
            {
                return this.paymentMethod;
            }
            
            public void setPaymentMethod(String PaymentMethod)
            {
                this.paymentMethod = PaymentMethod;
            }
        
            public String getPurchaseOrder()
            {
                return this.purchaseOrder;
            }
            
            public void setPurchaseOrder(String PurchaseOrder)
            {
                this.purchaseOrder = PurchaseOrder;
            }
        
            public String getTransactionId()
            {
                return this.transactionId;
            }
            
            public void setTransactionId(String TransactionId)
            {
                this.transactionId = TransactionId;
            }
        
            public Double getAmountBilled()
            {
                return this.amountBilled;
            }
            
            public void setAmountBilled(Double AmountBilled)
            {
                this.amountBilled = AmountBilled;
            }
        
            public String getPaymentStatus()
            {
                return this.paymentStatus;
            }
            
            public void setPaymentStatus(String PaymentStatus)
            {
                this.paymentStatus = PaymentStatus;
            }
        
            public String getPendingReason()
            {
                return this.pendingReason;
            }
            
            public void setPendingReason(String PendingReason)
            {
                this.pendingReason = PendingReason;
            }
        
            public String getPaymentType()
            {
                return this.paymentType;
            }
            
            public void setPaymentType(String PaymentType)
            {
                this.paymentType = PaymentType;
            }
        
            public Double getTransactionFee()
            {
                return this.transactionFee;
            }
            
            public void setTransactionFee(Double TransactionFee)
            {
                this.transactionFee = TransactionFee;
            }
        
            public String getCurrencyCode()
            {
                return this.currencyCode;
            }
            
            public void setCurrencyCode(String CurrencyCode)
            {
                this.currencyCode = CurrencyCode;
            }
        
            public String getPayerId()
            {
                return this.payerId;
            }
            
            public void setPayerId(String PayerId)
            {
                this.payerId = PayerId;
            }
        
            public Double getSubtotalAmount()
            {
                return this.subtotalAmount;
            }
            
            public void setSubtotalAmount(Double SubtotalAmount)
            {
                this.subtotalAmount = SubtotalAmount;
            }
        
            public Double getDiscountAmount()
            {
                return this.discountAmount;
            }
            
            public void setDiscountAmount(Double DiscountAmount)
            {
                this.discountAmount = DiscountAmount;
            }
        
            public Double getTaxAmount()
            {
                return this.taxAmount;
            }
            
            public void setTaxAmount(Double TaxAmount)
            {
                this.taxAmount = TaxAmount;
            }
        
            public Double getShippingAmount()
            {
                return this.shippingAmount;
            }
            
            public void setShippingAmount(Double ShippingAmount)
            {
                this.shippingAmount = ShippingAmount;
            }
        
            public Double getTotalAmount()
            {
                return this.totalAmount;
            }
            
            public void setTotalAmount(Double TotalAmount)
            {
                this.totalAmount = TotalAmount;
            }
        
            public Double getRefundAmount()
            {
                return this.refundAmount;
            }
            
            public void setRefundAmount(Double RefundAmount)
            {
                this.refundAmount = RefundAmount;
            }
        
            public String getNotes()
            {
                return this.notes;
            }
            
            public void setNotes(String Notes)
            {
                this.notes = Notes;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getShippingId()
            {
                return this.shippingId;
            }
            
            public void setShippingId(Integer ShippingId)
            {
                this.shippingId = ShippingId;
            }
        
            public Integer getAffiliateId()
            {
                return this.affiliateId;
            }
            
            public void setAffiliateId(Integer AffiliateId)
            {
                this.affiliateId = AffiliateId;
            }
           
            
    }

