package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import org.hibernate.annotations.Table;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double annualPurchases;

    public Customer(Long id, String name, double annualPurchases) {
        this.id = null;
        this.name = name;
        this.annualPurchases = annualPurchases;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAnnualPurchases() {
        return annualPurchases;
    }

    public void setAnnualPurchases(double annualPurchases) {
        this.annualPurchases = annualPurchases;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", annualPurchases=" + annualPurchases + '}';
    }
}
