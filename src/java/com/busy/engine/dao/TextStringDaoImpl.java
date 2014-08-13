





































    package com.busy.engine.dao;

import com.busy.engine.entity.TextString;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TextStringDaoImpl extends BasicConnection implements Serializable, TextStringDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public TextString find(Integer id)
        {
            return findByColumn("TextStringId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<TextString> findAll(Integer limit, Integer offset)
        {
            ArrayList<TextString> text_string = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("text_string");
                while(rs.next())
                {
                    text_string.add(TextString.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TextString object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
         
        }
        
        @Override
        public ArrayList<TextString> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TextString> text_stringList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("text_string", limit, offset);
                while (rs.next())
                {
                    text_stringList.add(TextString.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object TextString method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_stringList;
        }
        
        @Override
        public ArrayList<TextString> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TextString> text_string = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("text_string", TextString.checkColumnName(columnName), columnValue, TextString.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   text_string.add(TextString.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TextString's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
        } 
    
        @Override
        public int add(TextString obj)
        {
            int id = 0;
            try
            {
                
                TextString.checkColumnSize(obj.getKey(), 200);
                                            
                openConnection();
                prepareStatement("INSERT INTO text_string(Key) VALUES (?);");                    
                preparedStatement.setString(1, obj.getKey());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from text_string;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("TextString's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public TextString update(TextString obj)
        {
           try
            {   
                
                TextString.checkColumnSize(obj.getKey(), 200);
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string SET Key=? WHERE TextStringId=?;");                    
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setInt(2, obj.getTextStringId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("TextString's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("text_string");
        }
        
        
        @Override
        public void getRelatedInfo(TextString text_string)
        {
              
        }
        
        @Override
        public void getRelatedObjects(TextString text_string)
        {
            text_string.setLocalizedStringList(new LocalizedStringDaoImpl().findByColumn("TextStringId", text_string.getTextStringId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(TextString obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + obj.getTextStringId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TextString's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + id + ";");           
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
                updateQuery("DELETE FROM text_string;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TextString's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM text_string WHERE " + TextString.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TextString's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedLocalizedStringList(TextString text_string)
        {           
            text_string.setLocalizedStringList(new LocalizedStringDaoImpl().findByColumn("TextStringId", text_string.getTextStringId().toString(),null,null));
        }        
        
                             
    }

