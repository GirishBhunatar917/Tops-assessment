package com.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DatabaseUtils;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        String username = (String) request.getSession().getAttribute("username");

        // Validate that the new password and confirm password match
        if (!newPassword.equals(confirmNewPassword)) {
            response.sendRedirect("change-password.jsp?error=New passwords do not match");
            return;
        }

        // Check if the old password matches the one in the database
        if (!validateOldPassword(username, oldPassword)) {
            response.sendRedirect("change-password.jsp?error=Old password is incorrect");
            return;
        }

        // Update the password in the database
        updatePasswordInDatabase(username, newPassword);

        // Invalidate the session and redirect to the login page
        request.getSession().invalidate();
        response.sendRedirect("login.jsp?success=Password changed successfully");
    }

    // Validate old password
    private boolean validateOldPassword(String username, String oldPassword) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String query = "SELECT password FROM student WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(oldPassword); // In a real system, you would compare hashed passwords
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update the new password in the database
    private void updatePasswordInDatabase(String username, String newPassword) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String query = "UPDATE student SET password = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newPassword); // You should hash the password before saving it
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
