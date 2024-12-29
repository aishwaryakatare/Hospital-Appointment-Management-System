<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctors List</title>
    <link rel="stylesheet" href="css/view-doctor.css">
    <style>
       
    </style>
</head>
<body>
    <h2>Available Doctors</h2>
    <table>
        <tr> 
            <th>Doctor Name</th>
            <th>Specialty</th>
            <th>Experience</th>
            <th>Fees</th>
            <th>Action</th>
        </tr>
        <c:forEach var="doctor" items="${doctorList}">
            <tr>
                <td>${doctor.doctorName}</td>
                <td>${doctor.specialty}</td>
                <td>${doctor.experience}</td>
                <td>${doctor.fees}</td>
                <td><a href="BookAppointment.jsp?doctorId=${doctor.doctorId}">Book Appointment</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
