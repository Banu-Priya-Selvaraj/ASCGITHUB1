//package com.ecz.ui;
//
//import com.ecz.model.Product;
//import com.ecz.service.ProductService;
//import com.ecz.service.ProductServiceAI;
//import com.ecz.service.ProductServiceAIImpl;
//import com.ecz.service.ProductServiceImpl;
//
//import java.util.List;
//
//public class MenuUI {
//    ProductServiceAI productService = new ProductServiceAIImpl();
////    ProductService productService = new ProductServiceImpl();
////    What is UI?
////    UI stands for User Interface. It is the part of the application that the user interacts with
//    void accessMenu() {
//        System.out.println("Welcome to ECommerce App!");
//        System.out.println("1. Add Product");
//        System.out.println("2. View Products");
//        System.out.println("3. Update Product");
//        System.out.println("4. Delete Product");
//        System.out.println("5. Exit");
//    }
//    void addProduct() {
////        System.out.println("Enter Product ID: ");
////        System.out.println("Enter Product Name: ");
////        System.out.println("Enter Product Price: ");
//        Product product =  new Product("1003", "Laptop", 999.99, 1);
//        boolean  isAdded = productService.addProduct(product);
//        if (isAdded) {
//            System.out.println("Product added successfully");
//        } else {
//            System.out.println("Product not added");
//        }
//    }
//    void getAllProducts() {
//      List<Product> productList =  productService.getAllProducts();
//        for (Product product: productList) {
//            System.out.println(product);
//        }
//    }
//    // update product
//    void updateProduct() {
//        Product product =  new Product("1003", "Desktop", 1999.99, 11);
//        boolean  isUpdated = productService.updateProduct(product);
//        if (isUpdated) {
//            System.out.println("Product updated successfully");
//        } else {
//            System.out.println("Product not updated");
//        }
//    }
//}
package com.ecz.ui;

import com.ecz.model.Product;
import com.ecz.repository.ProductRepository;
import com.ecz.repository.ProductRepositoryDbImpl;

import java.sql.SQLException;
import java.util.List;

public class MenuUI {
    private final ProductRepository productRepository;

    public MenuUI() {
        this.productRepository = new ProductRepositoryDbImpl();
    }

    public void displayMenu() {
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("5. Exit");
    }

    public void addProduct(String id, String name, double price, int quantity) throws SQLException {
        Product product = new Product(id, name, price, quantity);
        boolean isAdded = productRepository.addProductToCart(product);
        if (isAdded) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    public void viewProducts() {
        List<Product> products = productRepository.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                        ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
            }
        }
    }

    public void updateProduct(String id, String name, double price, int quantity) throws SQLException {
        Product existingProduct = productRepository.getProductById(id);
        if (existingProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        existingProduct.setName(name);
        existingProduct.setPrice(price);
        existingProduct.setQuantity(quantity);

        boolean isUpdated = productRepository.updateProduct(existingProduct);
        if (isUpdated) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }

    }

    public void deleteProduct(String id) throws SQLException {
        boolean isDeleted = productRepository.deleteProduct(id);
        if (isDeleted) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    public void accessMenu() {
        // Optional method if you want to provide additional setup or options before accessing the menu
    }
}

