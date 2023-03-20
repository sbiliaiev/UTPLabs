package repositories;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import models.Product;

class ProductRepositoryTest {
    private static EntityManager entityManager;
    private static ProductRepository productRepository;

    @BeforeAll
    public static void setup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Customers");
        entityManager = emf.createEntityManager();
        productRepository = new ProductRepository(entityManager);
    }

    @AfterAll
    public static void tearDown() {
        entityManager.close();
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product(1L, "Apple");
        Optional<Product> savedProduct = productRepository.save(product);
        assertTrue(savedProduct.isPresent());
        assertEquals(product, savedProduct.get());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1l, "Test Product");
        productRepository.save(product);

        product.setName("Updated Test Product");
        productRepository.update(product);

        Optional<Product> updatedProduct = productRepository.findById(product.getId());
        assertTrue(updatedProduct.isPresent());
        assertEquals("Updated Test Product", updatedProduct.get().getName());
    }

    @Test
    void testDelete() {
        Product product = new Product(2L, "to be deleted");
        productRepository.save(product);

        Optional<Product> updatedProduct = productRepository.findById(product.getId());
        assertTrue(updatedProduct.isPresent());

        productRepository.delete(product.getId());

        updatedProduct = productRepository.findById(product.getId());
        assertFalse(updatedProduct.isPresent());
    }

    @Test
    void testFindById() {
        Product product = new Product(3L, "to be found");
        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("to be found", foundProduct.get().getName());
    }

    @Test
    void testFindByName() {
        Product product = new Product(1l, "Test Product");
        productRepository.save(product);

        List<Product> foundProducts = productRepository.findByName(product.getName());
        assertEquals(product.getName(), foundProducts.get(0).getName());
    }
}
