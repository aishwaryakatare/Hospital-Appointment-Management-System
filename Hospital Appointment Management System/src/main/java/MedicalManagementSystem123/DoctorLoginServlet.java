package MedicalManagementSystem123;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.DBconnection;


@WebServlet("/DoctorLoginServlet")
public class DoctorLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email").trim();  // Trim to remove spaces
        String password = request.getParameter("password").trim();  // Trim to remove spaces

        try {
            Connection con = DBconnection.getConnection();
            String query = "SELECT doctor_id, doctor_name FROM doctors WHERE email = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);  // If password is hashed, use Utility.hashPassword(password) here

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int doctorId = rs.getInt("doctor_id");
                String doctorName = rs.getString("doctor_name");

                HttpSession session = request.getSession();
                session.setAttribute("doctorId", doctorId);
                session.setAttribute("doctorName", doctorName);

                response.sendRedirect("doctor-dashboard.jsp");
            } else {
                response.getWriter().println("Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
