





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemAvailability;
import com.busy.engine.entity.ItemOption;
import com.busy.engine.entity.Item;
import com.busy.engine.entity.OptionAvailability;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class OptionAvailabilityDaoImpl extends BasicConnection implements Serializable, OptionAvailabilityDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public OptionAvailability find(Integer id)
        {
            return findByColumn("OptionAvailabilityId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<OptionAvailability> findAll(Integer limit, Integer offset)
        {
            ArrayList<OptionAvailability> option_availability = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("option_availability");
                while(rs.next())
                {
                    option_availability.add(OptionAvailability.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OptionAvailability object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availability;
         
        }
        
        @Override
        public ArrayList<OptionAvailability> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<OptionAvailability> option_availabilityList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("option_availability", limit, offset);
                while (rs.next())
                {
                    option_availabilityList.add(OptionAvailability.process(rs));
                }

                
                    for(OptionAvailability option_availability : option_availabilityList)
                    {
                        
                            getRecordById("Item", option_availability.getItemId().toString());
                            option_availability.setItem(Item.process(rs));               
                        
                            getRecordById("ItemOption", option_availability.getItemOptionId().toString());
                            option_availability.setItemOption(ItemOption.process(rs));               
                        
                            getRecordById("ItemAvailability", option_availability.getItemAvailabilityId().toString());
                            option_availability.setItemAvailability(ItemAvailability.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object OptionAvailability method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availabilityList;
        }
        
        @Override
        public ArrayList<OptionAvailability> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<OptionAvailability> option_availability = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("option_availability", OptionAvailability.checkColumnName(columnName), columnValue, OptionAvailability.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   option_availability.add(OptionAvailability.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("OptionAvailability's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return option_availability;
        } 
    
        @Override
        public int add(OptionAvailability obj)
        {
            int id = 0;
            try
            {
                
                
                
                
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO option_availability(ItemId,ItemOptionId,ItemAvailabilityId,AvailableQuantity,Price,AvailableFrom,AvailableTo,MaximumQuantity) VALUES (?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getItemOptionId());
                preparedStatement.setInt(3, obj.getItemAvailabilityId());
                preparedStatement.setInt(4, obj.getAvailableQuantity());
                preparedStatement.setDouble(5, obj.getPrice());
                preparedStatement.setDate(6, new java.sql.Date(obj.getAvailableFrom().getTime()));
                preparedStatement.setDate(7, new java.sql.Date(obj.getAvailableTo().getTime()));
                preparedStatement.setInt(8, obj.getMaximumQuantity());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from option_availability;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public OptionAvailability update(OptionAvailability obj)
        {
           try
            {   
                
                
                
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE option_availability SET ItemId=?,ItemOptionId=?,ItemAvailabilityId=?,AvailableQuantity=?,Price=?,AvailableFrom=?,AvailableTo=?,MaximumQuantity=? WHERE OptionAvailabilityId=?;");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getItemOptionId());
                preparedStatement.setInt(3, obj.getItemAvailabilityId());
                preparedStatement.setInt(4, obj.getAvailableQuantity());
                preparedStatement.setDouble(5, obj.getPrice());
                preparedStatement.setDate(6, new java.sql.Date(obj.getAvailableFrom().getTime()));
                preparedStatement.setDate(7, new java.sql.Date(obj.getAvailableTo().getTime()));
                preparedStatement.setInt(8, obj.getMaximumQuantity());
                preparedStatement.setInt(9, obj.getOptionAvailabilityId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("option_availability");
        }
        
        
        @Override
        public void getRelatedInfo(OptionAvailability option_availability)
        {
            
                try
                { 
                    
                            getRecordById("Item", option_availability.getItemId().toString());
                            option_availability.setItem(Item.process(rs));                                       
                    
                            getRecordById("ItemOption", option_availability.getItemOptionId().toString());
                            option_availability.setItemOption(ItemOption.process(rs));                                       
                    
                            getRecordById("ItemAvailability", option_availability.getItemAvailabilityId().toString());
                            option_availability.setItemAvailability(ItemAvailability.process(rs));                                       
                    
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
        public void getRelatedObjects(OptionAvailability option_availability)
        {
             
        }
        
        @Override
        public boolean remove(OptionAvailability obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM option_availability WHERE OptionAvailabilityId=" + obj.getOptionAvailabilityId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM option_availability WHERE OptionAvailabilityId=" + id + ";");           
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
                updateQuery("DELETE FROM option_availability;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM option_availability WHERE " + OptionAvailability.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("OptionAvailability's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

