





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class StateProvinceDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(StateProvince.PROP_STATE_PROVINCE_ID) || column.equals(StateProvince.PROP_NAME) || column.equals(StateProvince.PROP_ABBREVIATION) || column.equals(StateProvince.PROP_COUNTRY_ID) )
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
            if (column.equals(StateProvince.PROP_STATE_PROVINCE_ID) || column.equals(StateProvince.PROP_COUNTRY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static StateProvince processStateProvince(ResultSet rs) throws SQLException
        {        
            return new StateProvince(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }
        
        public static int addStateProvince(String Name, String Abbreviation, Integer CountryId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 100);
                checkColumnSize(Abbreviation, 10);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO state_province(Name,Abbreviation,CountryId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Abbreviation);
                preparedStatement.setInt(3, CountryId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from state_province;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addStateProvince error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllStateProvinceCount()
        {
            return getAllRecordsCountByTableName("state_province");        
        }
        
        public static ArrayList<StateProvince> getAllStateProvince()
        {
            ArrayList<StateProvince> state_province = new ArrayList<StateProvince>();
            try
            {
                getAllRecordsByTableName("state_province");
                while(rs.next())
                {
                    state_province.add(processStateProvince(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStateProvince error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
        }
        
        public static ArrayList<StateProvince> getAllStateProvinceWithRelatedInfo()
        {
            ArrayList<StateProvince> state_provinceList = new ArrayList<StateProvince>();
            try
            {
                getAllRecordsByTableName("state_province");
                while (rs.next())
                {
                    state_provinceList.add(processStateProvince(rs));
                }

                
                    for(StateProvince state_province : state_provinceList)
                    {
                        
                            getRecordById("Country", state_province.getCountryId().toString());
                            state_province.setCountry(CountryDAO.processCountry(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStateProvinceWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_provinceList;
        }
        
        
        public static StateProvince getRelatedInfo(StateProvince state_province)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Country", state_province.getCountryId().toString());
                            state_province.setCountry(CountryDAO.processCountry(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return state_province;
        }
        
        public static StateProvince getAllRelatedObjects(StateProvince state_province)
        {           
            state_province.setShippingList(ShippingDAO.getAllShippingByColumn("StateProvinceId", state_province.getStateProvinceId().toString()));
state_province.setTaxRateList(TaxRateDAO.getAllTaxRateByColumn("StateProvinceId", state_province.getStateProvinceId().toString()));
             
            return state_province;
        }
        
        
                    
        public static StateProvince getRelatedShippingList(StateProvince state_province)
        {           
            state_province.setShippingList(ShippingDAO.getAllShippingByColumn("StateProvinceId", state_province.getStateProvinceId().toString()));
            return state_province;
        }        
                    
        public static StateProvince getRelatedTaxRateList(StateProvince state_province)
        {           
            state_province.setTaxRateList(TaxRateDAO.getAllTaxRateByColumn("StateProvinceId", state_province.getStateProvinceId().toString()));
            return state_province;
        }        
        
                
        public static ArrayList<StateProvince> getStateProvincePaged(int limit, int offset)
        {
            ArrayList<StateProvince> state_province = new ArrayList<StateProvince>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("state_province", limit, offset);
                while (rs.next())
                {
                    state_province.add(processStateProvince(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getStateProvincePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
        } 
        
        public static ArrayList<StateProvince> getAllStateProvinceByColumn(String columnName, String columnValue)
        {
            ArrayList<StateProvince> state_province = new ArrayList<StateProvince>();
            try
            {
                getAllRecordsByColumn("state_province", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    state_province.add(processStateProvince(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllStateProvinceByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
        }
        
        public static StateProvince getStateProvinceByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            StateProvince state_province = new StateProvince();
            try
            {
                getRecordsByColumnWithLimitAndOffset("state_province", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   state_province = processStateProvince(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getStateProvinceByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
        }                
                
        public static void updateStateProvince(Integer StateProvinceId,String Name,String Abbreviation,Integer CountryId)
        {  
            try
            {   
                
                checkColumnSize(Name, 100);
                checkColumnSize(Abbreviation, 10);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE state_province SET Name=?,Abbreviation=?,CountryId=? WHERE StateProvinceId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Abbreviation);
                preparedStatement.setInt(3, CountryId);
                preparedStatement.setInt(4, StateProvinceId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateStateProvince error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllStateProvince()
        {
            try
            {
                updateQuery("DELETE FROM state_province;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllStateProvince error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteStateProvinceById(String id)
        {
            try
            {
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteStateProvinceById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteStateProvinceByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM state_province WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteStateProvinceByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

