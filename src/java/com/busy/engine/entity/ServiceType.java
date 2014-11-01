






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ServiceType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SERVICE_TYPE_ID = "ServiceTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_DESCIPTION = "Desciption";
        

        private Integer serviceTypeId;
                
        private String typeName;
                
        private String desciption;
                
                 
        ArrayList<Service> serviceList; 
        
        

        public ServiceType()
        {
            this.serviceTypeId = 0; 
       this.typeName = ""; 
       this.desciption = ""; 
        
       serviceList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return serviceTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("serviceTypeId", serviceTypeId == null ? 0 : serviceTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("desciption", desciption == null ? "" : desciption);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ServiceType.PROP_SERVICE_TYPE_ID) || column.equals(ServiceType.PROP_TYPE_NAME) || column.equals(ServiceType.PROP_DESCIPTION) )
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
            if (column.equals(ServiceType.PROP_SERVICE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ServiceType process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new ServiceType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public ServiceType(Integer ServiceTypeId, String TypeName, String Desciption)
       {
            this.serviceTypeId = ServiceTypeId;
       this.typeName = TypeName;
       this.desciption = Desciption;
              
       serviceList = null; 
        
       } 
        
             
        
            public Integer getServiceTypeId()
            {
                return this.serviceTypeId;
            }
            
            public void setServiceTypeId(Integer ServiceTypeId)
            {
                this.serviceTypeId = ServiceTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public String getDesciption()
            {
                return this.desciption;
            }
            
            public void setDesciption(String Desciption)
            {
                this.desciption = Desciption;
            }
            
            
         
        
        
        public ArrayList<Service> getServiceList()
            {
                return this.serviceList;
            }
            
            public void setServiceList(ArrayList<Service> serviceList)
            {
                this.serviceList = serviceList;
            }
        
            
    }

