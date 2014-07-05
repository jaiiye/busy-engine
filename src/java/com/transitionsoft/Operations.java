package com.transitionsoft;

import com.busy.dao.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Operations extends HttpServlet
{
    protected void deleteFile(String path) throws IOException
    {
        Path target = Paths.get(path);
        Files.delete(target);
        System.out.println("Deleted File: " + path);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getUserPrincipal().getName();
        com.transitionsoft.dao.User u = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new java.util.Date());
        
        SimpleDateFormat operatingDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (userName != null)
        {
            //see if the user is already logged in before
            String name = (String) request.getSession().getAttribute("userName");

            if (name == null)
            {
                //a new user is being logged-in
                request.getSession().setAttribute("userName", userName);

                //find out who the logged-on user is
                u = Database.getUser(userName);
            }
            else
            {
                u = Database.getUser(name);
            }
        }

        if (request.getParameter("form").equals("address"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Address.addAddress(request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("state"), request.getParameter("zipcode"), request.getParameter("country"), request.getParameter("region"), Integer.parseInt(request.getParameter("userId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address", id);
                        }
                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Added Address Successfully!");
                        break;
                    case 2: //update            
                        Address.updateAddress(Integer.parseInt(request.getParameter("addressId")), request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("state"), request.getParameter("zipcode"), request.getParameter("country"), request.getParameter("region"), Integer.parseInt(request.getParameter("userId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address", Integer.parseInt(request.getParameter("addressId")));
                        response.sendRedirect("admin/AddressUI.jsp?id=" + request.getParameter("addressId") + "&SuccessMsg=Updated Address Successfully!");
                        break;
                    case 3:  //delete
                        Address.deleteAddressById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address", request.getParameter("id"));
                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Deleted Address Successfully!");
                        break;
                    case 4:  //remove all records
                        //Address.deleteAllAddress();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address");
                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error editing Address, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error adding Address.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error editing Address.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error deleting Address.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error clearing Address.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Unknown Error Address, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("affiliate"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Affiliate.addAffiliate(request.getParameter("name"), request.getParameter("internetURL"), request.getParameter("eMail"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("details"), Integer.parseInt(request.getParameter("serviceHours")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate", id);
                        }
                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Added Affiliate Successfully!");
                        break;
                    case 2: //update            
                        Affiliate.updateAffiliate(Integer.parseInt(request.getParameter("affiliateId")), request.getParameter("name"), request.getParameter("internetURL"), request.getParameter("eMail"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("details"), Integer.parseInt(request.getParameter("serviceHours")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate", Integer.parseInt(request.getParameter("affiliateId")));
                        response.sendRedirect("admin/AffiliateUI.jsp?id=" + request.getParameter("affiliateId") + "&SuccessMsg=Updated Affiliate Successfully!");
                        break;
                    case 3:  //delete
                        Affiliate.deleteAffiliateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate", request.getParameter("id"));
                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Deleted Affiliate Successfully!");
                        break;
                    case 4:  //remove all records
                        Affiliate.deleteAllAffiliate();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate");
                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error editing Affiliate, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error adding Affiliate.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error editing Affiliate.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error deleting Affiliate.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error clearing Affiliate.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Unknown Error Affiliate, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("blog"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Blog.addBlog(request.getParameter("blogName"), Integer.parseInt(request.getParameter("blogLayoutType")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog", id);
                        }
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Added Blog Successfully!");
                        break;
                    case 2: //update            
                        Blog.updateBlog(Integer.parseInt(request.getParameter("blogId")), request.getParameter("blogName"), Integer.parseInt(request.getParameter("blogLayoutType")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog", Integer.parseInt(request.getParameter("blogId")));
                        response.sendRedirect("admin/BlogUI.jsp?id=" + request.getParameter("blogId") + "&SuccessMsg=Updated Blog Successfully!");
                        break;
                    case 3:  //delete
                        Blog.deleteBlogById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog", request.getParameter("id"));
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Deleted Blog Successfully!");
                        break;
                    case 4:  //remove all records
                        Blog.deleteAllBlog();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog");
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error editing Blog, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error adding Blog.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error editing Blog.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error deleting Blog.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error clearing Blog.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Unknown Error Blog, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("blog_post"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = BlogPost.addBlogPost(request.getParameter("postTitle"), request.getParameter("postBody"), request.getParameter("postPicURL"), operatingDateFormat.parse(request.getParameter("postDate")), request.getParameter("postTags"), Integer.parseInt(request.getParameter("postFeatured")), Integer.parseInt(request.getParameter("userId")), Double.parseDouble(request.getParameter("postRating")), Integer.parseInt(request.getParameter("blogId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost", id);
                        }
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Added BlogPost Successfully!");
                        break;
                    case 2: //update            
                        BlogPost.updateBlogPost(Integer.parseInt(request.getParameter("blogPostId")), request.getParameter("postTitle"), request.getParameter("postBody"), request.getParameter("postPicURL"), operatingDateFormat.parse(request.getParameter("postDate")), request.getParameter("postTags"), Integer.parseInt(request.getParameter("postFeatured")), Integer.parseInt(request.getParameter("userId")), Double.parseDouble(request.getParameter("postRating")), Integer.parseInt(request.getParameter("blogId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost", Integer.parseInt(request.getParameter("blogPostId")));
                        response.sendRedirect("admin/BlogPostUI.jsp?id=" + request.getParameter("blogPostId") + "&SuccessMsg=Updated BlogPost Successfully!");
                        break;
                    case 3:  //delete
                        BlogPost.deleteBlogPostById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost", request.getParameter("id"));
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Deleted BlogPost Successfully!");
                        break;
                    case 4:  //remove all records
                        BlogPost.deleteAllBlogPost();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost");
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error editing BlogPost, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error adding BlogPost.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error editing BlogPost.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error deleting BlogPost.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error clearing BlogPost.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Unknown Error BlogPost, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("blog_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = BlogType.addBlogType(request.getParameter("blogTypeName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType", id);
                        }
                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Added BlogType Successfully!");
                        break;
                    case 2: //update            
                        BlogType.updateBlogType(Integer.parseInt(request.getParameter("blogTypeId")), request.getParameter("blogTypeName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType", Integer.parseInt(request.getParameter("blogTypeId")));
                        response.sendRedirect("admin/BlogTypeUI.jsp?id=" + request.getParameter("blogTypeId") + "&SuccessMsg=Updated BlogType Successfully!");
                        break;
                    case 3:  //delete
                        BlogType.deleteBlogTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType", request.getParameter("id"));
                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Deleted BlogType Successfully!");
                        break;
                    case 4:  //remove all records
                        BlogType.deleteAllBlogType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType");
                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error editing BlogType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error adding BlogType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error editing BlogType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error deleting BlogType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error clearing BlogType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Unknown Error BlogType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("category"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Category.addCategory(request.getParameter("categoryName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category", id);
                        }
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Added Category Successfully!");
                        break;
                    case 2: //update            
                        Category.updateCategory(Integer.parseInt(request.getParameter("categoryId")), request.getParameter("categoryName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category", Integer.parseInt(request.getParameter("categoryId")));
                        response.sendRedirect("admin/CategoryUI.jsp?id=" + request.getParameter("categoryId") + "&SuccessMsg=Updated Category Successfully!");
                        break;
                    case 3:  //delete
                        Category.deleteCategoryById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category", request.getParameter("id"));
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Deleted Category Successfully!");
                        break;
                    case 4:  //remove all records
                        Category.deleteAllCategory();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category");
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error editing Category, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error adding Category.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error editing Category.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error deleting Category.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error clearing Category.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Unknown Error Category, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("comment"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Comment.addComment(Integer.parseInt(request.getParameter("postId")), request.getParameter("commentTitle"), request.getParameter("commentBody"), operatingDateFormat.parse(request.getParameter("commentDate")), Integer.parseInt(request.getParameter("userId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment", id);
                        }
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Added Comment Successfully!");
                        break;
                    case 2: //update            
                        Comment.updateComment(Integer.parseInt(request.getParameter("commentId")), Integer.parseInt(request.getParameter("postId")), request.getParameter("commentTitle"), request.getParameter("commentBody"), operatingDateFormat.parse(request.getParameter("commentDate")), Integer.parseInt(request.getParameter("userId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment", Integer.parseInt(request.getParameter("commentId")));
                        response.sendRedirect("admin/CommentUI.jsp?id=" + request.getParameter("commentId") + "&SuccessMsg=Updated Comment Successfully!");
                        break;
                    case 3:  //delete
                        Comment.deleteCommentById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment", request.getParameter("id"));
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Deleted Comment Successfully!");
                        break;
                    case 4:  //remove all records
                        Comment.deleteAllComment();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment");
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error editing Comment, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error adding Comment.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error editing Comment.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error deleting Comment.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error clearing Comment.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Unknown Error Comment, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("contact"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Contact.addContact(request.getParameter("title"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("position"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("email"), Integer.parseInt(request.getParameter("emailConfirmed")), request.getParameter("info"), Integer.parseInt(request.getParameter("userId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact", id);
                        }
                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Added Contact Successfully!");
                        break;
                    case 2: //update            
                        Contact.updateContact(Integer.parseInt(request.getParameter("contactId")), request.getParameter("title"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("position"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("email"), Integer.parseInt(request.getParameter("emailConfirmed")), request.getParameter("info"), Integer.parseInt(request.getParameter("userId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact", Integer.parseInt(request.getParameter("contactId")));
                        response.sendRedirect("admin/ContactUI.jsp?id=" + request.getParameter("contactId") + "&SuccessMsg=Updated Contact Successfully!");
                        break;
                    case 3:  //delete
                        Contact.deleteContactById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact", request.getParameter("id"));
                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Deleted Contact Successfully!");
                        break;
                    case 4:  //remove all records
                        Contact.deleteAllContact();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact");
                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error editing Contact, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error adding Contact.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error editing Contact.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error deleting Contact.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error clearing Contact.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Unknown Error Contact, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("customer"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Customer.addCustomer(Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer", id);
                        }
                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Added Customer Successfully!");
                        break;
                    case 2: //update            
                        Customer.updateCustomer(Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer", Integer.parseInt(request.getParameter("customerId")));
                        response.sendRedirect("admin/CustomerUI.jsp?id=" + request.getParameter("customerId") + "&SuccessMsg=Updated Customer Successfully!");
                        break;
                    case 3:  //delete
                        Customer.deleteCustomerById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer", request.getParameter("id"));
                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Deleted Customer Successfully!");
                        break;
                    case 4:  //remove all records
                        Customer.deleteAllCustomer();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer");
                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error editing Customer, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error adding Customer.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error editing Customer.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error deleting Customer.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error clearing Customer.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Unknown Error Customer, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("form"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Form.addForm(request.getParameter("formName"), request.getParameter("formDescription"), request.getParameter("formSubmissionEmail"), request.getParameter("formSubmissionMethod"), request.getParameter("formAction"), Integer.parseInt(request.getParameter("formResetPresent")), Integer.parseInt(request.getParameter("formFileUpload")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form", id);
                        }
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Added Form Successfully!");
                        break;
                    case 2: //update            
                        Form.updateForm(Integer.parseInt(request.getParameter("formId")), request.getParameter("formName"), request.getParameter("formDescription"), request.getParameter("formSubmissionEmail"), request.getParameter("formSubmissionMethod"), request.getParameter("formAction"), Integer.parseInt(request.getParameter("formResetPresent")), Integer.parseInt(request.getParameter("formFileUpload")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form", Integer.parseInt(request.getParameter("formId")));
                        response.sendRedirect("admin/FormUI.jsp?id=" + request.getParameter("formId") + "&SuccessMsg=Updated Form Successfully!");
                        break;
                    case 3:  //delete
                        Form.deleteFormById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form", request.getParameter("id"));
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Deleted Form Successfully!");
                        break;
                    case 4:  //remove all records
                        Form.deleteAllForm();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form");
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error editing Form, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error adding Form.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error editing Form.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error deleting Form.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error clearing Form.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Unknown Error Form, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("form_field"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = FormField.addFormField(Integer.parseInt(request.getParameter("formId")), request.getParameter("fieldName"), request.getParameter("fieldDataType"), request.getParameter("fieldLabel"), request.getParameter("fieldErrorText"), request.getParameter("fieldValidationRegex"), Integer.parseInt(request.getParameter("fieldRank")), request.getParameter("fieldDefaultValue"), request.getParameter("fieldOptions"), request.getParameter("fieldGroupName"), Integer.parseInt(request.getParameter("fieldOptional")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField", id);
                        }
                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Added FormField Successfully!");
                        break;
                    case 2: //update            
                        FormField.updateFormField(Integer.parseInt(request.getParameter("formFieldId")), Integer.parseInt(request.getParameter("formId")), request.getParameter("fieldName"), request.getParameter("fieldDataType"), request.getParameter("fieldLabel"), request.getParameter("fieldErrorText"), request.getParameter("fieldValidationRegex"), Integer.parseInt(request.getParameter("fieldRank")), request.getParameter("fieldDefaultValue"), request.getParameter("fieldOptions"), request.getParameter("fieldGroupName"), Integer.parseInt(request.getParameter("fieldOptional")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField", Integer.parseInt(request.getParameter("formFieldId")));
                        response.sendRedirect("admin/FormFieldUI.jsp?id=" + request.getParameter("formFieldId") + "&SuccessMsg=Updated FormField Successfully!");
                        break;
                    case 3:  //delete
                        FormField.deleteFormFieldById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField", request.getParameter("id"));
                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Deleted FormField Successfully!");
                        break;
                    case 4:  //remove all records
                        FormField.deleteAllFormField();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField");
                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error editing FormField, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error adding FormField.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error editing FormField.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error deleting FormField.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error clearing FormField.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Unknown Error FormField, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("form_field_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = FormFieldType.addFormFieldType(request.getParameter("fieldTypeName"), request.getParameter("fieldTypeInputType"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType", id);
                        }
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Added FormFieldType Successfully!");
                        break;
                    case 2: //update            
                        FormFieldType.updateFormFieldType(Integer.parseInt(request.getParameter("formFieldTypeId")), request.getParameter("fieldTypeName"), request.getParameter("fieldTypeInputType"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType", Integer.parseInt(request.getParameter("formFieldTypeId")));
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?id=" + request.getParameter("formFieldTypeId") + "&SuccessMsg=Updated FormFieldType Successfully!");
                        break;
                    case 3:  //delete
                        FormFieldType.deleteFormFieldTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType", request.getParameter("id"));
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Deleted FormFieldType Successfully!");
                        break;
                    case 4:  //remove all records
                        FormFieldType.deleteAllFormFieldType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType");
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error editing FormFieldType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error adding FormFieldType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error editing FormFieldType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error deleting FormFieldType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error clearing FormFieldType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Unknown Error FormFieldType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("image"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Image.addImage(Integer.parseInt(request.getParameter("typeId")), Integer.parseInt(request.getParameter("itemId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("linkUrl"), Integer.parseInt(request.getParameter("rank")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Image", id);
                        }
                        response.sendRedirect("admin/ImageUI.jsp?SuccessMsg=Added Image Successfully!");
                        break;
                    case 2: //update            
                        Image.updateImage(Integer.parseInt(request.getParameter("imageId")), Integer.parseInt(request.getParameter("typeId")), Integer.parseInt(request.getParameter("itemId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("linkUrl"), Integer.parseInt(request.getParameter("rank")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Image", Integer.parseInt(request.getParameter("imageId")));
                        response.sendRedirect("admin/ImageUI.jsp?id=" + request.getParameter("imageId") + "&SuccessMsg=Updated Image Successfully!");
                        break;
                    case 3:  //delete   
                        //if the number of associated sliders are more than 0, then image cannot be deleted
                        if(Database.checkSliderImageCount(Integer.parseInt(request.getParameter("id"))) > 0)
                        {
                            response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=" + "Image can be deleted when there are no sliders using it!"); 
                        }
                        else
                        {
                            ArrayList<Image> images = Image.getAllImageByColumn(Image.PROP_IMAGEID, request.getParameter("id"));
                            for(Image img : images)
                            {                            
                                deleteFile(getServletContext().getRealPath("/images-site/")  + File.separator + img.getFileName());
                            }                        
                        }
                        Image.deleteImageById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Image", request.getParameter("id"));
                        response.sendRedirect("admin/ImageUI.jsp?SuccessMsg=Deleted Image Successfully!");
                        break;
                    case 4:  //remove all records
                        Image.deleteAllImage();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Image");
                        response.sendRedirect("admin/ImageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=Error editing Image, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation: " + e.getMessage());
                e.printStackTrace();
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=Error adding Image.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=Error editing Image.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=Error deleting Image.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=Error clearing Image.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ImageUI.jsp?ErrorMsg=Unknown Error Image, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("image_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ImageType.addImageType(request.getParameter("typeName"), request.getParameter("typeDescription"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType", id);
                        }
                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Added ImageType Successfully!");
                        break;
                    case 2: //update            
                        ImageType.updateImageType(Integer.parseInt(request.getParameter("imageTypeId")), request.getParameter("typeName"), request.getParameter("typeDescription"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType", Integer.parseInt(request.getParameter("imageTypeId")));
                        response.sendRedirect("admin/ImageTypeUI.jsp?id=" + request.getParameter("imageTypeId") + "&SuccessMsg=Updated ImageType Successfully!");
                        break;
                    case 3:  //delete
                        ImageType.deleteImageTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType", request.getParameter("id"));
                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Deleted ImageType Successfully!");
                        break;
                    case 4:  //remove all records
                        ImageType.deleteAllImageType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType");
                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error editing ImageType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error adding ImageType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error editing ImageType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error deleting ImageType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error clearing ImageType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Unknown Error ImageType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("info"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Info.addInfo(request.getParameter("infoName"), request.getParameter("infoDescription"), Integer.parseInt(request.getParameter("formId")), request.getParameter("infoSeoTitle"), request.getParameter("infoSeoDescription"), request.getParameter("infoSeoKeywords"), Integer.parseInt(request.getParameter("sliderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Info", id);
                        }
                        response.sendRedirect("admin/InfoUI.jsp?SuccessMsg=Added Info Successfully!");
                        break;
                    case 2: //update            
                        Info.updateInfo(Integer.parseInt(request.getParameter("infoId")), request.getParameter("infoName"), request.getParameter("infoDescription"), Integer.parseInt(request.getParameter("formId")), request.getParameter("infoSeoTitle"), request.getParameter("infoSeoDescription"), request.getParameter("infoSeoKeywords"), Integer.parseInt(request.getParameter("sliderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Info", Integer.parseInt(request.getParameter("infoId")));
                        response.sendRedirect("admin/InfoUI.jsp?id=" + request.getParameter("infoId") + "&SuccessMsg=Updated Info Successfully!");
                        break;
                    case 3:  //delete
                        Info.deleteInfoById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Info", request.getParameter("id"));
                        response.sendRedirect("admin/InfoUI.jsp?SuccessMsg=Deleted Info Successfully!");
                        break;
                    case 4:  //remove all records
                        Info.deleteAllInfo();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Info");
                        response.sendRedirect("admin/InfoUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/InfoUI.jsp?ErrorMsg=Error editing Info, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/InfoUI.jsp?ErrorMsg=Error adding Info.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/InfoUI.jsp?ErrorMsg=Error editing Info.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/InfoUI.jsp?ErrorMsg=Error deleting Info.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/InfoUI.jsp?ErrorMsg=Error clearing Info.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/InfoUI.jsp?ErrorMsg=Unknown Error Info, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Item.addItem(request.getParameter("itemName"), request.getParameter("itemDescription"), Integer.parseInt(request.getParameter("itemBrandId")), Double.parseDouble(request.getParameter("itemListPrice")), Double.parseDouble(request.getParameter("itemPrice")), Integer.parseInt(request.getParameter("itemPriceAdjustment")), request.getParameter("itemSEOTitle"), request.getParameter("itemSEODescription"), request.getParameter("itemSEOKeywords"), Integer.parseInt(request.getParameter("itemType")), request.getParameter("itemUPC"), Double.parseDouble(request.getParameter("itemRating")), Integer.parseInt(request.getParameter("itemVoteCount")), request.getParameter("itemShortDescription"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item", id);
                        }
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Added Item Successfully!");
                        break;
                    case 2: //update            
                        Item.updateItem(Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemName"), request.getParameter("itemDescription"), Integer.parseInt(request.getParameter("itemBrandId")), Double.parseDouble(request.getParameter("itemListPrice")), Double.parseDouble(request.getParameter("itemPrice")), Integer.parseInt(request.getParameter("itemPriceAdjustment")), request.getParameter("itemSEOTitle"), request.getParameter("itemSEODescription"), request.getParameter("itemSEOKeywords"), Integer.parseInt(request.getParameter("itemType")), request.getParameter("itemUPC"), Double.parseDouble(request.getParameter("itemRating")), Integer.parseInt(request.getParameter("itemVoteCount")), request.getParameter("itemShortDescription"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item", Integer.parseInt(request.getParameter("itemId")));
                        response.sendRedirect("admin/ItemUI.jsp?id=" + request.getParameter("itemId") + "&SuccessMsg=Updated Item Successfully!");
                        break;
                    case 3:  //delete
                        Item.deleteItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item", request.getParameter("id"));
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Deleted Item Successfully!");
                        break;
                    case 4:  //remove all records
                        Item.deleteAllItem();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item");
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error editing Item, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error adding Item.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error editing Item.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error deleting Item.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error clearing Item.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Unknown Error Item, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_attribute"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemAttribute.addItemAttribute(request.getParameter("key"), request.getParameter("value"), request.getParameter("type"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute", id);
                        }
                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Added ItemAttribute Successfully!");
                        break;
                    case 2: //update            
                        ItemAttribute.updateItemAttribute(Integer.parseInt(request.getParameter("itemAttributeId")), request.getParameter("key"), request.getParameter("value"), request.getParameter("type"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute", Integer.parseInt(request.getParameter("itemAttributeId")));
                        response.sendRedirect("admin/ItemAttributeUI.jsp?id=" + request.getParameter("itemAttributeId") + "&SuccessMsg=Updated ItemAttribute Successfully!");
                        break;
                    case 3:  //delete
                        ItemAttribute.deleteItemAttributeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute", request.getParameter("id"));
                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Deleted ItemAttribute Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemAttribute.deleteAllItemAttribute();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute");
                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error editing ItemAttribute, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error adding ItemAttribute.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error editing ItemAttribute.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error deleting ItemAttribute.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error clearing ItemAttribute.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Unknown Error ItemAttribute, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_availability"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemAvailability.addItemAvailability(request.getParameter("itemAvailabilityType"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability", id);
                        }
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Added ItemAvailability Successfully!");
                        break;
                    case 2: //update            
                        ItemAvailability.updateItemAvailability(Integer.parseInt(request.getParameter("itemAvailabilityId")), request.getParameter("itemAvailabilityType"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability", Integer.parseInt(request.getParameter("itemAvailabilityId")));
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?id=" + request.getParameter("itemAvailabilityId") + "&SuccessMsg=Updated ItemAvailability Successfully!");
                        break;
                    case 3:  //delete
                        ItemAvailability.deleteItemAvailabilityById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability", request.getParameter("id"));
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Deleted ItemAvailability Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemAvailability.deleteAllItemAvailability();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability");
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error editing ItemAvailability, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error adding ItemAvailability.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error editing ItemAvailability.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error deleting ItemAvailability.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error clearing ItemAvailability.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Unknown Error ItemAvailability, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_brand"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemBrand.addItemBrand(request.getParameter("itemBrandName"), request.getParameter("itemBrandDescription"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand", id);
                        }
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Added ItemBrand Successfully!");
                        break;
                    case 2: //update            
                        ItemBrand.updateItemBrand(Integer.parseInt(request.getParameter("itemBrandId")), request.getParameter("itemBrandName"), request.getParameter("itemBrandDescription"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand", Integer.parseInt(request.getParameter("itemBrandId")));
                        response.sendRedirect("admin/ItemBrandUI.jsp?id=" + request.getParameter("itemBrandId") + "&SuccessMsg=Updated ItemBrand Successfully!");
                        break;
                    case 3:  //delete
                        ItemBrand.deleteItemBrandById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand", request.getParameter("id"));
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Deleted ItemBrand Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemBrand.deleteAllItemBrand();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand");
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error editing ItemBrand, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error adding ItemBrand.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error editing ItemBrand.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error deleting ItemBrand.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error clearing ItemBrand.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Unknown Error ItemBrand, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_category"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemCategory.addItemCategory(Integer.parseInt(request.getParameter("categoryId")), Integer.parseInt(request.getParameter("itemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory", id);
                        }
                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Added ItemCategory Successfully!");
                        break;
                    case 2: //update            
                        ItemCategory.updateItemCategory(Integer.parseInt(request.getParameter("itemCategoryId")), Integer.parseInt(request.getParameter("categoryId")), Integer.parseInt(request.getParameter("itemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory", Integer.parseInt(request.getParameter("itemCategoryId")));
                        response.sendRedirect("admin/ItemCategoryUI.jsp?id=" + request.getParameter("itemCategoryId") + "&SuccessMsg=Updated ItemCategory Successfully!");
                        break;
                    case 3:  //delete
                        ItemCategory.deleteItemCategoryById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory", request.getParameter("id"));
                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Deleted ItemCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemCategory.deleteAllItemCategory();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory");
                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error editing ItemCategory, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error adding ItemCategory.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error editing ItemCategory.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error deleting ItemCategory.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error clearing ItemCategory.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Unknown Error ItemCategory, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_file"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemFile.addItemFile(Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemFileName"), request.getParameter("itemFileDescription"), request.getParameter("itemFileLabel"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile", id);
                        }
                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Added ItemFile Successfully!");
                        break;
                    case 2: //update            
                        ItemFile.updateItemFile(Integer.parseInt(request.getParameter("itemFileId")), Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemFileName"), request.getParameter("itemFileDescription"), request.getParameter("itemFileLabel"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile", Integer.parseInt(request.getParameter("itemFileId")));
                        response.sendRedirect("admin/ItemFileUI.jsp?id=" + request.getParameter("itemFileId") + "&SuccessMsg=Updated ItemFile Successfully!");
                        break;
                    case 3:  //delete
                        ItemFile.deleteItemFileById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile", request.getParameter("id"));
                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Deleted ItemFile Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemFile.deleteAllItemFile();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile");
                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error editing ItemFile, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error adding ItemFile.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error editing ItemFile.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error deleting ItemFile.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error clearing ItemFile.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Unknown Error ItemFile, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_image"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemImage.addItemImage(Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemImageName"), request.getParameter("itemThumbnailImage"), request.getParameter("itemAltTag"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage", id);
                        }
                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Added ItemImage Successfully!");
                        break;
                    case 2: //update            
                        ItemImage.updateItemImage(Integer.parseInt(request.getParameter("itemImageId")), Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemImageName"), request.getParameter("itemThumbnailImage"), request.getParameter("itemAltTag"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage", Integer.parseInt(request.getParameter("itemImageId")));
                        response.sendRedirect("admin/ItemImageUI.jsp?id=" + request.getParameter("itemImageId") + "&SuccessMsg=Updated ItemImage Successfully!");
                        break;
                    case 3:  //delete                            
                        ArrayList<ItemImage> images = ItemImage.getAllItemImageByColumn(ItemImage.PROP_ITEMIMAGEID, request.getParameter("id"));
                        for(ItemImage img : images)
                        {                            
                            deleteFile(getServletContext().getRealPath("/items/")  + File.separator + img.getItemImageName());
                            deleteFile(getServletContext().getRealPath("/items/")  + File.separator + img.getItemThumbnailImage());
                        } 
                        
                        ItemImage.deleteItemImageById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage", request.getParameter("id"));
                        response.sendRedirect("admin/products.jsp?SuccessMsg=Deleted image Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemImage.deleteAllItemImage();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage");
                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error editing ItemImage, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error adding ItemImage.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error editing ItemImage.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error deleting ItemImage.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error clearing ItemImage.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Unknown Error ItemImage, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_location"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemLocation.addItemLocation(Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("addressId")), request.getParameter("latitude"), request.getParameter("longitude"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation", id);
                        }
                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Added ItemLocation Successfully!");
                        break;
                    case 2: //update            
                        ItemLocation.updateItemLocation(Integer.parseInt(request.getParameter("itemLocationId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("addressId")), request.getParameter("latitude"), request.getParameter("longitude"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation", Integer.parseInt(request.getParameter("itemLocationId")));
                        response.sendRedirect("admin/ItemLocationUI.jsp?id=" + request.getParameter("itemLocationId") + "&SuccessMsg=Updated ItemLocation Successfully!");
                        break;
                    case 3:  //delete
                        ItemLocation.deleteItemLocationById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation", request.getParameter("id"));
                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Deleted ItemLocation Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemLocation.deleteAllItemLocation();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation");
                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error editing ItemLocation, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error adding ItemLocation.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error editing ItemLocation.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error deleting ItemLocation.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error clearing ItemLocation.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Unknown Error ItemLocation, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_option"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemOption.addItemOption(request.getParameter("itemOptionType"), request.getParameter("itemOptionDescription"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption", id);
                        }
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Added ItemOption Successfully!");
                        break;
                    case 2: //update            
                        ItemOption.updateItemOption(Integer.parseInt(request.getParameter("itemOptionId")), request.getParameter("itemOptionType"), request.getParameter("itemOptionDescription"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption", Integer.parseInt(request.getParameter("itemOptionId")));
                        response.sendRedirect("admin/ItemOptionUI.jsp?id=" + request.getParameter("itemOptionId") + "&SuccessMsg=Updated ItemOption Successfully!");
                        break;
                    case 3:  //delete
                        ItemOption.deleteItemOptionById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption", request.getParameter("id"));
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Deleted ItemOption Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemOption.deleteAllItemOption();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption");
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error editing ItemOption, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error adding ItemOption.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error editing ItemOption.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error deleting ItemOption.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error clearing ItemOption.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Unknown Error ItemOption, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_option_available"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemOptionAvailable.addItemOptionAvailable(Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemOptionId")), Integer.parseInt(request.getParameter("itemAvailabilityId")), Integer.parseInt(request.getParameter("itemQuantity")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOptionAvailable", id);
                        }
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?SuccessMsg=Added ItemOptionAvailable Successfully!");
                        break;
                    case 2: //update            
                        ItemOptionAvailable.updateItemOptionAvailable(Integer.parseInt(request.getParameter("itemOptionAvailableId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemOptionId")), Integer.parseInt(request.getParameter("itemAvailabilityId")), Integer.parseInt(request.getParameter("itemQuantity")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOptionAvailable", Integer.parseInt(request.getParameter("itemOptionAvailableId")));
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?id=" + request.getParameter("itemOptionAvailableId") + "&SuccessMsg=Updated ItemOptionAvailable Successfully!");
                        break;
                    case 3:  //delete
                        ItemOptionAvailable.deleteItemOptionAvailableById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOptionAvailable", request.getParameter("id"));
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?SuccessMsg=Deleted ItemOptionAvailable Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemOptionAvailable.deleteAllItemOptionAvailable();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOptionAvailable");
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?ErrorMsg=Error editing ItemOptionAvailable, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?ErrorMsg=Error adding ItemOptionAvailable.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?ErrorMsg=Error editing ItemOptionAvailable.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?ErrorMsg=Error deleting ItemOptionAvailable.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?ErrorMsg=Error clearing ItemOptionAvailable.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemOptionAvailableUI.jsp?ErrorMsg=Unknown Error ItemOptionAvailable, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("item_review"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemReview.addItemReview(Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("commentId")), Integer.parseInt(request.getParameter("rating")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview", id);
                        }
                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Added ItemReview Successfully!");
                        break;
                    case 2: //update            
                        ItemReview.updateItemReview(Integer.parseInt(request.getParameter("itemReviewId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("commentId")), Integer.parseInt(request.getParameter("rating")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview", Integer.parseInt(request.getParameter("itemReviewId")));
                        response.sendRedirect("admin/ItemReviewUI.jsp?id=" + request.getParameter("itemReviewId") + "&SuccessMsg=Updated ItemReview Successfully!");
                        break;
                    case 3:  //delete
                        ItemReview.deleteItemReviewById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview", request.getParameter("id"));
                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Deleted ItemReview Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemReview.deleteAllItemReview();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview");
                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error editing ItemReview, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error adding ItemReview.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error editing ItemReview.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error deleting ItemReview.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error clearing ItemReview.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Unknown Error ItemReview, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("locale"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Locale.addLocale(request.getParameter("localeString"), request.getParameter("localeCharacterSet"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale", id);
                        }
                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Added Locale Successfully!");
                        break;
                    case 2: //update            
                        Locale.updateLocale(Integer.parseInt(request.getParameter("localeId")), request.getParameter("localeString"), request.getParameter("localeCharacterSet"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale", Integer.parseInt(request.getParameter("localeId")));
                        response.sendRedirect("admin/LocaleUI.jsp?id=" + request.getParameter("localeId") + "&SuccessMsg=Updated Locale Successfully!");
                        break;
                    case 3:  //delete
                        Locale.deleteLocaleById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale", request.getParameter("id"));
                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Deleted Locale Successfully!");
                        break;
                    case 4:  //remove all records
                        Locale.deleteAllLocale();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale");
                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error editing Locale, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error adding Locale.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error editing Locale.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error deleting Locale.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error clearing Locale.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Unknown Error Locale, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("localized_string"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = LocalizedString.addLocalizedString(Integer.parseInt(request.getParameter("localeId")), request.getParameter("stringValue"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString", id);
                        }
                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Added LocalizedString Successfully!");
                        break;
                    case 2: //update            
                        LocalizedString.updateLocalizedString(Integer.parseInt(request.getParameter("stringId")), Integer.parseInt(request.getParameter("localeId")), request.getParameter("stringValue"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString", Integer.parseInt(request.getParameter("stringId")));
                        response.sendRedirect("admin/LocalizedStringUI.jsp?id=" + request.getParameter("stringId") + "&SuccessMsg=Updated LocalizedString Successfully!");
                        break;
                    case 3:  //delete
                        LocalizedString.deleteLocalizedStringById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString", request.getParameter("id"));
                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Deleted LocalizedString Successfully!");
                        break;
                    case 4:  //remove all records
                        LocalizedString.deleteAllLocalizedString();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString");
                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error editing LocalizedString, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error adding LocalizedString.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error editing LocalizedString.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error deleting LocalizedString.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error clearing LocalizedString.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Unknown Error LocalizedString, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("mailinglist"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Mailinglist.addMailinglist(request.getParameter("mailinglistName"), request.getParameter("mailinglistEmail"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist", id);
                        }
                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Added Mailinglist Successfully!");
                        break;
                    case 2: //update            
                        Mailinglist.updateMailinglist(Integer.parseInt(request.getParameter("mailinglistId")), request.getParameter("mailinglistName"), request.getParameter("mailinglistEmail"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist", Integer.parseInt(request.getParameter("mailinglistId")));
                        response.sendRedirect("admin/MailinglistUI.jsp?id=" + request.getParameter("mailinglistId") + "&SuccessMsg=Updated Mailinglist Successfully!");
                        break;
                    case 3:  //delete
                        Mailinglist.deleteMailinglistById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist", request.getParameter("id"));
                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Deleted Mailinglist Successfully!");
                        break;
                    case 4:  //remove all records
                        Mailinglist.deleteAllMailinglist();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist");
                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error editing Mailinglist, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error adding Mailinglist.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error editing Mailinglist.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error deleting Mailinglist.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error clearing Mailinglist.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Unknown Error Mailinglist, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("order"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = com.busy.dao.Order.addOrder(Integer.parseInt(request.getParameter("shippingId")), request.getParameter("orderStatus"), operatingDateFormat.parse(request.getParameter("orderDate")), request.getParameter("payPalTransactionId"), Double.parseDouble(request.getParameter("payPalAmountBilled")), request.getParameter("payPalPaymentStatus"), request.getParameter("payPalPendingReason"), request.getParameter("payPalPaymentType"), Double.parseDouble(request.getParameter("payPalFeeCharged")), request.getParameter("payPalCurrencyCode"), request.getParameter("payPalPayerId"), Double.parseDouble(request.getParameter("orderTaxAmount")), Double.parseDouble(request.getParameter("orderShippingAmount")), request.getParameter("orderAdditionalData"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order", id);
                        }
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Added Order Successfully!");
                        break;
                    case 2: //update            
                        com.busy.dao.Order.updateOrder(Integer.parseInt(request.getParameter("orderId")), Integer.parseInt(request.getParameter("shippingId")), request.getParameter("orderStatus"), operatingDateFormat.parse(request.getParameter("orderDate")), request.getParameter("payPalTransactionId"), Double.parseDouble(request.getParameter("payPalAmountBilled")), request.getParameter("payPalPaymentStatus"), request.getParameter("payPalPendingReason"), request.getParameter("payPalPaymentType"), Double.parseDouble(request.getParameter("payPalFeeCharged")), request.getParameter("payPalCurrencyCode"), request.getParameter("payPalPayerId"), Double.parseDouble(request.getParameter("orderTaxAmount")), Double.parseDouble(request.getParameter("orderShippingAmount")), request.getParameter("orderAdditionalData"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order", Integer.parseInt(request.getParameter("orderId")));
                        response.sendRedirect("admin/OrderUI.jsp?id=" + request.getParameter("orderId") + "&SuccessMsg=Updated Order Successfully!");
                        break;
                    case 3:  //delete
                        com.busy.dao.Order.deleteOrderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order", request.getParameter("id"));
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Deleted Order Successfully!");
                        break;
                    case 4:  //remove all records
                        com.busy.dao.Order.deleteAllOrder();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order");
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error editing Order, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error adding Order.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error editing Order.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error deleting Order.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error clearing Order.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Unknown Error Order, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("page_template"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = PageTemplate.addPageTemplate(request.getParameter("templateName"), request.getParameter("templateMarkup"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate", id);
                        }
                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Added PageTemplate Successfully!");
                        break;
                    case 2: //update            
                        PageTemplate.updatePageTemplate(Integer.parseInt(request.getParameter("pageTemplateId")), request.getParameter("templateName"), request.getParameter("templateMarkup"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate", Integer.parseInt(request.getParameter("pageTemplateId")));
                        response.sendRedirect("admin/PageTemplateUI.jsp?id=" + request.getParameter("pageTemplateId") + "&SuccessMsg=Updated PageTemplate Successfully!");
                        break;
                    case 3:  //delete
                        PageTemplate.deletePageTemplateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate", request.getParameter("id"));
                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Deleted PageTemplate Successfully!");
                        break;
                    case 4:  //remove all records
                        PageTemplate.deleteAllPageTemplate();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate");
                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error editing PageTemplate, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error adding PageTemplate.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error editing PageTemplate.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error deleting PageTemplate.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error clearing PageTemplate.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Unknown Error PageTemplate, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("paypal"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Paypal.addPaypal(request.getParameter("payPalURL"), request.getParameter("currencyCode"), request.getParameter("apiUserName"), request.getParameter("apiPassword"), request.getParameter("apiSignature"), request.getParameter("apiEndpoint"), Boolean.parseBoolean(request.getParameter("activeProfile")), request.getParameter("returnURL"), request.getParameter("cancelURL"), request.getParameter("paymentType"), request.getParameter("environment"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal", id);
                        }
                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Added Paypal Successfully!");
                        break;
                    case 2: //update            
                        Paypal.updatePaypal(Integer.parseInt(request.getParameter("paypalId")), request.getParameter("payPalURL"), request.getParameter("currencyCode"), request.getParameter("apiUserName"), request.getParameter("apiPassword"), request.getParameter("apiSignature"), request.getParameter("apiEndpoint"), Boolean.parseBoolean(request.getParameter("activeProfile")), request.getParameter("returnURL"), request.getParameter("cancelURL"), request.getParameter("paymentType"), request.getParameter("environment"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal", Integer.parseInt(request.getParameter("paypalId")));
                        response.sendRedirect("admin/PaypalUI.jsp?id=" + request.getParameter("paypalId") + "&SuccessMsg=Updated Paypal Successfully!");
                        break;
                    case 3:  //delete
                        Paypal.deletePaypalById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal", request.getParameter("id"));
                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Deleted Paypal Successfully!");
                        break;
                    case 4:  //remove all records
                        Paypal.deleteAllPaypal();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal");
                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error editing Paypal, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error adding Paypal.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error editing Paypal.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error deleting Paypal.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error clearing Paypal.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Unknown Error Paypal, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("service"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Service.addService(Integer.parseInt(request.getParameter("serviceTypeId")), Integer.parseInt(request.getParameter("serviceChargeId")), request.getParameter("serviceName"), request.getParameter("serviceDescription"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service", id);
                        }
                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Added Service Successfully!");
                        break;
                    case 2: //update            
                        Service.updateService(Integer.parseInt(request.getParameter("serviceId")), Integer.parseInt(request.getParameter("serviceTypeId")), Integer.parseInt(request.getParameter("serviceChargeId")), request.getParameter("serviceName"), request.getParameter("serviceDescription"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service", Integer.parseInt(request.getParameter("serviceId")));
                        response.sendRedirect("admin/ServiceUI.jsp?id=" + request.getParameter("serviceId") + "&SuccessMsg=Updated Service Successfully!");
                        break;
                    case 3:  //delete
                        Service.deleteServiceById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service", request.getParameter("id"));
                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Deleted Service Successfully!");
                        break;
                    case 4:  //remove all records
                        Service.deleteAllService();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service");
                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error editing Service, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error adding Service.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error editing Service.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error deleting Service.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error clearing Service.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Unknown Error Service, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("service_charge"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ServiceCharge.addServiceCharge(request.getParameter("serviceChargeName"), request.getParameter("serviceChargeDescription"), request.getParameter("serviceChargeRate"), request.getParameter("serviceChargeUnits"), Integer.parseInt(request.getParameter("userServiceId")), operatingDateFormat.parse(request.getParameter("serviceChargeDate")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge", id);
                        }
                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Added ServiceCharge Successfully!");
                        break;
                    case 2: //update            
                        ServiceCharge.updateServiceCharge(Integer.parseInt(request.getParameter("serviceChargeId")), request.getParameter("serviceChargeName"), request.getParameter("serviceChargeDescription"), request.getParameter("serviceChargeRate"), request.getParameter("serviceChargeUnits"), Integer.parseInt(request.getParameter("userServiceId")), operatingDateFormat.parse(request.getParameter("serviceChargeDate")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge", Integer.parseInt(request.getParameter("serviceChargeId")));
                        response.sendRedirect("admin/ServiceChargeUI.jsp?id=" + request.getParameter("serviceChargeId") + "&SuccessMsg=Updated ServiceCharge Successfully!");
                        break;
                    case 3:  //delete
                        ServiceCharge.deleteServiceChargeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge", request.getParameter("id"));
                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Deleted ServiceCharge Successfully!");
                        break;
                    case 4:  //remove all records
                        ServiceCharge.deleteAllServiceCharge();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge");
                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error editing ServiceCharge, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error adding ServiceCharge.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error editing ServiceCharge.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error deleting ServiceCharge.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error clearing ServiceCharge.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Unknown Error ServiceCharge, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("service_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ServiceType.addServiceType(request.getParameter("serviceTypeName"), request.getParameter("serviceTypeDesciption"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType", id);
                        }
                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Added ServiceType Successfully!");
                        break;
                    case 2: //update            
                        ServiceType.updateServiceType(Integer.parseInt(request.getParameter("serviceTypeId")), request.getParameter("serviceTypeName"), request.getParameter("serviceTypeDesciption"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType", Integer.parseInt(request.getParameter("serviceTypeId")));
                        response.sendRedirect("admin/ServiceTypeUI.jsp?id=" + request.getParameter("serviceTypeId") + "&SuccessMsg=Updated ServiceType Successfully!");
                        break;
                    case 3:  //delete
                        ServiceType.deleteServiceTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType", request.getParameter("id"));
                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Deleted ServiceType Successfully!");
                        break;
                    case 4:  //remove all records
                        ServiceType.deleteAllServiceType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType");
                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error editing ServiceType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error adding ServiceType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error editing ServiceType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error deleting ServiceType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error clearing ServiceType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Unknown Error ServiceType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("shipping"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Shipping.addShipping(request.getParameter("shippingRegion"), request.getParameter("shippingMethod"), Double.parseDouble(request.getParameter("shippingRate")), Double.parseDouble(request.getParameter("shippingAdditional")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping", id);
                        }
                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Added Shipping Successfully!");
                        break;
                    case 2: //update            
                        Shipping.updateShipping(Integer.parseInt(request.getParameter("shippingId")), request.getParameter("shippingRegion"), request.getParameter("shippingMethod"), Double.parseDouble(request.getParameter("shippingRate")), Double.parseDouble(request.getParameter("shippingAdditional")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping", Integer.parseInt(request.getParameter("shippingId")));
                        response.sendRedirect("admin/ShippingUI.jsp?id=" + request.getParameter("shippingId") + "&SuccessMsg=Updated Shipping Successfully!");
                        break;
                    case 3:  //delete
                        Shipping.deleteShippingById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping", request.getParameter("id"));
                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Deleted Shipping Successfully!");
                        break;
                    case 4:  //remove all records
                        Shipping.deleteAllShipping();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping");
                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error editing Shipping, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error adding Shipping.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error editing Shipping.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error deleting Shipping.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error clearing Shipping.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Unknown Error Shipping, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("shopping_cart"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ShoppingCart.addShoppingCart(Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("orderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCart", id);
                        }
                        response.sendRedirect("admin/ShoppingCartUI.jsp?SuccessMsg=Added ShoppingCart Successfully!");
                        break;
                    case 2: //update            
                        ShoppingCart.updateShoppingCart(Integer.parseInt(request.getParameter("shoppingCartId")), Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("orderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCart", Integer.parseInt(request.getParameter("shoppingCartId")));
                        response.sendRedirect("admin/ShoppingCartUI.jsp?id=" + request.getParameter("shoppingCartId") + "&SuccessMsg=Updated ShoppingCart Successfully!");
                        break;
                    case 3:  //delete
                        ShoppingCart.deleteShoppingCartById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCart", request.getParameter("id"));
                        response.sendRedirect("admin/ShoppingCartUI.jsp?SuccessMsg=Deleted ShoppingCart Successfully!");
                        break;
                    case 4:  //remove all records
                        ShoppingCart.deleteAllShoppingCart();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCart");
                        response.sendRedirect("admin/ShoppingCartUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ShoppingCartUI.jsp?ErrorMsg=Error editing ShoppingCart, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ShoppingCartUI.jsp?ErrorMsg=Error adding ShoppingCart.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ShoppingCartUI.jsp?ErrorMsg=Error editing ShoppingCart.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ShoppingCartUI.jsp?ErrorMsg=Error deleting ShoppingCart.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ShoppingCartUI.jsp?ErrorMsg=Error clearing ShoppingCart.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ShoppingCartUI.jsp?ErrorMsg=Unknown Error ShoppingCart, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("shopping_cart_item"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ShoppingCartItem.addShoppingCartItem(Integer.parseInt(request.getParameter("shoppingCartId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemQuantity")), request.getParameter("itemOption"), Double.parseDouble(request.getParameter("itemUnitPrice")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCartItem", id);
                        }
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?SuccessMsg=Added ShoppingCartItem Successfully!");
                        break;
                    case 2: //update            
                        ShoppingCartItem.updateShoppingCartItem(Integer.parseInt(request.getParameter("shoppingCartItemId")), Integer.parseInt(request.getParameter("shoppingCartId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemQuantity")), request.getParameter("itemOption"), Double.parseDouble(request.getParameter("itemUnitPrice")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCartItem", Integer.parseInt(request.getParameter("shoppingCartItemId")));
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?id=" + request.getParameter("shoppingCartItemId") + "&SuccessMsg=Updated ShoppingCartItem Successfully!");
                        break;
                    case 3:  //delete
                        ShoppingCartItem.deleteShoppingCartItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCartItem", request.getParameter("id"));
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?SuccessMsg=Deleted ShoppingCartItem Successfully!");
                        break;
                    case 4:  //remove all records
                        ShoppingCartItem.deleteAllShoppingCartItem();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ShoppingCartItem");
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?ErrorMsg=Error editing ShoppingCartItem, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?ErrorMsg=Error adding ShoppingCartItem.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?ErrorMsg=Error editing ShoppingCartItem.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?ErrorMsg=Error deleting ShoppingCartItem.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?ErrorMsg=Error clearing ShoppingCartItem.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ShoppingCartItemUI.jsp?ErrorMsg=Unknown Error ShoppingCartItem, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_attribute"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteAttribute.addSiteAttribute(request.getParameter("attributeKey"), request.getParameter("attributeValue"), request.getParameter("attributeType"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute", id);
                        }
                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Added SiteAttribute Successfully!");
                        break;
                    case 2: //update            
                        SiteAttribute.updateSiteAttribute(Integer.parseInt(request.getParameter("siteAttributeId")), request.getParameter("attributeKey"), request.getParameter("attributeValue"), request.getParameter("attributeType"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute", Integer.parseInt(request.getParameter("siteAttributeId")));
                        response.sendRedirect("admin/SiteAttributeUI.jsp?id=" + request.getParameter("siteAttributeId") + "&SuccessMsg=Updated SiteAttribute Successfully!");
                        break;
                    case 3:  //delete
                        SiteAttribute.deleteSiteAttributeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute", request.getParameter("id"));
                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Deleted SiteAttribute Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteAttribute.deleteAllSiteAttribute();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute");
                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error editing SiteAttribute, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error adding SiteAttribute.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error editing SiteAttribute.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error deleting SiteAttribute.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error clearing SiteAttribute.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Unknown Error SiteAttribute, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_file"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteFile.addSiteFile(request.getParameter("fileName"), request.getParameter("fileDescription"), request.getParameter("fileLabel"), Integer.parseInt(request.getParameter("folderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile", id);
                        }
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Added SiteFile Successfully!");
                        break;
                    case 2: //update            
                        SiteFile.updateSiteFile(Integer.parseInt(request.getParameter("siteFileId")), request.getParameter("fileName"), request.getParameter("fileDescription"), request.getParameter("fileLabel"), Integer.parseInt(request.getParameter("folderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile", Integer.parseInt(request.getParameter("siteFileId")));
                        response.sendRedirect("admin/SiteFileUI.jsp?id=" + request.getParameter("siteFileId") + "&SuccessMsg=Updated SiteFile Successfully!");
                        break;
                    case 3:  //delete
                        SiteFile.deleteSiteFileById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile", request.getParameter("id"));
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Deleted SiteFile Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteFile.deleteAllSiteFile();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile");
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error editing SiteFile, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error adding SiteFile.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error editing SiteFile.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error deleting SiteFile.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error clearing SiteFile.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Unknown Error SiteFile, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_folder"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteFolder.addSiteFolder(request.getParameter("siteFolderName"), request.getParameter("siteFolderDescription"), Integer.parseInt(request.getParameter("siteFolderRank")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder", id);
                        }
                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Added SiteFolder Successfully!");
                        break;
                    case 2: //update            
                        SiteFolder.updateSiteFolder(Integer.parseInt(request.getParameter("siteFolderId")), request.getParameter("siteFolderName"), request.getParameter("siteFolderDescription"), Integer.parseInt(request.getParameter("siteFolderRank")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder", Integer.parseInt(request.getParameter("siteFolderId")));
                        response.sendRedirect("admin/SiteFolderUI.jsp?id=" + request.getParameter("siteFolderId") + "&SuccessMsg=Updated SiteFolder Successfully!");
                        break;
                    case 3:  //delete
                        SiteFolder.deleteSiteFolderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder", request.getParameter("id"));
                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Deleted SiteFolder Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteFolder.deleteAllSiteFolder();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder");
                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error editing SiteFolder, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error adding SiteFolder.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error editing SiteFolder.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error deleting SiteFolder.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error clearing SiteFolder.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Unknown Error SiteFolder, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_info"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteInfo.addSiteInfo(request.getParameter("siteAdministratorName"), request.getParameter("siteAdministratorEmail"), request.getParameter("siteNotificationEmail"), request.getParameter("siteURL"), request.getParameter("siteTestingURL"), Integer.parseInt(request.getParameter("siteMode")), request.getParameter("siteTestingEmail"), request.getParameter("siteTestingEmailPassword"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteInfo", id);
                        }
                        response.sendRedirect("admin/SiteInfoUI.jsp?SuccessMsg=Added SiteInfo Successfully!");
                        break;
                    case 2: //update            
                        SiteInfo.updateSiteInfo(Integer.parseInt(request.getParameter("siteInfoId")), request.getParameter("siteAdministratorName"), request.getParameter("siteAdministratorEmail"), request.getParameter("siteNotificationEmail"), request.getParameter("siteURL"), request.getParameter("siteTestingURL"), Integer.parseInt(request.getParameter("siteMode")), request.getParameter("siteTestingEmail"), request.getParameter("siteTestingEmailPassword"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteInfo", Integer.parseInt(request.getParameter("siteInfoId")));
                        response.sendRedirect("admin/SiteInfoUI.jsp?id=" + request.getParameter("siteInfoId") + "&SuccessMsg=Updated SiteInfo Successfully!");
                        break;
                    case 3:  //delete
                        SiteInfo.deleteSiteInfoById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteInfo", request.getParameter("id"));
                        response.sendRedirect("admin/SiteInfoUI.jsp?SuccessMsg=Deleted SiteInfo Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteInfo.deleteAllSiteInfo();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteInfo");
                        response.sendRedirect("admin/SiteInfoUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteInfoUI.jsp?ErrorMsg=Error editing SiteInfo, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteInfoUI.jsp?ErrorMsg=Error adding SiteInfo.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteInfoUI.jsp?ErrorMsg=Error editing SiteInfo.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteInfoUI.jsp?ErrorMsg=Error deleting SiteInfo.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteInfoUI.jsp?ErrorMsg=Error clearing SiteInfo.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteInfoUI.jsp?ErrorMsg=Unknown Error SiteInfo, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_resource_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteResourceType.addSiteResourceType(request.getParameter("resourceName"), request.getParameter("resourceValue"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteResourceType", id);
                        }
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?SuccessMsg=Added SiteResourceType Successfully!");
                        break;
                    case 2: //update            
                        SiteResourceType.updateSiteResourceType(Integer.parseInt(request.getParameter("siteResourceTypeId")), request.getParameter("resourceName"), request.getParameter("resourceValue"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteResourceType", Integer.parseInt(request.getParameter("siteResourceTypeId")));
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?id=" + request.getParameter("siteResourceTypeId") + "&SuccessMsg=Updated SiteResourceType Successfully!");
                        break;
                    case 3:  //delete
                        SiteResourceType.deleteSiteResourceTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteResourceType", request.getParameter("id"));
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?SuccessMsg=Deleted SiteResourceType Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteResourceType.deleteAllSiteResourceType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteResourceType");
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?ErrorMsg=Error editing SiteResourceType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?ErrorMsg=Error adding SiteResourceType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?ErrorMsg=Error editing SiteResourceType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?ErrorMsg=Error deleting SiteResourceType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?ErrorMsg=Error clearing SiteResourceType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteResourceTypeUI.jsp?ErrorMsg=Unknown Error SiteResourceType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_template"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteTemplate.addSiteTemplate(request.getParameter("templateName"), Integer.parseInt(request.getParameter("templateTypeId")), Integer.parseInt(request.getParameter("templateStatus")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplate", id);
                        }
                        response.sendRedirect("admin/SiteTemplateUI.jsp?SuccessMsg=Added SiteTemplate Successfully!");
                        break;
                    case 2: //update            
                        SiteTemplate.updateSiteTemplate(Integer.parseInt(request.getParameter("siteTemplateId")), request.getParameter("templateName"), Integer.parseInt(request.getParameter("templateTypeId")), Integer.parseInt(request.getParameter("templateStatus")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplate", Integer.parseInt(request.getParameter("siteTemplateId")));
                        response.sendRedirect("admin/SiteTemplateUI.jsp?id=" + request.getParameter("siteTemplateId") + "&SuccessMsg=Updated SiteTemplate Successfully!");
                        break;
                    case 3:  //delete
                        SiteTemplate.deleteSiteTemplateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplate", request.getParameter("id"));
                        response.sendRedirect("admin/SiteTemplateUI.jsp?SuccessMsg=Deleted SiteTemplate Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteTemplate.deleteAllSiteTemplate();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplate");
                        response.sendRedirect("admin/SiteTemplateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteTemplateUI.jsp?ErrorMsg=Error editing SiteTemplate, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteTemplateUI.jsp?ErrorMsg=Error adding SiteTemplate.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteTemplateUI.jsp?ErrorMsg=Error editing SiteTemplate.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteTemplateUI.jsp?ErrorMsg=Error deleting SiteTemplate.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteTemplateUI.jsp?ErrorMsg=Error clearing SiteTemplate.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteTemplateUI.jsp?ErrorMsg=Unknown Error SiteTemplate, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_template_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteTemplateType.addSiteTemplateType(request.getParameter("typeName"), request.getParameter("typeValue"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateType", id);
                        }
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?SuccessMsg=Added SiteTemplateType Successfully!");
                        break;
                    case 2: //update            
                        SiteTemplateType.updateSiteTemplateType(Integer.parseInt(request.getParameter("siteTemplateTypeId")), request.getParameter("typeName"), request.getParameter("typeValue"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateType", Integer.parseInt(request.getParameter("siteTemplateTypeId")));
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?id=" + request.getParameter("siteTemplateTypeId") + "&SuccessMsg=Updated SiteTemplateType Successfully!");
                        break;
                    case 3:  //delete
                        SiteTemplateType.deleteSiteTemplateTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateType", request.getParameter("id"));
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?SuccessMsg=Deleted SiteTemplateType Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteTemplateType.deleteAllSiteTemplateType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateType");
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?ErrorMsg=Error editing SiteTemplateType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?ErrorMsg=Error adding SiteTemplateType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?ErrorMsg=Error editing SiteTemplateType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?ErrorMsg=Error deleting SiteTemplateType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?ErrorMsg=Error clearing SiteTemplateType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteTemplateTypeUI.jsp?ErrorMsg=Unknown Error SiteTemplateType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_template_url"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteTemplateUrl.addSiteTemplateUrl(request.getParameter("url"), Integer.parseInt(request.getParameter("siteTemplateId")), request.getParameter("resourceTypeId"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateUrl", id);
                        }
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?SuccessMsg=Added SiteTemplateUrl Successfully!");
                        break;
                    case 2: //update            
                        SiteTemplateUrl.updateSiteTemplateUrl(Integer.parseInt(request.getParameter("siteTemplateUrlId")), request.getParameter("url"), Integer.parseInt(request.getParameter("siteTemplateId")), request.getParameter("resourceTypeId"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateUrl", Integer.parseInt(request.getParameter("siteTemplateUrlId")));
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?id=" + request.getParameter("siteTemplateUrlId") + "&SuccessMsg=Updated SiteTemplateUrl Successfully!");
                        break;
                    case 3:  //delete
                        SiteTemplateUrl.deleteSiteTemplateUrlById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateUrl", request.getParameter("id"));
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?SuccessMsg=Deleted SiteTemplateUrl Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteTemplateUrl.deleteAllSiteTemplateUrl();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteTemplateUrl");
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?ErrorMsg=Error editing SiteTemplateUrl, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?ErrorMsg=Error adding SiteTemplateUrl.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?ErrorMsg=Error editing SiteTemplateUrl.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?ErrorMsg=Error deleting SiteTemplateUrl.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?ErrorMsg=Error clearing SiteTemplateUrl.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteTemplateUrlUI.jsp?ErrorMsg=Unknown Error SiteTemplateUrl, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("slider"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Slider.addSlider(request.getParameter("sliderName"), Integer.parseInt(request.getParameter("sliderTypeId")), Integer.parseInt(request.getParameter("formId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider", id);
                        }
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Added Slider Successfully!");
                        break;
                    case 2: //update            
                        Slider.updateSlider(Integer.parseInt(request.getParameter("sliderId")), request.getParameter("sliderName"), Integer.parseInt(request.getParameter("sliderTypeId")), Integer.parseInt(request.getParameter("formId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider", Integer.parseInt(request.getParameter("sliderId")));
                        response.sendRedirect("admin/SliderUI.jsp?id=" + request.getParameter("sliderId") + "&SuccessMsg=Updated Slider Successfully!");
                        break;
                    case 3:  //delete
                        Slider.deleteSliderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider", request.getParameter("id"));
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Deleted Slider Successfully!");
                        break;
                    case 4:  //remove all records
                        Slider.deleteAllSlider();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider");
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error editing Slider, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error adding Slider.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error editing Slider.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error deleting Slider.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error clearing Slider.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Unknown Error Slider, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("slider_item"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SliderItem.addSliderItem(request.getParameter("sliderItemTitle"), request.getParameter("sliderItemDescription"), request.getParameter("sliderItemUrl"), request.getParameter("sliderItemImageName"), request.getParameter("sliderItemImageAlt"), Integer.parseInt(request.getParameter("sliderItemRank")), Integer.parseInt(request.getParameter("sliderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem", id);
                        }
                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Added SliderItem Successfully!");
                        break;
                    case 2: //update            
                        SliderItem.updateSliderItem(Integer.parseInt(request.getParameter("sliderItemId")), request.getParameter("sliderItemTitle"), request.getParameter("sliderItemDescription"), request.getParameter("sliderItemUrl"), request.getParameter("sliderItemImageName"), request.getParameter("sliderItemImageAlt"), Integer.parseInt(request.getParameter("sliderItemRank")), Integer.parseInt(request.getParameter("sliderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem", Integer.parseInt(request.getParameter("sliderItemId")));
                        response.sendRedirect("admin/SliderItemUI.jsp?id=" + request.getParameter("sliderItemId") + "&SuccessMsg=Updated SliderItem Successfully!");
                        break;
                    case 3:  //delete
                        SliderItem.deleteSliderItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem", request.getParameter("id"));
                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Deleted SliderItem Successfully!");
                        break;
                    case 4:  //remove all records
                        SliderItem.deleteAllSliderItem();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem");
                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error editing SliderItem, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error adding SliderItem.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error editing SliderItem.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error deleting SliderItem.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error clearing SliderItem.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Unknown Error SliderItem, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("slider_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SliderType.addSliderType(request.getParameter("sliderTypeName"), request.getParameter("sliderTypeCode"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType", id);
                        }
                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Added SliderType Successfully!");
                        break;
                    case 2: //update            
                        SliderType.updateSliderType(Integer.parseInt(request.getParameter("sliderTypeId")), request.getParameter("sliderTypeName"), request.getParameter("sliderTypeCode"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType", Integer.parseInt(request.getParameter("sliderTypeId")));
                        response.sendRedirect("admin/SliderTypeUI.jsp?id=" + request.getParameter("sliderTypeId") + "&SuccessMsg=Updated SliderType Successfully!");
                        break;
                    case 3:  //delete
                        SliderType.deleteSliderTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType", request.getParameter("id"));
                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Deleted SliderType Successfully!");
                        break;
                    case 4:  //remove all records
                        SliderType.deleteAllSliderType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType");
                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error editing SliderType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error adding SliderType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error editing SliderType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error deleting SliderType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error clearing SliderType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Unknown Error SliderType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("store_info"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = StoreInfo.addStoreInfo(request.getParameter("storeLogoTitle"), request.getParameter("storeLogoImage"), request.getParameter("storeName"), request.getParameter("companyName"), Integer.parseInt(request.getParameter("storeLocalization")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo", id);
                        }
                        response.sendRedirect("admin/StoreInfoUI.jsp?SuccessMsg=Added StoreInfo Successfully!");
                        break;
                    case 2: //update            
                        StoreInfo.updateStoreInfo(Integer.parseInt(request.getParameter("storeInfoId")), request.getParameter("storeLogoTitle"), request.getParameter("storeLogoImage"), request.getParameter("storeName"), request.getParameter("companyName"), Integer.parseInt(request.getParameter("storeLocalization")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo", Integer.parseInt(request.getParameter("storeInfoId")));
                        response.sendRedirect("admin/StoreInfoUI.jsp?id=" + request.getParameter("storeInfoId") + "&SuccessMsg=Updated StoreInfo Successfully!");
                        break;
                    case 3:  //delete
                        StoreInfo.deleteStoreInfoById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo", request.getParameter("id"));
                        response.sendRedirect("admin/StoreInfoUI.jsp?SuccessMsg=Deleted StoreInfo Successfully!");
                        break;
                    case 4:  //remove all records
                        StoreInfo.deleteAllStoreInfo();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo");
                        response.sendRedirect("admin/StoreInfoUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/StoreInfoUI.jsp?ErrorMsg=Error editing StoreInfo, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/StoreInfoUI.jsp?ErrorMsg=Error adding StoreInfo.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/StoreInfoUI.jsp?ErrorMsg=Error editing StoreInfo.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/StoreInfoUI.jsp?ErrorMsg=Error deleting StoreInfo.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/StoreInfoUI.jsp?ErrorMsg=Error clearing StoreInfo.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/StoreInfoUI.jsp?ErrorMsg=Unknown Error StoreInfo, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("tax"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Tax.addTax(request.getParameter("taxState"), Double.parseDouble(request.getParameter("taxRate")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Tax", id);
                        }
                        response.sendRedirect("admin/TaxUI.jsp?SuccessMsg=Added Tax Successfully!");
                        break;
                    case 2: //update            
                        Tax.updateTax(Integer.parseInt(request.getParameter("taxId")), request.getParameter("taxState"), Double.parseDouble(request.getParameter("taxRate")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Tax", Integer.parseInt(request.getParameter("taxId")));
                        response.sendRedirect("admin/TaxUI.jsp?id=" + request.getParameter("taxId") + "&SuccessMsg=Updated Tax Successfully!");
                        break;
                    case 3:  //delete
                        Tax.deleteTaxById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Tax", request.getParameter("id"));
                        response.sendRedirect("admin/TaxUI.jsp?SuccessMsg=Deleted Tax Successfully!");
                        break;
                    case 4:  //remove all records
                        Tax.deleteAllTax();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Tax");
                        response.sendRedirect("admin/TaxUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TaxUI.jsp?ErrorMsg=Error editing Tax, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TaxUI.jsp?ErrorMsg=Error adding Tax.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TaxUI.jsp?ErrorMsg=Error editing Tax.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TaxUI.jsp?ErrorMsg=Error deleting Tax.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TaxUI.jsp?ErrorMsg=Error clearing Tax.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TaxUI.jsp?ErrorMsg=Unknown Error Tax, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("template"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Template.addTemplate(request.getParameter("templateName"), request.getParameter("templateBody"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template", id);
                        }
                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Added Template Successfully!");
                        break;
                    case 2: //update            
                        Template.updateTemplate(Integer.parseInt(request.getParameter("templateId")), request.getParameter("templateName"), request.getParameter("templateBody"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template", Integer.parseInt(request.getParameter("templateId")));
                        response.sendRedirect("admin/TemplateUI.jsp?id=" + request.getParameter("templateId") + "&SuccessMsg=Updated Template Successfully!");
                        break;
                    case 3:  //delete
                        Template.deleteTemplateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template", request.getParameter("id"));
                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Deleted Template Successfully!");
                        break;
                    case 4:  //remove all records
                        Template.deleteAllTemplate();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template");
                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error editing Template, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error adding Template.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error editing Template.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error deleting Template.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error clearing Template.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Unknown Error Template, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("text_string"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = TextString.addTextString(request.getParameter("textStringKey"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString", id);
                        }
                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Added TextString Successfully!");
                        break;
                    case 2: //update            
                        TextString.updateTextString(Integer.parseInt(request.getParameter("textStringId")), request.getParameter("textStringKey"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString", Integer.parseInt(request.getParameter("textStringId")));
                        response.sendRedirect("admin/TextStringUI.jsp?id=" + request.getParameter("textStringId") + "&SuccessMsg=Updated TextString Successfully!");
                        break;
                    case 3:  //delete
                        TextString.deleteTextStringById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString", request.getParameter("id"));
                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Deleted TextString Successfully!");
                        break;
                    case 4:  //remove all records
                        TextString.deleteAllTextString();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString");
                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error editing TextString, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error adding TextString.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error editing TextString.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error deleting TextString.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error clearing TextString.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Unknown Error TextString, possibly an invalid action.");
                        break;
                }
            }
        }
    
        if(request.getParameter("form").equals("text_string_local"))
        {   
            try
            {     
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = TextStringLocal.addTextStringLocal(request.getParameter("textStringValue"), Integer.parseInt(request.getParameter("textStringId")), Integer.parseInt(request.getParameter("localeId")));                
                        if(id != 0) { Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal", id); }
                        response.sendRedirect("admin/TextStringLocalUI.jsp?SuccessMsg=Added TextStringLocal Successfully!");
                        break;
                    case 2: //update            
                        TextStringLocal.updateTextStringLocal(Integer.parseInt(request.getParameter("textStringLocalId")), request.getParameter("textStringValue"), Integer.parseInt(request.getParameter("textStringId")), Integer.parseInt(request.getParameter("localeId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal", Integer.parseInt(request.getParameter("textStringLocalId")));
                        response.sendRedirect("admin/TextStringLocalUI.jsp?id=" + request.getParameter("textStringLocalId") + "&SuccessMsg=Updated TextStringLocal Successfully!");
                        break;
                    case 3:  //delete
                        TextStringLocal.deleteTextStringLocalById(request.getParameter("id"));                        
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal", request.getParameter("id"));
                        response.sendRedirect("admin/TextStringLocalUI.jsp?SuccessMsg=Deleted TextStringLocal Successfully!");
                        break;
                    case 4:  //remove all records
                        TextStringLocal.deleteAllTextStringLocal();                        
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal");
                        response.sendRedirect("admin/TextStringLocalUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error editing TextStringLocal, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error Performing Operation:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error adding TextStringLocal.");   break; //create
                    case 2:  response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error editing TextStringLocal.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error deleting TextStringLocal."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error clearing TextStringLocal."); break; //clear                          
                    default: response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Unknown Error TextStringLocal, possibly an invalid action."); break;
                }
            }
        }
        
        if (request.getParameter("form").equals("type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = Type.addType(request.getParameter("typeName"), request.getParameter("typeDescription"), request.getParameter("typeRedirectURL"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Type", id);
                        }
                        response.sendRedirect("admin/TypeUI.jsp?SuccessMsg=Added Type Successfully!");
                        break;
                    case 2: //update            
                        Type.updateType(Integer.parseInt(request.getParameter("typeId")), request.getParameter("typeName"), request.getParameter("typeDescription"), request.getParameter("typeRedirectURL"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Type", Integer.parseInt(request.getParameter("typeId")));
                        response.sendRedirect("admin/TypeUI.jsp?id=" + request.getParameter("typeId") + "&SuccessMsg=Updated Type Successfully!");
                        break;
                    case 3:  //delete
                        Type.deleteTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Type", request.getParameter("id"));
                        response.sendRedirect("admin/TypeUI.jsp?SuccessMsg=Deleted Type Successfully!");
                        break;
                    case 4:  //remove all records
                        Type.deleteAllType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Type");
                        response.sendRedirect("admin/TypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TypeUI.jsp?ErrorMsg=Error editing Type, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TypeUI.jsp?ErrorMsg=Error adding Type.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TypeUI.jsp?ErrorMsg=Error editing Type.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TypeUI.jsp?ErrorMsg=Error deleting Type.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TypeUI.jsp?ErrorMsg=Error clearing Type.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TypeUI.jsp?ErrorMsg=Unknown Error Type, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("user"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = User.addUser(Integer.parseInt(request.getParameter("brandId")), Integer.parseInt(request.getParameter("typeId")), request.getParameter("userName"), request.getParameter("userPassword"), request.getParameter("userSecurityQuestion"), request.getParameter("userSecurityAnswer"), request.getParameter("userImgURL"), request.getParameter("userEmail"), Integer.parseInt(request.getParameter("userEmailConfirmed")), request.getParameter("userWebUrl"), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "User", id);
                        }
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Added User Successfully!");
                        break;
                    case 2: //update            
                        User.updateUser(Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("brandId")), Integer.parseInt(request.getParameter("typeId")), request.getParameter("userName"), request.getParameter("userPassword"), request.getParameter("userSecurityQuestion"), request.getParameter("userSecurityAnswer"), request.getParameter("userImgURL"), request.getParameter("userEmail"), Integer.parseInt(request.getParameter("userEmailConfirmed")), request.getParameter("userWebUrl"), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "User", Integer.parseInt(request.getParameter("userId")));
                        response.sendRedirect("admin/UserUI.jsp?id=" + request.getParameter("userId") + "&SuccessMsg=Updated User Successfully!");
                        break;
                    case 3:  //delete
                        User.deleteUserById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "User", request.getParameter("id"));
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Deleted User Successfully!");
                        break;
                    case 4:  //remove all records
                        User.deleteAllUser();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "User");
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error editing User, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error adding User.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error editing User.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error deleting User.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error clearing User.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Unknown Error User, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("user_action"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = UserAction.addUserAction(Integer.parseInt(request.getParameter("userId")), operatingDateFormat.parse(request.getParameter("actionDate")), Integer.parseInt(request.getParameter("actionTypeId")), request.getParameter("actionDetail"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction", id);
                        }
                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Added UserAction Successfully!");
                        break;
                    case 2: //update            
                        UserAction.updateUserAction(Integer.parseInt(request.getParameter("userActionId")), Integer.parseInt(request.getParameter("userId")), operatingDateFormat.parse(request.getParameter("actionDate")), Integer.parseInt(request.getParameter("actionTypeId")), request.getParameter("actionDetail"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction", Integer.parseInt(request.getParameter("userActionId")));
                        response.sendRedirect("admin/UserActionUI.jsp?id=" + request.getParameter("userActionId") + "&SuccessMsg=Updated UserAction Successfully!");
                        break;
                    case 3:  //delete
                        UserAction.deleteUserActionById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction", request.getParameter("id"));
                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Deleted UserAction Successfully!");
                        break;
                    case 4:  //remove all records
                        UserAction.deleteAllUserAction();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction");
                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error editing UserAction, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error adding UserAction.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error editing UserAction.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error deleting UserAction.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error clearing UserAction.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Unknown Error UserAction, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("user_action_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = UserActionType.addUserActionType(request.getParameter("actionTypeName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType", id);
                        }
                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Added UserActionType Successfully!");
                        break;
                    case 2: //update            
                        UserActionType.updateUserActionType(Integer.parseInt(request.getParameter("userActionTypeId")), request.getParameter("actionTypeName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType", Integer.parseInt(request.getParameter("userActionTypeId")));
                        response.sendRedirect("admin/UserActionTypeUI.jsp?id=" + request.getParameter("userActionTypeId") + "&SuccessMsg=Updated UserActionType Successfully!");
                        break;
                    case 3:  //delete
                        UserActionType.deleteUserActionTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType", request.getParameter("id"));
                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Deleted UserActionType Successfully!");
                        break;
                    case 4:  //remove all records
                        UserActionType.deleteAllUserActionType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType");
                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error editing UserActionType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error adding UserActionType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error editing UserActionType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error deleting UserActionType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error clearing UserActionType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Unknown Error UserActionType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("user_role"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = UserRole.addUserRole(request.getParameter("userName"), request.getParameter("roleName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole", id);
                        }
                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Added UserRole Successfully!");
                        break;
                    case 2: //update            
                        UserRole.updateUserRole(request.getParameter("userName"), request.getParameter("roleName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole", Integer.parseInt(request.getParameter("userName")));
                        response.sendRedirect("admin/UserRoleUI.jsp?id=" + request.getParameter("userName") + "&SuccessMsg=Updated UserRole Successfully!");
                        break;
                    case 3:  //delete
                        UserRole.deleteUserRoleById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole", request.getParameter("id"));
                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Deleted UserRole Successfully!");
                        break;
                    case 4:  //remove all records
                        UserRole.deleteAllUserRole();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole");
                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error editing UserRole, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error adding UserRole.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error editing UserRole.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error deleting UserRole.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error clearing UserRole.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Unknown Error UserRole, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("user_service"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = UserService.addUserService(Integer.parseInt(request.getParameter("serviceId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogId")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("details"), request.getParameter("contractUrl"), request.getParameter("deliverableUrl"), Double.parseDouble(request.getParameter("depositAmount")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService", id);
                        }
                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Added UserService Successfully!");
                        break;
                    case 2: //update            
                        UserService.updateUserService(Integer.parseInt(request.getParameter("userServiceId")), Integer.parseInt(request.getParameter("serviceId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogId")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("details"), request.getParameter("contractUrl"), request.getParameter("deliverableUrl"), Double.parseDouble(request.getParameter("depositAmount")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService", Integer.parseInt(request.getParameter("userServiceId")));
                        response.sendRedirect("admin/UserServiceUI.jsp?id=" + request.getParameter("userServiceId") + "&SuccessMsg=Updated UserService Successfully!");
                        break;
                    case 3:  //delete
                        UserService.deleteUserServiceById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService", request.getParameter("id"));
                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Deleted UserService Successfully!");
                        break;
                    case 4:  //remove all records
                        UserService.deleteAllUserService();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService");
                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error editing UserService, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error Performing Operation:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error adding UserService.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error editing UserService.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error deleting UserService.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error clearing UserService.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Unknown Error UserService, possibly an invalid action.");
                        break;
                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
}
