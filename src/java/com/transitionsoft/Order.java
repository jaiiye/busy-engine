package com.transitionsoft;

import com.transitionsoft.dao.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Order extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        try 
        {
            // First get the shipping values from the request.
            HttpSession session = request.getSession();

            // Get the cart.
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

            // If there is no shopping cart, create one (this should really be an error).
            if (cart == null)
            {
                out.println("<html><body><h1>Error</h1>");
                out.println("The following error occurred while processing your order:");
                out.println("<pre>");
                out.println("Your shopping cart is empty. We are aware of this situation and promptly look into the matter.");
                out.println("</pre>");
                out.println("</body></html>");
            }

            try
            {              
                //take care of shipping amount
                if(request.getParameter("txtShippingText") != null)
                {
                    cart.getShippingAddress().setMethod(request.getParameter("txtShippingText"));
                    cart.finalizeShipping(request.getParameter("txtShippingText"));
                }

                // Now display the cart and allow the user to check out
                response.sendRedirect(response.encodeRedirectURL("complete.jsp"));
            }
            catch (Exception exc)
            {
                out = response.getWriter();

                out.println("<html><body><h1>Error</h1>");
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
