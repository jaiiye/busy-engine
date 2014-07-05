
<% //HashMap<String, Object> pageStructure = (HashMap<String, Object>)request.getAttribute("pageStructure"); %>
<%= (String)pageStructure.get("pageFooter")%>

<% 
    connection.close();  
    statement.close();
%>
