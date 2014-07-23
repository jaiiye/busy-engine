











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Shipping implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SHIPPING_ID = "ShippingId";
        public static final String PROP_METHOD_NAME = "MethodName";
        public static final String PROP_QUANTITY = "Quantity";
        public static final String PROP_UNIT_OF_MEASURE = "UnitOfMeasure";
        public static final String PROP_RATE_PER_UNIT_COST = "RatePerUnitCost";
        public static final String PROP_ADDITIONAL_COST = "AdditionalCost";
        public static final String PROP_STATE_PROVINCE_ID = "StateProvinceId";
        public static final String PROP_COUNTRY_ID = "CountryId";
        

        private Integer shippingId;
                
        private String methodName;
                
        private Double quantity;
                
        private String unitOfMeasure;
                
        private Double ratePerUnitCost;
                
        private Double additionalCost;
                
        private Integer stateProvinceId;
        private StateProvince stateProvince;        
        private Integer countryId;
        private Country country;        
                 
        ArrayList<Order> orderList; 
        
        

        public Shipping()
        {
            this.shippingId = 0; 
       this.methodName = ""; 
       this.quantity = 0.0; 
       this.unitOfMeasure = ""; 
       this.ratePerUnitCost = 0.0; 
       this.additionalCost = 0.0; 
       this.stateProvinceId = 0; 
       this.countryId = 0; 
        
       orderList = null; 
        
       }
        
        public Shipping(Integer ShippingId, String MethodName, Double Quantity, String UnitOfMeasure, Double RatePerUnitCost, Double AdditionalCost, Integer StateProvinceId, Integer CountryId)
        {
            this.shippingId = ShippingId;
       this.methodName = MethodName;
       this.quantity = Quantity;
       this.unitOfMeasure = UnitOfMeasure;
       this.ratePerUnitCost = RatePerUnitCost;
       this.additionalCost = AdditionalCost;
       this.stateProvinceId = StateProvinceId;
       this.countryId = CountryId;
              
       orderList = null; 
        
       } 
        
             
        
            public Integer getShippingId()
            {
                return this.shippingId;
            }
            
            public void setShippingId(Integer ShippingId)
            {
                this.shippingId = ShippingId;
            }
            
            
        
            public String getMethodName()
            {
                return this.methodName;
            }
            
            public void setMethodName(String MethodName)
            {
                this.methodName = MethodName;
            }
            
            
        
            public Double getQuantity()
            {
                return this.quantity;
            }
            
            public void setQuantity(Double Quantity)
            {
                this.quantity = Quantity;
            }
            
            
        
            public String getUnitOfMeasure()
            {
                return this.unitOfMeasure;
            }
            
            public void setUnitOfMeasure(String UnitOfMeasure)
            {
                this.unitOfMeasure = UnitOfMeasure;
            }
            
            
        
            public Double getRatePerUnitCost()
            {
                return this.ratePerUnitCost;
            }
            
            public void setRatePerUnitCost(Double RatePerUnitCost)
            {
                this.ratePerUnitCost = RatePerUnitCost;
            }
            
            
        
            public Double getAdditionalCost()
            {
                return this.additionalCost;
            }
            
            public void setAdditionalCost(Double AdditionalCost)
            {
                this.additionalCost = AdditionalCost;
            }
            
            
        
            public Integer getStateProvinceId()
            {
                return this.stateProvinceId;
            }
            
            public void setStateProvinceId(Integer StateProvinceId)
            {
                this.stateProvinceId = StateProvinceId;
            }
            
            
                   
            public StateProvince getStateProvince()
                {
                    return this.stateProvince;
                }

                public void setStateProvince(StateProvince stateProvince)
                {
                    this.stateProvince = stateProvince;
                }
                   
            
        
            public Integer getCountryId()
            {
                return this.countryId;
            }
            
            public void setCountryId(Integer CountryId)
            {
                this.countryId = CountryId;
            }
            
            
                   
            public Country getCountry()
                {
                    return this.country;
                }

                public void setCountry(Country country)
                {
                    this.country = country;
                }
                   
            
         
        
        
            public ArrayList<Order> getOrderList()
            {
                return this.orderList;
            }
            
            public void setOrderList(ArrayList<Order> orderList)
            {
                this.orderList = orderList;
            }
        
            
    }
