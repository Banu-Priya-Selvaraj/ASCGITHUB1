package com.ecz.service;

import com.ecz.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    public boolean addProduct(Product product) throws SQLException;
    public List<Product> getAllProducts();
    public  boolean updateProduct(Product product) throws SQLException;

}
