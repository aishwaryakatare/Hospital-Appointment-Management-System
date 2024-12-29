package MedicalManagementSystem123;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import db.DBconnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewDoctorsServlet")
public class ViewDoctorsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the list of doctors from the database
            Connection con = DBconnection.getConnection();
            String query = "SELECT doctor_id, doctor_name, specialty, experience, fees FROM doctors";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            List<Doctor> doctorList = new ArrayList<>();
            
            while (rs.next()) {
                int doctorId = rs.getInt("doctor_id");
                String doctorName = rs.getString("doctor_name");
                String specialty = rs.getString("specialty");
                int experience = rs.getInt("experience");
                double fees = rs.getDouble("fees");
                
                // Add doctor details to the list
                Doctor doctor = new Doctor(doctorId, doctorName, specialty, experience, fees);
                doctorList.add(doctor);
            }
            
            // Set the doctor list as a request attribute with proper generics
            request.setAttribute("doctorList", doctorList);
            
            // Forward to the JSP page
            request.getRequestDispatcher("view-doctors.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

