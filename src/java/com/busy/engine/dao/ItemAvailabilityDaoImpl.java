





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemAvailability;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("ItemAvailability's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_availability;
        } 
    
        @Override
        public int add(ItemAvailability obj)
        {
            int id = 0;
            try
            {
                
                ItemAvailability.checkColumnSize(obj.getType(), 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_availability(Type) VALUES (?);");                    
                preparedStatement.setString(1, obj.getType());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_availability;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's add method error: " + ex.getMessage());
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
                System.out.println("ItemAvailability's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_availability");
        }
        
        
        @Override
        public void getRelatedInfo(ItemAvailability item_availability)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemAvailability item_availability)
        {
            item_availability.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemAvailabilityId", item_availability.getItemAvailabilityId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemAvailability obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + obj.getItemAvailabilityId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + id + ";");           
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
                updateQuery("DELETE FROM item_availability;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_availability WHERE " + ItemAvailability.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedOptionAvailabilityList(ItemAvailability item_availability)
        {           
            item_availability.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemAvailabilityId", item_availability.getItemAvailabilityId().toString(),null,null));
        }        
        
                             
    }

