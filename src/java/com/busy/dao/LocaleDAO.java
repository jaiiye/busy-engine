











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Locale;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class LocaleDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Locale.PROP_LOCALE_ID) || column.equals(Locale.PROP_LOCALE_STRING) || column.equals(Locale.PROP_LOCALE_CHARACTER_SET) )
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
            if (column.equals(Locale.PROP_LOCALE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Locale processLocale(ResultSet rs) throws SQLException
        {        
            return new Locale(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addLocale(String LocaleString, String LocaleCharacterSet)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(LocaleString, 10);
                checkColumnSize(LocaleCharacterSet, 25);
                                            
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
        
        public static int getAllLocaleCount()
        {
            return getAllRecordsCountByTableName("locale");        
        }
        
        public static ArrayList<Locale> getAllLocale()
        {
            ArrayList<Locale> locale = new ArrayList<Locale>();
            try
            {
                getAllRecordsByTableName("locale");
                while(rs.next())
                {
                    locale.add(processLocale(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllLocale error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        }
                
        public static ArrayList<Locale> getLocalePaged(int limit, int offset)
        {
            ArrayList<Locale> locale = new ArrayList<Locale>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("locale", limit, offset);
                while (rs.next())
                {
                    locale.add(processLocale(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getLocalePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        } 
        
        public static ArrayList<Locale> getAllLocaleByColumn(String columnName, String columnValue)
        {
            ArrayList<Locale> locale = new ArrayList<Locale>();
            try
            {
                getAllRecordsByColumn("locale", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    locale.add(processLocale(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllLocaleByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        }
        
        public static Locale getLocaleByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Locale locale = new Locale();
            try
            {
                getRecordsByColumnWithLimitAndOffset("locale", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   locale = processLocale(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getLocaleByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        }                
                
        public static void updateLocale(Integer LocaleId,String LocaleString,String LocaleCharacterSet)
        {  
            try
            {   
                
                checkColumnSize(LocaleString, 10);
                checkColumnSize(LocaleCharacterSet, 25);
                                  
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
        
        public static void deleteAllLocale()
        {
            try
            {
                updateQuery("DELETE FROM locale;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllLocale error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteLocaleById(String id)
        {
            try
            {
                updateQuery("DELETE FROM locale WHERE LocaleId=" + id + ";");            
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

        public static void deleteLocaleByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM locale WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocaleByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

