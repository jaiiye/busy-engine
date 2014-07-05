package com.transitionsoft.dao;

public class Contact
{
    private int id;
    private String title;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private String fax;
    private String email;
    private String emailConfirmed;
    private String info;
    private String userId;

    public Contact(int id, String firstName, String lastName, String email)
    {
        this.id = id;
        this.title = "-";
        this.firstName = firstName;
        this.lastName = lastName;
        this.position =  "-";
        this.phone =  "-";
        this.fax =  "-";
        this.email = email;
        this.emailConfirmed =  "-";
        this.info =  "-";
    }
    
    public Contact(int id, String title, String firstName, String lastName, String position, String phone, String fax, String email, String emailConfirmed, String info)
    {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.info = info;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmailConfirmed()
    {
        return emailConfirmed;
    }

    public void setEmailConfirmed(String emailConfirmed)
    {
        this.emailConfirmed = emailConfirmed;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    
}
