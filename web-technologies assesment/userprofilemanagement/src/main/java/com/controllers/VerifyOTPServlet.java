package com.controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/")
public class VerifyOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email and OTP from the request parameters
        String userEmail = request.getParameter("email");
        String userOTP = request.getParameter("otp");

        // Get the OTP stored in the session
        HttpSession session = request.getSession();
        String sessionOTP = (String) session.getAttribute("otp"); // Retrieve OTP stored in session

        // Validate OTP
        if (sessionOTP != null && sessionOTP.equals(userOTP)) {
            // OTP is valid, proceed with user registration or login
            request.setAttribute("success", "OTP Verified Successfully!");
            // You can redirect to a success page or continue with the registration/login flow
            response.sendRedirect("index.jsp");
        } else {
            // OTP is incorrect, show error message
            request.setAttribute("error", "Invalid OTP. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("otp-verification.jsp");
            dispatcher.forward(request, response);
        }
    }
}
