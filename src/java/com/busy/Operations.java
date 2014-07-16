package com.busy;

import com.busy.dao.*;
import com.transitionsoft.Database;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Operations extends HttpServlet
{

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
                        int id = AddressDAO.addAddress(request.getParameter("recipient"), request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("stateProvince"), request.getParameter("zipPostalCode"), request.getParameter("country"), request.getParameter("region"), Integer.parseInt(request.getParameter("status")), request.getParameter("locale"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address", id);
                        }

                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Added Address Successfully!");
                        break;
                    case 2: //update            
                        AddressDAO.updateAddress(Integer.parseInt(request.getParameter("addressId")), request.getParameter("recipient"), request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("stateProvince"), request.getParameter("zipPostalCode"), request.getParameter("country"), request.getParameter("region"), Integer.parseInt(request.getParameter("status")), request.getParameter("locale"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address", Integer.parseInt(request.getParameter("addressId")));
                        response.sendRedirect("admin/AddressUI.jsp?id=" + request.getParameter("addressId") + "&SuccessMsg=Updated Address Successfully!");
                        break;
                    case 3:  //delete
                        AddressDAO.deleteAddressById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Address", request.getParameter("id"));

                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Deleted Address Successfully!");
                        break;
                    case 4:  //remove all records
                        AddressDAO.deleteAllAddress();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = AffiliateDAO.addAffiliate(request.getParameter("companyName"), request.getParameter("email"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("webUrl"), request.getParameter("details"), Integer.parseInt(request.getParameter("serviceHours")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate", id);
                        }

                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Added Affiliate Successfully!");
                        break;
                    case 2: //update            
                        AffiliateDAO.updateAffiliate(Integer.parseInt(request.getParameter("affiliateId")), request.getParameter("companyName"), request.getParameter("email"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("webUrl"), request.getParameter("details"), Integer.parseInt(request.getParameter("serviceHours")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate", Integer.parseInt(request.getParameter("affiliateId")));
                        response.sendRedirect("admin/AffiliateUI.jsp?id=" + request.getParameter("affiliateId") + "&SuccessMsg=Updated Affiliate Successfully!");
                        break;
                    case 3:  //delete
                        AffiliateDAO.deleteAffiliateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Affiliate", request.getParameter("id"));

                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Deleted Affiliate Successfully!");
                        break;
                    case 4:  //remove all records
                        AffiliateDAO.deleteAllAffiliate();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = BlogDAO.addBlog(request.getParameter("topic"), Integer.parseInt(request.getParameter("blogTypeId")), Integer.parseInt(request.getParameter("knowledgeBaseId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog", id);
                        }
                        Database.updateCount("Blog", 1);
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Added Blog Successfully!");
                        break;
                    case 2: //update            
                        BlogDAO.updateBlog(Integer.parseInt(request.getParameter("blogId")), request.getParameter("topic"), Integer.parseInt(request.getParameter("blogTypeId")), Integer.parseInt(request.getParameter("knowledgeBaseId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog", Integer.parseInt(request.getParameter("blogId")));
                        response.sendRedirect("admin/BlogUI.jsp?id=" + request.getParameter("blogId") + "&SuccessMsg=Updated Blog Successfully!");
                        break;
                    case 3:  //delete
                        BlogDAO.deleteBlogById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Blog", request.getParameter("id"));
                        Database.updateCount("Blog", -1);
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Deleted Blog Successfully!");
                        break;
                    case 4:  //remove all records
                        BlogDAO.deleteAllBlog();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = BlogPostDAO.addBlogPost(request.getParameter("title"), request.getParameter("content"), request.getParameter("imageURL"), request.getParameter("tags"), Integer.parseInt(request.getParameter("featured")), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("status")), request.getParameter("excerpt"), operatingDateFormat.parse(request.getParameter("lastModified")), request.getParameter("locale"), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("metaTagId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost", id);
                        }
                        Database.updateCount("BlogPost", 1);
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Added BlogPost Successfully!");
                        break;
                    case 2: //update            
                        BlogPostDAO.updateBlogPost(Integer.parseInt(request.getParameter("blogPostId")), request.getParameter("title"), request.getParameter("content"), request.getParameter("imageURL"), request.getParameter("tags"), Integer.parseInt(request.getParameter("featured")), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("status")), request.getParameter("excerpt"), operatingDateFormat.parse(request.getParameter("lastModified")), request.getParameter("locale"), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("metaTagId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost", Integer.parseInt(request.getParameter("blogPostId")));
                        response.sendRedirect("admin/BlogPostUI.jsp?id=" + request.getParameter("blogPostId") + "&SuccessMsg=Updated BlogPost Successfully!");
                        break;
                    case 3:  //delete
                        BlogPostDAO.deleteBlogPostById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPost", request.getParameter("id"));
                        Database.updateCount("BlogPost", -1);
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Deleted BlogPost Successfully!");
                        break;
                    case 4:  //remove all records
                        BlogPostDAO.deleteAllBlogPost();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("blog_post_category"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = BlogPostCategoryDAO.addBlogPostCategory(Integer.parseInt(request.getParameter("blogPostId")), Integer.parseInt(request.getParameter("postCategoryId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPostCategory", id);
                        }

                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?SuccessMsg=Added BlogPostCategory Successfully!");
                        break;
                    case 2: //update            
                        BlogPostCategoryDAO.updateBlogPostCategory(Integer.parseInt(request.getParameter("blogPostCategoryId")), Integer.parseInt(request.getParameter("blogPostId")), Integer.parseInt(request.getParameter("postCategoryId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPostCategory", Integer.parseInt(request.getParameter("blogPostCategoryId")));
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?id=" + request.getParameter("blogPostCategoryId") + "&SuccessMsg=Updated BlogPostCategory Successfully!");
                        break;
                    case 3:  //delete
                        BlogPostCategoryDAO.deleteBlogPostCategoryById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPostCategory", request.getParameter("id"));

                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?SuccessMsg=Deleted BlogPostCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        BlogPostCategoryDAO.deleteAllBlogPostCategory();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogPostCategory");
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error editing BlogPostCategory, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error adding BlogPostCategory.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error editing BlogPostCategory.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error deleting BlogPostCategory.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error clearing BlogPostCategory.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Unknown Error BlogPostCategory, possibly an invalid action.");
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
                        int id = BlogTypeDAO.addBlogType(request.getParameter("typeName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType", id);
                        }

                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Added BlogType Successfully!");
                        break;
                    case 2: //update            
                        BlogTypeDAO.updateBlogType(Integer.parseInt(request.getParameter("blogTypeId")), request.getParameter("typeName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType", Integer.parseInt(request.getParameter("blogTypeId")));
                        response.sendRedirect("admin/BlogTypeUI.jsp?id=" + request.getParameter("blogTypeId") + "&SuccessMsg=Updated BlogType Successfully!");
                        break;
                    case 3:  //delete
                        BlogTypeDAO.deleteBlogTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "BlogType", request.getParameter("id"));

                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Deleted BlogType Successfully!");
                        break;
                    case 4:  //remove all records
                        BlogTypeDAO.deleteAllBlogType();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = CategoryDAO.addCategory(request.getParameter("categoryName"), Integer.parseInt(request.getParameter("discountId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category", id);
                        }
                        Database.updateCount("Category", 1);
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Added Category Successfully!");
                        break;
                    case 2: //update            
                        CategoryDAO.updateCategory(Integer.parseInt(request.getParameter("categoryId")), request.getParameter("categoryName"), Integer.parseInt(request.getParameter("discountId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category", Integer.parseInt(request.getParameter("categoryId")));
                        response.sendRedirect("admin/CategoryUI.jsp?id=" + request.getParameter("categoryId") + "&SuccessMsg=Updated Category Successfully!");
                        break;
                    case 3:  //delete
                        CategoryDAO.deleteCategoryById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Category", request.getParameter("id"));
                        Database.updateCount("Category", -1);
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Deleted Category Successfully!");
                        break;
                    case 4:  //remove all records
                        CategoryDAO.deleteAllCategory();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = CommentDAO.addComment(request.getParameter("title"), request.getParameter("content"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("postId")), Integer.parseInt(request.getParameter("reviewId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment", id);
                        }
                        Database.updateCount("Comment", 1);
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Added Comment Successfully!");
                        break;
                    case 2: //update            
                        CommentDAO.updateComment(Integer.parseInt(request.getParameter("commentId")), request.getParameter("title"), request.getParameter("content"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("postId")), Integer.parseInt(request.getParameter("reviewId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment", Integer.parseInt(request.getParameter("commentId")));
                        response.sendRedirect("admin/CommentUI.jsp?id=" + request.getParameter("commentId") + "&SuccessMsg=Updated Comment Successfully!");
                        break;
                    case 3:  //delete
                        CommentDAO.deleteCommentById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Comment", request.getParameter("id"));
                        Database.updateCount("Comment", -1);
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Deleted Comment Successfully!");
                        break;
                    case 4:  //remove all records
                        CommentDAO.deleteAllComment();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ContactDAO.addContact(request.getParameter("title"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("position"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("email"), Integer.parseInt(request.getParameter("status")), request.getParameter("webUrl"), request.getParameter("info"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact", id);
                        }

                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Added Contact Successfully!");
                        break;
                    case 2: //update            
                        ContactDAO.updateContact(Integer.parseInt(request.getParameter("contactId")), request.getParameter("title"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("position"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("email"), Integer.parseInt(request.getParameter("status")), request.getParameter("webUrl"), request.getParameter("info"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact", Integer.parseInt(request.getParameter("contactId")));
                        response.sendRedirect("admin/ContactUI.jsp?id=" + request.getParameter("contactId") + "&SuccessMsg=Updated Contact Successfully!");
                        break;
                    case 3:  //delete
                        ContactDAO.deleteContactById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Contact", request.getParameter("id"));

                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Deleted Contact Successfully!");
                        break;
                    case 4:  //remove all records
                        ContactDAO.deleteAllContact();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = CustomerDAO.addCustomer(Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("billingAddress")), Integer.parseInt(request.getParameter("shippingAddress")), Integer.parseInt(request.getParameter("status")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer", id);
                        }

                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Added Customer Successfully!");
                        break;
                    case 2: //update            
                        CustomerDAO.updateCustomer(Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("billingAddress")), Integer.parseInt(request.getParameter("shippingAddress")), Integer.parseInt(request.getParameter("status")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer", Integer.parseInt(request.getParameter("customerId")));
                        response.sendRedirect("admin/CustomerUI.jsp?id=" + request.getParameter("customerId") + "&SuccessMsg=Updated Customer Successfully!");
                        break;
                    case 3:  //delete
                        CustomerDAO.deleteCustomerById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Customer", request.getParameter("id"));

                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Deleted Customer Successfully!");
                        break;
                    case 4:  //remove all records
                        CustomerDAO.deleteAllCustomer();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("customer_order"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = CustomerOrderDAO.addCustomerOrder(Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("orderId")), Integer.parseInt(request.getParameter("discountId")), request.getParameter("customerIp"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "CustomerOrder", id);
                        }

                        response.sendRedirect("admin/CustomerOrderUI.jsp?SuccessMsg=Added CustomerOrder Successfully!");
                        break;
                    case 2: //update            
                        CustomerOrderDAO.updateCustomerOrder(Integer.parseInt(request.getParameter("customerOrderId")), Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("orderId")), Integer.parseInt(request.getParameter("discountId")), request.getParameter("customerIp"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "CustomerOrder", Integer.parseInt(request.getParameter("customerOrderId")));
                        response.sendRedirect("admin/CustomerOrderUI.jsp?id=" + request.getParameter("customerOrderId") + "&SuccessMsg=Updated CustomerOrder Successfully!");
                        break;
                    case 3:  //delete
                        CustomerOrderDAO.deleteCustomerOrderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "CustomerOrder", request.getParameter("id"));

                        response.sendRedirect("admin/CustomerOrderUI.jsp?SuccessMsg=Deleted CustomerOrder Successfully!");
                        break;
                    case 4:  //remove all records
                        CustomerOrderDAO.deleteAllCustomerOrder();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "CustomerOrder");
                        response.sendRedirect("admin/CustomerOrderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error editing CustomerOrder, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error adding CustomerOrder.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error editing CustomerOrder.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error deleting CustomerOrder.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error clearing CustomerOrder.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Unknown Error CustomerOrder, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("dashboard"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = DashboardDAO.addDashboard(Integer.parseInt(request.getParameter("userCount")), Integer.parseInt(request.getParameter("blogPostCount")), Integer.parseInt(request.getParameter("itemCount")), Integer.parseInt(request.getParameter("orderCount")), Integer.parseInt(request.getParameter("siteFileCount")), Integer.parseInt(request.getParameter("imageCount")), Integer.parseInt(request.getParameter("blogCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("pageCount")), Integer.parseInt(request.getParameter("formCount")), Integer.parseInt(request.getParameter("sliderCount")), Integer.parseInt(request.getParameter("itemBrandCount")), Integer.parseInt(request.getParameter("categoryCount")), Integer.parseInt(request.getParameter("itemOptionCount")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Dashboard", id);
                        }

                        response.sendRedirect("admin/DashboardUI.jsp?SuccessMsg=Added Dashboard Successfully!");
                        break;
                    case 2: //update            
                        DashboardDAO.updateDashboard(Integer.parseInt(request.getParameter("dashboardId")), Integer.parseInt(request.getParameter("userCount")), Integer.parseInt(request.getParameter("blogPostCount")), Integer.parseInt(request.getParameter("itemCount")), Integer.parseInt(request.getParameter("orderCount")), Integer.parseInt(request.getParameter("siteFileCount")), Integer.parseInt(request.getParameter("imageCount")), Integer.parseInt(request.getParameter("blogCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("pageCount")), Integer.parseInt(request.getParameter("formCount")), Integer.parseInt(request.getParameter("sliderCount")), Integer.parseInt(request.getParameter("itemBrandCount")), Integer.parseInt(request.getParameter("categoryCount")), Integer.parseInt(request.getParameter("itemOptionCount")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Dashboard", Integer.parseInt(request.getParameter("dashboardId")));
                        response.sendRedirect("admin/DashboardUI.jsp?id=" + request.getParameter("dashboardId") + "&SuccessMsg=Updated Dashboard Successfully!");
                        break;
                    case 3:  //delete
                        DashboardDAO.deleteDashboardById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Dashboard", request.getParameter("id"));

                        response.sendRedirect("admin/DashboardUI.jsp?SuccessMsg=Deleted Dashboard Successfully!");
                        break;
                    case 4:  //remove all records
                        DashboardDAO.deleteAllDashboard();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Dashboard");
                        response.sendRedirect("admin/DashboardUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error editing Dashboard, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error adding Dashboard.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error editing Dashboard.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error deleting Dashboard.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error clearing Dashboard.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Unknown Error Dashboard, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("discount"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = DiscountDAO.addDiscount(request.getParameter("discountName"), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("discountPercent")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("couponCode"), Integer.parseInt(request.getParameter("status")), Boolean.parseBoolean(request.getParameter("askCouponCode")), Boolean.parseBoolean(request.getParameter("usePercentage")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Discount", id);
                        }

                        response.sendRedirect("admin/DiscountUI.jsp?SuccessMsg=Added Discount Successfully!");
                        break;
                    case 2: //update            
                        DiscountDAO.updateDiscount(Integer.parseInt(request.getParameter("discountId")), request.getParameter("discountName"), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("discountPercent")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("couponCode"), Integer.parseInt(request.getParameter("status")), Boolean.parseBoolean(request.getParameter("askCouponCode")), Boolean.parseBoolean(request.getParameter("usePercentage")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Discount", Integer.parseInt(request.getParameter("discountId")));
                        response.sendRedirect("admin/DiscountUI.jsp?id=" + request.getParameter("discountId") + "&SuccessMsg=Updated Discount Successfully!");
                        break;
                    case 3:  //delete
                        DiscountDAO.deleteDiscountById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Discount", request.getParameter("id"));

                        response.sendRedirect("admin/DiscountUI.jsp?SuccessMsg=Deleted Discount Successfully!");
                        break;
                    case 4:  //remove all records
                        DiscountDAO.deleteAllDiscount();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Discount");
                        response.sendRedirect("admin/DiscountUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error editing Discount, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error adding Discount.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error editing Discount.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error deleting Discount.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error clearing Discount.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Unknown Error Discount, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("file_folder"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = FileFolderDAO.addFileFolder(Integer.parseInt(request.getParameter("siteFileId")), Integer.parseInt(request.getParameter("siteFolderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "FileFolder", id);
                        }

                        response.sendRedirect("admin/FileFolderUI.jsp?SuccessMsg=Added FileFolder Successfully!");
                        break;
                    case 2: //update            
                        FileFolderDAO.updateFileFolder(Integer.parseInt(request.getParameter("fileFolderId")), Integer.parseInt(request.getParameter("siteFileId")), Integer.parseInt(request.getParameter("siteFolderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "FileFolder", Integer.parseInt(request.getParameter("fileFolderId")));
                        response.sendRedirect("admin/FileFolderUI.jsp?id=" + request.getParameter("fileFolderId") + "&SuccessMsg=Updated FileFolder Successfully!");
                        break;
                    case 3:  //delete
                        FileFolderDAO.deleteFileFolderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "FileFolder", request.getParameter("id"));

                        response.sendRedirect("admin/FileFolderUI.jsp?SuccessMsg=Deleted FileFolder Successfully!");
                        break;
                    case 4:  //remove all records
                        FileFolderDAO.deleteAllFileFolder();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "FileFolder");
                        response.sendRedirect("admin/FileFolderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error editing FileFolder, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error adding FileFolder.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error editing FileFolder.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error deleting FileFolder.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error clearing FileFolder.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Unknown Error FileFolder, possibly an invalid action.");
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
                        int id = FormDAO.addForm(request.getParameter("formName"), request.getParameter("description"), request.getParameter("submissionEmail"), request.getParameter("submissionMethod"), request.getParameter("action"), Boolean.parseBoolean(request.getParameter("resettable")), Boolean.parseBoolean(request.getParameter("fileUpload")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form", id);
                        }
                        Database.updateCount("Form", 1);
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Added Form Successfully!");
                        break;
                    case 2: //update            
                        FormDAO.updateForm(Integer.parseInt(request.getParameter("formId")), request.getParameter("formName"), request.getParameter("description"), request.getParameter("submissionEmail"), request.getParameter("submissionMethod"), request.getParameter("action"), Boolean.parseBoolean(request.getParameter("resettable")), Boolean.parseBoolean(request.getParameter("fileUpload")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form", Integer.parseInt(request.getParameter("formId")));
                        response.sendRedirect("admin/FormUI.jsp?id=" + request.getParameter("formId") + "&SuccessMsg=Updated Form Successfully!");
                        break;
                    case 3:  //delete
                        FormDAO.deleteFormById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Form", request.getParameter("id"));
                        Database.updateCount("Form", -1);
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Deleted Form Successfully!");
                        break;
                    case 4:  //remove all records
                        FormDAO.deleteAllForm();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = FormFieldDAO.addFormField(request.getParameter("fieldName"), request.getParameter("label"), request.getParameter("errorText"), request.getParameter("validationRegex"), Integer.parseInt(request.getParameter("rank")), request.getParameter("defaultValue"), request.getParameter("options"), request.getParameter("groupName"), Integer.parseInt(request.getParameter("optional")), Integer.parseInt(request.getParameter("formFieldTypeId")), Integer.parseInt(request.getParameter("formId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField", id);
                        }

                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Added FormField Successfully!");
                        break;
                    case 2: //update            
                        FormFieldDAO.updateFormField(Integer.parseInt(request.getParameter("formFieldId")), request.getParameter("fieldName"), request.getParameter("label"), request.getParameter("errorText"), request.getParameter("validationRegex"), Integer.parseInt(request.getParameter("rank")), request.getParameter("defaultValue"), request.getParameter("options"), request.getParameter("groupName"), Integer.parseInt(request.getParameter("optional")), Integer.parseInt(request.getParameter("formFieldTypeId")), Integer.parseInt(request.getParameter("formId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField", Integer.parseInt(request.getParameter("formFieldId")));
                        response.sendRedirect("admin/FormFieldUI.jsp?id=" + request.getParameter("formFieldId") + "&SuccessMsg=Updated FormField Successfully!");
                        break;
                    case 3:  //delete
                        FormFieldDAO.deleteFormFieldById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormField", request.getParameter("id"));

                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Deleted FormField Successfully!");
                        break;
                    case 4:  //remove all records
                        FormFieldDAO.deleteAllFormField();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = FormFieldTypeDAO.addFormFieldType(request.getParameter("typeName"), request.getParameter("inputType"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType", id);
                        }

                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Added FormFieldType Successfully!");
                        break;
                    case 2: //update            
                        FormFieldTypeDAO.updateFormFieldType(Integer.parseInt(request.getParameter("formFieldTypeId")), request.getParameter("typeName"), request.getParameter("inputType"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType", Integer.parseInt(request.getParameter("formFieldTypeId")));
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?id=" + request.getParameter("formFieldTypeId") + "&SuccessMsg=Updated FormFieldType Successfully!");
                        break;
                    case 3:  //delete
                        FormFieldTypeDAO.deleteFormFieldTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "FormFieldType", request.getParameter("id"));

                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Deleted FormFieldType Successfully!");
                        break;
                    case 4:  //remove all records
                        FormFieldTypeDAO.deleteAllFormFieldType();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("image_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ImageTypeDAO.addImageType(request.getParameter("typeName"), request.getParameter("description"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType", id);
                        }

                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Added ImageType Successfully!");
                        break;
                    case 2: //update            
                        ImageTypeDAO.updateImageType(Integer.parseInt(request.getParameter("imageTypeId")), request.getParameter("typeName"), request.getParameter("description"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType", Integer.parseInt(request.getParameter("imageTypeId")));
                        response.sendRedirect("admin/ImageTypeUI.jsp?id=" + request.getParameter("imageTypeId") + "&SuccessMsg=Updated ImageType Successfully!");
                        break;
                    case 3:  //delete
                        ImageTypeDAO.deleteImageTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ImageType", request.getParameter("id"));

                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Deleted ImageType Successfully!");
                        break;
                    case 4:  //remove all records
                        ImageTypeDAO.deleteAllImageType();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("item"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemDAO.addItem(request.getParameter("itemName"), request.getParameter("description"), Double.parseDouble(request.getParameter("listPrice")), Double.parseDouble(request.getParameter("price")), request.getParameter("shortDescription"), Integer.parseInt(request.getParameter("adjustment")), request.getParameter("sku"), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("status")), request.getParameter("locale"), Integer.parseInt(request.getParameter("itemTypeId")), Integer.parseInt(request.getParameter("itemBrandId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item", id);
                        }
                        Database.updateCount("Item", 1);
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Added Item Successfully!");
                        break;
                    case 2: //update            
                        ItemDAO.updateItem(Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemName"), request.getParameter("description"), Double.parseDouble(request.getParameter("listPrice")), Double.parseDouble(request.getParameter("price")), request.getParameter("shortDescription"), Integer.parseInt(request.getParameter("adjustment")), request.getParameter("sku"), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("status")), request.getParameter("locale"), Integer.parseInt(request.getParameter("itemTypeId")), Integer.parseInt(request.getParameter("itemBrandId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item", Integer.parseInt(request.getParameter("itemId")));
                        response.sendRedirect("admin/ItemUI.jsp?id=" + request.getParameter("itemId") + "&SuccessMsg=Updated Item Successfully!");
                        break;
                    case 3:  //delete
                        ItemDAO.deleteItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Item", request.getParameter("id"));
                        Database.updateCount("Item", -1);
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Deleted Item Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemDAO.deleteAllItem();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ItemAttributeDAO.addItemAttribute(request.getParameter("key"), request.getParameter("value"), request.getParameter("locale"), Integer.parseInt(request.getParameter("attributeTypeId")), Integer.parseInt(request.getParameter("itemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute", id);
                        }

                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Added ItemAttribute Successfully!");
                        break;
                    case 2: //update            
                        ItemAttributeDAO.updateItemAttribute(Integer.parseInt(request.getParameter("itemAttributeId")), request.getParameter("key"), request.getParameter("value"), request.getParameter("locale"), Integer.parseInt(request.getParameter("attributeTypeId")), Integer.parseInt(request.getParameter("itemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute", Integer.parseInt(request.getParameter("itemAttributeId")));
                        response.sendRedirect("admin/ItemAttributeUI.jsp?id=" + request.getParameter("itemAttributeId") + "&SuccessMsg=Updated ItemAttribute Successfully!");
                        break;
                    case 3:  //delete
                        ItemAttributeDAO.deleteItemAttributeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttribute", request.getParameter("id"));

                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Deleted ItemAttribute Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemAttributeDAO.deleteAllItemAttribute();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("item_attribute_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemAttributeTypeDAO.addItemAttributeType(request.getParameter("attributeName"), request.getParameter("description"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttributeType", id);
                        }

                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?SuccessMsg=Added ItemAttributeType Successfully!");
                        break;
                    case 2: //update            
                        ItemAttributeTypeDAO.updateItemAttributeType(Integer.parseInt(request.getParameter("itemAttributeTypeId")), request.getParameter("attributeName"), request.getParameter("description"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttributeType", Integer.parseInt(request.getParameter("itemAttributeTypeId")));
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?id=" + request.getParameter("itemAttributeTypeId") + "&SuccessMsg=Updated ItemAttributeType Successfully!");
                        break;
                    case 3:  //delete
                        ItemAttributeTypeDAO.deleteItemAttributeTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttributeType", request.getParameter("id"));

                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?SuccessMsg=Deleted ItemAttributeType Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemAttributeTypeDAO.deleteAllItemAttributeType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAttributeType");
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error editing ItemAttributeType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error adding ItemAttributeType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error editing ItemAttributeType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error deleting ItemAttributeType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error clearing ItemAttributeType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Unknown Error ItemAttributeType, possibly an invalid action.");
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
                        int id = ItemAvailabilityDAO.addItemAvailability(request.getParameter("type"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability", id);
                        }

                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Added ItemAvailability Successfully!");
                        break;
                    case 2: //update            
                        ItemAvailabilityDAO.updateItemAvailability(Integer.parseInt(request.getParameter("itemAvailabilityId")), request.getParameter("type"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability", Integer.parseInt(request.getParameter("itemAvailabilityId")));
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?id=" + request.getParameter("itemAvailabilityId") + "&SuccessMsg=Updated ItemAvailability Successfully!");
                        break;
                    case 3:  //delete
                        ItemAvailabilityDAO.deleteItemAvailabilityById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemAvailability", request.getParameter("id"));

                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Deleted ItemAvailability Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemAvailabilityDAO.deleteAllItemAvailability();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ItemBrandDAO.addItemBrand(request.getParameter("name"), request.getParameter("description"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand", id);
                        }
                        Database.updateCount("ItemBrand", 1);
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Added ItemBrand Successfully!");
                        break;
                    case 2: //update            
                        ItemBrandDAO.updateItemBrand(Integer.parseInt(request.getParameter("itemBrandId")), request.getParameter("name"), request.getParameter("description"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand", Integer.parseInt(request.getParameter("itemBrandId")));
                        response.sendRedirect("admin/ItemBrandUI.jsp?id=" + request.getParameter("itemBrandId") + "&SuccessMsg=Updated ItemBrand Successfully!");
                        break;
                    case 3:  //delete
                        ItemBrandDAO.deleteItemBrandById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemBrand", request.getParameter("id"));
                        Database.updateCount("ItemBrand", -1);
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Deleted ItemBrand Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemBrandDAO.deleteAllItemBrand();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ItemCategoryDAO.addItemCategory(Integer.parseInt(request.getParameter("categoryId")), Integer.parseInt(request.getParameter("itemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory", id);
                        }

                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Added ItemCategory Successfully!");
                        break;
                    case 2: //update            
                        ItemCategoryDAO.updateItemCategory(Integer.parseInt(request.getParameter("itemCategoryId")), Integer.parseInt(request.getParameter("categoryId")), Integer.parseInt(request.getParameter("itemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory", Integer.parseInt(request.getParameter("itemCategoryId")));
                        response.sendRedirect("admin/ItemCategoryUI.jsp?id=" + request.getParameter("itemCategoryId") + "&SuccessMsg=Updated ItemCategory Successfully!");
                        break;
                    case 3:  //delete
                        ItemCategoryDAO.deleteItemCategoryById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemCategory", request.getParameter("id"));

                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Deleted ItemCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemCategoryDAO.deleteAllItemCategory();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("item_discount"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemDiscountDAO.addItemDiscount(Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("discountId")), Boolean.parseBoolean(request.getParameter("applyToOptions")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemDiscount", id);
                        }

                        response.sendRedirect("admin/ItemDiscountUI.jsp?SuccessMsg=Added ItemDiscount Successfully!");
                        break;
                    case 2: //update            
                        ItemDiscountDAO.updateItemDiscount(Integer.parseInt(request.getParameter("itemDiscountId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("discountId")), Boolean.parseBoolean(request.getParameter("applyToOptions")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemDiscount", Integer.parseInt(request.getParameter("itemDiscountId")));
                        response.sendRedirect("admin/ItemDiscountUI.jsp?id=" + request.getParameter("itemDiscountId") + "&SuccessMsg=Updated ItemDiscount Successfully!");
                        break;
                    case 3:  //delete
                        ItemDiscountDAO.deleteItemDiscountById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemDiscount", request.getParameter("id"));

                        response.sendRedirect("admin/ItemDiscountUI.jsp?SuccessMsg=Deleted ItemDiscount Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemDiscountDAO.deleteAllItemDiscount();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemDiscount");
                        response.sendRedirect("admin/ItemDiscountUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error editing ItemDiscount, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error adding ItemDiscount.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error editing ItemDiscount.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error deleting ItemDiscount.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error clearing ItemDiscount.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Unknown Error ItemDiscount, possibly an invalid action.");
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
                        int id = ItemFileDAO.addItemFile(request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label"), Boolean.parseBoolean(request.getParameter("hidden")), Integer.parseInt(request.getParameter("itemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile", id);
                        }

                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Added ItemFile Successfully!");
                        break;
                    case 2: //update            
                        ItemFileDAO.updateItemFile(Integer.parseInt(request.getParameter("itemFileId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label"), Boolean.parseBoolean(request.getParameter("hidden")), Integer.parseInt(request.getParameter("itemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile", Integer.parseInt(request.getParameter("itemFileId")));
                        response.sendRedirect("admin/ItemFileUI.jsp?id=" + request.getParameter("itemFileId") + "&SuccessMsg=Updated ItemFile Successfully!");
                        break;
                    case 3:  //delete
                        ItemFileDAO.deleteItemFileById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemFile", request.getParameter("id"));

                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Deleted ItemFile Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemFileDAO.deleteAllItemFile();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ItemImageDAO.addItemImage(request.getParameter("imageName"), request.getParameter("thumbnailName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("itemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage", id);
                        }

                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Added ItemImage Successfully!");
                        break;
                    case 2: //update            
                        ItemImageDAO.updateItemImage(Integer.parseInt(request.getParameter("itemImageId")), request.getParameter("imageName"), request.getParameter("thumbnailName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("itemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage", Integer.parseInt(request.getParameter("itemImageId")));
                        response.sendRedirect("admin/ItemImageUI.jsp?id=" + request.getParameter("itemImageId") + "&SuccessMsg=Updated ItemImage Successfully!");
                        break;
                    case 3:  //delete
                        ItemImageDAO.deleteItemImageById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemImage", request.getParameter("id"));

                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Deleted ItemImage Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemImageDAO.deleteAllItemImage();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ItemLocationDAO.addItemLocation(request.getParameter("latitude"), request.getParameter("longitude"), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("addressId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation", id);
                        }

                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Added ItemLocation Successfully!");
                        break;
                    case 2: //update            
                        ItemLocationDAO.updateItemLocation(Integer.parseInt(request.getParameter("itemLocationId")), request.getParameter("latitude"), request.getParameter("longitude"), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("addressId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation", Integer.parseInt(request.getParameter("itemLocationId")));
                        response.sendRedirect("admin/ItemLocationUI.jsp?id=" + request.getParameter("itemLocationId") + "&SuccessMsg=Updated ItemLocation Successfully!");
                        break;
                    case 3:  //delete
                        ItemLocationDAO.deleteItemLocationById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemLocation", request.getParameter("id"));

                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Deleted ItemLocation Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemLocationDAO.deleteAllItemLocation();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ItemOptionDAO.addItemOption(request.getParameter("optionName"), request.getParameter("description"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption", id);
                        }
                        Database.updateCount("ItemOption", 1);
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Added ItemOption Successfully!");
                        break;
                    case 2: //update            
                        ItemOptionDAO.updateItemOption(Integer.parseInt(request.getParameter("itemOptionId")), request.getParameter("optionName"), request.getParameter("description"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption", Integer.parseInt(request.getParameter("itemOptionId")));
                        response.sendRedirect("admin/ItemOptionUI.jsp?id=" + request.getParameter("itemOptionId") + "&SuccessMsg=Updated ItemOption Successfully!");
                        break;
                    case 3:  //delete
                        ItemOptionDAO.deleteItemOptionById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemOption", request.getParameter("id"));
                        Database.updateCount("ItemOption", -1);
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Deleted ItemOption Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemOptionDAO.deleteAllItemOption();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("item_review"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemReviewDAO.addItemReview(Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("rating")), Integer.parseInt(request.getParameter("helpfulYes")), Integer.parseInt(request.getParameter("helpfulNo")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview", id);
                        }

                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Added ItemReview Successfully!");
                        break;
                    case 2: //update            
                        ItemReviewDAO.updateItemReview(Integer.parseInt(request.getParameter("itemReviewId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("rating")), Integer.parseInt(request.getParameter("helpfulYes")), Integer.parseInt(request.getParameter("helpfulNo")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview", Integer.parseInt(request.getParameter("itemReviewId")));
                        response.sendRedirect("admin/ItemReviewUI.jsp?id=" + request.getParameter("itemReviewId") + "&SuccessMsg=Updated ItemReview Successfully!");
                        break;
                    case 3:  //delete
                        ItemReviewDAO.deleteItemReviewById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemReview", request.getParameter("id"));

                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Deleted ItemReview Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemReviewDAO.deleteAllItemReview();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("item_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ItemTypeDAO.addItemType(request.getParameter("typeName"), Integer.parseInt(request.getParameter("rank")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemType", id);
                        }

                        response.sendRedirect("admin/ItemTypeUI.jsp?SuccessMsg=Added ItemType Successfully!");
                        break;
                    case 2: //update            
                        ItemTypeDAO.updateItemType(Integer.parseInt(request.getParameter("itemTypeId")), request.getParameter("typeName"), Integer.parseInt(request.getParameter("rank")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemType", Integer.parseInt(request.getParameter("itemTypeId")));
                        response.sendRedirect("admin/ItemTypeUI.jsp?id=" + request.getParameter("itemTypeId") + "&SuccessMsg=Updated ItemType Successfully!");
                        break;
                    case 3:  //delete
                        ItemTypeDAO.deleteItemTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemType", request.getParameter("id"));

                        response.sendRedirect("admin/ItemTypeUI.jsp?SuccessMsg=Deleted ItemType Successfully!");
                        break;
                    case 4:  //remove all records
                        ItemTypeDAO.deleteAllItemType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ItemType");
                        response.sendRedirect("admin/ItemTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error editing ItemType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error adding ItemType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error editing ItemType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error deleting ItemType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error clearing ItemType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Unknown Error ItemType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("knowledge_base"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = KnowledgeBaseDAO.addKnowledgeBase(request.getParameter("knowledgeBaseName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), operatingDateFormat.parse(request.getParameter("lastModified")), Integer.parseInt(request.getParameter("latestTopic")), Integer.parseInt(request.getParameter("latestPost")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "KnowledgeBase", id);
                        }

                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?SuccessMsg=Added KnowledgeBase Successfully!");
                        break;
                    case 2: //update            
                        KnowledgeBaseDAO.updateKnowledgeBase(Integer.parseInt(request.getParameter("knowledgeBaseId")), request.getParameter("knowledgeBaseName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), operatingDateFormat.parse(request.getParameter("lastModified")), Integer.parseInt(request.getParameter("latestTopic")), Integer.parseInt(request.getParameter("latestPost")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "KnowledgeBase", Integer.parseInt(request.getParameter("knowledgeBaseId")));
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?id=" + request.getParameter("knowledgeBaseId") + "&SuccessMsg=Updated KnowledgeBase Successfully!");
                        break;
                    case 3:  //delete
                        KnowledgeBaseDAO.deleteKnowledgeBaseById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "KnowledgeBase", request.getParameter("id"));

                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?SuccessMsg=Deleted KnowledgeBase Successfully!");
                        break;
                    case 4:  //remove all records
                        KnowledgeBaseDAO.deleteAllKnowledgeBase();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "KnowledgeBase");
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error editing KnowledgeBase, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error adding KnowledgeBase.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error editing KnowledgeBase.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error deleting KnowledgeBase.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error clearing KnowledgeBase.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Unknown Error KnowledgeBase, possibly an invalid action.");
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
                        int id = LocaleDAO.addLocale(request.getParameter("localeString"), request.getParameter("localeCharacterSet"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale", id);
                        }

                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Added Locale Successfully!");
                        break;
                    case 2: //update            
                        LocaleDAO.updateLocale(Integer.parseInt(request.getParameter("localeId")), request.getParameter("localeString"), request.getParameter("localeCharacterSet"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale", Integer.parseInt(request.getParameter("localeId")));
                        response.sendRedirect("admin/LocaleUI.jsp?id=" + request.getParameter("localeId") + "&SuccessMsg=Updated Locale Successfully!");
                        break;
                    case 3:  //delete
                        LocaleDAO.deleteLocaleById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Locale", request.getParameter("id"));

                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Deleted Locale Successfully!");
                        break;
                    case 4:  //remove all records
                        LocaleDAO.deleteAllLocale();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = LocalizedStringDAO.addLocalizedString(Integer.parseInt(request.getParameter("locale")), request.getParameter("value"), Integer.parseInt(request.getParameter("textStringId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString", id);
                        }

                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Added LocalizedString Successfully!");
                        break;
                    case 2: //update            
                        LocalizedStringDAO.updateLocalizedString(Integer.parseInt(request.getParameter("localizedStringId")), Integer.parseInt(request.getParameter("locale")), request.getParameter("value"), Integer.parseInt(request.getParameter("textStringId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString", Integer.parseInt(request.getParameter("localizedStringId")));
                        response.sendRedirect("admin/LocalizedStringUI.jsp?id=" + request.getParameter("localizedStringId") + "&SuccessMsg=Updated LocalizedString Successfully!");
                        break;
                    case 3:  //delete
                        LocalizedStringDAO.deleteLocalizedStringById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "LocalizedString", request.getParameter("id"));

                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Deleted LocalizedString Successfully!");
                        break;
                    case 4:  //remove all records
                        LocalizedStringDAO.deleteAllLocalizedString();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = MailinglistDAO.addMailinglist(request.getParameter("listName"), request.getParameter("email"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("userId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist", id);
                        }

                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Added Mailinglist Successfully!");
                        break;
                    case 2: //update            
                        MailinglistDAO.updateMailinglist(Integer.parseInt(request.getParameter("mailinglistId")), request.getParameter("listName"), request.getParameter("email"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("userId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist", Integer.parseInt(request.getParameter("mailinglistId")));
                        response.sendRedirect("admin/MailinglistUI.jsp?id=" + request.getParameter("mailinglistId") + "&SuccessMsg=Updated Mailinglist Successfully!");
                        break;
                    case 3:  //delete
                        MailinglistDAO.deleteMailinglistById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Mailinglist", request.getParameter("id"));

                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Deleted Mailinglist Successfully!");
                        break;
                    case 4:  //remove all records
                        MailinglistDAO.deleteAllMailinglist();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("meta_tag"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = MetaTagDAO.addMetaTag(request.getParameter("title"), request.getParameter("description"), request.getParameter("keywords"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "MetaTag", id);
                        }

                        response.sendRedirect("admin/MetaTagUI.jsp?SuccessMsg=Added MetaTag Successfully!");
                        break;
                    case 2: //update            
                        MetaTagDAO.updateMetaTag(Integer.parseInt(request.getParameter("metaTagId")), request.getParameter("title"), request.getParameter("description"), request.getParameter("keywords"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "MetaTag", Integer.parseInt(request.getParameter("metaTagId")));
                        response.sendRedirect("admin/MetaTagUI.jsp?id=" + request.getParameter("metaTagId") + "&SuccessMsg=Updated MetaTag Successfully!");
                        break;
                    case 3:  //delete
                        MetaTagDAO.deleteMetaTagById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "MetaTag", request.getParameter("id"));

                        response.sendRedirect("admin/MetaTagUI.jsp?SuccessMsg=Deleted MetaTag Successfully!");
                        break;
                    case 4:  //remove all records
                        MetaTagDAO.deleteAllMetaTag();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "MetaTag");
                        response.sendRedirect("admin/MetaTagUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error editing MetaTag, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error adding MetaTag.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error editing MetaTag.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error deleting MetaTag.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error clearing MetaTag.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Unknown Error MetaTag, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("option_availability"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = OptionAvailabilityDAO.addOptionAvailability(Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemOptionId")), Integer.parseInt(request.getParameter("itemAvailabilityId")), Integer.parseInt(request.getParameter("availableQuantity")), Double.parseDouble(request.getParameter("price")), operatingDateFormat.parse(request.getParameter("availableFrom")), operatingDateFormat.parse(request.getParameter("availableTo")), Integer.parseInt(request.getParameter("maximumQuantity")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "OptionAvailability", id);
                        }

                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?SuccessMsg=Added OptionAvailability Successfully!");
                        break;
                    case 2: //update            
                        OptionAvailabilityDAO.updateOptionAvailability(Integer.parseInt(request.getParameter("optionAvailabilityId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemOptionId")), Integer.parseInt(request.getParameter("itemAvailabilityId")), Integer.parseInt(request.getParameter("availableQuantity")), Double.parseDouble(request.getParameter("price")), operatingDateFormat.parse(request.getParameter("availableFrom")), operatingDateFormat.parse(request.getParameter("availableTo")), Integer.parseInt(request.getParameter("maximumQuantity")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "OptionAvailability", Integer.parseInt(request.getParameter("optionAvailabilityId")));
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?id=" + request.getParameter("optionAvailabilityId") + "&SuccessMsg=Updated OptionAvailability Successfully!");
                        break;
                    case 3:  //delete
                        OptionAvailabilityDAO.deleteOptionAvailabilityById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "OptionAvailability", request.getParameter("id"));

                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?SuccessMsg=Deleted OptionAvailability Successfully!");
                        break;
                    case 4:  //remove all records
                        OptionAvailabilityDAO.deleteAllOptionAvailability();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "OptionAvailability");
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error editing OptionAvailability, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error adding OptionAvailability.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error editing OptionAvailability.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error deleting OptionAvailability.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error clearing OptionAvailability.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Unknown Error OptionAvailability, possibly an invalid action.");
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
                        int id = OrderDAO.addOrder(operatingDateFormat.parse(request.getParameter("orderDate")), operatingDateFormat.parse(request.getParameter("shipDate")), request.getParameter("paymentMethod"), request.getParameter("purchaseOrder"), request.getParameter("transactionId"), Double.parseDouble(request.getParameter("amountBilled")), request.getParameter("paymentStatus"), request.getParameter("pendingReason"), request.getParameter("paymentType"), Double.parseDouble(request.getParameter("transactionFee")), request.getParameter("currencyCode"), request.getParameter("payerId"), Double.parseDouble(request.getParameter("subtotalAmount")), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("taxAmount")), Double.parseDouble(request.getParameter("shippingAmount")), Double.parseDouble(request.getParameter("totalAmount")), Double.parseDouble(request.getParameter("refundAmount")), request.getParameter("notes"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("shippingId")), Integer.parseInt(request.getParameter("affiliateId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order", id);
                        }
                        Database.updateCount("Order", 1);
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Added Order Successfully!");
                        break;
                    case 2: //update            
                        OrderDAO.updateOrder(Integer.parseInt(request.getParameter("orderId")), operatingDateFormat.parse(request.getParameter("orderDate")), operatingDateFormat.parse(request.getParameter("shipDate")), request.getParameter("paymentMethod"), request.getParameter("purchaseOrder"), request.getParameter("transactionId"), Double.parseDouble(request.getParameter("amountBilled")), request.getParameter("paymentStatus"), request.getParameter("pendingReason"), request.getParameter("paymentType"), Double.parseDouble(request.getParameter("transactionFee")), request.getParameter("currencyCode"), request.getParameter("payerId"), Double.parseDouble(request.getParameter("subtotalAmount")), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("taxAmount")), Double.parseDouble(request.getParameter("shippingAmount")), Double.parseDouble(request.getParameter("totalAmount")), Double.parseDouble(request.getParameter("refundAmount")), request.getParameter("notes"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("shippingId")), Integer.parseInt(request.getParameter("affiliateId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order", Integer.parseInt(request.getParameter("orderId")));
                        response.sendRedirect("admin/OrderUI.jsp?id=" + request.getParameter("orderId") + "&SuccessMsg=Updated Order Successfully!");
                        break;
                    case 3:  //delete
                        OrderDAO.deleteOrderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Order", request.getParameter("id"));
                        Database.updateCount("Order", -1);
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Deleted Order Successfully!");
                        break;
                    case 4:  //remove all records
                        OrderDAO.deleteAllOrder();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("order_item"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = OrderItemDAO.addOrderItem(Integer.parseInt(request.getParameter("customerOrderId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("quantity")), request.getParameter("optionName"), Double.parseDouble(request.getParameter("unitPrice")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "OrderItem", id);
                        }

                        response.sendRedirect("admin/OrderItemUI.jsp?SuccessMsg=Added OrderItem Successfully!");
                        break;
                    case 2: //update            
                        OrderItemDAO.updateOrderItem(Integer.parseInt(request.getParameter("orderItemId")), Integer.parseInt(request.getParameter("customerOrderId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("quantity")), request.getParameter("optionName"), Double.parseDouble(request.getParameter("unitPrice")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "OrderItem", Integer.parseInt(request.getParameter("orderItemId")));
                        response.sendRedirect("admin/OrderItemUI.jsp?id=" + request.getParameter("orderItemId") + "&SuccessMsg=Updated OrderItem Successfully!");
                        break;
                    case 3:  //delete
                        OrderItemDAO.deleteOrderItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "OrderItem", request.getParameter("id"));

                        response.sendRedirect("admin/OrderItemUI.jsp?SuccessMsg=Deleted OrderItem Successfully!");
                        break;
                    case 4:  //remove all records
                        OrderItemDAO.deleteAllOrderItem();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "OrderItem");
                        response.sendRedirect("admin/OrderItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error editing OrderItem, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error adding OrderItem.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error editing OrderItem.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error deleting OrderItem.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error clearing OrderItem.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Unknown Error OrderItem, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("page"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = PageDAO.addPage(request.getParameter("pageName"), request.getParameter("content"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("formId")), Integer.parseInt(request.getParameter("sliderId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Page", id);
                        }
                        Database.updateCount("Page", 1);
                        response.sendRedirect("admin/PageUI.jsp?SuccessMsg=Added Page Successfully!");
                        break;
                    case 2: //update            
                        PageDAO.updatePage(Integer.parseInt(request.getParameter("pageId")), request.getParameter("pageName"), request.getParameter("content"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("formId")), Integer.parseInt(request.getParameter("sliderId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Page", Integer.parseInt(request.getParameter("pageId")));
                        response.sendRedirect("admin/PageUI.jsp?id=" + request.getParameter("pageId") + "&SuccessMsg=Updated Page Successfully!");
                        break;
                    case 3:  //delete
                        PageDAO.deletePageById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Page", request.getParameter("id"));
                        Database.updateCount("Page", -1);
                        response.sendRedirect("admin/PageUI.jsp?SuccessMsg=Deleted Page Successfully!");
                        break;
                    case 4:  //remove all records
                        PageDAO.deleteAllPage();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Page");
                        response.sendRedirect("admin/PageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error editing Page, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error adding Page.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error editing Page.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error deleting Page.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error clearing Page.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Unknown Error Page, possibly an invalid action.");
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
                        int id = PageTemplateDAO.addPageTemplate(request.getParameter("name"), request.getParameter("markup"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate", id);
                        }

                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Added PageTemplate Successfully!");
                        break;
                    case 2: //update            
                        PageTemplateDAO.updatePageTemplate(Integer.parseInt(request.getParameter("pageTemplateId")), request.getParameter("name"), request.getParameter("markup"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate", Integer.parseInt(request.getParameter("pageTemplateId")));
                        response.sendRedirect("admin/PageTemplateUI.jsp?id=" + request.getParameter("pageTemplateId") + "&SuccessMsg=Updated PageTemplate Successfully!");
                        break;
                    case 3:  //delete
                        PageTemplateDAO.deletePageTemplateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "PageTemplate", request.getParameter("id"));

                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Deleted PageTemplate Successfully!");
                        break;
                    case 4:  //remove all records
                        PageTemplateDAO.deleteAllPageTemplate();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = PaypalDAO.addPaypal(request.getParameter("payPalUrl"), request.getParameter("currencyCode"), request.getParameter("apiUsername"), request.getParameter("apiPassword"), request.getParameter("apiSignature"), request.getParameter("apiEndpoint"), Boolean.parseBoolean(request.getParameter("activeProfile")), request.getParameter("returnUrl"), request.getParameter("cancelUrl"), request.getParameter("paymentType"), request.getParameter("environment"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal", id);
                        }

                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Added Paypal Successfully!");
                        break;
                    case 2: //update            
                        PaypalDAO.updatePaypal(Integer.parseInt(request.getParameter("paypalId")), request.getParameter("payPalUrl"), request.getParameter("currencyCode"), request.getParameter("apiUsername"), request.getParameter("apiPassword"), request.getParameter("apiSignature"), request.getParameter("apiEndpoint"), Boolean.parseBoolean(request.getParameter("activeProfile")), request.getParameter("returnUrl"), request.getParameter("cancelUrl"), request.getParameter("paymentType"), request.getParameter("environment"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal", Integer.parseInt(request.getParameter("paypalId")));
                        response.sendRedirect("admin/PaypalUI.jsp?id=" + request.getParameter("paypalId") + "&SuccessMsg=Updated Paypal Successfully!");
                        break;
                    case 3:  //delete
                        PaypalDAO.deletePaypalById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Paypal", request.getParameter("id"));

                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Deleted Paypal Successfully!");
                        break;
                    case 4:  //remove all records
                        PaypalDAO.deleteAllPaypal();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("post_category"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = PostCategoryDAO.addPostCategory(request.getParameter("name"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "PostCategory", id);
                        }

                        response.sendRedirect("admin/PostCategoryUI.jsp?SuccessMsg=Added PostCategory Successfully!");
                        break;
                    case 2: //update            
                        PostCategoryDAO.updatePostCategory(Integer.parseInt(request.getParameter("postCategoryId")), request.getParameter("name"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "PostCategory", Integer.parseInt(request.getParameter("postCategoryId")));
                        response.sendRedirect("admin/PostCategoryUI.jsp?id=" + request.getParameter("postCategoryId") + "&SuccessMsg=Updated PostCategory Successfully!");
                        break;
                    case 3:  //delete
                        PostCategoryDAO.deletePostCategoryById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "PostCategory", request.getParameter("id"));

                        response.sendRedirect("admin/PostCategoryUI.jsp?SuccessMsg=Deleted PostCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        PostCategoryDAO.deleteAllPostCategory();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "PostCategory");
                        response.sendRedirect("admin/PostCategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error editing PostCategory, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error adding PostCategory.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error editing PostCategory.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error deleting PostCategory.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error clearing PostCategory.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Unknown Error PostCategory, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("recurring_payment"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = RecurringPaymentDAO.addRecurringPayment(Integer.parseInt(request.getParameter("cycleLength")), Integer.parseInt(request.getParameter("cyclePeriod")), Integer.parseInt(request.getParameter("totalCycles")), operatingDateFormat.parse(request.getParameter("startDate")), Integer.parseInt(request.getParameter("orderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "RecurringPayment", id);
                        }

                        response.sendRedirect("admin/RecurringPaymentUI.jsp?SuccessMsg=Added RecurringPayment Successfully!");
                        break;
                    case 2: //update            
                        RecurringPaymentDAO.updateRecurringPayment(Integer.parseInt(request.getParameter("recurringPaymentId")), Integer.parseInt(request.getParameter("cycleLength")), Integer.parseInt(request.getParameter("cyclePeriod")), Integer.parseInt(request.getParameter("totalCycles")), operatingDateFormat.parse(request.getParameter("startDate")), Integer.parseInt(request.getParameter("orderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "RecurringPayment", Integer.parseInt(request.getParameter("recurringPaymentId")));
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?id=" + request.getParameter("recurringPaymentId") + "&SuccessMsg=Updated RecurringPayment Successfully!");
                        break;
                    case 3:  //delete
                        RecurringPaymentDAO.deleteRecurringPaymentById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "RecurringPayment", request.getParameter("id"));

                        response.sendRedirect("admin/RecurringPaymentUI.jsp?SuccessMsg=Deleted RecurringPayment Successfully!");
                        break;
                    case 4:  //remove all records
                        RecurringPaymentDAO.deleteAllRecurringPayment();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "RecurringPayment");
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error editing RecurringPayment, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error adding RecurringPayment.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error editing RecurringPayment.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error deleting RecurringPayment.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error clearing RecurringPayment.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Unknown Error RecurringPayment, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("related_items"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = RelatedItemsDAO.addRelatedItems(Integer.parseInt(request.getParameter("item1")), Integer.parseInt(request.getParameter("item2")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "RelatedItems", id);
                        }

                        response.sendRedirect("admin/RelatedItemsUI.jsp?SuccessMsg=Added RelatedItems Successfully!");
                        break;
                    case 2: //update            
                        RelatedItemsDAO.updateRelatedItems(Integer.parseInt(request.getParameter("relatedItemId")), Integer.parseInt(request.getParameter("item1")), Integer.parseInt(request.getParameter("item2")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "RelatedItems", Integer.parseInt(request.getParameter("relatedItemId")));
                        response.sendRedirect("admin/RelatedItemsUI.jsp?id=" + request.getParameter("relatedItemId") + "&SuccessMsg=Updated RelatedItems Successfully!");
                        break;
                    case 3:  //delete
                        RelatedItemsDAO.deleteRelatedItemsById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "RelatedItems", request.getParameter("id"));

                        response.sendRedirect("admin/RelatedItemsUI.jsp?SuccessMsg=Deleted RelatedItems Successfully!");
                        break;
                    case 4:  //remove all records
                        RelatedItemsDAO.deleteAllRelatedItems();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "RelatedItems");
                        response.sendRedirect("admin/RelatedItemsUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/RelatedItemsUI.jsp?ErrorMsg=Error editing RelatedItems, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/RelatedItemsUI.jsp?ErrorMsg=Error adding RelatedItems.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/RelatedItemsUI.jsp?ErrorMsg=Error editing RelatedItems.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/RelatedItemsUI.jsp?ErrorMsg=Error deleting RelatedItems.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/RelatedItemsUI.jsp?ErrorMsg=Error clearing RelatedItems.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/RelatedItemsUI.jsp?ErrorMsg=Unknown Error RelatedItems, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("resource_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ResourceTypeDAO.addResourceType(request.getParameter("typeName"), request.getParameter("value"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceType", id);
                        }

                        response.sendRedirect("admin/ResourceTypeUI.jsp?SuccessMsg=Added ResourceType Successfully!");
                        break;
                    case 2: //update            
                        ResourceTypeDAO.updateResourceType(Integer.parseInt(request.getParameter("resourceTypeId")), request.getParameter("typeName"), request.getParameter("value"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceType", Integer.parseInt(request.getParameter("resourceTypeId")));
                        response.sendRedirect("admin/ResourceTypeUI.jsp?id=" + request.getParameter("resourceTypeId") + "&SuccessMsg=Updated ResourceType Successfully!");
                        break;
                    case 3:  //delete
                        ResourceTypeDAO.deleteResourceTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceType", request.getParameter("id"));

                        response.sendRedirect("admin/ResourceTypeUI.jsp?SuccessMsg=Deleted ResourceType Successfully!");
                        break;
                    case 4:  //remove all records
                        ResourceTypeDAO.deleteAllResourceType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceType");
                        response.sendRedirect("admin/ResourceTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error editing ResourceType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error adding ResourceType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error editing ResourceType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error deleting ResourceType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error clearing ResourceType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Unknown Error ResourceType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("resource_url"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ResourceUrlDAO.addResourceUrl(request.getParameter("url"), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("resourceTypeId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceUrl", id);
                        }

                        response.sendRedirect("admin/ResourceUrlUI.jsp?SuccessMsg=Added ResourceUrl Successfully!");
                        break;
                    case 2: //update            
                        ResourceUrlDAO.updateResourceUrl(Integer.parseInt(request.getParameter("resourceUrlId")), request.getParameter("url"), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("resourceTypeId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceUrl", Integer.parseInt(request.getParameter("resourceUrlId")));
                        response.sendRedirect("admin/ResourceUrlUI.jsp?id=" + request.getParameter("resourceUrlId") + "&SuccessMsg=Updated ResourceUrl Successfully!");
                        break;
                    case 3:  //delete
                        ResourceUrlDAO.deleteResourceUrlById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceUrl", request.getParameter("id"));

                        response.sendRedirect("admin/ResourceUrlUI.jsp?SuccessMsg=Deleted ResourceUrl Successfully!");
                        break;
                    case 4:  //remove all records
                        ResourceUrlDAO.deleteAllResourceUrl();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ResourceUrl");
                        response.sendRedirect("admin/ResourceUrlUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error editing ResourceUrl, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error adding ResourceUrl.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error editing ResourceUrl.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error deleting ResourceUrl.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error clearing ResourceUrl.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Unknown Error ResourceUrl, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("return_request"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ReturnRequestDAO.addReturnRequest(Integer.parseInt(request.getParameter("quantity")), operatingDateFormat.parse(request.getParameter("requestDate")), request.getParameter("returnReason"), request.getParameter("requestedAction"), request.getParameter("notes"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("orderItemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ReturnRequest", id);
                        }

                        response.sendRedirect("admin/ReturnRequestUI.jsp?SuccessMsg=Added ReturnRequest Successfully!");
                        break;
                    case 2: //update            
                        ReturnRequestDAO.updateReturnRequest(Integer.parseInt(request.getParameter("returnRequestId")), Integer.parseInt(request.getParameter("quantity")), operatingDateFormat.parse(request.getParameter("requestDate")), request.getParameter("returnReason"), request.getParameter("requestedAction"), request.getParameter("notes"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("orderItemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ReturnRequest", Integer.parseInt(request.getParameter("returnRequestId")));
                        response.sendRedirect("admin/ReturnRequestUI.jsp?id=" + request.getParameter("returnRequestId") + "&SuccessMsg=Updated ReturnRequest Successfully!");
                        break;
                    case 3:  //delete
                        ReturnRequestDAO.deleteReturnRequestById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ReturnRequest", request.getParameter("id"));

                        response.sendRedirect("admin/ReturnRequestUI.jsp?SuccessMsg=Deleted ReturnRequest Successfully!");
                        break;
                    case 4:  //remove all records
                        ReturnRequestDAO.deleteAllReturnRequest();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "ReturnRequest");
                        response.sendRedirect("admin/ReturnRequestUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error editing ReturnRequest, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error adding ReturnRequest.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error editing ReturnRequest.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error deleting ReturnRequest.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error clearing ReturnRequest.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Unknown Error ReturnRequest, possibly an invalid action.");
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
                        int id = ServiceDAO.addService(request.getParameter("serviceName"), request.getParameter("description"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("serviceChargeId")), Integer.parseInt(request.getParameter("serviceTypeId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service", id);
                        }

                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Added Service Successfully!");
                        break;
                    case 2: //update            
                        ServiceDAO.updateService(Integer.parseInt(request.getParameter("serviceId")), request.getParameter("serviceName"), request.getParameter("description"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("serviceChargeId")), Integer.parseInt(request.getParameter("serviceTypeId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service", Integer.parseInt(request.getParameter("serviceId")));
                        response.sendRedirect("admin/ServiceUI.jsp?id=" + request.getParameter("serviceId") + "&SuccessMsg=Updated Service Successfully!");
                        break;
                    case 3:  //delete
                        ServiceDAO.deleteServiceById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Service", request.getParameter("id"));

                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Deleted Service Successfully!");
                        break;
                    case 4:  //remove all records
                        ServiceDAO.deleteAllService();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ServiceChargeDAO.addServiceCharge(request.getParameter("chargeName"), request.getParameter("description"), request.getParameter("rate"), request.getParameter("units"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("userServiceId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge", id);
                        }

                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Added ServiceCharge Successfully!");
                        break;
                    case 2: //update            
                        ServiceChargeDAO.updateServiceCharge(Integer.parseInt(request.getParameter("serviceChargeId")), request.getParameter("chargeName"), request.getParameter("description"), request.getParameter("rate"), request.getParameter("units"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("userServiceId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge", Integer.parseInt(request.getParameter("serviceChargeId")));
                        response.sendRedirect("admin/ServiceChargeUI.jsp?id=" + request.getParameter("serviceChargeId") + "&SuccessMsg=Updated ServiceCharge Successfully!");
                        break;
                    case 3:  //delete
                        ServiceChargeDAO.deleteServiceChargeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceCharge", request.getParameter("id"));

                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Deleted ServiceCharge Successfully!");
                        break;
                    case 4:  //remove all records
                        ServiceChargeDAO.deleteAllServiceCharge();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = ServiceTypeDAO.addServiceType(request.getParameter("typeName"), request.getParameter("desciption"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType", id);
                        }

                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Added ServiceType Successfully!");
                        break;
                    case 2: //update            
                        ServiceTypeDAO.updateServiceType(Integer.parseInt(request.getParameter("serviceTypeId")), request.getParameter("typeName"), request.getParameter("desciption"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType", Integer.parseInt(request.getParameter("serviceTypeId")));
                        response.sendRedirect("admin/ServiceTypeUI.jsp?id=" + request.getParameter("serviceTypeId") + "&SuccessMsg=Updated ServiceType Successfully!");
                        break;
                    case 3:  //delete
                        ServiceTypeDAO.deleteServiceTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "ServiceType", request.getParameter("id"));

                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Deleted ServiceType Successfully!");
                        break;
                    case 4:  //remove all records
                        ServiceTypeDAO.deleteAllServiceType();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("shipment"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = ShipmentDAO.addShipment(operatingDateFormat.parse(request.getParameter("createdOn")), request.getParameter("trackingNumber"), Double.parseDouble(request.getParameter("totalWeight")), operatingDateFormat.parse(request.getParameter("shipDate")), operatingDateFormat.parse(request.getParameter("deliveryDate")), Integer.parseInt(request.getParameter("itemQuantity")), Integer.parseInt(request.getParameter("orderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipment", id);
                        }

                        response.sendRedirect("admin/ShipmentUI.jsp?SuccessMsg=Added Shipment Successfully!");
                        break;
                    case 2: //update            
                        ShipmentDAO.updateShipment(Integer.parseInt(request.getParameter("shipmentId")), operatingDateFormat.parse(request.getParameter("createdOn")), request.getParameter("trackingNumber"), Double.parseDouble(request.getParameter("totalWeight")), operatingDateFormat.parse(request.getParameter("shipDate")), operatingDateFormat.parse(request.getParameter("deliveryDate")), Integer.parseInt(request.getParameter("itemQuantity")), Integer.parseInt(request.getParameter("orderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipment", Integer.parseInt(request.getParameter("shipmentId")));
                        response.sendRedirect("admin/ShipmentUI.jsp?id=" + request.getParameter("shipmentId") + "&SuccessMsg=Updated Shipment Successfully!");
                        break;
                    case 3:  //delete
                        ShipmentDAO.deleteShipmentById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipment", request.getParameter("id"));

                        response.sendRedirect("admin/ShipmentUI.jsp?SuccessMsg=Deleted Shipment Successfully!");
                        break;
                    case 4:  //remove all records
                        ShipmentDAO.deleteAllShipment();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipment");
                        response.sendRedirect("admin/ShipmentUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error editing Shipment, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error adding Shipment.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error editing Shipment.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error deleting Shipment.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error clearing Shipment.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Unknown Error Shipment, possibly an invalid action.");
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
                        int id = ShippingDAO.addShipping(request.getParameter("methodName"), request.getParameter("stateProvince"), request.getParameter("country"), Double.parseDouble(request.getParameter("quantity")), request.getParameter("unitOfMeasure"), Double.parseDouble(request.getParameter("ratePerUnitCost")), Double.parseDouble(request.getParameter("additionalCost")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping", id);
                        }

                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Added Shipping Successfully!");
                        break;
                    case 2: //update            
                        ShippingDAO.updateShipping(Integer.parseInt(request.getParameter("shippingId")), request.getParameter("methodName"), request.getParameter("stateProvince"), request.getParameter("country"), Double.parseDouble(request.getParameter("quantity")), request.getParameter("unitOfMeasure"), Double.parseDouble(request.getParameter("ratePerUnitCost")), Double.parseDouble(request.getParameter("additionalCost")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping", Integer.parseInt(request.getParameter("shippingId")));
                        response.sendRedirect("admin/ShippingUI.jsp?id=" + request.getParameter("shippingId") + "&SuccessMsg=Updated Shipping Successfully!");
                        break;
                    case 3:  //delete
                        ShippingDAO.deleteShippingById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Shipping", request.getParameter("id"));

                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Deleted Shipping Successfully!");
                        break;
                    case 4:  //remove all records
                        ShippingDAO.deleteAllShipping();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("site"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteDAO.addSite(request.getParameter("siteName"), request.getParameter("domain"), Integer.parseInt(request.getParameter("mode")), request.getParameter("url"), request.getParameter("emailHost"), Integer.parseInt(request.getParameter("emailPort")), request.getParameter("emailUsername"), request.getParameter("emailPassword"), Integer.parseInt(request.getParameter("status")), request.getParameter("locale"), Integer.parseInt(request.getParameter("templateId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Site", id);
                        }

                        response.sendRedirect("admin/SiteUI.jsp?SuccessMsg=Added Site Successfully!");
                        break;
                    case 2: //update            
                        SiteDAO.updateSite(Integer.parseInt(request.getParameter("siteId")), request.getParameter("siteName"), request.getParameter("domain"), Integer.parseInt(request.getParameter("mode")), request.getParameter("url"), request.getParameter("emailHost"), Integer.parseInt(request.getParameter("emailPort")), request.getParameter("emailUsername"), request.getParameter("emailPassword"), Integer.parseInt(request.getParameter("status")), request.getParameter("locale"), Integer.parseInt(request.getParameter("templateId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Site", Integer.parseInt(request.getParameter("siteId")));
                        response.sendRedirect("admin/SiteUI.jsp?id=" + request.getParameter("siteId") + "&SuccessMsg=Updated Site Successfully!");
                        break;
                    case 3:  //delete
                        SiteDAO.deleteSiteById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Site", request.getParameter("id"));

                        response.sendRedirect("admin/SiteUI.jsp?SuccessMsg=Deleted Site Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteDAO.deleteAllSite();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Site");
                        response.sendRedirect("admin/SiteUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error editing Site, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error adding Site.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error editing Site.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error deleting Site.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error clearing Site.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Unknown Error Site, possibly an invalid action.");
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
                        int id = SiteAttributeDAO.addSiteAttribute(request.getParameter("key"), request.getParameter("value"), request.getParameter("type"), Integer.parseInt(request.getParameter("siteId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute", id);
                        }

                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Added SiteAttribute Successfully!");
                        break;
                    case 2: //update            
                        SiteAttributeDAO.updateSiteAttribute(Integer.parseInt(request.getParameter("siteAttributeId")), request.getParameter("key"), request.getParameter("value"), request.getParameter("type"), Integer.parseInt(request.getParameter("siteId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute", Integer.parseInt(request.getParameter("siteAttributeId")));
                        response.sendRedirect("admin/SiteAttributeUI.jsp?id=" + request.getParameter("siteAttributeId") + "&SuccessMsg=Updated SiteAttribute Successfully!");
                        break;
                    case 3:  //delete
                        SiteAttributeDAO.deleteSiteAttributeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteAttribute", request.getParameter("id"));

                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Deleted SiteAttribute Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteAttributeDAO.deleteAllSiteAttribute();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = SiteFileDAO.addSiteFile(request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile", id);
                        }
                        Database.updateCount("SiteFile", 1);
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Added SiteFile Successfully!");
                        break;
                    case 2: //update            
                        SiteFileDAO.updateSiteFile(Integer.parseInt(request.getParameter("siteFileId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile", Integer.parseInt(request.getParameter("siteFileId")));
                        response.sendRedirect("admin/SiteFileUI.jsp?id=" + request.getParameter("siteFileId") + "&SuccessMsg=Updated SiteFile Successfully!");
                        break;
                    case 3:  //delete
                        SiteFileDAO.deleteSiteFileById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFile", request.getParameter("id"));
                        Database.updateCount("SiteFile", -1);
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Deleted SiteFile Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteFileDAO.deleteAllSiteFile();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = SiteFolderDAO.addSiteFolder(request.getParameter("folderName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("siteId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder", id);
                        }

                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Added SiteFolder Successfully!");
                        break;
                    case 2: //update            
                        SiteFolderDAO.updateSiteFolder(Integer.parseInt(request.getParameter("siteFolderId")), request.getParameter("folderName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("siteId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder", Integer.parseInt(request.getParameter("siteFolderId")));
                        response.sendRedirect("admin/SiteFolderUI.jsp?id=" + request.getParameter("siteFolderId") + "&SuccessMsg=Updated SiteFolder Successfully!");
                        break;
                    case 3:  //delete
                        SiteFolderDAO.deleteSiteFolderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteFolder", request.getParameter("id"));

                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Deleted SiteFolder Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteFolderDAO.deleteAllSiteFolder();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("site_image"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteImageDAO.addSiteImage(request.getParameter("fileName"), request.getParameter("description"), request.getParameter("linkUrl"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("imageTypeId")), Integer.parseInt(request.getParameter("siteId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteImage", id);
                        }

                        response.sendRedirect("admin/SiteImageUI.jsp?SuccessMsg=Added SiteImage Successfully!");
                        break;
                    case 2: //update            
                        SiteImageDAO.updateSiteImage(Integer.parseInt(request.getParameter("imageId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("linkUrl"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("imageTypeId")), Integer.parseInt(request.getParameter("siteId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteImage", Integer.parseInt(request.getParameter("imageId")));
                        response.sendRedirect("admin/SiteImageUI.jsp?id=" + request.getParameter("imageId") + "&SuccessMsg=Updated SiteImage Successfully!");
                        break;
                    case 3:  //delete
                        SiteImageDAO.deleteSiteImageById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteImage", request.getParameter("id"));

                        response.sendRedirect("admin/SiteImageUI.jsp?SuccessMsg=Deleted SiteImage Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteImageDAO.deleteAllSiteImage();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteImage");
                        response.sendRedirect("admin/SiteImageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error editing SiteImage, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error adding SiteImage.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error editing SiteImage.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error deleting SiteImage.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error clearing SiteImage.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Unknown Error SiteImage, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_item"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteItemDAO.addSiteItem(Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("itemId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteItem", id);
                        }

                        response.sendRedirect("admin/SiteItemUI.jsp?SuccessMsg=Added SiteItem Successfully!");
                        break;
                    case 2: //update            
                        SiteItemDAO.updateSiteItem(Integer.parseInt(request.getParameter("siteItemId")), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("itemId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteItem", Integer.parseInt(request.getParameter("siteItemId")));
                        response.sendRedirect("admin/SiteItemUI.jsp?id=" + request.getParameter("siteItemId") + "&SuccessMsg=Updated SiteItem Successfully!");
                        break;
                    case 3:  //delete
                        SiteItemDAO.deleteSiteItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteItem", request.getParameter("id"));

                        response.sendRedirect("admin/SiteItemUI.jsp?SuccessMsg=Deleted SiteItem Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteItemDAO.deleteAllSiteItem();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteItem");
                        response.sendRedirect("admin/SiteItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error editing SiteItem, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error adding SiteItem.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error editing SiteItem.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error deleting SiteItem.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error clearing SiteItem.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Unknown Error SiteItem, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("site_language"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = SiteLanguageDAO.addSiteLanguage(request.getParameter("languageName"), request.getParameter("locale"), Boolean.parseBoolean(request.getParameter("rtl")), request.getParameter("flagFileName"), Integer.parseInt(request.getParameter("siteId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteLanguage", id);
                        }

                        response.sendRedirect("admin/SiteLanguageUI.jsp?SuccessMsg=Added SiteLanguage Successfully!");
                        break;
                    case 2: //update            
                        SiteLanguageDAO.updateSiteLanguage(Integer.parseInt(request.getParameter("siteLanguageId")), request.getParameter("languageName"), request.getParameter("locale"), Boolean.parseBoolean(request.getParameter("rtl")), request.getParameter("flagFileName"), Integer.parseInt(request.getParameter("siteId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteLanguage", Integer.parseInt(request.getParameter("siteLanguageId")));
                        response.sendRedirect("admin/SiteLanguageUI.jsp?id=" + request.getParameter("siteLanguageId") + "&SuccessMsg=Updated SiteLanguage Successfully!");
                        break;
                    case 3:  //delete
                        SiteLanguageDAO.deleteSiteLanguageById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteLanguage", request.getParameter("id"));

                        response.sendRedirect("admin/SiteLanguageUI.jsp?SuccessMsg=Deleted SiteLanguage Successfully!");
                        break;
                    case 4:  //remove all records
                        SiteLanguageDAO.deleteAllSiteLanguage();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "SiteLanguage");
                        response.sendRedirect("admin/SiteLanguageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error editing SiteLanguage, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error adding SiteLanguage.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error editing SiteLanguage.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error deleting SiteLanguage.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error clearing SiteLanguage.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Unknown Error SiteLanguage, possibly an invalid action.");
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
                        int id = SliderDAO.addSlider(request.getParameter("sliderName"), Integer.parseInt(request.getParameter("sliderTypeId")), Integer.parseInt(request.getParameter("formId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider", id);
                        }
                        Database.updateCount("Slider", 1);
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Added Slider Successfully!");
                        break;
                    case 2: //update            
                        SliderDAO.updateSlider(Integer.parseInt(request.getParameter("sliderId")), request.getParameter("sliderName"), Integer.parseInt(request.getParameter("sliderTypeId")), Integer.parseInt(request.getParameter("formId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider", Integer.parseInt(request.getParameter("sliderId")));
                        response.sendRedirect("admin/SliderUI.jsp?id=" + request.getParameter("sliderId") + "&SuccessMsg=Updated Slider Successfully!");
                        break;
                    case 3:  //delete
                        SliderDAO.deleteSliderById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Slider", request.getParameter("id"));
                        Database.updateCount("Slider", -1);
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Deleted Slider Successfully!");
                        break;
                    case 4:  //remove all records
                        SliderDAO.deleteAllSlider();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = SliderItemDAO.addSliderItem(request.getParameter("title"), request.getParameter("description"), request.getParameter("url"), request.getParameter("imageName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("sliderId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem", id);
                        }

                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Added SliderItem Successfully!");
                        break;
                    case 2: //update            
                        SliderItemDAO.updateSliderItem(Integer.parseInt(request.getParameter("sliderItemId")), request.getParameter("title"), request.getParameter("description"), request.getParameter("url"), request.getParameter("imageName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("sliderId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem", Integer.parseInt(request.getParameter("sliderItemId")));
                        response.sendRedirect("admin/SliderItemUI.jsp?id=" + request.getParameter("sliderItemId") + "&SuccessMsg=Updated SliderItem Successfully!");
                        break;
                    case 3:  //delete
                        SliderItemDAO.deleteSliderItemById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderItem", request.getParameter("id"));

                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Deleted SliderItem Successfully!");
                        break;
                    case 4:  //remove all records
                        SliderItemDAO.deleteAllSliderItem();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = SliderTypeDAO.addSliderType(request.getParameter("typeName"), request.getParameter("code"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType", id);
                        }

                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Added SliderType Successfully!");
                        break;
                    case 2: //update            
                        SliderTypeDAO.updateSliderType(Integer.parseInt(request.getParameter("sliderTypeId")), request.getParameter("typeName"), request.getParameter("code"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType", Integer.parseInt(request.getParameter("sliderTypeId")));
                        response.sendRedirect("admin/SliderTypeUI.jsp?id=" + request.getParameter("sliderTypeId") + "&SuccessMsg=Updated SliderType Successfully!");
                        break;
                    case 3:  //delete
                        SliderTypeDAO.deleteSliderTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "SliderType", request.getParameter("id"));

                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Deleted SliderType Successfully!");
                        break;
                    case 4:  //remove all records
                        SliderTypeDAO.deleteAllSliderType();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("status"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = StatusDAO.addStatus(Integer.parseInt(request.getParameter("code")), request.getParameter("statusName"), request.getParameter("appliesTo"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Status", id);
                        }

                        response.sendRedirect("admin/StatusUI.jsp?SuccessMsg=Added Status Successfully!");
                        break;
                    case 2: //update            
                        StatusDAO.updateStatus(Integer.parseInt(request.getParameter("statusId")), Integer.parseInt(request.getParameter("code")), request.getParameter("statusName"), request.getParameter("appliesTo"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Status", Integer.parseInt(request.getParameter("statusId")));
                        response.sendRedirect("admin/StatusUI.jsp?id=" + request.getParameter("statusId") + "&SuccessMsg=Updated Status Successfully!");
                        break;
                    case 3:  //delete
                        StatusDAO.deleteStatusById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Status", request.getParameter("id"));

                        response.sendRedirect("admin/StatusUI.jsp?SuccessMsg=Deleted Status Successfully!");
                        break;
                    case 4:  //remove all records
                        StatusDAO.deleteAllStatus();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Status");
                        response.sendRedirect("admin/StatusUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/StatusUI.jsp?ErrorMsg=Error editing Status, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/StatusUI.jsp?ErrorMsg=Error adding Status.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/StatusUI.jsp?ErrorMsg=Error editing Status.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/StatusUI.jsp?ErrorMsg=Error deleting Status.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/StatusUI.jsp?ErrorMsg=Error clearing Status.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/StatusUI.jsp?ErrorMsg=Unknown Error Status, possibly an invalid action.");
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
                        int id = StoreInfoDAO.addStoreInfo(request.getParameter("logoTitle"), request.getParameter("logoImage"), request.getParameter("storeName"), request.getParameter("companyName"), request.getParameter("locale"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo", id);
                        }

                        response.sendRedirect("admin/StoreInfoUI.jsp?SuccessMsg=Added StoreInfo Successfully!");
                        break;
                    case 2: //update            
                        StoreInfoDAO.updateStoreInfo(Integer.parseInt(request.getParameter("storeInfoId")), request.getParameter("logoTitle"), request.getParameter("logoImage"), request.getParameter("storeName"), request.getParameter("companyName"), request.getParameter("locale"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo", Integer.parseInt(request.getParameter("storeInfoId")));
                        response.sendRedirect("admin/StoreInfoUI.jsp?id=" + request.getParameter("storeInfoId") + "&SuccessMsg=Updated StoreInfo Successfully!");
                        break;
                    case 3:  //delete
                        StoreInfoDAO.deleteStoreInfoById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "StoreInfo", request.getParameter("id"));

                        response.sendRedirect("admin/StoreInfoUI.jsp?SuccessMsg=Deleted StoreInfo Successfully!");
                        break;
                    case 4:  //remove all records
                        StoreInfoDAO.deleteAllStoreInfo();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("tax_rate"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = TaxRateDAO.addTaxRate(request.getParameter("taxCategory"), request.getParameter("stateProvince"), request.getParameter("zipPostalCode"), request.getParameter("country"), request.getParameter("countryCode"), Double.parseDouble(request.getParameter("percentage")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "TaxRate", id);
                        }

                        response.sendRedirect("admin/TaxRateUI.jsp?SuccessMsg=Added TaxRate Successfully!");
                        break;
                    case 2: //update            
                        TaxRateDAO.updateTaxRate(Integer.parseInt(request.getParameter("taxRateId")), request.getParameter("taxCategory"), request.getParameter("stateProvince"), request.getParameter("zipPostalCode"), request.getParameter("country"), request.getParameter("countryCode"), Double.parseDouble(request.getParameter("percentage")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "TaxRate", Integer.parseInt(request.getParameter("taxRateId")));
                        response.sendRedirect("admin/TaxRateUI.jsp?id=" + request.getParameter("taxRateId") + "&SuccessMsg=Updated TaxRate Successfully!");
                        break;
                    case 3:  //delete
                        TaxRateDAO.deleteTaxRateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "TaxRate", request.getParameter("id"));

                        response.sendRedirect("admin/TaxRateUI.jsp?SuccessMsg=Deleted TaxRate Successfully!");
                        break;
                    case 4:  //remove all records
                        TaxRateDAO.deleteAllTaxRate();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "TaxRate");
                        response.sendRedirect("admin/TaxRateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error editing TaxRate, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error adding TaxRate.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error editing TaxRate.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error deleting TaxRate.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error clearing TaxRate.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Unknown Error TaxRate, possibly an invalid action.");
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
                        int id = TemplateDAO.addTemplate(request.getParameter("templateName"), request.getParameter("markup"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("templateTypeId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template", id);
                        }

                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Added Template Successfully!");
                        break;
                    case 2: //update            
                        TemplateDAO.updateTemplate(Integer.parseInt(request.getParameter("templateId")), request.getParameter("templateName"), request.getParameter("markup"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("templateTypeId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template", Integer.parseInt(request.getParameter("templateId")));
                        response.sendRedirect("admin/TemplateUI.jsp?id=" + request.getParameter("templateId") + "&SuccessMsg=Updated Template Successfully!");
                        break;
                    case 3:  //delete
                        TemplateDAO.deleteTemplateById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Template", request.getParameter("id"));

                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Deleted Template Successfully!");
                        break;
                    case 4:  //remove all records
                        TemplateDAO.deleteAllTemplate();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("template_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = TemplateTypeDAO.addTemplateType(request.getParameter("typeName"), request.getParameter("value"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "TemplateType", id);
                        }

                        response.sendRedirect("admin/TemplateTypeUI.jsp?SuccessMsg=Added TemplateType Successfully!");
                        break;
                    case 2: //update            
                        TemplateTypeDAO.updateTemplateType(Integer.parseInt(request.getParameter("templateTypeId")), request.getParameter("typeName"), request.getParameter("value"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "TemplateType", Integer.parseInt(request.getParameter("templateTypeId")));
                        response.sendRedirect("admin/TemplateTypeUI.jsp?id=" + request.getParameter("templateTypeId") + "&SuccessMsg=Updated TemplateType Successfully!");
                        break;
                    case 3:  //delete
                        TemplateTypeDAO.deleteTemplateTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "TemplateType", request.getParameter("id"));

                        response.sendRedirect("admin/TemplateTypeUI.jsp?SuccessMsg=Deleted TemplateType Successfully!");
                        break;
                    case 4:  //remove all records
                        TemplateTypeDAO.deleteAllTemplateType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "TemplateType");
                        response.sendRedirect("admin/TemplateTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error editing TemplateType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error adding TemplateType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error editing TemplateType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error deleting TemplateType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error clearing TemplateType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Unknown Error TemplateType, possibly an invalid action.");
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
                        int id = TextStringDAO.addTextString(request.getParameter("key"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString", id);
                        }

                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Added TextString Successfully!");
                        break;
                    case 2: //update            
                        TextStringDAO.updateTextString(Integer.parseInt(request.getParameter("textStringId")), request.getParameter("key"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString", Integer.parseInt(request.getParameter("textStringId")));
                        response.sendRedirect("admin/TextStringUI.jsp?id=" + request.getParameter("textStringId") + "&SuccessMsg=Updated TextString Successfully!");
                        break;
                    case 3:  //delete
                        TextStringDAO.deleteTextStringById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextString", request.getParameter("id"));

                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Deleted TextString Successfully!");
                        break;
                    case 4:  //remove all records
                        TextStringDAO.deleteAllTextString();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("text_string_local"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = TextStringLocalDAO.addTextStringLocal(request.getParameter("value"), request.getParameter("locale"), Integer.parseInt(request.getParameter("textStringId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal", id);
                        }

                        response.sendRedirect("admin/TextStringLocalUI.jsp?SuccessMsg=Added TextStringLocal Successfully!");
                        break;
                    case 2: //update            
                        TextStringLocalDAO.updateTextStringLocal(Integer.parseInt(request.getParameter("localizedStringId")), request.getParameter("value"), request.getParameter("locale"), Integer.parseInt(request.getParameter("textStringId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal", Integer.parseInt(request.getParameter("localizedStringId")));
                        response.sendRedirect("admin/TextStringLocalUI.jsp?id=" + request.getParameter("localizedStringId") + "&SuccessMsg=Updated TextStringLocal Successfully!");
                        break;
                    case 3:  //delete
                        TextStringLocalDAO.deleteTextStringLocalById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal", request.getParameter("id"));

                        response.sendRedirect("admin/TextStringLocalUI.jsp?SuccessMsg=Deleted TextStringLocal Successfully!");
                        break;
                    case 4:  //remove all records
                        TextStringLocalDAO.deleteAllTextStringLocal();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "TextStringLocal");
                        response.sendRedirect("admin/TextStringLocalUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error editing TextStringLocal, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error adding TextStringLocal.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error editing TextStringLocal.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error deleting TextStringLocal.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Error clearing TextStringLocal.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/TextStringLocalUI.jsp?ErrorMsg=Unknown Error TextStringLocal, possibly an invalid action.");
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
                        int id = UserDAO.addUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("securityQuestion"), request.getParameter("securityAnswer"), operatingDateFormat.parse(request.getParameter("registerDate")), request.getParameter("imageURL"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("rank")), request.getParameter("webUrl"), Integer.parseInt(request.getParameter("brandId")), Integer.parseInt(request.getParameter("userTypeId")), Integer.parseInt(request.getParameter("addressId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userGroupId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "User", id);
                        }
                        Database.updateCount("User", 1);
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Added User Successfully!");
                        break;
                    case 2: //update            
                        UserDAO.updateUser(Integer.parseInt(request.getParameter("userId")), request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("securityQuestion"), request.getParameter("securityAnswer"), operatingDateFormat.parse(request.getParameter("registerDate")), request.getParameter("imageURL"), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("rank")), request.getParameter("webUrl"), Integer.parseInt(request.getParameter("brandId")), Integer.parseInt(request.getParameter("userTypeId")), Integer.parseInt(request.getParameter("addressId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userGroupId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "User", Integer.parseInt(request.getParameter("userId")));
                        response.sendRedirect("admin/UserUI.jsp?id=" + request.getParameter("userId") + "&SuccessMsg=Updated User Successfully!");
                        break;
                    case 3:  //delete
                        UserDAO.deleteUserById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "User", request.getParameter("id"));
                        Database.updateCount("User", -1);
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Deleted User Successfully!");
                        break;
                    case 4:  //remove all records
                        UserDAO.deleteAllUser();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = UserActionDAO.addUserAction(operatingDateFormat.parse(request.getParameter("date")), request.getParameter("detail"), Integer.parseInt(request.getParameter("actionTypeId")), Integer.parseInt(request.getParameter("userId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction", id);
                        }

                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Added UserAction Successfully!");
                        break;
                    case 2: //update            
                        UserActionDAO.updateUserAction(Integer.parseInt(request.getParameter("userActionId")), operatingDateFormat.parse(request.getParameter("date")), request.getParameter("detail"), Integer.parseInt(request.getParameter("actionTypeId")), Integer.parseInt(request.getParameter("userId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction", Integer.parseInt(request.getParameter("userActionId")));
                        response.sendRedirect("admin/UserActionUI.jsp?id=" + request.getParameter("userActionId") + "&SuccessMsg=Updated UserAction Successfully!");
                        break;
                    case 3:  //delete
                        UserActionDAO.deleteUserActionById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserAction", request.getParameter("id"));

                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Deleted UserAction Successfully!");
                        break;
                    case 4:  //remove all records
                        UserActionDAO.deleteAllUserAction();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = UserActionTypeDAO.addUserActionType(request.getParameter("typeName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType", id);
                        }

                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Added UserActionType Successfully!");
                        break;
                    case 2: //update            
                        UserActionTypeDAO.updateUserActionType(Integer.parseInt(request.getParameter("userActionTypeId")), request.getParameter("typeName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType", Integer.parseInt(request.getParameter("userActionTypeId")));
                        response.sendRedirect("admin/UserActionTypeUI.jsp?id=" + request.getParameter("userActionTypeId") + "&SuccessMsg=Updated UserActionType Successfully!");
                        break;
                    case 3:  //delete
                        UserActionTypeDAO.deleteUserActionTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserActionType", request.getParameter("id"));

                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Deleted UserActionType Successfully!");
                        break;
                    case 4:  //remove all records
                        UserActionTypeDAO.deleteAllUserActionType();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("user_group"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = UserGroupDAO.addUserGroup(request.getParameter("groupName"), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("discountId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserGroup", id);
                        }

                        response.sendRedirect("admin/UserGroupUI.jsp?SuccessMsg=Added UserGroup Successfully!");
                        break;
                    case 2: //update            
                        UserGroupDAO.updateUserGroup(Integer.parseInt(request.getParameter("userGroupId")), request.getParameter("groupName"), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("discountId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserGroup", Integer.parseInt(request.getParameter("userGroupId")));
                        response.sendRedirect("admin/UserGroupUI.jsp?id=" + request.getParameter("userGroupId") + "&SuccessMsg=Updated UserGroup Successfully!");
                        break;
                    case 3:  //delete
                        UserGroupDAO.deleteUserGroupById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserGroup", request.getParameter("id"));

                        response.sendRedirect("admin/UserGroupUI.jsp?SuccessMsg=Deleted UserGroup Successfully!");
                        break;
                    case 4:  //remove all records
                        UserGroupDAO.deleteAllUserGroup();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserGroup");
                        response.sendRedirect("admin/UserGroupUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error editing UserGroup, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error adding UserGroup.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error editing UserGroup.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error deleting UserGroup.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error clearing UserGroup.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Unknown Error UserGroup, possibly an invalid action.");
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
                        int id = UserRoleDAO.addUserRole(request.getParameter("userName"), request.getParameter("roleName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole", id);
                        }

                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Added UserRole Successfully!");
                        break;
                    case 2: //update            
                        UserRoleDAO.updateUserRole(request.getParameter("userName"), request.getParameter("roleName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole", Integer.parseInt(request.getParameter("userName")));
                        response.sendRedirect("admin/UserRoleUI.jsp?id=" + request.getParameter("userName") + "&SuccessMsg=Updated UserRole Successfully!");
                        break;
                    case 3:  //delete
                        UserRoleDAO.deleteUserRoleById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserRole", request.getParameter("id"));

                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Deleted UserRole Successfully!");
                        break;
                    case 4:  //remove all records
                        UserRoleDAO.deleteAllUserRole();
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
                System.out.println("Error:" + e.getMessage());
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
                        int id = UserServiceDAO.addUserService(operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("details"), request.getParameter("contractUrl"), request.getParameter("deliverableUrl"), Double.parseDouble(request.getParameter("depositAmount")), Integer.parseInt(request.getParameter("userRank")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("serviceId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService", id);
                        }

                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Added UserService Successfully!");
                        break;
                    case 2: //update            
                        UserServiceDAO.updateUserService(Integer.parseInt(request.getParameter("userServiceId")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("details"), request.getParameter("contractUrl"), request.getParameter("deliverableUrl"), Double.parseDouble(request.getParameter("depositAmount")), Integer.parseInt(request.getParameter("userRank")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("serviceId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService", Integer.parseInt(request.getParameter("userServiceId")));
                        response.sendRedirect("admin/UserServiceUI.jsp?id=" + request.getParameter("userServiceId") + "&SuccessMsg=Updated UserService Successfully!");
                        break;
                    case 3:  //delete
                        UserServiceDAO.deleteUserServiceById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserService", request.getParameter("id"));

                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Deleted UserService Successfully!");
                        break;
                    case 4:  //remove all records
                        UserServiceDAO.deleteAllUserService();
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
                System.out.println("Error:" + e.getMessage());
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

        if (request.getParameter("form").equals("user_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = UserTypeDAO.addUserType(request.getParameter("typeName"), request.getParameter("description"), request.getParameter("redirectURL"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserType", id);
                        }

                        response.sendRedirect("admin/UserTypeUI.jsp?SuccessMsg=Added UserType Successfully!");
                        break;
                    case 2: //update            
                        UserTypeDAO.updateUserType(Integer.parseInt(request.getParameter("typeId")), request.getParameter("typeName"), request.getParameter("description"), request.getParameter("redirectURL"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserType", Integer.parseInt(request.getParameter("typeId")));
                        response.sendRedirect("admin/UserTypeUI.jsp?id=" + request.getParameter("typeId") + "&SuccessMsg=Updated UserType Successfully!");
                        break;
                    case 3:  //delete
                        UserTypeDAO.deleteUserTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserType", request.getParameter("id"));

                        response.sendRedirect("admin/UserTypeUI.jsp?SuccessMsg=Deleted UserType Successfully!");
                        break;
                    case 4:  //remove all records
                        UserTypeDAO.deleteAllUserType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "UserType");
                        response.sendRedirect("admin/UserTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error editing UserType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error adding UserType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error editing UserType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error deleting UserType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error clearing UserType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Unknown Error UserType, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("vendor"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = VendorDAO.addVendor(request.getParameter("vendorName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorTypeId")));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "Vendor", id);
                        }

                        response.sendRedirect("admin/VendorUI.jsp?SuccessMsg=Added Vendor Successfully!");
                        break;
                    case 2: //update            
                        VendorDAO.updateVendor(Integer.parseInt(request.getParameter("vendorId")), request.getParameter("vendorName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("status")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorTypeId")));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "Vendor", Integer.parseInt(request.getParameter("vendorId")));
                        response.sendRedirect("admin/VendorUI.jsp?id=" + request.getParameter("vendorId") + "&SuccessMsg=Updated Vendor Successfully!");
                        break;
                    case 3:  //delete
                        VendorDAO.deleteVendorById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "Vendor", request.getParameter("id"));

                        response.sendRedirect("admin/VendorUI.jsp?SuccessMsg=Deleted Vendor Successfully!");
                        break;
                    case 4:  //remove all records
                        VendorDAO.deleteAllVendor();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "Vendor");
                        response.sendRedirect("admin/VendorUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error editing Vendor, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error adding Vendor.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error editing Vendor.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error deleting Vendor.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error clearing Vendor.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Unknown Error Vendor, possibly an invalid action.");
                        break;
                }
            }
        }

        if (request.getParameter("form").equals("vendor_type"))
        {
            try
            {
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = VendorTypeDAO.addVendorType(request.getParameter("typeName"));
                        if (id != 0)
                        {
                            Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "VendorType", id);
                        }

                        response.sendRedirect("admin/VendorTypeUI.jsp?SuccessMsg=Added VendorType Successfully!");
                        break;
                    case 2: //update            
                        VendorTypeDAO.updateVendorType(Integer.parseInt(request.getParameter("vendorTypeId")), request.getParameter("typeName"));
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "VendorType", Integer.parseInt(request.getParameter("vendorTypeId")));
                        response.sendRedirect("admin/VendorTypeUI.jsp?id=" + request.getParameter("vendorTypeId") + "&SuccessMsg=Updated VendorType Successfully!");
                        break;
                    case 3:  //delete
                        VendorTypeDAO.deleteVendorTypeById(request.getParameter("id"));
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "VendorType", request.getParameter("id"));

                        response.sendRedirect("admin/VendorTypeUI.jsp?SuccessMsg=Deleted VendorType Successfully!");
                        break;
                    case 4:  //remove all records
                        VendorTypeDAO.deleteAllVendorType();
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "VendorType");
                        response.sendRedirect("admin/VendorTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error editing VendorType, Invalid Action.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error adding VendorType.");
                        break; //create
                    case 2:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error editing VendorType.");
                        break; //update                                                    
                    case 3:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error deleting VendorType.");
                        break; //delete                                                    
                    case 4:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error clearing VendorType.");
                        break; //clear                          
                    default:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Unknown Error VendorType, possibly an invalid action.");
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
