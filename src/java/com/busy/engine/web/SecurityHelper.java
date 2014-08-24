package com.busy.engine.web;

import com.busy.engine.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityHelper 
{
    public static final String SESSION_ATTRIB_USER = "sessionuser";

    public static User getSessionUser(HttpServletRequest request) 
    {
        User user = null;
        HttpSession session = request.getSession(true);
        Object obj = session.getAttribute(SESSION_ATTRIB_USER);

        if (obj != null && obj instanceof User) 
        {
            user = (User) obj;
        }

        return user;
    }
    
    public static void setSessionUser(HttpServletRequest request, User user) 
    {        
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_ATTRIB_USER, user);
    }

}
