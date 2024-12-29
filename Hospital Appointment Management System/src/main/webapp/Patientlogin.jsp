<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Login</title>
   <link rel="stylesheet" href="css/patient-login.css">
</head>
<body>
    <form action="PatientLoginServlet" method="post" autocomplete="off">
        <h2>Patient Login</h2>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  autocomplete="off" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" autocomplete="off" required>
        
        <button type="submit">Login</button>
    </form>
</body>
</html>
