


































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
import com.busy.engine.service.OrderServiceImpl;
import static com.busy.engine.web.AbstractHandler.getJsonErrorMsg;
import static com.busy.engine.web.SecurityHelper.getSessionUser;


@WebServlet("/rest/Order/*")
public class OrderHandler extends AbstractHandler
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
                        generateFindServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).find(sessionUser.getUsername(), Integer.parseInt(getRequiredParameter(request, "orderId"))), out);
                        break;
                    case "findAll":
                        generateFindAllServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).findAll(sessionUser.getUsername()), out);
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
                        generateStoreServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).store(sessionUser.getUsername(), getIntegerValue(obj.get("orderId")), getOperatingDateFormat().parse(obj.getString("orderDate")), getOperatingDateFormat().parse(obj.getString("shipDate")), obj.getString("paymentMethod"), obj.getString("purchaseOrder"), obj.getString("transactionId"), obj.getJsonNumber("amountBilled").doubleValue(), obj.getString("paymentStatus"), obj.getString("pendingReason"), obj.getString("paymentType"), obj.getJsonNumber("transactionFee").doubleValue(), obj.getString("currencyCode"), obj.getString("payerId"), obj.getJsonNumber("subtotalAmount").doubleValue(), obj.getJsonNumber("discountAmount").doubleValue(), obj.getJsonNumber("taxAmount").doubleValue(), obj.getJsonNumber("shippingAmount").doubleValue(), obj.getJsonNumber("totalAmount").doubleValue(), obj.getJsonNumber("refundAmount").doubleValue(), obj.getString("notes"), getIntegerValue(obj.get("orderStatus")), getIntegerValue(obj.get("shippingId")), getIntegerValue(obj.get("affiliateId"))), out);
                        break;
                    case "remove":
                        generateRemoveServiceResult(new OrderServiceImpl(request.getSession().getServletContext()).remove(sessionUser.getUsername(), getIntegerValue(obj.get("orderId"))), out);
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
        return "Handles the requests for Order resource with the following format: rest/Order/{Id:optional}";
    }
}

