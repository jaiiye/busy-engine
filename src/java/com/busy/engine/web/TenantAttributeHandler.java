


































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
import com.busy.engine.service.TenantAttributeServiceImpl;
import static com.busy.engine.web.AbstractHandler.getJsonErrorMsg;
import static com.busy.engine.web.SecurityHelper.getSessionUser;


@WebServlet("/rest/TenantAttribute/*")
public class TenantAttributeHandler extends AbstractHandler
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
                        generateFindServiceResult(new TenantAttributeServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "tenantAttributeId"))), out);
                        break;
                    case "findAll":
                        generateFindAllServiceResult(new TenantAttributeServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
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
                        generateStoreServiceResult(new TenantAttributeServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("tenantAttributeId")), obj.getString("attributeKey"), obj.getString("attributeValue"), getIntegerValue(obj.get("tenantId"))), out);
                        break;
                    case "remove":
                        generateRemoveServiceResult(new TenantAttributeServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("tenantAttributeId"))), out);
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
        return "Handles the requests for TenantAttribute resource with the following format: rest/TenantAttribute/{Id:optional}";
    }
}

