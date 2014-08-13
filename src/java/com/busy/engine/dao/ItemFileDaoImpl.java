





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemFile;
import com.busy.engine.entity.Item;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemFileDaoImpl extends BasicConnection implements Serializable, ItemFileDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemFile find(Integer id)
        {
            return findByColumn("ItemFileId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemFile> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemFile> item_file = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_file");
                while(rs.next())
                {
                    item_file.add(ItemFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemFile object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_file;
         
        }
        
        @Override
        public ArrayList<ItemFile> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemFile> item_fileList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_file", limit, offset);
                while (rs.next())
                {
                    item_fileList.add(ItemFile.process(rs));
                }

                
                    for(ItemFile item_file : item_fileList)
                    {
                        
                            getRecordById("Item", item_file.getItemId().toString());
                            item_file.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemFile method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_fileList;
        }
        
        @Override
        public ArrayList<ItemFile> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemFile> item_file = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_file", ItemFile.checkColumnName(columnName), columnValue, ItemFile.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_file.add(ItemFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemFile's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_file;
        } 
    
        @Override
        public int add(ItemFile obj)
        {
            int id = 0;
            try
            {
                
                ItemFile.checkColumnSize(obj.getFileName(), 255);
                ItemFile.checkColumnSize(obj.getDescription(), 255);
                ItemFile.checkColumnSize(obj.getLabel(), 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_file(FileName,Description,Label,Hidden,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                preparedStatement.setInt(4, obj.getHidden());
                preparedStatement.setInt(5, obj.getItemId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_file;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemFile update(ItemFile obj)
        {
           try
            {   
                
                ItemFile.checkColumnSize(obj.getFileName(), 255);
                ItemFile.checkColumnSize(obj.getDescription(), 255);
                ItemFile.checkColumnSize(obj.getLabel(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_file SET FileName=?,Description=?,Label=?,Hidden=?,ItemId=? WHERE ItemFileId=?;");                    
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                preparedStatement.setInt(4, obj.getHidden());
                preparedStatement.setInt(5, obj.getItemId());
                preparedStatement.setInt(6, obj.getItemFileId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_file");
        }
        
        
        @Override
        public void getRelatedInfo(ItemFile item_file)
        {
            
                try
                { 
                    
                            getRecordById("Item", item_file.getItemId().toString());
                            item_file.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemFile item_file)
        {
             
        }
        
        @Override
        public boolean remove(ItemFile obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_file WHERE ItemFileId=" + obj.getItemFileId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_file WHERE ItemFileId=" + id + ";");           
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
                updateQuery("DELETE FROM item_file;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_file WHERE " + ItemFile.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

