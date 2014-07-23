





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CountryDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Country.PROP_COUNTRY_ID) || column.equals(Country.PROP_NAME) || column.equals(Country.PROP_ISO_CODE) || column.equals(Country.PROP_ISO_NUMBER) || column.equals(Country.PROP_HAS_VAT) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(Country.PROP_COUNTRY_ID) || column.equals(Country.PROP_ISO_NUMBER) || column.equals(Country.PROP_HAS_VAT) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Country processCountry(ResultSet rs) throws SQLException
        {        
            return new Country(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addCountry(String Name, String IsoCode, Integer IsoNumber, Integer HasVat)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 100);
                checkColumnSize(IsoCode, 5);
                
                
                                            
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
        
        public static int getAllCountryCount()
        {
            return getAllRecordsCountByTableName("country");        
        }
        
        public static ArrayList<Country> getAllCountry()
        {
            ArrayList<Country> country = new ArrayList<Country>();
            try
            {
                getAllRecordsByTableName("country");
                while(rs.next())
                {
                    country.add(processCountry(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCountry error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        }
        
        public static ArrayList<Country> getAllCountryWithRelatedInfo()
        {
            ArrayList<Country> countryList = new ArrayList<Country>();
            try
            {
                getAllRecordsByTableName("country");
                while (rs.next())
                {
                    countryList.add(processCountry(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCountryWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return countryList;
        }
        
        
        public static Country getRelatedInfo(Country country)
        {
           
                  
            
            return country;
        }
        
        public static Country getAllRelatedObjects(Country country)
        {           
            country.setShippingList(ShippingDAO.getAllShippingByColumn("CountryId", country.getCountryId().toString()));
country.setStateProvinceList(StateProvinceDAO.getAllStateProvinceByColumn("CountryId", country.getCountryId().toString()));
country.setTaxRateList(TaxRateDAO.getAllTaxRateByColumn("CountryId", country.getCountryId().toString()));
             
            return country;
        }
        
        
                    
        public static Country getRelatedShippingList(Country country)
        {           
            country.setShippingList(ShippingDAO.getAllShippingByColumn("CountryId", country.getCountryId().toString()));
            return country;
        }        
                    
        public static Country getRelatedStateProvinceList(Country country)
        {           
            country.setStateProvinceList(StateProvinceDAO.getAllStateProvinceByColumn("CountryId", country.getCountryId().toString()));
            return country;
        }        
                    
        public static Country getRelatedTaxRateList(Country country)
        {           
            country.setTaxRateList(TaxRateDAO.getAllTaxRateByColumn("CountryId", country.getCountryId().toString()));
            return country;
        }        
        
                
        public static ArrayList<Country> getCountryPaged(int limit, int offset)
        {
            ArrayList<Country> country = new ArrayList<Country>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("country", limit, offset);
                while (rs.next())
                {
                    country.add(processCountry(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCountryPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        } 
        
        public static ArrayList<Country> getAllCountryByColumn(String columnName, String columnValue)
        {
            ArrayList<Country> country = new ArrayList<Country>();
            try
            {
                getAllRecordsByColumn("country", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    country.add(processCountry(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCountryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        }
        
        public static Country getCountryByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Country country = new Country();
            try
            {
                getRecordsByColumnWithLimitAndOffset("country", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   country = processCountry(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCountryByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return country;
        }                
                
        public static void updateCountry(Integer CountryId,String Name,String IsoCode,Integer IsoNumber,Integer HasVat)
        {  
            try
            {   
                
                checkColumnSize(Name, 100);
                checkColumnSize(IsoCode, 5);
                
                
                                  
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
        
        public static void deleteAllCountry()
        {
            try
            {
                updateQuery("DELETE FROM country;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllCountry error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteCountryById(String id)
        {
            try
            {
                updateQuery("DELETE FROM country WHERE CountryId=" + id + ";");            
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

        public static void deleteCountryByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM country WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteCountryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

