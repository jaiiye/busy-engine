





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Shipping's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return shipping;
        } 
    
        @Override
        public void add(Shipping obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String MethodName, Double Quantity, String UnitOfMeasure, Double RatePerUnitCost, Double AdditionalCost, Integer StateProvinceId, Integer CountryId)
        {   
            int id = 0;
            try
            {
                
                Shipping.checkColumnSize(MethodName, 100);
                
                Shipping.checkColumnSize(UnitOfMeasure, 100);
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO shipping(MethodName,Quantity,UnitOfMeasure,RatePerUnitCost,AdditionalCost,StateProvinceId,CountryId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, MethodName);
                preparedStatement.setDouble(2, Quantity);
                preparedStatement.setString(3, UnitOfMeasure);
                preparedStatement.setDouble(4, RatePerUnitCost);
                preparedStatement.setDouble(5, AdditionalCost);
                preparedStatement.setInt(6, StateProvinceId);
                preparedStatement.setInt(7, CountryId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from shipping;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addShipping error: " + ex.getMessage());
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
                System.out.println("updateShipping error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ShippingId,String MethodName,Double Quantity,String UnitOfMeasure,Double RatePerUnitCost,Double AdditionalCost,Integer StateProvinceId,Integer CountryId)
        {  
            try
            {   
                
                Shipping.checkColumnSize(MethodName, 100);
                
                Shipping.checkColumnSize(UnitOfMeasure, 100);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE shipping SET MethodName=?,Quantity=?,UnitOfMeasure=?,RatePerUnitCost=?,AdditionalCost=?,StateProvinceId=?,CountryId=? WHERE ShippingId=?;");                    
                preparedStatement.setString(1, MethodName);
                preparedStatement.setDouble(2, Quantity);
                preparedStatement.setString(3, UnitOfMeasure);
                preparedStatement.setDouble(4, RatePerUnitCost);
                preparedStatement.setDouble(5, AdditionalCost);
                preparedStatement.setInt(6, StateProvinceId);
                preparedStatement.setInt(7, CountryId);
                preparedStatement.setInt(8, ShippingId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateShipping error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("shipping");
        }
        
        
        @Override
        public Shipping getRelatedInfo(Shipping shipping)
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
              
            
            return shipping;
        }
        
        
        @Override
        public Shipping getRelatedObjects(Shipping shipping)
        {
            shipping.setOrderList(new OrderDaoImpl().findByColumn("ShippingId", shipping.getShippingId().toString(),null,null));
             
            return shipping;
        }
        
        
        
        @Override
        public void remove(Shipping obj)
        {            
            try
            {
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + obj.getShippingId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShippingById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipping WHERE ShippingId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteShippingById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipping;");
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM shipping WHERE " + Shipping.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Shipping's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Shipping getRelatedOrderList(Shipping shipping)
        {           
            shipping.setOrderList(new OrderDaoImpl().findByColumn("ShippingId", shipping.getShippingId().toString(),null,null));
            return shipping;
        }        
        
                             
    }

