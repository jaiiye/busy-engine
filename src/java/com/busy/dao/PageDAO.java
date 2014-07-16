











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Page;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PageDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Page.PROP_PAGE_ID) || column.equals(Page.PROP_PAGE_NAME) || column.equals(Page.PROP_CONTENT) || column.equals(Page.PROP_STATUS) || column.equals(Page.PROP_FORM_ID) || column.equals(Page.PROP_SLIDER_ID) || column.equals(Page.PROP_META_TAG_ID) || column.equals(Page.PROP_TEMPLATE_ID) )
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
            if (column.equals(Page.PROP_PAGE_ID) || column.equals(Page.PROP_STATUS) || column.equals(Page.PROP_FORM_ID) || column.equals(Page.PROP_SLIDER_ID) || column.equals(Page.PROP_META_TAG_ID) || column.equals(Page.PROP_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Page processPage(ResultSet rs) throws SQLException
        {        
            return new Page(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addPage(String PageName, String Content, Integer Status, Integer FormId, Integer SliderId, Integer MetaTagId, Integer TemplateId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(PageName, 150);
                checkColumnSize(Content, 65535);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO page(PageName,Content,Status,FormId,SliderId,MetaTagId,TemplateId) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, PageName);
                preparedStatement.setString(2, Content);
                preparedStatement.setInt(3, Status);
                preparedStatement.setInt(4, FormId);
                preparedStatement.setInt(5, SliderId);
                preparedStatement.setInt(6, MetaTagId);
                preparedStatement.setInt(7, TemplateId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from page;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addPage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllPageCount()
        {
            return getAllRecordsCountByTableName("page");        
        }
        
        public static ArrayList<Page> getAllPage()
        {
            ArrayList<Page> page = new ArrayList<Page>();
            try
            {
                getAllRecordsByTableName("page");
                while(rs.next())
                {
                    page.add(processPage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
        }
                
        public static ArrayList<Page> getPagePaged(int limit, int offset)
        {
            ArrayList<Page> page = new ArrayList<Page>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("page", limit, offset);
                while (rs.next())
                {
                    page.add(processPage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPagePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
        } 
        
        public static ArrayList<Page> getAllPageByColumn(String columnName, String columnValue)
        {
            ArrayList<Page> page = new ArrayList<Page>();
            try
            {
                getAllRecordsByColumn("page", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    page.add(processPage(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
        }
        
        public static Page getPageByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Page page = new Page();
            try
            {
                getRecordsByColumnWithLimitAndOffset("page", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   page = processPage(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPageByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page;
        }                
                
        public static void updatePage(Integer PageId,String PageName,String Content,Integer Status,Integer FormId,Integer SliderId,Integer MetaTagId,Integer TemplateId)
        {  
            try
            {   
                
                checkColumnSize(PageName, 150);
                checkColumnSize(Content, 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE page SET PageName=?,Content=?,Status=?,FormId=?,SliderId=?,MetaTagId=?,TemplateId=? WHERE PageId=?;");                    
                preparedStatement.setString(1, PageName);
                preparedStatement.setString(2, Content);
                preparedStatement.setInt(3, Status);
                preparedStatement.setInt(4, FormId);
                preparedStatement.setInt(5, SliderId);
                preparedStatement.setInt(6, MetaTagId);
                preparedStatement.setInt(7, TemplateId);
                preparedStatement.setInt(8, PageId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updatePage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllPage()
        {
            try
            {
                updateQuery("DELETE FROM page;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllPage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deletePageById(String id)
        {
            try
            {
                updateQuery("DELETE FROM page WHERE PageId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deletePageById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deletePageByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM page WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deletePageByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

