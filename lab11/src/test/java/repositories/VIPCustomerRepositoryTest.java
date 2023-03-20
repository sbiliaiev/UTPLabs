package repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Customer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.VIPCustomer;

class VIPCustomerRepositoryTest {
    private static VIPCustomerRepository repository;
    private static EntityManager em;
    private static EntityManagerFactory emf;

    @BeforeAll
    public static void setUp() {
        emf = Persistence.createEntityManagerFactory("Customers");
        em = emf.createEntityManager();
        repository = new VIPCustomerRepository(em);
    }

    @AfterAll
    public static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testSave() {
        VIPCustomer customer = new VIPCustomer(null, "John Smith", 1000, 0.10);
        Optional<VIPCustomer> savedCustomer = repository.save(customer);
        assertTrue(savedCustomer.isPresent());
        assertEquals("John Smith", savedCustomer.get().getName());
        assertEquals(1000, savedCustomer.get().getAnnualPurchases());
        assertEquals(0.10, savedCustomer.get().getDiscountRate());
    }

    @Test
    public void testUpdate() {
        VIPCustomer customer = new VIPCustomer(null, "John Smith", 1000, 0.10);
        repository.save(customer);
        customer.setName("Jane Smith");
        VIPCustomer updatedCustomer = repository.update(customer);
        assertEquals("Jane Smith", updatedCustomer.getName());
        assertEquals(1000, updatedCustomer.getAnnualPurchases());
        assertEquals(0.10, updatedCustomer.getDiscountRate());
    }

    @Test
    public void testDelete() {
        VIPCustomer customer = new VIPCustomer(null, "John Smith", 1000, 0.10);
        repository.save(customer);
        repository.delete(customer.getId());
        Optional<VIPCustomer> deletedCustomer = repository.findById(customer.getId());
        assertTrue(deletedCustomer.isEmpty());
    }

    @Test
    public void testFindById() {
        VIPCustomer customer = new VIPCustomer(null, "John Smith", 1000, 0.10);
        repository.save(customer);
        Optional<VIPCustomer> foundCustomer = repository.findById(customer.getId());
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Smith", foundCustomer.get().getName());
        assertEquals(1000, foundCustomer.get().getAnnualPurchases());
        assertEquals(0.10, foundCustomer.get().getDiscountRate());
    }

    @Test
    public void testFindByName() {
        VIPCustomer customer = new VIPCustomer(null, "John Smith", 1000, 0.10);
        repository.save(customer);
        List<VIPCustomer> foundCustomers = repository.findByName("John Doe");
        assertTrue(foundCustomers.isEmpty());
        foundCustomers = repository.findByName("John Smith");
        assertEquals("John Smith", foundCustomers.get(0).getName());
    }

    @Test
    public void testFindAll() {
        List<VIPCustomer> customers = repository.findAll();
        assertTrue(customers.isEmpty());
    }
}
