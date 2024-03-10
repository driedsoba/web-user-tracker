package com.eshop.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import javax.sql.DataSource;

public class UserDbUtil {

    private DataSource dataSource;

    public UserDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY last_name";

        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql);
             ResultSet myRs = myStmt.executeQuery()) {

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                String password = myRs.getString("password");

                User tempUser = new User(id, firstName, lastName, email, password);
                users.add(tempUser);
            }
        }
        return users;
    }

    public void addUser(User theUser) throws SQLException {
        String sql = "INSERT INTO user (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        
        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            
            String hashedPassword = BCrypt.hashpw(theUser.getPassword(), BCrypt.gensalt());
            
            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, hashedPassword);

            myStmt.executeUpdate();
        }
    }

    public User getUser(String theUserId) throws Exception {
        User theUser = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        
        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            
            myStmt.setInt(1, Integer.parseInt(theUserId));
            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    int id = myRs.getInt("id");
                    String firstName = myRs.getString("first_name");
                    String lastName = myRs.getString("last_name");
                    String email = myRs.getString("email");
                    String password = myRs.getString("password");

                    theUser = new User(id, firstName, lastName, email, password);
                } else {
                    throw new Exception("Could not find user id: " + theUserId);
                }
            }
        }
        return theUser;
    }

    public void updateUser(User theUser) throws Exception {
        String sql = "UPDATE user SET first_name=?, last_name=?, email=?, password=? WHERE id=?";

        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            
            String hashedPassword = BCrypt.hashpw(theUser.getPassword(), BCrypt.gensalt());
            
            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, hashedPassword);
            myStmt.setInt(5, theUser.getId());

            myStmt.executeUpdate();
        }
    }

    public void deleteUser(String theUserId) throws Exception {
        String sql = "DELETE FROM user WHERE id=?";

        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            
            myStmt.setInt(1, Integer.parseInt(theUserId));
            myStmt.executeUpdate();
        }
    }

    public User authenticateUser(String email, String password) throws Exception {
        User theUser = null;
        String sql = "SELECT * FROM user WHERE email = ?";

        try (Connection myConn = dataSource.getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            
            myStmt.setString(1, email);
            try (ResultSet myRs = myStmt.executeQuery()) {
                if (myRs.next()) {
                    String dbPassword = myRs.getString("password");
                    if (BCrypt.checkpw(password, dbPassword)) {
                        int id = myRs.getInt("id");
                        String firstName = myRs.getString("first_name");
                        String lastName = myRs.getString("last_name");
                        theUser = new User(id, firstName, lastName, email, dbPassword);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("Authentication failed", e);
        }
        return theUser; // return null or throw an exception if authentication fails
    }
}