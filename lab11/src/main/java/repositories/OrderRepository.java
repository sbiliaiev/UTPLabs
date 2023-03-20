package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Customer;
import models.Order;

public class OrderRepository {

    private final EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Order> save(Order order) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
            return Optional.of(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Order update(Order order) {
        return entityManager.merge(order);
    }

    public void delete(Long id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
    }

    public Optional<Order> findById(Long id) {
        Order order = entityManager.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    public List<Order> findByCustomer(Customer customer) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer = :customer", Order.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }

    public List<Order> findAll() {
        TypedQuery<Order> query = entityManager.createQuery("SELECT c FROM Order c", Order.class);
        return query.getResultList();
    }
}
