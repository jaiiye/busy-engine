





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TemplateTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TemplateType.PROP_TEMPLATE_TYPE_ID) || column.equals(TemplateType.PROP_TYPE_NAME) || column.equals(TemplateType.PROP_TYPE_VALUE) )
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
            if (column.equals(TemplateType.PROP_TEMPLATE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TemplateType processTemplateType(ResultSet rs) throws SQLException
        {        
            return new TemplateType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addTemplateType(String TypeName, String TypeValue)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeValue, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO template_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllTemplateTypeCount()
        {
            return getAllRecordsCountByTableName("template_type");        
        }
        
        public static ArrayList<TemplateType> getAllTemplateType()
        {
            ArrayList<TemplateType> template_type = new ArrayList<TemplateType>();
            try
            {
                getAllRecordsByTableName("template_type");
                while(rs.next())
                {
                    template_type.add(processTemplateType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
        }
        
        public static ArrayList<TemplateType> getAllTemplateTypeWithRelatedInfo()
        {
            ArrayList<TemplateType> template_typeList = new ArrayList<TemplateType>();
            try
            {
                getAllRecordsByTableName("template_type");
                while (rs.next())
                {
                    template_typeList.add(processTemplateType(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTemplateTypeWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_typeList;
        }
        
        
        public static TemplateType getRelatedInfo(TemplateType template_type)
        {
           
                  
            
            return template_type;
        }
        
        public static TemplateType getAllRelatedObjects(TemplateType template_type)
        {           
            template_type.setTemplateList(TemplateDAO.getAllTemplateByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString()));
             
            return template_type;
        }
        
        
                    
        public static TemplateType getRelatedTemplateList(TemplateType template_type)
        {           
            template_type.setTemplateList(TemplateDAO.getAllTemplateByColumn("TemplateTypeId", template_type.getTemplateTypeId().toString()));
            return template_type;
        }        
        
                
        public static ArrayList<TemplateType> getTemplateTypePaged(int limit, int offset)
        {
            ArrayList<TemplateType> template_type = new ArrayList<TemplateType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("template_type", limit, offset);
                while (rs.next())
                {
                    template_type.add(processTemplateType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTemplateTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
        } 
        
        public static ArrayList<TemplateType> getAllTemplateTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<TemplateType> template_type = new ArrayList<TemplateType>();
            try
            {
                getAllRecordsByColumn("template_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    template_type.add(processTemplateType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTemplateTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
        }
        
        public static TemplateType getTemplateTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            TemplateType template_type = new TemplateType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("template_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   template_type = processTemplateType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTemplateTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template_type;
        }                
                
        public static void updateTemplateType(Integer TemplateTypeId,String TypeName,String TypeValue)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(TypeValue, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE template_type SET TypeName=?,TypeValue=? WHERE TemplateTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                preparedStatement.setInt(3, TemplateTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllTemplateType()
        {
            try
            {
                updateQuery("DELETE FROM template_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllTemplateType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTemplateTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM template_type WHERE TemplateTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteTemplateTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM template_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

