





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class RelatedItemDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(RelatedItem.PROP_RELATED_ITEM_ID) || column.equals(RelatedItem.PROP_ITEM1) || column.equals(RelatedItem.PROP_ITEM2) )
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
            if (column.equals(RelatedItem.PROP_RELATED_ITEM_ID) || column.equals(RelatedItem.PROP_ITEM1) || column.equals(RelatedItem.PROP_ITEM2) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static RelatedItem processRelatedItem(ResultSet rs) throws SQLException
        {        
            return new RelatedItem(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addRelatedItem(Integer Item1, Integer Item2)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO related_item(Item1,Item2) VALUES (?,?);");                    
                preparedStatement.setInt(1, Item1);
                preparedStatement.setInt(2, Item2);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from related_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addRelatedItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllRelatedItemCount()
        {
            return getAllRecordsCountByTableName("related_item");        
        }
        
        public static ArrayList<RelatedItem> getAllRelatedItem()
        {
            ArrayList<RelatedItem> related_item = new ArrayList<RelatedItem>();
            try
            {
                getAllRecordsByTableName("related_item");
                while(rs.next())
                {
                    related_item.add(processRelatedItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllRelatedItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
        }
        
        public static ArrayList<RelatedItem> getAllRelatedItemWithRelatedInfo()
        {
            ArrayList<RelatedItem> related_itemList = new ArrayList<RelatedItem>();
            try
            {
                getAllRecordsByTableName("related_item");
                while (rs.next())
                {
                    related_itemList.add(processRelatedItem(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllRelatedItemWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_itemList;
        }
        
        
        public static RelatedItem getRelatedInfo(RelatedItem related_item)
        {
           
                  
            
            return related_item;
        }
        
        public static RelatedItem getAllRelatedObjects(RelatedItem related_item)
        {           
                         
            return related_item;
        }
        
        
        
                
        public static ArrayList<RelatedItem> getRelatedItemPaged(int limit, int offset)
        {
            ArrayList<RelatedItem> related_item = new ArrayList<RelatedItem>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("related_item", limit, offset);
                while (rs.next())
                {
                    related_item.add(processRelatedItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getRelatedItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
        } 
        
        public static ArrayList<RelatedItem> getAllRelatedItemByColumn(String columnName, String columnValue)
        {
            ArrayList<RelatedItem> related_item = new ArrayList<RelatedItem>();
            try
            {
                getAllRecordsByColumn("related_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    related_item.add(processRelatedItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllRelatedItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
        }
        
        public static RelatedItem getRelatedItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            RelatedItem related_item = new RelatedItem();
            try
            {
                getRecordsByColumnWithLimitAndOffset("related_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   related_item = processRelatedItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getRelatedItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
        }                
                
        public static void updateRelatedItem(Integer RelatedItemId,Integer Item1,Integer Item2)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE related_item SET Item1=?,Item2=? WHERE RelatedItemId=?;");                    
                preparedStatement.setInt(1, Item1);
                preparedStatement.setInt(2, Item2);
                preparedStatement.setInt(3, RelatedItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateRelatedItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllRelatedItem()
        {
            try
            {
                updateQuery("DELETE FROM related_item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllRelatedItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteRelatedItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteRelatedItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteRelatedItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM related_item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteRelatedItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

