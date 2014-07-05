
<%@ page import="java.util.AbstractMap.SimpleEntry" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%
    //get Form information	
    Form f = Database.getForm((String)request.getAttribute("formId"));    
    f.setFields(Database.getFormFields(f.getFormId()));
%>

<h3> <%= f.getFormDescription() %></h3> 

<div style="background-color:#eee; border: 1px solid #AAA; padding-top:10px;padding-bottom:10px;">
<table border="0" cellpadding="1" cellspacing="5" width="90%" bordercolor="#cccccc">
<tbody>
    <form name="<%= f.getFormName() %>" action="<%= f.getFormAction() %>" method="<%= f.getFormSubmissionMethod() %>" >
    <input name="submissionEmailAddress" type="hidden" value="<%= f.getFormSubmissionEmail() %>"> 
    <tr>
        <td>
                <%  for (FormField ff : f.getFields())
                    {    %>
                    <tr>
                    <td style="text-align: right; width: 25%;">
                    <label for="<%= ff.getFieldName() %>"><%= ff.getFieldLabel() %><%= ff.getFieldOptional()==0 ? "*":"" %></label>
                    </td><td>
                    <% if(ff.getFieldDataType().equals("textarea") ) { %>
                        <textarea name="<%=ff.getFieldName()%>" rows="<%= ff.getFieldOptions().get(0).getValue() %>" cols="<%= ff.getFieldOptions().get(1).getValue() %>" <%= ff.getFieldOptional()==0 ? " required ":"" %>  pattern="<%= ff.getFieldValidationRegex() %>" ><%= ff.getFieldDefaultValue() %></textarea>                         
                    <% } else if(ff.getFieldDataType().equals("select")) { %>
                        <select name="<%= ff.getFieldName() %>" <%= ff.getFieldOptional()==0 ? " required ":"" %>>
                            <% for(SimpleEntry entry : ff.getFieldOptions()) { %><option value="<%= entry.getValue()%>"><%= entry.getKey() %></option><% } %>                            
                   <% } else if(ff.getFieldDataType().equals("radio")) { %>
                            <% for(SimpleEntry entry : ff.getFieldOptions()) { %>
                                <input type="radio" name="<%= ff.getFieldName() %>" value="<%= entry.getValue()%>" <%= ff.getFieldOptional()==0 ? " required ":"" %> ><%= entry.getKey() %><br>
                            <% } %>                            
                  <% } else if(ff.getFieldDataType().equals("checkbox")) { %>
                            <% for(SimpleEntry entry : ff.getFieldOptions()) { %>
                                <input type="checkbox" name="<%= ff.getFieldName() %>" value="<%= entry.getValue()%>" <%= ff.getFieldOptional()==0 ? " required ":"" %>><%= entry.getKey() %><br>
                            <% } %>                            
                    <% } else if(ff.getFieldDataType().equals("text") || ff.getFieldDataType().equals("search") || ff.getFieldDataType().equals("url") || ff.getFieldDataType().equals("tel") || ff.getFieldDataType().equals("email") || ff.getFieldDataType().equals("password")) { %>
                    <input name="<%= ff.getFieldName() %>" type="<%= ff.getFieldDataType() %>" value="<%= ff.getFieldDefaultValue() %>" <%= ff.getFieldOptional()==0 ? " required ":"" %>   <%= ff.getFieldValidationRegex().equals("-") ? "" : ("pattern=\""+ ff.getFieldValidationRegex() + "\"") %>  class="text ui-widget-content ui-corner-all" size="60%"> <br /> 
                    <% } else { %>
                    <input name="<%= ff.getFieldName() %>" type="<%= ff.getFieldDataType() %>" value="<%= ff.getFieldDefaultValue() %>" <%= ff.getFieldOptional()==0 ? " required ":"" %>   class="text ui-widget-content ui-corner-all" size="60%"> <br />
                    <% } %>
                    
                    </td></tr>
                
                <%  }    %>

        </td>
    </tr>
    <tr>
        <td></td>
        <td style="text-align: right">
            
            <% if(f.isFormResettable()) { %>
                <input type="reset" value="Reset Form" /> 
            <% } %>
            <input type="submit" value="Submit Form" />
            
        </td>
    </tr>
    
   </form>
</tbody>
</table>
</div>

        
 