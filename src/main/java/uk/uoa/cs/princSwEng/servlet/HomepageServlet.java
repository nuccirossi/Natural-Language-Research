package uk.uoa.cs.princSwEng.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public final class HomepageServlet extends AbstractDatabaseServlet
{

/**
 * List all category.
 *
 * @param req
 *            the HTTP request from the client.
 * @param res
 *            the HTTP response from the server.
 *
 * @throws ServletException
 *             if any error occurs while executing the servlet.
 * @throws IOException
 *             if any error occurs in the client/server communication.
 */


public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{

		
		// forwards the control to the Homepage
		req.getRequestDispatcher("index.html").forward(req, res);

}


}
