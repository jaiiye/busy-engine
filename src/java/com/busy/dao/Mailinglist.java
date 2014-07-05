


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Mailinglist extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_MAILINGLISTID = "MailinglistId";
        public static final String PROP_MAILINGLISTNAME = "MailinglistName";
        public static final String PROP_MAILINGLISTEMAIL = "MailinglistEmail";
        
        
        private Integer mailinglistId;
        private String mailinglistName;
        private String mailinglistEmail;
        
        
        public Mailinglist()
        {
            this.mailinglistId = 0; 
       this.mailinglistName = ""; 
       this.mailinglistEmail = ""; 
        }
        
        public Mailinglist(Integer MailinglistId, String MailinglistName, String MailinglistEmail)
        {
            this.mailinglistId = MailinglistId;
       this.mailinglistName = MailinglistName;
       this.mailinglistEmail = MailinglistEmail;
        } 
        
        
            public Integer getMailinglistId()
            {
                return this.mailinglistId;
            }
            
            public void setMailinglistId(Integer MailinglistId)
            {
                this.mailinglistId = MailinglistId;
            }
        
            public String getMailinglistName()
            {
                return this.mailinglistName;
            }
            
            public void setMailinglistName(String MailinglistName)
            {
                this.mailinglistName = MailinglistName;
            }
        
            public String getMailinglistEmail()
            {
                return this.mailinglistEmail;
            }
            
            public void setMailinglistEmail(String MailinglistEmail)
            {
                this.mailinglistEmail = MailinglistEmail;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Mailinglist.PROP_MAILINGLISTID) || column.equals(Mailinglist.PROP_MAILINGLISTNAME) || column.equals(Mailinglist.PROP_MAILINGLISTEMAIL) )
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
            if (column.equals(Mailinglist.PROP_MAILINGLISTID) )
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
            return new Mailinglist(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addMailinglist(String MailinglistName, String MailinglistEmail)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(MailinglistName, 45);
                checkColumnSize(MailinglistEmail, 245);
                                            
                openConnection();
                prepareStatement("INSERT INTO mailinglist(MailinglistName,MailinglistEmail) VALUES (?,?);");                    
                preparedStatement.setString(1, MailinglistName);
                preparedStatement.setString(2, MailinglistEmail);
                
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
                
        public static void updateMailinglist(Integer MailinglistId,String MailinglistName,String MailinglistEmail)
        {  
            try
            {   
                
                checkColumnSize(MailinglistName, 45);
                checkColumnSize(MailinglistEmail, 245);
                                  
                openConnection();                           
                prepareStatement("UPDATE mailinglist SET MailinglistName=?,MailinglistEmail=? WHERE MailinglistId=?;");                    
                preparedStatement.setString(1, MailinglistName);
                preparedStatement.setString(2, MailinglistEmail);
                preparedStatement.setInt(3, MailinglistId);
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

