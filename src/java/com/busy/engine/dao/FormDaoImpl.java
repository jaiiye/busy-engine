





































    package com.busy.engine.dao;

import com.busy.engine.entity.Form;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("Form's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return form;
        } 
    
        @Override
        public int add(Form obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from form;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Form's add method error: " + ex.getMessage());
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
                System.out.println("Form's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("form");
        }
        
        
        @Override
        public void getRelatedInfo(Form form)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Form form)
        {
            form.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
form.setPageList(new PageDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
form.setSliderList(new SliderDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Form obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + obj.getFormId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Form's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM form WHERE FormId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM form;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Form's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM form WHERE " + Form.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Form's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedFormFieldList(Form form)
        {           
            form.setFormFieldList(new FormFieldDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
        }        
                    
        public void getRelatedPageList(Form form)
        {           
            form.setPageList(new PageDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
        }        
                    
        public void getRelatedSliderList(Form form)
        {           
            form.setSliderList(new SliderDaoImpl().findByColumn("FormId", form.getFormId().toString(),null,null));
        }        
        
                             
    }

