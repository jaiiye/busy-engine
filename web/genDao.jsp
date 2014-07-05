<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DatabaseMetaData"%>
<%@ page import="java.text.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="connection.jsp" %> 
<%!
public String getColumnDataType(int type)
{
    String columnDataType = "String";
    switch(type)
    {
        case java.sql.Types.INTEGER:
            columnDataType = "Integer";
            break;                
        case java.sql.Types.BOOLEAN:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.BIT:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.DATE:
            columnDataType = "Date";
            break;
        case java.sql.Types.DECIMAL:
            columnDataType = "Double";
            break;
        case java.sql.Types.SMALLINT:
            columnDataType = "Integer";
            break;
        case java.sql.Types.TINYINT:
            columnDataType = "Integer";
            break;
        case java.sql.Types.DOUBLE:
            columnDataType = "Double";
            break;
        case java.sql.Types.TIMESTAMP:
            columnDataType = "Date";
            break;
        default:
            columnDataType = "String";
            break;
    }
    return columnDataType;     
}
%><%!
public boolean isColumnDataTypeNumeric(int type)
{
    boolean isNumeric = false;
    switch(type)
    {
        case java.sql.Types.INTEGER:
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
        case java.sql.Types.DECIMAL:
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
        case java.sql.Types.DOUBLE:
            isNumeric = true;
            break;
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            isNumeric = false;
            break;
        default:
            isNumeric = false;
            break;
    }
    return isNumeric;     
}
%>
<%!
public String getColumnSetterName(int type)
{
    String columnDataType = "String";
    switch(type)
    {
        case java.sql.Types.INTEGER:
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
            columnDataType = "Int";
            break;                
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            columnDataType = "Date";
            break;
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            columnDataType = "Double";
            break;
        default:
            columnDataType = "String";
            break;
    }
    return columnDataType;     
}
%>

<%!
public int getNumberOfNumericColumns(ArrayList<String> columns,  ArrayList<Integer> columnTypes)
{
    int count = 0;
    for(int index = 0; index < columns.size(); index++) 
    { 
        if(isColumnDataTypeNumeric(columnTypes.get(index))) {
            count++;
        }
    }
    return count;
}
%>


<%!
public String getColumnDataTypeInitValue(int type)
{
    String value = "\"\"";
    switch(type)
    {
        case java.sql.Types.INTEGER:
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
            value = "0";
            break;                
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            value = "null";
            break;
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            value = "0.0";
            break;
        default:
            value = "\"\"";
            break;
    }
    return value;     
}

%><%!
public String getColumnResultSetDataType(int type)
{
    String columnDataType = "String";
    switch(type)
    {
        case java.sql.Types.INTEGER:
            columnDataType = "Int";
            break;                
        case java.sql.Types.BOOLEAN:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.BIT:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.DATE:
            columnDataType = "Date";
            break;
        case java.sql.Types.DECIMAL:
            columnDataType = "Double";
            break;
        case java.sql.Types.SMALLINT:
            columnDataType = "Int";
            break;
        case java.sql.Types.TINYINT:
            columnDataType = "Int";
            break;
        case java.sql.Types.DOUBLE:
            columnDataType = "Double";
            break;
        case java.sql.Types.TIMESTAMP:
            columnDataType = "Date";
            break;
        default:
            columnDataType = "String";
            break;
    }
    return columnDataType;     
}

%>


<%!
public String getColumnParseCommand(int type, String column, String columnNameLowercase, int size)
{
    String command = "";
    switch(type)
    {
        case java.sql.Types.INTEGER:
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
            //command = getColumnDataType(type) + columnNameLowercase + " = Integer.ParseInt(" +  column + ");";
            break;                
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
            //command = getColumnDataType(type) + columnNameLowercase + " = Boolean.ParseBoolean(" +  column + ");";
            break;
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            //command = getColumnDataType(type) + columnNameLowercase + " = SimpleDateFormat.parse(" +  column + ");";
            break;
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            //command = getColumnDataType(type) + columnNameLowercase + " = Double.ParseDouble(" +  column + ");";
            break;
        default:
            command = "checkColumnSize(" + column + ", " + size + ");";
            break;
    }
    return command;     
}

%>


<%!
public String getColumnDataTypeWrapper(int type)
{
    String value = "'";
    switch(type)
    {
        case java.sql.Types.INTEGER:
            value = "";
            break;                
        case java.sql.Types.BOOLEAN:
            value = "";
            break;
        case java.sql.Types.BIT:
            value = "";
            break;
        case java.sql.Types.DATE:
            value = "'";
            break;
        case java.sql.Types.DECIMAL:
            value = "";
            break;
        case java.sql.Types.SMALLINT:
            value = "";
            break;
        case java.sql.Types.TINYINT:
            value = "";
            break;
        case java.sql.Types.DOUBLE:
            value = "";
            break;
        case java.sql.Types.TIMESTAMP:
            value = "'";
            break;
        default:
            value = "'";
            break;
    }
    return value;     
}

%><% 
    String tableNameLowercase = request.getParameter("table"); 
    String tableNameUppercase = request.getParameter("table").substring(0, 1).toUpperCase() + request.getParameter("table").substring(1, request.getParameter("table").length());
    String objectName = tableNameUppercase.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
    objectName = objectName.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");

    DatabaseMetaData databaseMetaData = connection.getMetaData();
    String   catalog          = null;
    String   schemaPattern    = null;
    String   tableNamePattern = tableNameLowercase;
    String[] types            = null;
    String   columnNamePattern = null;

    rs = databaseMetaData.getColumns(catalog, schemaPattern,  tableNamePattern, columnNamePattern);

    ArrayList<String> columns = new ArrayList<String>(); 
    ArrayList<String> columnsNumeric = new ArrayList<String>();    
    ArrayList<Integer> columnTypes = new ArrayList<Integer>();
    ArrayList<Integer> columnSizes = new ArrayList<Integer>();
    ArrayList<String> columnDefaultValues = new ArrayList<String>();
    
    while(rs.next())
    {
        columns.add(rs.getString(4));
        if(isColumnDataTypeNumeric(rs.getInt(5))) { columnsNumeric.add(rs.getString(4)); } 
        columnTypes.add(rs.getInt(5));  
        columnSizes.add(rs.getInt("COLUMN_SIZE"));
        columnDefaultValues.add(rs.getString("COLUMN_DEF"));
    }
    
    int numColumns = columns.size(); 
    
    
%>
    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class <%= objectName %> extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        <%
        for(int index = 0; index < columns.size(); index++)
        {
            String column = columns.get(index);            
            String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
            String columnNameUppercase = column.toUpperCase();            
        %>public static final String PROP_<%= columnNameUppercase %> = "<%= column %>";
        <% } %>
        
        <%
        for(int index = 0; index < columns.size(); index++)
        {
            String column = columns.get(index);            
            String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());            
        %>private <%= getColumnDataType(columnTypes.get(index)) %> <%= columnNameLowercase %>;
        <% } %>
        
        public <%= objectName %>()
        {
            <%
            for(int index = 0; index < columns.size(); index++)
            {
                String column = columns.get(index);
                String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
            %>this.<%= columnNameLowercase%> = <%= getColumnDataTypeInitValue(columnTypes.get(index)) %>; 
       <% } %> }
        
        public <%= objectName %>(<%for(int i = 0; i < numColumns; i++) {  { %><%= getColumnDataType(columnTypes.get(i)) %> <%= columns.get(i) %><% if(i!=numColumns-1) { %>, <% } } }%>)
        {
            <%
            for(String column : columns)
            {
                String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
            %>this.<%= columnNameLowercase%> = <%= column%>;
       <% } %> } 
        
        <%
        for(int index = 0; index < columns.size(); index++)
        {
            String column = columns.get(index);            
            String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
        %>
            public <%= getColumnDataType(columnTypes.get(index)) %> get<%= column %>()
            {
                return this.<%= columnNameLowercase%>;
            }
            
            public void set<%= column %>(<%= getColumnDataType(columnTypes.get(index)) %> <%= column%>)
            {
                this.<%= columnNameLowercase%> = <%= column%>;
            }
        <%
        }
        %>
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(<% for(int index = 0; index < columns.size(); index++) { String column = columns.get(index); String columnNameUppercase = column.toUpperCase(); %>column.equals(<%= objectName %>.PROP_<%= columnNameUppercase %>) <% if(index!=columns.size()-1) { %>|| <% } } %>)
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
            if (<% for(int index = 0; index < columnsNumeric.size(); index++) { String column = columnsNumeric.get(index); String columnNameUppercase = column.toUpperCase(); %>column.equals(<%= objectName %>.PROP_<%= columnNameUppercase %>) <% if(index!=columnsNumeric.size()-1) { %>|| <% } } %>)
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static <%= objectName %> process<%= objectName %>(ResultSet rs) throws SQLException
        {        
            return new <%= objectName %>(<% for(int i = 0; i < numColumns; i++) { %>rs.get<%= getColumnResultSetDataType(columnTypes.get(i)) %>(<%=i+1%>)<% if(i!=numColumns-1) { %>, <% } }%>);
        }
        
        public static int add<%= objectName %>(<%for(int i = 1; i < numColumns; i++) {  { %><%= getColumnDataType(columnTypes.get(i)) %> <%= columns.get(i) %><% if(i!=numColumns-1) { %>, <% } } }%>)
        {   
            int id = 0;
            try
            {
                <%
                for(int index = 0; index < columns.size(); index++)
                {
                    String column = columns.get(index);            
                    String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
                %><%= getColumnParseCommand( columnTypes.get(index), columns.get(index), columnNameLowercase, columnSizes.get(index) ) %>
                <% } %>                            
                openConnection();
                prepareStatement("INSERT INTO <%= tableNameLowercase %>(<%for(int i = 1; i < numColumns; i++) { { %><%= columns.get(i) %><% if(i!=numColumns-1) { %>,<% } } }%>) VALUES (<%for(int i = 1; i < numColumns; i++) {  { %>?<% if(i!=numColumns-1) { %>,<% } } }%>);");                    
                <%
                for(int index = 1; index < columns.size(); index++)
                {
                    String column = columns.get(index);            
                    String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
                    String conversionString = columns.get(index);
                    if(columnTypes.get(index) == java.sql.Types.DATE || columnTypes.get(index) == java.sql.Types.TIMESTAMP)
                    {
                        conversionString = "new java.sql.Date(" + columns.get(index) + ".getTime())";
                    }  
                %>preparedStatement.set<%= getColumnSetterName(columnTypes.get(index)) %>(<%= index %>, <%= conversionString %>);
                <% } %>
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from <%= tableNameLowercase %>;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("add<%= objectName %> error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAll<%= objectName %>Count()
        {
            return getAllRecordsCountByTableName("<%= tableNameLowercase %>");        
        }
        
        public static ArrayList<<%= objectName %>> getAll<%= objectName %>()
        {
            ArrayList<<%= objectName %>> <%= tableNameLowercase %> = new ArrayList<<%= objectName %>>();
            try
            {
                getAllRecordsByTableName("<%= tableNameLowercase %>");
                while(rs.next())
                {
                    <%= tableNameLowercase %>.add(process<%= objectName %>(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAll<%= objectName %> error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return <%= tableNameLowercase %>;
        }
                
        public static ArrayList<<%= objectName %>> get<%= objectName %>Paged(int limit, int offset)
        {
            ArrayList<<%= objectName %>> <%= tableNameLowercase %> = new ArrayList<<%= objectName %>>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("<%= tableNameLowercase %>", limit, offset);
                while (rs.next())
                {
                    <%= tableNameLowercase %>.add(process<%= objectName %>(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("get<%= objectName %>Paged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return <%= tableNameLowercase %>;
        } 
        
        public static ArrayList<<%= objectName %>> getAll<%= objectName %>ByColumn(String columnName, String columnValue)
        {
            ArrayList<<%= objectName %>> <%= tableNameLowercase %> = new ArrayList<<%= objectName %>>();
            try
            {
                getAllRecordsByColumn("<%= tableNameLowercase %>", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    <%= tableNameLowercase %>.add(process<%= objectName %>(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAll<%= objectName %>ByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return <%= tableNameLowercase %>;
        }
        
        public static <%= objectName %> get<%= objectName %>ByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            <%= objectName %> <%= tableNameLowercase %> = new <%= objectName %>();
            try
            {
                getRecordsByColumnWithLimitAndOffset("<%= tableNameLowercase %>", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   <%= tableNameLowercase %> = process<%= objectName %>(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("get<%= objectName %>ByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return <%= tableNameLowercase %>;
        }                
                
        public static void update<%= objectName %>(<%for(int i = 0; i < numColumns; i++) {  { %><%= getColumnDataType(columnTypes.get(i)) %> <%= columns.get(i) %><% if(i!=numColumns-1) { %>,<% } } }%>)
        {  
            try
            {   
                <%
                for(int index = 0; index < columns.size(); index++)
                {
                    String column = columns.get(index);            
                    String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
                %><%= getColumnParseCommand( columnTypes.get(index), columns.get(index), columnNameLowercase, columnSizes.get(index) ) %>
                <% } %>                  
                openConnection();                           
                prepareStatement("UPDATE <%= tableNameLowercase %> SET <%for(int i = 1; i < numColumns; i++) { { %><%= columns.get(i) %>=?<% if(i!=numColumns-1) { %>,<% } } }%> WHERE <%= columns.get(0) %>=?;");                    
                <%
                for(int index = 1; index < columns.size(); index++)
                {
                    String column = columns.get(index);            
                    String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
                    String conversionString = columns.get(index);
                    if(columnTypes.get(index) == java.sql.Types.DATE || columnTypes.get(index) == java.sql.Types.TIMESTAMP)
                    {
                        conversionString = "new java.sql.Date(" + columns.get(index) + ".getTime())";
                    }                      
                %>preparedStatement.set<%= getColumnSetterName(columnTypes.get(index)) %>(<%= index %>, <%= conversionString %>);
                <% } %>preparedStatement.set<%= getColumnSetterName(columnTypes.get(0)) %>(<%= columns.size() %>, <%= columns.get(0) %>);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("update<%= objectName %> error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAll<%= objectName %>()
        {
            try
            {
                updateQuery("DELETE FROM <%= tableNameLowercase %>;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAll<%= objectName %> error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void delete<%= objectName %>ById(String id)
        {
            try
            {
                updateQuery("DELETE FROM <%= tableNameLowercase %> WHERE <%= objectName %>Id=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("delete<%= objectName %>ById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void delete<%= objectName %>ByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM <%= tableNameLowercase %> WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("delete<%= objectName %>ByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

<%
    connection.close();
    statement.close();
%>