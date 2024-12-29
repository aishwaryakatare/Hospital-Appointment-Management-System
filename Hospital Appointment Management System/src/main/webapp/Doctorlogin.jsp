<!DOCTYPE html>
<html>
<head>
    <title>Doctor Login</title>
    <link rel="stylesheet" href="css/doctor-login.css">
    
    
</head>
<body>
    <form action="DoctorLoginServlet" method="post">
        <h2>Doctor Login</h2>
        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <button type="submit">Login</button>
    </form>
</body>
</html>
