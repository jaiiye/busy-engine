package com.busy.engine.store;

import com.busy.engine.store.ShoppingCart;
import com.busy.engine.data.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExpressCheckout extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        // Get the ServletContext (application object is JSP)
        //ServletContext application = getServletConfig().getServletContext();
        try
        {
            //==================================================================
            //    PayPal Express Checkout Call
            //===================================================================	
//            PayPalFunctions PayPal = new PayPalFunctions(Database.getPayPalInfo());
//
//            //set the payment amount with the current total payment
//            NumberFormat formatter = new DecimalFormat("0.00");
//            Double d = ((ShoppingCart) session.getAttribute("ShoppingCart")).getSubTotalPrice();
//            session.setAttribute("Payment_Amount", formatter.format(d.doubleValue()));
//            
//            String paymentAmount = (String) session.getAttribute("Payment_Amount"); 
//            System.out.println("PayPal Set to charge: $" + paymentAmount);
//
//            //-------------------------------------------------
//            //    Calls the SetExpressCheckout API call
//            //-------------------------------------------------	
//            HashMap nvp = PayPal.CallSetExpressCheckout(session, paymentAmount);            
//            String strAck = nvp.get("ACK").toString();
//
//            if(strAck !=null && strAck.equalsIgnoreCase("Success"))
//            {
//                // Redirect to paypal.com
//                PayPal.RedirectURL(response,  nvp.get("TOKEN").toString());
//
//                //set the session's checkout method to Express Checkout
//                session.setAttribute("CheckoutMethod", "Express");
//            }
//            else
//            {  
//                // Display a user friendly Error on the page using any of the information returned by PayPal
//
//                String ErrorCode         = nvp.get("L_ERRORCODE0").toString();
//                String ErrorShortMsg     = nvp.get("L_SHORTMESSAGE0").toString();
//                String ErrorLongMsg      = nvp.get("L_LONGMESSAGE0").toString();
//                String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
//
//                System.out.println("Error occured on the expresscheckout.jsp page:");
//                System.out.println("     Code: " + ErrorCode);
//                System.out.println("Short Msg: " + ErrorShortMsg);
//                System.out.println(" Long Msg: " + ErrorLongMsg);
//                System.out.println(" Severity: " + ErrorSeverityCode);
//                System.out.println("-----------------------------------------------");
//            }
        } 
        finally
        {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
