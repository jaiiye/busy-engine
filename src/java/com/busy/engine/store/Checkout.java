
package com.busy.engine.store;

import com.busy.engine.store.ShoppingCart;
import com.busy.engine.entity.Address;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Checkout extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();   
        
        Address shipping = new Address();
        Address billing = new Address();
        
        try 
        {
            // First get the shipping values from the request.            
            shipping.setRecipient(request.getParameter("ship_fname")+ " " + request.getParameter("ship_lname"));
            shipping.setAddress1(request.getParameter("ship_address1"));
            shipping.setAddress2(request.getParameter("ship_address2"));
            shipping.setCity(request.getParameter("ship_city"));
            shipping.setStateProvince(request.getParameter("ship_state"));
            shipping.setZipPostalCode(request.getParameter("ship_postalCode"));
            shipping.setCountry(request.getParameter("ship_country"));
            //shipping.setEmail(request.getParameter("ship_email"));
            //shipping.setMethod(request.getParameter("txtShippingText"));

            // Next, get the billing values.       
            billing.setRecipient(request.getParameter("ship_fname")+ " " + request.getParameter("ship_lname"));            
            billing.setAddress1(request.getParameter("bill_address1"));
            billing.setAddress2(request.getParameter("bill_address2"));
            billing.setCity(request.getParameter("bill_city"));
            billing.setStateProvince(request.getParameter("bill_state"));
            billing.setZipPostalCode(request.getParameter("bill_postalCode"));
            billing.setCountry(request.getParameter("bill_country"));
            
//            billing.setPhone(request.getParameter("bill_phone"));            
//            billing.setCardType(request.getParameter("CardType"));
//            billing.setAccountNumber(request.getParameter("bill_card_number"));
//            billing.setExpirationDate(request.getParameter("ExpMon") + "" + request.getParameter("ExpYear"));
//            billing.setCvv2(request.getParameter("CVV2"));
    
            HttpSession session = request.getSession();

            // Get the cart.
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

            // If there is no shopping cart, create one (this should really be an error).
            if (cart == null)
            {
                cart = new ShoppingCart();
                session.setAttribute("ShoppingCart", cart);
            }

            try
            {      
                cart.setShippingAddress(shipping);
                cart.setBillingAddress(billing);                    
                
                //see which percentage belongs to which state
                //calculate the tax amount for a given SubTotalPrice
                //calculate shipping amount and save it in the shopping cart
                //double[] shippingAmounts = Database.getShippingRates(shipping.getMethod());                
                //System.out.println("Shipping method user chose is: " + shipping.getMethod());

                //take care of shipping amount
                if(request.getParameter("txtShippingText") != null)
                {
                    //cart.getShippingAddress().setMethod(request.getParameter("txtShippingText"));
                    cart.finalizeShipping(request.getParameter("txtShippingText"));
                }                
                
                //cart.finalizeShipping(shippingAmounts[0] + (cart.getTotalQuantity()-1)*shippingAmounts[1] );
                cart.finalizeTax();
                
                //set the session's checkout method to DirectPayment
                session.setAttribute("CheckoutMethod", "Direct");

                // Now process the order and show final results to the user
                response.sendRedirect(response.encodeRedirectURL("complete.jsp"));
            }
            catch (Exception exc)
            {
                out = response.getWriter();

                out.println("<html><body><h1>Checkout Servlet Error</h1>");
                out.println("The following error occurred while processing your order:");
                out.println("<pre>");
                out.println(exc.getMessage());
                out.println("</pre>");
                out.println("</body></html>");
                return;
            }
        } 
        finally 
        { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
