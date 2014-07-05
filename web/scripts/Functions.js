







function generateUserRoleDiv(userName)
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>Edit User';
		d += '			<dd>';
		d += '			  <form method="post" action="add.jsp?form=addUserRole">    ';                                         
		d += '				<table width="70%" cellpadding="0" cellspacing="0" border="0">';
		d += '			  		<tr><td align="right" width="40%">User Name: </td><td width="*"><input name="name" type="text" value="' + userName + '"/></td></tr>';
		d += '			  		<tr><td align="right">Role: </td><td>';	
		d += '						<select name="role">';
		d += '                             <option value="admin" selected>Administrator</option>';
		d += '                             <option value="member">Member</option>';
		d += '                             <option value="manager">Manager</option>';
		d += '                      </select></td></tr>';																		
		d += '			  		<tr><td align="right"><td><input type="submit" value="Add Role"/><input type="button" value="Cancel" onClick="closeInput();"/></td></tr>';
		d += '				</table>';
		d += '				</form> ';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}



function generateNewDesignDiv(dropDownMarkup)
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>Add New Item';
		d += '			<dd>';
		d += '			  <form method="post" action="add.jsp?form=itemDesign">    ';                                         
		d += '				<table width="70%" cellpadding="0" cellspacing="0" border="0">';
		d += '			  		<tr><td align="right" width="40%">Name: </td><td width="*"><input name="name" type="text" value=""/></td></tr>';
		d += '			  		<tr><td align="right" valign="top">Description: </td><td><textarea name="desc" rows="5" cols="40"></textarea></td></tr>';
		d += '			  		<tr><td align="right">Brand: </td><td>' + dropDownMarkup + '</td></tr>';
		d += '			  		<tr><td align="right">SEO Title: </td><td><input name="seotitle" type="text" value="" size="35"/></td></tr> ';                   
		d += '			  		<tr><td align="right">SEO Description: </td><td><input name="seodesc" type="text" value="" size="35"/></td></tr>   ';                 
		d += '			  		<tr><td align="right">SEO Keywords: </td><td><input name="seokeywords" type="text" value=""  size="35"/></td></tr>';
		d += '			  		<tr><td align="right">Type: </td><td>';	
		d += '						<select name="type">';
		d += '                             <option value="5" selected>New Contest</option>';
		d += '                             <option value="4">Completed Contest</option>';
		d += '                      </select></td></tr>';																		
		d += '			  		<tr><td align="right"><td><input type="submit" value="Add New Item"/><input type="button" value="Cancel" onClick="closeInput();"/></td></tr>';
		d += '				</table>';
		d += '				</form> ';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}

var myEditor;  //global variable to hold the textbox


function submitButton()
{        
    myEditor.saveHTML();       
}

function checkSelected(value, passed)
{
	if(value == passed) return ' selected ';
	else return '';
}

function generateExistingBlogPostDiv(title, featured, bodyText, tags, rating, postId, postImage, dropDownMarkup)
{

	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>Edit Post';
		d += '			<dd>';
		d += '				<form method="post" action="edit.jsp?form=blogPost">';
		d += '					<table width="100%" cellpadding="0" cellspacing="5" border="0">'; 
		d += '						<input name="PostId" type="hidden" value="' + postId + '"> ';                                             
		d += '						<tr>';
		d += '							<td align="right" width="25%">Title: </td>';
		d += '							<td  width="75%"><input name="PostTitle" type="text" value="' + title + '" size="70"/></td>';
		d += '						</tr>    ';
		d += '						<tr>';
		d += '							<td align="right" width="25%">Image: </td>';
		d += '							<td  width="75%"><input name="PostImage" type="text" value="' + postImage + '" size="70"/></td>';
		d += '						</tr>    ';
		d += '						<tr><td align="right">Blog: </td><td>' + dropDownMarkup + '</td></tr>'; 
		d += '						<tr><td align="right" valign="top"><strong>Body:</strong></td><td>';
		d += '							<textarea id="editor" name="body" rows="15" cols="65">'+ bodyText + '</textarea>   ';     
		d += '						</td></tr>   ';     
		d += '						<tr><td align="right">Tags: </td><td><input name="PostTags" type="text" value="'+ tags + '"  size="90"/></td></tr> ';                                                             
		d += '						<tr><td align="right">Postrating: </td><td><input name="PostRating" type="text" value="'+ rating + '"/></td></tr> ';       
		d += '						<tr><td align="right"><td><input type="button" value="Cancel" onClick="closeInput();" /><input type="submit" value="Save" onclick="javascript: myEditor.saveHTML();" /></td></tr>';
		d += '					</table>';
		d += '				</form>';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}


function generateEditAttributeForm(id, key, value, type)
{
	var d  = '<form method="post" action="edit.jsp?form=siteAttribute">';
		d += '<input name="id" type="hidden" value="' + id + '"> '; 
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';
		d += '			<label for="key">Key:</label> <input type="text" name="key" id="key" value="' + key + '" class="text ui-widget-content ui-corner-all" /><br/>';
		d += '			<label for="value">Value:</label> <input type="text" name="value" id="value" value="' + value + '" class="text ui-widget-content ui-corner-all" /><br/>';
		d += '			<label for="type">Type:</label> ';
		d += '			<select name="type" id="type" class="text ui-widget-content ui-corner-all" >';
		d += '				<option value="itemLayout" ' + ((type == "itemLayout") ? "selected": "") + '>Item Layout</option>	';			
		d += '				<option value="siteLayout" ' + ((type == "siteLayout") ? "selected": "") + '>SiteLayout</option>';	
		d += '			</select><br/>';
		d += '			<input type="submit" name="submit" value="Save Attribute" />';
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}

function generateNewFormFieldSubmissionForm(formId, dataTypeSelectMarkup)
{
	var d  = '<form method="post" action="add.jsp?form=form-field">';
		d += '<input name="formId" type="hidden" value="' + formId + '"> '; 
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';
		d += '			<label for="name">Name</label><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />';
		d += '			<label for="options">Options</label><br /><textarea name="options" id="options" value="" class="text ui-widget-content ui-corner-all" rows="6" cols="65"  ></textarea><br /><br />';
		d += '			<label for="optional">Optional</label> &nbsp;<input type="checkbox" name="optional" id="optional" value="1" />';
		d += '		</td>';
		d += '		<td>';
		
		d += '			<label for="label">Label</label>';
		d += '			<input type="text" name="label" id="label" value="" class="text ui-widget-content ui-corner-all" />';
		
		d += '			<label for="defaultValue">Default Value</label>';
		d += '			<input type="text" name="defaultValue" id="defaultValue" value="" class="text ui-widget-content ui-corner-all" /><br />'
		
		d += '			<label for="errorText">Error Text</label>';
		d += '			<input type="text" name="errorText" id="errorText" value="" class="text ui-widget-content ui-corner-all" /><br />';
		
		d += '			<label for="dataType">Data Type</label>' + dataTypeSelectMarkup;
		
		d += '			<label for="validationRegex">Validation Regex</label>';
		d += '			<select name="validationRegex" id="validationRegex" class="text ui-widget-content ui-corner-all" >';
		d += '				<option value="[A-Za-z]+" selected>Characters</option>	';			
		d += '				<option value="[0-9A-Za-z]+">Characters and Numbers</option>';	
		d += '				<option value="[0-9]+">Numbers</option>';
		d += '			</select>';
		
		d += '			<label for="rank">Rank</label>';
		d += '			<select name="rank" id="rank" class="text ui-widget-content ui-corner-all" >';
		d += '				<option value="1" selected>1</option>';			
		d += '				<option value="2">2</option>';			
		d += '				<option value="3">3</option>';			
		d += '				<option value="4">4</option>';			
		d += '				<option value="5">5</option>';			
		d += '				<option value="6">6</option>';			
		d += '				<option value="7">7</option>';			
		d += '				<option value="8">8</option>';		
		d += '				<option value="9">9</option>';			
		d += '				<option value="10">10</option>';			
		d += '				<option value="11">11</option>';			
		d += '				<option value="12">12</option>';			
		d += '				<option value="13">13</option>';			
		d += '				<option value="14">14</option>';			
		d += '				<option value="15">15</option>';		
		d += '				<option value="16">16</option>';			
		d += '				<option value="17">17</option>';			
		d += '				<option value="18">18</option>';			
		d += '				<option value="19">19</option>';			
		d += '				<option value="20">20</option>';			
		d += '				<option value="21">21</option>';			
		d += '				<option value="22">22</option>';			
		d += '				<option value="23">23</option>';			
		d += '				<option value="24">24</option>';			
		d += '				<option value="25">25</option>';
		d += '			</select>';
				
		
		d += '			<input type="submit" name="submit" value="Add Field" />';
		
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}

function generateEditFormParametersSubmissionForm(formId, formName, formDesc, formSubmissionEmail)
{
	var d  = '<form method="post" action="edit.jsp?form=form-parameters">';
		d += '<input name="formId" type="hidden" value="' + formId + '"> '; 
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';
		d += '			<label for="name">Name</label><br /><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" value="' + formName + '" /><br />';
		d += '			<label for="desc">Description</label><br /><input type="text" name="desc" id="desc" class="text ui-widget-content ui-corner-all" value="' + formDesc + '" /><br />';
		d += '			<label for="email">Submission Email</label><br /><input type="text" name="email" id="desc" class="text ui-widget-content ui-corner-all" value="' + formSubmissionEmail + '" />';
		d += '			<input type="submit" name="submit" value="Save Changes" />';
		
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}

function generateNewSliderItemSubmissionForm(sliderId, imageNameSelectMarkup)
{
	var d  = '<form method="post" action="add.jsp?form=sliderItem">';
		d += '<input name="sliderId" type="hidden" value="' + sliderId + '"> '; 
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';
		d += '			<label for="title">Title</label><br /><input type="text" name="title" id="title" class="text ui-widget-content ui-corner-all" /><br />';
		d += '			<label for="desc">Description</label><br /><input type="text" name="desc" id="desc" class="text ui-widget-content ui-corner-all" /><br />';
		d += '			<label for="url">Link Url</label><br /><input type="text" name="url" id="url" class="text ui-widget-content ui-corner-all" /><br />';	
		d += '			<label for="rank">Rank</label>';
		d += '			<select name="rank" id="rank" class="text ui-widget-content ui-corner-all" >';
		d += '				<option value="1" selected>1</option>';			
		d += '				<option value="2">2</option>';			
		d += '				<option value="3">3</option>';			
		d += '				<option value="4">4</option>';			
		d += '				<option value="5">5</option>';			
		d += '				<option value="6">6</option>';			
		d += '				<option value="7">7</option>';			
		d += '				<option value="8">8</option>';			
		d += '				<option value="9">9</option>';			
		d += '				<option value="10">10</option>';			
		d += '				<option value="11">11</option>';			
		d += '				<option value="12">12</option>';			
		d += '				<option value="13">13</option>';
		d += '			</select><br />';
		d += '			<label for="imageName">Image Name</label>' + imageNameSelectMarkup + '<br />';
		d += '			<label for="imageAlt">Image Caption</label>';
		d += '			<input type="text" name="imageAlt" id="imageAlt" value="" class="text ui-widget-content ui-corner-all" /><br />';		
		d += '			<input type="submit" name="submit" value="Add Item" />';		
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}

function generateNewSetSeoOnPageSubmissionForm(pageId, title, description, keywords)
{
	var d  = '<form method="post" action="edit.jsp?form=pageSeo">';
		d += '<input name="pageId" type="hidden" value="' + pageId + '"> ';  
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';	
		d += '			<label for="seoTitle">SEO Title</label><br />';
		d += '			<input type="text" name="seoTitle" id="seoTitle" value="' + title + '" class="text ui-widget-content ui-corner-all" /><br />';
		d += '			<label for="seoDescription">SEO Description</label>';
		d += '			<input type="text" name="seoDescription" id="seoDescription" value="' + description + '" class="text ui-widget-content ui-corner-all" /><br />';
		d += '			<label for="seoKeywords">SEO Keywords</label>';
		d += '			<input type="text" name="seoKeywords" id="seoKeywords" value="' + keywords + '" class="text ui-widget-content ui-corner-all" /><br />';
		d += '			<input type="submit" name="submit" value="Save" />';
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}

function generateNewSetFormOnPageSubmissionForm(pageId, FormNameSelectMarkup)
{
	var d  = '<form method="post" action="edit.jsp?form=pageForm">';
		d += '<input name="pageId" type="hidden" value="' + pageId + '"> ';  
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';	
		d += '			<label for="formId">Form: </label>' + FormNameSelectMarkup;
			
		d += '			<input type="submit" name="submit" value="Assign to Page" />';
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}


function generateNewSetSliderOnPage(pageId, SliderNameSelectMarkup)
{
	var d  = '<form method="post" action="edit.jsp?form=pageSlider">';
		d += '<input name="pageId" type="hidden" value="' + pageId + '"> ';  
		d += '<fieldset>';
		d += '<table width="100%" cellpadding="5" cellspacing="5">';
		d += '	<tr>';
		d += '		<td>';	
		d += '			<label for="sliderId">Slider: </label>' + SliderNameSelectMarkup;
			
		d += '			<input type="submit" name="submit" value="Assign to Page" />';
		d += '		</td>';
		d += '	</tr>';
		d += '</table>';
		d += '</fieldset>';
		d += '</form>';
	
	return d;
}

function generateExistingPageDiv(id, name, bodyText)
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>&nbsp;&nbsp;&nbsp;Edit <strong>' + name + '</strong> Page ';
		d += '			<dd>';
		d += '				<form method="post" action="edit.jsp?form=info">';
		d += '						<input name="name" type="hidden" value="' + name + '"> ';                                             
		d += '						<br /><textarea name="desc" rows="15" cols="65" style="padding:10px;">'+ bodyText + '</textarea>';     
		d += '						<br /><input type="button" value="Cancel" onClick="closeInput();" /><input type="submit" value="Save"/>';
		d += '				</form>';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}


function generatePageTemplateDiv(id, name, bodyText)
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>&nbsp;&nbsp;&nbsp;Edit <strong>' + name + '</strong> Page Template';
		d += '			<dd>';
		d += '				<form method="post" action="edit.jsp?form=pageTemplate">'; 
		d += '						<input name="name" type="hidden" value="' + name + '"> ';                         
		d += '						<br /><textarea name="desc" rows="15" cols="65">'+ bodyText + '</textarea>   ';     
		d += '						<br /><input type="button" value="Cancel" onClick="closeInput();" /><input type="submit" value="Save"/>';
		d += '				</form>';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}

function generateExistingTemplateDiv(id, name, bodyText)
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>&nbsp;&nbsp;&nbsp;Edit <strong>' + name + '</strong> Template';
		d += '			<dd>';
		d += '				<form method="post" action="edit.jsp?form=template">';  
		d += '						<input name="id" type="hidden" value="' + id + '"> ';   
		d += '						<input name="name" type="hidden" value="' + name + '"> ';           
		d += '						<br /><textarea name="body" rows="20" cols="85">'+ bodyText + '</textarea>   ';     
		d += '						<br /><input type="button" value="Cancel" onClick="closeInput();" /><input type="submit" value="Save"/>';
		d += '				</form>';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}

function generateNewContentPageDiv()
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>Add New Content Page';
		d += '			<dd>';
		d += '				<form method="post" action="add.jsp?form=pages">';                                           
		d += '					<table width="90%" cellpadding="0" cellspacing="0" border="0">'; 
		d += '						<tr><td align="right">Name: </td><td><input name="name" type="text" value=""/>'; 
		d += '						<input type="submit" value="Add"/><input type="button" value="Cancel" onClick="closeInput();"/></td></tr>'; 
		d += '					</table>'; 
		d += '				</form> ';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}

function generateNewContentTemplateDiv()
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>Add New Template';
		d += '			<dd>';
		d += '				<form method="post" action="add.jsp?form=template">';                                           
		d += '					<table width="90%" cellpadding="0" cellspacing="0" border="0">'; 
		d += '						<tr><td align="right">Name: </td><td><input name="name" type="text" value=""/>'; 
		d += '						<input type="submit" value="Add"/><input type="button" value="Cancel" onClick="closeInput();"/></td></tr>'; 
		d += '					</table>'; 
		d += '				</form> ';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}

function generateNewSubscriberDiv()
{
	var d  = '<div id="InputDivision">';
		d += '	<dl>';
		d += '		<dt>Add New Sbscriber';
		d += '			<dd>';
		d += '				<form method="post" action="add.jsp?form=mailinglist">';                                           
		d += '					<table width="90%" cellpadding="0" cellspacing="0" border="0">'; 
		d += '						<tr><td align="right">Name: </td><td><input name="Name" type="text" value=""/>'; 
		d += '						<tr><td align="right">EMail Address: </td><td><input name="EMailAddress" type="text" value=""/>'; 
		d += '						<input type="submit" value="Add"/><input type="button" value="Cancel" onClick="closeInput();"/></td></tr>'; 
		d += '					</table>'; 
		d += '				</form> ';
		d += '			</dd>';
		d += '		</dl>';
		d += '</div>';
	
	document.getElementById('input').innerHTML = d;
}

//trims the price 44.99 to 44 (trims from the dot onwards)
function trimChange(amount)
{
	return amount.substring(0,(amount.indexOf(".")));
}

function closeInput()
{
	document.getElementById('input').innerHTML = "";
}


function setVisibility(id, visibility) 
{
	document.getElementById(id).style.display = visibility;
}

function toggleVisibility(id) 
{
	if (document.getElementById(id).style.display == 'none')
	{
		document.getElementById(id).style.display = 'block';
	}
	else
	{
		document.getElementById(id).style.display = 'none';
	}
}

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
	if(document.getElementById('item10') != null)	document.getElementById('item10').style.display = "none"; 
	if(document.getElementById('item11') != null)	document.getElementById('item11').style.display = "none"; 
	if(document.getElementById('item12') != null)	document.getElementById('item12').style.display = "none"; 
	if(document.getElementById('item13') != null)	document.getElementById('item13').style.display = "none"; 
	if(document.getElementById('item14') != null)	document.getElementById('item14').style.display = "none"; 
	if(document.getElementById('item15') != null)	document.getElementById('item15').style.display = "none";
	if(document.getElementById('item16') != null)	document.getElementById('item16').style.display = "none";
}

function ask() 
{ 
    var answer = confirm("Are you sure you want to delete this?");    

    /* Question icon, Yes/No button. */
    /* 0 = error, 1 = OK, 2 = Cancel,*/

    if (answer == 1) { return (true);    }
    else             { return (false);   }
}

function MakeTable(divId, imageName, ItemId, ItemTitle, ListPrice, UrlPrice, AvailableSizes) 
{ 
//this table appears on the index page on the left side
	var d =  '<table cellspacing="0" cellpadding="0" align="center" border="0">';
	d += '<tbody>';
	d += '	<tr>';
	d += '		<td valign="top" align="center" colspan="2" >';
	d += '			<a href="items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '">';
	d += '				<img alt="' + ItemTitle + '" src="items/' + imageName + '" width="375" border="1"></a>';
	d += '		</td>';
	d += '	</tr>';
	d += '	<tr>';
	d += '		<td>';
	d += '			<a href="items.jsp?Id=' + ItemId + '&action=view"><font size="+1">' + ItemTitle + '</font></a>';
	d += '		</td>';
	d += '		<td>';
	d += '			<font size="+2"><b>$<del>' + trimChange(ListPrice) + '</del></b></font>';
	d += '		</td>';
	d += '	</tr>';
	d += '	<tr>';
	d += '		<td>';
	d += '			<b>Sizes:&nbsp;</b>' + AvailableSizes;
	d += '		</td>';
	d += '		<td>';
	d += '			<font size="+1">$' + trimChange(UrlPrice) + '</font>';
	d += '		</td>';
	d += '	</tr>';
	d += '</tbody>';
	d += '</table>';
	
	document.write(d);
}

function MakeThumbnailTable(divId, imageName, ItemId, ItemTitle, ListPrice, Price, BrandName) 
{ 
    var d =  '<div style="margin-right: 25px;" onMouseOver="HideAllDIV();DisplayDIV(' + "'" +  divId + "'" + ');">';
        d += '    <div>';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="items/' + imageName + '" border="0"  /></a>';
        d += '    </div>';
        d += '    <p style="color: gray; padding-top: 2px; margin-top: 5px; height: 50px; text-align: right; width: 250px; border-right: 3px solid #ccc; padding-right: 5px; float: left">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a>';
        d += '    	<br />';
        d += '    	';
        d += '    	<em><a href="items.jsp?Id=' + ItemId + '&action=view" style="font-weight: normal; color: gray;font-size: 11px;">' +  BrandName + '</a></em>';
        d += '    </p>';
        d += '    <div style="width: 50px; position: relative; float: right; margin-right: 40px; margin-top: 5px; ">';
        d += '    	<span style="font-size: 18px; color: red;">';
        d += '    		<del>$' + trimChange(ListPrice) + '</del>';
        d += '   	 </span>';
		 d += '    	<span style="font-size: 18px;">';
        d += '    		<strong>$' +  trimChange(Price) + '</strong>';
        d += '   	 </span>';
        d += '    </div>';
        d += '</div>';

    document.write(d);
}


function MakeDesignDiv(divId, imageName, ItemId, ItemTitle, MSRP, Price, BrandName, Height, Margin, FooterWidth, Displacement,  MSRPSize, PriceSize)  
{ 
	//star code from 
    var d =  '<div style="margin-right: 10px;" onMouseOver="HideAllDIV();DisplayDIV(' + "'" +  divId + "'" + ');">';
        d += '    <div style="background-image: url(images/product-shadow.jpg); background-position: bottom; height:' + (Height+8) + 'px; background-repeat: no-repeat;">';
        d += '    	<a href="design.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="items/' + imageName + '" border="0" height="' + Height + '"  /></a>';
        d += '    </div>';
        d += '    <p style="color: gray; padding-top: 2px; margin-top: 5px; height: 50px; text-align: right; width: ' + FooterWidth + 'px; border-right: 3px solid #ccc; padding-right: 5px; float: left">';
        d += '    	<a href="design.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a> <em><a href="vote.jsp?Id=' + ItemId + '&action=view" style="font-weight: normal; color: gray;font-size: 11px;">' +  BrandName + '</a></em>' ;
		d += '    	<br />';
		d += '    	<span>';
        d += '    		<iframe src="http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Fxtatico.com/Xtatico/design.jsp?Id=' + ItemId + '%2Fpage%2Fto%2Flike&amp;layout=button_count&amp;show_faces=true&amp;width=50&amp;action=like&amp;font=arial&amp;colorscheme=light&amp;height=21" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:50px; height:21px;" allowTransparency="true"></iframe>';
        d += '   	 </span>';
		d += '    	<span>';
 		d += '    		<a href="http://twitter.com/share" class="twitter-share-button" data-url="http://www.xtatico.com/Xtatico/vote.jsp?action=view&item=' + ItemId + '" data-text="The ' + ItemTitle + ' Design by ' + BrandName + ' is awsome:" data-count="none" data-via="xtatico">Tweet</a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>';
        d += '   	 </span>';
        d += '    	<table width="40%" border="0" callpadding="0" callspacing="0" style="margin-top:15px">';	
	    d += '   	 <tr><td>';				
        d += '    	<div class="rate">';		
        d += '    		<ul class="rating">';
        d += '    			<li><a href="add.jsp?fn=vote&Design=' + ItemId + '&rating=1" title="1 Star">1</a></li>';
        d += '    			<li><a href="add.jsp?fn=vote&Design=' + ItemId + '&rating=2" title="2 Stars">2</a></li>';
        d += '    			<li><a href="add.jsp?fn=vote&Design=' + ItemId + '&rating=3" title="3 Stars">3</a></li>';
        d += '    			<li><a href="add.jsp?fn=vote&Design=' + ItemId + '&rating=4" title="4 Stars">4</a></li>';
        d += '    			<li><a href="add.jsp?fn=vote&Design=' + ItemId + '&rating=5" title="5 Stars">5</a></li>';
        d += '    		</ul>';
        d += '    	</div>';				
		d += '    	</td></tr></table>';
        d += '    </p>';
        d += '    <div style="width: ' + Displacement + 'px; position: relative; float: right; margin-right: ' + Margin + 'px; margin-top: 5px; ">';
        d += '    </div>';
        d += '</div>';

    document.write(d);
}

function MakeUserDesignDiv(divId, imageName, ItemId, ItemTitle, MSRP, Price, BrandName, Height, Margin, FooterWidth, Displacement,  MSRPSize, PriceSize, score, votes)
{
		//star code from 
    var d =  '<div style="margin-right: 10px;">';
        d += '    <div style="background-image: url(../images/product-shadow.jpg); background-position: bottom; height:' + (Height+8) + 'px; background-repeat: no-repeat;">';
        d += '    	<a href="../design.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="../items/' + imageName + '" border="0" height="' + Height + '"  /></a>';
        d += '    </div>';
        d += '    <p style="color: gray; padding-top: 2px; margin-top: 5px; height: 50px; text-align: right; width: ' + FooterWidth + 'px; border-right: 3px solid #ccc; padding-right: 5px; float: left">';
        d += '    	<a href="../design.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a> ' ;
		d += '    	';
        d += '    	<table width="35%" border="0" callpadding="0" callspacing="0" style="margin-top:5px">';	
	    d += '   	 <tr><td>Rate:' +  Math.round((score/votes)*100)/100;								
		d += '    	 <br />Vote: ' + votes + ' </td></tr></table>';
        d += '    </p>';
        d += '    <div style="width: ' + Displacement + 'px; position: relative; float: right; margin-right: ' + Margin + 'px; margin-top: 5px; ">';
        d += '    </div>';
        d += '</div>';

    document.write(d);
}




function TitleBrandPricesItemDiv(divId, imageName, ItemId, ItemTitle, MSRP, Price, BrandName, Height, Margin, FooterWidth, Displacement,  MSRPSize, PriceSize, border, borderRadius, textShadow, boxShadow, backgroundColor) 
{ 
    var d =  '<div style="margin-right: 12px;border: ' + border + '' + borderRadius + '' + textShadow + '' + boxShadow + '' + backgroundColor + ';" onMouseOver="HideAllDIV();DisplayDIV(' + "'" +  divId + "'" + ');">';
        d += '    <div style="height:' + (Height+8) + 'px; background-repeat: no-repeat;">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="items/' + imageName + '" border="0" height="' + Height + '"  /></a>';
        d += '    </div>';
        d += '    <p style="color: gray; padding-top: 2px; margin-top: 5px; height: 50px; text-align: right; width: ' + FooterWidth + 'px; border-right: 3px solid #ccc; padding-right: 5px; float: left">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a>';
        d += '    	<br />';
        d += '    	';
        d += '    	<em><a href="items.jsp?Id=' + ItemId + '&action=view" style="font-weight: normal; color: gray;font-size: 11px;">' +  BrandName + '</a></em>';
        d += '    </p>';
        d += '    <div style="width: ' + Displacement + 'px; position: relative; float: right; margin-right: ' + Margin + 'px; margin-top: 5px; ">';
        d += '    	<span style="font-size: ' + MSRPSize + 'px; color: red;">';
        d += '    		<del>$' + trimChange(MSRP) + '</del>';
        d += '   	 </span>';
		 d += '    	<span style="font-size: ' + PriceSize + 'px; color: blue;">';
        d += '    		$' + trimChange(Price);
        d += '   	 </span>';
        d += '    </div>';
        d += '</div>';

    document.write(d);
}

function TitleBrandVoteItemDiv(divId, imageName, ItemId, ItemTitle, MSRP, Price, BrandName, Height, Margin, FooterWidth, Displacement,  MSRPSize, PriceSize) 
{ 
    var d =  '<div style="margin-right: 10px;" onMouseOver="HideAllDIV();DisplayDIV(' + "'" +  divId + "'" + ');">';
        d += '    <div style="background-image: url(images/product-shadow.jpg); background-position: bottom; height:' + (Height+8) + 'px; background-repeat: no-repeat;">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="items/' + imageName + '" border="0" height="' + Height + '"  /></a>';
        d += '    </div>';
        d += '    <p style="color: gray; padding-top: 2px; margin-top: 5px; height: 50px; text-align: right; width: ' + FooterWidth + 'px; border-right: 3px solid #ccc; padding-right: 5px; float: left">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a>';
        d += '    	<br />';
        d += '    	';
        d += '    	<em><a href="items.jsp?Id=' + ItemId + '&action=view" style="font-weight: normal; color: gray;font-size: 11px;">' +  BrandName + '</a></em>';
        d += '    </p>';		
		d += '    	<span>';
        d += '    		<iframe src="http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Fexample.com%2Fpage%2Fto%2Flike&amp;layout=button_count&amp;show_faces=true&amp;width=50&amp;action=like&amp;font=arial&amp;colorscheme=light&amp;height=21" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:50px; height:21px;" allowTransparency="true"></iframe>';
        d += '   	 </span>';
		d += '    	<span>';
        d += '    		<a href="http://twitter.com/share" class="twitter-share-button" data-url="vote.jsp?action=view&item=' + ItemId + '" data-text="The ' + ItemTitle + ' Design by ' + BrandName + ' is awsome:" data-count="none" data-via="xtatico">Tweet</a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>';
        d += '   	 </span>';
        d += '    	<table width="40%" border="0" callpadding="0" callspacing="0" style="margin-top:15px">';	
	    d += '   	 <tr><td>';				
        d += '    	<div class="rate">';		
        d += '    		<ul class="rating">';
        d += '    			<li><a href="#" title="1 Star">1</a></li>';
        d += '    			<li><a href="#" title="2 Stars">2</a></li>';
        d += '    			<li><a href="#" title="3 Stars">3</a></li>';
        d += '    			<li><a href="#" title="4 Stars">4</a></li>';
        d += '    			<li><a href="#" title="5 Stars">5</a></li>';
        d += '    		</ul>';
        d += '    	</div>';				
		d += '    	</td></tr></table>';				
        d += '    </div>';
        d += '</div>';

    document.write(d);
}


function TitleBrandItemDiv(divId, imageName, ItemId, ItemTitle, MSRP, Price, BrandName, Height, Margin, FooterWidth) 
{ 

    var d =  '<div style="margin-right: 10px;">';
        d += '    <div style="background-image: url(images/product-shadow.jpg); background-position: bottom; height:' + (Height+8) + 'px; background-repeat: no-repeat;">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="items/' + imageName + '" border="0" height="' + Height + '" /></a>';
        d += '    </div>';
        d += '    <p style="padding-top: 2px; margin-top: 0px; height: 50px; text-align: right; width: ' + FooterWidth + 'px; padding-right: 5px;>';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a>';
        d += '    	<br />';
        d += '    	';
        d += '    	<em><a href="items.jsp?Id=' + ItemId + '&action=view" style="font-weight: normal; color: gray;font-size: 11px;">' +  BrandName + '</a></em>';
        d += '    </p>';
        d += '</div>';

    document.write(d);
}

function MakeItemColumn(ItemId, ItemTitle, ItemDescription, ListPrice, UrlPrice, ItemSizeString) 
{    

	var value = Math.round(((ListPrice-UrlPrice)/(ListPrice))*100);  
	
    var d = '';
    d += '  <table width="100%" cellpadding="0" cellspacing="0" border="0">';
    d += '  <tr>';
	d += '  <form name="AddToCart" action="AddToShoppingCart" method="post">';
	d += '  <input name="productCode" type="hidden" value="' + ItemId + '">';
	d += '  <input name="description" type="hidden" value="' + ItemTitle + '">';
	d += '  <input name="quantity" type="hidden" value="1">';
	d += '  <input name="price" type="hidden" value="' + UrlPrice + '">';
    d += '    <td valign="top">';
    d += '      <p><b>Description: </b>' + ItemDescription + '</p>';
    d += '      <p>Item #: ' + ItemId + '</p>';
    d += '      <hr />';
    d += '      Sizes: ' + ItemSizeString ;
    d += '    <br /><br />';
    d += '    List Price: $' + trimChange(ListPrice) + '';
    d += '    <br /><b>TS Price: $' + trimChange(UrlPrice) + '</b>';
	d += '    <br /><font color="#FF0000">You Save: ' + value  + '%</font>';
    d += '    <br /><br /><img src="images/free_shipping.jpg" alt="Free Ground Shipping" />';
    d += '    <hr />';
    d += '     <input TYPE="image" SRC="images/button_add_to_cart_grey.gif" WIdTH="154" HEIGHT="44" BORDER="0" ALT="Add Item to Cart">';
    d += '    <a href="mail.jsp?item=' + ItemId + '" ><img border="0" src="images/button_email_page_grey.jpg" width="154" height="44"></a>   ';
    d += '    </td>';
	d += '  </form>';	
    d += '  </tr>';
	 d += '  </table>'; 
	
    document.write(d);    
}


function MakePriceForm(ItemId, ItemTitle, ListPriceStr, ListPrice, PriceStr, Price, ItemSizeString, RegularlyText, SavingsText, ChooseText, BuyText) 
{    

	var value = Math.round(((ListPrice-Price)/(ListPrice))*101);  
	
    var d = '';
    d += '  <table width="65%" cellpadding="0" cellspacing="0" border="0" align="right">';
    d += '  <tr>';
	d += ' 	<form name="AddToCart" action="AddToShoppingCart" method="post">';
	d += '  		<input name="productCode" type="hidden" value="' + ItemId + '">';
	d += '  		<input name="description" type="hidden" value="' + ItemTitle + '">';
	d += '  		<input name="quantity" type="hidden" value="1">';
	d += '  		<input name="price" type="hidden" value="' + Price + '">';
    d += '    	<td valign="top">';
    d += '    		' + RegularlyText + ':<del> <span style="font-size:18px">' + ListPriceStr + '</span></del><br />';
    d += '    		<span style="font-size:38px"><b>' + PriceStr + '</b></span><br />';
	d += '    		<span style="color:#F00">(' + value  + '%) ' + SavingsText + '!</span>';
    d += '     		<br /> ' + ChooseText + ': ' + ItemSizeString ;
    d += '    		<br /><br />';
    d += '     		<input type="submit" class="buyButton" value="' + BuyText + '">';
    d += '     </td>';
	d += '  </form>';	
    d += '  </tr>';
	 d += ' </table>'; 
	
    document.write(d);    
}


function MakeVoteForm(ItemId, ItemTitle, BrandName, score, votes) 
{    

    var d = '';
    d += '  <table width="100%" cellpadding="0" cellspacing="0" border="0">';
    d += '  <tr>';
	d += '  <form name="AddToCart" action="AddToShoppingCart" method="post">';
	d += '  <input name="productCode" type="hidden" value="' + ItemId + '">';
	d += '  <input name="description" type="hidden" value="' + ItemTitle + '">';
    d += '    <td valign="top">';
    d += '      <hr />';
		d += '    	<span>';
        d += '    		<iframe src="http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Fxtatico.com/Xtatico/design.jsp?Id=' + ItemId + '%2Fpage%2Fto%2Flike&amp;layout=button_count&amp;show_faces=true&amp;width=50&amp;action=like&amp;font=arial&amp;colorscheme=light&amp;height=21" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:50px; height:21px;" allowTransparency="true"></iframe>';
        d += '   	 </span>';
		d += '    	<span>';
        d += '    		<a href="http://twitter.com/share" class="twitter-share-button" data-url="http://www.xtatico.com/Xtatico/vote.jsp?action=view&item=' + ItemId + '" data-text="The ' + ItemTitle + ' Design by ' + BrandName + ' is awsome:" data-count="none" data-via="xtatico">Tweet</a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>';
        d += '   	 </span>';
        d += '    	<table width="40%" border="0" callpadding="0" callspacing="0" style="margin-top:15px">';	
	    d += '   	 <tr>';
		d += '   	 	<td>';				
        d += '    			Rate: <span style="font-size:28px">' + Math.round((score/votes)*100)/100   + '</span> from <span style="font-size:22px">' + votes + '</span> votes!';				
		d += '    	</td></tr></table>';
    d += '    </td>';
	d += '  </form>';	
    d += '  </tr>';
	 d += '  </table>'; 
	
    document.write(d);    
}

function MakeItemDiv(ItemId, ItemTitle, ItemDescription, ListPrice, UrlPrice, ItemSizeString) 
{    

	var value = Math.round(((ListPrice-UrlPrice)/(ListPrice))*100);  
	
    var d = '';
    d += '<td bgcolor="#e1e1e1" width="*"><b>' + ItemTitle + '</b></td>';
    d += '  </tr>';
    d += '  <tr>';
	d += '  <form name="AddToCart" action="AddToShoppingCart" method="post">';
	d += '  <input name="productCode" type="hidden" value="' + ItemId + '">';
	d += '  <input name="description" type="hidden" value="' + ItemTitle + '">';
	d += '  <input name="quantity" type="hidden" value="1">';
	d += '  <input name="price" type="hidden" value="' + UrlPrice + '">';
    d += '    <td valign="top">';
    d += '      <p><b>Description: </b>' + ItemDescription + '</p>';
    d += '      <p>Item #: ' + ItemId + '</p>';
    d += '      <hr />';
    d += '      Sizes: ' + ItemSizeString ;
    d += '    <br /><br />';
    d += '    List Price: $' + trimChange(ListPrice) + '';
    d += '    <br /><b>TS Price: $' + trimChange(UrlPrice) + '</b>';
	d += '    <br /><font color="#FF0000">You Save: ' + value  + '%</font>';
    d += '    <br /><br /><img src="images/free_shipping.jpg" alt="Free Ground Shipping" />';
    d += '    <hr />';
    d += '     <input TYPE="image" SRC="images/button_add_to_cart_grey.gif" WIdTH="154" HEIGHT="44" BORDER="0" ALT="Add Item to Cart">';
    d += '    <a href="mail.jsp?item=' + ItemId + '" ><img border="0" src="images/button_email_page_grey.jpg" width="154" height="44"></a>   ';
    d += '    </td>';
	d += '  </form>';	
    d += '  </tr>'; 
	
    document.write(d);    
}


function MakePost(divId, postTitle, postBody, postPicURL, postDate, postTags, postUserId, postRating, postAuthor) 
{ 		
    var d = '<div id="Post">  ';  
	    d += '    <div id="PostHeader">   	 ';
	    d += '    <div id="PostImage">  ';  	 	
	    d += '           		<img src="images/' + postPicURL + '" border="0"> ';  	 
	    d += '            </div>   	 ';
	    d += '            <div id="PostTitle">   	'; 	
	    d += '            	<strong>' + postTitle + '</strong><br>   ';	 
	    d += '            </div>   	 ';
	    d += '            <div id="PostAuthorDateRating" align="right"> ';   		
	    d += '            	<em>'+ postAuthor + '</em> on ' + postDate.substring(0,10) + '   	 ';
	    d += '            	<br>';
	    d += '            	<strong>' + postRating + '</strong>';
	    d += '            </div>    ';
	    d += '        </div>    ';
	    d += '        <div id="PostBody">' +  postBody + '';
	    d += '    ';
	    d += '        	<div id="PostBodyImage1"><img src="images/single_quote_start.jpg" border="0" /></div>';
	    d += '            <div id="PostBodyImage2"><img src="images/single_quote.jpg" border="0" /></div>';
	    d += '        </div>    ';
	    d += '        <div id="PostTags" align="right">' + postTags + '</div>';
	    d += '        <div id="PostFooterDiv">   	 ';
	    d += '          <div style="width: 200px;">    	 	';
	    d += '           		<img src="images/plus.jpg" width="20" height="20" border="0"> comments '; 	 
	    d += '            </div>   	  	 ';
	    d += '            <div id="PostFooterShareIcons" align="right">    	';	
	    d += '            	share:   	 ';
	    d += '            </div>    ';
	    d += '        </div>';
	    d += '    </div><br />';

    document.write(d);
}


function MakeNonProfitPost(divId, postTitle, postBody, postPicURL, postDate, postTags, postUserId, postRating, postAuthor) 
{ 
    var d =  '<div style="margin: 3px; padding: 1px;  clear: both; display:block">';					
		d += '    <br /><div style="width: 180px; float: left; clear: right;" align="center">';
        d += '   	 	<img src="images-site/' + postPicURL + '" border="0" />';				
        d += '    </div>';	
	    d += '    <div style="width: 100%; position: relative; ">';					
        d += '   	 <div style="position: absolute; left: 200px; Top: 0; width: 350px; font-size: 16px;">';
        d += '   	 	<strong>' + postTitle + '</strong><br />';	
        d += '   	 </div>';
        d += '    	<div style="margin: 10px; position: absolute; left: 200px; Top: 30px ">';
        d += '   	 	' +  postBody + '<hr />';
        d += '    	</div>';
        d += '    </div>';
        d += '</div>';

    document.write(d);
}

function MakeContestPost(divId, postTitle, postBody, postPicURL, postDate, postTags, postUserId, postRating, postAuthor) 
{ 
    var d =  '<div style="margin: 8px; padding: 1px;  width: 95%;">';					
		d += '    <br /><div style="width: 180px; float: left; clear: right;" align="center">';
        d += '   	 	<img src="images/' + postPicURL + '" border="0" width="180px" />';				
        d += '    </div>';
		d += '    <strong>' + postTitle + '</strong><br />';	
	    d += '    <div style="width: 100%; position: relative; margin:20px; ">';					
        d += '   	 <div style="left: 250px; Top: 0; width: 85%; font-size: 16px;">';
        d += '   	 	' +  postBody + '<hr />';
        d += '   	 </div>';

        d += '    </div>';
        d += '</div>';

    document.write(d);
}

function MakePostComment(divId, postTitle, postAuthor, postDate, postBody, Price, BrandName) 
{ 
    var d =  '<div style="margin-right: 10px;" onMouseOver="HideAllDIV();DisplayDIV(' + "'" +  divId + "'" + ');">';
        d += '    <div style="background-image: url(images/product-shadow.jpg); background-position: bottom; height:' + (Height+8) + 'px; background-repeat: no-repeat;">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" alt="' + ItemTitle + '"><img alt="' + ItemTitle + '" src="items/' + imageName + '" border="0" height="' + Height + '"  /></a>';
        d += '    </div>';
        d += '    <p style="color: gray; padding-top: 2px; margin-top: 5px; height: 50px; text-align: right; width: ' + FooterWidth + 'px; border-right: 3px solid #ccc; padding-right: 5px; float: left">';
        d += '    	<a href="items.jsp?Id=' + ItemId + '&action=view" style="font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;">' +  ItemTitle + '</a>';
        d += '    	<br />';
        d += '    	';
        d += '    	<em><a href="items.jsp?Id=' + ItemId + '&action=view" style="font-weight: normal; color: gray;font-size: 11px;">' +  BrandName + '</a></em>';
        d += '    </p>';
        d += '    <div style="width: ' + Displacement + 'px; position: relative; float: right; margin-right: ' + Margin + 'px; margin-top: 5px; ">';
        d += '    	<span style="font-size: ' + MSRPSize + 'px; color: red;">';
        d += '    		<del>$' + trimChange(MSRP) + '</del>';
        d += '   	 </span>';
		 d += '    	<span style="font-size: ' + PriceSize + 'px;">';
        d += '    		$' + trimChange(Price);
        d += '   	 </span>';
        d += '    </div>';
        d += '</div>';

    document.write(d);
}

function MakeLinks(count, pageName, pageLimit, style) 
{
	var i  = 0;
	var k = (count/pageLimit);
	var d = "";
			
	for(i = 0; i < k; i++)
	{
    	d += '	<a style="font-size:24px; font-weight:bold; font-family:Georgia, "Times New Roman", Times, serif;test-decoration:none;" href="' + pageName + '?page=' + (i+1) + '">&nbsp;' + ((style == 1) ? (i+1) : ".") + '&nbsp;</a>';    	
	}

	document.write(d);
}
