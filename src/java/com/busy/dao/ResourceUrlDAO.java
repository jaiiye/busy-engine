





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ResourceUrlDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ResourceUrl.PROP_RESOURCE_URL_ID) || column.equals(ResourceUrl.PROP_URL) || column.equals(ResourceUrl.PROP_TEMPLATE_ID) || column.equals(ResourceUrl.PROP_RESOURCE_TYPE_ID) )
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
            if (column.equals(ResourceUrl.PROP_RESOURCE_URL_ID) || column.equals(ResourceUrl.PROP_TEMPLATE_ID) || column.equals(ResourceUrl.PROP_RESOURCE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ResourceUrl processResourceUrl(ResultSet rs) throws SQLException
        {        
            return new ResourceUrl(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addResourceUrl(String Url, Integer TemplateId, Integer ResourceTypeId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Url, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO resource_url(Url,TemplateId,ResourceTypeId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Url);
                preparedStatement.setInt(2, TemplateId);
                preparedStatement.setInt(3, ResourceTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_url;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addResourceUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllResourceUrlCount()
        {
            return getAllRecordsCountByTableName("resource_url");        
        }
        
        public static ArrayList<ResourceUrl> getAllResourceUrl()
        {
            ArrayList<ResourceUrl> resource_url = new ArrayList<ResourceUrl>();
            try
            {
                getAllRecordsByTableName("resource_url");
                while(rs.next())
                {
                    resource_url.add(processResourceUrl(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllResourceUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_url;
        }
        
        public static ArrayList<ResourceUrl> getAllResourceUrlWithRelatedInfo()
        {
            ArrayList<ResourceUrl> resource_urlList = new ArrayList<ResourceUrl>();
            try
            {
                getAllRecordsByTableName("resource_url");
                while (rs.next())
                {
                    resource_urlList.add(processResourceUrl(rs));
                }

                
                    for(ResourceUrl resource_url : resource_urlList)
                    {
                        
                            getRecordById("Template", resource_url.getTemplateId().toString());
                            resource_url.setTemplate(TemplateDAO.processTemplate(rs));               
                        
                            getRecordById("ResourceType", resource_url.getResourceTypeId().toString());
                            resource_url.setResourceType(ResourceTypeDAO.processResourceType(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllResourceUrlWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_urlList;
        }
        
        
        public static ResourceUrl getRelatedInfo(ResourceUrl resource_url)
        {
           
                
                    try
                    { 
                        
                            getRecordById("Template", resource_url.getTemplateId().toString());
                            resource_url.setTemplate(TemplateDAO.processTemplate(rs));               
                        
                            getRecordById("ResourceType", resource_url.getResourceTypeId().toString());
                            resource_url.setResourceType(ResourceTypeDAO.processResourceType(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return resource_url;
        }
        
        public static ResourceUrl getAllRelatedObjects(ResourceUrl resource_url)
        {           
                         
            return resource_url;
        }
        
        
        
                
        public static ArrayList<ResourceUrl> getResourceUrlPaged(int limit, int offset)
        {
            ArrayList<ResourceUrl> resource_url = new ArrayList<ResourceUrl>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("resource_url", limit, offset);
                while (rs.next())
                {
                    resource_url.add(processResourceUrl(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getResourceUrlPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_url;
        } 
        
        public static ArrayList<ResourceUrl> getAllResourceUrlByColumn(String columnName, String columnValue)
        {
            ArrayList<ResourceUrl> resource_url = new ArrayList<ResourceUrl>();
            try
            {
                getAllRecordsByColumn("resource_url", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    resource_url.add(processResourceUrl(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllResourceUrlByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_url;
        }
        
        public static ResourceUrl getResourceUrlByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ResourceUrl resource_url = new ResourceUrl();
            try
            {
                getRecordsByColumnWithLimitAndOffset("resource_url", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   resource_url = processResourceUrl(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getResourceUrlByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_url;
        }                
                
        public static void updateResourceUrl(Integer ResourceUrlId,String Url,Integer TemplateId,Integer ResourceTypeId)
        {  
            try
            {   
                
                checkColumnSize(Url, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_url SET Url=?,TemplateId=?,ResourceTypeId=? WHERE ResourceUrlId=?;");                    
                preparedStatement.setString(1, Url);
                preparedStatement.setInt(2, TemplateId);
                preparedStatement.setInt(3, ResourceTypeId);
                preparedStatement.setInt(4, ResourceUrlId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateResourceUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllResourceUrl()
        {
            try
            {
                updateQuery("DELETE FROM resource_url;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllResourceUrl error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteResourceUrlById(String id)
        {
            try
            {
                updateQuery("DELETE FROM resource_url WHERE ResourceUrlId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteResourceUrlById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteResourceUrlByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM resource_url WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteResourceUrlByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

