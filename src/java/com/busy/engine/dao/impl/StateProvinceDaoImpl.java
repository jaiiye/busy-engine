





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object StateProvince's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return state_province;
        } 
    
        @Override
        public void add(StateProvince obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Name, String Abbreviation, Integer CountryId)
        {   
            int id = 0;
            try
            {
                
                StateProvince.checkColumnSize(Name, 100);
                StateProvince.checkColumnSize(Abbreviation, 10);
                
                                            
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
                System.out.println("updateStateProvince error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer StateProvinceId,String Name,String Abbreviation,Integer CountryId)
        {  
            try
            {   
                
                StateProvince.checkColumnSize(Name, 100);
                StateProvince.checkColumnSize(Abbreviation, 10);
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("state_province");
        }
        
        
        @Override
        public StateProvince getRelatedInfo(StateProvince state_province)
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
              
            
            return state_province;
        }
        
        
        @Override
        public StateProvince getRelatedObjects(StateProvince state_province)
        {
            state_province.setShippingList(new ShippingDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
state_province.setTaxRateList(new TaxRateDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
             
            return state_province;
        }
        
        
        
        @Override
        public void remove(StateProvince obj)
        {            
            try
            {
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + obj.getStateProvinceId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM state_province WHERE StateProvinceId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM state_province;");
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM state_province WHERE " + StateProvince.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("StateProvince's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public StateProvince getRelatedShippingList(StateProvince state_province)
        {           
            state_province.setShippingList(new ShippingDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
            return state_province;
        }        
                    
        public StateProvince getRelatedTaxRateList(StateProvince state_province)
        {           
            state_province.setTaxRateList(new TaxRateDaoImpl().findByColumn("StateProvinceId", state_province.getStateProvinceId().toString(),null,null));
            return state_province;
        }        
        
                             
    }

