





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemTypeDaoImpl extends BasicConnection implements Serializable, ItemTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemType find(Integer id)
        {
            return findByColumn("ItemTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemType> item_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_type");
                while(rs.next())
                {
                    item_type.add(ItemType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
         
        }
        
        @Override
        public ArrayList<ItemType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemType> item_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_type", limit, offset);
                while (rs.next())
                {
                    item_typeList.add(ItemType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_typeList;
        }
        
        @Override
        public ArrayList<ItemType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemType> item_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_type", ItemType.checkColumnName(columnName), columnValue, ItemType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_type.add(ItemType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
        } 
    
        @Override
        public int add(ItemType obj)
        {
            int id = 0;
            try
            {
                
                ItemType.checkColumnSize(obj.getTypeName(), 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_type(TypeName,Rank) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getRank());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemType update(ItemType obj)
        {
           try
            {   
                
                ItemType.checkColumnSize(obj.getTypeName(), 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_type SET TypeName=?,Rank=? WHERE ItemTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getRank());
                preparedStatement.setInt(3, obj.getItemTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_type");
        }
        
        
        @Override
        public void getRelatedInfo(ItemType item_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemType item_type)
        {
            item_type.setItemList(new ItemDaoImpl().findByColumn("ItemTypeId", item_type.getItemTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + obj.getItemTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM item_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_type WHERE " + ItemType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedItemList(ItemType item_type)
        {           
            item_type.setItemList(new ItemDaoImpl().findByColumn("ItemTypeId", item_type.getItemTypeId().toString(),null,null));
        }        
        
                             
    }

