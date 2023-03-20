package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Order;
import models.OrderItem;
import models.Product;

public class OrderItemRepository {
    private final EntityManager entityManager;

    public OrderItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<OrderItem> save(OrderItem orderItem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(orderItem);
            entityManager.getTransaction().commit();
            return Optional.of(orderItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public OrderItem update(OrderItem orderItem) {
        return entityManager.merge(orderItem);
    }

    public void delete(Long id) {
        OrderItem orderItem = entityManager.find(OrderItem.class, id);
        entityManager.remove(orderItem);
    }

    public Optional<OrderItem> findById(Long id) {
        OrderItem orderItem = entityManager.find(OrderItem.class, id);
        return Optional.ofNullable(orderItem);
    }

    public List<OrderItem> findByOrder(Order order) {
        TypedQuery<OrderItem> query = entityManager.createQuery("SELECT oi FROM OrderItem oi WHERE oi.order = :order", OrderItem.class);
        query.setParameter("order", order);
        return query.getResultList();
    }

    public List<OrderItem> findByProduct(Product product) {
        TypedQuery<OrderItem> query = entityManager.createQuery("SELECT oi FROM OrderItem oi WHERE oi.product = :product", OrderItem.class);
        query.setParameter("product", product);
        return query.getResultList();
    }

    public List<OrderItem> findAll() {
        TypedQuery<OrderItem> query = entityManager.createQuery("SELECT oi FROM OrderItem oi", OrderItem.class);
        return query.getResultList();
    }
}
