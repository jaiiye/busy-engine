











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Country implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_COUNTRY_ID = "CountryId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_ISO_CODE = "IsoCode";
        public static final String PROP_ISO_NUMBER = "IsoNumber";
        public static final String PROP_HAS_VAT = "HasVat";
        

        private Integer countryId;
                
        private String name;
                
        private String isoCode;
                
        private Integer isoNumber;
                
        private Integer hasVat;
                
                 
        ArrayList<Shipping> shippingList; 
        ArrayList<StateProvince> stateProvinceList; 
        ArrayList<TaxRate> taxRateList; 
        
        

        public Country()
        {
            this.countryId = 0; 
       this.name = ""; 
       this.isoCode = ""; 
       this.isoNumber = 0; 
       this.hasVat = 0; 
        
       shippingList = null; 
        stateProvinceList = null; 
        taxRateList = null; 
        
       }
        
        public Country(Integer CountryId, String Name, String IsoCode, Integer IsoNumber, Integer HasVat)
        {
            this.countryId = CountryId;
       this.name = Name;
       this.isoCode = IsoCode;
       this.isoNumber = IsoNumber;
       this.hasVat = HasVat;
              
       shippingList = null; 
        stateProvinceList = null; 
        taxRateList = null; 
        
       } 
        
             
        
            public Integer getCountryId()
            {
                return this.countryId;
            }
            
            public void setCountryId(Integer CountryId)
            {
                this.countryId = CountryId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
        
            public String getIsoCode()
            {
                return this.isoCode;
            }
            
            public void setIsoCode(String IsoCode)
            {
                this.isoCode = IsoCode;
            }
            
            
        
            public Integer getIsoNumber()
            {
                return this.isoNumber;
            }
            
            public void setIsoNumber(Integer IsoNumber)
            {
                this.isoNumber = IsoNumber;
            }
            
            
        
            public Integer getHasVat()
            {
                return this.hasVat;
            }
            
            public void setHasVat(Integer HasVat)
            {
                this.hasVat = HasVat;
            }
            
            
         
        
        
            public ArrayList<Shipping> getShippingList()
            {
                return this.shippingList;
            }
            
            public void setShippingList(ArrayList<Shipping> shippingList)
            {
                this.shippingList = shippingList;
            }
        
            public ArrayList<StateProvince> getStateProvinceList()
            {
                return this.stateProvinceList;
            }
            
            public void setStateProvinceList(ArrayList<StateProvince> stateProvinceList)
            {
                this.stateProvinceList = stateProvinceList;
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

