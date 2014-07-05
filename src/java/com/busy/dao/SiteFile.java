


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteFile extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITEFILEID = "SiteFileId";
        public static final String PROP_FILENAME = "FileName";
        public static final String PROP_FILEDESCRIPTION = "FileDescription";
        public static final String PROP_FILELABEL = "FileLabel";
        public static final String PROP_FOLDERID = "FolderId";
        
        
        private Integer siteFileId;
        private String fileName;
        private String fileDescription;
        private String fileLabel;
        private Integer folderId;
        
        
        public SiteFile()
        {
            this.siteFileId = 0; 
       this.fileName = ""; 
       this.fileDescription = ""; 
       this.fileLabel = ""; 
       this.folderId = 0; 
        }
        
        public SiteFile(Integer SiteFileId, String FileName, String FileDescription, String FileLabel, Integer FolderId)
        {
            this.siteFileId = SiteFileId;
       this.fileName = FileName;
       this.fileDescription = FileDescription;
       this.fileLabel = FileLabel;
       this.folderId = FolderId;
        } 
        
        
            public Integer getSiteFileId()
            {
                return this.siteFileId;
            }
            
            public void setSiteFileId(Integer SiteFileId)
            {
                this.siteFileId = SiteFileId;
            }
        
            public String getFileName()
            {
                return this.fileName;
            }
            
            public void setFileName(String FileName)
            {
                this.fileName = FileName;
            }
        
            public String getFileDescription()
            {
                return this.fileDescription;
            }
            
            public void setFileDescription(String FileDescription)
            {
                this.fileDescription = FileDescription;
            }
        
            public String getFileLabel()
            {
                return this.fileLabel;
            }
            
            public void setFileLabel(String FileLabel)
            {
                this.fileLabel = FileLabel;
            }
        
            public Integer getFolderId()
            {
                return this.folderId;
            }
            
            public void setFolderId(Integer FolderId)
            {
                this.folderId = FolderId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteFile.PROP_SITEFILEID) || column.equals(SiteFile.PROP_FILENAME) || column.equals(SiteFile.PROP_FILEDESCRIPTION) || column.equals(SiteFile.PROP_FILELABEL) || column.equals(SiteFile.PROP_FOLDERID) )
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
            if (column.equals(SiteFile.PROP_SITEFILEID) || column.equals(SiteFile.PROP_FOLDERID) )
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
            return new SiteFile(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        }
        
        public static int addSiteFile(String FileName, String FileDescription, String FileLabel, Integer FolderId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FileName, 255);
                checkColumnSize(FileDescription, 255);
                checkColumnSize(FileLabel, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_file(FileName,FileDescription,FileLabel,FolderId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, FileDescription);
                preparedStatement.setString(3, FileLabel);
                preparedStatement.setInt(4, FolderId);
                
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
                
        public static void updateSiteFile(Integer SiteFileId,String FileName,String FileDescription,String FileLabel,Integer FolderId)
        {  
            try
            {   
                
                checkColumnSize(FileName, 255);
                checkColumnSize(FileDescription, 255);
                checkColumnSize(FileLabel, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_file SET FileName=?,FileDescription=?,FileLabel=?,FolderId=? WHERE SiteFileId=?;");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, FileDescription);
                preparedStatement.setString(3, FileLabel);
                preparedStatement.setInt(4, FolderId);
                preparedStatement.setInt(5, SiteFileId);
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

