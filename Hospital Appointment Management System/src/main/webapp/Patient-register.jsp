<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Registration</title>
    <link rel="stylesheet" href="css/patient-register.css">
    
</head>
<body>
    <div class="container">
        <h2>Patient Registration</h2>
        <form action="PatientRegistrationServlet" method="post" autocomplete="off">
    <label for="patientName">Full Name:</label>
    <input type="text" id="patientName" name="patientName" autocomplete="off" required>
    
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" autocomplete="off" required>
    
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" autocomplete="off" required>
    
    <button type="submit">Register</button>
    <p>Already have an account? <a href="Patientlogin.jsp">Login here</a></p>
</form>

    </div>
</body>
</html>
