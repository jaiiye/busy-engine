
<%@ page import="java.util.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="connection.jsp" %>   

        <style>
			.contacts
			 { 
				 background-color: #fafafa;
				 border: 1px #666 solid;
				 border-collapse: collapse;
				 border-spacing: 0px; 
			 }
			 
			
			td.contact
			{ 
				 border-bottom: 1px #666 dotted;
				 text-align: right;
				 font-family: Verdana, sans-serif, Arial;
				 font-weight: normal;
				 font-size: .7em;
				 color: #404040;
				 background-color: #fafafa;
				 padding-top: 4px;
				 padding-bottom: 4px;
				 padding-left: 8px;
				 padding-right: 0px; 
			 }
			 
			 td.value
			{ 
				 border-bottom: 1px #666 dotted;
				 text-align: left;
				 font-family: Verdana, sans-serif, Arial;
				 font-weight: bold;
				 font-size: .7em;
				 color: #404040;
				 background-color: #fafafa;
				 padding-top: 4px;
				 padding-bottom: 4px;
				 padding-left: 8px;
				 padding-right: 0px; 
			 }
		</style>

    <SCRIPT language=JavaScript>
    
function swapDiv(divName, divContent) 
{
	document.getElementById(divName).innerHTML = divContent;
}

function HideDIV(d) { document.getElementById(d).style.display = "none"; }
function DisplayDIV(d) { document.getElementById(d).style.display = "block"; }

function HideAllDIV() 
{ 
	if(document.getElementById('item1') != null)    document.getElementById('item1').style.display = "none";
	if(document.getElementById('item2') != null)  	document.getElementById('item2').style.display = "none"; 
	if(document.getElementById('item3') != null)	document.getElementById('item3').style.display = "none"; 
	if(document.getElementById('item4') != null)	document.getElementById('item4').style.display = "none"; 
	if(document.getElementById('item5') != null)	document.getElementById('item5').style.display = "none"; 
	if(document.getElementById('item6') != null)	document.getElementById('item6').style.display = "none"; 
	if(document.getElementById('item7') != null)	document.getElementById('item7').style.display = "none"; 
	if(document.getElementById('item8') != null)	document.getElementById('item8').style.display = "none"; 
	if(document.getElementById('item9') != null)	document.getElementById('item9').style.display = "none"; 
}

function ask() 
{ 
    var answer = confirm("Do you want to delete this Item?");    

    /* Question icon, Yes/No button. */
    /* 0 = error, 1 = OK, 2 = Cancel,*/

    if (answer == 1) { return (true);    }
    else             { return (false);   }
}

function MakeTable(divId, imageName, ItemId, ItemTitle, ListPrice, UrlPrice, AvailableSizes) 
{ 
	var websiteUrl = 'http://www.transitionsoft.com/';
	var d =  '<table cellspacing="0" cellpadding="0" align="center" border="0">';
	d += '<tbody>';
	d += '<tr>';
	d += '<td valign="top" align="middle" width="280"  height="400">';
	d += '<a href="' + websiteUrl + 'Store/items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '">';
	d += '<img alt="' + ItemTitle + '" src="' + websiteUrl + 'Store/items/' + imageName + '" width=333 height=500 border="0"></a>';
	d += '</td>';
	d += '</tr>';
	d += '<tr>';
	d += '<td valign=top>';
	d += '<a href="' + websiteUrl + 'Store/items.jsp?id=' + ItemId + '&action=view">' + ItemTitle + '</a>';
	d += '<br>&nbsp;List:&nbsp;$' + ListPrice;
	d += '<br />&nbsp;TS:&nbsp;<b>$' + UrlPrice + '</b>';
	d += '<br>&nbsp;<b>sizes available:&nbsp;</b>' + AvailableSizes;
	d += '</td>';
	d += '</tr>';
	d += '</tbody>';
	d += '</table>';
	
	document.write(d);
}

    </SCRIPT>
