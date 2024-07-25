package com.ecz.service;

import com.ecz.model.Product;
import com.ecz.repository.ProductRepository;
//import com.ecz.repository.ProductRepositoryAI;
import com.ecz.repository.ProductRepositoryDbImpl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductServiceAIImpl implements ProductService{
    ProductRepository productRepository = new ProductRepositoryDbImpl();
    public boolean addProduct(Product product) throws SQLException {
        boolean isAdded = validateProductPriceAI(product);
        return isAdded;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    public boolean validateProductPriceAI(Product product) throws SQLException {
        System.out.println("AI validation for product price");
        boolean isAdded = false;
        if (product.getPrice() < 0) {
            System.out.println("Price cannot be negative");
        } else {
//           isAdded =  productRepository.addProductToCollectionCart(product);
            isAdded =  productRepository.addProductToCart(product);
        }
        return isAdded;
    }
    // update product
    public boolean updateProduct(Product product) throws SQLException {
        return productRepository.updateProduct(product);
    }
}

