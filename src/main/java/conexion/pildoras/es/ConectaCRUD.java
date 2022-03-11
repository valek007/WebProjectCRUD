package conexion.pildoras.es;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConectaCRUD
 */
@WebServlet("/ConectaCRUD")
public class ConectaCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConectaCRUD() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String pathJDBC = "jdbc:mysql://localhost:3306/gestionpedidoscrud?useSSL=false";
		String user = "root";
		String pass = "";
		String driver = "com.mysql.jdbc.Driver";

		try {

			PrintWriter out=response.getWriter();
			out.print("Nombre de la BBDD: "+pathJDBC+"<br>");
			Class.forName(driver);

			// 1. CREAR CONEXIÓN
			Connection connection = DriverManager.getConnection(pathJDBC, user, pass);

			out.print("Conexión exitosa.\n");
			connection.close();

		} catch (Exception e) {
			System.out.println("¡Fallo con la conexion!");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
