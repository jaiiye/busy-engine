
<%@page import="com.busy.engine.domain.ResourceUrl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.busy.engine.dao.base.TemplateDao"%>
<%@page import="com.busy.engine.dao.impl.TemplateDaoImpl"%>
<%@page import="com.busy.engine.domain.Template"%>
<%@page import="com.busy.engine.domain.Page"%>
<%@page import="com.busy.engine.dao.base.PageDao"%>
<%@page import="com.busy.engine.dao.impl.PageDaoImpl"%>
<%@page import="com.busy.engine.domain.Site"%>
<%@page import="com.busy.engine.dao.base.SiteDao"%>
<%@page import="com.busy.engine.dao.impl.SiteDaoImpl"%>
<%@page import="java.util.HashMap"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    SiteDao siteDao = new SiteDaoImpl();
    PageDao pageDao = new PageDaoImpl();

    Site s = siteDao.find(1);
    Page p = pageDao.find(1);

    s = siteDao.getRelatedInfo(s);
    p = pageDao.getRelatedInfo(p);

    s = siteDao.getRelatedSiteAttributeList(s);
    s = siteDao.getRelatedSiteImageList(s);	

    String templateName = s.getTemplate().getTemplateName();
    String headerFile = templateName.toLowerCase() + "_header.jsp";
    String navigationFile = templateName.toLowerCase() + "_navigation.jsp";
    String sliderFile = templateName.toLowerCase() + "_slider.jsp";
    String sidebarFile = templateName.toLowerCase() + "_sidebar.jsp";
    String formFile = templateName.toLowerCase() + "_form.jsp";
    String footerFile = templateName.toLowerCase() + "_footer.jsp";

    TemplateDao templateDao = new TemplateDaoImpl();
    Template t = templateDao.getRelatedResourceUrlList(s.getTemplate());

    ArrayList<ResourceUrl> links = new ArrayList<>();
    ArrayList<ResourceUrl> scripts = new ArrayList<>();
    
    for(ResourceUrl rUrl : t.getResourceUrlList())
    {
        if(rUrl.getResourceTypeId() == 4) //links
        {
            links.add(rUrl);            
        }
        else if(rUrl.getResourceTypeId() == 5) //scipts
        {
            scripts.add(rUrl);
        }
    }
    
    request.setAttribute("siteInfo", s);	
    request.setAttribute("pageInfo", p);
    request.setAttribute("links", links);
    request.setAttribute("scripts", scripts);	
    
%>

<!DOCTYPE html> 
<html>
    <head>            
        <%@include file="meta_tags.jsp" %>
        <%@include file="styles.jsp" %>
        <%@include file="scripts.jsp" %>
    </head>
    	
    <c:choose>
        <c:when test="${templateName == 'MultiPurpose'}">
            <body class="home">	
                <div class="root">	
                    <jsp:include file="<%=headerFile%>" flush="true" /> 
                    
                    <% if( !("0".equals(p.getSliderId())) )  { %>
                        <jsp:include file="<%=sliderFile%>" flush="true" />  
                    <% } %>       
                    
                    <section class="content">                         
                        <%= p.getContent() %>                        
                        <br />                        
                        <% if( !("0".equals(p.getFormId())) )  { %>
                            <jsp:include page="<%=formFile%>" flush="true" />  
                        <% } %> 
                    </section>                     
                    <footer>
                    <jsp:include file="<%=footerFile%>" flush="true" />  
                    </footer>
                </div>
            </body>
        </c:when>
        <c:when test="${templateName == 'ECommerce'}">
            <body>
                <jsp:include file="<%=headerFile%>" flush="true" />  
                <jsp:include file="<%=navigationFile%>" flush="true" /> 
                <jsp:include file="<%=sliderFile%>" flush="true" /> 
                <jsp:include file="<%=sidebarFile%>" flush="true" />  
                <jsp:include page="<%=formFile%>" flush="true" /> 
                <jsp:include file="<%=footerFile%>" flush="true" />
            </body>
        </c:when>
        <c:when test="${templateName == 'Corporate'}">
            <body>
                <jsp:include file="<%=headerFile%>" flush="true" />  
                <jsp:include file="<%=navigationFile%>" flush="true" /> 
                <jsp:include file="<%=sliderFile%>" flush="true" /> 
                <jsp:include file="<%=sidebarFile%>" flush="true" />  
                <jsp:include page="<%=formFile%>" flush="true" /> 
                <jsp:include file="<%=footerFile%>" flush="true" />
            </body> 
        </c:when>
        <c:when test="${templateName == 'Parallax'}">
            <body>
                <jsp:include file="<%=headerFile%>" flush="true" />  
                <jsp:include file="<%=navigationFile%>" flush="true" /> 
                <jsp:include file="<%=sliderFile%>" flush="true" /> 
                <jsp:include file="<%=sidebarFile%>" flush="true" />  
                <jsp:include page="<%=formFile%>" flush="true" /> 
                <jsp:include file="<%=footerFile%>" flush="true" />
            </body>
        </c:when>
        <c:otherwise>
            <body>
                <%@include file="default_header.jsp" %>            
                <%@include file="default_navigation.jsp" %>         
                <%@include file="default_slider.jsp" %>                            
                <%@include file="default_sidebar.jsp" %>                                    
                <%@include file="default_form.jsp" %> 
                <%@include file="default_footer.jsp" %>
            </body>
        </c:otherwise>
    </c:choose>
</html>

