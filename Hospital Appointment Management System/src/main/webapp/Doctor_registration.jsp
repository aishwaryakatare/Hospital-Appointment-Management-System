<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor Registration</title>
    <link rel="stylesheet" href="css/doctor-registration.css">
</head>
<body>
    <h2>Doctor Registration</h2>
    <form action="DoctorRegistrationServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Aryan subhedar" required><br>
        
        <label for="specialty">Specialty:</label>
        <input type="text" id="specialty" name="specialty" placeholder="Dentist" required><br>
        
        <label for="experience">Experience (in years):</label>
        <input type="number" id="experience" name="experience" placeholder="4" required><br>
        
        <label for="fees">Fees:</label>
        <input type="number" id="fees" name="fees" step="0.01"  placeholder="100" required><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  placeholder="abc@gmail.com" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"  placeholder="*****" required><br>
        
        <button type="submit">Register</button>
        <p>Already have an account? <a href="Doctorlogin.html">Login here</a></p>
    </form>
    
</body>
</html>
