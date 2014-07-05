
<%@ page import="java.text.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="header.jsp" %> 

<!-- Load TinyMCE -->
<script type="text/javascript" src="jscripts/tiny_mce/jquery.tinymce.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
    	$( "#tabs" ).tabs();
		
		$('textarea.tinymce').tinymce({
			// Location of TinyMCE script
			script_url : 'jscripts/tiny_mce/tiny_mce.js',

			// General options
			theme : "advanced",
			plugins : "autolink,lists,style,table,save,advhr,advimage,advlink,inlinepopups,insertdatetime,preview,media,searchreplace,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,advlist",

			// Theme options
			theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect,|,ltr,rtl,|,fullscreen",
			theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
			theme_advanced_buttons3 : "tablecontrols,|,advhr,removeformat,visualaid,|,sub,sup,charmap,|,media,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "bottom",
			theme_advanced_resizing : true,

			// Drop lists for link/image/media/template dialogs
			template_external_list_url : "lists/template_list.js",
			external_link_list_url : "lists/link_list.js",
			external_image_list_url : "lists/image_list.js",
			media_external_list_url : "lists/media_list.js"
		});
	});
	
		$(function() {
		var name = $( "#name" ),
			desc = $( "#desc" ),
			resettable = $( "#resettable" ),
			email = $( "#submissionEmail" ),
			action = $( "#submissionAction" ),
			method = $( "#submissionMethod" ),
			allFields = $( [] ).add(name).add(desc).add(resettable).add(email).add(action).add(method), tips = $( ".validateTips" );

		function updateTips( t ) {
			tips.text( t ).addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}

		function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " + min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}

		$( "#dialog-new-form-form" ).dialog({
			autoOpen: false,
			width: 750,
			height: 350,
			modal: true,
			buttons: {
				"Create a form": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( name, "name", 1, 100 );
					bValid = bValid && checkLength( desc, "description", 1, 65000 );
					bValid = bValid && checkLength( resettable, "resettable", 0, 1 );
					bValid = bValid && checkLength( email, "email", 5, 255 );
					bValid = bValid && checkLength( action, "action", 1, 255 );
					bValid = bValid && checkLength( method, "method", 3, 4 );

					if ( bValid ) {
						$( "#dialog-new-form-form" ).children('form').submit();
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
		
		
		$("#create-form").button().click(function() {
				$( "#dialog-new-form-form" ).dialog( "open" );
		});
	
			
		});
</script>
<!-- /TinyMCE -->



<%!
String makeDropDown(String name, String tableName, String selectedItem, int itemToShow, Connection connection, Statement statement, ResultSet rs)
{
    String output = "";
    try
    {                           
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName + ";");
        output += "<select name=\"" + name + "\" class=\"text ui-widget-content ui-corner-all\">";
        while(rs.next())
        {     
            output += "<option value=\"" + rs.getString(3) + "\" " + (rs.getString(3).equals(selectedItem) ? "selected" : "") + ">" + rs.getString(itemToShow) + "</option>";
        }   
        output += "</select>";
    }
    catch (Exception e) 
    {
        e.printStackTrace();
    }    
    return output;
}
%>  


<%
    //get Form information	
    ArrayList<Form> forms = null;
    forms = Database.getAllForms();
    for(Form f : forms)
    {
            f.setFields(Database.getFormFields(f.getFormId()));
    }
%>    

    <table border="0" cellpadding="0" cellspacing="" width="100%" align="center">
        <tr>   
            <td>
                 <DIV id="sidebar">
                     <DL>
                       <DT>Site Forms
                       <dd>
                       <button id="create-form">Create a New Form</button><br />
                       </dd>

                     </DL>
                 </DIV>
            </td>                   
        </tr>
    </table>
<br />

        
     <%
        for(Form f : forms)
        {
    %>

                              
<div id="formBox<%= f.getFormId()%>" title="<%= f.getFormName()%>" style="display:none"> 
    <div id="sidebar">
        <dl>
          <dt><input type="button" onClick="toggleVisibility('formBox<%= f.getFormId()%>')" value="x" /><%= f.getFormName()%>&nbsp; 
              <dd> 
 <table cellSpacing="5" cellPadding="0" width="100%">
       <tr> 
              <td valign="top" align="middle" width="30%" rowSpan="2">                    
                    <a name="<%= f.getFormId()%>"></a>
                    
                     <div style="height: 300px; width:100%; overflow: auto; padding: 5px;">                       
                     <table width="100%" cellpadding="0" cellspacing="2" border="0">
                            <tr>
                                <td>Name</td>
                                <td>Data Type</td>
                                <td>Label</td>
                                <td>Error Text</td>
                                <td>Validation Regex</td>
                                <td>Rank</td>
                                <td>Default Value</td>
                                <td>Optional</td>
                                <td>Options</td>
                                <td width="150px">Operations</td>
                            </tr>   
                        <%  
                            for(FormField ff : f.getFields() ) {                                              
                        %>
                            <form name="edit" action="edit.jsp?form=formField" method="post">
                                <input type=hidden name="formId" value="<%= f.getFormId()%>"/>
                                <input type=hidden name="fieldId" value="<%= ff.getFieldId()%>"/>
                                <tr>
                                    <td><input name="name" type="text" value="<%= ff.getFieldName()%>" size="30" class="text ui-widget-content ui-corner-all"/></td>
                                    <td>
                                       <%= makeDropDown("dataType", "form_field_type", ff.getFieldDataType(), 2, connection, statement, rs) %>
                                    </td>
                                    <td><input name="label" type="text" value="<%= ff.getFieldLabel()%>" size="30" class="text ui-widget-content ui-corner-all"/></td>
                                    <td><input name="errorText" type="text" value="<%= ff.getFieldErrorText()%>" size="50" class="text ui-widget-content ui-corner-all"/></td>
                                    <td><input name="validationRegex" type="text" value="<%= ff.getFieldValidationRegex()%>" size="50" class="text ui-widget-content ui-corner-all"/></td>
                                    <td><input name="rank" type="text" value="<%= ff.getFieldRank()%>" size="5" class="text ui-widget-content ui-corner-all"/></td>
                                    
			                        <td><input name="default" type="text" value="<%= ff.getFieldDefaultValue()%>" size="30" class="text ui-widget-content ui-corner-all"/></td>
                                    <td><input name="optional" type="checkbox" <%= ff.getFieldOptional()==0 ? "":"checked='checked'" %> value="<%= ff.getFieldOptional() %>" class="text ui-widget-content ui-corner-all" /></td>
                                    <td><input name="options" type="text" value="<%= ff.getFieldOptions().toString().replace('[', ' ').replace(']', ' ').trim()%>" size="60" class="text ui-widget-content ui-corner-all"/></td>
                                    <td>
                                        <a href="delete.jsp?form=form_field&id=<%= ff.getFieldId()%>"><img src="../images/delete.jpg" border="0" /></a>
                                        <input type="submit" value="Save"/>
                                    </td>
                                </tr>
                            </form>
                        <% } %>                        
                     
                     </table>
                   </div>
                        
                </td>
        </tr>

</table>
              </dd>
            </dl>
        </div>
	</div>
        
    <% 	
	} %>

<%
	if(request.getParameter("FormDeleteError") != null)
	{				
		%>
		<div align="center" style="color:#FF0000">
			<%= request.getParameter("FormDeleteError")%>
        </div>				
		<%
	}
%>


<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Form List</a></li>
  </ul>

  <div id="tabs-1">
    <div id="container">	  
        <div id="demo">  
          <table cellpadding="0" cellspacing="0" border="0" class="display" id="example" width="100%">
           <thead>
           <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Submission Email</th>
            <th>Submission Method</th>
            <th>Action</th>
            <th>Resettable</th>
            <th>Files</th>
            <th>Operations</th>
          </tr>                                  
           </thead>
            <tbody>  
    
            <%
                int k= 0;
                for(Form f : forms)
                {
            %>
                  <tr>
                    <td><%= f.getFormId()%></td>
                    <td><%= f.getFormName()%></td>
                    <td><%= f.getFormDescription()%></td>                
                    <td><%= f.getFormSubmissionEmail() %></td>
                    <td><%= f.getFormSubmissionMethod() %></td>
                    <td><%= f.getFormAction() %></td>
                    <td><%= f.isFormResettable() %></td>
                    <td><%= f.getFormFileUpload() %></td>
                    <td>                    
                        <button id="edit-form-parameters<%= f.getFormId()%>">Form</button>
                        <button id="edit-form<%= f.getFormId()%>">Fields</button>
                        <button id="delete-form<%= f.getFormId()%>">Delete</button>
                        <button id="add-form-field<%= f.getFormId()%>">Add Field</button>
                        
                        <script type="text/javascript">            
                        $("#edit-form<%= f.getFormId()%>").button({ icons: { primary: "ui-icon-pencil" } }).click(function() {                            
							//this toggles the visibility of item details on the page
							toggleVisibility('formBox<%= f.getFormId()%>');
								
							//this code allows embedding of additional info per row of the table
							//var nTr = $(this).parents('tr')[0];
							//dataTable.fnOpen( nTr, $("#formBox<%= f.getFormId()%>").html(), 'details' );
                         });
						 
						 $("#delete-form<%= f.getFormId()%>").button({ icons: { primary: "ui-icon-trash" } }).click(function() {                            
							window.location = 'delete.jsp?form=form&id=<%= f.getFormId()%>';
                         });
						 
						 
						 $("#add-form-field<%= f.getFormId()%>").button({ icons: { primary: "ui-icon-plus" } }).click(function() {                            						
							//this code allows embedding of additional info per row of the table
							var nTr = $(this).parents('tr')[0];
							dataTable.fnOpen( nTr, generateNewFormFieldSubmissionForm('<%= f.getFormId()%>', '<%= makeDropDown("dataType", "form_field_type", "text", 2, connection, statement, rs) %>'), 'details' );							
                         });
						 
						 $("#edit-form-parameters<%= f.getFormId()%>").button({ icons: { primary: "ui-icon-pencil" } }).click(function() {                            						
							//this code allows embedding of additional info per row of the table
							var nTr = $(this).parents('tr')[0];
							dataTable.fnOpen( nTr, generateEditFormParametersSubmissionForm('<%= f.getFormId()%>', '<%= f.getFormName()%>','<%= f.getFormDescription()%>','<%= f.getFormSubmissionEmail() %>'), 'details' );							
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

</div>




  

    
   
            
            
            

<div id="dialog-new-form-form" title="Create new form">
	<form method="post" action="add.jsp?form=form">
	<fieldset>
    <table width="100%" cellpadding="5" cellspacing="5">
    	<tr>
        	<td>
                <label for="name">Name</label><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                <label for="desc">Description</label><br /><textarea name="desc" id="desc" value="" class="text ui-widget-content ui-corner-all" rows="5" cols="65"  ></textarea><br />
                
			<p class="validateTips">All form fields are required.</p>
                
			</td>
        	<td>        
                <label for="submissionEmail">Submission Email</label>
                <input type="text" name="submissionEmail" id="submissionEmail" value="" class="text ui-widget-content ui-corner-all" />
                <label for="submissionAction">Submission Action</label>
                <input type="text" name="submissionAction" id="submissionAction" value="" class="text ui-widget-content ui-corner-all" />
                <label for="resettable">Resettable</label>                
                <select name="resettable" id="resettable" class="text ui-widget-content ui-corner-all" >
                	<option value="1" selected>True</option>				
                	<option value="0">False</option>
                </select><br /><br />
                <label for="submissionMethod">Method</label>
                <select name="submissionMethod" id="submissionMethod" class="text ui-widget-content ui-corner-all" >
                	<option value="post" selected>POST</option>				
                	<option value="get">GET</option>
                </select>
            </td>
        </tr>
    </table>
	</fieldset>
	</form>
</div>



<%@ include file="footer.jsp" %>

