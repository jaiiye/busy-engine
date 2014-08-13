





































    package com.busy.engine.dao;

import com.busy.engine.entity.Country;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CountryDaoImpl extends BasicConnection implements Serializable, CountryDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Country find(Integer id)
        {
            return findByColumn("CountryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Country> findAll(Integer limit, Integer offset)
        {
            ArrayList<Country> country = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("country");
                while(rs.next())
                {
                    country.add(Country.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Country object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
         
        }
        
        @Override
        public ArrayList<Country> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Country> countryList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("country", limit, offset);
                while (rs.next())
                {
                    countryList.add(Country.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Country method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return countryList;
        }
        
        @Override
        public ArrayList<Country> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Country> country = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("country", Country.checkColumnName(columnName), columnValue, Country.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   country.add(Country.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Country's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        } 
    
        @Override
        public int add(Country obj)
        {
            int id = 0;
            try
            {
                
                Country.checkColumnSize(obj.getName(), 100);
                Country.checkColumnSize(obj.getIsoCode(), 5);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO country(Name,IsoCode,IsoNumber,HasVat) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getIsoCode());
                preparedStatement.setInt(3, obj.getIsoNumber());
                preparedStatement.setInt(4, obj.getHasVat());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from country;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Country's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Country update(Country obj)
        {
           try
            {   
                
                Country.checkColumnSize(obj.getName(), 100);
                Country.checkColumnSize(obj.getIsoCode(), 5);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE country SET Name=?,IsoCode=?,IsoNumber=?,HasVat=? WHERE CountryId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getIsoCode());
                preparedStatement.setInt(3, obj.getIsoNumber());
                preparedStatement.setInt(4, obj.getHasVat());
                preparedStatement.setInt(5, obj.getCountryId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Country's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("country");
        }
        
        
        @Override
        public void getRelatedInfo(Country country)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Country country)
        {
            country.setShippingList(new ShippingDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
country.setStateProvinceList(new StateProvinceDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
country.setTaxRateList(new TaxRateDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Country obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM country WHERE CountryId=" + obj.getCountryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Country's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM country WHERE CountryId=" + id + ";");           
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
                updateQuery("DELETE FROM country;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Country's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM country WHERE " + Country.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Country's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedShippingList(Country country)
        {           
            country.setShippingList(new ShippingDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
        }        
                    
        public void getRelatedStateProvinceList(Country country)
        {           
            country.setStateProvinceList(new StateProvinceDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
        }        
                    
        public void getRelatedTaxRateList(Country country)
        {           
            country.setTaxRateList(new TaxRateDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
        }        
        
                             
    }

