package MedicalManagementSystem123;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBconnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PatientRegistrationServlet")
public class PatientRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientName = request.getParameter("patientName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection con = DBconnection.getConnection()) {
            // Check if email already exists
            String checkQuery = "SELECT email FROM patients WHERE email = ?";
            PreparedStatement checkPst = con.prepareStatement(checkQuery);
            checkPst.setString(1, email);
            ResultSet rs = checkPst.executeQuery();

            if (rs.next()) {
                response.getWriter().println("Error: Email is already registered.");
            } else {
                // Insert new patient
                String insertQuery = "INSERT INTO patients (patient_name, email, password) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(insertQuery);
                pst.setString(1, patientName);
                pst.setString(2, email);
                pst.setString(3, password);
                int result = pst.executeUpdate();

                if (result > 0) {
                    response.sendRedirect("Patientlogin.jsp");
                } else {
                    response.getWriter().println("Registration failed. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
