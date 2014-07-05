


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class TextStringLocal extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_TEXTSTRINGLOCALID = "TextStringLocalId";
        public static final String PROP_TEXTSTRINGVALUE = "TextStringValue";
        public static final String PROP_TEXTSTRINGID = "TextStringId";
        public static final String PROP_LOCALEID = "LocaleId";
        
        
        private Integer textStringLocalId;
        private String textStringValue;
        private Integer textStringId;
        private Integer localeId;
        
        
        public TextStringLocal()
        {
            this.textStringLocalId = 0; 
       this.textStringValue = ""; 
       this.textStringId = 0; 
       this.localeId = 0; 
        }
        
        public TextStringLocal(Integer TextStringLocalId, String TextStringValue, Integer TextStringId, Integer LocaleId)
        {
            this.textStringLocalId = TextStringLocalId;
       this.textStringValue = TextStringValue;
       this.textStringId = TextStringId;
       this.localeId = LocaleId;
        } 
        
        
            public Integer getTextStringLocalId()
            {
                return this.textStringLocalId;
            }
            
            public void setTextStringLocalId(Integer TextStringLocalId)
            {
                this.textStringLocalId = TextStringLocalId;
            }
        
            public String getTextStringValue()
            {
                return this.textStringValue;
            }
            
            public void setTextStringValue(String TextStringValue)
            {
                this.textStringValue = TextStringValue;
            }
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
        
            public Integer getLocaleId()
            {
                return this.localeId;
            }
            
            public void setLocaleId(Integer LocaleId)
            {
                this.localeId = LocaleId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TextStringLocal.PROP_TEXTSTRINGLOCALID) || column.equals(TextStringLocal.PROP_TEXTSTRINGVALUE) || column.equals(TextStringLocal.PROP_TEXTSTRINGID) || column.equals(TextStringLocal.PROP_LOCALEID) )
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
            if (column.equals(TextStringLocal.PROP_TEXTSTRINGLOCALID) || column.equals(TextStringLocal.PROP_TEXTSTRINGID) || column.equals(TextStringLocal.PROP_LOCALEID) )
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
            return new TextStringLocal(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addTextStringLocal(String TextStringValue, Integer TextStringId, Integer LocaleId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TextStringValue, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO text_string_local(TextStringValue,TextStringId,LocaleId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, TextStringValue);
                preparedStatement.setInt(2, TextStringId);
                preparedStatement.setInt(3, LocaleId);
                
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
                
        public static void updateTextStringLocal(Integer TextStringLocalId,String TextStringValue,Integer TextStringId,Integer LocaleId)
        {  
            try
            {   
                
                checkColumnSize(TextStringValue, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string_local SET TextStringValue=?,TextStringId=?,LocaleId=? WHERE TextStringLocalId=?;");                    
                preparedStatement.setString(1, TextStringValue);
                preparedStatement.setInt(2, TextStringId);
                preparedStatement.setInt(3, LocaleId);
                preparedStatement.setInt(4, TextStringLocalId);
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

