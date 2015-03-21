


































package com.busy.engine.web;

import com.busy.engine.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import com.busy.engine.util.PathProcessor;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.busy.engine.service.SiteServiceImpl;
import static com.busy.engine.web.AbstractHandler.getJsonErrorMsg;
import static com.busy.engine.web.SecurityHelper.getSessionUser;


@WebServlet("/rest/Site/*")
public class SiteHandler extends AbstractHandler
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        User sessionUser = getSessionUser(request);
        if (sessionUser == null)
        {
            out.print(getJsonErrorMsg("User is not logged on"));
        }
        else
        {
            try
            {
                switch (new PathProcessor(request.getPathInfo()).getOperation())
                {
                    case "find":
                        generateFindServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "siteId"))), out);
                        break;
                    case "findAll":
                        generateFindAllServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
                        break;
                    default:
                        out.print(getJsonErrorMsg("Invalid Operation"));
                        break;
                }
            }
            catch (Exception ex)
            {
                out.print(getJsonErrorMsg(ex.getMessage()));
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        User sessionUser = getSessionUser(request);
        if (sessionUser == null)
        {
            out.print(getJsonErrorMsg("User is not logged on"));
        }
        else
        {
            try
            {
                JsonObject obj = parseJsonObject(getRequiredParameter(request, "data"));

                switch (new PathProcessor(request.getPathInfo()).getOperation())
                {
                    case "store":
                        generateStoreServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("siteId")), obj.getString("siteName"), obj.getString("domain"), getIntegerValue(obj.get("mode")), obj.getString("url"), obj.getString("logoTitle"), obj.getString("logoImageUrl"), getIntegerValue(obj.get("useAsStore")), getIntegerValue(obj.get("siteStatus")), obj.getString("locale"), getIntegerValue(obj.get("templateId")), getIntegerValue(obj.get("siteEmailId")), getIntegerValue(obj.get("dashboardId")), getIntegerValue(obj.get("tenantId"))), out);
                        break;
                    case "remove":
                        generateRemoveServiceResult(new SiteServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("siteId"))), out);
                        break;
                    default:
                        out.print(getJsonErrorMsg("Invalid Operation"));
                        break;
                }
            }
            catch (Exception ex)
            {
                out.print(getJsonErrorMsg(ex.getMessage()));
            }
        }
    }    

    @Override
    public String getServletInfo()
    {
        return "Handles the requests for Site resource with the following format: rest/Site/{Id:optional}";
    }
}

