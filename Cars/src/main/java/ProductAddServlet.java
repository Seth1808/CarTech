

import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductAddServlet
 */
@WebServlet("/ProductAddServlet")
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		//Step 2: retrieve the four parameters from the request from the web form
		String a = request.getParameter("name");
		String b = request.getParameter("description");
		String c = request.getParameter("price");
		String d = request.getParameter("specs");
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/carproducts", "root", "password");
		
		PreparedStatement ps = con.prepareStatement("insert into PRODUCTS values(?,?,?,?)");
		
		ps.setString(1, a);
		ps.setString(2, b);
		ps.setString(3, c);
		ps.setString(4, d);
		
		int i = ps.executeUpdate();
		
		if (i > 0){
			PrintWriter writer = response.getWriter();
			writer.println("<h1>" + "You have successfully registered a Product!" +
			"</h1>");
			writer.close();
			}
			}
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
			}
			doGet(request, response);
	}

}
