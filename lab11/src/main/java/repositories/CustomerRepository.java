package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Customer;

public class CustomerRepository {

    private final EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Customer> save(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return Optional.of(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    public void delete(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
    }

    public Optional<Customer> findById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        return Optional.ofNullable(customer);
    }

    public List<Customer> findByName(String name) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :name", Customer.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }
}