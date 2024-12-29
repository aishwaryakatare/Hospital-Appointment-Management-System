package MedicalManagementSystem123;

import db.DBconnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminDasboardServlet")
public class AdminDasboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (Connection con = DBconnection.getConnection()) {
            // SQL Queries
            String totalDoctorsQuery = "SELECT COUNT(*) FROM doctors";
            String totalPatientsQuery = "SELECT COUNT(*) FROM patients";
            String totalAppointmentsQuery = "SELECT COUNT(*) FROM appointments";
            String patientsTodayQuery = "SELECT COUNT(*) FROM appointments WHERE appointment_date = CURDATE()";
            String patientsTomorrowQuery = "SELECT COUNT(*) FROM appointments WHERE appointment_date = CURDATE() + INTERVAL 1 DAY";

            // Prepare Statements
            PreparedStatement pstDoctors = con.prepareStatement(totalDoctorsQuery);
            PreparedStatement pstPatients = con.prepareStatement(totalPatientsQuery);
            PreparedStatement pstAppointments = con.prepareStatement(totalAppointmentsQuery);
            PreparedStatement pstPatientsToday = con.prepareStatement(patientsTodayQuery);
            PreparedStatement pstPatientsTomorrow = con.prepareStatement(patientsTomorrowQuery);

            // Execute Queries
            ResultSet rsDoctors = pstDoctors.executeQuery();
            ResultSet rsPatients = pstPatients.executeQuery();
            ResultSet rsAppointments = pstAppointments.executeQuery();
            ResultSet rsPatientsToday = pstPatientsToday.executeQuery();
            ResultSet rsPatientsTomorrow = pstPatientsTomorrow.executeQuery();

            rsDoctors.next();
            int totalDoctors = rsDoctors.getInt(1);

            rsPatients.next();
            int totalPatients = rsPatients.getInt(1);

            rsAppointments.next();
            int totalAppointments = rsAppointments.getInt(1);

            rsPatientsToday.next();
            int patientsToday = rsPatientsToday.getInt(1);

            rsPatientsTomorrow.next();
            int patientsTomorrow = rsPatientsTomorrow.getInt(1);

           
            
            response.getWriter().println("<html><head><style>");
            response.getWriter().println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
            response.getWriter().println(".header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }");
            response.getWriter().println(".header h1 { margin: 0; font-size: 2.5em; }");
            response.getWriter().println(".container { display: flex; flex-wrap: wrap; justify-content: space-around; padding: 20px; }");
            response.getWriter().println(".card { background-color: white; padding: 20px; margin: 10px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); flex: 1; min-width: 200px; text-align: center; }");
            response.getWriter().println(".card h3 { margin: 10px 0; }");
            response.getWriter().println(".card p { font-size: 24px; font-weight: bold; color: #4CAF50; }");
            response.getWriter().println(".card img { width: 100px; height: 100px; margin-bottom: 10px; border-radius: 50%; }");
            response.getWriter().println("</style></head><body>");

            response.getWriter().println("<div class='header'><h1>Admin Dashboard</h1><p>Overview of Medical Management System</p></div>");
            response.getWriter().println("<div class='container'>");

            response.getWriter().println("<div class='card'><img src='images/doctor.jpeg' alt='Doctors' />");
            response.getWriter().println("<h3>Total Doctors</h3><p>" + totalDoctors + "</p></div>");

            response.getWriter().println("<div class='card'><img src='images/Patients.jpg' alt='Patients' />");
            response.getWriter().println("<h3>Total Patients</h3><p>" + totalPatients + "</p></div>");

            response.getWriter().println("<div class='card'><img src='images/TotalAppointments.jpeg' alt='Appointments' />");
            response.getWriter().println("<h3>Total Appointments</h3><p>" + totalAppointments + "</p></div>");

            response.getWriter().println("<div class='card'><img src='images/TodayApp.jpg' alt='Today Patients' />");
            response.getWriter().println("<h3>Patients Today</h3><p>" + patientsToday + "</p></div>");

            response.getWriter().println("<div class='card'><img src='images/Tomorrow.png' alt='Tomorrow Patients' />");
            response.getWriter().println("<h3>Patients Tomorrow</h3><p>" + patientsTomorrow + "</p></div>");

            response.getWriter().println("</div></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
