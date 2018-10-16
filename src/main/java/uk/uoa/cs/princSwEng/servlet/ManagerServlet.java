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

public final class ManagerServlet extends AbstractDatabaseServlet
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

		
		// forwards the control to the ManagerPage
		req.getRequestDispatcher("manager.html").forward(req, res);

}


public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{

		// request parameter
		String translator;
		String languages;
		int number;
		String corpora;

		// model
		Company c = null;
		Message m = null;

		try
		{

				translator = req.getParameter("translator");
				languages = req.getParameter("languages");
				number = req.getParameter("number");
				corpora = req.getParameter("corpora");

				System.out.println("Parameters retrieved: " + translator + languages + number + corpora);

				// c = new ReadCompanyDatabase(getDataSource().getConnection(), translator).readCompany();
				
				
				// if(c!= null)
				// 	m = new Message("Company successfully read.");
				// else
				// 	m = new Message("Company doesn't find.");

		}/* catch (NumberFormatException ex)
		          {
		          m = new Message("Cannot read the company. Invalid input parameters: translator must be a string.",
		          "E100", ex.getMessage());
		          }*/catch (SQLException ex)
		{
				m = new Message("Cannot find the company: unexpected error while accessing the database.",
				                "E200", ex.getMessage());
		}

		// stores the deleted company and the message as a request attribute
		// req.setAttribute("company", c);
		// req.setAttribute("message", m);

		// forwards the control to the read-company-result JSP
		//req.getRequestDispatcher("/jsp/read-company-result.jsp").forward(req, res);

}



}
