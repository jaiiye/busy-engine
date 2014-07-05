package com.transitionsoft.dao;

public class Option 
{
    private String optionId;
    private String optionType;
    private String optionAvailability;
    private String optionAvailableQuantity;

    public Option(String optionId, String optionType, String optionAvailability, String optionAvailableQuantity) {
        this.optionId = optionId;
        this.optionType = optionType;
        this.optionAvailability = optionAvailability;
        this.optionAvailableQuantity = optionAvailableQuantity;
    }

    public String getOptionId() {
        return optionId;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionAvailability() {
        return optionAvailability;
    }

    public void setOptionAvailability(String optionAvailability) {
        this.optionAvailability = optionAvailability;
    }

    public String getOptionAvailableQuantity() {
        return optionAvailableQuantity;
    }

    public void setOptionAvailableQuantity(String optionAvailableQuantity) {
        this.optionAvailableQuantity = optionAvailableQuantity;
    }
    
    
}
