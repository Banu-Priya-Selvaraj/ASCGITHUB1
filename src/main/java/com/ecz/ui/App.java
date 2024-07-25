//package com.ecz.ui;
//
//public class App
//{
//    public static void main( String[] args )
//    {
//        System.out.println("Welcome to ECommerce App!");
//        MenuUI menuUI = new MenuUI();
//        menuUI.updateProduct();
//        menuUI.getAllProducts();
//    }
//}
package com.ecz.ui;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to ECommerce App!");

        MenuUI menuUI = new MenuUI();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            menuUI.displayMenu();
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add product
                    System.out.print("Enter Product ID: ");
                    String id = scanner.next();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Product Quantity: ");
                    int quantity = scanner.nextInt();
                    menuUI.addProduct(id, name, price, quantity);
                    break;
                case 2:
                    // View products
                    menuUI.viewProducts();
                    break;
                case 3:
                    // Update product
                    System.out.print("Enter Product ID to update: ");
                    String updateId = scanner.next();
                    System.out.print("Enter new Product Name: ");
                    String updateName = scanner.next();
                    System.out.print("Enter new Product Price: ");
                    double updatePrice = scanner.nextDouble();
                    System.out.print("Enter new Product Quantity: ");
                    int updateQuantity = scanner.nextInt();
                    menuUI.updateProduct(updateId, updateName, updatePrice, updateQuantity);
                    break;
                case 4:
                    // Delete product
                    System.out.print("Enter Product ID to delete: ");
                    String deleteId = scanner.next();
                    menuUI.deleteProduct(deleteId);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
