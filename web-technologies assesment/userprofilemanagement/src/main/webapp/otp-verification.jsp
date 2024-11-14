<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OTP Verification</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f9;
            margin-top: 50px;
        }
        .container {
            max-width: 500px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.1);
        }
        .form-title {
            text-align: center;
            margin-bottom: 30px;
        }
        .alert {
            display: none;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="form-title">OTP Verification</h2>

    <!-- Display error or success messages from the backend -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
            ${success}
        </div>
    </c:if>

    <form id="otpForm" action="VerifyOTPServlet" method="post">
        <!-- OTP input field -->
        <div class="form-group">
            <label for="otp">Enter OTP:</label>
            <input type="text" class="form-control" id="otp" name="otp" required>
        </div>

        <!-- Hidden field to store email address -->
        <input type="hidden" id="email" name="email" value="${User.email}" />

        <button type="submit" class="btn btn-primary btn-block">Verify OTP</button>
    </form>

    <br />
    <div class="text-center">
        <a href="resendOTP.jsp">Didn't receive the OTP? Resend</a>
    </div>
</div>

<!-- Optional: Add jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // Optional client-side validation for OTP format
    $(document).ready(function () {
        $('#otpForm').submit(function (e) {
            var otp = $('#otp').val();
            if (otp.length !== 4) {
                e.preventDefault();
                alert('OTP should be 4 digits');
            }
        });
    });
</script>

</body>
</html>
