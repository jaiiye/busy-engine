





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemFileDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemFile.PROP_ITEM_FILE_ID) || column.equals(ItemFile.PROP_FILE_NAME) || column.equals(ItemFile.PROP_DESCRIPTION) || column.equals(ItemFile.PROP_LABEL) || column.equals(ItemFile.PROP_HIDDEN) || column.equals(ItemFile.PROP_ITEM_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(ItemFile.PROP_ITEM_FILE_ID) || column.equals(ItemFile.PROP_HIDDEN) || column.equals(ItemFile.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemFile processItemFile(ResultSet rs) throws SQLException
        {        
            return new ItemFile(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
        
        public static int addItemFile(String FileName, String Description, String Label, Integer Hidden, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(Label, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_file(FileName,Description,Label,Hidden,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Label);
                preparedStatement.setInt(4, Hidden);
                preparedStatement.setInt(5, ItemId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_file;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemFileCount()
        {
            return getAllRecordsCountByTableName("item_file");        
        }
        
        public static ArrayList<ItemFile> getAllItemFile()
        {
            ArrayList<ItemFile> item_file = new ArrayList<ItemFile>();
            try
            {
                getAllRecordsByTableName("item_file");
                while(rs.next())
                {
                    item_file.add(processItemFile(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_file;
        }
        
        public static ArrayList<ItemFile> getAllItemFileWithRelatedInfo()
        {
            ArrayList<ItemFile> item_fileList = new ArrayList<ItemFile>();
            try
            {
                getAllRecordsByTableName("item_file");
                while (rs.next())
                {
                    item_fileList.add(processItemFile(rs));
                }

                
                    for(ItemFile item_file : item_fileList)
                    {
                        
                            getRecordById("Item", item_file.getItemId().toString());
                            item_file.setItem(ItemDAO.processItem(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemFileWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_fileList;
        }
        
        
        public static ItemFile getRelatedInfo(ItemFile item_file)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Item", item_file.getItemId().toString());
                            item_file.setItem(ItemDAO.processItem(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return item_file;
        }
        
        public static ItemFile getAllRelatedObjects(ItemFile item_file)
        {           
                         
            return item_file;
        }
        
        
        
                
        public static ArrayList<ItemFile> getItemFilePaged(int limit, int offset)
        {
            ArrayList<ItemFile> item_file = new ArrayList<ItemFile>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_file", limit, offset);
                while (rs.next())
                {
                    item_file.add(processItemFile(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemFilePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_file;
        } 
        
        public static ArrayList<ItemFile> getAllItemFileByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemFile> item_file = new ArrayList<ItemFile>();
            try
            {
                getAllRecordsByColumn("item_file", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_file.add(processItemFile(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemFileByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_file;
        }
        
        public static ItemFile getItemFileByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemFile item_file = new ItemFile();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_file", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_file = processItemFile(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemFileByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_file;
        }                
                
        public static void updateItemFile(Integer ItemFileId,String FileName,String Description,String Label,Integer Hidden,Integer ItemId)
        {  
            try
            {   
                
                checkColumnSize(FileName, 255);
                checkColumnSize(Description, 255);
                checkColumnSize(Label, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_file SET FileName=?,Description=?,Label=?,Hidden=?,ItemId=? WHERE ItemFileId=?;");                    
                preparedStatement.setString(1, FileName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Label);
                preparedStatement.setInt(4, Hidden);
                preparedStatement.setInt(5, ItemId);
                preparedStatement.setInt(6, ItemFileId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemFile()
        {
            try
            {
                updateQuery("DELETE FROM item_file;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemFile error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemFileById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_file WHERE ItemFileId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemFileById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemFileByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_file WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemFileByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

