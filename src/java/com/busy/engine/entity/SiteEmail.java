






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SiteEmail extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_EMAIL_ID = "SiteEmailId";
        public static final String PROP_HOST = "Host";
        public static final String PROP_PORT = "Port";
        public static final String PROP_USERNAME = "Username";
        public static final String PROP_PASSWORD = "Password";
        

        private Integer siteEmailId;
                
        private String host;
                
        private Integer port;
                
        private String username;
                
        private String password;
                
                 
        ArrayList<Site> siteList; 
        
        

        public SiteEmail()
        {
            this.siteEmailId = 0; 
       this.host = ""; 
       this.port = 0; 
       this.username = ""; 
       this.password = ""; 
        
       siteList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return siteEmailId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("siteEmailId", siteEmailId == null ? 0 : siteEmailId);
                
            builder.add("host", host == null ? "" : host);
                
            builder.add("port", port == null ? 0 : port);
                
            builder.add("username", username == null ? "" : username);
                
            builder.add("password", password == null ? "" : password);
        
        
    
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteEmail.PROP_SITE_EMAIL_ID) || column.equals(SiteEmail.PROP_HOST) || column.equals(SiteEmail.PROP_PORT) || column.equals(SiteEmail.PROP_USERNAME) || column.equals(SiteEmail.PROP_PASSWORD) )
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
            if (column.equals(SiteEmail.PROP_SITE_EMAIL_ID) || column.equals(SiteEmail.PROP_PORT) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteEmail process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new SiteEmail(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
        }
              
       public SiteEmail(Integer SiteEmailId, String Host, Integer Port, String Username, String Password)
       {
            this.siteEmailId = SiteEmailId;
       this.host = Host;
       this.port = Port;
       this.username = Username;
       this.password = Password;
              
       siteList = null; 
        
       } 
        
             
        
            public Integer getSiteEmailId()
            {
                return this.siteEmailId;
            }
            
            public void setSiteEmailId(Integer SiteEmailId)
            {
                this.siteEmailId = SiteEmailId;
            }
            
            
        
            public String getHost()
            {
                return this.host;
            }
            
            public void setHost(String Host)
            {
                this.host = Host;
            }
            
            
        
            public Integer getPort()
            {
                return this.port;
            }
            
            public void setPort(Integer Port)
            {
                this.port = Port;
            }
            
            
        
            public String getUsername()
            {
                return this.username;
            }
            
            public void setUsername(String Username)
            {
                this.username = Username;
            }
            
            
        
            public String getPassword()
            {
                return this.password;
            }
            
            public void setPassword(String Password)
            {
                this.password = Password;
            }
            
            
         
        
        
        public ArrayList<Site> getSiteList()
            {
                return this.siteList;
            }
            
            public void setSiteList(ArrayList<Site> siteList)
            {
                this.siteList = siteList;
            }
        
            
    }

