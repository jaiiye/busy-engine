



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class StateProvince extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return stateProvinceId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("stateProvinceId", stateProvinceId == null ? 0 : stateProvinceId);
                
            builder.add("name", name == null ? "" : name);
                
            builder.add("abbreviation", abbreviation == null ? "" : abbreviation);
                
            builder.add("countryId", countryId == null ? 0 : countryId);
        
        
    
     
     
     if(country != null) country.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(StateProvince.PROP_STATE_PROVINCE_ID) || column.equals(StateProvince.PROP_NAME) || column.equals(StateProvince.PROP_ABBREVIATION) || column.equals(StateProvince.PROP_COUNTRY_ID) )
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
            if (column.equals(StateProvince.PROP_STATE_PROVINCE_ID) || column.equals(StateProvince.PROP_COUNTRY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static StateProvince process(ResultSet rs) throws SQLException
        {        
            return new StateProvince(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
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

