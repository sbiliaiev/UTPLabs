package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Customer;
import models.Product;

public class ProductRepository {

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Product> save(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return Optional.of(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Product update(Product product) {
        return entityManager.merge(product);
    }

    public void delete(Long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    public Optional<Product> findById(Long id) {
        Product product = entityManager.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    public List<Product> findByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT c FROM Product c WHERE c.name = :name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT c FROM Product c", Product.class);
        return query.getResultList();
    }
}
