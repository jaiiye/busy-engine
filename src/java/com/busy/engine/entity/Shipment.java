






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Shipment extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return shipmentId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("shipmentId", shipmentId == null ? 0 : shipmentId);
                
            builder.add("createdOn", createdOn == null ? "" : new SimpleDateFormat("yyyyMMdd").format(createdOn));
                
            builder.add("trackingNumber", trackingNumber == null ? "" : trackingNumber);
                
            builder.add("totalWeight", totalWeight == null ? 0 : totalWeight);
                
            builder.add("shipDate", shipDate == null ? "" : new SimpleDateFormat("yyyyMMdd").format(shipDate));
                
            builder.add("deliveryDate", deliveryDate == null ? "" : new SimpleDateFormat("yyyyMMdd").format(deliveryDate));
                
            builder.add("itemQuantity", itemQuantity == null ? 0 : itemQuantity);
                
            builder.add("orderId", orderId == null ? 0 : orderId);
        
        
    
     
     
     
     
     
     
     if(order != null) order.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Shipment.PROP_SHIPMENT_ID) || column.equals(Shipment.PROP_CREATED_ON) || column.equals(Shipment.PROP_TRACKING_NUMBER) || column.equals(Shipment.PROP_TOTAL_WEIGHT) || column.equals(Shipment.PROP_SHIP_DATE) || column.equals(Shipment.PROP_DELIVERY_DATE) || column.equals(Shipment.PROP_ITEM_QUANTITY) || column.equals(Shipment.PROP_ORDER_ID) )
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
            if (column.equals(Shipment.PROP_SHIPMENT_ID) || column.equals(Shipment.PROP_TOTAL_WEIGHT) || column.equals(Shipment.PROP_ITEM_QUANTITY) || column.equals(Shipment.PROP_ORDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Shipment process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new Shipment(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getInt(7), rs.getInt(8));
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

