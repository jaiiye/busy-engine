
<%@ page import="java.util.AbstractMap.SimpleEntry" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%
    Slider s = Database.getSlider((String)request.getAttribute("sliderId"));    
    s.setItems(Database.getSliderItems(s.getSliderId()));
	String sliderType = Database.getSliderTypeName(s.getSliderType());
%>

<% //Form1 
if(sliderType.equals("slider11 p05")) 
{ %>
<section class="<%= sliderType %>">
    <div class="slider">
        
    <%  for (SliderItem item : s.getItems())
        {    %>
            <article>
                <div>
                    <h3><%= item.getItemTitle() %></h3>
                    <p><%= item.getItemDescription() %></p>
                    <p class="more"><a href="<%= item.getItemUrl() %>">Read more</a></p>
                </div>
                    <img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>">
            </article>    
    <%  }    %>
    </div>
</section>

<% if(s.getFormId() != 0) 
    { 
        //get Form information	
        Form f = Database.getForm(s.getFormId() + "");    
        f.setFields(Database.getFormFields(f.getFormId()));
%>

<section class="landing-form">
    <form name="<%= f.getFormName() %>" action="<%= f.getFormAction() %>" method="<%= f.getFormSubmissionMethod() %>" >
        <h2> <%= f.getFormDescription() %></h2> 
        <input name="submissionEmailAddress" type="hidden" value="<%= f.getFormSubmissionEmail() %>"> 
                <%  for (FormField ff : f.getFields())
                    {    %>
                    <p>
                        <label for="<%= ff.getFieldName() %>"><%= ff.getFieldLabel() %></label>                    
                        <% if(ff.getFieldDataType().equals("textarea") ) { %>
                            <textarea name="<%=ff.getFieldName()%>" rows="<%= ff.getFieldOptions().get(0).getValue() %>" cols="<%= ff.getFieldOptions().get(1).getValue() %>"><%= ff.getFieldDefaultValue() %></textarea>                         
                        <% } else if(ff.getFieldDataType().equals("select")) { %>
                            <select name="<%= ff.getFieldName() %>">
                                <% for(SimpleEntry entry : ff.getFieldOptions()) { %><option value="<%= entry.getValue()%>"><%= entry.getKey() %></option><% } %>                            
                       <% } else if(ff.getFieldDataType().equals("radio")) { %>
                                <% for(SimpleEntry entry : ff.getFieldOptions()) { %>
                                    <input type="radio" name="<%= ff.getFieldName() %>" value="<%= entry.getValue()%>"><%= entry.getKey() %><br>
                                <% } %>                            
                      <% } else if(ff.getFieldDataType().equals("checkbox")) { %>
                                <% for(SimpleEntry entry : ff.getFieldOptions()) { %>
                                    <input type="checkbox" name="<%= ff.getFieldName() %>" value="<%= entry.getValue()%>"><%= entry.getKey() %><br>
                                <% } %>                            
                        <% } else { %>
                        <input name="<%= ff.getFieldName() %>" type="<%= ff.getFieldDataType() %>" value="<%= ff.getFieldDefaultValue() %>" class="text ui-widget-content ui-corner-all" size="60%"> <br />
                        <% } %> 
                    </p>
                <%  }    %>
            
            <% if(f.isFormResettable()) { %>
                <p><input type="reset" value="Reset Form" /> </p>
            <% } %>
                
            <p><button name="submit" type="submit">Sign up now for free</button></p>
   </form>
</section>
<%      } %>  

<%  } %> 
 
 
 
<%  //Form 2 
if(sliderType.equals("slider5 p01")) 
{ %>
<section class="<%= sliderType %>">
<div>
    <div class="slider">        
    <%  for (SliderItem item : s.getItems())
        {    %>
            <article>
                <div>
                    <div class="img-border"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></div>
                    <h3><%= item.getItemTitle() %></h3>
                </div>
            </article>    
    <%  }    %>
    </div>
    <% if(s.getFormId() != 0) 
    { 
        Form f = Database.getForm(s.getFormId() + "");    
        f.setFields(Database.getFormFields(f.getFormId()));
%>

        <form name="<%= f.getFormName() %>" action="<%= f.getFormAction() %>" method="<%= f.getFormSubmissionMethod() %>" >
            <h2><%= f.getFormDescription() %></h2> 
            <input  name="submissionEmailAddress" type="hidden" value="<%= f.getFormSubmissionEmail() %>"> 
                    <%  for (FormField ff : f.getFields())
                        {    %>
                           <!-- <label for="<%= ff.getFieldName() %>"><%= ff.getFieldLabel() %></label>                    -->
                            <% if(ff.getFieldDataType().equals("textarea") ) { %>
                                <p class="textarea"><textarea placeholder="<%= ff.getFieldLabel() %>" name="<%=ff.getFieldName()%>" rows="<%= ff.getFieldOptions().get(0).getValue() %>" cols="<%= ff.getFieldOptions().get(1).getValue() %>"></textarea></p>                         
                            <% } else if(ff.getFieldDataType().equals("select")) { %>
                                <select name="<%= ff.getFieldName() %>" placeholder="<%= ff.getFieldLabel() %>" >
                                    <% for(SimpleEntry entry : ff.getFieldOptions()) { %><option value="<%= entry.getValue()%>"><%= entry.getKey() %></option><% } %>
                                 </select>                            
                           <% } else if(ff.getFieldDataType().equals("radio")) { %>
                           
                                <p  style="color:#FFF">
                                <label for="<%= ff.getFieldName() %>"  style="color:#FFF"><%= ff.getFieldLabel() %></label>
                                <% for(SimpleEntry entry : ff.getFieldOptions()) { %>
                                    <input type="radio" name="<%= ff.getFieldName() %>" value="<%= entry.getValue()%>"><%= entry.getKey() %>
                                <% } %> 
                                </p>                           
                          <% } else if(ff.getFieldDataType().equals("checkbox")) { %>
                                <p style="color:#FFF">
                                <label for="<%= ff.getFieldName() %>"  style="color:#FFF"><%= ff.getFieldLabel() %></label>
                                <% for(SimpleEntry entry : ff.getFieldOptions()) { %>
                                    <input type="checkbox" name="<%= ff.getFieldName() %>" value="<%= entry.getValue()%>"><%= entry.getKey() %>
                                <% } %>
                                </p>                            
                            <% } else { %>
                            <p class="input"><input placeholder="<%= ff.getFieldLabel() %>"  name="<%= ff.getFieldName() %>" type="<%= ff.getFieldDataType() %>" class="text ui-widget-content ui-corner-all" size="60%"></p>
                            <% } %> 
                    <%  }    %>
                
                <% if(f.isFormResettable()) { %>
                    <p><input type="reset" value="Reset Form" /> </p>
                <% } %>
                    
                <p class="submit"><button name="submit" type="submit">Submit</button></p>
       </form>
    <%      } %> 
    </div>
</section>


<%  } %> 


<%  //Full page 1
if(sliderType.equals("slider slider1")) 
{ %>

<section class="<%= sliderType %>">
	<%  for (SliderItem item : s.getItems())
        {    %>
            <article>
                <img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>">
                <div>
                    <h3><%= item.getItemTitle() %></h3>
                    <p><%= item.getItemDescription() %></p>
                    <p class="cta"><a href="<%= item.getItemUrl() %>" class="button">Buy now</a> <a href="#">Learn more</a></p>
                </div>
            </article>	    
    <%  }  %> 
</section>

<%  } %> 

<%  //Full page 2
if(sliderType.equals("slider slider8")) 
{ %>

<section class="<%= sliderType %>">
	<%  for (SliderItem item : s.getItems())
        {    %>
            <article>
                <img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>">
                <div><div>
                    <h3><%= item.getItemTitle() %></h3>
                    <p><%= item.getItemDescription() %></p>
                    <p><a href="<%= item.getItemUrl() %>" class="cta">View Details</a></p>
                </div></div>
            </article>	    
    <%  }  %> 
</section>

<%  } %>


<%  //Content 1
if(sliderType.equals("slider12 p05")) 
{ %>

<section class="<%= sliderType %>">
    <div class="slider">
		<%  for (SliderItem item : s.getItems())
        {    %>
                <article>
                    <div class="img"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></div>
                    <div class="text">
                        <h3><%= item.getItemTitle() %></h3>
                        <p><%= item.getItemDescription() %></p>
                        <p><a href="<%= item.getItemUrl() %>">Learn more</a></p>
                    </div>
                </article>	    
        <%  }  %> 		
    </div>
    <div class="control-bg"></div>
</section>

<%  } %>

<%  //Content 2
if(sliderType.equals("slider9 p05")) 
{ %>

    <section class="<%= sliderType %>">
        <div class="slider">
			<%  for (SliderItem item : s.getItems())
            {    %>
                    <article>
                        <div><div><span class="img-border"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></span></div></div>
                        <h3><%= item.getItemTitle() %></h3>
                        <p><%= item.getItemDescription() %></p>
                        <p><span class="more"><a href="<%= item.getItemUrl() %>">Learn more</a></span></p>
                    </article>	    
            <%  }  %> 	
        </div>
        <ul class="slider-titles">
			<%  for (SliderItem item : s.getItems())
            {    %>
        			<li><a href="#"><%= item.getItemTitle() %></a></li>	    
        	<%  }  %> 
        </ul>
    </section>

<%  } %>



<%  // Buttoned
if(sliderType.equals("slider4 p09")) 
{ %>
<section class="<%= sliderType %>">
    <div class="slider">
    	<%  for (SliderItem item : s.getItems())
        {    %>				
            <article>
                <div><span><span class="img-border"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></span></span></div>
                <h3><%= item.getItemTitle() %></h3>
                <p><%= item.getItemDescription() %></p>
                <p><a href="<%= item.getItemUrl() %>" class="button">Learn More...</a></p>
            </article>
		<%  } %>
    </div>
</section>

<%  } %>



<%  //Titled
if(sliderType.equals("slider6 p07")) 
{ %>
<section class="<%= sliderType %>">
    <div><div>
        <section class="menu">
            <a href="#" class="prev">previous</a>
            <div>
                <ul>
					<%  for (SliderItem item : s.getItems())
                    {    %>	
                    	<li><a href="<%= item.getItemUrl() %>"><span class="img-border"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></span></a></li>
		   			<%  } %>
                </ul>
            </div>
            <a href="#" class="next">next</a>
        </section>
        <section class="slides">
			<%  for (SliderItem item : s.getItems())
            {    %>	
                <article>
                    <div class="img"><span class="img-border"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></span></div>
                    <h3><a href="#"><%= item.getItemTitle() %></a></h3>
                </article>
		   <%  } %>
        </section>
        <div class="clear"></div>
    </div></div>
</section>

<%  } %>


<%  //Slideshow
if(sliderType.equals("slider slider2")) 
{ %>
<section class="<%= sliderType %>">
	<%  for (SliderItem item : s.getItems())
    {    %>	
        <article>
            <img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>">
            <div>
                <h3><%= item.getItemTitle() %></h3>
            </div>
        </article>
    <%  } %>				
</section>

<%  } %>

	
<%  //Product
if(sliderType.equals("slider10")) 
{ %>
<section class="<%= sliderType %>">
    <a href="#" class="prev">Previous</a>
    <a href="#" class="next">Next</a>
    <ul>    
		<%  for (SliderItem item : s.getItems())
        {    %>	
        	<li><a href="<%= item.getItemUrl() %>"><span class="img-border"><img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>"></span></a></li>
    <%  } %>	
    </ul>
</section>

<%  } %>


<%  //Itemized
if(sliderType.equals("slider7")) 
{ %>
<section class="<%= sliderType %>">
	<div class="p05">
        <div>
            <div class="slider">                
				<%  for (SliderItem item : s.getItems())
                {    %>	
                    <article>
                        <img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>">
                        <div>
                            <h3><%= item.getItemTitle() %></h3>
                            <p><%= item.getItemDescription() %></p>
                        </div>
                    </article>
    			<%  } %>				
            </div>
            <div class="controls">
                <ul class="slider-titles">            
					<%  for (SliderItem item : s.getItems())
                    {    %>	
                    	<li><a href="#" class="camera"><%= item.getItemTitle() %></a></li>
    			<%  } %>	
                </ul>
            </div>
        </div>
    </div>
</section>

<%  } %>


<%  //Portfolio
if(sliderType.equals("portfolio-slider slider")) 
{ %>
<section class="<%= sliderType %>">                
	<%  for (SliderItem item : s.getItems())
    {    %>	
        <article>
            <img src="images-site/<%= item.getItemImageName() %>" alt="<%= item.getItemImageAlt() %>">
            <div>
                <h3><%= item.getItemTitle() %></h3>
                <p><%= item.getItemDescription() %></p>
            </div>
        </article>
    <%  } %>
</section>

<%  } %>