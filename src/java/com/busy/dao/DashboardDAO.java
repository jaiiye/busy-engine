


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class DashboardDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Dashboard.PROP_DASHBOARD_ID) || column.equals(Dashboard.PROP_USER_COUNT) || column.equals(Dashboard.PROP_BLOG_POST_COUNT) || column.equals(Dashboard.PROP_ITEM_COUNT) || column.equals(Dashboard.PROP_ORDER_COUNT) || column.equals(Dashboard.PROP_SITE_FILE_COUNT) || column.equals(Dashboard.PROP_IMAGE_COUNT) || column.equals(Dashboard.PROP_BLOG_COUNT) || column.equals(Dashboard.PROP_COMMENT_COUNT) || column.equals(Dashboard.PROP_PAGE_COUNT) || column.equals(Dashboard.PROP_FORM_COUNT) || column.equals(Dashboard.PROP_SLIDER_COUNT) || column.equals(Dashboard.PROP_ITEM_BRAND_COUNT) || column.equals(Dashboard.PROP_CATEGORY_COUNT) || column.equals(Dashboard.PROP_ITEM_OPTION_COUNT) )
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
            if (column.equals(Dashboard.PROP_DASHBOARD_ID) || column.equals(Dashboard.PROP_USER_COUNT) || column.equals(Dashboard.PROP_BLOG_POST_COUNT) || column.equals(Dashboard.PROP_ITEM_COUNT) || column.equals(Dashboard.PROP_ORDER_COUNT) || column.equals(Dashboard.PROP_SITE_FILE_COUNT) || column.equals(Dashboard.PROP_IMAGE_COUNT) || column.equals(Dashboard.PROP_BLOG_COUNT) || column.equals(Dashboard.PROP_COMMENT_COUNT) || column.equals(Dashboard.PROP_PAGE_COUNT) || column.equals(Dashboard.PROP_FORM_COUNT) || column.equals(Dashboard.PROP_SLIDER_COUNT) || column.equals(Dashboard.PROP_ITEM_BRAND_COUNT) || column.equals(Dashboard.PROP_CATEGORY_COUNT) || column.equals(Dashboard.PROP_ITEM_OPTION_COUNT) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Dashboard processDashboard(ResultSet rs) throws SQLException
        {        
            return new Dashboard(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15));
        }
        
        public static int addDashboard(Integer UserCount, Integer BlogPostCount, Integer ItemCount, Integer OrderCount, Integer SiteFileCount, Integer ImageCount, Integer BlogCount, Integer CommentCount, Integer PageCount, Integer FormCount, Integer SliderCount, Integer ItemBrandCount, Integer CategoryCount, Integer ItemOptionCount)
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
        
        public static int getAllDashboardCount()
        {
            return getAllRecordsCountByTableName("dashboard");        
        }
        
        public static ArrayList<Dashboard> getAllDashboard()
        {
            ArrayList<Dashboard> dashboard = new ArrayList<Dashboard>();
            try
            {
                getAllRecordsByTableName("dashboard");
                while(rs.next())
                {
                    dashboard.add(processDashboard(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllDashboard error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        }
                
        public static ArrayList<Dashboard> getDashboardPaged(int limit, int offset)
        {
            ArrayList<Dashboard> dashboard = new ArrayList<Dashboard>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("dashboard", limit, offset);
                while (rs.next())
                {
                    dashboard.add(processDashboard(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getDashboardPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        } 
        
        public static ArrayList<Dashboard> getAllDashboardByColumn(String columnName, String columnValue)
        {
            ArrayList<Dashboard> dashboard = new ArrayList<Dashboard>();
            try
            {
                getAllRecordsByColumn("dashboard", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    dashboard.add(processDashboard(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllDashboardByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        }
        
        public static Dashboard getDashboardByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Dashboard dashboard = new Dashboard();
            try
            {
                getRecordsByColumnWithLimitAndOffset("dashboard", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   dashboard = processDashboard(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getDashboardByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        }                
                
        public static void updateDashboard(Integer DashboardId,Integer UserCount,Integer BlogPostCount,Integer ItemCount,Integer OrderCount,Integer SiteFileCount,Integer ImageCount,Integer BlogCount,Integer CommentCount,Integer PageCount,Integer FormCount,Integer SliderCount,Integer ItemBrandCount,Integer CategoryCount,Integer ItemOptionCount)
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
        
        public static void deleteAllDashboard()
        {
            try
            {
                updateQuery("DELETE FROM dashboard;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllDashboard error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteDashboardById(String id)
        {
            try
            {
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + id + ";");            
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

        public static void deleteDashboardByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM dashboard WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteDashboardByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

