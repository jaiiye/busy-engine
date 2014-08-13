





































    package com.busy.engine.dao;

import com.busy.engine.entity.VendorType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class VendorTypeDaoImpl extends BasicConnection implements Serializable, VendorTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public VendorType find(Integer id)
        {
            return findByColumn("VendorTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<VendorType> findAll(Integer limit, Integer offset)
        {
            ArrayList<VendorType> vendor_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("vendor_type");
                while(rs.next())
                {
                    vendor_type.add(VendorType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("VendorType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
         
        }
        
        @Override
        public ArrayList<VendorType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<VendorType> vendor_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("vendor_type", limit, offset);
                while (rs.next())
                {
                    vendor_typeList.add(VendorType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object VendorType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_typeList;
        }
        
        @Override
        public ArrayList<VendorType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<VendorType> vendor_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("vendor_type", VendorType.checkColumnName(columnName), columnValue, VendorType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   vendor_type.add(VendorType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("VendorType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
        } 
    
        @Override
        public int add(VendorType obj)
        {
            int id = 0;
            try
            {
                
                VendorType.checkColumnSize(obj.getTypeName(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO vendor_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from vendor_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public VendorType update(VendorType obj)
        {
           try
            {   
                
                VendorType.checkColumnSize(obj.getTypeName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE vendor_type SET TypeName=? WHERE VendorTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getVendorTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("vendor_type");
        }
        
        
        @Override
        public void getRelatedInfo(VendorType vendor_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(VendorType vendor_type)
        {
            vendor_type.setVendorList(new VendorDaoImpl().findByColumn("VendorTypeId", vendor_type.getVendorTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(VendorType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + obj.getVendorTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM vendor_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM vendor_type WHERE " + VendorType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedVendorList(VendorType vendor_type)
        {           
            vendor_type.setVendorList(new VendorDaoImpl().findByColumn("VendorTypeId", vendor_type.getVendorTypeId().toString(),null,null));
        }        
        
                             
    }

