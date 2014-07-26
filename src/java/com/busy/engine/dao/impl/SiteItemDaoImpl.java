





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteItemDaoImpl extends BasicConnection implements Serializable, SiteItemDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public SiteItem find(Integer id)
        {
            return findByColumn("SiteItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteItem> site_item = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site_item");
                while(rs.next())
                {
                    site_item.add(SiteItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
         
        }
        
        @Override
        public ArrayList<SiteItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteItem> site_itemList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("site_item", limit, offset);
                while (rs.next())
                {
                    site_itemList.add(SiteItem.process(rs));
                }

                
                    for(SiteItem site_item : site_itemList)
                    {
                        
                            getRecordById("Site", site_item.getSiteId().toString());
                            site_item.setSite(Site.process(rs));               
                        
                            getRecordById("Item", site_item.getItemId().toString());
                            site_item.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_itemList;
        }
        
        @Override
        public ArrayList<SiteItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteItem> site_item = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("site_item", SiteItem.checkColumnName(columnName), columnValue, SiteItem.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_item.add(SiteItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object SiteItem's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
        } 
    
        @Override
        public void add(SiteItem obj)
        {
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_item(SiteId,ItemId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getItemId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer SiteId, Integer ItemId)
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
        
        
        @Override
        public SiteItem update(SiteItem obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_item SET SiteId=?,ItemId=? WHERE SiteItemId=?;");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getSiteItemId());
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
            return obj;
        }
        
        public static void update(Integer SiteItemId,Integer SiteId,Integer ItemId)
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("site_item");
        }
        
        
        @Override
        public SiteItem getRelatedInfo(SiteItem site_item)
        {
            
                try
                { 
                    
                        getRecordById("Site", site_item.getSiteId().toString());
                        site_item.setSite(Site.process(rs));               
                    
                        getRecordById("Item", site_item.getItemId().toString());
                        site_item.setItem(Item.process(rs));               
                    

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
        
        
        @Override
        public SiteItem getRelatedObjects(SiteItem site_item)
        {
                         
            return site_item;
        }
        
        
        
        @Override
        public void remove(SiteItem obj)
        {            
            try
            {
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + obj.getSiteItemId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM site_item;");
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_item WHERE " + SiteItem.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

