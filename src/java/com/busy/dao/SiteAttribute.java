


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteAttribute extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITEATTRIBUTEID = "SiteAttributeId";
        public static final String PROP_ATTRIBUTEKEY = "AttributeKey";
        public static final String PROP_ATTRIBUTEVALUE = "AttributeValue";
        public static final String PROP_ATTRIBUTETYPE = "AttributeType";
        
        
        private Integer siteAttributeId;
        private String attributeKey;
        private String attributeValue;
        private String attributeType;
        
        
        public SiteAttribute()
        {
            this.siteAttributeId = 0; 
       this.attributeKey = ""; 
       this.attributeValue = ""; 
       this.attributeType = ""; 
        }
        
        public SiteAttribute(Integer SiteAttributeId, String AttributeKey, String AttributeValue, String AttributeType)
        {
            this.siteAttributeId = SiteAttributeId;
       this.attributeKey = AttributeKey;
       this.attributeValue = AttributeValue;
       this.attributeType = AttributeType;
        } 
        
        
            public Integer getSiteAttributeId()
            {
                return this.siteAttributeId;
            }
            
            public void setSiteAttributeId(Integer SiteAttributeId)
            {
                this.siteAttributeId = SiteAttributeId;
            }
        
            public String getAttributeKey()
            {
                return this.attributeKey;
            }
            
            public void setAttributeKey(String AttributeKey)
            {
                this.attributeKey = AttributeKey;
            }
        
            public String getAttributeValue()
            {
                return this.attributeValue;
            }
            
            public void setAttributeValue(String AttributeValue)
            {
                this.attributeValue = AttributeValue;
            }
        
            public String getAttributeType()
            {
                return this.attributeType;
            }
            
            public void setAttributeType(String AttributeType)
            {
                this.attributeType = AttributeType;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteAttribute.PROP_SITEATTRIBUTEID) || column.equals(SiteAttribute.PROP_ATTRIBUTEKEY) || column.equals(SiteAttribute.PROP_ATTRIBUTEVALUE) || column.equals(SiteAttribute.PROP_ATTRIBUTETYPE) )
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
            if (column.equals(SiteAttribute.PROP_SITEATTRIBUTEID) )
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
            return new SiteAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addSiteAttribute(String AttributeKey, String AttributeValue, String AttributeType)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(AttributeKey, 100);
                checkColumnSize(AttributeValue, 255);
                checkColumnSize(AttributeType, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO site_attribute(AttributeKey,AttributeValue,AttributeType) VALUES (?,?,?);");                    
                preparedStatement.setString(1, AttributeKey);
                preparedStatement.setString(2, AttributeValue);
                preparedStatement.setString(3, AttributeType);
                
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
                
        public static void updateSiteAttribute(Integer SiteAttributeId,String AttributeKey,String AttributeValue,String AttributeType)
        {  
            try
            {   
                
                checkColumnSize(AttributeKey, 100);
                checkColumnSize(AttributeValue, 255);
                checkColumnSize(AttributeType, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_attribute SET AttributeKey=?,AttributeValue=?,AttributeType=? WHERE SiteAttributeId=?;");                    
                preparedStatement.setString(1, AttributeKey);
                preparedStatement.setString(2, AttributeValue);
                preparedStatement.setString(3, AttributeType);
                preparedStatement.setInt(4, SiteAttributeId);
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

