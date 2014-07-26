





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class MetaTagDaoImpl extends BasicConnection implements Serializable, MetaTagDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public MetaTag find(Integer id)
        {
            return findByColumn("MetaTagId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<MetaTag> findAll(Integer limit, Integer offset)
        {
            ArrayList<MetaTag> meta_tag = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("meta_tag");
                while(rs.next())
                {
                    meta_tag.add(MetaTag.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("MetaTag object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
         
        }
        
        @Override
        public ArrayList<MetaTag> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<MetaTag> meta_tagList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("meta_tag", limit, offset);
                while (rs.next())
                {
                    meta_tagList.add(MetaTag.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object MetaTag method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tagList;
        }
        
        @Override
        public ArrayList<MetaTag> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<MetaTag> meta_tag = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("meta_tag", MetaTag.checkColumnName(columnName), columnValue, MetaTag.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   meta_tag.add(MetaTag.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object MetaTag's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
        } 
    
        @Override
        public void add(MetaTag obj)
        {
            try
            {
                
                MetaTag.checkColumnSize(obj.getTitle(), 150);
                MetaTag.checkColumnSize(obj.getDescription(), 255);
                MetaTag.checkColumnSize(obj.getKeywords(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO meta_tag(Title,Description,Keywords) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getKeywords());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Title, String Description, String Keywords)
        {   
            int id = 0;
            try
            {
                
                MetaTag.checkColumnSize(Title, 150);
                MetaTag.checkColumnSize(Description, 255);
                MetaTag.checkColumnSize(Keywords, 255);
                                            
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
        
        
        @Override
        public MetaTag update(MetaTag obj)
        {
           try
            {   
                
                MetaTag.checkColumnSize(obj.getTitle(), 150);
                MetaTag.checkColumnSize(obj.getDescription(), 255);
                MetaTag.checkColumnSize(obj.getKeywords(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE meta_tag SET Title=?,Description=?,Keywords=? WHERE MetaTagId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getKeywords());
                preparedStatement.setInt(4, obj.getMetaTagId());
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
            return obj;
        }
        
        public static void update(Integer MetaTagId,String Title,String Description,String Keywords)
        {  
            try
            {   
                
                MetaTag.checkColumnSize(Title, 150);
                MetaTag.checkColumnSize(Description, 255);
                MetaTag.checkColumnSize(Keywords, 255);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("meta_tag");
        }
        
        
        @Override
        public MetaTag getRelatedInfo(MetaTag meta_tag)
        {
              
            
            return meta_tag;
        }
        
        
        @Override
        public MetaTag getRelatedObjects(MetaTag meta_tag)
        {
            meta_tag.setBlogPostList(new BlogPostDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setItemList(new ItemDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setPageList(new PageDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setVendorList(new VendorDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
             
            return meta_tag;
        }
        
        
        
        @Override
        public void remove(MetaTag obj)
        {            
            try
            {
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + obj.getMetaTagId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM meta_tag;");
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM meta_tag WHERE " + MetaTag.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public MetaTag getRelatedBlogPostList(MetaTag meta_tag)
        {           
            meta_tag.setBlogPostList(new BlogPostDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
            return meta_tag;
        }        
                    
        public MetaTag getRelatedItemList(MetaTag meta_tag)
        {           
            meta_tag.setItemList(new ItemDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
            return meta_tag;
        }        
                    
        public MetaTag getRelatedPageList(MetaTag meta_tag)
        {           
            meta_tag.setPageList(new PageDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
            return meta_tag;
        }        
                    
        public MetaTag getRelatedVendorList(MetaTag meta_tag)
        {           
            meta_tag.setVendorList(new VendorDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
            return meta_tag;
        }        
        
                             
    }

