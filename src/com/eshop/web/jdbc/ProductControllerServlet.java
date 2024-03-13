package com.eshop.web.jdbc;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDbUtil productDbUtil;

    @Resource(name="jdbc/web_gameshop")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        productDbUtil = new ProductDbUtil(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productDbUtil.getProducts();
            request.setAttribute("PRODUCT_LIST", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}