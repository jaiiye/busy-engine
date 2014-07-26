





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAvailabilityDaoImpl extends BasicConnection implements Serializable, ItemAvailabilityDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemAvailability find(Integer id)
        {
            return findByColumn("ItemAvailabilityId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemAvailability> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemAvailability> item_availability = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_availability");
                while(rs.next())
                {
                    item_availability.add(ItemAvailability.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAvailability object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
         
        }
        
        @Override
        public ArrayList<ItemAvailability> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemAvailability> item_availabilityList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_availability", limit, offset);
                while (rs.next())
                {
                    item_availabilityList.add(ItemAvailability.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemAvailability method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availabilityList;
        }
        
        @Override
        public ArrayList<ItemAvailability> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemAvailability> item_availability = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_availability", ItemAvailability.checkColumnName(columnName), columnValue, ItemAvailability.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_availability.add(ItemAvailability.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemAvailability's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
        } 
    
        @Override
        public void add(ItemAvailability obj)
        {
            try
            {
                
                ItemAvailability.checkColumnSize(obj.getType(), 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_availability(Type) VALUES (?);");                    
                preparedStatement.setString(1, obj.getType());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Type)
        {   
            int id = 0;
            try
            {
                
                ItemAvailability.checkColumnSize(Type, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_availability(Type) VALUES (?);");                    
                preparedStatement.setString(1, Type);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_availability;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public ItemAvailability update(ItemAvailability obj)
        {
           try
            {   
                
                ItemAvailability.checkColumnSize(obj.getType(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_availability SET Type=? WHERE ItemAvailabilityId=?;");                    
                preparedStatement.setString(1, obj.getType());
                preparedStatement.setInt(2, obj.getItemAvailabilityId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemAvailabilityId,String Type)
        {  
            try
            {   
                
                ItemAvailability.checkColumnSize(Type, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_availability SET Type=? WHERE ItemAvailabilityId=?;");                    
                preparedStatement.setString(1, Type);
                preparedStatement.setInt(2, ItemAvailabilityId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemAvailability error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_availability");
        }
        
        
        @Override
        public ItemAvailability getRelatedInfo(ItemAvailability item_availability)
        {
              
            
            return item_availability;
        }
        
        
        @Override
        public ItemAvailability getRelatedObjects(ItemAvailability item_availability)
        {
            item_availability.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemAvailabilityId", item_availability.getItemAvailabilityId().toString(),null,null));
             
            return item_availability;
        }
        
        
        
        @Override
        public void remove(ItemAvailability obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + obj.getItemAvailabilityId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAvailabilityById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAvailabilityById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_availability;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_availability WHERE " + ItemAvailability.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ItemAvailability getRelatedOptionAvailabilityList(ItemAvailability item_availability)
        {           
            item_availability.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemAvailabilityId", item_availability.getItemAvailabilityId().toString(),null,null));
            return item_availability;
        }        
        
                             
    }

