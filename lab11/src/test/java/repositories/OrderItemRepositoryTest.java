package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Customer;
import models.Order;
import models.OrderItem;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemRepositoryTest {

    private EntityManager entityManager;
    private OrderItemRepository repository;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Customers");
        entityManager = emf.createEntityManager();
        repository = new OrderItemRepository(entityManager);
    }

    @Test
    void testSave() {
        OrderItem order = new OrderItem(1L, 2, null, null);
        Optional<OrderItem> savedOrder = repository.save(order);
        assertTrue(savedOrder.isPresent());
        assertEquals(order, savedOrder.get());
    }

    @Test
    void testUpdate() {
        OrderItem order = new OrderItem(1L, 2, null, null);
        repository.save(order);
        order.setCount(4);

        OrderItem updatedOrder = repository.update(order);

        assertNotNull(updatedOrder);
        assertEquals(4, updatedOrder.getCount());
    }

    @Test
    void testDelete() {
        OrderItem order = new OrderItem(1L, 2, null, null);
        repository.save(order);
        repository.delete(order.getId());
        Optional<OrderItem> result = repository.findById(order.getId());
        assertFalse(result.isPresent());
    }

    @Test
    void testFindById() {
        OrderItem order = new OrderItem(1L, 2, null, null);
        repository.save(order);

        Optional<OrderItem> result = repository.findById(order.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void testFindByProduct() {
        Product product = new Product(1L, "Apple");
        OrderItem order1 = new OrderItem(1L, 2, null, product);
        OrderItem order2 = new OrderItem(2L, 3, null, product);
        repository.save(order1);
        repository.save(order2);

        List<OrderItem> result = repository.findByProduct(product);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }

    @Test
    void testFindByOrder() {
        Order order = new Order(1L,null, null);
        OrderItem order1 = new OrderItem(1L, 2, order, null);
        OrderItem order2 = new OrderItem(2L, 3, order, null);
        repository.save(order1);
        repository.save(order2);

        List<OrderItem> result = repository.findByOrder(order);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }

    @Test
    void testFindAll() {
        List<OrderItem> results = repository.findAll();
        assertTrue(results.isEmpty());
    }

}