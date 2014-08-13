





































    package com.busy.engine.dao;

import com.busy.engine.entity.StateProvince;
import com.busy.engine.entity.Country;
import com.busy.engine.entity.TaxRate;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("TaxRate's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tax_rate;
        } 
    
        @Override
        public int add(TaxRate obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from tax_rate;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's add method error: " + ex.getMessage());
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
                System.out.println("TaxRate's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("tax_rate");
        }
        
        
        @Override
        public void getRelatedInfo(TaxRate tax_rate)
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
              
        }
        
        @Override
        public void getRelatedObjects(TaxRate tax_rate)
        {
             
        }
        
        @Override
        public boolean remove(TaxRate obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + obj.getTaxRateId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM tax_rate WHERE TaxRateId=" + id + ";");           
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
                updateQuery("DELETE FROM tax_rate;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM tax_rate WHERE " + TaxRate.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TaxRate's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

