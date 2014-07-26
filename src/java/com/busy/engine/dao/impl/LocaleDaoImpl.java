





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Locale's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        } 
    
        @Override
        public void add(Locale obj)
        {
            try
            {
                
                Locale.checkColumnSize(obj.getLocaleString(), 10);
                Locale.checkColumnSize(obj.getLocaleCharacterSet(), 25);
                                            
                openConnection();
                prepareStatement("INSERT INTO locale(LocaleString,LocaleCharacterSet) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getLocaleString());
                preparedStatement.setString(2, obj.getLocaleCharacterSet());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Locale's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String LocaleString, String LocaleCharacterSet)
        {   
            int id = 0;
            try
            {
                
                Locale.checkColumnSize(LocaleString, 10);
                Locale.checkColumnSize(LocaleCharacterSet, 25);
                                            
                openConnection();
                prepareStatement("INSERT INTO locale(LocaleString,LocaleCharacterSet) VALUES (?,?);");                    
                preparedStatement.setString(1, LocaleString);
                preparedStatement.setString(2, LocaleCharacterSet);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from locale;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addLocale error: " + ex.getMessage());
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
                System.out.println("updateLocale error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer LocaleId,String LocaleString,String LocaleCharacterSet)
        {  
            try
            {   
                
                Locale.checkColumnSize(LocaleString, 10);
                Locale.checkColumnSize(LocaleCharacterSet, 25);
                                  
                openConnection();                           
                prepareStatement("UPDATE locale SET LocaleString=?,LocaleCharacterSet=? WHERE LocaleId=?;");                    
                preparedStatement.setString(1, LocaleString);
                preparedStatement.setString(2, LocaleCharacterSet);
                preparedStatement.setInt(3, LocaleId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateLocale error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("locale");
        }
        
        
        @Override
        public Locale getRelatedInfo(Locale locale)
        {
              
            
            return locale;
        }
        
        
        @Override
        public Locale getRelatedObjects(Locale locale)
        {
                         
            return locale;
        }
        
        
        
        @Override
        public void remove(Locale obj)
        {            
            try
            {
                updateQuery("DELETE FROM locale WHERE LocaleId=" + obj.getLocaleId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocaleById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM locale WHERE LocaleId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocaleById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM locale;");
            }
            catch (Exception ex)
            {
                System.out.println("Locale's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM locale WHERE " + Locale.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Locale's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

