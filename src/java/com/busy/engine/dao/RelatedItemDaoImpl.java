





































    package com.busy.engine.dao;

import com.busy.engine.entity.RelatedItem;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("RelatedItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return related_item;
        } 
    
        @Override
        public int add(RelatedItem obj)
        {
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO related_item(Item1,Item2) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getItem1());
                preparedStatement.setInt(2, obj.getItem2());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from related_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's add method error: " + ex.getMessage());
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
                System.out.println("RelatedItem's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("related_item");
        }
        
        
        @Override
        public void getRelatedInfo(RelatedItem related_item)
        {
              
        }
        
        @Override
        public void getRelatedObjects(RelatedItem related_item)
        {
             
        }
        
        @Override
        public boolean remove(RelatedItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + obj.getRelatedItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + id + ";");           
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
                updateQuery("DELETE FROM related_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM related_item WHERE " + RelatedItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

