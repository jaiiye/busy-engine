





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TaxRateDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TaxRate.PROP_TAX_RATE_ID) || column.equals(TaxRate.PROP_TAX_CATEGORY) || column.equals(TaxRate.PROP_PERCENTAGE) || column.equals(TaxRate.PROP_ZIP_POSTAL_CODE) || column.equals(TaxRate.PROP_STATE_PROVINCE_ID) || column.equals(TaxRate.PROP_COUNTRY_ID) )
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
            if (column.equals(TaxRate.PROP_TAX_RATE_ID) || column.equals(TaxRate.PROP_PERCENTAGE) || column.equals(TaxRate.PROP_STATE_PROVINCE_ID) || column.equals(TaxRate.PROP_COUNTRY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TaxRate processTaxRate(ResultSet rs) throws SQLException
        {        
            return new TaxRate(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
        
        public static int addTaxRate(String TaxCategory, Double Percentage, String ZipPostalCode, Integer StateProvinceId, Integer CountryId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TaxCategory, 100);
                
                checkColumnSize(ZipPostalCode, 15);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO tax_rate(TaxCategory,Percentage,ZipPostalCode,StateProvinceId,CountryId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, TaxCategory);
                preparedStatement.setDouble(2, Percentage);
                preparedStatement.setString(3, ZipPostalCode);
                preparedStatement.setInt(4, StateProvinceId);
                preparedStatement.setInt(5, CountryId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from tax_rate;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTaxRate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllTaxRateCount()
        {
            return getAllRecordsCountByTableName("tax_rate");        
        }
        
        public static ArrayList<TaxRate> getAllTaxRate()
        {
            ArrayList<TaxRate> tax_rate = new ArrayList<TaxRate>();
            try
            {
                getAllRecordsByTableName("tax_rate");
                while(rs.next())
                {
                    tax_rate.add(processTaxRate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTaxRate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
        }
        
        public static ArrayList<TaxRate> getAllTaxRateWithRelatedInfo()
        {
            ArrayList<TaxRate> tax_rateList = new ArrayList<TaxRate>();
            try
            {
                getAllRecordsByTableName("tax_rate");
                while (rs.next())
                {
                    tax_rateList.add(processTaxRate(rs));
                }

                
                    for(TaxRate tax_rate : tax_rateList)
                    {
                        
                            getRecordById("StateProvince", tax_rate.getStateProvinceId().toString());
                            tax_rate.setStateProvince(StateProvinceDAO.processStateProvince(rs));               
                        
                            getRecordById("Country", tax_rate.getCountryId().toString());
                            tax_rate.setCountry(CountryDAO.processCountry(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTaxRateWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rateList;
        }
        
        
        public static TaxRate getRelatedInfo(TaxRate tax_rate)
        {
           
                
                    try
                    { 
                        
                            getRecordById("StateProvince", tax_rate.getStateProvinceId().toString());
                            tax_rate.setStateProvince(StateProvinceDAO.processStateProvince(rs));               
                        
                            getRecordById("Country", tax_rate.getCountryId().toString());
                            tax_rate.setCountry(CountryDAO.processCountry(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return tax_rate;
        }
        
        public static TaxRate getAllRelatedObjects(TaxRate tax_rate)
        {           
                         
            return tax_rate;
        }
        
        
        
                
        public static ArrayList<TaxRate> getTaxRatePaged(int limit, int offset)
        {
            ArrayList<TaxRate> tax_rate = new ArrayList<TaxRate>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("tax_rate", limit, offset);
                while (rs.next())
                {
                    tax_rate.add(processTaxRate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTaxRatePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
        } 
        
        public static ArrayList<TaxRate> getAllTaxRateByColumn(String columnName, String columnValue)
        {
            ArrayList<TaxRate> tax_rate = new ArrayList<TaxRate>();
            try
            {
                getAllRecordsByColumn("tax_rate", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    tax_rate.add(processTaxRate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTaxRateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
        }
        
        public static TaxRate getTaxRateByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            TaxRate tax_rate = new TaxRate();
            try
            {
                getRecordsByColumnWithLimitAndOffset("tax_rate", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   tax_rate = processTaxRate(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTaxRateByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
        }                
                
        public static void updateTaxRate(Integer TaxRateId,String TaxCategory,Double Percentage,String ZipPostalCode,Integer StateProvinceId,Integer CountryId)
        {  
            try
            {   
                
                checkColumnSize(TaxCategory, 100);
                
                checkColumnSize(ZipPostalCode, 15);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tax_rate SET TaxCategory=?,Percentage=?,ZipPostalCode=?,StateProvinceId=?,CountryId=? WHERE TaxRateId=?;");                    
                preparedStatement.setString(1, TaxCategory);
                preparedStatement.setDouble(2, Percentage);
                preparedStatement.setString(3, ZipPostalCode);
                preparedStatement.setInt(4, StateProvinceId);
                preparedStatement.setInt(5, CountryId);
                preparedStatement.setInt(6, TaxRateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTaxRate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllTaxRate()
        {
            try
            {
                updateQuery("DELETE FROM tax_rate;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllTaxRate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTaxRateById(String id)
        {
            try
            {
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTaxRateById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteTaxRateByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM tax_rate WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTaxRateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

