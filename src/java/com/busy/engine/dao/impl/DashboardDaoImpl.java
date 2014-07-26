





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class DashboardDaoImpl extends BasicConnection implements Serializable, DashboardDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Dashboard find(Integer id)
        {
            return findByColumn("DashboardId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Dashboard> findAll(Integer limit, Integer offset)
        {
            ArrayList<Dashboard> dashboard = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("dashboard");
                while(rs.next())
                {
                    dashboard.add(Dashboard.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Dashboard object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
         
        }
        
        @Override
        public ArrayList<Dashboard> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Dashboard> dashboardList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("dashboard", limit, offset);
                while (rs.next())
                {
                    dashboardList.add(Dashboard.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Dashboard method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboardList;
        }
        
        @Override
        public ArrayList<Dashboard> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Dashboard> dashboard = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("dashboard", Dashboard.checkColumnName(columnName), columnValue, Dashboard.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   dashboard.add(Dashboard.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Dashboard's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        } 
    
        @Override
        public void add(Dashboard obj)
        {
            try
            {
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO dashboard(UserCount,BlogPostCount,ItemCount,OrderCount,SiteFileCount,ImageCount,BlogCount,CommentCount,PageCount,FormCount,SliderCount,ItemBrandCount,CategoryCount,ItemOptionCount) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, obj.getUserCount());
                preparedStatement.setInt(2, obj.getBlogPostCount());
                preparedStatement.setInt(3, obj.getItemCount());
                preparedStatement.setInt(4, obj.getOrderCount());
                preparedStatement.setInt(5, obj.getSiteFileCount());
                preparedStatement.setInt(6, obj.getImageCount());
                preparedStatement.setInt(7, obj.getBlogCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPageCount());
                preparedStatement.setInt(10, obj.getFormCount());
                preparedStatement.setInt(11, obj.getSliderCount());
                preparedStatement.setInt(12, obj.getItemBrandCount());
                preparedStatement.setInt(13, obj.getCategoryCount());
                preparedStatement.setInt(14, obj.getItemOptionCount());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer UserCount, Integer BlogPostCount, Integer ItemCount, Integer OrderCount, Integer SiteFileCount, Integer ImageCount, Integer BlogCount, Integer CommentCount, Integer PageCount, Integer FormCount, Integer SliderCount, Integer ItemBrandCount, Integer CategoryCount, Integer ItemOptionCount)
        {   
            int id = 0;
            try
            {
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO dashboard(UserCount,BlogPostCount,ItemCount,OrderCount,SiteFileCount,ImageCount,BlogCount,CommentCount,PageCount,FormCount,SliderCount,ItemBrandCount,CategoryCount,ItemOptionCount) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, UserCount);
                preparedStatement.setInt(2, BlogPostCount);
                preparedStatement.setInt(3, ItemCount);
                preparedStatement.setInt(4, OrderCount);
                preparedStatement.setInt(5, SiteFileCount);
                preparedStatement.setInt(6, ImageCount);
                preparedStatement.setInt(7, BlogCount);
                preparedStatement.setInt(8, CommentCount);
                preparedStatement.setInt(9, PageCount);
                preparedStatement.setInt(10, FormCount);
                preparedStatement.setInt(11, SliderCount);
                preparedStatement.setInt(12, ItemBrandCount);
                preparedStatement.setInt(13, CategoryCount);
                preparedStatement.setInt(14, ItemOptionCount);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from dashboard;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addDashboard error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        
        @Override
        public Dashboard update(Dashboard obj)
        {
           try
            {   
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE dashboard SET UserCount=?,BlogPostCount=?,ItemCount=?,OrderCount=?,SiteFileCount=?,ImageCount=?,BlogCount=?,CommentCount=?,PageCount=?,FormCount=?,SliderCount=?,ItemBrandCount=?,CategoryCount=?,ItemOptionCount=? WHERE DashboardId=?;");                    
                preparedStatement.setInt(1, obj.getUserCount());
                preparedStatement.setInt(2, obj.getBlogPostCount());
                preparedStatement.setInt(3, obj.getItemCount());
                preparedStatement.setInt(4, obj.getOrderCount());
                preparedStatement.setInt(5, obj.getSiteFileCount());
                preparedStatement.setInt(6, obj.getImageCount());
                preparedStatement.setInt(7, obj.getBlogCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPageCount());
                preparedStatement.setInt(10, obj.getFormCount());
                preparedStatement.setInt(11, obj.getSliderCount());
                preparedStatement.setInt(12, obj.getItemBrandCount());
                preparedStatement.setInt(13, obj.getCategoryCount());
                preparedStatement.setInt(14, obj.getItemOptionCount());
                preparedStatement.setInt(15, obj.getDashboardId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateDashboard error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer DashboardId,Integer UserCount,Integer BlogPostCount,Integer ItemCount,Integer OrderCount,Integer SiteFileCount,Integer ImageCount,Integer BlogCount,Integer CommentCount,Integer PageCount,Integer FormCount,Integer SliderCount,Integer ItemBrandCount,Integer CategoryCount,Integer ItemOptionCount)
        {  
            try
            {   
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE dashboard SET UserCount=?,BlogPostCount=?,ItemCount=?,OrderCount=?,SiteFileCount=?,ImageCount=?,BlogCount=?,CommentCount=?,PageCount=?,FormCount=?,SliderCount=?,ItemBrandCount=?,CategoryCount=?,ItemOptionCount=? WHERE DashboardId=?;");                    
                preparedStatement.setInt(1, UserCount);
                preparedStatement.setInt(2, BlogPostCount);
                preparedStatement.setInt(3, ItemCount);
                preparedStatement.setInt(4, OrderCount);
                preparedStatement.setInt(5, SiteFileCount);
                preparedStatement.setInt(6, ImageCount);
                preparedStatement.setInt(7, BlogCount);
                preparedStatement.setInt(8, CommentCount);
                preparedStatement.setInt(9, PageCount);
                preparedStatement.setInt(10, FormCount);
                preparedStatement.setInt(11, SliderCount);
                preparedStatement.setInt(12, ItemBrandCount);
                preparedStatement.setInt(13, CategoryCount);
                preparedStatement.setInt(14, ItemOptionCount);
                preparedStatement.setInt(15, DashboardId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateDashboard error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("dashboard");
        }
        
        
        @Override
        public Dashboard getRelatedInfo(Dashboard dashboard)
        {
              
            
            return dashboard;
        }
        
        
        @Override
        public Dashboard getRelatedObjects(Dashboard dashboard)
        {
                         
            return dashboard;
        }
        
        
        
        @Override
        public void remove(Dashboard obj)
        {            
            try
            {
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + obj.getDashboardId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteDashboardById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteDashboardById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM dashboard;");
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM dashboard WHERE " + Dashboard.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

