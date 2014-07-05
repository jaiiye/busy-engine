package com.transitionsoft;

import com.transitionsoft.dao.StoreInfo;
import java.util.Calendar;

public class EmailComposer 
{
    private String userName;
    private String emailTitle;
    private String emailBody;
    private String submissionEmailAddress;
    private String blindCarbonCopy;
    private StoreInfo storeInfo;
    private String storeImagePath;
    MailerBean EMail;

    public EmailComposer(String userName, String emailTitle, String emailBody, String submissionEmailAddress, String blindCarbonCopy, String storeImagePath) 
    {
        this.userName = userName;
        this.emailTitle = emailTitle;
        this.emailBody = emailBody;
        this.submissionEmailAddress = submissionEmailAddress;
        this.blindCarbonCopy = blindCarbonCopy;
        this.storeImagePath = storeImagePath;
        storeInfo = Database.getStoreInfo("1"); //StoreInfoId passed in
        EMail = new MailerBean(); 
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
        template = template.replace("--*emailTitleImage*--", "<img src=\"" + Database.getSiteURL() + "/images-site/" + storeInfo.getLogoImage() + "\" border=\"0\" alt=\" " + storeInfo.getLogoTitle() + " \" />");
        template = template.replace("--*year*--", Calendar.getInstance().get(Calendar.YEAR) + "");
        template = template.replace("--*storeName*--", storeInfo.getStoreName() + "");

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
