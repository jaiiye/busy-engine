





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ItemOption's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
        } 
    
        @Override
        public void add(ItemOption obj)
        {
            try
            {
                
                ItemOption.checkColumnSize(obj.getOptionName(), 100);
                ItemOption.checkColumnSize(obj.getDescription(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_option(OptionName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getOptionName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String OptionName, String Description)
        {   
            int id = 0;
            try
            {
                
                ItemOption.checkColumnSize(OptionName, 100);
                ItemOption.checkColumnSize(Description, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_option(OptionName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, OptionName);
                preparedStatement.setString(2, Description);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_option;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemOption error: " + ex.getMessage());
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
                System.out.println("updateItemOption error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemOptionId,String OptionName,String Description)
        {  
            try
            {   
                
                ItemOption.checkColumnSize(OptionName, 100);
                ItemOption.checkColumnSize(Description, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_option SET OptionName=?,Description=? WHERE ItemOptionId=?;");                    
                preparedStatement.setString(1, OptionName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, ItemOptionId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemOption error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_option");
        }
        
        
        @Override
        public ItemOption getRelatedInfo(ItemOption item_option)
        {
              
            
            return item_option;
        }
        
        
        @Override
        public ItemOption getRelatedObjects(ItemOption item_option)
        {
            item_option.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemOptionId", item_option.getItemOptionId().toString(),null,null));
             
            return item_option;
        }
        
        
        
        @Override
        public void remove(ItemOption obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + obj.getItemOptionId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemOptionById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemOptionById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_option;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_option WHERE " + ItemOption.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ItemOption getRelatedOptionAvailabilityList(ItemOption item_option)
        {           
            item_option.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemOptionId", item_option.getItemOptionId().toString(),null,null));
            return item_option;
        }        
        
                             
    }

