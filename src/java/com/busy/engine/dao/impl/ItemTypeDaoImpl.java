





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ItemType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
        } 
    
        @Override
        public void add(ItemType obj)
        {
            try
            {
                
                ItemType.checkColumnSize(obj.getTypeName(), 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_type(TypeName,Rank) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getRank());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, Integer Rank)
        {   
            int id = 0;
            try
            {
                
                ItemType.checkColumnSize(TypeName, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_type(TypeName,Rank) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, Rank);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemType error: " + ex.getMessage());
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
                System.out.println("updateItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemTypeId,String TypeName,Integer Rank)
        {  
            try
            {   
                
                ItemType.checkColumnSize(TypeName, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_type SET TypeName=?,Rank=? WHERE ItemTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, Rank);
                preparedStatement.setInt(3, ItemTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_type");
        }
        
        
        @Override
        public ItemType getRelatedInfo(ItemType item_type)
        {
              
            
            return item_type;
        }
        
        
        @Override
        public ItemType getRelatedObjects(ItemType item_type)
        {
            item_type.setItemList(new ItemDaoImpl().findByColumn("ItemTypeId", item_type.getItemTypeId().toString(),null,null));
             
            return item_type;
        }
        
        
        
        @Override
        public void remove(ItemType obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + obj.getItemTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_type;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_type WHERE " + ItemType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ItemType getRelatedItemList(ItemType item_type)
        {           
            item_type.setItemList(new ItemDaoImpl().findByColumn("ItemTypeId", item_type.getItemTypeId().toString(),null,null));
            return item_type;
        }        
        
                             
    }

