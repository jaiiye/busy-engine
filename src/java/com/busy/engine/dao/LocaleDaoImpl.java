





































    package com.busy.engine.dao;

import com.busy.engine.entity.Locale;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class LocaleDaoImpl extends BasicConnection implements Serializable, LocaleDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Locale find(Integer id)
        {
            return findByColumn("LocaleId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Locale> findAll(Integer limit, Integer offset)
        {
            ArrayList<Locale> locale = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("locale");
                while(rs.next())
                {
                    locale.add(Locale.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Locale object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
         
        }
        
        @Override
        public ArrayList<Locale> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Locale> localeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("locale", limit, offset);
                while (rs.next())
                {
                    localeList.add(Locale.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Locale method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localeList;
        }
        
        @Override
        public ArrayList<Locale> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Locale> locale = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("locale", Locale.checkColumnName(columnName), columnValue, Locale.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   locale.add(Locale.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Locale's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        } 
    
        @Override
        public int add(Locale obj)
        {
            int id = 0;
            try
            {
                
                Locale.checkColumnSize(obj.getLocaleString(), 10);
                Locale.checkColumnSize(obj.getLocaleCharacterSet(), 25);
                                            
                openConnection();
                prepareStatement("INSERT INTO locale(LocaleString,LocaleCharacterSet) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getLocaleString());
                preparedStatement.setString(2, obj.getLocaleCharacterSet());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from locale;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Locale's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Locale update(Locale obj)
        {
           try
            {   
                
                Locale.checkColumnSize(obj.getLocaleString(), 10);
                Locale.checkColumnSize(obj.getLocaleCharacterSet(), 25);
                                  
                openConnection();                           
                prepareStatement("UPDATE locale SET LocaleString=?,LocaleCharacterSet=? WHERE LocaleId=?;");                    
                preparedStatement.setString(1, obj.getLocaleString());
                preparedStatement.setString(2, obj.getLocaleCharacterSet());
                preparedStatement.setInt(3, obj.getLocaleId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Locale's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("locale");
        }
        
        
        @Override
        public void getRelatedInfo(Locale locale)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Locale locale)
        {
             
        }
        
        @Override
        public boolean remove(Locale obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM locale WHERE LocaleId=" + obj.getLocaleId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Locale's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM locale WHERE LocaleId=" + id + ";");           
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
                updateQuery("DELETE FROM locale;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Locale's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM locale WHERE " + Locale.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Locale's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

