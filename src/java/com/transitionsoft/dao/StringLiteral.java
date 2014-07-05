package com.transitionsoft.dao;

import java.util.ArrayList;

public class StringLiteral
{
    private String textStringId;
    private String textStringKey;
    private ArrayList<LocalizedString> localizedStrings;

    public String getTextStringKey()
    {
        return textStringKey;
    }

    public void setTextStringKey(String StringKey)
    {
        this.textStringKey = StringKey;
    }

    public String getTextStringId()
    {
        return textStringId;
    }

    public void setTextStringId(String stringId)
    {
        this.textStringId = stringId;
    }

    public ArrayList<LocalizedString> getLocalizedStrings()
    {
        return localizedStrings;
    }

    public void setLocalizedStrings(ArrayList<LocalizedString> ls)
    {
        localizedStrings = ls;
    }    

    public void addToLocalizedString(LocalizedString ls)
    {
        this.localizedStrings.add(ls);
    }

    public String getStringsCommaSeperated()
    {
        StringBuilder s = new StringBuilder();
        for(LocalizedString ls : localizedStrings)
        {
            s.append(ls.getStringValue()).append(", ");
        }
        return s.toString();
    }

}
