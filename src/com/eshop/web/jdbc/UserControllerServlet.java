package com.eshop.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDbUtil userDbUtil;
	
	@Resource(name="jdbc/web_gameshop")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our user db util ... and pass in conn pool / datasource
		try {
			userDbUtil = new UserDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
				
	        // If the command is missing, then default to listing users
	        if (theCommand == null) {
	            theCommand = "LIST";
	        }
	        
	        // Route to the appropriate method
	        switch (theCommand) {
	            case "LIST":
	                listUsers(request, response);
	                break;
	            
	            case "ADD":
	                addUsers(request, response);
	                break;
	                
	            case "LOAD":
	                loadUsers(request, response);
	                break;
	                
	            case "UPDATE":
	                updateUsers(request, response);
	                break;
	                
	            case "DELETE":
	                deleteUsers(request, response);
	                break;
	                
	            default:
	                listUsers(request, response);
	        }
	    } catch (Exception exc) {
	        throw new ServletException(exc);
	    }
	}

	private void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// read student id from form data
		String theUserId = request.getParameter("userId");
				
		// delete student from database (db util)
		userDbUtil.deleteUser(theUserId);
		
		// send them back to "lists users" page
		listUsers(request, response);
		
	}

	private void updateUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read user info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");	
		String password = request.getParameter("password");
		
		// create a new user object
		User theUser = new User(id, firstName, lastName, email, password);
		
		
		// perform update on database
		userDbUtil.updateUser(theUser);
		
		// send them back to the "lsit students" page
		listUsers(request, response);
		
	}

	private void loadUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// read student id from form data
		String theUserId = request.getParameter("userId");
		
		// get student from database (db util)
		User theUser = userDbUtil.getUser(theUserId);
		
		// place student in request attribute
		request.setAttribute("THE_USER", theUser);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-user-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read user info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");		
		
		// create a new user object
		User theUser = new User(firstName, lastName, email);
		
		// add the user to the database
		userDbUtil.addUser(theUser);
				
		// send back to main page (the student list)
		listUsers(request, response);
		
}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get users from db util
		
		List<User> users = userDbUtil.getUsers();
		
		// add users to request
		
		request.setAttribute("USER_LIST", users);
		
		// sent to JSP page (view)
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/list-users.jsp");
		dispatcher.forward(request, response);
		
	}

}
