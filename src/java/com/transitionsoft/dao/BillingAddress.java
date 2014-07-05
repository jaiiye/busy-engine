

package com.transitionsoft.dao;

public class BillingAddress implements java.io.Serializable
{
    public String firstName;
    public String lastName;    
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String country;
    public String postalCode;    
    public String phone;   
    public String cardType;   
    public String accountNumber;   
    public String expirationDate;   
    public String cvv2; 

    public BillingAddress()
    {
    }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String aName) { firstName = aName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String aName) { lastName = aName; }

    public String getAddress1() { return address1; }
    public void setAddress1(String anAddress1) { address1 = anAddress1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String anAddress2)  { address2 = anAddress2; }

    public String getCity() { return city; }
    public void setCity(String aCity) { city = aCity; }

    public String getState() { return state; }
    public void setState(String aState) { state = aState; }

    public String getCountry() { return country; }
    public void setCountry(String aCountry) { country = aCountry; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String aPostalCode) { postalCode = aPostalCode; }
    
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public String getCvv2()
    {
        return cvv2;
    }

    public void setCvv2(String cvv2)
    {
        this.cvv2 = cvv2;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}
