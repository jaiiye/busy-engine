











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Shipment implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SHIPMENT_ID = "ShipmentId";
        public static final String PROP_CREATED_ON = "CreatedOn";
        public static final String PROP_TRACKING_NUMBER = "TrackingNumber";
        public static final String PROP_TOTAL_WEIGHT = "TotalWeight";
        public static final String PROP_SHIP_DATE = "ShipDate";
        public static final String PROP_DELIVERY_DATE = "DeliveryDate";
        public static final String PROP_ITEM_QUANTITY = "ItemQuantity";
        public static final String PROP_ORDER_ID = "OrderId";
        

        private Integer shipmentId;
                
        private Date createdOn;
                
        private String trackingNumber;
                
        private Double totalWeight;
                
        private Date shipDate;
                
        private Date deliveryDate;
                
        private Integer itemQuantity;
                
        private Integer orderId;
        private Order order;        
                 
        
        

        public Shipment()
        {
            this.shipmentId = 0; 
       this.createdOn = null; 
       this.trackingNumber = ""; 
       this.totalWeight = 0.0; 
       this.shipDate = null; 
       this.deliveryDate = null; 
       this.itemQuantity = 0; 
       this.orderId = 0; 
        
       
       }
        
        public Shipment(Integer ShipmentId, Date CreatedOn, String TrackingNumber, Double TotalWeight, Date ShipDate, Date DeliveryDate, Integer ItemQuantity, Integer OrderId)
        {
            this.shipmentId = ShipmentId;
       this.createdOn = CreatedOn;
       this.trackingNumber = TrackingNumber;
       this.totalWeight = TotalWeight;
       this.shipDate = ShipDate;
       this.deliveryDate = DeliveryDate;
       this.itemQuantity = ItemQuantity;
       this.orderId = OrderId;
              
       
       } 
        
             
        
            public Integer getShipmentId()
            {
                return this.shipmentId;
            }
            
            public void setShipmentId(Integer ShipmentId)
            {
                this.shipmentId = ShipmentId;
            }
            
            
        
            public Date getCreatedOn()
            {
                return this.createdOn;
            }
            
            public void setCreatedOn(Date CreatedOn)
            {
                this.createdOn = CreatedOn;
            }
            
            
        
            public String getTrackingNumber()
            {
                return this.trackingNumber;
            }
            
            public void setTrackingNumber(String TrackingNumber)
            {
                this.trackingNumber = TrackingNumber;
            }
            
            
        
            public Double getTotalWeight()
            {
                return this.totalWeight;
            }
            
            public void setTotalWeight(Double TotalWeight)
            {
                this.totalWeight = TotalWeight;
            }
            
            
        
            public Date getShipDate()
            {
                return this.shipDate;
            }
            
            public void setShipDate(Date ShipDate)
            {
                this.shipDate = ShipDate;
            }
            
            
        
            public Date getDeliveryDate()
            {
                return this.deliveryDate;
            }
            
            public void setDeliveryDate(Date DeliveryDate)
            {
                this.deliveryDate = DeliveryDate;
            }
            
            
        
            public Integer getItemQuantity()
            {
                return this.itemQuantity;
            }
            
            public void setItemQuantity(Integer ItemQuantity)
            {
                this.itemQuantity = ItemQuantity;
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

