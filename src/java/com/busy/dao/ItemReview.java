


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemReview extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMREVIEWID = "ItemReviewId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_COMMENTID = "CommentId";
        public static final String PROP_RATING = "Rating";
        
        
        private Integer itemReviewId;
        private Integer itemId;
        private Integer commentId;
        private Integer rating;
        
        
        public ItemReview()
        {
            this.itemReviewId = 0; 
       this.itemId = 0; 
       this.commentId = 0; 
       this.rating = 0; 
        }
        
        public ItemReview(Integer ItemReviewId, Integer ItemId, Integer CommentId, Integer Rating)
        {
            this.itemReviewId = ItemReviewId;
       this.itemId = ItemId;
       this.commentId = CommentId;
       this.rating = Rating;
        } 
        
        
            public Integer getItemReviewId()
            {
                return this.itemReviewId;
            }
            
            public void setItemReviewId(Integer ItemReviewId)
            {
                this.itemReviewId = ItemReviewId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getCommentId()
            {
                return this.commentId;
            }
            
            public void setCommentId(Integer CommentId)
            {
                this.commentId = CommentId;
            }
        
            public Integer getRating()
            {
                return this.rating;
            }
            
            public void setRating(Integer Rating)
            {
                this.rating = Rating;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemReview.PROP_ITEMREVIEWID) || column.equals(ItemReview.PROP_ITEMID) || column.equals(ItemReview.PROP_COMMENTID) || column.equals(ItemReview.PROP_RATING) )
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
            if (column.equals(ItemReview.PROP_ITEMREVIEWID) || column.equals(ItemReview.PROP_ITEMID) || column.equals(ItemReview.PROP_COMMENTID) || column.equals(ItemReview.PROP_RATING) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemReview processItemReview(ResultSet rs) throws SQLException
        {        
            return new ItemReview(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addItemReview(Integer ItemId, Integer CommentId, Integer Rating)
        {   
            int id = 0;
            try
            {
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_review(ItemId,CommentId,Rating) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, CommentId);
                preparedStatement.setInt(3, Rating);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_review;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemReview error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemReviewCount()
        {
            return getAllRecordsCountByTableName("item_review");        
        }
        
        public static ArrayList<ItemReview> getAllItemReview()
        {
            ArrayList<ItemReview> item_review = new ArrayList<ItemReview>();
            try
            {
                getAllRecordsByTableName("item_review");
                while(rs.next())
                {
                    item_review.add(processItemReview(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemReview error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_review;
        }
                
        public static ArrayList<ItemReview> getItemReviewPaged(int limit, int offset)
        {
            ArrayList<ItemReview> item_review = new ArrayList<ItemReview>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_review", limit, offset);
                while (rs.next())
                {
                    item_review.add(processItemReview(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemReviewPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_review;
        } 
        
        public static ArrayList<ItemReview> getAllItemReviewByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemReview> item_review = new ArrayList<ItemReview>();
            try
            {
                getAllRecordsByColumn("item_review", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_review.add(processItemReview(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemReviewByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_review;
        }
        
        public static ItemReview getItemReviewByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemReview item_review = new ItemReview();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_review", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_review = processItemReview(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemReviewByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_review;
        }                
                
        public static void updateItemReview(Integer ItemReviewId,Integer ItemId,Integer CommentId,Integer Rating)
        {  
            try
            {   
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_review SET ItemId=?,CommentId=?,Rating=? WHERE ItemReviewId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, CommentId);
                preparedStatement.setInt(3, Rating);
                preparedStatement.setInt(4, ItemReviewId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemReview error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemReview()
        {
            try
            {
                updateQuery("DELETE FROM item_review;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemReview error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemReviewById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_review WHERE ItemReviewId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemReviewById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemReviewByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_review WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemReviewByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

