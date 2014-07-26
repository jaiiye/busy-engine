





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object SiteImage's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
        } 
    
        @Override
        public void add(SiteImage obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String FileName, String Description, String LinkUrl, Integer Rank, Integer ImageTypeId, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                SiteImage.checkColumnSize(FileName, 255);
                SiteImage.checkColumnSize(Description, 255);
                SiteImage.checkColumnSize(LinkUrl, 255);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_image(FileName,Description,LinkUrl,Rank,ImageTypeId,SiteId) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, LinkUrl);
                preparedStatement.setInt(4, Rank);
                preparedStatement.setInt(5, ImageTypeId);
                preparedStatement.setInt(6, SiteId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_image;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteImage error: " + ex.getMessage());
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
                System.out.println("updateSiteImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer SiteImageId,String FileName,String Description,String LinkUrl,Integer Rank,Integer ImageTypeId,Integer SiteId)
        {  
            try
            {   
                
                SiteImage.checkColumnSize(FileName, 255);
                SiteImage.checkColumnSize(Description, 255);
                SiteImage.checkColumnSize(LinkUrl, 255);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_image SET FileName=?,Description=?,LinkUrl=?,Rank=?,ImageTypeId=?,SiteId=? WHERE SiteImageId=?;");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, LinkUrl);
                preparedStatement.setInt(4, Rank);
                preparedStatement.setInt(5, ImageTypeId);
                preparedStatement.setInt(6, SiteId);
                preparedStatement.setInt(7, SiteImageId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site_image");
        }
        
        
        @Override
        public SiteImage getRelatedInfo(SiteImage site_image)
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
              
            
            return site_image;
        }
        
        
        @Override
        public SiteImage getRelatedObjects(SiteImage site_image)
        {
                         
            return site_image;
        }
        
        
        
        @Override
        public void remove(SiteImage obj)
        {            
            try
            {
                updateQuery("DELETE FROM site_image WHERE SiteImageId=" + obj.getSiteImageId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteImageById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_image WHERE SiteImageId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteImageById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_image;");
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_image WHERE " + SiteImage.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SiteImage's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

