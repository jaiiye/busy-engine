
<%@page import="java.sql.PreparedStatement"%>
<%@ page import="
com.transitionsoft.*,
com.transitionsoft.dao.*,
java.io.*,
java.util.*,
java.text.*,
javax.servlet.*,
javax.servlet.http.*, 
javax.servlet.http.HttpServletRequest,
javax.servlet.ServletInputStream,
java.util.Dictionary,
java.util.Hashtable,
java.io.IOException"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="../connection.jsp" %> 


<%  

if(request.getParameter("form").equals("item"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO item(ItemName, ItemDescription, ItemBrandId, ItemListPrice, ItemPrice, ItemPriceAdjustment,  ItemSEOTitle,  ItemSEODescription, ItemSEOKeywords, ItemShortDescription)";
		
        query += " VALUES ( '" + Database.encodeHTML(request.getParameter("name")) + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("desc"))     + "', ";
        query += ""  + request.getParameter("brandId")  + ", ";
        query += "" + request.getParameter("listPrice")+ ", ";
        query += "" + request.getParameter("price")    + ", ";
        query += "'" + request.getParameter("adjust")   + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("seotitle"))  + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("seodesc"))  + "', ";
        query += "'" +  Database.encodeHTML(request.getParameter("seokeywords"))  + "', ";
        query += "'" +  Database.encodeHTML(request.getParameter("shortDesc"))  + "' ";
        query += ");";
		
        System.out.println(query); 
		
		PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);   
    
		pstmt.executeUpdate();   
		ResultSet keys = pstmt.getGeneratedKeys();   
		  
		keys.next();
		int key = 0;    
		key = keys.getInt(1);   
		keys.close();   
		pstmt.close();

		
		 query = "INSERT INTO item_image(ItemId, ItemImageName, ItemThumbnailImage)  VALUES (" + key + ", 'unknown.png', 'sm_unknown.png')";
		 statement.executeUpdate(query);
		 

        response.sendRedirect("index.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("itemDesign"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO item(ItemName, ItemDescription, ItemBrandId, ItemType, ItemRating, ItemVoteCount,  ItemSEOTitle,  ItemSEODescription, ItemSEOKeywords)";
		
		
        query += " VALUES ( '" + request.getParameter("name") + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("desc"))     + "', ";
        query += ""  + request.getParameter("brandId")  + ", ";
        query += "" + request.getParameter("type")+ ", ";
        query += "3.00, ";
        query += "1, ";
		query += "'" + Database.encodeHTML(request.getParameter("seotitle"))  + "', ";
		query += "'" + Database.encodeHTML(request.getParameter("seodesc"))  + "', ";
		query += "'" +  Database.encodeHTML(request.getParameter("seokeywords"))  + "' ";
        query += ");";
		
        System.out.println(query); 
		
		PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);   
    
		pstmt.executeUpdate();   
		ResultSet keys = pstmt.getGeneratedKeys();   
		  
		keys.next();
		int key = 0;    
		key = keys.getInt(1);   
		keys.close();   
		pstmt.close();

		
		 query = "INSERT INTO item_image(ItemId, ItemImageName, ItemThumbnailImage)  VALUES (" + key + ", 'unknown.jpg', 'unknown.jpg')";
		 statement.executeUpdate(query);
		 

        response.sendRedirect("design.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("item_brands"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO item_brand(ItemBrandName, ItemBrandDescription)";
        query += " VALUES ( '" + request.getParameter("name") + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("desc"))     + "' ";
        query += ");";
		
        //System.out.println(query);
        statement.executeUpdate(query); 

        response.sendRedirect("brands.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("newPage"))
{
    try
    { 
        String query = "";
        int pageType = Integer.valueOf(request.getParameter("typeId"));
        int pageSlider = Integer.valueOf(request.getParameter("sliderId"));		
        String pageDetails = "-";
		
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM  page_template WHERE PageTemplateId=" + pageType + ";");

        while(rs.next()){							
                pageDetails = rs.getString(3);		
        }

        query =  "INSERT INTO info(InfoName, InfoDescription)";
        query += " VALUES ( '" + request.getParameter("name") + "', '" + Database.encodeHTML(pageDetails) + "'";
        query += ");";
		
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("pages.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


else if(request.getParameter("form").equals("newPageTemplate"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO page_template(TemplateName, TemplateMarkup)";
        query += " VALUES ( '" + request.getParameter("name") + "', 'Place content here...'";
        query += ");";
		
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("page-templates.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("template"))
{
        try
    { 
        String query = "";

        query =  "INSERT INTO template(TemplateName, TemplateBody)";
        query += " VALUES ( '" + request.getParameter("name") + "', ";
        query += "' '";
        query += ");";
		
        //System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("templates.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("tax"))
{
        try
    { 
        String query = "";

        query =  "INSERT INTO tax(TaxState, TaxRate)";
        query += " VALUES ( '" + request.getParameter("state") + "', ";
        query += "'" + request.getParameter("rate")     + "' ";
        query += ");";
		
        //System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("tax.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("categories"))
{
        try
    { 
        String query = "";

        query =  "INSERT INTO category(CategoryName)";
        query += " VALUES ( '" + request.getParameter("name") + "' ";
        query += ");";
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("index.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("item_categories"))
{
	try
        {
            String query = "";
            String ItemId =  request.getParameter("ItemId") ;
            String CategoryId =  request.getParameter("CategoryId") ;
			
            //first check to see if category is already been added
            query =  "SELECT count(*) FROM item_category i WHERE ItemId= " + ItemId + " AND CategoryId = " + CategoryId + ";";
            System.out.println(query);
            rs = statement.executeQuery(query);
			
            if (rs.next())
            {
                if(rs.getInt(1)> 0)
                {
                    response.sendRedirect("products.jsp?id=" + ItemId + "&ErrorMsg=" + "Category is already associated with the item!"); 
                }
                else
                {					
                    query =  "INSERT INTO item_category(CategoryId, ItemId)";
                    query += " VALUES ( " + CategoryId + ", ";
                    query += "'" + ItemId + "'";
                    query += ");";
                    System.out.println(query);
                    statement.executeUpdate(query); 			

                    response.sendRedirect("products.jsp?id=" + ItemId + "&SuccessMsg=" + "Category Successfully Associated with Item!");		
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
}
else if(request.getParameter("form").equals("item_options"))
{
        try
    { 
        String query = "";

        query =  "INSERT INTO item_option(ItemOptionType, ItemOptionDescription)";
        query += " VALUES ( '" + request.getParameter("type") + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("desc")) + "'";
        query += ");";
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("options.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("item_options_available"))
{
        try
    { 
        String query = "";

        query =  "INSERT INTO item_option_available(ItemId, ItemOptionId, ItemAvailabilityId, ItemQuantity)";
        query += " VALUES ( " + request.getParameter("ItemId") + ", ";
        query += "" + request.getParameter("optionId")     + ", ";        
        query += "" + request.getParameter("availability")    + ", ";
        query += "" + request.getParameter("quantity")     + " ";
        query += ");";
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("index.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("blogPost"))
{
    try
    { 
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date tDate = new java.util.Date();
	
        String query = "";

        query =  "INSERT INTO blog_post(PostTitle, PostBody, PostPicURL,PostDate, PostTags, BlogId, UserId, PostRating)";
        query += " VALUES ( '" + Database.encodeHTML(request.getParameter("PostTitle")) + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("body"))     + "', ";
        query += "'" +    request.getParameter("PostImage") + "', ";  
        query += "'" +   df.format(tDate).substring(0,19) + "', ";
        query += "'" + request.getParameter("PostTags")     + "', ";
        query += "'" + request.getParameter("blogId") + "', ";
        query += "'" +  request.getParameter("UserId")   + "', ";
        query += "'" + request.getParameter("PostRating")   + "' ";											
        query += ");";
		
        System.out.println(query);
		System.out.println(request.getParameter("body"));
        statement.executeUpdate(query); 


        response.sendRedirect("blogs.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("siteAttribute"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO site_attribute(AttributeKey, AttributeValue, AttributeType)";
        query += " VALUES ( '" + request.getParameter("key") + "', ";
        query += "'" + request.getParameter("value")     + "', ";        
        query += "'" + request.getParameter("type")    + "' ";
        query += ");";
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("attributes.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("newFolder"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO site_folder(SiteFolderName, SiteFolderDescription, SiteFolderRank)";
        query += " VALUES ( '" + request.getParameter("name") + "', ";
        query += "'" + request.getParameter("desc")     + "', ";        
        query += "'" + request.getParameter("rank")    + "' ";
        query += ");";
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("files.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("mailinglist"))
{
    try
    { 
		statement = connection.createStatement();
		rs = statement.executeQuery("SELECT MAX(MailinglistId) FROM  mailinglist;");
		rs.next();
		String query = "INSERT INTO mailinglist(MailinglistId, MailinglistName, MailinglistEmail) VALUES( " + ( Integer.parseInt(rs.getString(1)) +1 ) +  ",'" + request.getParameter("Name")+ "' ,'"+ request.getParameter("EMailAddress") +"')";
		statement.executeUpdate(query);

        response.sendRedirect("newsletter.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("newUser"))
{
    try
    { 
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT count(*) FROM  user WHERE UserName='" + request.getParameter("name") + "';");
        rs.next();				

        if(rs.getInt(1) > 0 )
        {
            response.sendRedirect("users.jsp?error=UserNameTaken");			
        }
        else
        {
            if(request.getParameter("pass1") != null && request.getParameter("pass2")!= null &&  !(request.getParameter("pass1").equals(request.getParameter("pass2"))))
            {
                    response.sendRedirect("users.jsp?error=PasswordsDon'tMatch");	
            }

            if(request.getParameter("email") != null && request.getParameter("email2")!= null &&  !(request.getParameter("email").equals(request.getParameter("email2"))))
            {				
                    response.sendRedirect("users.jsp?error=EmailAddressesDon'tMatch");
            }
            else
            {	       
                String query = "INSERT INTO contact(FirstName, LastName, Email, Phone, Fax, Title, Position) VALUES('-','-','-','-','-','-','-')";                
                System.out.println(query);
                statement.executeUpdate(query);
                
                String contactId = "0";
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from contact;");
                while(rs.next())
                {
                    contactId =  rs.getString(1);
                }

                query = "INSERT INTO address(Address1, City, State, ZipCode, Country, Region) VALUES('-','-','-','-','-','-')";                
                System.out.println(query);
                statement.executeUpdate(query);
                
                String addressId = "0";
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from address;");
                while(rs.next())
                {
                    addressId =  rs.getString(1);
                }

                //java.util.Random, by default initializes to System.currentTimeMillis()
                int randomNumber = new Random().nextInt(999998) + 2; //minimum 2
                query = "INSERT INTO user(UserName, UserPassword, UserImgURL, UserEmail, UserSecurityQuestion, UserSecurityAnswer, UserEmailConfirmed, UserWebUrl, ContactId, AddressId) VALUES('" 
                               + request.getParameter("name") + "' ,'" 
                                + request.getParameter("pass1") + "', '" 
                                + request.getParameter("imgUrl") + "','" 
                                + request.getParameter("email") + "','" 
                                + request.getParameter("securityQuestion") + "','" 
                                + request.getParameter("securityAnswer") + "','" 
                                + randomNumber + "','" 
                                + request.getParameter("webUrl") + "','"
                                + contactId + "','"
                                + addressId + "')";
                System.out.println(query);
                statement.executeUpdate(query);

                query = "INSERT INTO user_role(UserName, RoleName) VALUES( '" + request.getParameter("name") + "','" + request.getParameter("role")+ "')";
                System.out.println(query);
                statement.executeUpdate(query);

                //get siteInfo fdor the site
                SiteInfo info = Database.getSiteInfo();

                String body = "<br> Please click here to confirm your email address:" 
                           + " <a href=\"" + (info.getSiteMode()== 1 ? info.getSiteUrl() : info.getSiteTestingUrl()) + "/ConfirmUserEmail?EMail=" + request.getParameter("email") + "&Key=" + randomNumber + "\">Click Here...</a>";

                //prepare a confirmation email to send to the user to confirm their email address
                EmailComposer email = new EmailComposer(request.getParameter("name"), "Confirmation Request:", body , request.getParameter("email"), info.getAdminEmail(), request.getServletContext().getRealPath("/images-site/"));
                email.prepareEmail();
                email.sendEmail();

                response.sendRedirect("users.jsp?msg=AddedNewUser");
            }
        }
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
else if(request.getParameter("form").equals("blog"))
{
        try
    { 
        String query = "";

        query =  "INSERT INTO blog(BlogName)";
        query += " VALUES ( '" + request.getParameter("name") + "' ";
        query += ");";
        System.out.println(query);
        statement.executeUpdate(query); 


        response.sendRedirect("blogs.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("form"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO form(FormName, FormDescription, FormSubmissionEmail, FormSubmissionMethod, FormAction, FormResetPresent)";
        query += " VALUES ( '" + request.getParameter("name") + "', ";
        query += "'" + Database.encodeHTML(request.getParameter("desc"))     + "', ";
        query += "'" + request.getParameter("submissionEmail")     + "', ";
        query += "'" + request.getParameter("submissionMethod")     + "', ";
        query += "'" + request.getParameter("submissionAction")     + "', ";
        query += "" + request.getParameter("resettable")     + " ";
        query += ");";
		
        //System.out.println(query);
        statement.executeUpdate(query); 

        response.sendRedirect("forms.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


else if(request.getParameter("form").equals("form-field"))
{
    try
    { 
        String query = "";

        String optional = request.getParameter("optional");
		
		if(optional == null)
		{
			optional = "0";
		}
		
		query =  "INSERT INTO form_field(FormId, FieldName, FieldDataType, FieldLabel, FieldErrorText, FieldValidationRegex, FieldRank, FieldDefaultValue, FieldOptions, FieldOptional)";
		
        query += " VALUES ( '" + request.getParameter("formId") + "', ";
        query += "'" + request.getParameter("name")     + "', ";
        query += "'" + request.getParameter("dataType") + "', ";
        query += "'" + request.getParameter("label")    + "', ";
        query += "'" + request.getParameter("errorText")+ "', ";
        query += "'" + request.getParameter("validationRegex")  + "', ";
        query += "'" + request.getParameter("rank")     + "', ";
        query += "'" + request.getParameter("defaultValue")     + "', ";
        query += "'" + request.getParameter("options")  + "', ";
        query += "'" + optional  + "' ";
        query += ");";
		
        System.out.println(query);
        statement.executeUpdate(query); 

        response.sendRedirect("forms.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}



else if(request.getParameter("form").equals("slider"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO slider(SliderName, SliderTypeId, FormId)";
        query += " VALUES ( '" + request.getParameter("name") + "', ";
        query += "" + request.getParameter("type") + ", ";
        query += "" + request.getParameter("formId") + " ";
        query += ");";
		
        System.out.println(query);
        statement.executeUpdate(query); 

        response.sendRedirect("sliders.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


else if(request.getParameter("form").equals("sliderItem"))
{
    try
    { 
        String query = "";

        query =  "INSERT INTO slider_item(SliderItemTitle, SliderItemDescription, SliderItemUrl, SliderItemImageName, SliderItemImageAlt, SliderItemRank, SliderId)";
        query += " VALUES ( '" + request.getParameter("title") + "', ";
        query += "'" + request.getParameter("desc")     	+ "', ";
        query += "'" + request.getParameter("url") 			+ "', ";
        query += "'" + request.getParameter("imageName")    + "', ";
        query += "'" + request.getParameter("imageAlt")     + "', ";
        query += "" + request.getParameter("rank")     	+ ", ";
        query += "" + request.getParameter("sliderId")  	+ "";
        query += ");";
		
        System.out.println(query);
        statement.executeUpdate(query); 

        response.sendRedirect("sliders.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

else if(request.getParameter("form").equals("addUserRole"))
{
    try
    { 
		statement = connection.createStatement();
		rs = statement.executeQuery("SELECT count(*) FROM  user_role WHERE UserName='" + request.getParameter("name") + "' AND RoleName='" + request.getParameter("role")+ "';");
		rs.next();				
		
		if(rs.getInt(1) > 0 )
		{
			response.sendRedirect("users.jsp?error=UserRoleAlreadyThere");			
		}
		else
		{
				String query = "INSERT INTO user_role(UserName, RoleName) VALUES( '" + request.getParameter("name") + "','" 
				        + request.getParameter("role")+ "')";
				statement.executeUpdate(query);
				
				response.sendRedirect("users.jsp?msg=AddedNewUserRole!");

			
		}
		
		

    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("addNewStringKey"))
{
    statement = connection.createStatement();
    
    try
    { 
		if(Database.isStringKeyTaken(request.getParameter("key")))
		{
			response.sendRedirect("strings.jsp?error=Key already Exists!");
		}
		else
		{
			String id = "";
			String query = "";			
			query = "INSERT INTO text_string(TextStringKey) VALUES('" + request.getParameter("key") + "')";
						
			System.out.println(query);
			statement.executeUpdate(query);
			
			//find the id of the just inserted key
			rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from text_string;");
            while(rs.next())
            {
                id =  rs.getString(1);
            } 
			
			//now add records for each locality
			
			rs = statement.executeQuery("SELECT * FROM  locale;");
			while(rs.next())
			{
				query = "INSERT INTO localized_string(StringId, LocaleId, StringValue) VALUES(?,?,'-')";
				PreparedStatement pstmt2 = connection.prepareStatement( query );
				pstmt2.setString( 1, id );
				pstmt2.setString( 2, rs.getString(1) );
				System.out.println(query);
				pstmt2.executeUpdate();
			}				
	
		
	
			response.sendRedirect("strings.jsp");
		}
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
%>