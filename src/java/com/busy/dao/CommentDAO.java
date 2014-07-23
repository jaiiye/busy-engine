





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CommentDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Comment.PROP_COMMENT_ID) || column.equals(Comment.PROP_TITLE) || column.equals(Comment.PROP_CONTENT) || column.equals(Comment.PROP_DATE) || column.equals(Comment.PROP_COMMENT_STATUS) || column.equals(Comment.PROP_USER_ID) || column.equals(Comment.PROP_BLOG_POST_ID) || column.equals(Comment.PROP_ITEM_REVIEW_ID) )
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
            if (column.equals(Comment.PROP_COMMENT_ID) || column.equals(Comment.PROP_COMMENT_STATUS) || column.equals(Comment.PROP_USER_ID) || column.equals(Comment.PROP_BLOG_POST_ID) || column.equals(Comment.PROP_ITEM_REVIEW_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Comment processComment(ResultSet rs) throws SQLException
        {        
            return new Comment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addComment(String Title, String Content, Date Date, Integer CommentStatus, Integer UserId, Integer BlogPostId, Integer ItemReviewId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Title, 45);
                checkColumnSize(Content, 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO comment(Title,Content,Date,CommentStatus,UserId,BlogPostId,ItemReviewId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Content);
                preparedStatement.setDate(3, new java.sql.Date(Date.getTime()));
                preparedStatement.setInt(4, CommentStatus);
                preparedStatement.setInt(5, UserId);
                preparedStatement.setInt(6, BlogPostId);
                preparedStatement.setInt(7, ItemReviewId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from comment;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addComment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllCommentCount()
        {
            return getAllRecordsCountByTableName("comment");        
        }
        
        public static ArrayList<Comment> getAllComment()
        {
            ArrayList<Comment> comment = new ArrayList<Comment>();
            try
            {
                getAllRecordsByTableName("comment");
                while(rs.next())
                {
                    comment.add(processComment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllComment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
        }
        
        public static ArrayList<Comment> getAllCommentWithRelatedInfo()
        {
            ArrayList<Comment> commentList = new ArrayList<Comment>();
            try
            {
                getAllRecordsByTableName("comment");
                while (rs.next())
                {
                    commentList.add(processComment(rs));
                }

                
                    for(Comment comment : commentList)
                    {
                        
                            getRecordById("User", comment.getUserId().toString());
                            comment.setUser(UserDAO.processUser(rs));               
                        
                            getRecordById("BlogPost", comment.getBlogPostId().toString());
                            comment.setBlogPost(BlogPostDAO.processBlogPost(rs));               
                        
                            getRecordById("ItemReview", comment.getItemReviewId().toString());
                            comment.setItemReview(ItemReviewDAO.processItemReview(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCommentWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return commentList;
        }
        
        
        public static Comment getRelatedInfo(Comment comment)
        {
           
                
                    try
                    { 
                        
                            getRecordById("User", comment.getUserId().toString());
                            comment.setUser(UserDAO.processUser(rs));               
                        
                            getRecordById("BlogPost", comment.getBlogPostId().toString());
                            comment.setBlogPost(BlogPostDAO.processBlogPost(rs));               
                        
                            getRecordById("ItemReview", comment.getItemReviewId().toString());
                            comment.setItemReview(ItemReviewDAO.processItemReview(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return comment;
        }
        
        public static Comment getAllRelatedObjects(Comment comment)
        {           
                         
            return comment;
        }
        
        
        
                
        public static ArrayList<Comment> getCommentPaged(int limit, int offset)
        {
            ArrayList<Comment> comment = new ArrayList<Comment>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("comment", limit, offset);
                while (rs.next())
                {
                    comment.add(processComment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCommentPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
        } 
        
        public static ArrayList<Comment> getAllCommentByColumn(String columnName, String columnValue)
        {
            ArrayList<Comment> comment = new ArrayList<Comment>();
            try
            {
                getAllRecordsByColumn("comment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    comment.add(processComment(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCommentByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
        }
        
        public static Comment getCommentByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Comment comment = new Comment();
            try
            {
                getRecordsByColumnWithLimitAndOffset("comment", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   comment = processComment(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCommentByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
        }                
                
        public static void updateComment(Integer CommentId,String Title,String Content,Date Date,Integer CommentStatus,Integer UserId,Integer BlogPostId,Integer ItemReviewId)
        {  
            try
            {   
                
                checkColumnSize(Title, 45);
                checkColumnSize(Content, 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE comment SET Title=?,Content=?,Date=?,CommentStatus=?,UserId=?,BlogPostId=?,ItemReviewId=? WHERE CommentId=?;");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, Content);
                preparedStatement.setDate(3, new java.sql.Date(Date.getTime()));
                preparedStatement.setInt(4, CommentStatus);
                preparedStatement.setInt(5, UserId);
                preparedStatement.setInt(6, BlogPostId);
                preparedStatement.setInt(7, ItemReviewId);
                preparedStatement.setInt(8, CommentId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateComment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllComment()
        {
            try
            {
                updateQuery("DELETE FROM comment;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllComment error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteCommentById(String id)
        {
            try
            {
                updateQuery("DELETE FROM comment WHERE CommentId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCommentById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteCommentByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM comment WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteCommentByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

