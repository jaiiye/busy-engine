





































    package com.busy.engine.dao;

import com.busy.engine.entity.SiteImage;
import com.busy.engine.entity.Site;
import com.busy.engine.entity.ImageType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteImageDaoImpl extends BasicConnection implements Serializable, SiteImageDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SiteImage find(Integer id)
        {
            return findByColumn("SiteImageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteImage> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteImage> site_image = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_image");
                while(rs.next())
                {
                    site_image.add(SiteImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteImage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
         
        }
        
        @Override
        public ArrayList<SiteImage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteImage> site_imageList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_image", limit, offset);
                while (rs.next())
                {
                    site_imageList.add(SiteImage.process(rs));
                }

                
                    for(SiteImage site_image : site_imageList)
                    {
                        
                            getRecordById("ImageType", site_image.getImageTypeId().toString());
                            site_image.setImageType(ImageType.process(rs));               
                        
                            getRecordById("Site", site_image.getSiteId().toString());
                            site_image.setSite(Site.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteImage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_imageList;
        }
        
        @Override
        public ArrayList<SiteImage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteImage> site_image = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_image", SiteImage.checkColumnName(columnName), columnValue, SiteImage.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_image.add(SiteImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteImage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
        } 
    
        @Override
        public int add(SiteImage obj)
        {
            int id = 0;
            try
            {
                
                SiteImage.checkColumnSize(obj.getFileName(), 255);
                SiteImage.checkColumnSize(obj.getDescription(), 255);
                SiteImage.checkColumnSize(obj.getLinkUrl(), 255);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_image(FileName,Description,LinkUrl,Rank,ImageTypeId,SiteId) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLinkUrl());
                preparedStatement.setInt(4, obj.getRank());
                preparedStatement.setInt(5, obj.getImageTypeId());
                preparedStatement.setInt(6, obj.getSiteId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_image;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public SiteImage update(SiteImage obj)
        {
           try
            {   
                
                SiteImage.checkColumnSize(obj.getFileName(), 255);
                SiteImage.checkColumnSize(obj.getDescription(), 255);
                SiteImage.checkColumnSize(obj.getLinkUrl(), 255);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_image SET FileName=?,Description=?,LinkUrl=?,Rank=?,ImageTypeId=?,SiteId=? WHERE SiteImageId=?;");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLinkUrl());
                preparedStatement.setInt(4, obj.getRank());
                preparedStatement.setInt(5, obj.getImageTypeId());
                preparedStatement.setInt(6, obj.getSiteId());
                preparedStatement.setInt(7, obj.getSiteImageId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("site_image");
        }
        
        
        @Override
        public void getRelatedInfo(SiteImage site_image)
        {
            
                try
                { 
                    
                            getRecordById("ImageType", site_image.getImageTypeId().toString());
                            site_image.setImageType(ImageType.process(rs));                                       
                    
                            getRecordById("Site", site_image.getSiteId().toString());
                            site_image.setSite(Site.process(rs));                                       
                    
                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
        }
        
        @Override
        public void getRelatedObjects(SiteImage site_image)
        {
             
        }
        
        @Override
        public boolean remove(SiteImage obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_image WHERE SiteImageId=" + obj.getSiteImageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_image WHERE SiteImageId=" + id + ";");           
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
                updateQuery("DELETE FROM site_image;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_image WHERE " + SiteImage.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

