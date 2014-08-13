





































    package com.busy.engine.dao;

import com.busy.engine.entity.Dashboard;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("Dashboard's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        } 
    
        @Override
        public int add(Dashboard obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from dashboard;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's add method error: " + ex.getMessage());
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
                System.out.println("Dashboard's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("dashboard");
        }
        
        
        @Override
        public void getRelatedInfo(Dashboard dashboard)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Dashboard dashboard)
        {
             
        }
        
        @Override
        public boolean remove(Dashboard obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + obj.getDashboardId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + id + ";");           
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
                updateQuery("DELETE FROM dashboard;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM dashboard WHERE " + Dashboard.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

