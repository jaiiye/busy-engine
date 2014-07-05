package com.transitionsoft.dao;

public class ShippingAddress implements java.io.Serializable
{
    public String firstName;
    public String lastName;    
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String country;
    public String postalCode;
    public String email;
    public String method;
    
    public ShippingAddress()
    {
        firstName = "";
        lastName = "";    
        address1 = "";
        address2 = "";
        city = "";
        state = "";
        country = "";
        postalCode = "";
        email = "";
        method = "1";
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String aName) { firstName = aName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String aName) { lastName = aName; }

    public String getAddress1() { return address1; }
    public void setAddress1(String anAddress1) { address1 = anAddress1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String anAddress2) { address2 = anAddress2; }

    public String getCity() { return city; }
    public void setCity(String aCity) { city = aCity; }

    public String getState() { return state; }
    public void setState(String aState) { state = aState; }

    public String getCountry() { return country; }
    public void setCountry(String aCountry) { country = aCountry; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String aPostalCode) { postalCode = aPostalCode; }

    public String getEmail() { return email; }
    public void setEmail(String anEmail) { email = anEmail; }
    
    public String getMethod() { return method; }
    public void setMethod(String anMethod) { method = anMethod; }
}