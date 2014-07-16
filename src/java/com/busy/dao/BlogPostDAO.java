











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.BlogPost;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogPostDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(BlogPost.PROP_BLOG_POST_ID) || column.equals(BlogPost.PROP_TITLE) || column.equals(BlogPost.PROP_CONTENT) || column.equals(BlogPost.PROP_IMAGE_U_R_L) || column.equals(BlogPost.PROP_TAGS) || column.equals(BlogPost.PROP_FEATURED) || column.equals(BlogPost.PROP_RATING_SUM) || column.equals(BlogPost.PROP_VOTE_COUNT) || column.equals(BlogPost.PROP_COMMENT_COUNT) || column.equals(BlogPost.PROP_STATUS) || column.equals(BlogPost.PROP_EXCERPT) || column.equals(BlogPost.PROP_LAST_MODIFIED) || column.equals(BlogPost.PROP_LOCALE) || column.equals(BlogPost.PROP_USER_ID) || column.equals(BlogPost.PROP_BLOG_ID) || column.equals(BlogPost.PROP_META_TAG_ID) )
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
            if (column.equals(BlogPost.PROP_BLOG_POST_ID) || column.equals(BlogPost.PROP_FEATURED) || column.equals(BlogPost.PROP_RATING_SUM) || column.equals(BlogPost.PROP_VOTE_COUNT) || column.equals(BlogPost.PROP_COMMENT_COUNT) || column.equals(BlogPost.PROP_STATUS) || column.equals(BlogPost.PROP_USER_ID) || column.equals(BlogPost.PROP_BLOG_ID) || column.equals(BlogPost.PROP_META_TAG_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static BlogPost processBlogPost(ResultSet rs) throws SQLException
        {        
            return new BlogPost(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getString(13), rs.getInt(14), rs.getInt(15), rs.getInt(16));
        }
        
        public static int addBlogPost(String Title, String Content, String ImageURL, String Tags, Integer Featured, Integer RatingSum, Integer VoteCount, Integer CommentCount, Integer Status, String Excerpt, Date LastModified, String Locale, Integer UserId, Integer BlogId, Integer MetaTagId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Title, 255);
                checkColumnSize(Content, 65535);
                checkColumnSize(ImageURL, 255);
                checkColumnSize(Tags, 255);
                
                
                
                
                
                checkColumnSize(Excerpt, 255);
                
                checkColumnSize(Locale, 10);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_post(Title,Content,ImageURL,Tags,Featured,RatingSum,VoteCount,CommentCount,Status,Excerpt,LastModified,Locale,UserId,BlogId,MetaTagId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Content);
                preparedStatement.setString(3, ImageURL);
                preparedStatement.setString(4, Tags);
                preparedStatement.setInt(5, Featured);
                preparedStatement.setInt(6, RatingSum);
                preparedStatement.setInt(7, VoteCount);
                preparedStatement.setInt(8, CommentCount);
                preparedStatement.setInt(9, Status);
                preparedStatement.setString(10, Excerpt);
                preparedStatement.setDate(11, new java.sql.Date(LastModified.getTime()));
                preparedStatement.setString(12, Locale);
                preparedStatement.setInt(13, UserId);
                preparedStatement.setInt(14, BlogId);
                preparedStatement.setInt(15, MetaTagId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addBlogPost error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllBlogPostCount()
        {
            return getAllRecordsCountByTableName("blog_post");        
        }
        
        public static ArrayList<BlogPost> getAllBlogPost()
        {
            ArrayList<BlogPost> blog_post = new ArrayList<BlogPost>();
            try
            {
                getAllRecordsByTableName("blog_post");
                while(rs.next())
                {
                    blog_post.add(processBlogPost(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogPost error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post;
        }
                
        public static ArrayList<BlogPost> getBlogPostPaged(int limit, int offset)
        {
            ArrayList<BlogPost> blog_post = new ArrayList<BlogPost>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("blog_post", limit, offset);
                while (rs.next())
                {
                    blog_post.add(processBlogPost(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogPostPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post;
        } 
        
        public static ArrayList<BlogPost> getAllBlogPostByColumn(String columnName, String columnValue)
        {
            ArrayList<BlogPost> blog_post = new ArrayList<BlogPost>();
            try
            {
                getAllRecordsByColumn("blog_post", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    blog_post.add(processBlogPost(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogPostByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post;
        }
        
        public static BlogPost getBlogPostByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            BlogPost blog_post = new BlogPost();
            try
            {
                getRecordsByColumnWithLimitAndOffset("blog_post", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog_post = processBlogPost(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogPostByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post;
        }                
                
        public static void updateBlogPost(Integer BlogPostId,String Title,String Content,String ImageURL,String Tags,Integer Featured,Integer RatingSum,Integer VoteCount,Integer CommentCount,Integer Status,String Excerpt,Date LastModified,String Locale,Integer UserId,Integer BlogId,Integer MetaTagId)
        {  
            try
            {   
                
                checkColumnSize(Title, 255);
                checkColumnSize(Content, 65535);
                checkColumnSize(ImageURL, 255);
                checkColumnSize(Tags, 255);
                
                
                
                
                
                checkColumnSize(Excerpt, 255);
                
                checkColumnSize(Locale, 10);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_post SET Title=?,Content=?,ImageURL=?,Tags=?,Featured=?,RatingSum=?,VoteCount=?,CommentCount=?,Status=?,Excerpt=?,LastModified=?,Locale=?,UserId=?,BlogId=?,MetaTagId=? WHERE BlogPostId=?;");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Content);
                preparedStatement.setString(3, ImageURL);
                preparedStatement.setString(4, Tags);
                preparedStatement.setInt(5, Featured);
                preparedStatement.setInt(6, RatingSum);
                preparedStatement.setInt(7, VoteCount);
                preparedStatement.setInt(8, CommentCount);
                preparedStatement.setInt(9, Status);
                preparedStatement.setString(10, Excerpt);
                preparedStatement.setDate(11, new java.sql.Date(LastModified.getTime()));
                preparedStatement.setString(12, Locale);
                preparedStatement.setInt(13, UserId);
                preparedStatement.setInt(14, BlogId);
                preparedStatement.setInt(15, MetaTagId);
                preparedStatement.setInt(16, BlogPostId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateBlogPost error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllBlogPost()
        {
            try
            {
                updateQuery("DELETE FROM blog_post;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllBlogPost error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteBlogPostById(String id)
        {
            try
            {
                updateQuery("DELETE FROM blog_post WHERE BlogPostId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogPostById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteBlogPostByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM blog_post WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogPostByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

