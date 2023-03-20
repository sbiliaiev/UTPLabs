package repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.VIPCustomer;

public class VIPCustomerRepository {

    private final EntityManager entityManager;

    public VIPCustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<VIPCustomer> save(VIPCustomer customer) {
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

    public VIPCustomer update(VIPCustomer customer) {
        return entityManager.merge(customer);
    }

    public void delete(Long id) {
        VIPCustomer customer = entityManager.find(VIPCustomer.class, id);
        entityManager.remove(customer);
    }

    public Optional<VIPCustomer> findById(Long id) {
        VIPCustomer customer = entityManager.find(VIPCustomer.class, id);
        return Optional.ofNullable(customer);
    }

    public List<VIPCustomer> findByName(String name) {
        TypedQuery<VIPCustomer> query = entityManager.createQuery("SELECT c FROM VIPCustomer c WHERE c.name = :name",
            VIPCustomer.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<VIPCustomer> findAll() {
        TypedQuery<VIPCustomer> query = entityManager.createQuery("SELECT c FROM VIPCustomer c", VIPCustomer.class);
        return query.getResultList();
    }
}
