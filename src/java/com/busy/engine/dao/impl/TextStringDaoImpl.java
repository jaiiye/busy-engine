





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object TextString's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
        } 
    
        @Override
        public void add(TextString obj)
        {
            try
            {
                
                TextString.checkColumnSize(obj.getKey(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO text_string(Key) VALUES (?);");                    
                preparedStatement.setString(1, obj.getKey());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("TextString's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Key)
        {   
            int id = 0;
            try
            {
                
                TextString.checkColumnSize(Key, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO text_string(Key) VALUES (?);");                    
                preparedStatement.setString(1, Key);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from text_string;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTextString error: " + ex.getMessage());
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
                
                TextString.checkColumnSize(obj.getKey(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string SET Key=? WHERE TextStringId=?;");                    
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setInt(2, obj.getTextStringId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTextString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer TextStringId,String Key)
        {  
            try
            {   
                
                TextString.checkColumnSize(Key, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string SET Key=? WHERE TextStringId=?;");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setInt(2, TextStringId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTextString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("text_string");
        }
        
        
        @Override
        public TextString getRelatedInfo(TextString text_string)
        {
              
            
            return text_string;
        }
        
        
        @Override
        public TextString getRelatedObjects(TextString text_string)
        {
            text_string.setLocalizedStringList(new LocalizedStringDaoImpl().findByColumn("TextStringId", text_string.getTextStringId().toString(),null,null));
             
            return text_string;
        }
        
        
        
        @Override
        public void remove(TextString obj)
        {            
            try
            {
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + obj.getTextStringId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTextStringById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTextStringById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM text_string;");
            }
            catch (Exception ex)
            {
                System.out.println("TextString's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM text_string WHERE " + TextString.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("TextString's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public TextString getRelatedLocalizedStringList(TextString text_string)
        {           
            text_string.setLocalizedStringList(new LocalizedStringDaoImpl().findByColumn("TextStringId", text_string.getTextStringId().toString(),null,null));
            return text_string;
        }        
        
                             
    }

