


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteTemplateUrl extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITETEMPLATEURLID = "SiteTemplateUrlId";
        public static final String PROP_URL = "Url";
        public static final String PROP_SITETEMPLATEID = "SiteTemplateId";
        public static final String PROP_RESOURCETYPEID = "ResourceTypeId";
        
        
        private Integer siteTemplateUrlId;
        private String url;
        private Integer siteTemplateId;
        private String resourceTypeId;
        
        
        public SiteTemplateUrl()
        {
            this.siteTemplateUrlId = 0; 
       this.url = ""; 
       this.siteTemplateId = 0; 
       this.resourceTypeId = ""; 
        }
        
        public SiteTemplateUrl(Integer SiteTemplateUrlId, String Url, Integer SiteTemplateId, String ResourceTypeId)
        {
            this.siteTemplateUrlId = SiteTemplateUrlId;
       this.url = Url;
       this.siteTemplateId = SiteTemplateId;
       this.resourceTypeId = ResourceTypeId;
        } 
        
        
            public Integer getSiteTemplateUrlId()
            {
                return this.siteTemplateUrlId;
            }
            
            public void setSiteTemplateUrlId(Integer SiteTemplateUrlId)
            {
                this.siteTemplateUrlId = SiteTemplateUrlId;
            }
        
            public String getUrl()
            {
                return this.url;
            }
            
            public void setUrl(String Url)
            {
                this.url = Url;
            }
        
            public Integer getSiteTemplateId()
            {
                return this.siteTemplateId;
            }
            
            public void setSiteTemplateId(Integer SiteTemplateId)
            {
                this.siteTemplateId = SiteTemplateId;
            }
        
            public String getResourceTypeId()
            {
                return this.resourceTypeId;
            }
            
            public void setResourceTypeId(String ResourceTypeId)
            {
                this.resourceTypeId = ResourceTypeId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteTemplateUrl.PROP_SITETEMPLATEURLID) || column.equals(SiteTemplateUrl.PROP_URL) || column.equals(SiteTemplateUrl.PROP_SITETEMPLATEID) || column.equals(SiteTemplateUrl.PROP_RESOURCETYPEID) )
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
            if (column.equals(SiteTemplateUrl.PROP_SITETEMPLATEURLID) || column.equals(SiteTemplateUrl.PROP_SITETEMPLATEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteTemplateUrl processSiteTemplateUrl(ResultSet rs) throws SQLException
        {        
            return new SiteTemplateUrl(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
        }
        
        public static int addSiteTemplateUrl(String Url, Integer SiteTemplateId, String ResourceTypeId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Url, 255);
                
                checkColumnSize(ResourceTypeId, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO site_template_url(Url,SiteTemplateId,ResourceTypeId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Url);
                preparedStatement.setInt(2, SiteTemplateId);
                preparedStatement.setString(3, ResourceTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_template_url;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteTemplateUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteTemplateUrlCount()
        {
            return getAllRecordsCountByTableName("site_template_url");        
        }
        
        public static ArrayList<SiteTemplateUrl> getAllSiteTemplateUrl()
        {
            ArrayList<SiteTemplateUrl> site_template_url = new ArrayList<SiteTemplateUrl>();
            try
            {
                getAllRecordsByTableName("site_template_url");
                while(rs.next())
                {
                    site_template_url.add(processSiteTemplateUrl(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteTemplateUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_url;
        }
                
        public static ArrayList<SiteTemplateUrl> getSiteTemplateUrlPaged(int limit, int offset)
        {
            ArrayList<SiteTemplateUrl> site_template_url = new ArrayList<SiteTemplateUrl>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_template_url", limit, offset);
                while (rs.next())
                {
                    site_template_url.add(processSiteTemplateUrl(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteTemplateUrlPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_url;
        } 
        
        public static ArrayList<SiteTemplateUrl> getAllSiteTemplateUrlByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteTemplateUrl> site_template_url = new ArrayList<SiteTemplateUrl>();
            try
            {
                getAllRecordsByColumn("site_template_url", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_template_url.add(processSiteTemplateUrl(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteTemplateUrlByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_url;
        }
        
        public static SiteTemplateUrl getSiteTemplateUrlByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteTemplateUrl site_template_url = new SiteTemplateUrl();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_template_url", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_template_url = processSiteTemplateUrl(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteTemplateUrlByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_url;
        }                
                
        public static void updateSiteTemplateUrl(Integer SiteTemplateUrlId,String Url,Integer SiteTemplateId,String ResourceTypeId)
        {  
            try
            {   
                
                checkColumnSize(Url, 255);
                
                checkColumnSize(ResourceTypeId, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_template_url SET Url=?,SiteTemplateId=?,ResourceTypeId=? WHERE SiteTemplateUrlId=?;");                    
                preparedStatement.setString(1, Url);
                preparedStatement.setInt(2, SiteTemplateId);
                preparedStatement.setString(3, ResourceTypeId);
                preparedStatement.setInt(4, SiteTemplateUrlId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteTemplateUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteTemplateUrl()
        {
            try
            {
                updateQuery("DELETE FROM site_template_url;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteTemplateUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteTemplateUrlById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_template_url WHERE SiteTemplateUrlId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteTemplateUrlById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteTemplateUrlByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_template_url WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteTemplateUrlByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

