package com.eshop.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

// Assuming User is a simple POJO with a constructor and getters/setters
// import com.eshop.web.jdbc.User; // Uncomment this line if User is in the same package

public class UserDbUtil {

    private DataSource dataSource;

    public UserDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<User> getUsers() throws Exception {

        List<User> users = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // Get a connection from the pool
            myConn = dataSource.getConnection();

            // Create SQL statement
            String sql = "SELECT * FROM user ORDER BY last_name";

            myStmt = myConn.createStatement();

            // Execute the query
            myRs = myStmt.executeQuery(sql);

            // Process the result set
            while (myRs.next()) {
                // Retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                // Create new user object and add it to the list
                User tempUser = new User(id, firstName, lastName, email);
                users.add(tempUser);
            }

            return users;
        } finally {
            // Ensure resources are closed in reverse order of their creation
            close(myConn, myStmt, myRs);
        }
    }

    public void addUser(User theUser) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Get database connection
            myConn = dataSource.getConnection();

            // SQL statement for insert
            String sql = "INSERT INTO user (first_name, last_name, email) VALUES (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // Set parameter values for the user
            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());

            // Execute SQL insert
            myStmt.execute();
        } finally {
            // Clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

	public User getUser(String theUserId) throws Exception {
		User theUser = null;
		
		Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int userId;
        
        try {
        	
        	// convert user id to int
        	userId = Integer.parseInt(theUserId);
        	
        	// get connection to database
        	myConn = dataSource.getConnection();
        	
        	// create sql to get selected student
        	String sql = "select * from user where id=?";
        	
        	// create prepared statement
        	myStmt = myConn.prepareStatement(sql);
        	
        	//set params
        	myStmt.setInt(1, userId);
        	
        	// execute statement
        	myRs = myStmt.executeQuery();
        	
        	// retrieve data from result set row
        	if (myRs.next()) {
        		String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
        		
                // use the userId during construction
                theUser = new User (userId, firstName, lastName, email);
        	}
        	else {
        		throw new Exception("Could not find student id: " + userId);
        	}
        	return theUser;
        }
		
        finally {
        	// clean up JDBC objects
        	close(myConn, myStmt, myRs);
        }
	}

	public void updateUser(User theUser) throws Exception {
		
		Connection myConn = null;
        PreparedStatement myStmt = null;
        
        try {
        // get db connection
        
        myConn = dataSource.getConnection();
        
        // create SQL update statement
        
        String sql = "update user " 
        		+ "set first_name=?, last_name=?, email=? "
        		+ "where id=?";
        
        // prepare statement
        myStmt = myConn.prepareStatement(sql);
        
        // set params
        myStmt.setString(1, theUser.getFirstName());
        myStmt.setString(2, theUser.getLastName());
        myStmt.setString(3, theUser.getEmail());
        myStmt.setInt(4, theUser.getId());
        
        // execute SQL statement
        myStmt.execute();	
	}
		finally {
	    	// clean up JDBC objects
	    	close(myConn, myStmt, null);
	    }
	}

	public void deleteUser(String theUserId) throws Exception{
		
		Connection myConn = null;
        PreparedStatement myStmt = null;
        
        try {
 
        	// convert user id to int
        	int userId = Integer.parseInt(theUserId);
        	
        	// get connection to database
        	myConn = dataSource.getConnection();
        	
        	// create sql to get selected student
        	String sql = "delete from user where id=?";
        	
        	// create prepared statement
        	myStmt = myConn.prepareStatement(sql);
        	
        	//set params
        	myStmt.setInt(1, userId);
        	
        	// execute statement
        	myStmt.execute();
        }
        finally {
        	// clean up JDBC code
        	close(myConn, myStmt, null);
        }
		
	}
}
