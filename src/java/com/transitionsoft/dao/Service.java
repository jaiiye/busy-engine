
package com.transitionsoft.dao;

public class Service 
{   
    private int Id;   
    private int serviceTypeId;    
    private int serviceChargeId;    
    private String serviceName;    
    private String serviceDescription;

    public Service(int Id, int serviceTypeId, int serviceChargeId, String serviceName, String serviceDescription) {
        this.Id = Id;
        this.serviceTypeId = serviceTypeId;
        this.serviceChargeId = serviceChargeId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public int getServiceChargeId() {
        return serviceChargeId;
    }

    public void setServiceChargeId(int serviceChargeId) {
        this.serviceChargeId = serviceChargeId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
    
    
}
