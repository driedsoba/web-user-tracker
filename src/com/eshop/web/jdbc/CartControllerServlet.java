package com.eshop.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/CartControllerServlet")
public class CartControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDbUtil productDbUtil;

    @Resource(name="jdbc/web_gameshop")
    private DataSource dataSource;

    public void init() {
        productDbUtil = new ProductDbUtil(dataSource);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        try {
            switch (command) {
                case "ADD":
                    addToCart(request);
                    break;
                case "REMOVE":
                    removeFromCart(request);
                    break;
                default:
                    break;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addToCart(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");

        if (cart == null) {
            cart = new Cart();
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productDbUtil.getProduct(productId);
        cart.addItem(product, 1); // For simplicity, we're always adding one item. You can adjust this as needed.

        session.setAttribute("CART", cart);
    }

    private void removeFromCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");

        if (cart != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            cart.removeItem(productId);

            session.setAttribute("CART", cart);
        }
    }
}
