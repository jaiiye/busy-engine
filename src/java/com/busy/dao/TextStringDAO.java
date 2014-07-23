





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TextStringDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TextString.PROP_TEXT_STRING_ID) || column.equals(TextString.PROP_KEY) )
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
            if (column.equals(TextString.PROP_TEXT_STRING_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TextString processTextString(ResultSet rs) throws SQLException
        {        
            return new TextString(rs.getInt(1), rs.getString(2));
        }
        
        public static int addTextString(String Key)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Key, 100);
                                            
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
        
        public static int getAllTextStringCount()
        {
            return getAllRecordsCountByTableName("text_string");        
        }
        
        public static ArrayList<TextString> getAllTextString()
        {
            ArrayList<TextString> text_string = new ArrayList<TextString>();
            try
            {
                getAllRecordsByTableName("text_string");
                while(rs.next())
                {
                    text_string.add(processTextString(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTextString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
        }
        
        public static ArrayList<TextString> getAllTextStringWithRelatedInfo()
        {
            ArrayList<TextString> text_stringList = new ArrayList<TextString>();
            try
            {
                getAllRecordsByTableName("text_string");
                while (rs.next())
                {
                    text_stringList.add(processTextString(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTextStringWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_stringList;
        }
        
        
        public static TextString getRelatedInfo(TextString text_string)
        {
           
                  
            
            return text_string;
        }
        
        public static TextString getAllRelatedObjects(TextString text_string)
        {           
            text_string.setLocalizedStringList(LocalizedStringDAO.getAllLocalizedStringByColumn("TextStringId", text_string.getTextStringId().toString()));
text_string.setTextStringLocalList(TextStringLocalDAO.getAllTextStringLocalByColumn("TextStringId", text_string.getTextStringId().toString()));
             
            return text_string;
        }
        
        
                    
        public static TextString getRelatedLocalizedStringList(TextString text_string)
        {           
            text_string.setLocalizedStringList(LocalizedStringDAO.getAllLocalizedStringByColumn("TextStringId", text_string.getTextStringId().toString()));
            return text_string;
        }        
                    
        public static TextString getRelatedTextStringLocalList(TextString text_string)
        {           
            text_string.setTextStringLocalList(TextStringLocalDAO.getAllTextStringLocalByColumn("TextStringId", text_string.getTextStringId().toString()));
            return text_string;
        }        
        
                
        public static ArrayList<TextString> getTextStringPaged(int limit, int offset)
        {
            ArrayList<TextString> text_string = new ArrayList<TextString>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("text_string", limit, offset);
                while (rs.next())
                {
                    text_string.add(processTextString(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTextStringPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
        } 
        
        public static ArrayList<TextString> getAllTextStringByColumn(String columnName, String columnValue)
        {
            ArrayList<TextString> text_string = new ArrayList<TextString>();
            try
            {
                getAllRecordsByColumn("text_string", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    text_string.add(processTextString(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTextStringByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
        }
        
        public static TextString getTextStringByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            TextString text_string = new TextString();
            try
            {
                getRecordsByColumnWithLimitAndOffset("text_string", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   text_string = processTextString(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTextStringByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string;
        }                
                
        public static void updateTextString(Integer TextStringId,String Key)
        {  
            try
            {   
                
                checkColumnSize(Key, 100);
                                  
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
        
        public static void deleteAllTextString()
        {
            try
            {
                updateQuery("DELETE FROM text_string;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllTextString error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTextStringById(String id)
        {
            try
            {
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + id + ";");            
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

        public static void deleteTextStringByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM text_string WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTextStringByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

