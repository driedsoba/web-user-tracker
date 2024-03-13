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

public class AdminDbUtil {

    private DataSource dataSource;

    public AdminDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Admin> getAdmins() throws Exception {

        List<Admin> admins = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // Get a connection from the pool
            myConn = dataSource.getConnection();

            // Create SQL statement
            String sql = "SELECT * FROM admin ORDER BY last_name";

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
                String password = myRs.getString("password");

                // Create new user object and add it to the list
                Admin tempAdmin = new Admin(id, firstName, lastName, email, password);
                admins.add(tempAdmin);
            }

            return admins;
        } finally {
            // Ensure resources are closed in reverse order of their creation
            close(myConn, myStmt, myRs);
        }
    }

    public void addAdmin(Admin theAdmin) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Get database connection
            myConn = dataSource.getConnection();

            // SQL statement for insert
            String sql = "INSERT INTO admin (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // Set parameter values for the admin
            myStmt.setString(1, theAdmin.getFirstName());
            myStmt.setString(2, theAdmin.getLastName());
            myStmt.setString(3, theAdmin.getEmail());
            myStmt.setString(4, theAdmin.getPassword());  

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

	public Admin getAdmin(String theAdminId) throws Exception {
		Admin theAdmin = null;
		
		Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int adminId;
        
        try {
        	
        	// convert user id to int
        	adminId = Integer.parseInt(theAdminId);
        	
        	// get connection to database
        	myConn = dataSource.getConnection();
        	
        	// create sql to get selected admin
        	String sql = "select * from admin where id=?";
        	
        	// create prepared statement
        	myStmt = myConn.prepareStatement(sql);
        	
        	//set params
        	myStmt.setInt(1, adminId);
        	
        	// execute statement
        	myRs = myStmt.executeQuery();
        	
        	// retrieve data from result set row
        	if (myRs.next()) {
        		String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                String password = myRs.getString("password");
        		
                // use the adminId during construction
                theAdmin = new Admin (adminId, firstName, lastName, email, password);
        	}
        	else {
        		throw new Exception("Could not find admin id: " + adminId);
        	}
        	return theAdmin;
        }
		
        finally {
        	// clean up JDBC objects
        	close(myConn, myStmt, myRs);
        }
	}

	public void updateAdmin(Admin theAdmin) throws Exception {
	    Connection myConn = null;
	    PreparedStatement myStmt = null;

	    try {
	        // get db connection
	        myConn = dataSource.getConnection();

	        // create SQL update statement
	        String sql = "UPDATE admin SET first_name=?, last_name=?, email=?, password=? WHERE id=?";

	        // prepare statement
	        myStmt = myConn.prepareStatement(sql);

	        // set params
	        myStmt.setString(1, theAdmin.getFirstName());
	        myStmt.setString(2, theAdmin.getLastName());
	        myStmt.setString(3, theAdmin.getEmail());
	        myStmt.setString(4, theAdmin.getPassword()); // Include password update
	        myStmt.setInt(5, theAdmin.getId());

	        // execute SQL statement
	        myStmt.execute();
	    } finally {
	        // clean up JDBC objects
	        close(myConn, myStmt, null);
	    }
	}

	public void deleteAdmin(String theAdminId) throws Exception{
		
		Connection myConn = null;
        PreparedStatement myStmt = null;
        
        try {
 
        	// convert admin id to int
        	int adminId = Integer.parseInt(theAdminId);
        	
        	// get connection to database
        	myConn = dataSource.getConnection();
        	
        	// create sql to get selected admin
        	String sql = "delete from admin where id=?";
        	
        	// create prepared statement
        	myStmt = myConn.prepareStatement(sql);
        	
        	//set params
        	myStmt.setInt(1, adminId);
        	
        	// execute statement
        	myStmt.execute();
        }
        finally {
        	// clean up JDBC code
        	close(myConn, myStmt, null);
        }
		
	}
	public Admin authenticateAdmin(String email, String password) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Get a connection from the pool
            myConn = dataSource.getConnection();

            // Create SQL statement to query admin by email
            String sql = "SELECT * FROM admin WHERE email = ?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, email);

            // Execute the query
            myRs = myStmt.executeQuery();

            // Check if admin exists
            if (myRs.next()) {
                // Retrieve password from database
                String dbPassword = myRs.getString("password");
                if (dbPassword.equals(password)) {
                    // Password matches, return admin
                    int id = myRs.getInt("id");
                    String firstName = myRs.getString("first_name");
                    String lastName = myRs.getString("last_name");
                    // Email already retrieved
                    // Create and return user object
                    return new Admin(id, firstName, lastName, email, password);
                }
            }
            // User not found or password does not match, return null
            return null;
        } finally {
            // Close all resources
            close(myConn, myStmt, myRs);
        }
    }
}