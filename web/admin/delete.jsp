
<%@ page import="com.transitionsoft.*"%>
<%@ page import="
java.io.*,
java.util.*,
java.text.*,
javax.servlet.*,
javax.servlet.http.*, 
javax.servlet.http.HttpServletRequest,
javax.servlet.ServletInputStream"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="../connection.jsp" %> 
 

<%
    ResultSet rs2 = null;
    statement = connection.createStatement();
    
    
    if(request.getParameter("form").equals("item"))
    {
        try
        { 
            Database.deleteItem(Integer.parseInt(request.getParameter("id")), getServletContext().getRealPath("item-files/"), getServletContext().getRealPath("items/"));
            response.sendRedirect("index.jsp");
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
            String ItemId =  request.getParameter("ItemId") ;
            String ItemOptionId =  request.getParameter("ItemOptionId") ;

            query =  "DELETE FROM item_option_available WHERE ItemId = " + ItemId + " AND ItemOptionId = " + ItemOptionId + ";";
            System.out.println(query);
            statement.executeUpdate(query);
            
            response.sendRedirect("index.jsp" + "?id="  + ItemId);
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
            String CategoryId =  request.getParameter("CategoryId") ;
            String ItemId =  request.getParameter("ItemId") ;

			query =  "DELETE FROM item_category WHERE CategoryId = " + CategoryId + " AND ItemId = " + ItemId + ";";
			System.out.println(query);
			statement.executeUpdate(query);
			
			response.sendRedirect("products.jsp" + "?id=" + ItemId + "&SuccessMsg=" + "Category Removed Successfully!");		

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	else if(request.getParameter("form").equals("categories"))
    {
        try
        { 
            String query = "";
            String CategoryId =  request.getParameter("CategoryId") ;

            query =  "DELETE FROM category WHERE CategoryId = " + CategoryId + ";";
            System.out.println(query);
            statement.executeUpdate(query);
            
            response.sendRedirect("brands.jsp");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    else if(request.getParameter("form").equals("brands"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("BrandId") ;

            query =  "DELETE FROM item_brand WHERE ItemBrandId = " + Id + ";";
            System.out.println(query);
            statement.executeUpdate(query);

            response.sendRedirect("brands.jsp");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(500);
        }
    }
	
	else if(request.getParameter("form").equals("page"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM info WHERE InfoId = " + Id + ";";
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
	
	
	
	else if(request.getParameter("form").equals("pageTemplate"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM page_template WHERE TemplateId = " + Id + ";";
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
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM template WHERE TemplateId = " + Id + ";";
            System.out.println(query);
            statement.executeUpdate(query);

            response.sendRedirect("templates.jsp");
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
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM site_attribute WHERE SiteAttributeId = " + Id + ";";
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
	
    else if(request.getParameter("form").equals("item_images"))
    {
        try
        {
            String query = "";
            String Id = request.getParameter("ItemImageId");
            String ItemId = request.getParameter("ItemId");
			int numberOfImages = 0;

            rs2 = statement.executeQuery("SELECT *, count(*) FROM item_image WHERE ItemId = " + ItemId + ";");
            if (rs2.next())
            {
                //delete all physical files except "unknown.png" which is a default one for new items
                if(!rs2.getString(3).equals("unknown.png"))
                {
                    //delete the image that was residing on the items folder
                    String filePath = getServletContext().getRealPath("/items/") + File.separator + rs2.getString(3);
                    System.out.println(filePath + " was deleted");
                    new File(filePath).delete();

                    //delete thumbnail
                    filePath = getServletContext().getRealPath("/items/") + File.separator + rs2.getString(4);
                    System.out.println(filePath + " was deleted");
                    new File(filePath).delete();
					
                    //record how many images are left
                    numberOfImages = rs2.getInt(6);					
                }
            }
                
            // if only one image was left, make it an unknown one
            if(numberOfImages == 1)
            {
                    query = "UPDATE item_image SET ItemImageName='unknown.png', ItemThumbnailImage = 'sm_unknown.png' WHERE ItemImageId = " + Id + ";";
            }

            //if more than one was left then delete the image
            else
            {
                query = "DELETE FROM item_image WHERE ItemImageId = " + Id + ";";	
            }
            System.out.println(query);
            statement.executeUpdate(query);

            response.sendRedirect("products.jsp?id=" + ItemId + "&SuccessMsg=Image removed successfully!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(500);
        }
    }
	
	
	else if(request.getParameter("form").equals("item_option"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("ItemOptionId") ;

            query =  "DELETE FROM item_option WHERE ItemOptionId = " + Id + ";";
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

	else if(request.getParameter("form").equals("blogPost"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM blog_post WHERE BlogPostId = " + Id + ";";
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
            String Id =  request.getParameter("id") ;

			//first check to see if any forms are used on a page
			query =  "SELECT count(*) FROM info WHERE FormId = " + Id + ";";
            System.out.println(query);
            rs2 = statement.executeQuery(query);
			
			if (rs2.next())
            {
                //if the number of associated pages is more than 0, then form cannot be deleted
                if(rs2.getInt(1)> 0)
                {
                        response.sendRedirect("forms.jsp?FormDeleteError=" + "Form can be deleted only if there are no pages associated with it!"); 
                }
                else
                {					
                    //check to see if the form has any fields
                    query =  "SELECT count(*) FROM form_field WHERE FormId = " + Id + ";";
                    System.out.println(query);
                    ResultSet rs3 = statement.executeQuery(query);

                    if (rs3.next())
                    {						
                        if(rs3.getInt(1)> 0)
                        {
                            response.sendRedirect("forms.jsp?FormDeleteError=" + "Form can be deleted only if there are no fields associated with it!"); 
                        }
                        else
                        {
                            query =  "DELETE FROM form WHERE FormId = " + Id + ";";
                            System.out.println(query);
                            statement.executeUpdate(query);

                            response.sendRedirect("forms.jsp?FormDeleteError=" + "Form deleted Successfully!");
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
		
	else if(request.getParameter("form").equals("form_field"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM form_field WHERE FormFieldId = " + Id + ";";
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
	
	else if(request.getParameter("form").equals("blogPostComment"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("CommentId") ;

            query =  "DELETE FROM comment WHERE CommentId = " + Id + ";";
            System.out.println(query);
            statement.executeUpdate(query);

            response.sendRedirect("comments.jsp?BlogPostId=" + request.getParameter("blogPostId"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(500);
        }
    }
	
	
	else if(request.getParameter("form").equals("newsletter"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id") ;

            query =  "DELETE FROM mailinglist WHERE MailinglistId = " + Id + ";";
            System.out.println(query);
            statement.executeUpdate(query);

            response.sendRedirect("newsletter.jsp");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(500);
        }
    }
	
    else if(request.getParameter("form").equals("image"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id");

            //first check to see if any slider are using the image
            query =  "SELECT count(*) FROM slider_item si, image i WHERE si.SliderITemImageName = i.FileName AND i.ImageId = " + Id + ";";
            System.out.println(query);
            rs2 = statement.executeQuery(query);
			
            if (rs2.next())
            {
                //if the number of associated sliders are more than 0, then image cannot be deleted
                if(rs2.getInt(1)> 0)
                {
                    response.sendRedirect("images.jsp?DeleteMsg=" + "Image can be deleted only if there are no sliders using it!"); 
                }
                else
                {
                    rs2 = statement.executeQuery("SELECT * FROM image WHERE ImageId = " + Id + ";");
                    if(rs2.next())
                    {                                              
                        //delete image from the folder
                        File file = new File(getServletContext().getRealPath("site-images/")  + "/" + rs2.getString(2));
                        file.delete();               
                    } 

                    query =  "DELETE FROM image WHERE ImageId = " + Id + ";";
                    statement.executeUpdate(query); 

                    response.sendRedirect("images.jsp?DeleteMsg=" + "Image deleted Successfully!");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } 
	
	else if(request.getParameter("form").equals("files"))
    {
        try
        { 
            rs2 = statement.executeQuery("SELECT * FROM site_file WHERE SiteFileId = " + request.getParameter("id") + ";");
            if(rs2.next())
            {                                                              
		System.out.println(getServletContext().getRealPath("files-site/")  + File.separator + rs2.getString(2) + " was deleted");
                File file = new File(getServletContext().getRealPath("files-site/")  + File.separator + rs2.getString(2));
                file.delete();               
            } 
                
            String query = "";
            String id =  request.getParameter("id") ;

            query =  "DELETE FROM site_file WHERE SiteFileId = " + id + ";";
            statement.executeUpdate(query); 


            response.sendRedirect("files.jsp");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            response.sendError(500);
        }
    } 
	
	
    else if(request.getParameter("form").equals("item_files"))
    {
        try
        { 
            rs2 = statement.executeQuery("SELECT * FROM item_file WHERE ItemId = " + request.getParameter("ItemId") + ";");
            if(rs2.next())
            {                                              
                //delete image from the folder
                System.out.println(getServletContext().getRealPath("item-files/")  + File.separator + rs2.getString(3) + " was deleted");
                File file = new File(getServletContext().getRealPath("item-files/")  + File.separator + rs2.getString(3));
                file.delete();               
            } 
                
            String query = "";
            String id =  request.getParameter("ItemId") ;

            query =  "DELETE FROM item_file WHERE ItemId = " + id + ";";
            statement.executeUpdate(query); 


            response.sendRedirect("index.jsp");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            response.sendError(500);
        }
    } 
	
    if(request.getParameter("form").equals("deleteUser"))
    {
        try
        {
            String query = "";
            query =  "DELETE FROM user WHERE UserId = '" + request.getParameter("id") + "';";

            System.out.println(query);
            statement.executeUpdate(query);
			
            query =  "DELETE FROM user_role WHERE UserName = '" + request.getParameter("userName") + "';";
            System.out.println(query);
            statement.executeUpdate(query);
            
            query =  "DELETE FROM address WHERE userId = '" + request.getParameter("id") + "';";
            System.out.println(query);
            statement.executeUpdate(query);            
            		
            query =  "DELETE FROM contact WHERE userId = '" + request.getParameter("id") + "';";
            System.out.println(query);
            statement.executeUpdate(query);
		
            response.sendRedirect("users.jsp?msg=DeleteSuccessful!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(500);
        }
    } 
	
    else if(request.getParameter("form").equals("folder"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("folderId") ;
			
			//first check to see if any files are inside this folder
			query =  "SELECT count(*) FROM site_file WHERE folderId = " + Id + ";";
            System.out.println(query);
            rs2 = statement.executeQuery(query);
			
			if (rs2.next())
            {
                //if the number of associated files is more than 0, then folder cannot be deleted
                if(rs2.getInt(1)> 0)
                {
					response.sendRedirect("files.jsp?FolderDeleteError=" + "Folder can be deleted only if there are no files associated with it!"); 
				}
				else
				{
					query =  "DELETE FROM site_folder WHERE SiteFolderId = " + Id + ";";
					System.out.println(query);
					statement.executeUpdate(query);
		
					response.sendRedirect("files.jsp?FolderDeleteError=" + "Folder deleted Successfully!");
				}
			}
			
			

            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //response.sendError(500);
        }
    }

	else if(request.getParameter("form").equals("slider"))
    {
        try
        {
            String query = "";
            String Id =  request.getParameter("id") ;

			//first check to see if any sliders are used on a page
			query =  "SELECT count(*) FROM info WHERE SliderId = " + Id + ";";
            System.out.println(query);
            rs2 = statement.executeQuery(query);
			
			if (rs2.next())
            {
                //if the number of associated pages is more than 0, then slider cannot be deleted
                if(rs2.getInt(1)> 0)
                {
					response.sendRedirect("sliders.jsp?DeleteMsg=" + "Slider can be deleted only if there are no pages associated with it!"); 
				}
				else
				{
					//first delete all slider items
					query =  "DELETE FROM slider_item WHERE SliderId = " + Id + ";";
					System.out.println(query);
					statement.executeUpdate(query);
					
					//then delete the slider itself
					query =  "DELETE FROM slider WHERE SliderId = " + Id + ";";
					System.out.println(query);
					statement.executeUpdate(query);
		
					response.sendRedirect("sliders.jsp?DeleteMsg=" + "Slider Deleted Successfully!");
				}
			}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

	
%>