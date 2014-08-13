





































    package com.busy.engine.dao;

import com.busy.engine.entity.UserService;
import com.busy.engine.entity.ServiceCharge;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceChargeDaoImpl extends BasicConnection implements Serializable, ServiceChargeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ServiceCharge find(Integer id)
        {
            return findByColumn("ServiceChargeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ServiceCharge> findAll(Integer limit, Integer offset)
        {
            ArrayList<ServiceCharge> service_charge = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("service_charge");
                while(rs.next())
                {
                    service_charge.add(ServiceCharge.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceCharge object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
         
        }
        
        @Override
        public ArrayList<ServiceCharge> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ServiceCharge> service_chargeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("service_charge", limit, offset);
                while (rs.next())
                {
                    service_chargeList.add(ServiceCharge.process(rs));
                }

                
                    for(ServiceCharge service_charge : service_chargeList)
                    {
                        
                            getRecordById("UserService", service_charge.getUserServiceId().toString());
                            service_charge.setUserService(UserService.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ServiceCharge method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_chargeList;
        }
        
        @Override
        public ArrayList<ServiceCharge> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ServiceCharge> service_charge = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("service_charge", ServiceCharge.checkColumnName(columnName), columnValue, ServiceCharge.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service_charge.add(ServiceCharge.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceCharge's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
        } 
    
        @Override
        public int add(ServiceCharge obj)
        {
            int id = 0;
            try
            {
                
                ServiceCharge.checkColumnSize(obj.getChargeName(), 100);
                ServiceCharge.checkColumnSize(obj.getDescription(), 65535);
                ServiceCharge.checkColumnSize(obj.getRate(), 12);
                ServiceCharge.checkColumnSize(obj.getUnits(), 12);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO service_charge(ChargeName,Description,Rate,Units,Date,UserServiceId) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getChargeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRate());
                preparedStatement.setString(4, obj.getUnits());
                preparedStatement.setDate(5, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(6, obj.getUserServiceId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_charge;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ServiceCharge update(ServiceCharge obj)
        {
           try
            {   
                
                ServiceCharge.checkColumnSize(obj.getChargeName(), 100);
                ServiceCharge.checkColumnSize(obj.getDescription(), 65535);
                ServiceCharge.checkColumnSize(obj.getRate(), 12);
                ServiceCharge.checkColumnSize(obj.getUnits(), 12);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service_charge SET ChargeName=?,Description=?,Rate=?,Units=?,Date=?,UserServiceId=? WHERE ServiceChargeId=?;");                    
                preparedStatement.setString(1, obj.getChargeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRate());
                preparedStatement.setString(4, obj.getUnits());
                preparedStatement.setDate(5, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(6, obj.getUserServiceId());
                preparedStatement.setInt(7, obj.getServiceChargeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("service_charge");
        }
        
        
        @Override
        public void getRelatedInfo(ServiceCharge service_charge)
        {
            
                try
                { 
                    
                            getRecordById("UserService", service_charge.getUserServiceId().toString());
                            service_charge.setUserService(UserService.process(rs));                                       
                    
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
        public void getRelatedObjects(ServiceCharge service_charge)
        {
            service_charge.setServiceList(new ServiceDaoImpl().findByColumn("ServiceChargeId", service_charge.getServiceChargeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ServiceCharge obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + obj.getServiceChargeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + id + ";");           
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
                updateQuery("DELETE FROM service_charge;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_charge WHERE " + ServiceCharge.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedServiceList(ServiceCharge service_charge)
        {           
            service_charge.setServiceList(new ServiceDaoImpl().findByColumn("ServiceChargeId", service_charge.getServiceChargeId().toString(),null,null));
        }        
        
                             
    }

