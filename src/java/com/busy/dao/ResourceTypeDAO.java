





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ResourceTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ResourceType.PROP_RESOURCE_TYPE_ID) || column.equals(ResourceType.PROP_TYPE_NAME) || column.equals(ResourceType.PROP_TYPE_VALUE) )
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
            if (column.equals(ResourceType.PROP_RESOURCE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ResourceType processResourceType(ResultSet rs) throws SQLException
        {        
            return new ResourceType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addResourceType(String TypeName, String TypeValue)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeValue, 150);
                                            
                openConnection();
                prepareStatement("INSERT INTO resource_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllResourceTypeCount()
        {
            return getAllRecordsCountByTableName("resource_type");        
        }
        
        public static ArrayList<ResourceType> getAllResourceType()
        {
            ArrayList<ResourceType> resource_type = new ArrayList<ResourceType>();
            try
            {
                getAllRecordsByTableName("resource_type");
                while(rs.next())
                {
                    resource_type.add(processResourceType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
        }
        
        public static ArrayList<ResourceType> getAllResourceTypeWithRelatedInfo()
        {
            ArrayList<ResourceType> resource_typeList = new ArrayList<ResourceType>();
            try
            {
                getAllRecordsByTableName("resource_type");
                while (rs.next())
                {
                    resource_typeList.add(processResourceType(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllResourceTypeWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_typeList;
        }
        
        
        public static ResourceType getRelatedInfo(ResourceType resource_type)
        {
           
                  
            
            return resource_type;
        }
        
        public static ResourceType getAllRelatedObjects(ResourceType resource_type)
        {           
            resource_type.setResourceUrlList(ResourceUrlDAO.getAllResourceUrlByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString()));
             
            return resource_type;
        }
        
        
                    
        public static ResourceType getRelatedResourceUrlList(ResourceType resource_type)
        {           
            resource_type.setResourceUrlList(ResourceUrlDAO.getAllResourceUrlByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString()));
            return resource_type;
        }        
        
                
        public static ArrayList<ResourceType> getResourceTypePaged(int limit, int offset)
        {
            ArrayList<ResourceType> resource_type = new ArrayList<ResourceType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("resource_type", limit, offset);
                while (rs.next())
                {
                    resource_type.add(processResourceType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getResourceTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
        } 
        
        public static ArrayList<ResourceType> getAllResourceTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<ResourceType> resource_type = new ArrayList<ResourceType>();
            try
            {
                getAllRecordsByColumn("resource_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    resource_type.add(processResourceType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllResourceTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
        }
        
        public static ResourceType getResourceTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ResourceType resource_type = new ResourceType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("resource_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   resource_type = processResourceType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getResourceTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
        }                
                
        public static void updateResourceType(Integer ResourceTypeId,String TypeName,String TypeValue)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeValue, 150);
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_type SET TypeName=?,TypeValue=? WHERE ResourceTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                preparedStatement.setInt(3, ResourceTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllResourceType()
        {
            try
            {
                updateQuery("DELETE FROM resource_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteResourceTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteResourceTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteResourceTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM resource_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteResourceTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

