
package com.transitionsoft;

import com.transitionsoft.dao.ShoppingCart;
import com.transitionsoft.dao.Item;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Sourena
 */
public class AddToShoppingCart extends HttpServlet 
{   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            // First get the item values from the request.
            String productCode = request.getParameter("productCode");
            String description = request.getParameter("description");
            String optionId = request.getParameter("optionID");            
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));


            // Now create an item to add to the cart.
            Item item = new Item(productCode, description, optionId, price, quantity);

            HttpSession session = request.getSession();

            // Get the cart.
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

            // If there is no shopping cart, create one.
            if (cart == null)
            {
                cart = new ShoppingCart();
                session.setAttribute("ShoppingCart", cart);
            }

            if( (cart.getSubTotalPrice() +item.getItemPrice()) < 8500.00 )
            {
                cart.addItem(item);
                // Now display the cart and allow the user to check out or order more items.
                response.sendRedirect("cart.jsp");
            }
            else
            {
                // Now display error message
                response.sendRedirect("cart.jsp?error=Subtotal%20Amount%20Cannot%20Exceed%20$8500,%20product%20was%20not%20added%20to%20cart");
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
