
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
                                
                            <c:choose>
                                <c:when test="${param.code == '404'}">
                                <h1 style="color:#F00"><%=application.getAttribute("file-not-found")%></h1><br />
                            	<a href="javascript: history.go(-1)">Back to previous page</a>	
                                </c:when>
                                    
                                <c:when test="${param.code == '500'}">
                            	<h1 style="color:#F00"><%=application.getAttribute("internal-server-error-text")%></h1><br />
                            	<%=application.getAttribute("file-not-found-message-1")%><br /><br />
                                <a href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>
                                </c:when>
                                    
                                <c:when test="${param.code == '989'}">
                            	<h1 style="color:#F00">Email Confirmation Failed!</h1><br />
                            	We're sorry, but it seems you either waited too long to confirm your email or submitted an invalid email confirmation code.<br /><br />
                                <a href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>
                                </c:otherwise>
                            </c:choose>
        
                       </div>
                    </TD>
                </TR>                    
            </TBODY>
        </TABLE>     
    </body>
</html>
