


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
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
            if(column.equals(TaxRate.PROP_TAX_RATE_ID) || column.equals(TaxRate.PROP_TAX_CATEGORY) || column.equals(TaxRate.PROP_STATE_PROVINCE) || column.equals(TaxRate.PROP_ZIP_POSTAL_CODE) || column.equals(TaxRate.PROP_COUNTRY) || column.equals(TaxRate.PROP_COUNTRY_CODE) || column.equals(TaxRate.PROP_PERCENTAGE) )
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
            if (column.equals(TaxRate.PROP_TAX_RATE_ID) || column.equals(TaxRate.PROP_PERCENTAGE) )
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
            return new TaxRate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
        }
        
        public static int addTaxRate(String TaxCategory, String StateProvince, String ZipPostalCode, String Country, String CountryCode, Double Percentage)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TaxCategory, 100);
                checkColumnSize(StateProvince, 100);
                checkColumnSize(ZipPostalCode, 15);
                checkColumnSize(Country, 150);
                checkColumnSize(CountryCode, 10);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO tax_rate(TaxCategory,StateProvince,ZipPostalCode,Country,CountryCode,Percentage) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, TaxCategory);
                preparedStatement.setString(2, StateProvince);
                preparedStatement.setString(3, ZipPostalCode);
                preparedStatement.setString(4, Country);
                preparedStatement.setString(5, CountryCode);
                preparedStatement.setDouble(6, Percentage);
                
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
                
        public static void updateTaxRate(Integer TaxRateId,String TaxCategory,String StateProvince,String ZipPostalCode,String Country,String CountryCode,Double Percentage)
        {  
            try
            {   
                
                checkColumnSize(TaxCategory, 100);
                checkColumnSize(StateProvince, 100);
                checkColumnSize(ZipPostalCode, 15);
                checkColumnSize(Country, 150);
                checkColumnSize(CountryCode, 10);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tax_rate SET TaxCategory=?,StateProvince=?,ZipPostalCode=?,Country=?,CountryCode=?,Percentage=? WHERE TaxRateId=?;");                    
                preparedStatement.setString(1, TaxCategory);
                preparedStatement.setString(2, StateProvince);
                preparedStatement.setString(3, ZipPostalCode);
                preparedStatement.setString(4, Country);
                preparedStatement.setString(5, CountryCode);
                preparedStatement.setDouble(6, Percentage);
                preparedStatement.setInt(7, TaxRateId);
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

