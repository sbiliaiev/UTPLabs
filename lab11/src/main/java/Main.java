import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Customer;
import models.Product;
import models.VIPCustomer;
import repositories.CustomerRepository;
import repositories.ProductRepository;
import repositories.VIPCustomerRepository;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Customers");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ProductRepository productRepository = new ProductRepository(entityManager);
        Product product = new Product(123l, "apple");
        productRepository.save(product);
        System.out.println(productRepository.findAll());

        CustomerRepository customerRepository = new CustomerRepository(entityManager);
        Customer customer = new Customer(123L, "Some Name", 199.23);
        Optional<Customer> savedCustomer = customerRepository.save(customer);
        System.out.println("Saved -> " + savedCustomer.get());
//
//        VIPCustomer vipmcustomer = new VIPCustomer(123L, "Some Name", 199.23, 0.03);
//        VIPCustomerRepository vipCustomerRepository = new VIPCustomerRepository(entityManager);
//        Optional<VIPCustomer> savedVipCustomer = vipCustomerRepository.save(vipmcustomer);
//        System.out.println("Saved VIP -> " + savedVipCustomer.get());
//        System.out.println(vipCustomerRepository.findAll());
//
//
        System.out.println(customerRepository.findAll());
//        System.out.println(customerRepository.findByName("Some Name"));
//        System.out.println(customerRepository.findById(123L));

    }
}
