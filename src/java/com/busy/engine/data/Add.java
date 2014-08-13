package com.busy.engine.data;

import com.busy.engine.util.Validator;
import com.busy.engine.dao.SiteDaoImpl;
import com.busy.engine.dao.TextStringDaoImpl;
import com.busy.engine.entity.Site;
import com.busy.engine.entity.TextString;
import com.busy.engine.mail.EmailComposer;
import com.busy.engine.mail.MailerBean;
import com.busy.engine.util.ActivationKey;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Sourena
 */
public class Add extends HttpServlet
{

    private ServletContext context = null;
    String connectionURL = null;
    Connection connection;
    Statement statement;
    ResultSet rs = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            connection = getDbConnection();
            statement = connection.createStatement();
        }
        catch (NamingException | SQLException ex)
        {
            ex.printStackTrace();
        }

        if (request.getParameter("fn").equals("vote"))
        {
            try
            {
                String query = "";

                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT ItemRating, ItemVoteCount FROM  item WHERE ItemId = " + request.getParameter("Design") + ";");
                rs.next();

                double oldRate = Double.parseDouble(rs.getString(1));
                int votes = Integer.parseInt(rs.getString(2));
                //System.out.println(oldRate);
                //System.out.println(request.getParameter("rating"));
                double combination = oldRate + Double.parseDouble(request.getParameter("rating"));
                //System.out.println(combination);

                query = "Update item SET ItemRating=";
                query += combination + ", ItemVoteCount= " + (votes + 1) + " WHERE ItemId = " + request.getParameter("Design") + ";";

                //System.out.println(query);
                statement.executeUpdate(query);

                response.sendRedirect("design.jsp?Id=" + request.getParameter("Design"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(500);
            }
        }
        //-----------------------------------------------------------------------
        //-----------------------------------------------------------------------
        if (request.getParameter("fn").equals("newUser"))
        {
            String msg = "<br />";
            boolean valid = true;
            try
            {
                //server side validation step
                if (request.getParameter("name") == null || request.getParameter("name").equals(""))
                {
                    valid = false;
                    msg += context.getAttribute("login-username") + " " + context.getAttribute("error-message-cannot-be-empty") + "<br />";
                }

                if (request.getParameter("password") == null || request.getParameter("pass2") == null)
                {
                    valid = false;
                    msg += context.getAttribute("register-password") + " " + context.getAttribute("error-message-invalid") + " <br />";
                }

                if (request.getParameter("password").equals("") || request.getParameter("pass2").equals(""))
                {
                    valid = false;
                    msg += context.getAttribute("register-password") + " " + context.getAttribute("error-message-cannot-be-empty") + "<br />";
                }

                if (!request.getParameter("password").equals(request.getParameter("pass2")))
                {
                    valid = false;
                    msg += context.getAttribute("register-password") + " " + context.getAttribute("error-message-does-not-match") + "<br />";
                }

                if (request.getParameter("email").equals(""))
                {
                    valid = false;
                    msg += context.getAttribute("register-email-address") + " " + context.getAttribute("error-message-cannot-be-empty") + "<br />";
                }

                if (request.getParameter("newsletter") != null && (!request.getParameter("newsletter").equals("NewsLetter")))
                {
                    valid = false;
                    msg += context.getAttribute("error-message-unknown-problem") + "<br />";
                }

                if (!Validator.validate("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", request.getParameter("email")))
                {
                    valid = false;
                    msg += context.getAttribute("register-email-address") + " " + context.getAttribute("error-message-invalid") + "<br />";
                }
                //System.out.println("null: " + (request.getParameter("email") == null));
                //System.out.println("empty:" + (request.getParameter("email").equals("")) );
                //System.out.println("validator:" + Validator.validate("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", request.getParameter("email")));

                if (valid)
                {
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT count(*) FROM  user WHERE UserName='" + request.getParameter("name") + "';");
                    rs.next();

                    if (rs.getInt(1) > 0)
                    {
                        response.sendRedirect("register.jsp?error=User Name Taken, Try Another Name");
                    }
                    else
                    {
                        //java.util.Random by default initializes to System.currentTimeMillis()
                        String query = "INSERT INTO user(UserName, UserPassword, UserEmail, UserImgUrl) VALUES('" + request.getParameter("name") + "' ,'" + request.getParameter("password") + "' ,'" + request.getParameter("email") + "','unknown.jpg')";
                        statement.executeUpdate(query);

                        //register the customer info
                        //query = "INSERT INTO customer(CustomerEmail, UserId) VALUES('" + request.getParameter("email") + "')";
                        //statement.executeUpdate(query);
                        //subscribe user for newsletter and
                        if (request.getParameter("newsletter") != null && request.getParameter("newsletter").equals("NewsLetter"))
                        {
                            Database.addNewsletterSubscriber(request.getParameter("name"), request.getParameter("email"));
                        }

                        query = "INSERT INTO user_roles(UserName, role_name) VALUES( '" + request.getParameter("name") + "','member')";
                        statement.executeUpdate(query);

                        //prepare a confirmation email to send to the user to confirm their email address
                        MailerBean EMail = new MailerBean();
                        EMail.setReceiver(request.getParameter("email"));

                        //get siteInfo fdor the site
                        Site info = new SiteDaoImpl().find(1);

                        //send an identical message to site admin notifying him/her of the confirmation email being sent
                        EMail.setBcc(info.getEmailUsername());

                        String title = "Confirmation Request:";
                        String template = Database.getTemplate("EmailTemplate");
                        String body = "<br> Please click here to confirm your email address:"
                                + " <a href=\"" + (info.getMode() == 1 ? info.getDomain() : info.getUrl()) + "/ConfirmUserEmail?EMail=" + request.getParameter("email") + "&Key=" + ActivationKey.makeKey(request.getParameter("name")) + "\">Click Here...</a>";

                        //process template
                        template = template.replace("--*emailTitle*--", "User/Email Confirmation Message");
                        template = template.replace("--*emailBody*--", body);
                        template = template.replace("--*userName*--", request.getParameter("name"));

                        EMail.addToMessage(template);
                        try
                        {
                            System.out.println("About to send email for new user confirmation:\n" + EMail.getMessage());
                            EMail.sendMail();
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }

                        response.sendRedirect("register.jsp?status=success");
                    }
                }
                else
                {
                    response.sendRedirect("login.jsp?error=" + URLEncoder.encode(msg, "UTF-8"));
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(500);
            }
        }

        if (request.getParameter("form").equals("item"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO item(ItemName, ItemDescription, ItemBrandId, ItemListPrice, ItemPrice, ItemPriceAdjustment,  ItemSEOTitle,  ItemSEODescription, ItemSEOKeywords, ItemShortDescription)";

                query += " VALUES ( '" + Database.encodeHTML(request.getParameter("name")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("desc")) + "', ";
                query += "" + request.getParameter("brandId") + ", ";
                query += "" + request.getParameter("listPrice") + ", ";
                query += "" + request.getParameter("price") + ", ";
                query += "'" + request.getParameter("adjust") + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("seotitle")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("seodesc")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("seokeywords")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("shortDesc")) + "' ";
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

        else if (request.getParameter("form").equals("itemDesign"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO item(ItemName, ItemDescription, ItemBrandId, ItemType, ItemRating, ItemVoteCount,  ItemSEOTitle,  ItemSEODescription, ItemSEOKeywords)";

                query += " VALUES ( '" + request.getParameter("name") + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("desc")) + "', ";
                query += "" + request.getParameter("brandId") + ", ";
                query += "" + request.getParameter("type") + ", ";
                query += "3.00, ";
                query += "1, ";
                query += "'" + Database.encodeHTML(request.getParameter("seotitle")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("seodesc")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("seokeywords")) + "' ";
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

        else if (request.getParameter("form").equals("item_brands"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO item_brand(ItemBrandName, ItemBrandDescription)";
                query += " VALUES ( '" + request.getParameter("name") + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("desc")) + "' ";
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

        else if (request.getParameter("form").equals("newPage"))
        {
            try
            {
                String query = "";
                int pageType = Integer.valueOf(request.getParameter("typeId"));
                int pageSlider = Integer.valueOf(request.getParameter("sliderId"));
                String pageDetails = "-";

                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM  page_template WHERE PageTemplateId=" + pageType + ";");

                while (rs.next())
                {
                    pageDetails = rs.getString(3);
                }

                query = "INSERT INTO info(InfoName, InfoDescription)";
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

        else if (request.getParameter("form").equals("newPageTemplate"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO page_template(TemplateName, TemplateMarkup)";
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

        else if (request.getParameter("form").equals("template"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO template(TemplateName, TemplateBody)";
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

        else if (request.getParameter("form").equals("tax"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO tax(TaxState, TaxRate)";
                query += " VALUES ( '" + request.getParameter("state") + "', ";
                query += "'" + request.getParameter("rate") + "' ";
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
        else if (request.getParameter("form").equals("categories"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO category(CategoryName)";
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
        else if (request.getParameter("form").equals("item_categories"))
        {
            try
            {
                String query = "";
                String ItemId = request.getParameter("ItemId");
                String CategoryId = request.getParameter("CategoryId");

                //first check to see if category is already been added
                query = "SELECT count(*) FROM item_category i WHERE ItemId= " + ItemId + " AND CategoryId = " + CategoryId + ";";
                System.out.println(query);
                rs = statement.executeQuery(query);

                if (rs.next())
                {
                    if (rs.getInt(1) > 0)
                    {
                        response.sendRedirect("products.jsp?id=" + ItemId + "&ErrorMsg=" + "Category is already associated with the item!");
                    }
                    else
                    {
                        query = "INSERT INTO item_category(CategoryId, ItemId)";
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
        else if (request.getParameter("form").equals("item_options"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO item_option(ItemOptionType, ItemOptionDescription)";
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
        else if (request.getParameter("form").equals("item_options_available"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO item_option_available(ItemId, ItemOptionId, ItemAvailabilityId, ItemQuantity)";
                query += " VALUES ( " + request.getParameter("ItemId") + ", ";
                query += "" + request.getParameter("optionId") + ", ";
                query += "" + request.getParameter("availability") + ", ";
                query += "" + request.getParameter("quantity") + " ";
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
        else if (request.getParameter("form").equals("blogPost"))
        {
            try
            {
                java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                java.util.Date tDate = new java.util.Date();

                String query = "";

                query = "INSERT INTO blog_post(PostTitle, PostBody, PostPicURL,PostDate, PostTags, BlogId, UserId, PostRating)";
                query += " VALUES ( '" + Database.encodeHTML(request.getParameter("PostTitle")) + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("body")) + "', ";
                query += "'" + request.getParameter("PostImage") + "', ";
                query += "'" + df.format(tDate).substring(0, 19) + "', ";
                query += "'" + request.getParameter("PostTags") + "', ";
                query += "'" + request.getParameter("blogId") + "', ";
                query += "'" + request.getParameter("UserId") + "', ";
                query += "'" + request.getParameter("PostRating") + "' ";
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
        else if (request.getParameter("form").equals("siteAttribute"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO site_attribute(AttributeKey, AttributeValue, AttributeType)";
                query += " VALUES ( '" + request.getParameter("key") + "', ";
                query += "'" + request.getParameter("value") + "', ";
                query += "'" + request.getParameter("type") + "' ";
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

        else if (request.getParameter("form").equals("newFolder"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO site_folder(SiteFolderName, SiteFolderDescription, SiteFolderRank)";
                query += " VALUES ( '" + request.getParameter("name") + "', ";
                query += "'" + request.getParameter("desc") + "', ";
                query += "'" + request.getParameter("rank") + "' ";
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

        else if (request.getParameter("form").equals("mailinglist"))
        {
            try
            {
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT MAX(MailinglistId) FROM  mailinglist;");
                rs.next();
                String query = "INSERT INTO mailinglist(MailinglistId, MailinglistName, MailinglistEmail) VALUES( " + (Integer.parseInt(rs.getString(1)) + 1) + ",'" + request.getParameter("Name") + "' ,'" + request.getParameter("EMailAddress") + "')";
                statement.executeUpdate(query);

                response.sendRedirect("newsletter.jsp");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(500);
            }
        }
        else if (request.getParameter("form").equals("newUser"))
        {
            try
            {
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT count(*) FROM  user WHERE UserName='" + request.getParameter("name") + "';");
                rs.next();

                if (rs.getInt(1) > 0)
                {
                    response.sendRedirect("users.jsp?error=UserNameTaken");
                }
                else
                {
                    if (request.getParameter("pass1") != null && request.getParameter("pass2") != null && !(request.getParameter("pass1").equals(request.getParameter("pass2"))))
                    {
                        response.sendRedirect("users.jsp?error=PasswordsDon'tMatch");
                    }

                    if (request.getParameter("email") != null && request.getParameter("email2") != null && !(request.getParameter("email").equals(request.getParameter("email2"))))
                    {
                        response.sendRedirect("users.jsp?error=EmailAddressesDon'tMatch");
                    }
                    else
                    {
                        String query = "INSERT INTO user(UserName, UserPassword, UserImgURL, UserEmail, UserSecurityQuestion, UserSecurityAnswer, UserEmailConfirmed, UserWebUrl, ContactId, AddressId) VALUES('"
                                + request.getParameter("name") + "' ,'"
                                + request.getParameter("pass1") + "', '"
                                + request.getParameter("imgUrl") + "','"
                                + request.getParameter("email") + "','"
                                + request.getParameter("securityQuestion") + "','"
                                + request.getParameter("securityAnswer") + "','"
                                + request.getParameter("webUrl") + "',')";
                        System.out.println(query);
                        statement.executeUpdate(query);

                        query = "INSERT INTO user_role(UserName, RoleName) VALUES( '" + request.getParameter("name") + "','" + request.getParameter("role") + "')";
                        System.out.println(query);
                        statement.executeUpdate(query);

                        //get the info for the site
                        Site site = new SiteDaoImpl().find(1);
                        
                        String body = "<br> Please click here to confirm your email address:"
                                + " <a href=\"" + (site.getMode() == 1 ? site.getDomain() : site.getUrl()) + "/ConfirmUserEmail?EMail=" + request.getParameter("email") + "&Key=" + ActivationKey.makeKey(request.getParameter("name")) + "\">Click Here...</a>";

                        //prepare a confirmation email to send to the user to confirm their email address
                        EmailComposer email = new EmailComposer(request.getParameter("name"), "Confirmation Request:", body, request.getParameter("email"), site.getEmailUsername(), request.getServletContext().getRealPath("/images-site/"), site);
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
        else if (request.getParameter("form").equals("blog"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO blog(BlogName)";
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

        else if (request.getParameter("form").equals("form"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO form(FormName, FormDescription, FormSubmissionEmail, FormSubmissionMethod, FormAction, FormResetPresent)";
                query += " VALUES ( '" + request.getParameter("name") + "', ";
                query += "'" + Database.encodeHTML(request.getParameter("desc")) + "', ";
                query += "'" + request.getParameter("submissionEmail") + "', ";
                query += "'" + request.getParameter("submissionMethod") + "', ";
                query += "'" + request.getParameter("submissionAction") + "', ";
                query += "" + request.getParameter("resettable") + " ";
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

        else if (request.getParameter("form").equals("form-field"))
        {
            try
            {
                String query = "";

                String optional = request.getParameter("optional");

                if (optional == null)
                {
                    optional = "0";
                }

                query = "INSERT INTO form_field(FormId, FieldName, FieldDataType, FieldLabel, FieldErrorText, FieldValidationRegex, FieldRank, FieldDefaultValue, FieldOptions, FieldOptional)";

                query += " VALUES ( '" + request.getParameter("formId") + "', ";
                query += "'" + request.getParameter("name") + "', ";
                query += "'" + request.getParameter("dataType") + "', ";
                query += "'" + request.getParameter("label") + "', ";
                query += "'" + request.getParameter("errorText") + "', ";
                query += "'" + request.getParameter("validationRegex") + "', ";
                query += "'" + request.getParameter("rank") + "', ";
                query += "'" + request.getParameter("defaultValue") + "', ";
                query += "'" + request.getParameter("options") + "', ";
                query += "'" + optional + "' ";
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

        else if (request.getParameter("form").equals("slider"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO slider(SliderName, SliderTypeId, FormId)";
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

        else if (request.getParameter("form").equals("sliderItem"))
        {
            try
            {
                String query = "";

                query = "INSERT INTO slider_item(SliderItemTitle, SliderItemDescription, SliderItemUrl, SliderItemImageName, SliderItemImageAlt, SliderItemRank, SliderId)";
                query += " VALUES ( '" + request.getParameter("title") + "', ";
                query += "'" + request.getParameter("desc") + "', ";
                query += "'" + request.getParameter("url") + "', ";
                query += "'" + request.getParameter("imageName") + "', ";
                query += "'" + request.getParameter("imageAlt") + "', ";
                query += "" + request.getParameter("rank") + ", ";
                query += "" + request.getParameter("sliderId") + "";
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

        else if (request.getParameter("form").equals("addUserRole"))
        {
            try
            {
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT count(*) FROM  user_role WHERE UserName='" + request.getParameter("name") + "' AND RoleName='" + request.getParameter("role") + "';");
                rs.next();

                if (rs.getInt(1) > 0)
                {
                    response.sendRedirect("users.jsp?error=UserRoleAlreadyThere");
                }
                else
                {
                    String query = "INSERT INTO user_role(UserName, RoleName) VALUES( '" + request.getParameter("name") + "','"
                            + request.getParameter("role") + "')";
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

        if (request.getParameter("form").equals("addNewStringKey"))
        {
            try
            {
                if (Database.isStringKeyTaken(request.getParameter("key")))
                {
                    response.sendRedirect("strings.jsp?error=Key already Exists!");
                }
                else
                {
                    TextString ts = new TextString();
                    ts.setKey(request.getParameter("key"));
                    int id = new TextStringDaoImpl().add(ts);

                    response.sendRedirect("TextStringUI.jsp");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(500);
            }
        }
    }

    private String checkVariable(String variableName, String variableValue, HttpSession session)
    {
        if (variableValue == null || variableValue.equals(""))
        {
            session.setAttribute(variableName, "");
            return variableName + " cannot be empty" + "<br />";

        }
        else
        {
            session.setAttribute(variableName, variableValue);
            return "";
        }
    }

    private Connection getDbConnection() throws NamingException, SQLException
    {
        Context initialContext = new InitialContext();
        Context environmentContext = (Context) initialContext.lookup("java:comp/env");
        DataSource ds = (DataSource) environmentContext.lookup("jdbc/dataSource");
        return ds.getConnection();
    }

    @Override
    public void init(ServletConfig config) throws ServletException 
    { 
         context = config.getServletContext(); 
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

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
