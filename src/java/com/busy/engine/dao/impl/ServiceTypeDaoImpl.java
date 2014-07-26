





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceTypeDaoImpl extends BasicConnection implements Serializable, ServiceTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ServiceType find(Integer id)
        {
            return findByColumn("ServiceTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ServiceType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ServiceType> service_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("service_type");
                while(rs.next())
                {
                    service_type.add(ServiceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
         
        }
        
        @Override
        public ArrayList<ServiceType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ServiceType> service_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("service_type", limit, offset);
                while (rs.next())
                {
                    service_typeList.add(ServiceType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ServiceType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_typeList;
        }
        
        @Override
        public ArrayList<ServiceType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ServiceType> service_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("service_type", ServiceType.checkColumnName(columnName), columnValue, ServiceType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service_type.add(ServiceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object ServiceType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
        } 
    
        @Override
        public void add(ServiceType obj)
        {
            try
            {
                
                ServiceType.checkColumnSize(obj.getTypeName(), 100);
                ServiceType.checkColumnSize(obj.getDesciption(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO service_type(TypeName,Desciption) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDesciption());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String Desciption)
        {   
            int id = 0;
            try
            {
                
                ServiceType.checkColumnSize(TypeName, 100);
                ServiceType.checkColumnSize(Desciption, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO service_type(TypeName,Desciption) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Desciption);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public ServiceType update(ServiceType obj)
        {
           try
            {   
                
                ServiceType.checkColumnSize(obj.getTypeName(), 100);
                ServiceType.checkColumnSize(obj.getDesciption(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE service_type SET TypeName=?,Desciption=? WHERE ServiceTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDesciption());
                preparedStatement.setInt(3, obj.getServiceTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ServiceTypeId,String TypeName,String Desciption)
        {  
            try
            {   
                
                ServiceType.checkColumnSize(TypeName, 100);
                ServiceType.checkColumnSize(Desciption, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE service_type SET TypeName=?,Desciption=? WHERE ServiceTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Desciption);
                preparedStatement.setInt(3, ServiceTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("service_type");
        }
        
        
        @Override
        public ServiceType getRelatedInfo(ServiceType service_type)
        {
              
            
            return service_type;
        }
        
        
        @Override
        public ServiceType getRelatedObjects(ServiceType service_type)
        {
            service_type.setServiceList(new ServiceDaoImpl().findByColumn("ServiceTypeId", service_type.getServiceTypeId().toString(),null,null));
             
            return service_type;
        }
        
        
        
        @Override
        public void remove(ServiceType obj)
        {            
            try
            {
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + obj.getServiceTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_type;");
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_type WHERE " + ServiceType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ServiceType getRelatedServiceList(ServiceType service_type)
        {           
            service_type.setServiceList(new ServiceDaoImpl().findByColumn("ServiceTypeId", service_type.getServiceTypeId().toString(),null,null));
            return service_type;
        }        
        
                             
    }

