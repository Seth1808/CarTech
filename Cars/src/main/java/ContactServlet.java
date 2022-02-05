

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Step 1: Prepare list of variables used for database connections
		 private String jdbcURL = "jdbc:mysql://localhost:3306/contactdetails";
		 private String jdbcUsername = "root";
		 private String jdbcPassword = "";
		 //Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
		 private static final String INSERT_CONTACT_SQL = "INSERT INTO contactdetails" + " (name, position,phonenumber email) VALUES " +" (?, ?, ?);";
		private static final String SELECT_CONTACT_BY_ID = "select name,position,phonenumber,email from contactdetails where name =?";
		private static final String SELECT_ALL_CONTACTS = "select * from contactdetails ";
		private static final String DELETE_CONTACTS_SQL = "delete from contactdetails where name = ?;";
		private static final String UPDATE_CONTACTS_SQL = "update contactdetails set name = ?,position= ?,phonenumber =?,email =? where name = ?;";//Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
		 protected Connection getConnection() {
		 Connection connection = null;
		 try {
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		 } catch (SQLException e) {
		 e.printStackTrace();
		 } catch (ClassNotFoundException e) {
		 e.printStackTrace();
		 }
		 return connection;
		 }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		 try {
		 switch (action) {
		 case "/ContactServlet/delete":
		 deleteContact(request, response);
		 break;
		 case "/ContactServlet/edit":
		 showEditForm(request, response);
		 break;
		 case "/ContactServlet/update":
		 updateContact(request, response);
		 break;
		 case "/ContactServlet/dashboard":
		 listContacts(request, response);
		 break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 } }
	//method to get parameter, query database for existing user data and redirect to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException {
	//get parameter passed in the URL
	String name = request.getParameter("name");
	contact existingUser = new contact("", "", "", "");
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
	// Step 2:Create a statement using connection object
	PreparedStatement preparedStatement =
	connection.prepareStatement(SELECT_CONTACT_BY_ID);) {
	preparedStatement.setString(1, name);
	// Step 3: Execute the query or update query
	ResultSet rs = preparedStatement.executeQuery();
	// Step 4: Process the ResultSet object
	while (rs.next()) {
	name = rs.getString("name");
	String position = rs.getString("position");
	String phonenumber = rs.getString("phonenumber");
	String email = rs.getString("email");
	existingUser = new contact(name, position,phonenumber, email);
	}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	//Step 5: Set existingUser to request and serve up the userEdit form
	request.setAttribute("contact", existingUser);
	request.getRequestDispatcher("/contactEdit.jsp").forward(request, response);
	}
	
	//method to update the user table base on the form data
	private void updateContact(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String oriName = request.getParameter("oriName");
	 String name = request.getParameter("name");
	 String position = request.getParameter("position");
	 String phonenumber = request.getParameter("phonenumber");
	 String email = request.getParameter("email");


	 //Step 2: Attempt connection with database and execute update user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(UPDATE_CONTACTS_SQL);) {
	 statement.setString(1, name);
	 statement.setString(2, position);
	 statement.setString(3, phonenumber);
	 statement.setString(4, email);
	 statement.setString(5, oriName);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8091/Cars/ContactServlet/dashboard");
	}
	//method to delete contact
	private void deleteContact(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String name = request.getParameter("name");
	 //Step 2: Attempt connection with database and execute delete user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_CONTACTS_SQL);) {
	 statement.setString(1, name);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to ContactServlet dashboard (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8091/Cars/ContactServlet/dashboard");
	}
	
		 private void listContacts(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
		 {
			 List <contact> contacts = new ArrayList <>();
			 try (Connection connection = getConnection();
			 // Step 5.1: Create a statement using connection object
			 PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_ALL_CONTACTS);) {
			 // Step 5.2: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Step 5.3: Process the ResultSet object.
			 while (rs.next()) {
			 String name = rs.getString("name");
			 String position = rs.getString("position");
			 String phonenumber = rs.getString("phonenumber");
			 String email = rs.getString("email");
			 contacts.add(new contact(name,position,phonenumber, email));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listContacts", contacts);
			request.getRequestDispatcher("/contactManagement.jsp").forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
