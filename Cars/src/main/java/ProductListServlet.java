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
	
	//Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/carproducts";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + " (name,description,price,specs) VALUES " + " (?, ?, ?);";
			private static final String SELECT_PRODUCTS_BY_ID = "name,description,price,specs from products where name =?";
			private static final String SELECT_ALL_PRODUCTS = "select * from products ";
			private static final String DELETE_PRODUCTS_SQL = "delete from products where name = ?;";
			private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,password= ?,email =?,language =? where name = ?;";
			
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/insert":
		break;
		case "/delete":
		break;
		case "/edit":
		break;
		case "/update":
		break;
		default:
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	//Step 5: listUsers function to connect to the database and retrieve all users records
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
	List <ProductList> Product = new ArrayList <>();
	try (Connection connection = getConnection();
	// Step 5.1: Create a statement using connection object
	PreparedStatement preparedStatement =
	connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
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
	// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
	request.setAttribute("listProducts", Product);
	request.getRequestDispatcher("/ProductList.jsp").forward(request, response);
	
	}
	

}
