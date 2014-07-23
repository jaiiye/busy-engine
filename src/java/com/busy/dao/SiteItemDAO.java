





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteItemDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteItem.PROP_SITE_ITEM_ID) || column.equals(SiteItem.PROP_SITE_ID) || column.equals(SiteItem.PROP_ITEM_ID) )
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
            if (column.equals(SiteItem.PROP_SITE_ITEM_ID) || column.equals(SiteItem.PROP_SITE_ID) || column.equals(SiteItem.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteItem processSiteItem(ResultSet rs) throws SQLException
        {        
            return new SiteItem(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addSiteItem(Integer SiteId, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_item(SiteId,ItemId) VALUES (?,?);");                    
                preparedStatement.setInt(1, SiteId);
                preparedStatement.setInt(2, ItemId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteItemCount()
        {
            return getAllRecordsCountByTableName("site_item");        
        }
        
        public static ArrayList<SiteItem> getAllSiteItem()
        {
            ArrayList<SiteItem> site_item = new ArrayList<SiteItem>();
            try
            {
                getAllRecordsByTableName("site_item");
                while(rs.next())
                {
                    site_item.add(processSiteItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
        }
        
        public static ArrayList<SiteItem> getAllSiteItemWithRelatedInfo()
        {
            ArrayList<SiteItem> site_itemList = new ArrayList<SiteItem>();
            try
            {
                getAllRecordsByTableName("site_item");
                while (rs.next())
                {
                    site_itemList.add(processSiteItem(rs));
                }

                
                    for(SiteItem site_item : site_itemList)
                    {
                        
                            getRecordById("Site", site_item.getSiteId().toString());
                            site_item.setSite(SiteDAO.processSite(rs));               
                        
                            getRecordById("Item", site_item.getItemId().toString());
                            site_item.setItem(ItemDAO.processItem(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteItemWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_itemList;
        }
        
        
        public static SiteItem getRelatedInfo(SiteItem site_item)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Site", site_item.getSiteId().toString());
                            site_item.setSite(SiteDAO.processSite(rs));               
                        
                            getRecordById("Item", site_item.getItemId().toString());
                            site_item.setItem(ItemDAO.processItem(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return site_item;
        }
        
        public static SiteItem getAllRelatedObjects(SiteItem site_item)
        {           
                         
            return site_item;
        }
        
        
        
                
        public static ArrayList<SiteItem> getSiteItemPaged(int limit, int offset)
        {
            ArrayList<SiteItem> site_item = new ArrayList<SiteItem>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_item", limit, offset);
                while (rs.next())
                {
                    site_item.add(processSiteItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteItemPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
        } 
        
        public static ArrayList<SiteItem> getAllSiteItemByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteItem> site_item = new ArrayList<SiteItem>();
            try
            {
                getAllRecordsByColumn("site_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_item.add(processSiteItem(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
        }
        
        public static SiteItem getSiteItemByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteItem site_item = new SiteItem();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_item", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_item = processSiteItem(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteItemByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
        }                
                
        public static void updateSiteItem(Integer SiteItemId,Integer SiteId,Integer ItemId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_item SET SiteId=?,ItemId=? WHERE SiteItemId=?;");                    
                preparedStatement.setInt(1, SiteId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, SiteItemId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteItem()
        {
            try
            {
                updateQuery("DELETE FROM site_item;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteItemById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteItemById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteItemByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_item WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteItemByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

