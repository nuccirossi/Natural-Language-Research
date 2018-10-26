
package uk.uoa.cs.princEngSw.servlet;

import uk.uoa.cs.princEngSw.resource.*;
import uk.uoa.cs.princEngSw.rest.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Manages the REST API for the different REST resources.
 *
 * @author ADHD
 * @version 1.00
 * @since 1.00
 */
public final class RestManagerServlet extends AbstractDatabaseServlet {

/**
 * The JSON MIME media type
 */
private static final String JSON_MEDIA_TYPE = "application/json";

/**
 * The JSON UTF-8 MIME media type
 */
private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

/**
 * The any MIME media type
 */
private static final String ALL_MEDIA_TYPE = "*/*";

@Override
protected final void service(final HttpServletRequest req, final HttpServletResponse res)
throws ServletException, IOException {

		res.setContentType(JSON_UTF_8_MEDIA_TYPE);
		final OutputStream out = res.getOutputStream();

		try {
				// if the request method and/or the MIME media type are not allowed, return.
				// Appropriate error message sent by {@code checkMethodMediaType}
				if (!checkMethodMediaType(req, res)) {
						return;
				}

				// if the requested resource was an Question, delegate its processing and return
				if (processQuestion(req, res)) {
						return;
				}

				// if the requested resource was a comment, delegate its processing and return
				if (processComment(req, res))
				{
						return;
				}
		


				// if none of the above process methods succeeds, it means an unknow resource has been requested
				final Message m = new Message("Unknown resource requested.", "E4A6",
				                              String.format("Requested resource is %s.", req.getRequestURI()));
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(out);
		} finally {
				// ensure to always flush and close the output stream
				out.flush();
				out.close();
		}
}

/**
 * Checks that the request method and MIME media type are allowed.
 *
 * @param req the HTTP request.
 * @param res the HTTP response.
 * @return {@code true} if the request method and the MIME type are allowed; {@code false} otherwise.
 *
 * @throws IOException if any error occurs in the client/server communication.
 */
private boolean checkMethodMediaType(final HttpServletRequest req, final HttpServletResponse res)
throws IOException {

		final String method = req.getMethod();
		final String contentType = req.getHeader("Content-Type");
		final String accept = req.getHeader("Accept");
		final OutputStream out = res.getOutputStream();

		Message m = null;

		if(accept == null) {
				m = new Message("Output media type not specified.", "E4A1", "Accept request header missing.");
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				m.toJSON(out);
				return false;
		}

		if(!accept.contains(JSON_MEDIA_TYPE) && !accept.equals(ALL_MEDIA_TYPE)) {
				m = new Message("Unsupported output media type. Resources are represented only in application/json.",
				                "E4A2", String.format("Requested representation is %s.", accept));
				res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				m.toJSON(out);
				return false;
		}

		switch(method) {
		case "GET":
		case "DELETE":
				// nothing to do
				break;

		case "POST":
		case "PUT":
				if(contentType == null) {
						m = new Message("Input media type not specified.", "E4A3", "Content-Type request header missing.");
						res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						m.toJSON(out);
						return false;
				}

				if(!contentType.contains(JSON_MEDIA_TYPE)) {
						m = new Message("Unsupported input media type. Resources are represented only in application/json.",
						                "E4A4", String.format("Submitted representation is %s.", contentType));
						res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
						m.toJSON(out);
						return false;
				}

				break;
		default:
				m = new Message("Unsupported operation.",
				                "E4A5", String.format("Requested operation %s.", method));
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				m.toJSON(out);
				return false;
		}

		return true;
}


/**
 * Checks whether the request if for an {@link Question} resource and, in case, processes it.
 *
 * @param req the HTTP request.
 * @param res the HTTP response.
 * @return {@code true} if the request was for an {@code Question}; {@code false} otherwise.
 *
 * @throws IOException if any error occurs in the client/server communication.
 */
private boolean processQuestion(HttpServletRequest req, HttpServletResponse res) throws IOException {

		final String method = req.getMethod();
		final OutputStream out = res.getOutputStream();

		String path = req.getRequestURI();
		Message m = null;

		// the requested resource was not an question
		if(path.lastIndexOf("rest/question") <= 0) {
				return false;
		}

		try {
				// strip everyhing until after the /question
				path = path.substring(path.lastIndexOf("question") + 8);

				// the request URI is: /question
				// if method GET, list questions
				// if method POST, create question
				if (path.length() == 0 || path.equals("/")) {

						switch (method) {
						case "GET":
								new QuestionRestResource(req, res, getDataSource().getConnection()).listQuestion();
								break;
						case "POST":
								new QuestionRestResource(req, res, getDataSource().getConnection()).createQuestion();
								break;
						default:
								m = new Message("Unsupported operation for URI /question.",
								                "E4A5", String.format("Requested operation %s.", method));
								res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
								m.toJSON(res.getOutputStream());
								break;
						}
				} else {
						// the request URI is: /question/category/{categoryName}
						if (path.contains("category")) {
								path = path.substring(path.lastIndexOf("category") + 8);

								if (path.length() == 0 || path.equals("/")) {
										m = new Message("Wrong format for URI /question/category/{categoryName}: no {categoryName} specified.",
										                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								} else {
										switch (method) {
										case "GET":


												/*	try {*/
												path.substring(1);

												new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestionByCategory();
												/*	} catch (NumberFormatException e) {
												   m = new Message(
												    "Wrong format for URI /question/salary/{salary}: {salary} is not an integer.",
												    "E4A7", e.getMessage());
												   res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
												   m.toJSON(res.getOutputStream());
												   }
												 */
												break;
										default:
												m = new Message("Unsupported operation for URI /question/Category/{categoryName}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								}
						} else if (path.contains("company")) {
								path = path.substring(path.lastIndexOf("company") + 7);

								if (path.length() == 0 || path.equals("/")) {
										m = new Message("Wrong format for URI /question/company/{companyName}: no {companyName} specified.",
										                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								} else {
										switch (method) {
										case "GET":


												/*	try {*/
												path.substring(1);

												new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestionByCompany();
												/*	} catch (NumberFormatException e) {
												   m = new Message(
												    "Wrong format for URI /question/salary/{salary}: {salary} is not an integer.",
												    "E4A7", e.getMessage());
												   res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
												   m.toJSON(res.getOutputStream());
												   }
												 */
												break;
										default:
												m = new Message("Unsupported operation for URI /question/Company/{companyName}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								}
						} else if (path.contains("difficulty")) {
								path = path.substring(path.lastIndexOf("difficulty") + 10);

								if (path.length() == 0 || path.equals("/")) {
										m = new Message("Wrong format for URI /question/difficulty/{difficulty}: no {difficulty} specified.",
										                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								} else {
										switch (method) {
										case "GET":


												/*	try {*/
												path.substring(1);

												new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestionByDifficulty();
												/*	} catch (NumberFormatException e) {
												   m = new Message(
												    "Wrong format for URI /question/salary/{salary}: {salary} is not an integer.",
												    "E4A7", e.getMessage());
												   res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
												   m.toJSON(res.getOutputStream());
												   }
												 */
												break;
										default:
												m = new Message("Unsupported operation for URI /question/difficulty/{difficulty}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								}
						} else if (path.contains("title")) {
								path = path.substring(path.lastIndexOf("title") + 5);

								if (path.length() == 0 || path.equals("/")) {
										m = new Message("Wrong format for URI /question/title/{title}: no {title} specified.",
										                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								} else {
										switch (method) {
										case "GET":


												/*	try {*/
												path.substring(1);

												new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestionByTitle();
												/*	} catch (NumberFormatException e) {
												   m = new Message(
												    "Wrong format for URI /question/salary/{salary}: {salary} is not an integer.",
												    "E4A7", e.getMessage());
												   res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
												   m.toJSON(res.getOutputStream());
												   }
												 */
												break;
										default:
												m = new Message("Unsupported operation for URI /question/title/{title}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								}



						}else if (path.contains("topic")) {
								path = path.substring(path.lastIndexOf("topic") + 5);

								if (path.length() == 0 || path.equals("/")) {
										m = new Message("Wrong format for URI /question/topic/{topicName}: no {topicName} specified.",
										                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								} else {
										switch (method) {
										case "GET":


												/*	try {*/
												path.substring(1);

												new QuestionRestResource(req, res, getDataSource().getConnection()).searchQuestionByTopic();
												/*	} catch (NumberFormatException e) {
												   m = new Message(
												    "Wrong format for URI /question/salary/{salary}: {salary} is not an integer.",
												    "E4A7", e.getMessage());
												   res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
												   m.toJSON(res.getOutputStream());
												   }
												 */
												break;
										default:
												m = new Message("Unsupported operation for URI /question/topic/{topicName}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								}
						}
						else if (path.contains("time")) {
								path = path.substring(path.lastIndexOf("time") + 4);


		
										switch (method) {
										case "GET":

												new QuestionRestResource(req, res, getDataSource().getConnection()).SearchRecentQuestion();

												break;
										default:
												m = new Message("Unsupported operation for URI /question/time/{time}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								

						}else {

								// the request URI is: /question/{questionID}
								try {

										// check that the parameter is actually an integer
										Integer.parseInt(path.substring(1));

										switch (method) {
										case "GET":
												new QuestionRestResource(req, res, getDataSource().getConnection()).readQuestion();
												break;
										/*	case "PUT":
										   new QuestionRestResource(req, res, getDataSource().getConnection()).updateQuestion();
										   break;*/
										case "DELETE":
												new QuestionRestResource(req, res, getDataSource().getConnection()).deleteQuestion();
												break;
										default:
												m = new Message("Unsupported operation for URI /question/{questionID}.",
												                "E4A5", String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
										}
								} catch (NumberFormatException e) {
										m = new Message("Wrong format for URI /question/{questionID}: {questionID} is not an integer.",
										                "E4A7", e.getMessage());
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								}
						}
				}
		} catch(Throwable t) {
				m = new Message("Unexpected error.", "E5A1", t.getMessage());
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
		}

		return true;

}

private boolean processComment(HttpServletRequest req, HttpServletResponse res) throws IOException
{
		final String method = req.getMethod();
		final OutputStream out = res.getOutputStream();

		String path = req.getRequestURI();
		Message m = null;

		// the requested resource was not a comment
		if(path.lastIndexOf("rest/comment") <= 0)
		{
				return false;
		}

		try
		{
				// strip everyhing until after the /comment
				path = path.substring(path.lastIndexOf("comment") + 7);

				// the request URI is: /comment
				// if method GET, list comments
				// if method POST, create a comment
				if (path.length() == 0 || path.equals("/"))
				{
						switch (method)
						{
						case "GET":
								new CommentRestResource(req, res, getDataSource().getConnection()).listComment();
								break;
						case "POST":
								new CommentRestResource(req, res, getDataSource().getConnection()).createComment();
								break;
						default:
								m = new Message("Unsupported operation for URI /comment.",
								                "E4A5", String.format("Requested operation %s.", method));
								res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
								m.toJSON(res.getOutputStream());
								break;
						}
				}
				else
				{
						// the request URI is: /comment/questionID/{questionID}
						if (path.contains("questionID"))
						{
								path = path.substring(path.lastIndexOf("questionID") + 10);

								if (path.length() == 0 || path.equals("/"))
								{
										m = new Message("Wrong format for URI /comment/questionID/{questionID}: no {questionID} specified.",
										                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								}
								else
								{
										switch (method)
										{
										case "GET":
												// check that the parameter is actually an integer
												try
												{
														Integer.parseInt(path.substring(1));

														new CommentRestResource(req, res, getDataSource().getConnection()).searchCommentByQuestionID();
												}
												catch (NumberFormatException e)
												{
														m = new Message(
																"Wrong format for URI /comment/questionID/{questionID}: {questionID} is not an integer.",
																"E4A7", e.getMessage());
														res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
														m.toJSON(res.getOutputStream());
												}

												break;
										default:
												m = new Message("Unsupported operation for URI /comment/questionID/{questionID}.", "E4A5",
												                String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
												break;
										}
								}
						}
						else
						{
								// the request URI is: /comment/{commentID}
								try
								{

										// check that the parameter is actually an integer
										Integer.parseInt(path.substring(1));

										switch (method)
										{
										case "GET":                                                                                                                        //lo userei per leggerne uno
										case "PUT":                                                                                                                         //per fare un update
												new CommentRestResource(req, res, getDataSource().getConnection()).updateComment();
												break;
										case "DELETE":
												new CommentRestResource(req, res, getDataSource().getConnection()).deleteComment();
												break;
										default:
												m = new Message("Unsupported operation for URI /comment/{commentID}.",
												                "E4A5", String.format("Requested operation %s.", method));
												res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
												m.toJSON(res.getOutputStream());
										}
								}
								catch (NumberFormatException e)
								{
										m = new Message("Wrong format for URI /comment/{commentID}: {commentID} is not an integer.",
										                "E4A7", e.getMessage());
										res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										m.toJSON(res.getOutputStream());
								}
						}
				}
		}
		catch(Throwable t)
		{
				m = new Message("Unexpected error.", "E5A1", t.getMessage());
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
		}

		return true;
}

}
