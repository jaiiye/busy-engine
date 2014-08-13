





































    package com.busy.engine.dao;

import com.busy.engine.entity.SiteFile;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("SiteFile's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_file;
        } 
    
        @Override
        public int add(SiteFile obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_file;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's add method error: " + ex.getMessage());
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
                System.out.println("SiteFile's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("site_file");
        }
        
        
        @Override
        public void getRelatedInfo(SiteFile site_file)
        {
              
        }
        
        @Override
        public void getRelatedObjects(SiteFile site_file)
        {
            site_file.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFileId", site_file.getSiteFileId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(SiteFile obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + obj.getSiteFileId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_file WHERE SiteFileId=" + id + ";");           
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
                updateQuery("DELETE FROM site_file;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_file WHERE " + SiteFile.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteFile's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedFileFolderList(SiteFile site_file)
        {           
            site_file.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFileId", site_file.getSiteFileId().toString(),null,null));
        }        
        
                             
    }

