






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Tenant extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TENANT_ID = "TenantId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_LOGO = "Logo";
        public static final String PROP_DASHBOARD_ID = "DashboardId";
        

        private Integer tenantId;
                
        private String name;
                
        private String logo;
                
        private Integer dashboardId;
        private Dashboard dashboard;        
                 
        ArrayList<Site> siteList; 
        ArrayList<TenantAttribute> tenantAttributeList; 
        
        

        public Tenant()
        {
            this.tenantId = 0; 
       this.name = ""; 
       this.logo = ""; 
       this.dashboardId = 0; 
        
       siteList = null; 
        tenantAttributeList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return tenantId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("tenantId", tenantId == null ? 0 : tenantId);
                
            builder.add("name", name == null ? "" : name);
                
            builder.add("logo", logo == null ? "" : logo);
                
            builder.add("dashboardId", dashboardId == null ? 0 : dashboardId);
        
        
    
     
     
     if(dashboard != null) dashboard.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Tenant.PROP_TENANT_ID) || column.equals(Tenant.PROP_NAME) || column.equals(Tenant.PROP_LOGO) || column.equals(Tenant.PROP_DASHBOARD_ID) )
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
            if (column.equals(Tenant.PROP_TENANT_ID) || column.equals(Tenant.PROP_DASHBOARD_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Tenant process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Tenant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }
              
       public Tenant(Integer TenantId, String Name, String Logo, Integer DashboardId)
       {
            this.tenantId = TenantId;
       this.name = Name;
       this.logo = Logo;
       this.dashboardId = DashboardId;
              
       siteList = null; 
        tenantAttributeList = null; 
        
       } 
        
             
        
            public Integer getTenantId()
            {
                return this.tenantId;
            }
            
            public void setTenantId(Integer TenantId)
            {
                this.tenantId = TenantId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
        
            public String getLogo()
            {
                return this.logo;
            }
            
            public void setLogo(String Logo)
            {
                this.logo = Logo;
            }
            
            
        
            public Integer getDashboardId()
            {
                return this.dashboardId;
            }
            
            public void setDashboardId(Integer DashboardId)
            {
                this.dashboardId = DashboardId;
            }
            
            
                   
            public Dashboard getDashboard()
                {
                    return this.dashboard;
                }

                public void setDashboard(Dashboard dashboard)
                {
                    this.dashboard = dashboard;
                }
                   
            
         
        
        
        public ArrayList<Site> getSiteList()
            {
                return this.siteList;
            }
            
            public void setSiteList(ArrayList<Site> siteList)
            {
                this.siteList = siteList;
            }
        
        public ArrayList<TenantAttribute> getTenantAttributeList()
            {
                return this.tenantAttributeList;
            }
            
            public void setTenantAttributeList(ArrayList<TenantAttribute> tenantAttributeList)
            {
                this.tenantAttributeList = tenantAttributeList;
            }
        
            
    }

