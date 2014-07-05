


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Template extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_TEMPLATEID = "TemplateId";
        public static final String PROP_TEMPLATENAME = "TemplateName";
        public static final String PROP_TEMPLATEBODY = "TemplateBody";
        
        
        private Integer templateId;
        private String templateName;
        private String templateBody;
        
        
        public Template()
        {
            this.templateId = 0; 
       this.templateName = ""; 
       this.templateBody = ""; 
        }
        
        public Template(Integer TemplateId, String TemplateName, String TemplateBody)
        {
            this.templateId = TemplateId;
       this.templateName = TemplateName;
       this.templateBody = TemplateBody;
        } 
        
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
        
            public String getTemplateName()
            {
                return this.templateName;
            }
            
            public void setTemplateName(String TemplateName)
            {
                this.templateName = TemplateName;
            }
        
            public String getTemplateBody()
            {
                return this.templateBody;
            }
            
            public void setTemplateBody(String TemplateBody)
            {
                this.templateBody = TemplateBody;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Template.PROP_TEMPLATEID) || column.equals(Template.PROP_TEMPLATENAME) || column.equals(Template.PROP_TEMPLATEBODY) )
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
            if (column.equals(Template.PROP_TEMPLATEID) )
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
            return new Template(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addTemplate(String TemplateName, String TemplateBody)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TemplateName, 45);
                checkColumnSize(TemplateBody, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO template(TemplateName,TemplateBody) VALUES (?,?);");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, TemplateBody);
                
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
                
        public static void updateTemplate(Integer TemplateId,String TemplateName,String TemplateBody)
        {  
            try
            {   
                
                checkColumnSize(TemplateName, 45);
                checkColumnSize(TemplateBody, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE template SET TemplateName=?,TemplateBody=? WHERE TemplateId=?;");                    
                preparedStatement.setString(1, TemplateName);
                preparedStatement.setString(2, TemplateBody);
                preparedStatement.setInt(3, TemplateId);
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

