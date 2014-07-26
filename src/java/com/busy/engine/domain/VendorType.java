











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class VendorType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_VENDOR_TYPE_ID = "VendorTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer vendorTypeId;
                
        private String typeName;
                
                 
        ArrayList<Vendor> vendorList; 
        
        

        public VendorType()
        {
            this.vendorTypeId = 0; 
       this.typeName = ""; 
        
       vendorList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return vendorTypeId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(VendorType.PROP_VENDOR_TYPE_ID) || column.equals(VendorType.PROP_TYPE_NAME) )
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
            if (column.equals(VendorType.PROP_VENDOR_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static VendorType process(ResultSet rs) throws SQLException
        {        
            return new VendorType(rs.getInt(1), rs.getString(2));
        }
              
       public VendorType(Integer VendorTypeId, String TypeName)
       {
            this.vendorTypeId = VendorTypeId;
       this.typeName = TypeName;
              
       vendorList = null; 
        
       } 
        
             
        
            public Integer getVendorTypeId()
            {
                return this.vendorTypeId;
            }
            
            public void setVendorTypeId(Integer VendorTypeId)
            {
                this.vendorTypeId = VendorTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
         
        
        
            public ArrayList<Vendor> getVendorList()
            {
                return this.vendorList;
            }
            
            public void setVendorList(ArrayList<Vendor> vendorList)
            {
                this.vendorList = vendorList;
            }
        
            
    }

