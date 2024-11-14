package com.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DatabaseUtils;
import com.dao.EmailUtils;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Check if email exists in the database
        if (!checkEmailExists(email)) {
            response.sendRedirect("forgot-password.jsp?error=Email not found");
            return;
        }

        // Generate OTP for resetting password
        String otp = generateOTP();
        
        // Send OTP to the email
        sendOTPEmail(email, otp);

        // Save OTP in the database (for comparison during reset)
        saveOTPInDatabase(email, otp);

        // Redirect to OTP verification page
        response.sendRedirect("reset-password.jsp?email=" + email);
    }

    // Check if the email exists in the database
    private boolean checkEmailExists(String email) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String query = "SELECT * FROM student WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Generate a 4-digit OTP
    private String generateOTP() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    // Send OTP via email
    private void sendOTPEmail(String email, String otp) {
        String subject = "Password Reset OTP";
        String body = "Your OTP for resetting your password is: " + otp;
        EmailUtils.sendEmail(email, subject, body);
    }

    // Save the OTP in the database to verify during password reset
    private void saveOTPInDatabase(String email, String otp) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String query = "UPDATE student SET otp = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, otp);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
