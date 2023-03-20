package models;

import javax.persistence.Entity;
import javax.persistence.Table;
//import org.hibernate.annotations.Table;

@Entity
@Table(name = "VIPCustomer")
public class VIPCustomer extends Customer {

    private double discountRate;

    public VIPCustomer(Long id, String name, double annualPurchases, double discountRate) {
        super(id, name, annualPurchases);
        this.discountRate = discountRate;
    }

    public VIPCustomer() {

    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "VIPCustomer{" + "discountRate=" + discountRate + "} " + super.toString();
    }
}
