





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TextStringLocalDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TextStringLocal.PROP_LOCALIZED_STRING_ID) || column.equals(TextStringLocal.PROP_VALUE) || column.equals(TextStringLocal.PROP_LOCALE) || column.equals(TextStringLocal.PROP_TEXT_STRING_ID) )
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
            if (column.equals(TextStringLocal.PROP_LOCALIZED_STRING_ID) || column.equals(TextStringLocal.PROP_TEXT_STRING_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TextStringLocal processTextStringLocal(ResultSet rs) throws SQLException
        {        
            return new TextStringLocal(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        }
        
        public static int addTextStringLocal(String Value, String Locale, Integer TextStringId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Value, 255);
                checkColumnSize(Locale, 10);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO text_string_local(Value,Locale,TextStringId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Value);
                preparedStatement.setString(2, Locale);
                preparedStatement.setInt(3, TextStringId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from text_string_local;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addTextStringLocal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllTextStringLocalCount()
        {
            return getAllRecordsCountByTableName("text_string_local");        
        }
        
        public static ArrayList<TextStringLocal> getAllTextStringLocal()
        {
            ArrayList<TextStringLocal> text_string_local = new ArrayList<TextStringLocal>();
            try
            {
                getAllRecordsByTableName("text_string_local");
                while(rs.next())
                {
                    text_string_local.add(processTextStringLocal(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTextStringLocal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string_local;
        }
        
        public static ArrayList<TextStringLocal> getAllTextStringLocalWithRelatedInfo()
        {
            ArrayList<TextStringLocal> text_string_localList = new ArrayList<TextStringLocal>();
            try
            {
                getAllRecordsByTableName("text_string_local");
                while (rs.next())
                {
                    text_string_localList.add(processTextStringLocal(rs));
                }

                
                    for(TextStringLocal text_string_local : text_string_localList)
                    {
                        
                            getRecordById("TextString", text_string_local.getTextStringId().toString());
                            text_string_local.setTextString(TextStringDAO.processTextString(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTextStringLocalWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string_localList;
        }
        
        
        public static TextStringLocal getRelatedInfo(TextStringLocal text_string_local)
        {
           
                
                    try
                    { 
                        
                            getRecordById("TextString", text_string_local.getTextStringId().toString());
                            text_string_local.setTextString(TextStringDAO.processTextString(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return text_string_local;
        }
        
        public static TextStringLocal getAllRelatedObjects(TextStringLocal text_string_local)
        {           
                         
            return text_string_local;
        }
        
        
        
                
        public static ArrayList<TextStringLocal> getTextStringLocalPaged(int limit, int offset)
        {
            ArrayList<TextStringLocal> text_string_local = new ArrayList<TextStringLocal>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("text_string_local", limit, offset);
                while (rs.next())
                {
                    text_string_local.add(processTextStringLocal(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTextStringLocalPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string_local;
        } 
        
        public static ArrayList<TextStringLocal> getAllTextStringLocalByColumn(String columnName, String columnValue)
        {
            ArrayList<TextStringLocal> text_string_local = new ArrayList<TextStringLocal>();
            try
            {
                getAllRecordsByColumn("text_string_local", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    text_string_local.add(processTextStringLocal(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllTextStringLocalByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string_local;
        }
        
        public static TextStringLocal getTextStringLocalByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            TextStringLocal text_string_local = new TextStringLocal();
            try
            {
                getRecordsByColumnWithLimitAndOffset("text_string_local", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   text_string_local = processTextStringLocal(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getTextStringLocalByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return text_string_local;
        }                
                
        public static void updateTextStringLocal(Integer LocalizedStringId,String Value,String Locale,Integer TextStringId)
        {  
            try
            {   
                
                checkColumnSize(Value, 255);
                checkColumnSize(Locale, 10);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string_local SET Value=?,Locale=?,TextStringId=? WHERE LocalizedStringId=?;");                    
                preparedStatement.setString(1, Value);
                preparedStatement.setString(2, Locale);
                preparedStatement.setInt(3, TextStringId);
                preparedStatement.setInt(4, LocalizedStringId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateTextStringLocal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllTextStringLocal()
        {
            try
            {
                updateQuery("DELETE FROM text_string_local;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllTextStringLocal error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteTextStringLocalById(String id)
        {
            try
            {
                updateQuery("DELETE FROM text_string_local WHERE TextStringLocalId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteTextStringLocalById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteTextStringLocalByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM text_string_local WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteTextStringLocalByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

