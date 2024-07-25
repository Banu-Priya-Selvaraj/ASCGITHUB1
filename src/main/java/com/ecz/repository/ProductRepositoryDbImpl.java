//package com.ecz.repository;
//
//import com.ecz.model.Product;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ProductRepositoryDbImpl implements  ProductRepository {
//    public boolean addProductToCart(Product product) {
//        // Load the driver class
//        try {
//            Connection connection = getConnection();
//            System.out.println("Driver Loaded!");
//            Statement statement = connection.createStatement();
//            System.out.println("Statement created");
//            // INSERT INTO product (id, name, price, quantity) VALUES ('1', 'Laptop', 999.99, 1);
//
//            String insertQuery = "INSERT INTO product (id, name, price, quantity) VALUES ('" + product.getId()
//                    + "', '" + product.getName() + "'," + product.getPrice() + "," + product.getQuantity() + ");";
//            int noOfRowsAffected = statement.executeUpdate(insertQuery);
//            System.out.println("No of rows affected: " + noOfRowsAffected);
//            return noOfRowsAffected > 0;
//        }
//
//        catch (SQLException sqlException) {
//            System.out.println(sqlException.getMessage());
//        }
//        return false;
//    }
//
//
//    private static Connection getConnection()   {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ladb", "root", "mysql");
//            return connection;
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//    @Override
//    // method to return all the products from the database as a list
//    public List<Product> getAllProducts() {
//        List<Product> products = new ArrayList<>();
//        try {
//            Connection connection = getConnection();
////            Connection connection = DbConnectionSingleton.getInstance();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//                double price = resultSet.getDouble("price");
//                int quantity = resultSet.getInt("quantity");
//                Product product = new Product(id, name, price, quantity);
//                products.add(product);
//            }
//        } catch (SQLException sqlException) {
//            System.out.println(sqlException.getMessage());
//        }
//        return products;
//    }
//
//    @Override
//    public Product getProductById(String id) {
//        return null;
//    }
//
//
//    @Override
//    public boolean UpdateProduct(Product product) {
//        return false;
//    }
//
//    @Override
//    public boolean updateProduct(Product product) {
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String updateQuery = "UPDATE product SET name = '" + product.getName() + "', price = " + product.getPrice() + ", quantity = " + product.getQuantity() + " WHERE id = '" + product.getId() + "';";
//            int noOfRowsAffected = statement.executeUpdate(updateQuery);
//            return noOfRowsAffected > 0;
//        } catch (SQLException sqlException) {
//            System.out.println(sqlException.getMessage());
//        }
//        return false;
//    }
//    @Override
//    public boolean deleteProduct(String productId) {
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String deleteQuery = "DELETE FROM product WHERE id = '" + productId + "';";
//            int noOfRowsAffected = statement.executeUpdate(deleteQuery);
//            return noOfRowsAffected > 0;
//        } catch (SQLException sqlException) {
//            System.out.println(sqlException.getMessage());
//        }
//        return false;
//    }
//
//
//}
//
//
//
package com.ecz.repository;

import com.ecz.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryDbImpl implements ProductRepository {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1/ladb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "mysql";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    @Override
    public boolean addProductToCart(Product product) throws SQLException {
        String insertQuery = "INSERT INTO product (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            int noOfRowsAffected = preparedStatement.executeUpdate();
            return noOfRowsAffected > 0;
        }
    }

    @Override
    public Product getProductById(String productId) throws SQLException {
        String selectQuery = "SELECT * FROM product WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        String selectQuery = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        String updateQuery = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getId());
            int noOfRowsAffected = preparedStatement.executeUpdate();
            return noOfRowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteProduct(String productId) throws SQLException {
        String deleteQuery = "DELETE FROM product WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, productId);
            int noOfRowsAffected = preparedStatement.executeUpdate();
            return noOfRowsAffected > 0;
        }
    }

    @Override
    public boolean UpdateProduct(Product product) {
        return false;
    }
}

