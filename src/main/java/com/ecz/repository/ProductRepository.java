package com.ecz.repository;

import com.ecz.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    boolean addProductToCart(Product product) throws SQLException;
    List<Product> getAllProducts();

    Product getProductById(String id) throws SQLException;

    //boolean updateProduct(Product existingProduct);
   // boolean UpdateProduct(Product product);

    boolean deleteProduct(String productid) throws SQLException;

    boolean UpdateProduct(Product product);

    boolean updateProduct(Product product) throws SQLException;
}