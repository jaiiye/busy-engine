


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class VendorTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
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
                               
        public static VendorType processVendorType(ResultSet rs) throws SQLException
        {        
            return new VendorType(rs.getInt(1), rs.getString(2));
        }
        
        public static int addVendorType(String TypeName)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO vendor_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, TypeName);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from vendor_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addVendorType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllVendorTypeCount()
        {
            return getAllRecordsCountByTableName("vendor_type");        
        }
        
        public static ArrayList<VendorType> getAllVendorType()
        {
            ArrayList<VendorType> vendor_type = new ArrayList<VendorType>();
            try
            {
                getAllRecordsByTableName("vendor_type");
                while(rs.next())
                {
                    vendor_type.add(processVendorType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllVendorType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
        }
                
        public static ArrayList<VendorType> getVendorTypePaged(int limit, int offset)
        {
            ArrayList<VendorType> vendor_type = new ArrayList<VendorType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("vendor_type", limit, offset);
                while (rs.next())
                {
                    vendor_type.add(processVendorType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getVendorTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
        } 
        
        public static ArrayList<VendorType> getAllVendorTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<VendorType> vendor_type = new ArrayList<VendorType>();
            try
            {
                getAllRecordsByColumn("vendor_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    vendor_type.add(processVendorType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllVendorTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
        }
        
        public static VendorType getVendorTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            VendorType vendor_type = new VendorType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("vendor_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   vendor_type = processVendorType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getVendorTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
        }                
                
        public static void updateVendorType(Integer VendorTypeId,String TypeName)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE vendor_type SET TypeName=? WHERE VendorTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, VendorTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateVendorType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllVendorType()
        {
            try
            {
                updateQuery("DELETE FROM vendor_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllVendorType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteVendorTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteVendorTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteVendorTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM vendor_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteVendorTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

