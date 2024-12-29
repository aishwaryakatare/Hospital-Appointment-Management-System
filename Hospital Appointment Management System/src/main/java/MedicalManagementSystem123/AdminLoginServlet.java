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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection con = DBconnection.getConnection()) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                response.sendRedirect("AdminDasboardServlet");
            } else {
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h2>Invalid Username or Password</h2>");
                response.getWriter().println("<a href='admin-login.html'>Try Again</a>");
                response.getWriter().println("</body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
