





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteFolderDaoImpl extends BasicConnection implements Serializable, SiteFolderDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SiteFolder find(Integer id)
        {
            return findByColumn("SiteFolderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteFolder> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteFolder> site_folder = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_folder");
                while(rs.next())
                {
                    site_folder.add(SiteFolder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteFolder object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folder;
         
        }
        
        @Override
        public ArrayList<SiteFolder> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteFolder> site_folderList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_folder", limit, offset);
                while (rs.next())
                {
                    site_folderList.add(SiteFolder.process(rs));
                }

                
                    for(SiteFolder site_folder : site_folderList)
                    {
                        
                            getRecordById("Site", site_folder.getSiteId().toString());
                            site_folder.setSite(Site.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteFolder method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folderList;
        }
        
        @Override
        public ArrayList<SiteFolder> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteFolder> site_folder = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_folder", SiteFolder.checkColumnName(columnName), columnValue, SiteFolder.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_folder.add(SiteFolder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteFolder's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_folder;
        } 
    
        @Override
        public void add(SiteFolder obj)
        {
            try
            {
                
                SiteFolder.checkColumnSize(obj.getFolderName(), 100);
                SiteFolder.checkColumnSize(obj.getDescription(), 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_folder(FolderName,Description,Rank,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFolderName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getSiteId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String FolderName, String Description, Integer Rank, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                SiteFolder.checkColumnSize(FolderName, 100);
                SiteFolder.checkColumnSize(Description, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_folder(FolderName,Description,Rank,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, FolderName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setInt(4, SiteId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_folder;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public SiteFolder update(SiteFolder obj)
        {
           try
            {   
                
                SiteFolder.checkColumnSize(obj.getFolderName(), 100);
                SiteFolder.checkColumnSize(obj.getDescription(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_folder SET FolderName=?,Description=?,Rank=?,SiteId=? WHERE SiteFolderId=?;");                    
                preparedStatement.setString(1, obj.getFolderName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getSiteId());
                preparedStatement.setInt(5, obj.getSiteFolderId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SiteFolderId,String FolderName,String Description,Integer Rank,Integer SiteId)
        {  
            try
            {   
                
                SiteFolder.checkColumnSize(FolderName, 100);
                SiteFolder.checkColumnSize(Description, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_folder SET FolderName=?,Description=?,Rank=?,SiteId=? WHERE SiteFolderId=?;");                    
                preparedStatement.setString(1, FolderName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, Rank);
                preparedStatement.setInt(4, SiteId);
                preparedStatement.setInt(5, SiteFolderId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteFolder error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site_folder");
        }
        
        
        @Override
        public SiteFolder getRelatedInfo(SiteFolder site_folder)
        {
            
                try
                { 
                    
                        getRecordById("Site", site_folder.getSiteId().toString());
                        site_folder.setSite(Site.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return site_folder;
        }
        
        
        @Override
        public SiteFolder getRelatedObjects(SiteFolder site_folder)
        {
            site_folder.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFolderId", site_folder.getSiteFolderId().toString(),null,null));
             
            return site_folder;
        }
        
        
        
        @Override
        public void remove(SiteFolder obj)
        {            
            try
            {
                updateQuery("DELETE FROM site_folder WHERE SiteFolderId=" + obj.getSiteFolderId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteFolderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_folder WHERE SiteFolderId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteFolderById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_folder;");
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_folder WHERE " + SiteFolder.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public SiteFolder getRelatedFileFolderList(SiteFolder site_folder)
        {           
            site_folder.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFolderId", site_folder.getSiteFolderId().toString(),null,null));
            return site_folder;
        }        
        
                             
    }

