





































    package com.busy.engine.dao;

import com.busy.engine.entity.MetaTag;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("MetaTag's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return meta_tag;
        } 
    
        @Override
        public int add(MetaTag obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from meta_tag;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's add method error: " + ex.getMessage());
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
                System.out.println("MetaTag's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("meta_tag");
        }
        
        
        @Override
        public void getRelatedInfo(MetaTag meta_tag)
        {
              
        }
        
        @Override
        public void getRelatedObjects(MetaTag meta_tag)
        {
            meta_tag.setBlogPostList(new BlogPostDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setItemList(new ItemDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setPageList(new PageDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setVendorList(new VendorDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(MetaTag obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + obj.getMetaTagId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + id + ";");           
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
                updateQuery("DELETE FROM meta_tag;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM meta_tag WHERE " + MetaTag.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedBlogPostList(MetaTag meta_tag)
        {           
            meta_tag.setBlogPostList(new BlogPostDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
                    
        public void getRelatedItemList(MetaTag meta_tag)
        {           
            meta_tag.setItemList(new ItemDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
                    
        public void getRelatedPageList(MetaTag meta_tag)
        {           
            meta_tag.setPageList(new PageDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
                    
        public void getRelatedVendorList(MetaTag meta_tag)
        {           
            meta_tag.setVendorList(new VendorDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
        
                             
    }

