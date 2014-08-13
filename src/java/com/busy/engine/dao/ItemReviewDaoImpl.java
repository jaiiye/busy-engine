





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemReview;
import com.busy.engine.entity.Item;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemReviewDaoImpl extends BasicConnection implements Serializable, ItemReviewDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemReview find(Integer id)
        {
            return findByColumn("ItemReviewId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemReview> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemReview> item_review = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_review");
                while(rs.next())
                {
                    item_review.add(ItemReview.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemReview object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_review;
         
        }
        
        @Override
        public ArrayList<ItemReview> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemReview> item_reviewList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_review", limit, offset);
                while (rs.next())
                {
                    item_reviewList.add(ItemReview.process(rs));
                }

                
                    for(ItemReview item_review : item_reviewList)
                    {
                        
                            getRecordById("Item", item_review.getItemId().toString());
                            item_review.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemReview method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_reviewList;
        }
        
        @Override
        public ArrayList<ItemReview> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemReview> item_review = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_review", ItemReview.checkColumnName(columnName), columnValue, ItemReview.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_review.add(ItemReview.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemReview's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_review;
        } 
    
        @Override
        public int add(ItemReview obj)
        {
            int id = 0;
            try
            {
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_review(ItemId,Rating,HelpfulYes,HelpfulNo) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getRating());
                preparedStatement.setInt(3, obj.getHelpfulYes());
                preparedStatement.setInt(4, obj.getHelpfulNo());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_review;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemReview update(ItemReview obj)
        {
           try
            {   
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_review SET ItemId=?,Rating=?,HelpfulYes=?,HelpfulNo=? WHERE ItemReviewId=?;");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getRating());
                preparedStatement.setInt(3, obj.getHelpfulYes());
                preparedStatement.setInt(4, obj.getHelpfulNo());
                preparedStatement.setInt(5, obj.getItemReviewId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_review");
        }
        
        
        @Override
        public void getRelatedInfo(ItemReview item_review)
        {
            
                try
                { 
                    
                            getRecordById("Item", item_review.getItemId().toString());
                            item_review.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemReview item_review)
        {
            item_review.setCommentList(new CommentDaoImpl().findByColumn("ItemReviewId", item_review.getItemReviewId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemReview obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_review WHERE ItemReviewId=" + obj.getItemReviewId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_review WHERE ItemReviewId=" + id + ";");           
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
                updateQuery("DELETE FROM item_review;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_review WHERE " + ItemReview.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedCommentList(ItemReview item_review)
        {           
            item_review.setCommentList(new CommentDaoImpl().findByColumn("ItemReviewId", item_review.getItemReviewId().toString(),null,null));
        }        
        
                             
    }

