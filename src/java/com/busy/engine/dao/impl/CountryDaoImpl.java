





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Country's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        } 
    
        @Override
        public void add(Country obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("Country's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Name, String IsoCode, Integer IsoNumber, Integer HasVat)
        {   
            int id = 0;
            try
            {
                
                Country.checkColumnSize(Name, 100);
                Country.checkColumnSize(IsoCode, 5);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO country(Name,IsoCode,IsoNumber,HasVat) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, IsoCode);
                preparedStatement.setInt(3, IsoNumber);
                preparedStatement.setInt(4, HasVat);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from country;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addCountry error: " + ex.getMessage());
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
                System.out.println("updateCountry error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer CountryId,String Name,String IsoCode,Integer IsoNumber,Integer HasVat)
        {  
            try
            {   
                
                Country.checkColumnSize(Name, 100);
                Country.checkColumnSize(IsoCode, 5);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE country SET Name=?,IsoCode=?,IsoNumber=?,HasVat=? WHERE CountryId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, IsoCode);
                preparedStatement.setInt(3, IsoNumber);
                preparedStatement.setInt(4, HasVat);
                preparedStatement.setInt(5, CountryId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateCountry error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("country");
        }
        
        
        @Override
        public Country getRelatedInfo(Country country)
        {
              
            
            return country;
        }
        
        
        @Override
        public Country getRelatedObjects(Country country)
        {
            country.setShippingList(new ShippingDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
country.setStateProvinceList(new StateProvinceDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
country.setTaxRateList(new TaxRateDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
             
            return country;
        }
        
        
        
        @Override
        public void remove(Country obj)
        {            
            try
            {
                updateQuery("DELETE FROM country WHERE CountryId=" + obj.getCountryId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCountryById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM country WHERE CountryId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCountryById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM country;");
            }
            catch (Exception ex)
            {
                System.out.println("Country's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM country WHERE " + Country.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Country's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Country getRelatedShippingList(Country country)
        {           
            country.setShippingList(new ShippingDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
            return country;
        }        
                    
        public Country getRelatedStateProvinceList(Country country)
        {           
            country.setStateProvinceList(new StateProvinceDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
            return country;
        }        
                    
        public Country getRelatedTaxRateList(Country country)
        {           
            country.setTaxRateList(new TaxRateDaoImpl().findByColumn("CountryId", country.getCountryId().toString(),null,null));
            return country;
        }        
        
                             
    }

