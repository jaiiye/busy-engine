











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Mailinglist;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class MailinglistDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Mailinglist.PROP_MAILINGLIST_ID) || column.equals(Mailinglist.PROP_LIST_NAME) || column.equals(Mailinglist.PROP_EMAIL) || column.equals(Mailinglist.PROP_STATUS) || column.equals(Mailinglist.PROP_USER_ID) )
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
            if (column.equals(Mailinglist.PROP_MAILINGLIST_ID) || column.equals(Mailinglist.PROP_STATUS) || column.equals(Mailinglist.PROP_USER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Mailinglist processMailinglist(ResultSet rs) throws SQLException
        {        
            return new Mailinglist(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addMailinglist(String ListName, String Email, Integer Status, Integer UserId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ListName, 45);
                checkColumnSize(Email, 245);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO mailinglist(ListName,Email,Status,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, ListName);
                preparedStatement.setString(2, Email);
                preparedStatement.setInt(3, Status);
                preparedStatement.setInt(4, UserId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from mailinglist;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addMailinglist error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllMailinglistCount()
        {
            return getAllRecordsCountByTableName("mailinglist");        
        }
        
        public static ArrayList<Mailinglist> getAllMailinglist()
        {
            ArrayList<Mailinglist> mailinglist = new ArrayList<Mailinglist>();
            try
            {
                getAllRecordsByTableName("mailinglist");
                while(rs.next())
                {
                    mailinglist.add(processMailinglist(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllMailinglist error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
        }
                
        public static ArrayList<Mailinglist> getMailinglistPaged(int limit, int offset)
        {
            ArrayList<Mailinglist> mailinglist = new ArrayList<Mailinglist>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("mailinglist", limit, offset);
                while (rs.next())
                {
                    mailinglist.add(processMailinglist(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getMailinglistPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
        } 
        
        public static ArrayList<Mailinglist> getAllMailinglistByColumn(String columnName, String columnValue)
        {
            ArrayList<Mailinglist> mailinglist = new ArrayList<Mailinglist>();
            try
            {
                getAllRecordsByColumn("mailinglist", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    mailinglist.add(processMailinglist(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllMailinglistByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
        }
        
        public static Mailinglist getMailinglistByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Mailinglist mailinglist = new Mailinglist();
            try
            {
                getRecordsByColumnWithLimitAndOffset("mailinglist", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   mailinglist = processMailinglist(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getMailinglistByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return mailinglist;
        }                
                
        public static void updateMailinglist(Integer MailinglistId,String ListName,String Email,Integer Status,Integer UserId)
        {  
            try
            {   
                
                checkColumnSize(ListName, 45);
                checkColumnSize(Email, 245);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE mailinglist SET ListName=?,Email=?,Status=?,UserId=? WHERE MailinglistId=?;");                    
                preparedStatement.setString(1, ListName);
                preparedStatement.setString(2, Email);
                preparedStatement.setInt(3, Status);
                preparedStatement.setInt(4, UserId);
                preparedStatement.setInt(5, MailinglistId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateMailinglist error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllMailinglist()
        {
            try
            {
                updateQuery("DELETE FROM mailinglist;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllMailinglist error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteMailinglistById(String id)
        {
            try
            {
                updateQuery("DELETE FROM mailinglist WHERE MailinglistId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteMailinglistById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteMailinglistByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM mailinglist WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteMailinglistByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

