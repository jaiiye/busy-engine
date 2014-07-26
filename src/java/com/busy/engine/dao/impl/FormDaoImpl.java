





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class FormDaoImpl extends BasicConnection implements Serializable, FormDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Form find(Integer id)
        {
            return findByColumn("FormId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Form> findAll(Integer limit, Integer offset)
        {
            ArrayList<Form> form = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("form");
                while(rs.next())
                {
                    form.add(Form.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Form object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
         
        }
        
        @Override
        public ArrayList<Form> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Form> formList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("form", limit, offset);
                while (rs.next())
                {
                    formList.add(Form.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Form method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return formList;
        }
        
        @Override
        public ArrayList<Form> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Form> form = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("form", Form.checkColumnName(columnName), columnValue, Form.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   form.add(Form.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Form's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        } 
    
        @Override
        public void add(Form obj)
        {
            try
            {
                
                Form.checkColumnSize(obj.getFormName(), 100);
                Form.checkColumnSize(obj.getDescription(), 255);
                Form.checkColumnSize(obj.getSubmissionEmail(), 255);
                Form.checkColumnSize(obj.getSubmissionMethod(), 5);
                Form.checkColumnSize(obj.getAction(), 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO form(FormName,Description,SubmissionEmail,SubmissionMethod,Action,Resettable,FileUpload) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFormName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getSubmissionEmail());
                preparedStatement.setString(4, obj.getSubmissionMethod());
                preparedStatement.setString(5, obj.getAction());
                preparedStatement.setInt(6, obj.getResettable());
                preparedStatement.setInt(7, obj.getFileUpload());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Form's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String FormName, String Description, String SubmissionEmail, String SubmissionMethod, String Action, Integer Resettable, Integer FileUpload)
        {   
            int id = 0;
            try
            {
                
                Form.checkColumnSize(FormName, 100);
                Form.checkColumnSize(Description, 255);
                Form.checkColumnSize(SubmissionEmail, 255);
                Form.checkColumnSize(SubmissionMethod, 5);
                Form.checkColumnSize(Action, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO form(FormName,Description,SubmissionEmail,SubmissionMethod,Action,Resettable,FileUpload) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, FormName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, SubmissionEmail);
                preparedStatement.setString(4, SubmissionMethod);
                preparedStatement.setString(5, Action);
                preparedStatement.setInt(6, Resettable);
                preparedStatement.setInt(7, FileUpload);
                
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
        
        
        @Override
        public Form update(Form obj)
        {
           try
            {   
                
                Form.checkColumnSize(obj.getFormName(), 100);
                Form.checkColumnSize(obj.getDescription(), 255);
                Form.checkColumnSize(obj.getSubmissionEmail(), 255);
                Form.checkColumnSize(obj.getSubmissionMethod(), 5);
                Form.checkColumnSize(obj.getAction(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form SET FormName=?,Description=?,SubmissionEmail=?,SubmissionMethod=?,Action=?,Resettable=?,FileUpload=? WHERE FormId=?;");                    
                preparedStatement.setString(1, obj.getFormName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getSubmissionEmail());
                preparedStatement.setString(4, obj.getSubmissionMethod());
                preparedStatement.setString(5, obj.getAction());
                preparedStatement.setInt(6, obj.getResettable());
                preparedStatement.setInt(7, obj.getFileUpload());
                preparedStatement.setInt(8, obj.getFormId());
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
            return obj;
        }
        
        public static void update(Integer FormId,String FormName,String Description,String SubmissionEmail,String SubmissionMethod,String Action,Integer Resettable,Integer FileUpload)
        {  
            try
            {   
                
                Form.checkColumnSize(FormName, 100);
                Form.checkColumnSize(Description, 255);
                Form.checkColumnSize(SubmissionEmail, 255);
                Form.checkColumnSize(SubmissionMethod, 5);
                Form.checkColumnSize(Action, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE form SET FormName=?,Description=?,SubmissionEmail=?,SubmissionMethod=?,Action=?,Resettable=?,FileUpload=? WHERE FormId=?;");                    
                preparedStatement.setString(1, FormName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, SubmissionEmail);
                preparedStatement.setString(4, SubmissionMethod);
                preparedStatement.setString(5, Action);
                preparedStatement.setInt(6, Resettable);
                preparedStatement.setInt(7, FileUpload);
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("form");
        }
        
        
        @Override
        public Form getRelatedInfo(Form form)
        {
              
            
            return form;
        }
        
        
        @Override
        public Form getRelatedObjects(Form form)
        {
            form.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
form.setPageList(new PageDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
form.setSliderList(new SliderDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
             
            return form;
        }
        
        
        
        @Override
        public void remove(Form obj)
        {            
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + obj.getFormId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM form;");
            }
            catch (Exception ex)
            {
                System.out.println("Form's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM form WHERE " + Form.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Form's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Form getRelatedFormFieldList(Form form)
        {           
            form.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
            return form;
        }        
                    
        public Form getRelatedPageList(Form form)
        {           
            form.setPageList(new PageDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
            return form;
        }        
                    
        public Form getRelatedSliderList(Form form)
        {           
            form.setSliderList(new SliderDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
            return form;
        }        
        
                             
    }

