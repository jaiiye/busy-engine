package com.busy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Affiliate implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final String PROP_AFFILIATE_ID = "AffiliateId";
    public static final String PROP_COMPANY_NAME = "CompanyName";
    public static final String PROP_EMAIL = "Email";
    public static final String PROP_PHONE = "Phone";
    public static final String PROP_FAX = "Fax";
    public static final String PROP_WEB_URL = "WebUrl";
    public static final String PROP_DETAILS = "Details";
    public static final String PROP_SERVICE_HOURS = "ServiceHours";
    public static final String PROP_AFFILIATE_STATUS = "AffiliateStatus";
    public static final String PROP_USER_ID = "UserId";
    public static final String PROP_CONTACT_ID = "ContactId";
    public static final String PROP_ADDRESS_ID = "AddressId";

    private Integer affiliateId;
    private String companyName;
    private String email;
    private String phone;
    private String fax;
    private String webUrl;
    private String details;
    private Integer serviceHours;
    private Integer affiliateStatus;

    private Integer userId;
    private User user;
    private Integer contactId;
    private Contact contact;
    private Integer addressId;
    private Address address;

    ArrayList<Order> orderList;

    public Affiliate()
    {
        this.affiliateId = 0;
        this.companyName = "";
        this.email = "";
        this.phone = "";
        this.fax = "";
        this.webUrl = "";
        this.details = "";
        this.serviceHours = 0;
        this.affiliateStatus = 0;
        this.userId = 0;
        this.contactId = 0;
        this.addressId = 0;

        orderList = null;
    }

    public Affiliate(Integer AffiliateId, String CompanyName, String Email, String Phone, String Fax, String WebUrl, String Details, Integer ServiceHours, Integer AffiliateStatus, Integer UserId, Integer ContactId, Integer AddressId)
    {
        this.affiliateId = AffiliateId;
        this.companyName = CompanyName;
        this.email = Email;
        this.phone = Phone;
        this.fax = Fax;
        this.webUrl = WebUrl;
        this.details = Details;
        this.serviceHours = ServiceHours;
        this.affiliateStatus = AffiliateStatus;
        this.userId = UserId;
        this.contactId = ContactId;
        this.addressId = AddressId;

        orderList = null;
    }

    public Integer getAffiliateId()
    {
        return this.affiliateId;
    }

    public void setAffiliateId(Integer AffiliateId)
    {
        this.affiliateId = AffiliateId;
    }

    public String getCompanyName()
    {
        return this.companyName;
    }

    public void setCompanyName(String CompanyName)
    {
        this.companyName = CompanyName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String Email)
    {
        this.email = Email;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String Phone)
    {
        this.phone = Phone;
    }

    public String getFax()
    {
        return this.fax;
    }

    public void setFax(String Fax)
    {
        this.fax = Fax;
    }

    public String getWebUrl()
    {
        return this.webUrl;
    }

    public void setWebUrl(String WebUrl)
    {
        this.webUrl = WebUrl;
    }

    public String getDetails()
    {
        return this.details;
    }

    public void setDetails(String Details)
    {
        this.details = Details;
    }

    public Integer getServiceHours()
    {
        return this.serviceHours;
    }

    public void setServiceHours(Integer ServiceHours)
    {
        this.serviceHours = ServiceHours;
    }

    public Integer getAffiliateStatus()
    {
        return this.affiliateStatus;
    }

    public void setAffiliateStatus(Integer AffiliateStatus)
    {
        this.affiliateStatus = AffiliateStatus;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Integer getContactId()
    {
        return this.contactId;
    }

    public void setContactId(Integer ContactId)
    {
        this.contactId = ContactId;
    }

    public Contact getContact()
    {
        return this.contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
    }

    public Integer getAddressId()
    {
        return this.addressId;
    }

    public void setAddressId(Integer AddressId)
    {
        this.addressId = AddressId;
    }

    public Address getAddress()
    {
        return this.address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public ArrayList<Order> getOrderList()
    {
        return this.orderList;
    }

    public void setOrderList(ArrayList<Order> orderList)
    {
        this.orderList = orderList;
    }

}
