package com.eshop.web.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDbUtil {
    private DataSource dataSource;

    public ProductDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Connection myConn = dataSource.getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery("SELECT * FROM products")) {

            while (myRs.next()) {
                Product tempProduct = new Product(
                    myRs.getInt("id"),
                    myRs.getString("name"),
                    myRs.getString("description"),
                    myRs.getDouble("price"),
                    myRs.getString("image_url"),
                    myRs.getString("category")
                );
                products.add(tempProduct);
            }
        }
        return products;
    }
}
