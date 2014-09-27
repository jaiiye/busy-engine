package com.busy.engine.data;

import com.busy.engine.dao.*;
import com.busy.engine.entity.*;
import static com.busy.engine.web.SecurityHelper.setSessionUser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Operations extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //String userName = request.getUserPrincipal().getName();
        String userName = (request.getUserPrincipal() == null || request.getUserPrincipal().getName() == null) ? "admin" : request.getUserPrincipal().getName();
        User user = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new java.util.Date());
        
        SimpleDateFormat operatingDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (!userName.equals("admin"))            
        {
            UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");

            //find out who the logged-on user is                
            user = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);

            setSessionUser(request, user);                 
        }       
        
        if(request.getParameter("form").equals("address"))
        {   
            try
            {    
                AddressDao addressDao = (AddressDao) this.getServletContext().getAttribute("addressDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = addressDao.add(new Address(null,request.getParameter("recipient"), request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("stateProvince"), request.getParameter("zipPostalCode"), request.getParameter("country"), request.getParameter("region"), Integer.parseInt(request.getParameter("addressStatus")), request.getParameter("locale")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Address", id); }
                        
                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Added Address Successfully!");
                        break;
                    case 2: //update            
                        addressDao.update(new Address(Integer.parseInt(request.getParameter("addressId")), request.getParameter("recipient"), request.getParameter("address1"), request.getParameter("address2"), request.getParameter("city"), request.getParameter("stateProvince"), request.getParameter("zipPostalCode"), request.getParameter("country"), request.getParameter("region"), Integer.parseInt(request.getParameter("addressStatus")), request.getParameter("locale")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Address", Integer.parseInt(request.getParameter("addressId")));
                        response.sendRedirect("admin/AddressUI.jsp?id=" + request.getParameter("addressId") + "&SuccessMsg=Updated Address Successfully!");
                        break;
                    case 3:  //delete
                        addressDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Address", request.getParameter("id"));
                        
                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Deleted Address Successfully!");
                        break;
                    case 4:  //remove all records
                        addressDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Address");
                        response.sendRedirect("admin/AddressUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error editing Address, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error adding Address.");   break; //create
                    case 2:  response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error editing Address.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error deleting Address."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Error clearing Address."); break; //clear                          
                    default: response.sendRedirect("admin/AddressUI.jsp?ErrorMsg=Unknown Error Address, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("affiliate"))
        {   
            try
            {    
                AffiliateDao affiliateDao = (AffiliateDao) this.getServletContext().getAttribute("affiliateDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = affiliateDao.add(new Affiliate(null,request.getParameter("companyName"), request.getParameter("email"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("webUrl"), request.getParameter("details"), Integer.parseInt(request.getParameter("serviceHours")), Integer.parseInt(request.getParameter("affiliateStatus")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Affiliate", id); }
                        
                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Added Affiliate Successfully!");
                        break;
                    case 2: //update            
                        affiliateDao.update(new Affiliate(Integer.parseInt(request.getParameter("affiliateId")), request.getParameter("companyName"), request.getParameter("email"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("webUrl"), request.getParameter("details"), Integer.parseInt(request.getParameter("serviceHours")), Integer.parseInt(request.getParameter("affiliateStatus")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("addressId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Affiliate", Integer.parseInt(request.getParameter("affiliateId")));
                        response.sendRedirect("admin/AffiliateUI.jsp?id=" + request.getParameter("affiliateId") + "&SuccessMsg=Updated Affiliate Successfully!");
                        break;
                    case 3:  //delete
                        affiliateDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Affiliate", request.getParameter("id"));
                        
                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Deleted Affiliate Successfully!");
                        break;
                    case 4:  //remove all records
                        affiliateDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Affiliate");
                        response.sendRedirect("admin/AffiliateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error editing Affiliate, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error adding Affiliate.");   break; //create
                    case 2:  response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error editing Affiliate.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error deleting Affiliate."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Error clearing Affiliate."); break; //clear                          
                    default: response.sendRedirect("admin/AffiliateUI.jsp?ErrorMsg=Unknown Error Affiliate, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("blog"))
        {   
            try
            {    
                BlogDao blogDao = (BlogDao) this.getServletContext().getAttribute("blogDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = blogDao.add(new Blog(null,request.getParameter("topic"), Integer.parseInt(request.getParameter("blogTypeId")), Integer.parseInt(request.getParameter("knowledgeBaseId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Blog", id); }
                        Database.updateCount("Blog", 1);
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Added Blog Successfully!");
                        break;
                    case 2: //update            
                        blogDao.update(new Blog(Integer.parseInt(request.getParameter("blogId")), request.getParameter("topic"), Integer.parseInt(request.getParameter("blogTypeId")), Integer.parseInt(request.getParameter("knowledgeBaseId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Blog", Integer.parseInt(request.getParameter("blogId")));
                        response.sendRedirect("admin/BlogUI.jsp?id=" + request.getParameter("blogId") + "&SuccessMsg=Updated Blog Successfully!");
                        break;
                    case 3:  //delete
                        blogDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Blog", request.getParameter("id"));
                        Database.updateCount("Blog", -1);
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Deleted Blog Successfully!");
                        break;
                    case 4:  //remove all records
                        blogDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Blog");
                        response.sendRedirect("admin/BlogUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error editing Blog, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error adding Blog.");   break; //create
                    case 2:  response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error editing Blog.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error deleting Blog."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Error clearing Blog."); break; //clear                          
                    default: response.sendRedirect("admin/BlogUI.jsp?ErrorMsg=Unknown Error Blog, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("blog_post"))
        {   
            try
            {    
                BlogPostDao blogPostDao = (BlogPostDao) this.getServletContext().getAttribute("blogPostDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = blogPostDao.add(new BlogPost(null,request.getParameter("title"), request.getParameter("content"), request.getParameter("imageURL"), request.getParameter("tags"), Integer.parseInt(request.getParameter("featured")), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("postStatus")), request.getParameter("excerpt"), operatingDateFormat.parse(request.getParameter("lastModified")), request.getParameter("locale"), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("metaTagId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPost", id); }
                        Database.updateCount("BlogPost", 1);
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Added BlogPost Successfully!");
                        break;
                    case 2: //update            
                        blogPostDao.update(new BlogPost(Integer.parseInt(request.getParameter("blogPostId")), request.getParameter("title"), request.getParameter("content"), request.getParameter("imageURL"), request.getParameter("tags"), Integer.parseInt(request.getParameter("featured")), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("postStatus")), request.getParameter("excerpt"), operatingDateFormat.parse(request.getParameter("lastModified")), request.getParameter("locale"), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("metaTagId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPost", Integer.parseInt(request.getParameter("blogPostId")));
                        response.sendRedirect("admin/BlogPostUI.jsp?id=" + request.getParameter("blogPostId") + "&SuccessMsg=Updated BlogPost Successfully!");
                        break;
                    case 3:  //delete
                        blogPostDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPost", request.getParameter("id"));
                        Database.updateCount("BlogPost", -1);
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Deleted BlogPost Successfully!");
                        break;
                    case 4:  //remove all records
                        blogPostDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPost");
                        response.sendRedirect("admin/BlogPostUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error editing BlogPost, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error adding BlogPost.");   break; //create
                    case 2:  response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error editing BlogPost.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error deleting BlogPost."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Error clearing BlogPost."); break; //clear                          
                    default: response.sendRedirect("admin/BlogPostUI.jsp?ErrorMsg=Unknown Error BlogPost, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("blog_post_category"))
        {   
            try
            {    
                BlogPostCategoryDao blogPostCategoryDao = (BlogPostCategoryDao) this.getServletContext().getAttribute("blogPostCategoryDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = blogPostCategoryDao.add(new BlogPostCategory(null,Integer.parseInt(request.getParameter("blogPostId")), Integer.parseInt(request.getParameter("postCategoryId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPostCategory", id); }
                        
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?SuccessMsg=Added BlogPostCategory Successfully!");
                        break;
                    case 2: //update            
                        blogPostCategoryDao.update(new BlogPostCategory(Integer.parseInt(request.getParameter("blogPostCategoryId")), Integer.parseInt(request.getParameter("blogPostId")), Integer.parseInt(request.getParameter("postCategoryId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPostCategory", Integer.parseInt(request.getParameter("blogPostCategoryId")));
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?id=" + request.getParameter("blogPostCategoryId") + "&SuccessMsg=Updated BlogPostCategory Successfully!");
                        break;
                    case 3:  //delete
                        blogPostCategoryDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPostCategory", request.getParameter("id"));
                        
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?SuccessMsg=Deleted BlogPostCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        blogPostCategoryDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogPostCategory");
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error editing BlogPostCategory, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error adding BlogPostCategory.");   break; //create
                    case 2:  response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error editing BlogPostCategory.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error deleting BlogPostCategory."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Error clearing BlogPostCategory."); break; //clear                          
                    default: response.sendRedirect("admin/BlogPostCategoryUI.jsp?ErrorMsg=Unknown Error BlogPostCategory, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("blog_type"))
        {   
            try
            {    
                BlogTypeDao blogTypeDao = (BlogTypeDao) this.getServletContext().getAttribute("blogTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = blogTypeDao.add(new BlogType(null,request.getParameter("typeName")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogType", id); }
                        
                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Added BlogType Successfully!");
                        break;
                    case 2: //update            
                        blogTypeDao.update(new BlogType(Integer.parseInt(request.getParameter("blogTypeId")), request.getParameter("typeName")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogType", Integer.parseInt(request.getParameter("blogTypeId")));
                        response.sendRedirect("admin/BlogTypeUI.jsp?id=" + request.getParameter("blogTypeId") + "&SuccessMsg=Updated BlogType Successfully!");
                        break;
                    case 3:  //delete
                        blogTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Deleted BlogType Successfully!");
                        break;
                    case 4:  //remove all records
                        blogTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "BlogType");
                        response.sendRedirect("admin/BlogTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error editing BlogType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error adding BlogType.");   break; //create
                    case 2:  response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error editing BlogType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error deleting BlogType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Error clearing BlogType."); break; //clear                          
                    default: response.sendRedirect("admin/BlogTypeUI.jsp?ErrorMsg=Unknown Error BlogType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("category"))
        {   
            try
            {    
                CategoryDao categoryDao = (CategoryDao) this.getServletContext().getAttribute("categoryDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = categoryDao.add(new Category(null,request.getParameter("categoryName"), Integer.parseInt(request.getParameter("discountId")), Integer.parseInt(request.getParameter("parentCategoryId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Category", id); }
                        Database.updateCount("Category", 1);
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Added Category Successfully!");
                        break;
                    case 2: //update            
                        categoryDao.update(new Category(Integer.parseInt(request.getParameter("categoryId")), request.getParameter("categoryName"), Integer.parseInt(request.getParameter("discountId")), Integer.parseInt(request.getParameter("parentCategoryId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Category", Integer.parseInt(request.getParameter("categoryId")));
                        response.sendRedirect("admin/CategoryUI.jsp?id=" + request.getParameter("categoryId") + "&SuccessMsg=Updated Category Successfully!");
                        break;
                    case 3:  //delete
                        categoryDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Category", request.getParameter("id"));
                        Database.updateCount("Category", -1);
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Deleted Category Successfully!");
                        break;
                    case 4:  //remove all records
                        categoryDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Category");
                        response.sendRedirect("admin/CategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error editing Category, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error adding Category.");   break; //create
                    case 2:  response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error editing Category.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error deleting Category."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Error clearing Category."); break; //clear                          
                    default: response.sendRedirect("admin/CategoryUI.jsp?ErrorMsg=Unknown Error Category, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("comment"))
        {   
            try
            {    
                CommentDao commentDao = (CommentDao) this.getServletContext().getAttribute("commentDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = commentDao.add(new Comment(null,request.getParameter("title"), request.getParameter("content"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("commentStatus")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogPostId")), Integer.parseInt(request.getParameter("itemReviewId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Comment", id); }
                        Database.updateCount("Comment", 1);
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Added Comment Successfully!");
                        break;
                    case 2: //update            
                        commentDao.update(new Comment(Integer.parseInt(request.getParameter("commentId")), request.getParameter("title"), request.getParameter("content"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("commentStatus")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("blogPostId")), Integer.parseInt(request.getParameter("itemReviewId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Comment", Integer.parseInt(request.getParameter("commentId")));
                        response.sendRedirect("admin/CommentUI.jsp?id=" + request.getParameter("commentId") + "&SuccessMsg=Updated Comment Successfully!");
                        break;
                    case 3:  //delete
                        commentDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Comment", request.getParameter("id"));
                        Database.updateCount("Comment", -1);
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Deleted Comment Successfully!");
                        break;
                    case 4:  //remove all records
                        commentDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Comment");
                        response.sendRedirect("admin/CommentUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error editing Comment, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error adding Comment.");   break; //create
                    case 2:  response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error editing Comment.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error deleting Comment."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Error clearing Comment."); break; //clear                          
                    default: response.sendRedirect("admin/CommentUI.jsp?ErrorMsg=Unknown Error Comment, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("contact"))
        {   
            try
            {    
                ContactDao contactDao = (ContactDao) this.getServletContext().getAttribute("contactDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = contactDao.add(new Contact(null,request.getParameter("title"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("position"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("email"), Integer.parseInt(request.getParameter("contactStatus")), request.getParameter("webUrl"), request.getParameter("info")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Contact", id); }
                        
                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Added Contact Successfully!");
                        break;
                    case 2: //update            
                        contactDao.update(new Contact(Integer.parseInt(request.getParameter("contactId")), request.getParameter("title"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("position"), request.getParameter("phone"), request.getParameter("fax"), request.getParameter("email"), Integer.parseInt(request.getParameter("contactStatus")), request.getParameter("webUrl"), request.getParameter("info")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Contact", Integer.parseInt(request.getParameter("contactId")));
                        response.sendRedirect("admin/ContactUI.jsp?id=" + request.getParameter("contactId") + "&SuccessMsg=Updated Contact Successfully!");
                        break;
                    case 3:  //delete
                        contactDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Contact", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Deleted Contact Successfully!");
                        break;
                    case 4:  //remove all records
                        contactDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Contact");
                        response.sendRedirect("admin/ContactUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error editing Contact, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error adding Contact.");   break; //create
                    case 2:  response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error editing Contact.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error deleting Contact."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Error clearing Contact."); break; //clear                          
                    default: response.sendRedirect("admin/ContactUI.jsp?ErrorMsg=Unknown Error Contact, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("country"))
        {   
            try
            {    
                CountryDao countryDao = (CountryDao) this.getServletContext().getAttribute("countryDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = countryDao.add(new Country(null,request.getParameter("name"), request.getParameter("isoCode"), Integer.parseInt(request.getParameter("isoNumber")), Integer.parseInt(request.getParameter("hasVat"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Country", id); }
                        
                        response.sendRedirect("admin/CountryUI.jsp?SuccessMsg=Added Country Successfully!");
                        break;
                    case 2: //update            
                        countryDao.update(new Country(Integer.parseInt(request.getParameter("countryId")), request.getParameter("name"), request.getParameter("isoCode"), Integer.parseInt(request.getParameter("isoNumber")), Integer.parseInt(request.getParameter("hasVat"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Country", Integer.parseInt(request.getParameter("countryId")));
                        response.sendRedirect("admin/CountryUI.jsp?id=" + request.getParameter("countryId") + "&SuccessMsg=Updated Country Successfully!");
                        break;
                    case 3:  //delete
                        countryDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Country", request.getParameter("id"));
                        
                        response.sendRedirect("admin/CountryUI.jsp?SuccessMsg=Deleted Country Successfully!");
                        break;
                    case 4:  //remove all records
                        countryDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Country");
                        response.sendRedirect("admin/CountryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CountryUI.jsp?ErrorMsg=Error editing Country, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/CountryUI.jsp?ErrorMsg=Error adding Country.");   break; //create
                    case 2:  response.sendRedirect("admin/CountryUI.jsp?ErrorMsg=Error editing Country.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/CountryUI.jsp?ErrorMsg=Error deleting Country."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/CountryUI.jsp?ErrorMsg=Error clearing Country."); break; //clear                          
                    default: response.sendRedirect("admin/CountryUI.jsp?ErrorMsg=Unknown Error Country, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("customer"))
        {   
            try
            {    
                CustomerDao customerDao = (CustomerDao) this.getServletContext().getAttribute("customerDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = customerDao.add(new Customer(null,Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("billingAddressId")), Integer.parseInt(request.getParameter("shippingAddressId")), Integer.parseInt(request.getParameter("customerStatus"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Customer", id); }
                        
                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Added Customer Successfully!");
                        break;
                    case 2: //update            
                        customerDao.update(new Customer(Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("billingAddressId")), Integer.parseInt(request.getParameter("shippingAddressId")), Integer.parseInt(request.getParameter("customerStatus"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Customer", Integer.parseInt(request.getParameter("customerId")));
                        response.sendRedirect("admin/CustomerUI.jsp?id=" + request.getParameter("customerId") + "&SuccessMsg=Updated Customer Successfully!");
                        break;
                    case 3:  //delete
                        customerDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Customer", request.getParameter("id"));
                        
                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Deleted Customer Successfully!");
                        break;
                    case 4:  //remove all records
                        customerDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Customer");
                        response.sendRedirect("admin/CustomerUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error editing Customer, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error adding Customer.");   break; //create
                    case 2:  response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error editing Customer.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error deleting Customer."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Error clearing Customer."); break; //clear                          
                    default: response.sendRedirect("admin/CustomerUI.jsp?ErrorMsg=Unknown Error Customer, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("customer_order"))
        {   
            try
            {    
                CustomerOrderDao customerOrderDao = (CustomerOrderDao) this.getServletContext().getAttribute("customerOrderDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = customerOrderDao.add(new CustomerOrder(null,Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("orderId")), Integer.parseInt(request.getParameter("discountId")), request.getParameter("customerIp")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "CustomerOrder", id); }
                        
                        response.sendRedirect("admin/CustomerOrderUI.jsp?SuccessMsg=Added CustomerOrder Successfully!");
                        break;
                    case 2: //update            
                        customerOrderDao.update(new CustomerOrder(Integer.parseInt(request.getParameter("customerOrderId")), Integer.parseInt(request.getParameter("customerId")), Integer.parseInt(request.getParameter("orderId")), Integer.parseInt(request.getParameter("discountId")), request.getParameter("customerIp")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "CustomerOrder", Integer.parseInt(request.getParameter("customerOrderId")));
                        response.sendRedirect("admin/CustomerOrderUI.jsp?id=" + request.getParameter("customerOrderId") + "&SuccessMsg=Updated CustomerOrder Successfully!");
                        break;
                    case 3:  //delete
                        customerOrderDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "CustomerOrder", request.getParameter("id"));
                        
                        response.sendRedirect("admin/CustomerOrderUI.jsp?SuccessMsg=Deleted CustomerOrder Successfully!");
                        break;
                    case 4:  //remove all records
                        customerOrderDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "CustomerOrder");
                        response.sendRedirect("admin/CustomerOrderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error editing CustomerOrder, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error adding CustomerOrder.");   break; //create
                    case 2:  response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error editing CustomerOrder.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error deleting CustomerOrder."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Error clearing CustomerOrder."); break; //clear                          
                    default: response.sendRedirect("admin/CustomerOrderUI.jsp?ErrorMsg=Unknown Error CustomerOrder, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("dashboard"))
        {   
            try
            {    
                DashboardDao dashboardDao = (DashboardDao) this.getServletContext().getAttribute("dashboardDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = dashboardDao.add(new Dashboard(null,Integer.parseInt(request.getParameter("userCount")), Integer.parseInt(request.getParameter("blogPostCount")), Integer.parseInt(request.getParameter("itemCount")), Integer.parseInt(request.getParameter("orderCount")), Integer.parseInt(request.getParameter("siteFileCount")), Integer.parseInt(request.getParameter("imageCount")), Integer.parseInt(request.getParameter("blogCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("pageCount")), Integer.parseInt(request.getParameter("formCount")), Integer.parseInt(request.getParameter("sliderCount")), Integer.parseInt(request.getParameter("itemBrandCount")), Integer.parseInt(request.getParameter("categoryCount")), Integer.parseInt(request.getParameter("itemOptionCount"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Dashboard", id); }
                        
                        response.sendRedirect("admin/DashboardUI.jsp?SuccessMsg=Added Dashboard Successfully!");
                        break;
                    case 2: //update            
                        dashboardDao.update(new Dashboard(Integer.parseInt(request.getParameter("dashboardId")), Integer.parseInt(request.getParameter("userCount")), Integer.parseInt(request.getParameter("blogPostCount")), Integer.parseInt(request.getParameter("itemCount")), Integer.parseInt(request.getParameter("orderCount")), Integer.parseInt(request.getParameter("siteFileCount")), Integer.parseInt(request.getParameter("imageCount")), Integer.parseInt(request.getParameter("blogCount")), Integer.parseInt(request.getParameter("commentCount")), Integer.parseInt(request.getParameter("pageCount")), Integer.parseInt(request.getParameter("formCount")), Integer.parseInt(request.getParameter("sliderCount")), Integer.parseInt(request.getParameter("itemBrandCount")), Integer.parseInt(request.getParameter("categoryCount")), Integer.parseInt(request.getParameter("itemOptionCount"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Dashboard", Integer.parseInt(request.getParameter("dashboardId")));
                        response.sendRedirect("admin/DashboardUI.jsp?id=" + request.getParameter("dashboardId") + "&SuccessMsg=Updated Dashboard Successfully!");
                        break;
                    case 3:  //delete
                        dashboardDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Dashboard", request.getParameter("id"));
                        
                        response.sendRedirect("admin/DashboardUI.jsp?SuccessMsg=Deleted Dashboard Successfully!");
                        break;
                    case 4:  //remove all records
                        dashboardDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Dashboard");
                        response.sendRedirect("admin/DashboardUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error editing Dashboard, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error adding Dashboard.");   break; //create
                    case 2:  response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error editing Dashboard.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error deleting Dashboard."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Error clearing Dashboard."); break; //clear                          
                    default: response.sendRedirect("admin/DashboardUI.jsp?ErrorMsg=Unknown Error Dashboard, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("discount"))
        {   
            try
            {    
                DiscountDao discountDao = (DiscountDao) this.getServletContext().getAttribute("discountDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = discountDao.add(new Discount(null,request.getParameter("discountName"), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("discountPercent")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("couponCode"), Integer.parseInt(request.getParameter("discountStatus")), Integer.parseInt(request.getParameter("askCouponCode")), Integer.parseInt(request.getParameter("usePercentage"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Discount", id); }
                        
                        response.sendRedirect("admin/DiscountUI.jsp?SuccessMsg=Added Discount Successfully!");
                        break;
                    case 2: //update            
                        discountDao.update(new Discount(Integer.parseInt(request.getParameter("discountId")), request.getParameter("discountName"), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("discountPercent")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("couponCode"), Integer.parseInt(request.getParameter("discountStatus")), Integer.parseInt(request.getParameter("askCouponCode")), Integer.parseInt(request.getParameter("usePercentage"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Discount", Integer.parseInt(request.getParameter("discountId")));
                        response.sendRedirect("admin/DiscountUI.jsp?id=" + request.getParameter("discountId") + "&SuccessMsg=Updated Discount Successfully!");
                        break;
                    case 3:  //delete
                        discountDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Discount", request.getParameter("id"));
                        
                        response.sendRedirect("admin/DiscountUI.jsp?SuccessMsg=Deleted Discount Successfully!");
                        break;
                    case 4:  //remove all records
                        discountDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Discount");
                        response.sendRedirect("admin/DiscountUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error editing Discount, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error adding Discount.");   break; //create
                    case 2:  response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error editing Discount.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error deleting Discount."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Error clearing Discount."); break; //clear                          
                    default: response.sendRedirect("admin/DiscountUI.jsp?ErrorMsg=Unknown Error Discount, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("entity_status"))
        {   
            try
            {    
                EntityStatusDao entityStatusDao = (EntityStatusDao) this.getServletContext().getAttribute("entityStatusDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = entityStatusDao.add(new EntityStatus(null,Integer.parseInt(request.getParameter("statusCode")), request.getParameter("statusName"), request.getParameter("appliesTo")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "EntityStatus", id); }
                        
                        response.sendRedirect("admin/EntityStatusUI.jsp?SuccessMsg=Added EntityStatus Successfully!");
                        break;
                    case 2: //update            
                        entityStatusDao.update(new EntityStatus(Integer.parseInt(request.getParameter("entityStatusId")), Integer.parseInt(request.getParameter("statusCode")), request.getParameter("statusName"), request.getParameter("appliesTo")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "EntityStatus", Integer.parseInt(request.getParameter("entityStatusId")));
                        response.sendRedirect("admin/EntityStatusUI.jsp?id=" + request.getParameter("entityStatusId") + "&SuccessMsg=Updated EntityStatus Successfully!");
                        break;
                    case 3:  //delete
                        entityStatusDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "EntityStatus", request.getParameter("id"));
                        
                        response.sendRedirect("admin/EntityStatusUI.jsp?SuccessMsg=Deleted EntityStatus Successfully!");
                        break;
                    case 4:  //remove all records
                        entityStatusDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "EntityStatus");
                        response.sendRedirect("admin/EntityStatusUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/EntityStatusUI.jsp?ErrorMsg=Error editing EntityStatus, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/EntityStatusUI.jsp?ErrorMsg=Error adding EntityStatus.");   break; //create
                    case 2:  response.sendRedirect("admin/EntityStatusUI.jsp?ErrorMsg=Error editing EntityStatus.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/EntityStatusUI.jsp?ErrorMsg=Error deleting EntityStatus."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/EntityStatusUI.jsp?ErrorMsg=Error clearing EntityStatus."); break; //clear                          
                    default: response.sendRedirect("admin/EntityStatusUI.jsp?ErrorMsg=Unknown Error EntityStatus, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("file_folder"))
        {   
            try
            {    
                FileFolderDao fileFolderDao = (FileFolderDao) this.getServletContext().getAttribute("fileFolderDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = fileFolderDao.add(new FileFolder(null,Integer.parseInt(request.getParameter("siteFileId")), Integer.parseInt(request.getParameter("siteFolderId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "FileFolder", id); }
                        
                        response.sendRedirect("admin/FileFolderUI.jsp?SuccessMsg=Added FileFolder Successfully!");
                        break;
                    case 2: //update            
                        fileFolderDao.update(new FileFolder(Integer.parseInt(request.getParameter("fileFolderId")), Integer.parseInt(request.getParameter("siteFileId")), Integer.parseInt(request.getParameter("siteFolderId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "FileFolder", Integer.parseInt(request.getParameter("fileFolderId")));
                        response.sendRedirect("admin/FileFolderUI.jsp?id=" + request.getParameter("fileFolderId") + "&SuccessMsg=Updated FileFolder Successfully!");
                        break;
                    case 3:  //delete
                        fileFolderDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "FileFolder", request.getParameter("id"));
                        
                        response.sendRedirect("admin/FileFolderUI.jsp?SuccessMsg=Deleted FileFolder Successfully!");
                        break;
                    case 4:  //remove all records
                        fileFolderDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "FileFolder");
                        response.sendRedirect("admin/FileFolderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error editing FileFolder, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error adding FileFolder.");   break; //create
                    case 2:  response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error editing FileFolder.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error deleting FileFolder."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Error clearing FileFolder."); break; //clear                          
                    default: response.sendRedirect("admin/FileFolderUI.jsp?ErrorMsg=Unknown Error FileFolder, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("form"))
        {   
            try
            {    
                FormDao formDao = (FormDao) this.getServletContext().getAttribute("formDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = formDao.add(new Form(null,request.getParameter("formName"), request.getParameter("description"), request.getParameter("submissionEmail"), request.getParameter("submissionMethod"), request.getParameter("action"), Integer.parseInt(request.getParameter("resettable")), Integer.parseInt(request.getParameter("fileUpload"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Form", id); }
                        Database.updateCount("Form", 1);
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Added Form Successfully!");
                        break;
                    case 2: //update            
                        formDao.update(new Form(Integer.parseInt(request.getParameter("formId")), request.getParameter("formName"), request.getParameter("description"), request.getParameter("submissionEmail"), request.getParameter("submissionMethod"), request.getParameter("action"), Integer.parseInt(request.getParameter("resettable")), Integer.parseInt(request.getParameter("fileUpload"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Form", Integer.parseInt(request.getParameter("formId")));
                        response.sendRedirect("admin/FormUI.jsp?id=" + request.getParameter("formId") + "&SuccessMsg=Updated Form Successfully!");
                        break;
                    case 3:  //delete
                        formDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Form", request.getParameter("id"));
                        Database.updateCount("Form", -1);
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Deleted Form Successfully!");
                        break;
                    case 4:  //remove all records
                        formDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Form");
                        response.sendRedirect("admin/FormUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error editing Form, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error adding Form.");   break; //create
                    case 2:  response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error editing Form.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error deleting Form."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Error clearing Form."); break; //clear                          
                    default: response.sendRedirect("admin/FormUI.jsp?ErrorMsg=Unknown Error Form, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("form_field"))
        {   
            try
            {    
                FormFieldDao formFieldDao = (FormFieldDao) this.getServletContext().getAttribute("formFieldDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = formFieldDao.add(new FormField(null,request.getParameter("fieldName"), request.getParameter("label"), request.getParameter("errorText"), request.getParameter("validationRegex"), Integer.parseInt(request.getParameter("rank")), request.getParameter("defaultValue"), request.getParameter("options"), request.getParameter("groupName"), Integer.parseInt(request.getParameter("optional")), Integer.parseInt(request.getParameter("formFieldTypeId")), Integer.parseInt(request.getParameter("formId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormField", id); }
                        
                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Added FormField Successfully!");
                        break;
                    case 2: //update            
                        formFieldDao.update(new FormField(Integer.parseInt(request.getParameter("formFieldId")), request.getParameter("fieldName"), request.getParameter("label"), request.getParameter("errorText"), request.getParameter("validationRegex"), Integer.parseInt(request.getParameter("rank")), request.getParameter("defaultValue"), request.getParameter("options"), request.getParameter("groupName"), Integer.parseInt(request.getParameter("optional")), Integer.parseInt(request.getParameter("formFieldTypeId")), Integer.parseInt(request.getParameter("formId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormField", Integer.parseInt(request.getParameter("formFieldId")));
                        response.sendRedirect("admin/FormFieldUI.jsp?id=" + request.getParameter("formFieldId") + "&SuccessMsg=Updated FormField Successfully!");
                        break;
                    case 3:  //delete
                        formFieldDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormField", request.getParameter("id"));
                        
                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Deleted FormField Successfully!");
                        break;
                    case 4:  //remove all records
                        formFieldDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormField");
                        response.sendRedirect("admin/FormFieldUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error editing FormField, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error adding FormField.");   break; //create
                    case 2:  response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error editing FormField.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error deleting FormField."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Error clearing FormField."); break; //clear                          
                    default: response.sendRedirect("admin/FormFieldUI.jsp?ErrorMsg=Unknown Error FormField, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("form_field_type"))
        {   
            try
            {    
                FormFieldTypeDao formFieldTypeDao = (FormFieldTypeDao) this.getServletContext().getAttribute("formFieldTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = formFieldTypeDao.add(new FormFieldType(null,request.getParameter("typeName"), request.getParameter("inputType")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormFieldType", id); }
                        
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Added FormFieldType Successfully!");
                        break;
                    case 2: //update            
                        formFieldTypeDao.update(new FormFieldType(Integer.parseInt(request.getParameter("formFieldTypeId")), request.getParameter("typeName"), request.getParameter("inputType")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormFieldType", Integer.parseInt(request.getParameter("formFieldTypeId")));
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?id=" + request.getParameter("formFieldTypeId") + "&SuccessMsg=Updated FormFieldType Successfully!");
                        break;
                    case 3:  //delete
                        formFieldTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormFieldType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Deleted FormFieldType Successfully!");
                        break;
                    case 4:  //remove all records
                        formFieldTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "FormFieldType");
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error editing FormFieldType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error adding FormFieldType.");   break; //create
                    case 2:  response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error editing FormFieldType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error deleting FormFieldType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Error clearing FormFieldType."); break; //clear                          
                    default: response.sendRedirect("admin/FormFieldTypeUI.jsp?ErrorMsg=Unknown Error FormFieldType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("image_type"))
        {   
            try
            {    
                ImageTypeDao imageTypeDao = (ImageTypeDao) this.getServletContext().getAttribute("imageTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = imageTypeDao.add(new ImageType(null,request.getParameter("typeName"), request.getParameter("description")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ImageType", id); }
                        
                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Added ImageType Successfully!");
                        break;
                    case 2: //update            
                        imageTypeDao.update(new ImageType(Integer.parseInt(request.getParameter("imageTypeId")), request.getParameter("typeName"), request.getParameter("description")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ImageType", Integer.parseInt(request.getParameter("imageTypeId")));
                        response.sendRedirect("admin/ImageTypeUI.jsp?id=" + request.getParameter("imageTypeId") + "&SuccessMsg=Updated ImageType Successfully!");
                        break;
                    case 3:  //delete
                        imageTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ImageType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Deleted ImageType Successfully!");
                        break;
                    case 4:  //remove all records
                        imageTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ImageType");
                        response.sendRedirect("admin/ImageTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error editing ImageType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error adding ImageType.");   break; //create
                    case 2:  response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error editing ImageType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error deleting ImageType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Error clearing ImageType."); break; //clear                          
                    default: response.sendRedirect("admin/ImageTypeUI.jsp?ErrorMsg=Unknown Error ImageType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item"))
        {   
            try
            {    
                ItemDao itemDao = (ItemDao) this.getServletContext().getAttribute("itemDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemDao.add(new Item(null,request.getParameter("itemName"), request.getParameter("description"), Double.parseDouble(request.getParameter("listPrice")), Double.parseDouble(request.getParameter("price")), request.getParameter("shortDescription"), Integer.parseInt(request.getParameter("adjustment")), request.getParameter("sku"), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("itemStatus")), request.getParameter("locale"), Integer.parseInt(request.getParameter("itemTypeId")), Integer.parseInt(request.getParameter("itemBrandId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Item", id); }
                        Database.updateCount("Item", 1);
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Added Item Successfully!");
                        break;
                    case 2: //update            
                        itemDao.update(new Item(Integer.parseInt(request.getParameter("itemId")), request.getParameter("itemName"), request.getParameter("description"), Double.parseDouble(request.getParameter("listPrice")), Double.parseDouble(request.getParameter("price")), request.getParameter("shortDescription"), Integer.parseInt(request.getParameter("adjustment")), request.getParameter("sku"), Integer.parseInt(request.getParameter("ratingSum")), Integer.parseInt(request.getParameter("voteCount")), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("itemStatus")), request.getParameter("locale"), Integer.parseInt(request.getParameter("itemTypeId")), Integer.parseInt(request.getParameter("itemBrandId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Item", Integer.parseInt(request.getParameter("itemId")));
                        response.sendRedirect("admin/ItemUI.jsp?id=" + request.getParameter("itemId") + "&SuccessMsg=Updated Item Successfully!");
                        break;
                    case 3:  //delete
                        itemDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Item", request.getParameter("id"));
                        Database.updateCount("Item", -1);
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Deleted Item Successfully!");
                        break;
                    case 4:  //remove all records
                        itemDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Item");
                        response.sendRedirect("admin/ItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    case 5:  //testing the Dao                        
                        ArrayList<Item> items = itemDao.findAll(null, null);
                        itemDao.find(2);
                        itemDao.findByColumn(Item.PROP_ITEM_NAME,"Sand", null, null);
                        break;
                    default:
                        response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error editing Item, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error adding Item.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error editing Item.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error deleting Item."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Error clearing Item."); break; //clear                          
                    default: response.sendRedirect("admin/ItemUI.jsp?ErrorMsg=Unknown Error Item, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_attribute"))
        {   
            try
            {    
                ItemAttributeDao itemAttributeDao = (ItemAttributeDao) this.getServletContext().getAttribute("itemAttributeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemAttributeDao.add(new ItemAttribute(null,request.getParameter("key"), request.getParameter("value"), request.getParameter("locale"), Integer.parseInt(request.getParameter("itemAttributeTypeId")), Integer.parseInt(request.getParameter("itemId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttribute", id); }
                        
                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Added ItemAttribute Successfully!");
                        break;
                    case 2: //update            
                        itemAttributeDao.update(new ItemAttribute(Integer.parseInt(request.getParameter("itemAttributeId")), request.getParameter("key"), request.getParameter("value"), request.getParameter("locale"), Integer.parseInt(request.getParameter("itemAttributeTypeId")), Integer.parseInt(request.getParameter("itemId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttribute", Integer.parseInt(request.getParameter("itemAttributeId")));
                        response.sendRedirect("admin/ItemAttributeUI.jsp?id=" + request.getParameter("itemAttributeId") + "&SuccessMsg=Updated ItemAttribute Successfully!");
                        break;
                    case 3:  //delete
                        itemAttributeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttribute", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Deleted ItemAttribute Successfully!");
                        break;
                    case 4:  //remove all records
                        itemAttributeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttribute");
                        response.sendRedirect("admin/ItemAttributeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error editing ItemAttribute, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error adding ItemAttribute.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error editing ItemAttribute.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error deleting ItemAttribute."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Error clearing ItemAttribute."); break; //clear                          
                    default: response.sendRedirect("admin/ItemAttributeUI.jsp?ErrorMsg=Unknown Error ItemAttribute, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_attribute_type"))
        {   
            try
            {    
                ItemAttributeTypeDao itemAttributeTypeDao = (ItemAttributeTypeDao) this.getServletContext().getAttribute("itemAttributeTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemAttributeTypeDao.add(new ItemAttributeType(null,request.getParameter("attributeName"), request.getParameter("description")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttributeType", id); }
                        
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?SuccessMsg=Added ItemAttributeType Successfully!");
                        break;
                    case 2: //update            
                        itemAttributeTypeDao.update(new ItemAttributeType(Integer.parseInt(request.getParameter("itemAttributeTypeId")), request.getParameter("attributeName"), request.getParameter("description")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttributeType", Integer.parseInt(request.getParameter("itemAttributeTypeId")));
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?id=" + request.getParameter("itemAttributeTypeId") + "&SuccessMsg=Updated ItemAttributeType Successfully!");
                        break;
                    case 3:  //delete
                        itemAttributeTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttributeType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?SuccessMsg=Deleted ItemAttributeType Successfully!");
                        break;
                    case 4:  //remove all records
                        itemAttributeTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAttributeType");
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error editing ItemAttributeType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error adding ItemAttributeType.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error editing ItemAttributeType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error deleting ItemAttributeType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Error clearing ItemAttributeType."); break; //clear                          
                    default: response.sendRedirect("admin/ItemAttributeTypeUI.jsp?ErrorMsg=Unknown Error ItemAttributeType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_availability"))
        {   
            try
            {    
                ItemAvailabilityDao itemAvailabilityDao = (ItemAvailabilityDao) this.getServletContext().getAttribute("itemAvailabilityDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemAvailabilityDao.add(new ItemAvailability(null,request.getParameter("type")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAvailability", id); }
                        
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Added ItemAvailability Successfully!");
                        break;
                    case 2: //update            
                        itemAvailabilityDao.update(new ItemAvailability(Integer.parseInt(request.getParameter("itemAvailabilityId")), request.getParameter("type")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAvailability", Integer.parseInt(request.getParameter("itemAvailabilityId")));
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?id=" + request.getParameter("itemAvailabilityId") + "&SuccessMsg=Updated ItemAvailability Successfully!");
                        break;
                    case 3:  //delete
                        itemAvailabilityDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAvailability", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Deleted ItemAvailability Successfully!");
                        break;
                    case 4:  //remove all records
                        itemAvailabilityDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemAvailability");
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error editing ItemAvailability, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error adding ItemAvailability.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error editing ItemAvailability.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error deleting ItemAvailability."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Error clearing ItemAvailability."); break; //clear                          
                    default: response.sendRedirect("admin/ItemAvailabilityUI.jsp?ErrorMsg=Unknown Error ItemAvailability, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_brand"))
        {   
            try
            {    
                ItemBrandDao itemBrandDao = (ItemBrandDao) this.getServletContext().getAttribute("itemBrandDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemBrandDao.add(new ItemBrand(null,request.getParameter("brandName"), request.getParameter("description")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemBrand", id); }
                        Database.updateCount("ItemBrand", 1);
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Added ItemBrand Successfully!");
                        break;
                    case 2: //update            
                        itemBrandDao.update(new ItemBrand(Integer.parseInt(request.getParameter("itemBrandId")), request.getParameter("brandName"), request.getParameter("description")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemBrand", Integer.parseInt(request.getParameter("itemBrandId")));
                        response.sendRedirect("admin/ItemBrandUI.jsp?id=" + request.getParameter("itemBrandId") + "&SuccessMsg=Updated ItemBrand Successfully!");
                        break;
                    case 3:  //delete
                        itemBrandDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemBrand", request.getParameter("id"));
                        Database.updateCount("ItemBrand", -1);
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Deleted ItemBrand Successfully!");
                        break;
                    case 4:  //remove all records
                        itemBrandDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemBrand");
                        response.sendRedirect("admin/ItemBrandUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error editing ItemBrand, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error adding ItemBrand.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error editing ItemBrand.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error deleting ItemBrand."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Error clearing ItemBrand."); break; //clear                          
                    default: response.sendRedirect("admin/ItemBrandUI.jsp?ErrorMsg=Unknown Error ItemBrand, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_category"))
        {   
            try
            {    
                ItemCategoryDao itemCategoryDao = (ItemCategoryDao) this.getServletContext().getAttribute("itemCategoryDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemCategoryDao.add(new ItemCategory(null,Integer.parseInt(request.getParameter("categoryId")), Integer.parseInt(request.getParameter("itemId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemCategory", id); }
                        
                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Added ItemCategory Successfully!");
                        break;
                    case 2: //update            
                        itemCategoryDao.update(new ItemCategory(Integer.parseInt(request.getParameter("itemCategoryId")), Integer.parseInt(request.getParameter("categoryId")), Integer.parseInt(request.getParameter("itemId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemCategory", Integer.parseInt(request.getParameter("itemCategoryId")));
                        response.sendRedirect("admin/ItemCategoryUI.jsp?id=" + request.getParameter("itemCategoryId") + "&SuccessMsg=Updated ItemCategory Successfully!");
                        break;
                    case 3:  //delete
                        itemCategoryDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemCategory", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Deleted ItemCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        itemCategoryDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemCategory");
                        response.sendRedirect("admin/ItemCategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error editing ItemCategory, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error adding ItemCategory.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error editing ItemCategory.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error deleting ItemCategory."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Error clearing ItemCategory."); break; //clear                          
                    default: response.sendRedirect("admin/ItemCategoryUI.jsp?ErrorMsg=Unknown Error ItemCategory, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_discount"))
        {   
            try
            {    
                ItemDiscountDao itemDiscountDao = (ItemDiscountDao) this.getServletContext().getAttribute("itemDiscountDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemDiscountDao.add(new ItemDiscount(null,Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("discountId")), Integer.parseInt(request.getParameter("applyToOptions"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemDiscount", id); }
                        
                        response.sendRedirect("admin/ItemDiscountUI.jsp?SuccessMsg=Added ItemDiscount Successfully!");
                        break;
                    case 2: //update            
                        itemDiscountDao.update(new ItemDiscount(Integer.parseInt(request.getParameter("itemDiscountId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("discountId")), Integer.parseInt(request.getParameter("applyToOptions"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemDiscount", Integer.parseInt(request.getParameter("itemDiscountId")));
                        response.sendRedirect("admin/ItemDiscountUI.jsp?id=" + request.getParameter("itemDiscountId") + "&SuccessMsg=Updated ItemDiscount Successfully!");
                        break;
                    case 3:  //delete
                        itemDiscountDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemDiscount", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemDiscountUI.jsp?SuccessMsg=Deleted ItemDiscount Successfully!");
                        break;
                    case 4:  //remove all records
                        itemDiscountDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemDiscount");
                        response.sendRedirect("admin/ItemDiscountUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error editing ItemDiscount, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error adding ItemDiscount.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error editing ItemDiscount.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error deleting ItemDiscount."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Error clearing ItemDiscount."); break; //clear                          
                    default: response.sendRedirect("admin/ItemDiscountUI.jsp?ErrorMsg=Unknown Error ItemDiscount, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_file"))
        {   
            try
            {    
                ItemFileDao itemFileDao = (ItemFileDao) this.getServletContext().getAttribute("itemFileDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemFileDao.add(new ItemFile(null,request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label"), Integer.parseInt(request.getParameter("hidden")), Integer.parseInt(request.getParameter("itemId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemFile", id); }
                        
                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Added ItemFile Successfully!");
                        break;
                    case 2: //update            
                        itemFileDao.update(new ItemFile(Integer.parseInt(request.getParameter("itemFileId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label"), Integer.parseInt(request.getParameter("hidden")), Integer.parseInt(request.getParameter("itemId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemFile", Integer.parseInt(request.getParameter("itemFileId")));
                        response.sendRedirect("admin/ItemFileUI.jsp?id=" + request.getParameter("itemFileId") + "&SuccessMsg=Updated ItemFile Successfully!");
                        break;
                    case 3:  //delete
                        itemFileDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemFile", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Deleted ItemFile Successfully!");
                        break;
                    case 4:  //remove all records
                        itemFileDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemFile");
                        response.sendRedirect("admin/ItemFileUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error editing ItemFile, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error adding ItemFile.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error editing ItemFile.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error deleting ItemFile."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Error clearing ItemFile."); break; //clear                          
                    default: response.sendRedirect("admin/ItemFileUI.jsp?ErrorMsg=Unknown Error ItemFile, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_image"))
        {   
            try
            {    
                ItemImageDao itemImageDao = (ItemImageDao) this.getServletContext().getAttribute("itemImageDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemImageDao.add(new ItemImage(null,request.getParameter("imageName"), request.getParameter("thumbnailName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("itemId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemImage", id); }
                        
                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Added ItemImage Successfully!");
                        break;
                    case 2: //update            
                        itemImageDao.update(new ItemImage(Integer.parseInt(request.getParameter("itemImageId")), request.getParameter("imageName"), request.getParameter("thumbnailName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("itemId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemImage", Integer.parseInt(request.getParameter("itemImageId")));
                        response.sendRedirect("admin/ItemImageUI.jsp?id=" + request.getParameter("itemImageId") + "&SuccessMsg=Updated ItemImage Successfully!");
                        break;
                    case 3:  //delete
                        itemImageDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemImage", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Deleted ItemImage Successfully!");
                        break;
                    case 4:  //remove all records
                        itemImageDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemImage");
                        response.sendRedirect("admin/ItemImageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error editing ItemImage, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error adding ItemImage.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error editing ItemImage.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error deleting ItemImage."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Error clearing ItemImage."); break; //clear                          
                    default: response.sendRedirect("admin/ItemImageUI.jsp?ErrorMsg=Unknown Error ItemImage, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_location"))
        {   
            try
            {    
                ItemLocationDao itemLocationDao = (ItemLocationDao) this.getServletContext().getAttribute("itemLocationDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemLocationDao.add(new ItemLocation(null,request.getParameter("latitude"), request.getParameter("longitude"), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("addressId")), Integer.parseInt(request.getParameter("contactId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemLocation", id); }
                        
                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Added ItemLocation Successfully!");
                        break;
                    case 2: //update            
                        itemLocationDao.update(new ItemLocation(Integer.parseInt(request.getParameter("itemLocationId")), request.getParameter("latitude"), request.getParameter("longitude"), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("addressId")), Integer.parseInt(request.getParameter("contactId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemLocation", Integer.parseInt(request.getParameter("itemLocationId")));
                        response.sendRedirect("admin/ItemLocationUI.jsp?id=" + request.getParameter("itemLocationId") + "&SuccessMsg=Updated ItemLocation Successfully!");
                        break;
                    case 3:  //delete
                        itemLocationDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemLocation", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Deleted ItemLocation Successfully!");
                        break;
                    case 4:  //remove all records
                        itemLocationDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemLocation");
                        response.sendRedirect("admin/ItemLocationUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error editing ItemLocation, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error adding ItemLocation.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error editing ItemLocation.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error deleting ItemLocation."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Error clearing ItemLocation."); break; //clear                          
                    default: response.sendRedirect("admin/ItemLocationUI.jsp?ErrorMsg=Unknown Error ItemLocation, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_option"))
        {   
            try
            {    
                ItemOptionDao itemOptionDao = (ItemOptionDao) this.getServletContext().getAttribute("itemOptionDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemOptionDao.add(new ItemOption(null,request.getParameter("optionName"), request.getParameter("description")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemOption", id); }
                        Database.updateCount("ItemOption", 1);
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Added ItemOption Successfully!");
                        break;
                    case 2: //update            
                        itemOptionDao.update(new ItemOption(Integer.parseInt(request.getParameter("itemOptionId")), request.getParameter("optionName"), request.getParameter("description")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemOption", Integer.parseInt(request.getParameter("itemOptionId")));
                        response.sendRedirect("admin/ItemOptionUI.jsp?id=" + request.getParameter("itemOptionId") + "&SuccessMsg=Updated ItemOption Successfully!");
                        break;
                    case 3:  //delete
                        itemOptionDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemOption", request.getParameter("id"));
                        Database.updateCount("ItemOption", -1);
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Deleted ItemOption Successfully!");
                        break;
                    case 4:  //remove all records
                        itemOptionDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemOption");
                        response.sendRedirect("admin/ItemOptionUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error editing ItemOption, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error adding ItemOption.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error editing ItemOption.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error deleting ItemOption."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Error clearing ItemOption."); break; //clear                          
                    default: response.sendRedirect("admin/ItemOptionUI.jsp?ErrorMsg=Unknown Error ItemOption, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_review"))
        {   
            try
            {    
                ItemReviewDao itemReviewDao = (ItemReviewDao) this.getServletContext().getAttribute("itemReviewDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemReviewDao.add(new ItemReview(null,Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("rating")), Integer.parseInt(request.getParameter("helpfulYes")), Integer.parseInt(request.getParameter("helpfulNo"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemReview", id); }
                        
                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Added ItemReview Successfully!");
                        break;
                    case 2: //update            
                        itemReviewDao.update(new ItemReview(Integer.parseInt(request.getParameter("itemReviewId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("rating")), Integer.parseInt(request.getParameter("helpfulYes")), Integer.parseInt(request.getParameter("helpfulNo"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemReview", Integer.parseInt(request.getParameter("itemReviewId")));
                        response.sendRedirect("admin/ItemReviewUI.jsp?id=" + request.getParameter("itemReviewId") + "&SuccessMsg=Updated ItemReview Successfully!");
                        break;
                    case 3:  //delete
                        itemReviewDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemReview", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Deleted ItemReview Successfully!");
                        break;
                    case 4:  //remove all records
                        itemReviewDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemReview");
                        response.sendRedirect("admin/ItemReviewUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error editing ItemReview, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error adding ItemReview.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error editing ItemReview.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error deleting ItemReview."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Error clearing ItemReview."); break; //clear                          
                    default: response.sendRedirect("admin/ItemReviewUI.jsp?ErrorMsg=Unknown Error ItemReview, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("item_type"))
        {   
            try
            {    
                ItemTypeDao itemTypeDao = (ItemTypeDao) this.getServletContext().getAttribute("itemTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = itemTypeDao.add(new ItemType(null,request.getParameter("typeName"), Integer.parseInt(request.getParameter("rank"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemType", id); }
                        
                        response.sendRedirect("admin/ItemTypeUI.jsp?SuccessMsg=Added ItemType Successfully!");
                        break;
                    case 2: //update            
                        itemTypeDao.update(new ItemType(Integer.parseInt(request.getParameter("itemTypeId")), request.getParameter("typeName"), Integer.parseInt(request.getParameter("rank"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemType", Integer.parseInt(request.getParameter("itemTypeId")));
                        response.sendRedirect("admin/ItemTypeUI.jsp?id=" + request.getParameter("itemTypeId") + "&SuccessMsg=Updated ItemType Successfully!");
                        break;
                    case 3:  //delete
                        itemTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ItemTypeUI.jsp?SuccessMsg=Deleted ItemType Successfully!");
                        break;
                    case 4:  //remove all records
                        itemTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ItemType");
                        response.sendRedirect("admin/ItemTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error editing ItemType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error adding ItemType.");   break; //create
                    case 2:  response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error editing ItemType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error deleting ItemType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Error clearing ItemType."); break; //clear                          
                    default: response.sendRedirect("admin/ItemTypeUI.jsp?ErrorMsg=Unknown Error ItemType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("knowledge_base"))
        {   
            try
            {    
                KnowledgeBaseDao knowledgeBaseDao = (KnowledgeBaseDao) this.getServletContext().getAttribute("knowledgeBaseDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = knowledgeBaseDao.add(new KnowledgeBase(null,request.getParameter("knowledgeBaseName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), operatingDateFormat.parse(request.getParameter("lastModified")), Integer.parseInt(request.getParameter("latestTopic")), Integer.parseInt(request.getParameter("latestPost"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "KnowledgeBase", id); }
                        
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?SuccessMsg=Added KnowledgeBase Successfully!");
                        break;
                    case 2: //update            
                        knowledgeBaseDao.update(new KnowledgeBase(Integer.parseInt(request.getParameter("knowledgeBaseId")), request.getParameter("knowledgeBaseName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), operatingDateFormat.parse(request.getParameter("lastModified")), Integer.parseInt(request.getParameter("latestTopic")), Integer.parseInt(request.getParameter("latestPost"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "KnowledgeBase", Integer.parseInt(request.getParameter("knowledgeBaseId")));
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?id=" + request.getParameter("knowledgeBaseId") + "&SuccessMsg=Updated KnowledgeBase Successfully!");
                        break;
                    case 3:  //delete
                        knowledgeBaseDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "KnowledgeBase", request.getParameter("id"));
                        
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?SuccessMsg=Deleted KnowledgeBase Successfully!");
                        break;
                    case 4:  //remove all records
                        knowledgeBaseDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "KnowledgeBase");
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error editing KnowledgeBase, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error adding KnowledgeBase.");   break; //create
                    case 2:  response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error editing KnowledgeBase.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error deleting KnowledgeBase."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Error clearing KnowledgeBase."); break; //clear                          
                    default: response.sendRedirect("admin/KnowledgeBaseUI.jsp?ErrorMsg=Unknown Error KnowledgeBase, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("locale"))
        {   
            try
            {    
                LocaleDao localeDao = (LocaleDao) this.getServletContext().getAttribute("localeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = localeDao.add(new Locale(null,request.getParameter("localeString"), request.getParameter("localeCharacterSet")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Locale", id); }
                        
                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Added Locale Successfully!");
                        break;
                    case 2: //update            
                        localeDao.update(new Locale(Integer.parseInt(request.getParameter("localeId")), request.getParameter("localeString"), request.getParameter("localeCharacterSet")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Locale", Integer.parseInt(request.getParameter("localeId")));
                        response.sendRedirect("admin/LocaleUI.jsp?id=" + request.getParameter("localeId") + "&SuccessMsg=Updated Locale Successfully!");
                        break;
                    case 3:  //delete
                        localeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Locale", request.getParameter("id"));
                        
                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Deleted Locale Successfully!");
                        break;
                    case 4:  //remove all records
                        localeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Locale");
                        response.sendRedirect("admin/LocaleUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error editing Locale, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error adding Locale.");   break; //create
                    case 2:  response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error editing Locale.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error deleting Locale."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Error clearing Locale."); break; //clear                          
                    default: response.sendRedirect("admin/LocaleUI.jsp?ErrorMsg=Unknown Error Locale, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("localized_string"))
        {   
            try
            {    
                LocalizedStringDao localizedStringDao = (LocalizedStringDao) this.getServletContext().getAttribute("localizedStringDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = localizedStringDao.add(new LocalizedString(null,Integer.parseInt(request.getParameter("locale")), request.getParameter("stringValue"), Integer.parseInt(request.getParameter("textStringId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "LocalizedString", id); }
                        
                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Added LocalizedString Successfully!");
                        break;
                    case 2: //update            
                        localizedStringDao.update(new LocalizedString(Integer.parseInt(request.getParameter("localizedStringId")), Integer.parseInt(request.getParameter("locale")), request.getParameter("stringValue"), Integer.parseInt(request.getParameter("textStringId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "LocalizedString", Integer.parseInt(request.getParameter("localizedStringId")));
                        response.sendRedirect("admin/LocalizedStringUI.jsp?id=" + request.getParameter("localizedStringId") + "&SuccessMsg=Updated LocalizedString Successfully!");
                        break;
                    case 3:  //delete
                        localizedStringDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "LocalizedString", request.getParameter("id"));
                        
                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Deleted LocalizedString Successfully!");
                        break;
                    case 4:  //remove all records
                        localizedStringDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "LocalizedString");
                        response.sendRedirect("admin/LocalizedStringUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error editing LocalizedString, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error adding LocalizedString.");   break; //create
                    case 2:  response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error editing LocalizedString.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error deleting LocalizedString."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Error clearing LocalizedString."); break; //clear                          
                    default: response.sendRedirect("admin/LocalizedStringUI.jsp?ErrorMsg=Unknown Error LocalizedString, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("mailinglist"))
        {   
            try
            {    
                MailinglistDao mailinglistDao = (MailinglistDao) this.getServletContext().getAttribute("mailinglistDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = mailinglistDao.add(new Mailinglist(null,request.getParameter("fullName"), request.getParameter("email"), Integer.parseInt(request.getParameter("listStatus")), Integer.parseInt(request.getParameter("userId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Mailinglist", id); }
                        
                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Added Mailinglist Successfully!");
                        break;
                    case 2: //update            
                        mailinglistDao.update(new Mailinglist(Integer.parseInt(request.getParameter("mailinglistId")), request.getParameter("fullName"), request.getParameter("email"), Integer.parseInt(request.getParameter("listStatus")), Integer.parseInt(request.getParameter("userId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Mailinglist", Integer.parseInt(request.getParameter("mailinglistId")));
                        response.sendRedirect("admin/MailinglistUI.jsp?id=" + request.getParameter("mailinglistId") + "&SuccessMsg=Updated Mailinglist Successfully!");
                        break;
                    case 3:  //delete
                        mailinglistDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Mailinglist", request.getParameter("id"));
                        
                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Deleted Mailinglist Successfully!");
                        break;
                    case 4:  //remove all records
                        mailinglistDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Mailinglist");
                        response.sendRedirect("admin/MailinglistUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error editing Mailinglist, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error adding Mailinglist.");   break; //create
                    case 2:  response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error editing Mailinglist.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error deleting Mailinglist."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Error clearing Mailinglist."); break; //clear                          
                    default: response.sendRedirect("admin/MailinglistUI.jsp?ErrorMsg=Unknown Error Mailinglist, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("meta_tag"))
        {   
            try
            {    
                MetaTagDao metaTagDao = (MetaTagDao) this.getServletContext().getAttribute("metaTagDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = metaTagDao.add(new MetaTag(null,request.getParameter("title"), request.getParameter("description"), request.getParameter("keywords")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "MetaTag", id); }
                        
                        response.sendRedirect("admin/MetaTagUI.jsp?SuccessMsg=Added MetaTag Successfully!");
                        break;
                    case 2: //update            
                        metaTagDao.update(new MetaTag(Integer.parseInt(request.getParameter("metaTagId")), request.getParameter("title"), request.getParameter("description"), request.getParameter("keywords")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "MetaTag", Integer.parseInt(request.getParameter("metaTagId")));
                        response.sendRedirect("admin/MetaTagUI.jsp?id=" + request.getParameter("metaTagId") + "&SuccessMsg=Updated MetaTag Successfully!");
                        break;
                    case 3:  //delete
                        metaTagDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "MetaTag", request.getParameter("id"));
                        
                        response.sendRedirect("admin/MetaTagUI.jsp?SuccessMsg=Deleted MetaTag Successfully!");
                        break;
                    case 4:  //remove all records
                        metaTagDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "MetaTag");
                        response.sendRedirect("admin/MetaTagUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error editing MetaTag, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error adding MetaTag.");   break; //create
                    case 2:  response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error editing MetaTag.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error deleting MetaTag."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Error clearing MetaTag."); break; //clear                          
                    default: response.sendRedirect("admin/MetaTagUI.jsp?ErrorMsg=Unknown Error MetaTag, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("option_availability"))
        {   
            try
            {    
                OptionAvailabilityDao optionAvailabilityDao = (OptionAvailabilityDao) this.getServletContext().getAttribute("optionAvailabilityDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = optionAvailabilityDao.add(new OptionAvailability(null,Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemOptionId")), Integer.parseInt(request.getParameter("itemAvailabilityId")), Integer.parseInt(request.getParameter("availableQuantity")), Double.parseDouble(request.getParameter("price")), operatingDateFormat.parse(request.getParameter("availableFrom")), operatingDateFormat.parse(request.getParameter("availableTo")), Integer.parseInt(request.getParameter("maximumQuantity"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "OptionAvailability", id); }
                        
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?SuccessMsg=Added OptionAvailability Successfully!");
                        break;
                    case 2: //update            
                        optionAvailabilityDao.update(new OptionAvailability(Integer.parseInt(request.getParameter("optionAvailabilityId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("itemOptionId")), Integer.parseInt(request.getParameter("itemAvailabilityId")), Integer.parseInt(request.getParameter("availableQuantity")), Double.parseDouble(request.getParameter("price")), operatingDateFormat.parse(request.getParameter("availableFrom")), operatingDateFormat.parse(request.getParameter("availableTo")), Integer.parseInt(request.getParameter("maximumQuantity"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "OptionAvailability", Integer.parseInt(request.getParameter("optionAvailabilityId")));
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?id=" + request.getParameter("optionAvailabilityId") + "&SuccessMsg=Updated OptionAvailability Successfully!");
                        break;
                    case 3:  //delete
                        optionAvailabilityDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "OptionAvailability", request.getParameter("id"));
                        
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?SuccessMsg=Deleted OptionAvailability Successfully!");
                        break;
                    case 4:  //remove all records
                        optionAvailabilityDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "OptionAvailability");
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error editing OptionAvailability, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error adding OptionAvailability.");   break; //create
                    case 2:  response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error editing OptionAvailability.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error deleting OptionAvailability."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Error clearing OptionAvailability."); break; //clear                          
                    default: response.sendRedirect("admin/OptionAvailabilityUI.jsp?ErrorMsg=Unknown Error OptionAvailability, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("order"))
        {   
            try
            {    
                OrderDao orderDao = (OrderDao) this.getServletContext().getAttribute("orderDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = orderDao.add(new Order(null,operatingDateFormat.parse(request.getParameter("orderDate")), operatingDateFormat.parse(request.getParameter("shipDate")), request.getParameter("paymentMethod"), request.getParameter("purchaseOrder"), request.getParameter("transactionId"), Double.parseDouble(request.getParameter("amountBilled")), request.getParameter("paymentStatus"), request.getParameter("pendingReason"), request.getParameter("paymentType"), Double.parseDouble(request.getParameter("transactionFee")), request.getParameter("currencyCode"), request.getParameter("payerId"), Double.parseDouble(request.getParameter("subtotalAmount")), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("taxAmount")), Double.parseDouble(request.getParameter("shippingAmount")), Double.parseDouble(request.getParameter("totalAmount")), Double.parseDouble(request.getParameter("refundAmount")), request.getParameter("notes"), Integer.parseInt(request.getParameter("orderStatus")), Integer.parseInt(request.getParameter("shippingId")), Integer.parseInt(request.getParameter("affiliateId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Order", id); }
                        Database.updateCount("Order", 1);
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Added Order Successfully!");
                        break;
                    case 2: //update            
                        orderDao.update(new Order(Integer.parseInt(request.getParameter("orderId")), operatingDateFormat.parse(request.getParameter("orderDate")), operatingDateFormat.parse(request.getParameter("shipDate")), request.getParameter("paymentMethod"), request.getParameter("purchaseOrder"), request.getParameter("transactionId"), Double.parseDouble(request.getParameter("amountBilled")), request.getParameter("paymentStatus"), request.getParameter("pendingReason"), request.getParameter("paymentType"), Double.parseDouble(request.getParameter("transactionFee")), request.getParameter("currencyCode"), request.getParameter("payerId"), Double.parseDouble(request.getParameter("subtotalAmount")), Double.parseDouble(request.getParameter("discountAmount")), Double.parseDouble(request.getParameter("taxAmount")), Double.parseDouble(request.getParameter("shippingAmount")), Double.parseDouble(request.getParameter("totalAmount")), Double.parseDouble(request.getParameter("refundAmount")), request.getParameter("notes"), Integer.parseInt(request.getParameter("orderStatus")), Integer.parseInt(request.getParameter("shippingId")), Integer.parseInt(request.getParameter("affiliateId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Order", Integer.parseInt(request.getParameter("orderId")));
                        response.sendRedirect("admin/OrderUI.jsp?id=" + request.getParameter("orderId") + "&SuccessMsg=Updated Order Successfully!");
                        break;
                    case 3:  //delete
                        orderDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Order", request.getParameter("id"));
                        Database.updateCount("Order", -1);
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Deleted Order Successfully!");
                        break;
                    case 4:  //remove all records
                        orderDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Order");
                        response.sendRedirect("admin/OrderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error editing Order, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error adding Order.");   break; //create
                    case 2:  response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error editing Order.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error deleting Order."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Error clearing Order."); break; //clear                          
                    default: response.sendRedirect("admin/OrderUI.jsp?ErrorMsg=Unknown Error Order, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("order_item"))
        {   
            try
            {    
                OrderItemDao orderItemDao = (OrderItemDao) this.getServletContext().getAttribute("orderItemDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = orderItemDao.add(new OrderItem(null,Integer.parseInt(request.getParameter("customerOrderId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("quantity")), request.getParameter("optionName"), Double.parseDouble(request.getParameter("unitPrice"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "OrderItem", id); }
                        
                        response.sendRedirect("admin/OrderItemUI.jsp?SuccessMsg=Added OrderItem Successfully!");
                        break;
                    case 2: //update            
                        orderItemDao.update(new OrderItem(Integer.parseInt(request.getParameter("orderItemId")), Integer.parseInt(request.getParameter("customerOrderId")), Integer.parseInt(request.getParameter("itemId")), Integer.parseInt(request.getParameter("quantity")), request.getParameter("optionName"), Double.parseDouble(request.getParameter("unitPrice"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "OrderItem", Integer.parseInt(request.getParameter("orderItemId")));
                        response.sendRedirect("admin/OrderItemUI.jsp?id=" + request.getParameter("orderItemId") + "&SuccessMsg=Updated OrderItem Successfully!");
                        break;
                    case 3:  //delete
                        orderItemDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "OrderItem", request.getParameter("id"));
                        
                        response.sendRedirect("admin/OrderItemUI.jsp?SuccessMsg=Deleted OrderItem Successfully!");
                        break;
                    case 4:  //remove all records
                        orderItemDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "OrderItem");
                        response.sendRedirect("admin/OrderItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error editing OrderItem, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error adding OrderItem.");   break; //create
                    case 2:  response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error editing OrderItem.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error deleting OrderItem."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Error clearing OrderItem."); break; //clear                          
                    default: response.sendRedirect("admin/OrderItemUI.jsp?ErrorMsg=Unknown Error OrderItem, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("page"))
        {   
            try
            {    
                PageDao pageDao = (PageDao) this.getServletContext().getAttribute("pageDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = pageDao.add(new Page(null,request.getParameter("pageName"), request.getParameter("content"), Integer.parseInt(request.getParameter("pageStatus")), Integer.parseInt(request.getParameter("formId")), Integer.parseInt(request.getParameter("sliderId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Page", id); }
                        Database.updateCount("Page", 1);
                        response.sendRedirect("admin/PageUI.jsp?SuccessMsg=Added Page Successfully!");
                        break;
                    case 2: //update            
                        pageDao.update(new Page(Integer.parseInt(request.getParameter("pageId")), request.getParameter("pageName"), request.getParameter("content"), Integer.parseInt(request.getParameter("pageStatus")), Integer.parseInt(request.getParameter("formId")), Integer.parseInt(request.getParameter("sliderId")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Page", Integer.parseInt(request.getParameter("pageId")));
                        response.sendRedirect("admin/PageUI.jsp?id=" + request.getParameter("pageId") + "&SuccessMsg=Updated Page Successfully!");
                        break;
                    case 3:  //delete
                        pageDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Page", request.getParameter("id"));
                        Database.updateCount("Page", -1);
                        response.sendRedirect("admin/PageUI.jsp?SuccessMsg=Deleted Page Successfully!");
                        break;
                    case 4:  //remove all records
                        pageDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Page");
                        response.sendRedirect("admin/PageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error editing Page, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error adding Page.");   break; //create
                    case 2:  response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error editing Page.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error deleting Page."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Error clearing Page."); break; //clear                          
                    default: response.sendRedirect("admin/PageUI.jsp?ErrorMsg=Unknown Error Page, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("page_template"))
        {   
            try
            {    
                PageTemplateDao pageTemplateDao = (PageTemplateDao) this.getServletContext().getAttribute("pageTemplateDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = pageTemplateDao.add(new PageTemplate(null,request.getParameter("name"), request.getParameter("markup")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "PageTemplate", id); }
                        
                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Added PageTemplate Successfully!");
                        break;
                    case 2: //update            
                        pageTemplateDao.update(new PageTemplate(Integer.parseInt(request.getParameter("pageTemplateId")), request.getParameter("name"), request.getParameter("markup")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "PageTemplate", Integer.parseInt(request.getParameter("pageTemplateId")));
                        response.sendRedirect("admin/PageTemplateUI.jsp?id=" + request.getParameter("pageTemplateId") + "&SuccessMsg=Updated PageTemplate Successfully!");
                        break;
                    case 3:  //delete
                        pageTemplateDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "PageTemplate", request.getParameter("id"));
                        
                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Deleted PageTemplate Successfully!");
                        break;
                    case 4:  //remove all records
                        pageTemplateDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "PageTemplate");
                        response.sendRedirect("admin/PageTemplateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error editing PageTemplate, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error adding PageTemplate.");   break; //create
                    case 2:  response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error editing PageTemplate.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error deleting PageTemplate."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Error clearing PageTemplate."); break; //clear                          
                    default: response.sendRedirect("admin/PageTemplateUI.jsp?ErrorMsg=Unknown Error PageTemplate, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("paypal"))
        {   
            try
            {    
                PaypalDao paypalDao = (PaypalDao) this.getServletContext().getAttribute("paypalDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = paypalDao.add(new Paypal(null,request.getParameter("payPalUrl"), request.getParameter("currencyCode"), request.getParameter("apiUsername"), request.getParameter("apiPassword"), request.getParameter("apiSignature"), request.getParameter("apiEndpoint"), Boolean.parseBoolean(request.getParameter("activeProfile")), request.getParameter("returnUrl"), request.getParameter("cancelUrl"), request.getParameter("paymentType"), request.getParameter("environment")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Paypal", id); }
                        
                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Added Paypal Successfully!");
                        break;
                    case 2: //update            
                        paypalDao.update(new Paypal(Integer.parseInt(request.getParameter("paypalId")), request.getParameter("payPalUrl"), request.getParameter("currencyCode"), request.getParameter("apiUsername"), request.getParameter("apiPassword"), request.getParameter("apiSignature"), request.getParameter("apiEndpoint"), Boolean.parseBoolean(request.getParameter("activeProfile")), request.getParameter("returnUrl"), request.getParameter("cancelUrl"), request.getParameter("paymentType"), request.getParameter("environment")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Paypal", Integer.parseInt(request.getParameter("paypalId")));
                        response.sendRedirect("admin/PaypalUI.jsp?id=" + request.getParameter("paypalId") + "&SuccessMsg=Updated Paypal Successfully!");
                        break;
                    case 3:  //delete
                        paypalDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Paypal", request.getParameter("id"));
                        
                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Deleted Paypal Successfully!");
                        break;
                    case 4:  //remove all records
                        paypalDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Paypal");
                        response.sendRedirect("admin/PaypalUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error editing Paypal, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error adding Paypal.");   break; //create
                    case 2:  response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error editing Paypal.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error deleting Paypal."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Error clearing Paypal."); break; //clear                          
                    default: response.sendRedirect("admin/PaypalUI.jsp?ErrorMsg=Unknown Error Paypal, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("post_category"))
        {   
            try
            {    
                PostCategoryDao postCategoryDao = (PostCategoryDao) this.getServletContext().getAttribute("postCategoryDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = postCategoryDao.add(new PostCategory(null,request.getParameter("categoryName")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "PostCategory", id); }
                        
                        response.sendRedirect("admin/PostCategoryUI.jsp?SuccessMsg=Added PostCategory Successfully!");
                        break;
                    case 2: //update            
                        postCategoryDao.update(new PostCategory(Integer.parseInt(request.getParameter("postCategoryId")), request.getParameter("categoryName")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "PostCategory", Integer.parseInt(request.getParameter("postCategoryId")));
                        response.sendRedirect("admin/PostCategoryUI.jsp?id=" + request.getParameter("postCategoryId") + "&SuccessMsg=Updated PostCategory Successfully!");
                        break;
                    case 3:  //delete
                        postCategoryDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "PostCategory", request.getParameter("id"));
                        
                        response.sendRedirect("admin/PostCategoryUI.jsp?SuccessMsg=Deleted PostCategory Successfully!");
                        break;
                    case 4:  //remove all records
                        postCategoryDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "PostCategory");
                        response.sendRedirect("admin/PostCategoryUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error editing PostCategory, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error adding PostCategory.");   break; //create
                    case 2:  response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error editing PostCategory.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error deleting PostCategory."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Error clearing PostCategory."); break; //clear                          
                    default: response.sendRedirect("admin/PostCategoryUI.jsp?ErrorMsg=Unknown Error PostCategory, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("recurring_payment"))
        {   
            try
            {    
                RecurringPaymentDao recurringPaymentDao = (RecurringPaymentDao) this.getServletContext().getAttribute("recurringPaymentDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = recurringPaymentDao.add(new RecurringPayment(null,Integer.parseInt(request.getParameter("cycleLength")), Integer.parseInt(request.getParameter("cyclePeriod")), Integer.parseInt(request.getParameter("totalCycles")), operatingDateFormat.parse(request.getParameter("startDate")), Integer.parseInt(request.getParameter("orderId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "RecurringPayment", id); }
                        
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?SuccessMsg=Added RecurringPayment Successfully!");
                        break;
                    case 2: //update            
                        recurringPaymentDao.update(new RecurringPayment(Integer.parseInt(request.getParameter("recurringPaymentId")), Integer.parseInt(request.getParameter("cycleLength")), Integer.parseInt(request.getParameter("cyclePeriod")), Integer.parseInt(request.getParameter("totalCycles")), operatingDateFormat.parse(request.getParameter("startDate")), Integer.parseInt(request.getParameter("orderId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "RecurringPayment", Integer.parseInt(request.getParameter("recurringPaymentId")));
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?id=" + request.getParameter("recurringPaymentId") + "&SuccessMsg=Updated RecurringPayment Successfully!");
                        break;
                    case 3:  //delete
                        recurringPaymentDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "RecurringPayment", request.getParameter("id"));
                        
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?SuccessMsg=Deleted RecurringPayment Successfully!");
                        break;
                    case 4:  //remove all records
                        recurringPaymentDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "RecurringPayment");
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error editing RecurringPayment, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error adding RecurringPayment.");   break; //create
                    case 2:  response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error editing RecurringPayment.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error deleting RecurringPayment."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Error clearing RecurringPayment."); break; //clear                          
                    default: response.sendRedirect("admin/RecurringPaymentUI.jsp?ErrorMsg=Unknown Error RecurringPayment, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("related_item"))
        {   
            try
            {    
                RelatedItemDao relatedItemDao = (RelatedItemDao) this.getServletContext().getAttribute("relatedItemDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = relatedItemDao.add(new RelatedItem(null,Integer.parseInt(request.getParameter("item1")), Integer.parseInt(request.getParameter("item2"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "RelatedItem", id); }
                        
                        response.sendRedirect("admin/RelatedItemUI.jsp?SuccessMsg=Added RelatedItem Successfully!");
                        break;
                    case 2: //update            
                        relatedItemDao.update(new RelatedItem(Integer.parseInt(request.getParameter("relatedItemId")), Integer.parseInt(request.getParameter("item1")), Integer.parseInt(request.getParameter("item2"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "RelatedItem", Integer.parseInt(request.getParameter("relatedItemId")));
                        response.sendRedirect("admin/RelatedItemUI.jsp?id=" + request.getParameter("relatedItemId") + "&SuccessMsg=Updated RelatedItem Successfully!");
                        break;
                    case 3:  //delete
                        relatedItemDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "RelatedItem", request.getParameter("id"));
                        
                        response.sendRedirect("admin/RelatedItemUI.jsp?SuccessMsg=Deleted RelatedItem Successfully!");
                        break;
                    case 4:  //remove all records
                        relatedItemDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "RelatedItem");
                        response.sendRedirect("admin/RelatedItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/RelatedItemUI.jsp?ErrorMsg=Error editing RelatedItem, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/RelatedItemUI.jsp?ErrorMsg=Error adding RelatedItem.");   break; //create
                    case 2:  response.sendRedirect("admin/RelatedItemUI.jsp?ErrorMsg=Error editing RelatedItem.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/RelatedItemUI.jsp?ErrorMsg=Error deleting RelatedItem."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/RelatedItemUI.jsp?ErrorMsg=Error clearing RelatedItem."); break; //clear                          
                    default: response.sendRedirect("admin/RelatedItemUI.jsp?ErrorMsg=Unknown Error RelatedItem, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("resource_type"))
        {   
            try
            {    
                ResourceTypeDao resourceTypeDao = (ResourceTypeDao) this.getServletContext().getAttribute("resourceTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = resourceTypeDao.add(new ResourceType(null,request.getParameter("typeName"), request.getParameter("typeValue")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceType", id); }
                        
                        response.sendRedirect("admin/ResourceTypeUI.jsp?SuccessMsg=Added ResourceType Successfully!");
                        break;
                    case 2: //update            
                        resourceTypeDao.update(new ResourceType(Integer.parseInt(request.getParameter("resourceTypeId")), request.getParameter("typeName"), request.getParameter("typeValue")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceType", Integer.parseInt(request.getParameter("resourceTypeId")));
                        response.sendRedirect("admin/ResourceTypeUI.jsp?id=" + request.getParameter("resourceTypeId") + "&SuccessMsg=Updated ResourceType Successfully!");
                        break;
                    case 3:  //delete
                        resourceTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ResourceTypeUI.jsp?SuccessMsg=Deleted ResourceType Successfully!");
                        break;
                    case 4:  //remove all records
                        resourceTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceType");
                        response.sendRedirect("admin/ResourceTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error editing ResourceType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error adding ResourceType.");   break; //create
                    case 2:  response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error editing ResourceType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error deleting ResourceType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Error clearing ResourceType."); break; //clear                          
                    default: response.sendRedirect("admin/ResourceTypeUI.jsp?ErrorMsg=Unknown Error ResourceType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("resource_url"))
        {   
            try
            {    
                ResourceUrlDao resourceUrlDao = (ResourceUrlDao) this.getServletContext().getAttribute("resourceUrlDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = resourceUrlDao.add(new ResourceUrl(null,request.getParameter("url"), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("resourceTypeId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceUrl", id); }
                        
                        response.sendRedirect("admin/ResourceUrlUI.jsp?SuccessMsg=Added ResourceUrl Successfully!");
                        break;
                    case 2: //update            
                        resourceUrlDao.update(new ResourceUrl(Integer.parseInt(request.getParameter("resourceUrlId")), request.getParameter("url"), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("resourceTypeId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceUrl", Integer.parseInt(request.getParameter("resourceUrlId")));
                        response.sendRedirect("admin/ResourceUrlUI.jsp?id=" + request.getParameter("resourceUrlId") + "&SuccessMsg=Updated ResourceUrl Successfully!");
                        break;
                    case 3:  //delete
                        resourceUrlDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceUrl", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ResourceUrlUI.jsp?SuccessMsg=Deleted ResourceUrl Successfully!");
                        break;
                    case 4:  //remove all records
                        resourceUrlDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ResourceUrl");
                        response.sendRedirect("admin/ResourceUrlUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error editing ResourceUrl, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error adding ResourceUrl.");   break; //create
                    case 2:  response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error editing ResourceUrl.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error deleting ResourceUrl."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Error clearing ResourceUrl."); break; //clear                          
                    default: response.sendRedirect("admin/ResourceUrlUI.jsp?ErrorMsg=Unknown Error ResourceUrl, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("return_request"))
        {   
            try
            {    
                ReturnRequestDao returnRequestDao = (ReturnRequestDao) this.getServletContext().getAttribute("returnRequestDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = returnRequestDao.add(new ReturnRequest(null,Integer.parseInt(request.getParameter("quantity")), operatingDateFormat.parse(request.getParameter("requestDate")), request.getParameter("returnReason"), request.getParameter("requestedAction"), request.getParameter("notes"), Integer.parseInt(request.getParameter("requestStatus")), Integer.parseInt(request.getParameter("orderItemId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ReturnRequest", id); }
                        
                        response.sendRedirect("admin/ReturnRequestUI.jsp?SuccessMsg=Added ReturnRequest Successfully!");
                        break;
                    case 2: //update            
                        returnRequestDao.update(new ReturnRequest(Integer.parseInt(request.getParameter("returnRequestId")), Integer.parseInt(request.getParameter("quantity")), operatingDateFormat.parse(request.getParameter("requestDate")), request.getParameter("returnReason"), request.getParameter("requestedAction"), request.getParameter("notes"), Integer.parseInt(request.getParameter("requestStatus")), Integer.parseInt(request.getParameter("orderItemId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ReturnRequest", Integer.parseInt(request.getParameter("returnRequestId")));
                        response.sendRedirect("admin/ReturnRequestUI.jsp?id=" + request.getParameter("returnRequestId") + "&SuccessMsg=Updated ReturnRequest Successfully!");
                        break;
                    case 3:  //delete
                        returnRequestDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ReturnRequest", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ReturnRequestUI.jsp?SuccessMsg=Deleted ReturnRequest Successfully!");
                        break;
                    case 4:  //remove all records
                        returnRequestDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ReturnRequest");
                        response.sendRedirect("admin/ReturnRequestUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error editing ReturnRequest, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error adding ReturnRequest.");   break; //create
                    case 2:  response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error editing ReturnRequest.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error deleting ReturnRequest."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Error clearing ReturnRequest."); break; //clear                          
                    default: response.sendRedirect("admin/ReturnRequestUI.jsp?ErrorMsg=Unknown Error ReturnRequest, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("service"))
        {   
            try
            {    
                ServiceDao serviceDao = (ServiceDao) this.getServletContext().getAttribute("serviceDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = serviceDao.add(new Service(null,request.getParameter("serviceName"), request.getParameter("description"), Integer.parseInt(request.getParameter("serviceStatus")), Integer.parseInt(request.getParameter("serviceChargeId")), Integer.parseInt(request.getParameter("serviceTypeId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Service", id); }
                        
                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Added Service Successfully!");
                        break;
                    case 2: //update            
                        serviceDao.update(new Service(Integer.parseInt(request.getParameter("serviceId")), request.getParameter("serviceName"), request.getParameter("description"), Integer.parseInt(request.getParameter("serviceStatus")), Integer.parseInt(request.getParameter("serviceChargeId")), Integer.parseInt(request.getParameter("serviceTypeId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Service", Integer.parseInt(request.getParameter("serviceId")));
                        response.sendRedirect("admin/ServiceUI.jsp?id=" + request.getParameter("serviceId") + "&SuccessMsg=Updated Service Successfully!");
                        break;
                    case 3:  //delete
                        serviceDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Service", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Deleted Service Successfully!");
                        break;
                    case 4:  //remove all records
                        serviceDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Service");
                        response.sendRedirect("admin/ServiceUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error editing Service, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error adding Service.");   break; //create
                    case 2:  response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error editing Service.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error deleting Service."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Error clearing Service."); break; //clear                          
                    default: response.sendRedirect("admin/ServiceUI.jsp?ErrorMsg=Unknown Error Service, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("service_charge"))
        {   
            try
            {    
                ServiceChargeDao serviceChargeDao = (ServiceChargeDao) this.getServletContext().getAttribute("serviceChargeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = serviceChargeDao.add(new ServiceCharge(null,request.getParameter("chargeName"), request.getParameter("description"), request.getParameter("rate"), request.getParameter("units"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("userServiceId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceCharge", id); }
                        
                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Added ServiceCharge Successfully!");
                        break;
                    case 2: //update            
                        serviceChargeDao.update(new ServiceCharge(Integer.parseInt(request.getParameter("serviceChargeId")), request.getParameter("chargeName"), request.getParameter("description"), request.getParameter("rate"), request.getParameter("units"), operatingDateFormat.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("userServiceId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceCharge", Integer.parseInt(request.getParameter("serviceChargeId")));
                        response.sendRedirect("admin/ServiceChargeUI.jsp?id=" + request.getParameter("serviceChargeId") + "&SuccessMsg=Updated ServiceCharge Successfully!");
                        break;
                    case 3:  //delete
                        serviceChargeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceCharge", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Deleted ServiceCharge Successfully!");
                        break;
                    case 4:  //remove all records
                        serviceChargeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceCharge");
                        response.sendRedirect("admin/ServiceChargeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error editing ServiceCharge, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error adding ServiceCharge.");   break; //create
                    case 2:  response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error editing ServiceCharge.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error deleting ServiceCharge."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Error clearing ServiceCharge."); break; //clear                          
                    default: response.sendRedirect("admin/ServiceChargeUI.jsp?ErrorMsg=Unknown Error ServiceCharge, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("service_type"))
        {   
            try
            {    
                ServiceTypeDao serviceTypeDao = (ServiceTypeDao) this.getServletContext().getAttribute("serviceTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = serviceTypeDao.add(new ServiceType(null,request.getParameter("typeName"), request.getParameter("desciption")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceType", id); }
                        
                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Added ServiceType Successfully!");
                        break;
                    case 2: //update            
                        serviceTypeDao.update(new ServiceType(Integer.parseInt(request.getParameter("serviceTypeId")), request.getParameter("typeName"), request.getParameter("desciption")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceType", Integer.parseInt(request.getParameter("serviceTypeId")));
                        response.sendRedirect("admin/ServiceTypeUI.jsp?id=" + request.getParameter("serviceTypeId") + "&SuccessMsg=Updated ServiceType Successfully!");
                        break;
                    case 3:  //delete
                        serviceTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Deleted ServiceType Successfully!");
                        break;
                    case 4:  //remove all records
                        serviceTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "ServiceType");
                        response.sendRedirect("admin/ServiceTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error editing ServiceType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error adding ServiceType.");   break; //create
                    case 2:  response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error editing ServiceType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error deleting ServiceType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Error clearing ServiceType."); break; //clear                          
                    default: response.sendRedirect("admin/ServiceTypeUI.jsp?ErrorMsg=Unknown Error ServiceType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("shipment"))
        {   
            try
            {    
                ShipmentDao shipmentDao = (ShipmentDao) this.getServletContext().getAttribute("shipmentDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = shipmentDao.add(new Shipment(null,operatingDateFormat.parse(request.getParameter("createdOn")), request.getParameter("trackingNumber"), Double.parseDouble(request.getParameter("totalWeight")), operatingDateFormat.parse(request.getParameter("shipDate")), operatingDateFormat.parse(request.getParameter("deliveryDate")), Integer.parseInt(request.getParameter("itemQuantity")), Integer.parseInt(request.getParameter("orderId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipment", id); }
                        
                        response.sendRedirect("admin/ShipmentUI.jsp?SuccessMsg=Added Shipment Successfully!");
                        break;
                    case 2: //update            
                        shipmentDao.update(new Shipment(Integer.parseInt(request.getParameter("shipmentId")), operatingDateFormat.parse(request.getParameter("createdOn")), request.getParameter("trackingNumber"), Double.parseDouble(request.getParameter("totalWeight")), operatingDateFormat.parse(request.getParameter("shipDate")), operatingDateFormat.parse(request.getParameter("deliveryDate")), Integer.parseInt(request.getParameter("itemQuantity")), Integer.parseInt(request.getParameter("orderId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipment", Integer.parseInt(request.getParameter("shipmentId")));
                        response.sendRedirect("admin/ShipmentUI.jsp?id=" + request.getParameter("shipmentId") + "&SuccessMsg=Updated Shipment Successfully!");
                        break;
                    case 3:  //delete
                        shipmentDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipment", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ShipmentUI.jsp?SuccessMsg=Deleted Shipment Successfully!");
                        break;
                    case 4:  //remove all records
                        shipmentDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipment");
                        response.sendRedirect("admin/ShipmentUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error editing Shipment, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error adding Shipment.");   break; //create
                    case 2:  response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error editing Shipment.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error deleting Shipment."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Error clearing Shipment."); break; //clear                          
                    default: response.sendRedirect("admin/ShipmentUI.jsp?ErrorMsg=Unknown Error Shipment, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("shipping"))
        {   
            try
            {    
                ShippingDao shippingDao = (ShippingDao) this.getServletContext().getAttribute("shippingDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = shippingDao.add(new Shipping(null,request.getParameter("methodName"), Double.parseDouble(request.getParameter("quantity")), request.getParameter("unitOfMeasure"), Double.parseDouble(request.getParameter("ratePerUnitCost")), Double.parseDouble(request.getParameter("additionalCost")), Integer.parseInt(request.getParameter("stateProvinceId")), Integer.parseInt(request.getParameter("countryId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipping", id); }
                        
                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Added Shipping Successfully!");
                        break;
                    case 2: //update            
                        shippingDao.update(new Shipping(Integer.parseInt(request.getParameter("shippingId")), request.getParameter("methodName"), Double.parseDouble(request.getParameter("quantity")), request.getParameter("unitOfMeasure"), Double.parseDouble(request.getParameter("ratePerUnitCost")), Double.parseDouble(request.getParameter("additionalCost")), Integer.parseInt(request.getParameter("stateProvinceId")), Integer.parseInt(request.getParameter("countryId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipping", Integer.parseInt(request.getParameter("shippingId")));
                        response.sendRedirect("admin/ShippingUI.jsp?id=" + request.getParameter("shippingId") + "&SuccessMsg=Updated Shipping Successfully!");
                        break;
                    case 3:  //delete
                        shippingDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipping", request.getParameter("id"));
                        
                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Deleted Shipping Successfully!");
                        break;
                    case 4:  //remove all records
                        shippingDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Shipping");
                        response.sendRedirect("admin/ShippingUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error editing Shipping, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error adding Shipping.");   break; //create
                    case 2:  response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error editing Shipping.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error deleting Shipping."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Error clearing Shipping."); break; //clear                          
                    default: response.sendRedirect("admin/ShippingUI.jsp?ErrorMsg=Unknown Error Shipping, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site"))
        {   
            try
            {    
                SiteDao siteDao = (SiteDao) this.getServletContext().getAttribute("siteDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteDao.add(new Site(null,request.getParameter("siteName"), request.getParameter("domain"), Integer.parseInt(request.getParameter("mode")), request.getParameter("url"), request.getParameter("logoTitle"), request.getParameter("logoImage"), Integer.parseInt(request.getParameter("useAsStore")), request.getParameter("emailHost"), Integer.parseInt(request.getParameter("emailPort")), request.getParameter("emailUsername"), request.getParameter("emailPassword"), Integer.parseInt(request.getParameter("siteStatus")), request.getParameter("locale"), Integer.parseInt(request.getParameter("templateId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Site", id); }
                        
                        response.sendRedirect("admin/SiteUI.jsp?SuccessMsg=Added Site Successfully!");
                        break;
                    case 2: //update            
                        siteDao.update(new Site(Integer.parseInt(request.getParameter("siteId")), request.getParameter("siteName"), request.getParameter("domain"), Integer.parseInt(request.getParameter("mode")), request.getParameter("url"), request.getParameter("logoTitle"), request.getParameter("logoImage"), Integer.parseInt(request.getParameter("useAsStore")), request.getParameter("emailHost"), Integer.parseInt(request.getParameter("emailPort")), request.getParameter("emailUsername"), request.getParameter("emailPassword"), Integer.parseInt(request.getParameter("siteStatus")), request.getParameter("locale"), Integer.parseInt(request.getParameter("templateId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Site", Integer.parseInt(request.getParameter("siteId")));
                        response.sendRedirect("admin/SiteUI.jsp?id=" + request.getParameter("siteId") + "&SuccessMsg=Updated Site Successfully!");
                        break;
                    case 3:  //delete
                        siteDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Site", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SiteUI.jsp?SuccessMsg=Deleted Site Successfully!");
                        break;
                    case 4:  //remove all records
                        siteDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Site");
                        response.sendRedirect("admin/SiteUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error editing Site, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error adding Site.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error editing Site.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error deleting Site."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Error clearing Site."); break; //clear                          
                    default: response.sendRedirect("admin/SiteUI.jsp?ErrorMsg=Unknown Error Site, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_attribute"))
        {   
            try
            {    
                SiteAttributeDao siteAttributeDao = (SiteAttributeDao) this.getServletContext().getAttribute("siteAttributeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteAttributeDao.add(new SiteAttribute(null,request.getParameter("attributeKey"), request.getParameter("attributeValue"), request.getParameter("attributeType"), Integer.parseInt(request.getParameter("siteId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteAttribute", id); }
                        
                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Added SiteAttribute Successfully!");
                        break;
                    case 2: //update            
                        siteAttributeDao.update(new SiteAttribute(Integer.parseInt(request.getParameter("siteAttributeId")), request.getParameter("attributeKey"), request.getParameter("attributeValue"), request.getParameter("attributeType"), Integer.parseInt(request.getParameter("siteId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteAttribute", Integer.parseInt(request.getParameter("siteAttributeId")));
                        response.sendRedirect("admin/SiteAttributeUI.jsp?id=" + request.getParameter("siteAttributeId") + "&SuccessMsg=Updated SiteAttribute Successfully!");
                        break;
                    case 3:  //delete
                        siteAttributeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteAttribute", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Deleted SiteAttribute Successfully!");
                        break;
                    case 4:  //remove all records
                        siteAttributeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteAttribute");
                        response.sendRedirect("admin/SiteAttributeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error editing SiteAttribute, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error adding SiteAttribute.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error editing SiteAttribute.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error deleting SiteAttribute."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Error clearing SiteAttribute."); break; //clear                          
                    default: response.sendRedirect("admin/SiteAttributeUI.jsp?ErrorMsg=Unknown Error SiteAttribute, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_file"))
        {   
            try
            {    
                SiteFileDao siteFileDao = (SiteFileDao) this.getServletContext().getAttribute("siteFileDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteFileDao.add(new SiteFile(null,request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFile", id); }
                        Database.updateCount("SiteFile", 1);
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Added SiteFile Successfully!");
                        break;
                    case 2: //update            
                        siteFileDao.update(new SiteFile(Integer.parseInt(request.getParameter("siteFileId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("label")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFile", Integer.parseInt(request.getParameter("siteFileId")));
                        response.sendRedirect("admin/SiteFileUI.jsp?id=" + request.getParameter("siteFileId") + "&SuccessMsg=Updated SiteFile Successfully!");
                        break;
                    case 3:  //delete
                        siteFileDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFile", request.getParameter("id"));
                        Database.updateCount("SiteFile", -1);
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Deleted SiteFile Successfully!");
                        break;
                    case 4:  //remove all records
                        siteFileDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFile");
                        response.sendRedirect("admin/SiteFileUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error editing SiteFile, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error adding SiteFile.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error editing SiteFile.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error deleting SiteFile."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Error clearing SiteFile."); break; //clear                          
                    default: response.sendRedirect("admin/SiteFileUI.jsp?ErrorMsg=Unknown Error SiteFile, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_folder"))
        {   
            try
            {    
                SiteFolderDao siteFolderDao = (SiteFolderDao) this.getServletContext().getAttribute("siteFolderDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteFolderDao.add(new SiteFolder(null,request.getParameter("folderName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("siteId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFolder", id); }
                        
                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Added SiteFolder Successfully!");
                        break;
                    case 2: //update            
                        siteFolderDao.update(new SiteFolder(Integer.parseInt(request.getParameter("siteFolderId")), request.getParameter("folderName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("siteId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFolder", Integer.parseInt(request.getParameter("siteFolderId")));
                        response.sendRedirect("admin/SiteFolderUI.jsp?id=" + request.getParameter("siteFolderId") + "&SuccessMsg=Updated SiteFolder Successfully!");
                        break;
                    case 3:  //delete
                        siteFolderDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFolder", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Deleted SiteFolder Successfully!");
                        break;
                    case 4:  //remove all records
                        siteFolderDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteFolder");
                        response.sendRedirect("admin/SiteFolderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error editing SiteFolder, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error adding SiteFolder.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error editing SiteFolder.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error deleting SiteFolder."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Error clearing SiteFolder."); break; //clear                          
                    default: response.sendRedirect("admin/SiteFolderUI.jsp?ErrorMsg=Unknown Error SiteFolder, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_image"))
        {   
            try
            {    
                SiteImageDao siteImageDao = (SiteImageDao) this.getServletContext().getAttribute("siteImageDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteImageDao.add(new SiteImage(null,request.getParameter("fileName"), request.getParameter("description"), request.getParameter("linkUrl"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("imageTypeId")), Integer.parseInt(request.getParameter("siteId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteImage", id); }
                        
                        response.sendRedirect("admin/SiteImageUI.jsp?SuccessMsg=Added SiteImage Successfully!");
                        break;
                    case 2: //update            
                        siteImageDao.update(new SiteImage(Integer.parseInt(request.getParameter("siteImageId")), request.getParameter("fileName"), request.getParameter("description"), request.getParameter("linkUrl"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("imageTypeId")), Integer.parseInt(request.getParameter("siteId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteImage", Integer.parseInt(request.getParameter("siteImageId")));
                        response.sendRedirect("admin/SiteImageUI.jsp?id=" + request.getParameter("siteImageId") + "&SuccessMsg=Updated SiteImage Successfully!");
                        break;
                    case 3:  //delete
                        siteImageDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteImage", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SiteImageUI.jsp?SuccessMsg=Deleted SiteImage Successfully!");
                        break;
                    case 4:  //remove all records
                        siteImageDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteImage");
                        response.sendRedirect("admin/SiteImageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error editing SiteImage, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error adding SiteImage.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error editing SiteImage.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error deleting SiteImage."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Error clearing SiteImage."); break; //clear                          
                    default: response.sendRedirect("admin/SiteImageUI.jsp?ErrorMsg=Unknown Error SiteImage, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_item"))
        {   
            try
            {    
                SiteItemDao siteItemDao = (SiteItemDao) this.getServletContext().getAttribute("siteItemDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteItemDao.add(new SiteItem(null,Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("itemId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteItem", id); }
                        
                        response.sendRedirect("admin/SiteItemUI.jsp?SuccessMsg=Added SiteItem Successfully!");
                        break;
                    case 2: //update            
                        siteItemDao.update(new SiteItem(Integer.parseInt(request.getParameter("siteItemId")), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("itemId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteItem", Integer.parseInt(request.getParameter("siteItemId")));
                        response.sendRedirect("admin/SiteItemUI.jsp?id=" + request.getParameter("siteItemId") + "&SuccessMsg=Updated SiteItem Successfully!");
                        break;
                    case 3:  //delete
                        siteItemDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteItem", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SiteItemUI.jsp?SuccessMsg=Deleted SiteItem Successfully!");
                        break;
                    case 4:  //remove all records
                        siteItemDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteItem");
                        response.sendRedirect("admin/SiteItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error editing SiteItem, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error adding SiteItem.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error editing SiteItem.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error deleting SiteItem."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Error clearing SiteItem."); break; //clear                          
                    default: response.sendRedirect("admin/SiteItemUI.jsp?ErrorMsg=Unknown Error SiteItem, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_language"))
        {   
            try
            {    
                SiteLanguageDao siteLanguageDao = (SiteLanguageDao) this.getServletContext().getAttribute("siteLanguageDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = siteLanguageDao.add(new SiteLanguage(null,request.getParameter("languageName"), request.getParameter("locale"), Integer.parseInt(request.getParameter("rtl")), request.getParameter("flagFileName"), Integer.parseInt(request.getParameter("siteId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteLanguage", id); }
                        
                        response.sendRedirect("admin/SiteLanguageUI.jsp?SuccessMsg=Added SiteLanguage Successfully!");
                        break;
                    case 2: //update            
                        siteLanguageDao.update(new SiteLanguage(Integer.parseInt(request.getParameter("siteLanguageId")), request.getParameter("languageName"), request.getParameter("locale"), Integer.parseInt(request.getParameter("rtl")), request.getParameter("flagFileName"), Integer.parseInt(request.getParameter("siteId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteLanguage", Integer.parseInt(request.getParameter("siteLanguageId")));
                        response.sendRedirect("admin/SiteLanguageUI.jsp?id=" + request.getParameter("siteLanguageId") + "&SuccessMsg=Updated SiteLanguage Successfully!");
                        break;
                    case 3:  //delete
                        siteLanguageDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteLanguage", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SiteLanguageUI.jsp?SuccessMsg=Deleted SiteLanguage Successfully!");
                        break;
                    case 4:  //remove all records
                        siteLanguageDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SiteLanguage");
                        response.sendRedirect("admin/SiteLanguageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error editing SiteLanguage, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error adding SiteLanguage.");   break; //create
                    case 2:  response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error editing SiteLanguage.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error deleting SiteLanguage."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Error clearing SiteLanguage."); break; //clear                          
                    default: response.sendRedirect("admin/SiteLanguageUI.jsp?ErrorMsg=Unknown Error SiteLanguage, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("site_page"))
        {   
            try
            {    
                SitePageDao sitePageDao = (SitePageDao) this.getServletContext().getAttribute("sitePageDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = sitePageDao.add(new SitePage(null,Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("pageId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SitePage", id); }
                        
                        response.sendRedirect("admin/SitePageUI.jsp?SuccessMsg=Added SitePage Successfully!");
                        break;
                    case 2: //update            
                        sitePageDao.update(new SitePage(Integer.parseInt(request.getParameter("sitePageId")), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("pageId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SitePage", Integer.parseInt(request.getParameter("sitePageId")));
                        response.sendRedirect("admin/SitePageUI.jsp?id=" + request.getParameter("sitePageId") + "&SuccessMsg=Updated SitePage Successfully!");
                        break;
                    case 3:  //delete
                        sitePageDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SitePage", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SitePageUI.jsp?SuccessMsg=Deleted SitePage Successfully!");
                        break;
                    case 4:  //remove all records
                        sitePageDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SitePage");
                        response.sendRedirect("admin/SitePageUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SitePageUI.jsp?ErrorMsg=Error editing SitePage, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SitePageUI.jsp?ErrorMsg=Error adding SitePage.");   break; //create
                    case 2:  response.sendRedirect("admin/SitePageUI.jsp?ErrorMsg=Error editing SitePage.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SitePageUI.jsp?ErrorMsg=Error deleting SitePage."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SitePageUI.jsp?ErrorMsg=Error clearing SitePage."); break; //clear                          
                    default: response.sendRedirect("admin/SitePageUI.jsp?ErrorMsg=Unknown Error SitePage, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("slider"))
        {   
            try
            {    
                SliderDao sliderDao = (SliderDao) this.getServletContext().getAttribute("sliderDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = sliderDao.add(new Slider(null,request.getParameter("sliderName"), Integer.parseInt(request.getParameter("sliderTypeId")), Integer.parseInt(request.getParameter("formId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Slider", id); }
                        Database.updateCount("Slider", 1);
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Added Slider Successfully!");
                        break;
                    case 2: //update            
                        sliderDao.update(new Slider(Integer.parseInt(request.getParameter("sliderId")), request.getParameter("sliderName"), Integer.parseInt(request.getParameter("sliderTypeId")), Integer.parseInt(request.getParameter("formId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Slider", Integer.parseInt(request.getParameter("sliderId")));
                        response.sendRedirect("admin/SliderUI.jsp?id=" + request.getParameter("sliderId") + "&SuccessMsg=Updated Slider Successfully!");
                        break;
                    case 3:  //delete
                        sliderDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Slider", request.getParameter("id"));
                        Database.updateCount("Slider", -1);
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Deleted Slider Successfully!");
                        break;
                    case 4:  //remove all records
                        sliderDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Slider");
                        response.sendRedirect("admin/SliderUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error editing Slider, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error adding Slider.");   break; //create
                    case 2:  response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error editing Slider.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error deleting Slider."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Error clearing Slider."); break; //clear                          
                    default: response.sendRedirect("admin/SliderUI.jsp?ErrorMsg=Unknown Error Slider, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("slider_item"))
        {   
            try
            {    
                SliderItemDao sliderItemDao = (SliderItemDao) this.getServletContext().getAttribute("sliderItemDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = sliderItemDao.add(new SliderItem(null,request.getParameter("title"), request.getParameter("description"), request.getParameter("url"), request.getParameter("imageName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("sliderId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderItem", id); }
                        
                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Added SliderItem Successfully!");
                        break;
                    case 2: //update            
                        sliderItemDao.update(new SliderItem(Integer.parseInt(request.getParameter("sliderItemId")), request.getParameter("title"), request.getParameter("description"), request.getParameter("url"), request.getParameter("imageName"), request.getParameter("alternateText"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("sliderId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderItem", Integer.parseInt(request.getParameter("sliderItemId")));
                        response.sendRedirect("admin/SliderItemUI.jsp?id=" + request.getParameter("sliderItemId") + "&SuccessMsg=Updated SliderItem Successfully!");
                        break;
                    case 3:  //delete
                        sliderItemDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderItem", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Deleted SliderItem Successfully!");
                        break;
                    case 4:  //remove all records
                        sliderItemDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderItem");
                        response.sendRedirect("admin/SliderItemUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error editing SliderItem, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error adding SliderItem.");   break; //create
                    case 2:  response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error editing SliderItem.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error deleting SliderItem."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Error clearing SliderItem."); break; //clear                          
                    default: response.sendRedirect("admin/SliderItemUI.jsp?ErrorMsg=Unknown Error SliderItem, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("slider_type"))
        {   
            try
            {    
                SliderTypeDao sliderTypeDao = (SliderTypeDao) this.getServletContext().getAttribute("sliderTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = sliderTypeDao.add(new SliderType(null,request.getParameter("typeName"), request.getParameter("code")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderType", id); }
                        
                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Added SliderType Successfully!");
                        break;
                    case 2: //update            
                        sliderTypeDao.update(new SliderType(Integer.parseInt(request.getParameter("sliderTypeId")), request.getParameter("typeName"), request.getParameter("code")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderType", Integer.parseInt(request.getParameter("sliderTypeId")));
                        response.sendRedirect("admin/SliderTypeUI.jsp?id=" + request.getParameter("sliderTypeId") + "&SuccessMsg=Updated SliderType Successfully!");
                        break;
                    case 3:  //delete
                        sliderTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Deleted SliderType Successfully!");
                        break;
                    case 4:  //remove all records
                        sliderTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "SliderType");
                        response.sendRedirect("admin/SliderTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error editing SliderType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error adding SliderType.");   break; //create
                    case 2:  response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error editing SliderType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error deleting SliderType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Error clearing SliderType."); break; //clear                          
                    default: response.sendRedirect("admin/SliderTypeUI.jsp?ErrorMsg=Unknown Error SliderType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("state_province"))
        {   
            try
            {    
                StateProvinceDao stateProvinceDao = (StateProvinceDao) this.getServletContext().getAttribute("stateProvinceDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = stateProvinceDao.add(new StateProvince(null,request.getParameter("name"), request.getParameter("abbreviation"), Integer.parseInt(request.getParameter("countryId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "StateProvince", id); }
                        
                        response.sendRedirect("admin/StateProvinceUI.jsp?SuccessMsg=Added StateProvince Successfully!");
                        break;
                    case 2: //update            
                        stateProvinceDao.update(new StateProvince(Integer.parseInt(request.getParameter("stateProvinceId")), request.getParameter("name"), request.getParameter("abbreviation"), Integer.parseInt(request.getParameter("countryId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "StateProvince", Integer.parseInt(request.getParameter("stateProvinceId")));
                        response.sendRedirect("admin/StateProvinceUI.jsp?id=" + request.getParameter("stateProvinceId") + "&SuccessMsg=Updated StateProvince Successfully!");
                        break;
                    case 3:  //delete
                        stateProvinceDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "StateProvince", request.getParameter("id"));
                        
                        response.sendRedirect("admin/StateProvinceUI.jsp?SuccessMsg=Deleted StateProvince Successfully!");
                        break;
                    case 4:  //remove all records
                        stateProvinceDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "StateProvince");
                        response.sendRedirect("admin/StateProvinceUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/StateProvinceUI.jsp?ErrorMsg=Error editing StateProvince, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/StateProvinceUI.jsp?ErrorMsg=Error adding StateProvince.");   break; //create
                    case 2:  response.sendRedirect("admin/StateProvinceUI.jsp?ErrorMsg=Error editing StateProvince.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/StateProvinceUI.jsp?ErrorMsg=Error deleting StateProvince."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/StateProvinceUI.jsp?ErrorMsg=Error clearing StateProvince."); break; //clear                          
                    default: response.sendRedirect("admin/StateProvinceUI.jsp?ErrorMsg=Unknown Error StateProvince, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("tax_rate"))
        {   
            try
            {    
                TaxRateDao taxRateDao = (TaxRateDao) this.getServletContext().getAttribute("taxRateDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = taxRateDao.add(new TaxRate(null,request.getParameter("taxCategory"), Double.parseDouble(request.getParameter("percentage")), request.getParameter("zipPostalCode"), Integer.parseInt(request.getParameter("stateProvinceId")), Integer.parseInt(request.getParameter("countryId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "TaxRate", id); }
                        
                        response.sendRedirect("admin/TaxRateUI.jsp?SuccessMsg=Added TaxRate Successfully!");
                        break;
                    case 2: //update            
                        taxRateDao.update(new TaxRate(Integer.parseInt(request.getParameter("taxRateId")), request.getParameter("taxCategory"), Double.parseDouble(request.getParameter("percentage")), request.getParameter("zipPostalCode"), Integer.parseInt(request.getParameter("stateProvinceId")), Integer.parseInt(request.getParameter("countryId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "TaxRate", Integer.parseInt(request.getParameter("taxRateId")));
                        response.sendRedirect("admin/TaxRateUI.jsp?id=" + request.getParameter("taxRateId") + "&SuccessMsg=Updated TaxRate Successfully!");
                        break;
                    case 3:  //delete
                        taxRateDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "TaxRate", request.getParameter("id"));
                        
                        response.sendRedirect("admin/TaxRateUI.jsp?SuccessMsg=Deleted TaxRate Successfully!");
                        break;
                    case 4:  //remove all records
                        taxRateDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "TaxRate");
                        response.sendRedirect("admin/TaxRateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error editing TaxRate, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error adding TaxRate.");   break; //create
                    case 2:  response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error editing TaxRate.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error deleting TaxRate."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Error clearing TaxRate."); break; //clear                          
                    default: response.sendRedirect("admin/TaxRateUI.jsp?ErrorMsg=Unknown Error TaxRate, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("template"))
        {   
            try
            {    
                TemplateDao templateDao = (TemplateDao) this.getServletContext().getAttribute("templateDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = templateDao.add(new Template(null,request.getParameter("templateName"), request.getParameter("markup"), Integer.parseInt(request.getParameter("templateStatus")), Integer.parseInt(request.getParameter("templateTypeId")), Integer.parseInt(request.getParameter("parentTemplateId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Template", id); }
                        
                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Added Template Successfully!");
                        break;
                    case 2: //update            
                        templateDao.update(new Template(Integer.parseInt(request.getParameter("templateId")), request.getParameter("templateName"), request.getParameter("markup"), Integer.parseInt(request.getParameter("templateStatus")), Integer.parseInt(request.getParameter("templateTypeId")), Integer.parseInt(request.getParameter("parentTemplateId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Template", Integer.parseInt(request.getParameter("templateId")));
                        response.sendRedirect("admin/TemplateUI.jsp?id=" + request.getParameter("templateId") + "&SuccessMsg=Updated Template Successfully!");
                        break;
                    case 3:  //delete
                        templateDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Template", request.getParameter("id"));
                        
                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Deleted Template Successfully!");
                        break;
                    case 4:  //remove all records
                        templateDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Template");
                        response.sendRedirect("admin/TemplateUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error editing Template, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error adding Template.");   break; //create
                    case 2:  response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error editing Template.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error deleting Template."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Error clearing Template."); break; //clear                          
                    default: response.sendRedirect("admin/TemplateUI.jsp?ErrorMsg=Unknown Error Template, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("template_type"))
        {   
            try
            {    
                TemplateTypeDao templateTypeDao = (TemplateTypeDao) this.getServletContext().getAttribute("templateTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = templateTypeDao.add(new TemplateType(null,request.getParameter("typeName"), request.getParameter("typeValue")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "TemplateType", id); }
                        
                        response.sendRedirect("admin/TemplateTypeUI.jsp?SuccessMsg=Added TemplateType Successfully!");
                        break;
                    case 2: //update            
                        templateTypeDao.update(new TemplateType(Integer.parseInt(request.getParameter("templateTypeId")), request.getParameter("typeName"), request.getParameter("typeValue")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "TemplateType", Integer.parseInt(request.getParameter("templateTypeId")));
                        response.sendRedirect("admin/TemplateTypeUI.jsp?id=" + request.getParameter("templateTypeId") + "&SuccessMsg=Updated TemplateType Successfully!");
                        break;
                    case 3:  //delete
                        templateTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "TemplateType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/TemplateTypeUI.jsp?SuccessMsg=Deleted TemplateType Successfully!");
                        break;
                    case 4:  //remove all records
                        templateTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "TemplateType");
                        response.sendRedirect("admin/TemplateTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error editing TemplateType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error adding TemplateType.");   break; //create
                    case 2:  response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error editing TemplateType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error deleting TemplateType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Error clearing TemplateType."); break; //clear                          
                    default: response.sendRedirect("admin/TemplateTypeUI.jsp?ErrorMsg=Unknown Error TemplateType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("text_string"))
        {   
            try
            {    
                TextStringDao textStringDao = (TextStringDao) this.getServletContext().getAttribute("textStringDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = textStringDao.add(new TextString(null,request.getParameter("key")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "TextString", id); }
                        
                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Added TextString Successfully!");
                        break;
                    case 2: //update            
                        textStringDao.update(new TextString(Integer.parseInt(request.getParameter("textStringId")), request.getParameter("key")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "TextString", Integer.parseInt(request.getParameter("textStringId")));
                        response.sendRedirect("admin/TextStringUI.jsp?id=" + request.getParameter("textStringId") + "&SuccessMsg=Updated TextString Successfully!");
                        break;
                    case 3:  //delete
                        textStringDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "TextString", request.getParameter("id"));
                        
                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Deleted TextString Successfully!");
                        break;
                    case 4:  //remove all records
                        textStringDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "TextString");
                        response.sendRedirect("admin/TextStringUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error editing TextString, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error adding TextString.");   break; //create
                    case 2:  response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error editing TextString.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error deleting TextString."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Error clearing TextString."); break; //clear                          
                    default: response.sendRedirect("admin/TextStringUI.jsp?ErrorMsg=Unknown Error TextString, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user"))
        {   
            try
            {    
                UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userDao.add(new User(null,request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("securityQuestion"), request.getParameter("securityAnswer"), operatingDateFormat.parse(request.getParameter("registerDate")), request.getParameter("imageUrl"), Integer.parseInt(request.getParameter("userStatus")), Integer.parseInt(request.getParameter("rank")), request.getParameter("webUrl"), Integer.parseInt(request.getParameter("itemBrandId")), Integer.parseInt(request.getParameter("userTypeId")), Integer.parseInt(request.getParameter("addressId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userGroupId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "User", id); }
                        Database.updateCount("User", 1);
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Added User Successfully!");
                        break;
                    case 2: //update            
                        userDao.update(new User(Integer.parseInt(request.getParameter("userId")), request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("securityQuestion"), request.getParameter("securityAnswer"), operatingDateFormat.parse(request.getParameter("registerDate")), request.getParameter("imageUrl"), Integer.parseInt(request.getParameter("userStatus")), Integer.parseInt(request.getParameter("rank")), request.getParameter("webUrl"), Integer.parseInt(request.getParameter("itemBrandId")), Integer.parseInt(request.getParameter("userTypeId")), Integer.parseInt(request.getParameter("addressId")), Integer.parseInt(request.getParameter("contactId")), Integer.parseInt(request.getParameter("userGroupId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "User", Integer.parseInt(request.getParameter("userId")));
                        response.sendRedirect("admin/UserUI.jsp?id=" + request.getParameter("userId") + "&SuccessMsg=Updated User Successfully!");
                        break;
                    case 3:  //delete
                        userDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "User", request.getParameter("id"));
                        Database.updateCount("User", -1);
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Deleted User Successfully!");
                        break;
                    case 4:  //remove all records
                        userDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "User");
                        response.sendRedirect("admin/UserUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error editing User, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error adding User.");   break; //create
                    case 2:  response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error editing User.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error deleting User."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Error clearing User."); break; //clear                          
                    default: response.sendRedirect("admin/UserUI.jsp?ErrorMsg=Unknown Error User, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user_action"))
        {   
            try
            {    
                UserActionDao userActionDao = (UserActionDao) this.getServletContext().getAttribute("userActionDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userActionDao.add(new UserAction(null,operatingDateFormat.parse(request.getParameter("date")), request.getParameter("detail"), Integer.parseInt(request.getParameter("userActionTypeId")), Integer.parseInt(request.getParameter("userId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserAction", id); }
                        
                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Added UserAction Successfully!");
                        break;
                    case 2: //update            
                        userActionDao.update(new UserAction(Integer.parseInt(request.getParameter("userActionId")), operatingDateFormat.parse(request.getParameter("date")), request.getParameter("detail"), Integer.parseInt(request.getParameter("userActionTypeId")), Integer.parseInt(request.getParameter("userId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserAction", Integer.parseInt(request.getParameter("userActionId")));
                        response.sendRedirect("admin/UserActionUI.jsp?id=" + request.getParameter("userActionId") + "&SuccessMsg=Updated UserAction Successfully!");
                        break;
                    case 3:  //delete
                        userActionDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserAction", request.getParameter("id"));
                        
                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Deleted UserAction Successfully!");
                        break;
                    case 4:  //remove all records
                        userActionDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserAction");
                        response.sendRedirect("admin/UserActionUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error editing UserAction, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error adding UserAction.");   break; //create
                    case 2:  response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error editing UserAction.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error deleting UserAction."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Error clearing UserAction."); break; //clear                          
                    default: response.sendRedirect("admin/UserActionUI.jsp?ErrorMsg=Unknown Error UserAction, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user_action_type"))
        {   
            try
            {    
                UserActionTypeDao userActionTypeDao = (UserActionTypeDao) this.getServletContext().getAttribute("userActionTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userActionTypeDao.add(new UserActionType(null,request.getParameter("typeName")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserActionType", id); }
                        
                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Added UserActionType Successfully!");
                        break;
                    case 2: //update            
                        userActionTypeDao.update(new UserActionType(Integer.parseInt(request.getParameter("userActionTypeId")), request.getParameter("typeName")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserActionType", Integer.parseInt(request.getParameter("userActionTypeId")));
                        response.sendRedirect("admin/UserActionTypeUI.jsp?id=" + request.getParameter("userActionTypeId") + "&SuccessMsg=Updated UserActionType Successfully!");
                        break;
                    case 3:  //delete
                        userActionTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserActionType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Deleted UserActionType Successfully!");
                        break;
                    case 4:  //remove all records
                        userActionTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserActionType");
                        response.sendRedirect("admin/UserActionTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error editing UserActionType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error adding UserActionType.");   break; //create
                    case 2:  response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error editing UserActionType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error deleting UserActionType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Error clearing UserActionType."); break; //clear                          
                    default: response.sendRedirect("admin/UserActionTypeUI.jsp?ErrorMsg=Unknown Error UserActionType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user_group"))
        {   
            try
            {    
                UserGroupDao userGroupDao = (UserGroupDao) this.getServletContext().getAttribute("userGroupDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userGroupDao.add(new UserGroup(null,request.getParameter("groupName"), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("discountId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserGroup", id); }
                        
                        response.sendRedirect("admin/UserGroupUI.jsp?SuccessMsg=Added UserGroup Successfully!");
                        break;
                    case 2: //update            
                        userGroupDao.update(new UserGroup(Integer.parseInt(request.getParameter("userGroupId")), request.getParameter("groupName"), Integer.parseInt(request.getParameter("siteId")), Integer.parseInt(request.getParameter("discountId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserGroup", Integer.parseInt(request.getParameter("userGroupId")));
                        response.sendRedirect("admin/UserGroupUI.jsp?id=" + request.getParameter("userGroupId") + "&SuccessMsg=Updated UserGroup Successfully!");
                        break;
                    case 3:  //delete
                        userGroupDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserGroup", request.getParameter("id"));
                        
                        response.sendRedirect("admin/UserGroupUI.jsp?SuccessMsg=Deleted UserGroup Successfully!");
                        break;
                    case 4:  //remove all records
                        userGroupDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserGroup");
                        response.sendRedirect("admin/UserGroupUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error editing UserGroup, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error adding UserGroup.");   break; //create
                    case 2:  response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error editing UserGroup.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error deleting UserGroup."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Error clearing UserGroup."); break; //clear                          
                    default: response.sendRedirect("admin/UserGroupUI.jsp?ErrorMsg=Unknown Error UserGroup, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user_role"))
        {   
            try
            {    
                UserRoleDao userRoleDao = (UserRoleDao) this.getServletContext().getAttribute("userRoleDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userRoleDao.add(new UserRole(request.getParameter("userName"),request.getParameter("roleName")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserRole", id); }
                        
                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Added UserRole Successfully!");
                        break;
                    case 2: //update            
                        userRoleDao.update(new UserRole(request.getParameter("userName"), request.getParameter("roleName")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserRole", Integer.parseInt(request.getParameter("userName")));
                        response.sendRedirect("admin/UserRoleUI.jsp?id=" + request.getParameter("userName") + "&SuccessMsg=Updated UserRole Successfully!");
                        break;
                    case 3:  //delete
                        userRoleDao.removeById(request.getParameter("userName") + request.getParameter("roleName"));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserRole", request.getParameter("userName") + request.getParameter("roleName"));
                        
                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Deleted UserRole Successfully!");
                        break;
                    case 4:  //remove all records
                        userRoleDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserRole");
                        response.sendRedirect("admin/UserRoleUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error editing UserRole, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error adding UserRole.");   break; //create
                    case 2:  response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error editing UserRole.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error deleting UserRole."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Error clearing UserRole."); break; //clear                          
                    default: response.sendRedirect("admin/UserRoleUI.jsp?ErrorMsg=Unknown Error UserRole, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user_service"))
        {   
            try
            {    
                UserServiceDao userServiceDao = (UserServiceDao) this.getServletContext().getAttribute("userServiceDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userServiceDao.add(new UserService(null,operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("details"), request.getParameter("contractUrl"), request.getParameter("deliverableUrl"), Double.parseDouble(request.getParameter("depositAmount")), Integer.parseInt(request.getParameter("userRank")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("serviceId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserService", id); }
                        
                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Added UserService Successfully!");
                        break;
                    case 2: //update            
                        userServiceDao.update(new UserService(Integer.parseInt(request.getParameter("userServiceId")), operatingDateFormat.parse(request.getParameter("startDate")), operatingDateFormat.parse(request.getParameter("endDate")), request.getParameter("details"), request.getParameter("contractUrl"), request.getParameter("deliverableUrl"), Double.parseDouble(request.getParameter("depositAmount")), Integer.parseInt(request.getParameter("userRank")), Integer.parseInt(request.getParameter("blogId")), Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("serviceId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserService", Integer.parseInt(request.getParameter("userServiceId")));
                        response.sendRedirect("admin/UserServiceUI.jsp?id=" + request.getParameter("userServiceId") + "&SuccessMsg=Updated UserService Successfully!");
                        break;
                    case 3:  //delete
                        userServiceDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserService", request.getParameter("id"));
                        
                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Deleted UserService Successfully!");
                        break;
                    case 4:  //remove all records
                        userServiceDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserService");
                        response.sendRedirect("admin/UserServiceUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error editing UserService, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error adding UserService.");   break; //create
                    case 2:  response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error editing UserService.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error deleting UserService."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Error clearing UserService."); break; //clear                          
                    default: response.sendRedirect("admin/UserServiceUI.jsp?ErrorMsg=Unknown Error UserService, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("user_type"))
        {   
            try
            {    
                UserTypeDao userTypeDao = (UserTypeDao) this.getServletContext().getAttribute("userTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = userTypeDao.add(new UserType(null,request.getParameter("typeName"), request.getParameter("description"), request.getParameter("redirectUrl")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserType", id); }
                        
                        response.sendRedirect("admin/UserTypeUI.jsp?SuccessMsg=Added UserType Successfully!");
                        break;
                    case 2: //update            
                        userTypeDao.update(new UserType(Integer.parseInt(request.getParameter("userTypeId")), request.getParameter("typeName"), request.getParameter("description"), request.getParameter("redirectUrl")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserType", Integer.parseInt(request.getParameter("userTypeId")));
                        response.sendRedirect("admin/UserTypeUI.jsp?id=" + request.getParameter("userTypeId") + "&SuccessMsg=Updated UserType Successfully!");
                        break;
                    case 3:  //delete
                        userTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/UserTypeUI.jsp?SuccessMsg=Deleted UserType Successfully!");
                        break;
                    case 4:  //remove all records
                        userTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "UserType");
                        response.sendRedirect("admin/UserTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error editing UserType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error adding UserType.");   break; //create
                    case 2:  response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error editing UserType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error deleting UserType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Error clearing UserType."); break; //clear                          
                    default: response.sendRedirect("admin/UserTypeUI.jsp?ErrorMsg=Unknown Error UserType, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("vendor"))
        {   
            try
            {    
                VendorDao vendorDao = (VendorDao) this.getServletContext().getAttribute("vendorDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = vendorDao.add(new Vendor(null,request.getParameter("vendorName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("vendorStatus")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorTypeId"))));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "Vendor", id); }
                        
                        response.sendRedirect("admin/VendorUI.jsp?SuccessMsg=Added Vendor Successfully!");
                        break;
                    case 2: //update            
                        vendorDao.update(new Vendor(Integer.parseInt(request.getParameter("vendorId")), request.getParameter("vendorName"), request.getParameter("description"), Integer.parseInt(request.getParameter("rank")), Integer.parseInt(request.getParameter("vendorStatus")), Integer.parseInt(request.getParameter("metaTagId")), Integer.parseInt(request.getParameter("templateId")), Integer.parseInt(request.getParameter("vendorTypeId"))));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "Vendor", Integer.parseInt(request.getParameter("vendorId")));
                        response.sendRedirect("admin/VendorUI.jsp?id=" + request.getParameter("vendorId") + "&SuccessMsg=Updated Vendor Successfully!");
                        break;
                    case 3:  //delete
                        vendorDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "Vendor", request.getParameter("id"));
                        
                        response.sendRedirect("admin/VendorUI.jsp?SuccessMsg=Deleted Vendor Successfully!");
                        break;
                    case 4:  //remove all records
                        vendorDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "Vendor");
                        response.sendRedirect("admin/VendorUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error editing Vendor, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error adding Vendor.");   break; //create
                    case 2:  response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error editing Vendor.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error deleting Vendor."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Error clearing Vendor."); break; //clear                          
                    default: response.sendRedirect("admin/VendorUI.jsp?ErrorMsg=Unknown Error Vendor, possibly an invalid action."); break;
                }
            }
        }
            
        if(request.getParameter("form").equals("vendor_type"))
        {   
            try
            {    
                VendorTypeDao vendorTypeDao = (VendorTypeDao) this.getServletContext().getAttribute("vendorTypeDao");
                
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create                        
                        int id = vendorTypeDao.add(new VendorType(null,request.getParameter("typeName")));
                        if(id != 0) { Database.RecordUserObjectCreationAction(user.getUserId().toString(), user.getUsername(), currentTime, "VendorType", id); }
                        
                        response.sendRedirect("admin/VendorTypeUI.jsp?SuccessMsg=Added VendorType Successfully!");
                        break;
                    case 2: //update            
                        vendorTypeDao.update(new VendorType(Integer.parseInt(request.getParameter("vendorTypeId")), request.getParameter("typeName")));
                        Database.RecordUserObjectUpdateAction(user.getUserId().toString(), user.getUsername(), currentTime, "VendorType", Integer.parseInt(request.getParameter("vendorTypeId")));
                        response.sendRedirect("admin/VendorTypeUI.jsp?id=" + request.getParameter("vendorTypeId") + "&SuccessMsg=Updated VendorType Successfully!");
                        break;
                    case 3:  //delete
                        vendorTypeDao.removeById(Integer.parseInt(request.getParameter("id")));                        
                        Database.RecordUserObjectDeletionAction(user.getUserId().toString(), user.getUsername(), currentTime, "VendorType", request.getParameter("id"));
                        
                        response.sendRedirect("admin/VendorTypeUI.jsp?SuccessMsg=Deleted VendorType Successfully!");
                        break;
                    case 4:  //remove all records
                        vendorTypeDao.removeAll();                        
                        Database.RecordUserObjectClearAction(user.getUserId().toString(), user.getUsername(), currentTime, "VendorType");
                        response.sendRedirect("admin/VendorTypeUI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error editing VendorType, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error adding VendorType.");   break; //create
                    case 2:  response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error editing VendorType.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error deleting VendorType."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Error clearing VendorType."); break; //clear                          
                    default: response.sendRedirect("admin/VendorTypeUI.jsp?ErrorMsg=Unknown Error VendorType, possibly an invalid action."); break;
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

