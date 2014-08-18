



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Country extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return countryId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("countryId", countryId == null ? 0 : countryId);
                
            builder.add("name", name == null ? "" : name);
                
            builder.add("isoCode", isoCode == null ? "" : isoCode);
                
            builder.add("isoNumber", isoNumber == null ? 0 : isoNumber);
                
            builder.add("hasVat", hasVat == null ? 0 : hasVat);
        
        
    
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Country.PROP_COUNTRY_ID) || column.equals(Country.PROP_NAME) || column.equals(Country.PROP_ISO_CODE) || column.equals(Country.PROP_ISO_NUMBER) || column.equals(Country.PROP_HAS_VAT) )
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
            if (column.equals(Country.PROP_COUNTRY_ID) || column.equals(Country.PROP_ISO_NUMBER) || column.equals(Country.PROP_HAS_VAT) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Country process(ResultSet rs) throws SQLException
        {        
            return new Country(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
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

