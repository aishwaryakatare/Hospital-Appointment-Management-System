package MedicalManagementSystem123;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBconnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String appointmentDate = request.getParameter("appointmentDate");
        String description = request.getParameter("description");
        
        // Retrieve patient details from session
        HttpSession session = request.getSession();
        int patientId = (int) session.getAttribute("patientId");
        
        try {
            Connection con = DBconnection.getConnection();
            String query = "INSERT INTO appointments (doctor_id, patient_id, appointment_date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, doctorId);
            pst.setInt(2, patientId);
            pst.setString(3, appointmentDate);
            pst.setString(4, description);
            int result = pst.executeUpdate();
            
            // HTML and CSS styling for success or failure messages
            response.setContentType("text/html");
            response.getWriter().println("<html><head><style>");
            response.getWriter().println("body { font-family: Arial, sans-serif; }");
            response.getWriter().println(".success { color: green; font-size: 18px; background-color: #d4edda; padding: 10px; border: 1px solid green; border-radius: 5px; margin: 10px 0; }");
            response.getWriter().println(".failure { color: red; font-size: 18px; background-color: #f8d7da; padding: 10px; border: 1px solid red; border-radius: 5px; margin: 10px 0; }");
            response.getWriter().println("</style></head><body>");
            
            if (result > 0) {
                response.getWriter().println("<div class='success'>Appointment booked successfully!</div>");
            } else {
                response.getWriter().println("<div class='failure'>Failed to book appointment. Please try again.</div>");
            }
            
            response.getWriter().println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<div class='failure'>Error: " + e.getMessage() + "</div>");
        }
    }
}
