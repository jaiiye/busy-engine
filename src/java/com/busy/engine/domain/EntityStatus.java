











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class EntityStatus extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ENTITY_STATUS_ID = "EntityStatusId";
        public static final String PROP_STATUS_CODE = "StatusCode";
        public static final String PROP_STATUS_NAME = "StatusName";
        public static final String PROP_APPLIES_TO = "AppliesTo";
        

        private Integer entityStatusId;
                
        private Integer statusCode;
                
        private String statusName;
                
        private String appliesTo;
                
                 
        
        

        public EntityStatus()
        {
            this.entityStatusId = 0; 
       this.statusCode = 0; 
       this.statusName = ""; 
       this.appliesTo = ""; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return entityStatusId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(EntityStatus.PROP_ENTITY_STATUS_ID) || column.equals(EntityStatus.PROP_STATUS_CODE) || column.equals(EntityStatus.PROP_STATUS_NAME) || column.equals(EntityStatus.PROP_APPLIES_TO) )
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
            if (column.equals(EntityStatus.PROP_ENTITY_STATUS_ID) || column.equals(EntityStatus.PROP_STATUS_CODE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static EntityStatus process(ResultSet rs) throws SQLException
        {        
            return new EntityStatus(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }
              
       public EntityStatus(Integer EntityStatusId, Integer StatusCode, String StatusName, String AppliesTo)
       {
            this.entityStatusId = EntityStatusId;
       this.statusCode = StatusCode;
       this.statusName = StatusName;
       this.appliesTo = AppliesTo;
              
       
       } 
        
             
        
            public Integer getEntityStatusId()
            {
                return this.entityStatusId;
            }
            
            public void setEntityStatusId(Integer EntityStatusId)
            {
                this.entityStatusId = EntityStatusId;
            }
            
            
        
            public Integer getStatusCode()
            {
                return this.statusCode;
            }
            
            public void setStatusCode(Integer StatusCode)
            {
                this.statusCode = StatusCode;
            }
            
            
        
            public String getStatusName()
            {
                return this.statusName;
            }
            
            public void setStatusName(String StatusName)
            {
                this.statusName = StatusName;
            }
            
            
        
            public String getAppliesTo()
            {
                return this.appliesTo;
            }
            
            public void setAppliesTo(String AppliesTo)
            {
                this.appliesTo = AppliesTo;
            }
            
            
         
        
        
            
    }

