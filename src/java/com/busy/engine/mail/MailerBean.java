package com.busy.engine.mail;
/*
 * MailerBean.java
 *
 * This class sends an E-mail to a prospective addressee
 * It should only be used on this server since the variables are only set
 * for this particular host
 */

import com.busy.engine.dao.SiteDaoImpl;
import com.busy.engine.dao.SiteEmailDaoImpl;
import com.busy.engine.entity.Site;
import com.busy.engine.entity.SiteEmail;
import java.io.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public final class MailerBean implements Serializable 
{	    
    private final static Site siteInfo = new SiteDaoImpl().find(1);
    
    private final static int mode = siteInfo.getMode(); // 1 for production, 2 for testing        
    final static SiteEmail email = new SiteEmailDaoImpl().find(siteInfo.getSiteEmailId());
    static Session session;        
            
    /* Bean Properties */
    
    private static String serverUrl = (siteInfo.getMode()== 1 ? siteInfo.getDomain() : siteInfo.getUrl());
    
    private static String toReceiver = email.getUsername(); //default notification email, unless overriden
    private static String from = "\"" + serverUrl.replace("www.", "")  + "\"<" + email.getUsername() + ">";
    private String subject = "Request from " + serverUrl + " on " + Calendar.getInstance().getTime();
    private String bcc = " ";
    private String html_message = " ";
    private String image = "";
    public final static Properties props = System.getProperties();

    //public final static Session session = Session.getDefaultInstance(props, null);
   
    static 
    {
        if(mode == 1) 
        {
            /* Setting Properties for STMP host */
            props.put("mail.smtp.host", "localhost");
            session = Session.getDefaultInstance(props, null);
        }
        else if (mode == 2)
        {
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", email.getHost());//smtp.gmail.com
            props.put("mail.smtp.port", email.getPort());//587
            
            session = Session.getInstance(props,
                new javax.mail.Authenticator() 
                {
                    protected PasswordAuthentication getPasswordAuthentication() 
                    {
                        return new PasswordAuthentication(email.getUsername(), email.getPassword());
                    }
                });
        }
    }        

    public void addToMessage(String message) 
    {            
        this.html_message += message;
    }
    
    public String getMessage() 
    {            
        return html_message;
    }
    
    public void setSubject(String s) 
    {            
        subject = s;
    }

    public void setReceiver(String address) 
    {            
        toReceiver = address;
    }
    
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    
    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
    
    /* Sends Email */
    public void sendMail() throws Exception
    {
        try
        {
            // PREPARE THE MULTIPART MESSAGE
            Message message = new MimeMessage(session);
            
            message.addHeader("Content-type", "text/html; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setSentDate(new Date());
            
            message.setSubject(this.subject);
            
            if(!bcc.equals(" "))
            {
                message.addHeader("BCC", this.bcc);
            }

            MimeMultipart multipart = new MimeMultipart("alternative");
            BodyPart bodyPartText = new MimeBodyPart();
            BodyPart bodyPartHtml = new MimeBodyPart();

            // THESE METHODS JUST RETURN TEXT OR HTML, RESPECTIVELY            
            String textContent = html_message;

            // SEND A HTML AND TEXT ALTERNATIVE EMAIL FOR THOSE WHO DON'T SUPPORT HTML
            bodyPartText.setContent(textContent, "text/html");
            bodyPartHtml.setContent(html_message, "text/html");

            //attach the image to the body of the message
            DataSource ds = new FileDataSource(image);
            bodyPartHtml.setDataHandler(new DataHandler(ds));
            bodyPartHtml.setFileName(image);
            bodyPartHtml.setHeader("Content-ID", "image_id");
            
            multipart.addBodyPart(bodyPartHtml, 0);
            multipart.addBodyPart(bodyPartText, 1);
            message.setContent(multipart);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toReceiver)); 

            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new Exception(e.getMessage());  
        }
    }
    
        /* Sends Notification Email */
    public static void sendNotification(String emailSubject, String msg) throws Exception
    {
        try
        {
            // PREPARE THE MULTIPART MESSAGE
            Message message = new MimeMessage(session);
            message.setSubject(emailSubject);

            MimeMultipart multipart = new MimeMultipart("alternative");
            BodyPart bodyPartText = new MimeBodyPart();
            BodyPart bodyPartHtml = new MimeBodyPart();

            // SEND A HTML AND TEXT ALTERNATIVE EMAIL FOR THOSE WHO DON'T SUPPORT HTML
            bodyPartText.setContent(msg, "text/html");
            bodyPartHtml.setContent(msg, "text/html");

            multipart.addBodyPart(bodyPartHtml, 0);
            //multipart.addBodyPart(bodyPartImage, 1);
            multipart.addBodyPart(bodyPartText, 1);
            message.setContent(multipart);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toReceiver)); 

            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new Exception(e.getMessage());  
        }
    }
        
}
        
        
        
        
        
        
        
        
        
        
        