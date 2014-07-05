


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemOption extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMOPTIONID = "ItemOptionId";
        public static final String PROP_ITEMOPTIONTYPE = "ItemOptionType";
        public static final String PROP_ITEMOPTIONDESCRIPTION = "ItemOptionDescription";
        
        
        private Integer itemOptionId;
        private String itemOptionType;
        private String itemOptionDescription;
        
        
        public ItemOption()
        {
            this.itemOptionId = 0; 
       this.itemOptionType = ""; 
       this.itemOptionDescription = ""; 
        }
        
        public ItemOption(Integer ItemOptionId, String ItemOptionType, String ItemOptionDescription)
        {
            this.itemOptionId = ItemOptionId;
       this.itemOptionType = ItemOptionType;
       this.itemOptionDescription = ItemOptionDescription;
        } 
        
        
            public Integer getItemOptionId()
            {
                return this.itemOptionId;
            }
            
            public void setItemOptionId(Integer ItemOptionId)
            {
                this.itemOptionId = ItemOptionId;
            }
        
            public String getItemOptionType()
            {
                return this.itemOptionType;
            }
            
            public void setItemOptionType(String ItemOptionType)
            {
                this.itemOptionType = ItemOptionType;
            }
        
            public String getItemOptionDescription()
            {
                return this.itemOptionDescription;
            }
            
            public void setItemOptionDescription(String ItemOptionDescription)
            {
                this.itemOptionDescription = ItemOptionDescription;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemOption.PROP_ITEMOPTIONID) || column.equals(ItemOption.PROP_ITEMOPTIONTYPE) || column.equals(ItemOption.PROP_ITEMOPTIONDESCRIPTION) )
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
            if (column.equals(ItemOption.PROP_ITEMOPTIONID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemOption processItemOption(ResultSet rs) throws SQLException
        {        
            return new ItemOption(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addItemOption(String ItemOptionType, String ItemOptionDescription)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ItemOptionType, 45);
                checkColumnSize(ItemOptionDescription, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_option(ItemOptionType,ItemOptionDescription) VALUES (?,?);");                    
                preparedStatement.setString(1, ItemOptionType);
                preparedStatement.setString(2, ItemOptionDescription);
                
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
        
        public static int getAllItemOptionCount()
        {
            return getAllRecordsCountByTableName("item_option");        
        }
        
        public static ArrayList<ItemOption> getAllItemOption()
        {
            ArrayList<ItemOption> item_option = new ArrayList<ItemOption>();
            try
            {
                getAllRecordsByTableName("item_option");
                while(rs.next())
                {
                    item_option.add(processItemOption(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemOption error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
        }
                
        public static ArrayList<ItemOption> getItemOptionPaged(int limit, int offset)
        {
            ArrayList<ItemOption> item_option = new ArrayList<ItemOption>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_option", limit, offset);
                while (rs.next())
                {
                    item_option.add(processItemOption(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemOptionPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
        } 
        
        public static ArrayList<ItemOption> getAllItemOptionByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemOption> item_option = new ArrayList<ItemOption>();
            try
            {
                getAllRecordsByColumn("item_option", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_option.add(processItemOption(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemOptionByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
        }
        
        public static ItemOption getItemOptionByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemOption item_option = new ItemOption();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_option", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_option = processItemOption(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemOptionByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_option;
        }                
                
        public static void updateItemOption(Integer ItemOptionId,String ItemOptionType,String ItemOptionDescription)
        {  
            try
            {   
                
                checkColumnSize(ItemOptionType, 45);
                checkColumnSize(ItemOptionDescription, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_option SET ItemOptionType=?,ItemOptionDescription=? WHERE ItemOptionId=?;");                    
                preparedStatement.setString(1, ItemOptionType);
                preparedStatement.setString(2, ItemOptionDescription);
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
        
        public static void deleteAllItemOption()
        {
            try
            {
                updateQuery("DELETE FROM item_option;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemOption error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemOptionById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + id + ";");            
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

        public static void deleteItemOptionByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_option WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemOptionByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

