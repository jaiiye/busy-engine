





































    package com.busy.engine.dao;

import com.busy.engine.entity.ImageType;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("ImageType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
        } 
    
        @Override
        public int add(ImageType obj)
        {
            int id = 0;
            try
            {
                
                ImageType.checkColumnSize(obj.getTypeName(), 45);
                ImageType.checkColumnSize(obj.getDescription(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO image_type(TypeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from image_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's add method error: " + ex.getMessage());
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
                System.out.println("ImageType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("image_type");
        }
        
        
        @Override
        public void getRelatedInfo(ImageType image_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ImageType image_type)
        {
            image_type.setSiteImageList(new SiteImageDaoImpl().findByColumn("ImageTypeId", image_type.getImageTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ImageType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + obj.getImageTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM image_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM image_type WHERE " + ImageType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ImageType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedSiteImageList(ImageType image_type)
        {           
            image_type.setSiteImageList(new SiteImageDaoImpl().findByColumn("ImageTypeId", image_type.getImageTypeId().toString(),null,null));
        }        
        
                             
    }

