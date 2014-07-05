


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemBrand extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMBRANDID = "ItemBrandId";
        public static final String PROP_ITEMBRANDNAME = "ItemBrandName";
        public static final String PROP_ITEMBRANDDESCRIPTION = "ItemBrandDescription";
        
        
        private Integer itemBrandId;
        private String itemBrandName;
        private String itemBrandDescription;
        
        
        public ItemBrand()
        {
            this.itemBrandId = 0; 
       this.itemBrandName = ""; 
       this.itemBrandDescription = ""; 
        }
        
        public ItemBrand(Integer ItemBrandId, String ItemBrandName, String ItemBrandDescription)
        {
            this.itemBrandId = ItemBrandId;
       this.itemBrandName = ItemBrandName;
       this.itemBrandDescription = ItemBrandDescription;
        } 
        
        
            public Integer getItemBrandId()
            {
                return this.itemBrandId;
            }
            
            public void setItemBrandId(Integer ItemBrandId)
            {
                this.itemBrandId = ItemBrandId;
            }
        
            public String getItemBrandName()
            {
                return this.itemBrandName;
            }
            
            public void setItemBrandName(String ItemBrandName)
            {
                this.itemBrandName = ItemBrandName;
            }
        
            public String getItemBrandDescription()
            {
                return this.itemBrandDescription;
            }
            
            public void setItemBrandDescription(String ItemBrandDescription)
            {
                this.itemBrandDescription = ItemBrandDescription;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemBrand.PROP_ITEMBRANDID) || column.equals(ItemBrand.PROP_ITEMBRANDNAME) || column.equals(ItemBrand.PROP_ITEMBRANDDESCRIPTION) )
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
            if (column.equals(ItemBrand.PROP_ITEMBRANDID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemBrand processItemBrand(ResultSet rs) throws SQLException
        {        
            return new ItemBrand(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addItemBrand(String ItemBrandName, String ItemBrandDescription)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ItemBrandName, 100);
                checkColumnSize(ItemBrandDescription, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_brand(ItemBrandName,ItemBrandDescription) VALUES (?,?);");                    
                preparedStatement.setString(1, ItemBrandName);
                preparedStatement.setString(2, ItemBrandDescription);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_brand;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemBrandCount()
        {
            return getAllRecordsCountByTableName("item_brand");        
        }
        
        public static ArrayList<ItemBrand> getAllItemBrand()
        {
            ArrayList<ItemBrand> item_brand = new ArrayList<ItemBrand>();
            try
            {
                getAllRecordsByTableName("item_brand");
                while(rs.next())
                {
                    item_brand.add(processItemBrand(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
        }
                
        public static ArrayList<ItemBrand> getItemBrandPaged(int limit, int offset)
        {
            ArrayList<ItemBrand> item_brand = new ArrayList<ItemBrand>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_brand", limit, offset);
                while (rs.next())
                {
                    item_brand.add(processItemBrand(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemBrandPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
        } 
        
        public static ArrayList<ItemBrand> getAllItemBrandByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemBrand> item_brand = new ArrayList<ItemBrand>();
            try
            {
                getAllRecordsByColumn("item_brand", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_brand.add(processItemBrand(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemBrandByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
        }
        
        public static ItemBrand getItemBrandByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemBrand item_brand = new ItemBrand();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_brand", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_brand = processItemBrand(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemBrandByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_brand;
        }                
                
        public static void updateItemBrand(Integer ItemBrandId,String ItemBrandName,String ItemBrandDescription)
        {  
            try
            {   
                
                checkColumnSize(ItemBrandName, 100);
                checkColumnSize(ItemBrandDescription, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_brand SET ItemBrandName=?,ItemBrandDescription=? WHERE ItemBrandId=?;");                    
                preparedStatement.setString(1, ItemBrandName);
                preparedStatement.setString(2, ItemBrandDescription);
                preparedStatement.setInt(3, ItemBrandId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemBrand()
        {
            try
            {
                updateQuery("DELETE FROM item_brand;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemBrand error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemBrandById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemBrandById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemBrandByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_brand WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemBrandByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

