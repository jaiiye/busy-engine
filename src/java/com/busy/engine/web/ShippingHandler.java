


































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
import com.busy.engine.service.ShippingServiceImpl;
import static com.busy.engine.web.AbstractHandler.getJsonErrorMsg;
import static com.busy.engine.web.SecurityHelper.getSessionUser;


@WebServlet("/rest/Shipping/*")
public class ShippingHandler extends AbstractHandler
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
                        generateFindServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "shippingId"))), out);
                        break;
                    case "findAll":
                        generateFindAllServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
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
                        generateStoreServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("shippingId")), obj.getString("methodName"), obj.getJsonNumber("quantity").doubleValue(), obj.getString("unitOfMeasure"), obj.getJsonNumber("ratePerUnitCost").doubleValue(), obj.getJsonNumber("additionalCost").doubleValue(), getIntegerValue(obj.get("stateProvinceId")), getIntegerValue(obj.get("countryId"))), out);
                        break;
                    case "remove":
                        generateRemoveServiceResult(new ShippingServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("shippingId"))), out);
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
        return "Handles the requests for Shipping resource with the following format: rest/Shipping/{Id:optional}";
    }
}
