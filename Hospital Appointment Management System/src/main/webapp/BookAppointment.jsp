<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Appointment</title>
     <link rel="stylesheet" href="css/book-appointment.css">
</head>
<body>

    <div class="form-container">
        <h2>Book Appointment</h2>
        <form action="BookAppointmentServlet" method="post">
            <input type="hidden" name="doctorId" value="${param.doctorId}">

            <label for="appointmentDate">Date:</label>
            <input type="date" id="appointmentDate" name="appointmentDate" required><br><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea><br><br>

            <button type="submit">Book Appointment</button>
        </form>
    </div>

</body>
</html>
