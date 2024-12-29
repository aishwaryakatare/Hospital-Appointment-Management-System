package MedicalManagementSystem123;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.DBconnection;


@WebServlet("/DoctorRegistrationServlet")
public class DoctorRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String specialty = request.getParameter("specialty"); // Matches form input name
        int experience = Integer.parseInt(request.getParameter("experience"));
        double fees = Double.parseDouble(request.getParameter("fees"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Establish database connection
            Connection con = DBconnection.getConnection();
            
            // Prepare SQL query for insertion
            String query = "INSERT INTO doctors (doctor_name, specialty, experience, fees, email, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, specialty);
            pst.setInt(3, experience);
            pst.setDouble(4, fees);
            pst.setString(5, email);
            pst.setString(6, password);

            // Execute the query
            int rows = pst.executeUpdate();

            // Handle response
            if (rows > 0) {
                response.sendRedirect("Doctorsuccess.jsp"); // Redirect to success page
            } else {
                response.getWriter().println("Failed to register doctor.");
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
