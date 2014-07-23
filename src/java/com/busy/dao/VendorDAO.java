





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class VendorDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Vendor.PROP_VENDOR_ID) || column.equals(Vendor.PROP_VENDOR_NAME) || column.equals(Vendor.PROP_DESCRIPTION) || column.equals(Vendor.PROP_RANK) || column.equals(Vendor.PROP_VENDOR_STATUS) || column.equals(Vendor.PROP_META_TAG_ID) || column.equals(Vendor.PROP_TEMPLATE_ID) || column.equals(Vendor.PROP_VENDOR_TYPE_ID) )
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
            if (column.equals(Vendor.PROP_VENDOR_ID) || column.equals(Vendor.PROP_RANK) || column.equals(Vendor.PROP_VENDOR_STATUS) || column.equals(Vendor.PROP_META_TAG_ID) || column.equals(Vendor.PROP_TEMPLATE_ID) || column.equals(Vendor.PROP_VENDOR_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Vendor processVendor(ResultSet rs) throws SQLException
        {        
            return new Vendor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addVendor(String VendorName, String Description, Integer Rank, Integer VendorStatus, Integer MetaTagId, Integer TemplateId, Integer VendorTypeId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(VendorName, 100);
                checkColumnSize(Description, 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO vendor(VendorName,Description,Rank,VendorStatus,MetaTagId,TemplateId,VendorTypeId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, VendorName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setInt(4, VendorStatus);
                preparedStatement.setInt(5, MetaTagId);
                preparedStatement.setInt(6, TemplateId);
                preparedStatement.setInt(7, VendorTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from vendor;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addVendor error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllVendorCount()
        {
            return getAllRecordsCountByTableName("vendor");        
        }
        
        public static ArrayList<Vendor> getAllVendor()
        {
            ArrayList<Vendor> vendor = new ArrayList<Vendor>();
            try
            {
                getAllRecordsByTableName("vendor");
                while(rs.next())
                {
                    vendor.add(processVendor(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllVendor error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        }
        
        public static ArrayList<Vendor> getAllVendorWithRelatedInfo()
        {
            ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
            try
            {
                getAllRecordsByTableName("vendor");
                while (rs.next())
                {
                    vendorList.add(processVendor(rs));
                }

                
                    for(Vendor vendor : vendorList)
                    {
                        
                            getRecordById("MetaTag", vendor.getMetaTagId().toString());
                            vendor.setMetaTag(MetaTagDAO.processMetaTag(rs));               
                        
                            getRecordById("Template", vendor.getTemplateId().toString());
                            vendor.setTemplate(TemplateDAO.processTemplate(rs));               
                        
                            getRecordById("VendorType", vendor.getVendorTypeId().toString());
                            vendor.setVendorType(VendorTypeDAO.processVendorType(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllVendorWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendorList;
        }
        
        
        public static Vendor getRelatedInfo(Vendor vendor)
        {
           
                
                    try
                    { 
                        
                            getRecordById("MetaTag", vendor.getMetaTagId().toString());
                            vendor.setMetaTag(MetaTagDAO.processMetaTag(rs));               
                        
                            getRecordById("Template", vendor.getTemplateId().toString());
                            vendor.setTemplate(TemplateDAO.processTemplate(rs));               
                        
                            getRecordById("VendorType", vendor.getVendorTypeId().toString());
                            vendor.setVendorType(VendorTypeDAO.processVendorType(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return vendor;
        }
        
        public static Vendor getAllRelatedObjects(Vendor vendor)
        {           
            vendor.setItemList(ItemDAO.getAllItemByColumn("VendorId", vendor.getVendorId().toString()));
             
            return vendor;
        }
        
        
                    
        public static Vendor getRelatedItemList(Vendor vendor)
        {           
            vendor.setItemList(ItemDAO.getAllItemByColumn("VendorId", vendor.getVendorId().toString()));
            return vendor;
        }        
        
                
        public static ArrayList<Vendor> getVendorPaged(int limit, int offset)
        {
            ArrayList<Vendor> vendor = new ArrayList<Vendor>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("vendor", limit, offset);
                while (rs.next())
                {
                    vendor.add(processVendor(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getVendorPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        } 
        
        public static ArrayList<Vendor> getAllVendorByColumn(String columnName, String columnValue)
        {
            ArrayList<Vendor> vendor = new ArrayList<Vendor>();
            try
            {
                getAllRecordsByColumn("vendor", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    vendor.add(processVendor(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllVendorByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        }
        
        public static Vendor getVendorByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Vendor vendor = new Vendor();
            try
            {
                getRecordsByColumnWithLimitAndOffset("vendor", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   vendor = processVendor(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getVendorByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        }                
                
        public static void updateVendor(Integer VendorId,String VendorName,String Description,Integer Rank,Integer VendorStatus,Integer MetaTagId,Integer TemplateId,Integer VendorTypeId)
        {  
            try
            {   
                
                checkColumnSize(VendorName, 100);
                checkColumnSize(Description, 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE vendor SET VendorName=?,Description=?,Rank=?,VendorStatus=?,MetaTagId=?,TemplateId=?,VendorTypeId=? WHERE VendorId=?;");                    
                preparedStatement.setString(1, VendorName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setInt(4, VendorStatus);
                preparedStatement.setInt(5, MetaTagId);
                preparedStatement.setInt(6, TemplateId);
                preparedStatement.setInt(7, VendorTypeId);
                preparedStatement.setInt(8, VendorId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateVendor error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllVendor()
        {
            try
            {
                updateQuery("DELETE FROM vendor;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllVendor error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteVendorById(String id)
        {
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteVendorById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteVendorByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM vendor WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteVendorByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

