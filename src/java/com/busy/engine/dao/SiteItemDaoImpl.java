





































    package com.busy.engine.dao;

import com.busy.engine.entity.Site;
import com.busy.engine.entity.Item;
import com.busy.engine.entity.SiteItem;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("SiteItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_item;
        } 
    
        @Override
        public int add(SiteItem obj)
        {
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_item(SiteId,ItemId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getItemId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_item;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's add method error: " + ex.getMessage());
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
                System.out.println("SiteItem's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("site_item");
        }
        
        
        @Override
        public void getRelatedInfo(SiteItem site_item)
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
              
        }
        
        @Override
        public void getRelatedObjects(SiteItem site_item)
        {
             
        }
        
        @Override
        public boolean remove(SiteItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + obj.getSiteItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + id + ";");           
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
                updateQuery("DELETE FROM site_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_item WHERE " + SiteItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

