


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteAttributeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteAttribute.PROP_SITE_ATTRIBUTE_ID) || column.equals(SiteAttribute.PROP_KEY) || column.equals(SiteAttribute.PROP_VALUE) || column.equals(SiteAttribute.PROP_TYPE) || column.equals(SiteAttribute.PROP_SITE_ID) )
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
            if (column.equals(SiteAttribute.PROP_SITE_ATTRIBUTE_ID) || column.equals(SiteAttribute.PROP_SITE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteAttribute processSiteAttribute(ResultSet rs) throws SQLException
        {        
            return new SiteAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        }
        
        public static int addSiteAttribute(String Key, String Value, String Type, Integer SiteId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Key, 100);
                checkColumnSize(Value, 255);
                checkColumnSize(Type, 45);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO site_attribute(Key,Value,Type,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Type);
                preparedStatement.setInt(4, SiteId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_attribute;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteAttributeCount()
        {
            return getAllRecordsCountByTableName("site_attribute");        
        }
        
        public static ArrayList<SiteAttribute> getAllSiteAttribute()
        {
            ArrayList<SiteAttribute> site_attribute = new ArrayList<SiteAttribute>();
            try
            {
                getAllRecordsByTableName("site_attribute");
                while(rs.next())
                {
                    site_attribute.add(processSiteAttribute(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
        }
                
        public static ArrayList<SiteAttribute> getSiteAttributePaged(int limit, int offset)
        {
            ArrayList<SiteAttribute> site_attribute = new ArrayList<SiteAttribute>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_attribute", limit, offset);
                while (rs.next())
                {
                    site_attribute.add(processSiteAttribute(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteAttributePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
        } 
        
        public static ArrayList<SiteAttribute> getAllSiteAttributeByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteAttribute> site_attribute = new ArrayList<SiteAttribute>();
            try
            {
                getAllRecordsByColumn("site_attribute", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_attribute.add(processSiteAttribute(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteAttributeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
        }
        
        public static SiteAttribute getSiteAttributeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteAttribute site_attribute = new SiteAttribute();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_attribute", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_attribute = processSiteAttribute(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteAttributeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_attribute;
        }                
                
        public static void updateSiteAttribute(Integer SiteAttributeId,String Key,String Value,String Type,Integer SiteId)
        {  
            try
            {   
                
                checkColumnSize(Key, 100);
                checkColumnSize(Value, 255);
                checkColumnSize(Type, 45);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_attribute SET Key=?,Value=?,Type=?,SiteId=? WHERE SiteAttributeId=?;");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Type);
                preparedStatement.setInt(4, SiteId);
                preparedStatement.setInt(5, SiteAttributeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteAttribute()
        {
            try
            {
                updateQuery("DELETE FROM site_attribute;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteAttributeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteAttributeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteAttributeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_attribute WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteAttributeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

