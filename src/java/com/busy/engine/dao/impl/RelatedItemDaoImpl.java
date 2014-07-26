





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class RelatedItemDaoImpl extends BasicConnection implements Serializable, RelatedItemDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public RelatedItem find(Integer id)
        {
            return findByColumn("RelatedItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<RelatedItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<RelatedItem> related_item = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("related_item");
                while(rs.next())
                {
                    related_item.add(RelatedItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("RelatedItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
         
        }
        
        @Override
        public ArrayList<RelatedItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<RelatedItem> related_itemList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("related_item", limit, offset);
                while (rs.next())
                {
                    related_itemList.add(RelatedItem.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object RelatedItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_itemList;
        }
        
        @Override
        public ArrayList<RelatedItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<RelatedItem> related_item = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("related_item", RelatedItem.checkColumnName(columnName), columnValue, RelatedItem.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   related_item.add(RelatedItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object RelatedItem's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
        } 
    
        @Override
        public void add(RelatedItem obj)
        {
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO related_item(Item1,Item2) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getItem1());
                preparedStatement.setInt(2, obj.getItem2());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer Item1, Integer Item2)
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
        
        
        @Override
        public RelatedItem update(RelatedItem obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE related_item SET Item1=?,Item2=? WHERE RelatedItemId=?;");                    
                preparedStatement.setInt(1, obj.getItem1());
                preparedStatement.setInt(2, obj.getItem2());
                preparedStatement.setInt(3, obj.getRelatedItemId());
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
            return obj;
        }
        
        public static void update(Integer RelatedItemId,Integer Item1,Integer Item2)
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("related_item");
        }
        
        
        @Override
        public RelatedItem getRelatedInfo(RelatedItem related_item)
        {
              
            
            return related_item;
        }
        
        
        @Override
        public RelatedItem getRelatedObjects(RelatedItem related_item)
        {
                         
            return related_item;
        }
        
        
        
        @Override
        public void remove(RelatedItem obj)
        {            
            try
            {
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + obj.getRelatedItemId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM related_item;");
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM related_item WHERE " + RelatedItem.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

