


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class TaxRate implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TAX_RATE_ID = "TaxRateId";
        public static final String PROP_TAX_CATEGORY = "TaxCategory";
        public static final String PROP_STATE_PROVINCE = "StateProvince";
        public static final String PROP_ZIP_POSTAL_CODE = "ZipPostalCode";
        public static final String PROP_COUNTRY = "Country";
        public static final String PROP_COUNTRY_CODE = "CountryCode";
        public static final String PROP_PERCENTAGE = "Percentage";
        

        private Integer taxRateId;
        private String taxCategory;
        private String stateProvince;
        private String zipPostalCode;
        private String country;
        private String countryCode;
        private Double percentage;
        

        public TaxRate()
        {
            this.taxRateId = 0; 
       this.taxCategory = ""; 
       this.stateProvince = ""; 
       this.zipPostalCode = ""; 
       this.country = ""; 
       this.countryCode = ""; 
       this.percentage = 0.0; 
        }
        
        public TaxRate(Integer TaxRateId, String TaxCategory, String StateProvince, String ZipPostalCode, String Country, String CountryCode, Double Percentage)
        {
            this.taxRateId = TaxRateId;
       this.taxCategory = TaxCategory;
       this.stateProvince = StateProvince;
       this.zipPostalCode = ZipPostalCode;
       this.country = Country;
       this.countryCode = CountryCode;
       this.percentage = Percentage;
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
        
            public String getStateProvince()
            {
                return this.stateProvince;
            }
            
            public void setStateProvince(String StateProvince)
            {
                this.stateProvince = StateProvince;
            }
        
            public String getZipPostalCode()
            {
                return this.zipPostalCode;
            }
            
            public void setZipPostalCode(String ZipPostalCode)
            {
                this.zipPostalCode = ZipPostalCode;
            }
        
            public String getCountry()
            {
                return this.country;
            }
            
            public void setCountry(String Country)
            {
                this.country = Country;
            }
        
            public String getCountryCode()
            {
                return this.countryCode;
            }
            
            public void setCountryCode(String CountryCode)
            {
                this.countryCode = CountryCode;
            }
        
            public Double getPercentage()
            {
                return this.percentage;
            }
            
            public void setPercentage(Double Percentage)
            {
                this.percentage = Percentage;
            }
           
            
    }

