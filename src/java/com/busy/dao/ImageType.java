


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ImageType extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_IMAGETYPEID = "ImageTypeId";
        public static final String PROP_TYPENAME = "TypeName";
        public static final String PROP_TYPEDESCRIPTION = "TypeDescription";
        
        
        private Integer imageTypeId;
        private String typeName;
        private String typeDescription;
        
        
        public ImageType()
        {
            this.imageTypeId = 0; 
       this.typeName = ""; 
       this.typeDescription = ""; 
        }
        
        public ImageType(Integer ImageTypeId, String TypeName, String TypeDescription)
        {
            this.imageTypeId = ImageTypeId;
       this.typeName = TypeName;
       this.typeDescription = TypeDescription;
        } 
        
        
            public Integer getImageTypeId()
            {
                return this.imageTypeId;
            }
            
            public void setImageTypeId(Integer ImageTypeId)
            {
                this.imageTypeId = ImageTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
        
            public String getTypeDescription()
            {
                return this.typeDescription;
            }
            
            public void setTypeDescription(String TypeDescription)
            {
                this.typeDescription = TypeDescription;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ImageType.PROP_IMAGETYPEID) || column.equals(ImageType.PROP_TYPENAME) || column.equals(ImageType.PROP_TYPEDESCRIPTION) )
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
            if (column.equals(ImageType.PROP_IMAGETYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ImageType processImageType(ResultSet rs) throws SQLException
        {        
            return new ImageType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addImageType(String TypeName, String TypeDescription)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeDescription, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO image_type(TypeName,TypeDescription) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeDescription);
                
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
        
        public static int getAllImageTypeCount()
        {
            return getAllRecordsCountByTableName("image_type");        
        }
        
        public static ArrayList<ImageType> getAllImageType()
        {
            ArrayList<ImageType> image_type = new ArrayList<ImageType>();
            try
            {
                getAllRecordsByTableName("image_type");
                while(rs.next())
                {
                    image_type.add(processImageType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllImageType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
        }
                
        public static ArrayList<ImageType> getImageTypePaged(int limit, int offset)
        {
            ArrayList<ImageType> image_type = new ArrayList<ImageType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("image_type", limit, offset);
                while (rs.next())
                {
                    image_type.add(processImageType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getImageTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
        } 
        
        public static ArrayList<ImageType> getAllImageTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<ImageType> image_type = new ArrayList<ImageType>();
            try
            {
                getAllRecordsByColumn("image_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    image_type.add(processImageType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllImageTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
        }
        
        public static ImageType getImageTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ImageType image_type = new ImageType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("image_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   image_type = processImageType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getImageTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image_type;
        }                
                
        public static void updateImageType(Integer ImageTypeId,String TypeName,String TypeDescription)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeDescription, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE image_type SET TypeName=?,TypeDescription=? WHERE ImageTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeDescription);
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
        
        public static void deleteAllImageType()
        {
            try
            {
                updateQuery("DELETE FROM image_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllImageType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteImageTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM image_type WHERE ImageTypeId=" + id + ";");            
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

        public static void deleteImageTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM image_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteImageTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

