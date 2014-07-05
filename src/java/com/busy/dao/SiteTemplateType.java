


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class SiteTemplateType extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SITETEMPLATETYPEID = "SiteTemplateTypeId";
        public static final String PROP_TYPENAME = "TypeName";
        public static final String PROP_TYPEVALUE = "TypeValue";
        
        
        private Integer siteTemplateTypeId;
        private String typeName;
        private String typeValue;
        
        
        public SiteTemplateType()
        {
            this.siteTemplateTypeId = 0; 
       this.typeName = ""; 
       this.typeValue = ""; 
        }
        
        public SiteTemplateType(Integer SiteTemplateTypeId, String TypeName, String TypeValue)
        {
            this.siteTemplateTypeId = SiteTemplateTypeId;
       this.typeName = TypeName;
       this.typeValue = TypeValue;
        } 
        
        
            public Integer getSiteTemplateTypeId()
            {
                return this.siteTemplateTypeId;
            }
            
            public void setSiteTemplateTypeId(Integer SiteTemplateTypeId)
            {
                this.siteTemplateTypeId = SiteTemplateTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
        
            public String getTypeValue()
            {
                return this.typeValue;
            }
            
            public void setTypeValue(String TypeValue)
            {
                this.typeValue = TypeValue;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteTemplateType.PROP_SITETEMPLATETYPEID) || column.equals(SiteTemplateType.PROP_TYPENAME) || column.equals(SiteTemplateType.PROP_TYPEVALUE) )
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
            if (column.equals(SiteTemplateType.PROP_SITETEMPLATETYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteTemplateType processSiteTemplateType(ResultSet rs) throws SQLException
        {        
            return new SiteTemplateType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addSiteTemplateType(String TypeName, String TypeValue)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeValue, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO site_template_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_template_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addSiteTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllSiteTemplateTypeCount()
        {
            return getAllRecordsCountByTableName("site_template_type");        
        }
        
        public static ArrayList<SiteTemplateType> getAllSiteTemplateType()
        {
            ArrayList<SiteTemplateType> site_template_type = new ArrayList<SiteTemplateType>();
            try
            {
                getAllRecordsByTableName("site_template_type");
                while(rs.next())
                {
                    site_template_type.add(processSiteTemplateType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_type;
        }
                
        public static ArrayList<SiteTemplateType> getSiteTemplateTypePaged(int limit, int offset)
        {
            ArrayList<SiteTemplateType> site_template_type = new ArrayList<SiteTemplateType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("site_template_type", limit, offset);
                while (rs.next())
                {
                    site_template_type.add(processSiteTemplateType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteTemplateTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_type;
        } 
        
        public static ArrayList<SiteTemplateType> getAllSiteTemplateTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<SiteTemplateType> site_template_type = new ArrayList<SiteTemplateType>();
            try
            {
                getAllRecordsByColumn("site_template_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    site_template_type.add(processSiteTemplateType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllSiteTemplateTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_type;
        }
        
        public static SiteTemplateType getSiteTemplateTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            SiteTemplateType site_template_type = new SiteTemplateType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("site_template_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   site_template_type = processSiteTemplateType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getSiteTemplateTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site_template_type;
        }                
                
        public static void updateSiteTemplateType(Integer SiteTemplateTypeId,String TypeName,String TypeValue)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeValue, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_template_type SET TypeName=?,TypeValue=? WHERE SiteTemplateTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                preparedStatement.setInt(3, SiteTemplateTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateSiteTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllSiteTemplateType()
        {
            try
            {
                updateQuery("DELETE FROM site_template_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllSiteTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteSiteTemplateTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM site_template_type WHERE SiteTemplateTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteTemplateTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteSiteTemplateTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM site_template_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteSiteTemplateTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

