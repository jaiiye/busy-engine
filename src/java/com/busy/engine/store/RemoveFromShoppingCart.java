
package com.busy.engine.store;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveFromShoppingCart extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            // Get the index of the item to remove.
            int itemIndex = Integer.parseInt(request.getParameter("item"));

            HttpSession session = request.getSession();

            // Get the cart.
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

            // If there is no shopping cart, create one.
            if (cart == null)
            {
                cart = new ShoppingCart();
                session.setAttribute("ShoppingCart", cart);
            }

            cart.removeItem(itemIndex);

            // Now display the cart and allow the user to check out or order more items.
            response.sendRedirect("cart.jsp");

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
