package com.eshop.web.jdbc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" }) // Apply the filter to all resources
public class AuthFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        // Any initialization code can be added here if required
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        // Extract the path of the requested resource
        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Paths that should be publicly accessible
        if (path.startsWith("/css") || 
            path.startsWith("/images") || 
            path.startsWith("/js") || 
            path.equals("/style.css") ||
            path.equals("/login.jsp") ||
            path.equals("/register.jsp") ||
            path.equals("/LoginServlet") ||
            path.equals("/add-user-form.jsp") ||
            path.equals("/update-user-form.jsp") ||
            path.equals("/list-users.jsp") ||
            path.equals("/admin.jsp") ||
            path.equals("/RegisterServlet")) {
            // Public path, do not apply the filter, just continue the chain
            chain.doFilter(request, response);
        } else {
            // Protected path, apply the filter
            // Check if there is a user object in the session
            HttpSession session = request.getSession(false); // false means do not create a new session
            if (session != null && session.getAttribute("user") != null) {
                // User is logged in, proceed with the request
                chain.doFilter(request, response);
            } else {
                // No user is logged in, redirect to the login page
                response.sendRedirect(request.getContextPath() + "/login.jsp"); // Adjust if the login URL is different
            }
        }
    }

    public void destroy() {
        // Any cleanup code can be added here if required
    }
}