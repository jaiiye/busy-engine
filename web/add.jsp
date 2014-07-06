
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="connection.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>

<%!
    String checkVariable(String variableName, String variableValue, HttpSession session)
    {
        if(variableValue == null || variableValue.equals(""))	     
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
%>    
    
<%
//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
if(request.getParameter("fn").equals("vote"))
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
                double combination =  oldRate + Double.parseDouble(request.getParameter("rating"));
                //System.out.println(combination);

                query =  "Update item SET ItemRating=";
                query += combination + ", ItemVoteCount= " + (votes+1) + " WHERE ItemId = " + request.getParameter("Design") + ";";

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
        if(request.getParameter("name") == null || request.getParameter("name").equals(""))	     
        {  
                valid = false;   msg += application.getAttribute("login-username")+ " " + application.getAttribute("error-message-cannot-be-empty") + "<br />";  
        }

        if(request.getParameter("password") == null || request.getParameter("pass2") == null) 	     
        {  
                valid = false;   msg += application.getAttribute("register-password") + " " + application.getAttribute("error-message-invalid") + " <br />";  
        }

        if(request.getParameter("password").equals("") || request.getParameter("pass2").equals(""))     
        {  
                valid = false;   msg += application.getAttribute("register-password") + " " + application.getAttribute("error-message-cannot-be-empty") + "<br />";  
        }

        if(!request.getParameter("password").equals(request.getParameter("pass2")) )			   
        {  
                valid = false;   msg += application.getAttribute("register-password") + " " + application.getAttribute("error-message-does-not-match") + "<br />";  
        }

        if(request.getParameter("email").equals(""))						           
        {  
                valid = false;   msg += application.getAttribute("register-email-address")  + " " + application.getAttribute("error-message-cannot-be-empty") + "<br />";  
        }

        if(request.getParameter("newsletter") != null && (!request.getParameter("newsletter").equals("NewsLetter"))) 
        { 
                valid = false;   msg +=  application.getAttribute("error-message-unknown-problem") + "<br />"; 
        }

        if(!Validator.validate("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", request.getParameter("email")))
        {                
                valid = false;
                msg += application.getAttribute("register-email-address") + " " + application.getAttribute("error-message-invalid") + "<br />";
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
                int randomNumber = new Random().nextInt(999998) + 2; //minimum 2
                String query = "INSERT INTO user(UserName, UserPassword, UserEmail, UserEmailConfirmed, UserImgUrl) VALUES('" + request.getParameter("name") + "' ,'" + request.getParameter("password") + "' ,'" + request.getParameter("email") +"'," + randomNumber + ",'unknown.jpg')";
                statement.executeUpdate(query);

                //register the customer info
                //query = "INSERT INTO customer(CustomerEmail, UserId) VALUES('" + request.getParameter("email") + "')";
                //statement.executeUpdate(query);

                //subscribe user for newsletter and
                if(request.getParameter("newsletter") != null && request.getParameter("newsletter").equals("NewsLetter"))
                {
                    Database.addNewsletterSubscriber(request.getParameter("name") , request.getParameter("email"));
                }

                query = "INSERT INTO user_roles(UserName, role_name) VALUES( '" + request.getParameter("name") + "','member')";
                statement.executeUpdate(query);

                //prepare a confirmation email to send to the user to confirm their email address
                MailerBean EMail = new MailerBean(); 
                EMail.setReceiver(request.getParameter("email"));

                //get siteInfo fdor the site
                SiteInfo info = Database.getSiteInfo();

                //send an identical message to site admin notifying him/her of the confirmation email being sent
                EMail.setBcc(info.getAdminEmail());

                String title = "Confirmation Request:";
                String template = Database.getTemplate("EmailTemplate");
                String body = "<br> Please click here to confirm your email address:" 
                           + " <a href=\"" + (info.getSiteMode()== 1 ? info.getSiteUrl() : info.getSiteTestingUrl()) + "/ConfirmUserEmail?EMail=" + request.getParameter("email") + "&Key=" + randomNumber + "\">Click Here...</a>";

                //process template
                template = template.replace("--*emailTitle*--", "User/Email Confirmation Message");
                template = template.replace("--*emailBody*--", body );
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
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode(msg,"UTF-8") );
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
//-----------------------------------------------------------------------
// Warranty Registration Page Code
//----------------------------------------------------------------------- 
if (request.getParameter("fn").equals("warranty"))
{
    String msg = "";
    try
    {
        //server side validation step
        msg += checkVariable("name",request.getParameter("name"), session);  

        msg += checkVariable("address",request.getParameter("address"), session);	                     
        msg += checkVariable("city",request.getParameter("city"), session);	                     
        msg += checkVariable("state",request.getParameter("state"), session);	                     
        msg += checkVariable("zip",request.getParameter("zip"), session);

        msg += checkVariable("phone",request.getParameter("phone"), session);                
        msg += checkVariable("email",request.getParameter("email"), session);

        msg += checkVariable("installer",request.getParameter("installer"), session);
        msg += checkVariable("installDate",request.getParameter("installDate"), session);                     
        msg +=  checkVariable("invoiceNum",request.getParameter("invoiceNum"), session);	                     
        msg +=  checkVariable("invoicePrice",request.getParameter("invoicePrice"), session);	                     

        //msg +=  checkVariable("FilmTypeOther",request.getParameter("FilmTypeOther"), session);		                     
        //msg +=  checkVariable("TintedAreaOther",request.getParameter("TintedAreaOther"), session);

        if(checkVariable("FilmType",request.getParameter("FilmType"), session).equals(""))
        {
            if(request.getParameter("FilmType").equals("Automotive"))
            {    
                msg +=  checkVariable("AutomotiveRollNum",request.getParameter("AutomotiveRollNum"), session);                                                           
                msg +=  checkVariable("VehicleYear",request.getParameter("VehicleYear"), session);		                     
                msg +=  checkVariable("VehicleMake",request.getParameter("VehicleMake"), session);		                     
                msg +=  checkVariable("VehicleModel",request.getParameter("VehicleModel"), session);		                     
                msg +=  checkVariable("VehicleVIN",request.getParameter("VehicleVIN"), session);
            }

            else if(request.getParameter("FilmType").equals("Architectural"))
            {
                msg +=  checkVariable("ArchitecturalRollNum",request.getParameter("ArchitecturalRollNum"), session);                    
            }

            else if(request.getParameter("FilmType").equals("Safety"))
            {
                msg +=  checkVariable("SafetyRollNum",request.getParameter("SafetyRollNum"), session);                    
            }
        } 

        checkVariable("TintedAreaFrontSide", request.getParameter("TintedAreaFrontSide"), session);                        
        checkVariable("TintedAreaRearSide", request.getParameter("TintedAreaRearSide"), session);                        
        checkVariable("TintedAreaRearWindow", request.getParameter("TintedAreaRearWindow"), session);

        if(checkVariable("TintedAreaOther",request.getParameter("TintedAreaOther"), session).equals(""))
        {
            if(request.getParameter("TintedAreaOther").equals("checked"))
            {    
                msg +=  checkVariable("OtherTintedArea",request.getParameter("OtherTintedArea"), session);
            }
        }

        if(!Validator.validate("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", request.getParameter("email")))
        {                
            msg += "<strong>Invalid email address</strong><br />";
        }                                                                                               

        /*
            if(request.getParameter("pass1") == null || request.getParameter("pass2") == null) 	     
            {  
                valid = false;   msg += " " + application.getAttribute("error-message-invalid") + " <br />";  
            }
        //*/

        if(msg.equals(""))                
        {
            int x = 0;

           //*
            statement = connection.createStatement();

            String rollNumber = "X";

            if(session.getAttribute("FilmType").equals("Automotive"))
            {
                rollNumber = (String)session.getAttribute("AutomotiveRollNum"); 
            }
            else if(session.getAttribute("FilmType").equals("Architectural"))
            {
                rollNumber = (String)session.getAttribute("ArchitecturalRollNum"); 
            }
            else if(session.getAttribute("FilmType").equals("Safety"))
            {
                rollNumber = (String)session.getAttribute("SafetyRollNum"); 
            }

            String tintedAreas = "-";

            if(session.getAttribute("TintedAreaFrontSide").equals("checked"))
            {
                tintedAreas +=  "-Front Side"; 
            }
            if(session.getAttribute("TintedAreaRearSide").equals("checked"))
            {
                tintedAreas += "-Rear Side"; 
            }
            if(session.getAttribute("TintedAreaRearWindow").equals("checked"))
            {
                tintedAreas += "-Rear Window"; 
            }                    
            if(session.getAttribute("TintedAreaOther").equals("checked"))
            {
                tintedAreas += "-OtherGlass:" +  (String)session.getAttribute("OtherTintedArea"); 
            }

            String query = "INSERT INTO warranty( FilmType, RollNumber," +
            "VehicleYear, VehicleMake, VehicleModel, VehicleVIN, TintedAreas, InstallerName, " +
            "InstallDate, InstallPrice, CustomerName, CustomerAddress, CustomerCity, " +
            "CustomerState, CustomerZipCode, CustomerPhone, CustomerEmail) VALUES(" + 

            "'" + session.getAttribute("FilmType") +  "'," +

            "'" + rollNumber +  "'," +

            "'" + session.getAttribute("VehicleYear") +  "'," +
            "'" + session.getAttribute("VehicleMake") +  "'," +
            "'" + session.getAttribute("VehicleModel") +  "'," +
            "'" + session.getAttribute("VehicleVIN") +  "'," +

            "'" + tintedAreas +  "'," +                             

            "'" + session.getAttribute("installer") +  "'," +
            "'" + session.getAttribute("installDate") +  "'," +
            //"'" + session.getAttribute("invoiceNum") +  "'," +                             
            "'" + session.getAttribute("invoicePrice") +  "'," +

            "'" + session.getAttribute("name") +  "'," +        
            "'" + session.getAttribute("address") +  "'," +
            "'" + session.getAttribute("city") +  "'," +
            "'" + session.getAttribute("state") +  "'," +
            "'" + session.getAttribute("zip") +  "'," +                            
            "'" + session.getAttribute("phone") +  "'," +                            
            "'" + session.getAttribute("email") +  "'" +
            ");";

            System.out.println("Query: " + query);                          

            statement.executeUpdate(query);           




            //subscribe user for newsletter and
            //if(request.getParameter("newsletter") != null && request.getParameter("newsletter").equals("NewsLetter"))
            //{
            //    Database.addNewsletterSubscriber(request.getParameter("name") , request.getParameter("email"));
            //}

            //query = "INSERT INTO user_roles(UserName, role_name) VALUES( '" + request.getParameter("name") + "','member')";
            //statement.executeUpdate(query);

            //*/
            response.sendRedirect("success.jsp");
        }
        else
        {
            response.sendRedirect("warranty-registration.jsp?error=" + URLEncoder.encode(msg,"UTF-8") );
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
        response.sendError(500);
    }
}
%>

</body>
</html>