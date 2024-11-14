package com.pojo;

public class User {
    private String email;
    private String otp;
    private boolean otpVerified;

    // Default constructor
    public User() {}

    // Constructor with fields
    public User(String email, String otp) {
        this.email = email;
        this.otp = otp;
        this.otpVerified = false; // OTP is not verified by default
    }

    // Getters and Setters for the fields
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isOtpVerified() {
        return otpVerified;
    }

    public void setOtpVerified(boolean otpVerified) {
        this.otpVerified = otpVerified;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", otp=" + otp + ", otpVerified=" + otpVerified + "]";
    }
}
