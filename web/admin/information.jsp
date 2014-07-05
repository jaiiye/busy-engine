
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="header.jsp" %> 

				<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
					<TBODY>
<TR >
    <TD colSpan=2>                
        <center>   
	<%  		
		statement = connection.createStatement();
		rs = statement.executeQuery("SELECT * FROM info;");
                int i = 1;
		while( rs.next() ) {                                              
    %>        
			<br>
            <DIV id=sidebar>
                <DL>
                	<DT><%= rs.getString(2)%>
                    <dd>   
                        <form method="POST" action="edit.jsp?form=info">
                            <input type="hidden" name="name" value="<%= rs.getString(2)%>">  
                            
                            <table width="50%" cellpadding="0" cellspacing="0" border="0">
                                <tr><td>
                                    <textarea id="editor<%=i++%>" name="desc" rows="20" cols="75">
                                        <%= rs.getString(3)%>
                                    </textarea></td></tr>
                                <tr><input name="rts" type="submit" value="Update Info" onclick="javascript: submitButton()"/></td></tr>
                            </table>
                       </form>
                    </dd>
                </DL>
            </DIV>                
        </center>
   		<HR width="100%" color=#222222 SIZE=3>           
    <% } %>  



<script>
var myEditor1;
var myEditor2;
var myEditor3;
var myEditor4;
var myEditor5;
var myEditor6;
var myEditor7;

(function() 
{

    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event;
    
    var myConfig = {
        height: '300px',
        width: '700px',
        dompath: true,
        focusAtStart: true,
        toolbarCollapsed: 1
    };

    myEditor1 = new YAHOO.widget.SimpleEditor('editor1', myConfig);  myEditor1.render();
    myEditor2 = new YAHOO.widget.SimpleEditor('editor2', myConfig);  myEditor2.render();
    myEditor3 = new YAHOO.widget.SimpleEditor('editor3', myConfig);  myEditor3.render();
    myEditor4 = new YAHOO.widget.SimpleEditor('editor4', myConfig);  myEditor4.render();
    myEditor5 = new YAHOO.widget.SimpleEditor('editor5', myConfig);  myEditor5.render();
    myEditor6 = new YAHOO.widget.SimpleEditor('editor6', myConfig);  myEditor6.render();
	    myEditor7 = new YAHOO.widget.SimpleEditor('editor7', myConfig);  myEditor7.render();

})();

function submitButton()
{        
    myEditor1.saveHTML();     
    myEditor2.saveHTML();
    myEditor3.saveHTML();
    myEditor4.saveHTML();
    myEditor5.saveHTML();
    myEditor6.saveHTML();  	
    myEditor7.saveHTML();       
}
</script>


                     
    </TD>
</TR>

</TABLE>
<BR>




<%@ include file="footer.jsp" %>