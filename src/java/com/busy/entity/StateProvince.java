











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class StateProvince implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_STATE_PROVINCE_ID = "StateProvinceId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_ABBREVIATION = "Abbreviation";
        public static final String PROP_COUNTRY_ID = "CountryId";
        

        private Integer stateProvinceId;
                
        private String name;
                
        private String abbreviation;
                
        private Integer countryId;
        private Country country;        
                 
        ArrayList<Shipping> shippingList; 
        ArrayList<TaxRate> taxRateList; 
        
        

        public StateProvince()
        {
            this.stateProvinceId = 0; 
       this.name = ""; 
       this.abbreviation = ""; 
       this.countryId = 0; 
        
       shippingList = null; 
        taxRateList = null; 
        
       }
        
        public StateProvince(Integer StateProvinceId, String Name, String Abbreviation, Integer CountryId)
        {
            this.stateProvinceId = StateProvinceId;
       this.name = Name;
       this.abbreviation = Abbreviation;
       this.countryId = CountryId;
              
       shippingList = null; 
        taxRateList = null; 
        
       } 
        
             
        
            public Integer getStateProvinceId()
            {
                return this.stateProvinceId;
            }
            
            public void setStateProvinceId(Integer StateProvinceId)
            {
                this.stateProvinceId = StateProvinceId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
        
            public String getAbbreviation()
            {
                return this.abbreviation;
            }
            
            public void setAbbreviation(String Abbreviation)
            {
                this.abbreviation = Abbreviation;
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
                   
            
         
        
        
            public ArrayList<Shipping> getShippingList()
            {
                return this.shippingList;
            }
            
            public void setShippingList(ArrayList<Shipping> shippingList)
            {
                this.shippingList = shippingList;
            }
        
            public ArrayList<TaxRate> getTaxRateList()
            {
                return this.taxRateList;
            }
            
            public void setTaxRateList(ArrayList<TaxRate> taxRateList)
            {
                this.taxRateList = taxRateList;
            }
        
            
    }

