


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteFileDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteFile.PROP_SITE_FILE_ID) || column.equals(SiteFile.PROP_FILE_NAME) || column.equals(SiteFile.PROP_DESCRIPTION) || column.equals(SiteFile.PROP_LABEL) )
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
            if (column.equals(SiteFile.PROP_SITE_FILE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteFile processSiteFile(ResultSet rs) throws SQLException
        {        
            return new SiteFile(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addSiteFile(String FileName, String Description, String Label)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(Label, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO site_file(FileName,Description,Label) VALUES (?,?,?);");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Label);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_file;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteFileCount()
        {
            return getAllRecordsCountByTableName("site_file");        
        }
        
        public static ArrayList<SiteFile> getAllSiteFile()
        {
            ArrayList<SiteFile> site_file = new ArrayList<SiteFile>();
            try
            {
                getAllRecordsByTableName("site_file");
                while(rs.next())
                {
                    site_file.add(processSiteFile(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
        }
                
        public static ArrayList<SiteFile> getSiteFilePaged(int limit, int offset)
        {
            ArrayList<SiteFile> site_file = new ArrayList<SiteFile>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_file", limit, offset);
                while (rs.next())
                {
                    site_file.add(processSiteFile(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteFilePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
        } 
        
        public static ArrayList<SiteFile> getAllSiteFileByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteFile> site_file = new ArrayList<SiteFile>();
            try
            {
                getAllRecordsByColumn("site_file", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_file.add(processSiteFile(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteFileByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
        }
        
        public static SiteFile getSiteFileByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteFile site_file = new SiteFile();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_file", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_file = processSiteFile(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteFileByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
        }                
                
        public static void updateSiteFile(Integer SiteFileId,String FileName,String Description,String Label)
        {  
            try
            {   
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(Label, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_file SET FileName=?,Description=?,Label=? WHERE SiteFileId=?;");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Label);
                preparedStatement.setInt(4, SiteFileId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteFile()
        {
            try
            {
                updateQuery("DELETE FROM site_file;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteFileById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteFileById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteFileByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_file WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteFileByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

