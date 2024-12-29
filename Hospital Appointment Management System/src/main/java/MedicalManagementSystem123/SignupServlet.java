package MedicalManagementSystem123;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_management?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Load MySQL driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL query to insert the user details
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

            // Execute the update and check if it's successful
            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("<h3>Registration successful! <a href='login.html'>Login here</a></h3>");
            } else {
                out.println("<h3>Error in registration.</h3>");
            }

            // Close the connection after operation
            con.close();

        } catch (ClassNotFoundException e) {
            // Handle class not found exception for MySQL driver
            out.println("<h3>MySQL JDBC Driver not found!</h3>");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQL exceptions
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } catch (Exception e) {
            // Catch any other exceptions
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>This servlet handles form submissions only. Please submit the form to register.</h3>");
    }
}
