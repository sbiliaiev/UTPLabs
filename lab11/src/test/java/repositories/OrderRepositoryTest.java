package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Customer;
import models.Order;
import models.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderRepositoryTest {

    private EntityManager entityManager;
    private OrderRepository repository;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Customers");
        entityManager = emf.createEntityManager();
        repository = new OrderRepository(entityManager);
    }

    @Test
    void testSave() {
        Order order = new Order(1L, null, null);
        Optional<Order> savedOrder = repository.save(order);
        assertTrue(savedOrder.isPresent());
        assertEquals(order, savedOrder.get());
    }

    @Test
    void testUpdate() {
        Order order = new Order(1L, List.of(), new Customer());
        repository.save(order);
        order.setItems(List.of(new OrderItem()));

        Order updatedOrder = repository.update(order);

        assertNotNull(updatedOrder);
        assertEquals(1, updatedOrder.getItems().size());
    }

    @Test
    void testDelete() {
        Order order = new Order(1L, null, null);
        repository.save(order);
        repository.delete(order.getId());
        Optional<Order> result = repository.findById(order.getId());
        assertFalse(result.isPresent());
    }

    @Test
    void testFindById() {
        Order order = new Order(1L, null, null);
        repository.save(order);

        Optional<Order> result = repository.findById(order.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void testFindByCustomer() {
        Customer customer = new Customer(1L, "John Doe", 10000.0);
        Order order1 = new Order(1L, null, customer);
        Order order2 = new Order(2L, null, customer);
        OrderRepository repository = new OrderRepository(entityManager);
        repository.save(order1);
        repository.save(order2);

        List<Order> result = repository.findByCustomer(customer);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }

    @Test
    void testFindAll() {
        List<Order> results = repository.findAll();
        assertTrue(results.isEmpty());
    }
}