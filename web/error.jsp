
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

    <head>
        <%@include file="includes.jsp" %> 
    </head>

    <body>
    	<br />
        <TABLE cellSpacing="5" cellPadding="5" width="60%" border="0" align="center" background="#FFF">
            <TBODY>
                <TR>                       
                    <TD width="*" valign="top">
                        <div align="center">
                            <p style="font-size:72px; color:#CCC">${param.code != null ? param.code + "":"ERROR"}</p>
                            ${param.msg != null ? param.msg + "":""}
                            
                            <c:if test="${param.code == '404'}">
                                <h1 style="color:#F00"><%=application.getAttribute("file-not-found")%></h1><br />
                            	<a href="javascript: history.go(-1)">Back to previous page</a>			
                            </c:if>
                            
                            <c:if test="${param.code == '500'}">
                            	<h1 style="color:#F00"><%=application.getAttribute("internal-server-error-text")%></h1><br />
                            	<%=application.getAttribute("file-not-found-message-1")%><br /><br />
                                <a href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>		
                            </c:if>                            
                       </div>
                    </TD>
                </TR>                    
            </TBODY>
        </TABLE>     
    </body>
</html>
