


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ContactDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Contact.PROP_CONTACT_ID) || column.equals(Contact.PROP_TITLE) || column.equals(Contact.PROP_FIRST_NAME) || column.equals(Contact.PROP_LAST_NAME) || column.equals(Contact.PROP_POSITION) || column.equals(Contact.PROP_PHONE) || column.equals(Contact.PROP_FAX) || column.equals(Contact.PROP_EMAIL) || column.equals(Contact.PROP_STATUS) || column.equals(Contact.PROP_WEB_URL) || column.equals(Contact.PROP_INFO) )
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
            if (column.equals(Contact.PROP_CONTACT_ID) || column.equals(Contact.PROP_STATUS) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Contact processContact(ResultSet rs) throws SQLException
        {        
            return new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11));
        }
        
        public static int addContact(String Title, String FirstName, String LastName, String Position, String Phone, String Fax, String Email, Integer Status, String WebUrl, String Info)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Title, 5);
                checkColumnSize(FirstName, 50);
                checkColumnSize(LastName, 50);
                checkColumnSize(Position, 255);
                checkColumnSize(Phone, 15);
                checkColumnSize(Fax, 15);
                checkColumnSize(Email, 255);
                
                checkColumnSize(WebUrl, 255);
                checkColumnSize(Info, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO contact(Title,FirstName,LastName,Position,Phone,Fax,Email,Status,WebUrl,Info) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, FirstName);
                preparedStatement.setString(3, LastName);
                preparedStatement.setString(4, Position);
                preparedStatement.setString(5, Phone);
                preparedStatement.setString(6, Fax);
                preparedStatement.setString(7, Email);
                preparedStatement.setInt(8, Status);
                preparedStatement.setString(9, WebUrl);
                preparedStatement.setString(10, Info);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from contact;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllContactCount()
        {
            return getAllRecordsCountByTableName("contact");        
        }
        
        public static ArrayList<Contact> getAllContact()
        {
            ArrayList<Contact> contact = new ArrayList<Contact>();
            try
            {
                getAllRecordsByTableName("contact");
                while(rs.next())
                {
                    contact.add(processContact(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
        }
                
        public static ArrayList<Contact> getContactPaged(int limit, int offset)
        {
            ArrayList<Contact> contact = new ArrayList<Contact>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("contact", limit, offset);
                while (rs.next())
                {
                    contact.add(processContact(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getContactPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
        } 
        
        public static ArrayList<Contact> getAllContactByColumn(String columnName, String columnValue)
        {
            ArrayList<Contact> contact = new ArrayList<Contact>();
            try
            {
                getAllRecordsByColumn("contact", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    contact.add(processContact(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllContactByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
        }
        
        public static Contact getContactByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Contact contact = new Contact();
            try
            {
                getRecordsByColumnWithLimitAndOffset("contact", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   contact = processContact(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getContactByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return contact;
        }                
                
        public static void updateContact(Integer ContactId,String Title,String FirstName,String LastName,String Position,String Phone,String Fax,String Email,Integer Status,String WebUrl,String Info)
        {  
            try
            {   
                
                checkColumnSize(Title, 5);
                checkColumnSize(FirstName, 50);
                checkColumnSize(LastName, 50);
                checkColumnSize(Position, 255);
                checkColumnSize(Phone, 15);
                checkColumnSize(Fax, 15);
                checkColumnSize(Email, 255);
                
                checkColumnSize(WebUrl, 255);
                checkColumnSize(Info, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE contact SET Title=?,FirstName=?,LastName=?,Position=?,Phone=?,Fax=?,Email=?,Status=?,WebUrl=?,Info=? WHERE ContactId=?;");                    
                preparedStatement.setString(1, Title);
                preparedStatement.setString(2, FirstName);
                preparedStatement.setString(3, LastName);
                preparedStatement.setString(4, Position);
                preparedStatement.setString(5, Phone);
                preparedStatement.setString(6, Fax);
                preparedStatement.setString(7, Email);
                preparedStatement.setInt(8, Status);
                preparedStatement.setString(9, WebUrl);
                preparedStatement.setString(10, Info);
                preparedStatement.setInt(11, ContactId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllContact()
        {
            try
            {
                updateQuery("DELETE FROM contact;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllContact error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteContactById(String id)
        {
            try
            {
                updateQuery("DELETE FROM contact WHERE ContactId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteContactById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteContactByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM contact WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteContactByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

