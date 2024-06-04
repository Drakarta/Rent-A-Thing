package ant.rentathing.Classes;

import java.util.List;
import java.util.UUID;

public abstract class Product {
    UUID id;
    String description;
    double pricePerDay; // PassengerCar 50 euro/day,        Truck 0.10 euro/kg payload/day, Drill 5 euro/day
    double insurancePerDay;   // PassengerCar 0.01 euro/cc/day,   Truck 0.01 euro/cc/day,         Drill 1 euro/day
    boolean availability;
    List<String> customer; //first- and lastname
    User employee;

    protected Product(String description, double pricePerDay, double insurancePerDay, boolean availability, List<String> customer, User employee){
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.insurancePerDay = insurancePerDay;
        this.availability = availability;
        this.customer = customer;
        this.employee = employee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getInsurancePerDay() {
        return insurancePerDay;
    }

    public void setInsurancePerDay(double insurancePerDay) {
        this.insurancePerDay = insurancePerDay;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<String> getCustomer() {
        return customer;
    }

    public void setCustomer(List<String> customer) {
        this.customer = customer;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }
}
