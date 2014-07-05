


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PageTemplate extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_PAGETEMPLATEID = "PageTemplateId";
        public static final String PROP_TEMPLATENAME = "TemplateName";
        public static final String PROP_TEMPLATEMARKUP = "TemplateMarkup";
        
        
        private Integer pageTemplateId;
        private String templateName;
        private String templateMarkup;
        
        
        public PageTemplate()
        {
            this.pageTemplateId = 0; 
       this.templateName = ""; 
       this.templateMarkup = ""; 
        }
        
        public PageTemplate(Integer PageTemplateId, String TemplateName, String TemplateMarkup)
        {
            this.pageTemplateId = PageTemplateId;
       this.templateName = TemplateName;
       this.templateMarkup = TemplateMarkup;
        } 
        
        
            public Integer getPageTemplateId()
            {
                return this.pageTemplateId;
            }
            
            public void setPageTemplateId(Integer PageTemplateId)
            {
                this.pageTemplateId = PageTemplateId;
            }
        
            public String getTemplateName()
            {
                return this.templateName;
            }
            
            public void setTemplateName(String TemplateName)
            {
                this.templateName = TemplateName;
            }
        
            public String getTemplateMarkup()
            {
                return this.templateMarkup;
            }
            
            public void setTemplateMarkup(String TemplateMarkup)
            {
                this.templateMarkup = TemplateMarkup;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(PageTemplate.PROP_PAGETEMPLATEID) || column.equals(PageTemplate.PROP_TEMPLATENAME) || column.equals(PageTemplate.PROP_TEMPLATEMARKUP) )
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
            if (column.equals(PageTemplate.PROP_PAGETEMPLATEID) )
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
        
        public static int addPageTemplate(String TemplateName, String TemplateMarkup)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TemplateName, 45);
                checkColumnSize(TemplateMarkup, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO page_template(TemplateName,TemplateMarkup) VALUES (?,?);");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, TemplateMarkup);
                
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
                
        public static void updatePageTemplate(Integer PageTemplateId,String TemplateName,String TemplateMarkup)
        {  
            try
            {   
                
                checkColumnSize(TemplateName, 45);
                checkColumnSize(TemplateMarkup, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE page_template SET TemplateName=?,TemplateMarkup=? WHERE PageTemplateId=?;");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, TemplateMarkup);
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

