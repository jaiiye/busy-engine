package com.busy.engine.mail;

import com.busy.engine.data.Database;
import com.busy.engine.entity.Site;
import java.util.Calendar;

public class EmailComposer 
{
    private String userName;
    private String emailTitle;
    private String emailBody;
    private String submissionEmailAddress;
    private String blindCarbonCopy;
    private String storeImagePath;
    Site siteInfo;
    MailerBean EMail;

    public EmailComposer(String userName, String emailTitle, String emailBody, String submissionEmailAddress, String blindCarbonCopy, String storeImagePath, Site siteInfo) 
    {
        this.userName = userName;
        this.emailTitle = emailTitle;
        this.emailBody = emailBody;
        this.submissionEmailAddress = submissionEmailAddress;
        this.blindCarbonCopy = blindCarbonCopy;
        this.storeImagePath = storeImagePath;       
        EMail = new MailerBean();
        EMail.setImage(storeImagePath + '/' + siteInfo.getLogoImage());
        this.siteInfo = siteInfo;
    }
    
    public void prepareEmail()
    {             
        //allows to override the default receiver email used by mailerBean
        if(submissionEmailAddress != null)
        {
            EMail.setReceiver(submissionEmailAddress);
        }

        //allows a copy of the email to be sent anonymously to the address
        if(blindCarbonCopy != null)
        {
                EMail.setBcc(blindCarbonCopy);
        }

        String template = Database.getTemplate("EmailTemplate");

        //process template
        template = template.replace("--*emailTitle*--", emailTitle);
        template = template.replace("--*emailBody*--", emailBody);
        template = template.replace("--*userName*--", userName == null ? "" : userName);
        template = template.replace("--*emailTitleImage*--", "<img src=\"cid:image_id\" border=\"0\" alt=\" " + siteInfo.getLogoTitle() + " \" />");
        template = template.replace("--*year*--", Calendar.getInstance().get(Calendar.YEAR) + "");
        template = template.replace("--*storeName*--", siteInfo.getSiteName() + "");

        EMail.addToMessage(template);
    }
    
    public boolean sendEmail()
    {
        try
        {
            System.out.println("About to send email:\n" + EMail.getMessage());
            EMail.sendMail(); 
            return true;
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();            
            return false;
        }
    }    
}
