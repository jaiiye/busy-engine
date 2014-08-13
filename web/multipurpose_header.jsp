<%@ page import="java.util.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    Map<String, String> attributes = Database.getSiteAttributesMap("siteLayout");
    String headerType = attributes.get("MainHeaderType");	
	
    HashMap pageStructure = Database.getSiteStructure();
    request.setAttribute("pageStructure", pageStructure);
%>

<header class="<%=attributes.get("MainMenuStyle")%>">  
    <section class="top">
        <div>
            <p>Call us: 1-123-123-1234 | E-mail: info@example.com</p>
             <nav class="utils">
                <ul>
                    <li><a href="cart.jsp" class="cart">Cart</a></li>
                    <li><a href="members/index.jsp" class="login">Login</a></li>
                    <li><a href="#" class="lang">Languages</a>
                        <ul>
                            <li><a href="english.jsp">English</a></li>
                        </ul>
                    </li>
                    
                    <li><a href="info2.jsp?id=1">Contact</a></li>
                </ul>
                <select id="top-nav" name="sec-nav">
                    <option value="#" selected="selected">Top Menu</option>                    
                    <option value="cart.jsp" >Cart</option>
                    <option value="members/index.jsp" >Login</option>
                    <option value="#" >English</option>
                </select>
            </nav>
        </div>
    </section>
    <section class="main-header">
        <p class="title">        
            <%
				// get title images
				for (Image img : (ArrayList<Image>)pageStructure.get("headerImages"))
				{
			%>
					<a href="<%= img.getImageLinkUrl()%>"><img src="images-site/<%= img.getImageFileName()%>" border="0"  alt="<%= img.getImageDescription()%>"></a>	
			<%
				}
				//if the strings are not available then load them from the database only once!
				if (application.getAttribute("index-header-link-1") == null)
				{
					for (AbstractMap.SimpleEntry e : Database.getLanguageStrings())
					{
						application.setAttribute((String) e.getKey(), e.getValue());
						System.out.println("setting Application attribute: (" + e.getKey() + ":" + e.getValue() + ")");
					}
				}
		
			%> 
        </p>
        
        <% if(headerType.equals("full")) { %>
            <form method="get" class="searchform" action="./header09.html">
                <fieldset>
                    <input type="text" value="" name="s" id="s" placeholder="Search">
                    <button type="submit" id="searchsubmit" value="Search"></button>
                </fieldset>
            </form>
        <% } %>
        
        <nav class="social">
            	<%= (String)pageStructure.get("socialLinks") %>
        </nav>
        
	<% if(headerType.equals("compact")) { %>
            <nav class="mainmenu">
            	<%= (String)pageStructure.get("pageNavigation") %>
            </nav> 
            <div class="clear"></div>
        <% } %>
        
    </section>
    
    <% if(headerType.equals("full")) { %>
        <nav class="mainmenu">
            <%= (String)pageStructure.get("pageNavigation") %>			
        </nav>
        <div class="clear"></div>
    <% } %>
</header>