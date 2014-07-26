





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ImageTypeDaoImpl extends BasicConnection implements Serializable, ImageTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ImageType find(Integer id)
        {
            return findByColumn("ImageTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ImageType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ImageType> image_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("image_type");
                while(rs.next())
                {
                    image_type.add(ImageType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ImageType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
         
        }
        
        @Override
        public ArrayList<ImageType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ImageType> image_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("image_type", limit, offset);
                while (rs.next())
                {
                    image_typeList.add(ImageType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ImageType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_typeList;
        }
        
        @Override
        public ArrayList<ImageType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ImageType> image_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("image_type", ImageType.checkColumnName(columnName), columnValue, ImageType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   image_type.add(ImageType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object ImageType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
        } 
    
        @Override
        public void add(ImageType obj)
        {
            try
            {
                
                ImageType.checkColumnSize(obj.getTypeName(), 45);
                ImageType.checkColumnSize(obj.getDescription(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO image_type(TypeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String Description)
        {   
            int id = 0;
            try
            {
                
                ImageType.checkColumnSize(TypeName, 45);
                ImageType.checkColumnSize(Description, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO image_type(TypeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Description);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from image_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addImageType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public ImageType update(ImageType obj)
        {
           try
            {   
                
                ImageType.checkColumnSize(obj.getTypeName(), 45);
                ImageType.checkColumnSize(obj.getDescription(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE image_type SET TypeName=?,Description=? WHERE ImageTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getImageTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateImageType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ImageTypeId,String TypeName,String Description)
        {  
            try
            {   
                
                ImageType.checkColumnSize(TypeName, 45);
                ImageType.checkColumnSize(Description, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE image_type SET TypeName=?,Description=? WHERE ImageTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, ImageTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateImageType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("image_type");
        }
        
        
        @Override
        public ImageType getRelatedInfo(ImageType image_type)
        {
              
            
            return image_type;
        }
        
        
        @Override
        public ImageType getRelatedObjects(ImageType image_type)
        {
            image_type.setSiteImageList(new SiteImageDaoImpl().findByColumn("ImageTypeId", image_type.getImageTypeId().toString(),null,null));
             
            return image_type;
        }
        
        
        
        @Override
        public void remove(ImageType obj)
        {            
            try
            {
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + obj.getImageTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteImageTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteImageTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM image_type;");
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM image_type WHERE " + ImageType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ImageType getRelatedSiteImageList(ImageType image_type)
        {           
            image_type.setSiteImageList(new SiteImageDaoImpl().findByColumn("ImageTypeId", image_type.getImageTypeId().toString(),null,null));
            return image_type;
        }        
        
                             
    }

