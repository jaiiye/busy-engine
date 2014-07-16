


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemReviewDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemReview.PROP_ITEM_REVIEW_ID) || column.equals(ItemReview.PROP_ITEM_ID) || column.equals(ItemReview.PROP_RATING) || column.equals(ItemReview.PROP_HELPFUL_YES) || column.equals(ItemReview.PROP_HELPFUL_NO) )
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
            if (column.equals(ItemReview.PROP_ITEM_REVIEW_ID) || column.equals(ItemReview.PROP_ITEM_ID) || column.equals(ItemReview.PROP_RATING) || column.equals(ItemReview.PROP_HELPFUL_YES) || column.equals(ItemReview.PROP_HELPFUL_NO) )
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
            return new ItemReview(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addItemReview(Integer ItemId, Integer Rating, Integer HelpfulYes, Integer HelpfulNo)
        {   
            int id = 0;
            try
            {
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_review(ItemId,Rating,HelpfulYes,HelpfulNo) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, Rating);
                preparedStatement.setInt(3, HelpfulYes);
                preparedStatement.setInt(4, HelpfulNo);
                
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
                
        public static void updateItemReview(Integer ItemReviewId,Integer ItemId,Integer Rating,Integer HelpfulYes,Integer HelpfulNo)
        {  
            try
            {   
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_review SET ItemId=?,Rating=?,HelpfulYes=?,HelpfulNo=? WHERE ItemReviewId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setInt(2, Rating);
                preparedStatement.setInt(3, HelpfulYes);
                preparedStatement.setInt(4, HelpfulNo);
                preparedStatement.setInt(5, ItemReviewId);
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

