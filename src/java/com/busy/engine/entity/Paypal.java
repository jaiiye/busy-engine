











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Paypal extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_PAYPAL_ID = "PaypalId";
        public static final String PROP_PAY_PAL_URL = "PayPalUrl";
        public static final String PROP_CURRENCY_CODE = "CurrencyCode";
        public static final String PROP_API_USERNAME = "ApiUsername";
        public static final String PROP_API_PASSWORD = "ApiPassword";
        public static final String PROP_API_SIGNATURE = "ApiSignature";
        public static final String PROP_API_ENDPOINT = "ApiEndpoint";
        public static final String PROP_ACTIVE_PROFILE = "ActiveProfile";
        public static final String PROP_RETURN_URL = "ReturnUrl";
        public static final String PROP_CANCEL_URL = "CancelUrl";
        public static final String PROP_PAYMENT_TYPE = "PaymentType";
        public static final String PROP_ENVIRONMENT = "Environment";
        

        private Integer paypalId;
                
        private String payPalUrl;
                
        private String currencyCode;
                
        private String apiUsername;
                
        private String apiPassword;
                
        private String apiSignature;
                
        private String apiEndpoint;
                
        private Boolean activeProfile;
                
        private String returnUrl;
                
        private String cancelUrl;
                
        private String paymentType;
                
        private String environment;
                
                 
        
        

        public Paypal()
        {
            this.paypalId = 0; 
       this.payPalUrl = ""; 
       this.currencyCode = ""; 
       this.apiUsername = ""; 
       this.apiPassword = ""; 
       this.apiSignature = ""; 
       this.apiEndpoint = ""; 
       this.activeProfile = null; 
       this.returnUrl = ""; 
       this.cancelUrl = ""; 
       this.paymentType = ""; 
       this.environment = ""; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return paypalId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("paypalId", paypalId).add("payPalUrl", payPalUrl).add("currencyCode", currencyCode).add("apiUsername", apiUsername).add("apiPassword", apiPassword).add("apiSignature", apiSignature).add("apiEndpoint", apiEndpoint).add("activeProfile", activeProfile).add("returnUrl", returnUrl).add("cancelUrl", cancelUrl).add("paymentType", paymentType).add("environment", environment);
        
             
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Paypal.PROP_PAYPAL_ID) || column.equals(Paypal.PROP_PAY_PAL_URL) || column.equals(Paypal.PROP_CURRENCY_CODE) || column.equals(Paypal.PROP_API_USERNAME) || column.equals(Paypal.PROP_API_PASSWORD) || column.equals(Paypal.PROP_API_SIGNATURE) || column.equals(Paypal.PROP_API_ENDPOINT) || column.equals(Paypal.PROP_ACTIVE_PROFILE) || column.equals(Paypal.PROP_RETURN_URL) || column.equals(Paypal.PROP_CANCEL_URL) || column.equals(Paypal.PROP_PAYMENT_TYPE) || column.equals(Paypal.PROP_ENVIRONMENT) )
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
            if (column.equals(Paypal.PROP_PAYPAL_ID) || column.equals(Paypal.PROP_ACTIVE_PROFILE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Paypal process(ResultSet rs) throws SQLException
        {        
            return new Paypal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
        }
              
       public Paypal(Integer PaypalId, String PayPalUrl, String CurrencyCode, String ApiUsername, String ApiPassword, String ApiSignature, String ApiEndpoint, Boolean ActiveProfile, String ReturnUrl, String CancelUrl, String PaymentType, String Environment)
       {
            this.paypalId = PaypalId;
       this.payPalUrl = PayPalUrl;
       this.currencyCode = CurrencyCode;
       this.apiUsername = ApiUsername;
       this.apiPassword = ApiPassword;
       this.apiSignature = ApiSignature;
       this.apiEndpoint = ApiEndpoint;
       this.activeProfile = ActiveProfile;
       this.returnUrl = ReturnUrl;
       this.cancelUrl = CancelUrl;
       this.paymentType = PaymentType;
       this.environment = Environment;
              
       
       } 
        
             
        
            public Integer getPaypalId()
            {
                return this.paypalId;
            }
            
            public void setPaypalId(Integer PaypalId)
            {
                this.paypalId = PaypalId;
            }
            
            
        
            public String getPayPalUrl()
            {
                return this.payPalUrl;
            }
            
            public void setPayPalUrl(String PayPalUrl)
            {
                this.payPalUrl = PayPalUrl;
            }
            
            
        
            public String getCurrencyCode()
            {
                return this.currencyCode;
            }
            
            public void setCurrencyCode(String CurrencyCode)
            {
                this.currencyCode = CurrencyCode;
            }
            
            
        
            public String getApiUsername()
            {
                return this.apiUsername;
            }
            
            public void setApiUsername(String ApiUsername)
            {
                this.apiUsername = ApiUsername;
            }
            
            
        
            public String getApiPassword()
            {
                return this.apiPassword;
            }
            
            public void setApiPassword(String ApiPassword)
            {
                this.apiPassword = ApiPassword;
            }
            
            
        
            public String getApiSignature()
            {
                return this.apiSignature;
            }
            
            public void setApiSignature(String ApiSignature)
            {
                this.apiSignature = ApiSignature;
            }
            
            
        
            public String getApiEndpoint()
            {
                return this.apiEndpoint;
            }
            
            public void setApiEndpoint(String ApiEndpoint)
            {
                this.apiEndpoint = ApiEndpoint;
            }
            
            
        
            public Boolean getActiveProfile()
            {
                return this.activeProfile;
            }
            
            public void setActiveProfile(Boolean ActiveProfile)
            {
                this.activeProfile = ActiveProfile;
            }
            
            
        
            public String getReturnUrl()
            {
                return this.returnUrl;
            }
            
            public void setReturnUrl(String ReturnUrl)
            {
                this.returnUrl = ReturnUrl;
            }
            
            
        
            public String getCancelUrl()
            {
                return this.cancelUrl;
            }
            
            public void setCancelUrl(String CancelUrl)
            {
                this.cancelUrl = CancelUrl;
            }
            
            
        
            public String getPaymentType()
            {
                return this.paymentType;
            }
            
            public void setPaymentType(String PaymentType)
            {
                this.paymentType = PaymentType;
            }
            
            
        
            public String getEnvironment()
            {
                return this.environment;
            }
            
            public void setEnvironment(String Environment)
            {
                this.environment = Environment;
            }
            
            
         
        
        
            
    }

