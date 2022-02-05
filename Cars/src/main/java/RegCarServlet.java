
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
 * Servlet implementation class RegCarServlet
 */
@WebServlet("/RegCarServlet")
public class RegCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/registeredcars";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_CARS_SQL = "INSERT INTO RegisteredCars"
			+ " (carModel, licensePlate, warranty) VALUES " + " (?, ?, ?);";
	private static final String SELECT_CAR_BY_ID = "select carModel,licensePlate,warranty from RegisteredCars where carModel =?";
	private static final String SELECT_ALL_CARS = "select * from RegisteredCars ";
	private static final String DELETE_CARS_SQL = "delete from RegisteredCars where carModel = ?;";
	private static final String UPDATE_CARS_SQL = "update RegisteredCars set carModel = ?,licensePlate= ?, warranty =? where carModel = ?;";

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
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

	public RegCarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/UserServlet/deleteCar":
				deleteCar(request, response);
				break;
			case "/UserServlet/editCar":
				showEditForm(request, response);
				break;
			case "/UserServlet/updateCar":
				updateCar(request, response);
				break;
			case "/UserServlet/dashboardCar":
				listCars(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listCars(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<RegisteredCars> cars = new ArrayList<>();

		try (Connection connection = getConnection();

				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS);) {

			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {

				String carModel = rs.getString("carModel");
				String licensePlate = rs.getString("licensePlate");
				String warranty = rs.getString("warranty");
				cars.add(new RegisteredCars(carModel, licensePlate, warranty));

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		// Step 5.4: Set the users list into the listCars attribute to be pass to the
		// carManagement.jsp
		request.setAttribute("listCars", cars);
		request.getRequestDispatcher("/carManagement.jsp").forward(request, response);
	}

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// get parameter passed in the URL
		String carModel = request.getParameter("carModel");

		RegisteredCars existingCar = new RegisteredCars("", "", "");

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID);) {

			preparedStatement.setString(1, carModel);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object
			while (rs.next()) {

				carModel = rs.getString("carModel");
				String licensePlate = rs.getString("licensePlate");
				String warranty = rs.getString("warranty");
				existingCar = new RegisteredCars(carModel, licensePlate, warranty);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("user", existingCar);
		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}

	// method to update the user table base on the form data
	private void updateCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		// Step 1: Retrieve value from the request
		String oricarModel = request.getParameter("oricarModel");
		String carModel = request.getParameter("carModel");
		String licensePlate = request.getParameter("licensePlate");
		String warranty = request.getParameter("warranty");
		

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CARS_SQL);) {

			statement.setString(1, carModel);
			statement.setString(2, licensePlate);
			statement.setString(3, warranty);
			statement.setString(5, oricarModel);
			int i = statement.executeUpdate();

		}

		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("http://localhost:8090/HelloWorldJavaEE/UserServlet");
	}

	// method to delete car
	private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		// Step 1: Retrieve value from the request
		String carModel = request.getParameter("carModel");
		
		// Step 2: Attempt connection with database and execute delete car SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CARS_SQL);) {
			
			statement.setString(1, carModel);
			int i = statement.executeUpdate();
			
		}
		
		// Step 3: redirect back to UserServlet dashboard 
		response.sendRedirect("http://localhost:8090/HelloWorldJavaEE/UserServlet/dashboard");
	}

}
