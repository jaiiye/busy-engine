


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemFile extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMFILEID = "ItemFileId";
        public static final String PROP_ITEMID = "ItemId";
        public static final String PROP_ITEMFILENAME = "ItemFileName";
        public static final String PROP_ITEMFILEDESCRIPTION = "ItemFileDescription";
        public static final String PROP_ITEMFILELABEL = "ItemFileLabel";
        
        
        private Integer itemFileId;
        private Integer itemId;
        private String itemFileName;
        private String itemFileDescription;
        private String itemFileLabel;
        
        
        public ItemFile()
        {
            this.itemFileId = 0; 
       this.itemId = 0; 
       this.itemFileName = ""; 
       this.itemFileDescription = ""; 
       this.itemFileLabel = ""; 
        }
        
        public ItemFile(Integer ItemFileId, Integer ItemId, String ItemFileName, String ItemFileDescription, String ItemFileLabel)
        {
            this.itemFileId = ItemFileId;
       this.itemId = ItemId;
       this.itemFileName = ItemFileName;
       this.itemFileDescription = ItemFileDescription;
       this.itemFileLabel = ItemFileLabel;
        } 
        
        
            public Integer getItemFileId()
            {
                return this.itemFileId;
            }
            
            public void setItemFileId(Integer ItemFileId)
            {
                this.itemFileId = ItemFileId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public String getItemFileName()
            {
                return this.itemFileName;
            }
            
            public void setItemFileName(String ItemFileName)
            {
                this.itemFileName = ItemFileName;
            }
        
            public String getItemFileDescription()
            {
                return this.itemFileDescription;
            }
            
            public void setItemFileDescription(String ItemFileDescription)
            {
                this.itemFileDescription = ItemFileDescription;
            }
        
            public String getItemFileLabel()
            {
                return this.itemFileLabel;
            }
            
            public void setItemFileLabel(String ItemFileLabel)
            {
                this.itemFileLabel = ItemFileLabel;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemFile.PROP_ITEMFILEID) || column.equals(ItemFile.PROP_ITEMID) || column.equals(ItemFile.PROP_ITEMFILENAME) || column.equals(ItemFile.PROP_ITEMFILEDESCRIPTION) || column.equals(ItemFile.PROP_ITEMFILELABEL) )
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
            if (column.equals(ItemFile.PROP_ITEMFILEID) || column.equals(ItemFile.PROP_ITEMID) )
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
            return new ItemFile(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        
        public static int addItemFile(Integer ItemId, String ItemFileName, String ItemFileDescription, String ItemFileLabel)
        {   
            int id = 0;
            try
            {
                
                
                checkColumnSize(ItemFileName, 255);
                checkColumnSize(ItemFileDescription, 255);
                checkColumnSize(ItemFileLabel, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_file(ItemId,ItemFileName,ItemFileDescription,ItemFileLabel) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setString(2, ItemFileName);
                preparedStatement.setString(3, ItemFileDescription);
                preparedStatement.setString(4, ItemFileLabel);
                
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
                
        public static void updateItemFile(Integer ItemFileId,Integer ItemId,String ItemFileName,String ItemFileDescription,String ItemFileLabel)
        {  
            try
            {   
                
                
                checkColumnSize(ItemFileName, 255);
                checkColumnSize(ItemFileDescription, 255);
                checkColumnSize(ItemFileLabel, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_file SET ItemId=?,ItemFileName=?,ItemFileDescription=?,ItemFileLabel=? WHERE ItemFileId=?;");                    
                preparedStatement.setInt(1, ItemId);
                preparedStatement.setString(2, ItemFileName);
                preparedStatement.setString(3, ItemFileDescription);
                preparedStatement.setString(4, ItemFileLabel);
                preparedStatement.setInt(5, ItemFileId);
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

