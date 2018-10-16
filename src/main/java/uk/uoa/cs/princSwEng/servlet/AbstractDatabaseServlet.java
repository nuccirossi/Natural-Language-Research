package uk.uoa.cs.princSwEng.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;

/**
 * Gets the {@code DataSource} for managing the connection pool to the database.
 */
public abstract class AbstractDatabaseServlet extends HttpServlet
{

// public void init(ServletConfig config) throws ServletException
// {

// 		// the JNDI lookup context
// 		InitialContext cxt;

// 		try
// 		{
// 				cxt = new InitialContext();
// 				ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/ECHO");

// 		} catch (NamingException e) {
// 				ds = null;

// 				throw new ServletException(
// 							  String.format("Impossible to access the connection pool to the database: %s", e.getMessage()));
// 		}
// }

// /**
//  * Releases the {@code DataSource} for managing the connection pool to the database.
//  */
// public void destroy()
// {
// 		ds = null;
// }

// /**
//  * Returns the {@code DataSource} for managing the connection pool to the database.
//  *
//  * @return the {@code DataSource} for managing the connection pool to the database
//  */
// protected final DataSource getDataSource()
// {
// 		return ds;
// }

// }


/**
 * The connection pool to the database.
 */
//private DataSource ds;

/**
 * Gets the {@code Connection} for managing the connection pool to the database.
 *
 * 
 * @throws URISyntaxException
 *          if an exception has occurred that interferes with the servlet's normal operation
 * @throws SQLException
 *          if an exception has occurred that interferes with the servlet's normal operation
 */
public static Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

    return DriverManager.getConnection(dbUrl, username, password);
}
}
