











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class TaxRate implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TAX_RATE_ID = "TaxRateId";
        public static final String PROP_TAX_CATEGORY = "TaxCategory";
        public static final String PROP_PERCENTAGE = "Percentage";
        public static final String PROP_ZIP_POSTAL_CODE = "ZipPostalCode";
        public static final String PROP_STATE_PROVINCE_ID = "StateProvinceId";
        public static final String PROP_COUNTRY_ID = "CountryId";
        

        private Integer taxRateId;
                
        private String taxCategory;
                
        private Double percentage;
                
        private String zipPostalCode;
                
        private Integer stateProvinceId;
        private StateProvince stateProvince;        
        private Integer countryId;
        private Country country;        
                 
        
        

        public TaxRate()
        {
            this.taxRateId = 0; 
       this.taxCategory = ""; 
       this.percentage = 0.0; 
       this.zipPostalCode = ""; 
       this.stateProvinceId = 0; 
       this.countryId = 0; 
        
       
       }
        
        public TaxRate(Integer TaxRateId, String TaxCategory, Double Percentage, String ZipPostalCode, Integer StateProvinceId, Integer CountryId)
        {
            this.taxRateId = TaxRateId;
       this.taxCategory = TaxCategory;
       this.percentage = Percentage;
       this.zipPostalCode = ZipPostalCode;
       this.stateProvinceId = StateProvinceId;
       this.countryId = CountryId;
              
       
       } 
        
             
        
            public Integer getTaxRateId()
            {
                return this.taxRateId;
            }
            
            public void setTaxRateId(Integer TaxRateId)
            {
                this.taxRateId = TaxRateId;
            }
            
            
        
            public String getTaxCategory()
            {
                return this.taxCategory;
            }
            
            public void setTaxCategory(String TaxCategory)
            {
                this.taxCategory = TaxCategory;
            }
            
            
        
            public Double getPercentage()
            {
                return this.percentage;
            }
            
            public void setPercentage(Double Percentage)
            {
                this.percentage = Percentage;
            }
            
            
        
            public String getZipPostalCode()
            {
                return this.zipPostalCode;
            }
            
            public void setZipPostalCode(String ZipPostalCode)
            {
                this.zipPostalCode = ZipPostalCode;
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
                   
            
         
        
        
            
    }

