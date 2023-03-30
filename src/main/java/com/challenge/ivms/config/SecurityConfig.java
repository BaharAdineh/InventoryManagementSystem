package com.challenge.ivms.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SecurityConfig", urlPatterns = {"/secure"}, initParams = {
        @WebInitParam(name = "username", value = "admin"),
        @WebInitParam(name = "password", value = "secret")})
@ServletSecurity
public class SecurityConfig extends HttpServlet {

    private String username;
    private String password;

    @Override
    public void init() throws ServletException {
        super.init();
        username = getInitParameter("username");
        password = getInitParameter("password");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String user = request.getParameter("username");
        final String pass = request.getParameter("password");

        if (username.equals(user) && password.equals(pass)) {
            response.setContentType("text/html");
            response.getWriter().println("<html><body><h1>Hello, secure world!</h1></body></html>");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
