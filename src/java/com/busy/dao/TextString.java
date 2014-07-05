


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TextString extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_TEXTSTRINGID = "TextStringId";
        public static final String PROP_TEXTSTRINGKEY = "TextStringKey";
        
        
        private Integer textStringId;
        private String textStringKey;
        
        
        public TextString()
        {
            this.textStringId = 0; 
       this.textStringKey = ""; 
        }
        
        public TextString(Integer TextStringId, String TextStringKey)
        {
            this.textStringId = TextStringId;
       this.textStringKey = TextStringKey;
        } 
        
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
        
            public String getTextStringKey()
            {
                return this.textStringKey;
            }
            
            public void setTextStringKey(String TextStringKey)
            {
                this.textStringKey = TextStringKey;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TextString.PROP_TEXTSTRINGID) || column.equals(TextString.PROP_TEXTSTRINGKEY) )
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
            if (column.equals(TextString.PROP_TEXTSTRINGID) )
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
        
        public static int addTextString(String TextStringKey)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TextStringKey, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO text_string(TextStringKey) VALUES (?);");                    
                preparedStatement.setString(1, TextStringKey);
                
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
                
        public static void updateTextString(Integer TextStringId,String TextStringKey)
        {  
            try
            {   
                
                checkColumnSize(TextStringKey, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string SET TextStringKey=? WHERE TextStringId=?;");                    
                preparedStatement.setString(1, TextStringKey);
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

