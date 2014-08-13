





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemReview;
import com.busy.engine.entity.User;
import com.busy.engine.entity.Comment;
import com.busy.engine.entity.BlogPost;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CommentDaoImpl extends BasicConnection implements Serializable, CommentDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Comment find(Integer id)
        {
            return findByColumn("CommentId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Comment> findAll(Integer limit, Integer offset)
        {
            ArrayList<Comment> comment = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("comment");
                while(rs.next())
                {
                    comment.add(Comment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Comment object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
         
        }
        
        @Override
        public ArrayList<Comment> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Comment> commentList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("comment", limit, offset);
                while (rs.next())
                {
                    commentList.add(Comment.process(rs));
                }

                
                    for(Comment comment : commentList)
                    {
                        
                            getRecordById("User", comment.getUserId().toString());
                            comment.setUser(User.process(rs));               
                        
                            getRecordById("BlogPost", comment.getBlogPostId().toString());
                            comment.setBlogPost(BlogPost.process(rs));               
                        
                            getRecordById("ItemReview", comment.getItemReviewId().toString());
                            comment.setItemReview(ItemReview.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Comment method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return commentList;
        }
        
        @Override
        public ArrayList<Comment> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Comment> comment = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("comment", Comment.checkColumnName(columnName), columnValue, Comment.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   comment.add(Comment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Comment's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
        } 
    
        @Override
        public int add(Comment obj)
        {
            int id = 0;
            try
            {
                
                Comment.checkColumnSize(obj.getTitle(), 45);
                Comment.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO comment(Title,Content,Date,CommentStatus,UserId,BlogPostId,ItemReviewId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setDate(3, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(4, obj.getCommentStatus());
                preparedStatement.setInt(5, obj.getUserId());
                preparedStatement.setInt(6, obj.getBlogPostId());
                preparedStatement.setInt(7, obj.getItemReviewId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from comment;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Comment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Comment update(Comment obj)
        {
           try
            {   
                
                Comment.checkColumnSize(obj.getTitle(), 45);
                Comment.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE comment SET Title=?,Content=?,Date=?,CommentStatus=?,UserId=?,BlogPostId=?,ItemReviewId=? WHERE CommentId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setDate(3, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(4, obj.getCommentStatus());
                preparedStatement.setInt(5, obj.getUserId());
                preparedStatement.setInt(6, obj.getBlogPostId());
                preparedStatement.setInt(7, obj.getItemReviewId());
                preparedStatement.setInt(8, obj.getCommentId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Comment's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("comment");
        }
        
        
        @Override
        public void getRelatedInfo(Comment comment)
        {
            
                try
                { 
                    
                            getRecordById("User", comment.getUserId().toString());
                            comment.setUser(User.process(rs));                                       
                    
                            getRecordById("BlogPost", comment.getBlogPostId().toString());
                            comment.setBlogPost(BlogPost.process(rs));                                       
                    
                            getRecordById("ItemReview", comment.getItemReviewId().toString());
                            comment.setItemReview(ItemReview.process(rs));                                       
                    
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
        public void getRelatedObjects(Comment comment)
        {
             
        }
        
        @Override
        public boolean remove(Comment obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM comment WHERE CommentId=" + obj.getCommentId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Comment's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM comment WHERE CommentId=" + id + ";");           
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
                updateQuery("DELETE FROM comment;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Comment's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM comment WHERE " + Comment.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Comment's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

