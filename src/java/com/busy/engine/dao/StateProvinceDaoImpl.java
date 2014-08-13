





































    package com.busy.engine.dao;

import com.busy.engine.entity.StateProvince;
import com.busy.engine.entity.Country;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class StateProvinceDaoImpl extends BasicConnection implements Serializable, StateProvinceDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public StateProvince find(Integer id)
        {
            return findByColumn("StateProvinceId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<StateProvince> findAll(Integer limit, Integer offset)
        {
            ArrayList<StateProvince> state_province = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("state_province");
                while(rs.next())
                {
                    state_province.add(StateProvince.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("StateProvince object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
         
        }
        
        @Override
        public ArrayList<StateProvince> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<StateProvince> state_provinceList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("state_province", limit, offset);
                while (rs.next())
                {
                    state_provinceList.add(StateProvince.process(rs));
                }

                
                    for(StateProvince state_province : state_provinceList)
                    {
                        
                            getRecordById("Country", state_province.getCountryId().toString());
                            state_province.setCountry(Country.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object StateProvince method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_provinceList;
        }
        
        @Override
        public ArrayList<StateProvince> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<StateProvince> state_province = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("state_province", StateProvince.checkColumnName(columnName), columnValue, StateProvince.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   state_province.add(StateProvince.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("StateProvince's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
        } 
    
        @Override
        public int add(StateProvince obj)
        {
            int id = 0;
            try
            {
                
                StateProvince.checkColumnSize(obj.getName(), 100);
                StateProvince.checkColumnSize(obj.getAbbreviation(), 10);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO state_province(Name,Abbreviation,CountryId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getAbbreviation());
                preparedStatement.setInt(3, obj.getCountryId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from state_province;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public StateProvince update(StateProvince obj)
        {
           try
            {   
                
                StateProvince.checkColumnSize(obj.getName(), 100);
                StateProvince.checkColumnSize(obj.getAbbreviation(), 10);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE state_province SET Name=?,Abbreviation=?,CountryId=? WHERE StateProvinceId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getAbbreviation());
                preparedStatement.setInt(3, obj.getCountryId());
                preparedStatement.setInt(4, obj.getStateProvinceId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("state_province");
        }
        
        
        @Override
        public void getRelatedInfo(StateProvince state_province)
        {
            
                try
                { 
                    
                            getRecordById("Country", state_province.getCountryId().toString());
                            state_province.setCountry(Country.process(rs));                                       
                    
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
        public void getRelatedObjects(StateProvince state_province)
        {
            state_province.setShippingList(new ShippingDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
state_province.setTaxRateList(new TaxRateDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(StateProvince obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + obj.getStateProvinceId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + id + ";");           
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
                updateQuery("DELETE FROM state_province;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM state_province WHERE " + StateProvince.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedShippingList(StateProvince state_province)
        {           
            state_province.setShippingList(new ShippingDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
        }        
                    
        public void getRelatedTaxRateList(StateProvince state_province)
        {           
            state_province.setTaxRateList(new TaxRateDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
        }        
        
                             
    }

