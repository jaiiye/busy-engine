package com.transitionsoft.dao;

import java.util.ArrayList;

public class Slider
{
    public int sliderId;
    public String sliderName;
    public int sliderType;
    public int formId;
    public ArrayList<SliderItem> items;

    public Slider(int sliderId, String sliderName, int sliderType, int formId)
    {
        this.sliderId = sliderId;
        this.sliderName = sliderName;
        this.sliderType = sliderType;
        this.formId = formId;
        items = new ArrayList<SliderItem>();
    }

    public int getSliderId()
    {
        return sliderId;
    }

    public void setSliderId(int sliderId)
    {
        this.sliderId = sliderId;
    }

    public String getSliderName()
    {
        return sliderName;
    }

    public void setSliderName(String sliderName)
    {
        this.sliderName = sliderName;
    }

    public int getSliderType()
    {
        return sliderType;
    }

    public int getFormId()
    {
        return formId;
    }

    public void setFormId(int formId)
    {
        this.formId = formId;
    }

    public void setSliderType(int sliderType)
    {
        this.sliderType = sliderType;
    }

    public ArrayList<SliderItem> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<SliderItem> items)
    {
        this.items = items;
    }

}
