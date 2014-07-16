











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.ItemOption;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemOptionDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemOption.PROP_ITEM_OPTION_ID) || column.equals(ItemOption.PROP_OPTION_NAME) || column.equals(ItemOption.PROP_DESCRIPTION) )
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
            if (column.equals(ItemOption.PROP_ITEM_OPTION_ID) )
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
        
        public static int addItemOption(String OptionName, String Description)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(OptionName, 100);
                checkColumnSize(Description, 65535);
                                            
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
                
        public static void updateItemOption(Integer ItemOptionId,String OptionName,String Description)
        {  
            try
            {   
                
                checkColumnSize(OptionName, 100);
                checkColumnSize(Description, 65535);
                                  
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

