<%
	// get slider images
	for(Image img : (ArrayList<Image>)pageStructure.get("sliderImages"))
	{
%>
	<a href="<%= img.getImageLinkUrl()%>"><img src="images-site/<%= img.getImageFileName() %>" border="0" alt="<%= img.getImageDescription() %>" ></a>	
<%
	}
%>