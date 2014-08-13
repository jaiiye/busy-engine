





































    package com.busy.engine.dao;

import com.busy.engine.entity.MetaTag;
import com.busy.engine.entity.User;
import com.busy.engine.entity.BlogPost;
import com.busy.engine.entity.Blog;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogPostDaoImpl extends BasicConnection implements Serializable, BlogPostDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public BlogPost find(Integer id)
        {
            return findByColumn("BlogPostId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<BlogPost> findAll(Integer limit, Integer offset)
        {
            ArrayList<BlogPost> blog_post = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blog_post");
                while(rs.next())
                {
                    blog_post.add(BlogPost.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogPost object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post;
         
        }
        
        @Override
        public ArrayList<BlogPost> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<BlogPost> blog_postList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("blog_post", limit, offset);
                while (rs.next())
                {
                    blog_postList.add(BlogPost.process(rs));
                }

                
                    for(BlogPost blog_post : blog_postList)
                    {
                        
                            getRecordById("User", blog_post.getUserId().toString());
                            blog_post.setUser(User.process(rs));               
                        
                            getRecordById("Blog", blog_post.getBlogId().toString());
                            blog_post.setBlog(Blog.process(rs));               
                        
                            getRecordById("MetaTag", blog_post.getMetaTagId().toString());
                            blog_post.setMetaTag(MetaTag.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object BlogPost method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_postList;
        }
        
        @Override
        public ArrayList<BlogPost> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<BlogPost> blog_post = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("blog_post", BlogPost.checkColumnName(columnName), columnValue, BlogPost.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog_post.add(BlogPost.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogPost's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post;
        } 
    
        @Override
        public int add(BlogPost obj)
        {
            int id = 0;
            try
            {
                
                BlogPost.checkColumnSize(obj.getTitle(), 255);
                BlogPost.checkColumnSize(obj.getContent(), 65535);
                BlogPost.checkColumnSize(obj.getImageURL(), 255);
                BlogPost.checkColumnSize(obj.getTags(), 255);
                
                
                
                
                
                BlogPost.checkColumnSize(obj.getExcerpt(), 255);
                
                BlogPost.checkColumnSize(obj.getLocale(), 10);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_post(Title,Content,ImageURL,Tags,Featured,RatingSum,VoteCount,CommentCount,PostStatus,Excerpt,LastModified,Locale,UserId,BlogId,MetaTagId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setString(3, obj.getImageURL());
                preparedStatement.setString(4, obj.getTags());
                preparedStatement.setInt(5, obj.getFeatured());
                preparedStatement.setInt(6, obj.getRatingSum());
                preparedStatement.setInt(7, obj.getVoteCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPostStatus());
                preparedStatement.setString(10, obj.getExcerpt());
                preparedStatement.setDate(11, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getUserId());
                preparedStatement.setInt(14, obj.getBlogId());
                preparedStatement.setInt(15, obj.getMetaTagId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public BlogPost update(BlogPost obj)
        {
           try
            {   
                
                BlogPost.checkColumnSize(obj.getTitle(), 255);
                BlogPost.checkColumnSize(obj.getContent(), 65535);
                BlogPost.checkColumnSize(obj.getImageURL(), 255);
                BlogPost.checkColumnSize(obj.getTags(), 255);
                
                
                
                
                
                BlogPost.checkColumnSize(obj.getExcerpt(), 255);
                
                BlogPost.checkColumnSize(obj.getLocale(), 10);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_post SET Title=?,Content=?,ImageURL=?,Tags=?,Featured=?,RatingSum=?,VoteCount=?,CommentCount=?,PostStatus=?,Excerpt=?,LastModified=?,Locale=?,UserId=?,BlogId=?,MetaTagId=? WHERE BlogPostId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setString(3, obj.getImageURL());
                preparedStatement.setString(4, obj.getTags());
                preparedStatement.setInt(5, obj.getFeatured());
                preparedStatement.setInt(6, obj.getRatingSum());
                preparedStatement.setInt(7, obj.getVoteCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPostStatus());
                preparedStatement.setString(10, obj.getExcerpt());
                preparedStatement.setDate(11, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getUserId());
                preparedStatement.setInt(14, obj.getBlogId());
                preparedStatement.setInt(15, obj.getMetaTagId());
                preparedStatement.setInt(16, obj.getBlogPostId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("blog_post");
        }
        
        
        @Override
        public void getRelatedInfo(BlogPost blog_post)
        {
            
                try
                { 
                    
                            getRecordById("User", blog_post.getUserId().toString());
                            blog_post.setUser(User.process(rs));                                       
                    
                            getRecordById("Blog", blog_post.getBlogId().toString());
                            blog_post.setBlog(Blog.process(rs));                                       
                    
                            getRecordById("MetaTag", blog_post.getMetaTagId().toString());
                            blog_post.setMetaTag(MetaTag.process(rs));                                       
                    
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
        public void getRelatedObjects(BlogPost blog_post)
        {
            blog_post.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
blog_post.setCommentList(new CommentDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(BlogPost obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_post WHERE BlogPostId=" + obj.getBlogPostId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_post WHERE BlogPostId=" + id + ";");           
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
                updateQuery("DELETE FROM blog_post;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_post WHERE " + BlogPost.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedBlogPostCategoryList(BlogPost blog_post)
        {           
            blog_post.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
        }        
                    
        public void getRelatedCommentList(BlogPost blog_post)
        {           
            blog_post.setCommentList(new CommentDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
        }        
        
                             
    }

