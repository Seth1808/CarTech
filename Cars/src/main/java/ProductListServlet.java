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
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/carproducts";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + " (name,description,price,specs) VALUES " + " (?, ?, ?)";
	private static final String SELECT_PRODUCTS_BY_ID = "select name,description,price,specs from products where name=?";
	private static final String SELECT_ALL_PRODUCTS = "select * from products";
	private static final String DELETE_PRODUCTS_SQL = "delete from products where name = ?";
	private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,description= ?,price =?,specs =? where name = ?";

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
	public ProductListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/ProductListServlet/delete":
		deleteProduct(request, response);
		break;
		case "/UProductListServlet/edit":
		showEditForm(request, response);
		break;
		case "/ProductListServlet/update":
		updateProduct(request, response);
		break;
		case "/ProductListServlet/dashboard":
		listProducts(request, response);
		break;
		}
		} catch (SQLException ex) {
		throw new ServletException(ex);
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Step 5: listUsers function to connect to the database and retrieve all users
	// records
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ProductList> Product = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				String price = rs.getString("price");
				String specs = rs.getString("specs");
				Product.add(new ProductList(name, description, price, specs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// userManagement.jsp
		request.setAttribute("listProducts", Product);
		request.getRequestDispatcher("/ProductList.jsp").forward(request, response);

	}
private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String name = request.getParameter("name");
		ProductList existingProduct = new ProductList("", "", "", "");
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_PRODUCTS_BY_ID);) {
						preparedStatement.setString(1, name);
						ResultSet rs = preparedStatement.executeQuery();
						
						while (rs.next()) {
							name = rs.getString("name");
							String description = rs.getString("description");
							String price = rs.getString("price");
							String specs = rs.getString("specs");
							existingProduct = new ProductList(name, description, price, specs);
							}
							} catch (SQLException e) {
							System.out.println(e.getMessage());
							}
		request.setAttribute("ProductList", existingProduct);
		request.getRequestDispatcher("/productEdit.jsp").forward(request, response);
	}
	//method to update the user table base on the form data
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String oriName = request.getParameter("oriName");
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String price = request.getParameter("price");
	String specs = request.getParameter("specs");
	//Step 2: Attempt connection with database and execute update user SQL query
	try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
	statement.setString(1, name);
	statement.setString(2, description);
	statement.setString(3, price);
	statement.setString(4, specs);
	statement.setString(5, oriName);
	int i = statement.executeUpdate();
	}
	//Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	response.sendRedirect("http://localhost:8090/Cars/ProductListServlet/dashboard");
	}
	//method to delete user
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String name = request.getParameter("name");
	//Step 2: Attempt connection with database and execute delete user SQL query
	try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
	statement.setString(1, name);
	int i = statement.executeUpdate();
	}
	//Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	response.sendRedirect("http://localhost:8090/Cars/ProductListServlet/dashboard");
	}
	
}
