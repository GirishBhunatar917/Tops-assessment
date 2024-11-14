package com.dao;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtils {
    
    // Method to send an email
    public static void sendEmail(String to, String subject, String body) {
        // SMTP server information
        String host = "smtp.gmail.com";
        final String user = "girishbhunatar917@gmail.com";  // Sender's email ID
        final String password = "iger gtbg ntmh zaxy";  // Sender's email password (use app password for Gmail)

        // Set up the mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");  // Use 587 for TLS
        properties.put("mail.smtp.auth", "true");  // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS
        properties.put("mail.smtp.starttls.required", "true");  // Ensure STARTTLS is required
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Specify the protocol version
        properties.put("mail.smtp.tls.trust", "*");  // Allow all TLS certificates

        // Get the session object
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);  // Authentication details
            }
        });

        try {
            // Create a MimeMessage object to represent the email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));  // Sender's email
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  // Recipient's email
            message.setSubject(subject);  // Email subject
            message.setText(body);  // Email body

            // Send the email
            Transport.send(message);  // Send the message via SMTP
            System.out.println("OTP Sent Successfully");

        } catch (MessagingException mex) {
            mex.printStackTrace();  // Print any errors
        }
    }
}
