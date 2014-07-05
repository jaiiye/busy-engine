package com.transitionsoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class BasicConnection
{
    protected static String connectionURL = null;
    protected static Connection connection = null;
    protected static Statement statement = null;
    protected static PreparedStatement preparedStatement = null;
    protected static ResultSet rs = null; 

    public static void openConnection()
    {
        try
        {            
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource) environmentContext.lookup("jdbc/dataSource");
            connection = ds.getConnection();
            //org.apache.tomcat.jdbc.pool.DataSource source = (org.apache.tomcat.jdbc.pool.DataSource) ds;                       
            //System.out.println("Connection Pool Active(" + source.getPool().getActive()  + ") Idle(" + source.getPool().getIdle() + ") ");
            statement = connection.createStatement();           
        }
        catch (Exception ex)
        {
            System.out.println("Open connection error: " + ex.getMessage());
        }        
    }
    
    public static void prepareStatement(String sql) throws Exception
    {            
        if(connection != null)
        {            
            System.out.println("Prepared Statement: " + sql);
            preparedStatement = connection.prepareStatement(sql);           
        }
        else 
        {
            throw new Exception("Connection object is NULL!");
        }
    }
        
    public static void closeConnection()
    {
        try
        {
            //if (rs != null)               try  { rs.close(); }                catch (Exception ex){ System.out.println("Close resultSet error: " + ex.getMessage()); }
            if (statement != null)        try  { statement.close(); }         catch (Exception ex){ System.out.println("Close statement error: " + ex.getMessage());}
            if (preparedStatement !=null) try  { preparedStatement.close(); } catch (Exception ex){ System.out.println("Close preparedStatement error: " + ex.getMessage());}
            if (connection != null)       try  { connection.close();}         catch (Exception ex){ System.out.println("Close connection error: " + ex.getMessage());}
        }
        catch (Exception ex)
        {
            System.out.println("Close connection error: " + ex.getMessage());
        }
    }
    
    public static void runQuery(String query)
    {
        try
        {       
            openConnection();
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("runQuery error: " + ex.getMessage());
        }   
    }
    
    public static void updateQuery(String query)
    {
        try
        {       
            openConnection();
            System.out.println("Query: " + query);
            statement.executeUpdate(query);
        }
        catch (Exception ex) 
        {
            System.out.println("updateQuery error: " + ex.getMessage());
        }   
    }
    
    public static void getAllRecordsByTableName(String tableName)
    {
        try
        {       
            openConnection();
            String query = "SELECT * FROM " + tableName + ";";            
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("gtAllRecordsByTableName error: " + ex.getMessage());
        }   
    }
       
    public static void getAllRecordsByTableNameOrderedByColumn(String tableName, String columnName)
    {
        try
        {       
            openConnection();
            String query = "SELECT * FROM " + tableName + " ORDER BY " + columnName + ";";            
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getAllRecordsByTableNameOrderedByColumn error: " + ex.getMessage());
        }   
    }
        
    public static void getRecordsByTableNameWithLimitAndOffset(String tableName, int limit, int offset)
    {
        try
        {       
            openConnection();
            String query = "SELECT * FROM " + tableName + " LIMIT ? OFFSET ?;";
            prepareStatement(query);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            rs = preparedStatement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getAllRecordsByTableNameWithLimitAndOffset error: " + ex.getMessage());
        }   
    }
        
    public static int getAllRecordsCountByTableName(String tableName)
    {
        int count = 0;
        try
        {       
            openConnection();
            String query = "SELECT count(*) FROM " + tableName + ";";           
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
            while (rs.next())
            {
                count = rs.getInt(0);
            }
        }
        catch (Exception ex) 
        {
            System.out.println("getAllRecordsCountByTableName error: " + ex.getMessage());
        }   
        finally
        {
            closeConnection();            
        }
        return count;
    }
            
    public static void getRecordById(String tableName, String id)
    {
        try
        {       
            String tableNameUppercase = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length());   
            String objectName = tableNameUppercase.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
            objectName = objectName.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
                
            String query = "SELECT * FROM " + tableName + " WHERE " + objectName + "Id = " + id + ";";
            runQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getRecordById error: " + ex.getMessage());
        }   
    }
        
    public static void getAllRecordsByColumnOrderedByColumn(String tableName, String columnName, String columnValue, boolean isNumeric, String orderColumn)
    {
        try
        {       
            openConnection();
            String query = "SELECT * FROM " + tableName + " WHERE " + columnName + "="  + (isNumeric?"":"'") + columnValue + (isNumeric?"":"'") + " ORDER BY " + orderColumn + ";";
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getAllRecordsByColumnOrderedByColumn error: " + ex.getMessage());
        }   
    }
    
    public static void getAllRecordsByColumn(String tableName, String columnName, String columnValue, boolean isNumeric)
    {
        try
        {       
            openConnection();
            String query = "SELECT * FROM " + tableName + " WHERE " + columnName + "="  + (isNumeric?"":"'") + columnValue + (isNumeric?"":"'") + ";";
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getAllRecordsByColumn error: " + ex.getMessage());
        }   
    }
    
       
    public static void getDistinctColumnValues(String tableName, String columnName)
    {
        try
        {       
            openConnection();
            String query = "SELECT DISTINCT " + columnName + " FROM " + tableName + ";";
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getDistinctColumnValues error: " + ex.getMessage());
        }   
    }    
    
    public static void getRecordsByColumnWithLimitAndOffset(String tableName, String columnName, String columnValue, boolean isNumeric, int limit, int offset)
    {
        try
        {       
            openConnection();
            String query = "SELECT * FROM " + tableName + " WHERE " + columnName + "="  + (isNumeric?"":"'") + columnValue + (isNumeric?"":"'") + " LIMIT " + limit + " OFFSET " + offset + ";";
            System.out.println("Query: " + query);
            rs = statement.executeQuery(query);
        }
        catch (Exception ex) 
        {
            System.out.println("getRecordsByColumnWithLimitAndOffset error: " + ex.getMessage());
        }   
    }
    
    public static String generateHtmlSelectOptionsFromTableAndColumn(String tableName, String selectedOptionValue, int columnToShow)
    {
        String output = "";
        getAllRecordsByTableName(tableName);
        try
        {
            while(rs.next())
            {     
                output += "<option value=\"" + rs.getString(1) + "\" " + (rs.getString(1).equals(selectedOptionValue) ? "selected" : "") + ">" + rs.getString(columnToShow) + "</option>\n";
            }   
        }
        catch(Exception ex)
        {
            System.out.println("generateHtmlSelectOptionsFromTableAndColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return output;
    }
    
    
    public static String getSelectedColumnValueFromTableAndId(String tableName, String id, int columnValueToReturn)
    {
        String output = "";
        getRecordById(tableName, id);
        try
        {
            while(rs.next())
            {     
                output = rs.getString(columnValueToReturn);
            }   
        }
        catch(Exception ex)
        {
            System.out.println("getSelectedColumnValueFromTableAndId error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return output;
    }
}
