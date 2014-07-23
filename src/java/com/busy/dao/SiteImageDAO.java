





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteImageDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteImage.PROP_SITE_IMAGE_ID) || column.equals(SiteImage.PROP_FILE_NAME) || column.equals(SiteImage.PROP_DESCRIPTION) || column.equals(SiteImage.PROP_LINK_URL) || column.equals(SiteImage.PROP_RANK) || column.equals(SiteImage.PROP_IMAGE_TYPE_ID) || column.equals(SiteImage.PROP_SITE_ID) )
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
            if (column.equals(SiteImage.PROP_SITE_IMAGE_ID) || column.equals(SiteImage.PROP_RANK) || column.equals(SiteImage.PROP_IMAGE_TYPE_ID) || column.equals(SiteImage.PROP_SITE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteImage processSiteImage(ResultSet rs) throws SQLException
        {        
            return new SiteImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
        }
        
        public static int addSiteImage(String FileName, String Description, String LinkUrl, Integer Rank, Integer ImageTypeId, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(LinkUrl, 255);
                
                
                
                                            
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
        
        public static int getAllSiteImageCount()
        {
            return getAllRecordsCountByTableName("site_image");        
        }
        
        public static ArrayList<SiteImage> getAllSiteImage()
        {
            ArrayList<SiteImage> site_image = new ArrayList<SiteImage>();
            try
            {
                getAllRecordsByTableName("site_image");
                while(rs.next())
                {
                    site_image.add(processSiteImage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
        }
        
        public static ArrayList<SiteImage> getAllSiteImageWithRelatedInfo()
        {
            ArrayList<SiteImage> site_imageList = new ArrayList<SiteImage>();
            try
            {
                getAllRecordsByTableName("site_image");
                while (rs.next())
                {
                    site_imageList.add(processSiteImage(rs));
                }

                
                    for(SiteImage site_image : site_imageList)
                    {
                        
                            getRecordById("ImageType", site_image.getImageTypeId().toString());
                            site_image.setImageType(ImageTypeDAO.processImageType(rs));               
                        
                            getRecordById("Site", site_image.getSiteId().toString());
                            site_image.setSite(SiteDAO.processSite(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteImageWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_imageList;
        }
        
        
        public static SiteImage getRelatedInfo(SiteImage site_image)
        {
           
                
                    try
                    { 
                        
                            getRecordById("ImageType", site_image.getImageTypeId().toString());
                            site_image.setImageType(ImageTypeDAO.processImageType(rs));               
                        
                            getRecordById("Site", site_image.getSiteId().toString());
                            site_image.setSite(SiteDAO.processSite(rs));               
                        

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
        
        public static SiteImage getAllRelatedObjects(SiteImage site_image)
        {           
                         
            return site_image;
        }
        
        
        
                
        public static ArrayList<SiteImage> getSiteImagePaged(int limit, int offset)
        {
            ArrayList<SiteImage> site_image = new ArrayList<SiteImage>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_image", limit, offset);
                while (rs.next())
                {
                    site_image.add(processSiteImage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteImagePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
        } 
        
        public static ArrayList<SiteImage> getAllSiteImageByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteImage> site_image = new ArrayList<SiteImage>();
            try
            {
                getAllRecordsByColumn("site_image", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_image.add(processSiteImage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteImageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
        }
        
        public static SiteImage getSiteImageByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteImage site_image = new SiteImage();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_image", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_image = processSiteImage(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteImageByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_image;
        }                
                
        public static void updateSiteImage(Integer SiteImageId,String FileName,String Description,String LinkUrl,Integer Rank,Integer ImageTypeId,Integer SiteId)
        {  
            try
            {   
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(LinkUrl, 255);
                
                
                
                                  
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
        
        public static void deleteAllSiteImage()
        {
            try
            {
                updateQuery("DELETE FROM site_image;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteImageById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_image WHERE SiteImageId=" + id + ";");            
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

        public static void deleteSiteImageByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_image WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteImageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

