package com.busy.engine.web;

import com.busy.engine.entity.User;
import com.busy.engine.service.UserService;
import com.busy.engine.service.UserServiceImpl;
import com.busy.engine.vo.Result;
import static com.busy.engine.web.AbstractHandler.getJsonErrorMsg;
import static com.busy.engine.web.SecurityHelper.SESSION_ATTRIB_USER;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/rest/security/*")
public class SecurityHandler extends AbstractHandler 
{
    protected UserService userService = new UserServiceImpl();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();
        
        if(pathInfo.contains("logout"))
        {            
            response.setContentType("application/json");
            HttpSession session = request.getSession(true);
            session.removeAttribute(SESSION_ATTRIB_USER);
            out.print(getJsonSuccessMsg("User logged out..."));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();
        
        if(pathInfo.contains("logon"))
        {
            response.setContentType("application/json");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if(username == null || password == null)
            {
                out.print(getJsonErrorMsg("Invalid username OR password"));
            }
            else
            {
                Result<User> ar = userService.findByUsernamePassword(username, password);

                if (ar.isSuccess()) 
                {
                    User user = ar.getData();
                    HttpSession session = request.getSession(true);
                    session.setAttribute(SESSION_ATTRIB_USER, user);            
                    out.print(getJsonSuccessData(user));
                } 
                else 
                {
                    out.print(getJsonErrorMsg(ar.getMsg()));
                }
            }            
        }
    }

    @Override
    public String getServletInfo() 
    {
        return "Handles the login and logout of restful api users";
    }
}
