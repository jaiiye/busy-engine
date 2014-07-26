





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ServiceCharge's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
        } 
    
        @Override
        public void add(ServiceCharge obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String ChargeName, String Description, String Rate, String Units, Date Date, Integer UserServiceId)
        {   
            int id = 0;
            try
            {
                
                ServiceCharge.checkColumnSize(ChargeName, 100);
                ServiceCharge.checkColumnSize(Description, 65535);
                ServiceCharge.checkColumnSize(Rate, 12);
                ServiceCharge.checkColumnSize(Units, 12);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO service_charge(ChargeName,Description,Rate,Units,Date,UserServiceId) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, ChargeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Rate);
                preparedStatement.setString(4, Units);
                preparedStatement.setDate(5, new java.sql.Date(Date.getTime()));
                preparedStatement.setInt(6, UserServiceId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_charge;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addServiceCharge error: " + ex.getMessage());
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
                System.out.println("updateServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ServiceChargeId,String ChargeName,String Description,String Rate,String Units,Date Date,Integer UserServiceId)
        {  
            try
            {   
                
                ServiceCharge.checkColumnSize(ChargeName, 100);
                ServiceCharge.checkColumnSize(Description, 65535);
                ServiceCharge.checkColumnSize(Rate, 12);
                ServiceCharge.checkColumnSize(Units, 12);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service_charge SET ChargeName=?,Description=?,Rate=?,Units=?,Date=?,UserServiceId=? WHERE ServiceChargeId=?;");                    
                preparedStatement.setString(1, ChargeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Rate);
                preparedStatement.setString(4, Units);
                preparedStatement.setDate(5, new java.sql.Date(Date.getTime()));
                preparedStatement.setInt(6, UserServiceId);
                preparedStatement.setInt(7, ServiceChargeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("service_charge");
        }
        
        
        @Override
        public ServiceCharge getRelatedInfo(ServiceCharge service_charge)
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
              
            
            return service_charge;
        }
        
        
        @Override
        public ServiceCharge getRelatedObjects(ServiceCharge service_charge)
        {
            service_charge.setServiceList(new ServiceDaoImpl().findByColumn("ServiceChargeId", service_charge.getServiceChargeId().toString(),null,null));
             
            return service_charge;
        }
        
        
        
        @Override
        public void remove(ServiceCharge obj)
        {            
            try
            {
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + obj.getServiceChargeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceChargeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceChargeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_charge;");
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_charge WHERE " + ServiceCharge.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ServiceCharge getRelatedServiceList(ServiceCharge service_charge)
        {           
            service_charge.setServiceList(new ServiceDaoImpl().findByColumn("ServiceChargeId", service_charge.getServiceChargeId().toString(),null,null));
            return service_charge;
        }        
        
                             
    }

