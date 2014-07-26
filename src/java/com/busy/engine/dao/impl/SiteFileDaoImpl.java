





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteFileDaoImpl extends BasicConnection implements Serializable, SiteFileDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SiteFile find(Integer id)
        {
            return findByColumn("SiteFileId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteFile> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteFile> site_file = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_file");
                while(rs.next())
                {
                    site_file.add(SiteFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteFile object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
         
        }
        
        @Override
        public ArrayList<SiteFile> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteFile> site_fileList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_file", limit, offset);
                while (rs.next())
                {
                    site_fileList.add(SiteFile.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteFile method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_fileList;
        }
        
        @Override
        public ArrayList<SiteFile> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteFile> site_file = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_file", SiteFile.checkColumnName(columnName), columnValue, SiteFile.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_file.add(SiteFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteFile's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
        } 
    
        @Override
        public void add(SiteFile obj)
        {
            try
            {
                
                SiteFile.checkColumnSize(obj.getFileName(), 255);
                SiteFile.checkColumnSize(obj.getDescription(), 255);
                SiteFile.checkColumnSize(obj.getLabel(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO site_file(FileName,Description,Label) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String FileName, String Description, String Label)
        {   
            int id = 0;
            try
            {
                
                SiteFile.checkColumnSize(FileName, 255);
                SiteFile.checkColumnSize(Description, 255);
                SiteFile.checkColumnSize(Label, 255);
                                            
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
        
        
        @Override
        public SiteFile update(SiteFile obj)
        {
           try
            {   
                
                SiteFile.checkColumnSize(obj.getFileName(), 255);
                SiteFile.checkColumnSize(obj.getDescription(), 255);
                SiteFile.checkColumnSize(obj.getLabel(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_file SET FileName=?,Description=?,Label=? WHERE SiteFileId=?;");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                preparedStatement.setInt(4, obj.getSiteFileId());
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
            return obj;
        }
        
        public static void update(Integer SiteFileId,String FileName,String Description,String Label)
        {  
            try
            {   
                
                SiteFile.checkColumnSize(FileName, 255);
                SiteFile.checkColumnSize(Description, 255);
                SiteFile.checkColumnSize(Label, 255);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site_file");
        }
        
        
        @Override
        public SiteFile getRelatedInfo(SiteFile site_file)
        {
              
            
            return site_file;
        }
        
        
        @Override
        public SiteFile getRelatedObjects(SiteFile site_file)
        {
            site_file.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFileId", site_file.getSiteFileId().toString(),null,null));
             
            return site_file;
        }
        
        
        
        @Override
        public void remove(SiteFile obj)
        {            
            try
            {
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + obj.getSiteFileId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM site_file;");
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_file WHERE " + SiteFile.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public SiteFile getRelatedFileFolderList(SiteFile site_file)
        {           
            site_file.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFileId", site_file.getSiteFileId().toString(),null,null));
            return site_file;
        }        
        
                             
    }

