





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemOption;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemOptionDaoImpl extends BasicConnection implements Serializable, ItemOptionDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemOption find(Integer id)
        {
            return findByColumn("ItemOptionId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemOption> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemOption> item_option = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_option");
                while(rs.next())
                {
                    item_option.add(ItemOption.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemOption object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
         
        }
        
        @Override
        public ArrayList<ItemOption> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemOption> item_optionList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_option", limit, offset);
                while (rs.next())
                {
                    item_optionList.add(ItemOption.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemOption method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_optionList;
        }
        
        @Override
        public ArrayList<ItemOption> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemOption> item_option = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_option", ItemOption.checkColumnName(columnName), columnValue, ItemOption.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_option.add(ItemOption.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemOption's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
        } 
    
        @Override
        public int add(ItemOption obj)
        {
            int id = 0;
            try
            {
                
                ItemOption.checkColumnSize(obj.getOptionName(), 100);
                ItemOption.checkColumnSize(obj.getDescription(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_option(OptionName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getOptionName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_option;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemOption update(ItemOption obj)
        {
           try
            {   
                
                ItemOption.checkColumnSize(obj.getOptionName(), 100);
                ItemOption.checkColumnSize(obj.getDescription(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_option SET OptionName=?,Description=? WHERE ItemOptionId=?;");                    
                preparedStatement.setString(1, obj.getOptionName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemOptionId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_option");
        }
        
        
        @Override
        public void getRelatedInfo(ItemOption item_option)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemOption item_option)
        {
            item_option.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemOptionId", item_option.getItemOptionId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemOption obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + obj.getItemOptionId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + id + ";");           
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
                updateQuery("DELETE FROM item_option;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_option WHERE " + ItemOption.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedOptionAvailabilityList(ItemOption item_option)
        {           
            item_option.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemOptionId", item_option.getItemOptionId().toString(),null,null));
        }        
        
                             
    }

