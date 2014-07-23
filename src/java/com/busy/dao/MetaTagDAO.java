





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class MetaTagDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(MetaTag.PROP_META_TAG_ID) || column.equals(MetaTag.PROP_TITLE) || column.equals(MetaTag.PROP_DESCRIPTION) || column.equals(MetaTag.PROP_KEYWORDS) )
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
            if (column.equals(MetaTag.PROP_META_TAG_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static MetaTag processMetaTag(ResultSet rs) throws SQLException
        {        
            return new MetaTag(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addMetaTag(String Title, String Description, String Keywords)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Title, 150);
                checkColumnSize(Description, 255);
                checkColumnSize(Keywords, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO meta_tag(Title,Description,Keywords) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Keywords);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from meta_tag;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addMetaTag error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllMetaTagCount()
        {
            return getAllRecordsCountByTableName("meta_tag");        
        }
        
        public static ArrayList<MetaTag> getAllMetaTag()
        {
            ArrayList<MetaTag> meta_tag = new ArrayList<MetaTag>();
            try
            {
                getAllRecordsByTableName("meta_tag");
                while(rs.next())
                {
                    meta_tag.add(processMetaTag(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllMetaTag error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
        }
        
        public static ArrayList<MetaTag> getAllMetaTagWithRelatedInfo()
        {
            ArrayList<MetaTag> meta_tagList = new ArrayList<MetaTag>();
            try
            {
                getAllRecordsByTableName("meta_tag");
                while (rs.next())
                {
                    meta_tagList.add(processMetaTag(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllMetaTagWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tagList;
        }
        
        
        public static MetaTag getRelatedInfo(MetaTag meta_tag)
        {
           
                  
            
            return meta_tag;
        }
        
        public static MetaTag getAllRelatedObjects(MetaTag meta_tag)
        {           
            meta_tag.setBlogPostList(BlogPostDAO.getAllBlogPostByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
meta_tag.setItemList(ItemDAO.getAllItemByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
meta_tag.setPageList(PageDAO.getAllPageByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
meta_tag.setVendorList(VendorDAO.getAllVendorByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
             
            return meta_tag;
        }
        
        
                    
        public static MetaTag getRelatedBlogPostList(MetaTag meta_tag)
        {           
            meta_tag.setBlogPostList(BlogPostDAO.getAllBlogPostByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
            return meta_tag;
        }        
                    
        public static MetaTag getRelatedItemList(MetaTag meta_tag)
        {           
            meta_tag.setItemList(ItemDAO.getAllItemByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
            return meta_tag;
        }        
                    
        public static MetaTag getRelatedPageList(MetaTag meta_tag)
        {           
            meta_tag.setPageList(PageDAO.getAllPageByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
            return meta_tag;
        }        
                    
        public static MetaTag getRelatedVendorList(MetaTag meta_tag)
        {           
            meta_tag.setVendorList(VendorDAO.getAllVendorByColumn("MetaTagId", meta_tag.getMetaTagId().toString()));
            return meta_tag;
        }        
        
                
        public static ArrayList<MetaTag> getMetaTagPaged(int limit, int offset)
        {
            ArrayList<MetaTag> meta_tag = new ArrayList<MetaTag>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("meta_tag", limit, offset);
                while (rs.next())
                {
                    meta_tag.add(processMetaTag(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getMetaTagPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
        } 
        
        public static ArrayList<MetaTag> getAllMetaTagByColumn(String columnName, String columnValue)
        {
            ArrayList<MetaTag> meta_tag = new ArrayList<MetaTag>();
            try
            {
                getAllRecordsByColumn("meta_tag", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    meta_tag.add(processMetaTag(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllMetaTagByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
        }
        
        public static MetaTag getMetaTagByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            MetaTag meta_tag = new MetaTag();
            try
            {
                getRecordsByColumnWithLimitAndOffset("meta_tag", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   meta_tag = processMetaTag(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getMetaTagByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
        }                
                
        public static void updateMetaTag(Integer MetaTagId,String Title,String Description,String Keywords)
        {  
            try
            {   
                
                checkColumnSize(Title, 150);
                checkColumnSize(Description, 255);
                checkColumnSize(Keywords, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE meta_tag SET Title=?,Description=?,Keywords=? WHERE MetaTagId=?;");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Keywords);
                preparedStatement.setInt(4, MetaTagId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateMetaTag error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllMetaTag()
        {
            try
            {
                updateQuery("DELETE FROM meta_tag;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllMetaTag error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteMetaTagById(String id)
        {
            try
            {
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteMetaTagById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteMetaTagByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM meta_tag WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteMetaTagByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

