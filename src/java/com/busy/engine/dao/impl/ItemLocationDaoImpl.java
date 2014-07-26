





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemLocationDaoImpl extends BasicConnection implements Serializable, ItemLocationDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemLocation find(Integer id)
        {
            return findByColumn("ItemLocationId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemLocation> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemLocation> item_location = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_location");
                while(rs.next())
                {
                    item_location.add(ItemLocation.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemLocation object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_location;
         
        }
        
        @Override
        public ArrayList<ItemLocation> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemLocation> item_locationList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_location", limit, offset);
                while (rs.next())
                {
                    item_locationList.add(ItemLocation.process(rs));
                }

                
                    for(ItemLocation item_location : item_locationList)
                    {
                        
                            getRecordById("Item", item_location.getItemId().toString());
                            item_location.setItem(Item.process(rs));               
                        
                            getRecordById("Address", item_location.getAddressId().toString());
                            item_location.setAddress(Address.process(rs));               
                        
                            getRecordById("Contact", item_location.getContactId().toString());
                            item_location.setContact(Contact.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemLocation method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_locationList;
        }
        
        @Override
        public ArrayList<ItemLocation> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemLocation> item_location = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_location", ItemLocation.checkColumnName(columnName), columnValue, ItemLocation.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_location.add(ItemLocation.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemLocation's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_location;
        } 
    
        @Override
        public void add(ItemLocation obj)
        {
            try
            {
                
                ItemLocation.checkColumnSize(obj.getLatitude(), 20);
                ItemLocation.checkColumnSize(obj.getLongitude(), 20);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_location(Latitude,Longitude,ItemId,AddressId,ContactId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getLatitude());
                preparedStatement.setString(2, obj.getLongitude());
                preparedStatement.setInt(3, obj.getItemId());
                preparedStatement.setInt(4, obj.getAddressId());
                preparedStatement.setInt(5, obj.getContactId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Latitude, String Longitude, Integer ItemId, Integer AddressId, Integer ContactId)
        {   
            int id = 0;
            try
            {
                
                ItemLocation.checkColumnSize(Latitude, 20);
                ItemLocation.checkColumnSize(Longitude, 20);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_location(Latitude,Longitude,ItemId,AddressId,ContactId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, Latitude);
                preparedStatement.setString(2, Longitude);
                preparedStatement.setInt(3, ItemId);
                preparedStatement.setInt(4, AddressId);
                preparedStatement.setInt(5, ContactId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_location;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public ItemLocation update(ItemLocation obj)
        {
           try
            {   
                
                ItemLocation.checkColumnSize(obj.getLatitude(), 20);
                ItemLocation.checkColumnSize(obj.getLongitude(), 20);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_location SET Latitude=?,Longitude=?,ItemId=?,AddressId=?,ContactId=? WHERE ItemLocationId=?;");                    
                preparedStatement.setString(1, obj.getLatitude());
                preparedStatement.setString(2, obj.getLongitude());
                preparedStatement.setInt(3, obj.getItemId());
                preparedStatement.setInt(4, obj.getAddressId());
                preparedStatement.setInt(5, obj.getContactId());
                preparedStatement.setInt(6, obj.getItemLocationId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemLocationId,String Latitude,String Longitude,Integer ItemId,Integer AddressId,Integer ContactId)
        {  
            try
            {   
                
                ItemLocation.checkColumnSize(Latitude, 20);
                ItemLocation.checkColumnSize(Longitude, 20);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_location SET Latitude=?,Longitude=?,ItemId=?,AddressId=?,ContactId=? WHERE ItemLocationId=?;");                    
                preparedStatement.setString(1, Latitude);
                preparedStatement.setString(2, Longitude);
                preparedStatement.setInt(3, ItemId);
                preparedStatement.setInt(4, AddressId);
                preparedStatement.setInt(5, ContactId);
                preparedStatement.setInt(6, ItemLocationId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemLocation error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_location");
        }
        
        
        @Override
        public ItemLocation getRelatedInfo(ItemLocation item_location)
        {
            
                try
                { 
                    
                        getRecordById("Item", item_location.getItemId().toString());
                        item_location.setItem(Item.process(rs));               
                    
                        getRecordById("Address", item_location.getAddressId().toString());
                        item_location.setAddress(Address.process(rs));               
                    
                        getRecordById("Contact", item_location.getContactId().toString());
                        item_location.setContact(Contact.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return item_location;
        }
        
        
        @Override
        public ItemLocation getRelatedObjects(ItemLocation item_location)
        {
                         
            return item_location;
        }
        
        
        
        @Override
        public void remove(ItemLocation obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_location WHERE ItemLocationId=" + obj.getItemLocationId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemLocationById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_location WHERE ItemLocationId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemLocationById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_location;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_location WHERE " + ItemLocation.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

