






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class TaxRate extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return taxRateId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("taxRateId", taxRateId == null ? 0 : taxRateId);
                
            builder.add("taxCategory", taxCategory == null ? "" : taxCategory);
                
            builder.add("percentage", percentage == null ? 0 : percentage);
                
            builder.add("zipPostalCode", zipPostalCode == null ? "" : zipPostalCode);
                
            builder.add("stateProvinceId", stateProvinceId == null ? 0 : stateProvinceId);
                
            builder.add("countryId", countryId == null ? 0 : countryId);
        
        
    
     
     
     
     if(stateProvince != null) stateProvince.addJson(builder);
        
     if(country != null) country.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TaxRate.PROP_TAX_RATE_ID) || column.equals(TaxRate.PROP_TAX_CATEGORY) || column.equals(TaxRate.PROP_PERCENTAGE) || column.equals(TaxRate.PROP_ZIP_POSTAL_CODE) || column.equals(TaxRate.PROP_STATE_PROVINCE_ID) || column.equals(TaxRate.PROP_COUNTRY_ID) )
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
            if (column.equals(TaxRate.PROP_TAX_RATE_ID) || column.equals(TaxRate.PROP_PERCENTAGE) || column.equals(TaxRate.PROP_STATE_PROVINCE_ID) || column.equals(TaxRate.PROP_COUNTRY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TaxRate process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new TaxRate(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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

