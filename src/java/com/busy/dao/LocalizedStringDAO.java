











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.LocalizedString;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class LocalizedStringDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(LocalizedString.PROP_LOCALIZED_STRING_ID) || column.equals(LocalizedString.PROP_LOCALE) || column.equals(LocalizedString.PROP_VALUE) || column.equals(LocalizedString.PROP_TEXT_STRING_ID) )
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
            if (column.equals(LocalizedString.PROP_LOCALIZED_STRING_ID) || column.equals(LocalizedString.PROP_LOCALE) || column.equals(LocalizedString.PROP_TEXT_STRING_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static LocalizedString processLocalizedString(ResultSet rs) throws SQLException
        {        
            return new LocalizedString(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
        }
        
        public static int addLocalizedString(Integer Locale, String Value, Integer TextStringId)
        {   
            int id = 0;
            try
            {
                
                
                checkColumnSize(Value, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO localized_string(Locale,Value,TextStringId) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, Locale);
                preparedStatement.setString(2, Value);
                preparedStatement.setInt(3, TextStringId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from localized_string;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addLocalizedString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllLocalizedStringCount()
        {
            return getAllRecordsCountByTableName("localized_string");        
        }
        
        public static ArrayList<LocalizedString> getAllLocalizedString()
        {
            ArrayList<LocalizedString> localized_string = new ArrayList<LocalizedString>();
            try
            {
                getAllRecordsByTableName("localized_string");
                while(rs.next())
                {
                    localized_string.add(processLocalizedString(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllLocalizedString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
        }
                
        public static ArrayList<LocalizedString> getLocalizedStringPaged(int limit, int offset)
        {
            ArrayList<LocalizedString> localized_string = new ArrayList<LocalizedString>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("localized_string", limit, offset);
                while (rs.next())
                {
                    localized_string.add(processLocalizedString(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getLocalizedStringPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
        } 
        
        public static ArrayList<LocalizedString> getAllLocalizedStringByColumn(String columnName, String columnValue)
        {
            ArrayList<LocalizedString> localized_string = new ArrayList<LocalizedString>();
            try
            {
                getAllRecordsByColumn("localized_string", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    localized_string.add(processLocalizedString(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllLocalizedStringByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
        }
        
        public static LocalizedString getLocalizedStringByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            LocalizedString localized_string = new LocalizedString();
            try
            {
                getRecordsByColumnWithLimitAndOffset("localized_string", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   localized_string = processLocalizedString(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getLocalizedStringByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localized_string;
        }                
                
        public static void updateLocalizedString(Integer LocalizedStringId,Integer Locale,String Value,Integer TextStringId)
        {  
            try
            {   
                
                
                checkColumnSize(Value, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE localized_string SET Locale=?,Value=?,TextStringId=? WHERE LocalizedStringId=?;");                    
                preparedStatement.setInt(1, Locale);
                preparedStatement.setString(2, Value);
                preparedStatement.setInt(3, TextStringId);
                preparedStatement.setInt(4, LocalizedStringId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateLocalizedString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllLocalizedString()
        {
            try
            {
                updateQuery("DELETE FROM localized_string;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllLocalizedString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteLocalizedStringById(String id)
        {
            try
            {
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocalizedStringById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteLocalizedStringByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM localized_string WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteLocalizedStringByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

