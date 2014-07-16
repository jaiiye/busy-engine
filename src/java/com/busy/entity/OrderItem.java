


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class OrderItem implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ORDER_ITEM_ID = "OrderItemId";
        public static final String PROP_CUSTOMER_ORDER_ID = "CustomerOrderId";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_QUANTITY = "Quantity";
        public static final String PROP_OPTION_NAME = "OptionName";
        public static final String PROP_UNIT_PRICE = "UnitPrice";
        

        private Integer orderItemId;
        private Integer customerOrderId;
        private Integer itemId;
        private Integer quantity;
        private String optionName;
        private Double unitPrice;
        

        public OrderItem()
        {
            this.orderItemId = 0; 
       this.customerOrderId = 0; 
       this.itemId = 0; 
       this.quantity = 0; 
       this.optionName = ""; 
       this.unitPrice = 0.0; 
        }
        
        public OrderItem(Integer OrderItemId, Integer CustomerOrderId, Integer ItemId, Integer Quantity, String OptionName, Double UnitPrice)
        {
            this.orderItemId = OrderItemId;
       this.customerOrderId = CustomerOrderId;
       this.itemId = ItemId;
       this.quantity = Quantity;
       this.optionName = OptionName;
       this.unitPrice = UnitPrice;
        } 
        
             
        
            public Integer getOrderItemId()
            {
                return this.orderItemId;
            }
            
            public void setOrderItemId(Integer OrderItemId)
            {
                this.orderItemId = OrderItemId;
            }
        
            public Integer getCustomerOrderId()
            {
                return this.customerOrderId;
            }
            
            public void setCustomerOrderId(Integer CustomerOrderId)
            {
                this.customerOrderId = CustomerOrderId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getQuantity()
            {
                return this.quantity;
            }
            
            public void setQuantity(Integer Quantity)
            {
                this.quantity = Quantity;
            }
        
            public String getOptionName()
            {
                return this.optionName;
            }
            
            public void setOptionName(String OptionName)
            {
                this.optionName = OptionName;
            }
        
            public Double getUnitPrice()
            {
                return this.unitPrice;
            }
            
            public void setUnitPrice(Double UnitPrice)
            {
                this.unitPrice = UnitPrice;
            }
           
            
    }

