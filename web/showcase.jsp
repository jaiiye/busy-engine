<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<%!
	String makeDropDown(String name, String tableName, String selectedItem, int itemToShow, Connection connection, Statement statement, ResultSet rs)
	{
		String output = "";
		try
		{                           
			statement = connection.createStatement();
			//rs = statement.executeQuery("SELECT * FROM " + tableName + ";");
			rs = statement.executeQuery("SELECT * FROM item_sizes isz, ( SELECT * FROM item_sizes_available WHERE ItemId = " + selectedItem + ") As i WHERE i.ItemSizeId = isz.ItemSizeId;");
			output += "<SELECT NAME=\"" + name + "\">";
			while(rs.next())
			{     
				output += "<OPTION VALUE=\"" + rs.getString(1) + "\" " + (rs.getString(1).equals(selectedItem) ? "selected" : "") + ">" + rs.getString(itemToShow) + "</OPTION>";
			}   
			output += "</SELECT>";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}    
		return output;
	}
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %> 
        <%@include file="analytics.jsp" %>
        
        <script type="text/javascript" src="scripts/Functions.js"></script>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
		<link href="css/scroll.css" rel="stylesheet" type="text/css" />   
            
        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page
			droplinemenu.buildmenu("mydroplinemenu");
		</script> 
	</head>
<body>

    <div id="wrapper">
	<%@include file="store_header.jsp" %> 
  
<%
	Map<String, String> attributes = Database.getSiteAttributesMap("itemLayout");
	ArrayList<Item> items =  new ArrayList<Item>();
	int pageNumber = 0;
	int i = 1;
	int count = 0;
	int itemsPerPage = Integer.parseInt(attributes.get("ItemLayoutItemsPerPage"));
	int numberOfColumns = Integer.parseInt(attributes.get("ItemLayoutColumnsPerPage"));
	int itemsType = 1; //product items

	if(request.getParameter("page") == null)
	{
		pageNumber = 1;
	}
	else 
	{
	   pageNumber = Integer.parseInt(request.getParameter("page")); 
	}


	if(request.getParameter("category") != null)
	{
		count = Database.getItemsCount(3, request.getParameter("category"),itemsType);
		items = Database.getItemsByCategory(itemsType,request.getParameter("category"), itemsPerPage, pageNumber);
	}
	else if(request.getParameter("brand") != null)
	{
		count = Database.getItemsCount(4, request.getParameter("brand"),itemsType);
		items = Database.getItemsByBrand(itemsType,request.getParameter("brand"), itemsPerPage,pageNumber);
	}
	else if(request.getParameter("option") != null)
	{
		count = Database.getItemsCount(2, request.getParameter("option"),itemsType);
		items = Database.getItemsByOption(itemsType,request.getParameter("option"), itemsPerPage,pageNumber);
	}	
	else                
	{
		count = Database.getItemsCount(1, "", itemsType);
		items = Database.getItems(itemsType, itemsPerPage, pageNumber);
	}  
%>



     
       <div id="topbarNoSide"></div>
   
    <div id="contentNoSide">
        
        <div style="background: #e5e5e5; border: 1px solid #ccc; padding: 5px 10px; margin-bottom: 5px; width:75%; margin-left:auto; margin-right:auto; margin-top: 5px; text-align:center">
			<script type="text/javascript">
                MakeLinks(<%= count%>, <%= "\"showcase.jsp\""%>, <%= itemsPerPage%>, 1);
             </script>                   
		</div>

     <div id="productDesigns" align="center">
       <table cellpadding="5" cellspacing="0" border="0">
          <tbody>
            <tr>
			<%       
				i = 1;
				for(Item i1 : items)
				{
				%>
					<td valign="top">
						<SCRIPT type="text/javascript">
							TitleBrandPricesItemDiv("item<%= i %>", "<%=i1.getItemImageName()%>", "<%=i1.getItemId()%>", "<%=i1.getItemName()%>", "<%=i1.getItemMSRP()%>", "<%=i1.getItemPrice()%>", "<%=i1.getItemBrandName()%>", <%= attributes.get("ItemLayoutHeight") %>, <%= attributes.get("ItemLayoutMargin") %>, <%= attributes.get("ItemLayoutFooterWidth") %>,  <%= attributes.get("ItemLayoutDisplacement") %>, <%= attributes.get("ItemLayoutItemMsrpSize") %>,<%= attributes.get("ItemLayoutItemPriceSize") %>, '<%= attributes.get("ItemLayoutItemBorder") %>','<%= attributes.get("ItemLayoutBorderRadius") %>','<%= attributes.get("ItemLayoutTextShadow") %>','<%= attributes.get("ItemLayoutBoxShadow") %>','<%= attributes.get("ItemLayoutBackgroundColor") %>'); 
						</SCRIPT>
					</td>
					<% if (i%numberOfColumns == 0) { %></tr><tr><% } %>
				<%
				if (i > itemsPerPage) break;
				i++;
				} 
			%>

            </tr>
        </tbody>
      </table>
    </div>
  
        
           </div>  
	<div id="bottomNoSide"></div>
	<%@include file="store_footer.jsp" %> 
  
  </div>
  
</body>
</html>