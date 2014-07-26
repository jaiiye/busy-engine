





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class VendorDaoImpl extends BasicConnection implements Serializable, VendorDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Vendor find(Integer id)
        {
            return findByColumn("VendorId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Vendor> findAll(Integer limit, Integer offset)
        {
            ArrayList<Vendor> vendor = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("vendor");
                while(rs.next())
                {
                    vendor.add(Vendor.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Vendor object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
         
        }
        
        @Override
        public ArrayList<Vendor> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Vendor> vendorList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("vendor", limit, offset);
                while (rs.next())
                {
                    vendorList.add(Vendor.process(rs));
                }

                
                    for(Vendor vendor : vendorList)
                    {
                        
                            getRecordById("MetaTag", vendor.getMetaTagId().toString());
                            vendor.setMetaTag(MetaTag.process(rs));               
                        
                            getRecordById("Template", vendor.getTemplateId().toString());
                            vendor.setTemplate(Template.process(rs));               
                        
                            getRecordById("VendorType", vendor.getVendorTypeId().toString());
                            vendor.setVendorType(VendorType.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Vendor method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendorList;
        }
        
        @Override
        public ArrayList<Vendor> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Vendor> vendor = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("vendor", Vendor.checkColumnName(columnName), columnValue, Vendor.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   vendor.add(Vendor.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Vendor's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        } 
    
        @Override
        public void add(Vendor obj)
        {
            try
            {
                
                Vendor.checkColumnSize(obj.getVendorName(), 100);
                Vendor.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO vendor(VendorName,Description,Rank,VendorStatus,MetaTagId,TemplateId,VendorTypeId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getVendorName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getVendorStatus());
                preparedStatement.setInt(5, obj.getMetaTagId());
                preparedStatement.setInt(6, obj.getTemplateId());
                preparedStatement.setInt(7, obj.getVendorTypeId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String VendorName, String Description, Integer Rank, Integer VendorStatus, Integer MetaTagId, Integer TemplateId, Integer VendorTypeId)
        {   
            int id = 0;
            try
            {
                
                Vendor.checkColumnSize(VendorName, 100);
                Vendor.checkColumnSize(Description, 65535);
                
                
                
                
                
                                            
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
        
        
        @Override
        public Vendor update(Vendor obj)
        {
           try
            {   
                
                Vendor.checkColumnSize(obj.getVendorName(), 100);
                Vendor.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE vendor SET VendorName=?,Description=?,Rank=?,VendorStatus=?,MetaTagId=?,TemplateId=?,VendorTypeId=? WHERE VendorId=?;");                    
                preparedStatement.setString(1, obj.getVendorName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getVendorStatus());
                preparedStatement.setInt(5, obj.getMetaTagId());
                preparedStatement.setInt(6, obj.getTemplateId());
                preparedStatement.setInt(7, obj.getVendorTypeId());
                preparedStatement.setInt(8, obj.getVendorId());
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
            return obj;
        }
        
        public static void update(Integer VendorId,String VendorName,String Description,Integer Rank,Integer VendorStatus,Integer MetaTagId,Integer TemplateId,Integer VendorTypeId)
        {  
            try
            {   
                
                Vendor.checkColumnSize(VendorName, 100);
                Vendor.checkColumnSize(Description, 65535);
                
                
                
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("vendor");
        }
        
        
        @Override
        public Vendor getRelatedInfo(Vendor vendor)
        {
            
                try
                { 
                    
                        getRecordById("MetaTag", vendor.getMetaTagId().toString());
                        vendor.setMetaTag(MetaTag.process(rs));               
                    
                        getRecordById("Template", vendor.getTemplateId().toString());
                        vendor.setTemplate(Template.process(rs));               
                    
                        getRecordById("VendorType", vendor.getVendorTypeId().toString());
                        vendor.setVendorType(VendorType.process(rs));               
                    

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
        
        
        @Override
        public Vendor getRelatedObjects(Vendor vendor)
        {
            vendor.setItemList(new ItemDaoImpl().findByColumn("VendorId", vendor.getVendorId().toString(),null,null));
             
            return vendor;
        }
        
        
        
        @Override
        public void remove(Vendor obj)
        {            
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + obj.getVendorId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM vendor;");
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM vendor WHERE " + Vendor.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Vendor getRelatedItemList(Vendor vendor)
        {           
            vendor.setItemList(new ItemDaoImpl().findByColumn("VendorId", vendor.getVendorId().toString(),null,null));
            return vendor;
        }        
        
                             
    }

