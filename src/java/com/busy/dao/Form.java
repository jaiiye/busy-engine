


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Form extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_FORMID = "FormId";
        public static final String PROP_FORMNAME = "FormName";
        public static final String PROP_FORMDESCRIPTION = "FormDescription";
        public static final String PROP_FORMSUBMISSIONEMAIL = "FormSubmissionEmail";
        public static final String PROP_FORMSUBMISSIONMETHOD = "FormSubmissionMethod";
        public static final String PROP_FORMACTION = "FormAction";
        public static final String PROP_FORMRESETPRESENT = "FormResetPresent";
        public static final String PROP_FORMFILEUPLOAD = "FormFileUpload";
        
        
        private Integer formId;
        private String formName;
        private String formDescription;
        private String formSubmissionEmail;
        private String formSubmissionMethod;
        private String formAction;
        private Integer formResetPresent;
        private Integer formFileUpload;
        
        
        public Form()
        {
            this.formId = 0; 
       this.formName = ""; 
       this.formDescription = ""; 
       this.formSubmissionEmail = ""; 
       this.formSubmissionMethod = ""; 
       this.formAction = ""; 
       this.formResetPresent = 0; 
       this.formFileUpload = 0; 
        }
        
        public Form(Integer FormId, String FormName, String FormDescription, String FormSubmissionEmail, String FormSubmissionMethod, String FormAction, Integer FormResetPresent, Integer FormFileUpload)
        {
            this.formId = FormId;
       this.formName = FormName;
       this.formDescription = FormDescription;
       this.formSubmissionEmail = FormSubmissionEmail;
       this.formSubmissionMethod = FormSubmissionMethod;
       this.formAction = FormAction;
       this.formResetPresent = FormResetPresent;
       this.formFileUpload = FormFileUpload;
        } 
        
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
        
            public String getFormName()
            {
                return this.formName;
            }
            
            public void setFormName(String FormName)
            {
                this.formName = FormName;
            }
        
            public String getFormDescription()
            {
                return this.formDescription;
            }
            
            public void setFormDescription(String FormDescription)
            {
                this.formDescription = FormDescription;
            }
        
            public String getFormSubmissionEmail()
            {
                return this.formSubmissionEmail;
            }
            
            public void setFormSubmissionEmail(String FormSubmissionEmail)
            {
                this.formSubmissionEmail = FormSubmissionEmail;
            }
        
            public String getFormSubmissionMethod()
            {
                return this.formSubmissionMethod;
            }
            
            public void setFormSubmissionMethod(String FormSubmissionMethod)
            {
                this.formSubmissionMethod = FormSubmissionMethod;
            }
        
            public String getFormAction()
            {
                return this.formAction;
            }
            
            public void setFormAction(String FormAction)
            {
                this.formAction = FormAction;
            }
        
            public Integer getFormResetPresent()
            {
                return this.formResetPresent;
            }
            
            public void setFormResetPresent(Integer FormResetPresent)
            {
                this.formResetPresent = FormResetPresent;
            }
        
            public Integer getFormFileUpload()
            {
                return this.formFileUpload;
            }
            
            public void setFormFileUpload(Integer FormFileUpload)
            {
                this.formFileUpload = FormFileUpload;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Form.PROP_FORMID) || column.equals(Form.PROP_FORMNAME) || column.equals(Form.PROP_FORMDESCRIPTION) || column.equals(Form.PROP_FORMSUBMISSIONEMAIL) || column.equals(Form.PROP_FORMSUBMISSIONMETHOD) || column.equals(Form.PROP_FORMACTION) || column.equals(Form.PROP_FORMRESETPRESENT) || column.equals(Form.PROP_FORMFILEUPLOAD) )
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
            if (column.equals(Form.PROP_FORMID) || column.equals(Form.PROP_FORMRESETPRESENT) || column.equals(Form.PROP_FORMFILEUPLOAD) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Form processForm(ResultSet rs) throws SQLException
        {        
            return new Form(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        }
        
        public static int addForm(String FormName, String FormDescription, String FormSubmissionEmail, String FormSubmissionMethod, String FormAction, Integer FormResetPresent, Integer FormFileUpload)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FormName, 100);
                checkColumnSize(FormDescription, 255);
                checkColumnSize(FormSubmissionEmail, 255);
                checkColumnSize(FormSubmissionMethod, 5);
                checkColumnSize(FormAction, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO form(FormName,FormDescription,FormSubmissionEmail,FormSubmissionMethod,FormAction,FormResetPresent,FormFileUpload) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, FormName);
                preparedStatement.setString(2, FormDescription);
                preparedStatement.setString(3, FormSubmissionEmail);
                preparedStatement.setString(4, FormSubmissionMethod);
                preparedStatement.setString(5, FormAction);
                preparedStatement.setInt(6, FormResetPresent);
                preparedStatement.setInt(7, FormFileUpload);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addForm error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllFormCount()
        {
            return getAllRecordsCountByTableName("form");        
        }
        
        public static ArrayList<Form> getAllForm()
        {
            ArrayList<Form> form = new ArrayList<Form>();
            try
            {
                getAllRecordsByTableName("form");
                while(rs.next())
                {
                    form.add(processForm(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllForm error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        }
                
        public static ArrayList<Form> getFormPaged(int limit, int offset)
        {
            ArrayList<Form> form = new ArrayList<Form>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("form", limit, offset);
                while (rs.next())
                {
                    form.add(processForm(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getFormPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        } 
        
        public static ArrayList<Form> getAllFormByColumn(String columnName, String columnValue)
        {
            ArrayList<Form> form = new ArrayList<Form>();
            try
            {
                getAllRecordsByColumn("form", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    form.add(processForm(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllFormByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        }
        
        public static Form getFormByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Form form = new Form();
            try
            {
                getRecordsByColumnWithLimitAndOffset("form", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   form = processForm(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getFormByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        }                
                
        public static void updateForm(Integer FormId,String FormName,String FormDescription,String FormSubmissionEmail,String FormSubmissionMethod,String FormAction,Integer FormResetPresent,Integer FormFileUpload)
        {  
            try
            {   
                
                checkColumnSize(FormName, 100);
                checkColumnSize(FormDescription, 255);
                checkColumnSize(FormSubmissionEmail, 255);
                checkColumnSize(FormSubmissionMethod, 5);
                checkColumnSize(FormAction, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form SET FormName=?,FormDescription=?,FormSubmissionEmail=?,FormSubmissionMethod=?,FormAction=?,FormResetPresent=?,FormFileUpload=? WHERE FormId=?;");                    
                preparedStatement.setString(1, FormName);
                preparedStatement.setString(2, FormDescription);
                preparedStatement.setString(3, FormSubmissionEmail);
                preparedStatement.setString(4, FormSubmissionMethod);
                preparedStatement.setString(5, FormAction);
                preparedStatement.setInt(6, FormResetPresent);
                preparedStatement.setInt(7, FormFileUpload);
                preparedStatement.setInt(8, FormId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateForm error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllForm()
        {
            try
            {
                updateQuery("DELETE FROM form;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllForm error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteFormById(String id)
        {
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteFormById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteFormByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM form WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteFormByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

