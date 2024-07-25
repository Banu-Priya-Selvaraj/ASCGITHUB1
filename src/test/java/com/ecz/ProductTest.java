package com.ecz;

import com.ecz.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testDefaultConstructor() {
        Product product = new Product();
        assertNull(product.getId());
        assertNull(product.getName());
        assertEquals(0.0, product.getPrice());
        assertEquals(0, product.getQuantity());
    }

    @Test
    void testParameterizedConstructor() {
        Product product = new Product("1", "Laptop", 999.99, 10);
        assertEquals("1", product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice());
        assertEquals(10, product.getQuantity());
    }
    @Test
    void testGettersAndSetters() {
        Product product = new Product();

        product.setId("2");
        assertEquals("2", product.getId());

        product.setName("Smartphone");
        assertEquals("Smartphone", product.getName());

        product.setPrice(499.99);
        assertEquals(499.99, product.getPrice());

        product.setQuantity(5);
        assertEquals(5, product.getQuantity());
    }

//    @Test
//    void testToString() {
//        Product product = new Product("3", "Tablet", 299.99, 15);
//        String expected = "Product{id='3', name='Tablet', price=299.99, quantity=15}";
//        assertEquals(expected, product.toString());
//    }

    @Test
    void testEquality() {
        Product product1 = new Product("1", "Laptop", 999.99, 10);
        Product product2 = new Product("1", "Laptop", 999.99, 10);
        assertEquals(product1, product2, "Products with the same attributes should be equal");
    }
}

