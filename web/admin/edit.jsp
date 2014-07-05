
<%@ page import="com.busy.dao.Address"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.util.*" %>
<%@ page import="com.transitionsoft.*" %>
<%@ page import="com.transitionsoft.dao.*" %>
<%@ page import="
java.io.*,
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
statement = connection.createStatement();   


if(request.getParameter("form").equals("post"))
{
    try
    { 
        String query = "";
        query =  "UPDATE blog_post SET PostBody = ? WHERE PostTitle LIKE '" + request.getParameter("title") + "';";
        String description = request.getParameter("body");
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, description );
        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("information.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("info"))
{
    try
    { 
        String query = "";
        query =  "UPDATE info SET InfoDescription = ? WHERE InfoName LIKE '" + request.getParameter("name") + "';";
        String description = request.getParameter("desc").replaceAll("\n", "");
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, description );
        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("pages.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}



if(request.getParameter("form").equals("pageTemplate"))
{
    try
    { 
        String query = "";
        query =  "UPDATE page_template SET TemplateMarkup = ? WHERE TemplateName LIKE '" + request.getParameter("name") + "';";
        String description = request.getParameter("desc").replaceAll("\n", "");
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, description );
        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("page-templates.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("item"))
{
    try
    { 
        String query = "UPDATE item SET ItemName = ?, ItemDescription = ?, ItemBrandId = ?, ItemListPrice = ?, ItemPrice = ?, ItemPriceAdjustment = ?, ItemSEOTitle = ?, ItemSEODescription = ?, ItemSEOKeywords = ?, ItemShortDescription = ? WHERE ItemId = " + request.getParameter("id") + ";";
		
        String name = request.getParameter("name");
		String description = Database.encodeHTML(request.getParameter("desc"));
		String brandId = request.getParameter("brandId");
		String listPrice = request.getParameter("listPrice"); 
		String price = request.getParameter("price");        
		String adjust = request.getParameter("adjust");
		String seotitle = request.getParameter("seotitle");
		String seodesc = request.getParameter("seodesc");
		String seokeywords = request.getParameter("seokeywords");
		String shortDesc = Database.encodeHTML(request.getParameter("shortDesc"));
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, description );
		pstmt.setString( 3, brandId );
		pstmt.setString( 4, listPrice );
		pstmt.setString( 5, price );
		pstmt.setString( 6, adjust );
		pstmt.setString( 7, seotitle );
		pstmt.setString( 8, seodesc );
		pstmt.setString( 9, seokeywords );
		pstmt.setString( 10, shortDesc );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("index.jsp?id=" + request.getParameter("id"));
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("itemDesign"))
{
    try
    { 
        String query = "";
        query =  "UPDATE item SET ItemName = ?, ItemDescription = ?, ItemBrandId = ?, ItemType = ?, ItemRating = ?, ItemSEOTitle = ?, ItemSEODescription = ?, ItemSEOKeywords = ? WHERE ItemId = " + request.getParameter("id") + ";";
        String name = request.getParameter("name");
		String description = Database.encodeHTML(request.getParameter("desc"));
		String brandId = request.getParameter("brandId");
		String type = request.getParameter("type"); 
		String rating = request.getParameter("rating");  
		String seotitle = request.getParameter("seotitle");
		String seodesc = request.getParameter("seodesc");
		String seokeywords = request.getParameter("seokeywords");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, description );
		pstmt.setString( 3, brandId );
		pstmt.setString( 4, type );
		pstmt.setString( 5, rating );
		pstmt.setString( 6, seotitle );
		pstmt.setString( 7, seodesc );
		pstmt.setString( 8, seokeywords );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("designs.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("shipping"))
{
    try
    { 
        String query = "";
        query =  "UPDATE shipping SET ShippingRegion = ?, ShippingMethod = ?, ShippingRate = ?, ShippingAdditional = ? WHERE ShippingId = " + request.getParameter("id") + ";";
        String region = request.getParameter("region");
		String method = request.getParameter("method");
		String catId = request.getParameter("catId");
		String additional = request.getParameter("additional"); 
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, region );
		pstmt.setString( 2, method );
		pstmt.setString( 3, catId );
		pstmt.setString( 4, additional );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("shipping.jsp#" + request.getParameter("id"));
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("siteAttribute"))
{
    try
    { 
        String query = "";
        query =  "UPDATE site_attribute SET AttributeKey = ?, AttributeValue = ?, AttributeType = ? WHERE SiteAttributeId = " + request.getParameter("id") + ";";
        String key = request.getParameter("key");
		String value = request.getParameter("value");
		String type = request.getParameter("type");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, key );
		pstmt.setString( 2, value );
		pstmt.setString( 3, type );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("attributes.jsp?type=" + type);
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("pageForm"))
{
    try
    { 
        String query = "";
        query =  "UPDATE info SET FormId = ? WHERE InfoId = " + request.getParameter("pageId") + ";";
        String formId = request.getParameter("formId");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, formId );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("pages.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("pageSlider"))
{
    try
    { 
        String query = "";
        query =  "UPDATE info SET SliderId = ? WHERE InfoId = " + request.getParameter("pageId") + ";";
        String sliderId = request.getParameter("sliderId");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, sliderId );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("pages.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("changeFileInfo"))
{
    try
    { 
        String query = "";
        query =  "UPDATE site_file SET FileDescription = ?, FileLabel = ?, FolderId = ? WHERE SiteFileId = " + request.getParameter("fileId") + ";";
        String desc = request.getParameter("desc");
        String label = request.getParameter("label");
        String folderId = request.getParameter("folderId");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, desc );
		pstmt.setString( 2, label );
		pstmt.setString( 3, folderId );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("files.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("pageSeo"))
{
    try
    { 
        String query = "";
        query =  "UPDATE info SET InfoSeoTitle = ?, InfoSeoDescription = ?, InfoSeoKeywords = ? WHERE InfoId = " + request.getParameter("pageId") + ";";
		
        String title = request.getParameter("seoTitle");
        String desc = request.getParameter("seoDescription");
        String keywords = request.getParameter("seoKeywords");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, title );
		pstmt.setString( 2, desc );
		pstmt.setString( 3, keywords );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("pages.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("template"))
{
    try
    { 
        String query = "";
        query =  "UPDATE template SET TemplateName = ?, TemplateBody = ? WHERE TemplateId = " + request.getParameter("id") + ";";
        String name = request.getParameter("name");
        String body = request.getParameter("body"); //do not encode
        body = body.replaceAll("\r\n", "");
        body = body.replaceAll("\n", "");
        body = body.replaceAll("\r", "");
        
        PreparedStatement pstmt = connection.prepareStatement( query );

        pstmt.setString( 1, name );
        pstmt.setString( 2, body );

        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("templates.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("item_brands"))
{
    try
    { 
        String query = "";
        query =  "UPDATE item_brand SET ItemBrandName = ?, ItemBrandDescription = ? WHERE ItemBrandId = " + request.getParameter("id") + ";";
        String name = request.getParameter("name");
		String desc = request.getParameter("desc");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, desc );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("brands.jsp#" + request.getParameter("id"));
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("tax"))
{
    try
    { 
        String query = "";
        query =  "UPDATE tax SET TaxState = ?, TaxRate = ? WHERE TaxId = " + request.getParameter("id") + ";";
        String state = request.getParameter("state");
		String rate = request.getParameter("rate");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, state );
		pstmt.setString( 2, rate );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("tax.jsp#" + request.getParameter("id"));
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("order"))
{
    try
    { 
        String query = "";
        query =  "UPDATE order SET OrderStatus = ? WHERE OrderId = " + request.getParameter("id") + ";";
        String status = request.getParameter("status");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, status );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("orders.jsp?status=paid");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("blogPost"))
{

    try
    { 
        String query = "";
        String PostId = request.getParameter("PostId");
        String PostTitle = Database.encodeHTML(request.getParameter("PostTitle"));
        String body = Database.encodeHTML(request.getParameter("body"));
        String BlogId = request.getParameter("blogId");
        String PostTags = request.getParameter("PostTags");
        String PostRating = request.getParameter("PostRating");
        String PostImage = request.getParameter("PostImage");
		
        query =  "UPDATE blog_post SET PostTitle = ?, PostBody = ?, PostTags = ?,  BlogId = ?, " 
		       + "PostRating = ?, PostPicURL = ? WHERE BlogPostId = '" + PostId + "';";
		
        PreparedStatement pstmt = connection.prepareStatement( query );
		
        pstmt.setString( 1, PostTitle );
		pstmt.setString( 2, body );
		pstmt.setString( 3, PostTags );
		pstmt.setString( 4, BlogId );
		pstmt.setString( 5, PostRating );
		pstmt.setString( 6, PostImage );
		
        System.out.println(query);
		//System.out.println(body);
        pstmt.executeUpdate();

        response.sendRedirect("blogs.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("stringLiteral"))
{

    try
    { 
        String query = "";
        String stringId = request.getParameter("stringId");
        String localeId = request.getParameter("localeId");
        String value = request.getParameter("value");
		
        query =  "UPDATE localized_string SET StringValue = ? WHERE StringId = '" + stringId + "' AND LocaleId = '" + localeId + "';";
		
        PreparedStatement pstmt = connection.prepareStatement( query );
		
        pstmt.setString( 1, value );
		
        System.out.println(query);
	System.out.println("Value: " + value);
        pstmt.executeUpdate();

        response.sendRedirect("string.jsp?id=" + stringId);
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("changeDefaultLocale"))
{
    try
    { 
        String query = "";
        query =  "UPDATE store_info SET StoreLocalization = ? WHERE StoreInfoId = 1;";
        String locale = request.getParameter("locale");
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, locale );
        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("apply.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("ChangeSiteInfo"))
{
    try
    { 
        String query = "";
        query =  "UPDATE site_info SET SiteAdministratorName = ?, SiteAdministratorEmail = ? , SiteNotificationEmail = ?, SiteURL = ?, SiteTestingURL = ?, SiteMode = ? , SiteTestingEmail = ?, SiteTestingEmailPassword = ? WHERE SiteInfoId = " + request.getParameter("id") + ";";
		
        String name = request.getParameter("AdministratorName");
		String email = request.getParameter("AdministratorEmail");		
		String notificationEmail = request.getParameter("NotificationEmail");
		String SiteURL = request.getParameter("SiteURL");
		String SiteTestingURL = request.getParameter("SiteTestingURL");
		String SiteMode = request.getParameter("SiteMode");
		String SiteTestingEmail = request.getParameter("SiteTestingEmail");
		String SiteTestingEmailPassword = request.getParameter("SiteTestingEmailPassword");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, email );		
		pstmt.setString( 3, notificationEmail );
		pstmt.setString( 4, SiteURL );
		pstmt.setString( 5, SiteTestingURL );
		pstmt.setString( 6, SiteMode );
		pstmt.setString( 7, SiteTestingEmail );
		pstmt.setString( 8, SiteTestingEmailPassword );
        
		System.out.println(query);

        pstmt.executeUpdate();

		//Send Notification of changes made
		//MailerBean.sendNotification("Armolan.us Admin: Store information changed!", "Hi,<br>This is to inform you,  site administrator that the site information has been changed.");
		
        response.sendRedirect("info.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("ChangeStoreInfo"))
{
    try
    { 
        String query = "";
        query =  "UPDATE store_info SET StoreLogoTitle = ?, StoreLogoImage = ?, StoreName = ?, CompanyName = ? WHERE StoreInfoId = " + request.getParameter("id") + ";";
        String title = request.getParameter("StoreLogoTitle");
		String url = request.getParameter("StoreLogoImageURL");
		String Store = request.getParameter("StoreName");
		String Company = request.getParameter("CompanyName");   
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, title );
		pstmt.setString( 2, url );
		pstmt.setString( 3, Store );
		pstmt.setString( 4, Company );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("info.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("formField"))
{
    try
    { 
        String query = "";
        query =  "UPDATE form_field SET FieldName = ?, FieldDataType = ?, FieldLabel = ?, FieldErrorText = ?, FieldValidationRegex = ?, FieldRank = ?, FieldDefaultValue = ?, FieldOptions = ?, FieldOptional = ? WHERE FormFieldId = " + request.getParameter("fieldId") + ";";
        String name = request.getParameter("name");
		String dataType = request.getParameter("dataType");
		String label = request.getParameter("label");
		String errorText = request.getParameter("errorText"); 
		String validationRegex = request.getParameter("validationRegex");
		String rank = request.getParameter("rank");
		String defaultValue = request.getParameter("default");
		String optional = request.getParameter("optional");
		String options = request.getParameter("options");  
        
                if(optional != null) 
                {
                    if(optional.equals("0"))
                    {
                        optional = "1";
                    }                    
                    if(optional.equals("1")) 
                    {
                        optional = "1";
                    }
                }
                else
                {
                    optional = "0";
                }
                
                
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, dataType );
		pstmt.setString( 3, label );
		pstmt.setString( 4, errorText );
		pstmt.setString( 5, validationRegex );
		pstmt.setString( 6, rank );
		pstmt.setString( 7, defaultValue );
		pstmt.setString( 8, options );
		pstmt.setString( 9, optional );		
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("forms.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("form-parameters"))
{
	try
    { 
        String query = "";
        query =  "UPDATE form SET FormName = ?, FormDescription = ?, FormSubmissionEmail = ? WHERE FormId = " + request.getParameter("formId") + ";";
        String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String email = request.getParameter("email"); 
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, desc );
		pstmt.setString( 3, email );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("forms.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("slider"))
{
    try
    { 
        String query = "";
        query =  "UPDATE slider SET SliderName = ?, SliderTypeId = ?, FormId = ? WHERE SliderId = " + request.getParameter("sliderId") + ";";
        String name = request.getParameter("sliderName");
		String type = request.getParameter("sliderType");
		String formId = request.getParameter("formId");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, name );
		pstmt.setString( 2, type );
		pstmt.setString( 3, formId );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("sliders.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("sliderItem"))
{
    try
    { 
        String query = "";
        query =  "UPDATE slider_item SET SliderItemTitle = ?, SliderItemDescription = ?, SliderItemUrl = ?, SliderItemImageName = ?, SliderItemImageAlt = ?, SliderItemRank = ? WHERE SliderItemId = " + request.getParameter("sliderItemId") + ";";
        String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String itemUrl = request.getParameter("itemUrl");
		String imageName = request.getParameter("imageName"); 
		String imageAlt = request.getParameter("imageAlt");
		String rank = request.getParameter("rank");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, title );
		pstmt.setString( 2, desc );
		pstmt.setString( 3, itemUrl );
		pstmt.setString( 4, imageName );
		pstmt.setString( 5, imageAlt );
		pstmt.setString( 6, rank );
        
		System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("sliders.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("ChangePayPalProfileInfo"))
{
    try
    { 
        String query = "";
        query =  "UPDATE paypal SET PayPalURL = ?, CurrencyCode = ?, APIUserName = ?, APIPassword = ?, APISignature = ?, APIEndpoint = ?, ActiveProfile = ?, ReturnURL = ?, CancelURL = ?, PaymentType = ?, Environment = ? WHERE PaypalId = " + request.getParameter("id") + ";";
        
		String PayPalURL = request.getParameter("PayPalURL");
		String CurrencyCode = request.getParameter("Currency");
		String APIUserName = request.getParameter("APIUserName");
		String APIPassword = request.getParameter("APIPassword"); 
		String APISignature = request.getParameter("APISignature");  
		String APIEndpoint = request.getParameter("APIEndpoint");
		String ActiveProfile = request.getParameter("ActiveProfile");
		String ReturnURL = request.getParameter("ReturnURL");
		String CancelURL = request.getParameter("CancelURL");
		String PaymentType = request.getParameter("PaymentType");
		String Environment = request.getParameter("Environment");
        
		PreparedStatement pstmt = connection.prepareStatement( query );
        
		pstmt.setString( 1, PayPalURL );
		pstmt.setString( 2, CurrencyCode );
		pstmt.setString( 3, APIUserName );
		pstmt.setString( 4, APIPassword );
		pstmt.setString( 5, APISignature );
		pstmt.setString( 6, APIEndpoint );
		pstmt.setString( 7, ActiveProfile );
		pstmt.setString( 8, ReturnURL );
		pstmt.setString( 9, CancelURL );
		pstmt.setString( 10, PaymentType );
		pstmt.setString( 11, Environment );
        
		System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("info.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
if(request.getParameter("form").equals("ChangeSiteImageInfo"))
{
    try
    { 
        String query = "";
        query =  "UPDATE image SET TypeId = ? WHERE ImageId = " + request.getParameter("id") + ";";

        String type = request.getParameter("typeId"); 

        PreparedStatement pstmt = connection.prepareStatement( query );

        pstmt.setString( 1, type );

        System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("images.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
if(request.getParameter("form").equals("ChangeImageProperties"))
{
    try
    { 
        String query = "";
        query =  "UPDATE image SET LinkUrl = ?, Description = ?, Rank = ?  WHERE ImageId = " + request.getParameter("id") + ";";

        String linkUrl = request.getParameter("LinkUrl");
        String description = request.getParameter("Description");
        String rank = request.getParameter("Rank"); 

        PreparedStatement pstmt = connection.prepareStatement( query );

        pstmt.setString( 1, linkUrl );
        pstmt.setString( 2, description );
        pstmt.setString( 3, rank );

        System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("images.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("OneColumnItemLayout"))
{
    try
    { 
        String query = "";
		query =  "UPDATE site_attribute SET AttributeValue = '650' WHERE AttributeKey = 'ItemLayoutHeight';";
		System.out.println(query);
        statement.executeUpdate(query); 
        
		query =  "UPDATE site_attribute SET AttributeValue = '5' WHERE AttributeKey = 'ItemLayoutItemsPerPage';";
		System.out.println(query);
        statement.executeUpdate(query); 
        
		query =  "UPDATE site_attribute SET AttributeValue = '1' WHERE AttributeKey = 'ItemLayoutColumnsPerPage';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '-webkit-border-radius: 5px 5px 12px 12px; border-radius: 5px 5px 12px 12px;' WHERE AttributeKey = 'ItemLayoutBorderRadius';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = 'text-shadow: 1px 1px 1px #dbd8db; filter: dropshadow(color=#dbd8db, offx=1, offy=1);' WHERE AttributeKey = 'ItemLayoutTextShadow';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '-webkit-box-shadow: 5px 5px 5px 5px rgba(100, 100, 100, .3); box-shadow: 5px 5px 5px 5px rgba(100, 100, 100, .3);' WHERE AttributeKey = 'ItemLayoutBoxShadow';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = 'background-color: rgba(255, 255, 255, 1); color: rgba(255, 255, 255, 1);' WHERE AttributeKey = 'ItemLayoutBackgroundColor';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '80' WHERE AttributeKey = 'ItemLayoutMargin';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '35' WHERE AttributeKey = 'ItemLayoutDisplacement';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '350' WHERE AttributeKey = 'ItemLayoutFooterWidth';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '25' WHERE AttributeKey = 'ItemLayoutItemMsrpSize';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '30' WHERE AttributeKey = 'ItemLayoutItemPriceSize';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '1px solid #ccc' WHERE AttributeKey = 'ItemLayoutItemBorder';";
		System.out.println(query);
        statement.executeUpdate(query);


        response.sendRedirect("layouts.jsp?operationResult=Successful");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("TwoColumnItemLayout"))
{
    try
    { 
        String query = "";
		query =  "UPDATE site_attribute SET AttributeValue = '345' WHERE AttributeKey = 'ItemLayoutHeight';";
		System.out.println(query);
        statement.executeUpdate(query); 
        
		query =  "UPDATE site_attribute SET AttributeValue = '12' WHERE AttributeKey = 'ItemLayoutItemsPerPage';";
		System.out.println(query);
        statement.executeUpdate(query); 
        
		query =  "UPDATE site_attribute SET AttributeValue = '2' WHERE AttributeKey = 'ItemLayoutColumnsPerPage';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '-webkit-border-radius: 5px 5px 12px 12px; border-radius: 5px 5px 12px 12px;' WHERE AttributeKey = 'ItemLayoutBorderRadius';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = 'text-shadow: 1px 1px 1px #dbd8db; filter: dropshadow(color=#dbd8db, offx=1, offy=1);' WHERE AttributeKey = 'ItemLayoutTextShadow';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '-webkit-box-shadow: 5px 5px 5px 5px rgba(100, 100, 100, .3); box-shadow: 5px 5px 5px 5px rgba(100, 100, 100, .3);' WHERE AttributeKey = 'ItemLayoutBoxShadow';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = 'background-color: rgba(255, 255, 255, 1); color: rgba(255, 255, 255, 1);' WHERE AttributeKey = 'ItemLayoutBackgroundColor';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '70' WHERE AttributeKey = 'ItemLayoutMargin';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '35' WHERE AttributeKey = 'ItemLayoutDisplacement';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '200' WHERE AttributeKey = 'ItemLayoutFooterWidth';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '20' WHERE AttributeKey = 'ItemLayoutItemMsrpSize';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '25' WHERE AttributeKey = 'ItemLayoutItemPriceSize';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '1px solid #ccc' WHERE AttributeKey = 'ItemLayoutItemBorder';";
		System.out.println(query);
        statement.executeUpdate(query);


        response.sendRedirect("layouts.jsp?operationResult=Successful");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("ThreeColumnItemLayout"))
{
    try
    { 
        String query = "";
		query =  "UPDATE site_attribute SET AttributeValue = '225' WHERE AttributeKey = 'ItemLayoutHeight';";
		System.out.println(query);
        statement.executeUpdate(query); 
        
		query =  "UPDATE site_attribute SET AttributeValue = '15' WHERE AttributeKey = 'ItemLayoutItemsPerPage';";
		System.out.println(query);
        statement.executeUpdate(query); 
        
		query =  "UPDATE site_attribute SET AttributeValue = '3' WHERE AttributeKey = 'ItemLayoutColumnsPerPage';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '-webkit-border-radius: 5px 5px 12px 12px; border-radius: 5px 5px 12px 12px;' WHERE AttributeKey = 'ItemLayoutBorderRadius';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = 'text-shadow: 1px 1px 1px #dbd8db; filter: dropshadow(color=#dbd8db, offx=1, offy=1);' WHERE AttributeKey = 'ItemLayoutTextShadow';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '-webkit-box-shadow: 5px 5px 5px 5px rgba(100, 100, 100, .3); box-shadow: 5px 5px 5px 5px rgba(100, 100, 100, .3);' WHERE AttributeKey = 'ItemLayoutBoxShadow';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = 'background-color: rgba(255, 255, 255, 1); color: rgba(255, 255, 255, 1);' WHERE AttributeKey = 'ItemLayoutBackgroundColor';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '40' WHERE AttributeKey = 'ItemLayoutMargin';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '35' WHERE AttributeKey = 'ItemLayoutDisplacement';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '140' WHERE AttributeKey = 'ItemLayoutFooterWidth';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '15' WHERE AttributeKey = 'ItemLayoutItemMsrpSize';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '20' WHERE AttributeKey = 'ItemLayoutItemPriceSize';";
		System.out.println(query);
        statement.executeUpdate(query);
        
		query =  "UPDATE site_attribute SET AttributeValue = '1px solid #ccc' WHERE AttributeKey = 'ItemLayoutItemBorder';";
		System.out.println(query);
        statement.executeUpdate(query);


        response.sendRedirect("layouts.jsp?operationResult=Successful");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("menu"))
{
    try
    { 
        String query = "";
		
		if(request.getParameter("type").equals("separator-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h1' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("underline-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h2' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("box-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h3' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("object-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h4' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("single-tabbed-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h5' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("full-tabbed-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h6' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("round-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h7' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("box-full-width-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h8' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("round-wide-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h9' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("underline-wide-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h10' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("square-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h11' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("rounded-square-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h13' WHERE AttributeKey = 'MainMenuStyle';";
		}
		if(request.getParameter("type").equals("advanced-square-menu")) 
		{
			query =  "UPDATE site_attribute SET AttributeValue = 'h14' WHERE AttributeKey = 'MainMenuStyle';";
		}
		
		System.out.println(query);
        statement.executeUpdate(query); 
        


        response.sendRedirect("layouts.jsp?operationResult=Successful");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("header"))
{
    try
    { 
        String query = "";
		
        if(request.getParameter("type").equals("compact-header")) 
        {
            query =  "UPDATE site_attribute SET AttributeValue = 'compact' WHERE AttributeKey = 'MainHeaderType';";
        }
        if(request.getParameter("type").equals("full-header")) 
        {
            query =  "UPDATE site_attribute SET AttributeValue = 'full' WHERE AttributeKey = 'MainHeaderType';";
        }		

        System.out.println(query);
        statement.executeUpdate(query); 
        
        response.sendRedirect("layouts.jsp?operationResult=Successful");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("blog"))
{
    try
    { 
        String query = "";
        query =  "UPDATE blog SET BlogLayoutType = ?  WHERE BlogId = " + request.getParameter("blogId") + ";";

        String blogLayoutType = request.getParameter("blogLayoutType"); 

        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, blogLayoutType );

        System.out.println(query);

        pstmt.executeUpdate();

        response.sendRedirect("blogs.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("categories"))
{
    try
    { 
        String query = "UPDATE category SET CategoryName = ? WHERE CategoryId = " + request.getParameter("id") + ";";
        String name = request.getParameter("name");
        
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, name );

        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("categories.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}

if(request.getParameter("form").equals("changeUserAddress"))
{
    try
    { 
        Integer addressId = Integer.parseInt(request.getParameter("addressId"));
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        
        Address.updateAddress(addressId, request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("state"), request.getParameter("zipCode"), request.getParameter("country"), request.getParameter("region"), userId);
        response.sendRedirect("users.jsp?id=" + userId);
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}


if(request.getParameter("form").equals("changeUserContact"))
{
    try
    { 
        String query = "UPDATE contact SET Title=?, FirstName=?, LastName=?, Position=?, Phone=?, Fax=?, Email=?, Info=?, UserId=? WHERE ContactId = " + request.getParameter("contactId") + ";";
        String title = request.getParameter("title");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String info = request.getParameter("info");
        String userId = request.getParameter("userId");
        
        PreparedStatement pstmt = connection.prepareStatement( query );

        pstmt.setString( 1, title );
        pstmt.setString( 2, firstName );
        pstmt.setString( 3, lastName );
        pstmt.setString( 4, position );
        pstmt.setString( 5, phone );
        pstmt.setString( 6, fax );
        pstmt.setString( 7, email );
        pstmt.setString( 8, info );
        pstmt.setString( 9, userId );

        System.out.println(query);
        pstmt.executeUpdate();

        response.sendRedirect("users.jsp?id=" + userId);
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
%>
