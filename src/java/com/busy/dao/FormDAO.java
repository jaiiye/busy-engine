











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Form;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FormDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Form.PROP_FORM_ID) || column.equals(Form.PROP_FORM_NAME) || column.equals(Form.PROP_DESCRIPTION) || column.equals(Form.PROP_SUBMISSION_EMAIL) || column.equals(Form.PROP_SUBMISSION_METHOD) || column.equals(Form.PROP_ACTION) || column.equals(Form.PROP_RESETTABLE) || column.equals(Form.PROP_FILE_UPLOAD) )
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
            if (column.equals(Form.PROP_FORM_ID) || column.equals(Form.PROP_RESETTABLE) || column.equals(Form.PROP_FILE_UPLOAD) )
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
            return new Form(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
        }
        
        public static int addForm(String FormName, String Description, String SubmissionEmail, String SubmissionMethod, String Action, Boolean Resettable, Boolean FileUpload)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(FormName, 100);
                checkColumnSize(Description, 255);
                checkColumnSize(SubmissionEmail, 255);
                checkColumnSize(SubmissionMethod, 5);
                checkColumnSize(Action, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO form(FormName,Description,SubmissionEmail,SubmissionMethod,Action,Resettable,FileUpload) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, FormName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, SubmissionEmail);
                preparedStatement.setString(4, SubmissionMethod);
                preparedStatement.setString(5, Action);
                preparedStatement.setBoolean(6, Resettable);
                preparedStatement.setBoolean(7, FileUpload);
                
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
                
        public static void updateForm(Integer FormId,String FormName,String Description,String SubmissionEmail,String SubmissionMethod,String Action,Boolean Resettable,Boolean FileUpload)
        {  
            try
            {   
                
                checkColumnSize(FormName, 100);
                checkColumnSize(Description, 255);
                checkColumnSize(SubmissionEmail, 255);
                checkColumnSize(SubmissionMethod, 5);
                checkColumnSize(Action, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form SET FormName=?,Description=?,SubmissionEmail=?,SubmissionMethod=?,Action=?,Resettable=?,FileUpload=? WHERE FormId=?;");                    
                preparedStatement.setString(1, FormName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, SubmissionEmail);
                preparedStatement.setString(4, SubmissionMethod);
                preparedStatement.setString(5, Action);
                preparedStatement.setBoolean(6, Resettable);
                preparedStatement.setBoolean(7, FileUpload);
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

