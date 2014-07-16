


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PageTemplateDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(PageTemplate.PROP_PAGE_TEMPLATE_ID) || column.equals(PageTemplate.PROP_NAME) || column.equals(PageTemplate.PROP_MARKUP) )
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
            if (column.equals(PageTemplate.PROP_PAGE_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static PageTemplate processPageTemplate(ResultSet rs) throws SQLException
        {        
            return new PageTemplate(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addPageTemplate(String Name, String Markup)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 45);
                checkColumnSize(Markup, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO page_template(Name,Markup) VALUES (?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Markup);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from page_template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addPageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllPageTemplateCount()
        {
            return getAllRecordsCountByTableName("page_template");        
        }
        
        public static ArrayList<PageTemplate> getAllPageTemplate()
        {
            ArrayList<PageTemplate> page_template = new ArrayList<PageTemplate>();
            try
            {
                getAllRecordsByTableName("page_template");
                while(rs.next())
                {
                    page_template.add(processPageTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_template;
        }
                
        public static ArrayList<PageTemplate> getPageTemplatePaged(int limit, int offset)
        {
            ArrayList<PageTemplate> page_template = new ArrayList<PageTemplate>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("page_template", limit, offset);
                while (rs.next())
                {
                    page_template.add(processPageTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPageTemplatePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_template;
        } 
        
        public static ArrayList<PageTemplate> getAllPageTemplateByColumn(String columnName, String columnValue)
        {
            ArrayList<PageTemplate> page_template = new ArrayList<PageTemplate>();
            try
            {
                getAllRecordsByColumn("page_template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    page_template.add(processPageTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPageTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_template;
        }
        
        public static PageTemplate getPageTemplateByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            PageTemplate page_template = new PageTemplate();
            try
            {
                getRecordsByColumnWithLimitAndOffset("page_template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   page_template = processPageTemplate(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPageTemplateByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return page_template;
        }                
                
        public static void updatePageTemplate(Integer PageTemplateId,String Name,String Markup)
        {  
            try
            {   
                
                checkColumnSize(Name, 45);
                checkColumnSize(Markup, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE page_template SET Name=?,Markup=? WHERE PageTemplateId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Markup);
                preparedStatement.setInt(3, PageTemplateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updatePageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllPageTemplate()
        {
            try
            {
                updateQuery("DELETE FROM page_template;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllPageTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deletePageTemplateById(String id)
        {
            try
            {
                updateQuery("DELETE FROM page_template WHERE PageTemplateId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deletePageTemplateById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deletePageTemplateByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM page_template WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deletePageTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

