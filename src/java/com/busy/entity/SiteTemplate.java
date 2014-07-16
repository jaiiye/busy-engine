


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteTemplate extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITETEMPLATEID = "SiteTemplateId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_SITETEMPLATETYPEID = "SiteTemplateTypeId";
        
        
        private Integer siteTemplateId;
        private String name;
        private Integer status;
        private Integer siteTemplateTypeId;
        
        
        public SiteTemplate()
        {
            this.siteTemplateId = 0; 
       this.name = ""; 
       this.status = 0; 
       this.siteTemplateTypeId = 0; 
        }
        
        public SiteTemplate(Integer SiteTemplateId, String Name, Integer Status, Integer SiteTemplateTypeId)
        {
            this.siteTemplateId = SiteTemplateId;
       this.name = Name;
       this.status = Status;
       this.siteTemplateTypeId = SiteTemplateTypeId;
        } 
        
        
            public Integer getSiteTemplateId()
            {
                return this.siteTemplateId;
            }
            
            public void setSiteTemplateId(Integer SiteTemplateId)
            {
                this.siteTemplateId = SiteTemplateId;
            }
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getSiteTemplateTypeId()
            {
                return this.siteTemplateTypeId;
            }
            
            public void setSiteTemplateTypeId(Integer SiteTemplateTypeId)
            {
                this.siteTemplateTypeId = SiteTemplateTypeId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteTemplate.PROP_SITETEMPLATEID) || column.equals(SiteTemplate.PROP_NAME) || column.equals(SiteTemplate.PROP_STATUS) || column.equals(SiteTemplate.PROP_SITETEMPLATETYPEID) )
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
            if (column.equals(SiteTemplate.PROP_SITETEMPLATEID) || column.equals(SiteTemplate.PROP_STATUS) || column.equals(SiteTemplate.PROP_SITETEMPLATETYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteTemplate processSiteTemplate(ResultSet rs) throws SQLException
        {        
            return new SiteTemplate(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addSiteTemplate(String Name, Integer Status, Integer SiteTemplateTypeId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_template(Name,Status,SiteTemplateTypeId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setInt(2, Status);
                preparedStatement.setInt(3, SiteTemplateTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteTemplateCount()
        {
            return getAllRecordsCountByTableName("site_template");        
        }
        
        public static ArrayList<SiteTemplate> getAllSiteTemplate()
        {
            ArrayList<SiteTemplate> site_template = new ArrayList<SiteTemplate>();
            try
            {
                getAllRecordsByTableName("site_template");
                while(rs.next())
                {
                    site_template.add(processSiteTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template;
        }
                
        public static ArrayList<SiteTemplate> getSiteTemplatePaged(int limit, int offset)
        {
            ArrayList<SiteTemplate> site_template = new ArrayList<SiteTemplate>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_template", limit, offset);
                while (rs.next())
                {
                    site_template.add(processSiteTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteTemplatePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template;
        } 
        
        public static ArrayList<SiteTemplate> getAllSiteTemplateByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteTemplate> site_template = new ArrayList<SiteTemplate>();
            try
            {
                getAllRecordsByColumn("site_template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_template.add(processSiteTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template;
        }
        
        public static SiteTemplate getSiteTemplateByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteTemplate site_template = new SiteTemplate();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_template = processSiteTemplate(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteTemplateByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template;
        }                
                
        public static void updateSiteTemplate(Integer SiteTemplateId,String Name,Integer Status,Integer SiteTemplateTypeId)
        {  
            try
            {   
                
                checkColumnSize(Name, 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_template SET Name=?,Status=?,SiteTemplateTypeId=? WHERE SiteTemplateId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setInt(2, Status);
                preparedStatement.setInt(3, SiteTemplateTypeId);
                preparedStatement.setInt(4, SiteTemplateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteTemplate()
        {
            try
            {
                updateQuery("DELETE FROM site_template;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteTemplateById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_template WHERE SiteTemplateId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteTemplateById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteTemplateByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_template WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

