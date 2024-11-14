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

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("signup.jsp?error=Passwords don't match");
            return;
        }

        // Validate email uniqueness
        if (checkEmailExists(email)) {
            response.sendRedirect("signup.jsp?error=Email already exists");
            return;
        }

        // Generate OTP and send email
        String otp = generateOTP();
        sendOTPEmail(email, otp);

        // Insert into database
        try {
            Connection conn = DatabaseUtils.getConnection();
            String query = "INSERT INTO student (first_name, last_name, email, password, login_status, otp) VALUES (?, ?, ?, ?, 'pending', ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);  // In a real system, hash the password
            stmt.setString(5, otp);
            stmt.executeUpdate();
            response.sendRedirect("otp-verification.jsp?email=" + email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    private String generateOTP() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    private void sendOTPEmail(String email, String otp) {
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otp;
        EmailUtils.sendEmail(email, subject, body);
    }
}
