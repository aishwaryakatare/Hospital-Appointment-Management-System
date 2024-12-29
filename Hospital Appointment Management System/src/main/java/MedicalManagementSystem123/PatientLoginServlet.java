package MedicalManagementSystem123;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBconnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PatientLoginServlet")
public class PatientLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBconnection.getConnection();
            String query = "SELECT patient_id, patient_name FROM patients WHERE email=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                int patientId = rs.getInt("patient_id");
                String patientName = rs.getString("patient_name");
                
                // Store patient details in session
                HttpSession session = request.getSession();
                session.setAttribute("patientId", patientId);
                session.setAttribute("patientName", patientName);
                
                // Redirect to ViewDoctorsServlet (to show list of doctors)
                response.sendRedirect("ViewDoctorsServlet");
            } else {
                response.getWriter().println("Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
