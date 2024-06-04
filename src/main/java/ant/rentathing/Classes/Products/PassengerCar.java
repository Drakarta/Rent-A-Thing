package ant.rentathing.Classes.Products;

import ant.rentathing.Classes.Product;
import ant.rentathing.Classes.User;

import java.util.List;

public class PassengerCar extends Product {
    String brand;
    double weight;
    int engineCapacity;

    public PassengerCar(String description, boolean availability, List<String> customer, User employee, String brand, double weight, int engineCapacity) {
        super(description, 50, 0.01 * engineCapacity, availability, customer, employee);
        this.brand = brand;
        this.weight = weight;
        this.engineCapacity = engineCapacity;
    }
}
