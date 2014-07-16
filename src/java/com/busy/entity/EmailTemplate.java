


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class EmailTemplate extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_EMAILTEMPLATEID = "EmailTemplateId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_BODY = "Body";
        
        
        private Integer emailTemplateId;
        private String name;
        private String body;
        
        
        public EmailTemplate()
        {
            this.emailTemplateId = 0; 
       this.name = ""; 
       this.body = ""; 
        }
        
        public EmailTemplate(Integer EmailTemplateId, String Name, String Body)
        {
            this.emailTemplateId = EmailTemplateId;
       this.name = Name;
       this.body = Body;
        } 
        
        
            public Integer getEmailTemplateId()
            {
                return this.emailTemplateId;
            }
            
            public void setEmailTemplateId(Integer EmailTemplateId)
            {
                this.emailTemplateId = EmailTemplateId;
            }
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
        
            public String getBody()
            {
                return this.body;
            }
            
            public void setBody(String Body)
            {
                this.body = Body;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(EmailTemplate.PROP_EMAILTEMPLATEID) || column.equals(EmailTemplate.PROP_NAME) || column.equals(EmailTemplate.PROP_BODY) )
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
            if (column.equals(EmailTemplate.PROP_EMAILTEMPLATEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static EmailTemplate processEmailTemplate(ResultSet rs) throws SQLException
        {        
            return new EmailTemplate(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addEmailTemplate(String Name, String Body)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 45);
                checkColumnSize(Body, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO email_template(Name,Body) VALUES (?,?);");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Body);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from email_template;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addEmailTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllEmailTemplateCount()
        {
            return getAllRecordsCountByTableName("email_template");        
        }
        
        public static ArrayList<EmailTemplate> getAllEmailTemplate()
        {
            ArrayList<EmailTemplate> email_template = new ArrayList<EmailTemplate>();
            try
            {
                getAllRecordsByTableName("email_template");
                while(rs.next())
                {
                    email_template.add(processEmailTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllEmailTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return email_template;
        }
                
        public static ArrayList<EmailTemplate> getEmailTemplatePaged(int limit, int offset)
        {
            ArrayList<EmailTemplate> email_template = new ArrayList<EmailTemplate>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("email_template", limit, offset);
                while (rs.next())
                {
                    email_template.add(processEmailTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getEmailTemplatePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return email_template;
        } 
        
        public static ArrayList<EmailTemplate> getAllEmailTemplateByColumn(String columnName, String columnValue)
        {
            ArrayList<EmailTemplate> email_template = new ArrayList<EmailTemplate>();
            try
            {
                getAllRecordsByColumn("email_template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    email_template.add(processEmailTemplate(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllEmailTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return email_template;
        }
        
        public static EmailTemplate getEmailTemplateByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            EmailTemplate email_template = new EmailTemplate();
            try
            {
                getRecordsByColumnWithLimitAndOffset("email_template", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   email_template = processEmailTemplate(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getEmailTemplateByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return email_template;
        }                
                
        public static void updateEmailTemplate(Integer EmailTemplateId,String Name,String Body)
        {  
            try
            {   
                
                checkColumnSize(Name, 45);
                checkColumnSize(Body, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE email_template SET Name=?,Body=? WHERE EmailTemplateId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Body);
                preparedStatement.setInt(3, EmailTemplateId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateEmailTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllEmailTemplate()
        {
            try
            {
                updateQuery("DELETE FROM email_template;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllEmailTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteEmailTemplateById(String id)
        {
            try
            {
                updateQuery("DELETE FROM email_template WHERE EmailTemplateId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteEmailTemplateById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteEmailTemplateByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM email_template WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteEmailTemplateByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

