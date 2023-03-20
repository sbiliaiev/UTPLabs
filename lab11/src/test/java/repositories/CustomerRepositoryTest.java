package repositories;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import models.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class CustomerRepositoryTest {

    private EntityManager entityManager;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Customers");
        entityManager = emf.createEntityManager();
        customerRepository = new CustomerRepository(entityManager);
    }

    @Test
    public void testSave() {
        Customer customer = new Customer(1L, "John Doe", 10000);
        Optional<Customer> savedCustomer = customerRepository.save(customer);
        assertTrue(savedCustomer.isPresent());
        assertEquals(customer, savedCustomer.get());
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer(1L, "John Doe", 10000);
        customer = customerRepository.update(customer);
        assertNotNull(customer);
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testDelete() {
        customerRepository.delete(1L);
        Optional<Customer> deletedCustomer = customerRepository.findById(1L);
        assertFalse(deletedCustomer.isPresent());
    }

    @Test
    public void testFindById() {
        Optional<Customer> foundCustomer = customerRepository.findById(1L);
        assertTrue(foundCustomer.isPresent());
        assertEquals(1L, foundCustomer.get().getId());
    }

    @Test
    public void testFindByName() {
        List<Customer> foundCustomers = customerRepository.findByName("John Doe");
        assertFalse(foundCustomers.isEmpty());
        assertEquals("John Doe", foundCustomers.get(0).getName());
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = customerRepository.findAll();
        assertTrue(customers.isEmpty());
    }
}
