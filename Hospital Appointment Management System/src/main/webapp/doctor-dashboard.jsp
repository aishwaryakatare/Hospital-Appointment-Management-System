
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="db.DBconnection" %>

<%
    // Get doctor ID and name from session
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("doctorId") == null) {
        response.sendRedirect("Doctorlogin.html");
        return;
    }

    int doctorId = (Integer) currentSession.getAttribute("doctorId");
    String doctorName = (String) currentSession.getAttribute("doctorName");

    // Fetch patients who have booked appointments
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        con = DBconnection.getConnection();
        String query = "SELECT p.patient_name, a.appointment_date, a.description " +
                       "FROM appointments a " +
                       "JOIN patients p ON a.patient_id = p.patient_id " +
                       "WHERE a.doctor_id = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, doctorId);
        rs = pst.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
    <h2>Welcome, Dr. <%= doctorName %></h2>
    <h3>Your Appointments</h3>
    <table border="1">
        <tr>
            <th>Patient Name</th>
            <th>Appointment Date</th>
            <th>Description</th>
        </tr>
        <%
            while (rs.next()) {
                String patientName = rs.getString("patient_name");
                Date appointmentDate = rs.getDate("appointment_date");
                String description = rs.getString("description");
        %>
        <tr>
            <td><%= patientName %></td>
            <td><%= appointmentDate %></td>
            <td><%= description %></td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="logout">Logout</a>
</body>
</html>
<%
    } catch (Exception e) {
        e.printStackTrace();
        out.println("Error: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (pst != null) pst.close();
        if (con != null) con.close();
    }
%>
