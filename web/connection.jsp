<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%!
    private Connection getDbConnection() throws NamingException, SQLException 
    {
        Context initialContext = new InitialContext();
        Context environmentContext = (Context) initialContext.lookup("java:comp/env");
        DataSource ds = (DataSource) environmentContext.lookup("jdbc/dataSource");
        return ds.getConnection();
    }    
%><%
    String connectionURL = null;
    Connection connection = getDbConnection();
    Statement statement = null;
    ResultSet rs = null;
    statement = connection.createStatement();    
%>