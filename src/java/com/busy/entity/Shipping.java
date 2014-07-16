


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Shipping implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SHIPPING_ID = "ShippingId";
        public static final String PROP_METHOD_NAME = "MethodName";
        public static final String PROP_STATE_PROVINCE = "StateProvince";
        public static final String PROP_COUNTRY = "Country";
        public static final String PROP_QUANTITY = "Quantity";
        public static final String PROP_UNIT_OF_MEASURE = "UnitOfMeasure";
        public static final String PROP_RATE_PER_UNIT_COST = "RatePerUnitCost";
        public static final String PROP_ADDITIONAL_COST = "AdditionalCost";
        

        private Integer shippingId;
        private String methodName;
        private String stateProvince;
        private String country;
        private Double quantity;
        private String unitOfMeasure;
        private Double ratePerUnitCost;
        private Double additionalCost;
        

        public Shipping()
        {
            this.shippingId = 0; 
       this.methodName = ""; 
       this.stateProvince = ""; 
       this.country = ""; 
       this.quantity = 0.0; 
       this.unitOfMeasure = ""; 
       this.ratePerUnitCost = 0.0; 
       this.additionalCost = 0.0; 
        }
        
        public Shipping(Integer ShippingId, String MethodName, String StateProvince, String Country, Double Quantity, String UnitOfMeasure, Double RatePerUnitCost, Double AdditionalCost)
        {
            this.shippingId = ShippingId;
       this.methodName = MethodName;
       this.stateProvince = StateProvince;
       this.country = Country;
       this.quantity = Quantity;
       this.unitOfMeasure = UnitOfMeasure;
       this.ratePerUnitCost = RatePerUnitCost;
       this.additionalCost = AdditionalCost;
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
        
            public String getStateProvince()
            {
                return this.stateProvince;
            }
            
            public void setStateProvince(String StateProvince)
            {
                this.stateProvince = StateProvince;
            }
        
            public String getCountry()
            {
                return this.country;
            }
            
            public void setCountry(String Country)
            {
                this.country = Country;
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
           
            
    }

