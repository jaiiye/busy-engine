


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TemplateDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Template.PROP_TEMPLATE_ID) || column.equals(Template.PROP_TEMPLATE_NAME) || column.equals(Template.PROP_MARKUP) || column.equals(Template.PROP_STATUS) || column.equals(Template.PROP_TEMPLATE_TYPE_ID) )
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
            if (column.equals(Template.PROP_TEMPLATE_ID) || column.equals(Template.PROP_STATUS) || column.equals(Template.PROP_TEMPLATE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Template processTemplate(ResultSet rs) throws SQLException
        {        
            return new Template(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addTemplate(String TemplateName, String Markup, Integer Status, Integer TemplateTypeId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TemplateName, 100);
                checkColumnSize(Markup, 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO template(TemplateName,Markup,Status,TemplateTypeId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, Markup);
                preparedStatement.setInt(3, Status);
                preparedStatement.setInt(4, TemplateTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllTemplateCount()
        {
            return getAllRecordsCountByTableName("template");        
        }
        
        public static ArrayList<Template> getAllTemplate()
        {
            ArrayList<Template> template = new ArrayList<Template>();
            try
            {
                getAllRecordsByTableName("template");
                while(rs.next())
                {
                    template.add(processTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        }
                
        public static ArrayList<Template> getTemplatePaged(int limit, int offset)
        {
            ArrayList<Template> template = new ArrayList<Template>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("template", limit, offset);
                while (rs.next())
                {
                    template.add(processTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTemplatePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        } 
        
        public static ArrayList<Template> getAllTemplateByColumn(String columnName, String columnValue)
        {
            ArrayList<Template> template = new ArrayList<Template>();
            try
            {
                getAllRecordsByColumn("template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    template.add(processTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        }
        
        public static Template getTemplateByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Template template = new Template();
            try
            {
                getRecordsByColumnWithLimitAndOffset("template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   template = processTemplate(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTemplateByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return template;
        }                
                
        public static void updateTemplate(Integer TemplateId,String TemplateName,String Markup,Integer Status,Integer TemplateTypeId)
        {  
            try
            {   
                
                checkColumnSize(TemplateName, 100);
                checkColumnSize(Markup, 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE template SET TemplateName=?,Markup=?,Status=?,TemplateTypeId=? WHERE TemplateId=?;");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, Markup);
                preparedStatement.setInt(3, Status);
                preparedStatement.setInt(4, TemplateTypeId);
                preparedStatement.setInt(5, TemplateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllTemplate()
        {
            try
            {
                updateQuery("DELETE FROM template;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTemplateById(String id)
        {
            try
            {
                updateQuery("DELETE FROM template WHERE TemplateId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteTemplateByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM template WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

