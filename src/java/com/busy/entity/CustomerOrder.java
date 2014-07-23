











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class CustomerOrder implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_CUSTOMER_ORDER_ID = "CustomerOrderId";
        public static final String PROP_CUSTOMER_ID = "CustomerId";
        public static final String PROP_ORDER_ID = "OrderId";
        public static final String PROP_DISCOUNT_ID = "DiscountId";
        public static final String PROP_CUSTOMER_IP = "CustomerIp";
        

        private Integer customerOrderId;
                
        private Integer customerId;
        private Customer customer;        
        private Integer orderId;
        private Order order;        
        private Integer discountId;
        private Discount discount;        
        private String customerIp;
                
                 
        ArrayList<OrderItem> orderItemList; 
        
        

        public CustomerOrder()
        {
            this.customerOrderId = 0; 
       this.customerId = 0; 
       this.orderId = 0; 
       this.discountId = 0; 
       this.customerIp = ""; 
        
       orderItemList = null; 
        
       }
        
        public CustomerOrder(Integer CustomerOrderId, Integer CustomerId, Integer OrderId, Integer DiscountId, String CustomerIp)
        {
            this.customerOrderId = CustomerOrderId;
       this.customerId = CustomerId;
       this.orderId = OrderId;
       this.discountId = DiscountId;
       this.customerIp = CustomerIp;
              
       orderItemList = null; 
        
       } 
        
             
        
            public Integer getCustomerOrderId()
            {
                return this.customerOrderId;
            }
            
            public void setCustomerOrderId(Integer CustomerOrderId)
            {
                this.customerOrderId = CustomerOrderId;
            }
            
            
        
            public Integer getCustomerId()
            {
                return this.customerId;
            }
            
            public void setCustomerId(Integer CustomerId)
            {
                this.customerId = CustomerId;
            }
            
            
                   
            public Customer getCustomer()
                {
                    return this.customer;
                }

                public void setCustomer(Customer customer)
                {
                    this.customer = customer;
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
                   
            
        
            public Integer getDiscountId()
            {
                return this.discountId;
            }
            
            public void setDiscountId(Integer DiscountId)
            {
                this.discountId = DiscountId;
            }
            
            
                   
            public Discount getDiscount()
                {
                    return this.discount;
                }

                public void setDiscount(Discount discount)
                {
                    this.discount = discount;
                }
                   
            
        
            public String getCustomerIp()
            {
                return this.customerIp;
            }
            
            public void setCustomerIp(String CustomerIp)
            {
                this.customerIp = CustomerIp;
            }
            
            
         
        
        
            public ArrayList<OrderItem> getOrderItemList()
            {
                return this.orderItemList;
            }
            
            public void setOrderItemList(ArrayList<OrderItem> orderItemList)
            {
                this.orderItemList = orderItemList;
            }
        
            
    }

