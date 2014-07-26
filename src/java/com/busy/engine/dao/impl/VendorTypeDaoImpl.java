





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object VendorType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return vendor_type;
        } 
    
        @Override
        public void add(VendorType obj)
        {
            try
            {
                
                VendorType.checkColumnSize(obj.getTypeName(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO vendor_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName)
        {   
            int id = 0;
            try
            {
                
                VendorType.checkColumnSize(TypeName, 100);
                                            
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
                System.out.println("updateVendorType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer VendorTypeId,String TypeName)
        {  
            try
            {   
                
                VendorType.checkColumnSize(TypeName, 100);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("vendor_type");
        }
        
        
        @Override
        public VendorType getRelatedInfo(VendorType vendor_type)
        {
              
            
            return vendor_type;
        }
        
        
        @Override
        public VendorType getRelatedObjects(VendorType vendor_type)
        {
            vendor_type.setVendorList(new VendorDaoImpl().findByColumn("VendorTypeId", vendor_type.getVendorTypeId().toString(),null,null));
             
            return vendor_type;
        }
        
        
        
        @Override
        public void remove(VendorType obj)
        {            
            try
            {
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + obj.getVendorTypeId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM vendor_type WHERE VendorTypeId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM vendor_type;");
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM vendor_type WHERE " + VendorType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("VendorType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public VendorType getRelatedVendorList(VendorType vendor_type)
        {           
            vendor_type.setVendorList(new VendorDaoImpl().findByColumn("VendorTypeId", vendor_type.getVendorTypeId().toString(),null,null));
            return vendor_type;
        }        
        
                             
    }

