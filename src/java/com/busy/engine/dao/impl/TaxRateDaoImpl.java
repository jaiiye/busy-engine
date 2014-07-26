





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TaxRateDaoImpl extends BasicConnection implements Serializable, TaxRateDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public TaxRate find(Integer id)
        {
            return findByColumn("TaxRateId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<TaxRate> findAll(Integer limit, Integer offset)
        {
            ArrayList<TaxRate> tax_rate = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("tax_rate");
                while(rs.next())
                {
                    tax_rate.add(TaxRate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TaxRate object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
         
        }
        
        @Override
        public ArrayList<TaxRate> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TaxRate> tax_rateList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("tax_rate", limit, offset);
                while (rs.next())
                {
                    tax_rateList.add(TaxRate.process(rs));
                }

                
                    for(TaxRate tax_rate : tax_rateList)
                    {
                        
                            getRecordById("StateProvince", tax_rate.getStateProvinceId().toString());
                            tax_rate.setStateProvince(StateProvince.process(rs));               
                        
                            getRecordById("Country", tax_rate.getCountryId().toString());
                            tax_rate.setCountry(Country.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object TaxRate method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rateList;
        }
        
        @Override
        public ArrayList<TaxRate> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TaxRate> tax_rate = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("tax_rate", TaxRate.checkColumnName(columnName), columnValue, TaxRate.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   tax_rate.add(TaxRate.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object TaxRate's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
        } 
    
        @Override
        public void add(TaxRate obj)
        {
            try
            {
                
                TaxRate.checkColumnSize(obj.getTaxCategory(), 100);
                
                TaxRate.checkColumnSize(obj.getZipPostalCode(), 15);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO tax_rate(TaxCategory,Percentage,ZipPostalCode,StateProvinceId,CountryId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTaxCategory());
                preparedStatement.setDouble(2, obj.getPercentage());
                preparedStatement.setString(3, obj.getZipPostalCode());
                preparedStatement.setInt(4, obj.getStateProvinceId());
                preparedStatement.setInt(5, obj.getCountryId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TaxCategory, Double Percentage, String ZipPostalCode, Integer StateProvinceId, Integer CountryId)
        {   
            int id = 0;
            try
            {
                
                TaxRate.checkColumnSize(TaxCategory, 100);
                
                TaxRate.checkColumnSize(ZipPostalCode, 15);
                
                
                                            
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
        
        
        @Override
        public TaxRate update(TaxRate obj)
        {
           try
            {   
                
                TaxRate.checkColumnSize(obj.getTaxCategory(), 100);
                
                TaxRate.checkColumnSize(obj.getZipPostalCode(), 15);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tax_rate SET TaxCategory=?,Percentage=?,ZipPostalCode=?,StateProvinceId=?,CountryId=? WHERE TaxRateId=?;");                    
                preparedStatement.setString(1, obj.getTaxCategory());
                preparedStatement.setDouble(2, obj.getPercentage());
                preparedStatement.setString(3, obj.getZipPostalCode());
                preparedStatement.setInt(4, obj.getStateProvinceId());
                preparedStatement.setInt(5, obj.getCountryId());
                preparedStatement.setInt(6, obj.getTaxRateId());
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
            return obj;
        }
        
        public static void update(Integer TaxRateId,String TaxCategory,Double Percentage,String ZipPostalCode,Integer StateProvinceId,Integer CountryId)
        {  
            try
            {   
                
                TaxRate.checkColumnSize(TaxCategory, 100);
                
                TaxRate.checkColumnSize(ZipPostalCode, 15);
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("tax_rate");
        }
        
        
        @Override
        public TaxRate getRelatedInfo(TaxRate tax_rate)
        {
            
                try
                { 
                    
                        getRecordById("StateProvince", tax_rate.getStateProvinceId().toString());
                        tax_rate.setStateProvince(StateProvince.process(rs));               
                    
                        getRecordById("Country", tax_rate.getCountryId().toString());
                        tax_rate.setCountry(Country.process(rs));               
                    

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
        
        
        @Override
        public TaxRate getRelatedObjects(TaxRate tax_rate)
        {
                         
            return tax_rate;
        }
        
        
        
        @Override
        public void remove(TaxRate obj)
        {            
            try
            {
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + obj.getTaxRateId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM tax_rate;");
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM tax_rate WHERE " + TaxRate.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

