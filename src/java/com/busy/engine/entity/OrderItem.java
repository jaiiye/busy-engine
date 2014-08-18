



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class OrderItem extends AbstractEntity implements EntityItem<Integer>
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
        private CustomerOrder customerOrder;        
        private Integer itemId;
        private Item item;        
        private Integer quantity;
                
        private String optionName;
                
        private Double unitPrice;
                
                 
        ArrayList<ReturnRequest> returnRequestList; 
        
        

        public OrderItem()
        {
            this.orderItemId = 0; 
       this.customerOrderId = 0; 
       this.itemId = 0; 
       this.quantity = 0; 
       this.optionName = ""; 
       this.unitPrice = 0.0; 
        
       returnRequestList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return orderItemId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("orderItemId", orderItemId == null ? 0 : orderItemId);
                
            builder.add("customerOrderId", customerOrderId == null ? 0 : customerOrderId);
                
            builder.add("itemId", itemId == null ? 0 : itemId);
                
            builder.add("quantity", quantity == null ? 0 : quantity);
                
            builder.add("optionName", optionName == null ? "" : optionName);
                
            builder.add("unitPrice", unitPrice == null ? 0 : unitPrice);
        
        
    
     if(customerOrder != null) customerOrder.addJson(builder);
        
     if(item != null) item.addJson(builder);
        
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(OrderItem.PROP_ORDER_ITEM_ID) || column.equals(OrderItem.PROP_CUSTOMER_ORDER_ID) || column.equals(OrderItem.PROP_ITEM_ID) || column.equals(OrderItem.PROP_QUANTITY) || column.equals(OrderItem.PROP_OPTION_NAME) || column.equals(OrderItem.PROP_UNIT_PRICE) )
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
            if (column.equals(OrderItem.PROP_ORDER_ITEM_ID) || column.equals(OrderItem.PROP_CUSTOMER_ORDER_ID) || column.equals(OrderItem.PROP_ITEM_ID) || column.equals(OrderItem.PROP_QUANTITY) || column.equals(OrderItem.PROP_UNIT_PRICE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static OrderItem process(ResultSet rs) throws SQLException
        {        
            return new OrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
        }
              
       public OrderItem(Integer OrderItemId, Integer CustomerOrderId, Integer ItemId, Integer Quantity, String OptionName, Double UnitPrice)
       {
            this.orderItemId = OrderItemId;
       this.customerOrderId = CustomerOrderId;
       this.itemId = ItemId;
       this.quantity = Quantity;
       this.optionName = OptionName;
       this.unitPrice = UnitPrice;
              
       returnRequestList = null; 
        
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
            
            
                   
            public CustomerOrder getCustomerOrder()
                {
                    return this.customerOrder;
                }

                public void setCustomerOrder(CustomerOrder customerOrder)
                {
                    this.customerOrder = customerOrder;
                }
                   
            
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
            
            
                   
            public Item getItem()
                {
                    return this.item;
                }

                public void setItem(Item item)
                {
                    this.item = item;
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
            
            
         
        
        
        public ArrayList<ReturnRequest> getReturnRequestList()
            {
                return this.returnRequestList;
            }
            
            public void setReturnRequestList(ArrayList<ReturnRequest> returnRequestList)
            {
                this.returnRequestList = returnRequestList;
            }
        
            
    }

