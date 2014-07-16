











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.SiteLanguage;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteLanguageDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteLanguage.PROP_SITE_LANGUAGE_ID) || column.equals(SiteLanguage.PROP_LANGUAGE_NAME) || column.equals(SiteLanguage.PROP_LOCALE) || column.equals(SiteLanguage.PROP_RTL) || column.equals(SiteLanguage.PROP_FLAG_FILE_NAME) || column.equals(SiteLanguage.PROP_SITE_ID) )
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
            if (column.equals(SiteLanguage.PROP_SITE_LANGUAGE_ID) || column.equals(SiteLanguage.PROP_RTL) || column.equals(SiteLanguage.PROP_SITE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteLanguage processSiteLanguage(ResultSet rs) throws SQLException
        {        
            return new SiteLanguage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getInt(6));
        }
        
        public static int addSiteLanguage(String LanguageName, String Locale, Boolean Rtl, String FlagFileName, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(LanguageName, 100);
                checkColumnSize(Locale, 10);
                
                checkColumnSize(FlagFileName, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_language(LanguageName,Locale,Rtl,FlagFileName,SiteId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, LanguageName);
                preparedStatement.setString(2, Locale);
                preparedStatement.setBoolean(3, Rtl);
                preparedStatement.setString(4, FlagFileName);
                preparedStatement.setInt(5, SiteId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_language;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteLanguage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteLanguageCount()
        {
            return getAllRecordsCountByTableName("site_language");        
        }
        
        public static ArrayList<SiteLanguage> getAllSiteLanguage()
        {
            ArrayList<SiteLanguage> site_language = new ArrayList<SiteLanguage>();
            try
            {
                getAllRecordsByTableName("site_language");
                while(rs.next())
                {
                    site_language.add(processSiteLanguage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteLanguage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_language;
        }
                
        public static ArrayList<SiteLanguage> getSiteLanguagePaged(int limit, int offset)
        {
            ArrayList<SiteLanguage> site_language = new ArrayList<SiteLanguage>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_language", limit, offset);
                while (rs.next())
                {
                    site_language.add(processSiteLanguage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteLanguagePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_language;
        } 
        
        public static ArrayList<SiteLanguage> getAllSiteLanguageByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteLanguage> site_language = new ArrayList<SiteLanguage>();
            try
            {
                getAllRecordsByColumn("site_language", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_language.add(processSiteLanguage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteLanguageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_language;
        }
        
        public static SiteLanguage getSiteLanguageByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteLanguage site_language = new SiteLanguage();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_language", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_language = processSiteLanguage(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteLanguageByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_language;
        }                
                
        public static void updateSiteLanguage(Integer SiteLanguageId,String LanguageName,String Locale,Boolean Rtl,String FlagFileName,Integer SiteId)
        {  
            try
            {   
                
                checkColumnSize(LanguageName, 100);
                checkColumnSize(Locale, 10);
                
                checkColumnSize(FlagFileName, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_language SET LanguageName=?,Locale=?,Rtl=?,FlagFileName=?,SiteId=? WHERE SiteLanguageId=?;");                    
                preparedStatement.setString(1, LanguageName);
                preparedStatement.setString(2, Locale);
                preparedStatement.setBoolean(3, Rtl);
                preparedStatement.setString(4, FlagFileName);
                preparedStatement.setInt(5, SiteId);
                preparedStatement.setInt(6, SiteLanguageId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteLanguage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteLanguage()
        {
            try
            {
                updateQuery("DELETE FROM site_language;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteLanguage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteLanguageById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_language WHERE SiteLanguageId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteLanguageById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteLanguageByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_language WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteLanguageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

