





































    package com.busy.engine.dao;

import com.busy.engine.entity.StateProvince;
import com.busy.engine.entity.Country;
import com.busy.engine.entity.Shipping;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ShippingDaoImpl extends BasicConnection implements Serializable, ShippingDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Shipping find(Integer id)
        {
            return findByColumn("ShippingId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Shipping> findAll(Integer limit, Integer offset)
        {
            ArrayList<Shipping> shipping = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("shipping");
                while(rs.next())
                {
                    shipping.add(Shipping.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Shipping object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
         
        }
        
        @Override
        public ArrayList<Shipping> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Shipping> shippingList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("shipping", limit, offset);
                while (rs.next())
                {
                    shippingList.add(Shipping.process(rs));
                }

                
                    for(Shipping shipping : shippingList)
                    {
                        
                            getRecordById("StateProvince", shipping.getStateProvinceId().toString());
                            shipping.setStateProvince(StateProvince.process(rs));               
                        
                            getRecordById("Country", shipping.getCountryId().toString());
                            shipping.setCountry(Country.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Shipping method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shippingList;
        }
        
        @Override
        public ArrayList<Shipping> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Shipping> shipping = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("shipping", Shipping.checkColumnName(columnName), columnValue, Shipping.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   shipping.add(Shipping.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Shipping's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        } 
    
        @Override
        public int add(Shipping obj)
        {
            int id = 0;
            try
            {
                
                Shipping.checkColumnSize(obj.getMethodName(), 100);
                
                Shipping.checkColumnSize(obj.getUnitOfMeasure(), 100);
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shipping(MethodName,Quantity,UnitOfMeasure,RatePerUnitCost,AdditionalCost,StateProvinceId,CountryId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getMethodName());
                preparedStatement.setDouble(2, obj.getQuantity());
                preparedStatement.setString(3, obj.getUnitOfMeasure());
                preparedStatement.setDouble(4, obj.getRatePerUnitCost());
                preparedStatement.setDouble(5, obj.getAdditionalCost());
                preparedStatement.setInt(6, obj.getStateProvinceId());
                preparedStatement.setInt(7, obj.getCountryId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shipping;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Shipping update(Shipping obj)
        {
           try
            {   
                
                Shipping.checkColumnSize(obj.getMethodName(), 100);
                
                Shipping.checkColumnSize(obj.getUnitOfMeasure(), 100);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipping SET MethodName=?,Quantity=?,UnitOfMeasure=?,RatePerUnitCost=?,AdditionalCost=?,StateProvinceId=?,CountryId=? WHERE ShippingId=?;");                    
                preparedStatement.setString(1, obj.getMethodName());
                preparedStatement.setDouble(2, obj.getQuantity());
                preparedStatement.setString(3, obj.getUnitOfMeasure());
                preparedStatement.setDouble(4, obj.getRatePerUnitCost());
                preparedStatement.setDouble(5, obj.getAdditionalCost());
                preparedStatement.setInt(6, obj.getStateProvinceId());
                preparedStatement.setInt(7, obj.getCountryId());
                preparedStatement.setInt(8, obj.getShippingId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("shipping");
        }
        
        
        @Override
        public void getRelatedInfo(Shipping shipping)
        {
            
                try
                { 
                    
                            getRecordById("StateProvince", shipping.getStateProvinceId().toString());
                            shipping.setStateProvince(StateProvince.process(rs));                                       
                    
                            getRecordById("Country", shipping.getCountryId().toString());
                            shipping.setCountry(Country.process(rs));                                       
                    
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
        public void getRelatedObjects(Shipping shipping)
        {
            shipping.setOrderList(new OrderDaoImpl().findByColumn("ShippingId", shipping.getShippingId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Shipping obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + obj.getShippingId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + id + ";");           
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
                updateQuery("DELETE FROM shipping;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipping WHERE " + Shipping.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedOrderList(Shipping shipping)
        {           
            shipping.setOrderList(new OrderDaoImpl().findByColumn("ShippingId", shipping.getShippingId().toString(),null,null));
        }        
        
                             
    }

