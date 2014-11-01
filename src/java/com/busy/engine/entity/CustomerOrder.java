






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class CustomerOrder extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return customerOrderId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("customerOrderId", customerOrderId == null ? 0 : customerOrderId);
                
            builder.add("customerId", customerId == null ? 0 : customerId);
                
            builder.add("orderId", orderId == null ? 0 : orderId);
                
            builder.add("discountId", discountId == null ? 0 : discountId);
                
            builder.add("customerIp", customerIp == null ? "" : customerIp);
        
        
    
     if(customer != null) customer.addJson(builder);
        
     if(order != null) order.addJson(builder);
        
     if(discount != null) discount.addJson(builder);
        
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(CustomerOrder.PROP_CUSTOMER_ORDER_ID) || column.equals(CustomerOrder.PROP_CUSTOMER_ID) || column.equals(CustomerOrder.PROP_ORDER_ID) || column.equals(CustomerOrder.PROP_DISCOUNT_ID) || column.equals(CustomerOrder.PROP_CUSTOMER_IP) )
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
            if (column.equals(CustomerOrder.PROP_CUSTOMER_ORDER_ID) || column.equals(CustomerOrder.PROP_CUSTOMER_ID) || column.equals(CustomerOrder.PROP_ORDER_ID) || column.equals(CustomerOrder.PROP_DISCOUNT_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static CustomerOrder process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new CustomerOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
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

