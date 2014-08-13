





































    package com.busy.engine.dao;

import com.busy.engine.entity.MetaTag;
import com.busy.engine.entity.Template;
import com.busy.engine.entity.VendorType;
import com.busy.engine.entity.Vendor;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("Vendor's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor;
        } 
    
        @Override
        public int add(Vendor obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from vendor;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's add method error: " + ex.getMessage());
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
                System.out.println("Vendor's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("vendor");
        }
        
        
        @Override
        public void getRelatedInfo(Vendor vendor)
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
              
        }
        
        @Override
        public void getRelatedObjects(Vendor vendor)
        {
            vendor.setItemList(new ItemDaoImpl().findByColumn("VendorId", vendor.getVendorId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Vendor obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + obj.getVendorId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM vendor WHERE VendorId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM vendor;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM vendor WHERE " + Vendor.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Vendor's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedItemList(Vendor vendor)
        {           
            vendor.setItemList(new ItemDaoImpl().findByColumn("VendorId", vendor.getVendorId().toString(),null,null));
        }        
        
                             
    }

