


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Image extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_IMAGEID = "ImageId";
        public static final String PROP_TYPEID = "TypeId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_FILENAME = "FileName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_LINKURL = "LinkUrl";
        public static final String PROP_RANK = "Rank";
        
        
        private Integer imageId;
        private Integer typeId;
        private Integer itemId;
        private String fileName;
        private String description;
        private String linkUrl;
        private Integer rank;
        
        
        public Image()
        {
            this.imageId = 0; 
       this.typeId = 0; 
       this.itemId = 0; 
       this.fileName = ""; 
       this.description = ""; 
       this.linkUrl = ""; 
       this.rank = 0; 
        }
        
        public Image(Integer ImageId, Integer TypeId, Integer ItemId, String FileName, String Description, String LinkUrl, Integer Rank)
        {
            this.imageId = ImageId;
       this.typeId = TypeId;
       this.itemId = ItemId;
       this.fileName = FileName;
       this.description = Description;
       this.linkUrl = LinkUrl;
       this.rank = Rank;
        } 
        
        
            public Integer getImageId()
            {
                return this.imageId;
            }
            
            public void setImageId(Integer ImageId)
            {
                this.imageId = ImageId;
            }
        
            public Integer getTypeId()
            {
                return this.typeId;
            }
            
            public void setTypeId(Integer TypeId)
            {
                this.typeId = TypeId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public String getFileName()
            {
                return this.fileName;
            }
            
            public void setFileName(String FileName)
            {
                this.fileName = FileName;
            }
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
        
            public String getLinkUrl()
            {
                return this.linkUrl;
            }
            
            public void setLinkUrl(String LinkUrl)
            {
                this.linkUrl = LinkUrl;
            }
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Image.PROP_IMAGEID) || column.equals(Image.PROP_TYPEID) || column.equals(Image.PROP_ITEMID) || column.equals(Image.PROP_FILENAME) || column.equals(Image.PROP_DESCRIPTION) || column.equals(Image.PROP_LINKURL) || column.equals(Image.PROP_RANK) )
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
            if (column.equals(Image.PROP_IMAGEID) || column.equals(Image.PROP_TYPEID) || column.equals(Image.PROP_ITEMID) || column.equals(Image.PROP_RANK) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Image processImage(ResultSet rs) throws SQLException
        {        
            return new Image(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        }
        
        public static int addImage(Integer TypeId, Integer ItemId, String FileName, String Description, String LinkUrl, Integer Rank)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(LinkUrl, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO image(TypeId,ItemId,FileName,Description,LinkUrl,Rank) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, TypeId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setString(3, FileName);
                preparedStatement.setString(4, Description);
                preparedStatement.setString(5, LinkUrl);
                preparedStatement.setInt(6, Rank);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from image;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllImageCount()
        {
            return getAllRecordsCountByTableName("image");        
        }
        
        public static ArrayList<Image> getAllImage()
        {
            ArrayList<Image> image = new ArrayList<Image>();
            try
            {
                getAllRecordsByTableName("image");
                while(rs.next())
                {
                    image.add(processImage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image;
        }
                
        public static ArrayList<Image> getImagePaged(int limit, int offset)
        {
            ArrayList<Image> image = new ArrayList<Image>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("image", limit, offset);
                while (rs.next())
                {
                    image.add(processImage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getImagePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image;
        } 
        
        public static ArrayList<Image> getAllImageByColumn(String columnName, String columnValue)
        {
            ArrayList<Image> image = new ArrayList<Image>();
            try
            {
                getAllRecordsByColumn("image", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    image.add(processImage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllImageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image;
        }
        
        public static Image getImageByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Image image = new Image();
            try
            {
                getRecordsByColumnWithLimitAndOffset("image", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   image = processImage(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getImageByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return image;
        }                
                
        public static void updateImage(Integer ImageId,Integer TypeId,Integer ItemId,String FileName,String Description,String LinkUrl,Integer Rank)
        {  
            try
            {   
                
                
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(LinkUrl, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE image SET TypeId=?,ItemId=?,FileName=?,Description=?,LinkUrl=?,Rank=? WHERE ImageId=?;");                    
                preparedStatement.setInt(1, TypeId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setString(3, FileName);
                preparedStatement.setString(4, Description);
                preparedStatement.setString(5, LinkUrl);
                preparedStatement.setInt(6, Rank);
                preparedStatement.setInt(7, ImageId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllImage()
        {
            try
            {
                updateQuery("DELETE FROM image;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllImage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteImageById(String id)
        {
            try
            {
                updateQuery("DELETE FROM image WHERE ImageId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteImageById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteImageByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM image WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteImageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

