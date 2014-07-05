
<%@ page import="java.text.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="header.jsp" %> 

<link rel="stylesheet" type="text/css" href="../uploadify/uploadify.css">
<script type="text/javascript" src="../uploadify/jquery.uploadify3.2.min.js"></script>
<script type="text/javascript" src="jscripts/tiny_mce/jquery.tinymce.js"></script>
<script type="text/javascript">
    $().ready(function() {

        $("#tabs").tabs();

        $('textarea.tinymce').tinymce({
            // Location of TinyMCE script
            script_url: 'jscripts/tiny_mce/tiny_mce.js',
            // General options
            theme: "advanced",
            plugins: "autolink,lists,style,table,save,advhr,advimage,advlink,inlinepopups,insertdatetime,preview,media,searchreplace,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,advlist",
            // Theme options
            theme_advanced_buttons1: "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect,|,ltr,rtl,|,fullscreen",
            theme_advanced_buttons2: "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
            theme_advanced_buttons3: "tablecontrols,|,advhr,removeformat,visualaid,|,sub,sup,charmap,|,media,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking",
            theme_advanced_toolbar_location: "top",
            theme_advanced_toolbar_align: "left",
            theme_advanced_statusbar_location: "bottom",
            theme_advanced_resizing: true,
            // Drop lists for link/image/media/template dialogs
            template_external_list_url: "lists/template_list.js",
            external_link_list_url: "lists/link_list.js",
            external_image_list_url: "lists/image_list.js",
            media_external_list_url: "lists/media_list.js"
        });
    });

    $(function() {
        var name = $("#name"),
                desc = $("#desc"),
                listPrice = $("#listPrice"),
                price = $("#price"),
                adjust = $("#adjust"),
                seotitle = $("#seotitle"),
                seodesc = $("#seodesc"),
                seokeywords = $("#seokeywords"),
                brandName = $("#brand_name"),
                brandDesc = $("#brand_desc"),
                categoryName = $("#category_name"),
                shortDesc = $("#shortDesc"),
                allFields = $([]).add(name).add(desc).add(listPrice).add(price).add(adjust).add(seotitle).add(seodesc).add(seokeywords).add(brandName).add(brandDesc).add(categoryName).add(shortDesc), tips = $(".validateTips");

        function updateTips(t) {
            tips.text(t).addClass("ui-state-highlight");
            setTimeout(function() {
                tips.removeClass("ui-state-highlight", 1500);
            }, 500);
        }

        function checkLength(o, n, min, max) {
            if (o.val().length > max || o.val().length < min) {
                o.addClass("ui-state-error");
                updateTips("Length of " + n + " must be between " + min + " and " + max + ".");
                return false;
            } else {
                return true;
            }
        }

        function checkRegexp(o, regexp, n) {
            if (!(regexp.test(o.val()))) {
                o.addClass("ui-state-error");
                updateTips(n);
                return false;
            } else {
                return true;
            }
        }

        $("#dialog-new-item-form").dialog({
            autoOpen: false,
            width: 750,
            height: 570,
            modal: true,
            buttons: {
                "Create an item": function() {
                    var bValid = true;
                    allFields.removeClass("ui-state-error");

                    bValid = bValid && checkLength(name, "name", 1, 255);
                    bValid = bValid && checkLength(desc, "description", 1, 65000);
                    bValid = bValid && checkLength(listPrice, "list Price", 1, 9);
                    bValid = bValid && checkLength(price, "price", 1, 9);
                    bValid = bValid && checkLength(adjust, "adjust", 1, 9);
                    bValid = bValid && checkLength(seotitle, "seo title", 1, 245);
                    bValid = bValid && checkLength(seodesc, "seo desc", 1, 245);
                    bValid = bValid && checkLength(seokeywords, "seo keywords", 1, 245);
                    bValid = bValid && checkRegexp(listPrice, /^(\d*\.\d{1,2}|\d+)$/, "Price field only allows :  0-9 and decimal point");
                    bValid = bValid && checkRegexp(price, /^(\d*\.\d{1,2}|\d+)$/, "Price field only allows :  0-9 and decimal point");
					
                    bValid = bValid && checkLength(desc, "shortDesc", 1, 255);

                    if (bValid) {
                        $("#dialog-new-item-form").children('form').submit();
                        $(this).dialog("close");
                    }
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                allFields.val("").removeClass("ui-state-error");
            }
        });

        $("#dialog-new-brand-form").dialog({
            autoOpen: false,
            width: 350,
            height: 280,
            modal: true,
            buttons: {
                "Create a brand": function() {
                    var bValid = true;
                    allFields.removeClass("ui-state-error");

                    bValid = bValid && checkLength(brandName, "name", 1, 100);
                    bValid = bValid && checkLength(brandDesc, "description", 1, 65000);


                    if (bValid) {
                        $("#dialog-new-brand-form").children('form').submit();
                        $(this).dialog("close");
                    }
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                allFields.val("").removeClass("ui-state-error");
            }
        });


        $("#dialog-new-category-form").dialog({
            autoOpen: false,
            width: 350,
            height: 220,
            modal: true,
            buttons: {
                "Create a category": function() {
                    var bValid = true;
                    allFields.removeClass("ui-state-error");

                    bValid = bValid && checkLength(categoryName, "name", 1, 100);

                    if (bValid) {
                        $("#dialog-new-category-form").children('form').submit();
                        $(this).dialog("close");
                    }
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                allFields.val("").removeClass("ui-state-error");
            }
        });


        $("#create-item").button().click(function() {
            $("#dialog-new-item-form").dialog("open");
        });


        $("#create-brand").button().click(function() {
            $("#dialog-new-brand-form").dialog("open");
        });

        $("#create-category").button().click(function() {
            $("#dialog-new-category-form").dialog("open");
        });

    });
</script>


<%
	ArrayList<Item> items = null;

	if (request.getParameter("category") != null)
	{
		items = Database.getAllItemsWithInfoByType(1, null, request.getParameter("category"));
	} 
	else
	{
		items = Database.getAllItemsWithInfoByType(1, null, null);
	}
%>

<h2> Product Items </h2>

<table border="0" cellpadding="0" cellspacing="" width="100%">
    <tr>
        <td width="50%" valign="top">
            <div id="sidebar">
                <dl>
                    <dt>Item Filter 
                    <dd>   
                        <form method="post" action="items.jsp">                                             
                            <table width="90%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td align="right">By Category: </td>
                                    <td>
                                        <select name="category">
                                           <%= Database.generateSelectOptionsFromTableAndColumn("category", request.getParameter("category"), 2)%>
                                        </select>
                                        <input type="submit" value="View Items"/> <a href="items.jsp">View All Items</a>
                                    </td>
                                </tr>
                            </table>
                        </form>                                    
                    </dd>
                </dl>
            </div>
        </td>

        <td width="50%">
            <div id="sidebar">
                <dl>
                    <dt>Operations
                    <dd>
                        <button id="create-item">New Item</button> 
                        <button id="create-brand">New Brand</button> 
                        <button id="create-category">New Category</button><br />
                    </dd>
                </dl>
            </div>
        </td>                   
    </tr>
</table>

<%  for (Item item : items)
    {  %>
<div id="itemBox<%= item.getItemId()%>" title="<%= item.getItemName()%>" style="display:<%=(request.getParameter("id") == null ? "none" : (item.getItemId().equals(request.getParameter("id")) ? "block" : "none"))%>"> 
    <div id="sidebar">
        <dl>
            <dt><input type="button" onClick="toggleVisibility('itemBox<%= item.getItemId()%>')" value="x" /><%= item.getItemName()%>
            <dd> 
                <table cellSpacing="5" cellPadding="0" width="100%">
                    <tr> 
                        <td valign="top" align="middle" width="30%" rowSpan="2">                    
                            <a name="<%= item.getItemId()%>"></a>
                            <div style="height: 450px; width:100%; overflow: auto; padding: 5px;">
                                <%									
                                    for(Image i : Database.getItemImages(item.getItemId()))
                                    {
                                %>
                                <div>
                                    <a href="../items/<%= i.getImageFileName()%>">
                                        <img class="shadow" src="../items/sm_<%= i.getImageFileName()%>" border="0" />
                                    </a>
                                    <br>
                                    <a href="delete.jsp?form=item_images&ItemId=<%= item.getItemId()%>&ItemImageId=<%= i.getImageId()%>">
                                        <img src="../images/delete.jpg" border="0" />
                                    </a>
                                </div>
                                <% }%>                        
                            </div>
                        </td>
                        <td valign="top" width="45%">
                            <table width="100%" cellpadding="2" cellspacing="2" border="0">
                                <form name="edit" action="edit.jsp?form=item" onSubmit="return validate();" method="post">
                                <input type="hidden" name="id" value="<%= item.getItemId()%>"/>
                                    <tr>
                                        <td>
                                            <label for="desc">Ite m Id(<b><%= item.getItemId()%></b>) Description:</label>
                                            <textarea name="desc" rows="10" cols="50" class="tinymce">
												<%= item.getItemDescription()%>
                                            </textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td>
                                                    	<label for="name">Name:</label>
                                                        <input name="name" type="text" value="<%= item.getItemName()%>" size="50" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                    <td>
                                                        <label for="brandId">Brand:</label>
                                                        <select name="brandId" class="text ui-widget-content ui-corner-all">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", item.getItemBrandId(), 2)%>
                                                        </select>
                                                    </td>
                                                </tr>
                                                 <tr>
                                                    <td colspan="2">
                                                    	<label for="shortDesc">Short Description:</label>
                                                        <input name="shortDesc" type="text" value="<%= item.getItemShortDescription()%>" size="50" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                	<td>
                                                    	<label for="listPrice">List Price:</label>
                                                        <input name="listPrice" type="text" value="<%= item.getItemMSRP()%>" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                    <td>
                                                    	<label for="seotitle">SEO Title:</label>
                                                        <input name="seotitle" type="text" value="<%= item.getItemSeoTitle()%>" size="45" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                	<td>
                                                    	<label for="price">Price:</label>
                                                        <input name="price" type="text" value="<%= item.getItemPrice()%>" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                	<td>
                                                		<label for="seodesc">SEO Description:</label>
                                                        <input name="seodesc" type="text" value="<%= item.getItemSeoDescription()%>" size="45" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                	<td>
                                                		<label for="adjust">Adjustment:</label>
                                                        <input name="adjust" type="text" value="<%= item.getItemPriceAdjustment()%>" class="text ui-widget-content ui-corner-all"/>
                                                	</td>
                                                    <td>
                                                    	<label for="seokeywords">SEO Keywords:</label>
                                                        <input name="seokeywords" type="text" value="<%= item.getItemSeoKeywords()%>" size="45" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                	<td colspan="2">
                                                        <input type="submit" value="Save Changes" class="text ui-widget-content ui-corner-all"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    </form>
                                    <tr>
                                        <td align="right">
                                            <table width="100%" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td align="center"> 
                                                    </td>  
                                                    <td align="center">                                                    
                                                        <form name="delete" action="delete.jsp?form=item" onSubmit="return ask();" method="post">
                                                            <input type=hidden name="id" value="<%= item.getItemId()%>"/>
                                                            <input type="submit" value="Delete Item"/>
                                                        </form>
                                                    </td>                       
                                                    <td align="center">     
                                                        <input type="file" name="image_upload" id="image_upload<%= item.getItemId()%>">
                                                        <script type="text/javascript">
                                                            $(function() {
                                                                $('#image_upload<%= item.getItemId()%>').uploadify({
                                                                                        'buttonText': 'Add Images...',
                                                                'formData': {'id': '<%= item.getItemId()%>'},
                                                                'fileTypeExts': '*.gif; *.jpg; *.png',
                                                                'swf': 'uploadify.swf',
                                                                'uploader': 'upload2.jsp',
                                                                'fileSizeLimit': '3MB',
                                                                'uploadLimit': 15,
                                                                'fileTypeDesc': 'Image Files...',
                                                                'method': 'post',
                                                                'onQueueComplete': function(file, data,response) {
                                                                        window.location = "index.jsp?id=<%= item.getItemId()%>";
                                                                        //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
                                                                        }
                                                                });
                                                            });
                                                        </script>
                                                    </td>   
                                                    <td align="center">     
                                                        <input type="file" name="file_upload" id="file_upload<%= item.getItemId()%>">
                                                        <script type="text/javascript">
                                                            $(function() {
                                                                $('#file_upload<%= item.getItemId()%>').uploadify({
                                                                                'buttonText': 'AddFiles...',
                                                                    'formData': {'id': '<%= item.getItemId()%>'},
                                                                    'swf': 'uploadify.swf',
                                                                    'uploader': 'upload-file-for-item.jsp',
                                                                    'fileSizeLimit': '30MB',
                                                                    'uploadLimit': 15,
                                                                    'fileTypeDesc': 'All Files...',
                                                                    'method': 'post',
                                                                                'onQueueComplete': function(file, data,response) {
                                                                        window.location = "index.jsp?id=<%= item.getItemId()%>";
                                                                    }
                                                                });
                                                            });
                                                        </script>
                                                    </td>    
                                                    <td align="center">      
                                                        <form name="code" action="../code.jsp" method="post">
                                                            <input type=hidden name="Id" value="<%= item.getItemId()%>"/>
                                                            <input type="submit" value="Generate Code"/>
                                                        </form>
                                                    </td>                      
                                                </tr>
                                            </table>  
                                        </td>
                                    </tr>
                                </table>
                                <hr> 
                        </td>  
                        <td valign="top" width="1%" bgcolor="#CCC">
                            <img src="../images/blank.gif" width="1" height="1" alt="Vertical separator" />
                        </td>
                        <td valign="top" width="*">
                            <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                <tr>
                                	<td align="center" colspan="4">
                                    	<h4>Options</h4>
                                    </td>
                                </tr>
                                <tr><td align="center">&nbsp;</td>
                                    <td align="center">Opt</td><td align="center">Qty</td><td align="center">Availability</td></tr>

                                <%  for (Option o : item.getItemInfo().getItemOptions())
                                    {
                                %>
                                <tr>
                                    <td align="center">
                                        <a href="delete.jsp?form=item_option_available&ItemId=<%= item.getItemId()%>&ItemOptionId=<%= o.getOptionId()%>">
                                            <img src="../images/delete.jpg" border="0" />
                                        </a>
                                    </td>
                                    <td align="center">
                                        <%= o.getOptionType()%> 
                                    </td>
                                    <td align="center">
                                        <%= o.getOptionAvailableQuantity()%>                               
                                    </td>
                                    <td align="center">
                                        <select name="availability" class="text ui-widget-content ui-corner-all">
                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_availability", o.getOptionAvailability(), 2) %>
                                        </select>
                                    </td>
                                </tr>                                        
                                <% }%>                                     
                                <tr>
                                	<td align="center" colspan="4">
                                    	<hr>
                                	</td>
                                </tr>
                                <form name="add" action="add.jsp?form=item_options_available" onSubmit="return validate();" method="post">
                                    <input type=hidden name="ItemId" value="<%= item.getItemId()%>"/>
                                    <tr>
                                        <td align="center"><input type="image" src="../images/insert.png" border="0" /></td>
                                        <td align="center"> 
                                            <select name="optionId" class="text ui-widget-content ui-corner-all">
                                                <%= Database.generateSelectOptionsFromTableAndColumn("item_option", "xx", 2)%>
                                            </select>
                                        </td>
                                        <td align="center"><input style="width:50" name="quantity" type="text" value="" width="50"/></td>
                                        <td align="center">
                                            <select name="availability" class="text ui-widget-content ui-corner-all">
                                                <%= Database.generateSelectOptionsFromTableAndColumn("item_availability", "xx", 2)%>
                                            </select>
                                        </td>
                                    </tr>
                                </form>
                            </table>
                            <hr size="3" color="#000000">
                            <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                <tr>
                                	<td align="center" colspan="2">
                                    	<h4>Categories</h4>
                                    </td>
                                </tr>

                                <%
                                    for (Category c : item.getItemInfo().getItemCategories())
                                    {
                                %>
                                <tr>
                                    <td align="center">
                                        <%= c.getCategoryName()%>
                                    </td>
                                    <td align="center">
										<a href="delete.jsp?form=item_category&ItemId=<%= item.getItemId()%>&CategoryId=<%= c.getCategoryId()%>">
                                            <img src="../images/delete.jpg" border="0" />
                                        </a>
                                    </td>
                                </tr>                                        
                                <% }%>                                     
                                <tr>
                                	<td align="center" colspan="4">
                                    	<%
                                            if(request.getParameter("CategoryAddMsg") != null)
                                            {				
                                                %>
                                                <div align="center" style="color:#FF0000">
                                                        <%= request.getParameter("CategoryAddMsg")%>
                                                </div>				
                                                <%
                                            }
                                        %>

                                        <%
                                            if(request.getParameter("CategoryDeleteMsg") != null)
                                            {				
                                                %>
                                                <div align="center" style="color:#FF0000">
                                                    <%= request.getParameter("CategoryDeleteMsg")%>
                                                </div>				
                                                <%
                                            }
                                        %>
                                    	<hr>
                                    </td>
                                </tr>


                                <form name="add" action="add.jsp?form=item_categories" onSubmit="return validate();" method="post">
                                    <input type=hidden name="ItemId" value="<%= item.getItemId()%>"/>
                                    <tr>
                                        <td align="center"><select name="CategoryId" class="text ui-widget-content ui-corner-all">
                                                <%= Database.generateSelectOptionsFromTableAndColumn("category", "xx", 2)%>
                                            </select></td>
                                        <td align="center"> 
                                            
                                            <input type="image" src="../images/insert.png" border="0" />
                                        </td>
                                    </tr>

                                </form>
                            </table>  
                            <hr size="3" color="#000000">
                            <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                <tr><td align="center" colspan="2"><h4>Files</h4></tr>

                                <%
                                    for (File f : item.getItemInfo().getItemFiles())
                                    {
                                %>
                                <tr>
                                    <td align="center">
                                        <a href="delete.jsp?form=item_files&ItemId=<%= item.getItemId()%>">
                                            <img src="../images/delete.jpg" border="0" />
                                        </a>
                                    </td>
                                    <td align="center"><%= f.getFileName()%></td>
                                </tr>                                        
                                <% }%>                                     
                                <tr><td align="center" colspan="4"><hr></tr>

                            </table>
                        </td>    
                    </tr>
                </table>
            </dd>
        </dl>
    </div>
</div>
<%  }  %>

<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Item List</a></li>
        <li><a href="#tabs-2">Item Images</a></li>
    </ul>

    <div id="tabs-1">
        <div id="container">	  
            <div id="demo">  
                <table cellpadding="0" cellspacing="0" border="0" class="display" id="example" width="100%">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Brand</th>
                            <th>List Price</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>                                  
                    </thead>
                    <tbody>  

                        <%
                            int k = 0;
                            NumberFormat formatter = NumberFormat.getCurrencyInstance();
                            for (Item i : items)
                            {
                                String msrpString = formatter.format(i.getItemMSRP());
                                String costString = formatter.format(i.getItemPrice());
                        %>
                        <tr>
                            <td><%= i.getItemId()%></td>
                            <td><%= i.getItemName()%></td>
                            <td><%= i.getItemBrandName()%></td>                
                            <td><%= msrpString%></td>
                            <td><%= costString%></td>
                            <td>
                                <button id="edit-item<%= i.getItemId()%>">Edit</button>
                                <script type="text/javascript">
									$("#edit-item<%= i.getItemId()%>").button().click(function() {

										//this toggles the visibility of item details on the page
										toggleVisibility('itemBox<%= i.getItemId()%>');

										//jQuery('#itemBox<%= i.getItemId()%>').toggle( function() {
										//	jQuery('#itemBox<%= i.getItemId()%>').css("display", "none");
										//}, function () {
										//	jQuery('#itemBox<%= i.getItemId()%>').css("display", "block");
										//}
										//);

										//$( '#itemBox<%= i.getItemId()%>' ).dialog({
										//								width: '85%',
										//								modal: true ,
										//								close: function() {
										//									$("#itemBox<%= i.getItemId()%>").removeClass( "ui-dialog-content" );
										//									$("#itemBox<%= i.getItemId()%>").removeClass( "ui-widget-content" );
										//									document.getElementById("itemBox<%= i.getItemId()%>").style.display = 'none';
										//									//$("#itemBox<%= i.getItemId()%>").removeClass();
										//								}
										//						});

										//this code allows embedding of additional info per row of the table
										//var nTr = $(this).parents('tr')[0];
										//dataTable.fnOpen( nTr, $("#itemBox<%= i.getItemId()%>").html(), 'details' );
									});
                                </script>
                            </td>
                        </tr>

                        <%
                                k++;
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div id="tabs-2">
        <div style="width:100%; overflow:scroll; height: 305px;">
            <%
                for (Item item : items)
                {
            %><img class="shadow" src="../items/sm_<%= item.getItemImageName()%>" border="1" onClick="toggleVisibility('itemBox<%= item.getItemId()%>')"  />
            <%  }%>
        </div>
    </div>
</div>

<div id="dialog-new-item-form" title="Create new item">
    <form method="post" action="add.jsp?form=item">
        <fieldset>
            <table width="100%" cellpadding="5" cellspacing="5">
                <tr>
                    <td>
                        <label for="name">Name</label><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                        <label for="shortDesc">Short Description</label><input type="text" name="shortDesc" id="shortDesc" class="text ui-widget-content ui-corner-all" />
                        <label for="desc">Description</label><br /><textarea name="desc" id="desc" value="" class="text ui-widget-content ui-corner-all" rows="6" cols="65"  ></textarea><br /><br />
                    </td>
                    <td> 
                    
                        <label for="listPrice">List Price</label>
                        <input type="text" name="listPrice" id="listPrice" value="" class="text ui-widget-content ui-corner-all" />
                        <label for="price">Price</label>
                        <input type="text" name="price" id="price" value="" class="text ui-widget-content ui-corner-all" />
                        <label for="adjust">Adjustment</label>
                        <input type="text" name="adjust" id="adjust" value="" class="text ui-widget-content ui-corner-all" />
                        <br /> <br />
                        <label for="brand">Brand</label>
	                    <select name="brandId" class="text ui-widget-content ui-corner-all">
                            <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", "xx", 2)%>
                        </select>  
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label for="seotitle">SEO Title</label>
                        <input type="text" name="seotitle" id="seotitle" value="" class="text ui-widget-content ui-corner-all" />
                        <label for="seodesc">SEO Description</label>
                        <input type="text" name="seodesc" id="seodesc" value="" class="text ui-widget-content ui-corner-all" />
                        <label for="seokeywords">SEO Keywords</label>
                        <input type="text" name="seokeywords" id="seokeywords" value="" class="text ui-widget-content ui-corner-all" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p class="validateTips">All form fields are required.</p>
</div>

<div id="dialog-new-brand-form" title="Create new product brand">
    <p class="validateTips">All form fields are required.</p>

    <form method="post" action="add.jsp?form=item_brands">
        <fieldset>
            <label for="name">Name</label>
            <input type="text" name="name" id="brand_name" class="text ui-widget-content ui-corner-all" />
            <label for="desc">Description</label>
            <input type="text" name="desc" id="brand_desc" value="" class="text ui-widget-content ui-corner-all" />
        </fieldset>
    </form>
</div>

<div id="dialog-new-category-form" title="Create new product category">
    <p class="validateTips">All form fields are required.</p>

    <form method="post" action="add.jsp?form=categories">
        <fieldset>
            <label for="category_name">Name</label>
            <input type="text" name="name" id="category_name" class="text ui-widget-content ui-corner-all" />
        </fieldset>
    </form>
</div>
<%@ include file="footer.jsp" %>

